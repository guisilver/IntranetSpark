package br.com.oma.intranet.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.oma.intranet.dao.PrevisaoRateioDAO;
import br.com.oma.intranet.entidades.intra_previsao_rateio;

@ViewScoped
@ManagedBean(name="prMB")
public class PrevisaoRateio extends PrevisaoOrcamentaria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7864946557975629213L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	
	//OBJETOS
	private PrevisaoRateioDAO prDAO;
	private intra_previsao_rateio iprBEAN = new intra_previsao_rateio();
	private List<intra_previsao_rateio> listaDeRateio, filtroDeRaterio;
	
	//ATRIBUTOS
	private int codigo;
	
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	//GET X SET
	public intra_previsao_rateio getIprBEAN() {
		return iprBEAN;
	}

	public void setIprBEAN(intra_previsao_rateio iprBEAN) {
		this.iprBEAN = iprBEAN;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public List<intra_previsao_rateio> getListaDeRateio() {
		if(this.listaDeRateio == null){
			this.prDAO = new PrevisaoRateioDAO();
			this.listaDeRateio = this.prDAO.listarVerbasRateio(this.getIcBEAN().getCodigo());
		}
		return listaDeRateio;
	}

	public void setListaDeRateio(List<intra_previsao_rateio> listaDeRateio) {
		this.listaDeRateio = listaDeRateio;
	}

	public List<intra_previsao_rateio> getFiltroDeRaterio() {
		return filtroDeRaterio;
	}

	public void setFiltroDeRaterio(List<intra_previsao_rateio> filtroDeRaterio) {
		this.filtroDeRaterio = filtroDeRaterio;
	}
	
	//METODOS
	
	public void salvarVerbas(){
		if(this.getIcBEAN().getCodigo() > 0){
			this.prDAO = new PrevisaoRateioDAO();
			this.prDAO.salvarVerbas(this.iprBEAN);
			this.iprBEAN = new intra_previsao_rateio();
			this.msgSalvo();
		}
	}
	
	
}
