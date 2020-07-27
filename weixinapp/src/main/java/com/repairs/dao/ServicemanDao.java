package com.repairs.dao;

import java.util.List;

import com.repairs.bo.Campusbo;
import com.repairs.bo.Servicemanbo;

public interface ServicemanDao {
	/**
	 * 查询维修人
	 * @param Servicemanbo serviceman
	 * @return 	List<Servicemanbo>
	 */
	public List<Servicemanbo> selectServiceman(Servicemanbo serviceman);
	/**
	 * 添加维修人员
	 * @param servicemanbo
	 * @return int
	 * **/
	public int insertServiceman(Servicemanbo servicemanbo);
	/**
	 * 修改维修人员状态
	 * @param servicemanbo
	 * @return int
	 * **/
	public int updateServiceman(Servicemanbo servicemanbo);

	public List<Servicemanbo> selectServicemanByState();

	
	public void deleteServiceman(Servicemanbo servicemanbo);
	//修改校区,状态
	public int updatesermanstate(Servicemanbo servicemanbo);
	
	public List<Servicemanbo> selectServicemanByCampusId(Integer id);
	
	public Servicemanbo selectServicemanById(Integer id);
}
