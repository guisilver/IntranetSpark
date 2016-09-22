package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.entidades.intra_papercut_isento;
import br.com.oma.intranet.util.JPAUtil;

public class PapercutIsentoDAO extends LogGeralDAO{

	private static final long serialVersionUID = 1L;

	private EntityManager manager;
	
	public PapercutIsentoDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	public List<intra_papercut_isento> listarPapercutIsento(){
		TypedQuery<intra_papercut_isento> query = this.manager.createQuery("from intra_papercut_isento", intra_papercut_isento.class);
		return query.getResultList();
	}

	public void salvarPaperIsento(intra_papercut_isento papercutIsentoBean) {
		this.manager.merge(papercutIsentoBean);
	}

	public void excluirIsencao(intra_papercut_isento papercutIsentoBean) {
		Query query = this.manager.createQuery("delete intra_papercut_isento where condominio = :p1");
		query.setParameter("p1", papercutIsentoBean.getCondominio());
		query.executeUpdate();
	}
}
