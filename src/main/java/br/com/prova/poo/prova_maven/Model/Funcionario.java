package br.com.prova.poo.prova_maven.Model;

public class Funcionario extends Pessoa {

	private int id;
	private String funcao;
	private int salario;
	
	
	public Funcionario() {
		
	}
	public Funcionario(int Id, String Nome, String Funcao, int Salario) {
		
		super(Nome);
		this.id = Id;
		this.funcao = Funcao;
		this.salario = Salario;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", funcao=" + funcao + ", salario=" + salario + ", getNome()=" + super.getNome()
				+ "]";
	}
	
	
}
