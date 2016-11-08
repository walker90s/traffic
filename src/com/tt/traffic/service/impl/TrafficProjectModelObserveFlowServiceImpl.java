package com.tt.traffic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.traffic.dao.TrafficProjectModelObserveFlowMapper;
import com.tt.traffic.domain.model.TrafficProjectModelObserveFlow;
import com.tt.traffic.service.TrafficProjectModelObserveFlowService;

@Service
public class TrafficProjectModelObserveFlowServiceImpl implements TrafficProjectModelObserveFlowService {

	@Autowired
	private TrafficProjectModelObserveFlowMapper trafficProjectModelObserveFlowMapper;

	public Integer insert(List<TrafficProjectModelObserveFlow> list) {
		return trafficProjectModelObserveFlowMapper.insert(list);
	}

	public List<Map<String, Object>> queryGeomInfoByModelId(Integer model_id) {
		return trafficProjectModelObserveFlowMapper.queryGeomInfoByModelId(model_id);
	}

	public TrafficProjectModelObserveFlow queryObserveFlowById(Integer id) {
		return trafficProjectModelObserveFlowMapper.queryObserveFlowById(id);
	}

	public List<TrafficProjectModelObserveFlow> queryObserveFlowsByModelId(Integer model_id) {
		return trafficProjectModelObserveFlowMapper.queryObserveFlowsByModelId(model_id);
	}

	public Integer deleteByModelId(Integer model_id) {
		return trafficProjectModelObserveFlowMapper.deleteByModelId(model_id);
	}

	public List<TrafficProjectModelObserveFlow> queryObserveFlowsByModelIdAndPostion(Integer model_id,
			String observe_position) {
		return trafficProjectModelObserveFlowMapper.queryObserveFlowsByModelIdAndPostion(model_id, observe_position);
	}

}
