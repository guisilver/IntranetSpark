package br.com.oma.intranet.managedbeans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.RelatorioMensalDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_relatorio_mensal;
import br.com.oma.intranet.entidades.intra_taxa_bancaria;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ManagedBean(name = "rmMB")
@ViewScoped
public class RelatorioMensalMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3148964189799013021L;

	// DEPENDENCIA

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS

	private RelatorioMensalDAO rmDAO;
	private intra_condominios icBean = new intra_condominios();
	private intra_grupo_gerente gerenteMB = new intra_grupo_gerente();
	private intra_relatorio_mensal rmBEAN = new intra_relatorio_mensal();
	private intra_taxa_bancaria tbBEAN = new intra_taxa_bancaria();
	private StreamedContent stream, stream2;

	// ATRIBUTOS

	private List<intra_condominios> listaDeCondominio;
	private List<intra_grupo_gerente> listaDeGerentes;
	private List<intra_relatorio_mensal> listaRelMensal, fltrRelatorio;
	private List<intra_taxa_bancaria> listaTaxaBanc;
	private Date dataInicio;
	private Date dataFim;
	private String pesquisarPor = "Condominio";

	public RelatorioMensalMB() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		int dia = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		int dia1 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes1 = (cal.get(Calendar.MONDAY) + 1);
		int ano1 = cal.get(Calendar.YEAR);
		try {
			this.dataInicio = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + mes1 + "/" + ano1);
			this.dataFim = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia1 + "/" + mes1 + "/" + ano1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// GET X SET

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public RelatorioMensalDAO getRmDAO() {
		return rmDAO;
	}

	public void setRmDAO(RelatorioMensalDAO rmDAO) {
		this.rmDAO = rmDAO;
	}

	public intra_condominios getIcBean() {
		return icBean;
	}

	public void setIcBean(intra_condominios icBean) {
		this.icBean = icBean;
	}

	public intra_grupo_gerente getGerenteMB() {
		return gerenteMB;
	}

	public void setGerenteMB(intra_grupo_gerente gerenteMB) {
		this.gerenteMB = gerenteMB;
	}

	public intra_relatorio_mensal getRmBEAN() {
		return rmBEAN;
	}

	public void setRmBEAN(intra_relatorio_mensal rmBEAN) {
		this.rmBEAN = rmBEAN;
	}

	public List<intra_condominios> getListaDeCondominio() {
		if (this.listaDeCondominio == null) {
			RelatorioMensalDAO dao;
			try {
				dao = new RelatorioMensalDAO();
				if (this.listaDeCondominio == null) {
					if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
						if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
							this.listaDeCondominio = dao.getListaCondominios();
						} else {
							if (this.gerenteMB != null) {
								this.listaDeCondominio = dao.getListaCondominios(this.gerenteMB.getCodigo());
							}
						}
					}
				}
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
		return this.listaDeCondominio;
	}

	public void setListaDeCondominio(List<intra_condominios> listaDeCondominio) {
		this.listaDeCondominio = listaDeCondominio;
	}

	public List<intra_grupo_gerente> getListaDeGerentes()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.listaDeGerentes == null) {
			this.listaDeGerentes = this.retornaGerentes();
		}
		return listaDeGerentes;
	}

	public void setListaDeGerentes(List<intra_grupo_gerente> listaDeGerentes) {
		this.listaDeGerentes = listaDeGerentes;
	}

	public List<intra_relatorio_mensal> getListaRelMensal() {
		return listaRelMensal;
	}

	public void setListaRelMensal(List<intra_relatorio_mensal> listaRelMensal) {
		this.listaRelMensal = listaRelMensal;
	}

	public List<intra_relatorio_mensal> getFltrRelatorio() {
		return fltrRelatorio;
	}

	public void setFltrRelatorio(List<intra_relatorio_mensal> fltrRelatorio) {
		this.fltrRelatorio = fltrRelatorio;
	}

	public intra_taxa_bancaria getTbBEAN() {
		return tbBEAN;
	}

	public void setTbBEAN(intra_taxa_bancaria tbBEAN) {
		this.tbBEAN = tbBEAN;
	}

	public List<intra_taxa_bancaria> getListaTaxaBanc() {
		return listaTaxaBanc;
	}

	public void setListaTaxaBanc(List<intra_taxa_bancaria> listaTaxaBanc) {
		this.listaTaxaBanc = listaTaxaBanc;
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

	public String getPesquisarPor() {
		return pesquisarPor;
	}

	public void setPesquisarPor(String pesquisarPor) {
		this.pesquisarPor = pesquisarPor;
	}

	public StreamedContent getStream() {
		try {
			this.gerarelatorioExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	public StreamedContent getStream2() {
		try {
			this.gerarelatorioExcelBanc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream2;
	}

	public void setStream2(StreamedContent stream2) {
		this.stream2 = stream2;
	}

	// MÉTODOS

	// ↓ MÉTODO PARA LIMPAR LISTAS ↓
	public void limparListas() {
		this.listaDeCondominio = null;
		this.listaDeGerentes = null;
	}

	// ↓ MÉTODO PARA LISTAR OS CONDOMÍNIOS ↓

	public void listarCondominios() {
		try {
			RelatorioMensalDAO dao = new RelatorioMensalDAO();
			if (this.gerenteMB != null) {
				if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
					this.listaDeCondominio = dao.getListaCondominios();
				} else {
					this.listaDeCondominio = dao.getListaCondominios(this.gerenteMB.getCodigo());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA RETORNAR O CÓDIGO E NOME DO GERENTE ↓

	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				this.gerenteMB.setCodigo(this.sessaoMB.getListaDeGerente().get(0).getCodigo());
				return this.sessaoMB.getListaDeGerente();
			} else {
				if (this.sessaoMB.getUsuario().getGrupoGer() != null) {
					this.gerenteMB = this.sessaoMB.getUsuario().getGrupoGer().get(0);
					this.listarCondominios();
				}
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}

	// ↓ MÉTODO PARA RENDERIZAR MENU GERENTE ↓

	public boolean getRenderizaMenuGerente() {
		if (this.sessaoMB.getUsuario().getEmail() != null) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				return false;
			} else if (this.sessaoMB.verificaDepto(" Todos")) {
				return false;
			} else if (this.pesquisarPor.equals("Condominio")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// ↓ MÉTODO PARA PESQUISAR RELATÓRIO MENSAL ↓

	public void pesquisarRelatorioMensal()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		List<intra_condominios> listaCndCarteira = null;
		RelatorioMensalDAO dao = new RelatorioMensalDAO();
		try {
			if (this.pesquisarPor.equals("Condominio") && this.icBean == null
					|| this.pesquisarPor.equals("Condominio") && this.icBean.getCodigo() == 0) {
				throw new Exception("Selecione um condomínio para pesquisar!");
			}
			if (this.pesquisarPor.equals("Carteira") && this.gerenteMB == null) {
				throw new Exception("Selecione um gerente para pesquisar!");
			}
			if (this.dataInicio == null || this.dataFim == null) {
				throw new Exception("Insira datas de início e fim para pesquisar!");
			} else {
				this.dataFim = new DateTime(this.dataFim).withHourOfDay(23).withMinuteOfHour(59).toDate();
			}
			switch (this.pesquisarPor) {
			case "Condominio":
				List<intra_condominios> listaCnd = new ArrayList<intra_condominios>();
				listaCnd.add(icBean);
				this.listaRelMensal = dao.pesquisaRelatorioMensal(listaCnd, this.dataInicio, this.dataFim);
				break;
			case "Carteira":
				listaCndCarteira = dao.getListaCondominios(this.gerenteMB.getCodigo());
				this.listaRelMensal = dao.pesquisaRelatorioMensal(listaCndCarteira, this.dataInicio, this.dataFim);
				break;
			case "Todos":
				listaCndCarteira = dao.getListaCondominios();
				this.listaRelMensal = dao.pesquisaRelatorioMensal(listaCndCarteira, this.dataInicio, this.dataFim);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA PESQUISAR AS TAXAS BANCARIAS ↓

	public void pesquisarTaxasBancarias()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		RelatorioMensalDAO dao = new RelatorioMensalDAO();
		try {
			if (this.dataInicio == null || this.dataFim == null) {
				throw new Exception("Insira datas de início e fim para pesquisar!");
			} else {
				this.dataFim = new DateTime(this.dataFim).withHourOfDay(23).withMinuteOfHour(59).toDate();
			}
			this.listaTaxaBanc = dao.pesquisarTaxaBancaria(this.dataInicio, this.dataFim);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODOS PARA IMPRESSÃO ↓

	public void gerarelatorioExcel() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		parametros.put("dataInicio", this.dataInicio);
		parametros.put("dataFim", this.dataFim);
		rju.geraRel2(parametros, "Relatorio-Mensal", "Relatorio-Mensal", 2);

		this.stream = rju.getStream();
	}

	public void gerarelatorioExcelBanc() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		parametros.put("dataInicio", this.dataInicio);
		parametros.put("dataFim", this.dataFim);
		rju.geraRel2(parametros, "Taxa-Bancaria", "Taxa-Bancaria", 2);

		this.stream2 = rju.getStream();
	}

	public String taxaBancaria() {
		double total = 0;
		if (this.listaTaxaBanc != null) {
			for (intra_taxa_bancaria t : this.listaTaxaBanc) {
				total += t.getValor();
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String taxaAdministrativa() {
		double total = 0;
		if (this.listaRelMensal != null) {
			for (intra_relatorio_mensal r : this.listaRelMensal) {
				total += r.getTaxaAdm();
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}
}
