/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionefarmacia;
import com.mycompany.gestionefarmacia.dao.FarmacoDAO;
import com.mycompany.gestionefarmacia.dao.DatabaseConnection;
import com.mycompany.gestionefarmacia.model.Farmaco;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author pc
 */
public class GestioneFarmacia {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Essayer de se connecter à la base de données
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connection réussie !");

            // Initialisation de FarmacoDAO
            FarmacoDAO farmacoDAO = new FarmacoDAO(connection);

            // Ajouter un nouveau médicament de test
            Farmaco farmaco = new Farmaco(0, "tardiferro", "Antidolorifico e antinfiammatorio", 50, 4.99, java.sql.Date.valueOf("2025-05-10"), "Farmacia Generale");

            // Vérification des valeurs avant d'ajouter à la base de données
            System.out.println("Nom: " + farmaco.getNome());
            System.out.println("Description: " + farmaco.getDescrizione());
            System.out.println("Quantité: " + farmaco.getQuantita());
            System.out.println("Prix: " + farmaco.getPrezzo());
            System.out.println("Date d'expiration: " + farmaco.getDataScadenza());
            System.out.println("Fournisseur: " + farmaco.getFornitore());

            farmacoDAO.aggiungiFarmaco(farmaco);
            System.out.println("Farmaco aggiunto con successo!");

            // Afficher tous les médicaments
            System.out.println("Elenco dei farmaci disponibili:");
            List<Farmaco> farmaci = farmacoDAO.getFarmaci();

            if (farmaci.isEmpty()) {
                System.out.println("Nessun farmaco trovato.");
            } else {
                for (Farmaco f : farmaci) {
                    System.out.println(f.getNome() + " - Quantità: " + f.getQuantita());
                }
            }
        } catch (SQLException e) {
            // Si la connexion échoue ou une autre exception SQL se produit, elle est capturée ici
            System.out.println("Erreur de connexion à la base de données.");
            e.printStackTrace();
        }
    }
}
