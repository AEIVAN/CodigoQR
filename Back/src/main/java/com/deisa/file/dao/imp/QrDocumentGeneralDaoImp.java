package com.deisa.file.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.deisa.file.dao.QrDocumentGeneralDao;
import com.deisa.file.dto.QrDocument;
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
	
	public List<QrDocument> getHistorical(String idQr, String numero)  throws Exception {
		System.out.println("getQrDocumentDao");
		String sql = "SELECT * FROM documento WHERE id = ? OR numero = ? ";
		Object[] params = {idQr , numero}; 
		List<QrDocument> response = new ArrayList<>(); 
		try {
			 response =  jdbcTemplate.query(sql, params,new BeanPropertyRowMapper<QrDocument>(QrDocument.class)); 
			return response;
		}catch (Exception e) {
			System.out.println("Exception: " +  e.toString());
			return response; 
		}
		
	}
	
	public List<String[]> getWeeklyReport() throws Exception{
		System.out.println("getWeeklyReport");
		String sql = "SELECT departamento, count(*) FROM documento WHERE STR_TO_DATE(fecha, '%d-%m-%Y') >= DATE(DATE_SUB(NOW(), INTERVAL 8 DAY))GROUP BY departamento";
		List<String[]> response = new ArrayList<>(); 
		try {
			 SqlRowSet rs  =  jdbcTemplate.queryForRowSet(sql); 
			 while(rs.next()) {
				 response.add(new String[]{rs.getString(1), rs.getString(2)}); 
			 }
			return response;
		}catch (Exception e) {
			System.out.println("Exception: " +  e.toString());
			return response; 
		}
	}
	
}
