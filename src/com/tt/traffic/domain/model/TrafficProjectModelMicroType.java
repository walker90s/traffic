package com.tt.traffic.domain.model;

public class TrafficProjectModelMicroType extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5938858072680315739L;

    private Integer id;
    private Integer model_id;
    private Integer model_year_type;
    private Integer plan_type;
    private String model_name;
    private String model_desc;
    private String traffic_array_1;
    private String traffic_array_2;
    private String cosResult_1;
    private String cosResult_2;
    private String model_correct_1;
    private String model_correct_2;
    private String createTime;
    private String updteTime;
    private Integer yn;

	public TrafficProjectModelMicroType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TrafficProjectModelMicroType(Integer id, Integer model_id, Integer model_year_type, Integer plan_type,
			String model_name, String model_desc, String traffic_array_1, String traffic_array_2, String cosResult_1,
			String cosResult_2, String model_correct_1, String model_correct_2, String createTime, String updteTime,
			Integer yn) {
		super();
		this.id = id;
		this.model_id = model_id;
		this.model_year_type = model_year_type;
		this.plan_type = plan_type;
		this.model_name = model_name;
		this.model_desc = model_desc;
		this.traffic_array_1 = traffic_array_1;
		this.traffic_array_2 = traffic_array_2;
		this.cosResult_1 = cosResult_1;
		this.cosResult_2 = cosResult_2;
		this.model_correct_1 = model_correct_1;
		this.model_correct_2 = model_correct_2;
		this.createTime = createTime;
		this.updteTime = updteTime;
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
	public Integer getPlan_type() {
		return plan_type;
	}
	public void setPlan_type(Integer plan_type) {
		this.plan_type = plan_type;
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
	public String getTraffic_array_1() {
		return traffic_array_1;
	}
	public void setTraffic_array_1(String traffic_array_1) {
		this.traffic_array_1 = traffic_array_1;
	}
	public String getTraffic_array_2() {
		return traffic_array_2;
	}
	public void setTraffic_array_2(String traffic_array_2) {
		this.traffic_array_2 = traffic_array_2;
	}
	public String getCosResult_1() {
		return cosResult_1;
	}
	public void setCosResult_1(String cosResult_1) {
		this.cosResult_1 = cosResult_1;
	}
	public String getCosResult_2() {
		return cosResult_2;
	}
	public void setCosResult_2(String cosResult_2) {
		this.cosResult_2 = cosResult_2;
	}
	public String getModel_correct_1() {
		return model_correct_1;
	}
	public void setModel_correct_1(String model_correct_1) {
		this.model_correct_1 = model_correct_1;
	}
	public String getModel_correct_2() {
		return model_correct_2;
	}
	public void setModel_correct_2(String model_correct_2) {
		this.model_correct_2 = model_correct_2;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdteTime() {
		return updteTime;
	}
	public void setUpdteTime(String updteTime) {
		this.updteTime = updteTime;
	}
	public Integer getYn() {
		return yn;
	}
	public void setYn(Integer yn) {
		this.yn = yn;
	}
    
    
    
}
