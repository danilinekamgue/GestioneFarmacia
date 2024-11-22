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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@WebServlet(urlPatterns = {"/client/ordini-cliente"})
public class OrdiniClienteController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> names = request.getParameterMap();
        double prezzoTotale = 0;

     // CHECK IF IT POSSIBLE TO SERVE THE ORDER (check the quntity)

        // if not posssible return the message that is not possible

        // if possible  insert order
        
        request.getRequestDispatcher("/client/ordini-cliente.jsp").forward(request, response);
    }


    private List<OrdineModel> getOrdiniByEmail(String email){

        List<OrdineModel> ordini = new ArrayList<>();
        String query = "SELECT o.id, o.id_famaco, f.nome, of.quantita,     " +
                " FROM ordini o " +
                " INNER JOIN ordine_farmaci of  ON  of.id_ordine= o.id " +
                " INNER JOIN farmaci f  ON  f.id = o.id_famaco " +
                " WHERE email = " + email ;


        Map<String, Farmaco> farmaci = new TreeMap<>();
        try {
            SqlConn sql = new SqlConn(query);
            ResultSet rs = sql.execute();
            while(rs != null && rs.next()){
                Farmaco farmaco = new Farmaco();
                farmaco.setId(rs.getInt("id"));
                farmaco.setNome(rs.getString("nome"));
                farmaco.setPrezzo(rs.getInt("prezzo"));
                farmaco.setQuantita(rs.getInt("quantita"));
                farmaci.put(farmaco.getId() + "", farmaco);
            }
            sql.freeSql();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ordini;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        List<Farmaco> farmaci = new ArrayList<>();
        farmaci.add(new Farmaco(1, "nooo","ndofdf",2, 2, LocalDate.now()));
        farmaci.add(new Farmaco(2, "nooo","ndofdf",2, 2, LocalDate.now()));

        System.out.println("je suis cicici " + farmaci.size());
        request.setAttribute("farmaci", farmaci);
        request.setAttribute("tttt", "tttt");
        request.getRequestDispatcher("/client/ordini-cliente.jsp").forward(request, response);
    }

}
