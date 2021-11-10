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
import com.sopadeletras.dao.SopaDAO;
import com.sopadeletras.model.Diccionario;
import com.sopadeletras.model.Sopa;

/**
 * Servlet implementation class Partida
 */
@WebServlet("/partida")
public class Partida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 
     * @see HttpServlet#HttpServlet()
     */
    public Partida() {

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiccionarioDAO diccionarioDB = new DiccionarioDAO();
		List<Diccionario> todoDiccionario = diccionarioDB.obtenerTodos();
		
		List<Diccionario> listaPalabras = new ArrayList<Diccionario>();
		
		
		for (int i = 0; i < 5; i++) {
			int rnd = new Random().nextInt(todoDiccionario.size());			
			Diccionario d = todoDiccionario.get(rnd);
			listaPalabras.add(d);
			todoDiccionario.remove(d);			
		}			

		request.setAttribute("listaPalabras", listaPalabras);		
		 
		request.getRequestDispatcher("partida.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
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
		
		
		Sopa sopa = new Sopa(user, puntuacion);
		SopaDAO sopaDAO = new SopaDAO();
		sopaDAO.insertar(sopa);
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(String.valueOf(puntuacion));		
		
	}

}
