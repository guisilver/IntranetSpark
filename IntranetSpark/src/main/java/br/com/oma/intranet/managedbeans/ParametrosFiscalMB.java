package br.com.oma.intranet.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.oma.intranet.dao.ParametrosFiscalDAO;
import br.com.oma.intranet.entidades.intra_fiscal_param;

@ViewScoped
@ManagedBean
public class ParametrosFiscalMB {

	private intra_fiscal_param fiscalParam = new intra_fiscal_param();

	public intra_fiscal_param getFiscalParam() {
		if (this.fiscalParam == null) {
			this.fiscalParam = new intra_fiscal_param();
		}
		return fiscalParam;
	}

	public void setFiscalParam(intra_fiscal_param fiscalParam) {
		this.fiscalParam = fiscalParam;
	}

	@PostConstruct
	public void init() {
		this.pesquisarFiscalParam();
	}

	public void pesquisarFiscalParam() {
		ParametrosFiscalDAO dao = new ParametrosFiscalDAO();
		this.fiscalParam = dao.pesquisarFiscalParam(1);
	}

	public void salvarFiscalParam() {
		try {
			ParametrosFiscalDAO dao = new ParametrosFiscalDAO();
			dao.salvarFiscalParam(this.fiscalParam);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

}
