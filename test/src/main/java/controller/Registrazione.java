package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Client;

@WebServlet("/register")
public class Registrazione  extends  HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.sendRedirect("register.jsp");
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String name = request.getParameter("name");
	
	String email = request.getParameter("email");
	
	String password = request.getParameter("password");
	
	System.out.println("Dati ricevuti: " + name + ", " + email + ", " + password);
	
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    
	
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	
    	String url = "jdbc:mysql://localhost:3306/agenzia"; 
    	String user = "root"; // 
    	String passwor = "Torino2028!";
    	Connection conn= DriverManager.getConnection(url,user,passwor);
    	
    	String sql = "INSERT INTO clients (nome, email, password) VALUES (?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, email);
		stmt.setString(3, password);
		int rows = stmt.executeUpdate();
	
		
		if (rows > 0) {
            out.println("<h1>Registrazione completata con successo!</h1>");
            out.println("<a href='list'>Visualizza utenti registrati</a>");
        } else {
            out.println("<h1>Errore durante la registrazione.</h1>");
        }
		
		stmt.close();
		conn.close();
	}catch(Exception e){
		
		 e.printStackTrace();
		 out.println("<h1>Errore: " + e.getMessage() + "</h1>");
	}
	
	} 
	
	

	

}
