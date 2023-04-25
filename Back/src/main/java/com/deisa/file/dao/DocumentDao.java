package com.deisa.file.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deisa.file.dto.Documento;
import com.deisa.file.dto.QrDocument;

@Repository
public interface DocumentDao {
	public List<Documento> getDocumento(String id) throws Exception; 	
	public boolean existId(String id) throws Exception; 
	public String exisDocumento(Documento documento) throws Exception; 
	public boolean insertDocumento(Documento documento) throws Exception; 
	public boolean updateDocumento(Documento documento) throws Exception; 
	/////////////////////////////////////////////////////////////////////
	public QrDocument downloadQrDocument(String id) throws Exception;
}
