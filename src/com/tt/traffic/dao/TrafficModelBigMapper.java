package com.tt.traffic.dao;

import com.tt.traffic.domain.model.TrafficModelBig;

import java.util.List;

public interface TrafficModelBigMapper {
    List<TrafficModelBig> selectTrafficModelBigByModelId(Integer modelId);

    List<TrafficModelBig> selectTrafficModelAndResultByResultId(Integer simulateId);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(TrafficModelBig record);
}