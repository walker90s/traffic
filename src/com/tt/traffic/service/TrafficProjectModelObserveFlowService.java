package com.tt.traffic.service;

import java.util.List;
import java.util.Map;
import com.tt.traffic.domain.model.TrafficProjectModelObserveFlow;


public interface TrafficProjectModelObserveFlowService {

	/**
	 * 添加观测流量信息
	 * @param vo
	 * @return
	 */
	Integer insert(List<TrafficProjectModelObserveFlow> list);

	/**
	 * 通过模型ID查询观测流量的地理信息
	 * @return
	 */
	List<Map<String,Object>> queryGeomInfoByModelId(Integer model_id);
	/**
	 * 根据模型ID和观测点位置查询对应的观测流量数据列表
	 * @param model_id
	 * @param observe_position
	 * @return
	 */
	List<TrafficProjectModelObserveFlow> queryObserveFlowsByModelIdAndPostion(Integer model_id,String observe_position);
	/**
	 * 根据id查询对应的观测流量信息
	 * @param id
	 * @return
	 */
	TrafficProjectModelObserveFlow queryObserveFlowById(Integer id);
	/**
	 * 根据模型ID查询对应的观测流量数据列表
	 * @param model_id
	 * @return
	 */
	List<TrafficProjectModelObserveFlow> queryObserveFlowsByModelId(Integer model_id);
	/**
	 * 删除模型ID下的观测流量信息
	 * @param vo
	 * @return
	 */
	Integer deleteByModelId(Integer model_id);
}
