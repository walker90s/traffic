package com.tt.traffic.domain.model;

public class TrafficIntersectionFlow {
    private Integer id;

    private Integer intersectionId;

    private Integer crossid;

    private String times;

    private Integer northFlow;

    private Integer northDirect;

    private Integer northLeft;

    private Integer northRight;

    private Integer eastFlow;

    private Integer eastDirect;

    private Integer eastLeft;

    private Integer eastRight;

    private Integer southFlow;

    private Integer southDirect;

    private Integer southLeft;

    private Integer southRight;

    private Integer westFlow;

    private Integer westDirect;

    private Integer westLeft;

    private Integer westRight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntersectionId() {
        return intersectionId;
    }

    public void setIntersectionId(Integer intersectionId) {
        this.intersectionId = intersectionId;
    }

    public Integer getCrossid() {
        return crossid;
    }

    public void setCrossid(Integer crossid) {
        this.crossid = crossid;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times == null ? null : times.trim();
    }

    public Integer getNorthFlow() {
        return northFlow;
    }

    public void setNorthFlow(Integer northFlow) {
        this.northFlow = northFlow;
    }

    public Integer getNorthDirect() {
        return northDirect;
    }

    public void setNorthDirect(Integer northDirect) {
        this.northDirect = northDirect;
    }

    public Integer getNorthLeft() {
        return northLeft;
    }

    public void setNorthLeft(Integer northLeft) {
        this.northLeft = northLeft;
    }

    public Integer getNorthRight() {
        return northRight;
    }

    public void setNorthRight(Integer northRight) {
        this.northRight = northRight;
    }

    public Integer getEastFlow() {
        return eastFlow;
    }

    public void setEastFlow(Integer eastFlow) {
        this.eastFlow = eastFlow;
    }

    public Integer getEastDirect() {
        return eastDirect;
    }

    public void setEastDirect(Integer eastDirect) {
        this.eastDirect = eastDirect;
    }

    public Integer getEastLeft() {
        return eastLeft;
    }

    public void setEastLeft(Integer eastLeft) {
        this.eastLeft = eastLeft;
    }

    public Integer getEastRight() {
        return eastRight;
    }

    public void setEastRight(Integer eastRight) {
        this.eastRight = eastRight;
    }

    public Integer getSouthFlow() {
        return southFlow;
    }

    public void setSouthFlow(Integer southFlow) {
        this.southFlow = southFlow;
    }

    public Integer getSouthDirect() {
        return southDirect;
    }

    public void setSouthDirect(Integer southDirect) {
        this.southDirect = southDirect;
    }

    public Integer getSouthLeft() {
        return southLeft;
    }

    public void setSouthLeft(Integer southLeft) {
        this.southLeft = southLeft;
    }

    public Integer getSouthRight() {
        return southRight;
    }

    public void setSouthRight(Integer southRight) {
        this.southRight = southRight;
    }

    public Integer getWestFlow() {
        return westFlow;
    }

    public void setWestFlow(Integer westFlow) {
        this.westFlow = westFlow;
    }

    public Integer getWestDirect() {
        return westDirect;
    }

    public void setWestDirect(Integer westDirect) {
        this.westDirect = westDirect;
    }

    public Integer getWestLeft() {
        return westLeft;
    }

    public void setWestLeft(Integer westLeft) {
        this.westLeft = westLeft;
    }

    public Integer getWestRight() {
        return westRight;
    }

    public void setWestRight(Integer westRight) {
        this.westRight = westRight;
    }
}