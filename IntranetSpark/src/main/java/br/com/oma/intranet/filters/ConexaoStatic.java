package br.com.oma.intranet.filters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoStatic {

	public static Connection getConexaoService()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		String banco = ConexaoGeral.banco;
		Connection con = null;
		if (banco.equals("local")) {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://192.1.5.134:1433;databaseName=omacorp", "sa",
					"paralela");
		} else {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://192.1.7.6:1433;databaseName=omacorp", "sa", "paralela");
		}
		return con;
	}
}
