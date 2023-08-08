package com.deisa.file.bo;

import org.springframework.stereotype.Service;

@Service
public class FirmaServiceBo {
	
	public String test(String test) throws Exception {
		System.out.println("test " + test);
		try {
			return test;
		} catch (Exception e) {
			throw (new Exception("*** Class " + this.getClass().getName() + " Method test ***  Excepcion : " + e));
		}
	}
	
	public String singTest() throws Exception {
		System.out.println("test singTest");
		try {
			return "Hola";
		} catch (Exception e) {
			throw (new Exception("*** Class " + this.getClass().getName() + " Method test ***  Excepcion : " + e));
		}
	}

}
