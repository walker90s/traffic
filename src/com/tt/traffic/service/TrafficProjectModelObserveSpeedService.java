package com.tt.traffic.service;

import java.util.List;
import java.util.Map;
import com.tt.traffic.domain.model.TrafficProjectModelObserveSpeed;

public interface TrafficProjectModelObserveSpeedService {

	/**
	 * 添加观测速度信息
	 * @param vo
	 * @return
	 */
	Integer insert(List<TrafficProjectModelObserveSpeed> list);

	/**
	 * 通过模型ID查询观测速度的地理信息
	 * @return
	 */
	List<Map<String,Object>> queryGeomInfoByModelIdAndType(Integer model_id,String type);
	/**
	 * 根据模型ID、观测点位置、类型查询对应的观测速度数据列表
	 * @param model_id
	 * @param observe_position
	 * @return
	 */
	List<TrafficProjectModelObserveSpeed> queryObserveSpeedsByModelIdAndPostionAndType(Integer model_id,String location,String type);
	/**
	 * 根据id查询对应的观测速度信息
	 * @param id
	 * @return
	 */
	TrafficProjectModelObserveSpeed queryObserveSpeedById(Integer id);
	/**
	 * 根据模型ID查询对应的观测速度数据列表
	 * @param model_id
	 * @return
	 */
	List<TrafficProjectModelObserveSpeed> queryObserveSpeedsByModelId(Integer model_id);
	/**
	 * 删除模型ID下的观测速度信息
	 * @param vo
	 * @return
	 */
	Integer deleteByModelId(Integer model_id);
}
