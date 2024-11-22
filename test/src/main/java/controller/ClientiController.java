package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
	// Dichiarazione della connessione
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {

        // Connexion à la base de données
        DbInfo db = DbConfig.getDbConfig();

        Class.forName("com.mysql.cj.jdbc.Driver");

        // Tentativo di connessione al database
        conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());

        // Preparazione della query
        String query = "SELECT * FROM users";
        stmt = conn.prepareStatement(query);

        // Esecuzione della query
        rs = stmt.executeQuery();

        // Creazione di una lista di oggetti Client per memorizzare i risultati
        List<Client> clients = new ArrayList<>();
        while (rs.next()) {
            // Aggiungi ogni cliente alla lista
            clients.add(new Client(rs.getString("email"),
                    rs.getString("nome"),
                    rs.getString("role"),
                    rs.getString("password")));
            System.out.println(clients);
        }

        // Aggiungi la lista dei clienti come attributo nella richiesta
        request.setAttribute("clients", clients);

        // Inoltra la richiesta alla pagina JSP
        request.getRequestDispatcher("home.jsp").forward(request, response);

    } catch (SQLException e) {
        // Gestione delle eccezioni SQL
        e.printStackTrace();
        throw new ServletException("Errore durante l'accesso al database", e);
    } catch (ClassNotFoundException e) {
        // Gestione dell'eccezione se il driver JDBC non viene trovato
        e.printStackTrace();
        throw new ServletException("Driver JDBC non trovato", e);
    } finally {
        // Chiusura delle risorse in modo sicuro
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // Log degli errori di chiusura
            e.printStackTrace();
        }
    }
}
    }
	
	
	
	



