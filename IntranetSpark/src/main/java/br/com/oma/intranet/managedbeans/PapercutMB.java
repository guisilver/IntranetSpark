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

import br.com.oma.intranet.dao.PapercutDao;
import br.com.oma.intranet.dao.PapercutJdbcDAO;
import br.com.oma.intranet.entidades.intra_papercut;

@ManagedBean(name = "papercut")
@ViewScoped
public class PapercutMB implements Serializable {

	private static final long serialVersionUID = 1L;
	

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private PapercutDao dao;
	private intra_papercut papercutBean = new intra_papercut();
	
	//ATRIBUTOS
	private List<intra_papercut> listaPapercut , listaPapercutAnalitico, selectPaperCut, listarPaperRel;
	
	private Date periodoInicial;
	private Date periodoFinal;
	
	private Date dataProcessamento;
	
	private String historico;

	// GET X SET
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public intra_papercut getPapercutBean() {
		return papercutBean;
	}

	public void setPapercutBean(intra_papercut papercutBean) {
		this.papercutBean = papercutBean;
	}

	public List<intra_papercut> getListaPapercut() {
		return listaPapercut;
	}

	public void setListaPapercut(List<intra_papercut> listaPapercut) {
		this.listaPapercut = listaPapercut;
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

	public List<intra_papercut> getListaPapercutAnalitico() {
		return listaPapercutAnalitico;
	}

	public void setListaPapercutAnalitico(List<intra_papercut> listaPapercutAnalitico) {
		this.listaPapercutAnalitico = listaPapercutAnalitico;
	}

	public List<intra_papercut> getSelectPaperCut() {
		return selectPaperCut;
	}

	public void setSelectPaperCut(List<intra_papercut> selectPaperCut) {
		this.selectPaperCut = selectPaperCut;
	}

	public List<intra_papercut> getListarPaperRel() {
		return listarPaperRel;
	}

	public void setListarPaperRel(List<intra_papercut> listarPaperRel) {
		this.listarPaperRel = listarPaperRel;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	//METODOS
	public void listarPapercut(){
		this.dao = new PapercutDao();
		if(this.periodoInicial != null & this.periodoFinal != null){
			this.listarPaperRel = this.dao.listarPaperRel(this.periodoInicial, this.periodoFinal);
			this.listaPapercut = this.dao.listarPapercut(this.periodoInicial, this.periodoFinal);
		}else if(this.periodoInicial != null){
			this.listarPaperRel = this.dao.listarPaperRel(this.periodoInicial);
			this.listaPapercut = this.dao.listarPapercut(this.periodoInicial);
		}
	}
	
	public void listarAnalitico(String nome, String subNome){
		this.dao = new PapercutDao();
		if(this.periodoInicial != null & this.periodoFinal !=null){
			this.listaPapercutAnalitico = this.dao.listarPapercutAnalitico(this.periodoInicial, this.periodoFinal, nome, subNome);
		}else if(this.periodoInicial != null){
			this.listaPapercutAnalitico = this.dao.listarPapercutAnalitico(this.periodoInicial, nome, subNome);
		}
	}
	
	public boolean valida() {
		if (this.periodoInicial == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Informe período inicial!"));
			RequestContext.getCurrentInstance().execute("PF('salvar').hide()");
			return false;
		} else if (this.selectPaperCut == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Ocorreu um erro inesperado, tente novamente!"));
			RequestContext.getCurrentInstance().execute("PF('salvar').hide()");
			return false;
		} else if (this.selectPaperCut.isEmpty()) {
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
		PapercutJdbcDAO paperDao = new PapercutJdbcDAO(); 
				
		if(this.valida()){
			int valor = 0;
			int qtdaLinhas = 0;
		for (intra_papercut paper : selectPaperCut) {
			if (paper.getNome().subSequence(0, 1).toString().trim().contains("0")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("1")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("2")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("3")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("4")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("5")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("6")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("7")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("8")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("9")
							& !paper.getSubNome().trim().contains("Recibos")) {
				if (!paper.getSubNome().trim().contains("Recibos")) {
					int condominio = Integer.valueOf(paper.getNome().substring(0, 4));
					int codigoDoServico = paperDao.retornaCodigoDoServico(condominio, this.tipoServico(paper.getSubNome()));
					if (codigoDoServico > 0) {
						qtdaLinhas = valor++;
					}
				}
			}
		}
		
		
		Thread t = new Thread(new PaperThread(this.selectPaperCut, this.periodoInicial, this.periodoFinal),"Thread salvar no Banco Papercut");
		t.start();
		System.out.println(qtdaLinhas);
		int codigoMsvcon = paperDao.retornaCodigoMsvcon(qtdaLinhas);
		for (intra_papercut paper : selectPaperCut) {
			if (paper.getNome().subSequence(0, 1).toString().trim().contains("0")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("1")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("2")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("3")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("4")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("5")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("6")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("7")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("8")
					|| paper.getNome().subSequence(0, 1).toString().trim().contains("9")
							& !paper.getSubNome().trim().contains("Recibos")) {
				if (!paper.getSubNome().trim().contains("Recibos")) {
					int condominio = Integer.valueOf(paper.getNome().substring(0, 4));
					int codigoDoServico = paperDao.retornaCodigoDoServico(condominio, this.tipoServico(paper.getSubNome()));
					if (codigoDoServico > 0) {
						paperDao.salvarCobrancaServico(codigoMsvcon, codigoDoServico, paper.getTotalPage(), this.historico, dataProcessamento, condominio, this.sessaoMB);
						codigoMsvcon += 1;
					}
				}
			}
		}
		
		RequestContext.getCurrentInstance().execute("PF('salvar').hide()");
		RequestContext.getCurrentInstance().execute("PF('processamento').hide();");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Processo concluído no SIGA, ainda processando para o Papercut!"));
		RequestContext.getCurrentInstance().update("frmPaper:msg2");
		RequestContext.getCurrentInstance().update("frmPaper:tbvPaper:tblPaper");
		RequestContext.getCurrentInstance().update("frmPaper:tbvPaper:tblPaperRel");

		this.listaPapercut = null;
		this.listarPaperRel = null;
		this.listaPapercutAnalitico = null;
		this.periodoInicial = null;
		this.periodoFinal = null;
		this.dataProcessamento = null;
		}
	}
	
	public double tipoServico(String nome) {
		double valor = 0.0;
		switch (nome) {
		case "ATA":
			valor = 3.0;
			this.historico = "Papercut - XEROX /IMPRESSÕES CIRCULAR";
			break;
		case "Balancete":
			valor = 4.0;
			this.historico = "Papercut - IMPRESSÃO LASER - PREST.CONTAS";
			break;
		case "Circulares":
			valor = 1.0;
			this.historico = "Papercut - XEROX /IMPRESSÕES CIRCULAR";
			break;
		case "Convencao-Reg. Interno":
			valor = 5.0;
			this.historico = "Papercut - XEROX CONVENCAO/REG.INTERNO";
			break;
		case "Convocacao":
			valor = 2.0;
			this.historico = "Papercut - IMPRESSÃO LASER-CONVOCAÇÃO";
			break;
		case "GERAL":
			valor = 7.0;
			this.historico = "Papercut - XEROX/ IMPRESSÕES EM GERAL";
			break;
		case "Impressoes Coloridas":
			valor = 7.04;
			this.historico = "Papercut - XEROX/ IMPRESSÕES COLORIDAS";
			break;
		}
		return valor;
	}
	
	public String alertaNumeroPaginas(double valor){
		if(valor > 500.0){
			return "mudarCor";
		}else{
			return "";
		}
		
	}
}
