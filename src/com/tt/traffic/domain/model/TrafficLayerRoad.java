package com.tt.traffic.domain.model;

public class TrafficLayerRoad {
    private Integer id;

    private String name;

    private String geom;

    private String osmId;

    private Integer snode;

    private Integer tnode;

    private String content;

    private Byte yn;

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

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom == null ? null : geom.trim();
    }

    public String getOsmId() {
        return osmId;
    }

    public void setOsmId(String osmId) {
        this.osmId = osmId == null ? null : osmId.trim();
    }

    public Integer getSnode() {
        return snode;
    }

    public void setSnode(Integer snode) {
        this.snode = snode;
    }

    public Integer getTnode() {
        return tnode;
    }

    public void setTnode(Integer tnode) {
        this.tnode = tnode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }
}