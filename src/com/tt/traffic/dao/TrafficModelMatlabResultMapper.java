package com.tt.traffic.dao;

import java.util.List;
import java.util.Map;

import com.tt.traffic.domain.model.TrafficModelMatlabResult;

public interface TrafficModelMatlabResultMapper {


    int insert(TrafficModelMatlabResult record);
    
    int insertList(List<TrafficModelMatlabResult> records);
    
    List<TrafficModelMatlabResult> selectByRecord(TrafficModelMatlabResult record);
    
    int updateDensityFlowSpeed(TrafficModelMatlabResult record);
    
    int batchUpdateDensityFlowSpeed(List<TrafficModelMatlabResult> records);
    
    
    int getMidModelResultCount(Map<String, Object> cond);
    List<Map<String, Object>> getMidModelResultPageList(Map<String, Object> cond);
    List<Map<String, Object>>  getMidModelResultAvgChart(Map<String, Object> cond);
    
    int deleteBySimulateId(Integer simulateId);
    
}