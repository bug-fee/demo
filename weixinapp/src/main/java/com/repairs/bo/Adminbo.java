package com.repairs.bo;

public class Adminbo {
	
	private Integer id;//id
	private String openId;//openId
	private String name;//name
	private String gender;//gernder;
	private String tell;//手机
	private String userName;//账号
	private String password;//密码
	private Integer campusboId;//区域
	private Integer offset;
	private Integer limit;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getCampusboId() {
		return campusboId;
	}
	public void setCampusboId(Integer campusboId) {
		this.campusboId = campusboId;
	}
	
	
	
}
