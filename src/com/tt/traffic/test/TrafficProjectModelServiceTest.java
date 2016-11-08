package com.tt.traffic.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tt.traffic.common.util.DateUtil;
import com.tt.traffic.domain.model.TrafficProjectModel;
import com.tt.traffic.service.TrafficProjectModelService;


public class TrafficProjectModelServiceTest extends BaseTest {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private TrafficProjectModelService trafficProjectModelService;
	
	
	public void insert(){
		TrafficProjectModel vo = new TrafficProjectModel();
		vo.setProject_id(12);
		vo.setType_id(1);
		vo.setName("宏观模型");
		vo.setModel_desc("关于改善91号洲际公路的模型");
		vo.setRoad_flow("时间数据流量");
		vo.setSpeed_data("flowUrl/flow.xls");
		vo.setCreateTime(DateUtil.getCurrentDateFormatString());
		vo.setUpdateTime(DateUtil.getCurrentDateFormatString());
		vo.setYn(1);
		trafficProjectModelService.insert(vo);
	}
	
	public void update(){
		TrafficProjectModel vo = new TrafficProjectModel();
		vo.setId(11);;
		vo.setName("宏观模型");
		vo.setModel_desc("关于改善91号洲际公路的模型");
		vo.setRoad_flow("观测数据流量");
		vo.setSpeed_data("flowUrl/flow.xls");
		vo.setCreateTime(DateUtil.getCurrentDateFormatString());
		vo.setUpdateTime(DateUtil.getCurrentDateFormatString());
		vo.setYn(1);
		trafficProjectModelService.update(vo);
	}
	
	public void delete(){
		TrafficProjectModel vo = new TrafficProjectModel();
		vo.setId(12);
		trafficProjectModelService.delete(vo);
	}

	public void deleteByProjectId(){
		Integer projectId=12;
		trafficProjectModelService.deleteByProjectId(projectId);
	}
	@Test
	public void queryTrafficProjectModelByProjectId(){
		Integer projectId=12;
		List<TrafficProjectModel> list = trafficProjectModelService.queryTrafficProjectModelByProjectId(projectId);
		if(null!=list && list.size()>0){
			for(TrafficProjectModel vo:list){
				if(null!=vo){
					log.info(vo.getModel_desc());
				}
			}
		}
	}
}
