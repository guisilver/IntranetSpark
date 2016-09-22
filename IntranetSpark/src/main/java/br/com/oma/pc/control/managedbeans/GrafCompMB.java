package br.com.oma.pc.control.managedbeans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;
import org.primefaces.model.chart.PieChartModel;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.pc.model.dao.GraficosDAO;
import br.com.oma.pc.model.dao.PlanoContasDAO;
import br.com.oma.pc.modelo.entidades.Capa;
import br.com.oma.pc.modelo.entidades.Condominio;
import br.com.oma.pc.modelo.entidades.Conta;
import br.com.oma.pc.modelo.entidades.Lancamento;

@ViewScoped
@ManagedBean
public class GrafCompMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1736390067537032799L;
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private List<Capa> capasMesAtual, capasDozeMeses;
	private Capa capaMesAtual, capaDozeMeses;
	private Conta conta;
	private List<Lancamento> lancamentosMes;
	private int mesInicio, anoInicio, mesFim, anoFim;
	private Condominio condominio = new Condominio();
	private Date mesSelecionado;
	private HashMap<Integer, GChartModel> mapGraficos = new HashMap<Integer, GChartModel>();
	private int periodoComparacao;
	private int indexTabContas;

	private PieChartModel pieModel1;
	private PieChartModel pieModel2;

	private intra_grupo_gerente gerente;

	private List<intra_condominios> listaCondominios;
	private intra_condominios condominioSelecionado;

	public GrafCompMB() {
		this.mesFim = new DateTime().minusMonths(1).getMonthOfYear();
		this.anoFim = new DateTime().minusMonths(1).getYear();
		this.periodoComparacao = 11;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public List<Capa> getCapasMesAtual() {
		return capasMesAtual;
	}

	public void setCapasMesAtual(List<Capa> capasMesAtual) {
		this.capasMesAtual = capasMesAtual;
	}

	public List<Capa> getCapasDozeMeses() {
		return capasDozeMeses;
	}

	public void setCapasDozeMeses(List<Capa> capasDozeMeses) {
		this.capasDozeMeses = capasDozeMeses;
	}

	public Capa getCapaMesAtual() {
		return capaMesAtual;
	}

	public void setCapaMesAtual(Capa capaMesAtual) {
		this.capaMesAtual = capaMesAtual;
	}

	public Capa getCapaDozeMeses() {
		return capaDozeMeses;
	}

	public void setCapaDozeMeses(Capa capaDozeMeses) {
		this.capaDozeMeses = capaDozeMeses;
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

	public HashMap<Integer, GChartModel> getMapGraficos() {
		return mapGraficos;
	}

	public void setMapGraficos(HashMap<Integer, GChartModel> mapGraficos) {
		this.mapGraficos = mapGraficos;
	}

	public int getPeriodoComparacao() {
		return periodoComparacao;
	}

	public void setPeriodoComparacao(int periodoComparacao) {
		this.periodoComparacao = periodoComparacao;
	}

	public int getIndexTabContas() {
		return indexTabContas;
	}

	public void setIndexTabContas(int indexTabContas) {
		this.indexTabContas = indexTabContas;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}

	public intra_grupo_gerente getGerente() {
		return gerente;
	}

	public void setGerente(intra_grupo_gerente gerente) {
		this.gerente = gerente;
	}

	public List<intra_condominios> getListaCondominios() {
		if (this.listaCondominios == null && this.sessaoMB.verificaDepto(" Todos")) {
			try {
				GraficosDAO dao;
				dao = new GraficosDAO();
				this.listaCondominios = dao.getListaTodosCondominios();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaCondominios;
	}

	public void setListaCondominios(List<intra_condominios> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public intra_condominios getCondominioSelecionado() {
		return condominioSelecionado;
	}

	public void setCondominioSelecionado(intra_condominios condominioSelecionado) {
		this.condominioSelecionado = condominioSelecionado;
	}

	public void filtrar() throws Exception {
		this.indexTabContas = 0;
		this.filtrarMesAtual();
		this.filtrarDozeMeses();
		this.getGeraGraficoMesAtual2();
		this.getGeraGraficoDozeMeses2();
	}

	public void filtrarMesAtual() throws Exception {
		try {
			PlanoContasDAO dao = new PlanoContasDAO();
			this.condominio.setCodigo((short) this.condominioSelecionado.getCodigo());
			this.condominio.setNome(this.condominioSelecionado.getNome());
			this.condominio = dao.pesquisaCnd(this.condominio);
			Date dataInicio = new DateTime().withMonthOfYear(this.mesFim).withYear(this.anoFim).toDate();
			Date dataFim = new DateTime().withMonthOfYear(this.mesFim).withYear(this.anoFim).toDate();
			if (this.condominio != null) {
				this.capasMesAtual = new ArrayList<>();
				this.capasMesAtual = dao.pesquisaCapas(this.condominio, dataInicio, dataFim);
				if (this.capasMesAtual.size() > 0) {
					this.capaMesAtual = this.capasMesAtual.get(0);
				} else {
					this.capaMesAtual = null;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum resultado encontrado!", ""));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum resultado encontrado!", ""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			e.printStackTrace();
		}
	}

	public void filtrarDozeMeses() throws Exception {
		try {
			PlanoContasDAO dao = new PlanoContasDAO();
			this.condominio.setCodigo((short) this.condominioSelecionado.getCodigo());
			this.condominio.setNome(this.condominioSelecionado.getNome());
			this.condominio = dao.pesquisaCnd(this.condominio);
			Date dataInicio = new DateTime().withMonthOfYear(this.mesFim).withYear(this.anoFim)
					.minusMonths(this.periodoComparacao).toDate();
			Date dataFim = new DateTime().withMonthOfYear(this.mesFim).withYear(this.anoFim).toDate();
			if (this.condominio != null) {
				this.capasDozeMeses = new ArrayList<>();
				this.capasDozeMeses = dao.pesquisaCapas(this.condominio, dataInicio, dataFim);
				if (this.capasDozeMeses.size() > 0) {
					this.capaDozeMeses = this.capasDozeMeses.get(0);
				} else {
					this.capaDozeMeses = null;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum resultado encontrado!", ""));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum resultado encontrado!", ""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public GChartModel getGeraGraficoMesAtual() throws Exception {
		if (this.capaMesAtual != null) {
			double valorTotalMes = 0;
			GChartModelBuilder cb = new GChartModelBuilder().setChartType(GChartType.PIE).addColumns("Mes", "Valor");
			for (Conta conta : this.capaMesAtual.getContas()) {
				valorTotalMes = 0;
				for (Lancamento lancamento : conta.getLancamentos()) {
					valorTotalMes += lancamento.getValor();
				}
				BigDecimal bd = new BigDecimal(valorTotalMes);
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				conta.setValorToral(bd.doubleValue());
				cb.addRow(conta.getNome(), bd.doubleValue());
			}
			return cb.build();
		} else {
			GChartModelBuilder cb = new GChartModelBuilder().setChartType(GChartType.PIE).addColumns("Mes", "Valor");
			cb.addRow("", 0);
			return cb.build();
		}
	}

	public GChartModel getGeraGraficoDozeMeses() throws Exception {
		if (this.capaDozeMeses != null) {
			double valorTotalMes = 0;
			GChartModelBuilder cb = new GChartModelBuilder().setChartType(GChartType.PIE).addColumns("Mes", "Valor");
			for (Conta conta : this.capaDozeMeses.getContas()) {
				valorTotalMes = 0;
				for (Lancamento lancamento : conta.getLancamentos()) {
					valorTotalMes += lancamento.getValor();
				}
				BigDecimal bd = new BigDecimal(valorTotalMes / (this.periodoComparacao + 1));
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				conta.setValorToral(bd.doubleValue());
				cb.addRow(conta.getNome(), bd.doubleValue());
			}
			return cb.build();
		} else {
			GChartModelBuilder cb = new GChartModelBuilder().setChartType(GChartType.PIE).addColumns("Mes", "Valor");
			cb.addRow("", 0);
			return cb.build();
		}
	}

	public void getGeraGraficoMesAtual2() throws Exception {
		pieModel1 = new PieChartModel();
		pieModel1.setTitle("Simple Pie");
		pieModel1.setLegendPosition("s");
		if (this.capaMesAtual != null) {
			double valorTotalMes = 0;
			for (Conta conta : this.capaMesAtual.getContas()) {
				valorTotalMes = 0;
				for (Lancamento lancamento : conta.getLancamentos()) {
					valorTotalMes += lancamento.getValor();
				}
				BigDecimal bd = new BigDecimal(valorTotalMes);
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				conta.setValorToral(bd.doubleValue());
				pieModel1.set(conta.getNome(), bd.doubleValue());
			}
		} else {
			pieModel1.set("", 0);
		}
	}

	public void getGeraGraficoDozeMeses2() throws Exception {
		pieModel2 = new PieChartModel();
		pieModel2.setTitle("Simple Pie");
		pieModel2.setLegendPosition("s");
		if (this.capaDozeMeses != null) {
			double valorTotalMes = 0;
			for (Conta conta : this.capaDozeMeses.getContas()) {
				valorTotalMes = 0;
				for (Lancamento lancamento : conta.getLancamentos()) {
					valorTotalMes += lancamento.getValor();
				}
				BigDecimal bd = new BigDecimal(valorTotalMes / (this.periodoComparacao + 1));
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				conta.setValorToral(bd.doubleValue());
				pieModel2.set(conta.getNome(), bd.doubleValue());
			}
		} else {
			pieModel2.set("", 0);
		}
	}

	public double calculaPercentual(List<Conta> contas, double valorTotalConta) {
		double valorTodasContas = 0;
		for (Conta conta : contas) {
			valorTodasContas += conta.getValorToral();
		}
		double percentual = ((valorTotalConta / valorTodasContas) * 100);
		return percentual;
	}

	public String getTitleGraficoMesAtual() {
		return this.mesFim + "/" + this.anoFim;
	}

	public String getTitleGraficoDozeMeses() {
		DateTime data = new DateTime().withMonthOfYear(this.mesFim).withYear(this.anoFim);
		return data.minusMonths(this.periodoComparacao).getMonthOfYear() + "/"
				+ data.minusMonths(this.periodoComparacao).getYear() + " - " + this.mesFim + "/" + this.anoFim;
	}

	public void setCapas(Capa capa) throws Exception {
		this.capaMesAtual = null;
		for (Capa aux : this.capasMesAtual) {
			if (aux.getCodigo() == capa.getCodigo()) {
				this.capaMesAtual = aux;
			}
		}
		this.capaDozeMeses = capa;
	}

	public void listarCondominios() {
		try {
			if (this.gerente != null) {
				GraficosDAO dao = new GraficosDAO();
				this.listaCondominios = dao.getListaCondominios(this.gerente.getCodigo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
