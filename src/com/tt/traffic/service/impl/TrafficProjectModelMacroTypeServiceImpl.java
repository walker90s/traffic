package com.tt.traffic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.traffic.dao.TrafficProjectModelMacroTypeMapper;
import com.tt.traffic.domain.model.TrafficProjectModelMacroType;
import com.tt.traffic.service.TrafficProjectModelMacroTypeService;

@Service
public class TrafficProjectModelMacroTypeServiceImpl implements TrafficProjectModelMacroTypeService {

	@Autowired
	private TrafficProjectModelMacroTypeMapper trafficProjectModelMacroTypeMapper;

	public Integer insert(TrafficProjectModelMacroType vo) {
		return trafficProjectModelMacroTypeMapper.insert(vo);
	}

	public Integer update(TrafficProjectModelMacroType vo) {

		return trafficProjectModelMacroTypeMapper.update(vo);
	}

	public Integer delete(TrafficProjectModelMacroType vo) {
		return trafficProjectModelMacroTypeMapper.delete(vo);
	}

	public Integer deleteByModelId(Integer modelId) {
		return trafficProjectModelMacroTypeMapper.deleteByModelId(modelId);
	}

	public TrafficProjectModelMacroType query(TrafficProjectModelMacroType vo) {
		return trafficProjectModelMacroTypeMapper.query(vo);
	}

	public List<TrafficProjectModelMacroType> queryTrafficProjectModelMacroTypeByModelId(Integer modelId) {
		return trafficProjectModelMacroTypeMapper.queryTrafficProjectModelMacroTypeByModelId(modelId);
	}

}
