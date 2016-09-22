package br.com.oma.intranet.managedbeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.oma.intranet.dao.EmissaoDAO;
import br.com.oma.intranet.dao.LISTAR_BL_UNI_DAO;
import br.com.oma.intranet.entidades.intra_cndrteio2;
import br.com.oma.intranet.entidades.intra_cndlteio2;
import br.com.oma.intranet.entidades.intra_cnduteio2;
import br.com.oma.intranet.entidades.intra_cndunidade;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_emissao;
import br.com.oma.intranet.entidades.intra_emissao2;
import br.com.oma.intranet.entidades.intra_emissao_completo;
import br.com.oma.intranet.entidades.intra_emissao_tipo;
import br.com.oma.intranet.entidades.intra_procuracao_juridico;
import br.com.oma.intranet.interfaces.CalculaValorRateioUnidade;
import br.com.oma.intranet.util.CalculaValorUnidade;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.sigadm.entidades.cndleitu;
import br.com.oma.sigadm.entidades.cndrteio;
import br.com.oma.sigadm.entidades.intra_cndconsu;
import br.com.oma.sigadm.entidades.intra_cndlteio;
import br.com.oma.sigadm.entidades.intra_cndprgrt;
import br.com.oma.sigadm.entidades.intra_cndtarif;
import br.com.oma.sigadm.entidades.intra_cndunida;
import br.com.oma.sigadm.entidades.intra_cnduteio;

