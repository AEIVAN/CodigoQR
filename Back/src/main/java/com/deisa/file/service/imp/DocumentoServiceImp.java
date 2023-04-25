package com.deisa.file.service.imp;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.deisa.file.dao.DocumentDao;
import com.deisa.file.dao.InformationDocumentDao;
import com.deisa.file.dao.QrDocumentGeneralDao;
import com.deisa.file.dao.QrDocumentDao;
import com.deisa.file.dao.imp.InformationDocumentDaoImp;
import com.deisa.file.dto.Documento;
import com.deisa.file.dto.InformationDocuments;
import com.deisa.file.dto.QrDocumentGeneral;
import com.deisa.file.dto.ResponseDTO;
import com.deisa.file.dto.QrDocument;
import com.deisa.file.service.DocumentService;
import com.deisa.file.service.QRCodeService;
import com.deisa.file.utils.GenerateAlphaNumericString;



@Service
public class DocumentoServiceImp implements DocumentService {
	@Autowired
	QRCodeService qRCodeService ;
	@Autowired
	GenerateAlphaNumericString generateAlphaNumericString ;
	@Autowired
	DocumentDao documentDao ; 
	@Autowired
	QrDocumentGeneralDao qrDocumentGeneralDao ;
	@Autowired
	QrDocumentDao qrDocumentDao;
	@Autowired
	InformationDocumentDao informationDocumentDao ; 
	
//	String urlBase = "http://localhost:4200/" ;
// 	String dirPathBase = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\" ; 
// 	String diagonal = "\\"; 
	
//	String urlBase = "http://localhost:8080/" ;
// 	String dirPathBase = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\" ; 
// 	String diagonal = "\\"; 
 	
	String urlBase = "http://reportesdeisa.ddns.net:8081/" ;
 	String dirPathBase = "/home/server/Documents/Deisa/" ; 
 	String diagonal = "/"; 
	
//	String urlBase = "http://52.23.232.199:8080/" ;
//    String dirPathBase = "C:\\Users\\Administrator\\Documents\\Deisa\\" ;

	@Override
	public Documento getQr(Documento documento) throws Exception {
		System.out.println("getQr");
		documento.setId(documentDao.exisDocumento(documento));
		if (documento.getId()=="") {
			int i = 0; 
			//Insert
			while (true) {
				documento.setId(generateAlphaNumericString.getRandomString(10));
				if (!documentDao.existId(documento.getId()))
					break; 
				if (i == 5)
					return null ; 
				i++;
			}
			if(!documentDao.insertDocumento(documento)) return null ;  
		}
//		else {
//			if(!documentDao.updateDocumento(documento)) return null ;  
//		}
		//String cadenaQr = "http://192.168.0.16:8080/deisa/?id=" + documento.getId(); 
		//String cadenaQr = "http://192.168.2.114:8080/deisa/?id=" + documento.getId();
		String cadenaQr = "http://52.23.232.199:8080/deisa/?id=" + documento.getId();
		System.out.println("cadenaQr "+ cadenaQr);
		documento.setQr(qRCodeService.crateQRCode(cadenaQr, 200, 200)); 
		System.out.println("Documento "+ documento);
		return documento;
	}
	
	public String cargaDocumento(MultipartFile file, String id,String extension) throws Exception{
			System.out.println("cargaDocumento");
			List<Documento> documentos = documentDao.getDocumento(id);
			System.out.println("documentos " + documentos.get(0));
			System.out.println("documentos.size() " + documentos.size());
			System.out.println("extension " + extension);
			System.out.println("documentos.get(0).getExtension() " + documentos.get(0).getExtension());
			System.out.println("documentos.size()!=0  " + (documentos.size()!=0) );
			System.out.println("extension == documentos.get(0).getExtension() " + extension.trim().equals(documentos.get(0).getExtension().trim()) );
			
			if (documentos.size()!=0 && extension.trim().equals(documentos.get(0).getExtension().trim()) ) {
				Documento documento = documentos.get(0) ; 
				System.out.println("fileName "+ file.getOriginalFilename());
				String fileName = file.getOriginalFilename();
		        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		        String localFileName = documento.getNombre()+fileSuffix;
		        String dirPath = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
		        //String dirPath = "C:\\Users\\Administrator\\Documents\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
		        documento.getNumero()+diagonal; 
		        String filePath = dirPath + localFileName;
				System.out.println("filePath "+ filePath);
		        File localFile = new File(filePath);
		        File imagePath = new File(dirPath);
		        if (!imagePath.exists()) {
		            imagePath.mkdirs();
		        }
		        file.transferTo(localFile);
		        return "OK";
			}
		return "Id no valido o se esperaba otro tipo de formato";
	}
	
	@Override
	public InformationDocuments getInfotmation(String id) throws Exception{
		InformationDocuments informationDocuments = informationDocumentDao.getInformationDocument(id); 
		return informationDocuments ; 
	}
	
