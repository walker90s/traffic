package com.tt.traffic.dao;

import java.util.List;
import com.tt.traffic.domain.model.TrafficProjectModel;

public interface TrafficProjectModelMapper {

	/**
	 * 添加项目信息
	 * @param vo
	 * @return
	 */
	Integer insert(TrafficProjectModel vo);
	/**
	 * 更新项目模型信息
	 * @param vo
	 * @return
	 */
	Integer update(TrafficProjectModel vo);
	/**
	 * 删除项目下的单个模型信息
	 * @param vo
	 * @return
	 */
	Integer delete(TrafficProjectModel vo);
	/**
	 * 删除一个项目下所有的模型信息
	 * @param vo
	 * @return
	 */
	Integer deleteByProjectId(Integer projectId );
	/**
	 * 查询项目下的模型信息列表
	 * @return
	 */
	List<TrafficProjectModel> queryTrafficProjectModelByProjectId(Integer projectId);
}
