package com.tt.traffic.service;

import java.util.List;
import com.tt.traffic.domain.model.TrafficProjectModelYearType;

public interface TrafficProjectModelYearTypeService {

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
	 * @param modelId
	 * @param model_year_type
	 * @return
	 */
	List<TrafficProjectModelYearType> queryListByModelIdModelYearType(Integer modelId,Integer model_year_type);
}
