package com.tt.traffic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.traffic.dao.TrafficProjectModelRoadLinkMapper;
import com.tt.traffic.domain.model.TrafficProjectModelRoadLink;
import com.tt.traffic.service.TrafficProjectModelRoadLinkService;

@Service
public class TrafficProjectModelRoadLinkServiceImpl implements TrafficProjectModelRoadLinkService {

	@Autowired
	private TrafficProjectModelRoadLinkMapper trafficProjectModelRoadLinkMapper;

	public Integer insert(List<TrafficProjectModelRoadLink> list) {
		return trafficProjectModelRoadLinkMapper.insert(list);
	}

	public Integer deleteByModelYearTypeId(Integer model_year_type_id) {
		return trafficProjectModelRoadLinkMapper.deleteByModelYearTypeId(model_year_type_id);
	}

	public TrafficProjectModelRoadLink queryRoadLinkById(Integer id) {
		return trafficProjectModelRoadLinkMapper.queryRoadLinkById(id);
	}

	public List<TrafficProjectModelRoadLink> queryListByModelYearTypeId(Integer model_year_type_id) {
		return trafficProjectModelRoadLinkMapper.queryListByModelYearTypeId(model_year_type_id);
	}
	
}
