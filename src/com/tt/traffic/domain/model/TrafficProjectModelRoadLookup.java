package com.tt.traffic.domain.model;

/**
 * 路段和路段组关联中间表
 * @author fulg
 *
 */
public class TrafficProjectModelRoadLookup extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6713603513811807519L;

	private Integer id;
    private Integer model_year_type_id;
    private Integer linkID;
    private String direction;
    private String type;
    private Integer sectionId;
    private String createTime;
    private Integer yn;
	public TrafficProjectModelRoadLookup() {
		super();
	}
	public TrafficProjectModelRoadLookup(Integer id, Integer model_year_type_id, Integer linkID, String direction,
			String type, Integer sectionId, String createTime, Integer yn) {
		super();
		this.id = id;
		this.model_year_type_id = model_year_type_id;
		this.linkID = linkID;
		this.direction = direction;
		this.type = type;
		this.sectionId = sectionId;
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
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
