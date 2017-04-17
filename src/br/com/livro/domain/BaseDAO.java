package br.com.livro.domain;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class BaseDAO {


	public BaseDAO() {

		try {
			// necessario para utilizar o driver JDBC do MySql

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException ex) {
			// erro de driver JDBC
			// adicione o driver .jar do Mysql em /web-inf/lib

			ex.printStackTrace();
		}
	}

	protected Connection getConnection() throws SQLException {
		// url de conexao com o BD
		String url = "jdbc:mysql://localhost/livro";
		// conecta utilizando a url, user e senha

		Connection conn = DriverManager.getConnection(url, "livro", "livro123");
		return conn;
	}

		public static void main(String[] args) throws SQLException {
		BaseDAO db = new BaseDAO();
		// testa conexao

		 Connection conn = db.getConnection();
		System.out.println("Conectado: "+ conn);
	}

}
