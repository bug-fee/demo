package com.repairs.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repairs.bo.Adminbo;
import com.repairs.bo.Servicemanbo;
import com.repairs.service.AdminService;
import com.repairs.service.ServicemanService;
import com.repairs.service.UserlogService;
import com.repairs.utils.CommonsResult;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserlogService userlogService;
	@Autowired
	private ServicemanService servicemanService;
	
	@RequestMapping(value="/seladminmsg.do",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String selAdminMsg(HttpServletRequest req){
		String openId = (String) req.getSession().getAttribute("openId");
		String userimgUrl = (String) req.getSession().getAttribute("imgurl");
		//String openId = "o8etwv2nyFtdtMHcXx1LY2DwNIEE";
		//String userimgUrl = "../../icons/memtx.png";
		if (openId==null) {
			return null;
		}
		String result = adminService.selAdminMsg(openId,userimgUrl);
		//System.out.println(result);
		return result;
	}
	@RequestMapping(value="updateadminname.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String updateAdminname(Integer adminid,String newadminname,HttpSession session){
		String openId=(String)session.getAttribute("openId");
		if (openId==null||openId.equals("")) {
			return "服务器忙，请退出后重试";
		}else{
			String result = adminService.updateAdminName(adminid,newadminname);
			return result;
		}
	}
	@RequestMapping(value="changeadmingender.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String updateAdmingender(String admingender,Integer adminid,HttpSession session){
		String openId=(String)session.getAttribute("openId");
		if (openId==null||openId.equals("")) {
			return "服务器忙，请退出后重试";
		}else{
			String result = adminService.updateAdminGender(adminid,admingender);
			return result;
		}
	}
	@RequestMapping(value="changeadminphone.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String updateAdminPhone(String adminphone,Integer adminid,HttpSession session){
		//System.out.println(adminphone+"=="+adminid);
		String openId=(String)session.getAttribute("openId");
		if (openId==null||openId.equals("")) {
			return "服务器忙，请退出后重试";
		}else{
			String result = adminService.updateAdminPhone(adminid,adminphone);
			return result;
		}
		
	}
	@RequestMapping(value="changeadminaccount.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String updateAdminAccount(String adminaccount,String adminaccountpsd,Integer adminid,HttpSession session){
		//System.out.println(adminaccountpsd+"=="+adminid+"=="+adminaccount);
		String openId=(String)session.getAttribute("openId");
		if (openId==null||openId.equals("")) {
			return "服务器忙，请退出后重试";
		}else{
			String result = adminService.updateAdminAccount(adminid,adminaccount,adminaccountpsd);
			return result;
		}
		
	}
	/**
	 * 管理员查询出五条信息
	 * **/
	@RequestMapping(value="/admin_selectNews.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String admin_selectNews(HttpServletRequest request){
		String success=adminService.selectNews();
		return success;
	}
	@RequestMapping(value="/userList.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String userList(String name,Integer type,Integer offset,Integer limit){
		CommonsResult result=adminService.userList(name,type,offset,limit);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/selectAllrepair.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String selectAllrepair(String facilityName,String seriano,String faultTitle,Integer offset,Integer limit,Integer state,String order){
		CommonsResult result=adminService.selectAllrepair(facilityName,seriano,faultTitle,offset,limit,state,order);
		return JSONObject.fromObject(result).toString();
		
	}
	/**
	 * 
	 * 初始化管理员认证页面
	 * **/
	@RequestMapping(value="initAdminApprove.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String initAdminApprove(HttpSession session){
		String openId=(String)session.getAttribute("openId");
		if(openId==null){
			return null;
		}
		String success=adminService.initAdminApprove();
		return success;
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String Login(Adminbo admin,HttpSession session){
		CommonsResult commonsResult = adminService.selectAdminOne(admin);
		if(commonsResult.isSuccess()){
			session.setAttribute("user",commonsResult.getData());
			userlogService.insertUserlog(((Adminbo)commonsResult.getData()).getName(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString(), 1);
		}
		return JSONObject.fromObject(commonsResult).toString();
	}
	
	@RequestMapping(value="/getSession.do",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String  getSessionUser(HttpSession session){
		if(null!=(Adminbo)session.getAttribute("user")){
			Adminbo adminbo=(Adminbo)session.getAttribute("user");
			return adminbo.getName();
		}else{
			return "false";
		}
		
	}
	@RequestMapping(value="/outLogin.do",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String outLogin(HttpSession session){
		if(null!=session.getAttribute("user")){
			Adminbo adminbo=(Adminbo)session.getAttribute("user");
			userlogService.insertUserlog(adminbo.getName(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString(),2);
			session.removeAttribute("user");
			return "success";
		}else{
			return "success";
		}
	}

	@RequestMapping(value="/adminPage.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String selectadminPage(Adminbo admin){
		return JSONObject.fromObject(adminService.selectAdminPage(admin)).toString();
	}
	
	@RequestMapping(value="/selectAdminOne.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String selectAdminOne(Adminbo admin){
		
		return JSONObject.fromObject(adminService.selectAdmin(admin)).toString();
	}
	
	@RequestMapping(value="/insertAdminOne.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String insertAdminOne(Adminbo admin){
		return JSONObject.fromObject(adminService.insertAdmin(admin)).toString();
	}
	
	@RequestMapping(value="/deleteAdminOne.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String deleteAdminOne(Integer id){
		return JSONObject.fromObject(adminService.deleteAdmin(id)).toString();
	}
	
	@RequestMapping(value="/logPage.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String deleteAdminOne(Integer offset,Integer limit){
		return JSONObject.fromObject(userlogService.selectUserlogByPage(offset, limit)).toString();
	}
	
	@RequestMapping(value="/deleteLog.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String deleteLogOne(Integer id){
		return JSONObject.fromObject(userlogService.delectUserlog(id)).toString();
	}
	
	@RequestMapping(value="/getAllServiceman.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String getAllServiceman(Servicemanbo servicemanbo){
		return JSONArray.fromObject(servicemanService.selectAllServiceman(servicemanbo)).toString();
	}
	
}
