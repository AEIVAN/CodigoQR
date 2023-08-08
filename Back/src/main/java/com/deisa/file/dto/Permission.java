package com.deisa.file.dto;

public class Permission {
	private String nombre;
	private String cuenta;
	private String permiso;
	private String contrasenia;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return "Permission [nombre=" + nombre + ", cuenta=" + cuenta + ", permiso=" + permiso + ", contrasenia="
				+ contrasenia + "]";
	}

	public Permission(String nombre, String cuenta, String permiso, String contrasenia) {
		super();
		this.nombre = nombre;
		this.cuenta = cuenta;
		this.permiso = permiso;
		this.contrasenia = contrasenia;
	}

	public Permission() {
		super();
		this.nombre = "";
		this.cuenta = "";
		this.permiso = "";
		this.contrasenia = "";
	}

}
