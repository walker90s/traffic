package com.tt.traffic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.traffic.dao.TrafficProjectModelObserveSpeedMapper;
import com.tt.traffic.domain.model.TrafficProjectModelObserveSpeed;
import com.tt.traffic.service.TrafficProjectModelObserveSpeedService;

@Service
public class TrafficProjectModelObserveSpeedServiceImpl implements TrafficProjectModelObserveSpeedService {

	@Autowired
	private TrafficProjectModelObserveSpeedMapper trafficProjectModelObserveSpeedMapper;

	public Integer insert(List<TrafficProjectModelObserveSpeed> list) {
		return trafficProjectModelObserveSpeedMapper.insert(list);
	}

	public List<Map<String, Object>> queryGeomInfoByModelIdAndType(Integer model_id, String type) {
		return trafficProjectModelObserveSpeedMapper.queryGeomInfoByModelIdAndType(model_id, type);
	}

	public List<TrafficProjectModelObserveSpeed> queryObserveSpeedsByModelIdAndPostionAndType(Integer model_id,
			String location, String type) {
		return trafficProjectModelObserveSpeedMapper.queryObserveSpeedsByModelIdAndPostionAndType(model_id, location, type);
	}

	public TrafficProjectModelObserveSpeed queryObserveSpeedById(Integer id) {
		return trafficProjectModelObserveSpeedMapper.queryObserveSpeedById(id);
	}

	public List<TrafficProjectModelObserveSpeed> queryObserveSpeedsByModelId(Integer model_id) {
		return trafficProjectModelObserveSpeedMapper.queryObserveSpeedsByModelId(model_id);
	}

	public Integer deleteByModelId(Integer model_id) {
		return trafficProjectModelObserveSpeedMapper.deleteByModelId(model_id);
	}

}
