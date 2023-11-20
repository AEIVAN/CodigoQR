package com.deisa.file.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deisa.file.bo.DocumentServiceBo;
import com.deisa.file.bo.FirmaServiceBo;
import com.deisa.file.dto.ResponseDTO;
import com.deisa.file.service.SingService;

@Service
public class SingServiceImp implements SingService{
	
	@Autowired
	FirmaServiceBo firmaServiceBo;

	@Override
	public ResponseDTO test(String test) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(firmaServiceBo.test(test));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}

	@Override
	public ResponseDTO singTest() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(firmaServiceBo.singTest());
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}
	
	@Override
	public ResponseDTO keyPairGenerator() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(firmaServiceBo.keyPairGenerator());
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}
	
	public ResponseDTO digitalSignature() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(firmaServiceBo.digitalSignature());
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}
	
	public ResponseDTO verifySignature(String firma) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			responseDTO.setMsg("Ok");
			responseDTO.setBody(firmaServiceBo.verifySignature(firma));
			responseDTO.setStatus(true);
		} catch (Exception e) {
			responseDTO.setMsg("Error");
			responseDTO.setBody(e.getMessage());
			responseDTO.setStatus(false);
		}
		return responseDTO;
	}
	
	
	
	
	
}
