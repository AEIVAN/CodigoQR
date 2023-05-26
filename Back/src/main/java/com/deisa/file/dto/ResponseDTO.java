package com.deisa.file.dto;

public class ResponseDTO {
	private String msg; 
	private Object body; 
	private Boolean status;
	

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "response [msg=" + msg + ", object=" + body + ", status=" + status + "]";
	}
	public ResponseDTO(String msg, Object body, Boolean status) {
		this.msg = msg;
		this.body = body;
		this.status = status;
	}
	public ResponseDTO() {
		this.msg = "";
		this.body = null; 
		this.status = false;
	}
	
}
