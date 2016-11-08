package com.tt.traffic.web.controller;

import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by songyang10 on 2016/5/5.
 */
@Controller
public class GlobalController extends ApplicationController {
    private static final Logger logger = Logger.getLogger(GlobalController.class);


    @RequestMapping(value="/locale", method = {RequestMethod.POST})
    public void test(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="langType", defaultValue="zh") String langType){

        if(langType.equals("zh")){
            Locale locale = new Locale("zh", "CN");
            System.out.println("zh===");
            (new CookieLocaleResolver()).setLocale (request, response, locale);
        }
        else if(langType.equals("en")){
            Locale locale = new Locale("en", "US");
            System.out.println("en===");
            (new CookieLocaleResolver()).setLocale(request, response, locale);
        }
        else {
            (new CookieLocaleResolver()).setLocale(request, response, LocaleContextHolder.getLocale());
        }
    }
}
