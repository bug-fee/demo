package com.repairs.bo;
/**
 * 公众帐号分组信息
 */
public class WeixinTag {
	//
	private int id;
	//
	private String name;
	//
	private int count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "WeixinTag [id=" + id + ", name=" + name + ", count=" + count + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
