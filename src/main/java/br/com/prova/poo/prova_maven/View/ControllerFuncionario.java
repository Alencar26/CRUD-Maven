package br.com.prova.poo.prova_maven.View;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import br.com.prova.poo.prova_maven.DAO.FuncionarioDAO;
import br.com.prova.poo.prova_maven.Model.Funcionario;
import br.com.prova.poo.prova_maven.db.dbConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerFuncionario extends Application implements Initializable{

	
	
	//IMPLEMENTAR AQUI!
	
	@FXML
    private TextField txtID;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtFuncao;

    @FXML
    private TextField txtSalario;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private TextField txtBuscaID;

    @FXML
    private Button btnBusca;

    @FXML
    private TableView<Funcionario> tabela;

    @FXML
    private TableColumn<Funcionario, String> ColunaID;

    @FXML
    private TableColumn<Funcionario, String> ColunaNome;

    @FXML
    private TableColumn<Funcionario, String> ColunaFuncao;

    @FXML
    private TableColumn<Funcionario, String> ColunaSalario;
	
    private ObservableList<Funcionario> observableListValores;
    
    @FXML
    void Adicionar(ActionEvent event) {

    	try(Connection con = dbConnection.conectar()){
    		
    		Funcionario funcionario = pegaDados();
    		int retorno = new FuncionarioDAO(con).inserir(funcionario);
    		limparCampos();
    		ConfirmaSelecao();
    		ListaFuncionarios();
    		System.out.println(retorno);
    		
    	}catch(SQLException e){
    		e.printStackTrace();
    		System.out.println("NÃO ADICIONOU");
    	}
    	
    }

    @FXML
    void Buscar(ActionEvent event) {

    	int id = Integer.parseInt(txtBuscaID.getText());
    	
    	Funcionario funcionario = null;
    	if(id != 0) {
    		
    		try(Connection con = dbConnection.conectar()){
        		
    			funcionario = new FuncionarioDAO(con).findById(id);
        		
        	}catch(SQLException e){
        		e.printStackTrace();
        		System.out.println("NÃO BUSCOU");
        	}
    	
    		if(funcionario != null) {
    			
    			txtID.setText(funcionario.getId()+"");
    	    	txtNome.setText(funcionario.getNome());
    	    	txtFuncao.setText(funcionario.getFuncao());
    	    	txtSalario.setText(funcionario.getSalario()+"");
    	    	
    		}else {
    			System.out.println("ALGO NÃO ESTÁ CERTO");
    		}
    		
    	}
    	
    	
    }

    @FXML
    void Editar(ActionEvent event) {

    	try(Connection con = dbConnection.conectar()){
    		
    		Funcionario funcionario = pegaDados();
    		int retorno = new FuncionarioDAO(con).alterar(funcionario);
    		System.out.println(retorno);
    		limparCampos();
    		ConfirmaSelecao();
    		ListaFuncionarios();
    		
    	}catch(SQLException e){
    		e.printStackTrace();
    		System.out.println("NÃO ALTEROU");
    	}
    	
    }

    @FXML
    void Excluir(ActionEvent event) {

    	int id = Integer.parseInt(txtID.getText());
    	
    	try(Connection con = dbConnection.conectar()){
    		
    		FuncionarioDAO dao = new FuncionarioDAO(con);
    		dao.excluir(id);
    		System.out.println("Excluido");
    		limparCampos();
    		ConfirmaSelecao();
    		ListaFuncionarios();
    		
    	}catch(SQLException e){
    		e.printStackTrace();
    		System.out.println("NÃO DELETOU");
    	}
    	
    }
    
    private void limparCampos() {
    	txtID.clear();
    	txtNome.clear();
    	txtFuncao.clear();
    	txtSalario.clear();
    	txtBuscaID.clear();
    }

    private void ListaFuncionarios() {

    	try(Connection con = dbConnection.conectar()){
    		
    		ColunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        	ColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        	ColunaFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        	ColunaSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
    		
    		List<Funcionario> listaFuncionarios = new FuncionarioDAO(con).listarTodos();
    		observableListValores = FXCollections.observableArrayList(listaFuncionarios);
        	
        	tabela.setItems(observableListValores);
    	}catch(SQLException e){
    		e.printStackTrace();
    		System.out.println("NÃO LISTOU");
    	}
    	
    }
    
    private void FuncionarioSelecionado(Funcionario f) {
    	
    	System.out.println("Funcionario selecionado: "+ f);
    	txtID.setText(f.getId()+"");
    	txtNome.setText(f.getNome());
    	txtFuncao.setText(f.getFuncao());
    	txtSalario.setText(f.getSalario()+"");
    	
		
    }
    
    private void ConfirmaSelecao() {
    	
    	tabela.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> FuncionarioSelecionado(newValue));
    }
    
    private Funcionario pegaDados() {
    	
    	int id = Integer.parseInt(txtID.getText());
    	int salario = Integer.parseInt(txtSalario.getText());
    	
    	return new Funcionario(id, txtNome.getText(), txtFuncao.getText(), salario);
    }
    
	
    @Override
    public void start(Stage stage) {
    	
    	try {
    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Funcionario.fxml"));
    		stage.setTitle("Inicial");
    		Scene sc = new Scene(pane);
    		stage.setScene(sc);
    		stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	 
    } 
    
    public void execute() {
    	launch();
    }
    
    public static void main(String[] args) {
		launch();
	}
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	ListaFuncionarios();
    	ConfirmaSelecao();
	}
	
	
}
