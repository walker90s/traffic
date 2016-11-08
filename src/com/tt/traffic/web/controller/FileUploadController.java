package com.tt.traffic.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.common.util.ExcelReader;
import com.tt.traffic.domain.model.*;
import com.tt.traffic.service.ITrafficSimulateBaseService;
import com.tt.traffic.service.common.Pagetor;
import jxl.Workbook;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FileUploadController extends ApplicationController<TrafficSimulateBase>{
	private static final Logger loggger = Logger.getLogger(FileUploadController.class);
    @Resource
    private ITrafficSimulateBaseService trafficSimulateBaseService;

	@RequestMapping("/toUpload")
	public String toUpload(){
		return "file_upload";
	}

    @RequestMapping("/uploadFile")
    public String fileUpload(Model model, @RequestParam("file") MultipartFile file){
		List<String[]> list = null;
		try {
			Workbook workbook = Workbook.getWorkbook(file.getInputStream());
			list = ExcelReader.readExcel(workbook, 0, null, 0, null);
		}catch (Exception e){
			loggger.error("上传文件异常�?" + e.getMessage());
		}

		for(String[] array : list){
			for(String str : array) {
				loggger.error(str);
			}
		}

		model.addAttribute("list", list);
		return "file_upload";
	}
}
