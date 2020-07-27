package com.repairs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.repairs.bo.Userbo;

public interface UserDao {
	/**
	 * 查找用户
	 * @param User user
	 * @return List<Userbo>
	 */
	public List<Userbo> selectUser(Userbo user);
	
	public void updateUserCampus(@Param("userid")Integer userid,@Param("campusid")Integer campusid);
	
	public void updateUser(Userbo user);
	public void insertUser(Userbo user);
}
