package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.joda.time.DateTime;

import br.com.oma.intranet.dao.LISTAR_BL_UNI_DAO;
import br.com.oma.intranet.dao.ProcuracoesJuridicoDAO;
import br.com.oma.intranet.entidades.intra_cndunidade;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_proc_jur_cob;
import br.com.oma.intranet.entidades.intra_procuracao_juridico;
import br.com.oma.intranet.entidades.intra_requisicao_protocolo;
import br.com.oma.intranet.filters.ConexaoGeral;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import br.com.oma.sigadm.entidades.intra_cndpruni;

@ManagedBean(name = "prjMB")
@ViewScoped
public class ProcuracoesJudiricoMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 528102740692093652L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private ProcuracoesJuridicoDAO pjDAO;
	private LISTAR_BL_UNI_DAO cndUnidaDAO;
	private intra_condominios icBEAN = new intra_condominios();
	private intra_grupo_gerente gerenteBEAN = new intra_grupo_gerente();
	private intra_cndunidade cndUnidaBEAN = new intra_cndunidade();
	private intra_cndpruni cndPruniBEAN = new intra_cndpruni();
	private intra_requisicao_protocolo rpBEAN = new intra_requisicao_protocolo();
	private intra_proc_jur_cob idSelecionado;
	private intra_proc_jur_cob pjcBEAN = new intra_proc_jur_cob();

	// ATRIBUTOS
	private List<intra_grupo_gerente> listaDeGerentes;
	private List<intra_condominios> listaDeCondominio, listaCondominios2;
	private List<intra_cndunidade> listaDeBloco, listaDeUnidade;
	private List<intra_procuracao_juridico> listaProcuracao, fltrProcuracao, listaExtrato, listarProcuracaoSelecionada;
	private List<intra_cndpruni> listaProntuario;
	private List<intra_proc_jur_cob> listaTabela;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	private int condominio;
	private String bloco;
	private String unidade;
	private String clienteNome;
	private String nomeCondo;
	private String pesquisarPor = "Condominio";
	private int sequencia;
	private String titulo;
	private int tipo;
	private Date dataInclusao;
	private String tipoPront;
	private String descricao;
	private int naoPublic;
	private Date dataInicial, dataFinal, dataInicial2;

	// GET X SET
	public ProcuracoesJuridicoDAO getPjDAO() {
		return pjDAO;
	}

	public void setPjDAO(ProcuracoesJuridicoDAO pjDAO) {
		this.pjDAO = pjDAO;
	}

	public LISTAR_BL_UNI_DAO getCndUnidaDAO() {
		return cndUnidaDAO;
	}

	public void setCndUnidaDAO(LISTAR_BL_UNI_DAO cndUnidaDAO) {
		this.cndUnidaDAO = cndUnidaDAO;
	}

	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
	}

	public intra_grupo_gerente getGerenteBEAN() {
		return gerenteBEAN;
	}

	public void setGerenteBEAN(intra_grupo_gerente gerenteBEAN) {
		this.gerenteBEAN = gerenteBEAN;
	}

	public intra_cndunidade getCndUnidaBEAN() {
		return cndUnidaBEAN;
	}

	public void setCndUnidaBEAN(intra_cndunidade cndUnidaBEAN) {
		this.cndUnidaBEAN = cndUnidaBEAN;
	}

	public intra_cndpruni getCndPruniBEAN() {
		return cndPruniBEAN;
	}

	public void setCndPruniBEAN(intra_cndpruni cndPruniBEAN) {
		this.cndPruniBEAN = cndPruniBEAN;
	}

	public intra_requisicao_protocolo getRpBEAN() {
		return rpBEAN;
	}

	public void setRpBEAN(intra_requisicao_protocolo rpBEAN) {
		this.rpBEAN = rpBEAN;
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

	public List<intra_condominios> getListaDeCondominio()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.gerenteBEAN.getCodigo() > 0) {
			this.pjDAO = new ProcuracoesJuridicoDAO();
			this.listaDeCondominio = this.pjDAO.listarCondominios(this.gerenteBEAN.getCodigo());
		}
		return listaDeCondominio;
	}

	public void setListaDeCondominio(List<intra_condominios> listaDeCondominio) {
		this.listaDeCondominio = listaDeCondominio;
	}

	public List<intra_condominios> getListaCondominios2() {
		if (this.listaCondominios2 == null) {
			ProcuracoesJuridicoDAO dao;
			try {
				dao = new ProcuracoesJuridicoDAO();
				if (this.listaCondominios2 == null) {
					if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
						if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
							this.listaCondominios2 = dao.getListaCondominios();
						} else {
							if (this.gerenteBEAN != null) {
								this.listaCondominios2 = dao.listarCondominios(this.gerenteBEAN.getCodigo());
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
		return listaCondominios2;
	}

	public void setListaCondominios2(List<intra_condominios> listaCondominios2) {
		this.listaCondominios2 = listaCondominios2;
	}

	public List<intra_cndunidade> getListaDeBloco() {
		if (this.condominio > 0) {
			this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
			this.listaDeBloco = null;
			if (this.rpBEAN != null) {
				this.rpBEAN.setAosCuidados("");
			}
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

	public List<intra_procuracao_juridico> getListaProcuracao() {
		return listaProcuracao;
	}

	public void setListaProcuracao(List<intra_procuracao_juridico> listaProcuracao) {
		this.listaProcuracao = listaProcuracao;
	}

	public List<intra_procuracao_juridico> getListarProcuracaoSelecionada() {
		return listarProcuracaoSelecionada;
	}

	public void setListarProcuracaoSelecionada(List<intra_procuracao_juridico> listarProcuracaoSelecionada) {
		this.listarProcuracaoSelecionada = listarProcuracaoSelecionada;
	}

	public List<intra_procuracao_juridico> getFltrProcuracao() {
		return fltrProcuracao;
	}

	public void setFltrProcuracao(List<intra_procuracao_juridico> fltrProcuracao) {
		this.fltrProcuracao = fltrProcuracao;
	}

	public List<intra_procuracao_juridico> getListaExtrato()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0) {
			ProcuracoesJuridicoDAO dao = new ProcuracoesJuridicoDAO();
			try {
				if (this.bloco == null && this.unidade == null) {
					this.listaExtrato = dao.pesquisarCotasAberto(this.condominio);
				} else if (this.bloco != null && this.unidade == null) {
					this.listaExtrato = dao.pesquisarCotasAberto2(this.condominio, this.bloco);
				} else {
					this.listaExtrato = dao.pesquisarCotasAberto3(this.condominio, this.bloco, this.unidade);
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}

		}
		return listaExtrato;
	}

	public void setListaExtrato(List<intra_procuracao_juridico> listaExtrato) {
		this.listaExtrato = listaExtrato;
	}

	public List<intra_cndpruni> getListaProntuario() {
		return listaProntuario;
	}

	public void setListaProntuario(List<intra_cndpruni> listaProntuario) {
		this.listaProntuario = listaProntuario;
	}

	public intra_proc_jur_cob getPjcBEAN() {
		return pjcBEAN;
	}

	public void setPjcBEAN(intra_proc_jur_cob pjcBEAN) {
		this.pjcBEAN = pjcBEAN;
	}

	public intra_proc_jur_cob getIdSelecionado() {
		return idSelecionado;
	}

	public void setIdSelecionado(intra_proc_jur_cob idSelecionado) {
		this.idSelecionado = idSelecionado;
	}

	public List<intra_proc_jur_cob> getListaTabela()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.listaTabela == null) {
			this.pjDAO = new ProcuracoesJuridicoDAO();
			this.listaTabela = pjDAO.listarTabela();
		}
		return listaTabela;
	}

	public void setListaTabela(List<intra_proc_jur_cob> listaTabela) {
		this.listaTabela = listaTabela;
	}

	public static int getDefaultBufferSize() {
		return DEFAULT_BUFFER_SIZE;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
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

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
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

	public int getSequencia() {
		return sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getTipoPront() {
		return tipoPront;
	}

	public void setTipoPront(String tipoPront) {
		this.tipoPront = tipoPront;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNaoPublic() {
		return naoPublic;
	}

	public void setNaoPublic(int naoPublic) {
		this.naoPublic = naoPublic;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataInicial2() {
		return dataInicial2;
	}

	public void setDataInicial2(Date dataInicial2) {
		this.dataInicial2 = dataInicial2;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public ProcuracoesJudiricoMB() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		int dia = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		int dia1 = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int mes1 = (cal.get(Calendar.MONDAY) + 1);
		int ano1 = cal.get(Calendar.YEAR);
		try {
			this.dataInicial = (new SimpleDateFormat("dd/MM/yyyy")).parse(01 + "/" + 05 + "/" + 2016);
			this.dataInicial2 = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + mes1 + "/" + ano1);
			this.dataFinal = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia1 + "/" + mes1 + "/" + ano1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	// MÉTODOS

	// ↓ MÉTODO PARA RETORNAR GERENTE ↓

	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				this.listaDeCondominio = null;
				this.listaDeBloco = null;
				this.listaDeUnidade = null;
				this.nomeCondo = "";
				this.gerenteBEAN.setCodigo(this.sessaoMB.getListaDeGerente().get(0).getCodigo());
				return this.sessaoMB.getListaDeGerente();
			} else {
				this.listaDeCondominio = null;
				this.listaDeBloco = null;
				this.listaDeUnidade = null;
				this.nomeCondo = "";
				this.gerenteBEAN.setCodigo(this.sessaoMB.getUsuario().getGrupoGer().get(0).getCodigo());
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}

	// ↓ MÉTODO PARA RETORNAR NOME CONDOMÍNIO ↓

	public void retornaNomeCondominio()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
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
			}
		}
	}

	// ↓ MÉTODO PARA RETORNAR NOME CLIENTE ↓

	@SuppressWarnings("static-access")
	public void nomeCliente() {
		for (intra_cndunidade uni : listaDeUnidade) {
			if (uni.getBloco().equals(this.bloco) & uni.getUnidade().equals(this.unidade)) {
				if (this.rpBEAN == null) {
					this.rpBEAN = new intra_requisicao_protocolo();
				}
				this.setClienteNome(this.trataNomeComposto(uni.getNome()).toUpperCase());
			}
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

	// ↓ MÉTODO PARA LIMPAR TABELA EXTRATO - GERENTE SELECIONADO ↓

	public void limparListas() {
		this.condominio = 0;
		this.listaDeGerentes = null;
		this.listaDeCondominio = null;
		this.listaCondominios2 = null;
		this.listaDeBloco = null;
		this.listaDeUnidade = null;
		this.listaExtrato = null;
		this.nomeCondo = "";
		this.bloco = null;
		this.unidade = null;
		this.clienteNome = null;
		this.icBEAN = new intra_condominios();
		this.pjcBEAN = new intra_proc_jur_cob();
	}

	// ↓ MÉTODO PARA LIMPAR TABELA EXTRATO - CONDOMÍNIO SELECIONADO ↓

	public void limpar() {
		this.listaDeBloco = null;
		this.listaDeUnidade = null;
		this.listaExtrato = null;
		this.nomeCondo = "";
		this.bloco = null;
		this.unidade = null;
		this.clienteNome = null;
	}

	// ↓ MÉTODO PARA LIMPAR PAINEL PRONTUÁRIO ↓

	public void limparProntuario() {
		this.dataInclusao = null;
		this.tipoPront = null;
		this.descricao = null;
		this.tipo = 0;
		this.titulo = null;
		this.sequencia = 0;
		this.cndPruniBEAN = new intra_cndpruni();
	}

	// ↓ MÉTODO PARA FILTRO LISTA PROCURACAO ↓

	public void filtrarListaProcuracao() {
		this.listaProcuracao = null;
		this.fltrProcuracao = null;
	}

	// ↓ MÉTODO PARA PESQUISAR COTAS EM ABERTO ↓

	public void pesquisarCotasAberto() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		List<intra_condominios> listaCndCarteira = null;
		ProcuracoesJuridicoDAO dao = new ProcuracoesJuridicoDAO();
		try {
			if (this.pesquisarPor.equals("Condominio") && this.icBEAN == null
					|| this.pesquisarPor.equals("Condominio") && this.icBEAN.getCodigo() == 0) {
				throw new Exception("Selecione um condomínio para pesquisar!");
			}
			if (this.pesquisarPor.equals("Carteira") && this.gerenteBEAN == null) {
				throw new Exception("Selecione um gerente para pesquisar!");
			}
			if (this.pesquisarPor.equals("Unidade") && this.unidade == null
					|| this.pesquisarPor.equals("Unidade") && this.bloco == null) {
				throw new Exception("Selecione uma unidade para pesquisar!");
			}
			switch (this.pesquisarPor) {
			case "Condominio":
				List<intra_condominios> listaCnd = new ArrayList<intra_condominios>();
				listaCnd.add(icBEAN);
				this.listaProcuracao = dao.unidadesDevedoras(listaCnd, this.dataInicial);
				break;
			case "Carteira":
				listaCndCarteira = dao.listarCondominios(this.gerenteBEAN.getCodigo());
				this.listaProcuracao = dao.unidadesDevedoras(listaCndCarteira, this.dataInicial);
				break;
			case "Todos":
				listaCndCarteira = dao.getListaCondominios();
				this.listaProcuracao = dao.unidadesDevedoras(listaCndCarteira, this.dataInicial);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA LISTAR PAINEL PRONTUÁRIO ↓

	public void listarProntuario() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0 && this.bloco != null && this.unidade != null) {
			this.pjDAO = new ProcuracoesJuridicoDAO();
			this.cndPruniBEAN = new intra_cndpruni();
			this.cndPruniBEAN = this.pjDAO.listarProntuarioJ(this.condominio, this.bloco, this.unidade);
			this.sequencia = this.cndPruniBEAN.getSequencia();
			this.titulo = this.cndPruniBEAN.getTitulo();
			this.tipo = this.cndPruniBEAN.getTipo();
			this.descricao = this.cndPruniBEAN.getDescricao();
			this.tipoPront = this.cndPruniBEAN.getTipo_pront();
			this.dataInclusao = this.cndPruniBEAN.getData_inclusao();
		}
	}

	// ↓ MÉTODO PARA SALVAR INFORMAÇÃO NO PRONTUÁRIO - SIGADM ↓

	public void salvarInfProntuario() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0) {

			this.pjDAO = new ProcuracoesJuridicoDAO();
			this.cndPruniBEAN.setCondominio(this.condominio);
			this.cndPruniBEAN.setBloco(this.bloco);
			this.cndPruniBEAN.setUnidade(this.unidade);
			this.cndPruniBEAN.setData_inclusao(this.dataInclusao);
			this.cndPruniBEAN.setDescricao(this.descricao);
			this.cndPruniBEAN.setNao_public(this.naoPublic);
			this.cndPruniBEAN.setTitulo(this.titulo);
			this.cndPruniBEAN.setTipo(this.tipo);
			this.cndPruniBEAN.setTipo_pront("J");
			this.cndPruniBEAN.setSequencia(this.sequencia);

			this.pjDAO.adicionarInfProntuario(this.cndPruniBEAN, this.sessaoMB);
			this.msgSalvo();
		}
	}

	// ↓ MÉTODO PARA ALTERAR INFORMAÇÃO PRONTUÁRIO - SIGADM ↓

	public void alterarInfProntuario() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.condominio > 0) {

			this.pjDAO = new ProcuracoesJuridicoDAO();
			this.cndPruniBEAN.setCondominio(this.condominio);
			this.cndPruniBEAN.setBloco(this.bloco);
			this.cndPruniBEAN.setUnidade(this.unidade);
			this.cndPruniBEAN.setData_inclusao(this.dataInclusao);
			this.cndPruniBEAN.setDescricao(this.descricao);
			this.cndPruniBEAN.setNao_public(this.naoPublic);
			this.cndPruniBEAN.setTitulo(this.titulo);
			this.cndPruniBEAN.setTipo(this.tipo);
			this.cndPruniBEAN.setTipo_pront("J");
			this.cndPruniBEAN.setSequencia(this.sequencia);

			this.pjDAO.alterarInfProntuario(this.cndPruniBEAN, this.sessaoMB);
			this.msgAlterado();
		}
	}

	// ↓ ENVIAR E-MAIL JURÍDICO ↓

	private Config conf = new Config(new ConexaoGeral().getEmail());

	public void enviaProtocoloCobr(intra_proc_jur_cob pjc, String solicitante) throws EmailException {

		try {
			HtmlEmail html = new HtmlEmail();
			html.setSmtpPort(Integer.valueOf(this.conf.Conf().getProperty("jdbc.server.email.porta")));
			html.setAuthentication(this.conf.Conf().getProperty("jdbc.server.email.user"),
					this.conf.Conf().getProperty("jdbc.server.email.password"));
			html.setDebug(false);
			html.setHostName(this.conf.Conf().getProperty("jdbc.server.email.host"));
			html.setFrom("cobranca@oma.com.br");
			html.setFrom(this.sessaoMB.getUsuario().getEmail());
			html.setSubject("Pedido de Procuração");

			StringBuilder mensagem = new StringBuilder("<head></head>" + "<body><h3> Prezados, <br/>"
					+ "Por gentileza, providenciar o envio de Procuração. </h3><br/>");
			mensagem = mensagem
					.append("Gerente: " + pjc.getNomeGerente() + "<br/>" + "Condomínio: " + pjc.getCodigoCondominio()
							+ " - " + pjc.getNomeCondominio() + "<br/>" + "Unidade: Bl. " + pjc.getBloco() + " - Unid. "
							+ pjc.getUnidade() + "<br/><br/>" + "Atenciosamente, <br/>" + solicitante);
			mensagem = mensagem.append(" </body></head></html>");
			String msg = mensagem.toString();
			html.setHtmlMsg(msg);

			html.addTo("kelly@oma.com.br");
			html.addTo("juridico1@oma.com.br");
			html.addTo("helen@hdmconsultoria.com.br");

			// html.setTLS(false);
			html.send();
		} catch (EmailException e) {
			System.out.println(e);
		}
	}

	// ↓ MÉTODO PARA SALVAR INFORMAÇÃO NA TABELA ↓

	public void salvarTabela() {
		try {
			for (intra_procuracao_juridico pj : this.listarProcuracaoSelecionada) {
				ProcuracoesJuridicoDAO dao = new ProcuracoesJuridicoDAO();
				intra_proc_jur_cob pjcBEAN = new intra_proc_jur_cob();
				pjcBEAN.setCodigoGerente(pj.getCodigoGerente());
				pjcBEAN.setNomeGerente(pj.getNomeGerente());
				pjcBEAN.setCodigoCondominio(pj.getCodigoCondominio());
				pjcBEAN.setNomeCondominio(pj.getNomeCondominio());
				pjcBEAN.setBloco(pj.getBloco());
				pjcBEAN.setUnidade(pj.getUnidade());
				pjcBEAN.setPedProcuracao(new Date());
				dao.salvarInformacao(pjcBEAN);
				this.enviaProtocoloCobr(pjcBEAN, this.sessaoMB.getUsuario().getNome());
			}

			this.limparListas();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA ALTERAR INFORMAÇÃO NA TABELA ↓

	public void alterarTabela() {
		try {
			ProcuracoesJuridicoDAO dao = new ProcuracoesJuridicoDAO();
			this.pjcBEAN.setBloco(this.pjcBEAN.getBloco());
			this.pjcBEAN.setUnidade(this.pjcBEAN.getUnidade());
			if (this.pjcBEAN.getId() != 0) {
				this.pjcBEAN.setCodigoCondominio(this.pjcBEAN.getCodigoCondominio());
				this.pjcBEAN.setNomeCondominio(this.pjcBEAN.getNomeCondominio());
				this.pjcBEAN.setCodigoGerente(this.pjcBEAN.getCodigoGerente());
				this.pjcBEAN.setNomeGerente(this.pjcBEAN.getNomeGerente());
				this.pjcBEAN.setParcelasAtrasadas(this.pjcBEAN.getParcelasAtrasadas());
				this.pjcBEAN.setPedProcuracao(this.pjcBEAN.getPedProcuracao());
				this.pjcBEAN.setEnvProcuracao(this.pjcBEAN.getEnvProcuracao());
				this.pjcBEAN.setRetornoSindico(this.pjcBEAN.getRetornoSindico());
				this.pjcBEAN.setStatus(this.pjcBEAN.getStatus());
				this.pjcBEAN.setObservacao(this.pjcBEAN.getObservacao());
				this.pjcBEAN.setSolicitacaoCRI(this.pjcBEAN.getSolicitacaoCRI());
				this.pjcBEAN.setRetiradaCRI(this.pjcBEAN.getRetiradaCRI());
				this.pjcBEAN.setCompraVenda(this.pjcBEAN.getCompraVenda());
				this.pjcBEAN.setDataIniciais(this.pjcBEAN.getDataIniciais());
			}
			dao.salvarInformacao(this.pjcBEAN);
			this.limparListas();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA ALTERAR INFORMAÇÃO TABELA ↓

	public void abreAlteraInformacao() {
		try {
			if (this.idSelecionado != null) {
				String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(caminho
						+ "/juridico/procuracoes/alterar-tabela.xhtml?idInformacao=" + this.idSelecionado.getId());
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA PESQUISAR ID DA TABELA ↓

	public void pesquisaIdTabela() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String id = params.get("idInformacao");
			if (id != null) {
				ProcuracoesJuridicoDAO dao = new ProcuracoesJuridicoDAO();
				this.pjcBEAN = dao.pesquisaIdPorCodigo(Integer.parseInt(id));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA FILTRAR POR DATA ↓

	public void pesquisarPorData() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		ProcuracoesJuridicoDAO dao = new ProcuracoesJuridicoDAO();
		try {
			if (this.dataInicial == null || this.dataFinal == null) {
				throw new Exception("Insira datas de início e fim para pesquisar!");
			} else {
				this.dataFinal = new DateTime(this.dataFinal).withHourOfDay(23).withMinuteOfHour(59).toDate();
			}
			List<intra_condominios> listaCnd = new ArrayList<intra_condominios>();
			listaCnd.add(icBEAN);
			this.listaTabela = dao.listarTabela2(listaCnd, this.dataInicial, this.dataFinal);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODOS PARA IMPRESSÃO ↓

	public void gerarRelatorio() throws Exception {
		HashMap<Object, Object> parametros = new HashMap<>();
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		parametros.put("dataInicial", this.dataInicial);
		parametros.put("dataFinal", this.dataFinal);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		this.limpar();
		this.limparListas();
		this.downloadPDF(rju.geraRelSiga(parametros, "Procuracao-Juridico", "Procuracao-Juridico", 1));
	}

	public void downloadPDF(byte[] retorno) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			input = new BufferedInputStream(new ByteArrayInputStream(retorno), DEFAULT_BUFFER_SIZE);
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(retorno.length));
			response.setHeader("Content-Disposition", "inline;filename=\"Procuracao-Juridico.pdf\"");
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();
		} finally {
			close(output);
			close(input);
		}
		facesContext.responseComplete();
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}