package com.tt.traffic.domain.model;

import java.util.List;

public class TrafficModel {
    private Integer id;

    private Byte type;

    private Integer userId;

    private String name;

    private Double lon;

    private String lat;

    private String content;

    private String area;
    private String target;
    private String speedData;
    private String flowData;
    private String modelPath;
    private String resultPath;
    private String chart;
    private String crosslevel;
    private String linklevel;
    private Integer yn;

    private List<TrafficModelBig> trafficModelBigList;

    private List<TrafficIntersection> trafficIntersectionList;

    private List<TrafficModelMatlab> trafficModelMatlabList;

    private List<TrafficModelMini> trafficModelMiniList;

    public List<TrafficModelMini> getTrafficModelMiniList() {
        return trafficModelMiniList;
    }

    public void setTrafficModelMiniList(List<TrafficModelMini> trafficModelMiniList) {
        this.trafficModelMiniList = trafficModelMiniList;
    }

    public List<TrafficIntersection> getTrafficIntersectionList() {
        return trafficIntersectionList;
    }

    public List<TrafficModelBig> getTrafficModelBigList() {
        return trafficModelBigList;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSpeedData() {
        return speedData;
    }

    public void setSpeedData(String speedData) {
        this.speedData = speedData;
    }

    public String getFlowData() {
        return flowData;
    }

    public void setFlowData(String flowData) {
        this.flowData = flowData;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public String getCrosslevel() {
        return crosslevel;
    }

    public void setCrosslevel(String crosslevel) {
        this.crosslevel = crosslevel;
    }

    public String getLinklevel() {
        return linklevel;
    }

    public void setLinklevel(String linklevel) {
        this.linklevel = linklevel;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public void setTrafficModelBigList(List<TrafficModelBig> trafficModelBigList) {
        this.trafficModelBigList = trafficModelBigList;
    }

    public void setTrafficIntersectionList(List<TrafficIntersection> trafficIntersectionList) {
        this.trafficIntersectionList = trafficIntersectionList;
    }

    public List<TrafficModelMatlab> getTrafficModelMatlabList() {
        return trafficModelMatlabList;
    }

    public void setTrafficModelMatlabList(List<TrafficModelMatlab> trafficModelMatlabList) {
        this.trafficModelMatlabList = trafficModelMatlabList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}