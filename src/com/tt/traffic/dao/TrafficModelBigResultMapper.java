package com.tt.traffic.dao;

import java.util.List;
import java.util.Map;

import com.tt.traffic.domain.model.TrafficModelBigResult;

public interface TrafficModelBigResultMapper {


    int deleteByPrimaryKey(Integer id);
    /**
     * 按数据id删除
     * @param simulate_id
     * @return
     */
    int deleteBySimulate_id(Integer simulate_id);

    int insert(TrafficModelBigResult record);
    
    int insertList(List<TrafficModelBigResult> records);

    List<TrafficModelBigResult> selectByResultId(int simulateId);

    TrafficModelBigResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrafficModelBigResult record);

    int updateByPrimaryKey(TrafficModelBigResult record);
    
    
    int selectChartStatisticsCountByCon(Map<String, Object> cond);
    List<Map<String, Object>> selectChartStatisticsByCon(Map<String, Object> cond);
    /**
     * 获取voc统计信息
     * @param cond
     * @return
     */
    List<Map<String, Object>> selectChartSumStatisticsByCon(Map<String, Object> cond);
    int deleteBySimulateId(Integer simulateId);
    
}