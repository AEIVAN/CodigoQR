package com.deisa.file.dto;

import java.util.ArrayList;
import java.util.List;

import com.deisa.file.dto.Documento;

public class InformationDocuments {
	
	private String cliente ; 
	private String dirigido; 
	private String emision ; 
	private String departamento; 
	private String documento ; 
	private String numero ; 
	private String nombre ;
	private String id ; 
	private List <Documento> documentos;
	
	public InformationDocuments(String cliente, String dirigido, String emision, String departamento, String documento,
			String numero, String nombre, String id, List<Documento> documentos) {
		super();
		this.cliente = cliente;
		this.dirigido = dirigido;
		this.emision = emision;
		this.departamento = departamento;
		this.documento = documento;
		this.numero = numero;
		this.nombre = nombre;
		this.id = id;
		this.documentos = documentos;
	}
	
	public InformationDocuments() {
		super();
		this.cliente = "";
		this.dirigido = "";
		this.emision = "";
		this.departamento = "";
		this.documento = "";
		this.numero = "";
		this.nombre = "";
		this.id = "";
		this.documentos = new ArrayList<>();
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDirigido() {
		return dirigido;
	}

	public void setDirigido(String dirigido) {
		this.dirigido = dirigido;
	}

	public String getEmision() {
		return emision;
	}

	public void setEmision(String emision) {
		this.emision = emision;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	@Override
	public String toString() {
		return "InformationDocuments [cliente=" + cliente + ", dirigido=" + dirigido + ", emision=" + emision
				+ ", departamento=" + departamento + ", documento=" + documento + ", numero=" + numero + ", nombre="
				+ nombre + ", id=" + id + ", documentos=" + documentos + "]";
	}
	
}
