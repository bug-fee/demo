package com.repairs.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repairs.bo.Adminbo;
import com.repairs.bo.Campusbo;
import com.repairs.bo.Repairbo;
import com.repairs.bo.Servicemanbo;
import com.repairs.bo.Userbo;
import com.repairs.bo.WechatTemplate;
import com.repairs.dao.AdminDao;
import com.repairs.dao.CampusDao;
import com.repairs.dao.RepairDao;
import com.repairs.dao.ServicemanDao;
import com.repairs.dao.UserDao;
import com.repairs.service.UserService;
import com.repairs.utils.CommonUtil;
import com.repairs.utils.TemplateUtil;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private CampusDao campusDao;
	@Autowired
	private RepairDao repairDao;
	@Autowired
	private ServicemanDao servicemanDao;
	@Autowired
	private AdminDao adminDao;
	
	public String selUserByOpenId(String openId,String userimgurl) {
		Userbo user = new Userbo();
		user.setOpenId(openId);
		String userjson="";
		List<Userbo> usermsg = userDao.selectUser(user);
		if(usermsg.size()!=0){
			JSONArray userarray = JSONArray.fromObject(usermsg);
			JSONObject  obj =(JSONObject) userarray.get(0);
			obj.put("userimgUrl", userimgurl);
			userjson = userarray.toString();
		}
		return  userjson;
	}

	@Override
	public void updataUserCampus(Integer userid, String campusName) {
		Campusbo campus = new Campusbo();
		campus.setName(campusName);
		int campusid =campusDao.selectCampus(campus).get(0).getId();
		userDao.updateUserCampus(userid,campusid);
	}

	@Override
	public void updataUserTell(String openId, String usertell) {
		// TODO Auto-generated method stub
		Userbo user = new Userbo();
		user.setOpenId(openId);
		user.setTell(usertell);
		userDao.updateUser(user);
	}

	@Override
	public void updataUserName(String openId, String username) {
		// TODO Auto-generated method stub
		Userbo user = new Userbo();
		user.setOpenId(openId);
		user.setName(username);
		userDao.updateUser(user);
	}

	@Override
	public void updataUserGender(Integer userid, String usergender) {
		// TODO Auto-generated method stub
		Userbo user = new Userbo();
		user.setId(userid);
		user.setGender(usergender);
		userDao.updateUser(user);
	}

	@Override
	public List<Userbo> selectUserByOpenId(String openId) {
		Userbo userbo=new Userbo();
		userbo.setOpenId(openId);
		List<Userbo> userbos=userDao.selectUser(userbo);
		
		return userbos;
	}

	@Override
	@Transactional
	public String callService(Repairbo repair,Servicemanbo servicemanbo) {
		TemplateUtil templateUtil=new TemplateUtil(CommonUtil.appid, CommonUtil.appsecret);
		WechatTemplate wt=templateUtil.setremindValue(servicemanbo.getOpenId(),repair);
		try {
			templateUtil.sendTemplateMessage(wt);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		if(repair.getUser()!=null){
			WechatTemplate temp=templateUtil.setAcceptValue(repair.getFacilityName(), servicemanbo.getOpenId(), repair.getUser().getOpenId(),servicemanbo.getTell());
			try {
				templateUtil.sendTemplateMessage(temp);
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}
		return "success";
	}
	
	@Override
	@Transactional
	public String callAdmin(Integer id) {
		Repairbo repair=repairDao.selectRepairById(id);
		List<Adminbo> adminbos= adminDao.selectByCampusboId(repair.getCampus().getId());
		TemplateUtil templateUtil=new TemplateUtil(CommonUtil.appid, CommonUtil.appsecret);
		for(Adminbo adminbo: adminbos){
			WechatTemplate wt=templateUtil.setremindValue(adminbo.getOpenId(),repair);
			try {
				templateUtil.sendTemplateMessage(wt);
			} catch (Exception e) {
				
				e.printStackTrace();
				return "fail";
			}
		}
		return "success";
	}
	
	@Override
	public String bandUser(String openId, Userbo user,String imageUrl) {
		String msg = "";
		System.out.println(openId+"==="+imageUrl);
		try {
			user.setOpenId(openId);
			user.setHeadimgurl(imageUrl);
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime=sdf.format(date);
			user.setCreateTime(createTime);
			System.out.println(user.toString());
			userDao.insertUser(user);
			msg = "绑定成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "服务器忙，稍后再试";
		}
		return msg;
	}





}
