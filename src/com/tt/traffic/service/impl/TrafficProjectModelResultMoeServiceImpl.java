package com.tt.traffic.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.traffic.dao.TrafficProjectModelResultMoeMapper;
import com.tt.traffic.domain.model.TrafficProjectModelResultMoe;
import com.tt.traffic.service.TrafficProjectModelResultMoeService;

@Service
public class TrafficProjectModelResultMoeServiceImpl implements TrafficProjectModelResultMoeService {

	@Autowired
	private TrafficProjectModelResultMoeMapper trafficProjectModelResultMoeMapper;

	public Integer insert(List<TrafficProjectModelResultMoe> list) {
		return trafficProjectModelResultMoeMapper.insert(list);
	}

	public Integer deleteByModelYearTypeId(Integer model_year_type_id) {
		return trafficProjectModelResultMoeMapper.deleteByModelYearTypeId(model_year_type_id);
	}

	public TrafficProjectModelResultMoe queryResultMoeById(Integer id) {
		return trafficProjectModelResultMoeMapper.queryResultMoeById(id);
	}

	public List<TrafficProjectModelResultMoe> queryListByModelYearTypeId(Integer model_year_type_id) {
		return trafficProjectModelResultMoeMapper.queryListByModelYearTypeId(model_year_type_id);
	}

	public List<Map<String, Object>> queryResultMoeByModelYearTypeId(Integer model_year_type_id) {
		return trafficProjectModelResultMoeMapper.queryResultMoeByModelYearTypeId(model_year_type_id);
	}

}
