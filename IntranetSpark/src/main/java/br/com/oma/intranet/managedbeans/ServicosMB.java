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
import br.com.oma.intranet.dao.ServicosDao;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_servicos;

@ManagedBean(name = "servicos")
@ViewScoped
public class ServicosMB implements Serializable{

	private static final long serialVersionUID = 1L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	
	//OBJETOS
	private ServicosDao dao;
	private LogGeralDAO logGeralDao;
	private intra_servicos servicosBean;
	
	//ATRIBUTOS
	private List<intra_servicos> listaDeServicos, selectServicos;
	private Date periodoInicial;
	private Date periodoFinal;
	private Date dataProcessamento;
	
	//GET SET
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public intra_servicos getServicosBean() {
		return servicosBean;
	}

	public void setServicosBean(intra_servicos servicosBean) {
		this.servicosBean = servicosBean;
	}

	public List<intra_servicos> getListaDeServicos() {
		return listaDeServicos;
	}

	public void setListaDeServicos(List<intra_servicos> listaDeServicos) {
		this.listaDeServicos = listaDeServicos;
	}

	public List<intra_servicos> getSelectServicos() {
		return selectServicos;
	}

	public void setSelectServicos(List<intra_servicos> selectServicos) {
		this.selectServicos = selectServicos;
	}
	
	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	//METODOS
	public void listarServicos(){
		this.dao = new ServicosDao();
		if (this.periodoInicial == null && this.periodoFinal == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Informe período para pesquisa!"));
		}else{
			this.listaDeServicos = this.dao.listarServicos(this.periodoInicial, this.periodoFinal);
		}
	}
	
	public boolean valida() {
		if (this.periodoInicial == null && this.periodoFinal == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Informe período para pesquisa!"));
			RequestContext.getCurrentInstance().execute("PF('salvar').hide()");
			return false;
		} else if (this.selectServicos == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Ocorreu um erro inesperado, tente novamente!"));
			RequestContext.getCurrentInstance().execute("PF('salvar').hide()");
			return false;
		} else if (this.selectServicos.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione um item!"));
			RequestContext.getCurrentInstance().execute("PF('salvar').hide()");
			return false;
		} else if (this.dataProcessamento == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Informe data de Processamento!"));
			RequestContext.getCurrentInstance().execute("PF('salvar').hide()");
			return false;
		}else{
			return true;
		}
	}
	
	public void salvar() {
		this.dao = new ServicosDao();
		this.logGeralDao = new LogGeralDAO();
		if (this.valida()) {
			for (intra_servicos serv : selectServicos) {
				if (serv.getCotaCondominio() > 0) {
					int codigoDoServico = this.dao.retornaCodigoDoServico(serv.getCondominio(), 8.0);
					int codigoMsvcon = this.dao.retornaCodigoMsvcon();
					this.dao.salvarCobrancaServico(codigoMsvcon, codigoDoServico, serv.getCotaCondominio(),
							"Serviços - IMPRESSÃO COBRANÇA - COTA COND", this.dataProcessamento);
					intra_log_geral lg = new intra_log_geral(serv.getCondominio(),
							this.sessaoMB.getUsuario().getEmail(), "sigadm.dbo.cndsvmov", true, false, false,
							"Insert Serviços codigo:" + codigoMsvcon, new Date(), 0, "", null);
					this.logGeralDao.logGeral(lg);
				}
			}
			
			for (intra_servicos serv : selectServicos) {
				if (serv.getCotaExtra() > 0) {
					int codigoDoServico = this.dao.retornaCodigoDoServico(serv.getCondominio(), 9.0);
					int codigoMsvcon = this.dao.retornaCodigoMsvcon();
					this.dao.salvarCobrancaServico(codigoMsvcon, codigoDoServico, serv.getCotaExtra(),
							"Serviços - IMPRESSÃO COBRANÇA- COTA EXTRA", this.dataProcessamento);
					intra_log_geral lg = new intra_log_geral(serv.getCondominio(),
							this.sessaoMB.getUsuario().getEmail(), "sigadm.dbo.cndsvmov", true, false, false,
							"Insert Serviços codigo:" + codigoMsvcon, new Date(), 0, "", null);
					this.logGeralDao.logGeral(lg);
				}
			}
			
			for(intra_servicos serv : selectServicos){
				this.dao.updateCndrecib(serv.getCondominio(), this.periodoInicial, periodoFinal);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Salvo!"));
			RequestContext.getCurrentInstance().execute("PF('salvar').hide()");
			RequestContext.getCurrentInstance().execute("PF('processamento').hide();");
			RequestContext.getCurrentInstance().update("frmServicos:tblServico");
			
			this.listaDeServicos = null;
			this.periodoInicial = null;
			this.periodoFinal = null;
			this.dataProcessamento = null;
		}
	}
	
}
