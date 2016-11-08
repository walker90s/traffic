package com.tt.traffic.service;

import com.tt.traffic.domain.model.TrafficIntersection;
import com.tt.traffic.domain.model.TrafficModel;

import java.util.List;

/**
 * Created by admin on 2015/10/14.
 */
public interface TrafficIntersectionService {

    List<TrafficIntersection> getIntersectionList(Integer modelId);

    /**
     * 根据crossid获取路口数据
     * @param crossid
     * @return
     */
    TrafficIntersection getTrafficIntersectionDataByCrossid(Integer crossid);
}
