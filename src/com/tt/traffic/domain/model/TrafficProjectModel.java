package com.tt.traffic.domain.model;

public class TrafficProjectModel extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6515219051380578063L;

    private Integer id;
    private Integer project_id;
    private Integer type_id;
    private String name;
    private String model_desc;
    private String road_flow;
    private String speed_data;
    private String createTime;
    private String updateTime;
    private Integer yn;
    
    
    public TrafficProjectModel() {
		// TODO Auto-generated constructor stub
	}


	public TrafficProjectModel(Integer id, Integer project_id, Integer type_id, String name, String model_desc,
			String road_flow, String speed_data, String createTime, String updateTime, Integer yn) {
		super();
		this.id = id;
		this.project_id = project_id;
		this.type_id = type_id;
		this.name = name;
		this.model_desc = model_desc;
		this.road_flow = road_flow;
		this.speed_data = speed_data;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.yn = yn;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getProject_id() {
		return project_id;
	}


	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}


	public Integer getType_id() {
		return type_id;
	}


	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getModel_desc() {
		return model_desc;
	}


	public void setModel_desc(String model_desc) {
		this.model_desc = model_desc;
	}


	public String getRoad_flow() {
		return road_flow;
	}


	public void setRoad_flow(String road_flow) {
		this.road_flow = road_flow;
	}


	public String getSpeed_data() {
		return speed_data;
	}


	public void setSpeed_data(String speed_data) {
		this.speed_data = speed_data;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	public Integer getYn() {
		return yn;
	}


	public void setYn(Integer yn) {
		this.yn = yn;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
