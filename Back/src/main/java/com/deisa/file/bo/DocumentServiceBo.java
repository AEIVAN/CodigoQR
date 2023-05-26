package com.deisa.file.bo;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.deisa.file.dao.DocumentDao;
import com.deisa.file.dao.InformationDocumentDao;
import com.deisa.file.dao.QrDocumentDao;
import com.deisa.file.dao.QrDocumentGeneralDao;
import com.deisa.file.dto.QrDocument;
import com.deisa.file.dto.QrDocumentGeneral;
import com.deisa.file.dto.ResponseDTO;
import com.deisa.file.service.QRCodeService;
import com.deisa.file.utils.GenerateAlphaNumericString;

@Service
public class DocumentServiceBo {
	@Autowired
	QRCodeService qRCodeService;
	@Autowired
	GenerateAlphaNumericString generateAlphaNumericString;
	@Autowired
	DocumentDao documentDao;
	@Autowired
	QrDocumentGeneralDao qrDocumentGeneralDao;
	@Autowired
	QrDocumentDao qrDocumentDao;
	@Autowired
	InformationDocumentDao informationDocumentDao;
	@Autowired
	DocumentServiceBo documentServiceBo;

//	String urlBase = "http://localhost:4200/" ;
// 	String dirPathBase = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\" ; 
// 	String diagonal = "\\"; 

	String urlBase = "http://localhost:8080/" ;
 	String dirPathBase = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\" ; 
 	String diagonal = "\\"; 

//	String urlBase = "http://reportesdeisa.ddns.net:8081/";
//	String dirPathBase = "/home/server/Documents/Deisa/";
//	String diagonal = "/";

//	String urlBase = "http://52.23.232.199:8080/" ;
//    String dirPathBase = "C:\\Users\\Administrator\\Documents\\Deisa\\" ;

	public String test(String test) throws Exception {
		System.out.println("test " + test);
		try {
			return test;
		} catch (Exception e) {
			throw (new Exception("*** Class " + this.getClass().getName() + " Method test ***  Excepcion : " + e));
		}
	}

	public Object testPath(String testPath) throws Exception {
		System.out.println("getDocument " + testPath);
		try {
			String dirPath = testPath;
			File file = new File(dirPath);
			byte[] fileContent = Files.readAllBytes(file.toPath());
			return "dirPath : " + dirPath;
		} catch (Exception e) {
			throw (new Exception("*** Class " + this.getClass().getName() + " Method  testPath ***  Excepcion : " + e));
		}
	}

	public Object getQrDocumentGeneral(QrDocumentGeneral qrDocumentGeneral) throws Exception {
		try {
			QrDocumentGeneral qrDocumentGeneralResponse = qrDocumentGeneralDao.getQrDocumentGeneral(qrDocumentGeneral);
			List<QrDocument> qrDocumentosInfoResponse = qrDocumentDao.getQrDocuments(qrDocumentGeneralResponse);
			for (QrDocument qrDocument : qrDocumentosInfoResponse) {
				qrDocument.setQr("Si");
				qrDocument.setFile(existFIle(qrDocument));
				qrDocument.setUrl(getUrl(qrDocument));
			}
			qrDocumentGeneralResponse.setQrDocumentosInfo(qrDocumentosInfoResponse);
			return qrDocumentGeneralResponse;
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getQrDocumentGeneral ***  Excepcion : " + e));
		}

	}

	public Object getQrDocument(QrDocument qrDocument) throws Exception {
		try {
			QrDocument qrDocumentResponse = qrDocumentDao.getQrDocument(qrDocument);
			if (qrDocumentResponse.getId().trim() != "") {
				qrDocumentResponse.setUrl(getUrl(qrDocumentResponse));
				qrDocumentResponse.setQr(getQr(qrDocumentResponse.getUrl()));
				qrDocumentResponse.setFile(existFIle(qrDocumentResponse));
				return qrDocumentResponse;
			}
			return qrDocumentResponse;
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getQrDocument ***  Excepcion : " + e));
		}

	}

	public byte[] downLoadDocument(String id) throws Exception {
		QrDocument qrDocument = new QrDocument();
		try {
			qrDocument = qrDocumentDao.getQrDocumentById(id);
			if (qrDocument.getId().trim() != "") {

				qrDocument = qrDocumentDao.downLoadDocument(id);
				String dirPath = dirPathBase + qrDocument.getDepartamento() + diagonal + qrDocument.getDocumento()
						+ diagonal + qrDocument.getNumero() + diagonal + qrDocument.getNombre() + "."
						+ qrDocument.getExtension();
				System.out.println("dirPath " + dirPath);
				File file = new File(dirPath);
				byte[] fileContent = Files.readAllBytes(file.toPath());
				return fileContent;

			}
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  downLoadDocument ***  Excepcion : " + e));
		}
		return null;
	}

	public Object postQrDocument(QrDocument qrDocument) throws Exception {
		try {
			qrDocument.setId(qrDocumentDao.exisDocumento(qrDocument));
			if (qrDocument.getId() == "") {
				int i = 0;
				while (true) {
					qrDocument.setId(generateAlphaNumericString.getRandomString(10));
					if (!documentDao.existId(qrDocument.getId()))
						break;
					if (i == 5)
						return null;
					i++;
				}
				if (!qrDocumentDao.insertQrDocument(qrDocument))
					return null;
			} else {
				if (!qrDocumentDao.updateQrDocument(qrDocument))
					return null;
			}

			qrDocument.setUrl(urlBase + "deisa/?id=" + qrDocument.getId());
			qrDocument.setQr(qRCodeService.crateQRCode(qrDocument.getUrl(), 200, 200));
			System.out.println("Documento " + qrDocument);
			return qrDocument;
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  postQrDocument ***  Excepcion : " + e));
		}
	}

