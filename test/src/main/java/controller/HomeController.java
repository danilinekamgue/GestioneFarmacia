package controller;

import Service.SqlConn;
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


@WebServlet(urlPatterns = {""})
public class HomeController  extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.setAttribute("farmaci", this.getData());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


    private List<Farmaco> getData(){

        String query = "SELECT id, nome, descrizione, quantita, prezzo   FROM farmaci WHERE quantita > 1 " ;
        List<Farmaco> farmaci = new ArrayList<>();
        try {
            SqlConn sql = new SqlConn(query);
            ResultSet rs = sql.execute();

            while(rs != null && rs.next()){
                Farmaco farmaco = new Farmaco();
                farmaco.setId(rs.getInt("id"));
                farmaco.setNome(rs.getString("nome"));
                farmaco.setDescrizione(rs.getString("descrizione"));
                farmaco.setPrezzo(rs.getInt("prezzo"));
                farmaco.setQuantita(rs.getInt("quantita"));
                farmaci.add(farmaco);
            }
            sql.freeSql();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return farmaci;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("farmaci", this.getData());
    }
}
