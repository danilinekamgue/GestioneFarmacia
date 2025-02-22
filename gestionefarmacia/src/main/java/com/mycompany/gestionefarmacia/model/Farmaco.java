/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionefarmacia.model;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class Farmaco {

   
    private int id;
    private String nome;
    private String descrizione;
    private int quantita;
    private double prezzo;
    private java.sql.Date dataScadenza;
    private String fornitore;
    
        // Constructeur sans paramètres
    public Farmaco() {
        // Valeurs par défaut
    }
    
    public Farmaco(int id, String nome, String descrizione, int quantita, double prezzo, Date dataScadenza, String fornitore) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.dataScadenza = dataScadenza;
        this.fornitore = fornitore;
    }


    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public java.sql.Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(java.sql.Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getFornitore() {
        return fornitore;
    }

    public void setFornitore(String fornitore) {
        this.fornitore = fornitore;
    }
}

