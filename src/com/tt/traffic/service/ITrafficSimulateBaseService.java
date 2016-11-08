package com.tt.traffic.service;

import java.util.List;
import java.util.Map;

import com.tt.traffic.domain.model.TrafficModelBigResult;
import com.tt.traffic.domain.model.TrafficModelInterResultApproach;
import com.tt.traffic.domain.model.TrafficModelInterResultInt;
import com.tt.traffic.domain.model.TrafficModelInterResultTurn;
import com.tt.traffic.domain.model.TrafficModelMatlabResult;
import com.tt.traffic.domain.model.TrafficModelMiniResult;
import com.tt.traffic.domain.model.TrafficSimulateBase;
import com.tt.traffic.service.common.Pagetor;



public interface ITrafficSimulateBaseService extends IBaseService<TrafficSimulateBase>{

	public void deleteById(Integer id);
	
	public void updateByPrimaryKey(TrafficSimulateBase base);
	
	public List<TrafficSimulateBase> findAllSimulate(TrafficSimulateBase cond);

	public TrafficSimulateBase findSimulateById(Integer id);
	
	//宏观模型
	public void addSimulateBaseMac(TrafficSimulateBase base,List<TrafficModelBigResult> bigResult);

	//中观模型
	public void addSimulateBaseMatLab(TrafficSimulateBase base,List<TrafficModelMatlabResult> result);
	
	//微观模型
	public void addSimulateBaseMicroResult(TrafficSimulateBase base,List<TrafficModelMiniResult> result);
	
	//路口模型
	public void addSimulateBaseInterResult(TrafficSimulateBase base,List<TrafficModelInterResultApproach> approachResultList,List<TrafficModelInterResultTurn> turnResultList,List<TrafficModelInterResultInt> intResultList);
	/**
	 * 宏观模型
	 * @param pageNo
	 * @param pageSize
	 * @param conditions
	 * @return
	 */
	public Pagetor<TrafficModelBigResult> getModelBigResultPageList(int pageNo, int pageSize, Map<String, Object> conditions);

	/**
	 * 宏观模型统计数据
	 * @param conditions
	 * @return
	 */
	public List<Map<String, Object>> getChartStatisticsModelBigResultPageList(Map<String, Object> conditions);

	//中观模型
	public Pagetor<TrafficModelMatlabResult> getMidModelResultPageList(int pageNo, int pageSize, Map<String, Object> conditions);
	public List<Map<String, Object>> getMidModelResultAvgChart(Map<String, Object> conditions);
	
	//微观模型
	public Pagetor<TrafficModelMiniResult> getMicModelResultPageList(int pageNo, int pageSize, Map<String, Object> conditions);
	public List<Map<String, Object>> getMicModelResultAvgChart(Map<String, Object> conditions);
	
	//路口模型
	public Pagetor<TrafficModelMiniResult> getInterModelResultPageList1(int pageNo, int pageSize, Map<String, Object> conditions);
	public Pagetor<TrafficModelMiniResult> getInterModelResultPageList2(int pageNo, int pageSize, Map<String, Object> conditions);
		
	/**
	 * 根据类型删除数据
	 * @param id
	 */
	public boolean deleteByType(Integer id,String type);
	/**
	 * 分页查找所以的数据
	 * @param trafficSimulateBase
	 * @return
	 */
	public List<TrafficSimulateBase> getListSimulate(TrafficSimulateBase trafficSimulateBase);
	/**
	 * 查找数据总数
	 * @return
	 */
	public int selectCount();
	/**
	 * 链接model表查询
	 * @return
	 */
	List<TrafficSimulateBase> selectSimOnModelType();

}
