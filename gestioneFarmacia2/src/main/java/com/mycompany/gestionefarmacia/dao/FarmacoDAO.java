/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionefarmacia.dao;

/**
 *
 * @author pc
 */
import com.mycompany.gestionefarmacia.model.Farmaco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmacoDAO {
    private Connection connection;

    public FarmacoDAO(Connection connection) {
        this.connection = connection;
    }

    // Aggiungere un nuovo farmaco
    public void aggiungiFarmaco(Farmaco farmaco) throws SQLException {
        String query = "INSERT INTO Farmaco (nome, descrizione, quantita, prezzo, data_scadenza, fornitore) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, farmaco.getNome());
            stmt.setString(2, farmaco.getDescrizione());
            stmt.setInt(3, farmaco.getQuantita());
            stmt.setDouble(4, farmaco.getPrezzo());
            stmt.setDate(5, farmaco.getDataScadenza());
            stmt.setString(6, farmaco.getFornitore());
            stmt.executeUpdate();
        }
    }

    // Ottenere tutti i farmaci
    public List<Farmaco> getFarmaci() throws SQLException {
        List<Farmaco> farmaci = new ArrayList<>();
        String query = "SELECT * FROM Farmaco";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Farmaco farmaco = new Farmaco();
                farmaco.setId(rs.getInt("id"));
                farmaco.setNome(rs.getString("nome"));
                farmaco.setDescrizione(rs.getString("descrizione"));
                farmaco.setQuantita(rs.getInt("quantita"));
                farmaco.setPrezzo(rs.getDouble("prezzo"));
                farmaco.setDataScadenza(rs.getDate("data_scadenza"));
                farmaco.setFornitore(rs.getString("fornitore"));
                farmaci.add(farmaco);
            }
        }
        return farmaci;
    }

    // Altri metodi CRUD (update, delete) possono essere aggiunti in modo simile.
}

