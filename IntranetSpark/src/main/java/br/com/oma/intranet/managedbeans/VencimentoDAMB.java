package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import br.com.oma.intranet.dao.ConcessionariaDAO;
import br.com.oma.intranet.dao.VencimentoDADAO;
import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.util.DownloadUtil;

@ViewScoped
@ManagedBean
public class VencimentoDAMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7044027512987088981L;
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private List<DebitoAutomatico> listaVencimentoDA, fltrVencimentoDA;
	private Date dataInicio, dataFim;
	private int idImg;
	private int fonte;
	private int contadorDA = 0;
	private boolean comImg, semImg;
	private int situacao = 1;

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public List<DebitoAutomatico> getListaVencimentoDA() {
		return listaVencimentoDA;
	}

	public void setListaVencimentoDA(List<DebitoAutomatico> listaVencimentoDA) {
		this.listaVencimentoDA = listaVencimentoDA;
	}

	public List<DebitoAutomatico> getFltrVencimentoDA() {
		return fltrVencimentoDA;
	}

	public void setFltrVencimentoDA(List<DebitoAutomatico> fltrVencimentoDA) {
		this.fltrVencimentoDA = fltrVencimentoDA;
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

	public int getIdImg() {
		return idImg;
	}

	public void setIdImg(int idImg) {
		this.idImg = idImg;
	}

	public int getFonte() {
		return fonte;
	}

	public void setFonte(int fonte) {
		this.fonte = fonte;
	}

	public int getContadorDA() {
		return contadorDA;
	}

	public void setContadorDA(int contadorDA) {
		this.contadorDA = contadorDA;
	}

	public boolean isComImg() {
		return comImg;
	}

	public void setComImg(boolean comImg) {
		this.comImg = comImg;
	}

	public boolean isSemImg() {
		return semImg;
	}

	public void setSemImg(boolean semImg) {
		this.semImg = semImg;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	@PostConstruct
	public void init() {
		this.dataInicio = new DateTime(new Date()).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime(new Date()).withMillisOfDay(0).plusDays(10).toDate();
		this.semImg = true;
		this.pesquisarVencimentoDA();
	}

	public void pesquisarVencimentoDA() {
		try {
			this.contadorDA = 0;
			if (this.sessaoMB.getGerenteSelecionado() != null) {
				VencimentoDADAO dao = new VencimentoDADAO();
				if (this.situacao == 1) {
					this.listaVencimentoDA = dao.getListaVencimentoDA(this.dataInicio, this.dataFim,
							this.sessaoMB.getGerenteSelecionado().getCodigo());
				}

				if (this.situacao == 2) {
					this.listaVencimentoDA = dao.getListaVencimentoDAPagos(this.dataInicio, this.dataFim,
							this.sessaoMB.getGerenteSelecionado().getCodigo());
				}
				List<DebitoAutomatico> l = new ArrayList<>();
				if (this.comImg) {
					for (DebitoAutomatico aux1 : this.listaVencimentoDA) {
						if (aux1.getId_catalogo() > 0) {
							l.add(aux1);
						}
					}
				}
				if (this.semImg) {
					for (DebitoAutomatico aux2 : this.listaVencimentoDA) {
						if (aux2.getId_catalogo() == 0) {
							l.add(aux2);
						}
					}
				}
				this.listaVencimentoDA = l;
				List<DebitoAutomatico> lContador = new ArrayList<>();
				for (DebitoAutomatico aux : this.listaVencimentoDA) {
					if (aux.getId_catalogo() == 0) {
						lContador.add(aux);
						this.contadorDA++;
					}
				}
				this.limparFiltros();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void baixarImg() {
		try {
			ConcessionariaDAO dao = new ConcessionariaDAO();
			byte[] pdf = dao.pesquisarPDF1(this.idImg);
			if (pdf == null) {
				pdf = dao.pesquisarPDF2(this.idImg);
			}
			if (pdf != null) {
				DownloadUtil.downloadPDF("VencimentoDA", pdf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onCellEdit(CellEditEvent event) {
		try {
			VencimentoDADAO dao = new VencimentoDADAO();
			String obs = String.valueOf(event.getNewValue());
			DebitoAutomatico aux = null;
			if (this.fltrVencimentoDA != null && !this.fltrVencimentoDA.isEmpty()) {
				aux = this.listaVencimentoDA.get(event.getRowIndex());
			} else {
				aux = this.listaVencimentoDA.get(event.getRowIndex());
			}
			if (obs != null && !obs.trim().isEmpty() && aux != null && aux.getIdFatura() > 0) {
				int i = dao.updateObsFatura(aux.getIdFatura(), obs);
				if (i > 0) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atualizado com sucesso!", ""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limparFiltros() {
		DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("frmTblVencimentoDA:tblVencDA");
		if (d != null) {
			d.setValue(null);
			RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
		}
	}

}
