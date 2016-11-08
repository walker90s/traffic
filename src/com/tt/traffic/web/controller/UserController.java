package com.tt.traffic.web.controller;

import com.tt.traffic.domain.model.User;
import com.tt.traffic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by admin on 2015/10/14.
 */
@Controller
public class UserController extends ApplicationController<User>{
    @Resource
    private UserService userService;

    @RequestMapping("/user")
    public void getUser(HttpServletResponse response){
        Writer out = null;
        try {
            out = response.getWriter();
            String res = "aa";
            out.write(res);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
