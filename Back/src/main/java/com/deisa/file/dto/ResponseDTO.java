package com.deisa.file.dto;

public class ResponseDTO {
	private String msg; 
	private Object object; 
	private Boolean status;
	

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "response [msg=" + msg + ", object=" + object + ", status=" + status + "]";
	}
	public ResponseDTO(String msg, Object object, Boolean status) {
		this.msg = msg;
		this.object = object;
		this.status = status;
	}
	public ResponseDTO() {
		this.msg = "";
		this.object = null; 
		this.status = false;
	}
	
}
