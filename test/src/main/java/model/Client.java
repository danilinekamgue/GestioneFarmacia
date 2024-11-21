package model;

public class Client {
	
	private String email;
	private String nome;
	private String password;
	private String role;
	public Client(String email, String nome) {
		super();
		this.email = email;
		this.nome = nome;
	}
	public Client(String email, String nome, String role, String password) {
		super();
		this.email = email;
		this.nome = nome;
		this.role = role;
		this.password = password;
	}
	
	public Client() {
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Client [email=" + email + ", nome=" + nome + ", password=" + password + ", role=" + role + "]";
	}
	
   
	
}