	public Object saveQrDocumentId(MultipartFile file, String id, String extension) throws Exception {
		try {
			System.out.println("cargaDocumento");
			QrDocument qrDocumentResponse = qrDocumentDao.getQrDocumentById(id);
			if (qrDocumentResponse.getId() != "" && extension.trim().equals(qrDocumentResponse.getExtension().trim())) {
				System.out.println("fileName " + file.getOriginalFilename());
				String fileName = file.getOriginalFilename();
				String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				String localFileName = qrDocumentResponse.getNombre() + fileSuffix;
				String dirPath = dirPathBase + qrDocumentResponse.getDepartamento() + diagonal
						+ qrDocumentResponse.getDocumento() + diagonal +
						// String dirPath =
						// "C:\\Users\\Administrator\\Documents\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
						qrDocumentResponse.getNumero() + diagonal + qrDocumentResponse.getTipo() + diagonal ;
				String filePath = dirPath + localFileName;
				System.out.println("filePath " + filePath);
				File localFile = new File(filePath);
				File imagePath = new File(dirPath);
				if (!imagePath.exists()) {
					imagePath.mkdirs();
				}
				file.transferTo(localFile);
				return "OK";
			}
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  saveQrDocumentId ***  Excepcion : " + e));
		}
		return "Id no valido o se esperaba otro tipo de formato";

	}

	public Object saveQrDocument(MultipartFile file, QrDocument qrDocument) throws Exception {
		try {
			System.out.println("cargaDocumento");
			String fileName = file.getOriginalFilename();
			String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			String localFileName = qrDocument.getNombre() + fileSuffix;
			String dirPath = dirPathBase + qrDocument.getDepartamento() + diagonal + qrDocument.getDocumento()
					+ diagonal +
					// String dirPath =
					// "C:\\Users\\Administrator\\Documents\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
					qrDocument.getNumero() + diagonal  + qrDocument.getTipo() + diagonal ;
			String filePath = dirPath + localFileName;
			System.out.println("filePath " + filePath);
			File localFile = new File(filePath);
			File imagePath = new File(dirPath);
			if (!imagePath.exists()) {
				imagePath.mkdirs();
			}
			file.transferTo(localFile);

			qrDocument.setId(qrDocumentDao.exisDocumento(qrDocument));
			if (qrDocument.getId() == "") {
				int i = 0;
				while (true) {
					qrDocument.setId(generateAlphaNumericString.getRandomString(10));
					if (!documentDao.existId(qrDocument.getId()))
						break;
					if (i == 5)
						return null;
					i++;
				}
				if (!qrDocumentDao.insertQrDocument(qrDocument))
					return null;
			} else {
				if (!qrDocumentDao.updateQrDocument(qrDocument))
					return null;
			}

			qrDocument.setUrl(urlBase + "deisa/?id=" + qrDocument.getId());
			qrDocument.setQr(qRCodeService.crateQRCode(qrDocument.getUrl(), 200, 200));
			qrDocument.setFile("Si");
			return qrDocument;
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  saveQrDocument ***  Excepcion : " + e));
		}
	}

	public Object getCodeQr(String id) throws Exception {
		try {
			QrDocument qrDocument = new QrDocument();
			qrDocument = qrDocumentDao.getQrDocumentById(id);
			qrDocument.setUrl(getUrl(qrDocument));
			return getQr(qrDocument.getUrl());
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getCodeQrr ***  Excepcion : " + e));
		}
	}

	public Object getQrDocumentGeneralId(String id) throws Exception {
		try {
			QrDocumentGeneral qrDocumentGeneralResponse = qrDocumentGeneralDao.getQrDocumentGeneralId(id);
			List<QrDocument> qrDocumentosInfoResponse = qrDocumentDao.getQrDocumentsById(qrDocumentGeneralResponse);
			for (QrDocument qrDocument : qrDocumentosInfoResponse) {
				qrDocument.setQr("Si");
				qrDocument.setFile(existFIle(qrDocument));
				qrDocument.setUrl(getUrl(qrDocument));
			}
			qrDocumentGeneralResponse.setQrDocumentosInfo(qrDocumentosInfoResponse);
			return qrDocumentGeneralResponse;
		} catch (Exception e) {
			throw (new Exception("*** Class " + this.getClass().getName()
					+ " Method  getQrDocumentGeneralId ***  Excepcion : " + e));
		}

	}

	public Object getValidaContrasenia(String id, String contrasenia) throws Exception {
		try {
			if (qrDocumentDao.getValidaContrasenia(id, contrasenia)) {
				return "Valido";
			}
			return "Invalido";
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getValidaContrasenia ***  Excepcion : " + e));
		}
	}

	private String getQr(String url) {
		try {
			return qRCodeService.crateQRCode(url, 200, 200);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return "";
		}
	}

	private String getUrl(QrDocument qrDocument) {
		qrDocument.setUrl(urlBase + "deisa/?id=" + qrDocument.getId());
		return urlBase + "deisa/?id=" + qrDocument.getId();
	}

	private String existFIle(QrDocument qrDocument) {
		String dirPath = dirPathBase + qrDocument.getDepartamento() + diagonal + qrDocument.getDocumento() + diagonal
				+ qrDocument.getNumero() + diagonal + qrDocument.getTipo() + diagonal + qrDocument.getNombre() + "." + qrDocument.getExtension();
		File file = new File(dirPath);
		if (file.exists()) {
			return "Si";
		} else {
			return "No";
		}
	}

}
