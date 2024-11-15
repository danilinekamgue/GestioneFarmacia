/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionefarmacia.dao;

/**
 *
 * @author pc
 */

import com.mycompany.gestionefarmacia.model.Ordine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
    private Connection connection;

    public OrdineDAO(Connection connection) {
        this.connection = connection;
    }

    // Aggiungere un nuovo ordine
    public void aggiungiOrdine(Ordine ordine) throws SQLException {
        String query = "INSERT INTO Ordine (id_cliente, data_ordine, stato) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ordine.getIdCliente());
            stmt.setDate(2, ordine.getDataOrdine());
            stmt.setString(3, ordine.getStato());
            stmt.executeUpdate();
        }
    }

    // Ottenere un ordine per ID
    public Ordine getOrdineById(int id) throws SQLException {
        String query = "SELECT * FROM Ordine WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Ordine ordine = new Ordine();
                    ordine.setId(rs.getInt("id"));
                    ordine.setIdCliente(rs.getInt("id_cliente"));
                    ordine.setDataOrdine(rs.getDate("data_ordine"));
                    ordine.setStato(rs.getString("stato"));
                    return ordine;
                }
            }
        }
        return null; // Ordine non trovato
    }

    // Ottenere tutti gli ordini
    public List<Ordine> getOrdini() throws SQLException {
        List<Ordine> ordini = new ArrayList<>();
        String query = "SELECT * FROM Ordine";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Ordine ordine = new Ordine();
                ordine.setId(rs.getInt("id"));
                ordine.setIdCliente(rs.getInt("id_cliente"));
                ordine.setDataOrdine(rs.getDate("data_ordine"));
                ordine.setStato(rs.getString("stato"));
                ordini.add(ordine);
            }
        }
        return ordini;
    }

    // Aggiornare uno stato dell'ordine
    public void aggiornaStatoOrdine(int id, String nuovoStato) throws SQLException {
        String query = "UPDATE Ordine SET stato = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuovoStato);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // Eliminare un ordine
    public void eliminaOrdine(int id) throws SQLException {
        String query = "DELETE FROM Ordine WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

