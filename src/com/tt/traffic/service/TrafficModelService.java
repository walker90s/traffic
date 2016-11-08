package com.tt.traffic.service;

import com.alibaba.fastjson.JSONArray;
import com.tt.traffic.domain.model.TrafficModel;
import com.tt.traffic.domain.model.TrafficModelBigResult;
import com.tt.traffic.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2015/10/14.
 */
@Service
public interface TrafficModelService {

    List<TrafficModel> getModelList();

    List<TrafficModel> getModelAllList();

    JSONArray getModelResult(Integer type, Integer resultId, Integer modelId);

    JSONArray getModelResultByTypeAndRoadId(Integer type, Integer roadId, Integer resultId, Byte aorb);

    /**
     * 按名称查询
     * @param name
     * @return
     */
    List<TrafficModel> getListByname(String name);

    /**
     * 修改数据路径
     * @param trafficModel
     * @return
     */
    int updatePath(TrafficModel trafficModel);
}
