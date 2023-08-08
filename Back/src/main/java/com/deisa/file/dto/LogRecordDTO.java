package com.deisa.file.dto;

public class LogRecordDTO {
	private String registro;
	private String fecha_registro;
	private String usuario ; 
	private String accion;
	private String orden;
	private String idQr;
	private String request;
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getIdQr() {
		return idQr;
	}
	public void setIdQr(String idQr) {
		this.idQr = idQr;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	@Override
	public String toString() {
		return "LogRecordDTO [registro=" + registro + ", fecha_registro=" + fecha_registro + ", usuario=" + usuario
				+ ", accion=" + accion + ", orden=" + orden + ", idQr=" + idQr + ", request=" + request + "]";
	}
	public LogRecordDTO(String registro, String fecha_registro, String usuario, String accion, String orden,
			String idQr, String request) {
		super();
		this.registro = registro;
		this.fecha_registro = fecha_registro;
		this.usuario = usuario;
		this.accion = accion;
		this.orden = orden;
		this.idQr = idQr;
		this.request = request;
	}
	
	public LogRecordDTO(String usuario, String accion, String orden,
			String idQr, String request) {
		super();
		this.registro = registro;
		this.fecha_registro = fecha_registro;
		this.usuario = usuario;
		this.accion = accion;
		this.orden = orden;
		this.idQr = idQr;
		this.request = request;
	}

	public LogRecordDTO() {
		super();
		this.registro = "";
		this.fecha_registro = "";
		this.usuario = "";
		this.accion = "";
		this.orden = "";
		this.idQr = "";
		this.request = "";
	}
	
	

}
