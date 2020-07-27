package com.repairs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repairs.bo.Userbo;
import com.repairs.dao.UserDao;
import com.repairs.service.RepairService;
import com.repairs.service.UserService;

@Controller

public class UserController {

	@Autowired
	private RepairService repairService;
	@Autowired
	private  UserService userService;
	@Autowired
	private UserDao userDao;
	/**
	 * 查询出用户最新的5条报修记录
	 * **/
	@RequestMapping(value="selectNews.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String selectNews(HttpServletRequest request) {
		String openId=(String) request.getSession().getAttribute("openId");
		if(openId==null){
			return null;
		}
		String success=repairService.selectNews(openId);
		return success;
	}
	/**
	 * @author xiaofan
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value="bandUser.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String bandUser(HttpServletRequest request,Userbo user){
		String openId=(String) request.getSession().getAttribute("openId");
		if(openId==null){
			return "服务器忙，稍后再试";
		}
		String imageurl = (String) request.getSession().getAttribute("imgurl");
		String success = userService.bandUser(openId,user,imageurl);
		return success;
	}
	
	
	@RequestMapping(value="/selusermsg.do",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String selUserByOpenid(HttpServletRequest req){
		String openId = (String) req.getSession().getAttribute("openId");
		String userimgUrl = (String) req.getSession().getAttribute("imgurl");
		if (openId==null) {
			openId="";
		}
		String userjson = userService.selUserByOpenId(openId,userimgUrl);
		return userjson;
	}
	
	@RequestMapping(value="changeCampus.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String changeCampus(String campusname,HttpSession session){
		String openId=(String)session.getAttribute("openId");
		if(openId==null){
			return "服务器忙，稍后再试";
		}
		Userbo user=new Userbo();
		user.setOpenId(openId);
		user=userDao.selectUser(user).get(0);
		String msg = "";
		try {
			userService.updataUserCampus(user.getId(), campusname);
			msg="修改成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg="服务器忙，稍后再试";
		}
		
		return msg;
	}
	
	@RequestMapping(value="/changetell.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String changeTell(String usertell,Integer userid,HttpSession session){
		String openId=(String)session.getAttribute("openId");
		if(openId==null){
			return "服务器忙，稍后再试";
		}
		String msg = "";
		try {
			userService.updataUserTell(openId, usertell);
			msg="修改成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg="服务器忙，稍后再试";
		}
		
		return msg;
	}
	
	@RequestMapping(value="/changeusername.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String changeUserName(String usernamemsg,HttpSession session){
		String msg = "";
		String openId=(String)session.getAttribute("openId");
		if(openId==null){
			return "服务器忙，稍后再试";
		}
		try {
			userService.updataUserName(openId, usernamemsg);
			msg="修改成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg="服务器忙，稍后再试";
		}
		
		return msg;
	}
	
	@RequestMapping(value="/changegender.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String changeUserGender(String usergender,Integer userid,HttpSession session){
		String msg = "";
		String openId=(String)session.getAttribute("openId");
		if (openId==null||openId.equals("")) {
			msg="服务器忙,稍后再试";
		}else{
			try {
				userService.updataUserGender(userid, usergender);
				msg="修改成功";
			} catch (Exception e) {
				e.printStackTrace();
				msg="服务器忙，稍后再试";
			}
		}
		
		
		return msg;
	}

	
	

	
}



