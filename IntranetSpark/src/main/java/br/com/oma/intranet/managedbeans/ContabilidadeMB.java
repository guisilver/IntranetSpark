package br.com.oma.intranet.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.ContabilidadeDao;

@ViewScoped
@ManagedBean(name = "contabilBean")
public class ContabilidadeMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	
	//OBJETOS
	private ContabilidadeDao dao;

	//ATRIBUTOS
	private String valor;
	private int cdFinancImagem;

	//GET X SET
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getCdFinancImagem() {
		return cdFinancImagem;
	}

	public void setCdFinancImagem(int cdFinancImagem) {
		this.cdFinancImagem = cdFinancImagem;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	//METODOS
	public void calcula() {
		if (this.valor != null) {
			if(this.valor.matches("^[0-9]*$")){
				this.cdFinancImagem = Integer.valueOf(this.valor);
				RequestContext.getCurrentInstance().execute("PF('baixar').show();");
			}else{
				System.out.println("letras");
				this.cdFinancImagem = 0;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Informe apenas números!"));
			}
		}
	}
	
	public void salvar(){
		boolean recebido;
		if(this.cdFinancImagem > 0){
		this.dao = new ContabilidadeDao();
		recebido = this.dao.salvar(this.cdFinancImagem, this.sessaoMB);
		
		if(recebido){
			RequestContext.getCurrentInstance().execute("PF('baixar').hide();");
			this.cdFinancImagem = 0;
			this.valor = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Essa Imagem já foi baixada!"));
		}else{
			RequestContext.getCurrentInstance().execute("PF('baixar').hide();");
			this.cdFinancImagem = 0;
			this.valor = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Baixado!"));
		}
		
		}
	}
	
	public void fechar(){
		RequestContext.getCurrentInstance().execute("PF('baixar').hide();");
		this.cdFinancImagem = 0;
		this.valor = null;
	}
	
}
