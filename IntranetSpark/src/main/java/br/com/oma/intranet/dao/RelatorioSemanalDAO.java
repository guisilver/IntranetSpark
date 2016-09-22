package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_relSem_historico;
import br.com.oma.intranet.entidades.intra_relatorio_semanal;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class RelatorioSemanalDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6914436802117976037L;

	private EntityManager manager;
	private Query query;
	private intra_log_geral ilg;
	private Date data;

	public RelatorioSemanalDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_relSem_historico> listarRelSemHistorico(int i) {
		this.query = this.manager
				.createQuery("from intra_relSem_historico where condominio = :p1 order by condominio, dataSalvou desc");
		this.query.setParameter("p1", i);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public intra_relatorio_semanal listarRelatorioSemanal(int codigo) {
		List<intra_relatorio_semanal> lista = new ArrayList<>();
		intra_relatorio_semanal relatorio = new intra_relatorio_semanal();
		this.query = this.manager
				.createQuery("from intra_relatorio_semanal where codigo = :p1");
		this.query.setParameter("p1", codigo);
		lista = this.query.getResultList();
		for (intra_relatorio_semanal irs : lista) {
			relatorio = irs;
			if (String.valueOf(irs.getTaxaAdm()) == null) {
				relatorio.setTaxaAdm(0.00);
			}
		}
		return relatorio;
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager
				.createQuery("from intra_condominios where codigoGerente = :p1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	public void salvarRelatorioSemanal(intra_relatorio_semanal rsBean,
			SessaoMB sessaoMB, intra_relatorio_semanal infoAnterior) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.manager.merge(rsBean);
		this.ilg.setCondominio(infoAnterior.getCodigo());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_relatorio_semanal");
		this.ilg.setInfoAnterior("Sindico: " + infoAnterior.getNomeSindico()
				+ " /Zelador: " + infoAnterior.getNomeZelador() + " /Mandato: "
				+ infoAnterior.getFinalMandato() + " /Saldo Anterior: "
				+ infoAnterior.getSaldoAnterior() + " /Responsável Receita:"
				+(infoAnterior.getReceitaFederal() == 1 ? "Sim" : "Não") +  " /Satisfação: "
				+(infoAnterior.getSatisfacao() == 1 ? "Ruim" : infoAnterior.getSatisfacao() == 2 ? "Bom" : "Otimo" )+ " /Contato Pessoal: "
				+(infoAnterior.isContatoPessoal() == true ? "Sim" : "Não" )+ " /Contato Telefone:"
				+(infoAnterior.isContatoTel() == true ? "Sim" : "Não")+ " /Contato E-mail: "
				+(infoAnterior.isContatoEmail() == true ? "Sim" : "Não")+ " /Obs: "
				+ infoAnterior.getObs() +" /ideia Vista no Condominio: "+infoAnterior.getIdeiaCondominio());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	@SuppressWarnings("unchecked")
	public List<intra_log_geral> listarHistoricoSemanal(int condominio) {
		this.query = this.manager.createQuery("from intra_log_geral where tabela = 'intra_relatorio_semanal' and condominio = "+condominio+" order by data_feito desc");
		return this.query.getResultList();
	}

}
