package br.com.prova.poo.prova_maven.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

	private static String usuario = "SA";
	private static String senha = "";
	private static String PathBase = "C:\\Users\\andre\\eclipse-workspace\\prova-maven\\Dados";
	private static String URL = "jdbc:hsqldb:file:" + PathBase + ";";
	
	
	public static Connection conectar() {
		try {
			System.out.println("Conectou");
			return DriverManager.getConnection(URL, usuario, senha);
			
		} catch (SQLException e) {
			System.out.println("Não conectou");
			throw new Error("SQLException");
		}
	}
	
}
