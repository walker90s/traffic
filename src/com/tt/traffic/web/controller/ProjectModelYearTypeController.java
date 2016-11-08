package com.tt.traffic.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tt.traffic.common.util.CsvUtil;
import com.tt.traffic.common.util.DateUtil;
import com.tt.traffic.domain.model.TrafficProjectModel;
import com.tt.traffic.domain.model.TrafficProjectModelResultMoe;
import com.tt.traffic.domain.model.TrafficProjectModelRoadLink;
import com.tt.traffic.domain.model.TrafficProjectModelRoadLookup;
import com.tt.traffic.domain.model.TrafficProjectModelYearType;
import com.tt.traffic.service.TrafficProjectModelResultMoeService;
import com.tt.traffic.service.TrafficProjectModelRoadLinkService;
import com.tt.traffic.service.TrafficProjectModelRoadLookupService;
import com.tt.traffic.service.TrafficProjectModelService;
import com.tt.traffic.service.TrafficProjectModelYearTypeService;

@Controller
@RequestMapping(value="projectModelYearType")
public class ProjectModelYearTypeController {

	@Autowired
	private TrafficProjectModelService trafficProjectModelService;
	@Autowired
	private TrafficProjectModelYearTypeService trafficProjectModelYearTypeService;
	@Autowired
	private TrafficProjectModelRoadLinkService trafficProjectModelRoadLinkService;
	@Autowired
	private TrafficProjectModelRoadLookupService trafficProjectModelRoadLookupService;
	@Autowired
	private TrafficProjectModelResultMoeService trafficProjectModelResultMoeService;
	
