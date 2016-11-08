package com.tt.traffic.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.domain.model.TrafficModelBigResult;
import com.tt.traffic.domain.model.TrafficModelMatlabResult;
import com.tt.traffic.domain.model.TrafficModelMiniResult;
import com.tt.traffic.domain.model.TrafficSimulateBase;
import com.tt.traffic.domain.model.User;
import com.tt.traffic.service.ITrafficSimulateBaseService;
import com.tt.traffic.service.common.Pagetor;

@Controller
public class ChartController extends ApplicationController<TrafficSimulateBase>{
    @Resource
    private ITrafficSimulateBaseService trafficSimulateBaseService;

    @RequestMapping("/page2")
    public String showData(HttpServletRequest request,HttpSession session,Model model){
    	List<TrafficSimulateBase> simulateList = trafficSimulateBaseService.findAllSimulate(null);
		User user = (User)session.getAttribute("sessionUser");
		if(user!=null){
			model.addAttribute("type", user.getType());
			model.addAttribute("username", user.getName());
		} else {
			model.addAttribute("type", null);
		}
    	model.addAttribute("simulateList", simulateList);
        return "page2";
    }
	@RequestMapping("/simulateList")
	public void simulateList(HttpServletResponse response){
		List<TrafficSimulateBase> simulateList = trafficSimulateBaseService.selectSimOnModelType();
		String jsonArrayString = JSONArray.toJSONString(simulateList);
		out(response, jsonArrayString);
	}
	
	@RequestMapping("/chart/base")
    @ResponseBody
    public Map<String, Object> getSimulateBase(HttpServletRequest request,@RequestBody String content,Model model){
    	
    	JSONObject params =  JSONObject.parseObject(content);
    	String id = params.getString("id");

    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	
    	if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
    		return result;
    	}
    	
		TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
	
		result.put("base", base);

