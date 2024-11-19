package controller;

import config.DbConfig;
import config.DbInfo;
import model.FarmacoModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {""})
public class HomeController  extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       // request.getSession().setAttribute("errorMessage", null);
/*
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            // Connexion à la base de données
            DbInfo db = DbConfig.getDbConfig();
            Class.forName("com.mysql.cj.jdbc.Driver");

            String getProduct = " SELECT nome, descrizione, quantita, prezzo " +
                    " FROM farmaci " +
                    " WHERE quantita > 1 ";

            conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            stmt = conn.createStatement();
            rs = stmt.executeQuery(getProduct);
            while (rs.next()){

            }
            if(!rs.next()){
                stmt.executeUpdate(getProduct);
            }

            response.sendRedirect(request.getContextPath() + "/");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
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
                System.out.println(e.getMessage());
            }
        }

 */

        request.setAttribute("farmaci", this.getData());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


    private List<FarmacoModel> getData(){
        List<FarmacoModel> farmaci = new ArrayList<>();
        farmaci.add(new FarmacoModel(1L, "nooo","ndofdf",2, 2, LocalDate.now()));
        farmaci.add(new FarmacoModel(2L, "nooo","ndofdf",2, 2, LocalDate.now()));

        return farmaci;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("farmaci", this.getData());
    }
}
