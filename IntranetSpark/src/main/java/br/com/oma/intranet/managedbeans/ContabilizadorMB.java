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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.ContabilizadorDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_contabilizador;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_usuario;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;
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

@ViewScoped
@ManagedBean(name = "ctMB")
public class ContabilizadorMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1977744619721268100L;

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private ContabilizadorDAO ctDAO;
	private intra_condominios condominio = new intra_condominios();
	private intra_contabilizador contabilizador = new intra_contabilizador();
	private intra_usuario usuario = new intra_usuario();
	private List<intra_contabilizador> listaContabilizador, fltrContabilizador;
	private List<intra_usuario> listaUsuarios;
	private SimpleDateFormat sf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	private Date dataInicial;
	private Date dataFinal;
	private String pesquisarPor = "Todos";

	// GET X SET
	public ContabilizadorMB() {
		this.dataInicial = new DateTime().minusDays(7).withMillisOfDay(0).toDate();
		this.dataFinal = new DateTime().withHourOfDay(23).withMinuteOfHour(59).toDate();
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public ContabilizadorDAO getCtDAO() {
		return ctDAO;
	}

	public void setCtDAO(ContabilizadorDAO ctDAO) {
		this.ctDAO = ctDAO;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public intra_contabilizador getContabilizador() {
		return contabilizador;
	}

	public void setContabilizador(intra_contabilizador contabilizador) {
		this.contabilizador = contabilizador;
	}

	public intra_usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(intra_usuario usuario) {
		this.usuario = usuario;
	}

	public List<intra_contabilizador> getListaContabilizador()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.listaContabilizador == null) {
			pesquisaContabilizador();
		}
		return listaContabilizador;
	}

	public void setListaContabilizador(List<intra_contabilizador> listaContabilizador) {
		this.listaContabilizador = listaContabilizador;
	}

	public List<intra_contabilizador> getFltrContabilizador() {
		return fltrContabilizador;
	}

	public void setFltrContabilizador(List<intra_contabilizador> fltrContabilizador) {
		this.fltrContabilizador = fltrContabilizador;
	}

	public List<intra_usuario> getListaUsuarios() {
		if (this.listaUsuarios == null) {
			ContabilizadorDAO dao;
			try {
				dao = new ContabilizadorDAO();
				if (this.listaUsuarios == null) {
					if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
						if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
							this.listaUsuarios = dao.getListaUsuario();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaUsuarios;
	}

	public void setListaUsuarios(List<intra_usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
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

	// ↓ MÉTODO PARA LIMPAR AS LISTAS DO CONTABILIZADOR ↓
	public void limpar() {
		this.listaUsuarios = null;
		this.contabilizador = new intra_contabilizador();
	}

	// ↓ MÉTODO PARA FILTRAR A LISTA DO CONTABILIZADOR ↓
	public void filtrarListaContabilizador() {
		this.listaContabilizador = null;
		this.fltrContabilizador = null;
		this.dataInicial = new DateTime(this.dataInicial).withMillisOfDay(0).toDate();
		this.dataFinal = new DateTime(this.dataFinal).withHourOfDay(23).withMinuteOfHour(59).toDate();
	}

	// ↓ MÉTODO PARA PESQUISAR ↓
	public void pesquisaContabilizador()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		List<intra_condominios> resultList = null;
		ContabilizadorDAO dao = new ContabilizadorDAO();
		try {
			if (this.pesquisarPor.equals("Condominio") && this.condominio == null
					|| this.pesquisarPor.equals("Condominio") && this.condominio.getCodigo() == 0) {
				throw new Exception("Selecione um condomínio para pesquisar!");
			}
			if (this.pesquisarPor.equals("Carteira") && this.sessaoMB.getGerenteSelecionado() == null) {
				throw new Exception("Selecione um gerente para pesquisar!");
			}
			if (this.pesquisarPor.equals("Usuario") && this.usuario == null) {
				throw new Exception("Selecione um funcionário para pesquisar!");
			}
			if (this.dataInicial == null || this.dataFinal == null) {
				throw new Exception("Insira datas de início e fim para a pesquisar!");
			} else {
				this.dataFinal = new DateTime(this.dataFinal).withHourOfDay(23).withMinuteOfHour(59).toDate();
			}
			switch (this.pesquisarPor) {
			case "Todos":
				this.listaContabilizador = dao.getListaContabilizador();
				break;
			case "Condominio":
				List<intra_condominios> listaCnd = new ArrayList<intra_condominios>();
				listaCnd.add(condominio);
				this.listaContabilizador = dao.listarContabilizador(this.dataInicial, this.dataFinal, listaCnd);
				break;
			case "Carteira":
				resultList = dao.listarCondominios(this.sessaoMB.getGerenteSelecionado().getCodigo());
				this.listaContabilizador = dao.listarContabilizador(this.dataInicial, this.dataFinal, resultList);
				break;
			case "Usuario":
				List<intra_usuario> listaUsuario = new ArrayList<intra_usuario>();
				listaUsuario.add(usuario);
				this.listaContabilizador = dao.listarContabilizadorU(this.dataInicial, this.dataFinal, listaUsuario);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA SALVAR ↓
	public void salvarNovoContabilizador() {
		ContabilizadorDAO dao;
		try {
			dao = new ContabilizadorDAO();
			this.contabilizador.setUsuario(this.sessaoMB.getUsuario().getNome());
			this.contabilizador.setDataInserido(new Date());
			this.contabilizador.setUsuario_email(this.sessaoMB.getUsuario().getEmail());
			this.contabilizador.setCondominio_nome(this.condominio.getNome());
			this.contabilizador.setCondominio_codigo(this.condominio.getCodigo());
			for (intra_grupo_gerente g : this.sessaoMB.getListaDeGerente()) {
				if (g.getCodigo() == this.sessaoMB.getGerenteSelecionado().getCodigo()) {
					this.contabilizador.setNomeGerente(g.getNome());
				}
			}
			dao.salvarNovoContabilizador(this.contabilizador);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Salvo com sucesso às " + sf.format(new Date()), ""));
			this.limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODOS PARA IMPRESSÃO ↓
	public void gerarRelatorioAnalitico() throws IOException, Exception {
		String nome = "Analitico";
		HashMap<Object, Object> parametros = new HashMap<>();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		this.limpar();
		this.downloadPDF(this.populaRelatorio(parametros, nome, nome, 1, listaContabilizador));
	}

	public void gerarRelatorioSintetico() throws IOException, Exception {
		String nome = "Sintetico";
		HashMap<Object, Object> parametro = new HashMap<>();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		this.limpar();
		this.downloadPDF(this.populaRelatorio(parametro, nome, nome, 1, listaContabilizador));
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
			response.setHeader("Content-Disposition", "inline;filename=\"Contabilizador.pdf\"");
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
		// java.lang.IllegalStateException: Cannot forward after response has
		// been committed.
		facesContext.responseComplete();
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail
				// it. It may be useful to
				// know that this will generally only be thrown when the client
				// aborted the download.
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] populaRelatorio(Map parametros, String nome_entrada, String nome_saida, int tipo,
			List<intra_contabilizador> lstProtocolo) throws Exception {
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