package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.RelatorioMensalDAO;
import br.com.oma.intranet.dao.SipoDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.omaonline.dao.FinanceiroDAO;
import br.com.oma.omaonline.entidades.financeiro_imagem;

@ManagedBean(name = "sipoMB")
@ViewScoped
public class SipoMB extends Mensagens {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1752739825443398659L;
	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private SipoDAO sipoDAO;
	private intra_condominios icBEAN = new intra_condominios();
	private intra_grupo_gerente gerenteBEAN = new intra_grupo_gerente();
	private financeiro_imagem fiBEAN;

	// ATRIBUTOS
	private int condominio;
	private List<intra_condominios> listaDeCondominio;
	private List<intra_grupo_gerente> listaDeGerentes;
	private Date dataInicio;
	private Date dataFim;
	private List<financeiro_imagem> listaImagem;
	private List<financeiro_imagem> listaArquivos = new ArrayList<financeiro_imagem>();
	private financeiro_imagem imagemSelecionada;
	private int cdFinancImagem;
	private boolean validaEtiqueta;
	private String etiqueta;
	private String nomeImagem;
	private byte[] arquivoImagem;
	private StreamedContent arquivoDownload;
	private String pesquisarPor = "Todos";
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	public SipoMB() {
		/*
		 * Calendar cal = GregorianCalendar.getInstance(); cal.setTime(new
		 * Date()); int dia = cal.getActualMinimum(Calendar.DAY_OF_MONTH); int
		 * dia1 = cal.getActualMaximum(Calendar.DAY_OF_MONTH); int mes =
		 * (cal.get(Calendar.MONDAY) + 1); int ano = cal.get(Calendar.YEAR); try
		 * { this.dataInicio = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia +
		 * "/" + mes + "/" + ano); this.dataFim = (new
		 * SimpleDateFormat("dd/MM/yyyy")).parse(dia1 + "/" + mes + "/" + ano);
		 * } catch (ParseException e) { e.printStackTrace(); }
		 */
		this.dataInicio = new DateTime().withDayOfMonth(1).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime().withDayOfMonth(1).plusMonths(1).minusDays(1).withHourOfDay(23)
				.withMinuteOfHour(59).toDate();
	}

	// GET X SET
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public SipoDAO getSipoDAO() {
		return sipoDAO;
	}

