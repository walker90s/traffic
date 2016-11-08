package com.tt.traffic.domain.model;

public class TrafficModelInterResultInt {
    private Integer id;

    private Integer modelId;

    private Integer simulateId;

    private Integer crossId;

    private String name;

    private Integer cycleLength;

    private Integer offset;

    private String controlType;

    private Float voc;

    private String los;

    private Float delay;

    private Float icu;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(Integer cycleLength) {
        this.cycleLength = cycleLength;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType == null ? null : controlType.trim();
    }

    public Float getVoc() {
        return voc;
    }

    public void setVoc(Float voc) {
        this.voc = voc;
    }

    public String getLos() {
        return los;
    }

    public void setLos(String los) {
        this.los = los == null ? null : los.trim();
    }

    public Float getDelay() {
        return delay;
    }

    public void setDelay(Float delay) {
        this.delay = delay;
    }

    public Float getIcu() {
        return icu;
    }

    public void setIcu(Float icu) {
        this.icu = icu;
    }
}