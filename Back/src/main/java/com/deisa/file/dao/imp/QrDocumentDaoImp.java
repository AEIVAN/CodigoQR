package com.deisa.file.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.deisa.file.dao.QrDocumentDao;
import com.deisa.file.dto.QrDocumentGeneral;
import com.deisa.file.dto.Documento;
import com.deisa.file.dto.QrDocument;

@Service
public class QrDocumentDaoImp implements QrDocumentDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate ; 

	@Override
	public List<QrDocument> getQrDocuments(QrDocumentGeneral qrDocumentGeneral) throws Exception {
		System.out.println("getQrDocuments");
		String sql = "SELECT * FROM documento WHERE departamento = ? AND documento = ? AND  numero  = ? AND estado = 'Disponible' ";
		Object[] params = {qrDocumentGeneral.getDepartamento(), qrDocumentGeneral.getDocumento(), qrDocumentGeneral.getNumero()}; 
		try {
			List<QrDocument> response =  jdbcTemplate.query(sql, params,new BeanPropertyRowMapper<QrDocument>(QrDocument.class)); 
			return response;
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.toString());
			return new ArrayList<QrDocument>(); 
		}
		
	}
	@Override
	public List<QrDocument> getQrDocumentsById(QrDocumentGeneral qrDocumentGeneral) throws Exception {
		System.out.println("getQrDocuments");
		String sql = "SELECT * FROM documento WHERE departamento = ? AND documento = ? AND  numero  = ? AND estado = 'Disponible' AND tipo = ? ";
		Object[] params = {qrDocumentGeneral.getDepartamento(), qrDocumentGeneral.getDocumento(), qrDocumentGeneral.getNumero(), qrDocumentGeneral.getTipo()}; 
		try {
			List<QrDocument> response =  jdbcTemplate.query(sql, params,new BeanPropertyRowMapper<QrDocument>(QrDocument.class)); 
			return response;
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.toString());
			return new ArrayList<QrDocument>(); 
		}
		
	}

	@Override
	public QrDocument getQrDocument(QrDocument qrDocument) throws Exception {
		System.out.println("getQrDocumentById");
		String sql = "SELECT * FROM documento WHERE departamento = ? AND documento = ? AND  numero  = ? ";
		Object[] params = {qrDocument.getDepartamento(),qrDocument.getDocumento(),qrDocument.getNumero()}; 
		QrDocument response =  jdbcTemplate.queryForObject(sql, params,new BeanPropertyRowMapper<QrDocument>(QrDocument.class)); 
		return response;
	}

	@Override
	public QrDocument downLoadDocument(String id) throws Exception {
		System.out.println("getDocumento");
		String sql = "SELECT * FROM documento WHERE id = ? ";
		Object[] params = {id}; 
		QrDocument qrDocumentResponse =  jdbcTemplate.queryForObject(sql, params,new BeanPropertyRowMapper<QrDocument>(QrDocument.class)); 
		return qrDocumentResponse;
	}

	@Override
	public String exisDocumento(QrDocument qrDocument) throws Exception {
		System.out.println("exisDocumento");
		String sql = "SELECT id FROM documento WHERE (departamento = ? AND documento = ? AND numero = ? AND nombre = ? AND extension = ? ) OR id = ? ";
		Object[] params = {
				qrDocument.getDepartamento(),
				qrDocument.getDocumento(),
				qrDocument.getNumero(),
				qrDocument.getNombre(),
				qrDocument.getExtension(),
				qrDocument.getId()
				}; 
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,params); 
		while (rs.next()) {
			return rs.getString(1) ; 
		}
		return "";
	}

	@Override
	public boolean insertQrDocument(QrDocument qrDocument) throws Exception {
		System.out.println("insertQrDocument");
		String sql = "INSERT INTO documento (id,tipo,departamento,documento,numero,razon_social,nombre,extension,estado,contrasenia) values (?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
				qrDocument.getId(),
				qrDocument.getDepartamento(),
				qrDocument.getDocumento(),
				qrDocument.getNumero(),
				qrDocument.getRazonSocial(),
				qrDocument.getNombre(),
				qrDocument.getExtension(),
				qrDocument.getEstado(),
				qrDocument.getContrasenia()
		}; 
		return jdbcTemplate.update(sql,params) > 0 ? true : false ; 
	}

	@Override
	public boolean updateQrDocument(QrDocument qrDocument) throws Exception {
		System.out.println("updateDocumento");
		String sql = "UPDATE documento SET departamento = ?,"
					+" documento = ?, numero = ?, nombre = ?, extension = ?, razon_social = ?, estado = ?, contrasenia = ? WHERE id = ? ";
		Object[] params = {
				qrDocument.getDepartamento(),
				qrDocument.getDocumento(),
				qrDocument.getNumero(),
				qrDocument.getNombre(),
				qrDocument.getExtension(),
				qrDocument.getRazonSocial(),
				qrDocument.getEstado(),
				qrDocument.getContrasenia(),				
				qrDocument.getId(),
		}; 
		return jdbcTemplate.update(sql,params) > 0 ? true : false ; 
	}

	@Override
	public QrDocument getQrDocumentById(String id) throws Exception {
		System.out.println("getQrDocumentById");
		String sql = "SELECT * FROM documento WHERE id = ? ";
		Object[] params = {id}; 
		QrDocument response =  jdbcTemplate.queryForObject(sql, params,new BeanPropertyRowMapper<QrDocument>(QrDocument.class)); 
		return response;
	}
	@Override
	public boolean getValidaContrasenia(String id, String contrasenia) throws Exception{
		System.out.println("getQrDocumentById");
		String sql = "SELECT * FROM documento WHERE id = ? AND contrasenia = ? ";
		Object[] params = {id,contrasenia}; 
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,params); 
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
