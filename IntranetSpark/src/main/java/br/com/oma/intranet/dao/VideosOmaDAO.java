package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.oma.intranet.entidades.intra_videos;
import br.com.oma.intranet.util.JPAUtil;

public class VideosOmaDAO {

	private EntityManager manager;

	public VideosOmaDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvarNovoVideo(intra_videos video) {
		this.manager.persist(video);
	}

	@SuppressWarnings("unchecked")
	public List<intra_videos> getVideos() {
		return this.manager
				.createQuery(
						"from intra_videos where nome is not null order by dataCriacao")
				.getResultList();
	}

	public void excluirVideo(intra_videos videoSelecionado) {
		videoSelecionado = this.manager.merge(videoSelecionado);
		this.manager.remove(videoSelecionado);
	}

}
