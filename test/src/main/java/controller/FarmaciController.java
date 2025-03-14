package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.DbConfig;
import config.DbInfo;
import model.Farmaco;


@WebServlet("/farmaci")
public class FarmaciController extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
	
	  @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		  List<Farmaco> farmaci = new ArrayList<>();
	        try  {
	        	
	        	DbInfo db = DbConfig.getDbConfig();

	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
	            String query = "SELECT * FROM farmaci";
	            stmt = conn.prepareStatement(query);
	            rs = stmt.executeQuery();

	            while (rs.next()) {
	                Farmaco farmaco = new Farmaco();
	                farmaco.setId(rs.getInt("id"));
	                farmaco.setNome(rs.getString("nome"));
	                farmaco.setDescrizione(rs.getString("descrizione"));
	                farmaco.setPrezzo(rs.getInt("prezzo"));
	                farmaco.setQuantita(rs.getInt("quantita"));

	                farmaci.add(farmaco);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        request.setAttribute("farmaci", farmaci);
	        request.getRequestDispatcher("home.jsp").forward(request, response);
	    }
	        
	   

}
