package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;

import br.com.oma.intranet.entidades.intra_assembleia;
import br.com.oma.intranet.util.JPAUtil;

public class ProtocolosDAO {

	private Query query;
	private EntityManager manager;

	public ProtocolosDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_assembleia> getLstAssembleias() {
		Date dataParametro = new DateTime().minusDays(3).toDate();
		query = manager
				.createQuery("from intra_assembleia where dataInserido is not null and dataInserido < :p1 and protocoloRecebido = 'False' and dataAssembleia > '2015-11-19 00:00:00.000' ORDER BY dataAssembleia ");
		query.setParameter("p1", dataParametro);
		return query.getResultList();
	}

	public void salvarProtocolos(List<intra_assembleia> assembleiaSelecionada) {
		for (intra_assembleia aux : assembleiaSelecionada) {
			Query query = this.manager
					.createQuery("update intra_assembleia set protocoloRecebido = :p1, devolvido = :p2 where codigo = :p3");
			query.setParameter("p1", true);
			query.setParameter("p2", new Date());
			query.setParameter("p3", aux.getCodigo()).executeUpdate();
		}
		this.manager.flush();
	}

}
