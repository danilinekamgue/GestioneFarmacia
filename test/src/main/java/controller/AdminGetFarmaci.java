package controller;

import config.DbConfig;
import config.DbInfo;
import model.Farmaco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/admin/admin-farmaci")
public class AdminGetFarmaci extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("farmaci", this.getFamarci());
        //request.getRequestDispatcher("home.jsp").forward(request, response);

        request.getRequestDispatcher("/admin/admin-farmaci.jsp").forward(request, response);
    }

    private List<Farmaco> getFamarci(){

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
        return  farmaci;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("farmaci", this.getFamarci());
        request.getRequestDispatcher("/admin/admin-farmaci.jsp").forward(request, response);
    }
}
