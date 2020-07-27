package com.repairs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairs.bo.Campusbo;
import com.repairs.dao.CampusDao;
import com.repairs.service.CampusService;

@Service("campusService")
public class CampusServiceImpl implements CampusService {
	@Autowired
	private CampusDao campusDao;
	
	@Override
	public List<Campusbo> selectAllCampus() {
		return campusDao.selectAllCampus();
	}

}
