package com.deisa.file.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface EmailDao {
	public String emailPassword(String[] emails, String order, String nombre, String url, String password, int descargar) ; 
	public String emailWeeklyReport(String[] emails, List<String[]> rows, String rows_start, String rows_end) ; 
}
