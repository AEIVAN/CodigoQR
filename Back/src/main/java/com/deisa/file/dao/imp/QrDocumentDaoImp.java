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
import com.deisa.file.dto.Email;
import com.deisa.file.dto.LogRecordDTO;
import com.deisa.file.dto.QrDocument;

@Service
public class QrDocumentDaoImp implements QrDocumentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<QrDocument> getQrDocuments(QrDocumentGeneral qrDocumentGeneral) throws Exception {
		System.out.println("getQrDocuments");
		String sql = "SELECT * FROM documento WHERE departamento = ? AND documento = ? AND  numero  = ? AND estado = 'Disponible' ";
		Object[] params = { qrDocumentGeneral.getDepartamento(), qrDocumentGeneral.getDocumento(),
				qrDocumentGeneral.getNumero() };
		try {
			List<QrDocument> response = jdbcTemplate.query(sql, params,
					new BeanPropertyRowMapper<QrDocument>(QrDocument.class));
			return response;
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
			return new ArrayList<QrDocument>();
		}

	}

	@Override
	public List<QrDocument> getQrDocumentsById(QrDocumentGeneral qrDocumentGeneral) throws Exception {
		System.out.println("getQrDocuments");
		String sql = "SELECT * FROM documento WHERE departamento = ? AND documento = ? AND  numero  = ? AND estado = 'Disponible' AND tipo = ? ";
		Object[] params = { qrDocumentGeneral.getDepartamento(), qrDocumentGeneral.getDocumento(),
				qrDocumentGeneral.getNumero(), qrDocumentGeneral.getTipo() };
		try {
			List<QrDocument> response = jdbcTemplate.query(sql, params,
					new BeanPropertyRowMapper<QrDocument>(QrDocument.class));
			return response;
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString());
			return new ArrayList<QrDocument>();
		}

	}

	@Override
	public QrDocument getQrDocument(QrDocument qrDocument) throws Exception {
		System.out.println("getQrDocumentById");
		String sql = "SELECT * FROM documento WHERE departamento = ? AND documento = ? AND  numero  = ? ";
		Object[] params = { qrDocument.getDepartamento(), qrDocument.getDocumento(), qrDocument.getNumero() };
		QrDocument response = jdbcTemplate.queryForObject(sql, params,
				new BeanPropertyRowMapper<QrDocument>(QrDocument.class));
		return response;
	}

	@Override
	public QrDocument downLoadDocument(String id) throws Exception {
		System.out.println("getDocumento");
		String sql = "SELECT * FROM documento WHERE id = ? ";
		Object[] params = { id };
		QrDocument qrDocumentResponse = jdbcTemplate.queryForObject(sql, params,
				new BeanPropertyRowMapper<QrDocument>(QrDocument.class));
		return qrDocumentResponse;
	}

	@Override
	public String exisDocumento(QrDocument qrDocument) throws Exception {
		System.out.println("exisDocumento");
		String sql = "SELECT id FROM documento WHERE (departamento = ? AND documento = ? AND numero = ? AND nombre = ? AND extension = ? ) OR id = ? ";
		Object[] params = { qrDocument.getDepartamento(), qrDocument.getDocumento(), qrDocument.getNumero(),
				qrDocument.getNombre(), qrDocument.getExtension(), qrDocument.getId() };

		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, params);
		while (rs.next()) {
			return rs.getString(1);
		}
		return "";
	}

	@Override
	public boolean insertQrDocument(QrDocument qrDocument) throws Exception {
		System.out.println("insertQrDocument");
		String sql = "INSERT INTO documento \r\n"
				+ "(id,tipo,departamento,documento,numero,razon_social,nombre,extension,contrasenia,usuario,descargar,estado) values \r\n"
				+ "(? ,?   ,?           ,?        ,?     ,?           ,?     ,?        ,?          ,?      ,?        ,?     )";
		Object[] params = { qrDocument.getId(), qrDocument.getTipo(), qrDocument.getDepartamento(),
				qrDocument.getDocumento(), qrDocument.getNumero(), qrDocument.getRazonSocial(), qrDocument.getNombre(),
				qrDocument.getExtension(), qrDocument.getContrasenia(),qrDocument.getUsuario(), qrDocument.getDescargar(), qrDocument.getEstado() };
		return jdbcTemplate.update(sql, params) > 0 ? true : false;
	}

	@Override
	public boolean updateQrDocument(QrDocument qrDocument) throws Exception {
		System.out.println("updateDocumento");
		String sql = "UPDATE documento SET departamento = ?,"
				+ " documento = ?, numero = ?, nombre = ?, extension = ?, razon_social = ?, estado = ? WHERE id = ? ";
		Object[] params = { qrDocument.getDepartamento(), qrDocument.getDocumento(), qrDocument.getNumero(),
				qrDocument.getNombre(), qrDocument.getExtension(), qrDocument.getRazonSocial(), qrDocument.getEstado(),
				qrDocument.getId(), };
		return jdbcTemplate.update(sql, params) > 0 ? true : false;
	}

	@Override
	public QrDocument getQrDocumentById(String id) throws Exception {
		System.out.println("getQrDocumentById");
		String sql = "SELECT * FROM documento WHERE id = ? ";
		Object[] params = { id };
		QrDocument response = jdbcTemplate.queryForObject(sql, params,
				new BeanPropertyRowMapper<QrDocument>(QrDocument.class));
		return response;
	}

	@Override
	public boolean getValidaContrasenia(String id, String contrasenia) throws Exception {
		System.out.println("getQrDocumentById");
		String sql = "SELECT * FROM documento WHERE id = ? AND contrasenia = ? ";
		Object[] params = { id, contrasenia };

		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, params);
		while (rs.next()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getValidaContraseniaPermiso(String contrasenia, QrDocument qrDocument) throws Exception {
		System.out.println("getQrDocumentById");
		String sql = "SELECT * FROM permisos WHERE Contrasenia = ? AND  Departamento = ? ";
		Object[] params = { contrasenia , qrDocument.getDepartamento()};

		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, params);
		while (rs.next()) {
			return rs.getString("Cuenta");
		}
		return "";
	}

	@Override
	public boolean changePassword(Email email) throws Exception {
		System.out.println("changePassword");
		String sql = "UPDATE documento SET contrasenia = ? WHERE id = ? ";
		Object[] params = { email.getContrasenia(), email.getIdQr() };

		return jdbcTemplate.update(sql, params) > 0 ? true : false;
	}

	public boolean insertLog(LogRecordDTO logRecordDTO) throws Exception {

		System.out.println("insertQrDocument");
		String sql = "INSERT INTO log_registro (fecha,usuario,accion,orden,idQr,request) values (NOW(),?,?,?,?,?)";
		Object[] params = {logRecordDTO.getUsuario(), logRecordDTO.getAccion(),
				logRecordDTO.getOrden(), logRecordDTO.getIdQr(), logRecordDTO.getRequest() };
		try {
			return jdbcTemplate.update(sql, params) > 0 ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean updateDownloads(QrDocument qrDocument) throws Exception {
		System.out.println("updateDownloads");
		try {
			String sql = "UPDATE documento SET descargar = ? WHERE id = ?";
			Object[] params = { qrDocument.getDescargar(), qrDocument.getId() };
			return jdbcTemplate.update(sql, params) > 0 ? true : false;
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			return false;
		}
	}
	
	
	public List<LogRecordDTO> getHistoricalLog(String idQr) throws Exception{
		System.out.println("getHistoricalLog");
		String sql = "SELECT registro, idQr, orden, fecha AS fecha_registro, usuario, accion, request FROM log_registro  WHERE idQr = ? ";
		Object[] params = { idQr };
		List<LogRecordDTO> response = new ArrayList<>(); 
		try {
			response =  jdbcTemplate.query(sql, params,new BeanPropertyRowMapper<LogRecordDTO>(LogRecordDTO.class)); 
			return response ; 
		}
		catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			return response;
		}
	}
}
