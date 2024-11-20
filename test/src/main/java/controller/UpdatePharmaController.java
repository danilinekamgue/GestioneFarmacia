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
import model.Farmaco;

@WebServlet ("/updateFarmaco")
public class UpdatePharmaController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni il parametro ID dalla query string
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID farmaco mancante.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID farmaco non valido.");
            return;
        }

        // Recupera i dettagli del farmaco dal database
        Farmaco farmaco = null;
        try {
            DbInfo db = DbConfig.getDbConfig();
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM farmaci WHERE id = ?")) {

                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        farmaco = new Farmaco();
                        farmaco.setId(rs.getInt("id"));
                        farmaco.setNome(rs.getString("nome"));
                        farmaco.setDescrizione(rs.getString("descrizione"));
                        farmaco.setPrezzo(rs.getInt("prezzo"));
                        farmaco.setQuantita(rs.getInt("quantita"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero del farmaco.");
            return;
        }

        if (farmaco == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Farmaco non trovato.");
            return;
        }

        // Passa il farmaco al JSP
        request.setAttribute("farmaco", farmaco);
        request.getRequestDispatcher("updateFarmaco.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Leggi i parametri dal form
        String idParam = request.getParameter("id");
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String prezzoParam = request.getParameter("prezzo");
        String quantitaParam = request.getParameter("quantita");

        // Validazione base dei parametri
        if (idParam == null || nome == null || descrizione == null || prezzoParam == null || quantitaParam == null ||
            idParam.isEmpty() || nome.isEmpty() || descrizione.isEmpty() || prezzoParam.isEmpty() || quantitaParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tutti i campi sono obbligatori.");
            return;
        }

        int id;
        double prezzo;
        int quantita;

        try {
            id = Integer.parseInt(idParam);
            prezzo = Double.parseDouble(prezzoParam);
            quantita = Integer.parseInt(quantitaParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato numerico non valido.");
            return;
        }

        // Aggiorna il farmaco nel database
        try {
            DbInfo db = DbConfig.getDbConfig();
            Class.forName("com.mysql.cj.jdbc.Driver");

            String updateQuery = "UPDATE farmaci SET nome = ?, descrizione = ?, prezzo = ?, quantita = ? WHERE id = ?";

            try (Connection conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
                 PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

                stmt.setString(1, nome);
                stmt.setString(2, descrizione);
                stmt.setDouble(3, prezzo);
                stmt.setInt(4, quantita);
                stmt.setInt(5, id);

                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {
                    // Reindirizza alla pagina di successo o alla home
                    response.sendRedirect("home.jsp?message=Farmaco aggiornato con successo");
                } else {
                    // Errore: farmaco non trovato
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Farmaco non trovato.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'aggiornamento del farmaco.");
        }
    }
}
	
	


