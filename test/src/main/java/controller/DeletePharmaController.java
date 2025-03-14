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

@WebServlet("/admin/deletefarmaco")
public class DeletePharmaController extends HttpServlet {
	
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String idParam = request.getParameter("id");

	        if (idParam == null || idParam.isEmpty()) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID farmaco mancante.");
	            return;
	        }

	        int id = 0;
	        try {
	            id = Integer.parseInt(idParam);
	        } catch (NumberFormatException e) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID farmaco non valido.");
	            return;
	        }

	        // Eliminazione del farmaco dal database
	        try {
	            DbInfo db = DbConfig.getDbConfig();
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            String deleteQuery = "DELETE FROM farmaci WHERE id = ?";



				String checkQuery = "SELECT * FROM ordine_farmaci WHERE id_famaco = ?";

				Connection conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());

				PreparedStatement stmt = conn.prepareStatement(checkQuery) ;
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()){

					if(request.getServletPath().contains("clienti")){
						request.getSession().setAttribute("url", "admin/admin-user");
					}else{
						request.getSession().setAttribute("url", "admin/admin-farmaci");
					}
					request.getSession().setAttribute("error", "Non puoi cancellare un farmaco essendo gia in un ordine !");
					response.sendRedirect(request.getContextPath() + "/error");
					return ;
				}
				 stmt = conn.prepareStatement(deleteQuery);

	                stmt.setInt(1, id);

	                int rowsDeleted = stmt.executeUpdate();
	                if (rowsDeleted > 0) {
	                    //Successo, reindirizza alla pagina home.jsp
						response.sendRedirect(request.getContextPath() + "/admin/admin-farmaci");
	                } else {
	                    // Farmaco non trovato
	                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Farmaco non trovato.");
	                }

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'eliminazione del farmaco.");
	        }
	    }

}
