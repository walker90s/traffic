package com.tt.traffic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tt.traffic.domain.model.TrafficModelMatlab;


@Service
public interface TrafficModelMatlabService {

    List<TrafficModelMatlab> getMatlabList();
    
    List<TrafficModelMatlab> getMatlabListByModelId(Integer modelId);
    
}
