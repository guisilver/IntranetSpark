package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.LISTAR_BL_UNI_DAO;
import br.com.oma.intranet.dao.ProtocoloCondoDAO;
import br.com.oma.intranet.entidades.intra_advert_mult;
import br.com.oma.intranet.entidades.intra_cndunidade;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.sigadm.entidades.intra_endereco_unidade;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_requisicao_protocolo;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import br.com.oma.intranet.util.StringUtil;

@ManagedBean(name = "pcMB")
@ViewScoped
public class ProtocoloCondoMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = -570554563269773092L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private ProtocoloCondoDAO pcDAO;
	private LISTAR_BL_UNI_DAO cndUnidaDAO;
	private intra_condominios icBEAN = new intra_condominios();
	private intra_cndunidade cndUnidaBEAN = new intra_cndunidade();
	private intra_advert_mult amBEAN = new intra_advert_mult();
	private intra_requisicao_protocolo rpBEAN = new intra_requisicao_protocolo();
	private intra_requisicao_protocolo codigoSelecionado;
	private intra_endereco_unidade euBEAN = new intra_endereco_unidade();

	// ATRIBUTOS
	private List<intra_cndunidade> listaDeBloco, listaDeUnidade;
	private List<intra_requisicao_protocolo> listarProtocolo, listarProtocoloSelecionados, listaProtocolosGerente,
			filtroProtocolo;
	private List<intra_endereco_unidade> listaDeEndereco;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	private String codigo;
	private String nomeCondo;
	private String cabecalho;
	private String aosCuidados;
	private String endereco;
	private String diaMalote;
	private String nomeunidade;
	private String nomeArquivo;
	private double valor1;
	private int condominio;
	private String bloco;
	private String unidade;
	private String enderecoUnidade;
	private String impressao = "Pendente";

	// Protocolo de Entrega
	private String recebi;

	// GET X SET

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
	}

	public intra_advert_mult getAmBEAN() {
		return amBEAN;
	}

	public void setAmBEAN(intra_advert_mult amBEAN) {
		this.amBEAN = amBEAN;
	}

	public intra_requisicao_protocolo getRpBEAN() {
		return rpBEAN;
	}

	public void setRpBEAN(intra_requisicao_protocolo rpBEAN) {
		this.rpBEAN = rpBEAN;
	}

	public String getNomeCondo() {
		return nomeCondo;
	}

	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	public String getDiaMalote() {
		return diaMalote;
	}

	public void setDiaMalote(String diaMalote) {
		this.diaMalote = diaMalote;
	}

	public String getAosCuidados() {
		return aosCuidados;
	}

	public void setAosCuidados(String aosCuidados) {
		this.aosCuidados = aosCuidados;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<intra_cndunidade> getListaDeUnidade() {
		if (this.sessaoMB.getGerenteSelecionado() != null && this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
			if (this.bloco != null) {
				if (!this.bloco.trim().isEmpty()) {
					this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
					this.listaDeUnidade = null;
					this.listaDeUnidade = this.cndUnidaDAO.listaDeUnidade(this.condominio, this.bloco);
				}
			}
		}
		return listaDeUnidade;
	}

	public void setListaDeUnidade(List<intra_cndunidade> listaDeUnidade) {
		this.listaDeUnidade = listaDeUnidade;
	}

	public intra_cndunidade getCndUnidaBEAN() {
		return cndUnidaBEAN;
	}

	public void setCndUnidaBEAN(intra_cndunidade cndUnidaBEAN) {
		this.cndUnidaBEAN = cndUnidaBEAN;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
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

	public double getValor1() {
		return valor1;
	}

	public void setValor1(double valor1) {
		this.valor1 = valor1;
	}

	public List<intra_requisicao_protocolo> getListarProtocolo()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.listarProtocolo == null) {
			try {
				ProtocoloCondoDAO dao = new ProtocoloCondoDAO();
				this.listarProtocolo = new ArrayList<>();
				List<intra_requisicao_protocolo> resultList = dao.listarProtocolo();
				switch (this.impressao) {
				case "Todos":
					this.listarProtocolo = resultList;
					break;
				case "Pendente":
					for (intra_requisicao_protocolo protocolo : resultList) {
						if (protocolo.getImpressao() != null) {
							if (protocolo.getImpressao().equals("Pendente")) {
								this.listarProtocolo.add(protocolo);
							}
						}
					}
					break;
				case "Impressao":
					for (intra_requisicao_protocolo protocolo : resultList) {
						if (protocolo.getImpressao() != null) {
							if (protocolo.getImpressao().equals("Impresso")) {
								this.listarProtocolo.add(protocolo);
							}
						}
					}
					break;
				default:
					break;
				}
				this.filtroProtocolo = null;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listarProtocolo;
	}

	public void setListarProtocolo(List<intra_requisicao_protocolo> listarProtocolo) {
		this.listarProtocolo = listarProtocolo;
	}

	public List<intra_requisicao_protocolo> getListarProtocoloSelecionados() {
		return listarProtocoloSelecionados;
	}

	public void setListarProtocoloSelecionados(List<intra_requisicao_protocolo> listarProtocoloSelecionados) {
		this.listarProtocoloSelecionados = listarProtocoloSelecionados;
	}

	public List<intra_requisicao_protocolo> getListaProtocolosGerente()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.listaProtocolosGerente == null) {
			this.pcDAO = new ProtocoloCondoDAO();
			this.listaProtocolosGerente = this.pcDAO
					.listarProtocoloGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
		}
		return listaProtocolosGerente;
	}

	public void setListaProtocolosGerente(List<intra_requisicao_protocolo> listaProtocolosGerente) {
		this.listaProtocolosGerente = listaProtocolosGerente;
	}

	public List<intra_requisicao_protocolo> getFiltroProtocolo() {
		return filtroProtocolo;
	}

	public void setFiltroProtocolo(List<intra_requisicao_protocolo> filtroProtocolo) {
		this.filtroProtocolo = filtroProtocolo;
	}

	public String getRecebi() {
		return recebi;
	}

	public void setRecebi(String recebi) {
		this.recebi = recebi;
	}

	public String getNomeunidade() {
		return nomeunidade;
	}

	public void setNomeunidade(String nomeunidade) {
		this.nomeunidade = nomeunidade;
	}

	public intra_requisicao_protocolo getCodigoSelecionado() {
		return codigoSelecionado;
	}

	public void setCodigoSelecionado(intra_requisicao_protocolo codigoSelecionado) {
		this.codigoSelecionado = codigoSelecionado;
	}

	public String getImpressao() {
		return impressao;
	}

	public void setImpressao(String impressao) {
		this.impressao = impressao;
	}

	public intra_endereco_unidade getEuBEAN() {
		return euBEAN;
	}

	public void setEuBEAN(intra_endereco_unidade euBEAN) {
		this.euBEAN = euBEAN;
	}

	public List<intra_endereco_unidade> getListaDeEndereco() {
		return listaDeEndereco;
	}

	public void setListaDeEndereco(List<intra_endereco_unidade> listaDeEndereco) {
		this.listaDeEndereco = listaDeEndereco;
	}

	public String getEnderecoUnidade() {
		return enderecoUnidade;
	}

	public void setEnderecoUnidade(String enderecoUnidade) {
		this.enderecoUnidade = enderecoUnidade;
	}

	// ↓ MÉTODO PARA RETORNAR O CÓDIGO E O NOME DO CONDOMÍNIO ↓
	public void retornaNomeCondominio()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.pcDAO = new ProtocoloCondoDAO();
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
				this.icBEAN.setBairro(c.getBairro());
				this.icBEAN.setEndereco(c.getEndereco());
				this.icBEAN.setLogradouro(c.getLogradouro());
				this.icBEAN.setNro(c.getNro());
				this.pcDAO.getdiaMalote(this.condominio);
			}
		}
	}

	// ↓ MÉTODO PARA RETORNAR O NOME DA UNIDADE ↓

	@SuppressWarnings("static-access")
	public void nomeCliente() {
		for (intra_cndunidade uni : listaDeUnidade) {
			if (uni.getBloco().equals(this.bloco) & uni.getUnidade().equals(this.unidade)) {
				if (this.rpBEAN == null) {
					this.rpBEAN = new intra_requisicao_protocolo();
				}
				this.rpBEAN.setAosCuidados(this.trataNomeComposto(uni.getNome()).toUpperCase());
			}
		}
	}

	// ↓ MÉTODO PARA RETORNAR ENDEREÇO DA UNIDADE (CORREIO) ↓

	@SuppressWarnings("static-access")
	public String retornaEnderecoUnidade(intra_endereco_unidade e)
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.pcDAO = new ProtocoloCondoDAO();

		this.bloco = e.getBloco();
		this.unidade = e.getUnidade();
		this.nomeunidade = e.getNome();
		this.euBEAN.setBairro(e.getBairro());
		this.euBEAN.setComplemento(e.getComplemento());
		this.euBEAN.setEndereco(e.getEndereco());
		this.euBEAN.setLogradouro(e.getLogradouro());
		this.euBEAN.setNro_end(e.getNro_end());
		this.euBEAN.setTipo_corresp(e.getTipo_corresp());

		String endereco = this.euBEAN.getLogradouro() + " " + this.euBEAN.getEndereco() + " " + this.euBEAN.getNro_end()
				+ " - " + this.euBEAN.getBairro();
		StringUtil util = new StringUtil();
		endereco = util.trataNomeComposto(endereco);

		return endereco;
	}

	// ↓ MÉTODOS PARA LIMPAR AS LISTAS DA REQUISIÇÃO DE MALOTE ↓

	public void limparListas() {
		this.listaDeBloco = null;
		this.listaDeUnidade = null;
		this.cabecalho = null;
		this.aosCuidados = null;
		this.nomeCondo = null;
		this.recebi = null;
		this.condominio = 0;
		this.rpBEAN = new intra_requisicao_protocolo();
		this.icBEAN = new intra_condominios();
		this.amBEAN = new intra_advert_mult();
		this.valor1 = 0;
	}

	public void limpar() {
		this.listaDeBloco = null;
		this.listaDeUnidade = null;
		this.cabecalho = null;
		this.aosCuidados = null;
		this.nomeCondo = null;
		this.recebi = null;
		this.condominio = 0;
		this.rpBEAN = new intra_requisicao_protocolo();
		this.icBEAN = new intra_condominios();
		this.amBEAN = new intra_advert_mult();
		this.valor1 = 0;
	}

	// ↓ MÉTODO PARA FILTRAR A REQUISIÇÃO DE MALOTE PARA O DEPTO. CONDOMÍNIO ↓

	public void filtroProtocolo() {
		this.listarProtocolo = null;
		this.filtroProtocolo = null;
	}

	// ↓ MÉTODO PARA SELECIONAR A DUPLICAÇÃO DE REQUISIÇÃO DE MALOTE ↓

	public void duplicarProtocolo() {
		try {
			this.pesquisaRequisicaoSelecionada();
			ProtocoloCondoDAO dao = new ProtocoloCondoDAO();
			dao.detachProtocolo(this.rpBEAN);
			this.rpBEAN.setCodigo(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODOS PARA ABRIR ALTERAÇÃO E DUPLICAÇÃO DE REQUISIÇÃO DE MALOTE ↓

	public void abreDuplicaProtocolo() throws IOException {
		try {
			if (this.codigoSelecionado != null) {
				String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(caminho + "/condominio/protocolos/novo-protocolo.xhtml?codigoRequisicao="
								+ this.codigoSelecionado.getCodigo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abreAlteraRequisicao() throws IOException {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/condominio/protocolos/alterar-requisicao.xhtml?codigoRequisicao="
							+ this.codigoSelecionado.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA SELECIONAR O ENDEREÇO ↓

	@SuppressWarnings("static-access")
	public void selecionarLocal() {
		try {
			if (this.condominio != 0 && this.rpBEAN.getCorreio() != null) {
				switch (this.rpBEAN.getCorreio()) {
				case "Malote":
					this.pcDAO = new ProtocoloCondoDAO();
					this.rpBEAN.setDiaMalote(this.pcDAO.getdiaMalote(this.condominio));
					String endereco = this.icBEAN.getLogradouro() + " " + this.icBEAN.getEndereco() + " "
							+ this.icBEAN.getNro() + " - " + this.icBEAN.getBairro();
					StringUtil util = new StringUtil();
					endereco = util.trataNomeComposto(endereco);
					this.rpBEAN.setEndereco(endereco.toUpperCase());
					break;
				case "Simples":
					this.rpBEAN.setDiaMalote("");
					this.rpBEAN.setEndereco("");
					break;
				case "AR":
					this.pcDAO = new ProtocoloCondoDAO();
					this.rpBEAN.setDiaMalote("");
					intra_endereco_unidade eu = this.pcDAO.getEnderecoUnidade(this.condominio, this.bloco,
							this.unidade);
					this.rpBEAN.setEndereco(retornaEnderecoUnidade(eu));
					break;
				case "Sedex":
					this.rpBEAN.setDiaMalote("");
					this.rpBEAN.setEndereco("");
					break;
				case "OMA":
					this.rpBEAN.setEndereco("Rua Cincinato Braga, 204, Bela Vista".toUpperCase());
					break;
				case "endUnid":
					this.pcDAO = new ProtocoloCondoDAO();
					this.rpBEAN.setDiaMalote("");
					intra_endereco_unidade e = this.pcDAO.getEnderecoUnidade(this.condominio, this.bloco, this.unidade);
					this.rpBEAN.setEndereco(retornaEnderecoUnidade(e));
					break;
				default:
					this.rpBEAN.setEndereco(this.getEndereco().toUpperCase());
					break;
				}
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA SELECIONAR AOS CUIDADOS ↓

	public void selecionaAC() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.bloco = null;
		this.unidade = null;
		switch (this.rpBEAN.getTipoAC()) {
		case "Z":
			this.rpBEAN.setAosCuidados("Zelador");
			break;
		case "S":
			this.pcDAO = new ProtocoloCondoDAO();
			String nome = this.pcDAO.retornaNomeSindico(this.condominio);
			this.rpBEAN.setAosCuidados(nome);
			break;
		default:
			break;
		}
	}

	// ↓ MÉTODO PARA PESQUISAR REQUISIÇÃO DE PROTOCOLO ↓

	public void pesquisaRequisicaoSelecionada() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String codigoRequisicao = params.get("codigoRequisicao");
			if (codigoRequisicao != null) {
				ProtocoloCondoDAO dao = new ProtocoloCondoDAO();
				this.rpBEAN = dao.pesquisaRequisicaoPorCodigo(Integer.parseInt(codigoRequisicao));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODOS PARA SALVAR E ALTERAR REQUISIÇÃO DE PROTOCOLO ↓

	public void salvarProtocolo() {
		try {
			this.pcDAO = new ProtocoloCondoDAO();
			this.rpBEAN.setBloco(this.bloco);
			this.rpBEAN.setUnidade(this.unidade);
			if (this.rpBEAN.getCodigo() == 0) {
				this.rpBEAN.setcond_cod(this.icBEAN.getNome().toUpperCase());
				this.rpBEAN.setCodGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
				this.rpBEAN.setFeitoem(new Date());
				if (this.rpBEAN.getReferencia() != null) {
					this.rpBEAN.setReferencia(this.rpBEAN.getReferencia().toUpperCase());
				} else if (this.cabecalho != null) {
					this.rpBEAN.setReferencia(this.cabecalho.toUpperCase());
				} else {
					this.rpBEAN.setReferencia("");
				}
				this.rpBEAN.setCodPredio(this.condominio);
				this.rpBEAN.setEndereco(this.rpBEAN.getEndereco().toUpperCase());
				this.rpBEAN.setAosCuidados(this.rpBEAN.getAosCuidados().toUpperCase());
				this.rpBEAN.setImpressao("Pendente");
				for (intra_grupo_gerente g : this.sessaoMB.getListaDeGerente()) {
					if (g.getCodigo() == this.sessaoMB.getGerenteSelecionado().getCodigo()) {
						this.rpBEAN.setNomGerente(g.getNome().toUpperCase());
					}
				}
			}
			this.pcDAO.salvarProtocolo(this.rpBEAN);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Salvo! Nº protocolo: " + this.rpBEAN.getCodigo()));
			this.rpBEAN.clone();
			this.limpar();
			this.limparListas();
			DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent("tbvRP:frmHistorico:dtHistorico");
			table.setValue(null);
			RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
			this.rpBEAN = new intra_requisicao_protocolo();
			this.valor1 = 0;
			this.listaProtocolosGerente = null;
			this.listarProtocolo = null;
			this.listarProtocoloSelecionados = null;
			this.filtroProtocolo = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void salvarAltProtocolo() {
		try {
			this.pcDAO = new ProtocoloCondoDAO();
			if (this.rpBEAN.getCodigo() != 0) {
				this.rpBEAN.setcond_cod(this.rpBEAN.getcond_cod().toUpperCase());
				this.rpBEAN.setCodGerente(this.rpBEAN.getCodGerente());
				this.rpBEAN.setFeitoem(new Date());
				this.rpBEAN.setReferencia(this.rpBEAN.getReferencia().toUpperCase());
				this.rpBEAN.setCodPredio(this.rpBEAN.getCodPredio());
				this.rpBEAN.setAosCuidados(this.rpBEAN.getAosCuidados());
				this.rpBEAN.setImpressao("Pendente");
				this.rpBEAN.setNomGerente(this.rpBEAN.getNomGerente());
			}
			this.pcDAO.salvarProtocolo(this.rpBEAN);
			this.limpar();
			this.limparListas();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
			this.rpBEAN = new intra_requisicao_protocolo();
			this.valor1 = 0;
			this.listaProtocolosGerente = null;
			this.listarProtocolo = null;
			this.listarProtocoloSelecionados = null;
			this.filtroProtocolo = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}

	}

	// ↓ MÉTODOS PARA EXCLUIR UMA REQUISIÇÃO DE PROTOCOLO ↓

	public void excluir() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.codigoSelecionado != null) {
			this.pcDAO = new ProtocoloCondoDAO();

			intra_requisicao_protocolo requisicao = this.pcDAO
					.pesquisaRequisicaoPorCodigo(this.codigoSelecionado.getCodigo());

			this.pcDAO.excluir(requisicao, this.sessaoMB.getUsuario().getNome(), this.sessaoMB.getIpUser());
			RequestContext.getCurrentInstance().execute("PF('dlgExclusao').hide();");
			this.limpar();
			this.limparListas();
		}
	}

	// ↓ MÉTODOS PARA IMPRESSÃO DA REQUISIÇÃO DE MALOTE E PROTOCOLO DE ENTREGA ↓

	public void gerarRequisicao() throws Exception {
		String nome = "";
		nome = "Protocolo-Condominio2";
		HashMap<Object, Object> parametros = new HashMap<>();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		ProtocoloCondoDAO dao = new ProtocoloCondoDAO();

		for (intra_requisicao_protocolo rp : this.getListarProtocoloSelecionados()) {
			rp.setImpressao("Impresso");
			rp.setDataConferido(new Date());
			dao.salvarProtocolo(rp);
		}
		this.listarProtocolo = null;
		this.limpar();
		this.limparListas();
		this.downloadPDF(this.populaRelatorio(parametros, nome, nome, 1, listarProtocoloSelecionados));
	}

	public void gerarProtocolo() throws Exception {
		HashMap<Object, Object> parametros = new HashMap<>();
		String nome = "";
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		parametros.put("codigo", this.condominio);
		parametros.put("recebi", this.recebi);
		nome = "Protocolo-de-Entrega";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		this.limpar();
		this.limparListas();
		this.downloadPDF(rju.geraRelSiga(parametros, nome, nome, 1));
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
			response.setHeader("Content-Disposition", "inline;filename=\"ProtocoloRequisicao.pdf\"");
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] populaRelatorio(Map parametros, String nome_entrada, String nome_saida, int tipo,
			List<intra_requisicao_protocolo> lstProtocolo) throws Exception {
		@SuppressWarnings("unused")
		StreamedContent retorno = null;
		byte[] b;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Connection conex = Conexao.getConexaoSiga();
			String caminho_rel = context.getExternalContext().getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros,
					new JRBeanCollectionDataSource(lstProtocolo));
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case RelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter exporterPdf = new JRPdfExporter();
				extensao_exportada = "pdf";
				exporterPdf.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterPdf.exportReport();
				break;
			case RelatorioJasperUtil.RELATORIO_DOCX:
				JRDocxExporter exporterWord = new JRDocxExporter();
				extensao_exportada = "docx";
				exporterWord.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterWord.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterWord.exportReport();
				break;
			}
			retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
					"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
			conex.close();
		} catch (JRException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível gerar o relatorio." + e, ""));
			throw new Exception("Não foi possível gerar o relatorio.", e);
		} catch (FileNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível gerar o relatorio." + e, ""));
			throw new Exception("Arquivo do relatório não encontrado.", e);
		}
		return b;
	}

}
