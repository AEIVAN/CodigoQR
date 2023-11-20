package com.deisa.file.dto;

import java.util.ArrayList;
import java.util.List;

public class QrDocument {
	private String id;
	private String tipo;
	private String departamento;
	private String documento;
	private String numero;
	private String razonSocial;
	private String nombre;
	private String extension;
	private String qr;
	private String file;
	private String url;
	private String usuario;
	private int descargar;
	private String contrasenia;
	private String estado;
	private String fecha;
	private List<LogRecordDTO> logRecordDTO ; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getDescargar() {
		return descargar;
	}

	public void setDescargar(int descargar) {
		this.descargar = descargar;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public List<LogRecordDTO> getLogRecordDTO() {
		return logRecordDTO;
	}

	public void setLogRecordDTO(List<LogRecordDTO> logRecordDTO) {
		this.logRecordDTO = logRecordDTO;
	}

	

	@Override
	public String toString() {
		return "QrDocument [id=" + id + ", tipo=" + tipo + ", departamento=" + departamento + ", documento=" + documento
				+ ", numero=" + numero + ", razonSocial=" + razonSocial + ", nombre=" + nombre + ", extension="
				+ extension + ", qr=" + qr + ", file=" + file + ", url=" + url + ", usuario=" + usuario + ", descargar="
				+ descargar + ", contrasenia=" + contrasenia + ", estado=" + estado + ", fecha=" + fecha
				+ ", logRecordDTO=" + logRecordDTO + "]";
	}

	public QrDocument(String id, String tipo, String departamento, String documento, String numero, String razonSocial,
			String nombre, String extension, String qr, String file, String url, String usuario, int descargar,
			String contrasenia, String estado, String fecha, List<LogRecordDTO> logRecordDTO) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.departamento = departamento;
		this.documento = documento;
		this.numero = numero;
		this.razonSocial = razonSocial;
		this.nombre = nombre;
		this.extension = extension;
		this.qr = qr;
		this.file = file;
		this.url = url;
		this.usuario = usuario;
		this.descargar = descargar;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.fecha = fecha;
		this.logRecordDTO = logRecordDTO;
	}

	public QrDocument() {
		super();
		this.id = "";
		this.tipo = "";
		this.departamento = "";
		this.documento = "";
		this.numero = "";
		this.razonSocial = "";
		this.nombre = "";
		this.extension = "";
		this.qr = "";
		this.file = "";
		this.url = "";
		this.usuario = "";
		this.descargar = 0;
		this.contrasenia = "";
		this.estado = "";
		this.fecha = "";
		this.logRecordDTO = new ArrayList<>(); 
	}

	
	
	

}
