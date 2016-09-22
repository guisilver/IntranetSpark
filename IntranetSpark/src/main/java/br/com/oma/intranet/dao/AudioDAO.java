package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.oma.intranet.entidades.Audio;
import br.com.oma.intranet.util.JPAUtil;

public class AudioDAO {

	private EntityManager manager;

	public AudioDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvarAudio(Audio audio) {
		this.manager.persist(audio);
	}

	public List<Audio> getAudios() {
		return this.manager.createQuery("from Audio order by nomeArquivo", Audio.class).getResultList();
	}

	public void excluirAudio(Audio audioSelecionado) {
		audioSelecionado = this.manager.merge(audioSelecionado);
		this.manager.remove(audioSelecionado);
	}

}
