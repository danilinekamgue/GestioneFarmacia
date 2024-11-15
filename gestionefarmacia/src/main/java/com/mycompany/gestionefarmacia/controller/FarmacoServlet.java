/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.mycompany.gestionefarmacia.controller;

import com.mycompany.gestionefarmacia.dao.FarmacoDAO;
import com.mycompany.gestionefarmacia.dao.DatabaseConnection;
import com.mycompany.gestionefarmacia.model.Farmaco;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
//import javax.servlet.RequestDispatcher;

@WebServlet(name="FarmacoServlet", urlPatterns="/controller/Farmaco") 
public class FarmacoServlet extends HttpServlet {

    private FarmacoDAO farmacoDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            farmacoDAO = new FarmacoDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("Errore di connessione al database", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action == null ? "list" : action) {
                case "new":
                    mostraFormAggiungiFarmaco(request, response);
                    break;
                case "edit":
                    mostraFormModificaFarmaco(request, response);
                    break;
                case "delete":
                    eliminaFarmaco(request, response);
                    break;
                default:
                    listaFarmaci(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("insert".equals(action)) {
                aggiungiFarmaco(request, response);
            } else if ("update".equals(action)) {
                aggiornaFarmaco(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listaFarmaci(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Farmaco> listaFarmaci = farmacoDAO.getFarmaci();
        request.setAttribute("listaFarmaci", listaFarmaci);
        request.getRequestDispatcher("/jsp/farmaco-list.jsp").forward(request, response);
        System.out.println("..................Fournisseur");
        
        //test
        //request.getRequestDispatcher("/jsp/test.jsp").forward(request, response);
       // request.getRequestDispatcher("/test.jsp").forward(request, response);
        // Redirigez la requête vers le JSP farmaco-list.jsp
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/farmaco-list.jsp");
       // dispatcher.forward(request, response);
    }

    private void mostraFormAggiungiFarmaco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/farmaco-form.jsp").forward(request, response);
    }

    private void aggiungiFarmaco(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String fornitore = request.getParameter("fornitore");
        java.sql.Date dataScadenza = java.sql.Date.valueOf(request.getParameter("dataScadenza"));

        Farmaco farmaco;
        farmaco = new Farmaco(quantita, nome, descrizione, quantita, prezzo, dataScadenza, fornitore);
        farmacoDAO.aggiungiFarmaco(farmaco);
        //response.sendRedirect("farmaci?action=list");
        response.sendRedirect("controller/Farmaco?action=list");

    }

    private void mostraFormModificaFarmaco(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Farmaco farmacoEsistente = farmacoDAO.getFarmacoById(id);
        request.setAttribute("farmaco", farmacoEsistente);
        request.getRequestDispatcher("/jsp/farmaco-form.jsp").forward(request, response);
    }

    private void aggiornaFarmaco(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String fornitore = request.getParameter("fornitore");
        java.sql.Date dataScadenza = java.sql.Date.valueOf(request.getParameter("dataScadenza"));

        Farmaco farmaco = new Farmaco(id, nome, descrizione, quantita, prezzo, dataScadenza, fornitore);
        farmacoDAO.aggiornaFarmaco(farmaco);
        //response.sendRedirect("farmaci?action=list");
        response.sendRedirect("controller/Farmaco?action=list");

    }

    private void eliminaFarmaco(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        farmacoDAO.eliminaFarmaco(id);
        //response.sendRedirect("farmaci?action=list");
        response.sendRedirect("controller/Farmaco?action=list");

    }
    
    
}
