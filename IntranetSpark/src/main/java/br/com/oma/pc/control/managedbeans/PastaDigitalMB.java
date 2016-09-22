package br.com.oma.pc.control.managedbeans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.joda.time.Months;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.pc.model.dao.PlanoContasDAO;
import br.com.oma.pc.modelo.entidades.Capa;
import br.com.oma.pc.modelo.entidades.Condominio;
import br.com.oma.pc.modelo.entidades.Conta;
import br.com.oma.pc.modelo.entidades.Documento;
import br.com.oma.pc.modelo.entidades.Lancamento;

@ManagedBean
@ViewScoped
public class PastaDigitalMB implements Serializable {

	/**
	 * 
	 */

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private static final long serialVersionUID = 1L;
	private List<Capa> capas;
	private Capa capa;
	private Conta conta;
	private List<Lancamento> lancamentosMes;
	private int mesInicio, anoInicio, mesFim, anoFim;
	private Date dataInicio, dataFim;
	private Condominio condominio = new Condominio();
	private Date mesSelecionado;
	private List<Documento> listaDoctos;
	private Documento doctoSelecionado;

	private int tabCapasIndex;
	private int tabContasIndex;

	private intra_condominios condominioSelecionado;
	private List<intra_condominios> listaCondominios;
	private boolean analit_sintet;

	public PastaDigitalMB() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.mesInicio = new DateTime().getMonthOfYear();
		this.mesFim = new DateTime().getMonthOfYear();
		this.anoInicio = new DateTime().getYear();
		this.anoFim = new DateTime().getYear();
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public List<Capa> getCapas() {
		return capas;
	}

	public void setCapas(List<Capa> capas) {
		this.capas = capas;
	}

	public Capa getCapa() {
		return capa;
	}

