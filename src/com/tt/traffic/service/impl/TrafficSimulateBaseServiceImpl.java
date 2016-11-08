package com.tt.traffic.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tt.traffic.dao.TrafficModelBigResultMapper;
import com.tt.traffic.dao.TrafficModelInterResultApproachMapper;
import com.tt.traffic.dao.TrafficModelInterResultIntMapper;
import com.tt.traffic.dao.TrafficModelInterResultTurnMapper;
import com.tt.traffic.dao.TrafficModelMatlabResultMapper;
import com.tt.traffic.dao.TrafficModelMiniMapper;
import com.tt.traffic.dao.TrafficModelMiniResultMapper;
import com.tt.traffic.dao.TrafficSimulateBaseMapper;
import com.tt.traffic.domain.model.TrafficModelBigResult;
import com.tt.traffic.domain.model.TrafficModelInterResultApproach;
import com.tt.traffic.domain.model.TrafficModelInterResultInt;
import com.tt.traffic.domain.model.TrafficModelInterResultTurn;
import com.tt.traffic.domain.model.TrafficModelMatlabResult;
import com.tt.traffic.domain.model.TrafficModelMiniResult;
import com.tt.traffic.domain.model.TrafficSimulateBase;
import com.tt.traffic.service.ITrafficSimulateBaseService;
import com.tt.traffic.service.common.Pagetor;


@Service
public class TrafficSimulateBaseServiceImpl extends BaseServiceImpl<TrafficSimulateBase> implements ITrafficSimulateBaseService {


	@Resource
	private TrafficSimulateBaseMapper trafficSimulateBaseMapper;

	@Resource
	private TrafficModelBigResultMapper trafficModelBigResultMapper;

	@Resource
	private TrafficModelMatlabResultMapper trafficModelMatlabResultMapper;

	@Resource
	private TrafficModelMiniMapper trafficModelMiniMapper;
	
	@Resource
	private TrafficModelMiniResultMapper trafficModelMiniResultMapper;
	
	@Resource
	private TrafficModelInterResultApproachMapper trafficModelInterResultApproachMapper;

	@Resource
	private TrafficModelInterResultTurnMapper trafficModelInterResultTurnMapper;
	
	@Resource
	private TrafficModelInterResultIntMapper trafficModelInterResultIntMapper;
	
	public List<?> findByConditions(int pageNo, int pageSize,Map<String, String> conditions, boolean isCount){
		TrafficSimulateBase record = new TrafficSimulateBase();
		if(conditions.get("local") != null){
			record.setLocal(Integer.parseInt(conditions.get("local")));
		}
		if(isCount){
			List<Integer> result = new ArrayList<Integer>();
			result.add(trafficSimulateBaseMapper.selectCount(record));
			return result;
		}

		record.setOffset((pageNo -1 ) * pageSize);
		record.setLimit(pageSize);
		return trafficSimulateBaseMapper.selectByPage(record);
	}

	public void deleteById(Integer id){
		trafficSimulateBaseMapper.deleteByPrimaryKey(id);
		trafficModelBigResultMapper.deleteBySimulateId(id);
		trafficModelMatlabResultMapper.deleteBySimulateId(id);
		trafficModelMiniResultMapper.deleteBySimulateId(id);
		trafficModelInterResultApproachMapper.deleteBySimulateId(id);
		trafficModelInterResultTurnMapper.deleteBySimulateId(id);
		trafficModelInterResultIntMapper.deleteBySimulateId(id);
	}
	
	public void updateByPrimaryKey(TrafficSimulateBase base){
		trafficSimulateBaseMapper.updateByPrimaryKey(base);	
	}
	
	//宏观导入
	public void addSimulateBaseMac(TrafficSimulateBase base,List<TrafficModelBigResult> bigResult){
		TrafficSimulateBase currentBase = trafficSimulateBaseMapper.selectByModelId(base.getSimulateModelId());
		if(currentBase == null){
			trafficSimulateBaseMapper.insert(base);
		}else{
			if(currentBase.getLocal() == 1){
				trafficSimulateBaseMapper.updateByModelId(base);
				base.setId(currentBase.getId());
				trafficModelBigResultMapper.deleteBySimulateId(currentBase.getId());
			}else{
				return;
			}
		}
		
		if(bigResult != null && !bigResult.isEmpty()){
			for(Iterator<TrafficModelBigResult> it = bigResult.iterator();it.hasNext();){
				TrafficModelBigResult item = it.next();
				item.setSimulateId(base.getId());
				item.setModelId(base.getSimulateModelId());
			}
			trafficModelBigResultMapper.insertList(bigResult);
		}
		
	}
	
