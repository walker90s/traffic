package com.tt.traffic.dao;

import java.util.List;

import com.tt.traffic.domain.model.TrafficModelInterResultInt;

public interface TrafficModelInterResultIntMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TrafficModelInterResultInt record);

    int insertList(List<TrafficModelInterResultInt> records);
    
    int insertSelective(TrafficModelInterResultInt record);

    TrafficModelInterResultInt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrafficModelInterResultInt record);

    int updateByPrimaryKey(TrafficModelInterResultInt record);
    
    int deleteBySimulateId(Integer simulateId);
}