package com.tt.traffic.domain.model;

public class TrafficModelMatlabResult {
    private Integer id;

    private Integer modelId;

    private Integer simulateId;
    
    private Integer roadId;

    private Byte aorb;

    private String time;

    private Float density;

    private Integer flow;

    private Float speed;

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

    public Byte getAorb() {
        return aorb;
    }

    public void setAorb(Byte aorb) {
        this.aorb = aorb;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Float getDensity() {
        return density;
    }

    public void setDensity(Float density) {
        this.density = density;
    }

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }

	public Integer getSimulateId() {
		return simulateId;
	}

	public void setSimulateId(Integer simulateId) {
		this.simulateId = simulateId;
	}
}