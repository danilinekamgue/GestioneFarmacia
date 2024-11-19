package model;

import java.sql.Date;

public class Farmaco {
	
	private int id;
	private String nome;
	private String descrizione;
	private int quantità;
	private int prezzo;
	
	public Farmaco(int id, String nome, String descrizione,int quantità,int prezzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.quantità = quantità;
		this.prezzo = prezzo;
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
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	public double calcolaPrezzoTotale() {
	    return prezzo * quantità;
	}
	@Override
	public String toString() {
		return "Farmaco [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", quantità=" + quantità
				+ ", prezzo=" + prezzo + "]";
	}
	
	
	

}
