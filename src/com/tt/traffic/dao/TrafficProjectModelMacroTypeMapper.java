package com.tt.traffic.dao;

import java.util.List;

import com.tt.traffic.domain.model.TrafficProjectModelMacroType;


public interface TrafficProjectModelMacroTypeMapper {

	/**
	 * 添加模型年类型信息
	 * @param vo
	 * @return
	 */
	Integer insert(TrafficProjectModelMacroType vo);
	/**
	 * 更新模型年类型信息
	 * @param vo
	 * @return
	 */
	Integer update(TrafficProjectModelMacroType vo);
	/**
	 * 删除模型年类型信息
	 * @param vo
	 * @return
	 */
	Integer delete(TrafficProjectModelMacroType vo);
	/**
	 * 删除一个模型下所有的模型年类型信息
	 * @param vo
	 * @return
	 */
	Integer deleteByModelId(Integer modelId );
	/**
	 * 查询单个模型年类型信息
	 * @param vo
	 * @return
	 */
	TrafficProjectModelMacroType query(TrafficProjectModelMacroType vo);
	/**
	 * 查询模型下的模型年类型信息列表
	 * @return
	 */
	List<TrafficProjectModelMacroType> queryTrafficProjectModelMacroTypeByModelId(Integer modelId);
}
