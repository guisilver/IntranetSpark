package br.com.oma.intranet.managedbeans;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.oma.intranet.dao.ComercialDAO;
import br.com.oma.intranet.entidades.intra_cptatendimento;
import br.com.oma.intranet.entidades.intra_cptcondo2;
import br.com.oma.intranet.entidades.intra_cptconstrutora2;
import br.com.oma.intranet.util.Mensagens;

@ManagedBean(name = "cmcaMB")
@ViewScoped
public class ComercialCAMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2861954745061838705L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private ComercialDAO cmDAO;

	private intra_cptconstrutora2 constBEAN = new intra_cptconstrutora2();
	private intra_cptconstrutora2 construtoraSelecionada;
	private intra_cptatendimento cptaBEAN = new intra_cptatendimento();
	private intra_cptatendimento atendimentoSelecionado;
	private intra_cptcondo2 condominioSelecionado;

	// ATRIBUTOS
	private List<intra_cptcondo2> listaCondominios2;
	private List<intra_cptconstrutora2> listaConstrutora;
	private List<intra_cptatendimento> listaAtendimento, listaAtendimento2;
	private List<String> listaCidades;
	private String logradouro, pesquisarPor = "Todos";

	// GET X SET

	public ComercialDAO getCmDAO() {
		return cmDAO;
	}

	public void setCmDAO(ComercialDAO cmDAO) {
		this.cmDAO = cmDAO;
	}

	public intra_cptconstrutora2 getConstBEAN() {
		return constBEAN;
	}

	public void setConstBEAN(intra_cptconstrutora2 constBEAN) {
		this.constBEAN = constBEAN;
	}

	public intra_cptconstrutora2 getConstrutoraSelecionada() {
		return construtoraSelecionada;
	}

	public void setConstrutoraSelecionada(intra_cptconstrutora2 construtoraSelecionada) {
		this.construtoraSelecionada = construtoraSelecionada;
	}

	public intra_cptatendimento getCptaBEAN() {
		return cptaBEAN;
	}

	public void setCptaBEAN(intra_cptatendimento cptaBEAN) {
		this.cptaBEAN = cptaBEAN;
	}

	public intra_cptatendimento getAtendimentoSelecionado() {
		return atendimentoSelecionado;
	}

	public void setAtendimentoSelecionado(intra_cptatendimento atendimentoSelecionado) {
		this.atendimentoSelecionado = atendimentoSelecionado;
	}

	public intra_cptcondo2 getCondominioSelecionado() {
		return condominioSelecionado;
	}

	public void setCondominioSelecionado(intra_cptcondo2 condominioSelecionado) {
		this.condominioSelecionado = condominioSelecionado;
	}

	public List<intra_cptcondo2> getListaCondominios2() {
		try {
			this.cmDAO = new ComercialDAO();
			this.listaCondominios2 = this.cmDAO.listaCondominios2();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaCondominios2;
	}

	public void setListaCondominios2(List<intra_cptcondo2> listaCondominios2) {
		this.listaCondominios2 = listaCondominios2;
	}

	public List<intra_cptconstrutora2> getListaConstrutora() {
		try {
			this.cmDAO = new ComercialDAO();
			this.listaConstrutora = this.cmDAO.listaConstrutora();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaConstrutora;
	}

	public void setListaConstrutora(List<intra_cptconstrutora2> listaConstrutora) {
		this.listaConstrutora = listaConstrutora;
	}

	public List<intra_cptatendimento> getListaAtendimento() {
		try {
			this.cmDAO = new ComercialDAO();
			this.listaAtendimento = this.cmDAO.listaAtendimento();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaAtendimento;
	}

	public void setListaAtendimento(List<intra_cptatendimento> listaAtendimento) {
		this.listaAtendimento = listaAtendimento;
	}

	public List<intra_cptatendimento> getListaAtendimento2() {
		try {
			this.cmDAO = new ComercialDAO();
			if (this.condominioSelecionado != null) {
				if (this.cptaBEAN.getId() != 0) {
					this.listaAtendimento2 = this.cmDAO.listaAtendimentosPorCodigo(this.condominioSelecionado);
				}
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaAtendimento2;
	}

	public void setListaAtendimento2(List<intra_cptatendimento> listaAtendimento2) {
		this.listaAtendimento2 = listaAtendimento2;
	}

	public List<String> getListaCidades() {
		try {
			if (this.constBEAN.getEstado() != 0) {
				this.cmDAO = new ComercialDAO();
				this.listaCidades = this.cmDAO.listaCidades(this.constBEAN.getEstado());
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaCidades;
	}

	public void setListaCidades(List<String> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getPesquisarPor() {
		return pesquisarPor;
	}

	public void setPesquisarPor(String pesquisarPor) {
		this.pesquisarPor = pesquisarPor;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// MÉTODOS
	
	// ↓ MÉTODO PARA INSERIR LOGRADOURO ↓
	
	@SuppressWarnings("static-access")
	public void logradouro() {
		this.setLogradouro(this.trataNomeComposto(this.getLogradouro()));
		this.constBEAN.setEndereco(this.trataNomeComposto(this.getLogradouro()));
	}

	// ↓ MÉTODO PARA CARREGAR DIALOG DA CONSTRUTORA ↓
	
	public void carregarDialogConstrutora(intra_cptconstrutora2 c) {
		this.constBEAN.setId(c.getId());
		this.constBEAN.setTipo(c.getTipo());
		this.constBEAN.setNome(c.getNome());
		this.constBEAN.setEndereco(c.getEndereco());
		this.constBEAN.setBairro(c.getBairro());
		this.constBEAN.setEstado(c.getEstado());
		this.constBEAN.setCidade(c.getCidade());
		this.constBEAN.setCep(c.getCep());
		this.constBEAN.setResponsavel(c.getResponsavel());
		this.constBEAN.setContatos(c.getContatos());
	}

	// ↓ MÉTODO PARA SALVAR CONSTRUTORA ↓
	
	public void salvarConstrutora() {
		try {
			this.cmDAO = new ComercialDAO();
			this.constBEAN.setTipo(this.constBEAN.getTipo());
			this.constBEAN.setNome(this.constBEAN.getNome().toUpperCase());
			this.constBEAN.setEndereco(this.constBEAN.getEndereco().toUpperCase());
			this.constBEAN.setBairro(this.constBEAN.getBairro().toUpperCase());
			this.constBEAN.setEstado(this.constBEAN.getEstado());
			this.constBEAN.setCidade(this.constBEAN.getCidade().toUpperCase());
			this.constBEAN.setCep(this.constBEAN.getCep());
			this.constBEAN.setResponsavel(this.constBEAN.getResponsavel().toUpperCase());
			this.constBEAN.setContatos(this.constBEAN.getContatos().toUpperCase());
			this.cmDAO.salvarConstrutora(this.constBEAN, this.sessaoMB);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "/comercial/construtora/tabela-de-construtoras.xhtml?faces-redirect=true");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	// ↓ MÉTODO PARA CARREGAR DIALOG DO ATENDIMENTO ↓
	

	public void carregarDialogAtendimento(intra_cptatendimento c) {
		this.cptaBEAN.setId(c.getId());
		this.cptaBEAN.setCodigoCondominio(c.getCodigoCondominio());
		this.cptaBEAN.setNomeCondominio(c.getNomeCondominio());
		this.cptaBEAN.setHistorico(c.getHistorico());
		this.cptaBEAN.setData(c.getData());
		this.cptaBEAN.setSolucionado(c.isSolucionado());
		this.cptaBEAN.setUsuario(c.getUsuario());
	}

	// ↓ MÉTODO PARA SALVAR ATENDIMENTO ↓
	
	public void salvarAtendimento() {
		try {
			this.cmDAO = new ComercialDAO();
			this.cptaBEAN.setCodigoCondominio(this.cptaBEAN.getCodigoCondominio());
			this.getListaCondominios2();
			for (intra_cptcondo2 c : this.listaCondominios2) {
				if (c.getId() == this.cptaBEAN.getCodigoCondominio()) {
					this.cptaBEAN.setNomeCondominio(c.getNome().toUpperCase());
				}
			}
			this.cptaBEAN.setHistorico(this.cptaBEAN.getHistorico().toUpperCase());
			this.cptaBEAN.setData(new Date());
			this.cptaBEAN.setSolucionado(this.cptaBEAN.isSolucionado());
			this.cptaBEAN.setUsuario(this.sessaoMB.getUsuario().getNome().toUpperCase());
			this.cmDAO.salvarAtendimento(this.cptaBEAN, this.sessaoMB);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "/comercial/atendimento/tabela-de-atendimento.xhtml?faces-redirect=true");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}