	/**
	 * 根据项目ID查询对应模型下的模型年信息数据
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="queryAllList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  queryAllList(HttpServletRequest request,@RequestBody String content,
			HttpServletResponse response){
    	Map<String, Object> json = new HashMap<String, Object>();
    	JSONObject params =  JSONObject.parseObject(content);
    	Integer projectId = params.getInteger("projectId");
    	List<TrafficProjectModel> list = null;
    	if(null!=projectId){
    		list=trafficProjectModelService.queryTrafficProjectModelByProjectId(projectId);
    	}

    	if(null!=list && list.size()>0){
    		for(TrafficProjectModel vo:list){
    			if(null!=vo && null!=vo.getId() && null!=vo.getType_id()){
    				if(1==vo.getType_id()){//宏观模型
    					List<TrafficProjectModelYearType> modelYearTypeList = trafficProjectModelYearTypeService.queryListByModelIdModelYearType(vo.getId(), null);
    					if(null != modelYearTypeList && modelYearTypeList.size() > 0 ){
    						json.put("macroModelYearTypeList", modelYearTypeList);
    					}
    				}else if(3==vo.getType_id()){//微观模型
    					List<TrafficProjectModelYearType> modelYearTypeList = trafficProjectModelYearTypeService.queryListByModelIdModelYearType(vo.getId(), null);
    					if(null != modelYearTypeList && modelYearTypeList.size() > 0 ){
    						json.put("microModelYearTypeList", modelYearTypeList);
    					}
    				}
    			}
    		}
    	}

    	return json;
    }

	/**
	 * 根据项目ID查询对应模型下的模型年信息数据
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="insert", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  insert(HttpServletRequest request,@RequestBody String traffic,
			HttpServletResponse response){
    	Map<String, Object> json = new HashMap<String, Object>();
    	JSONObject params =  JSONObject.parseObject(traffic);
//    	Integer model_id = params.getInteger("model_id");
//    	Integer model_year_type = params.getInteger("model_year_type");
//    	String model_name = params.getString("model_name");
//    	String model_desc = params.getString("model_desc");
//    	String networkPath = params.getString("networkPath");
//    	String trafficArrayPath = params.getString("trafficArrayPath");
//    	String lookupPath = params.getString("lookupPath");
//    	String flowPath = params.getString("flowPath");
//    	String speedPath = params.getString("speedPath");
//    	String moePath = params.getString("moePath");
		String proModelId = params.getString("proModelId");
		String types_id = params.getString("types_id");
		String modelName = params.getString("modelName");
		String modelDesc = params.getString("modelDesc");
		String gis = params.getString("gis");
		String traffic_demand = params.getString("traffic_demand");
		String traffic_flows = params.getString("traffic_flows");
		String road_speed = params.getString("road_speed");
		String road_result = params.getString("road_result");
		String road_lookup = params.getString("road_lookup");
    	if(null!=proModelId && null!=types_id && null!=modelName && !"".equals(modelName.trim())){
        	TrafficProjectModelYearType vo = new TrafficProjectModelYearType();
        	vo.setModel_id(Integer.valueOf(proModelId));
        	vo.setModel_year_type(Integer.valueOf(types_id));
        	vo.setModel_name(modelName);
        	vo.setModel_desc(modelDesc);
        	vo.setRoad_network(gis);
        	vo.setTraffic_array(traffic_demand);
        	vo.setRoad_flow(traffic_flows);
        	vo.setRoad_speed(road_speed);
        	vo.setCosResultMoe(road_result);
        	vo.setLookup(road_lookup);
    		vo.setCreateTime(DateUtil.getCurrentDateString());
    		vo.setUpdateTime(DateUtil.getCurrentDateString());
    		vo.setYn(1);
        	Integer ret=trafficProjectModelYearTypeService.insert(vo);
        	if(ret>0){
        		json.put("message_status","1");//插入记录成功
        		List<TrafficProjectModelYearType> list =trafficProjectModelYearTypeService.queryListByModelIdModelYearType(Integer.valueOf(proModelId), Integer.valueOf(types_id));
    			int maxId=0;
    			if(list!=null){
	        		for(int i=list.size()-1;i<list.size();){
	    				maxId=list.get(i).getId();
	    				break;
	    			}
    			}
        		String savePath = request.getRealPath("/upload/");
    			savePath += "/";
        		//networkPath如果不为空，则进行解析入库
        		analysisLinkCsv(gis, savePath, maxId);
        		//lookupPath如果不为空，则进行解析入库
        		analysisLookupCsv(road_lookup, savePath, maxId);
        		//moePath如果不为空，则进行解析入库
        		analysisMoeCsv(road_result, savePath, maxId);
        	}
    	}

    	return json;
    }

    /**
     * networkPath如果不为空，则进行解析入库
     * @param networkPath
     * @param savePath
     */
	private void analysisLinkCsv(String networkPath, String savePath,int maxId) {
		if(null!=networkPath && !"".equals(networkPath.trim())){
			// 解析csv
			CsvUtil csv = null;
			try {
				csv = new CsvUtil(savePath+ networkPath.replace(";", ""));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(null!=csv){
				int hang = csv.getRowNum();
				int lie = csv.getColNum();
				List<TrafficProjectModelRoadLink> list = new ArrayList<TrafficProjectModelRoadLink>();
				if (lie >= 17) {
					for (int i = 1; i < hang; i++) {
						TrafficProjectModelRoadLink linkVo = new TrafficProjectModelRoadLink();
						linkVo.setModel_year_type_id(maxId);
						linkVo.setLinkID(Integer.parseInt(csv.getString(i, 0)));
						linkVo.setOneway_twoway(Integer.parseInt(csv.getString(i, 1)));
						linkVo.setAb_direction(csv.getString(i, 2));
						linkVo.setBa_direction(csv.getString(i, 3));
						linkVo.setA_id(Integer.parseInt(csv.getString(i, 4)));
						linkVo.setA_latitude(Float.parseFloat(csv.getString(i, 5)));
						linkVo.setA_longitude(Float.parseFloat(csv.getString(i, 6)));
						linkVo.setB_id(Integer.parseInt(csv.getString(i, 7)));
						linkVo.setB_latitude(Float.parseFloat(csv.getString(i, 8)));
						linkVo.setB_longitude(Float.parseFloat(csv.getString(i, 9)));
						linkVo.setAb_lanes(Integer.parseInt(csv.getString(i, 10)));
						linkVo.setBa_lanes(Integer.parseInt(csv.getString(i, 11)));
						linkVo.setLength_mile(Float.parseFloat(csv.getString(i, 12)));
						linkVo.setType(csv.getString(i, 13));
						linkVo.setRoadName(csv.getString(i, 14));
						linkVo.setRouteName(csv.getString(i, 15));
						String routeIdStr = csv.getString(i, 16);
						Integer routeId = null;
						if(null!=routeIdStr && !"".equals(routeIdStr.trim())){
							routeId= Integer.parseInt(routeIdStr);
						}
						linkVo.setRouteID(routeId);
						list.add(linkVo);
					}
				}
				if( null != list && list.size() > 0 ){
					trafficProjectModelRoadLinkService.insert(list);
				}
			}
		}
	}

	/**
	 * lookupPath如果不为空，则进行解析入库
	 * @param lookupPath
	 * @param savePath
	 */
	private void analysisLookupCsv(String lookupPath, String savePath,int maxId) {
		if(null!=lookupPath && !"".equals(lookupPath.trim())){
			// 解析csv
			CsvUtil csv = null;
			try {
				csv = new CsvUtil(savePath+ lookupPath.replace(";", ""));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(null!=csv){
				int hang = csv.getRowNum();
				int lie = csv.getColNum();
				List<TrafficProjectModelRoadLookup> list = new ArrayList<TrafficProjectModelRoadLookup>();
				if (lie >= 4) {
					for (int i = 1; i < hang; i++) {
						TrafficProjectModelRoadLookup lookupVo = new TrafficProjectModelRoadLookup();
						lookupVo.setModel_year_type_id(maxId);
						lookupVo.setLinkID(Integer.parseInt(csv.getString(i, 0)));
						lookupVo.setDirection(csv.getString(i, 1));
						lookupVo.setType(csv.getString(i, 2));
						lookupVo.setSectionId(Integer.parseInt(csv.getString(i, 3)));
						lookupVo.setCreateTime(DateUtil.getCurrentDateString());
						lookupVo.setYn(1);
						list.add(lookupVo);
					}
				}
				if( null != list && list.size() > 0 ){
					trafficProjectModelRoadLookupService.insert(list);
				}
			}
		}
	}

	/**
	 * moePath如果不为空，则进行解析入库
	 * @param moePath
	 * @param savePath
	 */
	private void analysisMoeCsv(String moePath, String savePath,int maxId) {
		if(null!=moePath && !"".equals(moePath.trim())){
			// 解析csv
			CsvUtil csv = null;
			try {
				csv = new CsvUtil(savePath+ moePath.replace(";", ""));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(null!=csv){
				int hang = csv.getRowNum();
				int lie = csv.getColNum();
				List<TrafficProjectModelResultMoe> list = new ArrayList<TrafficProjectModelResultMoe>();
				if (lie >= 15) {
					for (int i = 1; i < hang; i++) {
						TrafficProjectModelResultMoe moeVo = new TrafficProjectModelResultMoe();
						moeVo.setModel_year_type_id(maxId);
						moeVo.setModelName(csv.getString(i,0));
						moeVo.setTime(csv.getString(i,1));
						moeVo.setVehicleType(csv.getString(i,2));
						moeVo.setDirection(csv.getString(i,3));
						moeVo.setSectionId(Integer.parseInt(csv.getString(i,4)));
						moeVo.setType(csv.getString(i,5));
						moeVo.setTravelTime_minute(Float.parseFloat(csv.getString(i,6)));
						moeVo.setSpeed_mph(Float.parseFloat(csv.getString(i,7)));
						moeVo.setDelay_hour(Float.parseFloat(csv.getString(i,8)));
						moeVo.setVolume(Float.parseFloat(csv.getString(i,9)));
						moeVo.setVht(Float.parseFloat(csv.getString(i,10)));
						moeVo.setVmt(Float.parseFloat(csv.getString(i,11)));
						moeVo.setVmt_vht(Float.parseFloat(csv.getString(i,12)));
						moeVo.setOccupancy(Float.parseFloat(csv.getString(i,13)));
						moeVo.setTruckPercentge(csv.getString(i,14));
						moeVo.setCreateTime(DateUtil.getCurrentDateString());
						moeVo.setYn(1);
						list.add(moeVo);
					}
				}
				if( null != list && list.size() > 0 ){
					trafficProjectModelResultMoeService.insert(list);
				}
			}
		}
	}
}
