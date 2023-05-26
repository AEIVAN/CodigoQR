package com.deisa.file.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.deisa.file.dto.Contact;
import com.deisa.file.dto.Documento;
import com.deisa.file.dto.InformationDocuments;
import com.deisa.file.dto.QrDocument;
import com.deisa.file.dto.QrDocumentGeneral;
import com.deisa.file.dto.ResponseDTO;
import com.deisa.file.service.DocumentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = { "*", "http://localhost:4200", "http://localhost",
		"http://reportesdeisa.ddns.net/qr" }, methods = { RequestMethod.GET, RequestMethod.POST })
@Api(value = "", description = "")
@RequestMapping("/file")
public class ContactsApi {
	@Autowired
	DocumentService documentService;

//	@ApiOperation("Obtiene Qr")
//	@PostMapping(value = "/getQr")
//	public Documento getQr(@RequestBody final Documento documento) {
//		System.out.println("getQr");
//		try {
//			return documentService.getQr(documento);
//		} catch (Exception e) {
//			System.out.println("Error : " + e);
//			return null;
//		}
//	}
//
//	@ApiOperation("Carga Documento")
//	@PostMapping(value = "/setDocument")
//	public String cargaDocumento(@RequestPart("file") MultipartFile file, @RequestPart("id") String id,
//			@RequestPart("extension") String extension) {
//		System.out.println("cargaDocumento");
//		try {
//			return documentService.cargaDocumento(file, id, extension);
//		} catch (Exception e) {
//			System.out.println("Error : " + e);
//			return "Error";
//		}
//	}
//
//	@ApiOperation("Obtiene informacion")
//	@PostMapping(value = "/getInformation")
//	public InformationDocuments getInformation(@RequestParam("id") String id) {
//		System.out.println("getInformation");
//		try {
//			return documentService.getInfotmation(id);
//		} catch (Exception e) {
//			System.out.println("Error : " + e);
//			return null;
//		}
//	}
//
//	@ApiOperation("Qr Documento")
//	@GetMapping(value = "/getQrDocumento")
//	public byte[] getDocument(@RequestParam("id") String id) {
//		System.out.println("getDocument");
//		try {
//			return documentService.getDocument(id);
//		} catch (Exception e) {
//			System.out.println("Error : " + e);
//			return null;
//		}
//	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * Sirve para comprobar que este sirviendo el servicio
	 */
	@ApiOperation("Servicio de prueba")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping(value = "/test")
	public ResponseDTO test(@RequestParam("test") String test) {

		return documentService.test(test);
	}

	/*
	 * Comprueba que se tenga acceso a la ruta del servidor
	 */
	@ApiOperation("Prueba de acceso al path")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping(value = "/testPath")
	public ResponseDTO testPath(@RequestParam("testPath") String testPath) {
		System.out.println("testPath");
		return documentService.testPath(testPath);
	}

	/*
	 * Obtiene la informacion general de una orden con la informacion de sus
	 * archivos
	 */
	@ApiOperation("Obtiene datos generales de una orden")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/getQrDocumentGeneral")
	public ResponseDTO getQrDocumentGeneral(@RequestBody final QrDocumentGeneral qrDocumentGeneral) {
		return documentService.getQrDocumentGeneral(qrDocumentGeneral);
	}

	/*
	 * Obtiene la informacion de un registro
	 */
	@ApiOperation("Obtiene informacion de un codigo QR")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/getQrDocument")
	public ResponseDTO getQrDocument(@RequestBody final QrDocument qrDocument) {
		System.out.println("getQrDocument");
		return documentService.getQrDocument(qrDocument);
	}

	/*
	 * Descarga un documento
	 */
	@ApiOperation("Descarga un documento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping(value = "/downLoadDocument")
	public byte[] downLoadDocument(@RequestParam("id") String id) {
		System.out.println("downLoadDocument");
		return documentService.downLoadDocument(id);
	}

	/*
	 * Crea un registro
	 */
	@ApiOperation("Crea un registro QR")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/postQrDocument")
	public ResponseDTO postQrDocument(@RequestBody final QrDocument qrDocument) {
		System.out.println("postQrDocument");
		return documentService.postQrDocument(qrDocument);
	}

	/*
	 * 
	 */
	@ApiOperation("Guarda un documento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/saveQrDocumentId")
	public ResponseDTO saveQrDocumentId(@RequestPart("file") MultipartFile file, @RequestPart("id") String id,
			@RequestPart("extension") String extension, @RequestPart("tipo") String tipo) {
		System.out.println("saveQrDocumentId");
		return documentService.saveQrDocumentId(file, id, extension);
	}

	/*
	 * 
	 */
	@ApiOperation("Guarda un documento")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/saveQrDocument")
	public ResponseDTO saveQrDocument(@RequestPart("file") MultipartFile file,
			@RequestPart("qrDocument") QrDocument qrDocument) {
		return documentService.saveQrDocument(file, qrDocument);
	}

	/*
	 * 
	 */
	@ApiOperation("Obtiene un codigo QR")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/getCodeQr")
	public ResponseDTO getQr(@RequestBody QrDocument qrDocument) {
		System.out.println("getCodeQr");
		return documentService.getCodeQr(qrDocument.getId());
	}

	/*
	 * 
	 */
	@ApiOperation("Obtiene datos generales de un id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/getQrDocumentGeneralId")
	public ResponseDTO getQrDocumentGeneralId(String id) {
		System.out.println("getQrDocumentGeneralId");
		return documentService.getQrDocumentGeneralId(id);
	}

	/*
	 * 
	 */
	@ApiOperation("Valida contraseña")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/getValidaContrasenia")
	public ResponseDTO getQrDocumentGeneralId(@RequestParam("id") String id,
			@RequestParam("contrasenia") String contrasenia) {
		System.out.println("getValidaContrasenia");
		return documentService.getValidaContrasenia(id, contrasenia);
	}

}