package com.tt.traffic.domain.model;

/**
 * 路段信息表
 * @author fulg
 *
 */

public class TrafficProjectModelRoadLink extends BaseObj{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5892458167030957660L;

	private Integer id;
    private Integer model_year_type_id;
    private Integer linkID;
    private Integer oneway_twoway;
    private String ab_direction;
    private String ba_direction;
    private Integer a_id;
    private Float a_latitude;
    private Float a_longitude;
    private Integer b_id;
    private Float b_latitude;
    private Float b_longitude;
    private Integer ab_lanes;
    private Integer ba_lanes;
    private Float length_mile;
    private String type;
    private String roadName;
    private String routeName;
    private Integer routeID;
    private String createTime;
    private Integer yn;

	public TrafficProjectModelRoadLink() {
		super();
	}

	public TrafficProjectModelRoadLink(Integer id, Integer model_year_type_id, Integer linkID, Integer oneway_twoway,
			String ab_direction, String ba_direction, Integer a_id, Float a_latitude, Float a_longitude, Integer b_id,
			Float b_latitude, Float b_longitude, Integer ab_lanes, Integer ba_lanes, Float length_mile, String type,
			String roadName, String routeName, Integer routeID, String createTime, Integer yn) {
		super();
		this.id = id;
		this.model_year_type_id = model_year_type_id;
		this.linkID = linkID;
		this.oneway_twoway = oneway_twoway;
		this.ab_direction = ab_direction;
		this.ba_direction = ba_direction;
		this.a_id = a_id;
		this.a_latitude = a_latitude;
		this.a_longitude = a_longitude;
		this.b_id = b_id;
		this.b_latitude = b_latitude;
		this.b_longitude = b_longitude;
		this.ab_lanes = ab_lanes;
		this.ba_lanes = ba_lanes;
		this.length_mile = length_mile;
		this.type = type;
		this.roadName = roadName;
		this.routeName = routeName;
		this.routeID = routeID;
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

	public Integer getLinkID() {
		return linkID;
	}

	public void setLinkID(Integer linkID) {
		this.linkID = linkID;
	}

	public Integer getOneway_twoway() {
		return oneway_twoway;
	}

	public void setOneway_twoway(Integer oneway_twoway) {
		this.oneway_twoway = oneway_twoway;
	}

	public String getAb_direction() {
		return ab_direction;
	}

	public void setAb_direction(String ab_direction) {
		this.ab_direction = ab_direction;
	}

	public String getBa_direction() {
		return ba_direction;
	}

	public void setBa_direction(String ba_direction) {
		this.ba_direction = ba_direction;
	}

	public Integer getA_id() {
		return a_id;
	}

	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}

	public Float getA_latitude() {
		return a_latitude;
	}

	public void setA_latitude(Float a_latitude) {
		this.a_latitude = a_latitude;
	}

	public Float getA_longitude() {
		return a_longitude;
	}

	public void setA_longitude(Float a_longitude) {
		this.a_longitude = a_longitude;
	}

	public Integer getB_id() {
		return b_id;
	}

	public void setB_id(Integer b_id) {
		this.b_id = b_id;
	}

	public Float getB_latitude() {
		return b_latitude;
	}

	public void setB_latitude(Float b_latitude) {
		this.b_latitude = b_latitude;
	}

	public Float getB_longitude() {
		return b_longitude;
	}

	public void setB_longitude(Float b_longitude) {
		this.b_longitude = b_longitude;
	}

	public Integer getAb_lanes() {
		return ab_lanes;
	}

	public void setAb_lanes(Integer ab_lanes) {
		this.ab_lanes = ab_lanes;
	}

	public Integer getBa_lanes() {
		return ba_lanes;
	}

	public void setBa_lanes(Integer ba_lanes) {
		this.ba_lanes = ba_lanes;
	}

	public Float getLength_mile() {
		return length_mile;
	}

	public void setLength_mile(Float length_mile) {
		this.length_mile = length_mile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Integer getRouteID() {
		return routeID;
	}

	public void setRouteID(Integer routeID) {
		this.routeID = routeID;
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
