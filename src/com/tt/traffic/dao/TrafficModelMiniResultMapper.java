package com.tt.traffic.dao;

import java.util.List;
import java.util.Map;

import com.tt.traffic.domain.model.TrafficModelMiniResult;

public interface TrafficModelMiniResultMapper {
    List<TrafficModelMiniResult> getList(TrafficModelMiniResult trafficModelMiniResult);

    int insert(TrafficModelMiniResult record);

    int insertList(List<TrafficModelMiniResult> records);
    
    int getMicModelResultCount(Map<String, Object> cond);
    List<Map<String, Object>> getMicModelResultPageList(Map<String, Object> cond);
    List<Map<String, Object>>  getMicModelResultAvgChart(Map<String, Object> cond);
    
    int updateByPrimaryKey(TrafficModelMiniResult record);
    
    int deleteBySimulateId(Integer simulateId);
}