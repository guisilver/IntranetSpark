package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_advert_susp;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class AdvertenciaSuspensaoDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3423029165824305868L;
	private EntityManager manager;
	private Query query;
	private Connection con;
	private Date data;
	private intra_log_geral ilg = new intra_log_geral();

	public AdvertenciaSuspensaoDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
		this.con = Conexao.getConexaoSiga();
	}

	public void fechaConexao() throws SQLException {
		this.con.close();
	}

	public void salvar(intra_advert_susp i, SessaoMB sessaoMB) {
		if (i.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(i);
			this.ilg.setCondominio(i.getEmpresa());
			this.ilg.setInfoAnterior("Nova Advertência/ Suspensão: " + i.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_advert_susp");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(i);
			this.ilg.setCondominio(i.getEmpresa());
			this.ilg.setInfoAnterior("Advertência/ Suspensão alterada: " + i.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_advert_susp");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void excluir(intra_advert_susp i, SessaoMB sessaoMB) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.manager.remove(i);
		this.ilg.setCondominio(i.getEmpresa());
		this.ilg.setInfoAnterior("Exclusão de Advertência/ Suspensão: " + i.getId());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_advert_susp");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public intra_advert_susp pesquisaAdvertSusp(int id) {
		return this.manager.find(intra_advert_susp.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<intra_advert_susp> listaTabela() {
		this.query = this.manager.createQuery("from intra_advert_susp order by id DESC");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_advert_susp> listaTabela(int codigo) {
		this.query = this.manager.createQuery("from intra_advert_susp where empresa = :p1 order by id DESC");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_advert_susp> listaTabela2(int codigo, int funcionario) {
		this.query = this.manager
				.createQuery("from intra_advert_susp where empresa = :p1 and funcionario = :p2 order by id DESC");
		this.query.setParameter("p1", codigo);
		this.query.setParameter("p2", funcionario);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_advert_susp> listaFuncionarios(int codigo) {
		List<intra_advert_susp> listaFuncionarios = new ArrayList<>();
		this.query = this.manager.createNativeQuery("select f.funcionario, p.nome from sigadm.dbo.gsffunc f "
				+ "inner join sigadm.dbo.gsftabel t on f.funcao = t.codigo "
				+ "inner join sigadm.dbo.cndcondo c on f.empresa = c.codigo "
				+ "inner join sigadm.dbo.gsfpessoa p on f.cod_pessoa = p.codigo "
				+ "where t.tipo_reg = 4 and c.data_baixa is null and f.data_desligto is null and (f.situacao = 1 or f.situacao = 5) and empresa = :p1 "
				+ "order by t.codigo,t.descricao,f.empresa, f.funcionario ");
		this.query.setParameter("p1", codigo);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_advert_susp i = new intra_advert_susp();
				if (obj[0] != null) {
					i.setFuncionario(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					i.setNome(obj[1].toString());
				}
				listaFuncionarios.add(i);
			}
		}
		return listaFuncionarios;
	}

	public String nomeFuncao(int codigo, int funcionario) throws SQLException {
		String query = "select t.descricao from sigadm.dbo.gsffunc f "
				+ "inner join sigadm.dbo.gsftabel t on f.funcao = t.codigo "
				+ "inner join sigadm.dbo.cndcondo c on f.empresa = c.codigo "
				+ "inner join sigadm.dbo.gsfpessoa p on f.cod_pessoa = p.codigo "
				+ "where t.tipo_reg = 4 and c.data_baixa is null and f.data_desligto is null and (f.situacao = 1 or f.situacao = 5) and f.empresa = ? and f.funcionario = ? "
				+ "order by t.codigo,t.descricao,f.empresa, f.funcionario ";
		String funcao = null;
		PreparedStatement stmt = this.con.prepareStatement(query);
		stmt.setInt(1, codigo);
		stmt.setInt(2, funcionario);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			funcao = rs.getString("descricao");
		}
		this.con.close();
		return funcao;
	}

	public String nomeFuncionario(int codigo, int funcionario) throws SQLException {
		String query = "select p.nome as nomeFuncionario from sigadm.dbo.gsffunc f "
				+ "inner join sigadm.dbo.gsftabel t on f.funcao = t.codigo "
				+ "inner join sigadm.dbo.cndcondo c on f.empresa = c.codigo "
				+ "inner join sigadm.dbo.gsfpessoa p on f.cod_pessoa = p.codigo "
				+ "where t.tipo_reg = 4 and c.data_baixa is null and f.data_desligto is null and (f.situacao = 1 or f.situacao = 5) and f.empresa = ? and f.funcionario = ? "
				+ "order by t.codigo,t.descricao,f.empresa, f.funcionario ";
		String nome = null;
		PreparedStatement stmt = this.con.prepareStatement(query);
		stmt.setInt(1, codigo);
		stmt.setInt(2, funcionario);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			nome = rs.getString("nomeFuncionario");
		}
		this.con.close();
		return nome;
	}
}
