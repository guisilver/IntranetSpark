package br.com.oma.intranet.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.managedbeans.SessaoMB;

public class PapercutJdbcDAO {

	private PreparedStatement pst;
	private Connection con;
	private ResultSet rs;
	
	public void salvarCobrancaServico(int codigoMsvcon,int codigoDoServico, double qtdaImpressao, String historico, Date dataProcessamento, int condominio, SessaoMB sessao) {
		try {
			String insert = "insert into [sigadm].[dbo].[cndsvmov] (codigo, cod_svcon, qtde, historico, data) values (?,?,?,?,?)";
			this.con = Conexao.getConexaoSiga();
			this.pst = this.con.prepareStatement(insert);
			this.pst.setInt(1, codigoMsvcon);
			this.pst.setInt(2, codigoDoServico);
			this.pst.setDouble(3, qtdaImpressao);
			this.pst.setString(4, historico);
			this.pst.setDate(5, new java.sql.Date(dataProcessamento.getTime()));
			this.pst.executeUpdate();
			
			String insertLog = "INSERT INTO [omacorp].[dbo].[intra_log_geral]([alterar],"
					+ "[aprovado_por] ,[condominio],[data_aprovacao],[data_feito],"
					+ "[deletar] ,[feito_por],[info_anterior],[inserir],[ip],[nro_lancto],[tabela])VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			this.con = Conexao.getConexao();
			this.pst = this.con.prepareStatement(insertLog);
			this.pst.setBoolean(1, false);
			this.pst.setString(2, "");
			this.pst.setInt(3, condominio);
			this.pst.setDate(4, new java.sql.Date(new Date().getTime()));
			this.pst.setDate(5, new java.sql.Date(new Date().getTime()));
			this.pst.setBoolean(6, false);
			this.pst.setString(7, sessao.getUsuario().getEmail());
			this.pst.setString(8, "Insert Papercut codigo:" + codigoMsvcon);
			this.pst.setBoolean(9, true);
			this.pst.setString(10, sessao.getIpUser());
			this.pst.setInt(11, 0);
			this.pst.setString(12, "sigadm.dbo.cndsvmov");
					
			this.pst.execute();
	
			
			this.pst.close();
			this.con.close();
					
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("insert na cndsvmov sistema Papercut "+e.getMessage());
		
		}
	}
	
	public int retornaCodigoDoServico(int condominio, double tipoDoEmissor) {
		int valor = 0;
		try {
			this.con = Conexao.getConexaoSiga();
			String sql = "select codigo from sigadm.dbo.cndsvcon where condominio = ? and codigo_serv = ?";
			this.con = Conexao.getConexaoSiga();
			this.pst = this.con.prepareStatement(sql);
			this.pst.setInt(1, condominio);
			this.pst.setDouble(2, tipoDoEmissor);
			this.rs = this.pst.executeQuery();
			while (this.rs.next()) {
				valor = this.rs.getInt("codigo");
			}
			this.con.close();
			this.pst.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("listar na cndsvcon sistema sigadm " + e.getMessage());

		}
		return valor;
	}
	
	public int retornaCodigoMsvcon(int qtdaLinhas) {
		int retorno = 0;
		try {
			String consulta = "select ult_svmov from [sigadm].[dbo].[cndmonit]";
			this.con = Conexao.getConexaoSiga();
			this.pst = con.prepareStatement(consulta);
			this.rs = this.pst.executeQuery();
			while(this.rs.next()){
				if(this.rs.getInt("ult_svmov") > 0){
					retorno = this.rs.getInt("ult_svmov");
					retorno += 1;
					String update = "update sigadm.dbo.cndmonit set ult_svmov = " +(retorno+qtdaLinhas);
					this.pst = this.con.prepareStatement(update);
					this.pst.executeUpdate();
				}
			}
			this.pst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("insert na cndmonit sistema Papercut "+e.getMessage());
		}
		return retorno;
	}
}
