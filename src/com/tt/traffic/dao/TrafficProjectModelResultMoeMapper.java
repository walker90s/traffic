package com.tt.traffic.dao;

import java.util.List;
import java.util.Map;
import com.tt.traffic.domain.model.TrafficProjectModelResultMoe;

public interface TrafficProjectModelResultMoeMapper {

	/**
	 * 添加仿真 结果 Moe信息
	 * @param list
	 * @return
	 */
	Integer insert(List<TrafficProjectModelResultMoe> list);
	/**
	 * 根据模型年ID来删除仿真 结果 Moe信息
	 * @param model_year_type_id
	 * @return
	 */
	Integer deleteByModelYearTypeId(Integer model_year_type_id);
	/**
	 * 根据id查询仿真 结果 Moe信息
	 * @param id
	 * @return
	 */
	TrafficProjectModelResultMoe queryResultMoeById(Integer id);
	/**
	 * 根据模型年ID来查询仿真 结果 Moe信息列表
	 * @param model_year_type_id
	 * @return
	 */
	List<TrafficProjectModelResultMoe> queryListByModelYearTypeId(Integer model_year_type_id);
	/**
	 * 根据模型年类型ID查询对应的路段仿真结果moe信息
	 * @param model_year_type_id
	 * @return
	 */
	List<Map<String,Object>> queryResultMoeByModelYearTypeId(Integer model_year_type_id);
	
}
