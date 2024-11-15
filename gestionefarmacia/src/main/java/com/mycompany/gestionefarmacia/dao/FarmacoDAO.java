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
    String sql = "INSERT INTO Farmaco (nome, descrizione, quantita, prezzo, data_scadenza, fornitore) VALUES (?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, farmaco.getNome());
        stmt.setString(2, farmaco.getDescrizione());
        stmt.setInt(3, farmaco.getQuantita());
        stmt.setDouble(4, farmaco.getPrezzo());
        stmt.setDate(5, farmaco.getDataScadenza());
        stmt.setString(6, farmaco.getFornitore());
        
        stmt.executeUpdate();
    } catch (SQLException e) {
        // Gestion des erreurs
        throw e;
    }
}


    // Ottenere tutti i farmaci
public List<Farmaco> getFarmaci() throws SQLException {
    List<Farmaco> farmaci = new ArrayList<>();
    String query = "SELECT * FROM Farmaco";  // Assurez-vous que la table 'Farmaco' existe et contient des données
    try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            // Créez un nouvel objet Farmaco à chaque itération
            Farmaco farmaco = new Farmaco(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descrizione"),
                rs.getInt("quantita"),
                rs.getDouble("prezzo"),
                rs.getDate("data_scadenza"),
                rs.getString("fornitore")
            );
            farmaci.add(farmaco);  // Ajoutez l'objet Farmaco à la liste
        }
    }
    return farmaci;
}

    public Farmaco getFarmacoById(int id) throws SQLException {
    String query = "SELECT * FROM Farmaco WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Farmaco farmaco = new Farmaco();
                farmaco.setId(rs.getInt("id"));
                farmaco.setNome(rs.getString("nome"));
                farmaco.setDescrizione(rs.getString("descrizione"));
                farmaco.setQuantita(rs.getInt("quantita"));
                farmaco.setPrezzo(rs.getDouble("prezzo"));
                farmaco.setDataScadenza(rs.getDate("data_scadenza"));
                farmaco.setFornitore(rs.getString("fornitore"));
                return farmaco;
            }
        }
    }
    return null; // Retourne null si aucun médicament n'est trouvé
}

    public void aggiornaFarmaco(Farmaco farmaco) throws SQLException {
    String query = "UPDATE Farmaco SET nome = ?, descrizione = ?, quantita = ?, prezzo = ?, data_scadenza = ?, fornitore = ? WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, farmaco.getNome());
        stmt.setString(2, farmaco.getDescrizione());
        stmt.setInt(3, farmaco.getQuantita());
        stmt.setDouble(4, farmaco.getPrezzo());
        stmt.setDate(5, farmaco.getDataScadenza());
        stmt.setString(6, farmaco.getFornitore());
        stmt.setInt(7, farmaco.getId());
        stmt.executeUpdate();
    }
}

    public void eliminaFarmaco(int id) throws SQLException {
    String query = "DELETE FROM Farmaco WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}



    // Altri metodi CRUD (update, delete) possono essere aggiunti in modo simile.
}

