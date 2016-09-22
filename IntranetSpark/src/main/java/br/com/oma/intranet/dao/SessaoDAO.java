package br.com.oma.intranet.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.util.JPAUtil;

public class SessaoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6556413209058920411L;
	private EntityManager manager;

	public SessaoDAO() {
		this.manager = JPAUtil.getManager();
	}

	public List<intra_condominios> listaDeCondominios() {
		return this.manager.createQuery("from intra_condominios order by codigo", intra_condominios.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> pesquidaCondominiosSiga() {
		List<intra_condominios> listaCnd = new ArrayList<>();
		intra_condominios cnd = null;
		Query query = this.manager.createNativeQuery(
				"select codigo,bairro,gerente,endereco,logradouro,nome,nro_end from sigadm.dbo.cndcondo order by codigo");
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			cnd = new intra_condominios();
			if (aux[0] != null) {
				cnd.setCodigo(Short.parseShort(aux[0].toString()));
			}
			if (aux[1] != null) {
				cnd.setBairro(aux[1].toString());
			}
			if (aux[2] != null) {
				cnd.setCodigoGerente(Integer.parseInt(aux[2].toString()));
				intra_grupo_gerente ger = this.pesquisaGerentePorCodigo(cnd.getCodigoGerente());
				if (ger != null) {
					cnd.setNomeGerente(ger.getNome());
					cnd.setEmailGerente(ger.getEmail());
				}
			}
			if (aux[3] != null) {
				cnd.setEndereco(aux[3].toString());
			}
			if (aux[4] != null) {
				cnd.setLogradouro(aux[4].toString());
			}
			if (aux[5] != null) {
				cnd.setNome(aux[5].toString());
			}
			if (aux[6] != null) {
				cnd.setNro(aux[6].toString());
			}

			listaCnd.add(cnd);
		}
		return listaCnd;
	}

	public intra_grupo_gerente pesquisaGerentePorCodigo(int codigo) {
		return this.manager.find(intra_grupo_gerente.class, codigo);
	}

	public List<intra_grupo_gerente> listarGerentes() {
		return this.manager
				.createQuery("from intra_grupo_gerente where baixar = 0 order by nome", intra_grupo_gerente.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios(int codigo) {
		Query query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1");
		query.setParameter("p1", codigo);
		return query.getResultList();
	}
}
