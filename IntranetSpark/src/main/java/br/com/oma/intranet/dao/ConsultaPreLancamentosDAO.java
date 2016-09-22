package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cndpagar_followup;

public class ConsultaPreLancamentosDAO {

	private EntityManager manager;

	public ConsultaPreLancamentosDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> getListaPreLancamento(int filtro) {
		Query query = null;
		if (filtro == 0) {
			query = this.manager.createQuery(
					"from cndpagar where status_sip > 0 and suspenso_fiscal = 0 and feito_fiscal_sip is null and (tipo_lancamento = 1 or tipo_lancamento = 4) and reprovado_fiscal = 0 order by emissaoNf,vencimento");
		} else if (filtro == 2) {
			query = this.manager.createQuery(
					"from cndpagar where status_sip > 0 and suspenso_fiscal = 0 and feito_fiscal_sip is not null and (tipo_lancamento = 1 or tipo_lancamento = 4) and reprovado_fiscal = 0 order by emissaoNf,vencimento");
		} else {
			query = this.manager.createQuery(
					"from cndpagar where status_sip > 0 and suspenso_fiscal = 3 and (tipo_lancamento = 1 or tipo_lancamento = 4) and reprovado_fiscal = 0 order by emissaoNf,vencimento");
		}
		return query.getResultList();
	}

	public intra_condominios getNomeCondominio(int codigo) {
		return this.manager.find(intra_condominios.class, codigo);
	}

	public void excluirLancamento(cndpagar lancamento) {
		lancamento = this.manager.merge(lancamento);
		this.manager.remove(lancamento);
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public int quantidadeVencimento() {
		int resultado = 0;
		Query query = this.manager.createNativeQuery("select * "
				+ "from omaonline.dbo.cndpagar where convert(date,data_inclusao, 103) > convert(date,vencimento - 3, 103) and feito_fiscal_sip is null and suspenso_fiscal = 0 and status_sip > 0");

		List<Object[]> lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] pagar : lista) {
				resultado += 1;
			}
		}

		Query query2 = this.manager.createNativeQuery("select * "
				+ "from omaonline.dbo.cndpagar where convert(date,data_inclusao, 103) > convert(date,vencimento - 1, 103) and feito_fiscal_sip is null and suspenso_fiscal = 0 and status_sip > 0");

		List<Object[]> lista2 = query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] pagar : lista) {
				resultado += 1;
			}
		}

		Query query3 = this.manager.createNativeQuery("select * "
				+ "from omaonline.dbo.cndpagar where convert(date,data_inclusao, 103) = convert(date,vencimento, 103) and feito_fiscal_sip is null and suspenso_fiscal = 0 and status_sip > 0");

		List<Object[]> lista3 = query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] pagar : lista) {
				resultado += 1;
			}
		}

		return resultado;
	}

	@SuppressWarnings("unused")
	public int quantidadeSuspenso() {
		int resultado = 0;
		TypedQuery<cndpagar> query = this.manager.createQuery(
				"from cndpagar where status_sip > 0 and suspenso_fiscal = 3 order by emissaoNf,vencimento",
				cndpagar.class);

		List<cndpagar> lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (cndpagar pagar : lista) {
				resultado += 1;
			}
		}

		return resultado;
	}

	public void suspenderLancamentoFiscal(cndpagar pagar, SessaoMB sessao, String obs) {
		Query query = this.manager.createNativeQuery(
				"update omaonline.dbo.cndpagar set suspenso_fiscal = 3, feito_fiscal_sip = getdate() where codigo = :p1");
		query.setParameter("p1", pagar.getCodigo());
		query.executeUpdate();
		this.registrarFollowUp(pagar.getCodigo(), "Suspenso", sessao.getUsuario().getEmail(), "oma",
				pagar.getCondominio(), obs);
	}

	public void liberarLancamentoFiscal(cndpagar pagar, SessaoMB sessao, String obs) {
		Query query = this.manager.createNativeQuery(
				"update omaonline.dbo.cndpagar set suspenso_fiscal = 0, feito_fiscal_sip = null where codigo = :p1");
		query.setParameter("p1", pagar.getCodigo());
		query.executeUpdate();
		this.registrarFollowUp(pagar.getCodigo(), "Liberado", sessao.getUsuario().getEmail(), "oma",
				pagar.getCondominio(), obs);
	}

	public void registrarFollowUp(int nrolancto, String acao, String feitoPor, String ip, short cdCnd,
			String obsLancto) {
		cndpagar_followup followup = new cndpagar_followup();
		followup.setObs(obsLancto);
		followup.setData(new Date());
		followup.setNrolancto(nrolancto);
		followup.setAcao(acao);
		followup.setFeitoPor(feitoPor);
		followup.setIp(ip);
		followup.setCdCnd(cdCnd);
		this.manager.persist(followup);
	}

}