package br.com.oma.omaonline.managedbeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.omaonline.dao.BlackListDAO;
import br.com.oma.omaonline.entidades.black_list;

@ManagedBean(name="blacklist")
public class BlackList extends Mensagens{

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	
	// OBJETOS
	private BlackListDAO blackListDAO;
	private black_list blackListMB = new black_list();
	private intra_condominios condoMB = new intra_condominios();
	
	//ATRIBUTOS
	private String cnpj;
	private List<black_list> listaDeBlackList;
	private List<intra_condominios> listaDeCondominios;
	
	private String nomeGerente;
	private int condominio;

	//GET x SET
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public black_list getBlackListMB() {
		return blackListMB;
	}

	public void setBlackListMB(black_list blackListMB) {
		this.blackListMB = blackListMB;
	}

	public intra_condominios getCondoMB() {
		return condoMB;
	}

	public void setCondoMB(intra_condominios condoMB) {
		this.condoMB = condoMB;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<intra_condominios> getListaDeCondominios() {
		this.blackListDAO = new BlackListDAO();
		if(this.sessaoMB.getGerenteSelecionado() != null){
		 this.listaDeCondominios = this.blackListDAO.listarCondominios(this.sessaoMB.getGerenteSelecionado().getCodigo());
		}
		return listaDeCondominios;
	}

	public void setListaDeCondominios(List<intra_condominios> listaDeCondominios) {
		this.listaDeCondominios = listaDeCondominios;
	}

	public List<black_list> getListaDeBlackList() {
		if(this.listaDeBlackList == null){
			this.blackListDAO = new BlackListDAO();
			if(this.sessaoMB.getGerenteSelecionado() != null){
				this.listaDeBlackList = this.blackListDAO.listarBlackList(this.sessaoMB.getGerenteSelecionado().getCodigo());
			}
		}
		return listaDeBlackList;
	}

	public void setListaDeBlackList(List<black_list> listaDeBlackList) {
		this.listaDeBlackList = listaDeBlackList;
	}
	
	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}
	
	

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	//METODOS
	public void salvar() {
		this.blackListDAO = new BlackListDAO();
		boolean retorno = false;
		boolean salvo = false;
		boolean contaDiferente = false;
		if(!this.cnpj.trim().equals("")){
			String removePontos = this.cnpj.replace(".", "").replace("/", "").replace("-", "");
			this.blackListMB.setCnpj(Double.valueOf(removePontos));
		}
		
		if(this.condoMB.getCodigo() > 0){
			for (intra_condominios condo : this.sessaoMB.getListaDeCondominios()) {
				if(this.condoMB.getCodigo() == condo.getCodigo()){
					this.condoMB = condo;
					break;
				}
			}
		}
		
		if (this.condoMB.getCodigo() > 0 && !this.cnpj.trim().equals("") && this.blackListMB.getContaContabil() > 0) {
			retorno = this.blackListDAO.verificaTodos(this.condoMB, this.blackListMB);
			if(retorno){
				this.alerta();
			}else{
				retorno = this.blackListDAO.verificaCodigoPlano(this.condoMB, this.blackListMB);
				if(retorno){
					this.blackListMB.setCondominio(this.condoMB.getCodigo());
					this.blackListMB.setCodigoGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
					this.blackListMB.setStatusItem(true);
					this.blackListDAO.salvarRegra(this.blackListMB, this.sessaoMB);
					salvo = true;
				}else{
					contaDiferente = true;
				}
			}
		}else if(this.condoMB.getCodigo() > 0 && !this.cnpj.trim().equals("")){
			retorno = this.blackListDAO.verificaCondoCNPJ(this.condoMB, this.blackListMB);
			if(retorno){
				this.alerta();
			}else{
				this.blackListMB.setCondominio(this.condoMB.getCodigo());
				this.blackListMB.setCodigoGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
				this.blackListMB.setStatusItem(true);
				this.blackListDAO.salvarRegra(this.blackListMB, this.sessaoMB);
				salvo = true;
			}
		}else if(this.condoMB.getCodigo() > 0 &&  this.blackListMB.getContaContabil() > 0){
			retorno = this.blackListDAO.verificaCondoContabil(this.condoMB, this.blackListMB);
			if(retorno){
				this.alerta();
			}else{
				retorno = this.blackListDAO.verificaCodigoPlano(this.condoMB, this.blackListMB);
				if(retorno){
					this.blackListMB.setCondominio(this.condoMB.getCodigo());
					this.blackListMB.setCodigoGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
					this.blackListMB.setStatusItem(true);
					this.blackListDAO.salvarRegra(this.blackListMB, this.sessaoMB);
					salvo = true;
				}else{
					contaDiferente = true;
				}
				
			}
		}else if(!this.cnpj.trim().equals("") && this.blackListMB.getContaContabil() > 0){
			retorno = this.blackListDAO.verificaCNPJContabil(this.blackListMB, this.sessaoMB);
			
			if(retorno){
				this.alerta();
			}else{
				this.blackListMB.setCodigoGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
				this.blackListMB.setStatusItem(true);
				this.blackListDAO.salvarRegra(this.blackListMB, this.sessaoMB);
				salvo= true;
			}
			
		}else if(!this.cnpj.trim().equals("")){
			retorno = this.blackListDAO.verificaCNPJ(this.blackListMB, this.sessaoMB);
			if(retorno){
				this.alerta();
			}else{
				this.blackListMB.setCodigoGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
				this.blackListMB.setStatusItem(true);
				this.blackListDAO.salvarRegra(this.blackListMB, this.sessaoMB);
				salvo= true;
			}
		}else if(this.blackListMB.getContaContabil() > 0){
			retorno = this.blackListDAO.verificaContabil(this.blackListMB, this.sessaoMB);
			if(retorno){
				this.alerta();
			}else{
				this.blackListMB.setCodigoGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
				this.blackListMB.setStatusItem(true);
				this.blackListDAO.salvarRegra(this.blackListMB, this.sessaoMB);
				salvo= true;
			}
		}
		
		if (this.condoMB.getCodigo() > 0) {
			if (this.blackListMB.getContaContabil() > 0) {
				retorno = this.blackListDAO.verificaCondoContabil(this.condoMB, this.blackListMB);
			} else {
				retorno = true;
			}
			if (retorno) {
				retorno = this.blackListDAO.verificaCondo(this.condoMB);
				if (retorno) {
					this.blackListDAO.regraGeral2(this.condoMB, this.sessaoMB);
				} else {
					this.blackListDAO.regraGeral(this.condoMB, this.sessaoMB);

					this.blackListMB.setCondominio(this.condoMB.getCodigo());
					this.blackListMB.setCodigoGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
					this.blackListMB.setStatusItem(true);
					this.blackListDAO.salvarRegra(this.blackListMB, this.sessaoMB);
					salvo = true;
				}
			}
		}
		
		if(salvo){
			this.msgSalvo();
		}
		if(contaDiferente){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Essa conta não pertence ao plano de conta do condomínio selecionado!", "Essa conta não pertence ao plano de conta do condomínio selecionado!"));
		}
		this.blackListMB = new black_list();
		this.condoMB = new intra_condominios();
		this.listaDeBlackList = null;
		RequestContext.getCurrentInstance().update("frmBlackList:dtBlackList");
	
	}
	
