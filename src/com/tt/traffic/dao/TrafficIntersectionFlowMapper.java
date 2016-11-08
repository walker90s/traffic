package com.tt.traffic.dao;

import com.tt.traffic.domain.model.TrafficIntersectionFlow;

import java.util.List;

public interface TrafficIntersectionFlowMapper {
    List<TrafficIntersectionFlow> getFlowListByCrossid(int crossid);
}