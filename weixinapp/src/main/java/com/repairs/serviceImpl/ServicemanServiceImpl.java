package com.repairs.serviceImpl;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.repairs.service.ServicemanService;
import com.repairs.utils.CommonUtil;
import com.repairs.utils.CommonsResult;
import com.repairs.utils.StringUtil;
import com.repairs.utils.TemplateUtil;

@Service
public class ServicemanServiceImpl implements ServicemanService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private ServicemanDao servicemanDao;
	@Autowired
	private CampusDao campusDao;
	@Autowired
	private AdminDao adminDao;

	@Autowired
	private RepairDao repairDao;
	
	@Override
	public String addServiceMan(Integer campusId, String openId) {
		Userbo userbo=new Userbo();
		userbo.setOpenId(openId);
		//查询出该用户
		List<Userbo> list=userDao.selectUser(userbo);
		
			
			userbo=list.get(0);
			//将该用户添加到维修人员表中
			Servicemanbo servicemanbo=new Servicemanbo();
			
			servicemanbo.setOpenId(openId);
			List<Servicemanbo> servicemanbos=servicemanDao.selectServiceman(servicemanbo);
			//如果维修人员表中已有该人员,则修改状态,没有则添加
			if (servicemanbos!=null&&servicemanbos.size()>0&&servicemanbos.get(0)!=null) {
				servicemanbo.setState(0);
				Campusbo campus=new Campusbo();
				campus.setId(campusId);
				servicemanbo.setNewcam(campus);
				int i=servicemanDao.updateServiceman(servicemanbo);
				if (i==1) {
					return "success";
				}else{
					
					return "fail"; 
				}
				
			}else{
				servicemanbo.setName(userbo.getName());
				servicemanbo.setState(0);
				servicemanbo.setTell(userbo.getTell());
				//设置所负责区域
				Campusbo campusbo=new Campusbo();
				campusbo.setId(campusId);
				servicemanbo.setCampus(campusbo);
				int i=servicemanDao.insertServiceman(servicemanbo);
				if (i==1) {
					return "success";
				}else{
					return "fail"; 
				}
			}
		
	}
	
	@Override
	public String serviceSelectNews(String openId) {
		Servicemanbo servicemanbo=new Servicemanbo();
		servicemanbo.setOpenId(openId);
		List<Servicemanbo> servicemanbos=servicemanDao.selectServiceman(servicemanbo);
		if (servicemanbos.get(0)!=null) {
			servicemanbo=servicemanbos.get(0);
			Campusbo campusbo=servicemanbo.getCampus();
			Repairbo repairbo=new Repairbo();
			repairbo.setCampus(campusbo);
			repairbo.setState(0);
			repairbo.setOffset(0);
			repairbo.setLimit(5);
			repairbo.setOrder("desc");
			//根据校区查询出5条报修记录
			List<Repairbo> repairbos=repairDao.selectRepair(repairbo);
			String json=JSONArray.fromObject(repairbos).toString();
			return json;
		}else{
			
			return null;
		}
		
	}
	
	@Override
	public CommonsResult searchRepairs(Repairbo repair,HttpSession session) {
		String openId=(String)session.getAttribute("openId");
		if(openId==null){
			return new CommonsResult(false);
		}
		if(repair.getState()!=null&&repair.getState()!=0){
			Servicemanbo serviceman=new Servicemanbo();
			serviceman.setOpenId(openId);
			serviceman=servicemanDao.selectServiceman(serviceman).get(0);
			repair.setServiceman(serviceman);
			if(repair.getState()==3){
				repair.setState(null);
			}
		}
		if(repair.getState()!=null&&repair.getState()==0){
			repair.setState(0);
			Servicemanbo serviceman=new Servicemanbo();
			serviceman.setOpenId(openId);
			List<Servicemanbo> servicemans=servicemanDao.selectServiceman(serviceman);
			if(servicemans!=null&&servicemans.size()>0){
				Campusbo campus=new Campusbo();
				repair.setCampus(servicemans.get(0).getCampus());
			}
		}
		if("".equals(repair.getFacilityName())){
			repair.setFacilityName(null);
		}
		if("".equals(repair.getFaultTitle())){
			repair.setFaultTitle(null);
		}
		if("".equals(repair.getFacilityModel())){
			repair.setFacilityModel(null);
		}
		if("".equals(repair.getSeriano())){
			repair.setSeriano(null);
		}
		List<Repairbo> repairs=repairDao.selectRepair(repair);
		if(repairs==null||repairs.size()<1){
			return new CommonsResult(false);
		}
		return new CommonsResult(true,repairs);
	}
	
	/**
	 * @author xiaofan
	 */
	public String searchServiceMan(String openId,String imgurl) {
		String msg = "";
		if(openId==null||openId.equals("")){
			msg = "[{\"type\":\"error\"}]";
		}else{
			Servicemanbo serviceman = new Servicemanbo();
			serviceman.setOpenId(openId);
			try {
				List<Servicemanbo> servicemanlist = servicemanDao.selectServiceman(serviceman);
				if(servicemanlist.size()!=0){
					JSONArray servicemanjson = JSONArray.fromObject(servicemanlist);
					JSONObject obj = servicemanjson.getJSONObject(0);
					obj.put("type", "success");
					obj.put("servicemanimg", imgurl);
					msg = servicemanjson.toString();
				}else{
					msg = "[{\"type\":\"error\"}]";
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				msg = "[{\"type\":\"error\"}]";
			}
			
		}
		return msg;
	}
	@Override
	public String updateServiceGender(String usergender, Integer userid) {
		String msg = "";
		if(userid==null||userid.equals("")){
			msg="系统异常，稍后再试";
		}else{
			Servicemanbo serviceman = new Servicemanbo();
			serviceman.setId(userid);
			serviceman.setGender(usergender);
			try {
				servicemanDao.updateServiceman(serviceman);
				msg="修改成功";
			} catch (Exception e) {
				e.printStackTrace();
				msg="系统异常，稍后再试";
			}
		}
		return msg;
	}
	@Override
	public String updateServicemanName(String usernamemsg, Integer userid) {
		String msg = "";
		if(userid==null||userid.equals("")){
			msg="系统异常，稍后再试";
		}else{
			Servicemanbo serviceman = new Servicemanbo();
			serviceman.setId(userid);
			serviceman.setName(usernamemsg);
			try {
				servicemanDao.updateServiceman(serviceman);
				msg="修改成功";
			} catch (Exception e) {
				e.printStackTrace();
				msg="系统异常，稍后再试";
			}
		}
		return msg;
	}
	@Override
	public String updateServicemanTell(String adminphone, Integer servicemanid) {
		String msg = "";
		if(servicemanid==null||servicemanid.equals("")){
			msg="系统异常，稍后再试";
		}else{
			Servicemanbo serviceman = new Servicemanbo();
			serviceman.setId(servicemanid);
			serviceman.setTell(adminphone);
			try {
				servicemanDao.updateServiceman(serviceman);
				msg="修改成功";
			} catch (Exception e) {
				e.printStackTrace();
				msg="系统异常，稍后再试";
			}
		}
		return msg;
	}
	@Override
	public String updateServicemanCampus(String campusname, Integer servicemanid) {
		String msg = "";
		if(servicemanid==null||servicemanid.equals("")){
			msg="系统异常，稍后再试";
		}else{
			try {
				Campusbo campus = new Campusbo();
				campus.setName(campusname);
				Servicemanbo serviceman = new Servicemanbo();
				serviceman.setId(servicemanid);
				serviceman.setState(3);
				serviceman.setNewcam(campusDao.selectCampus(campus).get(0));
					servicemanDao.updateServiceman(serviceman);
					msg="申请成功";
				} catch (Exception e) {
					e.printStackTrace();
					msg="系统异常，稍后再试";
				}
		}
		return msg;
	}

	@Override
	public CommonsResult updateRepairs(Repairbo queryReqpir, HttpSession session){
		String openId=(String)session.getAttribute("openId");
		if(openId==null){
			return new CommonsResult(false);
		}
		System.out.println(openId);
		TemplateUtil util=new TemplateUtil(CommonUtil.appid,CommonUtil.appsecret);
		Servicemanbo serviceman=new Servicemanbo();
		serviceman.setOpenId(openId);
		serviceman=servicemanDao.selectServiceman(serviceman).get(0);
		if("".equals(queryReqpir.getCharge())){
			queryReqpir.setCharge(null);
		}
		if("".equals(queryReqpir.getServiceLookup())){
			queryReqpir.setServiceLookup(null);
		}
		if("".equals(queryReqpir.getDefeatedreason())){
			queryReqpir.setDefeatedreason(null);
		}
		if(queryReqpir.getState()==1){//受理
			queryReqpir.setServiceman(serviceman);
			queryReqpir.setLookup(0);
			queryReqpir.setReceiptTime(StringUtil.currentTime());
			Repairbo re=new Repairbo();
			re.setId(queryReqpir.getId());
			re=repairDao.selectRepair(re).get(0);
			//发送消息推送给发布者
			if(re.getUser()!=null){
				WechatTemplate temp=util.setAcceptValue(re.getFacilityName(), serviceman.getOpenId(), re.getUser().getOpenId(),serviceman.getTell());
				try {
					util.sendTemplateMessage(temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if(queryReqpir.getState()==2){//完成
			queryReqpir.setEndTime(StringUtil.currentTime());
			queryReqpir.setAdminLookup(0);
			Iterator<Adminbo> it=adminDao.selectAdmin(new Adminbo()).iterator();
			while(it.hasNext()){
				Adminbo admin=it.next();
				//发送消息推送给管理员
				if(admin.getOpenId()!=null&&!"".equals(admin.getOpenId())){
					WechatTemplate temp=util.setAchieveValue(admin.getOpenId(),serviceman.getName(),serviceman.getTell());
					try {
						util.sendTemplateMessage(temp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}else if(queryReqpir.getState()==4){//未解决
			queryReqpir.setEndTime(StringUtil.currentTime());
			queryReqpir.setAdminLookup(0);
			//未解决消息发送给管理员
			Iterator<Adminbo> it=adminDao.selectAdmin(new Adminbo()).iterator();
			while(it.hasNext()){
				Adminbo admin=it.next();
				Repairbo repair=new Repairbo();
				repair.setId(queryReqpir.getId());
				repair=repairDao.selectRepair(repair).get(0);
				WechatTemplate temp=util.unRepairMessage(admin.getOpenId(), repair);
				try {
					util.sendTemplateMessage(temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		repairDao.updateRepair(queryReqpir);
		return new CommonsResult(true);
	}

	@Override
	public String updateServicemanState(Integer servicemanid,Integer state,String authorizedTime) {
		String msg = "";
		if(servicemanid==null||servicemanid.equals("")){
			msg="系统异常，稍后再试";
		}else{
			Servicemanbo serviceman = new Servicemanbo();
			serviceman.setId(servicemanid);
			List<Servicemanbo> servicemanbos=servicemanDao.selectServiceman(serviceman);
			Servicemanbo servicemanbo=servicemanbos.get(0);
			if(state==1){
				serviceman.setState(1);
				serviceman.setAuthorizedTime(authorizedTime);
				try {
					msg="修改成功";
					TemplateUtil templateUtil=new TemplateUtil(CommonUtil.appid, CommonUtil.appsecret);
					servicemanDao.updateServiceman(serviceman);
					WechatTemplate wt=templateUtil.setVerify(servicemanbo.getOpenId(), "欢迎加入维修者行列");
					templateUtil.sendTemplateMessage(wt);
				} catch (Exception e) {
					e.printStackTrace();
					msg="系统异常，稍后再试";
				}
				
			}else if(state==2){
				serviceman.setState(2);
				try {
					servicemanDao.deleteServiceman(serviceman);
					msg="取消成功";
					TemplateUtil templateUtil=new TemplateUtil(CommonUtil.appid, CommonUtil.appsecret);
					servicemanDao.updateServiceman(serviceman);
					WechatTemplate wt=templateUtil.setVerify(servicemanbo.getOpenId(), "不好意思,管理员拒绝了您的申请");
					templateUtil.sendTemplateMessage(wt);
				} catch (Exception e) {
					msg="系统异常，稍后再试";
				}
			}
		}
		
		return msg;
	}

	@Override
	public String changesermanstate(Integer id, Integer state) {
		String msg="";
		if(id==null||id.equals("")){
			msg="系统异常，稍后再试";
		}else{
			Servicemanbo servicemanbo=new Servicemanbo();
			servicemanbo.setId(id);
			List<Servicemanbo> servicemanbos=servicemanDao.selectServiceman(servicemanbo);
			servicemanbo.setState(1);
			
			if (state==1) {
				servicemanbo.setCampus(servicemanbos.get(0).getNewcam());
				int i=servicemanDao.updatesermanstate(servicemanbo);
				if (i==1) {
					msg= "修改成功";
					servicemanbo=servicemanbos.get(0);
					TemplateUtil templateUtil=new TemplateUtil(CommonUtil.appid, CommonUtil.appsecret);
					
					WechatTemplate wt=templateUtil.setVerify(servicemanbo.getOpenId(), "管理员同意了您修改校区的申请");
					try {
						templateUtil.sendTemplateMessage(wt);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					
					msg="系统异常，稍后再试";
				}
				
				
			}else{
				servicemanbo.setCampus(servicemanbos.get(0).getCampus());
				int i=servicemanDao.updatesermanstate(servicemanbo);
				if (i==1) {
					msg= "修改成功";
					servicemanbo=servicemanbos.get(0);
					TemplateUtil templateUtil=new TemplateUtil(CommonUtil.appid, CommonUtil.appsecret);
					
					WechatTemplate wt=templateUtil.setVerify(servicemanbo.getOpenId(), "管理员拒绝了您修改校区的申请");
					try {
						templateUtil.sendTemplateMessage(wt);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					
					msg="系统异常，稍后再试";
				}
			}
		}
		return msg;
	}

	@Override
	public List<Servicemanbo> selectAllServiceman(Servicemanbo servicemanbo) {
		return servicemanDao.selectServiceman(servicemanbo);
	}

	@Override
	public List<Servicemanbo> selectServicemanByCampusId(Integer id) {
		// TODO Auto-generated method stub
		return servicemanDao.selectServicemanByCampusId(id);
	}

	@Override
	public Servicemanbo selectServicemanById(Integer id) {
		// TODO Auto-generated method stub
		return servicemanDao.selectServicemanById(id);
	}


	
}
