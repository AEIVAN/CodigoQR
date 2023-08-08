package com.deisa.file.service;

import org.springframework.stereotype.Repository;

import com.deisa.file.dto.ResponseDTO;

@Repository
public interface SingService {
	public ResponseDTO test(String test) ; 
	
	public ResponseDTO singTest() ; 
}
