package com.tt.traffic.domain.model;

import java.util.List;

public class TrafficIntersection {
    private Integer id;

    private Integer modelId;

    private String name;

    private String lon;

    private String lat;

    private String geom;

    private Integer crossId;

    private Integer cycle;

    private Integer phase;

    private Integer yn;

    private List<TrafficIntersectionTime> timeList;

    private List<TrafficIntersectionFlow> flowList;

    public List<TrafficIntersectionFlow> getFlowList() {
        return flowList;
    }

    public void setFlowList(List<TrafficIntersectionFlow> flowList) {
        this.flowList = flowList;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public List<TrafficIntersectionTime> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<TrafficIntersectionTime> timeList) {
        this.timeList = timeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public Integer getCrossId() {
        return crossId;
    }

    public void setCrossId(Integer crossId) {
        this.crossId = crossId;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}