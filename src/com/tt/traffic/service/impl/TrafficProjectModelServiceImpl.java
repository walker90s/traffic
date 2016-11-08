package com.tt.traffic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.traffic.dao.TrafficProjectModelMapper;
import com.tt.traffic.domain.model.TrafficProjectModel;
import com.tt.traffic.service.TrafficProjectModelService;

@Service
public class TrafficProjectModelServiceImpl implements TrafficProjectModelService {

	@Autowired
	private TrafficProjectModelMapper trafficProjectModelMapper;

	public Integer insert(TrafficProjectModel vo) {
		return trafficProjectModelMapper.insert(vo);
	}

	public Integer update(TrafficProjectModel vo) {
		return trafficProjectModelMapper.update(vo);
	}

	public Integer delete(TrafficProjectModel vo) {
		return trafficProjectModelMapper.delete(vo);
	}

	public Integer deleteByProjectId(Integer projectId) {
		return trafficProjectModelMapper.deleteByProjectId(projectId);
	}

	public List<TrafficProjectModel> queryTrafficProjectModelByProjectId(Integer projectId) {
		return trafficProjectModelMapper.queryTrafficProjectModelByProjectId(projectId);
	}

}
