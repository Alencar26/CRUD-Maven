package br.com.prova.poo.prova_maven.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.prova.poo.prova_maven.Model.Funcionario;


public class FuncionarioDAO {
	
	private Connection connection;
	
	public FuncionarioDAO(Connection con) {
		this.connection = con;
	}

	final String SQL_INSERT_FUNCIONARIO = "INSERT INTO FUNCIONARIO(ID, NOME, FUNCAO, SALARIO) VALUES(?, ?, ?, ?)";
	final String SQL_SELECT_FUNCIONARIO_WHERE_ID = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
	final String SQL_DELETE_FUNCIONARIO = "DELETE FROM FUNCIONARIO WHERE ID = ?";
	final String SQL_UPDATE_FUNCIONARIO_ID = "UPDATE FUNCIONARIO SET NOME = ?, FUNCAO = ?, SALARIO = ? WHERE ID = ?";
	final String SQL_SELECT_TODOS_FUNCIONARIO = "SELECT * FROM FUNCIONARIO";
	
	public int inserir(Funcionario funcionario) {
		
		int quantidade = 0;
		
		try(PreparedStatement pst = connection.prepareStatement(SQL_INSERT_FUNCIONARIO);){
			
			pst.setInt(1, funcionario.getId());
			pst.setString(2, funcionario.getNome());
			pst.setString(3, funcionario.getFuncao());
			pst.setInt(4, funcionario.getSalario());

			
			
			quantidade = pst.executeUpdate();
			System.out.println("Adicionando Funcionário: "+ funcionario.toString());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return quantidade;
	}
	
	
	public Funcionario findById(int id) {
		
		Funcionario funcionario = null;
		
		try(PreparedStatement pst = connection.prepareStatement(SQL_SELECT_FUNCIONARIO_WHERE_ID);){
			
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				funcionario = new Funcionario();
				
				funcionario.setId(rs.getInt("ID"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setFuncao(rs.getString("FUNCAO"));
				funcionario.setSalario(rs.getInt("SALARIO"));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}
	
	public void excluir(int id) {
		try(PreparedStatement pst = connection.prepareStatement(SQL_DELETE_FUNCIONARIO);){
			
			pst.setFloat(1, id);
			pst.execute();
			System.out.println("Sucesso!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int alterar(Funcionario funcionario) {
		int quantidade = 0;
		
		if(funcionario != null) {
		
		try(PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_FUNCIONARIO_ID);){
			
			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getFuncao());
			pst.setInt(3, funcionario.getSalario());
			pst.setInt(4, funcionario.getId());
			
			quantidade = pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
		return quantidade;
	
	}
	
	public List<Funcionario> listarTodos() {
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		
		
		
		try(PreparedStatement pst = connection.prepareStatement(SQL_SELECT_TODOS_FUNCIONARIO);){
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Funcionario funcionario = new Funcionario();
				
				funcionario.setId(rs.getInt("ID"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setFuncao(rs.getString("FUNCAO"));
				funcionario.setSalario(rs.getInt("SALARIO"));
				
				
				listaFuncionario.add(funcionario);
			
				
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaFuncionario;
		
		
	}
	
	
	
}
