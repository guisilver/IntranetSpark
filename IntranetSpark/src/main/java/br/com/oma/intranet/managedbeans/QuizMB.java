package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.oma.intranet.dao.QuizDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_quiz_controle_condominios;
import br.com.oma.intranet.entidades.intra_quiz_controle_fase_geral;
import br.com.oma.intranet.entidades.intra_quiz_controle_respostas;
import br.com.oma.intranet.entidades.intra_quiz_emails;
import br.com.oma.intranet.entidades.intra_quiz_param;
import br.com.oma.intranet.entidades.intra_quiz_perguntas;
import br.com.oma.intranet.entidades.intra_quiz_respostas;
import br.com.oma.intranet.util.AESencrp;

@ViewScoped
@ManagedBean
public class QuizMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -14564694034945122L;
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private intra_condominios condominio;
	private List<intra_grupo_gerente> listaGerentes;
	private List<intra_condominios> listaCondominios;

	private intra_quiz_perguntas perguntas = new intra_quiz_perguntas();
	private intra_quiz_respostas respostas = new intra_quiz_respostas();

	private List<intra_quiz_respostas> listaRespostas, respostasGerencia, respostasAssembleias, respostasOMA,
			respostasPrincipaisServicos, listaRespostasSelecionadas, listaSugestoes;

	private List<intra_quiz_controle_fase_geral> listaFases;
	private intra_quiz_controle_fase_geral fase;
	private int pergunta = 0;
	private PieChartModel grafico1, grafico2, grafico3;
	private BarChartModel barra1;

	private int qtdCndResp = 0;

	private String exibePergunta, exibeResposta;

	private intra_quiz_param parametros = new intra_quiz_param();
	private intra_quiz_emails email = new intra_quiz_emails();
	private List<intra_quiz_emails> emails = new ArrayList<>();

	private intra_quiz_respostas sugestaoSelecionada;

	@PostConstruct
	public void init() {
		QuizDAO dao = new QuizDAO();
		this.parametros = dao.pesquisaParametros();
		this.perguntas = dao.getPerguntas();
		if (this.parametros == null) {
			this.parametros = new intra_quiz_param();
		}
		if (this.perguntas == null) {
			this.perguntas = new intra_quiz_perguntas();
		}
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public List<intra_grupo_gerente> getListaGerentes() {
		if (this.listaGerentes == null) {
			this.listaGerentes = this.retornaGerentes();
			this.getListaCondominios();
		}
		return listaGerentes;
	}

	public void setListaGerentes(List<intra_grupo_gerente> listaGerentes) {
		this.listaGerentes = listaGerentes;
	}

	public List<intra_condominios> getListaCondominios() {
		if (this.listaCondominios == null) {
			QuizDAO dao = new QuizDAO();
			this.listaCondominios = dao.getListaCondominios();
		}
		return listaCondominios;
	}

	public void setListaCondominios(List<intra_condominios> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public intra_quiz_perguntas getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(intra_quiz_perguntas perguntas) {
		this.perguntas = perguntas;
	}

	public intra_quiz_respostas getRespostas() {
		return respostas;
	}

	public void setRespostas(intra_quiz_respostas respostas) {
		this.respostas = respostas;
	}

	public List<intra_quiz_respostas> getListaRespostas() {
		return listaRespostas;
	}

	public void setListaRespostas(List<intra_quiz_respostas> listaRespostas) {
		this.listaRespostas = listaRespostas;
	}

	public List<intra_quiz_respostas> getRespostasGerencia() {
		return respostasGerencia;
	}

	public void setRespostasGerencia(List<intra_quiz_respostas> respostasGerencia) {
		this.respostasGerencia = respostasGerencia;
	}

	public List<intra_quiz_respostas> getRespostasAssembleias() {
		return respostasAssembleias;
	}

	public void setRespostasAssembleias(List<intra_quiz_respostas> respostasAssembleias) {
		this.respostasAssembleias = respostasAssembleias;
	}

	public List<intra_quiz_respostas> getRespostasOMA() {
		return respostasOMA;
	}

	public void setRespostasOMA(List<intra_quiz_respostas> respostasOMA) {
		this.respostasOMA = respostasOMA;
	}

	public List<intra_quiz_respostas> getRespostasPrincipaisServicos() {
		return respostasPrincipaisServicos;
	}

	public void setRespostasPrincipaisServicos(List<intra_quiz_respostas> respostasPrincipaisServicos) {
		this.respostasPrincipaisServicos = respostasPrincipaisServicos;
	}

	public List<intra_quiz_respostas> getListaRespostasSelecionadas() {
		return listaRespostasSelecionadas;
	}

	public void setListaRespostasSelecionadas(List<intra_quiz_respostas> listaRespostasSelecionadas) {
		this.listaRespostasSelecionadas = listaRespostasSelecionadas;
	}

	public List<intra_quiz_respostas> getListaSugestoes() {
		return listaSugestoes;
	}

	public void setListaSugestoes(List<intra_quiz_respostas> listaSugestoes) {
		this.listaSugestoes = listaSugestoes;
	}

	public List<intra_quiz_controle_fase_geral> getListaFases() {
		if (this.listaFases == null) {
			QuizDAO dao = new QuizDAO();
			this.listaFases = dao.getListaFases();
		}
		return listaFases;
	}

	public intra_quiz_controle_fase_geral getFase() {
		return fase;
	}

	public void setFase(intra_quiz_controle_fase_geral fase) {
		this.fase = fase;
	}

	public int getPergunta() {
		return pergunta;
	}

	public void setPergunta(int pergunta) {
		this.pergunta = pergunta;
	}

	public PieChartModel getGrafico1() {
		return grafico1;
	}

	public void setGrafico1(PieChartModel grafico1) {
		this.grafico1 = grafico1;
	}

	public PieChartModel getGrafico2() {
		return grafico2;
	}

	public void setGrafico2(PieChartModel grafico2) {
		this.grafico2 = grafico2;
	}

	public PieChartModel getGrafico3() {
		return grafico3;
	}

	public void setGrafico3(PieChartModel grafico3) {
		this.grafico3 = grafico3;
	}

	public BarChartModel getBarra1() {
		return barra1;
	}

	public void setBarra1(BarChartModel barra1) {
		this.barra1 = barra1;
	}

	public int getQtdCndResp() {
		return qtdCndResp;
	}

	public void setQtdCndResp(int qtdCndResp) {
		this.qtdCndResp = qtdCndResp;
	}

	public String getExibePergunta() {
		return exibePergunta;
	}

	public void setExibePergunta(String exibePergunta) {
		this.exibePergunta = exibePergunta;
	}

	public String getExibeResposta() {
		return exibeResposta;
	}

	public void setExibeResposta(String exibeResposta) {
		this.exibeResposta = exibeResposta;
	}

	public intra_quiz_param getParametros() {
		return parametros;
	}

	public void setParametros(intra_quiz_param parametros) {
		this.parametros = parametros;
	}

	public intra_quiz_emails getEmail() {
		return email;
	}

	public void setEmail(intra_quiz_emails email) {
		this.email = email;
	}

	public List<intra_quiz_emails> getEmails() {
		if (this.emails == null || this.emails.size() == 0) {
			QuizDAO dao = new QuizDAO();
			this.emails = dao.getEmails();
		}
		return emails;
	}

	public void setEmails(List<intra_quiz_emails> emails) {
		this.emails = emails;
	}

	public intra_quiz_respostas getSugestaoSelecionada() {
		return sugestaoSelecionada;
	}

	public void setSugestaoSelecionada(intra_quiz_respostas sugestaoSelecionada) {
		this.sugestaoSelecionada = sugestaoSelecionada;
	}

	public void salvarPerguntas() {
		try {
			QuizDAO dao = new QuizDAO();
			dao.salvarPerguntas(this.perguntas);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", e.getMessage()));
		}
	}

	public void constroiGrafico() {
		try {
			if (this.fase != null) {
				List<intra_quiz_respostas> listaRespostas = null;
				QuizDAO dao = new QuizDAO();

				if (this.sessaoMB.getGerenteSelecionado() == null) {
					listaRespostas = dao.pesquisaRespostasPergunta(this.fase.getCodigo());
				} else {
					listaRespostas = dao.pesquisaRespostasPerguntaGerente(this.fase.getCodigo(),
							this.sessaoMB.getGerenteSelecionado().getCodigo());
				}

				this.listaRespostas = listaRespostas;

				this.qtdCndResp = listaRespostas.size();

				this.constroiGraficoGerencia(listaRespostas);
				this.constroiGraficoAssembleias(listaRespostas);
				this.constroiGraficoOMA(listaRespostas);
				this.constroiBarra(listaRespostas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void constroiGraficoGerencia(List<intra_quiz_respostas> listaRespostas) {
		List<intra_quiz_respostas> nota0 = new ArrayList<>();
		List<intra_quiz_respostas> nota1 = new ArrayList<>();
		List<intra_quiz_respostas> nota2 = new ArrayList<>();
		List<intra_quiz_respostas> nota3 = new ArrayList<>();
		List<intra_quiz_respostas> nota4 = new ArrayList<>();
		this.respostasGerencia = new ArrayList<>();

		for (intra_quiz_respostas aux : listaRespostas) {
			switch (aux.getResposta1()) {
			case "Nunca":
				nota0.add(aux);
				break;
			case "Raramente":
				nota1.add(aux);
				break;
			case "Frequentemente":
				nota2.add(aux);
				break;
			case "Sempre":
				nota3.add(aux);
				break;
			case "NU":
				nota4.add(aux);
				break;
			default:
				break;
			}
			this.respostasGerencia.add(aux);
		}

		this.grafico1 = new PieChartModel();
		this.grafico1.setTitle("Gerência");
		this.grafico1.setLegendPosition("w");
		this.grafico1.set("Sempre", nota3.size());
		this.grafico1.set("Frequentemente", nota2.size());
		this.grafico1.set("Raramente", nota1.size());
		this.grafico1.set("Nunca", nota0.size());
		this.grafico1.set("NU", nota4.size());
		// this.grafico1.setSeriesColors("48B591, FFF90E, FF800E, FF0808");
		this.grafico1.setSeriesColors("00923F,  EEE300, E77817, DA251D, 000000");
	}

	public void constroiGraficoAssembleias(List<intra_quiz_respostas> listaRespostas) {
		List<intra_quiz_respostas> nota0 = new ArrayList<>();
		List<intra_quiz_respostas> nota1 = new ArrayList<>();
		List<intra_quiz_respostas> nota2 = new ArrayList<>();
		List<intra_quiz_respostas> nota3 = new ArrayList<>();
		List<intra_quiz_respostas> nota4 = new ArrayList<>();
		this.respostasAssembleias = new ArrayList<>();

		for (intra_quiz_respostas aux : listaRespostas) {
			switch (aux.getResposta7()) {
			case "Nunca":
				nota0.add(aux);
				break;
			case "Raramente":
				nota1.add(aux);
				break;
			case "Frequentemente":
				nota2.add(aux);
				break;
			case "Sempre":
				nota3.add(aux);
				break;
			case "NU":
				nota4.add(aux);
				break;
			default:
				break;
			}
			this.respostasAssembleias.add(aux);
		}

		this.grafico2 = new PieChartModel();
		this.grafico2.setTitle("Assembleias");
		this.grafico2.setLegendPosition("w");
		this.grafico2.set("Sempre", nota3.size());
		this.grafico2.set("Frequentemente", nota2.size());
		this.grafico2.set("Raramente", nota1.size());
		this.grafico2.set("Nunca", nota0.size());
		this.grafico2.set("NU", nota4.size());
		this.grafico2.setSeriesColors("00923F,  EEE300, E77817, DA251D, 000000");
	}

	public void constroiGraficoOMA(List<intra_quiz_respostas> listaRespostas) {
		List<intra_quiz_respostas> nota0 = new ArrayList<>();
		List<intra_quiz_respostas> nota1 = new ArrayList<>();
		List<intra_quiz_respostas> nota2 = new ArrayList<>();
		List<intra_quiz_respostas> nota3 = new ArrayList<>();
		List<intra_quiz_respostas> nota4 = new ArrayList<>();
		this.respostasOMA = new ArrayList<>();

		for (intra_quiz_respostas aux : listaRespostas) {
			switch (aux.getResposta8()) {
			case "Nunca":
				nota0.add(aux);
				break;
			case "Raramente":
				nota1.add(aux);
				break;
			case "Frequentemente":
				nota2.add(aux);
				break;
			case "Sempre":
				nota3.add(aux);
				break;
			case "NU":
				nota4.add(aux);
				break;
			default:
				break;
			}
			this.respostasOMA.add(aux);
		}

		this.grafico3 = new PieChartModel();
		this.grafico3.setTitle("OMA");
		this.grafico3.setLegendPosition("w");
		this.grafico3.set("Sempre", nota3.size());
		this.grafico3.set("Frequentemente", nota2.size());
		this.grafico3.set("Raramente", nota1.size());
		this.grafico3.set("Nunca", nota0.size());
		this.grafico3.set("NU", nota4.size());
		this.grafico3.setSeriesColors("00923F,  EEE300, E77817, DA251D, 000000");
	}

	public void constroiBarra(List<intra_quiz_respostas> listaRespostas) {
		List<intra_quiz_respostas> financeiroS = new ArrayList<>(), financeiroF = new ArrayList<>(),
				financeiroR = new ArrayList<>(), financeiroN = new ArrayList<>(), financeiroNU = new ArrayList<>();
		;
		List<intra_quiz_respostas> dpS = new ArrayList<>(), dpF = new ArrayList<>(), dpR = new ArrayList<>(),
				dpN = new ArrayList<>(), dpNU = new ArrayList<>();
		List<intra_quiz_respostas> providenciasS = new ArrayList<>(), providenciasF = new ArrayList<>(),
				providenciasR = new ArrayList<>(), providenciasN = new ArrayList<>(),
				providenciasNU = new ArrayList<>();
		List<intra_quiz_respostas> cobrancaS = new ArrayList<>(), cobrancaF = new ArrayList<>(),
				cobrancaR = new ArrayList<>(), cobrancaN = new ArrayList<>(), cobrancaNU = new ArrayList<>();
		List<intra_quiz_respostas> juridicoS = new ArrayList<>(), juridicoF = new ArrayList<>(),
				juridicoR = new ArrayList<>(), juridicoN = new ArrayList<>(), juridicoNU = new ArrayList<>();
		List<intra_quiz_respostas> rhS = new ArrayList<>(), rhF = new ArrayList<>(), rhR = new ArrayList<>(),
				rhN = new ArrayList<>(), rhNU = new ArrayList<>();
		this.respostasPrincipaisServicos = new ArrayList<>();
		this.respostasPrincipaisServicos.addAll(listaRespostas);

		for (intra_quiz_respostas aux : listaRespostas) {
			if (aux.getResposta2() != null) {
				switch (aux.getResposta2()) {
				case "Nunca":
					financeiroN.add(aux);
					break;
				case "Raramente":
					financeiroR.add(aux);
					break;
				case "Frequentemente":
					financeiroF.add(aux);
					break;
				case "Sempre":
					financeiroS.add(aux);
					break;
				case "NU":
					financeiroNU.add(aux);
					break;
				default:
					break;
				}
			}
		}

		for (intra_quiz_respostas aux : listaRespostas) {
			if (aux.getResposta3() != null) {
				switch (aux.getResposta3()) {
				case "Nunca":
					dpN.add(aux);
					break;
				case "Raramente":
					dpR.add(aux);
					break;
				case "Frequentemente":
					dpF.add(aux);
					break;
				case "Sempre":
					dpS.add(aux);
					break;
				case "NU":
					dpNU.add(aux);
					break;
				default:
					break;
				}
			}
		}

		for (intra_quiz_respostas aux : listaRespostas) {
			if (aux.getResposta4() != null) {
				switch (aux.getResposta4()) {
				case "Nunca":
					providenciasN.add(aux);
					break;
				case "Raramente":
					providenciasR.add(aux);
					break;
				case "Frequentemente":
					providenciasF.add(aux);
					break;
				case "Sempre":
					providenciasS.add(aux);
					break;
				case "NU":
					providenciasNU.add(aux);
					break;
				default:
					break;
				}
			}
		}

		for (intra_quiz_respostas aux : listaRespostas) {
			if (aux.getResposta5() != null) {
				switch (aux.getResposta5()) {
				case "Nunca":
					cobrancaN.add(aux);
					break;
				case "Raramente":
					cobrancaR.add(aux);
					break;
				case "Frequentemente":
					cobrancaF.add(aux);
					break;
				case "Sempre":
					cobrancaS.add(aux);
					break;
				case "NU":
					cobrancaNU.add(aux);
					break;
				default:
					break;
				}
			}
		}

		for (intra_quiz_respostas aux : listaRespostas) {
			if (aux.getResposta6() != null) {
				switch (aux.getResposta6()) {
				case "Nunca":
					juridicoN.add(aux);
					break;
				case "Raramente":
					juridicoR.add(aux);
					break;
				case "Frequentemente":
					juridicoF.add(aux);
					break;
				case "Sempre":
					juridicoS.add(aux);
					break;
				case "NU":
					juridicoNU.add(aux);
					break;
				default:
					break;
				}
			}
		}

		for (intra_quiz_respostas aux : listaRespostas) {
			if (aux.getResposta9() != null) {
				switch (aux.getResposta9()) {
				case "Nunca":
					rhN.add(aux);
					break;
				case "Raramente":
					rhR.add(aux);
					break;
				case "Frequentemente":
					rhF.add(aux);
					break;
				case "Sempre":
					rhS.add(aux);
					break;
				case "NU":
					rhNU.add(aux);
					break;
				default:
					break;
				}
			}
		}

		this.barra1 = new BarChartModel();
		ChartSeries sempre = new ChartSeries();
		sempre.setLabel("Sempre");
		sempre.set("Financeiro", financeiroS.size());
		sempre.set("DP", dpS.size());
		sempre.set("Providencias", providenciasS.size());
		sempre.set("Cobrança", cobrancaS.size());
		sempre.set("Juridico", juridicoS.size());
		sempre.set("RH", rhS.size());

		ChartSeries frequentemente = new ChartSeries();
		frequentemente.setLabel("Frequentemente");
		frequentemente.set("Financeiro", financeiroF.size());
		frequentemente.set("DP", dpF.size());
		frequentemente.set("Providencias", providenciasF.size());
		frequentemente.set("Cobrança", cobrancaF.size());
		frequentemente.set("Juridico", juridicoF.size());
		frequentemente.set("RH", rhF.size());

		ChartSeries raramente = new ChartSeries();
		raramente.setLabel("Raramente");
		raramente.set("Financeiro", financeiroR.size());
		raramente.set("DP", dpR.size());
		raramente.set("Providencias", providenciasR.size());
		raramente.set("Cobrança", cobrancaR.size());
		raramente.set("Juridico", juridicoR.size());
		raramente.set("RH", rhR.size());

		ChartSeries nunca = new ChartSeries();
		nunca.setLabel("Nunca");
		nunca.set("Financeiro", financeiroN.size());
		nunca.set("DP", dpN.size());
		nunca.set("Providencias", providenciasN.size());
		nunca.set("Cobrança", cobrancaN.size());
		nunca.set("Juridico", juridicoN.size());
		nunca.set("RH", rhN.size());

		ChartSeries nu = new ChartSeries();
		nu.setLabel("NU");
		nu.set("Financeiro", financeiroNU.size());
		nu.set("DP", dpNU.size());
		nu.set("Providencias", providenciasNU.size());
		nu.set("Cobrança", cobrancaNU.size());
		nu.set("Juridico", juridicoNU.size());
		nu.set("RH", rhNU.size());

		this.barra1.addSeries(sempre);
		this.barra1.addSeries(frequentemente);
		this.barra1.addSeries(raramente);
		this.barra1.addSeries(nunca);
		this.barra1.addSeries(nu);
		this.barra1.setLegendPosition("ne");
		this.barra1.setShowDatatip(false);
		this.barra1.setTitle("Principais Serviços");
		this.barra1.setSeriesColors("00923F,  EEE300, E77817, DA251D, 000000");
	}

	// METODOS
	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				return this.sessaoMB.getListaDeGerente();
			} else {
				if (this.sessaoMB.getUsuario().getGrupoGer() != null) {
				}
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}

	public void gerenciaSelect(ItemSelectEvent event) {
		String status = "";
		this.listaRespostasSelecionadas = new ArrayList<>();
		Map<String, Number> series = this.grafico1.getData();
		int i = 0;
		for (Entry<String, Number> entry : series.entrySet()) {
			if (i == event.getItemIndex()) {
				status = entry.getKey();
			}
			i++;
		}
		for (intra_quiz_respostas aux : this.getRespostasGerencia()) {
			if (aux.getResposta1().equals(status)) {
				this.listaRespostasSelecionadas.add(aux);
			}
		}

		this.exibePergunta = "Gerência";
		this.exibeResposta = status;

		RequestContext.getCurrentInstance().execute("PF('dlgCndGrafico').show();");
	}

	public void assembleiasSelect(ItemSelectEvent event) {
		String status = "";
		this.listaRespostasSelecionadas = new ArrayList<>();
		Map<String, Number> series = this.grafico2.getData();
		int i = 0;
		for (Entry<String, Number> entry : series.entrySet()) {
			if (i == event.getItemIndex()) {
				status = entry.getKey();
			}
			i++;
		}
		for (intra_quiz_respostas aux : this.getRespostasAssembleias()) {
			if (aux.getResposta7().equals(status)) {
				this.listaRespostasSelecionadas.add(aux);
			}
		}

		this.exibePergunta = "Assembleias";
		this.exibeResposta = status;

		RequestContext.getCurrentInstance().execute("PF('dlgCndGrafico').show();");
	}

	public void omaSelect(ItemSelectEvent event) {
		String status = "";
		this.listaRespostasSelecionadas = new ArrayList<>();
		Map<String, Number> series = this.grafico3.getData();
		int i = 0;
		for (Entry<String, Number> entry : series.entrySet()) {
			if (i == event.getItemIndex()) {
				status = entry.getKey();
			}
			i++;
		}
		for (intra_quiz_respostas aux : this.getRespostasOMA()) {
			if (aux.getResposta8().equals(status)) {
				this.listaRespostasSelecionadas.add(aux);
			}
		}

		this.exibePergunta = "OMA";
		this.exibeResposta = status;

		RequestContext.getCurrentInstance().execute("PF('dlgCndGrafico').show();");
	}

	public void principaisServicosSelect(ItemSelectEvent event) {
		String departamento = null;
		switch (event.getItemIndex()) {
		case 0:
			departamento = "Financeiro";
			break;
		case 1:
			departamento = "DP";
			break;
		case 2:
			departamento = "Providencias";
			break;
		case 3:
			departamento = "Cobrança";
			break;
		case 4:
			departamento = "Juridico";
			break;
		case 5:
			departamento = "RH";
			break;
		default:
			break;
		}

		String status = "";
		this.listaRespostasSelecionadas = new ArrayList<>();
		List<ChartSeries> series = this.barra1.getSeries();
		int i = 0;
		for (ChartSeries entry : series) {
			if (i == event.getSeriesIndex()) {
				status = entry.getLabel();
			}
			i++;
		}

		for (intra_quiz_respostas aux : this.respostasPrincipaisServicos) {
			if (departamento.equals("Financeiro")) {
				if (aux.getResposta2().equals(status)) {
					this.listaRespostasSelecionadas.add(aux);
				}
			}
			if (departamento.equals("DP")) {
				if (aux.getResposta3().equals(status)) {
					this.listaRespostasSelecionadas.add(aux);
				}
			}
			if (departamento.equals("Providencias")) {
				if (aux.getResposta4().equals(status)) {
					this.listaRespostasSelecionadas.add(aux);
				}
			}
			if (departamento.equals("Cobrança")) {
				if (aux.getResposta5().equals(status)) {
					this.listaRespostasSelecionadas.add(aux);
				}
			}
			if (departamento.equals("Juridico")) {
				if (aux.getResposta6().equals(status)) {
					this.listaRespostasSelecionadas.add(aux);
				}
			}
			if (departamento.equals("RH")) {
				if (aux.getResposta9().equals(status)) {
					this.listaRespostasSelecionadas.add(aux);
				}
			}
		}

		this.exibePergunta = departamento;
		this.exibeResposta = status;

		RequestContext.getCurrentInstance().execute("PF('dlgCndGrafico').show();");
	}

	public String retornaCodNomeCnd(int codCnd) {
		String codNome = String.valueOf(codCnd);
		for (intra_condominios cnd : this.listaCondominios) {
			if (cnd.getCodigo() == codCnd) {
				codNome = codNome + " - " + cnd.getNome();
			}
		}
		return codNome;
	}

	public String retornaNomeGerente(int codCnd) {
		String nomeGerente = String.valueOf(codCnd);
		for (intra_condominios cnd : this.listaCondominios) {
			if (cnd.getCodigo() == codCnd) {
				nomeGerente = cnd.getNomeGerente();
			}
		}
		return nomeGerente;
	}

	public String verificaStatusQuiz() {
		if (this.fase != null) {
			QuizDAO dao = new QuizDAO();
			return dao.verificaStatusQuiz(this.fase.getCodigo());
		}
		return null;
	}

	public void enviarQuiz() {
		try {
			if (this.condominio != null) {
				QuizDAO dao = new QuizDAO();
				intra_quiz_controle_respostas sindico = dao.pesquisaSindico(this.condominio.getCodigo());
				String url;
				sindico.setCondominio(this.condominio.getCodigo());
				sindico.setRespondeu(false);
				url = criptografaUrl(sindico);
				intra_quiz_controle_condominios condominioEnviar = new intra_quiz_controle_condominios();
				condominioEnviar.setCondominio(this.condominio.getCodigo());
				condominioEnviar.setDataEnviado(new Date());
				condominioEnviar.setQuestionarioEnviado(true);
				if (sindico != null) {
					dao.salvaControleRespostas(sindico);
					dao.salvaControleCondominios(condominioEnviar);
				}
				this.enviaEmailQuiz(sindico, url);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Enviado com sucesso para: " + sindico.getEmailSindico() + ", do condomínio "
								+ this.condominio.getCodigo() + " - " + this.condominio.getNome(), ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione um condomínio!", ""));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String criptografaUrl(intra_quiz_controle_respostas sindico) throws Exception {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("condominio=" + sindico.getCondominio() + "&");
		strBuffer.append("nomeSindico=" + sindico.getNomeSindico() + "&");
		strBuffer.append("emailSindico=" + sindico.getEmailSindico());
		String parametrosCriptografados = AESencrp.encrypt(strBuffer.toString());
		String url = "http://omaonline.com.br:42050/QuizSatisfacao/index.xhtml?p=" + parametrosCriptografados;
		return url;
	}

	@SuppressWarnings("deprecation")
	public void enviaEmailQuiz(intra_quiz_controle_respostas sindico, String url) {
		try {
			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(587);
			html.setAuthentication("oma", "Oma@123");
			html.setDebug(false);
			html.setHostName("mail.oma.com.br");
			html.setFrom("quiz@oma.com.br");
			html.setSubject("Quiz Satisfação OMA");
			StringBuilder mensagem = new StringBuilder(
					"<head></head><body> " + "Condomínio:" + sindico.getCondominio() + "<br/>" + "Nome do Síndico:"
							+ sindico.getNomeSindico() + "<br/>" + "Email do Síndico:" + sindico.getEmailSindico()
							+ "<br/>" + "Clique <a href='" + url + "'>" + "aqui</a>  para responder o quiz.<br/>");
			mensagem = mensagem.append(" </body></head></html>");
			String msg = mensagem.toString();
			html.setHtmlMsg(msg);
			html.addTo(sindico.getEmailSindico());
			html.addTo("guilherme.batista@oma.com.br");
			html.setTLS(false);
			html.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public void salvarParametros() {
		try {
			QuizDAO dao = new QuizDAO();
			dao.salvarParametros(this.parametros, this.emails);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", e.getMessage()));
		}
	}

	public void adicionarEmail() {
		try {
			if (this.email.getEmail().trim().isEmpty()) {
				throw new Exception("O e-mail deve ser informado!");
			}
			for (intra_quiz_emails aux : this.emails) {
				if (aux.getEmail().equals(this.email.getEmail())) {
					throw new Exception("Este e-mail já foi adicionado!");
				}
			}
			this.emails.add(this.email);
			this.email = new intra_quiz_emails();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void removerEmail(intra_quiz_emails email) {
		try {
			QuizDAO dao = new QuizDAO();
			dao.removerEmail(email);
			this.emails = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void showRespostas(intra_quiz_respostas respostas) {
		if (respostas != null) {
			this.respostas = respostas;
			RequestContext.getCurrentInstance().execute("PF('dlgRespostas').show();");
		}
	}

	public void pesquisaSugestoes() {
		this.listaSugestoes = new ArrayList<>();
		List<intra_quiz_respostas> l = this.listaRespostas;
		for (intra_quiz_respostas aux : l) {
			if (aux.getRespostaObs() != null && !aux.getRespostaObs().trim().isEmpty()) {
				this.listaSugestoes.add(aux);
			}
		}
		RequestContext.getCurrentInstance().execute("PF('dlgSugestoes').show();");
	}

}
