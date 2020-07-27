package com.repairs.weixinutil;

import java.util.ResourceBundle;

public class WeixinAppidUtil {

	private static ResourceBundle resource = ResourceBundle.getBundle("config");
	
	public static String getAppid(){
		String appid = resource.getString("appid");
		return appid;
	}
	
	public static String getAPPSECRET(){
		String APPSECRET = resource.getString("APPSECRET");
		return APPSECRET;
	}

}
