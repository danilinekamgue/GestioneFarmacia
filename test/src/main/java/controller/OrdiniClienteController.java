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
import java.util.stream.Collectors;


@WebServlet(urlPatterns = {"/client/ordini-cliente"})
public class OrdiniClienteController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> names = request.getParameterMap();
        double prezzoTotale = 0;


        // I GAR ALL THE FAMRCI AND PUT INTO VARIBLE
        String query = "SELECT id, quantita, prezzo, nome   FROM farmaci  " ;
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

        // CHECK IF I CAN SERVE
        boolean canServe = true;
        Farmaco farmaco = null;
        for(String key : names.keySet()){
            farmaco = farmaci.get(key);
            if(farmaco == null || farmaco.getQuantita() < Integer.parseInt(request.getParameter(key))){
                canServe = false;
                break;
            }
            prezzoTotale = prezzoTotale + Integer.parseInt(request.getParameter(key)) * farmaco.getPrezzo();
        }

        // IF I CAN NOT SERVE I REDIRECT AND SET THE MESSAGE ERROR
        if(!canServe){
            request.getSession().setAttribute("quantitaErrorMessage", "Non c è abbastanza quantita per questo farmaco : " + farmaco.getNome());
            response.sendRedirect(request.getContextPath() + "/client/client.jsp");
            return;
        }
        // IF YES I SERVE
        try {
            DbInfo db = DbConfig.getDbConfig();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            Statement stmt = conn.createStatement();
            ResultSet rs ;
            //id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
            String email = request.getSession().getAttribute("email").toString();

            query = " INSERT ordine ( email_user, prezzo, consegnato) VALUE ( '" + email+ "', "+ prezzoTotale + ", false ) ; ";
            stmt.executeUpdate(query);
            query = " SELECT MAX(id) as id FROM ordine WHERE email_user = '" + email+"' ;";
            rs = stmt.executeQuery(query);
            if(!rs.next()){
                throw new RuntimeException("not valide insert");
            }
            int idOrdine = rs.getInt("id");

            for(String key : names.keySet()) {
                HttpSession session = request.getSession();
                session.setAttribute("ordini", getOrdiniByEmail(""));
                double price = Integer.parseInt(request.getParameter(key)) * farmaci.get(key).getPrezzo();
                prezzoTotale = prezzoTotale + price;
                query = "UPDATE farmaci SET quantita = " + (farmaci.get(key).getQuantita() - Integer.parseInt(request.getParameter(key))) + " WHERE id = " + key;
                System.out.println(query);
                stmt.executeUpdate(query);

                query = " INSERT INTO ordine_farmaci (id_ordine, id_famaco, quantita) VALUE ("
                        + idOrdine +", "+ farmaci.get(key).getId()+" , " + Integer.parseInt(request.getParameter(key)) +")";

                stmt.executeUpdate(query);
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher("/client/ordini-cliente.jsp").forward(request, response);
    }


    private List<OrdineModel> getOrdiniByEmail(String email){

        Map<Integer, OrdineModel> ordini = new TreeMap<>();
        String query = "SELECT o.id, orf.id_famaco, f.nome, orf.quantita    " +
                " FROM ordine o " +
                " INNER JOIN ordine_farmaci orf  ON  orf.id_ordine= o.id " +
                " INNER JOIN farmaci f  ON  f.id = orf.id_famaco " +
                " WHERE o.email_user = '" + email+"'" ;


        //Map<String, Farmaco> farmaci = new TreeMap<>();
        try {
            SqlConn sql = new SqlConn(query);
            ResultSet rs = sql.execute();
            while(rs != null && rs.next()){
                Farmaco farmaco = new Farmaco();
                farmaco.setId(rs.getInt("id"));
                farmaco.setNome(rs.getString("nome"));
                //farmaco.setPrezzo(rs.getInt("prezzo"));
                farmaco.setQuantita(rs.getInt("quantita"));
                int idOrdine = rs.getInt("id");
                OrdineModel tmp = ordini.get(idOrdine);
                if(tmp == null){
                     tmp = new OrdineModel();
                     tmp.setNumero("N:"+idOrdine);
                     ordini.put(idOrdine, tmp);
                     System.out.println(tmp.getNumero());
                }
                tmp.getFarmaci().add(farmaco);
            }
            sql.freeSql();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        return ordini.values().stream().collect(Collectors.toList());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String email  = request.getSession().getAttribute("email").toString();
        request.getSession().setAttribute("ordini", this.getOrdiniByEmail(email));
        request.getRequestDispatcher("/client/ordini-cliente.jsp").forward(request, response);
    }

}



