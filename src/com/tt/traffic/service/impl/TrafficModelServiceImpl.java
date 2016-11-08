package com.tt.traffic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.tt.traffic.common.enums.ModelTypeEnum;
import com.tt.traffic.dao.TrafficIntersectionMapper;
import com.tt.traffic.dao.TrafficModelBigMapper;
import com.tt.traffic.dao.TrafficModelBigResultMapper;
import com.tt.traffic.dao.TrafficModelMapper;
import com.tt.traffic.dao.TrafficModelMatlabResultMapper;
import com.tt.traffic.dao.TrafficModelMatlatMapper;
import com.tt.traffic.dao.TrafficModelMiniMapper;
import com.tt.traffic.dao.TrafficModelMiniResultMapper;
import com.tt.traffic.domain.model.TrafficIntersection;
import com.tt.traffic.domain.model.TrafficModel;
import com.tt.traffic.domain.model.TrafficModelBig;
import com.tt.traffic.domain.model.TrafficModelMatlab;
import com.tt.traffic.domain.model.TrafficModelMatlabResult;
import com.tt.traffic.domain.model.TrafficModelMini;
import com.tt.traffic.domain.model.TrafficModelMiniResult;
import com.tt.traffic.service.TrafficModelService;

/**
 * Created by admin on 2015/10/14.
 */
@Service
public class TrafficModelServiceImpl implements TrafficModelService {
    @Resource
    private TrafficModelMapper trafficModelMapper;

    @Resource
    private TrafficModelBigResultMapper trafficModelBigResultMapper;

    @Resource
    private TrafficIntersectionMapper trafficIntersectionMapper;

    @Resource
    private TrafficModelBigMapper trafficModelBigMapper;

    @Resource
    private TrafficModelMatlatMapper trafficModelMatlatMapper;

    @Resource
    private TrafficModelMatlabResultMapper trafficModelMatlabResultMapper;

    @Resource
    private TrafficModelMiniMapper trafficModelMiniMapper;

    @Resource
    private TrafficModelMiniResultMapper trafficModelMiniResultMapper;

    public List<TrafficModel> getModelList() {
        return trafficModelMapper.getModelList();
    }

    public List<TrafficModel> getModelAllList() {

        List<TrafficModel> trafficModelList = trafficModelMapper.getModelList();
        for(TrafficModel trafficModel : trafficModelList) {
            int type = trafficModel.getType();
            int modelId = trafficModel.getId();

            if(type == ModelTypeEnum.BIG_MODEL.getValue()){
                //宏观模型
                List<TrafficModelBig> trafficModelBigList = trafficModelBigMapper.selectTrafficModelBigByModelId(modelId);
                trafficModel.setTrafficModelBigList(trafficModelBigList);
            }else if(type == ModelTypeEnum.MEDIUM_MODEL.getValue()){
                //中观模型
                List<TrafficModelMatlab> trafficModelMatlabList = trafficModelMatlatMapper.getMatlabListByModelId(modelId);
                trafficModel.setTrafficModelMatlabList(trafficModelMatlabList);
            }else if(type == ModelTypeEnum.MICROCOSMIC_MODEL.getValue()){
                //微观模型
                List<TrafficModelMini> trafficModelMiniList = trafficModelMiniMapper.getMiniListByModelId(modelId);
                trafficModel.setTrafficModelMiniList(trafficModelMiniList);
            }else if(type == ModelTypeEnum.INTERSECTION_MODEL.getValue()){
                //路口模型
                List<TrafficIntersection> trafficIntersectionList = trafficIntersectionMapper.getIntersectionListByModelId(modelId);
                trafficModel.setTrafficIntersectionList(trafficIntersectionList);
            }
        }
        return trafficModelList;
    }

    public JSONArray getModelResult(Integer type, Integer resultId, Integer modelId) {
        JSONArray resultArray = new JSONArray();
        //宏观
        if(type == ModelTypeEnum.BIG_MODEL.getValue()){
            List<TrafficModelBig> trafficModelBigResultList =  trafficModelBigMapper.selectTrafficModelAndResultByResultId(resultId);
            resultArray = (JSONArray)JSONArray.toJSON(trafficModelBigResultList);
        }
        //中观
        if(type == ModelTypeEnum.MEDIUM_MODEL.getValue()){
            //List<TrafficModelMatlabResult>
            List<TrafficModelMatlab> trafficModelMatlabList = trafficModelMatlatMapper.getMatlabListByModelId(modelId);
            resultArray = (JSONArray)JSONArray.toJSON(trafficModelMatlabList);
        }
        //微观
        if(type == ModelTypeEnum.MICROCOSMIC_MODEL.getValue()){
            List<TrafficModelMini> trafficModelMiniList = trafficModelMiniMapper.getMiniListByModelId(modelId);
            resultArray = (JSONArray)JSONArray.toJSON(trafficModelMiniList);
        }
        if(type == ModelTypeEnum.INTERSECTION_MODEL.getValue()){
            //路口模型
            List<TrafficIntersection> trafficIntersectionList = trafficIntersectionMapper.getIntersectionListByModelId(modelId);
            resultArray = (JSONArray)JSONArray.toJSON(trafficIntersectionList);
        }
        return resultArray;
    }

    public JSONArray getModelResultByTypeAndRoadId(Integer type, Integer roadId, Integer resultId, Byte aorb) {
        JSONArray resultArray = new JSONArray();
        //宏观
        if(type == ModelTypeEnum.BIG_MODEL.getValue()){
            return resultArray;
        }
        //中观
        if(type == ModelTypeEnum.MEDIUM_MODEL.getValue()){
            //List<TrafficModelMatlabResult>
            TrafficModelMatlabResult matlabResult = new TrafficModelMatlabResult();
            matlabResult.setRoadId(roadId);
            matlabResult.setSimulateId(resultId);
            matlabResult.setAorb(aorb);
            List<TrafficModelMatlabResult> trafficModelMatlabResultList = trafficModelMatlabResultMapper.selectByRecord(matlabResult);
            resultArray = (JSONArray)JSONArray.toJSON(trafficModelMatlabResultList);
        }
        //微观
        if(type == ModelTypeEnum.MICROCOSMIC_MODEL.getValue()){
            TrafficModelMiniResult trafficModelMiniResult = new TrafficModelMiniResult();
            trafficModelMiniResult.setSimulateId(resultId);
            trafficModelMiniResult.setRoadId(roadId);
            List<TrafficModelMiniResult> trafficModelMiniResultList = trafficModelMiniResultMapper.getList(trafficModelMiniResult);
            resultArray = (JSONArray)JSONArray.toJSON(trafficModelMiniResultList);
        }
        return resultArray;
    }

    public List<TrafficModel> getListByname(String name){

        return trafficModelMapper.getListByname(name);
    };

    public int updatePath(TrafficModel trafficModel){
        if(trafficModel.getSpeedData()!=null && trafficModel.getSpeedData()!=""){
            return trafficModelMapper.updateSpeedData(trafficModel);
        }else if(trafficModel.getFlowData()!=null && trafficModel.getFlowData()!=""){
            return trafficModelMapper.updateFlowData(trafficModel);
        }
        return 0;
    };
}
