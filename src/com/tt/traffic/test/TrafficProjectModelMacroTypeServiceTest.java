package com.tt.traffic.test;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.tt.traffic.common.util.DateUtil;
import com.tt.traffic.domain.model.TrafficProjectModelMacroType;
import com.tt.traffic.service.TrafficProjectModelMacroTypeService;


public class TrafficProjectModelMacroTypeServiceTest extends BaseTest {


	@Autowired
	private TrafficProjectModelMacroTypeService trafficProjectModelMacroTypeService;

	public void insert(){
		TrafficProjectModelMacroType vo = new TrafficProjectModelMacroType();
		vo.setModel_id(13);
		vo.setModel_year_type(1);
		vo.setPlan_type(1);

		vo.setModel_name("宏观模型基础年模型");
		vo.setModel_desc("改善91号公路出行负载方案");
		vo.setTraffic_array_1("TrafficArrayUrl/Traffic_array_1.xls");
		vo.setTraffic_array_2("TrafficArrayUrl/Traffic_array_2.xls");
		vo.setCosResult_1("cosResultUrl/cosResult1.xls");
		vo.setCosResult_2("cosResultUrl/cosResult2.xls");
		vo.setCreateTime(DateUtil.getCurrentDateString());
		vo.setUpdateTime(DateUtil.getCurrentDateString());
		vo.setYn(1);
		trafficProjectModelMacroTypeService.insert(vo);
	}

	public void update(){
		TrafficProjectModelMacroType vo = new TrafficProjectModelMacroType();
		vo.setId(11);
		vo.setModel_name("宏观模型基础年模型");
		vo.setModel_desc("定制改善91号公路出行负载方案");
		vo.setTraffic_array_1("TrafficArrayUrl/Traffic_array_1.xls");
		vo.setTraffic_array_2("TrafficArrayUrl/Traffic_array_2.xls");
		vo.setCosResult_1("cosResultUrl/cosResult1.xls");
		vo.setCosResult_2("cosResultUrl/cosResult2.xls");
		vo.setUpdateTime(DateUtil.getCurrentDateString());
		vo.setYn(1);
		trafficProjectModelMacroTypeService.update(vo);
	}
	
	public void delete(){
		TrafficProjectModelMacroType vo = new TrafficProjectModelMacroType();
		vo.setId(11);
		trafficProjectModelMacroTypeService.delete(vo);
	}

	public void deleteByModelId(){
		Integer modelId=13;
		trafficProjectModelMacroTypeService.deleteByModelId(modelId);
	}

	public void query(){
		TrafficProjectModelMacroType vo = new TrafficProjectModelMacroType();
		vo.setId(11);
		vo=trafficProjectModelMacroTypeService.query(vo);
		logger.info(vo.getModel_desc());
	}
	@Test
	public void queryTrafficProjectModelMacroTypeByModelId(){
		Integer modelId=13;
		List<TrafficProjectModelMacroType> list =trafficProjectModelMacroTypeService.queryTrafficProjectModelMacroTypeByModelId(modelId);
		if(null!=list && list.size()>0){
			for(TrafficProjectModelMacroType vo:list){
				if(null!=vo){
					logger.info(vo.getModel_desc());
				}
			}
		}
	}
}
