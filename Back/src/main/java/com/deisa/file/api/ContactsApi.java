package com.deisa.file.api;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

import com.deisa.file.config.EmailSender;
import com.deisa.file.dto.Contact;
import com.deisa.file.dto.Documento;
import com.deisa.file.dto.Email;
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
	 * Sistema Deisa : Consulta informacion al seleccionar una orden
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
	 * Sistema Deisa : Guarda o modifica infromacion
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
			@RequestPart("user") String user, @RequestPart("extension") String extension,
			@RequestPart("tipo") String tipo) {
		System.out.println("saveQrDocumentId");
		return documentService.saveQrDocumentId(file, id, extension, user);
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
			@RequestParam("contrasenia") String contrasenia, @RequestParam("usuario") String user,
			@RequestParam("numero") String order) {
		System.out.println("getValidaContrasenia");
		return documentService.getValidaContrasenia(id, contrasenia, user, order);
	}

	/*
	 * Cambiar contraseña
	 */
	@ApiOperation("Envia correo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/sendEmail")
	public ResponseDTO sendEmail(@RequestBody final Email email) {
		System.out.println("sendEmail");
		return documentService.sendEmail(email);
	}
	
	/*
	 * Cambiar contraseña
	 */
	@ApiOperation("Aumenta descargas ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/addDownloads")
	public ResponseDTO addDownloads(@RequestBody final QrDocument qrDocument) {
		System.out.println("addDownloads");
		return documentService.addDownloads(qrDocument);
	}
	
	/*
	 * Cambiar contraseña
	 */
	@ApiOperation("Obtiene permisos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/getPermissions")
	public ResponseDTO getPermissions() {
		System.out.println("getPermissions");
		return documentService.getPermissions();
	}
	
	/*
	 * Cambiar contraseña
	 */
	@ApiOperation("Obtiene el historial de la orden")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/getHistorical")
	public ResponseDTO getHistorical(@RequestParam("idQr") String idQr, @RequestParam("numero") String numero) {
		System.out.println("getHistorical");
		return documentService.getHistorical(idQr, numero);
	}
	
	@ApiOperation("Obtiene el historial de la orden")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping(value = "/getHistoricalPermission")
	public ResponseDTO getHistoricalPermission(@RequestParam("idQr") String idQr, @RequestParam("numero") String numero) {
		System.out.println("getHistorical");
		return documentService.getHistoricalPermission(idQr, numero);
	}
	
	@ApiOperation("Manda el reporte semanal")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping(value = "/sendWeeklyReport")
	public ResponseDTO sendWeeklyReport() {
		System.out.println("sendWeeklyReport");
		return documentService.sendWeeklyReport();
	}
	
	
	
//	@PostMapping("/send-email")
//    public String sendEmail() {
//        try {
//            // Obtener los datos del correo electrónico desde el objeto EmailRequest
////            String to = "ae_ivan@hotmail.com";
////            String subject = "Asunto de prueba";
////            String text = "Texto de prueba";
////            EmailSender emailSender = new EmailSender(); 
////            // Enviar el correo electrónico
////            emailSender.sendEmail(to, subject, text);
//
//            return "Correo electrónico enviado correctamente";
//        } catch (Exception e) {
//            return "Error al enviar el correo electrónico: " + e.getMessage();
//        }
//    }
	
	
}