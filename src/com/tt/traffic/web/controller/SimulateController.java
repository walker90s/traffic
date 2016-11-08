package com.tt.traffic.web.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.common.util.Const;
import com.tt.traffic.common.util.TimeUtil;
import com.tt.traffic.domain.model.TrafficModel;
import com.tt.traffic.domain.model.TrafficModelBigResult;
import com.tt.traffic.domain.model.TrafficModelInterResultApproach;
import com.tt.traffic.domain.model.TrafficModelInterResultInt;
import com.tt.traffic.domain.model.TrafficModelInterResultTurn;
import com.tt.traffic.domain.model.TrafficModelMatlab;
import com.tt.traffic.domain.model.TrafficModelMatlabResult;
import com.tt.traffic.domain.model.TrafficModelMiniResult;
import com.tt.traffic.domain.model.TrafficSimulateBase;
import com.tt.traffic.domain.model.User;
import com.tt.traffic.service.ITrafficSimulateBaseService;
import com.tt.traffic.service.TrafficModelMatlabService;
import com.tt.traffic.service.TrafficModelService;
import com.tt.traffic.service.UserService;
import com.tt.traffic.service.common.Pagetor;

@Controller
public class SimulateController extends ApplicationController<TrafficSimulateBase>{
    @Resource
    private ITrafficSimulateBaseService trafficSimulateBaseService;
	@Resource
	private UserService userService;

    @Resource
    private TrafficModelService trafficModelService;
    
    @Resource
    private TrafficModelMatlabService trafficModelMatlabService;
    
    public static List<TrafficModel> modelList;
    
    
    @RequestMapping("/page3")
    public String showData(HttpServletRequest request,Model model,HttpSession session){
    	String pageNo = request.getParameter("pageNo");
    	String pageSize = request.getParameter("pageSize");
    	String modelType = request.getParameter("modelType");
    	
    	if(StringUtils.isBlank(modelType))
    		modelType = "2";
    	
    	Map<String, String> conditions = new HashMap<String, String>();

    	if("1".equals(modelType)){//本地数据导入
    		model.addAttribute("modelType", "1");
    		conditions.put("local", "1");
    		Pagetor<TrafficSimulateBase> pagetor = trafficSimulateBaseService.pagetor(pageNo, pageSize, conditions);
        	model.addAttribute("page", pagetor);
    		
    	}else if("2".equals(modelType)){//显示服务器模�?
    		conditions.put("local", "2");
        	Pagetor<TrafficSimulateBase> pagetor = trafficSimulateBaseService.pagetor(pageNo, pageSize, conditions);
        	model.addAttribute("page", pagetor);
        	model.addAttribute("modelType", "2");
    	}
		User user = (User)session.getAttribute("sessionUser");
		if(user!=null){
			model.addAttribute("type", user.getType());
			model.addAttribute("username", user.getName());
		} else {
			model.addAttribute("type", null);
		}
        return "page3";
    }
    
    @RequestMapping("/model/turnPage")
    @ResponseBody
    public Pagetor<TrafficSimulateBase> turnPageData(HttpServletRequest request,Model model){
    	String pageNo = request.getParameter("pageNo");
    	String modelType = request.getParameter("modelType");
    	
    	if(StringUtils.isBlank(modelType))
    		modelType = "1";
    	
    	Map<String, String> conditions = new HashMap<String, String>();
    	
    	if("1".equals(modelType)){//本地数据导入
    		conditions.put("local", "1");
    		return trafficSimulateBaseService.pagetor(pageNo, null, conditions);
    		
    	}else if("2".equals(modelType)){//显示服务器模�?
    		conditions.put("local", "2");
        	return trafficSimulateBaseService.pagetor(pageNo, null, conditions);
    	}
    	return null;
    }
    

    public List<TrafficModel> getModelList(){
    	return trafficModelService.getModelList();
    }
 
    @RequestMapping("/model/local/uploadModeInfo")
    @ResponseBody
    public Pagetor<TrafficSimulateBase> uploadModelInfo(HttpServletRequest request,@RequestBody String content,Model model){
    	Pagetor<TrafficSimulateBase> pagetor = new Pagetor<TrafficSimulateBase>();
    	
    	String id = request.getParameter("id");
    	JSONObject json =  JSONObject.parseObject(content);
		String comment = json.getString("comment");
    	
    	if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
    		TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
        	if(base != null && base.getLocal() == 1){
        		base.setLocal(2);
        		base.setComment(comment);
        		trafficSimulateBaseService.updateByPrimaryKey(base);
        	}
    	}
    	
