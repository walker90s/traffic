package com.tt.traffic.domain.model;

public class TrafficModelBig {
    private Integer id;

    private Integer roadId;

    private Integer modelId;

    private Float length;

    private Byte dir;

    private Integer linkId;

    private String linkType;

    private Integer abSpeed;

    private Integer abLanes;

    private Integer abCapacit;

    private Float abCapaci1;

    private Float abTime;

    private Float baSpeed;

    private Integer baLanes;

    private Integer baCapacit;

    private Float baCapaci1;

    private Float baTime;

    private String roadname;

    private String abGeom;

    private String baGeom;

    private Byte yn;

    private TrafficModelBigResult trafficModelBigResult;

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

    public TrafficModelBigResult getTrafficModelBigResult() {
        return trafficModelBigResult;
    }

    public void setTrafficModelBigResult(TrafficModelBigResult trafficModelBigResult) {
        this.trafficModelBigResult = trafficModelBigResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoadId() {
        return roadId;
    }

    public void setRoadId(Integer roadId) {
        this.roadId = roadId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Byte getDir() {
        return dir;
    }

    public void setDir(Byte dir) {
        this.dir = dir;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
    }

    public Integer getAbSpeed() {
        return abSpeed;
    }

    public void setAbSpeed(Integer abSpeed) {
        this.abSpeed = abSpeed;
    }

    public Integer getAbLanes() {
        return abLanes;
    }

    public void setAbLanes(Integer abLanes) {
        this.abLanes = abLanes;
    }

    public Integer getAbCapacit() {
        return abCapacit;
    }

    public void setAbCapacit(Integer abCapacit) {
        this.abCapacit = abCapacit;
    }

    public Float getAbCapaci1() {
        return abCapaci1;
    }

    public void setAbCapaci1(Float abCapaci1) {
        this.abCapaci1 = abCapaci1;
    }

    public Float getAbTime() {
        return abTime;
    }

    public void setAbTime(Float abTime) {
        this.abTime = abTime;
    }

    public Float getBaSpeed() {
        return baSpeed;
    }

    public void setBaSpeed(Float baSpeed) {
        this.baSpeed = baSpeed;
    }

    public Integer getBaLanes() {
        return baLanes;
    }

    public void setBaLanes(Integer baLanes) {
        this.baLanes = baLanes;
    }

    public Integer getBaCapacit() {
        return baCapacit;
    }

    public void setBaCapacit(Integer baCapacit) {
        this.baCapacit = baCapacit;
    }

    public Float getBaCapaci1() {
        return baCapaci1;
    }

    public void setBaCapaci1(Float baCapaci1) {
        this.baCapaci1 = baCapaci1;
    }

    public Float getBaTime() {
        return baTime;
    }

    public void setBaTime(Float baTime) {
        this.baTime = baTime;
    }

    public String getRoadname() {
        return roadname;
    }

    public void setRoadname(String roadname) {
        this.roadname = roadname == null ? null : roadname.trim();
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }
}