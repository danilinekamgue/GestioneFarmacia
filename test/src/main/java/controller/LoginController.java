package controller;

import Service.SqlConn;
import config.DbConfig;
import config.DbInfo;
import model.Farmaco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();


        String role = null;

        String query = "SELECT nome, role  " +
                " FROM users" +
                " WHERE email = '" + email + "'" +
                " AND password= '" + password + "'"
                ;
        try {
            SqlConn sql = new SqlConn(query);
            ResultSet rs = sql.execute();
            if(rs != null && rs.next()){
                role = rs.getString("role");
                session.setAttribute("isLogged", true);
                session.setAttribute("role",role);
                session.setAttribute("email",email);
                session.setAttribute("nome",rs.getString("nome"));
            }
            else{
                sql.freeSql();
                request.getSession().setAttribute("errorMessage", "Credenziali sbagliati!");
                response.sendRedirect(request.getContextPath() + "");
                return;
            }
            sql.freeSql();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        if(role.equals("admin")){
            response.sendRedirect(request.getContextPath() + "/jsp/admin.jsp");
            return;
        }

        // IF LOGIN IS GOOD, REDIRECT TO ADMIN PAGE OR CLIENT PAGE
        System.out.println("Home controlllll");
        // request.getRequestDispatcher("/jsp/admin.jsp").forward(request, response);



        /*   List<FarmacoModel> farmaci = new ArrayList<>();
        farmaci.add(new FarmacoModel(1L, "nooo","ndofhjgjhgjgjdf",2, 2, LocalDate.now()));
        farmaci.add(new FarmacoModel(2L, "nooo","ndojhgjhjhgjfdf",2, 2, LocalDate.now()));

        System.out.println("je suis cicici " + farmaci.size());
        //request.setAttribute("farmaci", farmaci);
        // this change the url
        // HttpSession session = request.getSession();
        session.setAttribute("farmaci", farmaci);
        response.sendRedirect(request.getContextPath() + "/client/client.jsp");
       // response.sendRedirect(request.getContextPath() + "/jsp/admin.jsp");
        return;
    }*/
   
        Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	  List<Farmaco> farmaci = new ArrayList<>();
        try  {
        	
        	DbInfo db = DbConfig.getDbConfig();

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            String query1 = "SELECT * FROM farmaci";
            stmt = conn.prepareStatement(query1);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Farmaco farmaco = new Farmaco();
                farmaco.setId(rs.getInt("id"));
                farmaco.setNome(rs.getString("nome"));
                farmaco.setDescrizione(rs.getString("descrizione"));
                farmaco.setPrezzo(rs.getInt("prezzo"));
                farmaco.setQuantita(rs.getInt("quantita"));

                farmaci.add(farmaco);
                System.out.println(farmaci);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("farmaci", farmaci);
        response.sendRedirect(request.getContextPath() + "/client/client.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
