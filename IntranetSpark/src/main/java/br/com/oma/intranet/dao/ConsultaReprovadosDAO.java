package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.cndpagar;

public class ConsultaReprovadosDAO {

	private EntityManager manager;

	public ConsultaReprovadosDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> getListaReprovados(int filtro) {
		Query query = null;
		if (filtro == 0) {
			query = this.manager.createQuery(
					"from cndpagar where status_sip > 0 and suspenso_fiscal = 0 and feito_fiscal_sip is null and tipo_lancamento = 1 and reprovado_fiscal = 0 order by emissaoNf,vencimento");
		} else {
			query = this.manager.createQuery(
					"from cndpagar where status_sip > 0 and tipo_lancamento = 1 and reprovado_fiscal = 1 order by emissaoNf,vencimento");
		}
		return query.getResultList();
	}
	
	public intra_condominios getNomeCondominio(int codigo) {
		return this.manager.find(intra_condominios.class, codigo);
	}

}
