package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.ConsultaPreLancamentosDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.omaonline.dao.FinanceiroDAO;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cndpagar_followup;

@ViewScoped
@ManagedBean(name="fiscalPre")
public class FiscalPreLancto implements Serializable{

	private static final long serialVersionUID = 1L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private List<cndpagar> listaPreLancamento;
	private cndpagar cndpagarMB;
	private List<cndpagar_followup> listaDeFollowUp;

	private int opcaoFiltro;
	private String obsLancto;

	public List<cndpagar_followup> getListaDeFollowUp() {
		return listaDeFollowUp;
	}

	public void setListaDeFollowUp(List<cndpagar_followup> listaDeFollowUp) {
		this.listaDeFollowUp = listaDeFollowUp;
	}

	public String getObsLancto() {
		return obsLancto;
	}

	public void setObsLancto(String obsLancto) {
		this.obsLancto = obsLancto;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public cndpagar getCndpagarMB() {
		return cndpagarMB;
	}

	public void setCndpagarMB(cndpagar cndpagarMB) {
		this.cndpagarMB = cndpagarMB;
	}

	public int getOpcaoFiltro() {
		return opcaoFiltro;
	}

	public void setOpcaoFiltro(int opcaoFiltro) {
		this.opcaoFiltro = opcaoFiltro;
	}

	public List<cndpagar> getListaPreLancamento() {
		if(this.listaPreLancamento == null){
			
		ConsultaPreLancamentosDAO dao = new ConsultaPreLancamentosDAO();
		this.listaPreLancamento = dao.getListaPreLancamento(this.opcaoFiltro);
		}
		return listaPreLancamento;
	}

	public void setListaPreLancamento(List<cndpagar> listaPreLancamento) {
		this.listaPreLancamento = listaPreLancamento;
	}


	public String getNomeCondominio(int codigo) {
		ConsultaPreLancamentosDAO dao = new ConsultaPreLancamentosDAO();
		intra_condominios cnd = dao.getNomeCondominio(codigo);
		return cnd.getCndCodNome();
	}

	public void listarFiscal() {
		this.listaPreLancamento = null;
	}

	public int quantidadeVencimento() {
		ConsultaPreLancamentosDAO dao = new ConsultaPreLancamentosDAO();
		int valor = dao.quantidadeVencimento();
		return valor;
	}

	public int quantidadeSuspenso() {
		ConsultaPreLancamentosDAO dao = new ConsultaPreLancamentosDAO();
		int valor = dao.quantidadeSuspenso();
		return valor;
	}


	public void abrirFollowUp(cndpagar pagar) {
		FinanceiroDAO fncDAO = new FinanceiroDAO();
		this.listaDeFollowUp = fncDAO.listarFollowUp(pagar.getCodigo());
		if (this.listaDeFollowUp.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Não há nenhum Follow Up para este lançamento!", "Não há nenhum Follow Up para este lançamento!"));
		} else {
			RequestContext.getCurrentInstance().execute("PF('dlgFollowUp').show();");
		}
	}

}