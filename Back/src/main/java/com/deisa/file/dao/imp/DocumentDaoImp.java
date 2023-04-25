package com.deisa.file.dao.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.deisa.file.dao.DocumentDao;
import com.deisa.file.dto.Documento;
import com.deisa.file.dto.QrDocument;

@Service
public class DocumentDaoImp implements DocumentDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate ; 
	
	@Override
	public List<Documento> getDocumento(String id) throws Exception {
		System.out.println("getDocumento");
		String sql = "SELECT * FROM documento WHERE id = ? ";
		Object[] params = {id}; 
		List<Documento> documento =  jdbcTemplate.query(sql, params,new BeanPropertyRowMapper<Documento>(Documento.class)); 
		return documento;
	}

	@Override
	public boolean existId(String id) throws Exception {
		System.out.println("existId");
		String sql = "SELECT id FROM documento WHERE id = ? ";
		Object[] params = {id}; 
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,params); 
		while (rs.next()) {
			return true ; 
		}
		return false;
	}

	@Override
	public boolean insertDocumento(Documento documento) throws Exception {
		System.out.println("insertDocumento");
		String sql = "INSERT INTO documento (id,departamento,documento,numero,nombre,extension) values (?,?,?,?,?,?)";
		Object[] params = {
				documento.getId(),
				documento.getDepartamento(),
				documento.getDocumento(),
				documento.getNumero(),
				documento.getNombre(),
				documento.getExtension()
		}; 
		return jdbcTemplate.update(sql,params) > 0 ? true : false ; 
	}

	@Override
	public boolean updateDocumento(Documento documento) throws Exception {
		System.out.println("updateDocumento");
		String sql = "UPDATE FROM documento SET departamento = ?,"
					+" SET documento = ?, SET numero = ?,SET nombre = ?, SET extension = ? WHERE id = ? ";
		Object[] params = {
				documento.getDepartamento(),
				documento.getDocumento(),
				documento.getNumero(),
				documento.getNombre(),
				documento.getExtension(),
				documento.getId(),
		}; 
		return jdbcTemplate.update(sql,params) > 0 ? true : false ; 
	}

	@Override
	public String exisDocumento(Documento documento) throws Exception {
		System.out.println("exisDocumento");
		String sql = "SELECT id FROM documento WHERE departamento = ? AND documento = ? AND numero = ? AND nombre = ? AND extension = ?";
		Object[] params = {
				documento.getDepartamento(),
				documento.getDocumento(),
				documento.getNumero(),
				documento.getNombre(),
				documento.getExtension()}; 
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,params); 
		while (rs.next()) {
			return rs.getString(1) ; 
		}
		return "";
	}
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////
	@Override
	public QrDocument downloadQrDocument(String id) throws Exception {
		System.out.println("getDocumento");
		String sql = "SELECT * FROM documento WHERE id = ? ";
		Object[] params = {id}; 
		QrDocument documento = jdbcTemplate.queryForObject(sql, params,new BeanPropertyRowMapper<QrDocument>(QrDocument.class)); 
		return documento;
	}

}
