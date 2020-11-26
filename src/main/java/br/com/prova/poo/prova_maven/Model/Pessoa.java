package br.com.prova.poo.prova_maven.Model;

public abstract class Pessoa {

	private String nome;

	public Pessoa() {
		
	}
	
	public Pessoa(String Nome) {
			this.nome = Nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + "]";
	}
	
}
