package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.TrocaDeImagemDAO;
import br.com.oma.intranet.entidades.cndespes;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.DownloadUtil;
import br.com.oma.omaonline.entidades.financeiro_imagem;

@ViewScoped
@ManagedBean
public class TrocaDeImagemMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6583908340628903392L;
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private cndespes lancamentoSelecionado;
	private List<cndespes> listaLancamentos;
	private financeiro_imagem imagem;
	private financeiro_imagem imagemSelecionada;
	private List<financeiro_imagem> listaImagens;
	private intra_condominios condominio;
	private Integer conta, etiqueta;
	private Date dataInicio, dataFim;
	private byte[] arquivo;
	private String nomeArquivo;

	public TrocaDeImagemMB() {
		this.dataInicio = new DateTime().withDayOfMonth(1).toDate();
		this.dataFim = new DateTime().toDate();
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public cndespes getLancamentoSelecionado() {
		try {
			if (this.lancamentoSelecionado == null) {
				String codigoLancamento = FacesContext.getCurrentInstance().getExternalContext()
						.getRequestParameterMap().get("codigoLancamento");
				if (codigoLancamento != null && !codigoLancamento.trim().isEmpty()
						&& NumberUtils.isNumber(codigoLancamento)) {
					TrocaDeImagemDAO dao = new TrocaDeImagemDAO();
					this.lancamentoSelecionado = dao.pesquisaLancamentoPorCodigo(Integer.parseInt(codigoLancamento));
					if (this.lancamentoSelecionado == null) {
						throw new Exception("Lançamento não encontrado!");
					} else {
						this.pesquisarImagens(dao, this.lancamentoSelecionado.getNumero());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(cndespes lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	public List<cndespes> getListaLancamentos() {
		return listaLancamentos;
	}

	public void setListaLancamentos(List<cndespes> listaLancamentos) {
		this.listaLancamentos = listaLancamentos;
	}

	public financeiro_imagem getImagem() {
		return imagem;
	}

	public void setImagem(financeiro_imagem imagem) {
		this.imagem = imagem;
	}

	public financeiro_imagem getImagemSelecionada() {
		return imagemSelecionada;
	}

	public void setImagemSelecionada(financeiro_imagem imagemSelecionada) {
		this.imagemSelecionada = imagemSelecionada;
	}

	public List<financeiro_imagem> getListaImagens() {
		return listaImagens;
	}

	public void setListaImagens(List<financeiro_imagem> listaImagens) {
		this.listaImagens = listaImagens;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public Integer getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(Integer etiqueta) {
		this.etiqueta = etiqueta;
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

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public void pesquisarLancamentos() {
		try {
			if (this.condominio == null) {
				throw new Exception("O condomínio deve ser informado!");
			}
			if (this.dataInicio == null || this.dataFim == null) {
				throw new Exception("O período deve ser informado!");
			}
			TrocaDeImagemDAO dao = new TrocaDeImagemDAO();
			this.listaLancamentos = dao.pesquisarLancamentos(this.condominio.getCodigo(), this.conta, this.dataInicio,
					this.dataFim);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abreLancamentoSelecionado(int codigo) {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/troca-de-imagem/alterar-imagem.xhtml?codigoLancamento=" + codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pesquisarImagens(TrocaDeImagemDAO dao, int codigo) {
		this.listaImagens = dao.pesquisarImagens(codigo);
	}

	public financeiro_imagem constroiImagem(TrocaDeImagemDAO dao) {
		financeiro_imagem f = dao.pesquisaImagemEtiquetaSiga(this.etiqueta);
		f.setId(this.etiqueta);
		f.setCodLanctoOma(this.lancamentoSelecionado.getNumero());
		return f;
	}

	public void adicionarImagem() {
		try {
			TrocaDeImagemDAO dao = new TrocaDeImagemDAO();
			financeiro_imagem f = this.constroiImagem(dao);
			dao.adicionarImagem(f, this.sessaoMB.getUsuario().getEmail(), this.lancamentoSelecionado.getNumero(),
					this.lancamentoSelecionado.getNro_lanch());
			this.listaImagens = null;
			this.pesquisarImagens(dao, this.lancamentoSelecionado.getNumero());
			RequestContext.getCurrentInstance().execute("PF('dlgImagem').hide();");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Adicionado com sucesso!", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void validaEtiqueta(TrocaDeImagemDAO dao) throws Exception {
		boolean existe = dao.validaEtiquetaExiste(BigDecimal.valueOf(this.etiqueta));
		if (!existe) {
			throw new Exception("Essa etiqueta não é válida em nosso sistema!");
		}
		boolean usada = dao.validaEtiquetaJaFoiUsada(this.etiqueta);
		if (usada) {
			throw new Exception("Essa etiqueta já foi utilizada!");
		}
	}

	public void excluirImagem(long id) {
		try {
			TrocaDeImagemDAO dao = new TrocaDeImagemDAO();
			dao.excluirImagem(id, this.lancamentoSelecionado.getNumero(), this.lancamentoSelecionado.getNro_lanch());
			this.pesquisarImagens(dao, this.lancamentoSelecionado.getNumero());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirArquivo() {
		try {
			TrocaDeImagemDAO dao = new TrocaDeImagemDAO();
			byte[] arquivo = dao.pesquisarImagemPorCodigo(this.imagemSelecionada.getCodigo());
			DownloadUtil.downloadPDF(this.imagemSelecionada.getNome_arquivo(), arquivo);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"A imagem está corrompida!Não foi possível visualizar.", ""));
		}
	}

	public void imagemExiste() {
		try {
			if (this.etiqueta != null && this.etiqueta > 0) {
				TrocaDeImagemDAO dao = new TrocaDeImagemDAO();
				boolean existe = dao.imagemExiste(this.etiqueta);
				if (!existe) {
					throw new Exception("Nenhuma imagem foi encontrada para esta etiqueta.");
				}
				if (this.listaImagens != null && this.listaImagens.size() > 0) {
					for (financeiro_imagem aux : this.listaImagens) {
						if (aux.getId() == this.etiqueta) {
							throw new Exception("Esta etiqueta já foi associada a este lançamento!");
						}
					}
				}
				RequestContext.getCurrentInstance().execute("PF('dlgImagem').show();");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}
}
