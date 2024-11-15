/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionefarmacia;
import com.mycompany.gestionefarmacia.dao.FarmacoDAO;
import com.mycompany.gestionefarmacia.dao.DatabaseConnection;
import com.mycompany.gestionefarmacia.model.Farmaco;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author pc
 */
public class GestioneFarmacia {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try (Connection connection = DatabaseConnection.getConnection()) {
            FarmacoDAO farmacoDAO = new FarmacoDAO(connection);

            // Aggiungi un nuovo farmaco di prova
            Farmaco farmaco = new Farmaco();
            farmaco.setNome("Ibuprofene");
            farmaco.setDescrizione("Antidolorifico e antinfiammatorio");
            farmaco.setQuantita(50);
            farmaco.setPrezzo(4.99);
            farmaco.setDataScadenza(java.sql.Date.valueOf("2025-05-10"));
            farmaco.setFornitore("Farmacia Generale");

            farmacoDAO.aggiungiFarmaco(farmaco);
            System.out.println("Farmaco aggiunto con successo!");

            // Visualizza tutti i farmaci
            System.out.println("Elenco dei farmaci disponibili:");
            for (Farmaco f : farmacoDAO.getFarmaci()) {
                System.out.println(f.getNome() + " - Quantità: " + f.getQuantita());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
