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

@RestController
@CrossOrigin(origins = {"*","http://localhost:4200","http://localhost","http://reportesdeisa.ddns.net/qr"}, methods= {RequestMethod.GET,RequestMethod.POST})
@Api(value = "", description = "")
@RequestMapping("/file")
public class ContactsApi {
	@Autowired
	DocumentService documentService;
	
	@ApiOperation("Obtiene Qr")
	@PostMapping(value = "/getQr")
    public Documento getQr(@RequestBody final Documento documento){
		System.out.println("getQr");
		try {
			return documentService.getQr(documento) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null ; 
		}
    }
	
	@ApiOperation("Carga Documento")
	@PostMapping(value = "/setDocument")
    public String cargaDocumento(@RequestPart("file") MultipartFile file, @RequestPart("id") String id,  @RequestPart("extension") String extension){
		System.out.println("cargaDocumento");
		try {
			return documentService.cargaDocumento(file,id,extension) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return "Error" ; 
		}
    }
	
	@ApiOperation("Obtiene informacion")
	@PostMapping(value = "/getInformation")
    public InformationDocuments getInformation(@RequestParam("id") String id){
		System.out.println("getInformation");
		try {
			return documentService.getInfotmation(id) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null ; 
		}
    }
	

	@ApiOperation("Qr Documento")
	@GetMapping(value = "/getQrDocumento")
    public byte[] getDocument(@RequestParam("id") String id){
		System.out.println("getDocument");
		try {
			return documentService.getDocument(id) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null ; 
		}
    }
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Sirve para comprobar que este sirviendo el servicio
	 */
	@GetMapping(value = "/test")
    public String test(@RequestParam("test") String test){
		System.out.println("test");
        return "msg test " + test;
    }
	/*
	 * Comprueba que se tenga acceso a la ruta del servidor
	 */
	@GetMapping(value = "/testPath")
    public String testPath(@RequestParam("testPath") String testPath){
		System.out.println("testPath");
		try {
			return documentService.testPath(testPath) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null ; 
		}
    }
	/*
	 * Obtiene la informacion general de una orden con la informacion de sus archivos
	 */
	@ApiOperation("getQrDocumentGeneral")
	@PostMapping(value = "/getQrDocumentGeneral")
	public QrDocumentGeneral getQrDocumentGeneral (@RequestBody final QrDocumentGeneral qrDocumentGeneral){
		System.out.println("getQrDocumentGeneral");
		try {
			return documentService.getQrDocumentGeneral(qrDocumentGeneral) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null ; 
		}
    }
	
	/*
	 * Obtiene la informacion de un registro
	 */
	@ApiOperation("getQrDocument")
	@PostMapping(value = "/getQrDocument")
	public QrDocument getQrDocument (@RequestBody final QrDocument qrDocument){
		System.out.println("getQrDocument");
		try {
			return documentService.getQrDocument(qrDocument) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null ; 
		}
    }
	
	/*
	 * Descarga un documento
	 */
	@ApiOperation("downLoadDocument")
	@GetMapping(value = "/downLoadDocument")
    public byte[] downLoadDocument(@RequestParam("id") String id){
		System.out.println("downLoadDocument");
		try {
			return documentService.downLoadDocument(id) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null ; 
		}
    }
	
	/*
	 * Crea un registro 
	 */
	@ApiOperation("postQrDocument")
	@PostMapping(value = "/postQrDocument")
	public QrDocument postQrDocument(@RequestBody final QrDocument qrDocument){
		System.out.println("postQrDocument");
		try {
			return documentService.postQrDocument(qrDocument) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null ; 
		}
    }
	
	/*
	 * 
	 */
	@ApiOperation("saveQrDocumentId")
	@PostMapping(value = "/saveQrDocumentId")
    public String saveQrDocumentId(@RequestPart("file") MultipartFile file, @RequestPart("id") String id,  @RequestPart("extension") String extension){
		System.out.println("saveQrDocumentId");
		try {
			return documentService.saveQrDocumentId(file,id,extension) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return "Error" ; 
		}
    }
	
	/*
	 * 
	 */
	@ApiOperation("saveQrDocument")
	@PostMapping(value = "/saveQrDocument")
    public QrDocument saveQrDocument(@RequestPart("file") MultipartFile file, @RequestPart("qrDocument") QrDocument qrDocument){
		System.out.println("saveQrDocument");
		try {
			return documentService.saveQrDocument(file,qrDocument) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return  null ; 
		}
    }
	
	/*
	 * 
	 */
	@ApiOperation("getCodeQr")
	@PostMapping(value = "/getCodeQr")
    public String getQr(@RequestBody QrDocument qrDocument){
		System.out.println("getCodeQr");
		try {
			return documentService.getCodeQr(qrDocument.getId()) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return  null ; 
		}
    }
	
	/*
	 * 
	 */
	@ApiOperation("getQrDocumentGeneralId")
	@PostMapping(value = "/getQrDocumentGeneralId")
	public QrDocumentGeneral getQrDocumentGeneralId (String id){
		System.out.println("getQrDocumentGeneralId");
		try {
			return documentService.getQrDocumentGeneralId(id) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null ; 
		}
    }
	
	@ApiOperation("getValidaContrasenia")
	@PostMapping(value = "/getValidaContrasenia")
	public ResponseDTO getQrDocumentGeneralId (@RequestParam("id")String id,@RequestParam("contrasenia")String contrasenia){
		System.out.println("getValidaContrasenia");
		try {
			return documentService.getValidaContrasenia(id,contrasenia) ; 
		}
		catch (Exception e) {
			System.out.println("Error : " +  e);
			return null; 
		}
    }
	
}