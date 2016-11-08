package com.tt.traffic.domain.model;

public class TrafficIntersectionTime {
    private Integer id;

    private Integer crossid;

    private String name;

    private Integer cycle;

    private Integer phase;

    private Integer xlsid;

    private Integer transcadid;

    private Integer slink;

    private Integer tlink;

    private Integer phaseg;

    private Integer phasey;

    private Integer phaser;

    private Integer phasea;

    private Integer flow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrossid() {
        return crossid;
    }

    public void setCrossid(Integer crossid) {
        this.crossid = crossid;
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

    public Integer getXlsid() {
        return xlsid;
    }

    public void setXlsid(Integer xlsid) {
        this.xlsid = xlsid;
    }

    public Integer getTranscadid() {
        return transcadid;
    }

    public void setTranscadid(Integer transcadid) {
        this.transcadid = transcadid;
    }

    public Integer getSlink() {
        return slink;
    }

    public void setSlink(Integer slink) {
        this.slink = slink;
    }

    public Integer getTlink() {
        return tlink;
    }

    public void setTlink(Integer tlink) {
        this.tlink = tlink;
    }

    public Integer getPhaseg() {
        return phaseg;
    }

    public void setPhaseg(Integer phaseg) {
        this.phaseg = phaseg;
    }

    public Integer getPhasey() {
        return phasey;
    }

    public void setPhasey(Integer phasey) {
        this.phasey = phasey;
    }

    public Integer getPhaser() {
        return phaser;
    }

    public void setPhaser(Integer phaser) {
        this.phaser = phaser;
    }

    public Integer getPhasea() {
        return phasea;
    }

    public void setPhasea(Integer phasea) {
        this.phasea = phasea;
    }

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }
}