	//中观导入
	public void addSimulateBaseMatLab(TrafficSimulateBase base,List<TrafficModelMatlabResult> result){
		TrafficSimulateBase currentBase = trafficSimulateBaseMapper.selectByModelId(base.getSimulateModelId());
		if(currentBase == null){
			trafficSimulateBaseMapper.insert(base);
		}else{
			if(currentBase.getLocal() == 1){
				trafficSimulateBaseMapper.updateByModelId(base);
				base.setId(currentBase.getId());
				trafficModelMatlabResultMapper.deleteBySimulateId(currentBase.getId());
			}else{
				return;
			}
		}
		
		List<TrafficModelMatlabResult> insertResult = new ArrayList<TrafficModelMatlabResult>();
		List<TrafficModelMatlabResult> updateResult = new ArrayList<TrafficModelMatlabResult>();
		
		if(result != null && !result.isEmpty()){
			for(Iterator<TrafficModelMatlabResult> it = result.iterator();it.hasNext();){
				TrafficModelMatlabResult item = it.next();
				item.setSimulateId(base.getId());
				item.setModelId(base.getSimulateModelId());
				List<TrafficModelMatlabResult> curItemList = trafficModelMatlabResultMapper.selectByRecord(item);
				if(curItemList != null && !curItemList.isEmpty()){
					TrafficModelMatlabResult curItem =  curItemList.get(0);
					curItem.setDensity(item.getDensity());
					curItem.setFlow(item.getFlow());
					curItem.setSpeed(item.getSpeed());
					updateResult.add(curItem);
					
				}else{
					insertResult.add(item);
				}
			}
			if(!updateResult.isEmpty()){
				trafficModelMatlabResultMapper.batchUpdateDensityFlowSpeed(updateResult);
			}
			if(!insertResult.isEmpty()){
				trafficModelMatlabResultMapper.insertList(insertResult);
			}
		}
	}

	//微观模型导入
	public void addSimulateBaseMicroResult(TrafficSimulateBase base,List<TrafficModelMiniResult> result){
		TrafficSimulateBase currentBase = trafficSimulateBaseMapper.selectByModelId(base.getSimulateModelId());
		if(currentBase == null){
			trafficSimulateBaseMapper.insert(base);
		}else{
			if(currentBase.getLocal() == 1){
				trafficSimulateBaseMapper.updateByModelId(base);
				base.setId(currentBase.getId());
				trafficModelMiniResultMapper.deleteBySimulateId(currentBase.getId());
			}else{
				return;
			}
		}
		
		if(result != null && !result.isEmpty()){
			for(Iterator<TrafficModelMiniResult> it = result.iterator();it.hasNext();){
				TrafficModelMiniResult item = it.next();
				item.setSimulateId(base.getId());
				item.setModelId(base.getSimulateModelId());
			}
			trafficModelMiniResultMapper.insertList(result);
		}
		
	}
	
	//路口模型导入
	public void addSimulateBaseInterResult(TrafficSimulateBase base,List<TrafficModelInterResultApproach> approachResultList,List<TrafficModelInterResultTurn> turnResultList,List<TrafficModelInterResultInt> intResultList){
		TrafficSimulateBase currentBase = trafficSimulateBaseMapper.selectByModelId(base.getSimulateModelId());
		if(currentBase == null){
			trafficSimulateBaseMapper.insert(base);
		}else{
			if(currentBase.getLocal() == 1){
				trafficSimulateBaseMapper.updateByModelId(base);
				base.setId(currentBase.getId());
				trafficModelInterResultApproachMapper.deleteBySimulateId(currentBase.getId());
				trafficModelInterResultTurnMapper.deleteBySimulateId(currentBase.getId());
				trafficModelInterResultIntMapper.deleteBySimulateId(currentBase.getId());
			}else{
				return;
			}
		}
		
		if(approachResultList != null && !approachResultList.isEmpty()){
			for(Iterator<TrafficModelInterResultApproach> it = approachResultList.iterator();it.hasNext();){
				TrafficModelInterResultApproach item = it.next();
				item.setSimulateId(base.getId());
				item.setModelId(base.getSimulateModelId());
			}
			trafficModelInterResultApproachMapper.insertList(approachResultList);
		}
		if(turnResultList != null && !turnResultList.isEmpty()){
			for(Iterator<TrafficModelInterResultTurn> it = turnResultList.iterator();it.hasNext();){
				TrafficModelInterResultTurn item = it.next();
				item.setSimulateId(base.getId());
				item.setModelId(base.getSimulateModelId());
			}
			trafficModelInterResultTurnMapper.insertList(turnResultList);
		}
		if(intResultList != null && !intResultList.isEmpty()){
			for(Iterator<TrafficModelInterResultInt> it = intResultList.iterator();it.hasNext();){
				TrafficModelInterResultInt item = it.next();
				item.setSimulateId(base.getId());
				item.setModelId(base.getSimulateModelId());
			}
			trafficModelInterResultIntMapper.insertList(intResultList);
		}
		
	}
	
	
	public List<TrafficSimulateBase> findAllSimulate(TrafficSimulateBase cond){
		return trafficSimulateBaseMapper.selectAll(cond);
	}

