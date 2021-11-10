package com.sopadeletras.controller;
import java.io.IOException;
import java.util.Hashtable;
//import java.util.Properties;

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
    		  //String strUrl2 = "partida.jsp";
    		  String username = request.getParameter("username");
    		  String password = request.getParameter("password");

    		  Hashtable<String, String> env = new Hashtable<>();
    		  //Properties env = new Properties();
    		  
    		  //Variable control autentidicación
    		  boolean autentificado = false;
    		  
    		  //Declaramos parametros para la conexión LDAP
    		  env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    		  env.put(Context.PROVIDER_URL, "ldap://192.168.0.157:389");
    		  env.put(Context.SECURITY_AUTHENTICATION, "simple");
    		  env.put(Context.SECURITY_PRINCIPAL, "cn=" + username +", ou=Users, dc=p7j, dc=edu");
    		  env.put(Context.SECURITY_CREDENTIALS, password);

    		  try {
    		   // Creamos el contexto inicial
    		   DirContext ctx = new InitialDirContext(env);

    		   // Establecemos variable y cerramos el contexto
    		   autentificado = true;
    		   ctx.close();

    		  } catch (NamingException e) {
    			  autentificado = false;
    		  } finally {
    			  if (autentificado) {
    				  //System.out.print("Success");
    				  HttpSession session=request.getSession();
    			      session.setAttribute("user",username);
    				  response.sendRedirect("/com.sopadeletras/partida");
    				  //RequestDispatcher rd = request.getRequestDispatcher(strUrl2);
    	    		  //rd.forward(request, response);
    			   }else {
    				  //System.out.print("Failure");    				  
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
