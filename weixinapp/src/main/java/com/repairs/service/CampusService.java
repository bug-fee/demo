package com.repairs.service;

import java.util.List;

import com.repairs.bo.Campusbo;

public interface CampusService {
	
	/**
	 * 查询所有校区
	 * @return List<Campusbo>
	 */
	public List<Campusbo> selectAllCampus();

}
