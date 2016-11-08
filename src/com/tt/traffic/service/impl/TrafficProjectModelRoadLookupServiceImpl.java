package com.tt.traffic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.traffic.domain.model.TrafficProjectModelRoadLookup;
import com.tt.traffic.service.TrafficProjectModelRoadLookupService;
import com.tt.traffic.dao.TrafficProjectModelRoadLookupMapper;

@Service
public class TrafficProjectModelRoadLookupServiceImpl implements TrafficProjectModelRoadLookupService {

	@Autowired
	private TrafficProjectModelRoadLookupMapper trafficProjectModelRoadLookupMapper;

	public Integer insert(List<TrafficProjectModelRoadLookup> list) {
		return trafficProjectModelRoadLookupMapper.insert(list);
	}

	public Integer deleteByModelYearTypeId(Integer model_year_type_id) {
		return trafficProjectModelRoadLookupMapper.deleteByModelYearTypeId(model_year_type_id);
	}

	public TrafficProjectModelRoadLookup queryRoadLookupById(Integer id) {
		return trafficProjectModelRoadLookupMapper.queryRoadLookupById(id);
	}

	public List<TrafficProjectModelRoadLookup> queryListByModelYearTypeId(Integer model_year_type_id) {
		return trafficProjectModelRoadLookupMapper.queryListByModelYearTypeId(model_year_type_id);
	}

}
