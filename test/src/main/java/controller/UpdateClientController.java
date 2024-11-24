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
import model.Client;


@WebServlet("/admin/updateclienti")
public class UpdateClientController  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni il parametro ID dalla query string
        String emailParam = request.getParameter("email");
        System.out.println(emailParam);
        System.out.println("aaaaaa");
        if (emailParam == null || emailParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email Cliente mancante.");
            return;
        }

        

        // Recupera i dettagli del farmaco dal database
        Client client = null;
        try {
            DbInfo db = DbConfig.getDbConfig();
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?")) {

                stmt.setString(1,emailParam);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        client= new Client();
                       
                        client.setEmail(rs.getString("email"));
                        client.setNome(rs.getString("nome"));
                       
                        client.setRole(rs.getString("role"));
                        client.setPassword(rs.getString("password"));
                    }
                    
                    System.out.println(client);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero del farmaco.");
            return;
        }

        if (client == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Farmaco non trovato.");
            return;
        }

        // Passa il client al JSP
        request.setAttribute("client", client);
        request.getRequestDispatcher("updateclient.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Leggi i parametri dal form
        
        
    	String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        
        
        String password= request.getParameter("password");
        String role= request.getParameter("role");
        

        // Validazione base dei parametri
        if (nome == null ||email == null || role == null || password == null || 
            email.isEmpty() || nome.isEmpty() || role.isEmpty() || password.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tutti i campi sono obbligatori.");
            return;
        }

        try {
            DbInfo db = DbConfig.getDbConfig();
            Class.forName("com.mysql.cj.jdbc.Driver");

            String updateQuery = "UPDATE users SET nome = ?,  password = ?,role = ?  WHERE email = ?";

            try (Connection conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
                 PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

                stmt.setString(1, nome);
                stmt.setString(2, password);
                stmt.setString(3,role);
                stmt.setString(4, email);

                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    response.sendRedirect(request.getContextPath() + "/admin/admin-user");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Farmaco non trovato.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'aggiornamento del farmaco.");
        }
    }

}
