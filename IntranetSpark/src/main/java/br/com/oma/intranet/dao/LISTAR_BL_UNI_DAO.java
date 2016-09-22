package br.com.oma.intranet.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_cndunidade;
import br.com.oma.intranet.util.JPAUtil;

public class LISTAR_BL_UNI_DAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 613957596560168401L;
	private EntityManager manager;
	private Query query;

	public LISTAR_BL_UNI_DAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_cndunidade> listaDeUnidade(int condominio, String bloco) {
		List<intra_cndunidade> listaUnidade = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select condominio, bloco, unidade, nome from sigadm.dbo.cndunida where condominio = " + condominio
						+ " and bloco like '%" + bloco + "%' order by unidade");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cndunidade unida = new intra_cndunidade();
				unida.setCondominio(Integer.valueOf(obj[0].toString()));
				unida.setBloco(obj[1].toString());
				unida.setUnidade(obj[2].toString());
				unida.setNome(obj[3].toString());
				listaUnidade.add(unida);
			}
		}
		return listaUnidade;
	}

	@SuppressWarnings("unchecked")
	public List<intra_cndunidade> listaDeBloco(int condominio) {
		List<intra_cndunidade> listaBloco = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select distinct bloco from sigadm.dbo.cndunida where condominio = " + condominio + " order by bloco");
		List<String> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (String obj : lista) {
				intra_cndunidade unida = new intra_cndunidade();
				unida.setBloco(obj);
				listaBloco.add(unida);
			}
		}
		return listaBloco;
	}

}
