package com.sopadeletras.controller;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.*;
import javax.naming.directory.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    protected void procesoAutentificacionLDAP(HttpServletRequest request, HttpServletResponse response)
    		   throws ServletException, IOException {

    		  String strUrl = "index.jsp";
    		  String username = request.getParameter("username");
    		  String password = request.getParameter("password");

    		  Hashtable<String, String> env = new Hashtable<>();
    		  
    		  //Variable de autentificación
    		  boolean autentificado = false;
    		  
    		  //Declaramos parametros para la conexión LDAP
    		  //Hay que tener en cuenta sobre todo el uid y la contraseña
    		  //Vigilar también el tema de versiones 
    		  
  			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
  			env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
  			env.put(Context.SECURITY_PRINCIPAL, "uid=admin, ou=system");
  			env.put(Context.SECURITY_CREDENTIALS, "secret");

    		  try {
    		   // Creamos el contexto 
    		   DirContext ctx = new InitialDirContext(env);

    		   // Establecemos variable y cerramos el contexto
    		   autentificado = true;
    		   ctx.close();

    		  } catch (NamingException e) {
    			  autentificado = false;
    		  } finally {
    			  if (autentificado) {
    				  HttpSession session=request.getSession();
    			      session.setAttribute("user",username);
    				  response.sendRedirect("partida.jsp");
    			   }else {   				  
    				  RequestDispatcher rd = request.getRequestDispatcher(strUrl);
    	    		  rd.forward(request, response);

    		   }
    		  }


	 }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		procesoAutentificacionLDAP(request, response);
	}

}