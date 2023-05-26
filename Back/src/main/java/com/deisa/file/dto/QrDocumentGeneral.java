package com.deisa.file.dto;

import java.util.ArrayList;
import java.util.List;

public class QrDocumentGeneral {
	private String id ;
	private String tipo ;
	private String departamento ;
	private String documento ;
	private String numero ;
	private List<QrDocument> qrDocumentosInfo ;
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
	public List<QrDocument> getQrDocumentosInfo() {
		return qrDocumentosInfo;
	}
	
	public void setQrDocumentosInfo(List<QrDocument> qrDocumentosInfo) {
		this.qrDocumentosInfo = qrDocumentosInfo;
	}
	
	@Override
	public String toString() {
		return "QrDocumentGeneral [id=" + id + ", tipo=" + tipo + ", departamento=" + departamento + ", documento="
				+ documento + ", numero=" + numero + ", qrDocumentosInfo=" + qrDocumentosInfo + "]";
	}
	
	public QrDocumentGeneral(String id, String tipo, String departamento, String documento, String numero,
			List<QrDocument> qrDocumentosInfo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.departamento = departamento;
		this.documento = documento;
		this.numero = numero;
		this.qrDocumentosInfo = qrDocumentosInfo;
	}
	
	public QrDocumentGeneral() {
		super();
		this.id = "";
		this.tipo = "";
		this.departamento = "";
		this.documento = "";
		this.numero = "";
		this.qrDocumentosInfo = new ArrayList<QrDocument>();
	}
}
