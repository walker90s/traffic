package com.tt.traffic.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.domain.model.TrafficIntersection;
import com.tt.traffic.domain.model.TrafficModel;
import com.tt.traffic.service.TrafficIntersectionService;
import com.tt.traffic.service.TrafficModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by songyang on 2015/10/14.
 */
@Controller
public class IntersectionController extends ApplicationController<TrafficIntersection>{
    @Resource
    private TrafficIntersectionService trafficIntersectionService;

    @RequestMapping("/intersection")
    public String intersection(Model model, Integer crossid){
        model.addAttribute("crossid", crossid);
        return "intersection";
    }

    @RequestMapping("/getIntersectionJSONArray")
    public void getIntersectionJSONArray(HttpServletResponse response,Integer crossid){
        TrafficIntersection intersection = trafficIntersectionService.getTrafficIntersectionDataByCrossid(crossid);
        String jsonString = JSONObject.toJSONString(intersection);
        out(response, jsonString);
    }
}
