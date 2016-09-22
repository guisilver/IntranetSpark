package br.com.oma.intranet.managedbeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.oma.intranet.dao.ProtocolosDAO;
import br.com.oma.intranet.entidades.intra_assembleia;
import br.com.oma.intranet.entidades.intra_grupo_gerente;

@ViewScoped
@ManagedBean
public class ProtocolosMB {

	/* DEPENDENCIAS */
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private List<intra_assembleia> lstAssembleias, lstAssembleiasSelecionadas;
	private ProtocolosDAO ptclRep;
	private intra_grupo_gerente gerente;

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public void setLstAssembleias(List<intra_assembleia> lstAssembleias) {
		this.lstAssembleias = lstAssembleias;
	}

	public List<intra_assembleia> getLstAssembleias() {
		if (this.lstAssembleias == null) {
			ptclRep = new ProtocolosDAO();
			this.lstAssembleias = ptclRep.getLstAssembleias();
		}
		return lstAssembleias;
	}

	public void setLstEditais(List<intra_assembleia> lstAssembleias) {
		this.lstAssembleias = lstAssembleias;
	}

	public List<intra_assembleia> getLstAssembleiasSelecionadas() {
		return lstAssembleiasSelecionadas;
	}

	public void setLstAssembleiasSelecionadas(
			List<intra_assembleia> lstAssembleiasSelecionadas) {
		this.lstAssembleiasSelecionadas = lstAssembleiasSelecionadas;
	}

	public intra_grupo_gerente getGerente() {
		return gerente;
	}

	public void setGerente(intra_grupo_gerente gerente) {
		this.gerente = gerente;
	}

	public void salvarProtocolos() {
		if (this.lstAssembleiasSelecionadas.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Não existem protocolos pendentes!", ""));
		} else {
			this.ptclRep = new ProtocolosDAO();
			this.ptclRep.salvarProtocolos(this.lstAssembleiasSelecionadas);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Salvo com sucesso!", ""));
			this.lstAssembleiasSelecionadas = null;
			this.lstAssembleias = null;
			this.ptclRep = null;
		}
	}
}
