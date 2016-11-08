package com.tt.traffic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tt.traffic.service.IBaseService;
import com.tt.traffic.service.common.Pagetor;


@Service
public class BaseServiceImpl<T extends Object> implements IBaseService<T> {
	public int defaultPageSize = 10;
	public Pagetor<T> pagetor(int pageNo, int pageSize, Map<String, String> conditions){
		Pagetor<T> pagetor = new Pagetor<T>();
		// 验证页面大小
		if (0 >= pageSize) {
			pageSize = defaultPageSize;
		}
		pagetor.setPageSize(pageSize);
		// 取得总页数

		List<?> countList = findByConditions(pageNo, pageSize, conditions, true);
		if (countList.size() > 0) {
			pagetor.setTotalCount(Integer.parseInt((countList.get(0).toString())));
		} else {
			pagetor.setTotalCount(0);
		}
		// 验证第几页
		if (pageNo > pagetor.getPageSum()) {
			pageNo = pagetor.getPageSum();
		}
		if (0 >= pageNo) {
			pageNo = 1;
		}
		pagetor.setPageNo(pageNo);
		// 取得结果集
		List<?> list = findByConditions(pageNo, pageSize, conditions, false);
		pagetor.setPageList(list);
		pagetor.refreshPageInfo();
		return pagetor;

	}

	public Pagetor<T> pagetor(String pageNo, String pageSize, Map<String, String> conditions){
		int iPageNo = 1;
		if(StringUtils.isNotBlank(pageNo) && StringUtils.isNumeric(pageNo)){
			iPageNo = Integer.parseInt(pageNo);
		}

		int iPageSize = defaultPageSize;
		if(StringUtils.isNotBlank(pageSize) && StringUtils.isNumeric(pageSize)){
			iPageSize = Integer.parseInt(pageSize);
		}

		return this.pagetor(iPageNo, iPageSize, conditions);
	}

	public List<?> findByConditions(int pageNo, int pageSize,Map<String, String> conditions, boolean isCount){
		return null;
	}
}
