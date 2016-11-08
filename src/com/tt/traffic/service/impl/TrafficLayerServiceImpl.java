package com.tt.traffic.service.impl;

import com.tt.traffic.dao.TrafficLayerPointMapper;
import com.tt.traffic.dao.TrafficLayerRoadMapper;
import com.tt.traffic.service.TrafficLayerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/11/8.
 */
@Service
public class TrafficLayerServiceImpl implements TrafficLayerService {
    @Resource
    private TrafficLayerPointMapper trafficLayerPointMapper;

    @Resource
    private TrafficLayerRoadMapper trafficLayerRoadMapper;
    public List<Object> getLayerListByType(Integer type) {
        List layerList = new ArrayList<Object>();
        if(type == null || type == 5){
            //路段
            layerList = trafficLayerRoadMapper.getTrafficLayerRoadList();
        }else{
            //点位
            layerList = trafficLayerPointMapper.getTrafficLayerPointByType(type);
        }
        return layerList;
    }

}
