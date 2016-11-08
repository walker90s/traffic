package com.tt.traffic.dao;

import com.tt.traffic.domain.model.TrafficIntersection;

import java.util.List;

public interface TrafficIntersectionMapper {

    List<TrafficIntersection> getIntersectionList();

    List<TrafficIntersection> getIntersectionListByModelId(int modelId);

    TrafficIntersection getIntersectionByCrossid(Integer crossid);
}