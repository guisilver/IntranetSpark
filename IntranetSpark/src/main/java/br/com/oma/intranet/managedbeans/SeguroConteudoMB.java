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
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.dao.ProcuracoesJuridicoDAO;
import br.com.oma.intranet.dao.SeguroConteudoDAO;
import br.com.oma.intranet.entidades.SeguroConteudo;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;

@ManagedBean(name = "scMB")
@ViewScoped
public class SeguroConteudoMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4261479329240916740L;

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private SeguroConteudoDAO scDAO;
	private intra_condominios condominio = new intra_condominios();
	private intra_grupo_gerente gerente = new intra_grupo_gerente();
	private List<intra_grupo_gerente> listaGerentes;
	private List<intra_condominios> listaCondominios;
	private List<SeguroConteudo> listaSeguro, fltrSeguro;
	private String pesquisarPor = "Todos";
	private Date dataInicial;
	private Date dataFinal;
	private int mes;
	private int ano;
	private double valor;
	private int servico;
	private int classificacao;
	private int condominioE;
	private StreamedContent stream;

	public SeguroConteudoMB() {
		this.mes = new DateTime().getMonthOfYear();
		this.ano = new DateTime().getYear();

		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		int dia = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		int dia1 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes1 = (cal.get(Calendar.MONDAY) + 1);
		int ano1 = cal.get(Calendar.YEAR);
		try {
			this.dataInicial = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + mes1 + "/" + ano1);
			this.dataFinal = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia1 + "/" + mes1 + "/" + ano1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// GET X SET

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public SeguroConteudoDAO getScDAO() {
		return scDAO;
	}

	public void setScDAO(SeguroConteudoDAO scDAO) {
		this.scDAO = scDAO;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public intra_grupo_gerente getGerente() {
		return gerente;
	}

	public void setGerente(intra_grupo_gerente gerente) {
		this.gerente = gerente;
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
		if (this.listaCondominios == null) {
			SeguroConteudoDAO dao;
			try {
				dao = new SeguroConteudoDAO();
				if (this.listaCondominios == null) {
					if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
						if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
							this.listaCondominios = dao.getListaCondominios();
						} else {
							if (this.gerente != null) {
								this.listaCondominios = dao.listarCondominios(this.gerente.getCodigo());
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

	public List<SeguroConteudo> getListaSeguro() {
		return listaSeguro;
	}

	public void setListaSeguro(List<SeguroConteudo> listaSeguro) {
		this.listaSeguro = listaSeguro;
	}

	public List<SeguroConteudo> getFltrSeguro() {
		return fltrSeguro;
	}

	public void setFltrSeguro(List<SeguroConteudo> fltrSeguro) {
		this.fltrSeguro = fltrSeguro;
	}

	public String getPesquisarPor() {
		return pesquisarPor;
	}

	public void setPesquisarPor(String pesquisarPor) {
		this.pesquisarPor = pesquisarPor;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getServico() {
		return servico;
	}

	public void setServico(int servico) {
		this.servico = servico;
	}

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}

	public int getCondominioE() {
		return condominioE;
	}

	public void setCondominioE(int condominioE) {
		this.condominioE = condominioE;
	}

	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	public void limparListas() {
		this.listaGerentes = null;
		this.listaCondominios = null;
	}

	// MÉTODOS

	// ↓ MÉTODOS PARA RETORNAR OS GERENTES ↓

	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				this.gerente.setCodigo(this.sessaoMB.getListaDeGerente().get(0).getCodigo());
				return this.sessaoMB.getListaDeGerente();
			} else {
				if (this.sessaoMB.getUsuario().getGrupoGer() != null) {
					this.gerente = this.sessaoMB.getUsuario().getGrupoGer().get(0);
					this.listarCondominios();
				}
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}

	// ↓ MÉTODO PARA LISTAR OS CONDOMÍNIOS ↓

	public void listarCondominios() {
		try {
			ProcuracoesJuridicoDAO dao = new ProcuracoesJuridicoDAO();
			if (this.gerente != null) {
				if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
					this.listaCondominios = dao.getListaCondominios();
				} else {
					this.listaCondominios = dao.listarCondominios(this.gerente.getCodigo());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA RENDERIZAR O MENU DO GERENTE ↓

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

	// ↓ MÉTODO PARA PESQUISAR SEGURO CONDÔMINO ↓

	public void pesquisarSeguroConteudoCondomino()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		List<intra_condominios> listaCndCarteira = null;
		SeguroConteudoDAO dao = new SeguroConteudoDAO();
		try {
			if (this.pesquisarPor.equals("Condominio") && this.condominio == null
					|| this.pesquisarPor.equals("Condominio") && this.condominio.getCodigo() == 0) {
				throw new Exception("Selecione um condomínio para pesquisar!");
			}
			if (this.pesquisarPor.equals("Carteira") && this.gerente == null) {
				throw new Exception("Selecione um gerente para pesquisar!");
			}
			if (this.dataInicial == null || this.dataFinal == null) {
				throw new Exception("Insira datas de início e fim para pesquisar!");
			} else {
				this.dataFinal = new DateTime(this.dataFinal).withHourOfDay(23).withMinuteOfHour(59).toDate();
			}
			switch (this.pesquisarPor) {
			case "Condominio":
				List<intra_condominios> listaCnd = new ArrayList<intra_condominios>();
				listaCnd.add(condominio);
				this.listaSeguro = dao.listarSeguroCondomino(this.dataInicial, this.dataFinal, listaCnd);
				break;
			case "Carteira":
				listaCndCarteira = dao.listarCondominios(this.gerente.getCodigo());
				this.listaSeguro = dao.listarSeguroCondomino(this.dataInicial, this.dataFinal, listaCndCarteira);
				break;
			case "Todos":
				listaCndCarteira = dao.getListaCondominios();
				if (this.servico != 0 && this.classificacao != 0) {
					this.listaSeguro = dao.listarSeguroCondomino3(this.dataInicial, this.dataFinal, listaCndCarteira,
							this.servico, this.classificacao);
				} else if (this.servico != 0 && this.classificacao == 0) {
					this.listaSeguro = dao.listarSeguroCondomino2(this.dataInicial, this.dataFinal, listaCndCarteira,
							this.servico);
				} else {
					this.listaSeguro = dao.listarSeguroCondomino(this.dataInicial, this.dataFinal, listaCndCarteira);
				}
				break;
			default:
				break;
			}
			dao.fechaConexao();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA PESQUISAR SEGURO 8888 ↓

	public void pesquisarSeguroConteudo8888()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		SeguroConteudoDAO dao = new SeguroConteudoDAO();
		try {
			if (this.pesquisarPor.equals("Valor") && this.valor == 0) {
				throw new Exception("Selecione um serviço para pesquisar!");
			}
			if (this.dataInicial == null || this.dataFinal == null) {
				throw new Exception("Insira datas de início e fim para pesquisar!");
			} else {
				this.dataFinal = new DateTime(this.dataFinal).withHourOfDay(23).withMinuteOfHour(59).toDate();
			}
			switch (this.pesquisarPor) {
			case "Todos":
				this.listaSeguro = dao.listaSeguro8888(this.dataInicial, this.dataFinal);
				break;
			case "Valor":
				this.listaSeguro = dao.listaSeguro88882(this.dataInicial, this.dataFinal, this.valor);
				break;
			default:
				break;
			}
			dao.fechaConexao();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA PESQUISAR SEGURO LOCAÇÃO ↓

	public void pesquisarSeguroConteudoLocacao()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		SeguroConteudoDAO dao = new SeguroConteudoDAO();
		try {
			if (this.mes == 0 || this.ano == 0) {
				throw new Exception("Insira o mês e o ano para pesquisar!");
			} else {
				Calendar cal = Calendar.getInstance();
				this.ano = cal.get(Calendar.YEAR);
			}
			this.listaSeguro = dao.listaSeguroLocacao(this.mes, this.ano);
			dao.fechaConexao();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA PESQUISAR SEGURO EMBUTIDO ↓

	public void pesquisarSeguroConteudoEmbutido()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		SeguroConteudoDAO dao = new SeguroConteudoDAO();
		try {
			if (this.pesquisarPor.equals("CondominioE") && this.condominioE == 0) {
				throw new Exception("Selecione um condomínio para pesquisar!");
			}
			switch (this.pesquisarPor) {
			case "Todos":
				this.listaSeguro = dao.listaSeguroEmbutido();
				break;
			case "CondominioE":
				this.listaSeguro = dao.listaSeguroEmbutido2(this.condominioE);
				break;
			default:
				break;
			}
			dao.fechaConexao();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// MÉTODOS PARA CALCULAR QUANTIDADE E VALOR (POR SERVIÇO E CLASSIFICAÇÃO)

	public int tipoSeguro8() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					total += 1;
				}
			}
		}
		return total;
	}

	public int tipoSeguro81() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					if (s.getClassificacao() == 1) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro82() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					if (s.getClassificacao() == 2) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro83() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					if (s.getClassificacao() == 3) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro899() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					if (s.getClassificacao() == 99) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro9() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					total += 1;
				}
			}
		}
		return total;
	}

	public int tipoSeguro91() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					if (s.getClassificacao() == 1) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro92() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					if (s.getClassificacao() == 2) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro93() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					if (s.getClassificacao() == 3) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro999() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					if (s.getClassificacao() == 99) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro10() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					total += 1;
				}
			}
		}
		return total;
	}

	public int tipoSeguro101() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					if (s.getClassificacao() == 1) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro102() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					if (s.getClassificacao() == 2) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro103() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					if (s.getClassificacao() == 3) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro1099() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					if (s.getClassificacao() == 99) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro11() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					total += 1;
				}
			}
		}
		return total;
	}

	public int tipoSeguro111() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					if (s.getClassificacao() == 1) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro112() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					if (s.getClassificacao() == 2) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro113() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					if (s.getClassificacao() == 3) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	public int tipoSeguro1199() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					if (s.getClassificacao() == 99) {
						total += 1;
					}
				}
			}
		}
		return total;
	}

	@SuppressWarnings("unused")
	public int tipoSeguro() {
		int total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				total += 1;
			}
		}
		return total;
	}

	public String valorSeguro8() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					total += s.getValor_recbto();
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro81() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					if (s.getClassificacao() == 1) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro82() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					if (s.getClassificacao() == 2) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro83() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					if (s.getClassificacao() == 3) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro899() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 8) {
					if (s.getClassificacao() == 99) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro9() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					total += s.getValor_recbto();
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro91() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					if (s.getClassificacao() == 1) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro92() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					if (s.getClassificacao() == 2) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro93() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					if (s.getClassificacao() == 3) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro999() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 9) {
					if (s.getClassificacao() == 99) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro10() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					total += s.getValor_recbto();
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro101() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					if (s.getClassificacao() == 1) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro102() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					if (s.getClassificacao() == 2) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro103() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					if (s.getClassificacao() == 3) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro1099() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 10) {
					if (s.getClassificacao() == 99) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro11() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					total += s.getValor_recbto();
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro111() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					if (s.getClassificacao() == 1) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro112() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					if (s.getClassificacao() == 2) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro113() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					if (s.getClassificacao() == 3) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro1199() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				if (s.getServico() == 11) {
					if (s.getClassificacao() == 99) {
						total += s.getValor_recbto();
					}
				}
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}

	public String valorSeguro() {
		double total = 0;
		if (this.listaSeguro != null) {
			for (SeguroConteudo s : this.listaSeguro) {
				total += s.getValor_recbto();
			}
		}
		return new DecimalFormat("###,###.###").format(total);
	}
	
}