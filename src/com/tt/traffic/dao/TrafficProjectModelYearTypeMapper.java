package com.tt.traffic.dao;

import java.util.List;
import java.util.Map;

import com.tt.traffic.domain.model.TrafficProjectModelYearType;

public interface TrafficProjectModelYearTypeMapper {

	/**
	 * 添加模型年类型信息
	 * @param vo
	 * @return
	 */
	Integer insert(TrafficProjectModelYearType vo);
	/**
	 * 更新模型年类型信息
	 * @param vo
	 * @return
	 */
	Integer update(TrafficProjectModelYearType vo);
	/**
	 * 删除模型年类型信息
	 * @param id
	 * @return
	 */
	Integer deleteById(Integer id);
	/**
	 * 删除一个模型下所有的模型年类型信息
	 * @param modelId
	 * @return
	 */
	Integer deleteByModelId(Integer modelId );
	/**
	 * 查询单个模型年类型信息
	 * @param id
	 * @return
	 */
	TrafficProjectModelYearType queryById(Integer id);
	/**
	 * 查询模型下的模型年类型信息列表
	 * @param map
	 * @return
	 */
	List<TrafficProjectModelYearType> queryListByModelIdModelYearType(Map<String,Object> map);
}
