package controller;

import Service.SqlConn;
import config.DbConfig;
import config.DbInfo;
import model.Farmaco;
import model.OrdineModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@WebServlet("/admin/admin-ordini")
public class AdminOrdini extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("ordini", this.getOrdini());
        request.getRequestDispatcher("/admin/admin-ordini.jsp").forward(request, response);
    }

    private List<OrdineModel> getOrdini(){

        System.out.println("-----------------------------");
        Map<Integer, OrdineModel> ordini = new TreeMap<>();
        String query = "SELECT o.id, orf.id_famaco, f.nome, orf.quantita , o.prezzo, o.data   " +
                " FROM ordine o " +
                " INNER JOIN ordine_farmaci orf  ON  orf.id_ordine= o.id " +
                " INNER JOIN farmaci f  ON  f.id = orf.id_famaco ";

        try {
            SqlConn sql = new SqlConn(query);
            ResultSet rs = sql.execute();
            while(rs != null && rs.next()){
                Farmaco farmaco = new Farmaco();
                farmaco.setId(rs.getInt("id_famaco"));
                farmaco.setNome(rs.getString("nome"));
                farmaco.setQuantita(rs.getInt("quantita"));
                int idOrdine = rs.getInt("id");
                OrdineModel tmp = ordini.get(idOrdine);
                if(tmp == null){
                    tmp = new OrdineModel();
                    tmp.setNumero(idOrdine+"");
                    tmp.setTime(rs.getString("data"));
                    tmp.setPrezzototale(rs.getDouble("prezzo"));
                    ordini.put(idOrdine, tmp);
                }
                tmp.getFarmaci().add(farmaco);
            }
            sql.freeSql();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if(ordini.size() < 1){
            return null;
        }
        return ordini.values().stream().collect(Collectors.toList());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("ordini", this.getOrdini());
        request.getRequestDispatcher("/admin/admin-ordini.jsp").forward(request, response);
    }
}
