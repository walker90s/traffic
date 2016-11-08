package com.tt.traffic.domain.model;

public class TrafficProject extends BaseObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3833146892484780812L;

    private Integer id;
    private Integer user_id;
    private String projectName;
    private String proBackground;
    private String need_goals;
    private String createTime;
    private String updateTime;
    private Integer yn;
    
	public TrafficProject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrafficProject(Integer id,Integer user_id, String projectName, String proBackground, String need_goals, String createTime,
			String updateTime, Integer yn) {
		super();
		this.id = id;
		this.user_id=user_id;
		this.projectName = projectName;
		this.proBackground = proBackground;
		this.need_goals = need_goals;
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProBackground() {
		return proBackground;
	}
	public void setProBackground(String proBackground) {
		this.proBackground = proBackground;
	}
	public String getNeed_goals() {
		return need_goals;
	}
	public void setNeed_goals(String need_goals) {
		this.need_goals = need_goals;
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
