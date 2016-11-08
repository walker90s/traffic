package com.tt.traffic.dao;

import java.util.List;

import com.tt.traffic.domain.model.TrafficSimulateBase;

public interface TrafficSimulateBaseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TrafficSimulateBase record);

    int insertSelective(TrafficSimulateBase record);

    List<TrafficSimulateBase> selectByPage(TrafficSimulateBase record);
    
    List<TrafficSimulateBase> selectAll(TrafficSimulateBase record);

    /**
     * 链接model表查询
     * @return
     */
    List<TrafficSimulateBase> selectSimOnModelType();

    int selectCount(TrafficSimulateBase record);
    
    TrafficSimulateBase selectByPrimaryKey(Integer id);

    TrafficSimulateBase selectByModelId(Integer simulateModelId);
    
    int updateByPrimaryKeySelective(TrafficSimulateBase record);

    int updateByPrimaryKey(TrafficSimulateBase record);
    
    int updateByModelId(TrafficSimulateBase record);
    
}