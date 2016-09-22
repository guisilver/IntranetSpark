package br.com.oma.intranet.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.JPAUtil;

@Stateless
public class QuerygerDAO extends LogGeralDAO{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8366347751276298557L;
	private EntityManager manager;
	private Query query;

	
	public QuerygerDAO(){
		this.manager = JPAUtil.getManager();

	}
	
	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}
	
}