	public void setCapa(Capa capa) {
		this.capa = capa;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Lancamento> getLancamentosMes() {
		return lancamentosMes;
	}

	public void setLancamentosMes(List<Lancamento> lancamentosMes) {
		this.lancamentosMes = lancamentosMes;
	}

	public int getMesInicio() {
		return mesInicio;
	}

	public void setMesInicio(int mesInicio) {
		this.mesInicio = mesInicio;
	}

	public int getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(int anoInicio) {
		this.anoInicio = anoInicio;
	}

	public int getMesFim() {
		return mesFim;
	}

	public void setMesFim(int mesFim) {
		this.mesFim = mesFim;
	}

	public int getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(int anoFim) {
		this.anoFim = anoFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public Date getMesSelecionado() {
		return mesSelecionado;
	}

	public void setMesSelecionado(Date mesSelecionado) {
		this.mesSelecionado = mesSelecionado;
	}

	public List<Documento> getListaDoctos() {
		return listaDoctos;
	}

	public void setListaDoctos(List<Documento> listaDoctos) {
		this.listaDoctos = listaDoctos;
	}

	public Documento getDoctoSelecionado() {
		return doctoSelecionado;
	}

	public void setDoctoSelecionado(Documento doctoSelecionado) {
		this.doctoSelecionado = doctoSelecionado;
	}

	public int getTabCapasIndex() {
		return tabCapasIndex;
	}

	public void setTabCapasIndex(int tabCapasIndex) {
		this.tabCapasIndex = tabCapasIndex;
	}

	public int getTabContasIndex() {
		return tabContasIndex;
	}

	public void setTabContasIndex(int tabContasIndex) {
		this.tabContasIndex = tabContasIndex;
	}

	public intra_condominios getCondominioSelecionado() {
		return condominioSelecionado;
	}

	public void setCondominioSelecionado(intra_condominios condominioSelecionado) {
		this.condominioSelecionado = condominioSelecionado;
	}

	public boolean isAnalit_sintet() {
		return analit_sintet;
	}

	public void setAnalit_sintet(boolean analit_sintet) {
		this.analit_sintet = analit_sintet;
	}

	public List<intra_condominios> getListaCondominios() {
		return listaCondominios;
	}

	public void setListaCondominios(List<intra_condominios> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public void filtrar() throws Exception {
		try {
			PlanoContasDAO dao = new PlanoContasDAO();
			this.condominio.setCodigo((short) this.condominioSelecionado.getCodigo());
			this.condominio.setNome(this.condominioSelecionado.getNome());
			this.condominio = dao.pesquisaCnd(this.condominio);

			this.condominio.setAnalit_sitet(this.isAnalit_sintet());

			this.dataInicio = new DateTime().withMonthOfYear(this.mesInicio).withYear(this.anoInicio).toDate();
			this.dataFim = new DateTime().withMonthOfYear(this.mesInicio).withYear(this.anoInicio).toDate();
			if (!this.validaPeriodo(dataInicio, dataFim)) {
				this.capa = null;
				this.capas = new ArrayList<>();
			} else {
				if (this.condominio != null) {

					this.capas = new ArrayList<>();

					this.capas = dao.pesquisaCapas(this.condominio, dataInicio, dataFim);
					if (this.capas.size() > 0) {
						this.capa = this.capas.get(0);
						this.conta = this.capa.getContas().get(0);
						this.tabCapasIndex = 0;
						this.tabContasIndex = 0;
						this.pesquisaLancamentosTab(this.conta);
					} else {
						this.capa = null;
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum resultado encontrado!", ""));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum resultado encontrado!", ""));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public double getValorTotalPeriodo() {
		if (this.conta != null) {
			double valorTotal = 0;
			for (Lancamento lancamento : this.conta.getLancamentos()) {
				if (lancamento.getMes() == new DateTime(this.dataInicio).getMonthOfYear()
						&& lancamento.getAno() == new DateTime(this.dataInicio).getYear()) {
					valorTotal += lancamento.getValor();
				}
			}
			BigDecimal bd = new BigDecimal(valorTotal);
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			return bd.doubleValue();
		} else {
			return 0;
		}
	}

	public boolean validaPeriodo(Date dataInicio, Date dataFim) throws Exception {
		boolean sucesso = true;
		int periodo = Months.monthsBetween(new DateTime(dataInicio), new DateTime(dataFim)).getMonths();
		if (periodo > 11) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "O per�odo de pesquisa ultrapassou 12 meses!", ""));
			sucesso = false;
		} else if (dataFim.after(new Date()) || dataInicio.after(new Date())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"O m�s de pesquisa n�o pode ultrapassar o m�s corrente!", ""));
			sucesso = false;
		} else {
			sucesso = true;
		}
		return sucesso;
	}

	public void pesquisaPrimeraConta(Conta conta) {
		this.tabContasIndex = 0;
		this.pesquisaLancamentosTab(conta);
	}

	public void pesquisaLancamentosTab(Conta conta) {
		this.lancamentosMes = new ArrayList<>();
		this.conta = conta;
		for (Lancamento lancamento : this.conta.getLancamentos()) {
			this.lancamentosMes.add(lancamento);
		}
	}

	public void pesquisaDoctosLancto(Lancamento lancamento) {
		try {
			this.listaDoctos = new ArrayList<>();
			PlanoContasDAO dao = new PlanoContasDAO();
			this.listaDoctos = dao.pesquisaDoctosLancto(lancamento.getNumero(),
					(short) this.condominioSelecionado.getCodigo());
			this.doctoSelecionado = this.listaDoctos.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pesquisaDoctosLancto(Lancamento lancamento, intra_condominios condominioSelecionado) {
		try {
			this.listaDoctos = new ArrayList<>();
			PlanoContasDAO dao = new PlanoContasDAO();
			this.listaDoctos = dao.pesquisaDoctosLancto(lancamento.getNumero(),
					(short) condominioSelecionado.getCodigo());
			this.doctoSelecionado = this.listaDoctos.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}