package com.repairs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairs.bo.Userlogbo;
import com.repairs.dao.UserlogDao;
import com.repairs.service.UserlogService;
import com.repairs.utils.CommonsResult;

@Service("userlogService")
public class UserlogServiceImpl implements UserlogService{

	@Autowired
	private UserlogDao userlogDao;
	@Override
	public CommonsResult selectUserlogByPage(Integer offset, Integer limit) {
		Userlogbo userlog = new Userlogbo();
		userlog.setOffset(offset);
		userlog.setLimit(limit);
		int size = userlogDao.selectUserlogCount();
		List<Userlogbo> userlogs = userlogDao.selectUserlogBypage(userlog);
		if(size>0){
			int total = size%limit==0?size/limit:size/limit+1;
			return new CommonsResult(true, "", userlogs, total);
		}else{
			return new CommonsResult(false, "无数据");
		}
	}

	@Override
	public CommonsResult delectUserlog(Integer userlogid) {
		try {
			userlogDao.deleteUserlog(userlogid);
			return new CommonsResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonsResult(false);
		}
		
	}

	@Override
	public CommonsResult insertUserlog(String userlogname, String logtime,
			Integer state) {
		Userlogbo userlog = new Userlogbo();
		userlog.setName(userlogname);
		userlog.setLogtime(logtime);
		userlog.setState(state);
		try {
			userlogDao.insertUserlog(userlog);
			return new CommonsResult(true);
		} catch (Exception e) {
			return new CommonsResult(false, "无数据");
		}
	}

}