@ManagedBean(name = "emMB")
@ViewScoped
public class EmissaoMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8074964719964186761L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private EmissaoDAO emDAO;
	private LISTAR_BL_UNI_DAO cndUnidaDAO;
	private intra_condominios icBEAN = new intra_condominios();
	private intra_cndunidade cndUnidaBEAN = new intra_cndunidade();
	private intra_procuracao_juridico ipjBEAN = new intra_procuracao_juridico();
	private intra_emissao emBEAN = new intra_emissao();
	private intra_emissao2 em2BEAN = new intra_emissao2();
	private intra_emissao2 idSelecionado;
	private intra_emissao_completo emcBEAN = new intra_emissao_completo();
	private intra_emissao_completo infoAnterior = new intra_emissao_completo();
	private intra_emissao_completo emcSelecionado;
	private cndrteio rtBEAN = new cndrteio();
	private intra_cndrteio2 rteio = new intra_cndrteio2();
	private cndrteio rtBEANSelecionado;
	private intra_cndlteio ltBEAN = new intra_cndlteio();
	private intra_cndlteio2 lteio = new intra_cndlteio2();
	private intra_cnduteio utBEAN = new intra_cnduteio();
	private intra_cnduteio2 uteio = new intra_cnduteio2();
	private intra_cndunida cudBEAN = new intra_cndunida();
	private cndleitu leBEAN = new cndleitu();
	private intra_cndconsu ccBEAN = new intra_cndconsu();
	private intra_cndprgrt cpBEAN = new intra_cndprgrt();
	private intra_emissao_tipo tiBEAN = new intra_emissao_tipo();

	// ATRIBUTOS
	private List<intra_cndunidade> listaDeBloco, listaDeUnidade;
	private List<intra_emissao> listaRateios, listaRateiosSelecionados;
	private List<intra_emissao> listaRateiosU;
	private List<intra_emissao2> listaEmissao;
	private List<intra_emissao_completo> listaEmissoes, listaDEmissoes, listarEmissoesSelecionadas,
			listaEmissoesCanceladas;
	private List<intra_cndprgrt> listaVencimento, listaNroEmissaoSelecionados;
	private List<intra_cndunida> listaUnida, listaPredio, listaUnidaBloco;
	private List<intra_cndtarif> listaTarifas;
	private List<intra_cndconsu> listaConsumo;
	private List<cndleitu> listaLeitura;
	private List<intra_emissao_tipo> listaTipo, listaTipo2;
	private List<String> selectUnidades, selectTipo;
	private int condominio, emissao, recibo, regular, mes, ano;
	private Date vencto, dataInicial, dataFinal;
	private String nomeCondo, bloco, unidade, observacao, tarifas, pesquisarPor = "Todos";
	private boolean correio, meses, vencimento;

	// CNDRTEIO
	private int nro_rateio;
	private int conta;
	private String historico;
	private double valor_total;
	private int lctos;
	private int cta_anl_financ;
	// CNDLTEIO
	private int nro_lancto;
	private String tipo;
	private double valor;
	private double valor_calculado;
	// CNDUTEIO
	private String comp_hist;
	private double valorU;

	// GET X SET

	public EmissaoMB() {
		this.listaConsumo = new ArrayList<>();
		this.listaRateiosU = new ArrayList<>();
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		int dia = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		int dia1 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		this.mes = (cal.get(Calendar.MONDAY) + 2);
		int ano1 = cal.get(Calendar.YEAR);
		try {
			this.dataInicial = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + this.mes + "/" + ano1);
			this.dataFinal = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia1 + "/" + this.mes + "/" + ano1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public EmissaoDAO getEmDAO() {
		return emDAO;
	}

	public void setEmDAO(EmissaoDAO emDAO) {
		this.emDAO = emDAO;
	}

	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
	}

	public intra_procuracao_juridico getIpjBEAN() {
		return ipjBEAN;
	}

	public void setIpjBEAN(intra_procuracao_juridico ipjBEAN) {
		this.ipjBEAN = ipjBEAN;
	}

	public intra_emissao getEmBEAN() {
		return emBEAN;
	}

	public void setEmBEAN(intra_emissao emBEAN) {
		this.emBEAN = emBEAN;
	}

	public intra_emissao2 getEm2BEAN() {
		return em2BEAN;
	}

	public void setEm2BEAN(intra_emissao2 em2bean) {
		em2BEAN = em2bean;
	}

	public intra_emissao_completo getEmcBEAN() {
		return emcBEAN;
	}

	public void setEmcBEAN(intra_emissao_completo emcBEAN) {
		this.emcBEAN = emcBEAN;
	}

	public intra_emissao_completo getEmcSelecionado() {
		return emcSelecionado;
	}

	public void setEmcSelecionado(intra_emissao_completo emcSelecionado) {
		this.emcSelecionado = emcSelecionado;
	}

	public intra_emissao_completo getInfoAnterior() {
		return infoAnterior;
	}

	public void setInfoAnterior(intra_emissao_completo infoAnterior) {
		this.infoAnterior = infoAnterior;
	}

	public intra_emissao2 getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(intra_emissao2 idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public LISTAR_BL_UNI_DAO getCndUnidaDAO() {
		return cndUnidaDAO;
	}

	public void setCndUnidaDAO(LISTAR_BL_UNI_DAO cndUnidaDAO) {
		this.cndUnidaDAO = cndUnidaDAO;
	}

	public intra_cndunidade getCndUnidaBEAN() {
		return cndUnidaBEAN;
	}

	public void setCndUnidaBEAN(intra_cndunidade cndUnidaBEAN) {
		this.cndUnidaBEAN = cndUnidaBEAN;
	}

	public cndrteio getRtBEAN() {
		return rtBEAN;
	}

	public void setRtBEAN(cndrteio rtBEAN) {
		this.rtBEAN = rtBEAN;
	}

	public intra_cndrteio2 getRteio() {
		return rteio;
	}

	public void setRteio(intra_cndrteio2 rteio) {
		this.rteio = rteio;
	}

	public intra_cndlteio2 getLteio() {
		return lteio;
	}

	public void setLteio(intra_cndlteio2 lteio) {
		this.lteio = lteio;
	}

	public intra_cnduteio2 getUteio() {
		return uteio;
	}

	public void setUteio(intra_cnduteio2 uteio) {
		this.uteio = uteio;
	}

	public cndrteio getRtBEANSelecionado() {
		return rtBEANSelecionado;
	}

	public void setRtBEANSelecionado(cndrteio rtBEANSelecionado) {
		this.rtBEANSelecionado = rtBEANSelecionado;
	}

	public intra_cndlteio getLtBEAN() {
		return ltBEAN;
	}

	public void setLtBEAN(intra_cndlteio ltBEAN) {
		this.ltBEAN = ltBEAN;
	}

	public intra_cnduteio getUtBEAN() {
		return utBEAN;
	}

	public void setUtBEAN(intra_cnduteio utBEAN) {
		this.utBEAN = utBEAN;
	}

	public intra_cndunida getCudBEAN() {
		return cudBEAN;
	}

	public void setCudBEAN(intra_cndunida cudBEAN) {
		this.cudBEAN = cudBEAN;
	}

	public cndleitu getLeBEAN() {
		return leBEAN;
	}

	public void setLeBEAN(cndleitu leBEAN) {
		this.leBEAN = leBEAN;
	}

	public intra_cndconsu getCcBEAN() {
		return ccBEAN;
	}

	public void setCcBEAN(intra_cndconsu ccBEAN) {
		this.ccBEAN = ccBEAN;
	}

	public intra_cndprgrt getCpBEAN() {
		return cpBEAN;
	}

	public void setCpBEAN(intra_cndprgrt cpBEAN) {
		this.cpBEAN = cpBEAN;
	}

	public List<intra_cndunidade> getListaDeBloco() {
		if (this.condominio > 0) {
			this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
			this.listaDeBloco = null;
			this.listaDeBloco = this.cndUnidaDAO.listaDeBloco(this.condominio);
		}
		return listaDeBloco;
	}

	public void setListaDeBloco(List<intra_cndunidade> listaDeBloco) {
		this.listaDeBloco = listaDeBloco;
	}

	public List<intra_cndunidade> getListaDeUnidade() {

		if (this.bloco != null) {
			if (!this.bloco.trim().isEmpty()) {
				this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
				this.listaDeUnidade = null;
				this.listaDeUnidade = this.cndUnidaDAO.listaDeUnidade(this.condominio, this.bloco);

			}
		}
		return listaDeUnidade;
	}

	public void setListaDeUnidade(List<intra_cndunidade> listaDeUnidade) {
		this.listaDeUnidade = listaDeUnidade;
	}

	public List<intra_emissao> getListaRateios()
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
		if (this.sessaoMB.getGerenteSelecionado() != null && this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
			this.emDAO = new EmissaoDAO();
			this.listaRateios = this.emDAO.listarRateios(this.condominio);
		}
		return listaRateios;
	}

	public void setListaRateios(List<intra_emissao> listaRateios) {
		this.listaRateios = listaRateios;
	}

	public List<intra_emissao> getListaRateiosU() {
		return listaRateiosU;
	}

	public void setListaRateiosU(List<intra_emissao> listaRateiosU) {
		this.listaRateiosU = listaRateiosU;
	}

	public List<intra_emissao> getListaRateiosSelecionados() {
		return listaRateiosSelecionados;
	}

	public void setListaRateiosSelecionados(List<intra_emissao> listaRateiosSelecionados) {
		this.listaRateiosSelecionados = listaRateiosSelecionados;
	}

	public List<intra_emissao2> getListaEmissao()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		return listaEmissao;
	}

	public void setListaEmissao(List<intra_emissao2> listaEmissao) {
		this.listaEmissao = listaEmissao;
	}

	public List<intra_emissao_completo> getListarEmissoesSelecionadas() {
		return listarEmissoesSelecionadas;
	}

	public void setListarEmissoesSelecionadas(List<intra_emissao_completo> listarEmissoesSelecionadas) {
		this.listarEmissoesSelecionadas = listarEmissoesSelecionadas;
	}

	public List<intra_emissao_completo> getListaEmissoes()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.emDAO = new EmissaoDAO();
		if (this.sessaoMB.getUsuario().getGrupoGer().get(0).equals(" Todos")) {
			this.listaEmissoes = this.emDAO.listarEmissoes(this.dataInicial, this.dataFinal);
		} else {
			this.listaEmissoes = this.emDAO.listarEmissoes(this.sessaoMB.getGerenteSelecionado().getCodigo(),
					this.dataInicial, this.dataFinal);
		}
		return listaEmissoes;
	}

	public void setListaEmissoes(List<intra_emissao_completo> listaEmissoes) {
		this.listaEmissoes = listaEmissoes;
	}

	public List<intra_emissao_completo> getListaDEmissoes() throws Exception {
		this.pesquisarPorDataE();
		return listaDEmissoes;
	}

	public void setListaDEmissoes(List<intra_emissao_completo> listaDEmissoes) {
		this.listaDEmissoes = listaDEmissoes;
	}

	public List<intra_emissao_completo> getListaEmissoesCanceladas()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.emDAO = new EmissaoDAO();
		this.listaEmissoesCanceladas = this.emDAO.listarEmissoesCanceladas();
		return listaEmissoesCanceladas;
	}

	public void setListaEmissoesCanceladas(List<intra_emissao_completo> listaEmissoesCanceladas) {
		this.listaEmissoesCanceladas = listaEmissoesCanceladas;
	}

	public List<intra_cndprgrt> getListaVencimento()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.sessaoMB.getGerenteSelecionado() != null && this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
			this.emDAO = new EmissaoDAO();
			this.listaVencimento = this.emDAO.listarVencimento(this.condominio);
		}
		return listaVencimento;
	}

	public void setListaVencimento(List<intra_cndprgrt> listaVencimento) {
		this.listaVencimento = listaVencimento;
	}

	public List<intra_cndprgrt> getListaNroEmissaoSelecionados() {
		return listaNroEmissaoSelecionados;
	}

	public void setListaNroEmissaoSelecionados(List<intra_cndprgrt> listaNroEmissaoSelecionados) {
		this.listaNroEmissaoSelecionados = listaNroEmissaoSelecionados;
	}

	public List<intra_cndtarif> getListaTarifas()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.emDAO = new EmissaoDAO();
		this.listaTarifas = this.emDAO.listarTarifas();
		return listaTarifas;
	}

	public void setListaTarifas(List<intra_cndtarif> listaTarifas) {
		this.listaTarifas = listaTarifas;
	}

	public List<intra_cndunida> getListaUnida()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0) {
			this.emDAO = new EmissaoDAO();
			this.listaUnida = this.emDAO.retornarFracao(this.condominio);
		}

		return listaUnida;
	}

	public void setListaUnida(List<intra_cndunida> listaUnida) {
		this.listaUnida = listaUnida;
	}

	public List<intra_cndunida> getListaPredio()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0) {
			this.emDAO = new EmissaoDAO();
			this.listaPredio = this.emDAO.retornaFracaoPredio(this.condominio);
		}
		return listaPredio;
	}

	public void setListaPredio(List<intra_cndunida> listaPredio) {
		this.listaPredio = listaPredio;
	}

	public List<intra_cndunida> getListaUnidaBloco()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0) {
			this.emDAO = new EmissaoDAO();
			this.listaUnidaBloco = this.emDAO.retornarFracaoPorBloco(this.condominio, this.bloco);
		}
		return listaUnidaBloco;
	}

	public void setListaUnidaBloco(List<intra_cndunida> listaUnidaBloco) {
		this.listaUnidaBloco = listaUnidaBloco;
	}

	public List<intra_cndconsu> getListaConsumo() {
		return listaConsumo;
	}

	public void setListaConsumo(List<intra_cndconsu> listaConsumo) {
		this.listaConsumo = listaConsumo;
	}

	public List<cndleitu> getListaLeitura()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.emDAO = new EmissaoDAO();
		this.listaLeitura = this.emDAO.listaLeitura(this.condominio, this.leBEAN.getAno(), this.leBEAN.getMes(),
				this.leBEAN.getTipo());
		return listaLeitura;
	}

	public void setListaLeitura(List<cndleitu> listaLeitura) {
		this.listaLeitura = listaLeitura;
	}

	public List<String> getSelectUnidades() {
		return selectUnidades;
	}

	public void setSelectUnidades(List<String> selectUnidades) {
		this.selectUnidades = selectUnidades;
	}

	public intra_emissao_tipo getTiBEAN() {
		return tiBEAN;
	}

	public void setTiBEAN(intra_emissao_tipo tiBEAN) {
		this.tiBEAN = tiBEAN;
	}

	public List<intra_emissao_tipo> getListaTipo()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.emDAO = new EmissaoDAO();
		this.listaTipo = this.emDAO.listarTipo();
		return listaTipo;
	}

	public void setListaTipo(List<intra_emissao_tipo> listaTipo) {
		this.listaTipo = listaTipo;
	}

	public List<intra_emissao_tipo> getListaTipo2()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0) {
			this.emDAO = new EmissaoDAO();
			this.listaTipo2 = this.emDAO.listarTipo(this.condominio);
		}
		return listaTipo2;
	}

	public void setListaTipo2(List<intra_emissao_tipo> listaTipo2) {
		this.listaTipo2 = listaTipo2;
	}

	public List<String> getSelectTipo() {
		return selectTipo;
	}

	public void setSelectTipo(List<String> selectTipo) {
		this.selectTipo = selectTipo;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public int getEmissao() {
		return emissao;
	}

	public void setEmissao(int emissao) {
		this.emissao = emissao;
	}

	public int getRecibo() {
		return recibo;
	}

	public void setRecibo(int recibo) {
		this.recibo = recibo;
	}

	public int getRegular() {
		return regular;
	}

	public void setRegular(int regular) {
		this.regular = regular;
	}

	public Date getVencto() {
		return vencto;
	}

	public void setVencto(Date vencto) {
		this.vencto = vencto;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getNomeCondo() {
		return nomeCondo;
	}

	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	public String getPesquisarPor() {
		return pesquisarPor;
	}

	public void setPesquisarPor(String pesquisarPor) {
		this.pesquisarPor = pesquisarPor;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTarifas() {
		return tarifas;
	}

	public void setTarifas(String tarifas) {
		this.tarifas = tarifas;
	}

	public int getNro_rateio() {
		return nro_rateio;
	}

	public void setNro_rateio(int nro_rateio) {
		this.nro_rateio = nro_rateio;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public double getValorU() {
		return valorU;
	}

	public void setValorU(double valorU) {
		this.valorU = valorU;
	}

	public int getLctos() {
		return lctos;
	}

	public void setLctos(int lctos) {
		this.lctos = lctos;
	}

	public int getCta_anl_financ() {
		return cta_anl_financ;
	}

	public void setCta_anl_financ(int cta_anl_financ) {
		this.cta_anl_financ = cta_anl_financ;
	}

	public int getNro_lancto() {
		return nro_lancto;
	}

	public void setNro_lancto(int nro_lancto) {
		this.nro_lancto = nro_lancto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValor_calculado() {
		return valor_calculado;
	}

	public void setValor_calculado(double valor_calculado) {
		this.valor_calculado = valor_calculado;
	}

	public String getComp_hist() {
		return comp_hist;
	}

	public void setComp_hist(String comp_hist) {
		this.comp_hist = comp_hist;
	}

	public boolean isCorreio() {
		return correio;
	}

	public void setCorreio(boolean correio) {
		this.correio = correio;
	}

	public boolean isMeses() {
		return meses;
	}

	public void setMeses(boolean meses) {
		this.meses = meses;
	}

	public boolean isVencimento() {
		return vencimento;
	}

	public void setVencimento(boolean vencimento) {
		this.vencimento = vencimento;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// MÉTODOS

	// ↓ MÉTORO PARA LIMPAR ↓
	public void limpar() {
		this.condominio = 0;
		this.emissao = 0;
		this.recibo = 0;
		this.vencto = null;
		this.listaVencimento = null;
		this.listaRateios = null;
	}

	// ↓ MÉTODO PARA LIMPAR VENCIMENTO ↓
	public void limparVencimento() {
		this.listaVencimento = null;
	}

	// ↓ MÉTODO PARA LIMPAR UNIDADE ↓
	public void limparUnidade() {
		this.listaDeBloco = null;
		this.listaDeUnidade = null;
		this.unidade = null;
		this.bloco = null;
	}

	// ↓ MÉTODO PARA RETORNAR NOME DO CONDOMÍNIO ↓
	public void retornaNomeCondominio() {
		for (intra_condominios c : sessaoMB.getListaDeCondominios()) {
			if (c.getCodigo() == this.condominio) {
				this.nomeCondo = c.getNome();
				if (this.icBEAN == null) {
					this.icBEAN = new intra_condominios();
					this.icBEAN.setCodigo(this.condominio);
				}
				this.icBEAN.setNomeGerente(c.getNomeGerente());
				this.icBEAN.setEmailGerente(c.getEmailGerente());
				this.icBEAN.setCodigoGerente(c.getCodigoGerente());
				this.icBEAN.setNome(c.getNome());
				this.infoAnterior = this.emcBEAN;
			}
		}
	}

	// ↓ MÉTODO PARA CANCELAR RECIBO (SIGADM) ↓
	public void cancelarBoleto() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0) {
			this.emDAO = new EmissaoDAO();
			this.ipjBEAN.setCodigoCondominio(this.condominio);
			this.ipjBEAN.setEmissao(this.emissao);
			this.ipjBEAN.setRecibo(this.recibo);
			this.ipjBEAN.setVencto(this.vencto);
			this.emDAO.cancelarBoleto(this.ipjBEAN, this.sessaoMB);
			this.limpar();
			this.reciboCancelado();
		} else {
			this.msgErro();
		}
	}

	public void retornarVencimento() throws ParseException {
		try {
			this.emDAO = new EmissaoDAO();
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(new Date());
			int mes1 = (cal.get(Calendar.MONDAY) + 2);
			int ano1 = cal.get(Calendar.YEAR);
			if (this.vencimento == true) {
				int vencto02 = this.emDAO.listarVencto02(this.condominio);
				this.vencto = (new SimpleDateFormat("dd/MM/yyyy")).parse(vencto02 + "/" + mes1 + "/" + ano1);
			} else {
				int vencto01 = this.emDAO.listarVencto01(this.condominio);
				this.vencto = (new SimpleDateFormat("dd/MM/yyyy")).parse(vencto01 + "/" + mes1 + "/" + ano1);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	// ↓ MÉTODO PARA ADICIONAR RATEIO NO CÁLCULO DE RATEIO (SIGADM) ↓
	public void adicionarRateio() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0) {
			this.emDAO = new EmissaoDAO();

			this.rtBEAN.setCondominio(this.condominio);
			this.rtBEAN.setConta(this.conta);
			this.rtBEAN.setHistorico(this.historico.toUpperCase());
			this.rtBEAN.setExclusivo_prop('N');
			this.rtBEAN.setCta_anl_financ(this.cta_anl_financ());
			this.rtBEAN.setPerc_desc(0.000000);
			this.rtBEAN.setComp_valor(0);
			this.rtBEAN.setProcessado(0);
			this.rtBEAN.setTipo_desc(0);
			this.rtBEAN.setRegular(1);
			this.rtBEAN.setCompl_hist(0);
			this.rtBEAN.setTipo_cob(0);
			this.rtBEAN.setMes_ini(0);
			this.rtBEAN.setAno_ini(0);
			this.rtBEAN.setMes_fin(0);
			this.rtBEAN.setAno_fin(0);
			this.rtBEAN.setCobrar_cota(0);

			this.ltBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
			this.ltBEAN.setNro_lancto(1);
			this.ltBEAN.setBloco(this.bloco);
			char c = this.tipo.charAt(0);
			this.ltBEAN.setTipo(c);
			this.ltBEAN.setValor(this.valor);
			this.ltBEAN.setSeguro('N');
			this.ltBEAN.setFlag_calc('N');
			this.ltBEAN.setUsar_fator_mult('N');
			this.ltBEAN.setDesprezar_agreg(0);
			this.ltBEAN.setCobrar_por_metr(0);

			this.rtBEAN.setLctos(this.ltBEAN.getNro_lancto());
			this.rtBEAN.setValor_total(this.ltBEAN.getValor());
			this.emDAO.adicionarRateio(this.rtBEAN, this.ltBEAN, this.sessaoMB);

			this.getListaUnida();
			double valorTotalUnidade = 0;

			for (intra_cndunida cd : this.listaUnida) {
				if (this.ltBEAN.getTipo() == 'S' && cd.getTipo_unidade() == 'A') {
					this.salvarValorUnidadeIgual(cd);
					valorTotalUnidade += this.ltBEAN.getValor();
				} else if (this.ltBEAN.getTipo() == 'T' && cd.getTipo_unidade() == 'C') {
					this.salvarValorUnidadeIgual(cd);
					valorTotalUnidade += this.ltBEAN.getValor();
				} else if (this.ltBEAN.getTipo() == 'U' && cd.getTipo_unidade() == 'L') {
					this.salvarValorUnidadeIgual(cd);
					valorTotalUnidade += this.ltBEAN.getValor();
				} else {
					this.getListaPredio();
					this.getListaUnidaBloco();
					if (this.ltBEAN.getBloco().equals("0")) {
						for (intra_cndunida cd2 : this.listaPredio) {
							CalculaValorRateioUnidade cvru = new CalculaValorUnidade();
							double valor = cvru.retornaValor(cd2, cd, this.ltBEAN.getTipo(), this.ltBEAN.getValor());
							this.salvarValorUnidade(valor, cd);
							valorTotalUnidade += valor;
						}
					} else {
						for (intra_cndunida cd3 : this.listaUnidaBloco) {
							CalculaValorRateioUnidade cvru = new CalculaValorUnidade();
							double valor = cvru.retornaValor(cd3, cd, this.ltBEAN.getTipo(), this.ltBEAN.getValor());
							this.salvarValorUnidade(valor, cd);
							valorTotalUnidade += valor;
						}
					}
				}
				this.emDAO.salvarValorTotalUnidade(valorTotalUnidade, this.rtBEAN);
			}
			this.salvarCndrteio();
			this.salvarCndlteio(valorTotalUnidade);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		}
	}

	// ↓ MÉTODO PARA ADICIONAR RATEIO (TIPO I) NO CÁLCULO DE RATEIO (SIGADM) ↓
	public void adicionarRateioTipoI() {
		try {
			this.emDAO = new EmissaoDAO();
			this.rtBEAN.setCondominio(this.condominio);
			this.rtBEAN.setConta(this.conta);
			this.rtBEAN.setCta_anl_financ(this.cta_anl_financ());
			this.rtBEAN.setHistorico(this.historico.toUpperCase());
			this.rtBEAN.setPerc_desc(0.000000);
			this.rtBEAN.setExclusivo_prop('N');
			this.rtBEAN.setComp_valor(0);
			this.rtBEAN.setProcessado(0);
			this.rtBEAN.setTipo_desc(0);
			this.rtBEAN.setRegular(0);
			this.rtBEAN.setCompl_hist(0);
			this.rtBEAN.setTipo_cob(1);
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(new Date());
			int mes = (cal.get(Calendar.MONDAY) + 2);
			int ano = cal.get(Calendar.YEAR);
			this.rtBEAN.setMes_ini(mes);
			this.rtBEAN.setAno_ini(ano);
			this.rtBEAN.setMes_fin(0);
			this.rtBEAN.setAno_fin(0);
			this.rtBEAN.setCobrar_cota(0);

			this.ltBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
			this.ltBEAN.setNro_lancto(1);
			this.ltBEAN.setBloco("0");
			this.ltBEAN.setTipo('I');
			this.ltBEAN.setSeguro('N');
			this.ltBEAN.setFlag_calc('N');
			this.ltBEAN.setUsar_fator_mult('N');
			this.ltBEAN.setDesprezar_agreg(0);
			this.ltBEAN.setCobrar_por_metr(0);
			this.ltBEAN.setValor(this.valor);
			this.rtBEAN.setLctos(this.ltBEAN.getNro_lancto());
			this.rtBEAN.setValor_total(this.ltBEAN.getValor());
			this.emDAO.adicionarRateio(this.rtBEAN, this.ltBEAN, this.sessaoMB);

			double valorTotalUnidade = 0;
			this.utBEAN.setCondominio(this.rtBEAN.getCondominio());
			this.utBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
			this.utBEAN.setNro_lancto(this.ltBEAN.getNro_lancto());

			if (this.valida()) {
				for (String unidade : selectUnidades) {
					this.utBEAN.setBloco(this.bloco);
					this.utBEAN.setUnidade(unidade);
					this.utBEAN.setValor(this.valorU);
					this.utBEAN.setComp_hist(this.comp_hist);
					valorTotalUnidade += this.utBEAN.getValor();
					this.emDAO.salvarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
				}
				this.emDAO.salvarValorTotalUnidade(valorTotalUnidade, this.rtBEAN);
			}
			this.salvarCndrteio();
			this.salvarCndlteio(valorTotalUnidade);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA VALIDAR UNIDADES DO RATEIO TIPO I ↓
	public boolean valida() {
		boolean valida = true;
		if (this.selectUnidades == null & this.selectUnidades.isEmpty()) {
			valida = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione uma unidade!"));
			RequestContext.getCurrentInstance().update("frmAddRateio");
		}

		return valida;
	}

	// ↓ MÉTODO PARA ABRIR DIALOG - ALTERAR RATEIO (SIGADM) ↓
	public void carregarDialog(intra_emissao emissao) {
		this.rtBEAN.setCondominio(emissao.getCodigoCondominio());
		this.rtBEAN.setConta(emissao.getConta());
		this.rtBEAN.setCta_anl_financ(emissao.getCta_anl_financ());
		this.rtBEAN.setHistorico(emissao.getHistorico().toUpperCase());
		this.rtBEAN.setNro_rateio(emissao.getNroRateio());
		this.ltBEAN.setNro_lancto(emissao.getNro_lancto());
		this.ltBEAN.setBloco(emissao.getBloco());
		this.ltBEAN.setTipo(emissao.getTipo());
		this.ltBEAN.setValor(emissao.getValor());
	}

	// ↓ MÉTODO PARA ALTERAR RATEIO NO CÁLCULO DE RATEIO (SIGADM) ↓
	public void alterarRateio() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.emDAO = new EmissaoDAO();
		if (this.condominio > 0) {

			this.rtBEAN.setCondominio(this.rtBEAN.getCondominio());
			this.rtBEAN.setConta(this.rtBEAN.getConta());
			this.rtBEAN.setCta_anl_financ(this.rtBEAN.getCta_anl_financ());
			this.rtBEAN.setHistorico(this.rtBEAN.getHistorico().toUpperCase());
			this.ltBEAN.setBloco(this.ltBEAN.getBloco());
			this.ltBEAN.setNro_lancto(this.ltBEAN.getNro_lancto());
			this.ltBEAN.setTipo(this.ltBEAN.getTipo());
			this.ltBEAN.setValor(this.ltBEAN.getValor());
			this.rtBEAN.setValor_total(this.ltBEAN.getValor());
			this.rtBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
			this.emDAO.atualizarRateio(this.rtBEAN, this.ltBEAN, this.sessaoMB);

			this.getListaUnida();
			double valorTotalUnidade = 0;

			for (intra_cndunida cd : this.listaUnida) {
				if (this.ltBEAN.getTipo() == 'S' && cd.getTipo_unidade() == 'A') {
					this.atualizarValorUnidadeIgual(cd);
					valorTotalUnidade += this.ltBEAN.getValor();
				} else if (this.ltBEAN.getTipo() == 'T' && cd.getTipo_unidade() == 'C') {
					this.atualizarValorUnidadeIgual(cd);
					valorTotalUnidade += this.ltBEAN.getValor();
				} else if (this.ltBEAN.getTipo() == 'U' && cd.getTipo_unidade() == 'L') {
					this.atualizarValorUnidadeIgual(cd);
					valorTotalUnidade += this.ltBEAN.getValor();
				} else {
					this.getListaPredio();
					this.getListaUnidaBloco();
					if (this.ltBEAN.getBloco().equals("0")) {
						for (intra_cndunida cd2 : this.listaPredio) {
							CalculaValorRateioUnidade cvru = new CalculaValorUnidade();
							double valor = cvru.retornaValor(cd2, cd, this.ltBEAN.getTipo(), this.ltBEAN.getValor());
							this.atualizarValorUnidade(valor, cd);
							valorTotalUnidade += valor;
						}
					} else {
						for (intra_cndunida cd3 : this.listaUnidaBloco) {
							CalculaValorRateioUnidade cvru = new CalculaValorUnidade();
							double valor = cvru.retornaValor(cd3, cd, this.ltBEAN.getTipo(), this.ltBEAN.getValor());
							this.atualizarValorUnidade(valor, cd);
							valorTotalUnidade += valor;
						}
					}
				}
				this.emDAO.salvarValorTotalUnidade(valorTotalUnidade, this.rtBEAN);
			}
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		}
	}

	// ↓ MÉTODO PARA SALVAR RATEIO TIPO I ↓
	public void atualizarRateioTipoI() {
		try {
			this.emDAO = new EmissaoDAO();
			this.rtBEAN.setCondominio(this.rtBEAN.getCondominio());
			this.rtBEAN.setConta(this.rtBEAN.getConta());
			this.rtBEAN.setCta_anl_financ(3);
			this.rtBEAN.setHistorico(this.rtBEAN.getHistorico().toUpperCase());
			this.ltBEAN.setNro_lancto(this.ltBEAN.getNro_lancto());
			this.ltBEAN.setTipo(this.ltBEAN.getTipo());
			this.ltBEAN.setValor(this.ltBEAN.getValor());
			this.rtBEAN.setLctos(this.ltBEAN.getNro_lancto());
			this.emDAO.atualizarRateio(this.rtBEAN, this.ltBEAN, this.sessaoMB);

			double valorTotalUnidade = 0;
			if (this.valida()) {
				for (String unidade : selectUnidades) {
					this.utBEAN.setBloco(this.utBEAN.getBloco());
					this.utBEAN.setUnidade(unidade);
					this.utBEAN.setValor(this.utBEAN.getValor());
					valorTotalUnidade += this.utBEAN.getValor();
					this.emDAO.atualizarUnidadeTipoI(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
				}
				this.emDAO.salvarValorTotalUnidade(valorTotalUnidade, this.rtBEAN);
			}
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA SALVAR UNIDADE DO CÁLCULO DE RATEIO (SIGADM) ↓
	public void salvarValorUnidade(double valorUnidade, intra_cndunida unida) {
		this.utBEAN.setCondominio(this.rtBEAN.getCondominio());
		this.utBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
		this.utBEAN.setNro_lancto(this.ltBEAN.getNro_lancto());
		if (this.ltBEAN.getBloco().equals("0")) {
			this.utBEAN.setBloco(unida.getBloco());
			this.utBEAN.setUnidade(unida.getUnidade());
			this.utBEAN.setValor(valorUnidade);
			this.emDAO.salvarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
			this.salvarCnduteio();
		} else {
			if (this.ltBEAN.getBloco().equals(unida.getBloco().trim())) {
				this.utBEAN.setBloco(this.bloco);
				this.utBEAN.setUnidade(unida.getUnidade());
				this.utBEAN.setValor(valorUnidade);
				this.emDAO.salvarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB, this.bloco);
				this.salvarCnduteio();
			} else {
				this.utBEAN.setBloco(unida.getBloco());
				this.utBEAN.setUnidade(unida.getUnidade());
				this.utBEAN.setValor(0);
				this.emDAO.salvarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
				this.salvarCnduteio();
			}
		}
	}

	// ↓ MÉTODO PARA ATUALIZAR UNIDADE DO CÁLCULO DE RATEIO (SIGADM) ↓
	public void atualizarValorUnidade(double valorUnidade, intra_cndunida unida) {
		this.utBEAN.setCondominio(this.rtBEAN.getCondominio());
		this.utBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
		this.utBEAN.setNro_lancto(this.ltBEAN.getNro_lancto());
		if (this.ltBEAN.getBloco().equals("0")) {
			this.utBEAN.setBloco(unida.getBloco());
			this.utBEAN.setUnidade(unida.getUnidade());
			this.utBEAN.setValor(valorUnidade);
			this.emDAO.atualizarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
		} else {
			if (this.ltBEAN.getBloco().equals(unida.getBloco().trim())) {
				this.utBEAN.setBloco(this.ltBEAN.getBloco());
				this.utBEAN.setUnidade(unida.getUnidade());
				this.utBEAN.setValor(valorUnidade);
				this.emDAO.atualizarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
			} else {
				this.utBEAN.setBloco(unida.getBloco());
				this.utBEAN.setUnidade(unida.getUnidade());
				this.utBEAN.setValor(0);
				this.emDAO.atualizarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
			}
		}
	}

	// ↓ MÉTODO PARA SALVAR UNIDADE (COM MESMO VALOR) DO CÁLCULO DE RATEIO
	// (SIGADM) ↓
	public void salvarValorUnidadeIgual(intra_cndunida unida) {
		this.utBEAN.setBloco(unida.getBloco());
		this.utBEAN.setUnidade(unida.getUnidade());
		this.utBEAN.setCondominio(this.rtBEAN.getCondominio());
		this.utBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
		this.utBEAN.setNro_lancto(this.ltBEAN.getNro_lancto());
		this.utBEAN.setValor(this.ltBEAN.getValor());
		this.emDAO.salvarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
		this.salvarCnduteio();
	}

	// ↓ MÉTODO PARA ATUALIZAR UNIDADE (COM MESMO VALOR) DO CÁLCULO DE RATEIO
	// (SIGADM) ↓
	public void atualizarValorUnidadeIgual(intra_cndunida unida) {
		this.utBEAN.setBloco(unida.getBloco());
		this.utBEAN.setUnidade(unida.getUnidade());
		this.utBEAN.setCondominio(this.rtBEAN.getCondominio());
		this.utBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
		this.utBEAN.setNro_lancto(this.ltBEAN.getNro_lancto());
		this.utBEAN.setValor(this.ltBEAN.getValor());
		this.emDAO.atualizarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
	}

	// ↓ MÉTODO PARA SALVAR EMISSÃO (OMACORP) ↓
	public void salvarEmissao() {
		try {
			this.emcBEAN.setCodigo_gerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
			this.emcBEAN.setCodigo_condominio(this.condominio);
			this.emcBEAN.setNome_condominio(this.icBEAN.getNome().toUpperCase());
			this.emcBEAN.setObservacao(this.observacao.toUpperCase());
			this.emcBEAN.setCorreio(this.correio);
			this.emcBEAN.setTipo(this.regular);
			this.emcBEAN.setVencimento(this.cpBEAN.getVencimento());
			this.emcBEAN.setLiberacao_gerente(new Date());
			if (this.regular == 1) {
				int emissao = this.emDAO.listarNroEmissao(this.condominio, this.cpBEAN.getVencimento());
				this.emcBEAN.setNroEmissao(emissao);
			} else {
				this.emcBEAN.setNroEmissao(0);
			}
			this.emcBEAN.setProximoMes("Manual");
			this.emDAO.salvarEmissao2(this.emcBEAN, this.sessaoMB);

			for (intra_emissao em : this.listaRateiosSelecionados) {
				this.emDAO = new EmissaoDAO();
				intra_emissao2 emBEAN = new intra_emissao2();
				emBEAN.setConta(em.getConta());
				emBEAN.setHistorico(em.getHistorico().toUpperCase());
				emBEAN.setValor(em.getValorTotal());
				emBEAN.setTipo(em.getTipo());
				emBEAN.setNroRateio(em.getNroRateio());
				emBEAN.setCodigo(this.condominio);
				emBEAN.setRateio(emcBEAN);
				this.emDAO.salvarEmissao(emBEAN, this.sessaoMB);
			}
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA VISUALIZAR DETALHES DA EMISSÃO ↓
	public void listarEmissaoPorCondominio(int rateioId) {
		try {
			this.emDAO = new EmissaoDAO();
			this.listaEmissao = new ArrayList<>();
			this.listaEmissao = this.emDAO.listarRateiosPorCondominio(rateioId);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA SALVAR CNDRTEIO (OMACORP) ↓
	public void salvarCndrteio() {
		try {
			this.emDAO = new EmissaoDAO();
			this.rteio.setAno_fin(this.rtBEAN.getAno_fin());
			this.rteio.setAno_ini(this.rtBEAN.getAno_ini());
			this.rteio.setCobrar_cota(this.rtBEAN.getCobrar_cota());
			this.rteio.setComp_valor(this.rtBEAN.getComp_valor());
			this.rteio.setCompl_hist(this.rtBEAN.getCompl_hist());
			this.rteio.setCondominio(this.rtBEAN.getCondominio());
			this.rteio.setConta(this.rtBEAN.getConta());
			this.rteio.setCta_anl_financ(this.rtBEAN.getCta_anl_financ());
			this.rteio.setData_alteracao(this.rtBEAN.getData_alteracao());
			this.rteio.setExclusivo_prop(this.rtBEAN.getExclusivo_prop());
			this.rteio.setHistorico(this.rtBEAN.getHistorico());
			this.rteio.setLctos(this.rtBEAN.getLctos());
			this.rteio.setMes_fin(this.rtBEAN.getMes_fin());
			this.rteio.setMes_ini(this.rtBEAN.getMes_ini());
			this.rteio.setNome_coluna(this.rtBEAN.getNome_coluna());
			this.rteio.setNro_leitura(this.rtBEAN.getNro_leitura());
			this.rteio.setNro_rateio(this.rtBEAN.getNro_rateio());
			this.rteio.setNro_rateio_aux(this.rtBEAN.getNro_rateio_aux());
			this.rteio.setPerc_desc(this.rtBEAN.getPerc_desc());
			this.rteio.setProcessado(this.rtBEAN.getProcessado());
			this.rteio.setRegular(this.rtBEAN.getRegular());
			this.rteio.setTaxa_adm(this.rtBEAN.getTaxa_adm());
			this.rteio.setTipo_cob(this.rtBEAN.getTipo_cob());
			this.rteio.setTipo_desc(this.rtBEAN.getTipo_desc());
			this.rteio.setValor_total(this.rtBEAN.getValor_total());
			this.emDAO.salvarCndrteio(this.rteio);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA SALVAR CNDLTEIO (OMACORP) ↓
	public void salvarCndlteio(double valorTotalUnidade) {
		try {
			this.emDAO = new EmissaoDAO();
			this.lteio.setBloco(this.ltBEAN.getBloco());
			this.lteio.setCobrar_por_metr(this.ltBEAN.getCobrar_por_metr());
			this.lteio.setCodigo_tarifa(this.ltBEAN.getCodigo_tarifa());
			this.lteio.setData_alteracao(this.ltBEAN.getData_alteracao());
			this.lteio.setDesprezar_agreg(this.ltBEAN.getDesprezar_agreg());
			this.lteio.setDfrecnum(this.ltBEAN.getDfrecnum());
			this.lteio.setFlag_calc(this.ltBEAN.getFlag_calc());
			this.lteio.setHistorico(this.ltBEAN.getHistorico());
			this.lteio.setNro_lancto(this.ltBEAN.getNro_lancto());
			this.lteio.setNro_rateio(this.rtBEAN.getNro_rateio());
			this.lteio.setPerc_desc(this.ltBEAN.getPerc_desc());
			this.lteio.setSeguro(this.ltBEAN.getSeguro());
			this.lteio.setTipo(this.ltBEAN.getTipo());
			this.lteio.setUsar_fator_mult(this.ltBEAN.getUsar_fator_mult());
			this.lteio.setValor(valorTotalUnidade);
			this.lteio.setValor_calculado(valorTotalUnidade);
			this.emDAO.salvarCndlteio(this.lteio);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA SALVAR CNDUTEIO (OMACORP) ↓
	public void salvarCnduteio() {
		try {
			this.emDAO = new EmissaoDAO();
			this.uteio.setBloco(this.utBEAN.getBloco());
			this.uteio.setComp_hist(this.utBEAN.getComp_hist());
			this.uteio.setCondominio(this.utBEAN.getCondominio());
			this.uteio.setDfrecnum(this.utBEAN.getDfrecnum());
			this.uteio.setNro_lancto(this.utBEAN.getNro_lancto());
			this.uteio.setNro_rateio(this.utBEAN.getNro_rateio());
			this.uteio.setUnidade(this.utBEAN.getUnidade());
			this.uteio.setValor(this.utBEAN.getValor());
			this.emDAO.atualizarUnidade(this.uteio);
			this.uteio.setId(0);
			this.emDAO.salvarCnduteio(this.uteio);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA ATUALIZAR EMISSÃO (OMACORP) ↓
	public void gerarProximoMes() {
		try {
			for (intra_emissao_completo ie : this.listarEmissoesSelecionadas) {
				this.emDAO = new EmissaoDAO();
				this.emDAO.atualizarEmissao(ie);
				List<intra_emissao2> listarRateiosPorCondominio = this.emDAO.listarRateiosPorCondominio(ie.getId());
				ie.setId(0);
				ie.setCodigo_gerente(ie.getCodigo_gerente());
				ie.setCodigo_condominio(ie.getCodigo_condominio());
				ie.setNome_condominio(ie.getNome_condominio().toUpperCase());
				ie.setObservacao(ie.getObservacao().toUpperCase());
				ie.setCorreio(ie.isCorreio());
				ie.setTipo(ie.getTipo());
				ie.setLiberacao_gerente(new Date());
				Calendar c = Calendar.getInstance();
				c.setTime(ie.getVencimento());
				c.add(Calendar.MONDAY, +1);
				ie.setVencimento(c.getTime());
				if (ie.getTipo() == 1) {
					int emissao = this.emDAO.listarNroEmissao(ie.getCodigo_condominio(), ie.getVencimento());
					ie.setNroEmissao(emissao);
				} else {
					ie.setNroEmissao(0);
				}
				ie.setProximoMes("Automático");
				this.emDAO.salvarEmissao2(ie, this.sessaoMB);

				for (intra_emissao2 ie2 : listarRateiosPorCondominio) {
					ie2.setRateio(ie);
					ie2.setCodigo(ie2.getCodigo());
					ie2.setConta(ie2.getConta());
					ie2.setHistorico(ie2.getHistorico().toUpperCase());
					ie2.setNroRateio(ie2.getNroRateio());
					ie2.setTipo(ie2.getTipo());
					ie2.setValor(ie2.getValor());
					this.emDAO.atualizarRateio(ie2);
					ie2.setId(0);
					this.emDAO.salvarEmissao(ie2, this.sessaoMB);
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA ABRIR O ALTERAR EMISSÃO ↓
	public void abreAlteraEmissao() {
		try {
			if (this.emcSelecionado != null) {
				String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						caminho + "/emissao/emissao/alterar-emissao.xhtml?idEmissao=" + this.emcSelecionado.getId());
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA PESQUISAR ID PARA ALTERAR EMISSÃO ↓
	public void pesquisaIdEmissao() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String id = params.get("idEmissao");
			if (id != null) {
				this.emDAO = new EmissaoDAO();
				this.emcBEAN = this.emDAO.pesquisaEmissaoPorCodigo(Integer.parseInt(id));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA ALTERAR EMISSÃO ↓
	public void alterarEmissao() {
		try {
			this.emDAO = new EmissaoDAO();
			if (this.emcBEAN.getId() != 0) {
				this.emcBEAN.setNome_condominio(this.emcBEAN.getNome_condominio());
				this.emcBEAN.setTipo_impressao(this.emcBEAN.getTipo_impressao());
				this.emcBEAN.setLiberacao_gerente(this.emcBEAN.getLiberacao_gerente());
				this.emcBEAN.setData_impressao(this.emcBEAN.getData_impressao());
				this.emcBEAN.setEnvio_predio(this.emcBEAN.getEnvio_predio());
				this.emcBEAN.setLiberacao_expedicao(this.emcBEAN.getLiberacao_expedicao());
				this.emcBEAN.setNroEmissao(this.emcBEAN.getNroEmissao());
			}
			this.emDAO.salvarEmissao2(this.emcBEAN, this.sessaoMB);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "tabela-emissoes.xhtml?faces-redirect=true");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA ATUALIZAR A DATA E TIPO DE IMPRESSÃO (DEPTO. EMISSÃO) ↓
	public void atualizarDataImpressao() {
		try {
			for (intra_emissao_completo ie : this.listarEmissoesSelecionadas) {
				this.emDAO = new EmissaoDAO();
				if (this.sessaoMB.getUsuario().getGrupoDepto().get(0).getNome().equals("Emissao")
						|| this.sessaoMB.getUsuario().getGrupoDepto().get(0).getNome().equals(" Todos")) {
					if (ie.getId() != 0) {
						if (ie.getNroEmissao() == 0) {
							int emissao = this.emDAO.listarNroEmissao(ie.getCodigo_condominio(), ie.getVencimento());
							ie.setNroEmissao(emissao);
						}
						ie.setData_impressao(new Date());
						ie.setTipo_impressao("Global");
					}
					this.emDAO.salvarEmissao2(ie, this.sessaoMB);
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA ATUALIZAR A DATA DE LIBERAÇÃO PARA A EXPEDIÇÃO (DEPTO.
	// CONDOMÍNIO) ↓
	public void atualizarLiberacaoExpedicao() {
		try {
			for (intra_emissao_completo ie : this.listarEmissoesSelecionadas) {
				this.emDAO = new EmissaoDAO();
				if (this.sessaoMB.getUsuario().getGrupoDepto().get(0).getNome().equals("Condominio")
						|| this.sessaoMB.getUsuario().getGrupoDepto().get(0).getNome().equals(" Todos")) {
					if (ie.getId() != 0) {
						ie.setLiberacao_expedicao(new Date());
					}
					this.emDAO.salvarEmissao2(ie, this.sessaoMB);
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA ATUALIZAR A DATA DE LIBERAÇÃO PARA O PRÉDIO (DEPTO.
	// EXPEDIÇÃO) ↓
	public void atualizarLiberacaoPredio() {
		try {
			for (intra_emissao_completo ie : this.listarEmissoesSelecionadas) {
				this.emDAO = new EmissaoDAO();
				if (this.sessaoMB.getUsuario().getGrupoDepto().get(0).getNome().equals("Expedicao")
						|| this.sessaoMB.getUsuario().getGrupoDepto().get(0).getNome().equals(" Todos")) {
					if (ie.getId() != 0) {
						ie.setEnvio_predio(new Date());
					}
					this.emDAO.salvarEmissao2(ie, this.sessaoMB);
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA PESQUISAR EMISSÃO (TELA GERENTE) ↓
	public void pesquisarPorData() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.emDAO = new EmissaoDAO();
		try {
			if (this.dataInicial == null || this.dataFinal == null) {
				throw new Exception("Insira datas de início e fim para pesquisar!");
			} else {
				this.dataFinal = new DateTime(this.dataFinal).withHourOfDay(23).withMinuteOfHour(59).toDate();
			}
			this.listaEmissoes = this.emDAO.listarEmissoes(this.sessaoMB.getGerenteSelecionado().getCodigo(),
					this.dataInicial, this.dataFinal);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA PESQUISAR EMISSÃO (TELA EMISSÃO) ↓
	public void pesquisarPorDataE() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.emDAO = new EmissaoDAO();
		try {
			if (this.dataInicial == null || this.dataFinal == null) {
				throw new Exception("Insira datas de início e fim para pesquisar!");
			} else {
				this.dataFinal = new DateTime(this.dataFinal).withHourOfDay(23).withMinuteOfHour(59).toDate();
			}
			switch (this.pesquisarPor) {
			case "Todos":
				this.listaDEmissoes = this.emDAO.listarEmissoes(this.dataInicial, this.dataFinal);
				break;
			case "Pendentes":
				this.listaDEmissoes = this.emDAO.listarEmissoesP(this.dataInicial, this.dataFinal);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA PARAMETRIZAR OS TIPOS DE RATEIO ↓
	public void salvarTipoRateio() {
		try {
			if (this.valida2()) {
				for (String tipo : selectTipo) {
					this.emDAO = new EmissaoDAO();
					this.tiBEAN.setCodigo(this.condominio);
					this.tiBEAN.setTipo(tipo.charAt(0));
					this.emDAO.atualizarTipo(this.tiBEAN);
					this.tiBEAN.setId(0);
					this.emDAO.salvarTipo(this.tiBEAN, this.sessaoMB);
				}
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Adicionado"));
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}

	}

	// ↓ MÉTODO PARA VALIDAR OS PARÂMETROS DO TIPO DE RATEIO ↓
	public boolean valida2() {
		boolean valida2 = true;
		if (this.selectTipo == null & this.selectTipo.isEmpty()) {
			valida2 = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione um tipo!"));
			RequestContext.getCurrentInstance().update("frmTipoRateio");
		}

		return valida2;
	}

	// ↓ MÉTODO PARA EXCLUIR EMISSÃO ↓
	public void excluir() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.emDAO = new EmissaoDAO();
		if (this.emcBEAN.getTipo_impressao() != null) {
			if (this.bloquearCancelamento() == false) {
				this.emcBEAN.setMotivo(this.emcSelecionado.getMotivo());
				this.emDAO.excluirRateio(this.emcSelecionado, this.sessaoMB);
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Não foi possível cancelar essa Emissão. Por gentileza, solicitar o cancelamento junto ao Depto. de Emissão"));
		}
	}

	// ↓ MÉTODO PARA BLOQUEAR O CANCELAMENTO DA EMISSÃO ↓
	public boolean bloquearCancelamento() {
		boolean bloqueio = false;
		DateTime dataTime = new DateTime(new Date());
		DateTime dateTime = new DateTime(this.emcSelecionado.getVencimento());
		Days d = Days.daysBetween(dataTime, dateTime);
		if (d.getDays() < 25) {
			bloqueio = true;
		}
		return bloqueio;
	}

	// ↓ MÉTODO PARA SALVAR CONTA DE ANÁLISE FINANCEIRA ↓
	public int cta_anl_financ() {
		int cta = 0;
		switch (this.conta) {
		case 35:
			cta = 1088;
			break;
		case 51:
			cta = 3;
			break;
		case 78:
			cta = 29;
			break;
		case 124:
			cta = 34;
			break;
		case 159:
			cta = 37;
			break;
		case 388:
			cta = 60;
			break;
		case 1406:
			cta = 1093;
			break;
		case 1473:
			cta = 1149;
			break;
		case 1546:
			cta = 1177;
		case 1627:
			cta = 1194;
			break;
		case 1694:
			cta = 1223;
			break;
		case 1830:
			cta = 1445;
			break;
		case 1864:
			cta = 1446;
			break;
		case 1902:
			cta = 1477;
			break;
		case 1937:
			cta = 1478;
			break;
		case 1970:
			cta = 1487;
			break;
		case 2046:
			cta = 1495;
			break;
		case 2119:
			cta = 1506;
			break;
		case 2259:
			cta = 1524;
			break;
		case 2381:
			cta = 1543;
			break;
		case 2526:
			cta = 1568;
			break;
		case 2593:
			cta = 1592;
			break;
		case 2917:
			cta = 1604;
			break;
		case 3107:
			cta = 1617;
			break;
		case 3204:
			cta = 1624;
			break;
		case 3271:
			cta = 1636;
			break;
		case 3361:
			cta = 1647;
			break;
		case 3603:
			cta = 1669;
			break;
		case 3671:
			cta = 1675;
			break;
		case 3905:
			cta = 1696;
			break;
		case 5266:
			cta = 1704;
			break;
		case 5410:
			cta = 1715;
			break;
		case 5487:
			cta = 1725;
			break;
		case 5614:
			cta = 1732;
			break;
		case 8761:
			cta = 1872;
			break;
		case 9202:
			cta = 5;
			break;
		case 11746:
			cta = 2043;
			break;
		case 11860:
			cta = 2054;
			break;
		case 11967:
			cta = 2065;
			break;
		case 12726:
			cta = 2085;
			break;
		case 13005:
			cta = 2108;
			break;
		case 13951:
			cta = 2114;
			break;
		case 15571:
			cta = 2139;
			break;
		case 16012:
			cta = 544;
			break;
		case 16101:
			cta = 1458;
			break;
		case 17881:
			cta = 2198;
			break;
		case 18241:
			cta = 2217;
			break;
		case 19391:
			cta = 46;
			break;
		case 19431:
			cta = 2233;
			break;
		case 19521:
			cta = 2241;
			break;
		case 19941:
			cta = 2278;
			break;
		case 20101:
			cta = 2296;
			break;
		case 21031:
			cta = 2364;
			break;
		case 21101:
			cta = 1055;
			break;
		case 21161:
			cta = 2372;
			break;
		case 21221:
			cta = 2188;
			break;
		case 22221:
			cta = 2403;
			break;
		case 22361:
			cta = 2411;
			break;
		case 22461:
			cta = 2419;
			break;
		case 22511:
			cta = 2425;
			break;
		case 23631:
			cta = 2478;
		case 23831:
			cta = 2488;
			break;
		case 25762:
			cta = 2970;
			break;
		case 25921:
			cta = 3117;
			break;
		case 25975:
			cta = 3169;
			break;
		case 25990:
			cta = 3183;
			break;
		case 26050:
			cta = 3243;
			break;
		case 26051:
			cta = 3244;
			break;
		case 26081:
			cta = 3273;
			break;
		case 26465:
			cta = 3656;
			break;
		case 26566:
			cta = 3757;
			break;
		case 26911:
			cta = 4051;
			break;
		case 26913:
			cta = 4053;
			break;
		case 26959:
			cta = 4096;
			break;
		case 27075:
			cta = 4208;
			break;
		case 27076:
			cta = 4209;
			break;
		case 27077:
			cta = 4210;
			break;
		case 27113:
			cta = 4245;
			break;
		case 27172:
			cta = 4304;
			break;
		case 27593:
			cta = 4724;
			break;
		case 27620:
			cta = 4751;
			break;
		case 28169:
			cta = 5294;
			break;
		case 28171:
			cta = 5296;
			break;
		default:
			cta = 3;
			break;
		}
		return cta;
	}

	@SuppressWarnings("unused")
	public int quantidadeEmissoes() {
		int total = 0;
		if (this.listaDEmissoes != null) {
			for (intra_emissao_completo e : this.listaDEmissoes) {
				total += 1;
			}
		}
		if (this.listaEmissoes != null) {
			for (intra_emissao_completo e : this.listaEmissoes) {
				total += 1;
			}
		}
		return total;
	}

	/********************* ↓ CONSUMO ↓ **************************/

	// ↓ MÉTODO PARA IMPORTAR ARQUIVOS XLSX ↓
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<intra_cndconsu> importarExcelXLSX(String caminho_arquivo) throws IOException {
		List<intra_cndconsu> listaConsumo = new ArrayList();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(caminho_arquivo));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet nome = workbook.getSheetAt(0);
			Iterator rows_nome = nome.rowIterator();
			intra_cndconsu cndconsu = null;
			while (rows_nome.hasNext()) {
				cndconsu = new intra_cndconsu();
				XSSFRow row = (XSSFRow) rows_nome.next();
				if (row.getRowNum() > 0) {
					if (row.getCell(0) != null && row.getCell(0).toString() != null) {
						row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						cndconsu.setBloco(row.getCell(0).toString());
					}
					if (row.getCell(1) != null && row.getCell(1).toString() != null) {
						row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
						cndconsu.setUnidade(row.getCell(1).toString());
					}
					if (row.getCell(2) != null && row.getCell(2).toString() != null) {
						row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						cndconsu.setAnterior(Double.valueOf(row.getCell(2).toString()));
					}
					if (row.getCell(3) != null && row.getCell(3).toString() != null) {
						row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
						cndconsu.setAtual(Double.valueOf(row.getCell(3).toString()));
					}
					if (row.getCell(4) != null && row.getCell(4).toString() != null) {
						row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						cndconsu.setMedia(Double.valueOf(row.getCell(4).toString()));
					}
					if (row.getCell(5) != null && row.getCell(5).toString() != null) {
						row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
						cndconsu.setValor(Double.valueOf(row.getCell(5).toString()));
					}

					listaConsumo.add(cndconsu);
				}
			}
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return listaConsumo;
	}

	// ↓ MÉTODO PARA IMPORTAR ARQUIVOS XLS ↓
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<intra_cndconsu> importarExcelXLS(String caminho_arquivo) throws InvalidFormatException {
		List<intra_cndconsu> listaConsumo = new ArrayList();
		try {
			org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create((new FileInputStream(caminho_arquivo)));
			org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rows_nome = sheet.iterator();
			intra_cndconsu cndconsu = null;
			while (rows_nome.hasNext()) {
				cndconsu = new intra_cndconsu();
				Row row = rows_nome.next();
				if (row.getCell(0) != null && row.getCell(0).toString() != null) {
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					cndconsu.setBloco(row.getCell(0).toString());
				}
				if (row.getCell(1) != null && row.getCell(1).toString() != null) {
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					cndconsu.setUnidade(row.getCell(1).toString());
				}
				if (row.getCell(2) != null && row.getCell(2).toString() != null) {
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					cndconsu.setAnterior(Double.valueOf(row.getCell(2).toString()));
				}
				if (row.getCell(3) != null && row.getCell(3).toString() != null) {
					row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					cndconsu.setAtual(Double.valueOf(row.getCell(3).toString()));
				}
				if (row.getCell(4) != null && row.getCell(4).toString() != null) {
					row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
					cndconsu.setMedia(Double.valueOf(row.getCell(4).toString()));
				}
				if (row.getCell(5) != null && row.getCell(5).toString() != null) {
					row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
					cndconsu.setValor(Double.valueOf(row.getCell(5).toString()));
				}
				listaConsumo.add(cndconsu);
			}
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
		return listaConsumo;
	}

	// ↓ MÉTODO PARA UPLOAD DOS ARQUIVOS (XLSX OU XLS) ↓
	public void handleFileUpload(FileUploadEvent event) throws InvalidFormatException {
		byte[] arquivo = event.getFile().getContents();
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		String caminho_arquivo = servletContext.getRealPath("") + File.separator + "resources" + File.separator
				+ "arquivos" + File.separator + event.getFile().getFileName();
		try {
			FileOutputStream fos = new FileOutputStream(new File(caminho_arquivo));
			fos.write(arquivo);
			fos.flush();
			fos.close();
			if (caminho_arquivo.contains("xlsx")) {
				this.listaConsumo.addAll(importarExcelXLSX(caminho_arquivo));
			} else {
				this.listaConsumo.addAll(importarExcelXLS(caminho_arquivo));
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Importado com sucesso!", ""));
			File file = new File(caminho_arquivo);
			if (file.exists()) {
				file.delete();
			}
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA SALVAR CONSUMO (SIGADM) ↓
	public void salvarConsumo() {
		if (this.condominio > 0) {
			try {
				this.emDAO = new EmissaoDAO();
				int idLeitura = this.emDAO.retornaCodigoConsumo();

				this.leBEAN.setCondominio(this.condominio);
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(new Date());
				int mes = (cal.get(Calendar.MONDAY) + 1);
				int ano = cal.get(Calendar.YEAR);
				this.leBEAN.setMes(mes);
				this.leBEAN.setAno(ano);
				this.leBEAN.setData(new Date());
				this.leBEAN.setTipo(this.leBEAN.getTipo());
				this.leBEAN.setImportacao(1);
				this.leBEAN.setBloco("0".trim());
				this.leBEAN.setNumero(idLeitura);

				this.getListaLeitura();
				for (cndleitu cl : this.listaLeitura) {
					cl.setCondominio(cl.getCondominio());
					cl.setBloco(cl.getBloco());
					cl.setTipo(cl.getTipo());
					cl.setAno(cl.getAno());
					cl.setMes(cl.getMes());

					if (this.leBEAN.getCondominio() == cl.getCondominio() && this.leBEAN.getTipo() == cl.getTipo()
							&& this.leBEAN.getAno() == cl.getAno() && this.leBEAN.getMes() == cl.getMes()) {
						this.emDAO.deletarCndConsu(cl);
						this.emDAO.deletarCndLeitu(cl, this.sessaoMB);
						this.emDAO.adicionarLeitura(this.leBEAN, this.sessaoMB);
					}
				}
				this.emDAO.deletarCndConsu(this.leBEAN);
				this.emDAO.deletarCndLeitu(this.leBEAN, this.sessaoMB);
				this.emDAO.adicionarLeitura(this.leBEAN, this.sessaoMB);

				double valorTotal = 0;
				double totalConsumo = 0;
				for (intra_cndconsu ic : this.listaConsumo) {
					ic.setNumero(idLeitura);
					ic.setCondominio(this.leBEAN.getCondominio());
					ic.setBloco(ic.getBloco());

					if (ic.getUnidade().length() == 6) {
						ic.setUnidade(ic.getUnidade());
					} else if (ic.getUnidade().length() == 5) {
						ic.setUnidade("0" + ic.getUnidade());
					} else if (ic.getUnidade().length() == 4) {
						ic.setUnidade("00" + ic.getUnidade());
					} else if (ic.getUnidade().length() == 3) {
						ic.setUnidade("000" + ic.getUnidade());
					} else if (ic.getUnidade().length() == 2) {
						ic.setUnidade("0000" + ic.getUnidade());
					} else if (ic.getUnidade().length() == 1) {
						ic.setUnidade("00000" + ic.getUnidade());
					}

					ic.setMes(mes);
					ic.setAno(ano);
					ic.setAnterior(ic.getAnterior());
					ic.setAtual(ic.getAtual());
					ic.setMedia(ic.getMedia());
					ic.setConsumo_conv(ic.getMedia());
					ic.setConsumo_orig(ic.getMedia());
					ic.setCobrar_media("N");
					ic.setValor(ic.getValor());
					valorTotal += ic.getValor();
					totalConsumo += ic.getConsumo_conv();
					this.emDAO.adicionarLeituraUnidade(this.leBEAN, ic, this.sessaoMB);
				}
				this.emDAO.salvarValorEConsumoTotal(valorTotal, totalConsumo, this.leBEAN);

			} catch (ClassNotFoundException | IOException | SQLException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}

		}
	}

	// ↓ MÉTODO PARA INCLUIR CONSUMO NO CÁLCULO DE RATEIO (SIGADM) ↓
	public void incluirRateio() {
		try {
			this.emDAO = new EmissaoDAO();
			this.rtBEAN.setCondominio(this.condominio);
			this.rtBEAN.setConta(this.conta);
			this.rtBEAN.setHistorico(this.historico);
			this.rtBEAN.setCta_anl_financ(this.cta_anl_financ());
			this.rtBEAN.setPerc_desc(0.000000);
			this.rtBEAN.setExclusivo_prop('N');
			this.rtBEAN.setComp_valor(0);
			this.rtBEAN.setProcessado(0);
			this.rtBEAN.setTipo_desc(0);
			this.rtBEAN.setRegular(0);
			this.rtBEAN.setCompl_hist(0);
			this.rtBEAN.setTipo_cob(1);
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(new Date());
			int mes = (cal.get(Calendar.MONDAY) + 2);
			int ano = cal.get(Calendar.YEAR);
			this.rtBEAN.setMes_ini(mes);
			this.rtBEAN.setAno_ini(ano);
			this.rtBEAN.setMes_fin(0);
			this.rtBEAN.setAno_fin(0);
			this.rtBEAN.setCobrar_cota(0);

			this.leBEAN.setLcto_cobranca(this.rtBEAN.getNro_rateio());
			this.ltBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
			this.ltBEAN.setNro_lancto(1);
			this.ltBEAN.setBloco("0");
			this.ltBEAN.setTipo('I');
			this.ltBEAN.setSeguro('N');
			this.ltBEAN.setFlag_calc('N');
			this.ltBEAN.setUsar_fator_mult('N');
			this.ltBEAN.setDesprezar_agreg(0);
			this.ltBEAN.setCobrar_por_metr(0);
			this.ltBEAN.setValor(this.leBEAN.getValor_total());
			this.rtBEAN.setLctos(this.ltBEAN.getNro_lancto());
			this.rtBEAN.setValor_total(this.ltBEAN.getValor());
			this.emDAO.adicionarRateio(this.rtBEAN, this.ltBEAN, this.sessaoMB);
			this.emDAO.salvarRateioConsumo(this.rtBEAN, this.leBEAN);

			double valorTotalUnidade = 0;
			this.getListaConsumo();
			for (intra_cndconsu ic : this.listaConsumo) {
				this.utBEAN.setBloco(ic.getBloco());
				this.utBEAN.setUnidade(ic.getUnidade());
				this.utBEAN.setCondominio(this.rtBEAN.getCondominio());
				this.utBEAN.setNro_rateio(this.rtBEAN.getNro_rateio());
				this.utBEAN.setNro_lancto(this.ltBEAN.getNro_lancto());
				this.utBEAN.setComp_hist(String.valueOf(ic.getMedia()));
				this.utBEAN.setValor(ic.getValor());
				valorTotalUnidade += this.utBEAN.getValor();
				this.emDAO.salvarUnidade(this.rtBEAN, this.ltBEAN, this.utBEAN, this.sessaoMB);
			}
			this.emDAO.salvarValorTotalUnidade(valorTotalUnidade, this.rtBEAN);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void testeMeses() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		int dia = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		int dia1 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//this.mes = (cal.get(Calendar.MONDAY) + 2);
		this.ano = cal.get(Calendar.YEAR);
		try {
			this.dataInicial = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + this.mes + "/" + this.ano);
			this.dataFinal = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia1 + "/" + this.mes + "/" + this.ano);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
