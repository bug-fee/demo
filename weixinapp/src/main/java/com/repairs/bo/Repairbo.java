package com.repairs.bo;

public class Repairbo {
	private Integer id;	//id
	private String repairName;//报修人/
	private String subDeptName;//提交部门名称
	private String floorName;//楼名字
	private String roomName;//房间名字
	private String workTell;//办公电话
	private String mobileTell;//移动电话
	private String facilityName;//设备名称
	private String facilityModel;//设备编号
	private Integer facilityNum;//设备数量
	private String faultTitle;//故障标题
	private String faultDescribe;//故障描述
	private Integer state;//维修状态 0未受理 1处理中 2报修完成 3任意状态都查询处理 4未解决
	private String defeatedreason;//未解决原因
	private String subTime;//报修时间
	private String endTime;//结束时间
	private Double charge;//维修花费费用
	private Campusbo campus;//校区
	private Userbo user;//用户
	private Servicemanbo serviceman;//维修人
	private Adminbo adminbo;
	private String maintenanceContent; //维修内容

	private String seriano;//订单序列号
	private String receiptTime;//受理日期
	private Integer lookup;//首页普通用户是否查看过消息(小红点) 0没有,1查看过
	private Integer serviceLookup;//首页维修人员是否查看过消息(小红点) 0普通用户没有,1查看过
	private Integer adminLookup;//首页管理员是否查看过消息(小红点) 0普通用户没有,1查看过
	
	private Integer offset;//分页起点
	private Integer limit;//分页结束点
	
	private String order;//排序
	private String updatetime;//修改时间
	
	private String chargeSource;//费用来源
	private String signatureDepartment;//签字部门
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getServiceLookup() {
		return serviceLookup;
	}
	public void setServiceLookup(Integer serviceLookup) {
		this.serviceLookup = serviceLookup;
	}
	public String getReceiptTime() {
		return receiptTime;
	}
	public void setReceiptTime(String receiptTime) {
		this.receiptTime = receiptTime;
	}
	public Integer getAdminLookup() {
		return adminLookup;
	}
	public void setAdminLookup(Integer adminLookup) {
		this.adminLookup = adminLookup;
	}
	public String getSubDeptName() {
		return subDeptName;
	}
	public void setSubDeptName(String subDeptName) {
		this.subDeptName = subDeptName;
	}
	public String getFloorName() {
		return floorName;
	}
	public String getSubTime() {
		return subTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}
	public String getDefeatedreason() {
		return defeatedreason;
	}
	public void setDefeatedreason(String defeatedreason) {
		this.defeatedreason = defeatedreason;
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
	public Integer getLookup() {
		return lookup;
	}
	public void setLookup(Integer lookup) {
		this.lookup = lookup;
	}
	public Double getCharge() {
		return charge;
	}
	public void setCharge(Double charge) {
		this.charge = charge;
	}
	public void setWorkTell(String workTell) {
		this.workTell = workTell;
	}
	public String getMobileTell() {
		return mobileTell;
	}
	public void setMobileTell(String mobileTell) {
		this.mobileTell = mobileTell;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getFacilityModel() {
		return facilityModel;
	}
	public void setFacilityModel(String facilityModel) {
		this.facilityModel = facilityModel;
	}
	public String getSeriano() {
		return seriano;
	}
	public void setSeriano(String seriano) {
		this.seriano = seriano;
	}
	public Integer getFacilityNum() {
		return facilityNum;
	}
	public void setFacilityNum(Integer facilityNum) {
		this.facilityNum = facilityNum;
	}
	public String getFaultTitle() {
		return faultTitle;
	}
	public void setFaultTitle(String faultTitle) {
		this.faultTitle = faultTitle;
	}
	public String getFaultDescribe() {
		return faultDescribe;
	}
	public void setFaultDescribe(String faultDescribe) {
		this.faultDescribe = faultDescribe;
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
	public Userbo getUser() {
		return user;
	}
	public void setUser(Userbo user) {
		this.user = user;
	}
	public Servicemanbo getServiceman() {
		return serviceman;
	}
	public void setServiceman(Servicemanbo serviceman) {
		this.serviceman = serviceman;
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
	public Repairbo() {
		
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getMaintenanceContent() {
		return maintenanceContent;
	}
	public void setMaintenanceContent(String maintenanceContent) {
		this.maintenanceContent = maintenanceContent;
	}
	public String getRepairName() {
		return repairName;
	}
	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
	public Adminbo getAdminbo() {
		return adminbo;
	}
	public void setAdminbo(Adminbo adminbo) {
		this.adminbo = adminbo;
	}
	public String getChargeSource() {
		return chargeSource;
	}
	public void setChargeSource(String chargeSource) {
		this.chargeSource = chargeSource;
	}
	public String getSignatureDepartment() {
		return signatureDepartment;
	}
	public void setSignatureDepartment(String signatureDepartment) {
		this.signatureDepartment = signatureDepartment;
	}

	
	

}
