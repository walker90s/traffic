package com.tt.traffic.service.impl;

import com.tt.traffic.dao.TrafficIntersectionFlowMapper;
import com.tt.traffic.dao.TrafficIntersectionMapper;
import com.tt.traffic.dao.TrafficIntersectionTimeMapper;
import com.tt.traffic.domain.model.TrafficIntersection;
import com.tt.traffic.domain.model.TrafficIntersectionFlow;
import com.tt.traffic.domain.model.TrafficIntersectionTime;
import com.tt.traffic.service.TrafficIntersectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2015/10/14.
 */
@Service
public class TrafficIntersectionServiceImpl implements TrafficIntersectionService {
    @Resource
    private TrafficIntersectionMapper trafficIntersectionMapper;

    @Resource
    private TrafficIntersectionTimeMapper trafficIntersectionTimeMapper;

    @Resource
    private TrafficIntersectionFlowMapper trafficIntersectionFlowMapper;

    public List<TrafficIntersection> getIntersectionList(Integer modelId) {
        return trafficIntersectionMapper.getIntersectionListByModelId(modelId);
    }

    public TrafficIntersection getTrafficIntersectionDataByCrossid(Integer crossid) {
        TrafficIntersection intersection = trafficIntersectionMapper.getIntersectionByCrossid(crossid);
        //配时列表
        List<TrafficIntersectionTime> timeList = trafficIntersectionTimeMapper.getTimeListByCrossidGroupByPhase(crossid);
        //流量列表
        List<TrafficIntersectionFlow> flowList = trafficIntersectionFlowMapper.getFlowListByCrossid(crossid);

        intersection.setTimeList(timeList);
        intersection.setFlowList(flowList);
        return intersection;
    }
}
