package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_grupo_depto;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class GrupoDeptoDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995975827523329722L;

	private EntityManager manager;
	private Query query;
	
	private Date data;
	private intra_log_geral ilg;

	public GrupoDeptoDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void adiconaGrupo(intra_grupo_depto g, SessaoMB sessao) {
		this.manager.persist(g);
		data = new Date();
		this.ilg = new intra_log_geral();
		this.ilg.setIp(sessao.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
		this.ilg.setTabela("intra_grupo_depto");
		this.ilg.setInfoAnterior("Grupo: " + g.getNome());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void excluirGrupo(intra_grupo_depto g, SessaoMB sessao) {
		try {
			this.query = this.manager
					.createQuery("delete intra_grupo_depto where nome = :p1");
			this.query.setParameter("p1", g.getNome());
			this.query.executeUpdate();
			
			data = new Date();
			this.ilg = new intra_log_geral();
			this.ilg.setIp(sessao.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(true);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
			this.ilg.setTabela("intra_grupo_depto");
			this.ilg.setInfoAnterior("Grupo: " + g.getNome());
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
			
		} catch (Exception e) {
			this.msgErro();
		}
	}

	public List<intra_grupo_depto> buscaTodos() {
		return this.manager.createQuery("select x from intra_grupo_depto x",
				intra_grupo_depto.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public boolean pesquisaDepto(intra_grupo_depto depto) {
		List<Object[]> lista = new ArrayList<Object[]>();
		boolean valida = false;
		this.query = this.manager.createNativeQuery("select * from intra_usuario_intra_grupo_depto where grupoDepto_nome = :p1");
		this.query.setParameter("p1", depto.getNome());
		lista = query.getResultList();
		if(lista.size() > 0){
			valida = true;
		}
			
		return valida;
	}
	

}
