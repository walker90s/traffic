package com.tt.traffic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tt.traffic.dao.TrafficModelMatlatMapper;
import com.tt.traffic.domain.model.TrafficModelMatlab;
import com.tt.traffic.service.TrafficModelMatlabService;


@Service
public class TrafficModelMatlabServiceImpl implements TrafficModelMatlabService {

    @Resource
    private TrafficModelMatlatMapper trafficModelMatlatMapper;


    public   List<TrafficModelMatlab> getMatlabList(){
    	return trafficModelMatlatMapper.getList();
    }
    
    public List<TrafficModelMatlab> getMatlabListByModelId(Integer modelId){
    	return trafficModelMatlatMapper.getMatlabListByModelId(modelId);
    }
    
}
