package com.tt.traffic.dao;

import com.tt.traffic.domain.model.TrafficLayerPoint;
import java.util.List;

public interface TrafficLayerPointMapper {

    List<TrafficLayerPoint> getTrafficLayerPointByType(Integer type);

}