/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.mycompagny.gestionefarmacia.dao;

/**
 *
 * @author pc
 */
package com.mycompany.gestionefarmacia.dao;

import com.mycompany.gestionefarmacia.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Aggiungere un nuovo cliente
    public void aggiungiCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO Cliente (nome, cognome, indirizzo, telefono, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCognome());
            stmt.setString(3, cliente.getIndirizzo());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getEmail());
            stmt.executeUpdate();
        }
    }

    // Ottenere un cliente per ID
    public Cliente getClienteById(int id) throws SQLException {
        String query = "SELECT * FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCognome(rs.getString("cognome"));
                    cliente.setIndirizzo(rs.getString("indirizzo"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setEmail(rs.getString("email"));
                    return cliente;
                }
            }
        }
        return null; // Cliente non trovato
    }

    // Ottenere tutti i clienti
    public List<Cliente> getClienti() throws SQLException {
        List<Cliente> clienti = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCognome(rs.getString("cognome"));
                cliente.setIndirizzo(rs.getString("indirizzo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("email"));
                clienti.add(cliente);
            }
        }
        return clienti;
    }

    // Modificare un cliente
    public void aggiornaCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE Cliente SET nome = ?, cognome = ?, indirizzo = ?, telefono = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCognome());
            stmt.setString(3, cliente.getIndirizzo());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6, cliente.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminare un cliente
    public void eliminaCliente(int id) throws SQLException {
        String query = "DELETE FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

