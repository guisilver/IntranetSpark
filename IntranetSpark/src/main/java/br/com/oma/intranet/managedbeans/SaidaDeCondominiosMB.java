package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.oma.intranet.dao.SaidaDeCondominiosDAO;
import br.com.oma.intranet.dao.SessaoDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_condominios_saida;

@ViewScoped
@ManagedBean
public class SaidaDeCondominiosMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 542880280731972260L;
	private intra_condominios_saida ics = new intra_condominios_saida();
	private intra_condominios cndSelecionado;
	private List<intra_condominios_saida> listaCndSaida;
	private List<intra_condominios> listaCnd;
	private Date dataSaida;

	@PostConstruct
	public void init() {
		this.carregar();
	}

	public intra_condominios_saida getIcs() {
		return ics;
	}

	public void setIcs(intra_condominios_saida ics) {
		this.ics = ics;
	}

	public intra_condominios getCndSelecionado() {
		return cndSelecionado;
	}

	public void setCndSelecionado(intra_condominios cndSelecionado) {
		this.cndSelecionado = cndSelecionado;
	}

	public List<intra_condominios_saida> getListaCndSaida() {
		return listaCndSaida;
	}

	public void setListaCndSaida(List<intra_condominios_saida> listaCndSaida) {
		this.listaCndSaida = listaCndSaida;
	}

	public List<intra_condominios> getListaCnd() {
		return listaCnd;
	}

	public void setListaCnd(List<intra_condominios> listaCnd) {
		this.listaCnd = listaCnd;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public void carregar() {
		SessaoDAO dao = new SessaoDAO();
		this.listaCnd = dao.pesquidaCondominiosSiga();
		SaidaDeCondominiosDAO sdcDao = new SaidaDeCondominiosDAO();
		this.listaCndSaida = sdcDao.getListaCndSaida();
		Iterator<intra_condominios> itr = this.listaCnd.iterator();
		while (itr.hasNext()) {
			intra_condominios aux = (intra_condominios) itr.next();
			boolean remove = false;
			for (intra_condominios_saida aux2 : this.listaCndSaida) {
				if (aux.getCodigo() == aux2.getCodigo()) {
					remove = true;
				}
			}
			if (remove) {
				itr.remove();
			}
		}
	}

	public void constroiSaidaCnd() throws Exception {
		if (this.cndSelecionado == null) {
			throw new Exception("Selecione um condomínio para adicionar a saída!");
		}
		if (this.ics == null) {
			this.ics = new intra_condominios_saida();
		}
		this.ics.setBairro(this.cndSelecionado.getBairro());
		this.ics.setCodigo(this.cndSelecionado.getCodigo());
		this.ics.setNome(this.cndSelecionado.getNome());
		this.ics.setCodigoGerente(this.cndSelecionado.getCodigoGerente());
		this.ics.setEmailGerente(this.cndSelecionado.getEmailGerente());
		this.ics.setNomeGerente(this.cndSelecionado.getNomeGerente());
		this.ics.setEndereco(this.cndSelecionado.getEndereco());
		this.ics.setLogradouro(this.cndSelecionado.getLogradouro());
		this.ics.setNro(this.cndSelecionado.getNro());
		this.ics.setDataSaida(this.dataSaida);
	}

	public void salvarSaidaCnd() {
		try {
			SaidaDeCondominiosDAO dao = new SaidaDeCondominiosDAO();
			this.constroiSaidaCnd();
			dao.salvarSaidaCnd(this.ics);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
			this.limpar();
			this.carregar();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro: " + e.getMessage(), ""));
		}
	}

	public void excluirSaidaCnd(intra_condominios_saida ics) {
		try {
			SaidaDeCondominiosDAO dao = new SaidaDeCondominiosDAO();
			dao.excluirSaidaCnd(ics);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
			this.limpar();
			this.carregar();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro: " + e.getMessage(), ""));
		}
	}

	public void limpar() {
		this.ics = new intra_condominios_saida();
		this.listaCnd = null;
		this.listaCndSaida = null;
		this.dataSaida = null;
	}

}
