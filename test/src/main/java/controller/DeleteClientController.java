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

@WebServlet("/admin/deleteclienti")

public class DeleteClientController extends HttpServlet {
	
	
	

	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String emailParam = request.getParameter("email");

	        if (emailParam == null || emailParam.isEmpty()) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID farmaco mancante.");
	            return;
	        }

			// VERIFICA SE HA ORDINE

	        // Eliminazione del user dal database
	        try {
	            DbInfo db = DbConfig.getDbConfig();
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            String deleteQuery = "DELETE FROM users WHERE email = ?";


				String checkQuery = "SELECT * FROM ordine WHERE email_user = ?";

				Connection conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());

				PreparedStatement stmt = conn.prepareStatement(checkQuery) ;
				stmt.setString(1, emailParam);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()){

					if(request.getServletPath().contains("clienti")){
						request.getSession().setAttribute("url", "admin/admin-user");
					}else{
						request.getSession().setAttribute("url", "admin/admin-farmaci");
					}
					request.getSession().setAttribute("error", "Non puoi cancellare un utente avendo gia fatto un ordine !");
					response.sendRedirect(request.getContextPath() + "/error");
					return ;
				}


				stmt = conn.prepareStatement(deleteQuery) ;

	            stmt.setString(1, emailParam);

	            int rowsDeleted = stmt.executeUpdate();
	            if (rowsDeleted > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/admin-user");
				} else {
	                    // Farmaco non trovato
	                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "user non trovato.");
				}

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'eliminazione del user.");
	        }
	    }


}
