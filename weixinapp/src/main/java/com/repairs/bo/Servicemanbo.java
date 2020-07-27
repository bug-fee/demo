package com.repairs.bo;

public class Servicemanbo {
	private Integer id;//id
	private String openId;//openId
	private String name;//name
	private String gender;//gender
	private String tell;//电话
	private String authorizedTime;//审核通过时间
	private Integer state;//状态：0未审核(申请中状态)，1审核通过(正式维修员)，2取消授权(不能进入维修人员中心),3更改管辖区域状态(可以进入维修人员中心)
	private String headimgurl;//头像地址
	private Campusbo newcam;//申请更改的管辖区域
	private Campusbo campus;//校区
	
	private Integer offset;//分页起始
	private Integer limit;//分页结束
	
	public Integer getId() {
		return id;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
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
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Campusbo getCampus() {
		return campus;
	}
	public void setCampus(Campusbo campus) {
		this.campus = campus;
	}
	public String getAuthorizedTime() {
		return authorizedTime;
	}
	public void setAuthorizedTime(String authorizedTime) {
		this.authorizedTime = authorizedTime;
	}
	public Campusbo getNewcam() {
		return newcam;
	}
	public void setNewcam(Campusbo newcam) {
		this.newcam = newcam;
	}
	public Servicemanbo() {
		
	}
	
	
}
