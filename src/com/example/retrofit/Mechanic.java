package com.example.retrofit;

public class Mechanic {
	private int id;
	private String code;
	private String name;
	private int status;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String toString() {
		String estado = (status==1) ? "Activo" : "Inactivo";

		String mecanico = code+"-"+name+"-"+estado;
		return mecanico;
	}
	
}
