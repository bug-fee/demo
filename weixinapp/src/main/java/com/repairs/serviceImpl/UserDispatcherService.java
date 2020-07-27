package com.repairs.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repairs.bo.Adminbo;
import com.repairs.bo.Servicemanbo;
import com.repairs.bo.Userbo;
import com.repairs.dao.AdminDao;
import com.repairs.dao.ServicemanDao;
import com.repairs.dao.UserDao;
import com.repairs.utils.CommonUtil;
import com.repairs.weixinutil.AdvancedUtil;
import com.repairs.weixinutil.SNSUserInfo;
import com.repairs.weixinutil.WeixinOauth2Token;
@Service("userDispatcherService")
public class UserDispatcherService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ServicemanDao servicemanDao;
	@Autowired
	private UserDao userDao;
	public String getDispatcherUrl(HttpServletRequest req) {
		String url = "";
		String openId="";
		String code = req.getParameter("code");
		HttpSession session=req.getSession();
		WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getAouth2AccessToken(CommonUtil.appid,CommonUtil.appsecret , code);
		String accessToken = weixinOauth2Token.getAccessToken();
		openId = weixinOauth2Token.getOpenId();
		SNSUserInfo userInfo= AdvancedUtil.getSNSUserInof(accessToken,openId);
		String imgurl=userInfo.getHeadImgUrl();
		session.setAttribute("openId", openId);
		session.setAttribute("imgurl",imgurl);
		session.setMaxInactiveInterval(600);
		Adminbo adminbo = new Adminbo();
		adminbo.setOpenId(openId);
		List<Adminbo> adminlist = adminDao.selectAdmin(adminbo);
		if (adminlist.size()!=0) {
			url="wx/admin/myRepair_home_01.html";
		}else{
			Servicemanbo servicemanbo = new Servicemanbo();
			servicemanbo.setOpenId(openId);
			servicemanbo.setState(1);
			List<Servicemanbo> servicemanlist = servicemanDao.selectServiceman(servicemanbo);
			if(servicemanlist.size()!=0){
				url="wx/service/myRepair_home_01.html";
				servicemanbo=servicemanlist.get(0);
				servicemanbo.setHeadimgurl(imgurl);
				servicemanDao.updateServiceman(servicemanbo);
			}else{
				Userbo userbo = new Userbo();
				userbo.setOpenId(openId);
				List<Userbo> userlist = userDao.selectUser(userbo);
				if(userlist.size()!=0){
					url="wx/myRepair_home_01.html";
				}else{
					url="wx/band.html?type=1";
				}
			}
		}
		return url;
	}
	public String getRepairDispatcherUrl(HttpServletRequest req) {
		String url = "";
		String openId="";
		String code = req.getParameter("code");
		HttpSession session=req.getSession();
		WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getAouth2AccessToken(CommonUtil.appid,CommonUtil.appsecret  , code);
		String accessToken = weixinOauth2Token.getAccessToken();
		openId = weixinOauth2Token.getOpenId();
		SNSUserInfo userInfo= AdvancedUtil.getSNSUserInof(accessToken,openId);
		String imgurl=userInfo.getHeadImgUrl();
		session.setAttribute("openId", openId);
		session.setAttribute("imgurl",imgurl);
		session.setMaxInactiveInterval(600);
		Userbo userbo = new Userbo();
		userbo.setOpenId(openId);
		List<Userbo> userlist = userDao.selectUser(userbo);
		if(userlist.size()!=0){
			url="wx/maintain.html";
		}else{
			url="wx/band.html?type=2";
		}
		return url;
	}

}
