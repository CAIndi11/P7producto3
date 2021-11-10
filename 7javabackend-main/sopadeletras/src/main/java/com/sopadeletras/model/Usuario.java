package com.sopadeletras.model;

public class Usuario {
	
	//Propiedades
	String username;
	String password;
	
	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	//Getters and Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
