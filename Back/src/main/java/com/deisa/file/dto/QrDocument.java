package com.deisa.file.dto;

public class QrDocument {
	private String id ;
	private String departamento; 
	private String documento ; 
	private String numero ; 
	private String razonSocial ; 
    private String nombre ;
    private String extension ;
    private String qr ;
    private String file ;
    private String url ;
    private String contrasenia ;
    private String estado ;
    private String fecha ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "QrDocument [id=" + id + ", departamento=" + departamento + ", documento=" + documento + ", numero="
				+ numero + ", razonSocial=" + razonSocial + ", nombre=" + nombre + ", extension=" + extension + ", qr="
				+ qr + ", file=" + file + ", url=" + url + ", contrasenia=" + contrasenia + ", estado=" + estado
				+ ", fecha=" + fecha + "]";
	}
	
	public QrDocument(String id, String departamento, String documento, String numero, String razonSocial,
			String nombre, String extension, String qr, String file, String url, String contrasenia, String estado,
			String fecha) {
		super();
		this.id = id;
		this.departamento = departamento;
		this.documento = documento;
		this.numero = numero;
		this.razonSocial = razonSocial;
		this.nombre = nombre;
		this.extension = extension;
		this.qr = qr;
		this.file = file;
		this.url = url;
		this.contrasenia = contrasenia;
		this.estado = estado;
		this.fecha = fecha;
	} 
    
	public QrDocument() {
		super();
		this.id = "";
		this.departamento = "";
		this.documento = "";
		this.numero = "";
		this.razonSocial = "";
		this.nombre = "";
		this.extension = "";
		this.qr = "";
		this.file = "";
		this.url = "";
		this.contrasenia = "";
		this.estado = "";
		this.fecha = "";
	} 
	
}
