package com.deisa.file.service;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.deisa.file.dto.Documento;
import com.deisa.file.dto.InformationDocuments;
import com.deisa.file.dto.QrDocument;
import com.deisa.file.dto.QrDocumentGeneral;
import com.deisa.file.dto.ResponseDTO;
@Repository
public interface DocumentService {
	public Documento getQr(Documento documento) throws Exception; 
	public String cargaDocumento(MultipartFile file, String id, String extension) throws Exception; 
	public InformationDocuments getInfotmation(String id) throws Exception; 
	public byte[] getDocument(String id) throws Exception; 
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public String testPath(String id) throws Exception; 
	public QrDocumentGeneral getQrDocumentGeneral(QrDocumentGeneral qrDocumentGeneral) throws Exception; 
	public QrDocumentGeneral getQrDocumentGeneralId(String id) throws Exception; 
	public QrDocument getQrDocument(QrDocument qrDocument) throws Exception; 
	public byte[] downLoadDocument(String id) throws Exception; 
	public QrDocument postQrDocument(QrDocument qrDocument) throws Exception; 
	public String saveQrDocumentId(MultipartFile file, String id, String extension) throws Exception; 
	public QrDocument saveQrDocument(MultipartFile file, QrDocument qrDocument) throws Exception; 
	public String getCodeQr(String id) throws Exception; 
	public ResponseDTO getValidaContrasenia(String id, String contrasenia) throws Exception; 
}
