package com.deisa.file.dao;

import org.springframework.stereotype.Repository;

import com.deisa.file.dto.InformationDocuments;

@Repository
public interface InformationDocumentDao {
	public InformationDocuments getInformationDocument(String id) throws Exception; 	
}
