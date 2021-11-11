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
public class Juego implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//propiedades
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idJuego")
	int idJuego;
	@Column(name = "idJugador")
	String idJugador;
	@Column(name = "puntos")
	int puntos;
	
	//Contructor vacio
	public Juego() {
		
	}	
	
	//Contructor con 2 atributos
	public Juego(String idJugador, int puntos) {
		
		//super();
		this.idJugador = idJugador;
		this.puntos = puntos;
	}
	

	public int getIdJuego() {
		return idJuego;
	}


	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
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
