package com.repairs.service;


import com.repairs.bo.Userlogbo;
import com.repairs.utils.CommonsResult;

public interface UserlogService {
	/**
	 * 分页查询登录日志
	 * @param offset
	 * @param limit
	 * @return
	 */
	public CommonsResult selectUserlogByPage(Integer offset,Integer limit);
	/**
	 * 删除登录日志
	 * @param userlogid
	 * @return
	 */
	public CommonsResult delectUserlog(Integer userlogid);
	/**
	 * 添加登录日志
	 * @param userlogname
	 * @param logtime
	 * @param state
	 * @return
	 */
	public CommonsResult insertUserlog(String userlogname,String logtime,Integer state);
	
}
