package com.tt.traffic.dao;

import java.util.List;
import java.util.Map;

import com.tt.traffic.domain.model.TrafficModelInterResultTurn;

public interface TrafficModelInterResultTurnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrafficModelInterResultTurn record);

    int insertList(List<TrafficModelInterResultTurn> records);
    
    int insertSelective(TrafficModelInterResultTurn record);
    
    TrafficModelInterResultTurn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrafficModelInterResultTurn record);

    int updateByPrimaryKey(TrafficModelInterResultTurn record);
    
    int getInterTurnModelResultCount(Map<String, Object> cond);
    List<Map<String, Object>> getInterTurnModelResultPageList(Map<String, Object> cond);
    
    int deleteBySimulateId(Integer simulateId);
}