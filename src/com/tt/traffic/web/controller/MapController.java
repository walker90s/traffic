package com.tt.traffic.web.controller;

import com.tt.traffic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by songyang on 2015/10/14.
 */
@Controller
public class MapController {
    @RequestMapping(value = {"/map"})
    public String index(){
        return "map";
    }
}
