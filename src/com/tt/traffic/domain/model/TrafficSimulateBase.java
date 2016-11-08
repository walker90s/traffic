package com.tt.traffic.domain.model;

import java.util.Date;

import com.tt.traffic.common.util.TimeUtil;

public class TrafficSimulateBase {

    private Integer id;

    private String name;

    private String simulateSoftId;
    private String simulateSoft;

    private Integer simulateModelId;
    private String simulateModel;

    private Integer local;
    
    private Long simulateTime;
    private Date simulateTimed;
    private String simulateTimes;
    
    private Long simulateStartTime;
    private Date simulateStartTimed;
    private String simulateStartTimes;

    private Long simulateEndTime;
    private Date simulateEndTimed;
    private String simulateEndTimes;

    private Long importTime;
    private Date importTimed;
    private String importTimes;
    
	private Long importUserId;
    private String importUserName="";

    private String comment;

    private Integer offset;
    
    private Integer limit;
    
    private String fileName;

	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
        this.name = name;
    }

    public String getSimulateSoft() {
        return simulateSoft;
    }

    public void setSimulateSoft(String simulateSoft) {
        this.simulateSoft = simulateSoft;
    }

    public String getSimulateModel() {
        return simulateModel;
    }

    public void setSimulateModel(String simulateModel) {
        this.simulateModel = simulateModel;
    }

    public Integer getLocal() {
		return local;
	}

	public void setLocal(Integer local) {
		this.local = local;
	}

	public Long getSimulateTime() {
        return simulateTime;
    }

    public void setSimulateTime(Long simulateTime) {
        this.simulateTime = simulateTime;
        this.simulateTimed = new Date(simulateTime);
        this.simulateTimes =  TimeUtil.getLocalString(simulateTimed);
    }

    public Long getSimulateStartTime() {
        return simulateStartTime;
    }

    public void setSimulateStartTime(Long simulateStartTime) {
        this.simulateStartTime = simulateStartTime;
        this.simulateStartTimed = new Date(simulateStartTime);
        this.simulateStartTimes = TimeUtil.getLocalString(simulateStartTimed);
    }

    public Long getSimulateEndTime() {
        return simulateEndTime;
    }

    public void setSimulateEndTime(Long simulateEndTime) {
        this.simulateEndTime = simulateEndTime;
        this.simulateEndTimed = new Date(simulateEndTime);
        this.simulateEndTimes = TimeUtil.getLocalString(simulateEndTimed);
    }

    public Long getImportTime() {
        return importTime;
    }

    public void setImportTime(Long importTime) {
        this.importTime = importTime;
        this.importTimed = new Date(importTime);
        this.importTimes = TimeUtil.getLocalString(importTimed);
    }

    public Long getImportUserId() {
		return importUserId;
	}

	public void setImportUserId(Long importUserId) {
		this.importUserId = importUserId;
	}

	public String getImportUserName() {
		if(importUserName == null )
			return "";
		return importUserName;
	}

	public void setImportUserName(String importUserName) {
		this.importUserName = importUserName;
	}

	public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Date getSimulateTimed() {
		return simulateTimed;
	}

	public void setSimulateTimed(Date simulateTimed) {
		this.simulateTimed = simulateTimed;
	}

	public Date getSimulateStartTimed() {
		return simulateStartTimed;
	}

	public void setSimulateStartTimed(Date simulateStartTimed) {
		this.simulateStartTimed = simulateStartTimed;
	}

	public Date getSimulateEndTimed() {
		return simulateEndTimed;
	}

	public void setSimulateEndTimed(Date simulateEndTimed) {
		this.simulateEndTimed = simulateEndTimed;
	}

	public Date getImportTimed() {
		return importTimed;
	}

	public void setImportTimed(Date importTimed) {
		this.importTimed = importTimed;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

    public String getSimulateStartTimes() {
		return simulateStartTimes;
	}

	public void setSimulateStartTimes(String simulateStartTimes) {
		this.simulateStartTimes = simulateStartTimes;
	}

	public String getSimulateEndTimes() {
		return simulateEndTimes;
	}

	public void setSimulateEndTimes(String simulateEndTimes) {
		this.simulateEndTimes = simulateEndTimes;
	}

	public String getImportTimes() {
		return importTimes;
	}

	public void setImportTimes(String importTimes) {
		this.importTimes = importTimes;
	}

	public String getSimulateTimes() {
		return simulateTimes;
	}

	public void setSimulateTimes(String simulateTimes) {
		this.simulateTimes = simulateTimes;
	}

	public String getSimulateSoftId() {
		return simulateSoftId;
	}

	public void setSimulateSoftId(String simulateSoftId) {
		this.simulateSoftId = simulateSoftId;
	}

	public Integer getSimulateModelId() {
		return simulateModelId;
	}

	public void setSimulateModelId(Integer simulateModelId) {
		this.simulateModelId = simulateModelId;
	}

}