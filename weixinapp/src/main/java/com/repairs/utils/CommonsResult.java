package com.repairs.utils;

import java.io.Serializable;

public class CommonsResult<T> implements Serializable{
	private boolean success;
	private T data;
	private String message;
	private Integer total;
	
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public CommonsResult(boolean success){
		this.success=success;
	}
	
	public CommonsResult(boolean success,T data){
		this.success=success;
		this.data=data;
	}
	
	public CommonsResult(boolean success,String message){
		this.success=success;
		this.message=message;
	}
	
	public CommonsResult(boolean success,String message,T data){
		this.success=success;
		this.message=message;
		this.data=data;
	}
	
	public CommonsResult(boolean success,String message,T data,Integer total){
		this.success=success;
		this.message=message;
		this.data=data;
		this.total=total;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	

}
