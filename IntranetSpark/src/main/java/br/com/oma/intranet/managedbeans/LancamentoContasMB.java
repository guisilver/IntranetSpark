package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.oma.intranet.dao.LancamentoContasDao;
import br.com.oma.omaonline.entidades.cndpagar;

@ViewScoped
@ManagedBean(name = "lanctoBean")
public class LancamentoContasMB implements Serializable{

	private static final long serialVersionUID = 1L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	
	//OBJETOS
	private cndpagar cndpagarBean = new cndpagar();
	private LancamentoContasDao dao;
	
	//ATRIBUTOS
	private List<cndpagar> listarCndpagarOma, filtroCndpagarOma;
	private boolean proxima1 = true;
	private boolean proxima2;

	//GET x SET
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public cndpagar getCndpagarBean() {
		return cndpagarBean;
	}

	public void setCndpagarBean(cndpagar cndpagarBean) {
		this.cndpagarBean = cndpagarBean;
	}

	public List<cndpagar> getListarCndpagarOma() {
		if(this.listarCndpagarOma == null){
			this.dao = new LancamentoContasDao();
			this.listarCndpagarOma = this.dao.listarLanctoOma();
		}
		return listarCndpagarOma;
	}

	public void setListarCndpagarOma(List<cndpagar> listarCndpagarOma) {
		this.listarCndpagarOma = listarCndpagarOma;
	}

	public List<cndpagar> getFiltroCndpagarOma() {
		return filtroCndpagarOma;
	}

	public void setFiltroCndpagarOma(List<cndpagar> filtroCndpagarOma) {
		this.filtroCndpagarOma = filtroCndpagarOma;
	}
	
	public boolean isProxima1() {
		return proxima1;
	}

	public void setProxima1(boolean proxima1) {
		this.proxima1 = proxima1;
	}

	public boolean isProxima2() {
		return proxima2;
	}

	public void setProxima2(boolean proxima2) {
		this.proxima2 = proxima2;
	}

	//METODOS
	public String listarFornecedor(String credor){
		this.dao = new LancamentoContasDao();
		String nome = this.dao.listarCredorNome(credor).toString();
		return nome;
	}
	
	public String listarFornecedorCNPJ(String credor){
		this.dao = new LancamentoContasDao();
		String cnpj = this.dao.listarCredorCNPJ(credor).toString();
		return cnpj;
	}

	public void avancar(cndpagar pagar){
		this.proxima1 = false;
		this.proxima2 = true;
		this.cndpagarBean = pagar;
	}
	
	public void voltar(){
		this.proxima1 = true;
		this.proxima2 = false;
	}
}
