package com.tt.traffic.domain.model;

public class TrafficProjectModelYearType extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1147885162927015222L;

    private Integer id;
    private Integer model_id;
    private Integer model_year_type;
    private String model_name;
    private String model_desc;
    private String road_network;
    private String traffic_array;
    private String lookup;
    private String road_flow;
    private String road_speed;
    private String cosResultMoe;
    private String createTime;
    private String updateTime;
    private Integer yn;
    
    
    
	public TrafficProjectModelYearType() {
		super();
	}
	
	public TrafficProjectModelYearType(Integer id, Integer model_id, Integer model_year_type, String model_name,
			String model_desc, String road_network, String traffic_array,String lookup, String road_flow, String road_speed,
			String cosResultMoe, String createTime, String updateTime, Integer yn) {
		super();
		this.id = id;
		this.model_id = model_id;
		this.model_year_type = model_year_type;
		this.model_name = model_name;
		this.model_desc = model_desc;
		this.road_network = road_network;
		this.traffic_array = traffic_array;
		this.lookup = lookup;
		this.road_flow = road_flow;
		this.road_speed = road_speed;
		this.cosResultMoe = cosResultMoe;
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
	public Integer getModel_id() {
		return model_id;
	}
	public void setModel_id(Integer model_id) {
		this.model_id = model_id;
	}
	public Integer getModel_year_type() {
		return model_year_type;
	}
	public void setModel_year_type(Integer model_year_type) {
		this.model_year_type = model_year_type;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getModel_desc() {
		return model_desc;
	}
	public void setModel_desc(String model_desc) {
		this.model_desc = model_desc;
	}
	public String getRoad_network() {
		return road_network;
	}
	public void setRoad_network(String road_network) {
		this.road_network = road_network;
	}
	public String getTraffic_array() {
		return traffic_array;
	}
	public void setTraffic_array(String traffic_array) {
		this.traffic_array = traffic_array;
	}
	public String getLookup() {
		return lookup;
	}
	public void setLookup(String lookup) {
		this.lookup = lookup;
	}
	public String getRoad_flow() {
		return road_flow;
	}
	public void setRoad_flow(String road_flow) {
		this.road_flow = road_flow;
	}
	public String getRoad_speed() {
		return road_speed;
	}
	public void setRoad_speed(String road_speed) {
		this.road_speed = road_speed;
	}
	public String getCosResultMoe() {
		return cosResultMoe;
	}
	public void setCosResultMoe(String cosResultMoe) {
		this.cosResultMoe = cosResultMoe;
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

    
}
