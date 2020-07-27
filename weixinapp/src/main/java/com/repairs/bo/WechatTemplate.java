package com.repairs.bo;

import java.util.Map;

public class WechatTemplate {
	//接收方
	private String touser;
	//模板id
	private String template_id;
	//跳转路径
	private String url;
	//数据集合
	private Map<String,TemplateData> data;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	@Override
	public String toString() {
		return "WechatTemplate [touser=" + touser + ", template_id=" + template_id + ", url=" + url + ", data=" + data
				+ "]";
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, TemplateData> getData() {
		return data;
	}
	public void setData(Map<String, TemplateData> data) {
		this.data = data;
	}
	
	
}
