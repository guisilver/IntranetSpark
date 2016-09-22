package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_candidato_vaga;
import br.com.oma.intranet.util.JPAUtil;

public class RelatorioVagasDAO {

	private EntityManager manager;

	public RelatorioVagasDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato_vaga> pesquisaRelatorio(Date dataInicio, Date dataFim) {
		Query query = this.manager
				.createQuery("from intra_candidato_vaga where dataAbertura between :p1 and :p2 order by dataAbertura");
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato_vaga> pesquisaRelatorioResponsavel(Date dataInicio, Date dataFim,
			String responsavelVaga) {
		Query query = this.manager.createQuery(
				"from intra_candidato_vaga where dataAbertura between :p1 and :p2 and responsavelVaga = :p3 order by dataAbertura");
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		query.setParameter("p3", responsavelVaga);
		return query.getResultList();
	}

}
