package com.tt.traffic.domain.model;

public class TrafficModelMini {
    private Integer id;

    private Integer modelId;

    private Integer roadId;

    private Integer aorb;

    private String linkGroup;

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

    public Integer getAorb() {
        return aorb;
    }

    public void setAorb(Integer aorb) {
        this.aorb = aorb;
    }

    public String getLinkGroup() {
        return linkGroup;
    }

    public void setLinkGroup(String linkGroup) {
        this.linkGroup = linkGroup == null ? null : linkGroup.trim();
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