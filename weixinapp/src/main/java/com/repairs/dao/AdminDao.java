package com.repairs.dao;

import java.util.List;

import com.repairs.bo.Adminbo;

public interface AdminDao {
	/**
	 * 查找管理员
	 * @param Adminbo admin
	 * @return List<Adminbo>
	 */
	public List<Adminbo> selectAdmin(Adminbo admin);
	/**
	 * 插入管理员
	 * @param Adminbo admin
	 */
	public int insertAdmin(Adminbo admin);
	/**
	 * 修改管理员信息
	 * @author xiaofan
	 * @param admin
	 */
	public void updateAdmin(Adminbo admin);

	
	public Adminbo selectAdminOne(Adminbo admin);
	/**
	 * 删除管理人员
	 * @param id 管理员的id
	 * **/
	public int delteAdminById(Integer id);
	
	
	public List<Adminbo> selectAdminAll();
	
	public List<Adminbo> selectAdminPage(Adminbo admin);
	
	public Adminbo selectAdminByUserId(Adminbo admin);
	
	public List<Adminbo> selectByCampusboId(Integer campusboId);
	
	public Adminbo selectAdminByOpenId(String openId);
}
