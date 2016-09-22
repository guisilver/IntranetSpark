package br.com.oma.intranet.dao;

import javax.persistence.EntityManager;

import br.com.oma.intranet.entidades.intra_fiscal_param;
import br.com.oma.intranet.util.JPAUtil;

public class ParametrosFiscalDAO {

	private EntityManager manager;

	public ParametrosFiscalDAO() {
		this.manager = JPAUtil.getManager();
	}

	public intra_fiscal_param pesquisarFiscalParam(int codigo) {
		return this.manager.find(intra_fiscal_param.class, codigo);
	}

	public void salvarFiscalParam(intra_fiscal_param fiscalParam) {
		if (fiscalParam.getCodigo() == 0) {
			this.manager.persist(fiscalParam);
		} else {
			this.manager.merge(fiscalParam);
		}
	}
}
