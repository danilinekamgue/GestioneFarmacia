package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.DbConfig;
import config.DbInfo;
import model.Client;

@WebServlet("/clienti")
public class ClientiController extends HttpServlet{

	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Connexion à la base de données
            DbInfo db = DbConfig.getDbConfig();

            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword())) {
                String query = "SELECT * FROM clients";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                // Stocker les résultats dans une liste
                List<Client> clients = new ArrayList<>();
                while (rs.next()) {
                    clients.add(new Client(rs.getInt("id"), rs.getString("nome"), rs.getString("email")));
                }
                System.out.println(clients);
                // Ajouter la liste dans l'attribut de requête
                request.setAttribute("clients", clients);

                // Rediriger vers la page JSP
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'accès à la base de données", e);
        }
    }
	
	
	
	

}
