package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

import br.com.oma.intranet.dao.GrupoDeptoDAO;
import br.com.oma.intranet.dao.GrupoGerDAO;
import br.com.oma.intranet.dao.GrupoPermissaoDAO;
import br.com.oma.intranet.entidades.intra_grupo_depto;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_grupo_permissao;
import br.com.oma.intranet.util.Mensagens;

@ViewScoped
@ManagedBean(name = "grupo")
public class GrupoMB extends Mensagens implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1669784151962345079L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private GrupoDeptoDAO deptoDAO;
	private GrupoPermissaoDAO permissaoDAO;
	private GrupoGerDAO grupoGerenDAO;
	private intra_grupo_depto deptoBean = new intra_grupo_depto();
	private intra_grupo_depto deptoTblBean = new intra_grupo_depto();
	private intra_grupo_permissao permissaoBean = new intra_grupo_permissao();
	private intra_grupo_gerente gerenBean = new intra_grupo_gerente();

	// ATRIBUTOS
	private List<intra_grupo_depto> listaDepto, listaExclusaoDepto,
			filtroDepto;
	private List<intra_grupo_permissao> gruposPerm, listaPermissao,
			filtroPermissao;
	private List<intra_grupo_gerente> listaGeren, filtroGeren;

	private String nomePermissao;
	//private String codigoGerente;

	// GET X SET
	public List<intra_grupo_depto> getListaDepto() {
		this.deptoDAO = new GrupoDeptoDAO();
		if (this.listaDepto == null) {
			this.listaDepto = this.deptoDAO.buscaTodos();
		}
		return listaDepto;
	}

	public void setListaDepto(List<intra_grupo_depto> listaDepto) {
		this.listaDepto = listaDepto;
	}

	public List<intra_grupo_depto> getFiltroDepto() {
		return filtroDepto;
	}

	public void setFiltroDepto(List<intra_grupo_depto> filtroDepto) {
		this.filtroDepto = filtroDepto;
	}

	public intra_grupo_depto getDeptoBean() {
		return deptoBean;
	}

	public void setDeptoBean(intra_grupo_depto deptoBean) {
		this.deptoBean = deptoBean;
	}

	public intra_grupo_depto getDeptoTblBean() {
		return deptoTblBean;
	}

	public void setDeptoTblBean(intra_grupo_depto deptoTblBean) {
		this.deptoTblBean = deptoTblBean;
	}

	public intra_grupo_permissao getPermissaoBean() {
		return permissaoBean;
	}

	public void setPermissaoBean(intra_grupo_permissao permissaoBean) {
		this.permissaoBean = permissaoBean;
	}

	public List<intra_grupo_permissao> getListaPermissao() {
		this.permissaoDAO = new GrupoPermissaoDAO();
		if (this.listaPermissao == null) {
			this.listaPermissao = this.permissaoDAO.buscaTodos();
		}
		return listaPermissao;
	}

	public void setListaPermissao(List<intra_grupo_permissao> listaPermissao) {
		this.listaPermissao = listaPermissao;
	}

	public List<intra_grupo_permissao> getFiltroPermissao() {
		return filtroPermissao;
	}

	public void setFiltroPermissao(List<intra_grupo_permissao> filtroPermissao) {
		this.filtroPermissao = filtroPermissao;
	}

	/**
	 * @return the listaExclusaoDepto
	 */
	public List<intra_grupo_depto> getListaExclusaoDepto() {
		return listaExclusaoDepto;
	}

	/**
	 * @param listaExclusaoDepto
	 *            the listaExclusaoDepto to set
	 */
	public void setListaExclusaoDepto(List<intra_grupo_depto> listaExclusaoDepto) {
		this.listaExclusaoDepto = listaExclusaoDepto;
	}

	/**
	 * @return the nomePermissao
	 */
	public String getNomePermissao() {
		return nomePermissao;
	}

	/**
	 * @param nomePermissao
	 *            the nomePermissao to set
	 */
	public void setNomePermissao(String nomePermissao) {
		this.nomePermissao = nomePermissao;
	}

	/**
	 * @return the gruposPerm
	 */
	public List<intra_grupo_permissao> getGruposPerm() {
		if (this.gruposPerm == null) {
			this.permissaoDAO = new GrupoPermissaoDAO();
			this.gruposPerm = this.permissaoDAO.buscaTodos();
		}
		return gruposPerm;
	}

	/**
	 * @param gruposPerm
	 *            the gruposPerm to set
	 */
	public void setGruposPerm(List<intra_grupo_permissao> gruposPerm) {
		this.gruposPerm = gruposPerm;
	}

	/**
	 * @return the gerenBean
	 */
	public intra_grupo_gerente getGerenBean() {
		return gerenBean;
	}

	/**
	 * @param gerenBean
	 *            the gerenBean to set
	 */
	public void setGerenBean(intra_grupo_gerente gerenBean) {
		this.gerenBean = gerenBean;
	}

	/**
	 * @return the listaGeren
	 */
	public List<intra_grupo_gerente> getListaGeren() {
		if (this.listaGeren == null) {
			this.grupoGerenDAO = new GrupoGerDAO();
			this.listaGeren = this.grupoGerenDAO.buscaTodos();
		}
		return listaGeren;
	}

	/**
	 * @param listaGeren
	 *            the listaGeren to set
	 */
	public void setListaGeren(List<intra_grupo_gerente> listaGeren) {
		this.listaGeren = listaGeren;
	}

	/**
	 * @return the filtroGeren
	 */
	public List<intra_grupo_gerente> getFiltroGeren() {
		return filtroGeren;
	}

	/**
	 * @param filtroGeren
	 *            the filtroGeren to set
	 */
	public void setFiltroGeren(List<intra_grupo_gerente> filtroGeren) {
		this.filtroGeren = filtroGeren;
	}

	/**
	 * @return the codigoGerente
	 *//*
	public String getCodigoGerente() {
		return codigoGerente;
	}

	*//**
	 * @param codigoGerente
	 *            the codigoGerente to set
	 *//*
	public void setCodigoGerente(String codigoGerente) {
		this.codigoGerente = codigoGerente;
	}*/
	
	/**
	 * @param sessaoMB the sessaoMB to set
	 */
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// METODOS
	public void adicionarDepto() {
		this.deptoDAO = new GrupoDeptoDAO();
		boolean valida = false;
		if (this.deptoBean.getNome().trim().equals("")) {
			this.msgObrigatorio();
		} else {
			try {
				for (intra_grupo_depto f : listaDepto) {
					if (this.deptoBean.getNome().equals(f.getNome())) {
						valida = true;
						this.msgExiste();
						break;
					}
				}
				if (valida == false) {
					this.deptoDAO.adiconaGrupo(this.deptoBean, this.sessaoMB);
					this.deptoBean = new intra_grupo_depto();
					this.listaDepto = null;
					this.msgAdicinado();
				}
			} catch (Exception e) {
				this.msgErro();
			}
		}
	}

	public void excluirDepto() {
		this.deptoDAO = new GrupoDeptoDAO();
		this.listaExclusaoDepto = new ArrayList<intra_grupo_depto>();
		boolean valida = false;
		try {
			valida = this.deptoDAO.pesquisaDepto(this.deptoTblBean);
			if (valida) {
				this.msgAssociado();
			} else {
				this.deptoDAO.excluirGrupo(this.deptoTblBean, this.sessaoMB);
				this.deptoTblBean = new intra_grupo_depto();
				this.listaDepto = null;
				this.msgExclusao();
			}
		} catch (Exception e) {
			this.msgErro();
		}
	}

	public void adionarPermissao() {
		boolean valida = false;
		this.permissaoDAO = new GrupoPermissaoDAO();
		if (this.permissaoBean != null) {
			if (this.permissaoBean.getNomeGrupo().trim().equals("")) {
				this.msgObrigatorio();
			} else {
				if (this.permissaoBean.getNomeGrupo().trim().equals("")) {
					this.msgObrigatorio();
				} else {

					this.listaPermissao = this.permissaoDAO.buscaTodos();
					for (intra_grupo_permissao permissao : this.listaPermissao) {
						if (permissao.getNomeGrupo().trim()
								.equals(this.permissaoBean.getNomeGrupo())) {
							valida = true;
						}
					}
					if (valida) {
						this.msgExiste();
					} else {
						this.permissaoDAO = new GrupoPermissaoDAO();
						this.permissaoDAO.adiciona(this.permissaoBean, this.sessaoMB);
						this.permissaoBean = new intra_grupo_permissao();
						this.msgAdicinado();
					}
				}
			}
		}
	}

	public void adicionarGerente() {
		this.grupoGerenDAO = new GrupoGerDAO();
		if (this.gerenBean != null) {
			if (this.gerenBean.getNome() != null
					& this.gerenBean.getEmail() != null) {
				if (this.gerenBean.getCodigo() > 0
						& !this.gerenBean.getNome().trim().equals("")
						& !this.gerenBean.getEmail().trim().equals("")) {
					if (this.validaEmail(this.gerenBean.getEmail())) {
						if(this.grupoGerenDAO.verificaGerente(this.gerenBean.getCodigo())){
						this.grupoGerenDAO.adiconaGeren(this.gerenBean, this.sessaoMB);
						this.gerenBean = new intra_grupo_gerente();
						
						DataTable table = (DataTable) FacesContext.getCurrentInstance()
								.getViewRoot().findComponent("frmGrupoGeren:tblGeren");
						table.setValue(null);
						RequestContext.getCurrentInstance().execute(
								"$('.ui-column-filter').val('');");
						
						this.listaGeren = null;
						this.filtroGeren = null;
						this.msgAdicinado();
						}else{
							this.msgUsuario();
						}
					} else {
						this.msgEmail();
					}
				} else {
					this.msgObrigatorio();
				}
			} else {
				this.msgObrigatorio();
			}
		} else {
			this.msgObrigatorio();
		}
	}

	public void excluirGerente() {
		this.grupoGerenDAO = new GrupoGerDAO();
		if (this.gerenBean.getCodigo() > 0) {
			this.grupoGerenDAO.ExcluirGerente(this.gerenBean, this.sessaoMB);
			this.gerenBean = new intra_grupo_gerente();
			this.listaGeren = null;
			this.msgExclusao();
		} else {
			this.msgObrigatorio();
		}
	}

	public void excluirPermissao() {
		this.permissaoDAO = new GrupoPermissaoDAO();

		if (this.permissaoDAO.verificarExclusaoPermissao(this.nomePermissao)) {
			msgAssociado();
		} else {
			this.permissaoDAO.deletarGrupoPerm(this.permissaoBean);
			this.permissaoBean = new intra_grupo_permissao();
			this.listaPermissao = null;
			this.gruposPerm = null;
			this.msgExclusao();
		}
	}

	public void alterarPermissao() {
		this.permissaoDAO = new GrupoPermissaoDAO();
		this.permissaoDAO.alterar(this.permissaoBean);
		this.msgAlterado();
		this.permissaoBean = new intra_grupo_permissao();
		this.listaPermissao = null;
	}

	public String handleFlow(FlowEvent event) {
		String currentStepId = event.getNewStep();
		this.permissaoDAO = new GrupoPermissaoDAO();
		this.listaPermissao = null;
		this.listaPermissao = this.permissaoDAO.buscaTodos(this.nomePermissao);
		for (intra_grupo_permissao per : this.listaPermissao) {
			this.permissaoBean = per;
		}
		return currentStepId;
	}

	public void limparGerente() {
		this.gerenBean = new intra_grupo_gerente();
	}
	
	public void desativar(intra_grupo_gerente gerente){
		this.permissaoDAO = new GrupoPermissaoDAO();
		this.permissaoDAO.desativarGerente(gerente);
		this.msgSalvo();
	}

	/*public void updateCodigoGeren() {
		this.codigoGerente = String.valueOf(this.gerenBean.getCodigo());
	}*/

	/*public void validaCodigoGeren() {
		if (!this.validaInteiro(Sintrg(this.gerenBean.getCodigo()))) {
			this.msgInteiro();
			this.gerenBean = new intra_grupo_gerente();
			this.codigoGerente = "";

		}
	}*/

}
