package com.tt.traffic.domain.model;

public class TrafficModelInterResultTurn {
    private Integer id;

    private Integer modelId;

    private Integer simulateId;

    private Integer crossId;

    private String turn;

    private Integer volume;

    private Integer flow;

    private Float vcRatio;

    private Float controlDelay;

    private Float queueDelay;

    private Float totalDelay;

    private String los;

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
    
    public Integer getSimulateId() {
        return simulateId;
    }

    public void setSimulateId(Integer simulateId) {
        this.simulateId = simulateId;
    }

    public Integer getCrossId() {
        return crossId;
    }

    public void setCrossId(Integer crossId) {
        this.crossId = crossId;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn == null ? null : turn.trim();
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }

    public Float getVcRatio() {
        return vcRatio;
    }

    public void setVcRatio(Float vcRatio) {
        this.vcRatio = vcRatio;
    }

    public Float getControlDelay() {
        return controlDelay;
    }

    public void setControlDelay(Float controlDelay) {
        this.controlDelay = controlDelay;
    }

    public Float getQueueDelay() {
        return queueDelay;
    }

    public void setQueueDelay(Float queueDelay) {
        this.queueDelay = queueDelay;
    }

    public Float getTotalDelay() {
        return totalDelay;
    }

    public void setTotalDelay(Float totalDelay) {
        this.totalDelay = totalDelay;
    }

    public String getLos() {
        return los;
    }

    public void setLos(String los) {
        this.los = los == null ? null : los.trim();
    }
}