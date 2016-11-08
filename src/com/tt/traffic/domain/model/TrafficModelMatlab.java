package com.tt.traffic.domain.model;

public class TrafficModelMatlab {
    private Integer id;

    private String modelId;

    private String roadId;

    private Byte aorb;

    private Integer densityLinkId;

    private Integer flowSpeedLinkId;

    private String roadname;

    private String linkType;

    private Float length;

    private Byte yn;

    private String geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId == null ? null : roadId.trim();
    }

    public Byte getAorb() {
        return aorb;
    }

    public void setAorb(Byte aorb) {
        this.aorb = aorb;
    }

    public Integer getDensityLinkId() {
        return densityLinkId;
    }

    public void setDensityLinkId(Integer densityLinkId) {
        this.densityLinkId = densityLinkId;
    }

    public Integer getFlowSpeedLinkId() {
        return flowSpeedLinkId;
    }

    public void setFlowSpeedLinkId(Integer flowSpeedLinkId) {
        this.flowSpeedLinkId = flowSpeedLinkId;
    }

    public String getRoadname() {
        return roadname;
    }

    public void setRoadname(String roadname) {
        this.roadname = roadname == null ? null : roadname.trim();
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType == null ? null : linkType.trim();
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom == null ? null : geom.trim();
    }
}