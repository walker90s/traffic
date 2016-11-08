package com.tt.traffic.dao;

import java.util.List;

import com.tt.traffic.domain.model.TrafficProjectModelRoadLink;

public interface TrafficProjectModelRoadLinkMapper {

	/**
	 * 添加路段基本信息
	 * @param list
	 * @return
	 */
	Integer insert(List<TrafficProjectModelRoadLink> list);
	/**
	 * 根据模型年ID来删除关联的路段基本信息
	 * @param model_year_type_id
	 * @return
	 */
	Integer deleteByModelYearTypeId(Integer model_year_type_id);
	/**
	 * 根据id查询路段基本信息
	 * @param id
	 * @return
	 */
	TrafficProjectModelRoadLink queryRoadLinkById(Integer id);
	/**
	 * 根据模型年ID来查询关联的路段基本信息列表
	 * @param model_year_type_id
	 * @return
	 */
	List<TrafficProjectModelRoadLink> queryListByModelYearTypeId(Integer model_year_type_id);
}