	public List<TrafficSimulateBase> selectSimOnModelType(){
		return trafficSimulateBaseMapper.selectSimOnModelType();
	}

	public TrafficSimulateBase findSimulateById(Integer id){
		return trafficSimulateBaseMapper.selectByPrimaryKey(id);
	}

	/**
	 * 宏观模型
	 */
	public Pagetor<TrafficModelBigResult> getModelBigResultPageList(int pageNo, int pageSize, Map<String, Object> conditions){
		Pagetor<TrafficModelBigResult> pagetor = new Pagetor<TrafficModelBigResult>();
		// 验证页面大小
		if (0 >= pageSize) {
			pageSize = defaultPageSize;
		}
		pagetor.setPageSize(pageSize);
		// 取得总页数

		List<?> countList = getModelBigResultPageList(pageNo, pageSize, conditions, true);
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
		List<?> list = getModelBigResultPageList(pageNo, pageSize, conditions, false);
		pagetor.setPageList(list);
		pagetor.refreshPageInfo();
		return pagetor;
	}

	private List<?> getModelBigResultPageList(int pageNo, int pageSize,Map<String, Object> conditions, boolean isCount){
		if(isCount){
			List<Integer> result = new ArrayList<Integer>();
			result.add(trafficModelBigResultMapper.selectChartStatisticsCountByCon(conditions));
			return result;
		}

		conditions.put("offset", (pageNo -1 ) * pageSize);
		conditions.put("limit", pageSize);
		return trafficModelBigResultMapper.selectChartStatisticsByCon(conditions);
	}

	public List<Map<String, Object>> getChartStatisticsModelBigResultPageList(Map<String, Object> conditions){
		return trafficModelBigResultMapper.selectChartSumStatisticsByCon(conditions);

	}
	
	/**
	 * 中观模型
	 */
	public Pagetor<TrafficModelMatlabResult> getMidModelResultPageList(int pageNo, int pageSize, Map<String, Object> conditions){
		Pagetor<TrafficModelMatlabResult> pagetor = new Pagetor<TrafficModelMatlabResult>();
		// 验证页面大小
		if (0 >= pageSize) {
			pageSize = defaultPageSize;
		}
		pagetor.setPageSize(pageSize);
		// 取得总页数

		List<?> countList = getMidModelResultPageList(pageNo, pageSize, conditions, true);
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
		List<?> list = getMidModelResultPageList(pageNo, pageSize, conditions, false);
		pagetor.setPageList(list);
		pagetor.refreshPageInfo();
		return pagetor;
	}
	private List<?> getMidModelResultPageList(int pageNo, int pageSize,Map<String, Object> conditions, boolean isCount){
		if(isCount){
			List<Integer> result = new ArrayList<Integer>();
			result.add(trafficModelMatlabResultMapper.getMidModelResultCount(conditions));
			return result;
		}

		conditions.put("offset", (pageNo -1 ) * pageSize);
		conditions.put("limit", pageSize);
		return trafficModelMatlabResultMapper.getMidModelResultPageList(conditions);
	}
	public List<Map<String, Object>> getMidModelResultAvgChart(Map<String, Object> conditions){
		return trafficModelMatlabResultMapper.getMidModelResultAvgChart(conditions);

	}
	
	
	/**
	 * 微观模型
	 */
	public Pagetor<TrafficModelMiniResult> getMicModelResultPageList(int pageNo, int pageSize, Map<String, Object> conditions){
		Pagetor<TrafficModelMiniResult> pagetor = new Pagetor<TrafficModelMiniResult>();
		// 验证页面大小
		if (0 >= pageSize) {
			pageSize = defaultPageSize;
		}
		pagetor.setPageSize(pageSize);
		// 取得总页数

		List<?> countList = getMicModelResultPageList(pageNo, pageSize, conditions, true);
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
		List<?> list = getMicModelResultPageList(pageNo, pageSize, conditions, false);
		pagetor.setPageList(list);
		pagetor.refreshPageInfo();
		return pagetor;
	}
	private List<?> getMicModelResultPageList(int pageNo, int pageSize,Map<String, Object> conditions, boolean isCount){
		if(isCount){
			List<Integer> result = new ArrayList<Integer>();
			result.add(trafficModelMiniResultMapper.getMicModelResultCount(conditions));
			return result;
		}

		conditions.put("offset", (pageNo -1 ) * pageSize);
		conditions.put("limit", pageSize);
		return trafficModelMiniResultMapper.getMicModelResultPageList(conditions);
	}
	public List<Map<String, Object>> getMicModelResultAvgChart(Map<String, Object> conditions){
		return trafficModelMiniResultMapper.getMicModelResultAvgChart(conditions);
	}
	
