package br.com.oma.omaonline.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import br.com.oma.omaonline.dao.ProtocoloDAO;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.financeiro_imagem;
import br.com.oma.omaonline.entidades.protocolo;
import br.com.oma.omaonline.entidades.protocolo_lancamentos;
import br.com.oma.omaonline.entidades.protocolo_obs;


@ViewScoped
@ManagedBean(name="ProtGEDMB")
public class ProtocoloGEDMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8068912121582277484L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private protocolo protocolo = new protocolo();
	private protocolo_obs obs = new protocolo_obs();
	private ProtocoloDAO protDAO = new ProtocoloDAO();
	private intra_condominios icBEAN = new intra_condominios();
	private intra_grupo_gerente gerenteBEAN = new intra_grupo_gerente();
	
	// ATRIBUTOS
	private List<protocolo> lstProtocolos;
	private List<protocolo> protocoloSelecionado;
	private List<intra_grupo_gerente> listaDeGerentes;
	private List<intra_condominios> listaDeCondominio;
	private Long idImagem;
	private String nomeRelatorio;
	private String listarPor = "P";
	private String nomeCondo;
	private int condominio;
	
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	// GET X SET
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public protocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(protocolo protocolo) {
		this.protocolo = protocolo;
	}

	public protocolo_obs getObs() {
		return obs;
	}

	public void setObs(protocolo_obs obs) {
		this.obs = obs;
	}

	public List<protocolo> getProtocoloSelecionado() {
		return protocoloSelecionado;
	}

	public void setProtocoloSelecionado(List<protocolo> protocoloSelecionado) {
		this.protocoloSelecionado = protocoloSelecionado;
	}

	public List<protocolo> getLstProtocolos() {
		if (this.lstProtocolos == null) {
			this.listarProtocolos();
		}
		return lstProtocolos;
	}

	public void setLstProtocolos(List<protocolo> lstProtocolos) {
		this.lstProtocolos = lstProtocolos;
	}

	public Long getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(Long idImagem) {
		this.idImagem = idImagem;
	}

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public String getListarPor() {
		return listarPor;
	}

	public void setListarPor(String listarPor) {
		this.listarPor = listarPor;
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

	public List<intra_grupo_gerente> getListaDeGerentes() {
		if(this.lstProtocolos != null){
			DataTable d = (DataTable) FacesContext.getCurrentInstance()
					.getViewRoot().findComponent("frmTblProtocolo:tblProtocolos");
			d.setValue(null);
			this.lstProtocolos = null;
			RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
		}
		if (this.listaDeGerentes == null) {
			this.listaDeGerentes = this.retornaGerentes();
		}
		return listaDeGerentes;
	}

	public void setListaDeGerentes(List<intra_grupo_gerente> listaDeGerentes) {
		this.listaDeGerentes = listaDeGerentes;
	}

	public List<intra_condominios> getListaDeCondominio() {
		if (this.gerenteBEAN.getCodigo() > 0) {
			this.protDAO = new ProtocoloDAO();
			this.listaDeCondominio = this.protDAO.listarCondominios(this.gerenteBEAN.getCodigo());
		}
		return listaDeCondominio;
	}

	public void setListaDeCondominio(List<intra_condominios> listaDeCondominio) {
		this.listaDeCondominio = listaDeCondominio;
	}

	public String getNomeCondo() {
		return nomeCondo;
	}

	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	// METODOS
	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome()
					.equals(" Todos")) {
				this.listaDeCondominio = null;
				this.nomeCondo = "";
				this.gerenteBEAN.setCodigo(this.sessaoMB.getListaDeGerente().get(0).getCodigo());
				return this.sessaoMB.getListaDeGerente();
			} else {
				this.listaDeCondominio = null;
				this.nomeCondo = "";
				this.gerenteBEAN.setCodigo(this.sessaoMB.getUsuario().getGrupoGer().get(0).getCodigo());
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}

	public void retornaNomeCondominio() {
		for (intra_condominios c : sessaoMB.getListaDeCondominios()) {
			if (c.getCodigo() == this.condominio) {
				this.nomeCondo = c.getNome();
				if(this.icBEAN == null){
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
	
	public void adicionarLancto() {
		try {
			if (this.idImagem != null && this.idImagem > 0) {
				boolean jaFoiInserido = false;
				for (protocolo_lancamentos aux : this.protocolo
						.getLancamentos()) {
					if (aux.getIdImagem() == this.idImagem) {
						jaFoiInserido = true;
					}
				}
				if (jaFoiInserido) {
					this.msgProtJaExiste();
				} else {
					double valor = 0.0;
					cndpagar lancto = new cndpagar();
					this.protDAO = new ProtocoloDAO();
					financeiro_imagem img = this.protDAO.pesquisaImagem(Short.valueOf(String.valueOf(this.condominio)),	Double.valueOf(this.idImagem));
					List<cndpagar> lstLancamentos = this.protDAO.pesquisaLancto(img);
					if (img.getCodigo() == 0) {
						this.msgProtEtiquetaN();
						this.idImagem = null;
					} else if (lstLancamentos.size() == 0) {
						this.msgProtLanctoN();
						this.idImagem = null;
					} else {
						lancto = lstLancamentos.get(0);
						for (cndpagar aux : lstLancamentos) {
							valor += aux.getValor();
						}

						long nf = 0;
						if (lancto.getNumeroNf() != null) {
							nf = Long.valueOf(lancto.getNumeroNf().toString());
						}

						protocolo_lancamentos plancto = new protocolo_lancamentos(
								lancto.getCodigo(), this.idImagem,
								lancto.getVencimento(), new Date(), null,
								BigDecimal.valueOf(valor), nf,
								lancto.getEmpresa(), this.sessaoMB.getUsuario().getEmail(), "", false);
						this.protocolo.getLancamentos().add(plancto);
						this.msgAdicinado();
						this.idImagem = null;
					}
				}
			} else {
				this.idImagem = null;
				this.msgProtInserirE();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void baixarLancamento(long idImagem) {
		this.idImagem = idImagem;
		if (this.idImagem == null || this.idImagem == 0) {
			this.idImagem = null;
			UIComponent component = FacesContext.getCurrentInstance()
					.getViewRoot()
					.findComponent("frmDetalhesProtocolo:txtIdArquivo");
			((UIInput) component).setValid(false);
			this.msgProtBaixarR();
		} else {
			int contador = 0;
			if (autorizaBaixaProtocolo()) {
				this.msgProtBaixarTR();
			} else {
				protocolo_lancamentos lancto = null;
				for (protocolo_lancamentos aux : this.protocolo
						.getLancamentos()) {
					if (aux.getIdImagem() == this.idImagem) {
						aux.setSituacao(true);
						aux.setRecebidoPor(this.sessaoMB.getUsuario().getEmail());
						aux.setDataRecebimento(new Date());
						lancto = aux;
						contador = 1;
					}
				}
				if (contador == 0) {
					this.msgProtEtiquetaN();
				} else if (lancto == null) {
					this.msgErro();
				} else {

				}
			}
		}
		this.idImagem = null;
	}

	public void removerBaixaLancamento(long idImagem) {
		for (protocolo_lancamentos aux : this.protocolo.getLancamentos()) {
			if (aux.getIdImagem() == idImagem) {
				aux.setSituacao(false);
				aux.setDataRecebimento(null);
				aux.setRecebidoPor(null);
			}
		}
	}

	public void salvarProtocolo() {
		if (this.protocolo == null) {
			this.msgProtErroS();
		} else if (this.protocolo.getLancamentos().size() == 0) {
			this.msgProtErroSV();
		} else if (this.protocolo.getCodigo() == 0) {
			this.protocolo.setData(new Date());
			this.protDAO = new ProtocoloDAO();
			this.protocolo.setCd_cnd(Short.valueOf(String.valueOf(this.condominio)));
			if (this.protocolo.getObs() == null
					|| this.protocolo.getObs().equals("")) {
				this.protocolo.setObs(this.obs.getObs());
			}
			this.protDAO.salvarProtocolo(this.protocolo);
			RequestContext.getCurrentInstance().execute("PF('dlgNovoProtocolo').hide()");
			RequestContext.getCurrentInstance().execute("PF('dlgConfImpressao').show()");
			this.msgSalvo();
			this.lstProtocolos = null;
		} else {
			this.protDAO = new ProtocoloDAO();
			this.protDAO.salvarProtocolo(this.protocolo);
			RequestContext.getCurrentInstance().execute("PF('dlgDetalhesProtocolo').hide()");
			this.msgSalvo();
			this.lstProtocolos = null;
		}
	}

	public void abreDlgNovoProtocolo() {
		this.protocolo = new protocolo();
		this.idImagem = null;
		this.protDAO = new ProtocoloDAO();
		this.obs = this.protDAO.pesquisaObs(Short.valueOf(String.valueOf(this.condominio)));
		this.protocolo.setObs(this.obs.getObs());
		RequestContext.getCurrentInstance().execute("PF('dlgNovoProtocolo').show()");
	}

	public boolean autorizaBaixaProtocolo() {
		if (this.protocolo != null
				&& this.protocolo.getLancamentos().size() > 0) {
			int contador = 0;
			for (protocolo_lancamentos aux : this.protocolo.getLancamentos()) {
				if (aux.isSituacao()) {
					contador++;
				}
			}
			if (contador == this.protocolo.getLancamentos().size()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gerarRelatorio() throws Exception {
		RequestContext.getCurrentInstance().execute("PF('dlgConfImpressao').hide()");
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String caminho_relatorio = servletContext.getRealPath("")
				+ File.separator + "resources" + File.separator + "relatorios"
				+ File.separator;
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		HashMap parametros = new HashMap();
		if (this.protocolo.getObs() != null) {
			parametros.put("obs", this.protocolo.getObs());
		}
		parametros.put("cd_cnd", this.condominio);
		parametros.put("nome_cnd", this.nomeCondo);
		parametros.put("pCodigo", this.protocolo.getCodigo());
		parametros.put("SUBREPORT_DIR", caminho_relatorio);
		
		byte[] retorno = null;
		retorno = rju.geraRel2(parametros, "Protocolo", "Protocolo", 1);
		this.downloadPDF(retorno);
	/*
		try {
			byte[] retorno = rju.geraRel2(parametros, "Protocolo", "Protocolo", 	1);
			this.nomeRelatorio = rju.geraRel2(parametros, "protocolo", "protocolo", 1);
			RequestContext.getCurrentInstance().execute("PF('dlgRelatorio').show()");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

/*	public void excluiRelatorio() {
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		rju.excluiRelatorio(this.nomeRelatorio);
	}*/

	public void excluirProtocolo() {
		if (this.protocolo == null) {
			this.msgProtSelec();
		} else {
			this.protDAO = new ProtocoloDAO();
			this.protDAO.excluirProtocolo(this.protocolo);
			this.msgExclusao();
			RequestContext.getCurrentInstance().execute(("PF('dlgExclui').hide()"));
			RequestContext.getCurrentInstance().execute(("PF('dlgDetalhesProtocolo').hide()"));
			this.lstProtocolos = null;
		}
	}

	public void excluirLanctoProtocolo(int idImagem) {
		Iterator<protocolo_lancamentos> itr = this.protocolo.getLancamentos()
				.iterator();
		while (itr.hasNext()) {
			if (itr.next().getIdImagem() == idImagem) {
				itr.remove();
			}
		}
	}

	public void abreDlgDetalhesProtocolo(int codigoProtocolo) {
		this.idImagem = null;
		this.protDAO = new ProtocoloDAO();
		this.protocolo = this.protDAO.pesquisaProtocolo(codigoProtocolo);
		RequestContext.getCurrentInstance().execute(("PF('dlgDetalhesProtocolo').show();"));
	}

	public void listarProtocolos() {
		this.protDAO = new ProtocoloDAO();
		
		
		List<protocolo> lstRetorno = this.protDAO.listarProtocolos(Short.valueOf(String.valueOf(this.condominio)));
		switch (this.listarPor) {
		case "P":
			this.lstProtocolos = new ArrayList<protocolo>();
			for (protocolo aux : lstRetorno) {
				if (!verificaSituacaoProtocolo(aux)) {
					this.lstProtocolos.add(aux);
				}
			}
			;
			break;
		case "R":
			this.lstProtocolos = new ArrayList<protocolo>();
			for (protocolo aux : lstRetorno) {
				if (verificaSituacaoProtocolo(aux)) {
					this.lstProtocolos.add(aux);
				}
			}
			;
			break;
		case "T":
			this.lstProtocolos = lstRetorno;
		default:
			break;
		}
	}

	public void salvarObs() {
		this.protDAO = new ProtocoloDAO();
		this.obs.setCd_cnd(Short.valueOf(String.valueOf(this.condominio)));
		this.protDAO.salvarObs(this.obs);
		RequestContext.getCurrentInstance().execute("PF('dlgObs').hide()");
		this.msgSalvo();
		this.obs = new protocolo_obs();
	}

	public void abrirDlgObs() {
		this.protDAO = new ProtocoloDAO();
		this.obs = this.protDAO.pesquisaObs(Short.valueOf(String.valueOf(this.condominio)));
		RequestContext.getCurrentInstance().execute("PF('dlgObs').show()");
	}

	public boolean verificaSituacaoProtocolo(protocolo protocolo) {
		boolean todosBaixados = true;
		if (protocolo != null && protocolo.getLancamentos() != null) {
			for (protocolo_lancamentos aux : protocolo.getLancamentos()) {
				if (!aux.isSituacao()) {
					todosBaixados = false;
				}
			}
		}
		return todosBaixados;
	}

	public void reset() {
		this.protocolo = new protocolo();
		this.lstProtocolos = null;
		this.idImagem = null;
		this.obs = new protocolo_obs();
		this.protocoloSelecionado = null;
		this.nomeRelatorio = null;
		this.listarPor = null;
	}
	
	public void limparListas(){
		this.lstProtocolos = null;
		//this.cndUnidaBEAN = new intra_cndunidade();
		this.nomeCondo = "";
		this.icBEAN = new intra_condominios();
	}
	
	public void downloadPDF(byte[] retorno) throws IOException {
		// Prepare.
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext
				.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			// Open file.
			input = new BufferedInputStream(new ByteArrayInputStream(retorno),
					DEFAULT_BUFFER_SIZE);
			// Init servlet response.
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(retorno.length));
			response.setHeader("Content-Disposition",
					"inline;filename=\"Protocolo.pdf\"");
			output = new BufferedOutputStream(response.getOutputStream(),
					DEFAULT_BUFFER_SIZE);
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
