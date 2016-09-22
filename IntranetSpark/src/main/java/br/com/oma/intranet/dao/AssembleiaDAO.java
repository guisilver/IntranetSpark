package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.entidades.intra_assembleia;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.util.JPAUtil;

public class AssembleiaDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1117344393356959618L;
	private EntityManager manager;

	public AssembleiaDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvarAssembleia(intra_assembleia assembleia) {
		if (assembleia.getCodigo() == 0) {
			this.manager.persist(assembleia);
			assembleia.getReserva().setCod_edital(assembleia.getCodigo());
			this.manager.merge(assembleia.getReserva());
			intra_log_geral log = new intra_log_geral(assembleia.getCondominio().getCodigo(), retornaIdUsuario(),
					"intra_assembleia", true, false, false, assembleia.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		} else {
			this.manager.merge(assembleia);
			intra_log_geral log = new intra_log_geral(assembleia.getCondominio().getCodigo(), retornaIdUsuario(),
					"intra_assembleia", false, true, false, assembleia.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		}
	}

	public void excluirAssembleia(intra_assembleia assembleia) {
		assembleia = this.manager.merge(assembleia);
		this.manager.remove(assembleia);
		intra_log_geral log = new intra_log_geral(assembleia.getCondominio().getCodigo(), retornaIdUsuario(),
				"intra_assembleia", false, false, true, assembleia.toString(), new Date(), 0, null, null);
		this.logGeral(log);
	}

	@SuppressWarnings("unchecked")
	public List<intra_grupo_gerente> getListaGerentes() {
		return this.manager.createQuery("from intra_grupo_gerente where nome not like '%Todos%' order by nome")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios(int codigoGerente) {
		Query query = this.manager
				.createQuery("from intra_condominios where codigoGerente = :codigoGerente order by nome");
		query.setParameter("codigoGerente", codigoGerente);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaTodosCondominios() {
		Query query = this.manager.createQuery("from intra_condominios");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_assembleia> getListaAssembleias(Date dataInicio) {
		Query query = this.manager
				.createQuery("from intra_assembleia where dataAssembleia >= :dataInicio order by dataAssembleia DESC");
		query.setParameter("dataInicio", dataInicio);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_assembleia> getListaAssembleiasPorGerente(Date dataInicio, int gerenteCodigo) {
		Query query = this.manager.createQuery(
				"from intra_assembleia where gerente_codigo = :p1 and dataAssembleia >= :dataInicio order by dataAssembleia DESC");
		query.setParameter("p1", gerenteCodigo);
		query.setParameter("dataInicio", dataInicio);
		return query.getResultList();
	}

	public intra_assembleia pesquisaAssembleiaPorCodigo(int codigoAssembleia) {
		return this.manager.find(intra_assembleia.class, codigoAssembleia);
	}

	public List<intra_assembleia> pesquisaConvocacoes(Date dataInicio, Date dataFim, String tipoAssembleia,
			List<String> ordenacao, int codigoGerente, boolean publicados) {
		CriteriaBuilder cb = this.manager.getCriteriaBuilder();
		CriteriaQuery<intra_assembleia> c = cb.createQuery(intra_assembleia.class);
		Root<intra_assembleia> root = c.from(intra_assembleia.class);

		c.select(root);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (dataInicio != null && dataFim != null) {
			Predicate data = cb.between(root.<Date>get("dataAssembleia"), dataInicio, dataFim);
			predicates.add(data);
		}

		if (tipoAssembleia != null && !tipoAssembleia.isEmpty() && !tipoAssembleia.equals("Todas")) {
			Predicate tipo = cb.equal(root.get("tipo"), tipoAssembleia);
			predicates.add(tipo);
		}

		if (codigoGerente > 1) {
			Predicate cdGerente = cb.equal(root.<intra_assembleia>get("gerente").get("codigo"), codigoGerente);
			predicates.add(cdGerente);
		}

		if (publicados) {
			Predicate publicacao = cb.isNull(root.get("publicacaoConvocacao"));
			predicates.add(publicacao);
		}

		c.where(predicates.toArray(new Predicate[predicates.size()]));

		// ADICIONA CADA ELEMENTO DO ORDER BY À QUERY
		List<Order> listaOrdenacao = new ArrayList<Order>();
		if (ordenacao != null && ordenacao.size() > 0) {
			for (String aux : ordenacao) {
				if (aux.equals("Nome do Cnd")) {
					listaOrdenacao.add(cb.asc(root.<intra_condominios>get("condominio").get("nome")));
				}
				if (aux.equals("Código do Cnd")) {
					listaOrdenacao.add(cb.asc(root.<intra_condominios>get("condominio").get("codigo")));
				}
				if (aux.equals("Gerente")) {
					listaOrdenacao.add(cb.asc(root.<intra_condominios>get("gerente").get("nome")));
				}
				if (aux.equals("Data")) {
					listaOrdenacao.add(cb.asc(root.get("dataAssembleia")));
				}
			}
		}

		c.orderBy(listaOrdenacao);

		TypedQuery<intra_assembleia> query = manager.createQuery(c);
		return query.getResultList();
	}

	public List<intra_assembleia> pesquisaAtas(Date dataInicio, Date dataFim, String tipoAssembleia,
			List<String> ordenacao, int codigoGerente, boolean publicados) {
		CriteriaBuilder cb = this.manager.getCriteriaBuilder();
		CriteriaQuery<intra_assembleia> c = cb.createQuery(intra_assembleia.class);
		Root<intra_assembleia> root = c.from(intra_assembleia.class);

		c.select(root);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (dataInicio != null && dataFim != null) {
			Predicate data = cb.between(root.<Date>get("dataAssembleia"), dataInicio, dataFim);
			predicates.add(data);
		}

		if (tipoAssembleia != null && !tipoAssembleia.isEmpty() && !tipoAssembleia.equals("Todas")) {
			Predicate tipo = cb.equal(root.get("tipo"), tipoAssembleia);
			predicates.add(tipo);
		}

		if (codigoGerente > 1) {
			Predicate cdGerente = cb.equal(root.<intra_assembleia>get("gerente").get("codigo"), codigoGerente);
			predicates.add(cdGerente);
		}

		if (publicados) {
			Predicate publicacao = cb.isNotNull(root.get("publicacaoAta"));
			predicates.add(publicacao);
		}

		c.where(predicates.toArray(new Predicate[predicates.size()]));

		// ADICIONA CADA ELEMENTO DO ORDER BY À QUERY
		List<Order> listaOrdenacao = new ArrayList<Order>();
		if (ordenacao != null && ordenacao.size() > 0) {
			for (String aux : ordenacao) {
				if (aux.equals("Nome do Cnd")) {
					listaOrdenacao.add(cb.asc(root.<intra_condominios>get("condominio").get("nome")));
				}
				if (aux.equals("Código do Cnd")) {
					listaOrdenacao.add(cb.asc(root.<intra_condominios>get("condominio").get("codigo")));
				}
				if (aux.equals("Gerente")) {
					listaOrdenacao.add(cb.asc(root.<intra_condominios>get("gerente").get("nome")));
				}
				if (aux.equals("Data")) {
					listaOrdenacao.add(cb.asc(root.get("dataAssembleia")));
				}
			}
		}

		c.orderBy(listaOrdenacao);

		TypedQuery<intra_assembleia> query = manager.createQuery(c);
		return query.getResultList();
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

}