	//路口模型
	public Pagetor<TrafficModelMiniResult> getInterModelResultPageList1(int pageNo, int pageSize, Map<String, Object> conditions){
		Pagetor<TrafficModelMiniResult> pagetor = new Pagetor<TrafficModelMiniResult>();
		// 验证页面大小
		if (0 >= pageSize) {
			pageSize = defaultPageSize;
		}
		pagetor.setPageSize(pageSize);
		// 取得总页数

		List<?> countList = getInterModelResultPageList1(pageNo, pageSize, conditions, true);
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
		List<?> list = getInterModelResultPageList1(pageNo, pageSize, conditions, false);
		pagetor.setPageList(list);
		pagetor.refreshPageInfo();
		return pagetor;
	}
	private List<?> getInterModelResultPageList1(int pageNo, int pageSize,Map<String, Object> conditions, boolean isCount){
		if(isCount){
			List<Integer> result = new ArrayList<Integer>();
			result.add(trafficModelInterResultApproachMapper.getInterApproachModelResultCount(conditions));
			return result;
		}

		conditions.put("offset", (pageNo -1 ) * pageSize);
		conditions.put("limit", pageSize);
		return trafficModelInterResultApproachMapper.getInterApproachModelResultPageList(conditions);
	}
	
	//转向模型
	public Pagetor<TrafficModelMiniResult> getInterModelResultPageList2(int pageNo, int pageSize, Map<String, Object> conditions){
		Pagetor<TrafficModelMiniResult> pagetor = new Pagetor<TrafficModelMiniResult>();
		// 验证页面大小
		if (0 >= pageSize) {
			pageSize = defaultPageSize;
		}
		pagetor.setPageSize(pageSize);
		// 取得总页数

		List<?> countList = getInterModelResultPageList2(pageNo, pageSize, conditions, true);
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
		List<?> list = getInterModelResultPageList2(pageNo, pageSize, conditions, false);
		pagetor.setPageList(list);
		pagetor.refreshPageInfo();
		return pagetor;
	}
	private List<?> getInterModelResultPageList2(int pageNo, int pageSize,Map<String, Object> conditions, boolean isCount){
		if(isCount){
			List<Integer> result = new ArrayList<Integer>();
			result.add(trafficModelInterResultTurnMapper.getInterTurnModelResultCount(conditions));
			return result;
		}

		conditions.put("offset", (pageNo -1 ) * pageSize);
		conditions.put("limit", pageSize);
		return trafficModelInterResultTurnMapper.getInterTurnModelResultPageList(conditions);
	}
	
	
	public boolean deleteByType(Integer id,String type){
		int row = 0 ;
		int row2 = 0;
		if(type=="宏观"){
			row = trafficSimulateBaseMapper.deleteByPrimaryKey(id);
			row2 = trafficModelBigResultMapper.deleteBySimulate_id(id);
			if(row!=0 && row2!=0){
				return true;
			}
		}else{
			row = trafficSimulateBaseMapper.deleteByPrimaryKey(id);
			if(row!=0){
				return true;
			}
		}
		return false;
	}
	public List<TrafficSimulateBase> getListSimulate(
			TrafficSimulateBase trafficSimulateBase) {
		return trafficSimulateBaseMapper.selectByPage(trafficSimulateBase);
	}

	public int selectCount() {
		TrafficSimulateBase record = new TrafficSimulateBase();
		return trafficSimulateBaseMapper.selectCount(record);
	}
}
