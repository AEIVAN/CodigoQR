package com.deisa.file.dao.imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.deisa.file.dao.InformationDocumentDao;
import com.deisa.file.dto.Documento;
import com.deisa.file.dto.InformationDocuments;

@Service
public class InformationDocumentDaoImp implements InformationDocumentDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate ;

	@Override
	public InformationDocuments getInformationDocument(String id) throws Exception {
		System.out.println("getInformationDocument");
		String sql = "SELECT id, departamento, documento, numero, nombre,'Desarrollo Ecologico Industrial S.A. De C.V.' AS cliente,'Ing. Pruebas Deisa' AS dirigido, '2022-08-17' AS emision FROM documento WHERE id = ? ";
		Object[] params = {id}; 
		List<InformationDocuments> informationDocuments =  jdbcTemplate.query(sql, params,new BeanPropertyRowMapper<InformationDocuments>(InformationDocuments.class)); 
		if (informationDocuments.size()!=0) {
			sql = "SELECT id, departamento, documento, numero, nombre, extension FROM documento WHERE departamento = ? AND documento = ? AND numero = ? ";
			Object[] paramsr = {
					informationDocuments.get(0).getDepartamento(),
					informationDocuments.get(0).getDocumento(),
					informationDocuments.get(0).getNumero()
			}; 
			List<Documento> documento = jdbcTemplate.query(sql, paramsr,new BeanPropertyRowMapper<Documento>(Documento.class)); 
			if (documento.size()!=0) {
				informationDocuments.get(0).setDocumentos(documento);
				return informationDocuments.get(0) ; 
			}
		}
		return null;
	} 

}
