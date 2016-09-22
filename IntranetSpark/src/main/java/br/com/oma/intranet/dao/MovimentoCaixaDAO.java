package br.com.oma.intranet.dao;

import java.util.List;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.entidades.intra_bd_es;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.util.JPAUtil;

public class MovimentoCaixaDAO extends LogGeralDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7711422491145587754L;
	private EntityManager manager;

	public MovimentoCaixaDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvarNovoMovimento(intra_bd_es movimentoCaixa) {
		this.manager.persist(movimentoCaixa);
		intra_log_geral log = new intra_log_geral(movimentoCaixa.getCodigo(), retornaIdUsuario(), "intra_bd_es", true, false, false, movimentoCaixa.toString(), new Date(), 0, null, null);
		this.logGeral(log);
	}

	public void salvarAlteracoesMovimento(intra_bd_es movimentoCaixa) {
		this.manager.merge(movimentoCaixa);
		this.manager.flush();
		intra_log_geral log = new intra_log_geral(movimentoCaixa.getCodigo(), retornaIdUsuario(), "intra_bd_es", false, true, false, movimentoCaixa.toString(), new Date(), 0, null, null);
		this.logGeral(log);
	}

	public void excluirMovimento(intra_bd_es movimentoCaixa) {
		movimentoCaixa = this.manager.merge(movimentoCaixa);
		this.manager.remove(movimentoCaixa);
		intra_log_geral log = new intra_log_geral(movimentoCaixa.getCodigo(), retornaIdUsuario(), "intra_bd_es", false, false, true, movimentoCaixa.toString(), new Date(), 0, null, null);
		this.logGeral(log);
	}

	public List<intra_bd_es> getListaMovimentoCaixa() {
		TypedQuery<intra_bd_es> query = this.manager.createQuery("FROM intra_bd_es ORDER BY vencimento DESC",
				intra_bd_es.class);
		return query.getResultList();
	}

	public List<intra_bd_es> getListaMovimentoCaixaGerente(int codigoGerente) {
		TypedQuery<intra_bd_es> query = this.manager.createQuery("FROM intra_bd_es cod_gernete = :p1",
				intra_bd_es.class);
		query.setParameter("p1", codigoGerente);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios(int codigo) {
		Query query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1");
		query.setParameter("p1", codigo);
		return query.getResultList();
	}

	public intra_bd_es pesquisaMovimentoPorCodigo(int codigoMovimento) {
		return this.manager.find(intra_bd_es.class, codigoMovimento);
	}

	public intra_grupo_gerente pesquisaGerentePorCodigo(int codGerente) {
		return this.manager.find(intra_grupo_gerente.class, codGerente);
	}

	public void detachMovimento(intra_bd_es movimentoCaixa) {
		this.manager.detach(movimentoCaixa);
	}

	public void excluirMovimentoTbl(intra_bd_es movimentoSelecionado) {
		movimentoSelecionado = this.manager.merge(movimentoSelecionado);
		this.manager.remove(movimentoSelecionado);
	}

	public List<intra_bd_es> getListaMovimentoCaixaGerente(intra_grupo_gerente gerente) {
		TypedQuery<intra_bd_es> query = this.manager.createQuery(
				"FROM intra_bd_es where cod_gerente = :codigoGerente  ORDER BY vencimento DESC", intra_bd_es.class);
		query.setParameter("codigoGerente", gerente.getCodigo());
		return query.getResultList();
	}

	public List<intra_bd_es> getListaMovimentoCaixaTipo(int tipo) {
		TypedQuery<intra_bd_es> query = this.manager
				.createQuery("FROM intra_bd_es where tipo = :tipo ORDER BY vencimento DESC", intra_bd_es.class);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}

	public List<intra_bd_es> getListaMovimentoCaixaGerenteTipo(intra_grupo_gerente gerente, int tipo) {
		TypedQuery<intra_bd_es> query = this.manager.createQuery(
				"FROM intra_bd_es where cod_gerente = :codigoGerente and tipo = :tipo  ORDER BY vencimento DESC",
				intra_bd_es.class);
		query.setParameter("codigoGerente", gerente.getCodigo());
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}
	
	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}
}
