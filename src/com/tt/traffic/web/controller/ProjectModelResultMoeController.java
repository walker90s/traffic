package com.tt.traffic.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.domain.model.TrafficProjectModelYearType;
import com.tt.traffic.service.TrafficProjectModelResultMoeService;
import com.tt.traffic.service.TrafficProjectModelYearTypeService;

@Controller
@RequestMapping(value="/projectModelResultMoe")
public class ProjectModelResultMoeController {

	@Autowired
	private TrafficProjectModelYearTypeService trafficProjectModelYearTypeService;
	@Autowired
	private TrafficProjectModelResultMoeService trafficProjectModelResultMoeService;

	/**
	 * 根据模型ID查询对应模型下的模型年信息数据
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="/queryGeomMoeList")
    @ResponseBody
    public Map<String, Object>  queryAllList(HttpServletRequest request,@RequestBody String content,
			HttpServletResponse response){
    	Map<String, Object> json = new HashMap<String, Object>();
    	JSONObject params =  JSONObject.parseObject(content);
    	String model_id_str = params.getString("model_id");
    	String model_year_type_str = params.getString("model_year_type");
    	Integer model_id = null,model_year_type = null;
    	if(null!=model_id_str && !"".equals(model_id_str.trim()) && null!=model_year_type_str && !"".equals(model_year_type_str.trim())){
    		model_id = Integer.parseInt(model_id_str);
    		model_year_type = Integer.parseInt(model_year_type_str);
    	}
    	if(null!=model_id && null!=model_year_type){
    		List<TrafficProjectModelYearType> modelYearTypeList = trafficProjectModelYearTypeService.queryListByModelIdModelYearType(model_id, model_year_type);
    		if(null!=modelYearTypeList && modelYearTypeList.size()>0){
    			TrafficProjectModelYearType vo = (TrafficProjectModelYearType)modelYearTypeList.get(0);
    			if( null != vo && null != vo.getId() ){
    				List<Map<String,Object>> list=trafficProjectModelResultMoeService.queryResultMoeByModelYearTypeId(vo.getId());
    				json.put("linkResultMoeData", list);
    			}
    		}
    	}
    	return json;
    }
}
