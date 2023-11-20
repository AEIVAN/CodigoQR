package com.deisa.file.bo;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamSource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.deisa.file.dao.DocumentDao;
import com.deisa.file.dao.EmailDao;
import com.deisa.file.dao.InformationDocumentDao;
import com.deisa.file.dao.PermissionDao;
import com.deisa.file.dao.QrDocumentDao;
import com.deisa.file.dao.QrDocumentGeneralDao;
import com.deisa.file.dto.Email;
import com.deisa.file.dto.LogRecordDTO;
import com.deisa.file.dto.Permission;
import com.deisa.file.dto.QrDocument;
import com.deisa.file.dto.QrDocumentGeneral;
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
	@Autowired
	EmailDao emailDao;
	@Autowired
	PermissionDao permissionDao;

//	String urlBase = "http://localhost:4200/" ;
// 	String dirPathBase = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\" ; 
// 	String diagonal = "\\"; 

//	String urlBase = "http://localhost:8080/";
//	String dirPathBase = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\";
//	String diagonal = "\\";

	String urlBase = "http://reportesdeisa.ddns.net:8081/";
	String dirPathBase = "/home/server/Documents/Deisa/";
	String diagonal = "/";

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
						+ diagonal + qrDocument.getNumero() + diagonal + qrDocument.getTipo() + diagonal
						+ qrDocument.getNombre() + "." + qrDocument.getExtension();
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
				if (qrDocumentDao.insertQrDocument(qrDocument)) {
					LogRecordDTO logRecordDTO = new LogRecordDTO(qrDocument.getUsuario(), "Generate QR",
							qrDocument.getNumero(), qrDocument.getId(), qrDocument.toString());
					qrDocumentDao.insertLog(logRecordDTO);
				} else {
					return null;
				}
			} else {
				if (qrDocumentDao.updateQrDocument(qrDocument)) {
					LogRecordDTO logRecordDTO = new LogRecordDTO(qrDocument.getUsuario(), "Update QR",
							qrDocument.getNumero(), qrDocument.getId(), qrDocument.toString());
					qrDocumentDao.insertLog(logRecordDTO);
				} else {
					return null;

				}
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

	public Object saveQrDocumentId(MultipartFile file, String id, String extension, String user) throws Exception {
		try {
			System.out.println("cargaDocumento");
			String accion = "Update File" ; 
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
						qrDocumentResponse.getNumero() + diagonal + qrDocumentResponse.getTipo() + diagonal;
				String filePath = dirPath + localFileName;
				System.out.println("filePath " + filePath);
				File localFile = new File(filePath);
				File imagePath = new File(dirPath);
				if (!imagePath.exists()) {
					imagePath.mkdirs();
					accion = "Save Filee";
				}
				file.transferTo(localFile);
				LogRecordDTO logRecordDTO = new LogRecordDTO(user, accion, qrDocumentResponse.getNumero(),
						qrDocumentResponse.getId(), qrDocumentResponse.toString());
				qrDocumentDao.insertLog(logRecordDTO);
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
			String accion = "Update File" ; 
			String fileName = file.getOriginalFilename();
			String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			String localFileName = qrDocument.getNombre() + fileSuffix;
			String dirPath = dirPathBase + qrDocument.getDepartamento() + diagonal + qrDocument.getDocumento()
					+ diagonal +
					// String dirPath =
					// "C:\\Users\\Administrator\\Documents\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
					qrDocument.getNumero() + diagonal + qrDocument.getTipo() + diagonal;
			String filePath = dirPath + localFileName;
			System.out.println("filePath " + filePath);
			File localFile = new File(filePath);
			File imagePath = new File(dirPath);
			if (!imagePath.exists()) {
				imagePath.mkdirs();
				accion = "Save Filee";
			}
			file.transferTo(localFile);
			LogRecordDTO logRecordDTO = new LogRecordDTO(qrDocument.getUsuario(), accion, qrDocument.getNumero(),
					qrDocument.getId(), qrDocument.toString());
			qrDocumentDao.insertLog(logRecordDTO);

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
				if (qrDocumentDao.insertQrDocument(qrDocument)) {
					logRecordDTO = new LogRecordDTO(qrDocument.getUsuario(), "Generate QR", qrDocument.getNumero(),
							qrDocument.getId(), qrDocument.toString());
					qrDocumentDao.insertLog(logRecordDTO);
				} else {
					return null;
				}
			} else {
				if (qrDocumentDao.updateQrDocument(qrDocument)) {
					logRecordDTO = new LogRecordDTO(qrDocument.getUsuario(), "Update QR", qrDocument.getNumero(),
							qrDocument.getId(), qrDocument.toString());
					qrDocumentDao.insertLog(logRecordDTO);
				} else {
					return null;

				}
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

	public Object getValidaContrasenia(String id, String contrasenia, String user, String order) throws Exception {
		try {
			LogRecordDTO logRecordDTO;
			String cuenta = "";
			QrDocument qrDocument = qrDocumentDao.getQrDocumentById(id);
			if (qrDocumentDao.getValidaContrasenia(id, contrasenia)) {
				logRecordDTO = new LogRecordDTO(user, "Valid Password Cliente", order, id,
						"id = " + id + " contrasenia = " + contrasenia + " user = " + user + " order = " + order);
				qrDocumentDao.insertLog(logRecordDTO);
				if (qrDocument.getDescargar() > 0) {
					qrDocument.setDescargar(qrDocument.getDescargar() - 1);
					if (qrDocumentDao.updateDownloads(qrDocument)) {
						logRecordDTO = new LogRecordDTO(user, "Consume Descarga", order, id,
								"id = " + id + " descargas = " + qrDocument.getDescargar() + " user = " + user
										+ " order = " + order);
						qrDocumentDao.insertLog(logRecordDTO);
						return "Valido";
					} else {
						logRecordDTO = new LogRecordDTO(user, "No Consume Descarga", order, id,
								"id = " + id + " descargas = " + qrDocument.getDescargar() + " user = " + user
										+ " order = " + order);
						qrDocumentDao.insertLog(logRecordDTO);
						return "Error";
					}
				} else {
					logRecordDTO = new LogRecordDTO(user, "No Descargas", order, id, "id = " + id + " descargas = "
							+ qrDocument.getDescargar() + " user = " + user + " order = " + order);
					qrDocumentDao.insertLog(logRecordDTO);
					return "Descargas";
				}
			}
			cuenta = qrDocumentDao.getValidaContraseniaPermiso(contrasenia, qrDocument);
			if (cuenta.trim() != "") {
				logRecordDTO = new LogRecordDTO(cuenta, "Valid Password Deisa", order, id,
						"id = " + id + " contrasenia = " + contrasenia + " user = " + user.replace("Cliente", cuenta)
								+ "order = " + order);
				qrDocumentDao.insertLog(logRecordDTO);
				return "Valido";
			}
			logRecordDTO = new LogRecordDTO(user, "Invalid Password", order, id,
					"id = " + id + " contrasenia = " + contrasenia + " user = " + user + "order = " + order);
			qrDocumentDao.insertLog(logRecordDTO);
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

	private String getUrl(String idQr) {
		return urlBase + "deisa/?id=" + idQr;
	}

	private String existFIle(QrDocument qrDocument) {
		String dirPath = dirPathBase + qrDocument.getDepartamento() + diagonal + qrDocument.getDocumento() + diagonal
				+ qrDocument.getNumero() + diagonal + qrDocument.getTipo() + diagonal + qrDocument.getNombre() + "."
				+ qrDocument.getExtension();
		File file = new File(dirPath);
		if (file.exists()) {
			return "Si";
		} else {
			return "No";
		}
	}

	public Object sendEmail(Email email) throws Exception {
		try {
			if (qrDocumentDao.changePassword(email)) {
				LogRecordDTO logRecordDTO = new LogRecordDTO(email.getUsuario(), "Change Password", email.getOrden(),
						email.getIdQr(), email.toString());
				qrDocumentDao.insertLog(logRecordDTO);
				String[] emails = { email.getCorreo() };
				QrDocument qrDocument = qrDocumentDao.getQrDocumentById(email.getIdQr());
				String msg = emailDao.emailPassword(emails, email.getOrden(), qrDocument.getNombre(),
						getUrl(email.getIdQr()), email.getContrasenia(), qrDocument.getDescargar());
				logRecordDTO = new LogRecordDTO(email.getUsuario(), "Send Email", email.getOrden(), email.getIdQr(),
						"[" + email.getCorreo() + "] " + msg);
				qrDocumentDao.insertLog(logRecordDTO);
				return "Se envio el correo";
			}
			return false;
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getValidaContrasenia ***  Excepcion : " + e));
		}
	}

	public Object addDownloads(QrDocument qrDocument) throws Exception {
		try {
			if (qrDocumentDao.updateDownloads(qrDocument)) {
				LogRecordDTO logRecordDTO  = new LogRecordDTO(qrDocument.getUsuario(), "Send Email", qrDocument.getNumero(), qrDocument.getId(),
						"[" + qrDocument.getDescargar() + "] " + qrDocument.toString());
				qrDocumentDao.insertLog(logRecordDTO);
				return "Se actualizo el numero de  descargas";
			} else {
				return "No se pudo actualizar el numero de  descargas";
			}
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getValidaContrasenia ***  Excepcion : " + e));
		}
	}

	public Object getPermissions() throws Exception {
		try {
			return permissionDao.getPermissions();
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getValidaContrasenia ***  Excepcion : " + e));
		}
	}

	public Object getHistorical(String idQr, String numero) throws Exception {
		try {

			List<QrDocument> listQrDocument = qrDocumentGeneralDao.getHistorical(idQr, numero);

			for (QrDocument qrDocument : listQrDocument) {
				qrDocument.setUrl(getUrl(qrDocument));
				qrDocument.setFile(existFIle(qrDocument));
				List<LogRecordDTO> listLogRecordDTO = qrDocumentDao.getHistoricalLog(qrDocument.getId());
				qrDocument.setLogRecordDTO(transformHistory(listLogRecordDTO, qrDocument.getContrasenia()));
				qrDocument.setContrasenia("****");
			}

			return listQrDocument;
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getValidaContrasenia ***  Excepcion : " + e));
		}
	}

	public Object getHistoricalPermission(String idQr, String numero) throws Exception {
		try {

			List<QrDocument> listQrDocument = qrDocumentGeneralDao.getHistorical(idQr, numero);

			for (QrDocument qrDocument : listQrDocument) {
				qrDocument.setUrl(getUrl(qrDocument));
				qrDocument.setFile(existFIle(qrDocument));
				qrDocument.setLogRecordDTO(qrDocumentDao.getHistoricalLog(qrDocument.getId()));
			}

			return listQrDocument;
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getValidaContrasenia ***  Excepcion : " + e));
		}
	}

	public List<LogRecordDTO> transformHistory (List<LogRecordDTO> listLogRecordDTO, String password){
		
		for (LogRecordDTO logRecordDTO :listLogRecordDTO) {
			if(logRecordDTO.getAccion().contains("Generate QR")) {
				logRecordDTO.setAccion("Se genero QR");
			}else if(logRecordDTO.getAccion().contains("Save File")) {
				logRecordDTO.setAccion("Se guardo documento");
			}else if(logRecordDTO.getAccion().contains("Update File")) {
				logRecordDTO.setAccion("Se actualizo documento");
			}else if(logRecordDTO.getAccion().contains("Update QR")) {
				logRecordDTO.setAccion("Se actualizo QR");
			}else if(logRecordDTO.getAccion().contains("Change Password")) {
				logRecordDTO.setAccion("Se cambio la contraseña");
			}else if(logRecordDTO.getAccion().contains("Send Email")) {
				logRecordDTO.setAccion("Se envio correo");
				logRecordDTO.setRequest(logRecordDTO.getRequest().replace(password, "****"));
			}else if(logRecordDTO.getAccion().contains("Valid Password Customer")) {
				logRecordDTO.setAccion("Contraseña del cliente valida");
			}else if(logRecordDTO.getAccion().contains("Valid Password Deisa")) {
				logRecordDTO.setAccion("Contraseña de Deisa valida");
			}else if(logRecordDTO.getAccion().contains("Invalid Password")) {
				logRecordDTO.setAccion("Contraseña no valida");
			}else if(logRecordDTO.getAccion().contains("Consume Downloads")) {
				logRecordDTO.setAccion("Se consumio una descarga");
			}else if(logRecordDTO.getAccion().contains("No Consume Downloads")) {
				logRecordDTO.setAccion("No se consumio descarga");
			}else if(logRecordDTO.getAccion().contains("Add Downloads")) {
				logRecordDTO.setAccion("Se agrego descargas");
			}else if(logRecordDTO.getAccion().contains("No Downloads")) {
				logRecordDTO.setAccion("Sin descargas");
			}else {
				logRecordDTO.setAccion("Accion no identificada");
			}
		}
		return listLogRecordDTO ; 
	}

	public Object sendWeeklyReport() throws Exception {
		try {
			String[] emails = { "ae_ivan@hotmail.com", "direccion@deisacv.com", "sistemadeisa@deisacv.com" };
			List<String[]> rows = qrDocumentGeneralDao.getWeeklyReport();

			Date fechaActual = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaActual);
			calendar.add(Calendar.DAY_OF_YEAR, -8); // Restar 8 días

			Date fechaRestada = calendar.getTime();
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yy");
			String fechaActualFormateada = formato.format(fechaActual);
			String fechaRestadaFormateada = formato.format(fechaRestada);

			String msg = emailDao.emailWeeklyReport(emails, rows, fechaActualFormateada, fechaRestadaFormateada);
			return "Se envio el correo";
		} catch (Exception e) {
			throw (new Exception(
					"*** Class " + this.getClass().getName() + " Method  getValidaContrasenia ***  Excepcion : " + e));
		}
	}

}
