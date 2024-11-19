package model;

import java.time.LocalDate;

public class FarmacoModel {
    private Long id;
    private String nome;
    private String descrizione;
    private int quantita_disponibile;
    private int quantita_presso;
    private int prezzo;
    private LocalDate data_scadenza;

    public FarmacoModel() {

    }

    public FarmacoModel(Long id, String nome, String descrizione, int quantita_disponibile, int prezzo, LocalDate data_scadenza) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.quantita_disponibile = quantita_disponibile;
        this.prezzo = prezzo;
        this.data_scadenza = data_scadenza;
        this.quantita_presso = 0;
    }

    public int getQuantita_presso() {
        return quantita_presso;
    }

    public void setQuantita_presso(int quantita_presso) {
        this.quantita_presso = quantita_presso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getQuantita_disponibile() {
        return quantita_disponibile;
    }

    public void setQuantita_disponibile(int quantita_disponibile) {
        this.quantita_disponibile = quantita_disponibile;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
    }

    public void setData_scadenza(LocalDate data_scadenza) {
        this.data_scadenza = data_scadenza;
    }
}
