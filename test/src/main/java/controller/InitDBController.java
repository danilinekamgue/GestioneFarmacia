package controller;

import config.DbConfig;
import config.DbInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/init-db")
public class InitDBController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        String sqlScript0 = " CREATE DATABASE IF NOT EXISTS FARMACIA ; ";


        String sqlScript1 =
                " CREATE TABLE IF NOT EXISTS users ( \n" +
                " email varchar(100) PRIMARY KEY NOT NULL, \n" +
                " nome varchar(100),\n" +
                " password varchar(100),\n" +
                " role varchar(100)\n" +
                ") ;\n";


        String sqlScript2 = " CREATE TABLE IF NOT EXISTS farmaci (\n" +
                " id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                " nome varchar(100) ,\n" +
                " descrizione varchar(100),\n" +
                " prezzo double,\n" +
                " quantita int\n" +
                ");\n";

        String sqlScript3 = " CREATE TABLE IF NOT EXISTS ordine (\n" +
                " id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                " email_user varchar(100),\n" +
                " prezzo double,\n" +
                " consegnato boolean,\n" +
                " data VARCHAR(30),\n" +
                " FOREIGN KEY (email_user) REFERENCES users(email)\n" +
                ");\n" ;

        String sqlScript4 = "CREATE TABLE IF NOT EXISTS ordine_farmaci (\n" +
                " id_ordine int NOT NULL,\n" +
                " id_famaco int NOT NULL,\n" +
                " quantita int,\n" +
                " FOREIGN KEY (id_famaco) REFERENCES farmaci(id),\n" +
                " FOREIGN KEY (id_ordine) REFERENCES ordine(id),\n" +
                " CONSTRAINT pk_farmaco_ordine PRIMARY KEY (id_famaco, id_ordine)\n" +
                ");\n" ;

        String sqlScript5 = "INSERT INTO users (email, nome, password, role) VALUE \n" +
                "('admin@admin.it', 'DANI', 'admin', 'admin');  ";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            // Connexion à la base de données
            DbInfo db = DbConfig.getDbConfig();

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            stmt = conn.createStatement();

            stmt.executeUpdate(sqlScript0);

            // USE THESE SCRIPT TO CLEAN TABLES
            //stmt.executeUpdate("DROP TABLE ordine_farmaci;");
            //stmt.executeUpdate("DROP TABLE ordine;");
            //stmt.executeUpdate("DROP TABLE users;");
            //stmt.executeUpdate("DROP TABLE farmaci;");

            stmt.executeUpdate(sqlScript1);
            stmt.executeUpdate(sqlScript2);
            stmt.executeUpdate(sqlScript3);
            stmt.executeUpdate(sqlScript4);


            rs = stmt.executeQuery("SELECT email FROM users WHERE email ='admin@admin.it'");
            if(!rs.next()){
                stmt.executeUpdate(sqlScript5);
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
    }
}
