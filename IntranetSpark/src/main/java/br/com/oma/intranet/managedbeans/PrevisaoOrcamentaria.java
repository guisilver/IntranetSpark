package br.com.oma.intranet.managedbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.oma.intranet.dao.PrevisaoDAO;
import br.com.oma.intranet.dao.PrevisaoRateioDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_previsao_orcamentaria;
import br.com.oma.intranet.entidades.intra_previsao_rateio;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.PrevisaoAUX;
import br.com.oma.intranet.util.PrevisaoImprimir;
import br.com.oma.intranet.util.PrevisaoTotalGeralAUX;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ManagedBean(name="previsaoMB")
@ViewScoped
public class PrevisaoOrcamentaria extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4226655926840469040L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	
	//OBJETOS
	private PrevisaoTotalGeralAUX ptgAUX;
	private PrevisaoDAO previsaoDAO;
	private PrevisaoRateioDAO prDAO;
	private intra_previsao_orcamentaria ipoBEAN = new intra_previsao_orcamentaria();
	private intra_previsao_orcamentaria ipoAlterBEAN = new intra_previsao_orcamentaria();
	private intra_grupo_gerente gerenteMB = new intra_grupo_gerente();
	private intra_condominios icBEAN = new intra_condominios();
	private intra_previsao_rateio iprBEAN = new intra_previsao_rateio();

	//ATRIBUTOS
	private List<intra_previsao_rateio> listaDeRateio, filtroDeRaterio;
	private List<intra_previsao_orcamentaria> listaDePrevisao, filtroDePrevisao;
	private List<intra_previsao_orcamentaria> listaDePrevisaoAlterar, filtroDePrevisaoAlterar;
	private List<intra_previsao_orcamentaria> listarCapas;
	private List<intra_condominios> listaDeCondominio;
	private List<intra_grupo_gerente> listaDeGerentes;
	private String nomeCondo;
	private List<String> adv = new ArrayList<String>();
	private byte[] arquivo;
	private String nomeArquivoPrevisao;
	private String nomeArquivoMedia;
	private String nomeArquivoGrafico;
	
	DecimalFormat df = new DecimalFormat("#,###,##0.00",
			new DecimalFormatSymbols(new Locale("pt", "BR")));
	
	private List<PrevisaoAUX> teams2;
	
	private String mediaGeral;
	private String percentualGeral;
	private String tipoDeRateio;
	private String nomeTipoRateio;
	
	private String periodo1;
	private String periodo2;
	private String periodo3;
	private String periodo4;
	private String periodo5;
	private String periodo6;
	private String periodo7;
	private String periodo8;
	private String periodo9;
	private String periodo10;
	private String periodo11;
	private String periodo12;
	
	private boolean inadi;
	
	
	private String inadimplecia1;
	private String inadimplecia2;
	private String inadimplecia3;
	private String inadimplecia4;
	private String inadimplecia5;
	private String inadimplecia6;
	private String inadimplecia7;
	private String inadimplecia8;
	private String inadimplecia9;
	private String inadimplecia10;
	private String inadimplecia11;
	private String inadimplecia12;
	private String inadimplecia13;
	
	private String totalNeceRat1;
	private String totalNeceRat2;
	private String totalNeceRat3;
	private String totalNeceRat4;
	private String totalNeceRat5;
	private String totalNeceRat6;
	private String totalNeceRat7;
	private String totalNeceRat8;
	private String totalNeceRat9;
	private String totalNeceRat10;
	private String totalNeceRat11;
	private String totalNeceRat12;
	private String totalNeceRat13;
	
	private String capa1;
	
	private String mes1;
	private String mes2;
	private String mes3;
	private String mes4;
	private String mes5;
	private String mes6;
	private String mes7;
	private String mes8;
	private String mes9;
	private String mes10;
	private String mes11;
	private String mes12;
	
	private double val1;
	private double val2;
	private double val3;
	private double val4;
	private double val5;
	private double val6;
	private double val7;
	private double val8;
	private double val9;
	private double val10;
	private double val11;
	private double val12;
	private double val13;
	

	//GET SET
	
	public String getCapa1() {
		return capa1;
	}
	
	public void setCapa1(String capa1) {
		this.capa1 = capa1;
	}
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}
	
	public intra_grupo_gerente getGerenteMB() {
		return gerenteMB;
	}

	public void setGerenteMB(intra_grupo_gerente gerenteMB) {
		this.gerenteMB = gerenteMB;
	}

	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
	}

	public List<intra_condominios> getListaDeCondominio() {
		if (this.gerenteMB.getCodigo() > 0) {
			this.previsaoDAO = new PrevisaoDAO();
			this.listaDeCondominio = this.previsaoDAO.listarCondominios(this.gerenteMB.getCodigo());
			}
		return listaDeCondominio;
	}

	public void setListaDeCondominio(List<intra_condominios> listaDeCondominio) {
		this.listaDeCondominio = listaDeCondominio;
	}

	public List<intra_grupo_gerente> getListaDeGerentes() {
		if (this.listaDeGerentes == null) {
			this.listaDeGerentes = this.retornaGerentes();
		}
		return listaDeGerentes;
	}

	public void setListaDeGerentes(List<intra_grupo_gerente> listaDeGerentes) {
		this.listaDeGerentes = listaDeGerentes;
	}

	public String getNomeCondo() {
		return nomeCondo;
	}

	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	public intra_previsao_orcamentaria getIpoBEAN() {
		return ipoBEAN;
	}

	public void setIpoBEAN(intra_previsao_orcamentaria ipoBEAN) {
		this.ipoBEAN = ipoBEAN;
	}

	public List<intra_previsao_orcamentaria> getListaDePrevisao() {
			
		return listaDePrevisao;
	}

	public void setListaDePrevisao(List<intra_previsao_orcamentaria> listaDePrevisao) {
		this.listaDePrevisao = listaDePrevisao;
	}

	public List<intra_previsao_orcamentaria> getFiltroDePrevisao() {
		return filtroDePrevisao;
	}

	public void setFiltroDePrevisao(
			List<intra_previsao_orcamentaria> filtroDePrevisao) {
		this.filtroDePrevisao = filtroDePrevisao;
	}
	
	public List<intra_previsao_orcamentaria> getListarCapas() {
		
		return listarCapas;
	}

	public void setListarCapas(List<intra_previsao_orcamentaria> listarCapas) {
		this.listarCapas = listarCapas;
	}

	public List<PrevisaoAUX> getTeams2() {
		return teams2;
	}

	public void setTeams2(List<PrevisaoAUX> teams2) {
		this.teams2 = teams2;
	}
	
	public String getMes1() {
		return mes1;
	}

	public void setMes1(String mes1) {
		this.mes1 = mes1;
	}

	public String getMes2() {
		return mes2;
	}

	public void setMes2(String mes2) {
		this.mes2 = mes2;
	}

	public String getMes3() {
		return mes3;
	}

	public void setMes3(String mes3) {
		this.mes3 = mes3;
	}

	public String getMes4() {
		return mes4;
	}

	public void setMes4(String mes4) {
		this.mes4 = mes4;
	}

	public String getMes5() {
		return mes5;
	}

	public void setMes5(String mes5) {
		this.mes5 = mes5;
	}

	public String getMes6() {
		return mes6;
	}

	public void setMes6(String mes6) {
		this.mes6 = mes6;
	}

	public String getMes7() {
		return mes7;
	}

	public void setMes7(String mes7) {
		this.mes7 = mes7;
	}

	public String getMes8() {
		return mes8;
	}

	public void setMes8(String mes8) {
		this.mes8 = mes8;
	}

	public String getMes9() {
		return mes9;
	}

	public void setMes9(String mes9) {
		this.mes9 = mes9;
	}

	public String getMes10() {
		return mes10;
	}

	public void setMes10(String mes10) {
		this.mes10 = mes10;
	}

	public String getMes11() {
		return mes11;
	}

	public void setMes11(String mes11) {
		this.mes11 = mes11;
	}

	public String getMes12() {
		return mes12;
	}

	public void setMes12(String mes12) {
		this.mes12 = mes12;
	}
	
	public String getMediaGeral() {
		return mediaGeral;
	}

	public void setMediaGeral(String mediaGeral) {
		this.mediaGeral = mediaGeral;
	}

	public String getPeriodo1() {
		return periodo1;
	}

	public void setPeriodo1(String periodo1) {
		this.periodo1 = periodo1;
	}

	public String getPeriodo2() {
		return periodo2;
	}

	public void setPeriodo2(String periodo2) {
		this.periodo2 = periodo2;
	}

	public String getPeriodo3() {
		return periodo3;
	}

	public void setPeriodo3(String periodo3) {
		this.periodo3 = periodo3;
	}

	public String getPeriodo4() {
		return periodo4;
	}

	public void setPeriodo4(String periodo4) {
		this.periodo4 = periodo4;
	}

	public String getPeriodo5() {
		return periodo5;
	}

	public void setPeriodo5(String periodo5) {
		this.periodo5 = periodo5;
	}

	public String getPeriodo6() {
		return periodo6;
	}

	public void setPeriodo6(String periodo6) {
		this.periodo6 = periodo6;
	}

	public String getPeriodo7() {
		return periodo7;
	}

	public void setPeriodo7(String periodo7) {
		this.periodo7 = periodo7;
	}

	public String getPeriodo8() {
		return periodo8;
	}

	public void setPeriodo8(String periodo8) {
		this.periodo8 = periodo8;
	}

	public String getPeriodo9() {
		return periodo9;
	}

	public void setPeriodo9(String periodo9) {
		this.periodo9 = periodo9;
	}

	public String getPeriodo10() {
		return periodo10;
	}

	public void setPeriodo10(String periodo10) {
		this.periodo10 = periodo10;
	}

	public String getPeriodo11() {
		return periodo11;
	}

	public void setPeriodo11(String periodo11) {
		this.periodo11 = periodo11;
	}

	public String getPeriodo12() {
		return periodo12;
	}

	public void setPeriodo12(String periodo12) {
		this.periodo12 = periodo12;
	}

	public String getPercentualGeral() {
		return percentualGeral;
	}

	public void setPercentualGeral(String percentualGeral) {
		this.percentualGeral = percentualGeral;
	}
	
	public intra_previsao_orcamentaria getIpoAlterBEAN() {
		return ipoAlterBEAN;
	}

	public void setIpoAlterBEAN(intra_previsao_orcamentaria ipoAlterBEAN) {
		this.ipoAlterBEAN = ipoAlterBEAN;
	}

	public List<intra_previsao_orcamentaria> getListaDePrevisaoAlterar() {
		return listaDePrevisaoAlterar;
	}

	public void setListaDePrevisaoAlterar(
			List<intra_previsao_orcamentaria> listaDePrevisaoAlterar) {
		this.listaDePrevisaoAlterar = listaDePrevisaoAlterar;
	}

	public List<intra_previsao_orcamentaria> getFiltroDePrevisaoAlterar() {
		return filtroDePrevisaoAlterar;
	}

	public void setFiltroDePrevisaoAlterar(
			List<intra_previsao_orcamentaria> filtroDePrevisaoAlterar) {
		this.filtroDePrevisaoAlterar = filtroDePrevisaoAlterar;
	}

	public PrevisaoDAO getPrevisaoDAO() {
		return previsaoDAO;
	}

	public void setPrevisaoDAO(PrevisaoDAO previsaoDAO) {
		this.previsaoDAO = previsaoDAO;
	}

	public boolean isInadi() {
		return inadi;
	}

	public void setInadi(boolean inadi) {
		this.inadi = inadi;
	}

	public String getInadimplecia1() {
		return inadimplecia1;
	}

	public void setInadimplecia1(String inadimplecia1) {
		this.inadimplecia1 = inadimplecia1;
	}

	public String getInadimplecia2() {
		return inadimplecia2;
	}

	public void setInadimplecia2(String inadimplecia2) {
		this.inadimplecia2 = inadimplecia2;
	}

	public String getInadimplecia3() {
		return inadimplecia3;
	}

	public void setInadimplecia3(String inadimplecia3) {
		this.inadimplecia3 = inadimplecia3;
	}

	public String getInadimplecia4() {
		return inadimplecia4;
	}

	public void setInadimplecia4(String inadimplecia4) {
		this.inadimplecia4 = inadimplecia4;
	}

	public String getInadimplecia5() {
		return inadimplecia5;
	}

	public void setInadimplecia5(String inadimplecia5) {
		this.inadimplecia5 = inadimplecia5;
	}

	public String getInadimplecia6() {
		return inadimplecia6;
	}

	public void setInadimplecia6(String inadimplecia6) {
		this.inadimplecia6 = inadimplecia6;
	}

	public String getInadimplecia7() {
		return inadimplecia7;
	}

	public void setInadimplecia7(String inadimplecia7) {
		this.inadimplecia7 = inadimplecia7;
	}

	public String getInadimplecia8() {
		return inadimplecia8;
	}

	public void setInadimplecia8(String inadimplecia8) {
		this.inadimplecia8 = inadimplecia8;
	}

	public String getInadimplecia9() {
		return inadimplecia9;
	}

	public void setInadimplecia9(String inadimplecia9) {
		this.inadimplecia9 = inadimplecia9;
	}

	public String getInadimplecia10() {
		return inadimplecia10;
	}

	public void setInadimplecia10(String inadimplecia10) {
		this.inadimplecia10 = inadimplecia10;
	}

	public String getInadimplecia11() {
		return inadimplecia11;
	}

	public void setInadimplecia11(String inadimplecia11) {
		this.inadimplecia11 = inadimplecia11;
	}

	public String getInadimplecia12() {
		return inadimplecia12;
	}

	public void setInadimplecia12(String inadimplecia12) {
		this.inadimplecia12 = inadimplecia12;
	}

	public String getInadimplecia13() {
		return inadimplecia13;
	}

	public void setInadimplecia13(String inadimplecia13) {
		this.inadimplecia13 = inadimplecia13;
	}

	public String getTotalNeceRat1() {
		return totalNeceRat1;
	}

	public void setTotalNeceRat1(String totalNeceRat1) {
		this.totalNeceRat1 = totalNeceRat1;
	}

	public String getTotalNeceRat2() {
		return totalNeceRat2;
	}

	public void setTotalNeceRat2(String totalNeceRat2) {
		this.totalNeceRat2 = totalNeceRat2;
	}

	public String getTotalNeceRat3() {
		return totalNeceRat3;
	}

	public void setTotalNeceRat3(String totalNeceRat3) {
		this.totalNeceRat3 = totalNeceRat3;
	}

	public String getTotalNeceRat4() {
		return totalNeceRat4;
	}

	public void setTotalNeceRat4(String totalNeceRat4) {
		this.totalNeceRat4 = totalNeceRat4;
	}

	public String getTotalNeceRat5() {
		return totalNeceRat5;
	}

	public void setTotalNeceRat5(String totalNeceRat5) {
		this.totalNeceRat5 = totalNeceRat5;
	}

	public String getTotalNeceRat6() {
		return totalNeceRat6;
	}

	public void setTotalNeceRat6(String totalNeceRat6) {
		this.totalNeceRat6 = totalNeceRat6;
	}

	public String getTotalNeceRat7() {
		return totalNeceRat7;
	}

	public void setTotalNeceRat7(String totalNeceRat7) {
		this.totalNeceRat7 = totalNeceRat7;
	}

	public String getTotalNeceRat8() {
		return totalNeceRat8;
	}

	public void setTotalNeceRat8(String totalNeceRat8) {
		this.totalNeceRat8 = totalNeceRat8;
	}

	public String getTotalNeceRat9() {
		return totalNeceRat9;
	}

	public void setTotalNeceRat9(String totalNeceRat9) {
		this.totalNeceRat9 = totalNeceRat9;
	}

	public String getTotalNeceRat10() {
		return totalNeceRat10;
	}

	public void setTotalNeceRat10(String totalNeceRat10) {
		this.totalNeceRat10 = totalNeceRat10;
	}

	public String getTotalNeceRat11() {
		return totalNeceRat11;
	}

	public void setTotalNeceRat11(String totalNeceRat11) {
		this.totalNeceRat11 = totalNeceRat11;
	}

	public String getTotalNeceRat12() {
		return totalNeceRat12;
	}

	public void setTotalNeceRat12(String totalNeceRat12) {
		this.totalNeceRat12 = totalNeceRat12;
	}

	public String getTotalNeceRat13() {
		return totalNeceRat13;
	}

	public void setTotalNeceRat13(String totalNeceRat13) {
		this.totalNeceRat13 = totalNeceRat13;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getNomeArquivoPrevisao() {
		return nomeArquivoPrevisao;
	}

	public void setNomeArquivoPrevisao(String nomeArquivoPrevisao) {
		this.nomeArquivoPrevisao = nomeArquivoPrevisao;
	}

	public String getNomeArquivoMedia() {
		return nomeArquivoMedia;
	}

	public void setNomeArquivoMedia(String nomeArquivoMedia) {
		this.nomeArquivoMedia = nomeArquivoMedia;
	}

	public String getNomeArquivoGrafico() {
		return nomeArquivoGrafico;
	}

	public void setNomeArquivoGrafico(String nomeArquivoGrafico) {
		this.nomeArquivoGrafico = nomeArquivoGrafico;
	}

	public List<intra_previsao_rateio> getListaDeRateio() {
		if(this.icBEAN.getCodigo() > 0){
			this.listaDeRateio = new ArrayList<intra_previsao_rateio>();
			this.prDAO = new PrevisaoRateioDAO();
			this.listaDeRateio = this.prDAO.listarVerbasRateio(this.icBEAN.getCodigo());
		}
		return listaDeRateio;
	}

	public void setListaDeRateio(List<intra_previsao_rateio> listaDeRateio) {
		this.listaDeRateio = listaDeRateio;
	}

	public List<intra_previsao_rateio> getFiltroDeRaterio() {
		return filtroDeRaterio;
	}

	public void setFiltroDeRaterio(List<intra_previsao_rateio> filtroDeRaterio) {
		this.filtroDeRaterio = filtroDeRaterio;
	}

	public intra_previsao_rateio getIprBEAN() {
		return iprBEAN;
	}

	public void setIprBEAN(intra_previsao_rateio iprBEAN) {
		this.iprBEAN = iprBEAN;
	}

	public String getTipoDeRateio() {
		return tipoDeRateio;
	}

	public void setTipoDeRateio(String tipoDeRateio) {
		this.tipoDeRateio = tipoDeRateio;
	}

	public String getNomeTipoRateio() {
		return nomeTipoRateio;
	}

	public void setNomeTipoRateio(String nomeTipoRateio) {
		this.nomeTipoRateio = nomeTipoRateio;
	}

	//METODOS
	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome()
					.equals(" Todos")) {
				this.listaDeCondominio = null;
				this.nomeCondo = "";
				this.icBEAN = new intra_condominios();
				this.gerenteMB.setCodigo(this.sessaoMB.getListaDeGerente().get(0).getCodigo());
				return this.sessaoMB.getListaDeGerente();
			} else {
				this.listaDeCondominio = null;
				this.nomeCondo = "";
				this.icBEAN= new intra_condominios();
				this.gerenteMB.setCodigo(this.sessaoMB.getListaDeGerente().get(0).getCodigo());
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}
	
	public void retornaNomeCondominio() {
		for (intra_condominios c : sessaoMB.getListaDeCondominios()) {
			if (c.getCodigo() == this.icBEAN.getCodigo()) {
				this.nomeCondo = c.getNome();
				this.icBEAN.setNomeGerente(c.getNomeGerente());
				this.icBEAN.setEmailGerente(c.getEmailGerente());
				this.icBEAN.setCodigoGerente(c.getCodigoGerente());
			}
		}
		this.carregar();
		this.carregaListaAlterar();
	}
	
	public void carregaListaAlterar() {
		this.listaDePrevisaoAlterar = new ArrayList<>();
		if (this.icBEAN.getCodigo() > 0) {
			this.listaDePrevisaoAlterar = this.previsaoDAO.listarPrevisao(
					this.icBEAN.getCodigo(), this.gerenteMB.getCodigo());
		}
	}
	
	public void carregar() {
		if (this.icBEAN.getCodigo() > 0) {
			this.listaDePrevisao = null;
			this.previsaoDAO = new PrevisaoDAO();
			this.ptgAUX = new PrevisaoTotalGeralAUX();
			this.teams2 = null;
			this.percentualGeral = "";
			this.listaDePrevisao = this.previsaoDAO.listarPrevisao(this.icBEAN.getCodigo(), this.gerenteMB.getCodigo());

			if (this.listaDePrevisao != null) {
				if(!this.listaDePrevisao.isEmpty()){
				int capa1 = 0;
				int capa2 = 0;
				int capa3 = 0;
				int capa4 = 0;
				int capa5 = 0;
				int capa6 = 0;
				int capa7 = 0;
				int capa8 = 0;
				PrevisaoAUX ptCapa1 = new PrevisaoAUX();
				PrevisaoAUX ptCapa2 = new PrevisaoAUX();
				PrevisaoAUX ptCapa3 = new PrevisaoAUX();
				PrevisaoAUX ptCapa4 = new PrevisaoAUX();
				PrevisaoAUX ptCapa5 = new PrevisaoAUX();
				PrevisaoAUX ptCapa6 = new PrevisaoAUX();
				PrevisaoAUX ptCapa7 = new PrevisaoAUX();
				PrevisaoAUX ptCapa8 = new PrevisaoAUX();
				teams2 = new ArrayList<PrevisaoAUX>();
				this.percentualGeral = "100%";
				for (intra_previsao_orcamentaria p : this.listaDePrevisao) {
					int i = 0;
					if(i==0){
						
						DateTimeFormatter dtf = DateTimeFormat.forPattern("MMM").withLocale(new Locale("pt","BR"));
						DateTime dt = new DateTime(p.getMesProjecao());
						
						this.mes1 = String.valueOf(dtf.print(dt.plusMonths(i)) +"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes2 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes3 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes4 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes5 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes6 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes7 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes8 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes9 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes10 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						dt.plusMonths(i);
						this.mes11 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
						i +=1;
						this.mes12 = String.valueOf(dtf.print(dt.plusMonths(i))+"/"+dt.plusMonths(i).getYearOfCentury());
					}
				}
				
				intra_previsao_orcamentaria conta01 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta02 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta03 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta04 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta05 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta06 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta07 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta08 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta09 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta010 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta011 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta012 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta013 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta014 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta015 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta016 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta017 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta018 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta019 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta020 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta021 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta022 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta023 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta024 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta025 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta026 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta027 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta028 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta029 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta030 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta031 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta032 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta033 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta034 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta035 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta036 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta037 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta038 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta039 = new intra_previsao_orcamentaria();
				
				intra_previsao_orcamentaria conta11 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta12 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta13 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta14 = new intra_previsao_orcamentaria();
				
				intra_previsao_orcamentaria conta21 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta22 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta23 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta24 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta25 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta26 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta27 = new intra_previsao_orcamentaria();
				
				intra_previsao_orcamentaria conta31 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta32 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta33 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta34 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta35 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta36 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta37 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta38 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta39 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta310 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta311 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta312 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta313 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta314 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta315 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta316 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta317 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta318 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta319 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta320 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta321 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta322 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta323 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta324 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta325 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta326 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta327 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta328 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta329 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta330 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta331 = new intra_previsao_orcamentaria();
				
				intra_previsao_orcamentaria conta41 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta42 = new intra_previsao_orcamentaria();
				
				intra_previsao_orcamentaria conta51 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta52 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta53 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta54 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta55 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta56 = new intra_previsao_orcamentaria();
				
				intra_previsao_orcamentaria conta61 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta62 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta63 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta64 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta65 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta66 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta67 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta68 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta69 = new intra_previsao_orcamentaria();
				
				intra_previsao_orcamentaria conta71 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta72 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta73 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta74 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta75 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta76 = new intra_previsao_orcamentaria();
				
				intra_previsao_orcamentaria conta81 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta82 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta83 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta84 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta85 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta86 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta87 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta88 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta89 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta810 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta811 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta812 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta813 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta814 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta815 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta816 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta817 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta818 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta819 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta820 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta821 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta822 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta823 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta824 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta825 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta826 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta827 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta828 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta829 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta830 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta831 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta832 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta833 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta834 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta835 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta836 = new intra_previsao_orcamentaria();
				intra_previsao_orcamentaria conta837 = new intra_previsao_orcamentaria();
			
				for (intra_previsao_orcamentaria p : this.listaDePrevisao) {
					
					if (p.getConta() == 1400 & capa1 == 0) {
						ptCapa1 = new PrevisaoAUX(p.getCapa());
						capa1++;
					}
					if (p.getCodigoDespesa().equals("01")) {
						conta01 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("02")) {
						conta02 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("03")) {
						conta03 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("04")) {
						conta04 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("05")) {
						conta05 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("06")) {
						conta06 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("07")) {
						conta07 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("08")) {
						conta08 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("09")) {
						conta09 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("010")) {
						conta010 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("011")) {
						conta011 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("012")) {
						conta012 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("013")) {
						conta013 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("014")) {
						conta014 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("015")) {
						conta015 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("016")) {
						conta016 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("017")) {
						conta017 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("018")) {
						conta018 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("019")) {
						conta019 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("020")) {
						conta020 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("021")) {
						conta021 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("022")) {
						conta022 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("023")) {
						conta023 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("024")) {
						conta024 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("025")) {
						conta025 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("026")) {
						conta026 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("027")) {
						conta027 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("028")) {
						conta028 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("029")) {
						conta029 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("030")) {
						conta030 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("031")) {
						conta031 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("032")) {
						conta032 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("033")) {
						conta033 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("034")) {
						conta034 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("035")) {
						conta035 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("036")) {
						conta036 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("037")) {
						conta037 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("038")) {
						conta038 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("039")) {
						conta039 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("11")) {
						conta11 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("12")) {
						conta12 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("13")) {
						conta13 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("14")) {
						conta14 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					
					if (p.getConta() == 1405 & capa2 == 0) {
						ptCapa2 = new PrevisaoAUX(p.getCapa());
						capa2++;
					}
					
					if (p.getCodigoDespesa().equals("21")) {
						conta21 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), 	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("22")) {
						conta22 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("23")) {
						conta23 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("24")) {
						conta24 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("25")) {
						conta25 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("26")) {
						conta26 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("27")) {
						conta27 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					
					if (p.getConta() == 1406 & capa3 == 0) {
						ptCapa3 = new PrevisaoAUX(p.getCapa());
						capa3++;
					}
					if (p.getCodigoDespesa().equals("31")) {
						conta31 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("32")) {
						conta32 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("33")) {
						conta33 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("34")) {
						conta34 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("35")) {
						conta35 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("36")) {
						conta36 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("37")) {
						conta37 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("38")) {
						conta38 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("39")) {
						conta39 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("310")) {
						conta310 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("311")) {
						conta311 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("312")) {
						conta312 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("313")) {
						conta313 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("314")) {
						conta314 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("315")) {
						conta315 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("316")) {
						conta316 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("317")) {
						conta317 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("318")) {
						conta318 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("319")) {
						conta319 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("320")) {
						conta320 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("321")) {
						conta321 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("322")) {
						conta322 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("323")) {
						conta323 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("324")) {
						conta324 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("325")) {
						conta325 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("326")) {
						conta326 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("327")) {
						conta327 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("328")) {
						conta328 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("329")) {
						conta329 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("330")) {
						conta330 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("331")) {
						conta331 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					
					if (p.getConta() == 1407 & capa4 == 0) {
						ptCapa4 = new PrevisaoAUX(p.getCapa());
						capa4++;
					}
					if (p.getCodigoDespesa().equals("41")) {
						conta41 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("42")) {
						conta42 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					
					if (p.getConta() == 1408 & capa5 == 0) {
						ptCapa5 = new PrevisaoAUX(p.getCapa());
						capa5++;
					}
					if (p.getCodigoDespesa().equals("51")) {
						conta51 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("52")) {
						conta52 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					
					if (p.getConta() == 1409 & capa6 == 0) {
						ptCapa6 = new PrevisaoAUX(p.getCapa());
						capa6++;
					}
					
					if (p.getCodigoDespesa().equals("61")) {
						conta61 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), 	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("62")) {
						conta62 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("63")) {
						conta63 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("64")) {
						conta64 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("65")) {
						conta65 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("66")) {
						conta66 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("67")) {
						conta67 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("68")) {
						conta68 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("69")) {
						conta69 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					
					if (p.getConta() == 1410 & capa7 == 0) {
						ptCapa7 = new PrevisaoAUX(p.getCapa());
						capa7++;
					}
					
					if (p.getCodigoDespesa().equals("71")) {
						conta71 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), 	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("72")) {
						conta72 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("73")) {
						conta73 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("74")) {
						conta74 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("75")) {
						conta75 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("76")) {
						conta76 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					
					if (p.getConta() == 1411 & capa8 == 0) {
						ptCapa8 = new PrevisaoAUX(p.getCapa());
						capa8++;
					}
					
					if (p.getCodigoDespesa().equals("81")) {
						conta81 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("82")) {
						conta82 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("83")) {
						conta83 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("84")) {
						conta84 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("85")) {
						conta85 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("86")) {
						conta86 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("87")) {
						conta87 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("88")) {
						conta88 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("89")) {
						conta89 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("810")) {
						conta810 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("811")) {
						conta811 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(),	this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("812")) {
						conta812 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("813")) {
						conta813 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("814")) {
						conta814 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("815")) {
						conta815 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("816")) {
						conta816 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("817")) {
						conta817 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("818")) {
						conta818 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("819")) {
						conta819 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("820")) {
						conta820 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("821")) {
						conta821 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("822")) {
						conta822 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("823")) {
						conta823 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("824")) {
						conta824 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("825")) {
						conta825 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("826")) {
						conta826 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("827")) {
						conta827 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("828")) {
						conta828 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("829")) {
						conta829 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("830")) {
						conta830 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("831")) {
						conta831 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("832")) {
						conta832 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("833")) {
						conta833 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("834")) {
						conta834 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("835")) {
						conta835 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("836")) {
						conta836 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					if (p.getCodigoDespesa().equals("837")) {
						conta837 = (new intra_previsao_orcamentaria(p.getCondominio(), p.getDespesas(), this.retornaMes(p, 0), this.retornaMes(p, 1), this.retornaMes(p, 2), 
								this.retornaMes(p, 3), this.retornaMes(p, 4), this.retornaMes(p, 5), this.retornaMes(p, 6), this.retornaMes(p, 7), this.retornaMes(p, 8), 
								this.retornaMes(p, 9), this.retornaMes(p, 10), this.retornaMes(p, 11), p.getMedia()));
					}
					
				}
				if(!this.listaDePrevisao.isEmpty()){
				this.val1 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 0);
				this.val2 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 1);
				this.val3 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 2);
				this.val4 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 3);
				this.val5 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 4);
				this.val6 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 5);
				this.val7 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 6);
				this.val8 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 7);
				this.val9 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 8);
				this.val10 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 9);
				this.val11 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 10);
				this.val12 = this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 11);
				this.val13 = this.ptgAUX.retornaTotalGeralMedia(this.listaDePrevisao);
				}
				if(!this.listaDePrevisao.isEmpty()){
				this.periodo1 = String.valueOf(df.format(this.val1));
				this.periodo2 = String.valueOf(df.format(this.val2));
				this.periodo3 = String.valueOf(df.format(this.val3));
				this.periodo4 = String.valueOf(df.format(this.val4));
				this.periodo5 = String.valueOf(df.format(this.val5));
				this.periodo6 = String.valueOf(df.format(this.val6));
				this.periodo7 = String.valueOf(df.format(this.val7));
				this.periodo8 = String.valueOf(df.format(this.val8));
				this.periodo9 = String.valueOf(df.format(this.val9));
				this.periodo10 = String.valueOf(df.format(this.val10));
				this.periodo11 = String.valueOf(df.format(this.val11));
				this.periodo12 = String.valueOf(df.format(this.val12));
				this.mediaGeral = String.valueOf(df.format(this.val13));
				}else{
					this.periodo1 = "";this.periodo2 = "";this.periodo3 = "";this.periodo4 = "";this.periodo5 = "";
					this.periodo6 = "";this.periodo7 = "";this.periodo8 = "";this.periodo9 = "";this.periodo10 = "";
					this.periodo11 = "";this.periodo12 = "";this.mediaGeral = "";
				}
				if(!this.listaDePrevisao.isEmpty()){
					for (intra_previsao_orcamentaria list : this.listaDePrevisao) {
						if(list.getCapa().equals("Inadimplência")){
							this.inadi = true;
							this.inadimplecia1 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 0)*list.getInadimplencia()) /100 ));
							this.inadimplecia2 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 1)*list.getInadimplencia()) /100 ));
							this.inadimplecia3 = String.valueOf(df.format( (this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 2)*list.getInadimplencia()) /100 ));
							this.inadimplecia4 = String.valueOf(df.format( (this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 3)*list.getInadimplencia()) /100 ));
							this.inadimplecia5 = String.valueOf(df.format( (this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 4)*list.getInadimplencia()) /100 ));
							this.inadimplecia6 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 5)*list.getInadimplencia()) /100 ));
							this.inadimplecia7 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 6)*list.getInadimplencia()) /100 ));
							this.inadimplecia8 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 7)*list.getInadimplencia()) /100 ));
							this.inadimplecia9 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 8)*list.getInadimplencia()) /100 ));
							this.inadimplecia10 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 9)*list.getInadimplencia()) /100 ));
							this.inadimplecia11 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 10)*list.getInadimplencia()) /100 ));
							this.inadimplecia12 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 11)*list.getInadimplencia()) /100 ));
							this.inadimplecia13 = String.valueOf(df.format((this.ptgAUX.retornaTotalGeralMedia(this.listaDePrevisao)*list.getInadimplencia()) /100 ));
						}
					}
				}
				if(!this.listaDePrevisao.isEmpty()){
						if(this.inadi){
							for (intra_previsao_orcamentaria list : this.listaDePrevisao) {
								if(list.getCapa().equals("Inadimplência")){
									this.inadi = true;
							this.totalNeceRat1 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 0)*list.getInadimplencia())/100)+this.val1));
							this.totalNeceRat2 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 1)*list.getInadimplencia())/100)+this.val2));
							this.totalNeceRat3= String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 2)*list.getInadimplencia())/100)+this.val3));
							this.totalNeceRat4 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 3)*list.getInadimplencia())/100)+this.val4));
							this.totalNeceRat5 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 4)*list.getInadimplencia())/100)+this.val5));
							this.totalNeceRat6 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 5)*list.getInadimplencia())/100)+this.val6));
							this.totalNeceRat7 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 6)*list.getInadimplencia())/100)+this.val7));
							this.totalNeceRat8 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 7)*list.getInadimplencia())/100)+this.val8));
							this.totalNeceRat9 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 8)*list.getInadimplencia())/100)+this.val9));
							this.totalNeceRat10 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 9)*list.getInadimplencia())/100)+this.val10));
							this.totalNeceRat11 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 10)*list.getInadimplencia())/100)+this.val1));
							this.totalNeceRat12 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 11)*list.getInadimplencia())/100)+this.val2));
							this.totalNeceRat13 = String.valueOf(df.format(((this.ptgAUX.retornaTotalGeralMedia(this.listaDePrevisao)*list.getInadimplencia()/100)+this.val13)));
							}
						}
					}else{
						this.totalNeceRat1 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 0)));
						this.totalNeceRat2 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 1)));
						this.totalNeceRat3 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 2)));
						this.totalNeceRat4 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 3)));
						this.totalNeceRat5 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 4)));
						this.totalNeceRat6 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 5)));
						this.totalNeceRat7 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 6)));
						this.totalNeceRat8 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 7)));
						this.totalNeceRat9 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 8)));
						this.totalNeceRat10 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 9)));
						this.totalNeceRat11 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 10)));
						this.totalNeceRat12 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeral(this.listaDePrevisao, 11)));
						this.totalNeceRat13 = String.valueOf(df.format(this.ptgAUX.retornaTotalGeralMedia(this.listaDePrevisao)));
					}
				}
				
				if(conta01.getCondominio() > 0)
					ptCapa1.getLista().add(conta01);
				if(conta02.getCondominio() > 0)
					ptCapa1.getLista().add(conta02);
				if(conta03.getCondominio() > 0)
					ptCapa1.getLista().add(conta03);
				if(conta04.getCondominio() > 0)
					ptCapa1.getLista().add(conta04);
				if(conta05.getCondominio() > 0)
					ptCapa1.getLista().add(conta05);
				if(conta06.getCondominio() > 0)
					ptCapa1.getLista().add(conta06);
				if(conta07.getCondominio() > 0)
					ptCapa1.getLista().add(conta07);
				if(conta08.getCondominio() > 0)
					ptCapa1.getLista().add(conta08);
				if(conta09.getCondominio() > 0)
					ptCapa1.getLista().add(conta09);
				if(conta010.getCondominio() > 0)
					ptCapa1.getLista().add(conta010);
				if(conta011.getCondominio() > 0)
					ptCapa1.getLista().add(conta011);
				if(conta012.getCondominio() > 0)
					ptCapa1.getLista().add(conta012);
				if(conta013.getCondominio() > 0)
					ptCapa1.getLista().add(conta013);
				if(conta014.getCondominio() > 0)
					ptCapa1.getLista().add(conta014);
				if(conta015.getCondominio() > 0)
					ptCapa1.getLista().add(conta015);
				if(conta016.getCondominio() > 0)
					ptCapa1.getLista().add(conta016);
				if(conta017.getCondominio() > 0)
					ptCapa1.getLista().add(conta017);
				if(conta018.getCondominio() > 0)
					ptCapa1.getLista().add(conta018);
				if(conta019.getCondominio() > 0)
					ptCapa1.getLista().add(conta019);
				if(conta020.getCondominio() > 0)
					ptCapa1.getLista().add(conta020);
				if(conta021.getCondominio() > 0)
					ptCapa1.getLista().add(conta021);
				if(conta022.getCondominio() > 0)
					ptCapa1.getLista().add(conta022);
				if(conta023.getCondominio() > 0)
					ptCapa1.getLista().add(conta023);
				if(conta024.getCondominio() > 0)
					ptCapa1.getLista().add(conta024);
				if(conta025.getCondominio() > 0)
					ptCapa1.getLista().add(conta025);
				if(conta026.getCondominio() > 0)
					ptCapa1.getLista().add(conta026);
				if(conta027.getCondominio() > 0)
					ptCapa1.getLista().add(conta027);
				if(conta028.getCondominio() > 0)
					ptCapa1.getLista().add(conta028);
				if(conta029.getCondominio() > 0)
					ptCapa1.getLista().add(conta029);
				if(conta030.getCondominio() > 0)
					ptCapa1.getLista().add(conta030);
				if(conta031.getCondominio() > 0)
					ptCapa1.getLista().add(conta031);
				if(conta032.getCondominio() > 0)
					ptCapa1.getLista().add(conta032);
				if(conta033.getCondominio() > 0)
					ptCapa1.getLista().add(conta033);
				if(conta034.getCondominio() > 0)
					ptCapa1.getLista().add(conta034);
				if(conta035.getCondominio() > 0)
					ptCapa1.getLista().add(conta035);
				if(conta036.getCondominio() > 0)
					ptCapa1.getLista().add(conta036);
				if(conta037.getCondominio() > 0)
					ptCapa1.getLista().add(conta037);
				if(conta038.getCondominio() > 0)
					ptCapa1.getLista().add(conta038);
				if(conta039.getCondominio() > 0)
					ptCapa1.getLista().add(conta039);
				if(conta11.getCondominio() > 0)
					ptCapa1.getLista().add(conta11);
				if(conta12.getCondominio() > 0)
					ptCapa1.getLista().add(conta12);
				if(conta13.getCondominio() > 0)
					ptCapa1.getLista().add(conta13);
				if(conta14.getCondominio() > 0)
					ptCapa1.getLista().add(conta14);
				
				if(ptCapa1.getNomeCapa() != null){
					this.teams2.add(ptCapa1);
				}
				
				if(conta21.getCondominio() > 0)
					ptCapa2.getLista().add(conta21);
				if(conta22.getCondominio() > 0)
					ptCapa2.getLista().add(conta22);
				if(conta23.getCondominio() > 0)
					ptCapa2.getLista().add(conta23);
				if(conta24.getCondominio() > 0)
					ptCapa2.getLista().add(conta24);
				if(conta25.getCondominio() > 0)
					ptCapa2.getLista().add(conta25);
				if(conta26.getCondominio() > 0)
					ptCapa2.getLista().add(conta26);
				if(conta27.getCondominio() > 0)
					ptCapa2.getLista().add(conta27);
				
				if(ptCapa2.getNomeCapa() != null){
					this.teams2.add(ptCapa2);
				}
				
				if(conta31.getCondominio() > 0)
					ptCapa3.getLista().add(conta31);
				if(conta32.getCondominio() > 0)
					ptCapa3.getLista().add(conta32);
				if(conta33.getCondominio() > 0)
					ptCapa3.getLista().add(conta33);
				if(conta34.getCondominio() > 0)
					ptCapa3.getLista().add(conta34);
				if(conta35.getCondominio() > 0)
					ptCapa3.getLista().add(conta35);
				if(conta36.getCondominio() > 0)
					ptCapa3.getLista().add(conta36);
				if(conta37.getCondominio() > 0)
					ptCapa3.getLista().add(conta37);
				if(conta38.getCondominio() > 0)
					ptCapa3.getLista().add(conta38);
				if(conta39.getCondominio() > 0)
					ptCapa3.getLista().add(conta39);
				if(conta310.getCondominio() > 0)
					ptCapa3.getLista().add(conta310);
				if(conta311.getCondominio() > 0)
					ptCapa3.getLista().add(conta311);
				if(conta312.getCondominio() > 0)
					ptCapa3.getLista().add(conta312);
				if(conta313.getCondominio() > 0)
					ptCapa3.getLista().add(conta313);
				if(conta314.getCondominio() > 0)
					ptCapa3.getLista().add(conta314);
				if(conta315.getCondominio() > 0)
					ptCapa3.getLista().add(conta315);
				if(conta316.getCondominio() > 0)
					ptCapa3.getLista().add(conta316);
				if(conta317.getCondominio() > 0)
					ptCapa3.getLista().add(conta317);
				if(conta318.getCondominio() > 0)
					ptCapa3.getLista().add(conta318);
				if(conta319.getCondominio() > 0)
					ptCapa3.getLista().add(conta319);
				if(conta320.getCondominio() > 0)
					ptCapa3.getLista().add(conta320);
				if(conta321.getCondominio() > 0)
					ptCapa3.getLista().add(conta321);
				if(conta322.getCondominio() > 0)
					ptCapa3.getLista().add(conta322);
				if(conta323.getCondominio() > 0)
					ptCapa3.getLista().add(conta323);
				if(conta324.getCondominio() > 0)
					ptCapa3.getLista().add(conta324);
				if(conta325.getCondominio() > 0)
					ptCapa3.getLista().add(conta325);
				if(conta326.getCondominio() > 0)
					ptCapa3.getLista().add(conta326);
				if(conta327.getCondominio() > 0)
					ptCapa3.getLista().add(conta327);
				if(conta328.getCondominio() > 0)
					ptCapa3.getLista().add(conta328);
				if(conta329.getCondominio() > 0)
					ptCapa3.getLista().add(conta329);
				if(conta330.getCondominio() > 0)
					ptCapa3.getLista().add(conta330);
				if(conta331.getCondominio() > 0)
					ptCapa3.getLista().add(conta331);
				
				if(ptCapa3.getNomeCapa() != null){
					this.teams2.add(ptCapa3);
				}
				
				if(conta41.getCondominio() > 0)
					ptCapa4.getLista().add(conta41);
				if(conta42.getCondominio() > 0)
					ptCapa4.getLista().add(conta42);
				
				if(ptCapa4.getNomeCapa() != null){
					this.teams2.add(ptCapa4);
				}
				
				if(conta51.getCondominio() > 0)
					ptCapa5.getLista().add(conta51);
				if(conta52.getCondominio() > 0)
					ptCapa5.getLista().add(conta52);
				if(conta53.getCondominio() > 0)
					ptCapa5.getLista().add(conta53);
				if(conta54.getCondominio() > 0)
					ptCapa5.getLista().add(conta54);
				if(conta55.getCondominio() > 0)
					ptCapa5.getLista().add(conta55);
				if(conta56.getCondominio() > 0)
					ptCapa5.getLista().add(conta56);
				
				if(ptCapa5.getNomeCapa() != null){
					this.teams2.add(ptCapa5);
				}
				
				if(conta61.getCondominio() > 0)
					ptCapa6.getLista().add(conta61);
				if(conta62.getCondominio() > 0)
					ptCapa6.getLista().add(conta62);
				if(conta63.getCondominio() > 0)
					ptCapa6.getLista().add(conta63);
				if(conta64.getCondominio() > 0)
					ptCapa6.getLista().add(conta64);
				if(conta65.getCondominio() > 0)
					ptCapa6.getLista().add(conta65);
				if(conta66.getCondominio() > 0)
					ptCapa6.getLista().add(conta66);
				if(conta67.getCondominio() > 0)
					ptCapa6.getLista().add(conta67);
				if(conta68.getCondominio() > 0)
					ptCapa6.getLista().add(conta68);
				if(conta69.getCondominio() > 0)
					ptCapa6.getLista().add(conta69);
				
				if(ptCapa6.getNomeCapa() != null){
					this.teams2.add(ptCapa6);
				}
				
				if(conta71.getCondominio() > 0)
					ptCapa7.getLista().add(conta71);
				if(conta72.getCondominio() > 0)
					ptCapa7.getLista().add(conta72);
				if(conta73.getCondominio() > 0)
					ptCapa7.getLista().add(conta73);
				if(conta74.getCondominio() > 0)
					ptCapa7.getLista().add(conta74);
				if(conta75.getCondominio() > 0)
					ptCapa7.getLista().add(conta75);
				if(conta76.getCondominio() > 0)
					ptCapa7.getLista().add(conta76);
				
				if(ptCapa7.getNomeCapa() != null){
					this.teams2.add(ptCapa7);
				}
				
				if(conta81.getCondominio() > 0)
					ptCapa8.getLista().add(conta81);
				if(conta82.getCondominio() > 0)
					ptCapa8.getLista().add(conta82);
				if(conta83.getCondominio() > 0)
					ptCapa8.getLista().add(conta83);
				if(conta84.getCondominio() > 0)
					ptCapa8.getLista().add(conta84);
				if(conta85.getCondominio() > 0)
					ptCapa8.getLista().add(conta85);
				if(conta86.getCondominio() > 0)
					ptCapa8.getLista().add(conta86);
				if(conta87.getCondominio() > 0)
					ptCapa8.getLista().add(conta87);
				if(conta88.getCondominio() > 0)
					ptCapa8.getLista().add(conta88);
				if(conta89.getCondominio() > 0)
					ptCapa8.getLista().add(conta89);
				if(conta810.getCondominio() > 0)
					ptCapa8.getLista().add(conta810);
				if(conta811.getCondominio() > 0)
					ptCapa8.getLista().add(conta811);
				if(conta812.getCondominio() > 0)
					ptCapa8.getLista().add(conta812);
				if(conta813.getCondominio() > 0)
					ptCapa8.getLista().add(conta813);
				if(conta814.getCondominio() > 0)
					ptCapa8.getLista().add(conta814);
				if(conta815.getCondominio() > 0)
					ptCapa8.getLista().add(conta815);
				if(conta816.getCondominio() > 0)
					ptCapa8.getLista().add(conta816);
				if(conta817.getCondominio() > 0)
					ptCapa8.getLista().add(conta817);
				if(conta818.getCondominio() > 0)
					ptCapa8.getLista().add(conta818);
				if(conta819.getCondominio() > 0)
					ptCapa8.getLista().add(conta819);
				if(conta820.getCondominio() > 0)
					ptCapa8.getLista().add(conta820);
				if(conta821.getCondominio() > 0)
					ptCapa8.getLista().add(conta821);
				if(conta822.getCondominio() > 0)
					ptCapa8.getLista().add(conta822);
				if(conta823.getCondominio() > 0)
					ptCapa8.getLista().add(conta823);
				if(conta824.getCondominio() > 0)
					ptCapa8.getLista().add(conta824);
				if(conta825.getCondominio() > 0)
					ptCapa8.getLista().add(conta825);
				if(conta826.getCondominio() > 0)
					ptCapa8.getLista().add(conta826);
				if(conta827.getCondominio() > 0)
					ptCapa8.getLista().add(conta827);
				if(conta828.getCondominio() > 0)
					ptCapa8.getLista().add(conta828);
				if(conta829.getCondominio() > 0)
					ptCapa8.getLista().add(conta829);
				if(conta830.getCondominio() > 0)
					ptCapa8.getLista().add(conta830);
				if(conta831.getCondominio() > 0)
					ptCapa8.getLista().add(conta831);
				if(conta832.getCondominio() > 0)
					ptCapa8.getLista().add(conta832);
				if(conta833.getCondominio() > 0)
					ptCapa8.getLista().add(conta833);
				if(conta834.getCondominio() > 0)
					ptCapa8.getLista().add(conta834);
				if(conta835.getCondominio() > 0)
					ptCapa8.getLista().add(conta835);
				if(conta836.getCondominio() > 0)
					ptCapa8.getLista().add(conta836);
				if(conta837.getCondominio() > 0)
					ptCapa8.getLista().add(conta837);
				
				if(ptCapa8.getNomeCapa() != null){
					this.teams2.add(ptCapa8);
				}
				
				} else {
					this.periodo1 = "";this.periodo2 = "";this.periodo3 = "";this.periodo4 = "";this.periodo5 = "";this.periodo6 = "";
					this.periodo7 = "";this.periodo8 = "";this.periodo9 = "";this.periodo10 = "";this.periodo11 = "";this.periodo12 = "";
					this.mediaGeral = "";
					this.mes1 = "";this.mes2 = "";this.mes3 = "";this.mes4 = "";this.mes5 = "";this.mes6 = "";
					this.mes7 = "";this.mes8 = "";this.mes9 = "";this.mes10 = "";this.mes11 = "";this.mes12 = "";
				}
			}
		}
	}
	
	public double retornaMes(intra_previsao_orcamentaria ipo, int mesProjecao){
		DateTime dt = new DateTime(ipo.getMesProjecao());
		double valor = 0;
		switch (dt.plusMonths(mesProjecao).getMonthOfYear()) {
		case 1:	valor = ipo.getMesJaneiro();break;
		case 2:	valor = ipo.getMesFevereiro();break;
		case 3:	valor = ipo.getMesMarco();break;
		case 4:	valor = ipo.getMesAbril();break;
		case 5:	valor = ipo.getMesMaio();break;
		case 6:	valor = ipo.getMesJunho();break;
		case 7:	valor = ipo.getMesJulho();break;
		case 8: valor = ipo.getMesAgosto();break;
		case 9:	valor = ipo.getMesSetembro();break;
		case 10:valor = ipo.getMesOutubro();break;
		case 11:valor = ipo.getMesNovembro();break;
		case 12:valor = ipo.getMesDezembro();break;
		}
		return valor;
	}
	
	public void alterarPrevisao(){
		if(this.ipoAlterBEAN != null){
			if(this.ipoAlterBEAN.getCodigo() > 0){
				this.previsaoDAO = new PrevisaoDAO();
				
				this.ipoAlterBEAN.setMedia((
						this.ipoAlterBEAN.getMesJaneiro()
						+this.ipoAlterBEAN.getMesFevereiro() 
						+this.ipoAlterBEAN.getMesMarco() 
						+this.ipoAlterBEAN.getMesAbril()
						+this.ipoAlterBEAN.getMesMaio()
						+this.ipoAlterBEAN.getMesJunho()
						+this.ipoAlterBEAN.getMesJulho()
						+this.ipoAlterBEAN.getMesAgosto()
						+this.ipoAlterBEAN.getMesSetembro()
						+this.ipoAlterBEAN.getMesOutubro()
						+this.ipoAlterBEAN.getMesNovembro()
						+this.ipoAlterBEAN.getMesDezembro())/12);
				
				this.previsaoDAO.alterarPrevisao(this.ipoAlterBEAN);
				this.ipoAlterBEAN = new intra_previsao_orcamentaria();
				this.carregar();
				this.carregaListaAlterar();
				this.msgAlterado();
			}else{
				this.msgSelecione();
			}
		}else{
			this.msgSelecione();
		}
	}
	
	public void excluirPrevisao(){
		if(this.ipoAlterBEAN != null){
			if(this.ipoAlterBEAN.getCodigo() > 0){
				this.previsaoDAO = new PrevisaoDAO();
					this.previsaoDAO.excluirPrevisao(this.ipoAlterBEAN.getCodigo());
				this.carregar();
				this.carregaListaAlterar();
				this.msgAlterado();
			}else{
				this.msgSelecione();
			}
		}else{
			this.msgSelecione();
		}
	}
	
	public void imprimir() throws Exception{
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		List<PrevisaoImprimir> lista = new ArrayList<>();
		List<PrevisaoImprimir> listaGrafico = new ArrayList<>();
		String nome = "";
		if(!this.listaDePrevisao.isEmpty()){
			this.previsaoDAO = new PrevisaoDAO();
			lista = this.previsaoDAO.listarPrevisaoImprimir(this.icBEAN.getCodigo());
			listaGrafico = this.previsaoDAO.listarPrevisaoImprimirGrafico(this.icBEAN.getCodigo());
		
		HashMap<Object, Object> parametros = new HashMap<>();
			
			parametros.put("mes1", this.mes1);
			parametros.put("mes2", this.mes2);
			parametros.put("mes3", this.mes3);
			parametros.put("mes4", this.mes4);
			parametros.put("mes5", this.mes5);
			parametros.put("mes6", this.mes6);
			parametros.put("mes7", this.mes7);
			parametros.put("mes8", this.mes8);
			parametros.put("mes9", this.mes9);
			parametros.put("mes10", this.mes10);
			parametros.put("mes11", this.mes11);
			parametros.put("mes12", this.mes12);
		if(this.inadi){
			nome = "previsaoInadimplente";
		}else{
			nome = "previsao";
		}
		this.nomeArquivoPrevisao = oncapture(rju.geraRelPrevisaoOrcamentaria(parametros, nome, nome, 1, lista));
		if(this.inadi){
			nome = "previsaoInadimplenteMedia";
		}else{
			nome = "previsaoMedia";
		}
			this.nomeArquivoMedia = oncapture(rju.geraRelPrevisaoOrcamentaria(parametros, nome, nome, 1, lista));
			this.nomeArquivoGrafico = oncapture(rju.geraRelPrevisaoOrcamentaria(parametros, "graficos", "graficos", 1, listaGrafico));
		}
	}
	
	public String oncapture(byte[] relatorio) {
		String rel = getRandomImageName();
		this.adv.add(rel);
		String newFileName;
		if (relatorio != null) {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			newFileName = servletContext.getRealPath("") + File.separator
					+ "relatorios" + File.separator + rel + ".pdf";
			FileOutputStream imageOutput;
			try {
				imageOutput = new FileOutputStream(new File(newFileName));
				imageOutput.write(relatorio);
				imageOutput.close();
			} catch (Exception e) {
				throw new FacesException("Erro na Converção!");
			}
		} else {
			newFileName = "relatoryweek";
			rel = newFileName;
		}
		return rel;
	}
	
	public void voltar(){
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null,
				"projetarPrevisao.xhtml?faces-redirect=true");
	}
	
	/**
	 * Metodo radom para gerar o relatório
	 */
	private String getRandomImageName() {
		int i = (int) (Math.random() * 10000000);
		return String.valueOf(i);
	}
	
	public void salvarVerbas(){
		if(this.icBEAN.getCodigo() > 0){
			if(this.tipoDeRateio.trim().equals("")){
			this.prDAO = new PrevisaoRateioDAO();
			this.iprBEAN.setCondominio(this.icBEAN.getCodigo());
			this.prDAO.salvarVerbas(this.iprBEAN);
			this.iprBEAN = new intra_previsao_rateio();
			this.msgSalvo();
			this.listaDeRateio = null;
			}else{
				this.msgSelecioneRateio();
			}
		}else{
			this.msgSelecioneRateio();
		}
	}
	
	public void atualizaTelaPrevisao(){
		this.carregar();
		this.carregaListaAlterar();
	}
	
	public void listarTipoRateio(){
		switch (this.tipoDeRateio) {
		case "A":
			this.nomeTipoRateio = "Apartamento";
			break;
		case "B":
			this.nomeTipoRateio = "Apartamento + Garagem";
			break;
		case "C":
			this.nomeTipoRateio = "Apartamento + Conjunto";
			break;
		case "D":
			this.nomeTipoRateio = "Apartamento + Conjunto + Garagem";
			break;
		case "E":
			this.nomeTipoRateio = "Apartamento + Loja";
			break;
		case "F":
			this.nomeTipoRateio = "Apartamento + Loja + Garagem";
			break;
		case "G":
			this.nomeTipoRateio = "Apartamento + Conjunto + Loja";
			break;
		case "H":
			this.nomeTipoRateio = "Apartamento + Conjunto + Loja + Garagem";
			break;
		case "I":
			this.nomeTipoRateio = "Por Unidade";
			break;
		case "J":
			this.nomeTipoRateio = "Conjunto + Garagem";
			break;
		case "K":
			this.nomeTipoRateio = "Conjunto + Loja";
			break;
		case "L":
			this.nomeTipoRateio = "Conjunto + Loja + Garagem";
			break;
		case "M":
			this.nomeTipoRateio = "Loja";
			break;
		case "N":
			this.nomeTipoRateio = "Loja + Garagem";
			break;
		case "O":
			this.nomeTipoRateio = "Outros/Extras";
			break;
		case "P":
			this.nomeTipoRateio = "Garagem";
			break;
		case "Q":
			this.nomeTipoRateio = "Conjunto";
			break;
		case "R":
			this.nomeTipoRateio = "Unidade ( - ) VGE*";
			break;
		case "S":
			this.nomeTipoRateio = "Apartamentos";
			break;
		case "T":
			this.nomeTipoRateio = "Conjuntos";
			break;
		case "U":
			this.nomeTipoRateio = "Lojas";
			break;
		case "V":
			this.nomeTipoRateio = "Garagem";
			break;
		case "W":
			this.nomeTipoRateio = "VGE (S)";
			break;
		case "X":
			this.nomeTipoRateio = "Correio";
			break;
		}
	}
}
