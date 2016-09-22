package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_previsao_rateio;
import br.com.oma.intranet.util.JPAUtil;

public class PrevisaoRateioDAO extends LogGeralDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7942707168209292285L;

	private EntityManager manager;
	private Query query;
	
	public PrevisaoRateioDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_previsao_rateio> listarVerbasRateio(int codigo) {
		this.query = this.manager.createQuery("from intra_previsao_rateio where condominio = :p1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	public void salvarVerbas(intra_previsao_rateio iprBEAN) {
		this.manager.persist(iprBEAN);
	}
	
	
}
