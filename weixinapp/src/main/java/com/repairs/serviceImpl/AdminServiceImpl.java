package com.repairs.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairs.bo.Adminbo;
import com.repairs.bo.Repairbo;
import com.repairs.bo.Servicemanbo;
import com.repairs.bo.Userbo;
import com.repairs.dao.AdminDao;
import com.repairs.dao.RepairDao;
import com.repairs.dao.ServicemanDao;
import com.repairs.dao.UserDao;
import com.repairs.service.AdminService;
import com.repairs.utils.CommonsResult;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private RepairDao repairDao;
	@Autowired
	private ServicemanDao servicemanDao;
	@Autowired
	private UserDao userdao;
	
	@Override
	public String selAdminMsg(String openId, String userimgUrl) {
		Adminbo admin = new Adminbo();
		admin.setOpenId(openId);
		String adminmsg="";
		List<Adminbo> adminlist = adminDao.selectAdmin(admin);
		if(adminlist.size()!=0){
			JSONArray adminjson = JSONArray.fromObject(adminlist);
			JSONObject obj = adminjson.getJSONObject(0);
			obj.put("adminimg", userimgUrl);
			adminmsg = adminjson.toString();
		}
		return adminmsg;
	}
	@Override
	public String updateAdminName(Integer adminid, String newadminname) {
		String msg = "";
		Adminbo admin = new Adminbo();
		admin.setId(adminid);
		admin.setName(newadminname);
		try {
			adminDao.updateAdmin(admin);
			msg="修改成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg="服务器繁忙，稍后再试";
		}
		adminDao.updateAdmin(admin);
		return msg;
	}
	@Override
	public String updateAdminGender(Integer adminid, String admingender) {
		String msg = "";
		Adminbo admin = new Adminbo();
		admin.setId(adminid);
		admin.setGender(admingender);
		try {
			adminDao.updateAdmin(admin);
			msg="修改成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg="服务器繁忙，稍后再试";
		}
		adminDao.updateAdmin(admin);
		return msg;
	}
	@Override
	public String updateAdminPhone(Integer adminid, String adminphone) {
		String msg = "";
		Adminbo admin = new Adminbo();
		admin.setId(adminid);
		admin.setTell(adminphone);
		try {
			adminDao.updateAdmin(admin);
			msg="修改成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg="服务器繁忙，稍后再试";
		}
		adminDao.updateAdmin(admin);
		return msg;
	}
	@Override
	public String updateAdminAccount(Integer adminid, String adminaccount,
			String adminaccountpsd) {
		String msg = "";
		Adminbo admin = new Adminbo();
		admin.setId(adminid);
		admin.setUserName(adminaccount);
		admin.setPassword(adminaccountpsd);
		try {
			adminDao.updateAdmin(admin);
			msg="修改成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg="服务器繁忙，稍后再试";
		}
		adminDao.updateAdmin(admin);
		return msg;
	}
	
	@Override
	public String selectNews() {
		Repairbo repairbo=new Repairbo();
		repairbo.setOffset(0);
		repairbo.setLimit(5);
		repairbo.setOrder("desc");
		List<Repairbo> repairbos=repairDao.selectRepair(repairbo);
		String json=JSONArray.fromObject(repairbos).toString();
		return json;
	}
	
	@Override
	public CommonsResult userList(String name, Integer type, Integer offset,Integer limit) {
		Servicemanbo serviceman=new Servicemanbo();
		if("".equals(name)){
			name=null;
			serviceman.setName(name);
			serviceman.setState(type);
			serviceman.setLimit(limit);
			serviceman.setOffset(offset);
			List<Servicemanbo> servicemans=servicemanDao.selectServiceman(serviceman);
			serviceman.setState(3);
			List<Servicemanbo> applystate=servicemanDao.selectServiceman(serviceman);
			servicemans.addAll(applystate);
			if(servicemans!=null&&servicemans.size()>0){
				return new CommonsResult(true,servicemans);
			}else {
				return new CommonsResult(false,"系统繁忙请稍后重试");
			}
		}else {
			serviceman.setName(name);
			serviceman.setState(type);
			serviceman.setLimit(limit);
			serviceman.setOffset(offset);
			List<Servicemanbo> servicemans=servicemanDao.selectServiceman(serviceman);
			if(servicemans!=null&&servicemans.size()>0){
				return new CommonsResult(true,servicemans);
			}else {
				return new CommonsResult(false,"系统繁忙请稍后重试");
			}
		}
	}
	@Override
	public CommonsResult selectAllrepair(String facilityName,String seriano, String faultTitle, Integer offset,Integer limit, Integer state,String order) {
		/*入参检查*/
		//String facilityName,String facilityModel, String faultTitle, Integer offset,Integer limit, Integer state
		Repairbo repair=new Repairbo();
		if(state!=null&&state==3){
			state=null;
		}
		if(facilityName!=null&&!"".equals(facilityName.trim())){
			facilityName=facilityName.trim();
		}else{
			facilityName=null;
		}
		if(seriano!=null&&!"".equals(seriano.trim())){
			seriano=seriano.trim();
		}else{
			seriano=null;
		}
		if(faultTitle!=null&&!"".equals(faultTitle.trim())){
			faultTitle=faultTitle.trim();
		}else{
			faultTitle=null;
		}
		if(limit==null){
			limit=5;
		}
		if(offset==null){
			offset=0;
		}
		if(order==null || "".equals(order)){
			order="desc";
		}
		repair.setLimit(limit);
		repair.setState(state);
		repair.setOffset(offset);
		repair.setSeriano(seriano);
		repair.setFacilityName(facilityName);
		repair.setFaultTitle(faultTitle);
		repair.setOrder(order);
		List<Repairbo> repairs=repairDao.selectRepair(repair);
		return new CommonsResult(true,repairs);
	}
	@Override
	public String initAdminApprove() {
		List<Servicemanbo> servicemanbos=servicemanDao.selectServicemanByState(); 
		String json=JSONArray.fromObject(servicemanbos).toString();
		return json;
	}
	@Override
	public CommonsResult selectAdminOne(Adminbo admin) {
		// TODO Auto-generated method stub
		Adminbo adminbo =adminDao.selectAdminOne(admin);
		if(null!=adminbo){
			return new CommonsResult(true, adminbo);
		}else{
			return new CommonsResult(false);
		}
		
	}
	@Override
	public CommonsResult insertAdmin(Adminbo admin) {
		Adminbo adminbo=new Adminbo();
		adminbo.setUserName(admin.getUserName());
		List<Adminbo> adminbos=adminDao.selectAdmin(adminbo);
		CommonsResult result=null;
		if (adminbos.size()==0) {
			try {
				Integer i=adminDao.insertAdmin(admin);
				if (i==1) {
					result=new CommonsResult(true,"添加成功");
				}else{
					result=new CommonsResult(false,"添加失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				result=new CommonsResult(false,"添加失败");
			}
			
		}else{
			result=new CommonsResult(false, "用户名已存在");
		}
		return result;
	}
	@Override
	public CommonsResult deleteAdmin(Integer id) {
		CommonsResult result=null;
		if (id!=null) {
			try {
				int i=adminDao.delteAdminById(id);
				if (i==1) {
					
					result=new CommonsResult(true, "删除成功");
				}else{
					result=new CommonsResult(false, "删除失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				result=new CommonsResult(false, "删除失败");
			}
			
		}else{
			result=new CommonsResult(false,"删除失败");
		}
		return result;
	}
	@Override
	public CommonsResult selectAdminPage(Adminbo admin) {
		// TODO Auto-generated method stub
		List<Adminbo> admins  = adminDao.selectAdminPage(admin);
		int size =adminDao.selectAdminAll().size();
		if(size>0){
			int total =size%admin.getLimit()==0?size/admin.getLimit():size/admin.getLimit()+1;;
			return new CommonsResult(true, "", admins, total);
		}else{
			return new CommonsResult(false,"无数据");
		}
	}
	@Override
	public CommonsResult selectAdmin(Adminbo admin) {
		// TODO Auto-generated method stub
		Adminbo adminbo = adminDao.selectAdminByUserId(admin);
		return new CommonsResult(true,adminbo);
	}
	@Override
	public CommonsResult selectByCampusboId(Integer campusboId) {
		// TODO Auto-generated method stub
		List<Adminbo> adminbos = adminDao.selectByCampusboId(campusboId);
		if(adminbos.size()>0){
			return new CommonsResult(true, "获取成功");
		}
		return new CommonsResult(false, "无数据");
	}
	@Override
	public Adminbo selectByOpenId(String openId) {
		// TODO Auto-generated method stub
		Adminbo adminbo = adminDao.selectAdminByOpenId(openId);
		return adminbo;
	}

}
