package com.tt.traffic.service;

import java.util.List;

import com.tt.traffic.domain.model.TrafficProject;

public interface TrafficProjectService {

	/**
	 * 添加项目信息
	 * @param vo
	 * @return
	 */
	Integer insert(TrafficProject vo);
	/**
	 * 通过ID查询项目
	 * @return
	 */
	TrafficProject queryTrafficProjectById(Integer id);
	/**
	 * 查询全部的项目
	 * @return
	 */
	List<TrafficProject> queryTrafficProjectAll();

	/**
	 * 更新项目信息
	 * @param vo
	 * @return
	 */
	Integer update(TrafficProject vo);

	/**
	 * 删除项目信息
	 * @param vo
	 * @return
	 */
	Integer delete(TrafficProject vo);
	/**
	 * 通过项目名称查询项目Id
	 * @param projectName
	 * @return
	 */
	Integer queryTrafficIdByName(String projectName);
	/**
	 * 通过项目Id删除项目
	 * @param proId
	 * @return
	 */
	Integer deleteTrafficProjectById(Integer proId);
}