        return result;
    }

    @RequestMapping("/chart/list")
    @ResponseBody
    public Map<String, Object> getModelBigResult(HttpServletRequest request,@RequestBody String content,Model model){
    	
    	JSONObject params =  JSONObject.parseObject(content);
    	String id = params.getString("id");
    	String pageNo = params.getString("pageNo");
		String roadType = params.getString("roadType");
    	if(StringUtils.isBlank(pageNo) || !StringUtils.isNumeric(pageNo)){
    		pageNo = "1";
    	}
    	
    	Map<String, Object> result = new HashMap<String,Object>();
    	if(StringUtils.isBlank(roadType)){
    		return result;
    	}
    	
    	if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
    		return result;
    	}
    	
		TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
		Pagetor<TrafficModelBigResult> pagetor = new Pagetor<TrafficModelBigResult>();
		if(base != null){
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("simulateId", Integer.valueOf(id));
			conditions.put("linkType", roadType);
			pagetor = trafficSimulateBaseService.getModelBigResultPageList(Integer.parseInt(pageNo), 0, conditions);
		}
		
		result.put("base", base);
		result.put("page",pagetor);

        return result;
    }
    
    @RequestMapping("/chart/chart")
    @ResponseBody
    public Map<String, Object> getModelBigResultChart(HttpServletRequest request,@RequestBody String content,Model model){
    	
    	JSONObject params =  JSONObject.parseObject(content);
    	String id = params.getString("id");
		String roadType = params.getString("roadType");
  
    	Map<String, Object> result = new HashMap<String,Object>();
    	if(StringUtils.isBlank(roadType)){
    		return result;
    	}
    	
    	if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
    		return result;
    	}
    	
    	TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
    	
    	Map<String, Object> conditions = new HashMap<String, Object>();
    	conditions.put("simulateId", Integer.valueOf(id));
		conditions.put("linkType", roadType);
		 List<Map<String, Object>> chartInfo = trafficSimulateBaseService.getChartStatisticsModelBigResultPageList(conditions);

    	Integer count_0_0$25 = 0;
    	Integer count_0$25_0$5 = 0;
    	Integer count_0$5_0$75 = 0;
    	Integer count_0$75_1$0 = 0;
    	Integer count_1$0_1$25 = 0;
    	Integer count_1$25_1$5 = 0;
    	Integer count_1$5_1$75 = 0;
    	Integer count_other = 0;


    	Double length_0_0$25 = Double.valueOf("0");
    	Double length_0$25_0$5 = Double.valueOf("0");
    	Double length_0$5_0$75 = Double.valueOf("0");
    	Double length_0$75_1$0 = Double.valueOf("0");
    	Double length_1$0_1$25 = Double.valueOf("0");
    	Double length_1$25_1$5 = Double.valueOf("0");
    	Double length_1$5_1$75 = Double.valueOf("0");
    	Double length_other = Double.valueOf("0");
    	
    	if(chartInfo != null && chartInfo.size() > 0){
    		for(int i=0;i<chartInfo.size();i++){
    			Map<String, Object> map = chartInfo.get(i);
    			
    			Double ab_voc = new Double(0);
    			Double ba_voc = new Double(0);
    			Double length = new Double(0);
    			try{
    				if(map.get("ab_voc") instanceof String){
    					ab_voc = (Double)map.get("ab_voc");
    				}else{
    					ab_voc = Double.valueOf(map.get("ab_voc").toString());
    				}
    			}catch(Exception e){
    				
    			}
    			try{
    				if(map.get("ba_voc") instanceof String){
    					ba_voc = (Double)map.get("ba_voc");
    				}else{
    					ba_voc = Double.valueOf(map.get("ba_voc").toString());
    				}
    			}catch(Exception e){
    				
    			}
    			
    			try{
    				if(map.get("length") instanceof String){
    					length = (Double)map.get("length");
    				}else{
    					length = Double.valueOf(map.get("length").toString());
    				}
    			}catch(Exception e){
    				
    			}
    			if(ab_voc.compareTo(Double.valueOf("0")) > 0  && ab_voc.compareTo(Double.valueOf("0.25")) <= 0){
    				count_0_0$25++;
    				length_0_0$25 += length;
    			}else if(ab_voc.compareTo(Double.valueOf("0.25")) > 0  && ab_voc.compareTo(Double.valueOf("0.5")) <= 0){
    				count_0$25_0$5++;
    				length_0$25_0$5 += length;
    			}else if(ab_voc.compareTo(Double.valueOf("0.5")) > 0  && ab_voc.compareTo(Double.valueOf("0.75")) <= 0){
    				count_0$5_0$75++;
    				length_0$5_0$75 += length;
    			}else if(ab_voc.compareTo(Double.valueOf("0.75")) > 0  && ab_voc.compareTo(Double.valueOf("1.0")) <= 0){
    				count_0$75_1$0++;
    				length_0$75_1$0 += length;
    			}else if(ab_voc.compareTo(Double.valueOf("1.0")) > 0  && ab_voc.compareTo(Double.valueOf("1.25")) <= 0){
    				count_1$0_1$25++;
    				length_1$0_1$25 += length;
    			}else if(ab_voc.compareTo(Double.valueOf("1.25")) > 0  && ab_voc.compareTo(Double.valueOf("1.5")) <= 0){
    				count_1$25_1$5++;
    				length_1$25_1$5 += length;
    			}else if(ab_voc.compareTo(Double.valueOf("1.5")) > 0  && ab_voc.compareTo(Double.valueOf("1.75")) <= 0){
    				count_1$5_1$75++;
    				length_1$5_1$75 += length;
    			}else{
    				count_other++;
    				length_other += length;
    			}
    			
    			if(ba_voc.compareTo(Double.valueOf("0")) > 0  && ba_voc.compareTo(Double.valueOf("0.25")) <= 0){
    				count_0_0$25++;
    				length_0_0$25 += length;
    			}else if(ba_voc.compareTo(Double.valueOf("0.25")) > 0  && ba_voc.compareTo(Double.valueOf("0.5")) <= 0){
    				count_0$25_0$5++;
    				length_0$25_0$5 += length;
    			}else if(ba_voc.compareTo(Double.valueOf("0.5")) > 0  && ba_voc.compareTo(Double.valueOf("0.75")) <= 0){
    				count_0$5_0$75++;
    				length_0$5_0$75 += length;
    			}else if(ba_voc.compareTo(Double.valueOf("0.75")) > 0  && ba_voc.compareTo(Double.valueOf("1.0")) <= 0){
    				count_0$75_1$0++;
    				length_0$75_1$0 += length;
    			}else if(ba_voc.compareTo(Double.valueOf("1.0")) > 0  && ba_voc.compareTo(Double.valueOf("1.25")) <= 0){
    				count_1$0_1$25++;
    				length_1$0_1$25 += length;
    			}else if(ba_voc.compareTo(Double.valueOf("1.25")) > 0  && ba_voc.compareTo(Double.valueOf("1.5")) <= 0){
    				count_1$25_1$5++;
    				length_1$25_1$5 += length;
    			}else if(ba_voc.compareTo(Double.valueOf("1.5")) > 0  && ba_voc.compareTo(Double.valueOf("1.75")) <= 0){
    				count_1$5_1$75++;
    				length_1$5_1$75 += length;
    			}else{
    				count_other++;
    				length_other += length;
    			}
    		}
    	}

    	
    	StringBuffer count = new StringBuffer();
    	count.append("[").append("['0~0.25',").append(count_0_0$25).append("]").append(",")
    	.append("['0.25~0.5',").append(count_0$25_0$5).append("]").append(",")
    	.append("['0.5~0.75',").append(count_0$5_0$75).append("]").append(",")
    	.append("['0.75~1.0',").append(count_0$5_0$75).append("]").append(",")
    	.append("['1.0~1.25',").append(count_1$0_1$25).append("]").append(",")
    	.append("['1.25~1.5',").append(count_1$25_1$5).append("]").append(",")
    	.append("['1.5~1.75',").append(count_1$5_1$75).append("]").append(",")
    	.append("['other',").append(count_other).append("]]");
    	
    	StringBuffer length = new StringBuffer();
    	length.append("[").append("['0~0.25',").append(length_0_0$25).append("]").append(",")
    	.append("['0.25~0.5',").append(length_0$25_0$5).append("]").append(",")
    	.append("['0.5~0.75',").append(length_0$5_0$75).append("]").append(",")
    	.append("['0.75~1.0',").append(length_0$5_0$75).append("]").append(",")
    	.append("['1.0~1.25',").append(length_1$0_1$25).append("]").append(",")
    	.append("['1.25~1.5',").append(length_1$25_1$5).append("]").append(",")
    	.append("['1.5~1.75',").append(length_1$5_1$75).append("]").append(",")
    	.append("['other',").append(length_other).append("]]");
    	
    	result.put("base", base);
    	result.put("count", count.toString());
    	result.put("length", length.toString());
  
        return result;
    }
    
    
    @RequestMapping("/chart/midList")
    @ResponseBody
    public Map<String, Object> getMidModelResult(HttpServletRequest request,@RequestBody String content,Model model){
    	
    	JSONObject params =  JSONObject.parseObject(content);
    	String id = params.getString("id");
    	String pageNo = params.getString("pageNo");
    	if(StringUtils.isBlank(pageNo) || !StringUtils.isNumeric(pageNo)){
    		pageNo = "1";
    	}
    	
    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
    		return result;
    	}
    	
		TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
		Pagetor<TrafficModelMatlabResult> pagetor = new Pagetor<TrafficModelMatlabResult>();
		if(base != null){
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("simulateId", Integer.valueOf(id));
			pagetor = trafficSimulateBaseService.getMidModelResultPageList(Integer.parseInt(pageNo), 0, conditions);
		}
		
		result.put("base", base);
		result.put("page",pagetor);

        return result;
    }
    
    @RequestMapping("/chart/midChart")
    @ResponseBody
    public Map<String, Object> getMidModelResultChart(HttpServletRequest request,@RequestBody String content,Model model){
    	JSONObject params =  JSONObject.parseObject(content);
    	String id = params.getString("id");
  
    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
    		return result;
    	}
    	
    	TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
    	
    	Map<String, Object> conditions = new HashMap<String, Object>();
    	conditions.put("simulateId", Integer.valueOf(id));
    	List<Map<String, Object>> chartInfo = trafficSimulateBaseService.getMidModelResultAvgChart(conditions);
    	double[] density = {(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0};
    	double[] flow = {0,0,0,0,0,0,0,0};
    	double[] speed = {(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0};
    	String[] category =  {"'7:00-7:15'", "'7:15-7:30'", "'7:30-7:45'", "'7:45:8:00'", "'8:00-8:15'", "'8:15-8:30'", "'8:30-8:45'", "'8:45:9:00'"};
    	
    	if(chartInfo != null && chartInfo.size() > 0){
    		density = new double[chartInfo.size()];
    		flow = new double[chartInfo.size()];
    		speed = new double[chartInfo.size()];
    		category = new String[chartInfo.size()];
    		
    		for(int i=0;i<chartInfo.size();i++){
    			try{
    				Map<String, Object> map = chartInfo.get(i);
    				density[i] = Double.parseDouble(map.get("density").toString());
    				flow[i] = Double.parseDouble(map.get("flow").toString());
    				speed[i] = Double.parseDouble(map.get("speed").toString());
    				category[i] = "'"+map.get("time").toString()+"'";
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	result.put("base", base);
    	result.put("category", Arrays.toString(category));
    	result.put("density", Arrays.toString(density));
    	result.put("flow", Arrays.toString(flow));
    	result.put("speed", Arrays.toString(speed));
    	
        return result;
    }
    
    
    @RequestMapping("/chart/micList")
    @ResponseBody
    public Map<String, Object> getMicModelResult(HttpServletRequest request,@RequestBody String content,Model model){
    	
    	JSONObject params =  JSONObject.parseObject(content);
    	String id = params.getString("id");
    	String pageNo = params.getString("pageNo");
    	if(StringUtils.isBlank(pageNo) || !StringUtils.isNumeric(pageNo)){
    		pageNo = "1";
    	}
    	
    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
    		return result;
    	}
    	
		TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
		Pagetor<TrafficModelMiniResult> pagetor = new Pagetor<TrafficModelMiniResult>();
		if(base != null){
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("simulateId", Integer.valueOf(id));
			pagetor = trafficSimulateBaseService.getMicModelResultPageList(Integer.parseInt(pageNo), 0, conditions);
		}
		
		result.put("base", base);
		result.put("page",pagetor);

        return result;
    }
    
    @RequestMapping("/chart/micChart")
    @ResponseBody
    public Map<String, Object> getMicModelResultChart(HttpServletRequest request,@RequestBody String content,Model model){
    	JSONObject params =  JSONObject.parseObject(content);
    	String id = params.getString("id");
  
    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
    		return result;
    	}
    	
    	TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
    	
    	Map<String, Object> conditions = new HashMap<String, Object>();
    	conditions.put("simulateId", Integer.valueOf(id));
    	List<Map<String, Object>> chartInfo = trafficSimulateBaseService.getMicModelResultAvgChart(conditions);
    	double[] density = {(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0};
    	double[] flow = {0,0,0,0,0,0,0,0};
    	double[] speed = {(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0,(float)0.0};
    	String[] category = {"'08:00:00-08:05:00'","'08:05:00-08:10:00'","'08:10:00-08:15:00'","'08:15:00-08:20:00'","'08:20:00-08:25:00'","'08:25:00-08:30:00'","'08:30:00-08:35:00'","'08:35:00-08:40:00'"};
    	
    	if(chartInfo != null && chartInfo.size() > 0){
    		density = new double[chartInfo.size()];
    		flow = new double[chartInfo.size()];
    		speed = new double[chartInfo.size()];
    		category = new String[chartInfo.size()];
    		
    		for(int i=0;i<chartInfo.size();i++){
    			try{
    				Map<String, Object> map = chartInfo.get(i);
    				density[i] = Double.parseDouble(map.get("density").toString());
    				flow[i] = Double.parseDouble(map.get("flow").toString());
    				speed[i] = Double.parseDouble(map.get("speed").toString());
    				category[i] = "'"+map.get("time").toString()+"'";
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	result.put("base", base);
    	result.put("category", Arrays.toString(category));
    	result.put("density", Arrays.toString(density));
    	result.put("flow", Arrays.toString(flow));
    	result.put("speed", Arrays.toString(speed));
    	
        return result;
    }
    
    //路口
    @RequestMapping("/chart/interList1")
    @ResponseBody
    public Map<String, Object> getInterModelResult1(HttpServletRequest request,@RequestBody String content,Model model){
    	
    	JSONObject params =  JSONObject.parseObject(content);
    	String id = params.getString("id");
    	String modelId = params.getString("modelId");
    	String pageNo = params.getString("pageNo");
    	if(StringUtils.isBlank(pageNo) || !StringUtils.isNumeric(pageNo)){
    		pageNo = "1";
    	}
    	
    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
    		return result;
    	}
    	
    	if(StringUtils.isBlank(modelId) || !StringUtils.isNumeric(modelId)){
    		return result;
    	}
    	
		TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
		Pagetor<TrafficModelMiniResult> pagetor = new Pagetor<TrafficModelMiniResult>();
		if(base != null){
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("modelId", Integer.valueOf(modelId));
			pagetor = trafficSimulateBaseService.getInterModelResultPageList1(Integer.parseInt(pageNo), 0, conditions);
		}
		
		result.put("base", base);
		result.put("page",pagetor);

        return result;
    }
    //转向
    @RequestMapping("/chart/interList2")
    @ResponseBody
    public Map<String, Object> getInterModelResult2(HttpServletRequest request,@RequestBody String content,Model model){
    	
    	JSONObject params =  JSONObject.parseObject(content);
    	String id = params.getString("id");
    	String modelId = params.getString("modelId");
    	String pageNo = params.getString("pageNo");
    	if(StringUtils.isBlank(pageNo) || !StringUtils.isNumeric(pageNo)){
    		pageNo = "1";
    	}
    	
    	Map<String, Object> result = new HashMap<String,Object>();
    	
    	if(StringUtils.isBlank(id) || !StringUtils.isNumeric(id)){
    		return result;
    	}
    	
    	if(StringUtils.isBlank(modelId) || !StringUtils.isNumeric(modelId)){
    		return result;
    	}
    	
		TrafficSimulateBase base = trafficSimulateBaseService.findSimulateById(Integer.parseInt(id));
		Pagetor<TrafficModelMiniResult> pagetor = new Pagetor<TrafficModelMiniResult>();
		if(base != null){
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("modelId", Integer.valueOf(modelId));
			pagetor = trafficSimulateBaseService.getInterModelResultPageList2(Integer.parseInt(pageNo), 0, conditions);
		}
		
		result.put("base", base);
		result.put("page",pagetor);

        return result;
    }
}
