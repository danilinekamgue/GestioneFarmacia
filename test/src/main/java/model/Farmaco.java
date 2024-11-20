package model;

import java.sql.Date;

public class Farmaco {
	
	private int id;
	private String nome;
	private String descrizione;
	private int quantita;
	private int prezzo;
	
	public Farmaco(int id, String nome, String descrizione,int quantita,int prezzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	public Farmaco() {
		super();
		
	}
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
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	public double calcolaPrezzoTotale() {
	    return prezzo * quantita;
	}
	@Override
	public String toString() {
		return "Farmaco [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", quantità=" + quantita
				+ ", prezzo=" + prezzo + "]";
	}
	
	
	

}
