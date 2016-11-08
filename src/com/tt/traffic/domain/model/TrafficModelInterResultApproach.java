package com.tt.traffic.domain.model;

public class TrafficModelInterResultApproach {
    private Integer id;

    private Integer modelId;

    private Integer simulateId;
    
    private Integer crossId;

    private String direction;

    private Float approachDelay;

    private String approachLos;

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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public Float getApproachDelay() {
        return approachDelay;
    }

    public void setApproachDelay(Float approachDelay) {
        this.approachDelay = approachDelay;
    }

    public String getApproachLos() {
        return approachLos;
    }

    public void setApproachLos(String approachLos) {
        this.approachLos = approachLos == null ? null : approachLos.trim();
    }
}