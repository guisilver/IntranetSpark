package br.com.oma.intranet.filters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.oma.intranet.managedbeans.Config;

public class Conexao {

	private static Config config = new Config(new ConexaoGeral().getBanco());

	private static String driver = config.Conf().getProperty("jdbc.server.driver");
	private static String user = config.Conf().getProperty("jdbc.server.user");
	private static String pass = config.Conf().getProperty("jdbc.server.password");
	private static String url = config.Conf().getProperty("jdbc.server.omacorp.url");
	private static String urlSiga = config.Conf().getProperty("jdbc.server.siga.url");
	private static String urlOmaonline = config.Conf().getProperty("jdbc.server.omaonline.url");
	private static String urlSigaDocumentos = config.Conf().getProperty("jdbc.server.siga.urldocumentos");
	private static String urlPapercut = config.Conf().getProperty("jdbc.server.papercut.url");
	
	private static String urlSIP = config.Conf().getProperty("jdbc.server.sip_teste.url");

	public static Connection getConexao()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection con;
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
		return con;
	}
	
	public static Connection getConexaoOmaonline()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection con;
		Class.forName(driver);
		con = DriverManager.getConnection(urlOmaonline, user, pass);
		return con;
	}

	public static Connection getConexaoSigaDocumentos()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection con;
		Class.forName(driver);
		con = DriverManager.getConnection(urlSigaDocumentos, user, pass);
		return con;
	}

	public static Connection getConexaoSiga()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection con;
		Class.forName(driver);
		con = DriverManager.getConnection(urlSiga, user, pass);
		return con;
	}
	
	public static Connection getConexaoSIP()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection con;
		Class.forName(driver);
		con = DriverManager.getConnection(urlSIP, user, pass);
		return con;
	}

	public static Connection getConexaoPapercut()
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		Connection con;
		Class.forName(driver);
		con = DriverManager.getConnection(urlPapercut, user, pass);
		return con;
	}

}
