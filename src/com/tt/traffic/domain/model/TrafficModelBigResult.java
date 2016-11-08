package com.tt.traffic.domain.model;

public class TrafficModelBigResult {
    private Integer id;

    private Integer simulateId;
    
    private Integer modelId;

    private Integer roadId;

    private Float abFlow;

    private Float baFlow;

    private Float totFlow;

    private Float abTime;

    private Float baTime;

    private Float maxTime;

    private Float abVoc;

    private Float baVoc;

    private Float maxVoc;

    private Float abVkmt;

    private String baVkmt;

    private Float totVkmt;

    private Float abVht;

    private Float baVht;

    private Float totVht;

    private Float abSpeed;

    private Float baSpeed;

    private Float abVdf;

    private Float baVdf;

    private Float maxVdf;

    private Byte yn;
    /**
     * 坐标数据，取模型表数据，用于前台展示，不需要导入
     */
    private String abGeom;


    private String baGeom;

    public String getAbGeom() {
        return abGeom;
    }

    public void setAbGeom(String abGeom) {
        this.abGeom = abGeom;
    }

    public String getBaGeom() {
        return baGeom;
    }

    public void setBaGeom(String baGeom) {
        this.baGeom = baGeom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSimulateId() {
		return simulateId;
	}

	public void setSimulateId(Integer simulateId) {
		this.simulateId = simulateId;
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

    public Float getAbFlow() {
        return abFlow;
    }

    public void setAbFlow(Float abFlow) {
        this.abFlow = abFlow;
    }

    public Float getBaFlow() {
        return baFlow;
    }

    public void setBaFlow(Float baFlow) {
        this.baFlow = baFlow;
    }

    public Float getTotFlow() {
        return totFlow;
    }

    public void setTotFlow(Float totFlow) {
        this.totFlow = totFlow;
    }

    public Float getAbTime() {
        return abTime;
    }

    public void setAbTime(Float abTime) {
        this.abTime = abTime;
    }

    public Float getBaTime() {
        return baTime;
    }

    public void setBaTime(Float baTime) {
        this.baTime = baTime;
    }

    public Float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Float maxTime) {
        this.maxTime = maxTime;
    }

    public Float getAbVoc() {
        return abVoc;
    }

    public void setAbVoc(Float abVoc) {
        this.abVoc = abVoc;
    }

    public Float getBaVoc() {
        return baVoc;
    }

    public void setBaVoc(Float baVoc) {
        this.baVoc = baVoc;
    }

    public Float getMaxVoc() {
        return maxVoc;
    }

    public void setMaxVoc(Float maxVoc) {
        this.maxVoc = maxVoc;
    }

    public Float getAbVkmt() {
        return abVkmt;
    }

    public void setAbVkmt(Float abVkmt) {
        this.abVkmt = abVkmt;
    }

    public String getBaVkmt() {
        return baVkmt;
    }

    public void setBaVkmt(String baVkmt) {
        this.baVkmt = baVkmt == null ? null : baVkmt.trim();
    }

    public Float getTotVkmt() {
        return totVkmt;
    }

    public void setTotVkmt(Float totVkmt) {
        this.totVkmt = totVkmt;
    }

    public Float getAbVht() {
        return abVht;
    }

    public void setAbVht(Float abVht) {
        this.abVht = abVht;
    }

    public Float getBaVht() {
        return baVht;
    }

    public void setBaVht(Float baVht) {
        this.baVht = baVht;
    }

    public Float getTotVht() {
        return totVht;
    }

    public void setTotVht(Float totVht) {
        this.totVht = totVht;
    }

    public Float getAbSpeed() {
        return abSpeed;
    }

    public void setAbSpeed(Float abSpeed) {
        this.abSpeed = abSpeed;
    }

    public Float getBaSpeed() {
        return baSpeed;
    }

    public void setBaSpeed(Float baSpeed) {
        this.baSpeed = baSpeed;
    }

    public Float getAbVdf() {
        return abVdf;
    }

    public void setAbVdf(Float abVdf) {
        this.abVdf = abVdf;
    }

    public Float getBaVdf() {
        return baVdf;
    }

    public void setBaVdf(Float baVdf) {
        this.baVdf = baVdf;
    }

    public Float getMaxVdf() {
        return maxVdf;
    }

    public void setMaxVdf(Float maxVdf) {
        this.maxVdf = maxVdf;
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }
}