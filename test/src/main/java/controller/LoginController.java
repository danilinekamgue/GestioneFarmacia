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
            response.sendRedirect(request.getContextPath() + "/admin/admin-farmaci");
            return;
        }
        response.sendRedirect(request.getContextPath() + "/client/cliente-farmaci");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
