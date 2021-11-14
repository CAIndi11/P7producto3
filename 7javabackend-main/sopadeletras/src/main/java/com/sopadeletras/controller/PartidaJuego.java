package com.sopadeletras.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopadeletras.dao.DiccionarioDAO;
import com.sopadeletras.dao.JuegoDAO;
import com.sopadeletras.model.Diccionario;
import com.sopadeletras.model.Juego;

/**
 * Servlet implementation class PartidaJuego
 */
@WebServlet("/partidajuego")
public class PartidaJuego extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 
     * @see HttpServlet#HttpServlet()
     */
    public PartidaJuego() {

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiccionarioDAO dicBD = new DiccionarioDAO();
		List<Diccionario> diccionario = dicBD.obtenerTodos();
		
		List<Diccionario> listaPalabras = new ArrayList<Diccionario>();
		
		
		for (int i = 0; i < 5; i++) {
			int rnd = new Random().nextInt(diccionario.size());			
			Diccionario d = diccionario.get(rnd);
			listaPalabras.add(d);
			diccionario.remove(d);			
		}			

		request.setAttribute("listaPalabras", listaPalabras);		
		 
		request.getRequestDispatcher("partida.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = (String)request.getSession().getAttribute("user");
		
		int tiempo = Integer.parseInt(request.getParameter("tiempo"));
		
		int puntuacion = 0;
		
		if (tiempo <= 10) {
			puntuacion = 500;			
		} else if (tiempo <= 20){
			puntuacion = 200;
		} else if (tiempo <= 30){
			puntuacion = 100;
		} else if (tiempo <= 60){
			puntuacion = 50;
		} else if (tiempo <= 120){
			puntuacion = 25;
		}else puntuacion = 10;	
		
		//Se inserta una nueva instancia de la clase Juego en la BBDD
		Juego Juego = new Juego(user, puntuacion);
		JuegoDAO JuegoDAO = new JuegoDAO();
		JuegoDAO.insertar(Juego);
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(String.valueOf(puntuacion));		
		
	}

}