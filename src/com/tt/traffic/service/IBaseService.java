package com.tt.traffic.service;

import java.util.List;
import java.util.Map;

import com.tt.traffic.service.common.Pagetor;


public interface IBaseService<T> {
	Pagetor<T> pagetor(int pageNo, int pageSize, Map<String, String> conditions);
	
	Pagetor<T> pagetor(String pageNo, String pageSize, Map<String, String> conditions);

	List<?> findByConditions(int pageNo, int pageSize,Map<String, String> conditions, boolean isCount);
}