	@Override
	public byte[] getDocument(String id) throws Exception{
		System.out.println("getDocument " + id);
		List<Documento> documentos = documentDao.getDocumento(id);
		if(documentos.size()!=0) {
			Documento documento = documentos.get(0); 
			String dirPath = dirPathBase+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
			//String dirPath = "C:\\Users\\JF-Sistemas\\Desktop\\prueba_qr\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
			//String dirPath = "C:\\Users\\Administrator\\Documents\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
			documento.getNumero()+diagonal+documento.getNombre()+"."+documento.getExtension();
			System.out.println("dirPath " + dirPath);
			File file = new File(dirPath); 
	        byte[] fileContent = Files.readAllBytes(file.toPath());
	        //System.out.println("fileContent " + fileContent);
	        return fileContent; 
		}
		return null;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public QrDocumentGeneral getQrDocumentGeneral(QrDocumentGeneral qrDocumentGeneral) throws Exception{
		QrDocumentGeneral qrDocumentGeneralResponse = qrDocumentGeneralDao.getQrDocumentGeneral(qrDocumentGeneral); 
		List<QrDocument> qrDocumentosInfoResponse = qrDocumentDao.getQrDocuments(qrDocumentGeneralResponse);
		for (QrDocument qrDocument : qrDocumentosInfoResponse) {
			qrDocument.setQr("Si");
			qrDocument.setFile(existFIle(qrDocument));
			qrDocument.setUrl(getUrl(qrDocument));
		}	
		qrDocumentGeneralResponse.setQrDocumentosInfo(qrDocumentosInfoResponse);
		return qrDocumentGeneralResponse ; 
	}
	
	public QrDocument getQrDocument(QrDocument qrDocument) throws Exception{
		QrDocument qrDocumentResponse = qrDocumentDao.getQrDocument(qrDocument); 
		if (qrDocumentResponse.getId().trim()!="") {
			qrDocumentResponse.setUrl(getUrl(qrDocumentResponse));
			qrDocumentResponse.setQr(getQr(qrDocumentResponse.getUrl())); 
			qrDocumentResponse.setFile(existFIle(qrDocumentResponse));
			return qrDocumentResponse ; 
		}
		return qrDocumentResponse ; 
	}
	
	@Override
	public byte[] downLoadDocument(String id) throws Exception{
		System.out.println("getDocument " + id);
		QrDocument qrDocument = new QrDocument(); 
		qrDocument = qrDocumentDao.getQrDocumentById(id); 
		if(qrDocument.getId().trim()!="") {
			try {
				qrDocument =qrDocumentDao.downLoadDocument(id);
				String dirPath = dirPathBase+qrDocument.getDepartamento()+diagonal+qrDocument.getDocumento()+diagonal+
				qrDocument.getNumero()+diagonal+qrDocument.getNombre()+"."+qrDocument.getExtension();
				System.out.println("dirPath " + dirPath);
				File file = new File(dirPath); 
		        byte[] fileContent = Files.readAllBytes(file.toPath());
		        return fileContent; 
			}
			catch (Exception e) {
				System.out.println("getDocument " + e);
				return null ; 
			}
		}
		return null;
	}
	
	public QrDocument postQrDocument(QrDocument qrDocument) throws Exception{
		System.out.println("getQr");
		qrDocument.setId(qrDocumentDao.exisDocumento(qrDocument));
		if (qrDocument.getId()=="") {
			int i = 0; 
			while (true) {
				qrDocument.setId(generateAlphaNumericString.getRandomString(10));
				if (!documentDao.existId(qrDocument.getId()))
					break; 
				if (i == 5)
					return null ; 
				i++;
			}
			if(!qrDocumentDao.insertQrDocument(qrDocument)) return null ;  
		}
		else {
			if(!qrDocumentDao.updateQrDocument(qrDocument)) return null ;  
		}
		
		qrDocument.setUrl(urlBase+"deisa/?id=" + qrDocument.getId());
		qrDocument.setQr(qRCodeService.crateQRCode(qrDocument.getUrl(), 200, 200)); 
		System.out.println("Documento "+ qrDocument);
		return qrDocument;
	}
	
	public String saveQrDocumentId(MultipartFile file, String id, String extension) throws Exception{

		System.out.println("cargaDocumento");
		QrDocument qrDocumentResponse = qrDocumentDao.getQrDocumentById(id);
		if (qrDocumentResponse.getId()!="" && extension.trim().equals(qrDocumentResponse.getExtension().trim()) ) {
			System.out.println("fileName "+ file.getOriginalFilename());
			String fileName = file.getOriginalFilename();
	        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
	        String localFileName = qrDocumentResponse.getNombre()+fileSuffix;
	        String dirPath = dirPathBase+qrDocumentResponse.getDepartamento()+diagonal+qrDocumentResponse.getDocumento()+diagonal+
	        //String dirPath = "C:\\Users\\Administrator\\Documents\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
	        qrDocumentResponse.getNumero()+diagonal; 
	        String filePath = dirPath + localFileName;
			System.out.println("filePath "+ filePath);
	        File localFile = new File(filePath);
	        File imagePath = new File(dirPath);
	        if (!imagePath.exists()) {
	            imagePath.mkdirs();
	        }
	        file.transferTo(localFile);
	        return "OK";
		}
	return "Id no valido o se esperaba otro tipo de formato";
	}
	public QrDocument saveQrDocument(MultipartFile file, QrDocument qrDocument) throws Exception{
		System.out.println("cargaDocumento");
			String fileName = file.getOriginalFilename();
	        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
	        String localFileName = qrDocument.getNombre()+fileSuffix;
	        String dirPath = dirPathBase+qrDocument.getDepartamento()+diagonal+qrDocument.getDocumento()+diagonal+
	        //String dirPath = "C:\\Users\\Administrator\\Documents\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
	        qrDocument.getNumero()+diagonal; 
	        String filePath = dirPath + localFileName;
			System.out.println("filePath "+ filePath);
	        File localFile = new File(filePath);
	        File imagePath = new File(dirPath);
	        if (!imagePath.exists()) {
	            imagePath.mkdirs();
	        }
	        file.transferTo(localFile);
	        
			qrDocument.setId(qrDocumentDao.exisDocumento(qrDocument));
			if (qrDocument.getId()=="") {
				int i = 0; 
				while (true) {
					qrDocument.setId(generateAlphaNumericString.getRandomString(10));
					if (!documentDao.existId(qrDocument.getId()))
						break; 
					if (i == 5)
						return null ; 
					i++;
				}
				if(!qrDocumentDao.insertQrDocument(qrDocument)) return null ;  
			}
			else {
				if(!qrDocumentDao.updateQrDocument(qrDocument)) return null ;  
			}
			
			qrDocument.setUrl(urlBase+"deisa/?id=" + qrDocument.getId());
			qrDocument.setQr(qRCodeService.crateQRCode(qrDocument.getUrl(), 200, 200)); 
			qrDocument.setFile("Si");
			return qrDocument;
	}
	
	public String getCodeQr(String id) throws Exception{
		QrDocument qrDocument = new QrDocument(); 
		qrDocument = qrDocumentDao.getQrDocumentById(id); 
		qrDocument.setUrl(getUrl(qrDocument)); 
		return getQr(qrDocument.getUrl());
	}
	
	private String getUrl(QrDocument qrDocument) {
		qrDocument.setUrl(urlBase+"deisa/?id=" + qrDocument.getId());
		return urlBase+"deisa/?id=" + qrDocument.getId() ; 
	}
	
	private String existFIle(QrDocument qrDocument) {
		String dirPath = dirPathBase+qrDocument.getDepartamento()+diagonal+qrDocument.getDocumento()+diagonal+
				qrDocument.getNumero()+diagonal+qrDocument.getNombre()+"."+qrDocument.getExtension();
				File file = new File(dirPath); 
				if (file.exists()) {
					return "Si";
				}
				else
				{
					return "No";
				}
	}
	
	private String getQr(String url) {
		try {
			return qRCodeService.crateQRCode(url, 200, 200);
		}
		catch (Exception e) {
			System.out.println("Exception: " +  e);
			return "";
		}
		
	}
	
	 
	public QrDocumentGeneral getQrDocumentGeneralId(String id) throws Exception{
		QrDocumentGeneral qrDocumentGeneralResponse = qrDocumentGeneralDao.getQrDocumentGeneralId(id); 
		List<QrDocument> qrDocumentosInfoResponse = qrDocumentDao.getQrDocuments(qrDocumentGeneralResponse);
		for (QrDocument qrDocument : qrDocumentosInfoResponse) {
			qrDocument.setQr("Si");
			qrDocument.setFile(existFIle(qrDocument));
			qrDocument.setUrl(getUrl(qrDocument));
		}	
		qrDocumentGeneralResponse.setQrDocumentosInfo(qrDocumentosInfoResponse);
		return qrDocumentGeneralResponse ; 
	}
	
	public ResponseDTO getValidaContrasenia(String id, String contrasenia) throws Exception{
		if(qrDocumentDao.getValidaContrasenia(id,contrasenia)) {
			return new ResponseDTO("Ok","Valido",true); 
		}
		return new ResponseDTO("Ok","Invalido",true); 
	}
	
	@Override
	public String testPath(String id) throws Exception{
		System.out.println("getDocument " + id);
		QrDocument qrDocument = new QrDocument(); 
		if(true) {
			try {
				String dirPath = id ;
				File file = new File(dirPath); 
		        byte[] fileContent = Files.readAllBytes(file.toPath());
		        return "dirPath : " + dirPath; 
			}
			catch (Exception e) {
				System.out.println("getDocument " + e.toString());
				return e.toString() ; 
			}
		}
		return "Nose ";
	}
	
}
