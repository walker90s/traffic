package com.tt.traffic.domain.model;

public class TrafficProjectModelObserveFlow extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1704962623908127744L;

    private Integer id;
    private Integer model_id;
    private String observe_position;
    private String road_name;
    private String dir;
    private Float length;
    private String road_type;
    private Float lon;
    private Float lat;
    private Integer dataYear;
    private String time;
    private Float observe_flow;
    private String source;
    private String accuracy;
    private String note;
    private String createTime;
    private Integer yn;

	public TrafficProjectModelObserveFlow(Integer id, Integer model_id, String observe_position, String road_name,
			String dir, Float length, String road_type, Float lon, Float lat,Integer dataYear, String time, Float observe_flow,
			String source,String accuracy,String note, String createTime, Integer yn) {
		super();
		this.id = id;
		this.model_id = model_id;
		this.observe_position = observe_position;
		this.road_name = road_name;
		this.dir = dir;
		this.length = length;
		this.road_type = road_type;
		this.lon = lon;
		this.lat = lat;
		this.dataYear = dataYear;
		this.time = time;
		this.observe_flow = observe_flow;
		this.source = source;
		this.accuracy =accuracy;
		this.note = note;
		this.createTime = createTime;
		this.yn = yn;
	}

	public TrafficProjectModelObserveFlow() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getObserve_position() {
		return observe_position;
	}
	public void setObserve_position(String observe_position) {
		this.observe_position = observe_position;
	}
	public String getRoad_name() {
		return road_name;
	}
	public void setRoad_name(String road_name) {
		this.road_name = road_name;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public Float getLength() {
		return length;
	}
	public void setLength(Float length) {
		this.length = length;
	}
	public String getRoad_type() {
		return road_type;
	}
	public void setRoad_type(String road_type) {
		this.road_type = road_type;
	}
	public Float getLon() {
		return lon;
	}
	public void setLon(Float lon) {
		this.lon = lon;
	}
	public Float getLat() {
		return lat;
	}
	public void setLat(Float lat) {
		this.lat = lat;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Float getObserve_flow() {
		return observe_flow;
	}
	public void setObserve_flow(Float observe_flow) {
		this.observe_flow = observe_flow;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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

	public Integer getDataYear() {
		return dataYear;
	}

	public void setDataYear(Integer dataYear) {
		this.dataYear = dataYear;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
