package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.cndpagar;

public class LancamentoContasDao extends LogGeralDAO{

	private static final long serialVersionUID = 1L;

	private EntityManager manager;
	
	public LancamentoContasDao() {
		this.manager = JPAUtil.getManager();
	}
	
	public List<cndpagar> listarLanctoOma(){
		TypedQuery<cndpagar> query = this.manager.createQuery("from cndpagar where status_sip > 0", cndpagar.class);
		return query.getResultList();
	}


	public String listarCredorNome(String credor) {
		Query query = this.manager.createNativeQuery("select nome from sigadm.dbo.cpcredor where usualcred = :p1");
		query.setParameter("p1", credor.trim());
		String nome = query.getResultList().get(0).toString();
		return nome;
	}
	
	public String listarCredorCNPJ(String credor) {
		Query query = this.manager.createNativeQuery("select inscricao from sigadm.dbo.cpcredor where usualcred = :p1");
		query.setParameter("p1", credor.trim());
		String cnpj = query.getResultList().get(0).toString();
		return cnpj;
	}
}
