package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.oma.intranet.dao.RelatorioSemanalDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_relSem_historico;
import br.com.oma.intranet.entidades.intra_relatorio_semanal;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ManagedBean(name = "rsMB")
@ViewScoped
public class RelatorioSemanal extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3148964189799013021L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private RelatorioSemanalDAO rsDAO;
	private intra_condominios icBean = new intra_condominios();
	private intra_relSem_historico rshBean = new intra_relSem_historico();
	private intra_relatorio_semanal infoAnterior = new intra_relatorio_semanal();
	private intra_relatorio_semanal rsBean = new intra_relatorio_semanal();
	private intra_log_geral intraLogGeralBean = new intra_log_geral();

	// ATRIBUTOS
	private String nomeCondo;
	private List<intra_relSem_historico> listarRSH, filtrarRSH;
	private List<intra_relatorio_semanal> listarRS, filtrarRS;
	private List<intra_log_geral> historicoRelatorioSemanal, filtro;

	private String nomePDF;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	// GET X SET
	/**
	 * @param sessaoMB
	 *            the sessaoMB to set
	 */
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	/**
	 * @return the icBean
	 */
	public intra_condominios getIcBean() {
		return icBean;
	}

	/**
	 * @param icBean
	 *            the icBean to set
	 */
	public void setIcBean(intra_condominios icBean) {
		this.icBean = icBean;
	}

	/**
	 * @return the nomeCondo
	 */
	public String getNomeCondo() {
		return nomeCondo;
	}

	/**
	 * @param nomeCondo
	 *            the nomeCondo to set
	 */
	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	/**
	 * @return the rshBean
	 */
	public intra_relSem_historico getRshBean() {
		return rshBean;
	}

	/**
	 * @param rshBean
	 *            the rshBean to set
	 */
	public void setRshBean(intra_relSem_historico rshBean) {
		this.rshBean = rshBean;
	}

	/**
	 * @return the rsBean
	 */
	public intra_relatorio_semanal getRsBean() {
		return rsBean;
	}

	/**
	 * @param rsBean
	 *            the rsBean to set
	 */
	public void setRsBean(intra_relatorio_semanal rsBean) {
		this.rsBean = rsBean;
	}

	/**
	 * @return the listarRSH
	 */
	public List<intra_relSem_historico> getListarRSH() {
		if (this.listarRSH == null) {
			if (this.icBean.getCodigo() > 0) {
				this.rsDAO = new RelatorioSemanalDAO();
				this.listarRSH = this.rsDAO.listarRelSemHistorico(this.icBean.getCodigo());
			}
		}
		return listarRSH;
	}

	/**
	 * @param listarRSH
	 *            the listarRSH to set
	 */
	public void setListarRSH(List<intra_relSem_historico> listarRSH) {
		this.listarRSH = listarRSH;
	}

	/**
	 * @return the filtrarRSH
	 */
	public List<intra_relSem_historico> getFiltrarRSH() {
		return filtrarRSH;
	}

	/**
	 * @param filtrarRSH
	 *            the filtrarRSH to set
	 */
	public void setFiltrarRSH(List<intra_relSem_historico> filtrarRSH) {
		this.filtrarRSH = filtrarRSH;
	}

	/**
	 * @return the listarRS
	 */
	public List<intra_relatorio_semanal> getListarRS() {
		return listarRS;
	}

	/**
	 * @param listarRS
	 *            the listarRS to set
	 */
	public void setListarRS(List<intra_relatorio_semanal> listarRS) {
		this.listarRS = listarRS;
	}

	/**
	 * @return the filtrarRS
	 */
	public List<intra_relatorio_semanal> getFiltrarRS() {
		return filtrarRS;
	}

	/**
	 * @param filtrarRS
	 *            the filtrarRS to set
	 */
	public void setFiltrarRS(List<intra_relatorio_semanal> filtrarRS) {
		this.filtrarRS = filtrarRS;
	}

	/**
	 * @return the nomePDF
	 */
	public String getNomePDF() {
		return nomePDF;
	}

	/**
	 * @param nomePDF
	 *            the nomePDF to set
	 */
	public void setNomePDF(String nomePDF) {
		this.nomePDF = nomePDF;
	}

	/**
	 * @return the infoAnterior
	 */
	public intra_relatorio_semanal getInfoAnterior() {
		return infoAnterior;
	}

	/**
	 * @param infoAnterior
	 *            the infoAnterior to set
	 */
	public void setInfoAnterior(intra_relatorio_semanal infoAnterior) {
		this.infoAnterior = infoAnterior;
	}

	public List<intra_log_geral> getHistoricoRelatorioSemanal() {
		return historicoRelatorioSemanal;
	}

	public void setHistoricoRelatorioSemanal(List<intra_log_geral> historicoRelatorioSemanal) {
		this.historicoRelatorioSemanal = historicoRelatorioSemanal;
	}

	public List<intra_log_geral> getFiltro() {
		return filtro;
	}

	public void setFiltro(List<intra_log_geral> filtro) {
		this.filtro = filtro;
	}

	public intra_log_geral getIntraLogGeralBean() {
		return intraLogGeralBean;
	}

	public void setIntraLogGeralBean(intra_log_geral intraLogGeralBean) {
		this.intraLogGeralBean = intraLogGeralBean;
	}

	// METODOS
	public void listarHistorioSemanal() {
		if (this.icBean.getCodigo() > 0) {
			this.rsDAO = new RelatorioSemanalDAO();
			this.historicoRelatorioSemanal = this.rsDAO.listarHistoricoSemanal(this.icBean.getCodigo());
		}
	}

	public void retornaNomeCondominio() {
		this.rsDAO = new RelatorioSemanalDAO();
		for (intra_condominios c : this.sessaoMB.getListaCondominios()) {
			if (c.getCodigo() == this.icBean.getCodigo()) {
				this.nomeCondo = c.getNome();
				this.icBean.setNomeGerente(c.getNomeGerente());
				this.icBean.setEmailGerente(c.getEmailGerente());
				this.icBean.setCodigoGerente(c.getCodigoGerente());
				this.rsBean = this.rsDAO.listarRelatorioSemanal(c.getCodigo());
				this.infoAnterior = this.rsBean;
			}
		}
	}

	public void salvarRelSem() {
		if (this.icBean.getCodigo() > 0) {
			this.rsDAO = new RelatorioSemanalDAO();
			this.rsDAO.salvarRelatorioSemanal(this.rsBean, this.sessaoMB, this.infoAnterior);
			this.rsBean = new intra_relatorio_semanal();
			this.listarRS = null;
			this.historicoRelatorioSemanal = null;
			this.msgAdicinado();
		} else {
			this.msgCondominio();
		}
	}

	public void gerarRelatorio() throws Exception {
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		String nome = "relatoriosemanal";
		byte[] retorno = null;
		HashMap<Object, Object> parametros = new HashMap<>();

		parametros.put("datainicial", this.rshBean.getDataInicial());
		parametros.put("datafinal", this.rshBean.getDataFinal());
		parametros.put("codigoGerente", this.sessaoMB.getGerenteSelecionado().getCodigo());

		retorno = rju.geraRel2(parametros, nome, nome, 1);
		this.downloadPDF(retorno);
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
			response.setHeader("Content-Disposition", "inline;filename=\"relatorio-semanal.pdf\"");
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
