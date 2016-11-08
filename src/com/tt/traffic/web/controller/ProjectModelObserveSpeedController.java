package com.tt.traffic.web.controller;

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
import com.tt.traffic.domain.model.TrafficProjectModel;
import com.tt.traffic.domain.model.TrafficProjectModelObserveSpeed;
import com.tt.traffic.service.TrafficProjectModelObserveSpeedService;
import com.tt.traffic.service.TrafficProjectModelService;

@Controller
@RequestMapping(value="projectModelObserveSpeed")
public class ProjectModelObserveSpeedController {

	@Resource
	TrafficProjectModelObserveSpeedService trafficProjectModelObserveSpeedService;
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
    		List<Map<String,Object>> list = trafficProjectModelObserveSpeedService.queryGeomInfoByModelIdAndType(modelId,"HV");
    		if( null==list || list.size()==0){
    			list = trafficProjectModelObserveSpeedService.queryGeomInfoByModelIdAndType(modelId,"ML");
    		}
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
    @RequestMapping(value="/querySpeedDataList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  queryProjectModelObserveSpeedInfoList(HttpServletRequest request,@RequestBody String content,
			HttpServletResponse response){
    	Map<String, Object> json = new HashMap<String, Object>();
    	JSONObject params =  JSONObject.parseObject(content);
    	Integer model_id = params.getInteger("model_id");
    	String location = params.getString("location");
    	List<TrafficProjectModelObserveSpeed>  list = trafficProjectModelObserveSpeedService.queryObserveSpeedsByModelIdAndPostionAndType(model_id, location,"HV");
    	if( null==list || list.size()==0){
    		list = trafficProjectModelObserveSpeedService.queryObserveSpeedsByModelIdAndPostionAndType(model_id, location,"ML");
    	}
		if( null!=list && list.size()>0 ) {
			//json.put("projectModelObserveFlowData", list);
	    	String[] speed = null;
	    	String[] category = null;
	    	
	    	if(list != null && list.size() > 0){
	    		speed = new String[list.size()];
	    		category = new String[list.size()];
	    		
	    		for(int i=0;i<list.size();i++){
	    			try{
	    				TrafficProjectModelObserveSpeed vo = (TrafficProjectModelObserveSpeed)list.get(i);
	    				if( null!=vo.getSpeed() ){
	    					if( vo.getSpeed()==0 ){
			    				speed[i] = null;
		    				}else{
			    				speed[i] =vo.getSpeed().toString();
		    				}
	    				}

	    				category[i] = "'"+vo.getTime()+"'";
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    		}
	    	}

	    	json.put("category", Arrays.toString(category));

	    	json.put("speed", Arrays.toString(speed));
		}

    	return json;
    }
}
