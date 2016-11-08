package com.tt.traffic.dao;

import java.util.List;

import com.tt.traffic.domain.model.TrafficModelMini;

public interface TrafficModelMiniMapper {
    public List<TrafficModelMini> getList();

    public List<TrafficModelMini> getMiniListByModelId(Integer modelId);

}