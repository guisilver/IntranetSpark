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

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.LISTAR_BL_UNI_DAO;
import br.com.oma.intranet.dao.ProtocoloJurDAO;
import br.com.oma.intranet.entidades.intra_advert_mult;
import br.com.oma.intranet.entidades.intra_cndunidade;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_requisicao_juridico;
import br.com.oma.intranet.entidades.intra_usuario;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import br.com.oma.intranet.util.StringUtil;
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

@ManagedBean(name = "pjMB")
@ViewScoped
public class ProtocoloJurMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1971157112036990623L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private ProtocoloJurDAO pjDAO;
	private LISTAR_BL_UNI_DAO cndUnidaDAO;
	private intra_condominios icBEAN = new intra_condominios();
	private intra_cndunidade cndUnidaBEAN = new intra_cndunidade();
	private intra_requisicao_juridico rpBEAN = new intra_requisicao_juridico();
	private intra_advert_mult amBEAN = new intra_advert_mult();

	// ATRIBUTOS
	private intra_requisicao_juridico codigoSelecionado;
	private intra_usuario usuario;
	private List<intra_condominios> listaDeCondominio;
	private List<intra_cndunidade> listaDeBloco, listaDeUnidade;
	private List<intra_requisicao_juridico> listarProtocolo, listarProtocoloSelecionados, listaProtocolosGerente,
			filtroProtocolo;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10Kb
	private String codigo;
	private String nomeCondo;
	private String cabecalho;
	private String diaMalote;
	private int condominio;
	private String bloco;
	private String unidade;
	private String endereco;
	private String impressao = "Pendente";

	// GETTERS X SETTERS

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
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

	public intra_cndunidade getCndUnidaBEAN() {
		return cndUnidaBEAN;
	}

	public void setCndUnidaBEAN(intra_cndunidade cndUnidaBEAN) {
		this.cndUnidaBEAN = cndUnidaBEAN;
	}

	public intra_requisicao_juridico getRpBEAN() {
		return rpBEAN;
	}

	public void setRpBEAN(intra_requisicao_juridico rpBEAN) {
		this.rpBEAN = rpBEAN;
	}

	public intra_requisicao_juridico getCodigoSelecionado() {
		return codigoSelecionado;
	}

	public void setCodigoSelecionado(intra_requisicao_juridico codigoSelecionado) {
		this.codigoSelecionado = codigoSelecionado;
	}

	public intra_usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(intra_usuario usuario) {
		this.usuario = usuario;
	}

	public List<intra_condominios> getListaDeCondominio()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.sessaoMB.getGerenteSelecionado() != null && this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
			this.pjDAO = new ProtocoloJurDAO();
			this.listaDeCondominio = this.pjDAO.listarCondominios(this.sessaoMB.getGerenteSelecionado().getCodigo());
		}
		return listaDeCondominio;
	}

	public void setListaDeCondominio(List<intra_condominios> listaDeCondominio) {
		this.listaDeCondominio = listaDeCondominio;
	}

	public List<intra_cndunidade> getListaDeBloco() {
		if (this.condominio > 0) {
			this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
			this.listaDeBloco = null;
			if (this.rpBEAN != null) {
				this.rpBEAN.setSindico("");
			}
			this.listaDeBloco = this.cndUnidaDAO.listaDeBloco(this.condominio);
		}
		return listaDeBloco;
	}

	public void setListaDeBloco(List<intra_cndunidade> listaDeBloco) {
		this.listaDeBloco = listaDeBloco;
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

	public List<intra_requisicao_juridico> getListarProtocolo() {
		if (this.listarProtocolo == null) {
			try {
				ProtocoloJurDAO dao = new ProtocoloJurDAO();
				this.listarProtocolo = new ArrayList<>();
				List<intra_requisicao_juridico> resultList = dao.listarProtocolo();
				switch (this.impressao) {
				case "Todos":
					this.listarProtocolo = resultList;
					break;
				case "Pendente":
					for (intra_requisicao_juridico protocolo : resultList) {
						if (protocolo.getImpressao() != null) {
							if (protocolo.getImpressao().equals("Pendente")) {
								this.listarProtocolo.add(protocolo);
							}
						}
					}
					break;
				case "Impressao":
					for (intra_requisicao_juridico protocolo : resultList) {
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

	public void setListarProtocolo(List<intra_requisicao_juridico> listarProtocolo) {
		this.listarProtocolo = listarProtocolo;
	}

	public List<intra_requisicao_juridico> getListarProtocoloSelecionados() {
		return listarProtocoloSelecionados;
	}

	public void setListarProtocoloSelecionados(List<intra_requisicao_juridico> listarProtocoloSelecionados) {
		this.listarProtocoloSelecionados = listarProtocoloSelecionados;
	}

	public List<intra_requisicao_juridico> getListaProtocolosGerente()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.listaProtocolosGerente == null) {
			this.pjDAO = new ProtocoloJurDAO();
			this.listaProtocolosGerente = this.pjDAO
					.listarProtocoloGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
		}
		return listaProtocolosGerente;
	}

	public void setListaProtocolosGerente(List<intra_requisicao_juridico> listaProtocolosGerente) {
		this.listaProtocolosGerente = listaProtocolosGerente;
	}

	public List<intra_requisicao_juridico> getFiltroProtocolo() {
		return filtroProtocolo;
	}

	public void setFiltroProtocolo(List<intra_requisicao_juridico> filtroProtocolo) {
		this.filtroProtocolo = filtroProtocolo;
	}

	public intra_advert_mult getAmBEAN() {
		return amBEAN;
	}

	public void setAmBEAN(intra_advert_mult amBEAN) {
		this.amBEAN = amBEAN;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeCondo() {
		return nomeCondo;
	}

	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	public String getDiaMalote() {
		return diaMalote;
	}

	public void setDiaMalote(String diaMalote) {
		this.diaMalote = diaMalote;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getImpressao() {
		return impressao;
	}

	public void setImpressao(String impressao) {
		this.impressao = impressao;
	}

	// ↓ MÉTODO PARA RETORNAR O CÓDIGO E O NOME DO CONDOMÍNIO ↓
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
				this.icBEAN.setBairro(c.getBairro());
				this.icBEAN.setEndereco(c.getEndereco());
				this.icBEAN.setLogradouro(c.getLogradouro());
				this.icBEAN.setNro(c.getNro());

			}
		}
	}

	// ↓ MÉTODO PARA RETORNAR O NOME DA UNIDADE ↓

	@SuppressWarnings("static-access")
	public void nomeCliente() {
		for (intra_cndunidade uni : listaDeUnidade) {
			if (uni.getBloco().equals(this.bloco) & uni.getUnidade().equals(this.unidade)) {
				if (this.rpBEAN == null) {
					this.rpBEAN = new intra_requisicao_juridico();
				}
				this.rpBEAN.setSindico(this.trataNomeComposto(uni.getNome()).toUpperCase());
			}
		}
	}

	// ↓ MÉTODOS PARA LIMPAR AS LISTAS DA REQUISIÇÃO DE MALOTE ↓

	public void limparListas() {
		this.listaDeBloco = null;
		this.listaDeUnidade = null;
		this.cabecalho = null;
		this.nomeCondo = null;
		this.listaDeCondominio = null;
		this.condominio = 0;
		this.rpBEAN = new intra_requisicao_juridico();
		this.icBEAN = new intra_condominios();
	}

	public void limpar() {
		this.listaDeBloco = null;
		this.listaDeUnidade = null;
		this.cabecalho = null;
		this.nomeCondo = null;
		this.listaDeCondominio = null;
		this.condominio = 0;
		this.rpBEAN = new intra_requisicao_juridico();
		this.icBEAN = new intra_condominios();
	}

	// ↓ MÉTODO PARA FILTRAR A REQUISIÇÃO DE MALOTE PARA O DEPTO. CONDOMÍNIO ↓

	public void filtroProtocolo() {
		this.listarProtocolo = null;
		this.filtroProtocolo = null;
	}

	// ↓ MÉTODOS PARA ABRIR ALTERAÇÃO E DUPLICAÇÃO DE REQUISIÇÃO DE MALOTE ↓

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
					this.pjDAO = new ProtocoloJurDAO();
					this.rpBEAN.setDiaMalote(this.pjDAO.getdiaMalote(this.condominio));
					String endereco = this.icBEAN.getLogradouro() + " " + this.icBEAN.getEndereco() + " "
							+ this.icBEAN.getNro() + ", " + this.icBEAN.getBairro();
					StringUtil util = new StringUtil();
					endereco = util.trataNomeComposto(endereco);
					this.rpBEAN.setEndereco(endereco.toUpperCase());
					break;
				case "Simples":
					this.rpBEAN.setEndereco("");
					break;
				case "OMA":
					this.rpBEAN.setEndereco("Rua Cincinato Braga, 204, Bela Vista".toUpperCase());
					break;
				default:
					this.rpBEAN.setEndereco(this.getEndereco().toUpperCase());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA PESQUISAR REQUISIÇÃO DE PROTOCOLO ↓

	public void pesquisaRequisicaoSelecionada() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String codigoRequisicao = params.get("codigoRequisicao");
			if (codigoRequisicao != null) {
				ProtocoloJurDAO dao = new ProtocoloJurDAO();
				this.rpBEAN = dao.pesquisaRequisicaoPorCodigo(Integer.parseInt(codigoRequisicao));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ↓ MÉTODOS PARA SALVAR E ALTERAR REQUISIÇÃO DE PROTOCOLO ↓

	public void salvarProtocolo() {
		try {
			ProtocoloJurDAO dao = new ProtocoloJurDAO();
			if (this.rpBEAN.getCodigo() == 0) {
				this.rpBEAN.setCond_cod(this.icBEAN.getNome().toUpperCase());
				this.rpBEAN.setReferencia(this.cabecalho.toUpperCase());
				this.rpBEAN.setCodPredio(this.condominio);
				this.rpBEAN.setEndereco(this.rpBEAN.getEndereco().toUpperCase());
				this.rpBEAN.setSindico(this.rpBEAN.getSindico().toUpperCase());
				this.rpBEAN.setImpressao("Pendente");
				this.rpBEAN.setCodGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
			}
			this.rpBEAN.setNomJuridico(this.sessaoMB.getUsuario().getNome());
			this.rpBEAN.setNomJuridico_email(this.sessaoMB.getUsuario().getEmail());
			dao.salvarProtocolo(this.rpBEAN);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Salvo! Nº protocolo: " + this.rpBEAN.getCodigo()));
			this.limpar();
			this.limparListas();
			DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent("tbvRP:frmHistorico:dtHistorico");
			table.setValue(null);
			RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
			this.rpBEAN = new intra_requisicao_juridico();
			this.listaProtocolosGerente = null;
			this.listarProtocolo = null;
			this.listarProtocoloSelecionados = null;
			this.filtroProtocolo = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarAltProtocolo() {
		try {
			ProtocoloJurDAO dao = new ProtocoloJurDAO();
			if (this.rpBEAN.getCodigo() == 0) {
				this.rpBEAN.setCond_cod(this.icBEAN.getNome().toUpperCase());
				if (this.cabecalho != null) {
					this.rpBEAN.setReferencia(this.cabecalho.toUpperCase());
				}
				this.rpBEAN.setCodGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
				this.rpBEAN.setCodPredio(this.condominio);
				this.rpBEAN.setSindico(this.rpBEAN.getSindico().toUpperCase());
				this.rpBEAN.setImpressao("Pendente");

			}
			this.rpBEAN.setNomJuridico_email(this.sessaoMB.getUsuario().getEmail());
			this.rpBEAN.setNomJuridico(this.sessaoMB.getUsuario().getNome());
			dao.salvarProtocolo(this.rpBEAN);
			this.limpar();
			this.limparListas();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
			this.rpBEAN = new intra_requisicao_juridico();
			this.listaProtocolosGerente = null;
			this.listarProtocolo = null;
			this.listarProtocoloSelecionados = null;
			this.filtroProtocolo = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ↓ MÉTODOS PARA EXCLUIR UMA REQUISIÇÃO DE PROTOCOLO ↓

	public void excluir() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.codigoSelecionado != null) {
			this.pjDAO = new ProtocoloJurDAO();

			intra_requisicao_juridico requisicao = this.pjDAO
					.pesquisaRequisicaoPorCodigo(this.codigoSelecionado.getCodigo());

			this.pjDAO.excluir(requisicao, this.sessaoMB.getUsuario().getNome(), this.sessaoMB.getIpUser());
			RequestContext.getCurrentInstance().execute("PF('dlgExclusao').hide();");
			this.limpar();
			this.limparListas();
			;
		}
	}
	// ↓ MÉTODO PARA IMPRESSÃO DA REQUISIÇÃO DE MALOTE ↓

	public void gerarRequisicao() throws Exception {
		String nome = "";
		nome = "ProtocoloJuridico";
		HashMap<Object, Object> parametros = new HashMap<>();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		ProtocoloJurDAO dao = new ProtocoloJurDAO();

		for (intra_requisicao_juridico rp : this.getListarProtocoloSelecionados()) {
			rp.setImpressao("Impresso");
			dao.salvarProtocolo(rp);
		}
		this.listarProtocolo = null;
		this.limpar();
		this.limparListas();
		this.downloadPDF(this.populaRelatorio(parametros, nome, nome, 1, listarProtocoloSelecionados));
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
			response.setHeader("Content-Disposition", "inline;filename=\"ProtocoloJuridico.pdf\"");
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
			List<intra_requisicao_juridico> lstProtocolo) throws Exception {
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