package com.deisa.file.service.imp;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.deisa.file.bo.DocumentServiceBo;
import com.deisa.file.dao.DocumentDao;
import com.deisa.file.dao.InformationDocumentDao;
import com.deisa.file.dao.QrDocumentGeneralDao;
import com.deisa.file.dao.QrDocumentDao;
import com.deisa.file.dao.imp.InformationDocumentDaoImp;
import com.deisa.file.dto.Documento;
import com.deisa.file.dto.Email;
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
	DocumentServiceBo documentServiceBo;

//	String urlBase = "http://localhost:4200/" ;
// 	String dirPathBase = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\" ; 
// 	String diagonal = "\\"; 

	String urlBase = "http://localhost:8080/";
	String dirPathBase = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\";
	String diagonal = "\\";

//	String urlBase = "http://reportesdeisa.ddns.net:8081/";
//	String dirPathBase = "/home/server/Documents/Deisa/";
//	String diagonal = "/";

//	String urlBase = "http://52.23.232.199:8080/" ;
//    String dirPathBase = "C:\\Users\\Administrator\\Documents\\Deisa\\" ;

//	@Override
//	public Documento getQr(Documento documento) throws Exception {
//		System.out.println("getQr");
//		documento.setId(documentDao.exisDocumento(documento));
//		if (documento.getId() == "") {
//			int i = 0;
//			// Insert
//			while (true) {
//				documento.setId(generateAlphaNumericString.getRandomString(10));
//				if (!documentDao.existId(documento.getId()))
//					break;
//				if (i == 5)
//					return null;
//				i++;
//			}
//			if (!documentDao.insertDocumento(documento))
//				return null;
//		}
////		else {
////			if(!documentDao.updateDocumento(documento)) return null ;  
////		}
//		// String cadenaQr = "http://192.168.0.16:8080/deisa/?id=" + documento.getId();
//		// String cadenaQr = "http://192.168.2.114:8080/deisa/?id=" + documento.getId();
//		String cadenaQr = "http://52.23.232.199:8080/deisa/?id=" + documento.getId();
//		System.out.println("cadenaQr " + cadenaQr);
//		documento.setQr(qRCodeService.crateQRCode(cadenaQr, 200, 200));
//		System.out.println("Documento " + documento);
//		return documento;
//	}
//
//	public String cargaDocumento(MultipartFile file, String id, String extension) throws Exception {
//		System.out.println("cargaDocumento");
//		List<Documento> documentos = documentDao.getDocumento(id);
//		System.out.println("documentos " + documentos.get(0));
//		System.out.println("documentos.size() " + documentos.size());
//		System.out.println("extension " + extension);
//		System.out.println("documentos.get(0).getExtension() " + documentos.get(0).getExtension());
//		System.out.println("documentos.size()!=0  " + (documentos.size() != 0));
//		System.out.println("extension == documentos.get(0).getExtension() "
//				+ extension.trim().equals(documentos.get(0).getExtension().trim()));
//
//		if (documentos.size() != 0 && extension.trim().equals(documentos.get(0).getExtension().trim())) {
//			Documento documento = documentos.get(0);
//			System.out.println("fileName " + file.getOriginalFilename());
//			String fileName = file.getOriginalFilename();
//			String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
//			String localFileName = documento.getNombre() + fileSuffix;
//			String dirPath = "C:\\Users\\ae_iv\\Desktop\\prueba\\Deisa\\" + documento.getDepartamento() + diagonal
//					+ documento.getDocumento() + diagonal +
//					// String dirPath =
//					// "C:\\Users\\Administrator\\Documents\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
//					documento.getNumero() + diagonal;
//			String filePath = dirPath + localFileName;
//			System.out.println("filePath " + filePath);
//			File localFile = new File(filePath);
//			File imagePath = new File(dirPath);
//			if (!imagePath.exists()) {
//				imagePath.mkdirs();
//			}
//			file.transferTo(localFile);
//			return "OK";
//		}
//		return "Id no valido o se esperaba otro tipo de formato";
//	}
//
//	@Override
//	public InformationDocuments getInfotmation(String id) throws Exception {
//		InformationDocuments informationDocuments = informationDocumentDao.getInformationDocument(id);
//		return informationDocuments;
//	}
//
//	@Override
//	public byte[] getDocument(String id) throws Exception {
//		System.out.println("getDocument " + id);
//		List<Documento> documentos = documentDao.getDocumento(id);
//		if (documentos.size() != 0) {
//			Documento documento = documentos.get(0);
//			String dirPath = dirPathBase + documento.getDepartamento() + diagonal + documento.getDocumento() + diagonal
//					+
//					// String dirPath =
//					// "C:\\Users\\JF-Sistemas\\Desktop\\prueba_qr\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
//					// String dirPath =
//					// "C:\\Users\\Administrator\\Documents\\Deisa\\"+documento.getDepartamento()+diagonal+documento.getDocumento()+diagonal+
//					documento.getNumero() + diagonal + documento.getNombre() + "." + documento.getExtension();
//			System.out.println("dirPath " + dirPath);
//			File file = new File(dirPath);
//			byte[] fileContent = Files.readAllBytes(file.toPath());
//			// System.out.println("fileContent " + fileContent);
//			return fileContent;
//		}
//		return null;
//	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public ResponseDTO test(String test) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.test(test));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}

	@Override
	public ResponseDTO testPath(String testPath) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.testPath(testPath));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(true);
		}
		return responseDTO;
	}

	public ResponseDTO getQrDocumentGeneral(QrDocumentGeneral qrDocumentGeneral) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.getQrDocumentGeneral(qrDocumentGeneral));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(true);
		}
		return responseDTO;
	}

	public ResponseDTO getQrDocument(QrDocument qrDocument) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.getQrDocument(qrDocument));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(true);
		}
		return responseDTO;
	}

	@Override
	public byte[] downLoadDocument(String id) {
		byte[] file = null;
		try {
			file = documentServiceBo.downLoadDocument(id);
		} catch (Exception e) {
			file = null;
		}
		return file;
	}

	public ResponseDTO postQrDocument(QrDocument qrDocument) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.postQrDocument(qrDocument));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}

	public ResponseDTO saveQrDocumentId(MultipartFile file, String id, String extension, String user) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.saveQrDocumentId(file, id, extension, user));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}

	public ResponseDTO saveQrDocument(MultipartFile file, QrDocument qrDocument) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.saveQrDocument(file, qrDocument));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}

	public ResponseDTO getCodeQr(String id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.getCodeQr(id));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}

	public ResponseDTO getQrDocumentGeneralId(String id) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.getQrDocumentGeneralId(id));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}

	public ResponseDTO getValidaContrasenia(String id, String contrasenia, String user, String order) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.getValidaContrasenia(id, contrasenia,user, order));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}

	@Override
	public ResponseDTO sendEmail(Email email) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.sendEmail(email));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}
	
	public ResponseDTO addDownloads (QrDocument qrDocument) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.addDownloads(qrDocument));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}
	
	public ResponseDTO getPermissions () {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.getPermissions());
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}
	
	public ResponseDTO getHistorical (String idQr, String numero) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.getHistorical(idQr, numero));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}
	
	public ResponseDTO getHistoricalPermission (String idQr, String numero) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.getHistoricalPermission(idQr, numero));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}
	
	
	
//	 @Scheduled(cron = "0 * * * * *") // Se ejecutará cada  minuto
//	@Scheduled(cron = "0 0 * * 1") // Se ejecutará todos los lunes a las 12:00 AM (hora del sistema)
//	@Scheduled(cron = "0 15 7 * * 1")
    public void executeSendWeeklyReport() {
		System.out.println("Se ejecuto la tarea");
		sendWeeklyReport();
    }
	
	
	public ResponseDTO sendWeeklyReport () {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(documentServiceBo.sendWeeklyReport());
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}

}
