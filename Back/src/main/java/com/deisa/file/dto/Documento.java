package com.deisa.file.dto;

public class Documento {
	private String departamento; 
	private String documento ; 
	private String numero ; 
	private String nombre ;
	private String extension; 
	private String id ; 
	private String qr ; 
	
	public Documento(String departamento, String documento, String numero, String nombre, String extension, String id, String qr) {
		super();
		this.departamento = departamento;
		this.documento = documento;
		this.numero = numero;
		this.nombre = nombre;
		this.extension = extension ; 
		this.id = id;
		this.qr = qr;
	}
	
	public Documento() {
		super();
		this.departamento = "";
		this.documento = "";
		this.numero = "";
		this.nombre = "";
		this.extension = "" ; 
		this.id = "";
		this.qr = "";
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQr() {
		return qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	@Override
	public String toString() {
		return "Documento [departamento=" + departamento + ", documento=" + documento + ", numero=" + numero + ", nombre="
				+ nombre + ", extension=" + extension + ", id=" + id + ", qr=" + qr + "]";
	}
	
}
