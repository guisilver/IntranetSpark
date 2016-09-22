package br.com.oma.intranet.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_grupo_permissao;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class GrupoPermissaoDAO extends LogGeralDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager manager;
	private Query query;
	
	private Date data;
	private intra_log_geral ilg;

	public GrupoPermissaoDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void adiciona(intra_grupo_permissao p, SessaoMB sessao) {
		this.manager.persist(p);
		data = new Date();
		this.ilg = new intra_log_geral();
		this.ilg.setIp(sessao.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
		this.ilg.setTabela("intra_grupo_permissao");
		this.ilg.setInfoAnterior("Grupo: " + p.getNomeGrupo());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}
	
	public void alterar(intra_grupo_permissao p) {
		this.manager.merge(p);
	}
	
	public List<intra_grupo_permissao> buscaTodos(){
		return this.manager.createQuery("from intra_grupo_permissao", intra_grupo_permissao.class).getResultList();
	}
	
	public List<intra_grupo_permissao> buscaTodos(String nome){
		return this.manager.createQuery("from intra_grupo_permissao where nome_grupo = '"+nome+"'", intra_grupo_permissao.class).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean verificarExclusaoPermissao(String nome){
		boolean valida = false;
		List<Object> lista = new ArrayList<Object>();
		this.query =  this.manager.createNativeQuery("select * from intra_usuario_intra_grupo_permissao where grupoPer_nome_grupo = '"+nome+"'");
		lista = this.query.getResultList();
		if(!lista.isEmpty()){
			valida = true;
		}
		
		return valida;
	}
	
	public void deletarGrupoPerm(intra_grupo_permissao permissao){
		this.query = this.manager.createQuery("delete intra_grupo_permissao where nome_grupo = :p1");
		this.query.setParameter("p1", permissao.getNomeGrupo());
		this.query.executeUpdate();
	}

	public void desativarGerente(intra_grupo_gerente gerente) {
		this.manager.merge(gerente);
	}
}
