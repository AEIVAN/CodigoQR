package com.deisa.file.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deisa.file.dto.Documento;
import com.deisa.file.dto.QrDocumentGeneral;

@Repository
public interface QrDocumentGeneralDao {
	public QrDocumentGeneral getQrDocumentGeneral(QrDocumentGeneral qrDocumentGeneral) throws Exception; 
	public QrDocumentGeneral getQrDocumentGeneralId(String id) throws Exception; 
}
