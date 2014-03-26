package com.example.retrofit;

public class Tool {
	private int id;
	private String code;
	private String name;

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
	

	public String toString() {

		String herramienta = code+"-"+name;
		return herramienta;
	}
}
