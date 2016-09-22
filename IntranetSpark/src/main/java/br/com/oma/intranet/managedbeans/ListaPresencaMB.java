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

import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.LISTAR_BL_UNI_DAO;
import br.com.oma.intranet.entidades.intra_cndunidade;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ManagedBean(name = "lpMB")
@ViewScoped
public class ListaPresencaMB extends Mensagens {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2202849808953211082L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private LISTAR_BL_UNI_DAO cndUnidaDAO;
	private intra_condominios icBEAN = new intra_condominios();
	private intra_cndunidade cndUnidaBEAN = new intra_cndunidade();
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	private StreamedContent stream;

	// ATRIBUTOS
	private List<intra_cndunidade> listaDeBloco, listaDeUnidade;
	private String codigo;
	private String nomeCondo;
	private String cabecalho;
	private String nomeArquivo;

	// GET X SET
	/**
	 * @param sessaoMB
	 *            the sessaoMB to set
	 */
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	/**
	 * @return the icBEAN
	 */
	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	/**
	 * @param icBEAN
	 *            the icBEAN to set
	 */
	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
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
	 * @return the listaDeBloco
	 */
	public List<intra_cndunidade> getListaDeBloco() {
		if (this.icBEAN.getCodigo() > 0) {
			this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
			this.listaDeBloco = null;
			this.listaDeBloco = this.cndUnidaDAO.listaDeBloco(this.icBEAN.getCodigo());
		}
		return listaDeBloco;
	}

	/**
	 * @param listaDeBloco
	 *            the listaDeBloco to set
	 */
	public void setListaDeBloco(List<intra_cndunidade> listaDeBloco) {
		this.listaDeBloco = listaDeBloco;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the lstaDeUnidade
	 */
	public List<intra_cndunidade> getListaDeUnidade() {
		if (this.sessaoMB.getGerenteSelecionado() != null && this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
			if (this.cndUnidaBEAN.getBloco() != null) {
				if (!this.cndUnidaBEAN.getBloco().trim().isEmpty()) {
					this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
					this.listaDeUnidade = null;
					this.listaDeUnidade = this.cndUnidaDAO.listaDeUnidade(this.icBEAN.getCodigo(),
							this.cndUnidaBEAN.getBloco());
				}
			}
		}
		return listaDeUnidade;
	}

	/**
	 * @param lstaDeUnidade
	 *            the lstaDeUnidade to set
	 */
	public void setListaDeUnidade(List<intra_cndunidade> listaDeUnidade) {
		this.listaDeUnidade = listaDeUnidade;
	}

	/**
	 * @return the cndUnidaBEAN
	 */
	public intra_cndunidade getCndUnidaBEAN() {
		return cndUnidaBEAN;
	}

	/**
	 * @param cndUnidaBEAN
	 *            the cndUnidaBEAN to set
	 */
	public void setCndUnidaBEAN(intra_cndunidade cndUnidaBEAN) {
		this.cndUnidaBEAN = cndUnidaBEAN;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
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

	// ↓ MÉTODO PARA RETORNAR O CÓDIGO E O NOME DO CONDOMÍNIO ↓

	public void retornaNomeCondominio() {
		for (intra_condominios c : sessaoMB.getListaDeCondominios()) {
			if (c.getCodigo() == this.icBEAN.getCodigo()) {
				this.nomeCondo = c.getNome();
				this.icBEAN.setNomeGerente(c.getNomeGerente());
				this.icBEAN.setEmailGerente(c.getEmailGerente());
				this.icBEAN.setCodigoGerente(c.getCodigoGerente());
				this.icBEAN.setNome(c.getNome());
			}
		}
	}

	// ↓ MÉTODO PARA GERAR RELATÓRIO DE E-MAIL ↓
	public void gerarelatorioExcel() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		parametros.put("condominio", this.icBEAN.getCodigo());
		rju.geraRel2(parametros, "QueryEmail", "QueryEmail", 2);

		this.stream = rju.getStream();
	}

	// ↓ MÉTODOS PARA IMPRESSÃO ↓

	public void gerarPresenca() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();

		String nome = "";
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		if (this.cndUnidaBEAN.getBloco().trim().isEmpty()) {
			parametros.put("mensagem", this.cabecalho);
			parametros.put("condominio_num", (short) this.icBEAN.getCodigo());
			nome = "Condominos";
		} else {
			parametros.put("mensagem", this.cabecalho);
			parametros.put("condominio_num", (short) this.icBEAN.getCodigo());
			parametros.put("bloco_num", this.cndUnidaBEAN.getBloco());
			nome = "Condominos_Bloco";
		}
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		this.downloadPDF(rju.geraRelSiga(parametros, nome, nome, 1));
	}

	public void gerarContatos() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();

		String nome = "";
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		if (this.cndUnidaBEAN.getBloco().trim().isEmpty()) {

			parametros.put("textfield", this.cabecalho);
			parametros.put("condominio_num", (short) this.icBEAN.getCodigo());
			nome = "Condominos_Contatos";

		} else {
			parametros.put("textfield", this.cabecalho);
			parametros.put("condominio_num", (short) this.icBEAN.getCodigo());
			parametros.put("bloco_num", this.cndUnidaBEAN.getBloco());
			nome = "Condominos_Contatos_Bloco";
		}

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		this.downloadPDF(rju.geraRelSiga(parametros, nome, nome, 1));
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
			response.setHeader("Content-Disposition", "inline;filename=\"ListaPresenca.pdf\"");
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
