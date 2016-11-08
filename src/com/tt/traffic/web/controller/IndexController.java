package com.tt.traffic.web.controller;

import com.tt.traffic.domain.model.User;
import com.tt.traffic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.String;

/**
 * Created by songyang on 2015/10/14.
 */
@Controller
public class IndexController{
    @Resource
    private UserService userService;

    @RequestMapping(value = {"","/","/index"})
    public String index(HttpSession session,Model model){
        User user = (User)session.getAttribute("sessionUser");
        if(user!=null){
            model.addAttribute("type", user.getType());
            model.addAttribute("username", user.getName());
        } else {
            model.addAttribute("type", null);
        }
        return "index";
    }
}
