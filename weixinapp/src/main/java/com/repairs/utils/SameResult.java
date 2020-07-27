package com.repairs.utils;

import java.io.Serializable;



public class SameResult<T> implements Serializable {
	
	private boolean success=true;
	private Integer code;
	private String message;
	private T data;
	private Long total;
	
	public static<T> SameResult<T> successReturn(T data,Long total){
		return successReturn(data, total, null);
	}
	
	public static<T> SameResult<T> successReturn(T data,String message){
		return successReturn(data,null,message);
	}
	
	public static<T> SameResult<T> errorReturn(String message){
		return errorReturn(null,message);
	}
	
	public static<T> SameResult<T> successReturn(T data,Long total,String message){
		SameResult<T> result = new SameResult<T>();
		result.setSuccess(true);
		result.setCode(0);
		result.setMessage(message);
		result.setData(data);
		result.setTotal(total);
		return result;
	}
	
	public static<T> SameResult<T> errorReturn(Integer code,String message){
		SameResult<T> result = new SameResult<T>();
		result.setSuccess(false);
		result.setCode(code);
		result.setMessage(message);
		result.setData(null);
		result.setTotal(null);
		return result;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
}
