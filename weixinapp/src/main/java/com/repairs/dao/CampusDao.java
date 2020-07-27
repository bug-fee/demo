package com.repairs.dao;

import java.util.List;

import com.repairs.bo.Campusbo;

public interface CampusDao {

	/**
	 * 查询所有校区
	 * @return List<Campusbo>
	 */
	public List<Campusbo> selectAllCampus();
	/**
	 * 
	 */
	public List<Campusbo> selectCampus(Campusbo campusbo);

}
