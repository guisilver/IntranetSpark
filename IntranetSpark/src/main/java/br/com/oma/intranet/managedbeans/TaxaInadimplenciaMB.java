package br.com.oma.intranet.managedbeans;

import java.io.FileNotFoundException;
import java.io.IOException;
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

import br.com.oma.intranet.dao.TaxaInadimplenciaDAO;
import br.com.oma.intranet.entidades.TaxaInadimplencia;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;

@ViewScoped
@ManagedBean
public class TaxaInadimplenciaMB {

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private intra_condominios condominio = new intra_condominios();
	private Date dataInicio;
	private Date dataFim;
	private int mesInicio, anoInicio, mesFim, anoFim;
	private List<TaxaInadimplencia> listaTaxas;
	private List<intra_condominios> listaCondominios;
	private List<intra_grupo_gerente> listaGerentes;
	private String pesquisarPor = "Condominio";
	private boolean agruparPorMes;

	public TaxaInadimplenciaMB() {
		this.mesInicio = new DateTime().minusMonths(3).getMonthOfYear();
		this.anoInicio = new DateTime().minusMonths(3).getYear();
		this.mesFim = new DateTime().getMonthOfYear();
		this.anoFim = new DateTime().getYear();
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

	public List<TaxaInadimplencia> getListaTaxas() {
		return listaTaxas;
	}

	public void setListaTaxas(List<TaxaInadimplencia> listaTaxas) {
		this.listaTaxas = listaTaxas;
	}

	public List<intra_condominios> getListaCondominios() {
		if (this.listaCondominios == null) {
			TaxaInadimplenciaDAO dao;
			try {
				dao = new TaxaInadimplenciaDAO();
				if (this.listaCondominios == null) {
					if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
						if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
							this.listaCondominios = dao.getListaCondominios();
						} else {
							if (this.sessaoMB.getGerenteSelecionado() != null) {
								this.listaCondominios = dao
										.getListaCondominios(this.sessaoMB.getGerenteSelecionado().getCodigo());
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
		return this.listaCondominios;
	}

	public void setListaCondominios(List<intra_condominios> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public List<intra_grupo_gerente> getListaGerentes()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.listaGerentes == null) {
			this.listaGerentes = this.retornaGerentes();
		}
		return listaGerentes;
	}

	public void setListaGerentes(List<intra_grupo_gerente> listaGerentes) {
		this.listaGerentes = listaGerentes;
	}

	public String getPesquisarPor() {
		return pesquisarPor;
	}

	public void setPesquisarPor(String pesquisarPor) {
		this.pesquisarPor = pesquisarPor;
	}

	public boolean isAgruparPorMes() {
		return agruparPorMes;
	}

	public void setAgruparPorMes(boolean agruparPorMes) {
		this.agruparPorMes = agruparPorMes;
	}

	public void limparListas() {
		this.listaGerentes = null;
		this.listaCondominios = null;
	}

	public void pesquisaTaxaInadimplencia()
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
		List<intra_condominios> listaCndCarteira = null;
		TaxaInadimplenciaDAO dao = new TaxaInadimplenciaDAO();
		try {
			if (this.pesquisarPor.equals("Condominio") && this.condominio == null
					|| this.pesquisarPor.equals("Condominio") && this.condominio.getCodigo() == 0) {
				throw new Exception("Selecione um condomínio para pesquisar!");
			}
			if (this.pesquisarPor.equals("Carteira") && this.sessaoMB.getGerenteSelecionado() == null) {
				throw new Exception("Selecione um gerente para pesquisar!");
			}
			this.dataInicio = new DateTime().withMonthOfYear(this.mesInicio).withYear(this.anoInicio).toDate();
			this.dataFim = new DateTime().withMonthOfYear(this.mesFim).withYear(this.anoFim).toDate();
			switch (this.pesquisarPor) {
			case "Condominio":
				List<intra_condominios> listaCnd = new ArrayList<intra_condominios>();
				listaCnd.add(condominio);
				this.listaTaxas = dao.pesquisaTaxaTotal(listaCnd, dataInicio, dataFim);
				break;
			case "Carteira":
				if (this.agruparPorMes) {
					listaCndCarteira = dao.getListaCondominios(this.sessaoMB.getGerenteSelecionado().getCodigo());
					this.listaTaxas = dao.pesquisaTaxaTotal(listaCndCarteira, dataInicio, dataFim);
					this.calcularRelatorioResumido(this.dataInicio, this.dataFim);
				} else {
					listaCndCarteira = dao.getListaCondominios(this.sessaoMB.getGerenteSelecionado().getCodigo());
					this.listaTaxas = dao.pesquisaTaxaTotal(listaCndCarteira, dataInicio, dataFim);
				}
				break;
			case "Todos":
				if (this.agruparPorMes) {
					listaCndCarteira = dao.getListaCondominios();
					this.listaTaxas = dao.pesquisaTaxaTotal(listaCndCarteira, this.dataInicio, this.dataFim);
					this.calcularRelatorioResumido(this.dataInicio, this.dataFim);
				} else {
					listaCndCarteira = dao.getListaCondominios();
					this.listaTaxas = dao.pesquisaTaxaTotal(listaCndCarteira, this.dataInicio, this.dataFim);
				}
				break;
			default:
				break;
			}
			dao.fechaConexao();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			e.printStackTrace();
		}
	}

	public void listarCondominios() {
		try {
			TaxaInadimplenciaDAO dao = new TaxaInadimplenciaDAO();
			if (this.sessaoMB.getGerenteSelecionado() != null) {
				if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
					this.listaCondominios = dao.getListaCondominios();
				} else {
					this.listaCondominios = dao.getListaCondominios(this.sessaoMB.getGerenteSelecionado().getCodigo());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// METODOS
	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				if (this.sessaoMB.getGerenteSelecionado() == null) {
					this.sessaoMB.setGerenteSelecionado(this.sessaoMB.getListaDeGerente().get(0));
				}
				return this.sessaoMB.getListaDeGerente();
			} else {
				if (this.sessaoMB.getUsuario().getGrupoGer() != null) {
					if (this.sessaoMB.getGerenteSelecionado() == null) {
						this.sessaoMB.setGerenteSelecionado(this.sessaoMB.getUsuario().getGrupoGer().get(0));
					}
					this.listarCondominios();
				}
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}

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

	public String nomeMes(int numeroMes) {
		switch (numeroMes) {
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Março";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";
		default:
			return null;
		}
	}

	public void calcularRelatorioResumido(Date dataInicio, Date dataFim) {
		int qtdMeses = Months.monthsBetween(new DateTime(dataInicio), new DateTime(dataFim)).getMonths();
		TaxaInadimplencia taxaFinal = null;
		List<TaxaInadimplencia> listaTaxas = new ArrayList<>();
		int mes = new DateTime(dataInicio).getMonthOfYear();
		int ano = new DateTime(dataInicio).getYear();
		for (int i = 0; i <= qtdMeses; i++) {
			taxaFinal = new TaxaInadimplencia();
			for (TaxaInadimplencia aux1 : this.listaTaxas) {
				if (aux1.getMesVencimento() == mes && aux1.getAnoVencimento() == ano) {
					taxaFinal.setAnoVencimento(aux1.getAnoVencimento());
					taxaFinal.setMesVencimento(aux1.getMesVencimento());
					taxaFinal.setRecibo(taxaFinal.getRecibo() + aux1.getRecibo());
					taxaFinal.setReciboValor(taxaFinal.getReciboValor() + aux1.getReciboValor());
					taxaFinal.setReciboPago(taxaFinal.getReciboPago() + aux1.getReciboPago());
					taxaFinal.setReciboValorPago(taxaFinal.getReciboValorPago() + aux1.getReciboValorPago());
				}
			}
			taxaFinal.setInadimplenciaQtd(taxaFinal.getRecibo() - taxaFinal.getReciboPago());
			taxaFinal.setInadimplenciaValor(taxaFinal.getReciboValor() - taxaFinal.getReciboValorPago());
			double taxaQtd = (taxaFinal.getInadimplenciaQtd() / taxaFinal.getRecibo()) * 100;
			double taxaValor = ((taxaFinal.getReciboValor() - taxaFinal.getReciboValorPago())
					/ taxaFinal.getReciboValor()) * 100;
			taxaFinal.setPorcentagemQtd(taxaQtd);
			taxaFinal.setPorcentagemValor(taxaValor);
			listaTaxas.add(taxaFinal);
			mes++;
			if (mes > 12) {
				ano++;
				mes = 1;
			}
		}
		this.listaTaxas = listaTaxas;
	}
}