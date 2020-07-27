package com.repairs.utils;

/**
 * 发送模板消息
 */
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.repairs.bo.Repairbo;
import com.repairs.bo.TemplateData;
import com.repairs.bo.WechatTemplate;
public class TemplateUtil {
	private   String appid;
	private   String appsecret;
	private   String SEND_TEMPLAYE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?" +
			"access_token=ACCESS_TOKEN";  

	private String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	public TemplateUtil() {
		//super();
	}
	public TemplateUtil(String appid,String appsecret){
		this.appid = appid;
		this.appsecret = appsecret;
		String servleturl="http://1w74497d58.imwork.net/repairs/userdispatcher.do";
		servleturl=urlEncodeUTF8(servleturl);
		this.url = url.replace("URI", servleturl);
		this.url = url.replace("APPID", appid);
	}
	
	public  void sendTemplateMessage(WechatTemplate wechatTemplate) throws Exception {
		String accessToken = CommonUtil.getToken(appid, appsecret).getAccessToken();
		String josnString = JSONObject.fromObject(wechatTemplate).toString();
		String requestUrl = SEND_TEMPLAYE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", josnString);
		if (null!=jsonObject) {
			System.out.println(jsonObject.toString());
		}
	}
	/**
	 * 以完成通知推送,发送给管理员
	 * @param toUser 消息接收方
	 * @param phoneNum 
	 * @return 消息内容
	 */
	public  WechatTemplate setAchieveValue(String toUser,String fromUser, String phoneNum){
		
		WechatTemplate wechatTemplate = new WechatTemplate();
		wechatTemplate.setTemplate_id("eZdZoHMtYmG5FujVr_5eScDmad6zNEk2JWidBYsDKus");
		wechatTemplate.setTouser(toUser);
		
		wechatTemplate.setUrl(url);
		Map<String,TemplateData> m = new HashMap<String,TemplateData>();
		TemplateData keyword1 = new TemplateData();
		keyword1.setValue(fromUser);
		keyword1.setColor("#000000");
		m.put("keyword1", keyword1);
		TemplateData remark = new TemplateData();
		remark.setValue("\n备注：如有疑问，请致电"+phoneNum);
		remark.setColor("#000000");
		m.put("remark", remark);
		wechatTemplate.setData(m);
		return wechatTemplate;
	}
	private String urlEncodeUTF8(String servleturl) {
		String result = servleturl;
		try {
			result = java.net.URLEncoder.encode(servleturl,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 受理通知推送
	 * @param maintainContext 维修内容
	 * @param fromUser 是谁维修的
	 * @param toUser 发送给谁
	 * @param phoneNum 维修人员号码
	 * @return 消息内容
	 */
	public WechatTemplate setAcceptValue(String maintainContext,String fromUser,String toUser,String phoneNum){
		WechatTemplate wechatTemplate = new WechatTemplate();
		wechatTemplate.setTemplate_id("fct0jFKhk0Rk1ilRzsn5AWiJ49Ufw1RBG0Tr9a3Ds_o");
		wechatTemplate.setTouser(toUser);
		System.out.println(url);
		wechatTemplate.setUrl(url);
		Map<String,TemplateData> m = new HashMap<String,TemplateData>();
		TemplateData keyword1 = new TemplateData();
		keyword1.setValue(maintainContext);
		keyword1.setColor("#000000");
		m.put("keyword1", keyword1);
		TemplateData keyword2 = new TemplateData();
		keyword2.setValue(maintainContext);
		keyword2.setColor("#000000");
		m.put("keyword1", keyword1);
		TemplateData remark = new TemplateData();
		remark.setValue("\n备注：如有疑问，请联系报修者电话"+phoneNum);
		remark.setColor("#000000");
		m.put("remark", remark);
		wechatTemplate.setData(m);
		return wechatTemplate;
	}
	
	/**
	 * 报修通知推送模板
	 * @param templateid 模板id
	 * @param toUser 接收方
	 * @param phoneNum 申请方电话
	 * @return 消息内容
	 */
	public WechatTemplate setReportValue(String toUser,String phoneNum ){
		WechatTemplate wechatTemplate = new WechatTemplate();
		wechatTemplate.setTemplate_id("XyuFzZQMKOZA4PfFElMsCtkUGRnjAkfMsPCskH5ZOu0");
		wechatTemplate.setTouser(toUser);
		wechatTemplate.setUrl(url);
		Map<String,TemplateData> m = new HashMap<String,TemplateData>();
		TemplateData remark = new TemplateData();
		remark.setValue("\n备注：如有疑问，请致电"+phoneNum);
		remark.setColor("#000000");
		m.put("remark", remark);
		wechatTemplate.setData(m);
		return wechatTemplate;
	}
	/**
	 * 审核维修人员
	 * @param toUser
	 * @return
	 */
	public WechatTemplate setVerify(String toUser,String msg){
		WechatTemplate wechatTemplate = new WechatTemplate();
		wechatTemplate.setTemplate_id("RIE0u27ean2_dyD8TuzMQhrwrj27hr6l8arK92iI7-A");
		wechatTemplate.setTouser(toUser);
		wechatTemplate.setUrl(url);
		Map<String,TemplateData> m = new HashMap<String,TemplateData>();
		TemplateData remark = new TemplateData();
		remark.setValue(msg);
		remark.setColor("#000000");
		m.put("remark", remark);
		wechatTemplate.setData(m);
		return wechatTemplate;
	}
	
	/**
	 * 报修提醒推送模板
	 * @param templateid 模板id
	 * @param toUser 接收方
	 * @param phoneNum 申请方电话
	 * @return 消息内容
	 */
	public WechatTemplate setremindValue(String toUser,Repairbo repairbo){
		WechatTemplate wechatTemplate = new WechatTemplate();
		wechatTemplate.setTemplate_id("51wDpjLyvm5x3wXE1RgbwrmYUGENA8QgGcIYnGIPrYc");
		wechatTemplate.setTouser(toUser);
		
		wechatTemplate.setUrl(url);
		Map<String,TemplateData> m = new HashMap<String,TemplateData>();
		TemplateData remark = new TemplateData();
		String name="";
		if (repairbo.getUser()==null) {
			name=repairbo.getRepairName();
		}else{
			repairbo.getUser().getName();
		}
		remark.setValue("\n请及时处理以下维修请求:" +
						 "\n发布人:"+name+
							"\n报修内容:"+repairbo.getFaultTitle()+
							"\n报修时间:"+repairbo.getSubTime()+
							"\n地址:"+repairbo.getCampus().getName()+repairbo.getFloorName()+repairbo.getRoomName()+
							"\n电话:"+repairbo.getMobileTell());
		remark.setColor("#000000");
		m.put("remark", remark);
		wechatTemplate.setData(m);
		return wechatTemplate;
	}
	/**
	 * 未解决通知模板
	 * @param toUser 接受者openid
	 * @param repairbo 报修记录实体
	 * @return 模板内容
	 */
	public WechatTemplate unRepairMessage(String toUser,Repairbo repairbo){
		WechatTemplate wechatTemplate = new WechatTemplate();
		wechatTemplate.setTemplate_id("j0ncEqTCC1fyFFV30p7to9t-wF8TbVyWmIyIitAMqKM");
		wechatTemplate.setTouser(toUser);
		wechatTemplate.setUrl(url);
		Map<String,TemplateData> m = new HashMap<String,TemplateData>();
		TemplateData keyword1 = new TemplateData();
		keyword1.setValue(repairbo.getServiceman().getName());
		keyword1.setColor("#000000");
		m.put("keyword1", keyword1);
		TemplateData keyword2 = new TemplateData();
		keyword2.setValue(repairbo.getFacilityName()+"的问题未解决");
		keyword2.setColor("#000000");
		m.put("keyword2", keyword2);
		TemplateData keyword3 = new TemplateData();
		keyword3.setValue(repairbo.getDefeatedreason());
		keyword3.setColor("#000000");
		m.put("keyword3", keyword3);
		TemplateData remark = new TemplateData();
		remark.setValue(repairbo.getServiceman().getTell());
		remark.setColor("#000000");
		m.put("remark", remark);
		wechatTemplate.setData(m);
		return wechatTemplate;
	}
}




















