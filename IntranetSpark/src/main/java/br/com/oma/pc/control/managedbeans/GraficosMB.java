package br.com.oma.pc.control.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import br.com.oma.pc.model.dao.GraficosDAO;
import br.com.oma.pc.model.dao.PlanoContasDAO;
import br.com.oma.pc.modelo.entidades.Capa;
import br.com.oma.pc.modelo.entidades.Condominio;
import br.com.oma.pc.modelo.entidades.Conta;
import br.com.oma.pc.modelo.entidades.ElementoGrafico;
import br.com.oma.pc.modelo.entidades.Grafico;
import br.com.oma.pc.modelo.entidades.Lancamento;

@ManagedBean
@ViewScoped
public class GraficosMB implements Serializable {

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private static final long serialVersionUID = 1L;
	private List<Capa> capas, capasSelecionadas;
	private List<String> capasSelecionadasUtil;
	private Capa capa;
	private Conta conta;
	private List<Lancamento> lancamentosMes;
	private int mesInicio, anoInicio, mesFim, anoFim;
	private Date dataInicio, dataFim;
	private Condominio condominio = new Condominio();
	private Date mesSelecionado;
	private intra_grupo_gerente gerente;
	private intra_condominios condominioSelecionado;
	private boolean analit_sintet;
	private List<intra_grupo_gerente> listaGerentes;
	private List<intra_condominios> listaCondominios;
	private BarChartModel barModel;
	private LineChartModel lineModel;
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	private byte[] retorno = null;
	private String tipoGrafico;
	private boolean todasCapas;

	private List<intra_condominios> condominiosSelecionados;

