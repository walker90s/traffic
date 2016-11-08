package com.tt.traffic.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.geotools.data.shapefile.ShpFiles;
import org.geotools.data.shapefile.shp.ShapefileReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.tt.traffic.domain.model.TrafficModel;
import com.tt.traffic.domain.model.User;
import com.tt.traffic.service.TrafficModelService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;


/**
 * Created by songyang on 2015/10/14.
 */
@Controller
public class ModelController extends ApplicationController<TrafficModel>{
    @Resource
    private TrafficModelService trafficModelService;

    /**
     * 返回�?有模型数据，包括宏观、中观�?�微�?
     * @param response
     */
    @RequestMapping("/getModelAllJSONArray")
    public void getModelAllJSONArray(HttpServletResponse response){
        List<TrafficModel> modelList = trafficModelService.getModelAllList();

        String jsonArrayString = JSONArray.toJSONString(modelList);
        out(response, jsonArrayString);
    }

    /**
     * 获取结果展示页地图展示数�?
     * 包括宏观模型结果数据，但是不包括中观和微观结果数�?
     * @param response
     * @param type
     * @param resultId
     */
    @RequestMapping("/getModelResult")
    public void getModelResult(HttpServletResponse response, Integer type, Integer resultId, Integer modelId){
        JSONArray resultArray = trafficModelService.getModelResult(type, resultId, modelId);

        String jsonArrayString = resultArray.toJSONString();
        out(response, jsonArrayString);
    }

    /**
     * 根据模型类型（中观模型和微观模型）和结果id返回结果信息
     * @param response
     * @param type 模型类型
     * @param roadId 道路id
     */
    @RequestMapping("/getModelResultByTypeAndId")
    public void getModelResultByTypeAndId(HttpServletResponse response,
                                          Integer type, Integer roadId, Integer resultId, Byte aorb){
        JSONArray resultArray = trafficModelService.getModelResultByTypeAndRoadId(type, roadId, resultId, aorb);

        String jsonArrayString = resultArray.toJSONString();
        out(response, jsonArrayString);
    }

    @RequestMapping("/modelmanage")
    public String modelmanage(HttpServletResponse response,Model model,HttpSession session){
        List<TrafficModel> modelList = trafficModelService.getModelList();
        TrafficModel model2 = new TrafficModel();
        if(modelList!=null && modelList.size()>0){
            List<TrafficModel> model3 = trafficModelService.getListByname(modelList.get(0).getName());
            model2 = model3.get(0);
        }
        User user = (User)session.getAttribute("sessionUser");
        if(user!=null){
            model.addAttribute("type", user.getType());
            model.addAttribute("username", user.getName());
        } else {
            model.addAttribute("type", null);
        }
        model.addAttribute("TrafficModel", model2);
        model.addAttribute("modelList", modelList);
        return "modelmanage";
    }

