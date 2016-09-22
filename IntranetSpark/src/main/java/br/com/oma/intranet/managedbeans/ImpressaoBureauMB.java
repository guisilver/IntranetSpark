package br.com.oma.intranet.managedbeans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.EmissorDAO;
import br.com.oma.intranet.dao.ImpressaoBureauDAO;
import br.com.oma.intranet.entidades.intra_emissor;
import br.com.oma.intranet.entidades.intra_emissor_arquivos;
import br.com.oma.intranet.entidades.intra_lote;
import br.com.oma.intranet.entidades.intra_lote_arquivos;

@ViewScoped
@ManagedBean
public class ImpressaoBureauMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -855955637759158905L;

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private intra_lote loteSelecionado;
	private List<intra_lote> listaLotes;
	private List<intra_lote> fltrLotes;
	private int arquivoCodigo;
	private char recebido = 'N';
	private String nomeArquivo;
	private Date dataInicio, dataFim;
	private boolean destinatario, emissor1, emissor2;

	public ImpressaoBureauMB() {
		this.dataInicio = new DateTime().minusDays(7).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime().withHourOfDay(23).withMinuteOfHour(59).toDate();
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public intra_lote getLoteSelecionado() {
		return loteSelecionado;
	}

	public void setLoteSelecionado(intra_lote loteSelecionado) {
		this.loteSelecionado = loteSelecionado;
	}

	public List<intra_lote> getListaLotes() throws Exception {
		try {
			if (this.dataInicio == null || this.dataFim == null) {
				throw new Exception("Insira datas de início e fim para a pesquisa!");
			} else {
				this.dataFim = new DateTime(this.dataFim).withHourOfDay(23).withMinuteOfHour(59).toDate();
				if (this.listaLotes == null) {
					try {
						ImpressaoBureauDAO dao = new ImpressaoBureauDAO();
						this.listaLotes = new ArrayList<>();
						List<intra_lote> resultList = dao.getListaTodosLotes(this.dataInicio, this.dataFim);
						switch (this.recebido) {
						case 'T':
							this.listaLotes = resultList;
							break;
						case 'S':
							for (intra_lote lote : resultList) {
								if (lote.isConferido()) {
									this.listaLotes.add(lote);
								}
							}
							break;
						case 'N':
							for (intra_lote lote : resultList) {
								if (!lote.isConferido()) {
									this.listaLotes.add(lote);
								}
							}
							break;
						default:
							break;
						}
						this.fltrLotes = null;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
		return listaLotes;
	}

	public void setListaLotes(List<intra_lote> listaLotes) {
		this.listaLotes = listaLotes;
	}

	public List<intra_lote> getFltrLotes() {
		return fltrLotes;
	}

	public void setFltrLotes(List<intra_lote> fltrLotes) {
		this.fltrLotes = fltrLotes;
	}

	public int getArquivoCodigo() {
		return arquivoCodigo;
	}

	public void setArquivoCodigo(int arquivoCodigo) {
		this.arquivoCodigo = arquivoCodigo;
	}

	public char getRecebido() {
		return recebido;
	}

	public void setRecebido(char recebido) {
		this.recebido = recebido;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
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

	public void receberArquivo(intra_lote_arquivos arquivo) {
		arquivo.setSituacao("Recebido");
		arquivo.setDataConferido(new Date());
		this.verificarConferido();
	}

	public void removerDataRecebimento(intra_lote_arquivos arquivo) {
		arquivo.setSituacao("Pendente");
		arquivo.setDataConferido(null);
		this.verificarConferido();
	}

	public void salvar() {
		try {
			ImpressaoBureauDAO dao = new ImpressaoBureauDAO();
			dao.salvar(this.loteSelecionado, this.sessaoMB.getUsuario().getNome(), this.sessaoMB.getIpUser());
			this.listaLotes = null;
			this.fltrLotes = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocorreu um erro ao salvar!", ""));
		}
	}

	public void excluir() {
		try {
			ImpressaoBureauDAO dao = new ImpressaoBureauDAO();
			intra_lote global = dao.pesquisaGlobalPorCodigo(this.loteSelecionado.getCodigo());
			dao.excluir(global, this.sessaoMB.getUsuario().getNome(), this.sessaoMB.getIpUser());
			this.listaLotes = null;
			this.fltrLotes = null;
			this.limparFiltroTbl();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
			RequestContext.getCurrentInstance().execute("PF('dlgExclusao').hide();");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", e.getMessage()));
		}
	}

	public StreamedContent getDownload() {
		StreamedContent download = null;
		for (intra_lote_arquivos aux : this.loteSelecionado.getArquivos()) {
			if (aux.getCodigo() == this.arquivoCodigo) {
				download = new DefaultStreamedContent(new ByteArrayInputStream(aux.getArquivo()), "",
						aux.getNomeArquivo());
			}
		}
		if (download == null) {
			try {
				throw new Exception("Erro!");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}
		}
		return download;
	}

	public void filtrarListaLotes() {
		this.listaLotes = null;
		this.fltrLotes = null;
		this.nomeArquivo = null;
		this.dataInicio = new DateTime(this.dataInicio).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime(this.dataFim).withHourOfDay(23).withMinuteOfHour(59).toDate();
	}

	public void pesquisaLoteComNomeArquivo() {
		this.fltrLotes = null;
		this.listaLotes = new ArrayList<>();
		ImpressaoBureauDAO dao = new ImpressaoBureauDAO();
		this.dataInicio = new DateTime(this.dataInicio).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime(this.dataFim).withHourOfDay(23).withMinuteOfHour(59).toDate();
		if (this.nomeArquivo != null && !this.nomeArquivo.isEmpty()) {
			List<intra_lote> listaLotes = dao.pesquisaLoteComNomeArquivo(this.nomeArquivo, this.dataInicio,
					this.dataFim);
			if (listaLotes == null || listaLotes.size() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum resultado encontrado", ""));
			} else {
				switch (this.recebido) {
				case 'T':
					this.listaLotes = listaLotes;
					break;
				case 'S':
					for (intra_lote lote : listaLotes) {
						if (lote.isConferido()) {
							this.listaLotes.add(lote);
						}
					}
					break;
				case 'N':
					for (intra_lote lote : listaLotes) {
						if (!lote.isConferido()) {
							this.listaLotes.add(lote);
						}
					}
					break;
				default:
					break;
				}
			}
		} else {
			List<intra_lote> listaLotes = dao.getListaTodosLotes(this.dataInicio, this.dataFim);
			switch (this.recebido) {
			case 'T':
				this.listaLotes = listaLotes;
				break;
			case 'S':
				for (intra_lote lote : listaLotes) {
					if (lote.isConferido()) {
						this.listaLotes.add(lote);
					}
				}
				break;
			case 'N':
				for (intra_lote lote : listaLotes) {
					if (!lote.isConferido()) {
						this.listaLotes.add(lote);
					}
				}
				break;
			default:
				break;
			}
		}
	}

	public void showDlgArquivos(int codigo) {
		ImpressaoBureauDAO dao = new ImpressaoBureauDAO();
		this.loteSelecionado = dao.pesquisaLotePorCodigo(codigo);
		if (this.loteSelecionado != null) {
			if (this.loteSelecionado.getServico().equals("EMISSOR")) {
				RequestContext.getCurrentInstance().execute("PF('dlgEmissor').show();");
				List<intra_emissor> listaEmissores = dao.pesquisaEmissorLote(this.loteSelecionado);
				if (listaEmissores != null && listaEmissores.size() > 0) {
					this.loteSelecionado.setEmissor(listaEmissores.get(0));
					this.renderizaBotoesDwld();
				}
			} else {
				this.loteSelecionado.getArquivos();
				RequestContext.getCurrentInstance().execute("PF('dlgArquivos').show();");
			}
		}
	}

	public StreamedContent getDwnldDest() {
		StreamedContent download = null;
		if (this.loteSelecionado != null && this.loteSelecionado.getEmissor() != null) {
			download = new DefaultStreamedContent(
					new ByteArrayInputStream(this.loteSelecionado.getEmissor().getArquivos().getTxtDest()), "",
					this.loteSelecionado.getEmissor().getArquivos().getNomeTxtDest());
		} else {
			try {
				throw new Exception("Erro!");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}
		}
		return download;
	}

	public StreamedContent getDwnldEmissor1() {
		StreamedContent download = null;
		if (this.loteSelecionado != null && this.loteSelecionado.getEmissor() != null) {
			download = new DefaultStreamedContent(
					new ByteArrayInputStream(this.loteSelecionado.getEmissor().getArquivos().getEmissor1()), "",
					this.loteSelecionado.getEmissor().getArquivos().getNomeEmissor1());
		} else {
			try {
				throw new Exception("Erro!");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}
		}
		return download;
	}

	public StreamedContent getDwnldEmissor2() {
		StreamedContent download = null;
		if (this.loteSelecionado != null && this.loteSelecionado.getEmissor() != null) {
			download = new DefaultStreamedContent(
					new ByteArrayInputStream(this.loteSelecionado.getEmissor().getArquivos().getEmissor2()), "",
					this.loteSelecionado.getEmissor().getArquivos().getNomeEmissor2());
		} else {
			try {
				throw new Exception("Erro!");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}
		}
		return download;
	}

	public void cancelarArquivo(intra_lote_arquivos arquivo) {
		arquivo.setSituacao("Cancelado");
		arquivo.setDataConferido(new Date());
		this.verificarConferido();
	}

	public void removerCancelarArquivo(intra_lote_arquivos arquivo) {
		arquivo.setSituacao("Pendente");
		arquivo.setDataConferido(null);
		this.verificarConferido();
	}

	public void receberEmissor() {
		Date data = new Date();
		for (intra_lote_arquivos lote : this.loteSelecionado.getArquivos()) {
			lote.setDataConferido(data);
			lote.setSituacao("Recebido");
		}
		this.verificarConferido();
		this.loteSelecionado.getEmissor().setSituacao("Recebido");
		this.loteSelecionado.getEmissor().setDataConferido(data);
	}

	public void removerDataRecebimentoEmissor() {
		for (intra_lote_arquivos lote : this.loteSelecionado.getArquivos()) {
			lote.setDataConferido(null);
			lote.setSituacao("Pendente");
		}
		this.verificarConferido();
		this.loteSelecionado.getEmissor().setSituacao("Pendente");
		this.loteSelecionado.getEmissor().setDataConferido(null);
	}

	public void cancelarArquivoEmissor() {
		Date data = new Date();
		for (intra_lote_arquivos lote : this.loteSelecionado.getArquivos()) {
			lote.setDataConferido(data);
			lote.setSituacao("Recebido");
		}
		this.verificarConferido();
		this.loteSelecionado.getEmissor().setSituacao("Cancelado");
		this.loteSelecionado.getEmissor().setDataConferido(data);

	}

	public void removerCancelarArquivoEmissor() {
		for (intra_lote_arquivos lote : this.loteSelecionado.getArquivos()) {
			lote.setDataConferido(null);
			lote.setSituacao("Pendente");
		}
		this.verificarConferido();
		this.loteSelecionado.getEmissor().setSituacao("Pendente");
		this.loteSelecionado.getEmissor().setDataConferido(null);
	}

	public void verificarConferido() {
		boolean verdadeiro = true;
		for (intra_lote_arquivos aux : this.loteSelecionado.getArquivos()) {
			if (aux.getDataConferido() == null) {
				verdadeiro = false;
			}
		}
		if (verdadeiro) {
			this.loteSelecionado.setConferido(true);
		} else {
			this.loteSelecionado.setConferido(false);
		}
	}

	public int pesquisaQtdArquivos() {
		if (this.loteSelecionado != null) {
			ImpressaoBureauDAO dao = new ImpressaoBureauDAO();
			return dao.pesquisaQtdArquivos(this.loteSelecionado);
		}
		return 0;
	}

	public void renderizaBotoesDwld() {
		EmissorDAO dao = new EmissorDAO();
		List<intra_emissor_arquivos> listaRetorno = dao.pesquisaArquivosEmissor(this.loteSelecionado.getEmissor());
		if (listaRetorno != null) {
			this.loteSelecionado.getEmissor().setArquivos(listaRetorno.get(0));
		}
		if (this.loteSelecionado.getEmissor() != null
				&& this.loteSelecionado.getEmissor().getArquivos().getTxtDest() != null) {
			this.destinatario = true;
		} else {
			this.destinatario = false;
		}

		if (this.loteSelecionado.getEmissor() != null
				&& this.loteSelecionado.getEmissor().getArquivos().getEmissor1() != null) {
			this.emissor1 = true;
		} else {
			this.emissor1 = false;
		}

		if (this.loteSelecionado.getEmissor() != null
				&& this.loteSelecionado.getEmissor().getArquivos().getEmissor2() != null) {
			this.emissor2 = true;
		} else {
			this.emissor2 = false;
		}
	}

	public void limparFiltroTbl() {
		DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmTblLote:tblLotes");
		d.setValue(null);
		RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
	}
}
