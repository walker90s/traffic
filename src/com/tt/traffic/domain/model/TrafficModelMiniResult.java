package com.tt.traffic.domain.model;

public class TrafficModelMiniResult {
    private Integer id;

    private Integer modelId;

    private Integer simulateId;

    private Integer roadId;

    private String startInterval;

    private String endInterval;

    private String linkGroup;

    private String linkType;

    private Float linkCount;

    private Float linkDelay;

    private Float linkDensity;

    private Float linkFlow;

    private String linkLevel;

    private String serviceMeasure;

    private Float losValue;

    private Float linkSpeed;

    private Float linkTravelTime;

    private Float linkVdt;

    private Float linkVht;

    private Float linkPtd;

    private Float linkStopTime;

    private Byte yn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getRoadId() {
        return roadId;
    }

    public void setRoadId(Integer roadId) {
        this.roadId = roadId;
    }

    public Integer getSimulateId() {
        return simulateId;
    }

    public void setSimulateId(Integer simulateId) {
        this.simulateId = simulateId;
    }

    public String getStartInterval() {
        return startInterval;
    }

    public void setStartInterval(String startInterval) {
        this.startInterval = startInterval == null ? null : startInterval.trim();
    }

    public String getEndInterval() {
        return endInterval;
    }

    public void setEndInterval(String endInterval) {
        this.endInterval = endInterval == null ? null : endInterval.trim();
    }

    public String getLinkGroup() {
        return linkGroup;
    }

    public void setLinkGroup(String linkGroup) {
        this.linkGroup = linkGroup == null ? null : linkGroup.trim();
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
    }

    public Float getLinkCount() {
        return linkCount;
    }

    public void setLinkCount(Float linkCount) {
        this.linkCount = linkCount;
    }

    public Float getLinkDelay() {
        return linkDelay;
    }

    public void setLinkDelay(Float linkDelay) {
        this.linkDelay = linkDelay;
    }

    public Float getLinkDensity() {
        return linkDensity;
    }

    public void setLinkDensity(Float linkDensity) {
        this.linkDensity = linkDensity;
    }

    public Float getLinkFlow() {
        return linkFlow;
    }

    public void setLinkFlow(Float linkFlow) {
        this.linkFlow = linkFlow;
    }

    public String getLinkLevel() {
        return linkLevel;
    }

    public void setLinkLevel(String linkLevel) {
        this.linkLevel = linkLevel == null ? null : linkLevel.trim();
    }

    public String getServiceMeasure() {
        return serviceMeasure;
    }

    public void setServiceMeasure(String serviceMeasure) {
        this.serviceMeasure = serviceMeasure == null ? null : serviceMeasure.trim();
    }

    public Float getLosValue() {
        return losValue;
    }

    public void setLosValue(Float losValue) {
        this.losValue = losValue;
    }

    public Float getLinkSpeed() {
        return linkSpeed;
    }

    public void setLinkSpeed(Float linkSpeed) {
        this.linkSpeed = linkSpeed;
    }

    public Float getLinkTravelTime() {
        return linkTravelTime;
    }

    public void setLinkTravelTime(Float linkTravelTime) {
        this.linkTravelTime = linkTravelTime;
    }

    public Float getLinkVdt() {
        return linkVdt;
    }

    public void setLinkVdt(Float linkVdt) {
        this.linkVdt = linkVdt;
    }

    public Float getLinkVht() {
        return linkVht;
    }

    public void setLinkVht(Float linkVht) {
        this.linkVht = linkVht;
    }

    public Float getLinkPtd() {
        return linkPtd;
    }

    public void setLinkPtd(Float linkPtd) {
        this.linkPtd = linkPtd;
    }

    public Float getLinkStopTime() {
        return linkStopTime;
    }

    public void setLinkStopTime(Float linkStopTime) {
        this.linkStopTime = linkStopTime;
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }
}