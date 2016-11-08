package com.tt.traffic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.traffic.dao.TrafficProjectModelYearTypeMapper;
import com.tt.traffic.domain.model.TrafficProjectModelYearType;
import com.tt.traffic.service.TrafficProjectModelYearTypeService;

@Service
public class TrafficProjectModelYearTypeServiceImpl implements TrafficProjectModelYearTypeService {

	@Autowired
	private TrafficProjectModelYearTypeMapper trafficProjectModelYearTypeMapper; 

	public Integer insert(TrafficProjectModelYearType vo) {
		return trafficProjectModelYearTypeMapper.insert(vo);
	}

	public Integer update(TrafficProjectModelYearType vo) {
		return trafficProjectModelYearTypeMapper.update(vo);
	}

	public Integer deleteById(Integer id) {
		return trafficProjectModelYearTypeMapper.deleteById(id);
	}

	public Integer deleteByModelId(Integer modelId) {
		return trafficProjectModelYearTypeMapper.deleteByModelId(modelId);
	}

	public TrafficProjectModelYearType queryById(Integer id) {
		return trafficProjectModelYearTypeMapper.queryById(id);
	}

	public List<TrafficProjectModelYearType> queryListByModelIdModelYearType(Integer modelId,Integer model_year_type) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(null!=modelId){
			map.put("model_id", modelId);
		}
		if(null!=model_year_type){
			map.put("model_year_type", model_year_type);
		}
		return trafficProjectModelYearTypeMapper.queryListByModelIdModelYearType(map);
	}

}
