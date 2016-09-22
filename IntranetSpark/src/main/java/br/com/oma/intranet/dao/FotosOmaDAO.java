package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_albuns;
import br.com.oma.intranet.util.JPAUtil;

public class FotosOmaDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3411769007267086213L;
	private EntityManager manager;

	public FotosOmaDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvarFotos(intra_albuns album) {
		this.manager.merge(album);
	}

	public void salvarNovoAlbum(intra_albuns album) {
		this.manager.persist(album);
	}

	public intra_albuns pesquisaAlbumPorCodigo(int codigoAlbum) {
		return this.manager.find(intra_albuns.class, codigoAlbum);
	}

	@SuppressWarnings("unchecked")
	public List<intra_albuns> getAlbuns() {
		return this.manager
				.createQuery(
						"from intra_albuns where nome is not null order by dataCriacao")
				.getResultList();
	}

	public void excluirAlbum(intra_albuns albumSelecionado) {
		albumSelecionado = this.manager.merge(albumSelecionado);
		this.manager.remove(albumSelecionado);
	}

	@SuppressWarnings("unchecked")
	public List<intra_albuns> pesquisaAlbumPorNome(String filename) {
		Query query = this.manager
				.createQuery("from intra_albuns where nome = :p1 order by dataCriacao");
		query.setParameter("p1", filename);
		return query.getResultList();
	}

	public void alterarNomeAlbum(intra_albuns albumSelecionado) {
		this.manager.merge(albumSelecionado);
	}
}