	public void listarCondominios(){
		this.blackListDAO = new BlackListDAO();
		if(this.sessaoMB.getGerenteSelecionado().getCodigo() > 0){
		 this.listaDeCondominios = this.blackListDAO.listarCondominios(this.sessaoMB.getGerenteSelecionado().getCodigo());
		}
	}
	
	public void habilitarDesabilitar(black_list bl){
		this.blackListDAO = new BlackListDAO();
		if(bl.getCondominio() > 0 && bl.getCnpj() == 0 && bl.getContaContabil() == 0){
			this.blackListDAO.deleteBlackList(bl, this.sessaoMB);
		}else{
			if(bl.isStatusItem()){
				boolean retorno = false;
				retorno = this.blackListDAO.verificaCondo(bl);
				if(retorno){
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Para habilitar essa regra você deve excluir a regra Geral!",
									"Para habilitar essa regra você deve excluir a regra Geral!"));
					bl.setStatusItem(false);
					this.blackListMB.setStatusItem(false);
					this.listaDeBlackList = null;
					RequestContext.getCurrentInstance().update("frmBlackList:dtBlackList");
				}else{
				bl.setStatusItem(true);
				this.blackListDAO.update(bl, this.sessaoMB);
				this.msgAlterado();
				}
			}else{
				bl.setStatusItem(false);
				this.blackListDAO.update(bl, this.sessaoMB);
				this.msgAlterado();
			}
		}
		this.listaDeBlackList = null;
		RequestContext.getCurrentInstance().update("frmBlackList:dtBlackList");
		
	}
	
	public void deletarBlackList(black_list bl){
		this.blackListDAO = new BlackListDAO();
		this.blackListDAO.deleteBlackList(bl, this.sessaoMB);
		this.listaDeBlackList = null;
		RequestContext.getCurrentInstance().update("frmBlackList:dtBlackList");
		this.msgExclusao();
	}
	
	public void alerta(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Essa regra já foi habilitada!","Essa regra já foi habilitada!"));
	}

}
