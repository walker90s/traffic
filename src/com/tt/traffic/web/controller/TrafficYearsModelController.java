package com.tt.traffic.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.common.util.DateUtil;
import com.tt.traffic.dao.TrafficProjectModelYearTypeMapper;
import com.tt.traffic.domain.model.TrafficProjectModelYearType;

@Controller
public class TrafficYearsModelController {
	@Resource
	private TrafficProjectModelYearTypeMapper trafficYearsModelMapper;
	@RequestMapping(value="/addTrafficYearsModel")
	@ResponseBody
	public Map<String,Object> addYearsModel(HttpServletResponse response,HttpServletRequest request,@RequestBody String traffic){
		Map<String,Object> json = new HashMap<String, Object>();
		JSONObject params = JSONObject.parseObject(traffic);
		String proModelId = params.getString("proModelId");
		String types_id = params.getString("types_id");
		String modelName = params.getString("modelName");
		String modelDesc = params.getString("modelDesc");
		String gis = params.getString("gis");
		String traffic_demand = params.getString("traffic_demand");
		String traffic_flows = params.getString("traffic_flows");
		String road_speed = params.getString("road_speed");
		String road_result = params.getString("road_result");
		String road_lookup = params.getString("road_lookup");
		TrafficProjectModelYearType tpmyt = new TrafficProjectModelYearType();
		tpmyt.setModel_id(Integer.valueOf(proModelId));
		tpmyt.setModel_year_type(Integer.valueOf(types_id));
		tpmyt.setModel_name(modelName);
		tpmyt.setModel_desc(modelDesc);
		tpmyt.setRoad_network(gis);
		tpmyt.setTraffic_array(traffic_demand);
		tpmyt.setRoad_flow(traffic_flows);
		tpmyt.setRoad_speed(road_speed);
		tpmyt.setCosResultMoe(road_result);
		tpmyt.setLookup(road_lookup);
		tpmyt.setCreateTime(DateUtil.getCurrentDateString());
		tpmyt.setUpdateTime(DateUtil.getCurrentDateString());
		tpmyt.setYn(1);
		Integer result = trafficYearsModelMapper.insert(tpmyt);
		if (null != result && result > 0) {
			json.put("message_status", "1");// 插入记录成功
			//解析CSV
			String savePath = request.getRealPath("/upload/");
			savePath += "/";
			
		}
		return json;
	}
}
