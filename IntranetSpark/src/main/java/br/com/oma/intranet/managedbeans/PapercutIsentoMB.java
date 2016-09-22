package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.LogGeralDAO;
import br.com.oma.intranet.dao.PapercutIsentoDAO;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_papercut_isento;
import br.com.oma.intranet.util.Mensagens;

@ViewScoped
@ManagedBean(name = "paperIsentoBean")
public class PapercutIsentoMB extends Mensagens implements Serializable{

	private static final long serialVersionUID = 1L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private PapercutIsentoDAO dao;
	private LogGeralDAO log;
	private intra_papercut_isento papercutIsentoBean = new intra_papercut_isento();

	// ATRIBUTOS
	private List<intra_papercut_isento> listaPapercutIsento;
	private List<String> selectCondominios;

	private int condominio;
	private boolean selectAta;
	private boolean selectBalancete;
	private boolean selectCircular;
	private boolean selectConvReg;
	private boolean selectConvocacao;
	private boolean selectGeral;
	private boolean selectImpCol;

	public intra_papercut_isento getPapercutIsentoBean() {
		return papercutIsentoBean;
	}

	public void setPapercutIsentoBean(intra_papercut_isento papercutIsentoBean) {
		this.papercutIsentoBean = papercutIsentoBean;
	}

	public List<intra_papercut_isento> getListaPapercutIsento() {
		if (this.listaPapercutIsento == null) {
			this.dao = new PapercutIsentoDAO();
			this.listaPapercutIsento = this.dao.listarPapercutIsento();
		}
		return listaPapercutIsento;
	}

	public void setListaPapercutIsento(List<intra_papercut_isento> listaPapercutIsento) {
		this.listaPapercutIsento = listaPapercutIsento;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public boolean isSelectAta() {
		return selectAta;
	}

	public void setSelectAta(boolean selectAta) {
		this.selectAta = selectAta;
	}

	public boolean isSelectBalancete() {
		return selectBalancete;
	}

	public void setSelectBalancete(boolean selectBalancete) {
		this.selectBalancete = selectBalancete;
	}

	public boolean isSelectCircular() {
		return selectCircular;
	}

	public void setSelectCircular(boolean selectCircular) {
		this.selectCircular = selectCircular;
	}

	public boolean isSelectConvReg() {
		return selectConvReg;
	}

	public void setSelectConvReg(boolean selectConvReg) {
		this.selectConvReg = selectConvReg;
	}

	public boolean isSelectConvocacao() {
		return selectConvocacao;
	}

	public void setSelectConvocacao(boolean selectConvocacao) {
		this.selectConvocacao = selectConvocacao;
	}

	public boolean isSelectGeral() {
		return selectGeral;
	}

	public void setSelectGeral(boolean selectGeral) {
		this.selectGeral = selectGeral;
	}

	public boolean isSelectImpCol() {
		return selectImpCol;
	}

	public void setSelectImpCol(boolean selectImpCol) {
		this.selectImpCol = selectImpCol;
	}

	public List<String> getSelectCondominios() {
		return selectCondominios;
	}

	public void setSelectCondominios(List<String> selectCondominios) {
		this.selectCondominios = selectCondominios;
	}

	// METODOS
	public void salvar() {
		this.dao = new PapercutIsentoDAO();
		this.log = new LogGeralDAO(); 
				
		if (this.valida()) {

			for (String condo : selectCondominios) {
				this.papercutIsentoBean = new intra_papercut_isento();
				this.papercutIsentoBean.setCondominio(Integer.valueOf(condo));
				
				this.papercutIsentoBean.setAta(this.selectAta);
				this.papercutIsentoBean.setBalancete(this.selectBalancete);
				this.papercutIsentoBean.setCirculares(this.selectCircular);
				this.papercutIsentoBean.setConvencaoRegulamento(this.selectConvReg);
				this.papercutIsentoBean.setConvocacao(this.selectConvocacao);
				this.papercutIsentoBean.setGeral(this.selectGeral);
				this.papercutIsentoBean.setImpressaoColorida(this.selectImpCol);
				
				this.dao.salvarPaperIsento(this.papercutIsentoBean);
				
				intra_log_geral lg = new intra_log_geral(this.papercutIsentoBean.getCondominio(),
						this.sessaoMB.getUsuario().getEmail(), "intra_papercut_isento", true, false, false,
						this.papercutIsentoBean.toString(), new Date(), 0, null, null);  
						
				this.log.logGeral(lg);
			}

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Salvo!"));
			RequestContext.getCurrentInstance().update("frmPaperIsento:msg");
			this.listaPapercutIsento = null;
			this.selectCondominios = null;
			this.selectAta = false;
			this.selectBalancete = false;
			this.selectCircular = false;
			this.selectConvReg = false;
			this.selectConvocacao = false;
			this.selectGeral = false;
			this.selectImpCol = false;
		}

	}
	
	public void excluir(intra_papercut_isento isento){
		this.log = new LogGeralDAO();
		this.papercutIsentoBean = isento;
		if(this.papercutIsentoBean != null){
			if(this.papercutIsentoBean.getCondominio() > 0){
				this.dao = new PapercutIsentoDAO();
				intra_log_geral lg = new intra_log_geral(this.papercutIsentoBean.getCondominio(),
						this.sessaoMB.getUsuario().getEmail(), "intra_papercut_isento", false, false, true,
						this.papercutIsentoBean.toString(), new Date(), 0, null, null);  
						
				this.log.logGeral(lg);
				this.dao.excluirIsencao(this.papercutIsentoBean);
				this.msgExclusao();
				this.papercutIsentoBean = new intra_papercut_isento();
				this.listaPapercutIsento = null;
				this.selectCondominios = null;
				this.selectAta = false;
				this.selectBalancete = false;
				this.selectCircular = false;
				this.selectConvReg = false;
				this.selectConvocacao = false;
				this.selectGeral = false;
				this.selectImpCol = false;
			}
		}
	}

	public boolean valida() {
		boolean valida = true;
		if (this.selectCondominios == null & this.selectCondominios.isEmpty()) {
			valida = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione pelo menos um condomínio!"));
			RequestContext.getCurrentInstance().update("frmPaperIsento:msg");
		}

		return valida;
	}
}
