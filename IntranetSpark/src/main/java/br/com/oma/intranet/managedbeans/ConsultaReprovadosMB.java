package br.com.oma.intranet.managedbeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.ConsultaReprovadosDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.omaonline.dao.FinanceiroDAO;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cndpagar_followup;

@ViewScoped
@ManagedBean
public class ConsultaReprovadosMB {

	private List<cndpagar> listaReprovados;
	private List<cndpagar_followup> fltrFollowUp, lstFollowUp;
	private int filtro;

	public List<cndpagar> getListaReprovados() {
		if (this.listaReprovados == null) {
			this.pesquisarLancamentos();
		}
		return listaReprovados;
	}

	public void setListaReprovados(List<cndpagar> listaReprovados) {
		this.listaReprovados = listaReprovados;
	}

	public List<cndpagar_followup> getFltrFollowUp() {
		return fltrFollowUp;
	}

	public void setFltrFollowUp(List<cndpagar_followup> fltrFollowUp) {
		this.fltrFollowUp = fltrFollowUp;
	}

	public List<cndpagar_followup> getLstFollowUp() {
		return lstFollowUp;
	}

	public void setLstFollowUp(List<cndpagar_followup> lstFollowUp) {
		this.lstFollowUp = lstFollowUp;
	}

	public int getFiltro() {
		return filtro;
	}

	public void setFiltro(int filtro) {
		this.filtro = filtro;
	}

	public void abreAlteraPreLancamento(int codigo) {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/sip/pre-lancamento/alterar-pre-lancamento.xhtml?codigoLancamento=" + codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirFollowUp(int codigoLancto) {
		FinanceiroDAO dao = new FinanceiroDAO();
		this.lstFollowUp = dao.listarFollowUp(codigoLancto);
		if (this.lstFollowUp.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Não há nenhum Follow Up para este lançamento!", "Não há nenhum Follow Up para este lançamento!"));
		} else {
			RequestContext.getCurrentInstance().execute("PF('dlgFollowUp').show();");
		}
	}

	public void pesquisarLancamentos() {
		ConsultaReprovadosDAO dao = new ConsultaReprovadosDAO();
		this.listaReprovados = dao.getListaReprovados(this.filtro);
		for (cndpagar aux : this.listaReprovados) {
			intra_condominios c = dao.getNomeCondominio(aux.getCondominio());
			if (c != null) {
				aux.setCndCodNome(aux.getCondominio() + " - " + c.getNome());
			}
		}
	}

}
