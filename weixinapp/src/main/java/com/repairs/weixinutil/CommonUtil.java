package com.repairs.weixinutil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 普通微信接口工具类
 * @author Administrator
 *
 */
public class CommonUtil {
	/**
	 * URL：用于获取普通接口使用权限Access_token
	 */
	public final static String token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * 通用----HTTPS请求方法
	 * @param requestUrl		请求地址
	 * @param requestMethod		请求方式：post、get
	 * @param outputStr			请求数据json字符串
	 * @return
	 */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject=null;
		try{
			//创建SSLContext对象，并使用我们制定的信任器初始化
			TrustManager[] tm={new MyX509TrustManager()};
			SSLContext sslContext=SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new SecureRandom());
			//从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf=sslContext.getSocketFactory();
			
			URL url=new URL(requestUrl);
			HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			//设置请求方式(get/post)
			conn.setRequestMethod(requestMethod);
			
			//当outputStr不为null时，向输出流写数据
			if(null!=outputStr){
				OutputStream outputStream = conn.getOutputStream();
				//注意编码格式
				outputStream .write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			//从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
			String str=null;
			StringBuffer buffer=new StringBuffer();
			while((str=bufferedReader.readLine())!=null){
				buffer.append(str);
			}
			//释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream=null;
			conn.disconnect();
			jsonObject=JSONObject.fromObject(buffer.toString());
		}catch(ConnectException ce){
			System.out.println("连接超时");
		}catch(Exception e){
			System.out.println("http请求异常");
		}
		return jsonObject;
	}
	
	/**
	 * 获取普通接口使用权限Access_token
	 */
	public static Token getToken(String appid,String appsecret){
		Token token=null;
		String requestUrl=token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		//发起GET请求获取凭证
		JSONObject jsonObject=httpsRequest(requestUrl,"GET",null);
		if(null!=jsonObject){
			try{
				token=new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			}catch(JSONException e){
				token=null;
				//获取token失败
				System.out.println("获取token失败");
			}
		}
		return token;
	}
	
}
