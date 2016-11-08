package com.tt.traffic.domain.model;

public class TrafficProjectModelResultMoe extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2530738422202909210L;

    private Integer id;
    private Integer model_year_type_id;
    private String modelName;
    private String time;
    private String vehicleType;
    private String direction;
    private Integer sectionId;
    private String type;
    private Float travelTime_minute;
    private Float speed_mph;
    private Float delay_hour;
    private Float volume;
    private Float vht;
    private Float vmt;
    private Float vmt_vht;
    private Float occupancy;
    private String truckPercentge;
    private String createTime;
    private Integer yn;

	public TrafficProjectModelResultMoe() {
		super();
	}
	public TrafficProjectModelResultMoe(Integer id, Integer model_year_type_id, String modelName, String time,
			String vehicleType, String direction, Integer sectionId, String type, Float travelTime_minute,
			Float speed_mph, Float delay_hour, Float volume, Float vht, Float vmt, Float vmt_vht, Float occupancy,
			String truckPercentge, String createTime, Integer yn) {
		super();
		this.id = id;
		this.model_year_type_id = model_year_type_id;
		this.modelName = modelName;
		this.time = time;
		this.vehicleType = vehicleType;
		this.direction = direction;
		this.sectionId = sectionId;
		this.type = type;
		this.travelTime_minute = travelTime_minute;
		this.speed_mph = speed_mph;
		this.delay_hour = delay_hour;
		this.volume = volume;
		this.vht = vht;
		this.vmt = vmt;
		this.vmt_vht = vmt_vht;
		this.occupancy = occupancy;
		this.truckPercentge = truckPercentge;
		this.createTime = createTime;
		this.yn = yn;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getModel_year_type_id() {
		return model_year_type_id;
	}
	public void setModel_year_type_id(Integer model_year_type_id) {
		this.model_year_type_id = model_year_type_id;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getTravelTime_minute() {
		return travelTime_minute;
	}
	public void setTravelTime_minute(Float travelTime_minute) {
		this.travelTime_minute = travelTime_minute;
	}
	public Float getSpeed_mph() {
		return speed_mph;
	}
	public void setSpeed_mph(Float speed_mph) {
		this.speed_mph = speed_mph;
	}
	public Float getDelay_hour() {
		return delay_hour;
	}
	public void setDelay_hour(Float delay_hour) {
		this.delay_hour = delay_hour;
	}
	public Float getVolume() {
		return volume;
	}
	public void setVolume(Float volume) {
		this.volume = volume;
	}
	public Float getVht() {
		return vht;
	}
	public void setVht(Float vht) {
		this.vht = vht;
	}
	public Float getVmt() {
		return vmt;
	}
	public void setVmt(Float vmt) {
		this.vmt = vmt;
	}
	public Float getVmt_vht() {
		return vmt_vht;
	}
	public void setVmt_vht(Float vmt_vht) {
		this.vmt_vht = vmt_vht;
	}
	public Float getOccupancy() {
		return occupancy;
	}
	public void setOccupancy(Float occupancy) {
		this.occupancy = occupancy;
	}
	public String getTruckPercentge() {
		return truckPercentge;
	}
	public void setTruckPercentge(String truckPercentge) {
		this.truckPercentge = truckPercentge;
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
