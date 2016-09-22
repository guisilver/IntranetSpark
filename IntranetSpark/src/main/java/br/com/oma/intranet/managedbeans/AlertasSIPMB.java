package br.com.oma.intranet.managedbeans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.joda.time.DateTime;

import br.com.oma.intranet.dao.ConsultaPreLancamentosDAO;
import br.com.oma.intranet.dao.VencimentoDADAO;
import br.com.oma.intranet.entidades.intra_grupo_depto;
import br.com.oma.omaonline.dao.FinanceiroDAO;

@ManagedBean(name = "alertaSIP")
public class AlertasSIPMB {

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private FinanceiroDAO financeiroDAO;
	private VencimentoDADAO dao = new VencimentoDADAO();

	private Date dataInicio;

	private Date dataFim;

	private int contadorDA;

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public int getRetornaQtdaDA() {
		this.dataInicio = new DateTime(new Date()).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime(new Date()).withMillisOfDay(0).plusDays(10).toDate();

		this.contadorDA = 0;
		if (this.sessaoMB.getGerenteSelecionado() != null) {
			this.contadorDA = dao.getListaVencimentoDAContador(this.dataInicio, this.dataFim, this.sessaoMB.getGerenteSelecionado().getCodigo());
		}else{
			this.contadorDA = dao.getListaVencimentoDAContador(this.dataInicio, this.dataFim, 1);
		}
		return this.contadorDA;
	}

	public int getRetornaQtdaSuspenso(List<intra_grupo_depto> depto) {
		int valor = 0;
		if (depto != null) {

			for (intra_grupo_depto grupo : depto) {
				if (grupo.getNome().equals("Gerencia")) {
					if (this.sessaoMB.getGerenteSelecionado() != null) {
						this.financeiroDAO = new FinanceiroDAO();
						valor = this.financeiroDAO
								.quantidadeSuspensoGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
						break;
					}
				} else if (grupo.getNome().equals("Contas a Pagar")) {
					ConsultaPreLancamentosDAO dao = new ConsultaPreLancamentosDAO();
					valor = dao.quantidadeSuspenso();

					this.financeiroDAO = new FinanceiroDAO();
					valor += this.financeiroDAO.quantidadeSuspensoLancto();
					break;
				} else if (grupo.getNome().equals(" Todos")) {

					this.financeiroDAO = new FinanceiroDAO();
					valor += this.financeiroDAO.quantidadeSuspensoGerente();

					ConsultaPreLancamentosDAO dao = new ConsultaPreLancamentosDAO();
					valor += dao.quantidadeSuspenso();

					this.financeiroDAO = new FinanceiroDAO();
					valor += this.financeiroDAO.quantidadeSuspensoLancto();

					break;
				}
			}
		}
		return valor;
	}
	
	
}
