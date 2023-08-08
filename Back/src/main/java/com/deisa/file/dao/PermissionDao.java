package com.deisa.file.dao;

import java.util.List;

import com.deisa.file.dto.InformationDocuments;
import com.deisa.file.dto.Permission;

public interface PermissionDao {
	public List<Permission> getPermissions() throws Exception; 
}
