package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class GrupoGerDAO extends LogGeralDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	private Query query;
	
	private Date data;
	private intra_log_geral ilg;
	
	public GrupoGerDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	public void adiconaGrupo(intra_grupo_gerente g, SessaoMB sessao) {
		this.manager.persist(g);
		data = new Date();
		this.ilg = new intra_log_geral();
		this.ilg.setIp(sessao.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
		this.ilg.setTabela("intra_grupo_permissao");
		this.ilg.setInfoAnterior("Grupo: " + g.getNome());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}
	
	public void adiconaGeren(intra_grupo_gerente g, SessaoMB sessao) {
		this.manager.merge(g);
		data = new Date();
		this.ilg = new intra_log_geral();
		this.ilg.setIp(sessao.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
		this.ilg.setTabela("intra_grupo_permissao");
		this.ilg.setInfoAnterior("Gerente: " + g.getNome());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public List<intra_grupo_gerente> buscaTodos() {
		return this.manager.createQuery("from intra_grupo_gerente", intra_grupo_gerente.class).getResultList();
	}

	public void ExcluirGerente(intra_grupo_gerente gerenBean, SessaoMB sessao) {
		this.query = this.manager.createQuery("delete intra_grupo_gerente where codigo = :p1");
		this.query.setParameter("p1", gerenBean.getCodigo());
		this.query.executeUpdate();
		
		data = new Date();
		this.ilg = new intra_log_geral();
		this.ilg.setIp(sessao.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
		this.ilg.setTabela("intra_grupo_permissao");
		this.ilg.setInfoAnterior("Gerente: " + gerenBean.getEmail());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public boolean verificaGerente(int codigo) {
		this.query = this.manager.createQuery("from intra_grupo_gerente where codigo = :p1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList().isEmpty();
	}
}