    @RequestMapping("/getModelByname")
    public void getModelByname(HttpServletResponse response,@RequestParam String name){
        List<TrafficModel> modelList = trafficModelService.getListByname(name);
        JSONArray arr = (JSONArray)JSONArray.toJSON(modelList);
        PrintWriter out;
        try {
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();
            String json = arr.toString();
            out.write(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateFilePath")//
    public void updateFilePath(HttpServletResponse response,TrafficModel model){
        String data = "";
        if(trafficModelService.updatePath(model)!=0){
            data = "ok";
        }
        PrintWriter out;
        try {
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();
            out.write(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
	 * 
	* @Title: updateSLDStyle 
	* @Description: 根据选择的sld文件，返回sld文件内容
	* @param @param response
	* @param @param request
	* @param @param sldFile    
	* @return void    
	* @throws
	 */
	@RequestMapping(value="/uploadSHP")
	public void updateSLDStyle(HttpServletResponse response, HttpServletRequest request, @RequestParam MultipartFile shpfile){
		Writer out = null;
		String fileName=null;
		JSONArray resArray = new JSONArray();
		try{
			String savePath = request.getRealPath("/upload/");
			savePath += "/";
//			for(int i=0; i<10; i++){
//				savePath += SecCodeController.randomChar();
//			}
			
			File file = new File(savePath);
			//判断上传文件的保存目录是否存在
			if (!file.exists() && !file.isDirectory()) {
				System.out.println(savePath+"目录不存在，需要创建");
				//创建目录
				file.mkdirs();
			}
			
			//获取item中的上传文件的输入流
			InputStream in = shpfile.getInputStream();
			fileName = shpfile.getOriginalFilename();
			savePath +=fileName;
			//创建一个文件输出流
			FileOutputStream fout = new FileOutputStream(savePath);
			//创建一个缓冲区
			byte buffer[] = new byte[1024];
			//判断输入流中的数据是否已经读完的标识
			int len = 0;
			//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
			while((len=in.read(buffer))>0){
			 //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
				fout.write(buffer, 0, len);
			}
			fout.flush();
			fout.close();
			//关闭输入流
			in.close();
			/*=======上传文件结束=====*/
			
			//解压上传的文件
//			String shpPath = testZipToUnzip(savePath,file);			
//			try {  
//			    ShpFiles sf = new ShpFiles(shpPath);  
//			    ShapefileReader r = new ShapefileReader( sf, false, false, new GeometryFactory() );  
//			    while (r.hasNext()) {  
//			        Geometry shape = (Geometry) r.nextRecord().shape();  //com.vividsolutions.jts.geom.Geometry;  
//			        Coordinate[] cs = shape.getCoordinates();
//			        for (Coordinate coordinate : cs) {
//			        	String coord = coordinate.toString();
//			        	String[] ss = coord.split(",");
//			    		String jd = ss[0];
//			    		jd = jd.substring(1);
//			    		String wd = ss[1];
//			    		String jwd = jd+","+wd.trim();
//			    		resArray.add(jwd);
//					}
//			        resArray.add("?");
//			    }
//			    r.close();  
//			    sf.delete();
//			} catch (Exception e) {  
//			    e.printStackTrace();  
//			} 
			
			
//			//删除上传的文件
//			file = new File(filepath);
//			File dirFile = file.getParentFile();
//	        file.delete();
//	        dirFile.delete();
	        //返回结果  
			response.setCharacterEncoding("utf8");
			out = response.getWriter();
			resArray.add(fileName);
			out.write(resArray.toString());
			//out.write(fileName);
			out.flush();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * 解压文件
	 * @param zipPath 压缩包路径
	 * @param file 指定文件路径
	 * @return shp文件路径
	 */
	public String testZipToUnzip(String zipPath,File file){
		String path = "";
    	try {
    	  ZipFile zipfile = new ZipFile(new File(zipPath));
    	  //将一个压缩文件zipfile(可以包含文件)解压到file文件中
    	  ZipEntry ze=null;
    	  File outfile;
    	  InputStream zis=null;
    	  FileOutputStream fos=null;
    	  Enumeration e = zipfile.entries();
    	  while(e.hasMoreElements()){
    		  ze=(ZipEntry) e.nextElement();
    		  outfile=new File(file+File.separator+ze.getName());   
    		  if(ze.toString().length()!=(ze.getName()).lastIndexOf('/')+1){
    			//这句话的作用：如果ze不是一个目录则执行下面的语句，也就是创建输出文件并且将输入流输出到输出流中。
    			  //上面的if语句作用和该语句作用一样
    			  if(!ze.isDirectory()){
    				  zis=zipfile.getInputStream(ze);   
    				  System.out.println(outfile.getPath());
    				  String name = outfile.getPath();
    				  name = name.substring(name.length()-1,name.length());
    				  if(name.equals("p")){
    					  path = outfile.getPath();
    				  }
    				  if(!outfile.exists()){
    					  //如果输出文件不存在,那么创建输出文件的父目录
    					  outfile.getParentFile().mkdirs();    
    					  fos=new FileOutputStream(outfile);     
    					  byte[] b=new byte[1024];
    					  int len;
    					  while((len=zis.read(b))>0){
    					  		fos.write(b, 0, len);
    					  }
    					  fos.close();
    					  zis.close();
    				   }	  
    			  	}
    		  	}
    	  	}
    	} catch (ZipException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 } catch (IOException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 }
    	return path;
    }
    
}
