package com.tt.traffic.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.common.util.CsvUtil;
import com.tt.traffic.common.util.DateUtil;
import com.tt.traffic.domain.model.TrafficModel;
import com.tt.traffic.domain.model.TrafficProjectModel;
import com.tt.traffic.domain.model.TrafficProjectModelObserveFlow;
import com.tt.traffic.domain.model.TrafficProjectModelObserveSpeed;
import com.tt.traffic.service.TrafficProjectModelObserveFlowService;
import com.tt.traffic.service.TrafficProjectModelObserveSpeedService;
import com.tt.traffic.service.TrafficProjectModelService;

@Controller
public class TrafficModelController extends ApplicationController<TrafficModel> {
	@Resource
	private TrafficProjectModelService trafficProjectModelService;
	@Resource
	private TrafficProjectModelObserveFlowService trafficProjectModelObserviceFlowService;
	@Resource
	private TrafficProjectModelObserveSpeedService trafficProjectModelObserveSpeedService;
	@RequestMapping(value = "/getTrafficProjectModelJSONArray")
	@ResponseBody
	public Map<String, Object> upLoadExcel(HttpServletResponse response, HttpServletRequest request,
			@RequestBody String traffic) {
		Map<String, Object> json = new HashMap<String, Object>();
		JSONObject params = JSONObject.parseObject(traffic);
		String proId = params.getString("proId");
		String type_id = params.getString("type_id");
		String road_flow = params.getString("road_flow");
		String speed_data = params.getString("speed_data");
		// try{
		// if(null!=proId&&null!=data_desc&&null!=observe_flow){
		// proId=new String(proId.getBytes("iso-8859-1"),"utf-8");
		// data_desc=new String(data_desc.getBytes("iso-8859-1"),"utf-8");
		// observe_flow=new String(observe_flow.getBytes("iso-8859-1"),"utf-8");
		// }
		// }catch(UnsupportedEncodingException e){
		// e.printStackTrace();
		// }
		TrafficProjectModel tpm = new TrafficProjectModel();
		tpm.setProject_id(Integer.valueOf(proId));
		tpm.setType_id(Integer.valueOf(type_id));
		tpm.setRoad_flow(road_flow);
		tpm.setSpeed_data(speed_data);
		tpm.setCreateTime(DateUtil.getCurrentDateString());
		tpm.setUpdateTime(DateUtil.getCurrentDateString());
		tpm.setYn(1);
		tpm.setModel_desc("");
		tpm.setName("");
		Integer result = trafficProjectModelService.insert(tpm);
		if (null != result && result > 0) {
			json.put("message_status", "1");// 插入记录成功
			
			// 先获取模型ID
			int modelId=0;
			List<TrafficProjectModel> modelList = trafficProjectModelService.queryTrafficProjectModelByProjectId(Integer.valueOf(proId));
			for (int i = 0; i < modelList.size(); i++) {
				if(modelList.get(i).getType_id()==Integer.valueOf(type_id)){
					modelId=modelList.get(i).getId();
					break;
				}
			}
			json.put("proModelId", modelId);
			// 解析csv
			String savePath = request.getRealPath("/upload/");
			savePath += "/";
			String singleSavePath[] = road_flow.split(";");
			
			for(int w = 0; w<singleSavePath.length; w++){
				if(singleSavePath[w]!=null&&!singleSavePath[w].equals("")){
					CsvUtil csv = null;
					try {
						csv = new CsvUtil(savePath+ singleSavePath[w]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int hang = csv.getRowNum();
					int lie = csv.getColNum();
					
					List<TrafficProjectModelObserveFlow> list = new ArrayList<TrafficProjectModelObserveFlow>();
					if (lie >= 34) {
						
						for (int i = 1; i < hang; i++) {

							for (int j = 11; j <= 34; j++) {
								TrafficProjectModelObserveFlow tpmof = new TrafficProjectModelObserveFlow();
								tpmof.setModel_id(modelId);
								tpmof.setObserve_position(csv.getString(i, 0));
								tpmof.setRoad_name(csv.getString(i, 1));
								tpmof.setDir(csv.getString(i, 2));
								tpmof.setLength(Float.parseFloat(csv.getString(i, 3)));
								tpmof.setRoad_type(csv.getString(i, 4));
								tpmof.setLon(Float.parseFloat(csv.getString(i, 6)));
								tpmof.setLat(Float.parseFloat(csv.getString(i, 5)));
								tpmof.setDataYear(Integer.getInteger(csv.getString(i, 7)));
								tpmof.setSource(csv.getString(i, 8));
								tpmof.setAccuracy(csv.getString(i, 9));
								tpmof.setNote(csv.getString(i, 10));
								tpmof.setTime(csv.getString(0, j));
								if (csv.getString(i, j) == null || csv.getString(i, j).equals("")) {
									tpmof.setObserve_flow(0f);
								} else {
									tpmof.setObserve_flow(Float.parseFloat(csv.getString(i, j)));
								}
								tpmof.setCreateTime(DateUtil.getCurrentDateString());
								list.add(tpmof);
							}
						}

					}
					// 保存
					trafficProjectModelObserviceFlowService.insert(list);
				}
			}
			
			String document[] = speed_data.split(";");
			
			for (int k = 0; k < document.length; k++) {
				if(document[k]!=null&&!document[k].equals("")){
				String docu[] = document[k].split("_");
				String ment[] = document[k].split("-");
				CsvUtil CSV = null;
				try {
					CSV = new CsvUtil(savePath + document[k]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int row = CSV.getRowNum();
				int col = CSV.getColNum();
				List<TrafficProjectModelObserveSpeed> speedList = new ArrayList<TrafficProjectModelObserveSpeed>();
					for (int i = 1; i < row; i++) {
						for (int j = 3; j < col; j++) {
							TrafficProjectModelObserveSpeed tpmos = new TrafficProjectModelObserveSpeed();
							tpmos.setModel_id(modelId);
							tpmos.setPostMile(Float.parseFloat(CSV.getString(i, 0)));
							tpmos.setLocation(CSV.getString(i, 1));
							tpmos.setType(CSV.getString(i, 2));
							tpmos.setTime(CSV.getString(0,j));
							tpmos.setSpeed(Float.parseFloat(CSV.getString(i, j)));
							tpmos.setCreateTime(DateUtil.getCurrentDateString());
							tpmos.setYn(1);
							tpmos.setTime_type(ment[0]);
							
							tpmos.setDirection_type(docu[1]);
							speedList.add(tpmos);
						}
					}
					trafficProjectModelObserveSpeedService.insert(speedList);
				}
			}
			
				
		}
		return json;
	}
}
