package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_cadastro_recepcao;
import br.com.oma.intranet.entidades.intra_cadastro_recepcao_foto;
import br.com.oma.intranet.util.JPAUtil;

public class CadastroRecepcaoDAO {

	private EntityManager manager;

	public CadastroRecepcaoDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_cadastro_recepcao> pesquisaVisitantes(Date inicio,
			Date fim) {
		Query query = this.manager
				.createQuery("from intra_cadastro_recepcao where dataEntrada between :p1 and :p2");
		query.setParameter("p1", inicio);
		query.setParameter("p2", fim);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaFoto(Integer codigo) {
		byte[] foto = null;
		Query query = this.manager
				.createQuery("from intra_cadastro_recepcao_foto where codigo = :p1");
		query.setParameter("p1", codigo);
		List<intra_cadastro_recepcao_foto> l = query.getResultList();
		for (intra_cadastro_recepcao_foto aux : l) {
			if (aux != null) {
				foto = aux.getFoto();
			}
		}
		return foto;
	}

}
