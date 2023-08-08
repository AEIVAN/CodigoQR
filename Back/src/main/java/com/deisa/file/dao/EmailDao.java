package com.deisa.file.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface EmailDao {
	public String emailPassword(String[] emails, String order, String nombre, String url, String password, int descargar) ; 

}
