/**
 * 
 */
package br.com.rpires.dao.generic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author rodrigo.pires
 *
 */
public class ConnectionFactory {

	private static Connection connection;

	private ConnectionFactory(Connection connection) {

	}

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = initConnection();
			return connection;
		} else if (connection.isClosed()) {
			connection = initConnection();
			return connection;
		} else {
			return connection;
		}
	}

	private static Connection initConnection() {
		try {
			// garante que o driver do PostgreSQL seja carregado
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/vendas_online_2",
					"postgres",
					"Floresta"
			);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver do PostgreSQL não encontrado!", e);
		} catch (SQLException e) {
			throw new RuntimeException("Erro conectando ao banco de dados!", e);
		}

	}
}