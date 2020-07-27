package com.repairs.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 网络协议证书
 * @author SamWang
 */
public class MyX509TrustManager implements X509TrustManager{

	//检查客户端证书
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		
	}

	//检查服务器端证书
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		
	}

	//返回受信任的x509证书数组
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
	

}
