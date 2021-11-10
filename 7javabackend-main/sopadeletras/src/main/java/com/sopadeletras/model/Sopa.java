package com.sopadeletras.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Sopa")
public class Sopa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//propiedades
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSopa")
	int idSopa;
	@Column(name = "idJugador")
	String idJugador;
	@Column(name = "puntos")
	int puntos;
	
	//Contructor vacio
	public Sopa() {
		
	}	
	
	//Contructor con 2 atributos
	public Sopa(String idJugador, int puntos) {
		//super();
		this.idJugador = idJugador;
		this.puntos = puntos;
	}
	

	public int getIdSopa() {
		return idSopa;
	}


	public void setIdSopa(int idSopa) {
		this.idSopa = idSopa;
	}


	public String getIdJugador() {
		return idJugador;
	}


	public void setIdJugador(String idJugador) {
		this.idJugador = idJugador;
	}


	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

}
