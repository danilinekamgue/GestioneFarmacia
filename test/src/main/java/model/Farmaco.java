package model;

import java.sql.Date;

public class Farmaco {
	
	private int id;
	private String nome;
	private String descrizione;
	
	public Farmaco(int id, String nome, String descrizione) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		
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
	
	
	
	
	

}
