package com.tt.traffic.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tt.traffic.dao.TrafficProjectMapper;
import com.tt.traffic.domain.model.TrafficProject;
import com.tt.traffic.service.TrafficProjectService;

@Service
public class TrafficProjectServiceImpl implements TrafficProjectService {

	@Autowired
	private TrafficProjectMapper trafficProjectMapper;


	public Integer insert(TrafficProject vo) {
		return trafficProjectMapper.insert(vo);
	}


	public List<TrafficProject> queryTrafficProjectAll() {
		return trafficProjectMapper.queryTrafficProjectAll();
	}


	public Integer update(TrafficProject vo) {
		return trafficProjectMapper.update(vo);
	}


	public Integer delete(TrafficProject vo) {
		return trafficProjectMapper.delete(vo);
	}


	public TrafficProject queryTrafficProjectById(Integer id) {
		return trafficProjectMapper.queryTrafficProjectById(id);
	}


	@Override
	public Integer queryTrafficIdByName(String projectName) {
		// TODO Auto-generated method stub
		return trafficProjectMapper.queryTrafficIdByName(projectName);
	}


	@Override
	public Integer deleteTrafficProjectById(Integer proId) {
		// TODO Auto-generated method stub
		return trafficProjectMapper.deleteTrafficProjectById(proId);
	}





}
