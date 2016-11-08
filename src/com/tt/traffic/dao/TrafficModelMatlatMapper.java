package com.tt.traffic.dao;

import java.util.List;

import com.tt.traffic.domain.model.TrafficModelMatlab;

public interface TrafficModelMatlatMapper {

    int insert(TrafficModelMatlab record);

    List<TrafficModelMatlab> getList();
    
    List<TrafficModelMatlab> getMatlabListByModelId(Integer modelId);
}