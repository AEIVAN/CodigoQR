package com.deisa.file.dao.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.deisa.file.dao.QrDocumentGeneralDao;
import com.deisa.file.dto.QrDocumentGeneral;

@Service
public class QrDocumentGeneralDaoImp implements QrDocumentGeneralDao{

	@Autowired
	JdbcTemplate jdbcTemplate ; 
	
	@Override
	public QrDocumentGeneral getQrDocumentGeneral(QrDocumentGeneral qrDocumentGeneral) throws Exception {
		System.out.println("getQrDocumentDao");
		String sql = "SELECT * FROM documento WHERE departamento = ? AND documento = ? AND  numero  = ?  LIMIT 1";
		Object[] params = {qrDocumentGeneral.getDepartamento(), qrDocumentGeneral.getDocumento(), qrDocumentGeneral.getNumero()}; 
		try {
			QrDocumentGeneral response =  jdbcTemplate.queryForObject(sql, params,new BeanPropertyRowMapper<QrDocumentGeneral>(QrDocumentGeneral.class)); 
			return response;
		}catch (Exception e) {
			System.out.println("Exception: " +  e.toString());
			return new QrDocumentGeneral();
		}
		
	}
	
	@Override
	public QrDocumentGeneral getQrDocumentGeneralId(String id) throws Exception {
		System.out.println("getQrDocumentDao");
		String sql = "SELECT * FROM documento WHERE id  = ?  LIMIT 1";
		Object[] params = {id}; 
		try {
			QrDocumentGeneral response =  jdbcTemplate.queryForObject(sql, params,new BeanPropertyRowMapper<QrDocumentGeneral>(QrDocumentGeneral.class)); 
			return response;
		}catch (Exception e) {
			System.out.println("Exception: " +  e.toString());
			return new QrDocumentGeneral();
		}
		
	}

}
