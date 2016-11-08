package com.tt.traffic.domain.model;

public class User {
    private Integer id;
    private String name;
    private String loginname;
    private String password;
    private String studentid;
    private String studentClass;
    private String professional;
    private String registeredTime;
    private Integer type;
    private Integer yn;
    
    private Integer offset;
    private Integer limit;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

	public String getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(String registeredTime) {
		this.registeredTime = registeredTime;
	}

	public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getStudentid() {
        return studentid;
    }
    public void setStudentid(String studentid) {
        this.studentid = studentid;
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
        this.name = name == null ? null : name.trim();
    }
}