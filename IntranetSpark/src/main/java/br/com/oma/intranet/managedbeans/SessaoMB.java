package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.oma.intranet.dao.SessaoDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_depto;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_grupo_permissao;
import br.com.oma.intranet.entidades.intra_usuario;

@SessionScoped
@ManagedBean(name = "SessaoMB")
public class SessaoMB extends PermissaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7305987285537728306L;
	// OBJETOS
	private intra_usuario usuario = new intra_usuario();
	private intra_grupo_permissao igp = new intra_grupo_permissao();

	// ATRIBUTOS
	private boolean logado;
	private intra_grupo_gerente gerenteSelecionado;
	private List<intra_usuario> listaUsuario;
	private List<intra_condominios> listaDeCondominios;
	private List<intra_condominios> listaCondominios, listaTodosCondominios;
	private List<intra_grupo_gerente> listaDeGerente, listaGerentes;

	private String ipUser;

	// OBJETOS
	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public intra_grupo_gerente getGerenteSelecionado() {
		return gerenteSelecionado;
	}

	public void setGerenteSelecionado(intra_grupo_gerente gerenteSelecionado) {
		this.gerenteSelecionado = gerenteSelecionado;
	}

	public intra_usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(intra_usuario usuario) {
		this.usuario = usuario;
	}

	public List<intra_usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<intra_usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getIpUser() {
		return ipUser;
	}

	public void setIpUser(String ipUser) {
		this.ipUser = ipUser;
	}

	public intra_grupo_permissao getIgp() {
		if (this.usuario != null) {
			this.igp = this.retornaPermissao(this.usuario.getGrupoPer());
		}
		return igp;
	}

	public void setIgp(intra_grupo_permissao igp) {
		this.igp = igp;
	}

	public boolean verificaDepto(String nome) {
		boolean valida = false;
		if (this.usuario.getGrupoDepto() != null) {
			for (intra_grupo_depto gd : this.usuario.getGrupoDepto()) {
				if (nome.equals(gd.getNome())) {
					valida = true;
				}
			}
		}
		return valida;
	}

	/**
	 * @return the listaDeCondominios
	 */
	public List<intra_condominios> getListaDeCondominios() {
		SessaoDAO dao = new SessaoDAO();
		List<intra_condominios> lista = dao.listaDeCondominios();
		if (!this.usuario.getGrupoGer().isEmpty()) {
			if (!this.usuario.getGrupoGer().get(0).getNome().equals(" Todos")) {
				this.listaDeCondominios = new ArrayList<intra_condominios>();
				for (intra_grupo_gerente user : this.usuario.getGrupoGer()) {
					for (intra_condominios ic : lista) {
						if (user.getCodigo() == ic.getCodigoGerente() || ic.getCodigo() == 1) {
							intra_condominios condo = new intra_condominios();
							condo = ic;
							this.listaDeCondominios.add(condo);
							Collections.sort(this.listaDeCondominios);
						}
					}
				}
			} else {
				this.listaDeCondominios = lista;
			}
		} else {
			this.listaDeCondominios = lista;
		}
		return listaDeCondominios;
	}

	/**
	 * @param listaDeCondominios
	 *            the listaDeCondominios to set
	 */
	public void setListaDeCondominios(List<intra_condominios> listaDeCondominios) {
		this.listaDeCondominios = listaDeCondominios;
	}

	public List<intra_condominios> getListaCondominios() {
		if (this.listaCondominios == null) {
			this.listarCondominios();
		}
		return listaCondominios;
	}

	public void setListaCondominios(List<intra_condominios> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public List<intra_condominios> getListaTodosCondominios() {
		if (this.listaTodosCondominios == null) {
			SessaoDAO dao = new SessaoDAO();
			this.listaTodosCondominios = dao.listaDeCondominios();
		}
		return listaTodosCondominios;
	}

	public void setListaTodosCondominios(List<intra_condominios> listaTodosCondominios) {
		this.listaTodosCondominios = listaTodosCondominios;
	}

	/**
	 * @return the listaDeGerente
	 */
	public List<intra_grupo_gerente> getListaDeGerente() {
		SessaoDAO dao = new SessaoDAO();
		if (this.listaDeGerente == null) {
			this.listaDeGerente = dao.listarGerentes();
		}
		return listaDeGerente;
	}

	/**
	 * @param listaDeGerente
	 *            the listaDeGerente to set
	 */
	public void setListaDeGerente(List<intra_grupo_gerente> listaDeGerente) {
		this.listaDeGerente = listaDeGerente;
	}

	public List<intra_grupo_gerente> getListaGerentes() {
		if (this.listaGerentes == null) {
			SessaoDAO dao = new SessaoDAO();
			if (!this.usuario.getGrupoGer().isEmpty()) {
				if (this.usuario.getGrupoGer().get(0).getNome().equals(" Todos")) {
					this.listaGerentes = dao.listarGerentes();
					if (this.listaGerentes != null && this.listaGerentes.size() > 0) {
						if (this.gerenteSelecionado == null) {
							this.gerenteSelecionado = this.listaGerentes.get(0);
						}
					}
				} else {
					if (this.usuario.getGrupoGer() != null) {
						this.listaGerentes = this.usuario.getGrupoGer();
						if (this.usuario.getGrupoGer() != null && this.usuario.getGrupoGer().size() > 0) {
							if (this.gerenteSelecionado == null) {
								this.gerenteSelecionado = this.usuario.getGrupoGer().get(0);
							}
						}
					}
				}
				this.listarCondominios();
			}
		}
		return listaGerentes;
	}

	public void setListaGerentes(List<intra_grupo_gerente> listaGerentes) {
		this.listaGerentes = listaGerentes;
	}

	public void listarCondominios() {
		try {
			if (this.getGerenteSelecionado() != null) {
				SessaoDAO dao = new SessaoDAO();
				if (this.usuario.getGrupoGer().get(0).getNome().equals(" Todos")) {
					this.listaCondominios = dao.listaDeCondominios();
				} else {
					this.listaCondominios = dao.getListaCondominios(this.getGerenteSelecionado().getCodigo());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setGerentePrincipal(intra_usuario usuario) {
		if (this.listaGerentes != null && usuario != null) {
			for (intra_grupo_gerente aux : this.listaGerentes) {
				if (usuario.getGerente() == aux.getCodigo()) {
					this.gerenteSelecionado = aux;
				}
			}
		}
	}

}
