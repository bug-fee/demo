package com.repairs.dao;

import java.util.List;

import com.repairs.bo.Userlogbo;

public interface UserlogDao {
	/**
	 * 分页查询登录记录
	 * @param userlog
	 * @return
	 */
	public List<Userlogbo> selectUserlogBypage(Userlogbo userlog);
	/**
	 * 查询所有登录记录条数
	 * @return
	 */
	public int selectUserlogCount();
	/**
	 * 插入登录记录
	 * @param userlog
	 */
	public void insertUserlog(Userlogbo userlog);
	/**
	 * 删除某条登录记录
	 * @param userid
	 */
	public void deleteUserlog(Integer userlogid);
}
