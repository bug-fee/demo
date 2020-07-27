package com.repairs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repairs.bo.Repairbo;
import com.repairs.service.ServicemanService;
import com.repairs.utils.CommonsResult;
@Controller
@RequestMapping("/service")
public class ServicemanController {
	@Autowired
	private ServicemanService servicemanService;
	
	/**
	 * 添加维修人员
	 * **/
	@RequestMapping(value="/addServiceMan.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String addServiceMan(Integer campusId,HttpServletRequest request){
		String openId=(String) request.getSession().getAttribute("openId");
		String success=servicemanService.addServiceMan(campusId,openId);
		return success;
	}

	/**
	 * 查询出本辖区内最新的5条维修记录
	 * **/
	@RequestMapping(value="/serviceSelectNews.do",produces="text/html;charset=utf-8")
	@ResponseBody
	public String serviceSelectNews(HttpServletRequest request){
		String openId=(String) request.getSession().getAttribute("openId");
		if(openId==null){
			return null;
		}
		String success=servicemanService.serviceSelectNews(openId);
		return success;
	}
	@RequestMapping(value="/searchRepairs.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String searchRepairs(Repairbo repair,HttpSession session){
		CommonsResult result=servicemanService.searchRepairs(repair,session);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/updateRepairs.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String updateRepairs(Repairbo queryReqpir,HttpSession session){
		CommonsResult result=servicemanService.updateRepairs(queryReqpir,session);
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * @author xiaofan
	 * @param request
	 * @return json
	 */
	@RequestMapping(value="selserviceusermsg.do",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String selServiceUserMsg(HttpServletRequest request){
		String openId=(String) request.getSession().getAttribute("openId");
		if(openId==null||openId==""){
			return "[{\"type\":\"error\"}]";
		}
		String userimgUrl = (String) request.getSession().getAttribute("imgurl");
		String result = servicemanService.searchServiceMan(openId,userimgUrl);
		return result;
	}
	/**
	 * 修改性别
	 * @param usergender
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="changeservciemangender.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String changeServciemanGender(String usergender,Integer userid,HttpSession session){
		String result="";
		if (session.getAttribute("openId")==null||session.getAttribute("openId")=="") {
			result = "服务器忙，请退出后重试";
		}else{
			result  = servicemanService.updateServiceGender(usergender,userid);
		}
		return result;
	}
	/**
	 * 修改姓名
	 * @param usernamemsg
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="changeservicemanname.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String changeServciemanName(String usernamemsg,Integer userid,HttpSession session){
		String result="";
		if (session.getAttribute("openId")==null||session.getAttribute("openId")=="") {
			result = "服务器忙，请退出后重试";
		}else{
			result  = servicemanService.updateServicemanName(usernamemsg,userid);
		}
		return result;
	}
	/**
	 * 修改电话
	 * @param adminphone
	 * @param servicemanid
	 * @return
	 */
	@RequestMapping(value="changeservicemanphone.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String changeServciemanTell(String adminphone,Integer servicemanid,HttpSession session){
		String result="";
		if (session.getAttribute("openId")==null||session.getAttribute("openId")=="") {
			result = "服务器忙，请退出后重试";
		}else{
			result  = servicemanService.updateServicemanTell(adminphone,servicemanid);
		}
		return result;
	}
	/**
	 * 修改校区
	 * @param campusname
	 * @param servicemanid
	 * @return
	 */
	@RequestMapping(value="changesermanCampus.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String changeServciemanCampus(String campusname,Integer servicemanid,HttpSession session){
		String result="";
		if (session.getAttribute("openId")==null||session.getAttribute("openId")=="") {
			result = "服务器忙，请退出后重试";
		}else{
			result  = servicemanService.updateServicemanCampus(campusname,servicemanid);
		}
		return result;
	}
	/**
	 * 修改状态
	 * @param servicemanid
	 * @param state
	 * @return
	 */
	@RequestMapping(value="/changesermanstate.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String changeServicemanState(Integer servicemanid,Integer state,String authorizedTime,HttpSession session){
		String openId=(String) session.getAttribute("openId");
		String result ="";
		if (openId==null) {
			result="系统繁忙,请退出后重试";
		}else{
			
			result = servicemanService.updateServicemanState(servicemanid ,state,authorizedTime);
		}
		return result;
	}
	/**
	 * 用户的管辖区域的认证
	 * **/
		@RequestMapping(value="/changesermancampus.do",produces="text/html;charset=utf-8")
		@ResponseBody
		public String changesermancampus(Integer id,Integer state,HttpSession session){
			String openId=(String) session.getAttribute("openId");
			String success="";
			if (openId==null) {
				success="系统繁忙,请退出后重试";
			}else{
				
				success=servicemanService.changesermanstate(id,state);
			}
			return success;
		}
}




