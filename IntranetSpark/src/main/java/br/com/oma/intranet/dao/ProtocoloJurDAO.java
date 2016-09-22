package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_requisicao_juridico;

import br.com.oma.intranet.util.JPAUtil;

@Stateless
public class ProtocoloJurDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140664493634530614L;
	private Query query;
	private EntityManager manager;
	private Connection con;

	public ProtocoloJurDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
		this.con = Conexao.getConexaoSiga();
	}

	public void salvarProtocolo(intra_requisicao_juridico codigo) {
		if (codigo.getCodigo() == 0) {
			this.manager.persist(codigo);
			intra_log_geral log = new intra_log_geral(codigo.getCodigo(), retornaIdUsuario(),
					"intra_requisicao_juridico", true, false, false, 
					codigo.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		} else {
			this.manager.merge(codigo);
			intra_log_geral log = new intra_log_geral(codigo.getCodigo(), retornaIdUsuario(),
					"intra_requisicao_juridico", false, true, false, 
					codigo.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		}
	}

	public void fechaConexao() throws SQLException {
		this.con.close();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1 order by codigo DESC");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_requisicao_juridico> listarProtocolo(int codigo) {
		this.query = this.manager.createQuery(" from intra_requisicao_juridico where codigo = :p1 order by codigo DESC");
		query.setParameter("p1", codigo);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_requisicao_juridico> listarProtocolo() {
		this.query = this.manager.createQuery(" from intra_requisicao_juridico");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_requisicao_juridico> listarProtocoloGerente(int codGerente) {
		this.query = this.manager.createQuery(" from intra_requisicao_juridico where codGerente = :p1 order by codigo DESC");
		query.setParameter("p1", codGerente);
		return query.getResultList();
	}

	public intra_requisicao_juridico pesquisaRequisicaoPorCodigo(int codigoRequisicao) {
		return this.manager.find(intra_requisicao_juridico.class, codigoRequisicao);
	}
	
	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}
	
	public void excluir(intra_requisicao_juridico protocoloSelecionado, String usuario,
			String ip) {
		
		this.manager.remove(protocoloSelecionado);

		String resumoInfos = 
				"Nome CND: "+ protocoloSelecionado.getCond_cod()
				+ "; Cod. CND: " + protocoloSelecionado.getCodPredio()
				+ "; Nome Juridico: " + protocoloSelecionado.getNomJuridico()
				+ "; Endereço: " + protocoloSelecionado.getEndereco() 
				+ "; Sindico: " + protocoloSelecionado.getSindico()
				+ "; Referência: " + protocoloSelecionado.getReferencia();

		intra_log_geral log = new intra_log_geral(
				protocoloSelecionado.getCodPredio(), this.retornaIdUsuario(),
				"intra_requisicao_juridico", false, false, true, resumoInfos, new Date(),
				0, null, null);
		log.setIp(ip);
		this.logGeral(log);
	}
	
	public String getdiaMalote(int condominio) throws SQLException {
		String query = " select c.roteiro FROM sigadm.dbo.cndcondo c "
				+ "inner join omacorp.dbo.intra_condominios t on t.codigo = c.codigo where c.codigo = ?";

		String roteiro = null;

		PreparedStatement stmt = this.con.prepareStatement(query);

		stmt.setInt(1, condominio);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			roteiro = rs.getString("roteiro");

		}
		this.fechaConexao();
		return roteiro;
	}

}
