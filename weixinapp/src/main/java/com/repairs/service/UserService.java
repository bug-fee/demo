package com.repairs.service;

import java.util.List;

import com.repairs.bo.Repairbo;
import com.repairs.bo.Servicemanbo;
import com.repairs.bo.Userbo;

public interface UserService {
	
	public String selUserByOpenId(String openId,String userimgUrl);
	public void updataUserCampus(Integer userid,String campusName);
	public void updataUserTell(String openId,String usertell);
	public void updataUserName(String openId,String username);
	public void updataUserGender(Integer userid, String usergender);
	public List<Userbo> selectUserByOpenId(String openId);
	public String bandUser(String openId, Userbo user,String imageUrl);
	
	public String callAdmin(Integer id);
	/**
	 * 提醒维修人员去维修
	 * @param id 维修记录的id
	 * @throws Exception 
	 * **/
	public String callService(Repairbo repair, Servicemanbo servicemanbo);
}
