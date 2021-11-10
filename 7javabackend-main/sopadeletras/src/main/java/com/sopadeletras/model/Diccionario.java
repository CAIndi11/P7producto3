package com.sopadeletras.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Diccionario")
public class Diccionario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//propiedades
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPalabra")
	int idPalabra;
	@Column(name = "palabra")
	String palabra;
	
	//Contructor vacio
	public Diccionario() {
	}
	
	//Contructor con palabra de atributo
	public Diccionario(String palabra) {
		this.palabra = palabra;
	}
	
	//Contructor con 2 atributos
	public Diccionario(int idPalabra, String palabra) {
		super();
		this.idPalabra = idPalabra;
		this.palabra = palabra;
	}
	
	//Getter and Setters
	public int getIdPalabra() {
		return idPalabra;
	}


	public void setIdPalabra(int idPalabra) {
		this.idPalabra = idPalabra;
	}


	public String getPalabra() {
		return palabra;
	}


	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

}
