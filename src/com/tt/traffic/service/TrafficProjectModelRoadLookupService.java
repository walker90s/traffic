package com.tt.traffic.service;

import java.util.List;

import com.tt.traffic.domain.model.TrafficProjectModelRoadLookup;

public interface TrafficProjectModelRoadLookupService {

	/**
	 * 添加路段和路段组中间表信息
	 * @param list
	 * @return
	 */
	Integer insert(List<TrafficProjectModelRoadLookup> list);
	/**
	 * 根据模型年ID来删除关联的路段和路段组中间表信息
	 * @param model_year_type_id
	 * @return
	 */
	Integer deleteByModelYearTypeId(Integer model_year_type_id);
	/**
	 * 根据id查询路段和路段组中间表信息
	 * @param id
	 * @return
	 */
	TrafficProjectModelRoadLookup queryRoadLookupById(Integer id);
	/**
	 * 根据模型年ID来查询路段和路段组中间表信息列表
	 * @param model_year_type_id
	 * @return
	 */
	List<TrafficProjectModelRoadLookup> queryListByModelYearTypeId(Integer model_year_type_id);
}
