package com.repairs.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repairs.bo.Adminbo;
import com.repairs.bo.Repairbo;
import com.repairs.bo.Servicemanbo;
import com.repairs.bo.WechatTemplate;
import com.repairs.service.AdminService;
import com.repairs.service.RepairService;
import com.repairs.service.ServicemanService;
import com.repairs.service.UserService;
import com.repairs.utils.CommonUtil;
import com.repairs.utils.CommonsResult;
import com.repairs.utils.TemplateUtil;

@Controller
public class RepairController {
	@Autowired
	private RepairService repairService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ServicemanService servicemanService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 根据repair的id查询出repair的详细信息
	 * @param repair
	 *
	 * **/
	@RequestMapping(value="selectNewsDetails.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String selectNewsDetails(Repairbo repair){
		String success=repairService.selectNewsDetails(repair);
		return success;
	}
	/**
	 * 当访问myIndent_01.html页面时进行数据的初始化
	 * @param showtype 根据该参数确定显示数据的类型
	 * @return
	 */
	@RequestMapping(value = "/initRepair.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String initRepair(int showtype,int offset,HttpServletRequest request) {
		String openId=(String) request.getSession().getAttribute("openId");
		if(openId==null){
			return  "{\"infor\":\"false\",\"message\",\"请先登录\"}";
		}
		return repairService.initRepair(openId,showtype, offset);
	}
	
	/**
	 * 添加个人报修的repair
	 * **/
	@RequestMapping(value="/insertRepair.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String insertRepair(Repairbo repairbo,HttpServletRequest request){
		String openId=(String) request.getSession().getAttribute("openId");
		String success=repairService.insertRepair(repairbo,openId);
		return success;
	}
	
	@RequestMapping(value="/insertReapirByPC.do",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String insertReapirByPC(Repairbo repairbo){
		return JSONObject.fromObject(repairService.insertRepairByPC(repairbo)).toString();
	}
	
	
	// 根据搜索框中的内容进行模糊查询
		@RequestMapping(value = "/selectBytext.do", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
		@ResponseBody
		public String selectBytext(Integer flage, String textvalue, Integer offset,Integer showtype,HttpServletRequest request) {
			String openId=(String) request.getSession().getAttribute("openId");
			return repairService.repairResult(openId,flage, textvalue, offset,showtype);
		}
		
	@RequestMapping(value="/selectRepairPage.do",method= RequestMethod.POST,produces = "text/html;charset=utf-8")
	@ResponseBody
	public String selectRepairPage(Repairbo repairbo){
		return JSONObject.fromObject(repairService.selectRepairPage(repairbo)).toString();
	}
	
	@RequestMapping(value="/deleteRepair.do",method=RequestMethod.POST,produces = "text/html;charset=utf-8")
	@ResponseBody
	public String deleteRepair(int id){
		return JSONObject.fromObject(repairService.deleteRepair(id)).toString();
	}
	/**
	 * 修改repair
	 * **/
	@RequestMapping(value="/updateRepair.do",method= RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String updateRepair(Repairbo repair){
		CommonsResult success=repairService.updateRepair(repair);
		return JSONObject.fromObject(success).toString();
	}
	/**
	 * 指派自己
	 * **/
	@RequestMapping(value="/myselfRepair.do",method= RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String myselfRepair(Integer id,HttpServletRequest request){
		String openId=(String) request.getSession().getAttribute("openId");
		Repairbo repairbo = repairService.select(id);
		if(repairbo!=null){
			Adminbo adminbo = adminService.selectByOpenId(openId);
			if(adminbo!=null){
				repairbo.setAdminbo(adminbo);
				repairbo.setState(1);
				repairbo.setReceiptTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
				if(repairbo.getUser()!=null){
					TemplateUtil util=new TemplateUtil(CommonUtil.appid,CommonUtil.appsecret);
					WechatTemplate temp=util.setAcceptValue(repairbo.getFacilityName(), adminbo.getOpenId(), repairbo.getUser().getOpenId(),adminbo.getTell());
					try {
						util.sendTemplateMessage(temp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return JSONObject.fromObject(repairService.updateRepair(repairbo)).toString();
			}
		}
		return JSONObject.fromObject(new CommonsResult(false, "false")).toString() ;
	}
	
	@RequestMapping(value="/getServiceMan.do",method= RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String getServiceMan(Integer id,HttpServletRequest request){
		Repairbo repairbo = repairService.select(id);
		List<Servicemanbo> servicemans= servicemanService.selectServicemanByCampusId(repairbo.getCampus().getId());
		if(servicemans.size()>0){
			return JSONObject.fromObject(new CommonsResult(true, servicemans)).toString();
		}
		return JSONObject.fromObject(new CommonsResult(false, "没有维修人员")).toString();
	}
	/**
	 * 
	 * 去提醒维修人员及时处理报修
	 * **/
	@RequestMapping(value="/callServiceMan.do",method= RequestMethod.POST)
	@ResponseBody
	public String callServiceMan(Integer repairId,Integer servicemanId){
		Repairbo repairbo = repairService.select(repairId);
		Servicemanbo servicemanbo=servicemanService.selectServicemanById(servicemanId);
		repairbo.setState(1);
		repairbo.setServiceman(servicemanbo);
		repairbo.setReceiptTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
		repairService.updateRepair(repairbo);
	
		String success=userService.callService(repairbo,servicemanbo);
		if(success.equals("success")){
			return JSONObject.fromObject(new CommonsResult(true)).toString();
		}
		return JSONObject.fromObject(new CommonsResult(false)).toString();
	}

	
	@RequestMapping(value="/getRepairByState.do")
	public void getRepairByState(Integer expTime,HttpServletResponse response) throws IOException{
	
		HSSFWorkbook wb=repairService.selectRepairByState(expTime);
		response.setContentType("application/vnd.ms-excel");    
        String time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        response.setHeader("Content-disposition", "attachment;filename="+time+".xls");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();

	}
	
	/**
	 * 统计
	 * 
	 * **/
	@RequestMapping(value="/stat.do")
	@ResponseBody
	public String stat(){
		String result=repairService.stat();
		System.out.println(result);
		return result;
		
	}
}
