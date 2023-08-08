package com.deisa.file.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deisa.file.dto.ResponseDTO;
import com.deisa.file.service.DocumentService;
import com.deisa.file.service.SingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = { "*", "http://localhost:4200", "http://localhost",
		"http://reportesdeisa.ddns.net/qr" }, methods = { RequestMethod.GET, RequestMethod.POST })
@Api(value = "", description = "")
@RequestMapping("/Firma")
public class FirmaApi {
	
	@Autowired
	SingService singService;

	@ApiOperation("Servicio de prueba para firma ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping(value = "/test")
	public ResponseDTO test(@RequestParam("test") String test) {

		return singService.test(test);
	}
	
	@ApiOperation("Servicio de prueba para firma ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Customer not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@GetMapping(value = "/signTest")
	public ResponseDTO signTest() {

		return singService.singTest();
	}
	
	
}
