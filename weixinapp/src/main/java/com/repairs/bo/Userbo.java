package com.repairs.bo;

public class Userbo {
	private Integer id;	//id
	private String openId;//openId
	private String name;	//name
	private String gender;//gender;
	private String tell;//手机号
	private String createTime;//创建时间
	private String headimgurl;//头像地址
	private Campusbo campus;//校区
	private String floorName;//楼名字
	private String roomName;//房间名字
	private String workTell;//办公电话
	private String subDeptName;//部门名称
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Campusbo getCampus() {
		return campus;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setCampus(Campusbo campus) {
		this.campus = campus;
	}
	public Userbo() {
		
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getWorkTell() {
		return workTell;
	}
	public void setWorkTell(String workTell) {
		this.workTell = workTell;
	}
	public String getSubDeptName() {
		return subDeptName;
	}
	public void setSubDeptName(String subDeptName) {
		this.subDeptName = subDeptName;
	}
	@Override
	public String toString() {
		return "Userbo [id=" + id + ", openId=" + openId + ", name=" + name
				+ ", gender=" + gender + ", tell=" + tell + ", createTime="
				+ createTime + ", headimgurl=" + headimgurl + ", campus="
				+ campus + ", floorName=" + floorName + ", roomName="
				+ roomName + ", workTell=" + workTell + ", subDeptName="
				+ subDeptName + ", offset=" + offset + ", limit=" + limit + "]";
	}
	
	
}
