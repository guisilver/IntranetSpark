package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_requisicao_cobranca;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.sigadm.entidades.intra_endereco_unidade;

@Stateless
public class ProtocoloCobrDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140664493634530614L;
	private Query query;
	private EntityManager manager;
	private Connection con;

	public ProtocoloCobrDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
		this.con = Conexao.getConexaoSiga();
	}

	public void salvarProtocolo(intra_requisicao_cobranca codigo) {
		if (codigo.getCodigo() == 0) {
			this.manager.persist(codigo);
			intra_log_geral log = new intra_log_geral(codigo.getCodigo(), retornaIdUsuario(),
					"intra_requisicao_cobranca", true, false, false, codigo.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		} else {
			this.manager.merge(codigo);
			intra_log_geral log = new intra_log_geral(codigo.getCodigo(), retornaIdUsuario(),
					"intra_requisicao_cobranca", false, true, false, codigo.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		}
	}

	public void detachProtocolo(intra_requisicao_cobranca codigo) {
		this.manager.detach(codigo);
	}

	public void fechaConexao() throws SQLException {
		this.con.close();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager
				.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1 order by codigo");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_requisicao_cobranca> listarProtocolo(int codigo) {
		this.query = this.manager
				.createQuery(" from intra_requisicao_cobranca where codigo = :p1 order by codigo DESC");
		query.setParameter("p1", codigo);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_requisicao_cobranca> listarProtocolo() {
		this.query = this.manager.createQuery("from intra_requisicao_cobranca order by codigo DESC");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_requisicao_cobranca> listarProtocoloGerente(int codGerente) {
		this.query = this.manager
				.createQuery(" from intra_requisicao_cobranca where codGerente = :p1 order by codigo DESC");
		query.setParameter("p1", codGerente);
		return query.getResultList();
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

	public intra_requisicao_cobranca pesquisaRequisicaoPorCodigo(int codigoRequisicao) {
		return this.manager.find(intra_requisicao_cobranca.class, codigoRequisicao);
	}

	public intra_grupo_gerente pesquisaGerentePorCodigo(int codGerente) {
		return this.manager.find(intra_grupo_gerente.class, codGerente);
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

	public void excluir(intra_requisicao_cobranca protocoloSelecionado, String usuario, String ip) {

		this.manager.remove(protocoloSelecionado);

		String resumoInfos = "Nome CND: " + protocoloSelecionado.getcond_cod() + "; Cod. CND: "
				+ protocoloSelecionado.getCodPredio() + "; Cod. Gerente: " + protocoloSelecionado.getCodGerente()
				+ "; Endereço: " + protocoloSelecionado.getEndereco() + "; Aos Cuidados: "
				+ protocoloSelecionado.getAosCuidados() + "; Feito Em: " + protocoloSelecionado.getFeitoem()
				+ "; Referência: " + protocoloSelecionado.getReferencia();

		intra_log_geral log = new intra_log_geral(protocoloSelecionado.getCodPredio(), this.retornaIdUsuario(),
				"intra_requisicao_cobranca", false, false, true, resumoInfos, new Date(), 0, null, null);
		log.setIp(ip);
		this.logGeral(log);
	}

	@SuppressWarnings("unchecked")
	public intra_endereco_unidade getEnderecoUnidade(int condominio, String bloco, String unidade) throws SQLException {

		List<intra_endereco_unidade> eu = new ArrayList<intra_endereco_unidade>();
		this.query = this.manager
				.createNativeQuery("select c.condominio, c.bloco, c.unidade, c.nome, i.logradouro, i.endereco, "
						+ " i.nro_end, i.complemento, i.bairro, c.tipo_corresp from sigadm.dbo.cndunida c "
						+ "inner join sigadm.dbo.ilclient i on i.nome = c.nome "
						+ "inner join sigadm.dbo.cndcondo t on t.codigo = c.condominio "
						+ "where condominio = :p1 and c.bloco = :p2 and c.unidade = :p3 and tipo_corresp = 'C'"
						+ "group by c.condominio, c.bloco, c.unidade, c.nome, i.logradouro, i.endereco, i.nro_end, i.complemento, i.bairro, c.tipo_corresp "
						+ "order by c.condominio, c.bloco, c.unidade ");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", bloco);
		this.query.setParameter("p3", unidade);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_endereco_unidade ieu = new intra_endereco_unidade();
				if (obj[0] != null) {
					ieu.setCondominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ieu.setBloco(obj[1].toString());
				}
				if (obj[2] != null) {
					ieu.setUnidade(obj[2].toString());
				}
				if (obj[3] != null) {
					ieu.setNome(obj[3].toString());
				}
				if (obj[4] != null) {
					ieu.setLogradouro(obj[4].toString());
				}
				if (obj[5] != null) {
					ieu.setEndereco(obj[5].toString());
				}
				if (obj[6] != null) {
					ieu.setNro_end(obj[6].toString());
				}
				if (obj[7] != null) {
					ieu.setComplemento(obj[7].toString());
				}
				if (obj[8] != null) {
					ieu.setBairro(obj[8].toString());
				}
				if (obj[9] != null) {
					ieu.setTipo_corresp(obj[9].toString());
				}
				eu.add(ieu);
			}
		}
		return eu.get(0);
	}
}
