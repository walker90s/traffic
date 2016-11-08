package com.tt.traffic.dao;

import java.util.List;
import java.util.Map;

import com.tt.traffic.domain.model.TrafficModelInterResultApproach;

public interface TrafficModelInterResultApproachMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TrafficModelInterResultApproach record);

    int insertList(List<TrafficModelInterResultApproach> records);
    
    int insertSelective(TrafficModelInterResultApproach record);

    TrafficModelInterResultApproach selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrafficModelInterResultApproach record);

    int updateByPrimaryKey(TrafficModelInterResultApproach record);
    
    int getInterApproachModelResultCount(Map<String, Object> cond);
    List<Map<String, Object>> getInterApproachModelResultPageList(Map<String, Object> cond);
    
    int deleteBySimulateId(Integer simulateId);
}