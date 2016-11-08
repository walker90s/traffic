package com.tt.traffic.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.tt.traffic.service.TrafficLayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by admin on 2015/11/5.
 */
@Controller
public class LayerController extends ApplicationController{
    @Resource
    private TrafficLayerService trafficLayerService;

    @RequestMapping(value = "/getLayerJsonArray")
    public void getLayerJsonArray(HttpServletResponse response,Integer type){
        List<Object> layerList = trafficLayerService.getLayerListByType(type);
        String jsonArrayString = JSONArray.toJSONString(layerList);
        out(response, jsonArrayString);
    }
}
