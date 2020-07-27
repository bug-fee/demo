package com.repairs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.repairs.bo.Repairbo;
import com.repairs.bo.Servicemanbo;
import com.repairs.utils.CommonsResult;

public interface ServicemanService {
	/**
	 * 用户申请成为维修人员
	 **/
	public String addServiceMan(Integer campusId, String openId);
	/**
	 * 查询出维修人员最新的五条记录
	 * **/
	public String serviceSelectNews(String openId);

	/**
	 * 查询维修记录
	 * @param queryReqpir 查询对象
	 * @param session HttpSession
	 * @return JSON结果集
	 */
	public CommonsResult searchRepairs(Repairbo repair,HttpSession session);
	
	/**
	 * 更改维修状态： 0未受理1处理中2报修完成3任意状态都查询处理4未解决
	 * @param queryReqpir 查询对象
	 * @param session HttpSession
	 * @return JSON结果集
	 */
	public CommonsResult updateRepairs(Repairbo queryReqpir, HttpSession session);

	public String searchServiceMan(String openId,String imgurl);

	public String updateServiceGender(String usergender, Integer userid);

	public String updateServicemanName(String usernamemsg, Integer userid);

	public String updateServicemanTell(String adminphone, Integer servicemanid);

	public String updateServicemanCampus(String campusname, Integer servicemanid);
	public String updateServicemanState(Integer servicemanid,Integer state,String authorizedTime);
	//修改serviceman的辖区
	public String changesermanstate(Integer id, Integer state);
	
	/**
	 * 查询出所有维修人员
	 * @return List<ServiceManbo>
	 */
	public List<Servicemanbo> selectAllServiceman(Servicemanbo servicemanbo);

	public List<Servicemanbo> selectServicemanByCampusId(Integer id);
	
	public Servicemanbo selectServicemanById(Integer id);
}