	public void setSipoDAO(SipoDAO sipoDAO) {
		this.sipoDAO = sipoDAO;
	}

	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
	}

	public List<intra_condominios> getListaDeCondominio()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		if (this.listaDeCondominio == null) {
			SipoDAO dao;
			dao = new SipoDAO();
			if (this.listaDeCondominio == null) {
				if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
					if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos"))
						;
					this.listaDeCondominio = dao.listarCondominios();
				} else {
					if (this.gerenteBEAN != null) {
						this.listaDeCondominio = dao.getListaCondominios(this.gerenteBEAN.getCodigo());
					}
				}
			}
		}
		return listaDeCondominio;
	}

	public void setListaDeCondominio(List<intra_condominios> listaDeCondominio) {
		this.listaDeCondominio = listaDeCondominio;
	}

	public intra_grupo_gerente getGerenteBEAN() {
		return gerenteBEAN;
	}

	public void setGerenteBEAN(intra_grupo_gerente gerenteBEAN) {
		this.gerenteBEAN = gerenteBEAN;
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

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public List<financeiro_imagem> getListaImagem() {
		if (this.listaImagem == null) {
			this.sipoDAO = new SipoDAO();
			this.listaImagem = this.sipoDAO.listarImagem();
		}
		return listaImagem;
	}

	public void setListaImagem(List<financeiro_imagem> listaImagem) {
		this.listaImagem = listaImagem;
	}

	public financeiro_imagem getFiBEAN() {
		return fiBEAN;
	}

	public void setFiBEAN(financeiro_imagem fiBEAN) {
		this.fiBEAN = fiBEAN;
	}

	public List<financeiro_imagem> getListaArquivos() {
		return listaArquivos;
	}

	public void setListaArquivos(List<financeiro_imagem> listaArquivos) {
		this.listaArquivos = listaArquivos;
	}

	public financeiro_imagem getImagemSelecionada() {
		return imagemSelecionada;
	}

	public void setImagemSelecionada(financeiro_imagem imagemSelecionada) {
		this.imagemSelecionada = imagemSelecionada;
	}

	public int getCdFinancImagem() {
		return cdFinancImagem;
	}

	public void setCdFinancImagem(int cdFinancImagem) {
		this.cdFinancImagem = cdFinancImagem;
	}

	public boolean isValidaEtiqueta() {
		return validaEtiqueta;
	}

	public void setValidaEtiqueta(boolean validaEtiqueta) {
		this.validaEtiqueta = validaEtiqueta;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public byte[] getArquivoImagem() {
		return arquivoImagem;
	}

	public void setArquivoImagem(byte[] arquivoImagem) {
		this.arquivoImagem = arquivoImagem;
	}

	public StreamedContent getArquivoDownload() {
		return arquivoDownload;
	}

	public void setArquivoDownload(StreamedContent arquivoDownload) {
		this.arquivoDownload = arquivoDownload;
	}

	public String getPesquisarPor() {
		return pesquisarPor;
	}

	public void setPesquisarPor(String pesquisarPor) {
		this.pesquisarPor = pesquisarPor;
	}

	// MÉTODOS

	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				this.gerenteBEAN.setCodigo(this.sessaoMB.getListaDeGerente().get(0).getCodigo());
				return this.sessaoMB.getListaDeGerente();
			} else {
				if (this.sessaoMB.getUsuario().getGrupoGer() != null) {
					this.gerenteBEAN = this.sessaoMB.getUsuario().getGrupoGer().get(0);
					this.listarCondominios();
				}
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}

	public void listarCondominios() {
		try {
			RelatorioMensalDAO dao = new RelatorioMensalDAO();
			if (this.gerenteBEAN != null) {
				if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
					this.listaDeCondominio = dao.getListaCondominios();
				} else {
					this.listaDeCondominio = dao.getListaCondominios(this.gerenteBEAN.getCodigo());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public void pesquisarImagem() {
		this.validaEtiqueta = false;
		if (this.etiqueta != null && !this.etiqueta.isEmpty() && Double.parseDouble(this.etiqueta) > 0) {
			this.sipoDAO = new SipoDAO();
			BigDecimal bd = new BigDecimal(this.etiqueta);

			for (BigDecimal b : this.sipoDAO.validaIdImagem()) {
				if (bd.equals(b)) {
					this.validaEtiqueta = true;
				}
			}

			if (this.validaEtiqueta) {
				this.imagemSelecionada = this.sipoDAO.pesquisaImagem(Double.parseDouble(this.etiqueta),
						this.getCondominio());
				if (this.imagemSelecionada.getCodigo() > 0) {
					this.nomeImagem = this.imagemSelecionada.getNome_arquivo();
					this.arquivoImagem = this.imagemSelecionada.getImagem();
					RequestContext.getCurrentInstance().update("frmUploadImagem");
				} else {
					this.nomeImagem = null;
					this.arquivoImagem = null;
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"O número da etiqueta não foi encontrado!", "O número da etiqueta não é valido!"));
			}
		} else {
			this.nomeImagem = null;
		}
	}

	public void pesquisarTabelaImagem() {
		SipoDAO dao = new SipoDAO();
		try {
			if (this.dataInicio == null || this.dataFim == null) {
				throw new Exception("Insira datas de início e fim para pesquisar!");
			} else {
				this.dataFim = new DateTime(this.dataFim).withHourOfDay(23).withMinuteOfHour(59).toDate();
			}
			switch (this.pesquisarPor) {
			case "Todos":
				if (this.dataInicio != null && this.dataFim != null) {
					this.dataInicio = new DateTime(this.dataInicio).minusMinutes(1).toDate();
					this.listaImagem = dao.listarImagem(this.dataInicio, this.dataFim);
				} else {
					this.listaImagem = dao.listarImagem();
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void pesquisaIdImagem() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String id = params.get("idImagem");
			if (id != null) {
				SipoDAO dao = new SipoDAO();
				this.fiBEAN = dao.pesquisaImagemPorCodigo(Integer.parseInt(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abreAlteraGED() {
		try {
			if (this.imagemSelecionada != null) {
				String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(
						caminho + "/sipo/ged/alterar-ged.xhtml?idImagem=" + this.imagemSelecionada.getCodigo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterarTabela() {
		try {
			SipoDAO dao = new SipoDAO();
			if (this.fiBEAN.getCodigo() != 0) {
				this.fiBEAN.setCdCnd(this.fiBEAN.getCdCnd());
				this.fiBEAN.setDataVinculoArq(new Date());
				this.fiBEAN.setId(this.fiBEAN.getId());
				this.fiBEAN.setId_tipoarquivo(this.fiBEAN.getId_tipoarquivo());
				this.fiBEAN.setCodLanctoOma(this.fiBEAN.getCodLanctoOma());
				this.fiBEAN.setIdentificacaoTipoArq(this.fiBEAN.getIdentificacaoTipoArq());
				this.fiBEAN.setImagem(this.arquivoImagem);
				this.fiBEAN.setNome_arquivo(this.nomeImagem);
			}
			dao.salvarImagemGED(this.fiBEAN);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "/sipo/ged/tabela-ged.xhtml?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excDocumento(int index) {
		this.listaArquivos.remove(index);
	}

	public String converte(long value) {
		BigInteger teste = BigInteger.valueOf(value);
		return teste.toString();
	}

	public String converteDouble(long i) {
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(0);
		return df.format(i);
	}

	public void excluir() {
		if (this.imagemSelecionada != null) {
			if (this.verificaExclusao()) {
				this.sipoDAO = new SipoDAO();

				// financeiro_imagem img =
				// this.sipoDAO.pesquisaImagemPorCodigo(this.imagemSelecionada.getCodigo());

				this.sipoDAO.excluir(imagemSelecionada, this.sessaoMB.getUsuario().getNome(),
						this.sessaoMB.getIpUser());
				this.listaImagem = null;

				RequestContext.getCurrentInstance().execute("PF('dlgExclusao').hide();");
				this.msgExclusao();
			} else {
				RequestContext.getCurrentInstance().execute("PF('dlgExclusao').hide();");
				this.msgExclusaoEtiquetaErro();
			}
		}
	}

	private boolean verificaExclusao() {
		this.sipoDAO = new SipoDAO();
		boolean valida = this.sipoDAO.verificaExclusao(this.imagemSelecionada.getId());
		System.out.println(valida);
		return valida;
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (event.getFile().getContents() != null) {
				financeiro_imagem img = new financeiro_imagem();
				img.setNome_arquivo(event.getFile().getFileName());
				img.setImagem(event.getFile().getContents());
				img.setId(Double.parseDouble(this.etiqueta));
				img.setDataVinculoArq(new Date());
				img.setIdentificacaoTipoArq(".PDF");
				this.listaArquivos.add(img);
				SipoDAO dao = new SipoDAO();
				dao.salvarImagemGED(img);
				RequestContext.getCurrentInstance().reset("frmUploadImagem");
				RequestContext.getCurrentInstance().execute("PF('dlgUploadImagem').hide();");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirImagem() {
		try {
			FinanceiroDAO fncDAO = new FinanceiroDAO();
			byte[] arquivo = null;
			if (this.imagemSelecionada.getCodigo() != 0) {
				arquivo = fncDAO.pesquisaPDF(this.imagemSelecionada.getCodigo());
				this.downloadPDF(arquivo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			response.setHeader("Content-Disposition", "inline;filename=\"Imagem.pdf\"");
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
}
