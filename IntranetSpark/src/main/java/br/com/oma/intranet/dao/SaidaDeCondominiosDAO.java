package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.oma.intranet.entidades.intra_condominios_saida;
import br.com.oma.intranet.util.JPAUtil;

public class SaidaDeCondominiosDAO {

	private EntityManager manager;

	public SaidaDeCondominiosDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvarSaidaCnd(intra_condominios_saida ics) {
		if (ics.getCodigo() == 0) {
			this.manager.persist(ics);
		} else {
			this.manager.merge(ics);
		}
	}

	public void excluirSaidaCnd(intra_condominios_saida ics) {
		ics = this.manager.merge(ics);
		this.manager.remove(ics);
	}

	public List<intra_condominios_saida> getListaCndSaida() {
		return this.manager
				.createQuery("from intra_condominios_saida order by dataSaida", intra_condominios_saida.class)
				.getResultList();
	}
}
