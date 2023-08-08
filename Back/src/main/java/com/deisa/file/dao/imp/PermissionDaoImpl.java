package com.deisa.file.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.deisa.file.dao.PermissionDao;
import com.deisa.file.dto.Permission;

@Service
public class PermissionDaoImpl implements PermissionDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Permission> getPermissions() throws Exception {
		System.out.println("getPermissions");
		List<Permission> permission = new ArrayList<>();
		String sql = "SELECT Cuenta,Permiso  FROM permisos WHERE Permiso = 'Codigo QR' ";
		try {
			permission = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Permission>(Permission.class));
			return permission;
		} catch (Exception e) {
			return null;
		}
	}
}
