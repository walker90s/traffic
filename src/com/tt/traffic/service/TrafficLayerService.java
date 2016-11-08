package com.tt.traffic.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2015/10/14.
 */
public interface TrafficLayerService {

    List<Object> getLayerListByType(Integer type);
}
