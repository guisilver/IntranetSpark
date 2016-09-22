package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.joda.time.DateTime;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.pdf.PdfReader;

import br.com.oma.intranet.dao.EmissorDAO;
import br.com.oma.intranet.dao.ImpressaoBureauDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_emissor;
import br.com.oma.intranet.entidades.intra_emissor_arquivos;
import br.com.oma.intranet.entidades.intra_lote;
import br.com.oma.intranet.entidades.intra_lote_arquivos;
import br.com.oma.intranet.filters.ConexaoGeral;

@ViewScoped
@ManagedBean
public class EmissorMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6712700129533187349L;

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private intra_emissor emissor = new intra_emissor();
	private intra_emissor_arquivos emissorArquivos = new intra_emissor_arquivos();
	private intra_emissor emissorSelecionado;
	private EmissorDAO emissorDAO;
	private List<intra_emissor> lstEmissor, fltrEmissor;
	private intra_lote lote;
	private intra_condominios condominio;

	public static String TIPO_COMPACTACAO = "zip";

	private static Config config;
	private static String LINK_FTP;
	private static String USUARIO_FTP;
	private static String SENHA_FTP;

	public static SimpleDateFormat SF_DIA = new SimpleDateFormat("ddMMyy");
	public static SimpleDateFormat SF_HORA = new SimpleDateFormat("HHmm");

	private Date dataInicio;
	private Date dataFim;

	private int linhasTXT;
	private int qtdPGEmissor1;
	private int qtdPGEmissor2;
	private double tipoDoEmissor;

	private boolean destinatario, emissor1, emissor2;

	public EmissorMB() {
		this.dataInicio = new DateTime().minusDays(7).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime().withHourOfDay(23).withMinuteOfHour(59).toDate();
		config = new Config(new ConexaoGeral().getPersistence());
		LINK_FTP = config.Conf().getProperty("link.ftp");
		USUARIO_FTP = config.Conf().getProperty("user.ftp");
		SENHA_FTP = config.Conf().getProperty("password.ftp");
	}

	public void init() {
		if (this.sessaoMB.getGerenteSelecionado() != null) {
			this.pesquisaEmissores();
		}
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public intra_emissor getEmissor() {
		return emissor;
	}

	public void setEmissor(intra_emissor emissor) {
		this.emissor = emissor;
	}

	public intra_emissor_arquivos getEmissorArquivos() {
		return emissorArquivos;
	}

	public void setEmissorArquivos(intra_emissor_arquivos emissorArquivos) {
		this.emissorArquivos = emissorArquivos;
	}

	public intra_emissor getEmissorSelecionado() {
		return emissorSelecionado;
	}

	public void setEmissorSelecionado(intra_emissor emissorSelecionado) {
		this.emissorSelecionado = emissorSelecionado;
	}

	public List<intra_emissor> getLstEmissor() {
		if (this.lstEmissor == null) {
			this.fltrEmissor = null;
			this.emissorDAO = new EmissorDAO();
			if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
				if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
					this.lstEmissor = this.emissorDAO.listaTodosEmissores(this.dataInicio, this.dataFim);
				} else {
					if (this.sessaoMB.getGerenteSelecionado() != null) {
						this.lstEmissor = this.emissorDAO.listaEmissoresGerente(
								this.sessaoMB.getGerenteSelecionado().getCodigo(), this.dataInicio, this.dataFim);
					}
				}
			}
		}
		return lstEmissor;
	}

	public void setLstEmissor(List<intra_emissor> lstEmissor) {
		this.lstEmissor = lstEmissor;
	}

	public List<intra_emissor> getFltrEmissor() {
		return fltrEmissor;
	}

	public void setFltrEmissor(List<intra_emissor> fltrEmissor) {
		this.fltrEmissor = fltrEmissor;
	}

	public intra_lote getLote() {
		return lote;
	}

	public void setLote(intra_lote lote) {
		this.lote = lote;
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

	public boolean isDestinatario() {
		return destinatario;
	}

	public void setDestinatario(boolean destinatario) {
		this.destinatario = destinatario;
	}

	public boolean isEmissor1() {
		return emissor1;
	}

	public void setEmissor1(boolean emissor1) {
		this.emissor1 = emissor1;
	}

	public boolean isEmissor2() {
		return emissor2;
	}

	public void setEmissor2(boolean emissor2) {
		this.emissor2 = emissor2;
	}

	public int getLinhasTXT() {
		return linhasTXT;
	}

	public void setLinhasTXT(int linhasTXT) {
		this.linhasTXT = linhasTXT;
	}

	public int getQtdPGEmissor1() {
		return qtdPGEmissor1;
	}

	public void setQtdPGEmissor1(int qtdPGEmissor1) {
		this.qtdPGEmissor1 = qtdPGEmissor1;
	}

	public int getQtdPGEmissor2() {
		return qtdPGEmissor2;
	}

	public void setQtdPGEmissor2(int qtdPGEmissor2) {
		this.qtdPGEmissor2 = qtdPGEmissor2;
	}

	public double getTipoDoEmissor() {
		return tipoDoEmissor;
	}

	public void setTipoDoEmissor(double tipoDoEmissor) {
		this.tipoDoEmissor = tipoDoEmissor;
	}

	public void salvar() {
		try {
			this.validacoes();
			this.emissorDAO = new EmissorDAO();
			Date data = new Date();
			this.lote = new intra_lote();
			this.lote.setDataEnviado(data);
			this.lote.setServico("EMISSOR");
			this.lote.setArquivos(new ArrayList<intra_lote_arquivos>());
			if (this.emissorArquivos.getTxtDest() != null) {
				intra_lote_arquivos arq1 = new intra_lote_arquivos();
				arq1.setArquivo(this.emissorArquivos.getTxtDest());
				arq1.setNomeArquivo(this.emissorArquivos.getNomeTxtDest());
				this.lote.getArquivos().add(arq1);
			}
			if (this.emissorArquivos.getEmissor1() != null) {
				intra_lote_arquivos arq2 = new intra_lote_arquivos();
				arq2.setArquivo(this.emissorArquivos.getEmissor1());
				arq2.setNomeArquivo(this.emissorArquivos.getNomeEmissor1());
				this.lote.getArquivos().add(arq2);
			}
			if (this.emissorArquivos.getEmissor2() != null) {
				intra_lote_arquivos arq3 = new intra_lote_arquivos();
				arq3.setArquivo(this.emissorArquivos.getEmissor2());
				arq3.setNomeArquivo(this.emissorArquivos.getNomeEmissor2());
				this.lote.getArquivos().add(arq3);
			}
			this.emissor.setDataEnviado(this.lote.getDataEnviado());
			this.emissor.setCodCondominio((short) this.condominio.getCodigo());
			this.emissor.setNomeCondominio(this.condominio.getNome());
			this.zipEnviaArquivos();
			this.emissor.setLote(lote);
			this.emissor.setSituacao("Pendente");
			this.emissor.setCodGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
			this.emissorArquivos.setEmissor(this.emissor);
			this.emissor.setQtdaPaginasImpressa(this.linhasTXT * (this.qtdPGEmissor1 + this.qtdPGEmissor2));
			this.emissorDAO.salvar(this.emissor, this.emissorArquivos, this.sessaoMB.getUsuario().getNome(),
					this.sessaoMB.getIpUser());
			this.lote.setReferencia(this.emissor.getReferencia());
			double qtdaImpressao = (this.linhasTXT * (this.qtdPGEmissor1 + this.qtdPGEmissor2));
			int codigoDoServico = this.emissorDAO.retornaCodigoDoServico(this.condominio, this.tipoDoEmissor);
			if (codigoDoServico > 0) {
				int codigoMsvcon = this.emissorDAO.retornaCodigoMsvcon();
				this.emissorDAO.salvarCobrancaServico(codigoMsvcon, codigoDoServico, qtdaImpressao,
						"Emissor Global - " + emissor.getReferencia());
			}
			this.linhasTXT = 0;
			this.qtdPGEmissor1 = 0;
			this.qtdPGEmissor2 = 0;
			this.limpar();
			RequestContext.getCurrentInstance().execute("PF('dlgNovoEmissor').hide();");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", ""));
		} catch (SocketTimeoutException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"O servidor da global não está respondendo!", "Por favor, tente novamente mais tarde."));
		} catch (SocketException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"O servidor da global não está respondendo!", "Por favor, tente novamente mais tarde."));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocorreu um erro ao compactar!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "Contate o administrador"));
		}
	}

	public void pesquisaNomeCond() {
		if (this.condominio != null) {
			this.emissor.setCodCondominio((short) this.condominio.getCodigo());
			this.emissor.setNomeCondominio(this.condominio.getNome());
		} else {
			this.emissor.setNomeCondominio(null);
		}
	}

	public void uploadDestinatario(FileUploadEvent event) {
		if (event.getFile().getContents() != null) {
			this.emissorArquivos.setTxtDest(event.getFile().getContents());
			this.emissorArquivos.setNomeTxtDest(event.getFile().getFileName());
			LineNumberReader counterLinhas;
			try {
				counterLinhas = new LineNumberReader(new InputStreamReader(event.getFile().getInputstream()));
				@SuppressWarnings("unused")
				String nextLine = null;
				while ((nextLine = counterLinhas.readLine()) != null) {
					// System.out.println(nextLine);
				}
				this.linhasTXT = counterLinhas.getLineNumber();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void uploadEmissor1(FileUploadEvent event) {
		if (event.getFile().getContents() != null) {
			this.emissorArquivos.setEmissor1(event.getFile().getContents());
			this.emissorArquivos.setNomeEmissor1(event.getFile().getFileName());
			this.qtdPGEmissor1 = 0;
			PdfReader pdfReader;
			HWPFDocument doc;
			XWPFDocument docx;
			try {
				String ext = event.getFile().getFileName();
				String extensao = ext.substring(ext.lastIndexOf('.') + 1);
				extensao.toLowerCase();
				if (extensao.trim().equals("pdf")) {
					pdfReader = new PdfReader(event.getFile().getContents());
					this.qtdPGEmissor1 = pdfReader.getNumberOfPages();
				} else if (extensao.trim().equals("docx")) {
					docx = new XWPFDocument(event.getFile().getInputstream());
					this.qtdPGEmissor1 = docx.getProperties().getExtendedProperties().getUnderlyingProperties()
							.getPages();
				} else if (extensao.trim().equals("doc")) {
					doc = new HWPFDocument(event.getFile().getInputstream());
					this.qtdPGEmissor1 = doc.getSummaryInformation().getPageCount();
				}

			} catch (IOException e) {
				System.out.println("Erro ao le PDF para calculo de quantidade de paginas" + e);
			}

		}
	}

	public void uploadEmissor2(FileUploadEvent event) {
		if (event.getFile().getContents() != null) {
			this.emissorArquivos.setEmissor2(event.getFile().getContents());
			this.emissorArquivos.setNomeEmissor2(event.getFile().getFileName());
			this.emissorArquivos.setNomeEmissor2(event.getFile().getFileName());
			this.qtdPGEmissor2 = 0;
			PdfReader pdfReader;
			HWPFDocument doc;
			XWPFDocument docx;
			try {
				String ext = event.getFile().getFileName();
				String extensao = ext.substring(ext.lastIndexOf('.') + 1);
				extensao.toLowerCase();
				if (extensao.trim().equals("pdf")) {
					pdfReader = new PdfReader(event.getFile().getContents());
					this.qtdPGEmissor2 = pdfReader.getNumberOfPages();
				} else if (extensao.trim().equals("docx")) {
					docx = new XWPFDocument(event.getFile().getInputstream());
					this.qtdPGEmissor2 = docx.getProperties().getExtendedProperties().getUnderlyingProperties()
							.getPages();
				} else if (extensao.trim().equals("doc")) {
					doc = new HWPFDocument(event.getFile().getInputstream());
					this.qtdPGEmissor2 = doc.getSummaryInformation().getPageCount();
				}

			} catch (IOException e) {
				System.out.println("Erro ao le PDF para calculo de quantidade de paginas" + e);
			}
		}
	}

	public void excDestinarario() {
		this.emissorArquivos.setTxtDest(null);
		this.emissorArquivos.setNomeTxtDest("");
		this.linhasTXT = 0;
	}

	public void excEmissor1() {
		this.emissorArquivos.setEmissor1(null);
		this.emissorArquivos.setNomeEmissor1("");
		this.qtdPGEmissor1 = 0;
	}

	public void excEmissor2() {
		this.emissorArquivos.setEmissor2(null);
		this.emissorArquivos.setNomeEmissor2("");
		this.qtdPGEmissor2 = 0;
	}

	public String geraNomeLote() {
		StringBuilder nomeGerado = new StringBuilder();
		nomeGerado.append("EM");
		nomeGerado.append(SF_DIA.format(this.lote.getDataEnviado()));
		nomeGerado.append(SF_HORA.format(this.lote.getDataEnviado()));
		nomeGerado.append("CPD");
		nomeGerado.append("01");
		nomeGerado.append("M");
		return verificaNomeLoteValido(nomeGerado.toString());
	}

	public void zipEnviaArquivos() throws Exception {
		int TIMEOUT_VALUE = 10000;
		this.lote.setNomeLote(geraNomeLote());
		URL url = new URL("ftp://" + USUARIO_FTP + ":" + SENHA_FTP + "@" + LINK_FTP + "//" + this.lote.getNomeLote()
				+ "." + TIPO_COMPACTACAO);
		URLConnection urlc = url.openConnection();
		urlc.setDoInput(true);
		urlc.setDoOutput(true);
		urlc.setUseCaches(false); // Don't use a Cached Copy
		urlc.setConnectTimeout(TIMEOUT_VALUE);
		urlc.setReadTimeout(TIMEOUT_VALUE);
		urlc.connect();
		OutputStream os = urlc.getOutputStream();// To upload
		ZipOutputStream zos = new ZipOutputStream(os);
		System.out.println("Compactando...");
		if (this.emissorArquivos.getTxtDest() != null) {
			ZipEntry z1 = new ZipEntry(this.emissorArquivos.getNomeTxtDest());
			zos.putNextEntry(z1);
			zos.write(this.emissorArquivos.getTxtDest(), 0, this.emissorArquivos.getTxtDest().length);
		}
		if (this.emissorArquivos.getEmissor1() != null) {
			ZipEntry z2 = new ZipEntry(this.emissorArquivos.getNomeEmissor1());
			zos.putNextEntry(z2);
			zos.write(this.emissorArquivos.getEmissor1(), 0, this.emissorArquivos.getEmissor1().length);
		}
		if (this.emissorArquivos.getEmissor2() != null) {
			ZipEntry z3 = new ZipEntry(this.emissorArquivos.getNomeEmissor2());
			zos.putNextEntry(z3);
			zos.write(this.emissorArquivos.getEmissor2(), 0, this.emissorArquivos.getEmissor2().length);
		}
		ZipEntry z4 = new ZipEntry("MSG.txt");
		zos.putNextEntry(z4);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(zos));
		bw.write("MSG VERSO = " + this.emissor.getReferencia() + "\r\n");
		bw.newLine();
		bw.write("MSG PROTOCOLO = ");
		bw.flush();
		if (this.sessaoMB.getUsuario() != null) {
			ZipEntry z5 = new ZipEntry("info.txt");
			zos.putNextEntry(z5);
			bw = new BufferedWriter(new OutputStreamWriter(zos));
			bw.write("Nome:" + this.sessaoMB.getUsuario().getNome());
			bw.newLine();
			bw.write("Email:" + this.sessaoMB.getUsuario().getEmail());
		}
		bw.close();
		zos.close();
		System.out.println("Compactado e enviado com sucesso!");
	}

	public void salvaLote(intra_lote lote) {
		try {
			System.out.println("Conectando ao banco de dados...");
			ImpressaoBureauDAO dao = new ImpressaoBureauDAO();
			dao.salvar(lote, this.sessaoMB.getUsuario().getNome(), this.sessaoMB.getIpUser());
			System.out.println("Salvo com sucesso!");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao salvar!");
			e.printStackTrace();
		}
	}

	public void validacoes() throws Exception {
		if (this.emissorArquivos.getTxtDest() == null) {
			throw new Exception("Insira o arquivo txt de destinatário!");
		}
		if (this.emissorArquivos.getEmissor1() == null && this.emissorArquivos.getEmissor2() == null) {
			throw new Exception("Insira ao menos um arquivo de emissor!");
		}
		if ((this.emissorArquivos.getNomeEmissor1() != null
				&& this.emissorArquivos.getNomeEmissor1().equals(this.emissorArquivos.getNomeEmissor2()))
				|| (this.emissorArquivos.getNomeEmissor2() != null
						&& this.emissorArquivos.getNomeEmissor2().equals(this.emissorArquivos.getNomeEmissor1()))) {
			throw new Exception("Os arquivos de emissor não podem ser duplicados!");
		}
		if (this.tipoDoEmissor == 0.0) {
			throw new Exception("Informe o tipo do Emissor!");
		}
	}

	public StreamedContent getDwnldDest() {
		StreamedContent download = null;
		if (this.emissorSelecionado != null && this.emissorSelecionado.getArquivos().getTxtDest() != null) {
			download = new DefaultStreamedContent(
					new ByteArrayInputStream(this.emissorSelecionado.getArquivos().getTxtDest()), "",
					this.emissorSelecionado.getArquivos().getNomeTxtDest());
		} else {
			try {
				throw new Exception("Erro! Este emissor não possui o arquivo de destino!");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}
		}
		return download;
	}

	public StreamedContent getDwnldEmissor1() {
		StreamedContent download = null;
		if (this.emissorSelecionado != null && this.emissorSelecionado.getArquivos().getEmissor1() != null) {
			download = new DefaultStreamedContent(
					new ByteArrayInputStream(this.emissorSelecionado.getArquivos().getEmissor1()), "",
					this.emissorSelecionado.getArquivos().getNomeEmissor1());
		} else {
			try {
				throw new Exception("Este emissor não possui o primeiro arquivo!");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}
		}
		return download;
	}

	public StreamedContent getDwnldEmissor2() {
		StreamedContent download = null;
		if (this.emissorSelecionado != null && this.emissorSelecionado.getArquivos().getEmissor2() != null) {
			download = new DefaultStreamedContent(
					new ByteArrayInputStream(this.emissorSelecionado.getArquivos().getEmissor2()), "",
					this.emissorSelecionado.getArquivos().getNomeEmissor2());
		} else {
			try {
				throw new Exception("Este emissor não possui o segundo arquivo!");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}
		}
		return download;
	}

	public void filtrarListaEmissores() {
		this.dataInicio = new DateTime(this.dataInicio).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime(this.dataFim).withHourOfDay(23).withMinuteOfHour(59).toDate();
		this.lstEmissor = null;
		this.fltrEmissor = null;
	}

	public void excluir() {
		if (this.emissorSelecionado != null) {
			this.emissorDAO = new EmissorDAO();

			intra_emissor emissor = this.emissorDAO.pesquisaEmissorPorCodigo(this.emissorSelecionado.getCodigo());

			this.emissorDAO.excluir(emissor, this.sessaoMB.getUsuario().getNome(), this.sessaoMB.getIpUser());
			RequestContext.getCurrentInstance().execute("PF('dlgExclusao').hide();");
			this.limpar();
			this.limparFiltroTbl();
		}
	}

	public String verificaNomeLoteValido(String nomeLote) {
		EmissorDAO dao = new EmissorDAO();
		StringBuilder nomeGerado = new StringBuilder();
		nomeGerado.append("EM");
		nomeGerado.append(SF_DIA.format(this.lote.getDataEnviado()));
		nomeGerado.append(SF_HORA.format(this.lote.getDataEnviado()));
		nomeGerado.append("CPD");
		nomeGerado.append("01");
		nomeGerado.append("M");
		int contador = 0;
		while (dao.verificaNomeLoteValido(nomeGerado.toString()) == false) {
			nomeGerado = nomeGerado.replace(8, 12,
					SF_HORA.format(new DateTime(this.lote.getDataEnviado()).plusMinutes(contador).toDate()));
			contador++;
		}
		return nomeGerado.toString();
	}

	public void pesquisaEmissores() {
		this.fltrEmissor = null;
		this.emissorDAO = new EmissorDAO();
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				this.lstEmissor = this.emissorDAO.listaTodosEmissores(this.dataInicio, this.dataFim);
			} else {
				this.lstEmissor = this.emissorDAO.listaEmissoresGerente(
						this.sessaoMB.getGerenteSelecionado().getCodigo(), this.dataInicio, this.dataFim);
			}
		}
	}

	public void renderizaBotoesDwld() {
		EmissorDAO dao = new EmissorDAO();
		List<intra_emissor_arquivos> listaRetorno = dao.pesquisaArquivosEmissor(this.emissorSelecionado);
		if (listaRetorno != null) {
			this.emissorSelecionado.setArquivos(listaRetorno.get(0));
		}
		if (this.emissorSelecionado != null && this.emissorSelecionado.getArquivos().getTxtDest() != null) {
			this.destinatario = true;
		} else {
			this.destinatario = false;
		}
		if (this.emissorSelecionado != null && this.emissorSelecionado.getArquivos().getEmissor1() != null) {
			this.emissor1 = true;
		} else {
			this.emissor1 = false;
		}
		if (this.emissorSelecionado != null && this.emissorSelecionado.getArquivos().getEmissor2() != null) {
			this.emissor2 = true;
		} else {
			this.emissor2 = false;
		}
	}

	public int contarPaginas(byte[] pdf) {
		try {
			PdfReader reader = new PdfReader(pdf);
			int paginas = reader.getNumberOfPages();
			reader.close();
			return paginas;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int contarDestinatarios(byte[] txt) throws IOException {
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(txt));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

	public void limpar() {
		this.emissor = new intra_emissor();
		this.emissorArquivos = new intra_emissor_arquivos();
		this.lote = new intra_lote();
		this.lstEmissor = null;
		this.fltrEmissor = null;
		this.condominio = null;
	}

	public void limparFiltroTbl() {
		DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("frmTblEmissor:tblEmissor");
		d.setValue(null);
		RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
	}
}
