package com.deisa.file.dto;

import java.util.ArrayList;
import java.util.List;

public class QrDocumentGeneral {
	private String id ;
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
		return "QrDocument [id=" + id + ", departamento=" + departamento + ", documento=" + documento + ", numero="
				+ numero + ", qrDocumentosInfo=" + qrDocumentosInfo + "]";
	}
	
	public QrDocumentGeneral() {
		this.id = "";
		this.departamento = "";
		this.documento = "";
		this.numero = "";
		this.qrDocumentosInfo = new ArrayList<QrDocument>();
	}
	
	public QrDocumentGeneral(String id, String departamento, String documento, String numero,
			List<QrDocument> qrDocumentosInfo) {
		super();
		this.id = id;
		this.departamento = departamento;
		this.documento = documento;
		this.numero = numero;
		this.qrDocumentosInfo = qrDocumentosInfo;
	} 
	
}