	public GraficosMB() {
		this.mesInicio = new DateTime().minusMonths(6).getMonthOfYear();
		this.anoInicio = new DateTime().minusMonths(6).getYear();
		this.mesFim = new DateTime().minusMonths(1).getMonthOfYear();
		this.anoFim = new DateTime().minusMonths(1).getYear();
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

	public List<Capa> getCapasSelecionadas() {
		return capasSelecionadas;
	}

	public void setCapasSelecionadas(List<Capa> capasSelecionadas) {
		this.capasSelecionadas = capasSelecionadas;
	}

	public List<String> getCapasSelecionadasUtil() {
		return capasSelecionadasUtil;
	}

	public void setCapasSelecionadasUtil(List<String> capasSelecionadasUtil) {
		this.capasSelecionadasUtil = capasSelecionadasUtil;
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

	public intra_grupo_gerente getGerente() {
		return gerente;
	}

	public void setGerente(intra_grupo_gerente gerente) {
		this.gerente = gerente;
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

	public List<intra_grupo_gerente> getListaGerentes() {
		if (this.listaGerentes == null) {
			this.listaGerentes = this.retornaGerentes();
		}
		return listaGerentes;
	}

	public void setListaGerentes(List<intra_grupo_gerente> listaGerentes) {
		this.listaGerentes = listaGerentes;
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

	public BarChartModel getBarModel() {
		return barModel;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public byte[] getRetorno() {
		return retorno;
	}

	public void setRetorno(byte[] retorno) {
		this.retorno = retorno;
	}

	public String getTipoGrafico() {
		return tipoGrafico;
	}

	public void setTipoGrafico(String tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}

	public boolean isTodasCapas() {
		return todasCapas;
	}

	public void setTodasCapas(boolean todasCapas) {
		this.todasCapas = todasCapas;
	}

	public List<intra_condominios> getCondominiosSelecionados() {
		return condominiosSelecionados;
	}

	public void setCondominiosSelecionados(List<intra_condominios> condominiosSelecionados) {
		this.condominiosSelecionados = condominiosSelecionados;
	}

	public void filtrar() throws Exception {
		try {
			this.capasSelecionadasUtil = null;
			this.capasSelecionadas = null;
			this.todasCapas = false;
			PlanoContasDAO dao = new PlanoContasDAO();
			this.condominio.setCodigo((short) this.condominioSelecionado.getCodigo());
			this.condominio.setNome(this.condominioSelecionado.getNome());
			this.condominio = dao.pesquisaCnd(this.condominio);
			this.condominio.setAnalit_sitet(this.isAnalit_sintet());
			this.dataInicio = new DateTime().withMonthOfYear(this.mesInicio).withYear(this.anoInicio).toDate();
			this.dataFim = new DateTime().withMonthOfYear(this.mesFim).withYear(this.anoFim).toDate();
			if (!this.validaPeriodo(dataInicio, dataFim)) {
				this.capa = null;
				this.capas = new ArrayList<>();
			} else {
				if (this.condominio != null) {
					this.capas = new ArrayList<>();
					this.capas = dao.pesquisaCapas(this.condominio, dataInicio, dataFim);
					if (this.capas.size() > 0) {
						this.capa = this.capas.get(0);
						RequestContext.getCurrentInstance().execute("PF('dlgSelecionaCapas').show();");
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

	public void itemSelect(ItemSelectEvent event) {
		this.lancamentosMes = new ArrayList<>();
		List<ChartSeries> series = barModel.getSeries();
		ChartSeries girls = series.get(0);
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String codigo = params.get("codigo");
		for (Conta conta : this.capa.getContas()) {
			if (conta.getCodigo() == Integer.valueOf(codigo)) {
				this.conta = conta;
			}
		}
		String[] str = null;
		int mes = 0;
		int ano = 0;
		String label = "";
		int i = 0;
		for (Entry<Object, Number> entry : girls.getData().entrySet()) {
			if (i == event.getItemIndex()) {
				label = entry.getKey().toString();
			}
			i++;
		}
		str = label.split("/");
		mes = Integer.parseInt(str[0]);
		ano = Integer.parseInt(str[1]);
		this.mesSelecionado = new DateTime().withMonthOfYear(mes).withYear(ano).toDate();
		for (Lancamento lancamento : this.conta.getLancamentos()) {
			if (lancamento.getMes() == mes && lancamento.getAno() == ano) {
				this.lancamentosMes.add(lancamento);
			}
		}
		RequestContext.getCurrentInstance().execute("PF('dlgDetalhesContas').show();");
	}

	public double getValorTotalPeriodo() {
		if (this.conta != null) {
			double valorTotal = 0;
			for (Lancamento lancamento : this.conta.getLancamentos()) {
				if (lancamento.getMes() == new DateTime(this.mesSelecionado).getMonthOfYear()
						&& lancamento.getAno() == new DateTime(this.mesSelecionado).getYear()) {
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
					new FacesMessage(FacesMessage.SEVERITY_WARN, "O periodo de pesquisa ultrapassou 12 meses!", ""));
			sucesso = false;
		} else if (dataFim.after(new Date()) || dataInicio.after(new Date())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"O mes de pesquisa nao pode ultrapassar o mes corrente!", ""));
			sucesso = false;
		} else {
			sucesso = true;
		}
		return sucesso;
	}

	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				if (this.gerente == null) {
					this.gerente = this.sessaoMB.getListaDeGerente().get(0);
				}
				this.listarCondominios();
				return this.sessaoMB.getListaDeGerente();
			} else {
				if (this.sessaoMB.getUsuario().getGrupoGer() != null) {
					if (this.gerente == null) {
						this.gerente = this.sessaoMB.getUsuario().getGrupoGer().get(0);
					}
					this.listarCondominios();
				}
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
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

	public void listarTodosCondominios() {
		try {
			if (this.gerente != null) {
				GraficosDAO dao = new GraficosDAO();
				this.listaCondominios = dao.getListaTodosCondominios();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BarChartModel geraGraficoBarraConta(Conta conta, Date dataInicio, Date dataFim) throws Exception {
		if (this.capasSelecionadas.size() > 0) {
			BarChartModel model = new BarChartModel();
			ChartSeries series = new ChartSeries();
			double valorTotalMes = 0;
			int mes = new DateTime(dataInicio).getMonthOfYear();
			int ano = new DateTime(dataInicio).getYear();
			int mesesDiferenca = Months.monthsBetween(new DateTime(dataInicio), new DateTime(dataFim)).getMonths();
			for (int i = 0; i <= mesesDiferenca; i++) {
				valorTotalMes = 0;
				for (Lancamento lancamento : conta.getLancamentos()) {
					if (lancamento.getMes() == mes && lancamento.getAno() == ano) {
						valorTotalMes += lancamento.getValor();
					}
				}
				BigDecimal bd = new BigDecimal(valorTotalMes);
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				series.set(mes + "/" + ano, bd.doubleValue());
				mes++;
				if (mes == 13) {
					ano++;
					mes = 1;
				}
			}
			model.addSeries(series);
			this.barModel = model;
			return model;
		}
		return null;
	}

	public LineChartModel geraGraficoLinhaConta(Capa capa, Date dataInicio, Date dataFim) throws Exception {
		LineChartModel lineModel = new LineChartModel();
		lineModel.setZoom(true);
		try {
			double valorMaior = 0;
			LineChartSeries series = null;
			if (this.capasSelecionadas.size() > 0) {
				for (Conta conta : capa.getContas()) {
					series = new LineChartSeries();
					series.setLabel(this.trataNomeConta(conta.getNome()));
					double valorTotalMes = 0;
					int mes = new DateTime(dataInicio).getMonthOfYear();
					int ano = new DateTime(dataInicio).getYear();
					int mesesDiferenca = Months.monthsBetween(new DateTime(dataInicio), new DateTime(dataFim))
							.getMonths();
					for (int i = 0; i <= mesesDiferenca; i++) {
						valorTotalMes = 0;
						for (Lancamento lancamento : conta.getLancamentos()) {
							if (lancamento.getMes() == mes && lancamento.getAno() == ano) {
								valorTotalMes += lancamento.getValor();
							}
						}
						BigDecimal bd = new BigDecimal(valorTotalMes);
						bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
						series.set(mes + "/" + ano, bd.doubleValue());
						mes++;
						if (bd.doubleValue() > valorMaior) {
							valorMaior = bd.doubleValue();
						}
						if (mes == 13) {
							ano++;
							mes = 1;
						}
					}
					lineModel.addSeries(series);
				}
			}
			lineModel.setLegendPosition("w");
			lineModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
			lineModel.setShowPointLabels(true);
			lineModel.getAxes().put(AxisType.X, new CategoryAxis("Mês/Ano"));
			Axis yAxis = lineModel.getAxis(AxisType.Y);
			yAxis.setLabel("Valor");
			yAxis.setMin(0);
			yAxis.setMax(valorMaior + (valorMaior / 10));

			return lineModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineModel;
	}

	public void listarCapasSelecionadas() {
		try {
			this.capasSelecionadas = new ArrayList<>();
			for (Capa capa : this.capas) {
				if (this.capasSelecionadasUtil.contains(capa.getNome())) {
					this.capasSelecionadas.add(capa);
				}
			}
			if (this.capasSelecionadas.size() > 0) {
				RequestContext.getCurrentInstance().execute("PF('dlgSelecionaCapas').hide();");
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione ao menos uma capa!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tabChange(Capa capa) {
		this.capa = capa;
	}

	public void gerarRelatorio() {
		try {
			List<Grafico> listaGraficos = new ArrayList<Grafico>();
			List<ElementoGrafico> listaCrossTab = new ArrayList<ElementoGrafico>();
			Grafico grafico = null;
			ElementoGrafico elemento = null;
			for (Capa capa : this.capasSelecionadas) {
				for (Conta conta : capa.getContas()) {
					grafico = new Grafico();
					double valorTotalMes = 0;
					int mes = new DateTime(dataInicio).getMonthOfYear();
					int ano = new DateTime(dataInicio).getYear();
					int mesesDiferenca = Months.monthsBetween(new DateTime(dataInicio), new DateTime(dataFim))
							.getMonths();
					for (int i = 0; i <= mesesDiferenca; i++) {
						elemento = new ElementoGrafico();
						valorTotalMes = 0;
						for (Lancamento lancamento : conta.getLancamentos()) {
							if (lancamento.getMes() == mes && lancamento.getAno() == ano) {
								valorTotalMes += lancamento.getValor();
							}
						}
						BigDecimal bd = new BigDecimal(valorTotalMes);
						bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
						elemento.setMesAno(mes + "/" + ano);
						elemento.setValor(bd.doubleValue());
						grafico.getElementos().add(elemento);
						mes++;
						if (mes == 13) {
							ano++;
							mes = 1;
						}
						listaCrossTab.add(elemento);
						elemento.setNomeConta(conta.getNome());
					}
					grafico.setCapa(capa);
					grafico.setNomeConta(conta.getNome());
					listaGraficos.add(grafico);
				}
				capa.getListaGraficos().addAll(listaGraficos);
				capa.getListaElementoGrafico().addAll(listaCrossTab);
				listaCrossTab = new ArrayList<>();
				listaGraficos = new ArrayList<>();
			}

			if (this.tipoGrafico.equals("Barra")) {
				this.populaRelatorioBarra(this.capasSelecionadas, "GraficoBarraCapa");
			} else if (this.tipoGrafico.equals("Linha")) {
				this.populaRelatorioLinha(this.capasSelecionadas, "GraficoLinhaCapa");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gerarRelatorioEspecial() throws Exception {
		PDFMergerUtility merger = new PDFMergerUtility();
		for (intra_condominios cnd : this.condominiosSelecionados) {
			this.condominioSelecionado = cnd;

			this.filtrar();
			this.capasSelecionadasUtil = new ArrayList<>();
			this.capasSelecionadasUtil.add("ORDINARIA");
			this.listarCapasSelecionadas();

			try {
				List<Grafico> listaGraficos = new ArrayList<Grafico>();
				List<ElementoGrafico> listaCrossTab = new ArrayList<ElementoGrafico>();
				Grafico grafico = null;
				ElementoGrafico elemento = null;
				for (Capa capa : this.capasSelecionadas) {
					for (Conta conta : capa.getContas()) {
						grafico = new Grafico();
						double valorTotalMes = 0;
						int mes = new DateTime(dataInicio).getMonthOfYear();
						int ano = new DateTime(dataInicio).getYear();
						int mesesDiferenca = Months.monthsBetween(new DateTime(dataInicio), new DateTime(dataFim))
								.getMonths();
						for (int i = 0; i <= mesesDiferenca; i++) {
							elemento = new ElementoGrafico();
							valorTotalMes = 0;
							for (Lancamento lancamento : conta.getLancamentos()) {
								if (lancamento.getMes() == mes && lancamento.getAno() == ano) {
									valorTotalMes += lancamento.getValor();
								}
							}
							BigDecimal bd = new BigDecimal(valorTotalMes);
							bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
							elemento.setMesAno(mes + "/" + ano);
							elemento.setValor(bd.doubleValue());
							grafico.getElementos().add(elemento);
							mes++;
							if (mes == 13) {
								ano++;
								mes = 1;
							}
							listaCrossTab.add(elemento);
							elemento.setNomeConta(conta.getNome());
						}
						grafico.setCapa(capa);
						grafico.setNomeConta(conta.getNome());
						listaGraficos.add(grafico);
					}
					capa.getListaGraficos().addAll(listaGraficos);
					capa.getListaElementoGrafico().addAll(listaCrossTab);
					listaCrossTab = new ArrayList<>();
					listaGraficos = new ArrayList<>();
				}

				byte[] lol = null;
				if (this.tipoGrafico.equals("Barra")) {
					lol = this.populaRelatorioBarraEspecial(this.capasSelecionadas, "GraficoBarraCapa");

				} else if (this.tipoGrafico.equals("Linha")) {
					lol = this.populaRelatorioLinhaEspecial(this.capasSelecionadas, "GraficoLinhaCapa");
				}
				merger.addSource(new ByteArrayInputStream(lol));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		merger.setDestinationStream(byteArrayOutputStream);
		merger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
		byte[] b = byteArrayOutputStream.toByteArray();
		this.downloadPDF(b);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] populaRelatorioBarraEspecial(List<Capa> listaCapas, String nomeRelatorio) throws Exception {
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator;
			HashMap parametros = new HashMap();
			parametros.put("SUBREPORT_DIR", caminho_relatorio);
			parametros.put("nomeCondominio", this.condominio.getNome());
			parametros.put("codigoCondominio", this.condominio.getCodigo());
			parametros.put("periodo", this.getLabelPeriodo());
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			return rju.geraRelatorioGrafico(parametros, nomeRelatorio, nomeRelatorio, 1, listaCapas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public byte[] populaRelatorioLinhaEspecial(List<Capa> listaCapas, String nomeRelatorio) throws Exception {
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator;
			@SuppressWarnings("rawtypes")
			HashMap parametros = new HashMap();
			parametros.put("SUBREPORT_DIR", caminho_relatorio);
			parametros.put("nomeCondominio", this.condominio.getNome());
			parametros.put("codigoCondominio", this.condominio.getCodigo());
			parametros.put("periodo", this.getLabelPeriodo());
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			return rju.geraRelatorioGraficoLinha(parametros, nomeRelatorio, nomeRelatorio, 1, listaCapas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void populaRelatorioBarra(List<Capa> listaCapas, String nomeRelatorio) throws Exception {
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator;
			HashMap parametros = new HashMap();
			parametros.put("SUBREPORT_DIR", caminho_relatorio);
			parametros.put("nomeCondominio", this.condominio.getNome());
			parametros.put("codigoCondominio", this.condominio.getCodigo());
			parametros.put("periodo", this.getLabelPeriodo());
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			this.retorno = rju.geraRelatorioGrafico(parametros, nomeRelatorio, nomeRelatorio, 1, listaCapas);
			this.downloadPDF(this.retorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void populaRelatorioLinha(List<Capa> listaCapas, String nomeRelatorio) throws Exception {
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator;
			HashMap parametros = new HashMap();
			parametros.put("SUBREPORT_DIR", caminho_relatorio);
			parametros.put("nomeCondominio", this.condominio.getNome());
			parametros.put("codigoCondominio", this.condominio.getCodigo());
			parametros.put("periodo", this.getLabelPeriodo());
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			this.retorno = rju.geraRelatorioGraficoLinha(parametros, nomeRelatorio, nomeRelatorio, 1, listaCapas);
			this.downloadPDF(this.retorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadPDF(byte[] retorno) throws IOException {
		// Prepare.
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			// Open file.
			input = new BufferedInputStream(new ByteArrayInputStream(retorno), DEFAULT_BUFFER_SIZE);
			// Init servlet response.
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(retorno.length));
			response.setHeader("Content-Disposition", "inline;filename=\"GraficoBarra.pdf\"");
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			// Finalize task.
			output.flush();
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}
		// Inform JSF that it doesn't need to handle response.
		// This is very important, otherwise you will get the following
		// exception in the logs:
		// java.lang.IllegalStateException: Cannot forward after response
		// has
		// been committed.
		facesContext.responseComplete();
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or
				// mail
				// it. It may be useful to
				// know that this will generally only be thrown when the
				// client
				// aborted the download.
				e.printStackTrace();
			}
		}
	}

	public String getLabelPeriodo() {
		String periodo = "PERÍODO DE "
				+ new java.text.SimpleDateFormat("MMMM'/'yyyy", new Locale("pt", "BR")).format(this.dataInicio) + " À "
				+ new java.text.SimpleDateFormat("MMMM'/'yyyy", new Locale("pt", "BR")).format(this.dataFim);
		return periodo.toUpperCase();
	}

	public String trataNomeConta(String nomeConta) {
		nomeConta = nomeConta.toUpperCase();
		nomeConta = nomeConta.replace("'", "");
		nomeConta = nomeConta.replace("-", "");
		nomeConta = nomeConta.replace("Ã", "A");
		nomeConta = nomeConta.replace("Á", "A");
		nomeConta = nomeConta.replace("É", "E");
		nomeConta = nomeConta.replace("Í", "I");
		nomeConta = nomeConta.replace("Ó", "O");
		nomeConta = nomeConta.replace("Ú", "U");
		nomeConta = nomeConta.replace("Ç", "C");
		return nomeConta;
	}

	public void selecionaTodasCapas() {
		if (this.todasCapas) {
			this.capasSelecionadasUtil = new ArrayList<>();
			if (this.capas != null) {
				for (Capa capa : this.capas) {
					this.capasSelecionadasUtil.add(capa.getNome());
				}
			}
		} else {
			this.capasSelecionadasUtil = new ArrayList<>();
		}
	}

}