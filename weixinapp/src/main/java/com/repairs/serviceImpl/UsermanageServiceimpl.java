package com.repairs.serviceImpl;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairs.bo.Servicemanbo;
import com.repairs.dao.ServicemanDao;
import com.repairs.service.UsermanageService;
@Service
public class UsermanageServiceimpl implements UsermanageService{
	@Autowired
private ServicemanDao servicemanDao;
/**
 * 根据id查询serviceman
 */
	@Override
	public String selectServiceman(int id) {
		Servicemanbo servicemanbo=new Servicemanbo();
		servicemanbo.setId(id);
		List<Servicemanbo> servicemanbos=servicemanDao.selectServiceman(servicemanbo);
		JSONArray jsonArray=JSONArray.fromObject(servicemanbos);
		return jsonArray.toString();
	}
/**
 * 根据Serviceman的id更改state
 */
	@Override
	public void updateServiceman(int id, int state) {
		Servicemanbo servicemanbo=new Servicemanbo();
		servicemanbo.setId(id);
		servicemanbo.setState(state);
		servicemanDao.updateServiceman(servicemanbo);
	}
}
