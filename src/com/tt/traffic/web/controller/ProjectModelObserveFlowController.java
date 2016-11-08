package com.tt.traffic.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.domain.model.TrafficProject;
import com.tt.traffic.domain.model.TrafficProjectModel;
import com.tt.traffic.domain.model.TrafficProjectModelObserveFlow;
import com.tt.traffic.service.TrafficProjectModelObserveFlowService;
import com.tt.traffic.service.TrafficProjectModelService;

@Controller
@RequestMapping(value="projectModelObserveFlow")
public class ProjectModelObserveFlowController extends ApplicationController<TrafficProject>{

	@Resource
	TrafficProjectModelObserveFlowService trafficProjectModelObserveFlowService;
	@Resource
	TrafficProjectModelService trafficProjectModelService;
	/**
	 * 根据项目ID和模型类型查询对应模型下的数据流量的地理信息数据
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="queryGeomInfoList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  queryGeomInfoList(HttpServletRequest request,@RequestBody String content,
			HttpServletResponse response){
    	Map<String, Object> json = new HashMap<String, Object>();
    	JSONObject params =  JSONObject.parseObject(content);
    	Integer modelType = params.getInteger("modelType");
    	Integer projectId = params.getInteger("projectId");
    	Integer modelId=null;
    	List<TrafficProjectModel>  listModels=trafficProjectModelService.queryTrafficProjectModelByProjectId(projectId);
    	if(null!=listModels && listModels.size()>0){
    		for(TrafficProjectModel vo:listModels){
    			if(null!=vo && null!=vo.getType_id() && modelType==vo.getType_id().intValue()){
    				modelId=vo.getId();
    				break;
    			}
    		}
    	}
    	if(null!=modelId){
    		List<Map<String,Object>> list = trafficProjectModelObserveFlowService.queryGeomInfoByModelId(modelId);
    		if( null!=list && list.size()>0 ) {
				json.put("projectModelFlowGeomData", list);
			}
    	}
    	return json;
    }
	/**
	 * 根据模型ID和位置信息查询对应模型下的数据流量的数据
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="/queryFlowDataList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  queryProjectModelObserveFlowInfoList(HttpServletRequest request,@RequestBody String content,
			HttpServletResponse response){
    	Map<String, Object> json = new HashMap<String, Object>();
    	JSONObject params =  JSONObject.parseObject(content);
    	Integer model_id = params.getInteger("model_id");
    	String observe_position = params.getString("observe_position");
    	List<TrafficProjectModelObserveFlow>  list = trafficProjectModelObserveFlowService.queryObserveFlowsByModelIdAndPostion(model_id, observe_position);
    		if( null!=list && list.size()>0 ) {
				//json.put("projectModelObserveFlowData", list);
		    	String[] flow = null;
		    	String[] category = null;
		    	
		    	if(list != null && list.size() > 0){
		    		flow = new String[list.size()];
		    		category = new String[list.size()];
		    		
		    		for(int i=0;i<list.size();i++){
		    			try{
		    				TrafficProjectModelObserveFlow vo = (TrafficProjectModelObserveFlow)list.get(i);
		    				if( null!=vo.getObserve_flow() ){
		    					if( vo.getObserve_flow()==0 ){
				    				flow[i] = null;
			    				}else{
				    				flow[i] =vo.getObserve_flow().toString();
			    				}
		    				}

		    				category[i] = "'"+vo.getTime()+"h'";
		    			}catch(Exception e){
		    				e.printStackTrace();
		    			}
		    		}
		    	}

		    	json.put("category", Arrays.toString(category));

		    	json.put("flow", Arrays.toString(flow));
			}

    	return json;
    }
}
