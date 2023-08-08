package com.deisa.file.dto;

public class Email {
	private String correo;
	
	private String usuario;
	
	private String contrasenia;
	
	private String fecha;
	
	private String msg;
	
	private String orden;
	
	private String idQr;

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	@Override
	public String toString() {
		return "Email [correo=" + correo + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", fecha=" + fecha
				+ ", msg=" + msg + ", orden=" + orden + ", idQr=" + idQr + "]";
	}

	public Email(String correo, String usuario, String contrasenia, String fecha, String msg, String orden,
			String idQr) {
		super();
		this.correo = correo;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.fecha = fecha;
		this.msg = msg;
		this.orden = orden;
		this.idQr = idQr;
	}

	public Email() {
		super();
		this.correo = "";
		this.usuario = "";
		this.contrasenia = "";
		this.fecha = "";
		this.msg = "";
		this.orden = "";
		this.idQr = "";
	}
}
