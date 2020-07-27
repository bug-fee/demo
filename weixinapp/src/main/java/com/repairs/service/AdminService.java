package com.repairs.service;

import javax.servlet.http.HttpSession;

import com.repairs.bo.Adminbo;
import com.repairs.dao.AdminDao;
import com.repairs.utils.CommonsResult;

public interface AdminService {

	public String selAdminMsg(String openId, String userimgUrl);

	public String updateAdminName(Integer adminid, String newadminname);

	public String updateAdminGender(Integer adminid, String admingender);

	public String updateAdminPhone(Integer adminid, String adminphone);

	public String updateAdminAccount(Integer adminid, String adminaccount,
			String adminaccountpsd);
	/**
	 * 管理员查询出五条新信息
	 * **/
	public String selectNews();

	
	/**
	 * 用户管理列出所有用户
	 * @param name 用户姓名
	 * @param type 用户类型0:维修人员1:普通用户
	 * @param offset 分页起始
	 * @param limit 分页结束
	 * @return 
	 */
	public CommonsResult userList(String name, Integer type, Integer offset,Integer limit);

	/**
	 * 查看报修记录
	 * @param facilityName 设备名称
	 * @param facilityModel 设备编号
	 * @param faultTitle 故障标题
	 * @param offset 分页起始
	 * @param limit 分页结束
	 * @param sate 维修状态
	 */
	public CommonsResult selectAllrepair(String facilityName, String seriano,String faultTitle, Integer offset, Integer limit, Integer state,String order);
	/**
	 * 初始化管理员认证页面
	 * **/
	public String initAdminApprove();
	
	public CommonsResult selectAdminOne(Adminbo admin);
	
	
	public CommonsResult selectAdminPage(Adminbo admin);
	
	
	public CommonsResult selectAdmin(Adminbo admin);

/**
 *添加管理员 
 **/
	public CommonsResult insertAdmin(Adminbo admin);
	/**
	 * 
	 * 删除管理员
	 * **/
	public CommonsResult deleteAdmin(Integer id);
	
	
	public CommonsResult selectByCampusboId(Integer campusboId);
	
	public Adminbo selectByOpenId(String openId);
	
}