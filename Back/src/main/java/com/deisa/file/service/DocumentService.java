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
//	public Documento getQr(Documento documento) throws Exception; 
//	public String cargaDocumento(MultipartFile file, String id, String extension) throws Exception; 
//	public InformationDocuments getInfotmation(String id) throws Exception; 
//	public byte[] getDocument(String id) throws Exception; 
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public ResponseDTO test(String test) ; 
	public ResponseDTO testPath(String testPath); 
	public ResponseDTO getQrDocumentGeneral(QrDocumentGeneral qrDocumentGeneral); 
	public ResponseDTO getQrDocument(QrDocument qrDocument); 
	public byte[] downLoadDocument(String id); 
	public ResponseDTO postQrDocument(QrDocument qrDocument); 
	public ResponseDTO saveQrDocumentId(MultipartFile file, String id, String extension); 
	public ResponseDTO saveQrDocument(MultipartFile file, QrDocument qrDocument); 
	public ResponseDTO getCodeQr(String id); 
	public ResponseDTO getQrDocumentGeneralId(String id); 
	public ResponseDTO getValidaContrasenia(String id, String contrasenia); 
}
