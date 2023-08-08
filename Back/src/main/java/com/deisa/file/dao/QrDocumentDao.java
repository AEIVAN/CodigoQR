package com.deisa.file.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deisa.file.dto.Documento;
import com.deisa.file.dto.Email;
import com.deisa.file.dto.LogRecordDTO;
import com.deisa.file.dto.QrDocument;
import com.deisa.file.dto.QrDocumentGeneral;

@Repository
public interface QrDocumentDao {
	public String exisDocumento(QrDocument qrDocument) throws Exception; 
	public List<QrDocument> getQrDocuments(QrDocumentGeneral qrDocumentGeneral) throws Exception; 
	public List<QrDocument> getQrDocumentsById(QrDocumentGeneral qrDocumentGeneral) throws Exception; 
	public QrDocument getQrDocument(QrDocument qrDocument) throws Exception; 
	public QrDocument getQrDocumentById(String id) throws Exception; 
	public QrDocument downLoadDocument(String id) throws Exception;
	public boolean insertQrDocument(QrDocument qrDocument) throws Exception; 
	public boolean updateQrDocument(QrDocument qrDocument) throws Exception; 
	public boolean getValidaContrasenia(String id, String contrasenia) throws Exception; 
	public String getValidaContraseniaPermiso( String contrasenia, QrDocument qrDocument) throws Exception; 
	public boolean changePassword(Email email) throws Exception; 
	public boolean insertLog(LogRecordDTO logRecordDTO) throws Exception;
	public boolean updateDownloads(QrDocument qrDocument) throws Exception;
}
