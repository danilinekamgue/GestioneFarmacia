package controller;

import config.DbConfig;
import config.DbInfo;
import model.Client;
import model.Farmaco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/admin-user")
public class AdminUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("clients", this.getClienti());
        request.getRequestDispatcher("/admin/admin-user.jsp").forward(request, response);
    }

    private List<Client> getClienti(){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Client> clients = new ArrayList<>();

        try {
            DbInfo db = DbConfig.getDbConfig();
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            String query = "SELECT * FROM users";
            stmt = conn.prepareStatement(query);

            rs = stmt.executeQuery();

            while (rs.next()) {
                // Aggiungi ogni cliente alla lista
                clients.add(new Client(rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("role"),
                        rs.getString("password")));
                System.out.println(clients);
            }

        } catch (SQLException e) {
            // Gestione delle eccezioni
            return  null;
        } catch (ClassNotFoundException e) {
            return  null;
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
        return  clients;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("clients", this.getClienti());
        request.getRequestDispatcher("/admin/admin-user.jsp").forward(request, response);
    }

}
