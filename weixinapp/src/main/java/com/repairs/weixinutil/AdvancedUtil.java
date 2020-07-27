package com.repairs.weixinutil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.repairs.utils.CommonUtil;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 高级微信接口工具类
 * @author Administrator
 *
 */
public class AdvancedUtil {
	
	/**
	 * 获取通过授权Access_token
	 */
	public static WeixinOauth2Token getAouth2AccessToken(String appId,
			String appSecret,String code) {
		WeixinOauth2Token wat = new WeixinOauth2Token();
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
				"appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE",code);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		try {
			if (null != jsonObject) {
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			}
		} catch (Exception e) {
			wat = null;
			e.printStackTrace();
		}
		return wat;
	}
	
	/**
	 * url路径utf-8转码
	 */
	public static String urlEncodeUTF8(String source) throws UnsupportedEncodingException{
		String result=source;
		result=URLEncoder.encode(source,"utf-8");
		return result;
	}
	
	
	/**
	 * 刷新授权Token
	 * @param appId
	 * @param refreshToken
	 * @return
	 * @throws Exception
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,String refreshToken){
		
		WeixinOauth2Token wat = null;
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?" +
				"appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		//刷新网页授权凭证
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null!=jsonObject) {
			try {

				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				System.out.println(jsonObject.getString("errmsg"));
			}
		}
		return wat;
	}
	
	
	
	/**
	 * 通过网页授权获取用户信息
	 * @throws Exception 
	 * @return 用户信息对象
	 */
	@SuppressWarnings("unchecked")
	public static SNSUserInfo getSNSUserInof(String accessToken,String openId){
		
		SNSUserInfo snsUserInfo = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null!=jsonObject){
			try {
				snsUserInfo = new SNSUserInfo();
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				snsUserInfo.setNickName(jsonObject.getString("nickname"));
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				snsUserInfo.setCountry(jsonObject.getString("country"));
				snsUserInfo.setProvince(jsonObject.getString("province"));
				snsUserInfo.setCity(jsonObject.getString("city"));
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"),List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				System.out.println(jsonObject.getString("errmsg"));
			}
		}
		return snsUserInfo;
	}
}

