package com.tt.traffic.dao;

import com.tt.traffic.domain.model.TrafficModel;

import java.util.List;

public interface TrafficModelMapper {
    List<TrafficModel> getModelList();

    /**
     * 按名称查询
     * @param name
     * @return
     */
    List<TrafficModel> getListByname(String name);

    /**
     * 修改速度数据路径
     * @param trafficModel
     * @return
     */
    int updateSpeedData(TrafficModel trafficModel);
    /**
     * 修改流量数据路径
     * @param trafficModel
     * @return
     */
    int updateFlowData(TrafficModel trafficModel);
}