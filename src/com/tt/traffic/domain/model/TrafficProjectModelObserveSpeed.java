package com.tt.traffic.domain.model;

public class TrafficProjectModelObserveSpeed extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4673315310524559913L;

    private Integer id;
    private Integer model_id;
    private Float postMile;
    private String location;
    private String type;
    private String time_type;
    private String direction_type;
    private String time;
    private Float speed;
    private String createTime;
    private Integer yn;
    
    
    
	public TrafficProjectModelObserveSpeed() {
		super();
	}
	

	
	public TrafficProjectModelObserveSpeed(Integer id, Integer model_id, Float postMile, String location, String type,
			String time_type, String direction_type, String time, Float speed, String createTime, Integer yn) {
		super();
		this.id = id;
		this.model_id = model_id;
		this.postMile = postMile;
		this.location = location;
		this.type = type;
		this.time_type = time_type;
		this.direction_type = direction_type;
		this.time = time;
		this.speed = speed;
		this.createTime = createTime;
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
	public Float getPostMile() {
		return postMile;
	}
	public void setPostMile(Float postMile) {
		this.postMile = postMile;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime_type() {
		return time_type;
	}
	public void setTime_type(String time_type) {
		this.time_type = time_type;
	}
	public String getDirection_type() {
		return direction_type;
	}
	public void setDirection_type(String direction_type) {
		this.direction_type = direction_type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Float getSpeed() {
		return speed;
	}
	public void setSpeed(Float speed) {
		this.speed = speed;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getYn() {
		return yn;
	}
	public void setYn(Integer yn) {
		this.yn = yn;
	}

}