    	return pagetor;
    }
    
    @RequestMapping("/model/local/deleteModeInfo")
    @ResponseBody
    public Pagetor<TrafficSimulateBase> deleteModelInfo(HttpServletRequest request){
    	Pagetor<TrafficSimulateBase> pagetor = new Pagetor<TrafficSimulateBase>();
    	
    	String id = request.getParameter("id");
    	
    	if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
    		TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
        	if(base != null && base.getLocal() == 1){
        		trafficSimulateBaseService.deleteById(Integer.parseInt(id));
        	}
    	}
    	
    	return pagetor;
    }
    
    @RequestMapping("/model/local/uploadFile")
    @ResponseBody
    public Pagetor<TrafficSimulateBase> uploadModelFile(@RequestBody String content,HttpServletRequest request,Model model){
    	try{
    		JSONObject json =  JSONObject.parseObject(content);
    		String softId = json.getString("softId");
    		String softName = json.getString("softName");
    		Integer modelType = json.getInteger("modelType");
    		Integer modelId = json.getInteger("modelId");
    		String modelName = json.getString("modelName");
    		String time = json.getString("time");
    		String startTime = json.getString("startTime");
    		String endTime = json.getString("endTime");

    		JSONArray results = json.getJSONArray("result");
        		
    		TrafficSimulateBase baseResult = new TrafficSimulateBase();
    		baseResult.setName(modelName);
    		baseResult.setSimulateSoftId(softId);
    		baseResult.setSimulateSoft(softName);
    		baseResult.setLocal(1);
    		baseResult.setSimulateModelId(modelId);
    		baseResult.setSimulateModel(modelName);
    		
    		try{
    			baseResult.setSimulateTime(TimeUtil.getTimestamp(time).getTime());
    		}catch(Exception e){
    			
    		}
    		try{
    			baseResult.setSimulateStartTime(TimeUtil.getTimestamp(startTime).getTime());
    		}catch(Exception e){
    			
    		}
    		try{
    			baseResult.setSimulateEndTime(TimeUtil.getTimestamp(endTime).getTime());
    		}catch(Exception e){
    			
    		}
    		
    		baseResult.setImportTime(new Date().getTime());
    		

	    	
			if(modelType == Const.MODEL_TYPE.MACRO){
				processMacroData(baseResult, results);
			}else if(modelType == Const.MODEL_TYPE.MEDIUM){
				processMediumData(baseResult, results);
			}else if(modelType == Const.MODEL_TYPE.MICRO){
				processMicroData(baseResult, results);
			}else if(modelType == Const.MODEL_TYPE.INTERSECTION){
				processIntersectionData(baseResult, results);
			}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		
    	}
    	Pagetor<TrafficSimulateBase> pagetor = new Pagetor<TrafficSimulateBase>();
    	return pagetor;
    }
    
    private void processMacroData(TrafficSimulateBase baseResult,JSONArray results) throws Exception{
    	JSONObject result = results.getJSONObject(0);
//    	String fileName = result.getString("fileName");
    	String content = result.getString("fileData");
    	int data_index = content.indexOf("base64") + 7;
    	byte[] buffer = new BASE64Decoder().decodeBuffer(content.substring(data_index));
    	
    	Workbook book = WorkbookFactory.create(new ByteArrayInputStream(buffer));
    	List<TrafficModelBigResult> resultList = new ArrayList<TrafficModelBigResult>();
    	Sheet sheet = book.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();	// 总行�?
		for(int i=0;i<totalRows;i++){
			Row row = sheet.getRow(i);
			if(row == null)
				continue;
			
			TrafficModelBigResult bigResult = convertModelResult(row);
			if(bigResult == null)
				continue;
			resultList.add(bigResult);
		}
		
		trafficSimulateBaseService.addSimulateBaseMac(baseResult, resultList);
    }
    
    private TrafficModelBigResult convertModelResult(Row row){
    	
    	TrafficModelBigResult result = new TrafficModelBigResult();
  
    	result.setId((int)getCellValueFloat(row.getCell(0)));
    	result.setAbFlow(getCellValueFloat(row.getCell(1)));
    	result.setBaFlow(getCellValueFloat(row.getCell(2)));
    	result.setTotFlow(getCellValueFloat(row.getCell(3)));
    	result.setAbTime(getCellValueFloat(row.getCell(4)));
    	result.setBaTime(getCellValueFloat(row.getCell(5)));
    	result.setMaxTime(getCellValueFloat(row.getCell(6)));
    	result.setAbVoc(getCellValueFloat(row.getCell(7)));
    	result.setBaVoc(getCellValueFloat(row.getCell(8)));
    	result.setMaxVoc(getCellValueFloat(row.getCell(9)));
    	result.setAbVkmt(getCellValueFloat(row.getCell(10)));
    	result.setBaVkmt(String.valueOf(getCellValueFloat(row.getCell(11))));
    	result.setTotVkmt(getCellValueFloat(row.getCell(12)));
    	result.setAbVht(getCellValueFloat(row.getCell(13)));
    	result.setBaVht(getCellValueFloat(row.getCell(14)));
    	result.setTotVht(getCellValueFloat(row.getCell(15)));
    	result.setAbSpeed(getCellValueFloat(row.getCell(16)));
    	result.setBaSpeed(getCellValueFloat(row.getCell(17)));
    	result.setAbVdf(getCellValueFloat(row.getCell(18)));
    	result.setBaVdf(getCellValueFloat(row.getCell(19)));
    	result.setMaxVdf(getCellValueFloat(row.getCell(20)));
    	
    	return result;
    }

    private void processMediumData(TrafficSimulateBase baseResult,JSONArray results) throws Exception{
    	JSONObject result1 = results.getJSONObject(0);
    	byte[] buffer1 = null;
    	byte[] buffer2 = null;
    	byte[] buffer3 = null;
    	
    	String fileName = result1.getString("fileName");
    	String content1 = result1.getString("fileData");
    	int data_index1 = content1.indexOf("base64") + 7;
    	if(fileName.indexOf("Matlab_density") >= 0){
    		buffer1 = new BASE64Decoder().decodeBuffer(content1.substring(data_index1));
    	}else if(fileName.indexOf("Matlab_flow") >= 0){
    		buffer2 = new BASE64Decoder().decodeBuffer(content1.substring(data_index1));
    	}else{
    		buffer3 = new BASE64Decoder().decodeBuffer(content1.substring(data_index1));
    	}
    	
    	
    	
    	JSONObject result2 = results.getJSONObject(1);
    	fileName = result2.getString("fileName");
    	String content2 = result2.getString("fileData");
    	int data_index2 = content2.indexOf("base64") + 7;
    	if(fileName.indexOf("Matlab_density") >= 0){
    		buffer1 = new BASE64Decoder().decodeBuffer(content2.substring(data_index2));
    	}else if(fileName.indexOf("Matlab_flow") >= 0){
    		buffer2 = new BASE64Decoder().decodeBuffer(content2.substring(data_index2));
    	}else{
    		buffer3 = new BASE64Decoder().decodeBuffer(content2.substring(data_index2));
    	}
    	
    	
    	JSONObject result3 = results.getJSONObject(2);
    	fileName = result3.getString("fileName");
    	String content3 = result3.getString("fileData");
    	int data_index3 = content2.indexOf("base64") + 7;
    	if(fileName.indexOf("Matlab_density") >= 0){
    		buffer1 = new BASE64Decoder().decodeBuffer(content3.substring(data_index3));
    	}else if(fileName.indexOf("Matlab_flow") >= 0){
    		buffer2 = new BASE64Decoder().decodeBuffer(content3.substring(data_index3));
    	}else{
    		buffer3 = new BASE64Decoder().decodeBuffer(content3.substring(data_index3));
    	}
    	
    	processMediumData(baseResult, buffer1, buffer2, buffer3);
 
    }
    
    private void processMediumData(TrafficSimulateBase baseResult,byte[] buffer1,byte[] buffer2,byte[] buffer3) throws Exception{
    	List<TrafficModelMatlab> defList = trafficModelMatlabService.getMatlabListByModelId(baseResult.getSimulateModelId());
    	if(defList == null)
    		return;
    	Map<String,Map<String, TrafficModelMatlabResult>> roadMap = new HashMap<String,Map<String, TrafficModelMatlabResult>>();
    	
    	
    	Map<Integer,TrafficModelMatlab> densityMap = new HashMap<Integer,TrafficModelMatlab>();
    	Map<Integer,TrafficModelMatlab> flowSpeedMap = new HashMap<Integer,TrafficModelMatlab>();
    	
    	for(int i=0;i<defList.size();i++){
    		TrafficModelMatlab matlab = defList.get(i);
    		densityMap.put(matlab.getDensityLinkId(), matlab);
    		flowSpeedMap.put(matlab.getFlowSpeedLinkId(), matlab);
    	}

		List<TrafficModelMatlabResult> resultList = new ArrayList<TrafficModelMatlabResult>();
		
    	String content1 = new String(buffer1);
    	String[] lines1 = content1.split("\n");
		List<String> lineList1 = Arrays.asList(lines1);
		
		
		for(int rowIndex=0;rowIndex<lineList1.size();rowIndex++){
			String line = lineList1.get(rowIndex);
			String[] values = line.split("\t");
			List<String> valueList = Arrays.asList(values);
			for(int colIndx=0;colIndx < valueList.size();colIndx++){
				String value = valueList.get(colIndx);
				
				
				TrafficModelMatlab matlab = densityMap.get(colIndx);
				if(matlab == null)
					continue;
				
				float density = 0;
				try{
					density = getValueFloat(value);
				}catch(Exception e){
					
				}
				
				Map<String, TrafficModelMatlabResult> roadTimeMap = roadMap.get(matlab.getRoadId());
				if(roadTimeMap == null){
					roadTimeMap = new HashMap<String, TrafficModelMatlabResult>();
					roadMap.put(matlab.getRoadId(), roadTimeMap);
				}
				TrafficModelMatlabResult result = roadTimeMap.get(getTimeString(rowIndex));
				if(result == null){
					result = new TrafficModelMatlabResult();
					result.setRoadId(getValueInteger(matlab.getRoadId()));
					result.setAorb(matlab.getAorb());
					result.setTime(getTimeString(rowIndex));
					result.setDensity(density);
					result.setYn(matlab.getYn());
					resultList.add(result);
					
					roadTimeMap.put(getTimeString(rowIndex), result);
				}else{
					result.setDensity(density);
				}
				
			}
		}
    	
		
    	String content2 = new String(buffer2);
    	
    	String[] lines2 = content2.split("\n");
		List<String> lineList2 = Arrays.asList(lines2);
		
		for(int rowIndex=0;rowIndex<lineList2.size();rowIndex++){
			String line = lineList2.get(rowIndex);
			String[] values = line.split("\t");
			List<String> valueList = Arrays.asList(values);
			for(int colIndx=0;colIndx < valueList.size();colIndx++){
				String value = valueList.get(colIndx);
				
				
				TrafficModelMatlab matlab = flowSpeedMap.get(colIndx);
				if(matlab == null)
					continue;
				
				Integer flow = 0;
				try{
					flow = getValueInteger(value);
				}catch(Exception e){
					
				}
				
				Map<String, TrafficModelMatlabResult> roadTimeMap = roadMap.get(matlab.getRoadId());
				if(roadTimeMap == null){
					roadTimeMap = new HashMap<String, TrafficModelMatlabResult>();
					roadMap.put(matlab.getRoadId(), roadTimeMap);
				}
				TrafficModelMatlabResult result = roadTimeMap.get(getTimeString(rowIndex));
				if(result == null){
					result = new TrafficModelMatlabResult();
					result.setRoadId(getValueInteger(matlab.getRoadId()));
					result.setAorb(matlab.getAorb());
					result.setTime(getTimeString(rowIndex));
					result.setFlow(flow);
					result.setYn(matlab.getYn());
					resultList.add(result);
					
					roadTimeMap.put(getTimeString(rowIndex), result);
				}else{
					result.setFlow(flow);
				}
			}
		}
		
		String content3 = new String(buffer3);
    	
    	String[] lines3 = content3.split("\n");
		List<String> lineList3 = Arrays.asList(lines3);
		
		for(int rowIndex=0;rowIndex<lineList3.size();rowIndex++){
			String line = lineList3.get(rowIndex);
			String[] values = line.split("\t");
			List<String> valueList = Arrays.asList(values);
			for(int colIndx=0;colIndx < valueList.size();colIndx++){
				String value = valueList.get(colIndx);
				
				
				TrafficModelMatlab matlab = flowSpeedMap.get(colIndx);
				if(matlab == null)
					continue;
				
				float speed = 0;
				try{
					speed = getValueFloat(value);
				}catch(Exception e){
					
				}
				
				Map<String, TrafficModelMatlabResult> roadTimeMap = roadMap.get(matlab.getRoadId());
				if(roadTimeMap == null){
					roadTimeMap = new HashMap<String, TrafficModelMatlabResult>();
					roadMap.put(matlab.getRoadId(), roadTimeMap);
				}
				TrafficModelMatlabResult result = roadTimeMap.get(getTimeString(rowIndex));
				if(result == null){
					result = new TrafficModelMatlabResult();
					result.setRoadId(getValueInteger(matlab.getRoadId()));
					result.setAorb(matlab.getAorb());
					result.setTime(getTimeString(rowIndex));
					result.setSpeed(speed);
					result.setYn(matlab.getYn());
					resultList.add(result);
					
					roadTimeMap.put(getTimeString(rowIndex), result);
				}else{
					result.setSpeed(speed);
				}
				
			}
		}
		
		trafficSimulateBaseService.addSimulateBaseMatLab(baseResult, resultList);
    }
    
    private String getTimeString(int index){
    	if(index == 0)
    		return "7:00-7:15";
    	
    	if(index == 1)
    		return "7:15-7:30";
    	
    	if(index == 2)
    		return "7:30-7:45";
    	
    	if(index == 3)
    		return "7:45-8:00";
    	
    	if(index == 4)
    		return "8:00-8:15";
    	
    	if(index == 5)
    		return "8:15-8:30";
    	
    	if(index == 6)
    		return "8:30-8:45";
    	
    	if(index == 7)
    		return "8:45-9:00";
    	return "";
    }
    
    private void processMicroData(TrafficSimulateBase baseResult,JSONArray results) throws Exception{
    	JSONObject result1 = results.getJSONObject(0);
    	byte[] bufferGroupAll = null;
    	byte[] bufferStdAll = null;
    	
    	String fileName1 = result1.getString("fileName");
    	String content1 = result1.getString("fileData");
    	int data_index1 = content1.indexOf("base64") + 7;
    	if(fileName1.indexOf("sgl-link-group-all.csv") >= 0){
    		bufferGroupAll = new BASE64Decoder().decodeBuffer(content1.substring(data_index1));
    	}else if(fileName1.indexOf("sgl-turning-std-all.csv") >= 0){
    		bufferStdAll = new BASE64Decoder().decodeBuffer(content1.substring(data_index1));
    	}
    	
//    	JSONObject result2 = results.getJSONObject(1);
//    	String fileName2 = result2.getString("fileName");
//    	String content2 = result2.getString("fileData");
//    	int data_index2 = content2.indexOf("base64") + 7;
//    	if(fileName2.indexOf("sgl-link-group-all.csv") >= 0){
//    		bufferGroupAll = new BASE64Decoder().decodeBuffer(content2.substring(data_index2));
//    	}else if(fileName2.indexOf("sgl-turning-std-all.csv") >= 0){
//    		bufferStdAll = new BASE64Decoder().decodeBuffer(content2.substring(data_index2));
//    	}
    	
    	processMicroData(baseResult, bufferGroupAll, bufferStdAll);
    }
    
    private void processMicroData(TrafficSimulateBase baseResult,byte[] bufferGroupAll,byte[] bufferStdAll) throws Exception{
    	
    	List<TrafficModelMiniResult> resultList = new ArrayList<TrafficModelMiniResult>();
    	
    	String content1 = new String(bufferGroupAll);
    	String[] lines1 = content1.split("\n");
		List<String> lineList1 = Arrays.asList(lines1);
		
		for(int rowIndex=0;rowIndex<lineList1.size();rowIndex++){
			String line = lineList1.get(rowIndex);
			
			TrafficModelMiniResult micMiniResult = convertMicMiniResult(line);
			if(micMiniResult == null)
				continue;
			resultList.add(micMiniResult);
			
		}
		
		trafficSimulateBaseService.addSimulateBaseMicroResult(baseResult, resultList);
    }
    
    private TrafficModelMiniResult convertMicMiniResult(String content){
    	
    	if(StringUtils.isBlank(content))
    		return null;
    	
    	if(content.startsWith("##"))
    		return null;
    	
    	if(content.startsWith("Start Interval"))
    		return null;
    				
    	TrafficModelMiniResult result = null;
    	
    	String[] values = content.split(",");
    	if(values == null || (values.length != 14 && values.length != 15))
    		return null;
    	
    	//Start Interval,End Interval,Link Group,Link Type,Link Count,Link Delay,Link Density,Link Flow,Link (Level Of Service),Service Measure,Custom MOE/LOS Value,Link Speed,Link Travel Time,Link VDT,Link VHT
    	//08:55:00,09:00:00,SB_FR_6,freeway,67.2,0,43.0029,806.4,F,linkDensity,43.002892,49.2819,58.6953,6.86515,0.218
    	if(values.length == 15){//高�??
    		result = new TrafficModelMiniResult();
        	result.setStartInterval(values[0]);
        	result.setEndInterval(values[1]);
        	result.setLinkGroup(values[2]);
        	result.setLinkType(values[3]);
        	result.setLinkCount(getValueFloat(values[4]));
        	result.setLinkDelay(getValueFloat(values[5]));
        	result.setLinkDensity(getValueFloat(values[6]));
        	result.setLinkFlow(getValueFloat(values[7]));
        	result.setLinkLevel(values[8]);
        	result.setServiceMeasure(values[9]);
        	result.setLosValue(getValueFloat(values[10]));
        	result.setLinkSpeed(getValueFloat(values[11]));
        	result.setLinkTravelTime(getValueFloat(values[12]));
        	result.setLinkVdt(getValueFloat(values[13]));
        	result.setLinkVht(getValueFloat(values[14]));
        	
        	return result;
    	}
    	
    	//Start Interval,End Interval,Link Group,Link Type,Link Count,Link Delay,Link Density,Link Flow,Link PTD,Link Speed,Link Stop Time,Link Travel Time,Link VDT,Link VHT
    	//08:00:00,08:05:00,NB_1,multi lane highway,45,45.8805,12.1838,540,196.802,37.0362,12.168,76.2731,7.3882,0.319167
    	if(values.length == 14){//市区
    		result = new TrafficModelMiniResult();
    		result.setStartInterval(values[0]);
        	result.setEndInterval(values[1]);
        	result.setLinkGroup(values[2]);
        	result.setLinkType(values[3]);
        	result.setLinkCount(getValueFloat(values[4]));
        	result.setLinkDelay(getValueFloat(values[5]));
        	result.setLinkDensity(getValueFloat(values[6]));
        	result.setLinkFlow(getValueFloat(values[7]));
        	result.setLinkPtd(getValueFloat(values[8]));
        	result.setLinkSpeed(getValueFloat(values[9]));
        	result.setLinkStopTime(getValueFloat(values[10]));
        	result.setLinkTravelTime(getValueFloat(values[11]));
        	result.setLinkVdt(getValueFloat(values[12]));
        	result.setLinkVht(getValueFloat(values[13]));
        	
        	return result;
    	}

    	return null;
    }
    
    private void processIntersectionData(TrafficSimulateBase baseResult,JSONArray results) throws Exception{

    	JSONObject result1 = results.getJSONObject(0);
    	byte[] bufferApproach = null;
    	byte[] bufferInt = null;
    	byte[] bufferTurn = null;
    	
    	String fileName1 = result1.getString("fileName");
    	String content1 = result1.getString("fileData");
    	int data_index1 = content1.indexOf("base64") + 7;
    	if(fileName1.indexOf("Report_approach.csv") >= 0){
    		bufferApproach = new BASE64Decoder().decodeBuffer(content1.substring(data_index1));
    	}else if(fileName1.indexOf("Report_int.csv") >= 0){
    		bufferInt = new BASE64Decoder().decodeBuffer(content1.substring(data_index1));
    	}else{
    		bufferTurn = new BASE64Decoder().decodeBuffer(content1.substring(data_index1));
    	}
    	
    	JSONObject result2 = results.getJSONObject(1);
    	String fileName2 = result2.getString("fileName");
    	String content2 = result2.getString("fileData");
    	int data_index2 = content2.indexOf("base64") + 7;
    	if(fileName2.indexOf("Report_approach.csv") >= 0){
    		bufferApproach = new BASE64Decoder().decodeBuffer(content2.substring(data_index2));
    	}else if(fileName2.indexOf("Report_int.csv") >= 0){
    		bufferInt = new BASE64Decoder().decodeBuffer(content2.substring(data_index2));
    	}else{
    		bufferTurn = new BASE64Decoder().decodeBuffer(content2.substring(data_index2));
    	}
    	
    	
    	JSONObject result3 = results.getJSONObject(2);
    	String fileName3 = result3.getString("fileName");
    	String content3 = result3.getString("fileData");
    	int data_index3 = content2.indexOf("base64") + 7;
    	if(fileName3.indexOf("Report_approach.csv") >= 0){
    		bufferApproach = new BASE64Decoder().decodeBuffer(content3.substring(data_index3));
    	}else if(fileName3.indexOf("Report_int.csv") >= 0){
    		bufferInt = new BASE64Decoder().decodeBuffer(content3.substring(data_index3));
    	}else{
    		bufferTurn = new BASE64Decoder().decodeBuffer(content3.substring(data_index3));
    	}
    	
    	processIntersectionData(baseResult, bufferApproach, bufferInt, bufferTurn);
    }
    private void processIntersectionData(TrafficSimulateBase baseResult,byte[] bufferApproach,byte[] bufferInt,byte[] bufferTurn) throws Exception{
    
    	List<TrafficModelInterResultApproach> approachResultList = new ArrayList<TrafficModelInterResultApproach>();
    	if(bufferApproach != null){
    		String content1 = new String(bufferApproach);
        	String[] lines1 = content1.split("\n");
    		List<String> lineList1 = Arrays.asList(lines1);
    		for(int rowIndex=0;rowIndex<lineList1.size();rowIndex++){
    			String line = lineList1.get(rowIndex);
    			
    			TrafficModelInterResultApproach intApproachResult = convertInterApproachResult(line);
    			if(intApproachResult == null)
    				continue;
    			approachResultList.add(intApproachResult);
    		}
    	}
    	
    	List<TrafficModelInterResultTurn> turnResultList = new ArrayList<TrafficModelInterResultTurn>();
    	if(bufferTurn != null){
    		String content2 = new String(bufferTurn);
        	String[] lines2 = content2.split("\n");
    		List<String> lineList2 = Arrays.asList(lines2);
    		for(int rowIndex=0;rowIndex<lineList2.size();rowIndex++){
    			String line = lineList2.get(rowIndex);
    			
    			TrafficModelInterResultTurn turnResult = convertInterTurnResult(line);
    			if(turnResult == null)
    				continue;
    			turnResultList.add(turnResult);
    		}
    	}
		
    	List<TrafficModelInterResultInt> intResultList = new ArrayList<TrafficModelInterResultInt>();
    	if(bufferInt != null){
    		String content3 = new String(bufferInt);
        	String[] lines3 = content3.split("\n");
    		List<String> lineList3 = Arrays.asList(lines3);
    		for(int rowIndex=0;rowIndex<lineList3.size();rowIndex++){
    			String line = lineList3.get(rowIndex);
    			
    			TrafficModelInterResultInt turnResult = convertInterIntResult(line);
    			if(turnResult == null)
    				continue;
    			intResultList.add(turnResult);
    		}
    	}
    	
		trafficSimulateBaseService.addSimulateBaseInterResult(baseResult, approachResultList, turnResultList, intResultList);
    }
    
    private TrafficModelInterResultApproach convertInterApproachResult(String content){
    	
    	if(StringUtils.isBlank(content))
    		return null;
    	
    	if(content.startsWith("cross_id"))
    		return null;
    				
    	TrafficModelInterResultApproach result = new TrafficModelInterResultApproach();
    	//cross_id,direction,Approach_Delay,Approach_LOS
    	//3,EB,13.4,B
    	String[] values = content.split(",");
    	if(values == null || values.length != 4)
    		return null;
    	result.setCrossId(getValueInteger(values[0]));
    	result.setDirection(values[1]);
    	result.setApproachDelay(getValueFloat(values[2]));
    	result.setApproachLos(values[3]);
    	return result;
    }
    
    private TrafficModelInterResultTurn convertInterTurnResult(String content){
    	
    	if(StringUtils.isBlank(content))
    		return null;
    	
    	if(content.startsWith("cross_id"))
    		return null;
    				
    	TrafficModelInterResultTurn result = new TrafficModelInterResultTurn();
    	//cross_id,turn,Volume,Flow,vc_Ratio,Control_Delay,Queue_Delay,Total_Delay,LOS
    	//3,EBL,20,22,0.17,27.9,0,27.9,C
    	String[] values = content.split(",");
    	if(values == null || values.length != 9)
    		return null;
    	result.setCrossId(getValueInteger(values[0]));
    	result.setTurn(values[1]);
    	result.setVolume(getValueInteger(values[2]));
    	result.setFlow(getValueInteger(values[3]));
    	result.setVcRatio(getValueFloat(values[4]));
    	result.setControlDelay(getValueFloat(values[5]));
    	result.setQueueDelay(getValueFloat(values[6]));
    	result.setTotalDelay(getValueFloat(values[7]));
    	result.setLos(values[8]);
    	
    	return result;
    }
    
    private TrafficModelInterResultInt convertInterIntResult(String content){
    	
    	if(StringUtils.isBlank(content))
    		return null;
    	
    	if(content.startsWith("cross_id"))
    		return null;
    				
    	TrafficModelInterResultInt result = new TrafficModelInterResultInt();
    	//cross_id,name,cycle_length,offset,control_type,voc,los,delay,icu
    	//3,W Hongtai St,56,0,Pretimed,0.51,B,16,34.70%
    	String[] values = content.split(",");
    	if(values == null || values.length != 9)
    		return null;
    	result.setCrossId(getValueInteger(values[0]));
    	result.setName(values[1]);
    	result.setCycleLength(getValueInteger(values[2]));
    	result.setOffset(getValueInteger(values[3]));
    	result.setControlType(values[4]);
    	result.setVoc(getValueFloat(values[5]));
    	result.setLos(values[6]);
    	result.setDelay(getValueFloat(values[7]));
    	result.setIcu(getValueFloat(values[8]));
    	
    	return result;
    }
    
    private Float getValueFloat(String value){
    	try{
	    	return Float.valueOf(value.toString());
    	}catch(Exception e){
    		
    	}
    	return null;
    }
    
    private Integer getValueInteger(String value){
    	try{
	    	return Integer.valueOf(value.toString());
    	}catch(Exception e){
    		
    	}
    	return null;
    }
    
    private float getCellValueFloat(Cell cell){
    	try{
	    	if(cell == null){
	    		return 0;
	    	}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
				return (float)cell.getNumericCellValue();
			}else if(cell.getCellType() == Cell.CELL_TYPE_STRING){
				return getValueFloat(cell.getStringCellValue());
			}
    	}catch(Exception e){
    		
    	}
    	return 0;
    }
    
    @RequestMapping("/model/delete")
    public String delete(HttpServletRequest request,Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
    		//trafficSimulateBaseService.deleteById(Integer.parseInt(id));
    	}
    	
    	Map<String, String> conditions = new HashMap<String, String>();
    	conditions.put("local", "2");
    	Pagetor<TrafficSimulateBase> pagetor = trafficSimulateBaseService.pagetor("", "", conditions);
    	model.addAttribute("page", pagetor);
    	model.addAttribute("modelType", "2");
    	
        return "redirect:/page3";
    }
	@RequestMapping("/simulate")
	public String Simulate(HttpServletRequest request,HttpSession session,Model model){
		TrafficSimulateBase simulate = new TrafficSimulateBase();
		simulate.setOffset(0);
		simulate.setLimit(10);
		List<TrafficSimulateBase> Simlist = trafficSimulateBaseService.getListSimulate(simulate);
		int count = trafficSimulateBaseService.selectCount();
		int page = (count%10==0)?count/10:count/10+1;

		User us = (User)session.getAttribute("sessionUser");
		if(us==null){
			return "login";
		}
		model.addAttribute("username",us.getName());
		model.addAttribute("Simlist",Simlist);
		model.addAttribute("page",page);
		model.addAttribute("count",count);
		return "simulate";
	}
	@RequestMapping("/usermanage")
	public String Usermanage(HttpServletRequest request,HttpSession session,Model model){
		User user = new User();
		user.setOffset(0);
		user.setLimit(10);
		List<User> userlist = userService.getUserList(user);
		int count = userService.selectCount();
		int page = (count%10==0)?count/10:count/10+1;

		User us = (User)session.getAttribute("sessionUser");
		if(us==null){
			return "login";
		}
		model.addAttribute("username",us.getName());
		model.addAttribute("userlist",userlist);
		model.addAttribute("page",page);
		model.addAttribute("count",count);
		return "usermanage";
	}

	@RequestMapping("/deleteData")
	public void deleteData(HttpServletResponse response,@RequestParam Integer id,@RequestParam String type){
		String data = "";
		if(trafficSimulateBaseService.deleteByType(id,type)){
			data = "ok";
		}else{
			data = "no";
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
	@RequestMapping("/countPage")
	public void countPage(HttpServletResponse response,@RequestParam Integer type){
		int count =0;
		int page = 0;
		if(type==0){
			count = trafficSimulateBaseService.selectCount();
		}else{
			count =  userService.selectCount();
		}
		page = (count%10==0)?count/10:count/10+1;
		String data = page+"";
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
	@RequestMapping("/deleteUser")
	public void deleteUser(HttpServletResponse response,@RequestParam Integer id){
		String data = "";
		if(userService.deleteUser(id)){
			data = "ok";
		}else{
			data = "no";
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
	@RequestMapping("/getSimPage")
	public void getSimPage(HttpServletResponse response,@RequestParam Integer page,@RequestParam Integer type){
		JSONArray arr = null;
		if(type == 0){
			List<TrafficSimulateBase> simlist = new ArrayList<TrafficSimulateBase>();
			TrafficSimulateBase trafficSimulateBase = new TrafficSimulateBase();
			page = (page-1)*10;
			trafficSimulateBase.setOffset(page);
			trafficSimulateBase.setLimit(10);
			simlist = trafficSimulateBaseService.getListSimulate(trafficSimulateBase);
			arr = (JSONArray)JSONArray.toJSON(simlist);
		}else{
			List<User> userlist = new ArrayList<User>();
			User user = new User();
			page = (page-1)*10;
			user.setOffset(page);
			user.setLimit(10);
			userlist = userService.getUserList(user);
			arr = (JSONArray)JSONArray.toJSON(userlist);
		}

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

	@RequestMapping("/ExportExcalUser")
	public void ExportExcalUser(HttpServletResponse response){

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
 	 	String now = format.format(new Date());
        String basePath = System.getProperties().getProperty("user.dir")+File.separator+"Excel"+ File.separator;
  		String exportFileName = "用户信息导出"+now+".xls";//导出文件�?

		List<User> list = userService.ExportExcalUser();
  		HSSFWorkbook workBook = null;
  		String[] cellTitle = { "账号" , "姓名" , "注册时间" , "学号" ,"班级","专业"};
  		try {
   			workBook = new HSSFWorkbook();//创建工作�?
   			HSSFSheet sheet = workBook.createSheet();
   			workBook.setSheetName(0, "人员信息");//工作簿名�?
   			HSSFFont font = workBook.createFont();
   			font.setColor(HSSFFont.COLOR_NORMAL);
   			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
  	 		HSSFCellStyle cellStyle = workBook.createCellStyle();//创建格式
   			cellStyle.setFont(font);
   			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
   			//创建第一行标�?
   			HSSFRow titleRow = sheet.createRow((short) 0);//第一行标�?
   			for(int i = 0,size = cellTitle.length; i < size; i++){//创建�?1行标题单元格
    		switch(i){
    		case 0:
     			sheet.setColumnWidth(0, 3000);
    			 break;
    		case 1:
     			sheet.setColumnWidth(1,4000);
     			break;
    		case 2:
				sheet.setColumnWidth(2,4000);
     			break;
    		case 3:
     			sheet.setColumnWidth(3,4000);
     			break;
			case 4:
				sheet.setColumnWidth(4,4000);
				break;
			case 5:
				sheet.setColumnWidth(5,4000);
				break;
			}
   	 		HSSFCell cell = titleRow.createCell(i,0);
    		cell.setCellStyle(cellStyle);
    		cell.setCellValue(cellTitle[i]);
  	 		}
         	//从第二行�?始写入数�?
  	 		//注：此处如果数据过多，会抛出java.lang.IllegalStateException异常：The maximum number of cell styles was exceeded.
   			//You can define up to 4000 styles in a .xls workbook。这是是由于cell styles太多create造成，故�?般可以把cellstyle设置放到循环外面

   			if(list!=null && !list.isEmpty()){
    		HSSFCellStyle style = workBook.createCellStyle();//创建格式
    		for(int i=0,size=list.size();i<size;i++){
     			User entity = list.get(i);
     			HSSFRow row = sheet.createRow((short) i+1);
     			for (int j = 0,length=cellTitle.length; j < length; j++) {
            	HSSFCell cell = row.createCell(j, 0);// 在上面行索引0的位置创建单元格
         		cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 定义单元格为字符串类�?
         		switch(j){// 在单元格中输入一些内�?
         			case 0://序号
          				cell.setCellValue(i+1);
          				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
          				cell.setCellStyle(style);
       					break;
        	 		case 1://姓名
          				cell.setCellValue(entity.getName());
          				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
          				cell.setCellStyle(style);
          				break;
         			case 2://注册时间
          				cell.setCellValue(String.valueOf(entity.getRegisteredTime()));
          				style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
          				cell.setCellStyle(style);
          				break;
         			case 3://学号
          				cell.setCellValue(entity.getStudentid());
          				style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
          				cell.setCellStyle(style);
          				break;
					case 4://班级
						cell.setCellValue(entity.getStudentClass());
						style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
						cell.setCellStyle(style);
						break;
					case 5://专业
						cell.setCellValue(entity.getProfessional());
						style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
						cell.setCellStyle(style);
						break;
				}
     			}
          	}
   		}


	 		// 表示以附件的形式把文件发送到客户�?
	 		response.setHeader("Content-Disposition", "attachment;filename=" + new String((exportFileName).getBytes(), "ISO-8859-1"));//设定输出文件�?
		 	response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型
	 		// 通过response的输出流把工作薄的流发�?�浏览器形成文件
	 		OutputStream outStream = response.getOutputStream();
	 		workBook.write(outStream);
	 		outStream.flush();
		}catch(IOException e){
			System.out.println("生成人员信息Excel发生IO 异常�?"+e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("文件导出发生异常！异常原因："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/simulate/modelTypeChange")
	public void getModelByModelType(long modelType,HttpServletResponse response){
		if(modelList == null || modelList.isEmpty())
			modelList = trafficModelService.getModelList();
		
		StringBuffer modelOptions = new StringBuffer();
		if(modelList != null && !modelList.isEmpty()){
			for(Iterator<TrafficModel> it = modelList.iterator();it.hasNext();){
				TrafficModel item = it.next();
				if(modelType != item.getType())
					continue;
				if(modelOptions.length() == 0){
					modelOptions.append("<option value ='").append(item.getId()).append("' selected='selected'>").append(item.getName()).append("</option>");
				}else{
					modelOptions.append("<option value ='").append(item.getId()).append("'>").append(item.getName()).append("</option>");
				}
			}
		}
		Map<String, String> result = new HashMap<String,String>();
		result.put("data", modelOptions.toString());
		out(response,JSONArray.toJSONString(result));
	}
}
