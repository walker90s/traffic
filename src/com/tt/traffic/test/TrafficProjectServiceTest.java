package com.tt.traffic.test;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.tt.traffic.common.util.DateUtil;
import com.tt.traffic.domain.model.TrafficProject;
import com.tt.traffic.service.TrafficProjectService;


public class TrafficProjectServiceTest extends BaseTest {

	
	@Autowired
	private TrafficProjectService trafficProjectService;


	public void insert(){
		TrafficProject vo = new TrafficProject();
		vo.setUser_id(1);
		vo.setProjectName("91号洲际公路");
		vo.setProBackground("改善交通出行量增加的需求");
		vo.setNeed_goals("满足未来20年交通出行量增加的需求");
		vo.setCreateTime(DateUtil.getCurrentDateString());
		vo.setUpdateTime(DateUtil.getCurrentDateString());
		vo.setYn(1);
		trafficProjectService.insert(vo);
	}
	
	public void update(){
		TrafficProject vo = new TrafficProject();
		vo.setId(11);
		vo.setProjectName("91号洲际公路");
		vo.setProBackground("为了改善交通出行量增加的需求");
		vo.setNeed_goals("定制满足未来20年交通出行量增加的需求");
		vo.setCreateTime(DateUtil.getCurrentDateString());
		vo.setUpdateTime(DateUtil.getCurrentDateString());
		vo.setYn(1);
		trafficProjectService.update(vo);
	}
	@Test
	public void queryTrafficProjectById(){
		TrafficProject vo=trafficProjectService.queryTrafficProjectById(1);
		if(null!=vo){
			logger.info(vo.getProBackground());
		}
	}
	public void queryTrafficProjectAll(){
		List<TrafficProject> list=trafficProjectService.queryTrafficProjectAll();
		if(null!=list && list.size()>0){
			for(TrafficProject vo :list){
				if(null!=vo){
					logger.info(vo.getProBackground());
				}
			}
		}
	}
	
	public void delete(){
		TrafficProject vo = new TrafficProject();
		vo.setId(11);
		trafficProjectService.delete(vo);
	}
	
}
