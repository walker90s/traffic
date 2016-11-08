package com.tt.traffic.web.controller;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller 基类
 */
public abstract class ApplicationController<T> {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yy-MM-dd"), true));
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Boolean.class,new CustomBooleanEditor(true));
    }

    protected ResponseEntity<T> ok(T entity) {
        return new ResponseEntity<T>(entity, HttpStatus.OK);
    }

    protected ResponseEntity<T> ok() {
        return new ResponseEntity<T>(HttpStatus.OK);
    }

    protected ResponseEntity<T> created(T entity) {
        return new ResponseEntity<T>(entity, HttpStatus.CREATED);
    }

    public void out(HttpServletResponse response, String outString){
        response.setCharacterEncoding("utf-8");
        Writer out = null;

        try {
            out = response.getWriter();
            if (outString != null){
                out.write(outString);
                out.flush();
            }
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
