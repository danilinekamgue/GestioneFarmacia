package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.DbConfig;
import config.DbInfo;

@WebServlet("/admin/addclient")
public class AddClient  extends  HttpServlet{
	
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String nome = request.getParameter("nome");
	        String email = request.getParameter("email");
	        String role = request.getParameter("role");
	        
	        
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try  {
	        	
	        	DbInfo db = DbConfig.getDbConfig();
				System.out.println("nome-----------");
				System.out.println(email);
				System.out.println(role);
				System.out.println("nome-----------");

	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
	            String sql = "INSERT INTO users (nome, email,password,role) VALUES (?, ?,?,?)";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, nome);
	            stmt.setString(2, email);
	            stmt.setString(3, email);
	            stmt.setString(4, role);

	            int rowsInserted = stmt.executeUpdate();
	            if (rowsInserted > 0) {
	                request.setAttribute("message", "Cliente aggiunto con successo!");
	            } else {
	                request.setAttribute("message", "Errore durante l'aggiunta del cliente.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("message", "Errore: " + e.getMessage());
	        }

	        // Reindirizza alla pagina dei clienti dopo l'aggiunta
	       // response.sendRedirect("home.jsp");
		 response.sendRedirect("admin-user");
	    }

}
