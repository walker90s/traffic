package com.tt.traffic.service.common;

import java.util.ArrayList;
import java.util.List;

public class Pagetor<T> {
	private List<?> pageList;
	private int pageNo = 1;
	private int totalCount;
	private int pageSize = 10;

	private List<Integer> indexList = new ArrayList<Integer>();
	private int start = 1;
	private int end = 1;
	
	private String softModelOptions = "";
	public String getSoftModelOptions() {
		return softModelOptions;
	}

	public void setSoftModelOptions(String softModelOptions) {
		this.softModelOptions = softModelOptions;
	}

	public List<?> getPageList() {
		return pageList;
	}

	public void setPageList(List<?> pageList) {
		this.pageList = pageList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSum() {

		return totalCount / pageSize + ((totalCount % pageSize) == 0 ? 0 : 1);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public void refreshPageInfo(){
		indexList.clear();
		int count = 5;
		start = pageNo - count + 1;
		if(start < 1)
			start = 1;
		
		end = start + count -1;
		if(end > getPageSum())
			end = getPageSum();
		
		for(int i=start;i<= end;i++){
			indexList.add(i);
		}
	}

	public List<Integer> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<Integer> indexList) {
		this.indexList = indexList;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
