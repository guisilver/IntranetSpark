package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.entidades.intra_conciliacao;
import br.com.oma.intranet.util.JPAUtil;

public class ConciliacaoDADAO {

	private EntityManager manager;

	public ConciliacaoDADAO() {
		this.manager = JPAUtil.getManager();
	}

	public List<intra_conciliacao> getListaConciliacao(Date dataInicio, Date dataFim) {
		TypedQuery<intra_conciliacao> query = this.manager.createQuery(
				"from intra_conciliacao where dataPagto between :p1 and :p2 order by dataPagto",
				intra_conciliacao.class);
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public boolean possuiImg(int id_lcto, String tipo_lcto) {
		Query query = this.manager
				.createNativeQuery("select * from sigadm.dbo.glbCatalogo_Lcto where id_lcto = :p1 and tipo_lcto = :p2");
		query.setParameter("p1", id_lcto);
		query.setParameter("p2", tipo_lcto);
		List<Object[]> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int excluir(int codigo) {
		Query query = this.manager.createQuery("delete intra_conciliacao where codigo = :codigo");
		int i = query.setParameter("codigo", codigo).executeUpdate();
		return i;
	}

}
