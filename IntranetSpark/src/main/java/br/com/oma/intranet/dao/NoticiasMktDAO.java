package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.oma.intranet.entidades.intra_noticias;
import br.com.oma.intranet.entidades.intra_noticias_mkt;
import br.com.oma.intranet.util.JPAUtil;

public class NoticiasMktDAO {

	private EntityManager manager;

	public NoticiasMktDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvarNovaNoticia(intra_noticias_mkt noticia) {
		this.manager.persist(noticia);
	}

	public void salvarAlteracoesNoticia(intra_noticias_mkt noticia) {
		this.manager.merge(noticia);
	}

	public void excluirNoticia(intra_noticias_mkt noticia) {
		noticia = this.manager.merge(noticia);
		this.manager.remove(noticia);
	}

	public List<intra_noticias_mkt> getListaNoticias() {
		return this.manager.createQuery("FROM intra_noticias_mkt ORDER BY dataNoticia DESC", intra_noticias_mkt.class)
				.getResultList();
	}

	public void salvarNovaNoticia2(intra_noticias noticia) {
		this.manager.persist(noticia);
	}

	public List<intra_noticias> getListaNoticias2() {
		return this.manager.createQuery("FROM intra_noticias", intra_noticias.class).getResultList();
	}

	public intra_noticias pesquisaNoticiaPorCodigo(int codigoNoticia) {
		return this.manager.find(intra_noticias.class, codigoNoticia);
	}

	public List<intra_noticias> getNoticias() {
		return this.manager.createQuery("FROM intra_noticias ORDER BY dataInserido DESC", intra_noticias.class)
				.getResultList();
	}

	public List<intra_noticias> getListaColunaEsquerda() {
		return this.manager
				.createQuery("FROM intra_noticias WHERE coluna = 1  ORDER BY dataInserido DESC", intra_noticias.class)
				.getResultList();
	}

	public List<intra_noticias> getListaColunaDireita() {
		return this.manager
				.createQuery("FROM intra_noticias WHERE coluna = 2 ORDER BY dataInserido DESC", intra_noticias.class)
				.getResultList();
	}

	public void excluirNoticia(intra_noticias noticia) {
		noticia = this.manager.merge(noticia);
		this.manager.remove(noticia);
	}

	public void salvarAlteracoesNoticia(intra_noticias noticia) {
		this.manager.merge(noticia);
	}

}
