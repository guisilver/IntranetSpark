package br.com.oma.intranet.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intramural;

public class MuralDAO {
	private EntityManager manager;
	private Query query;

	/*----------------------------------------------CONSTRUTOR-----------------------------------------------*/

	public MuralDAO(EntityManager manager) {
		this.manager = manager;
	}

	/*----------------------------------------------CREATE-----------------------------------------------*/

	public void salvarMural(intramural mr) {
		try {
			this.manager.merge(mr);
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Erro ao atualizar!", ""));
		}
	}

	/*----------------------------------------------READ-----------------------------------------------*/

	@SuppressWarnings("unchecked")
	public List<intramural> pesquisaMkt() {
		this.query = this.manager.createQuery("from intramural where mkt is not null and status = true order by data ASC");
		//this.query = this.manager.createQuery("from intramural");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intramural> pesquisaRh() {
		this.query = this.manager.createQuery("from intramural where rh is not null and status = true order by data ASC");
		//this.query = this.manager.createQuery("from intramural");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intramural> pesquisaNoticiasMkt2() {
		this.query = this.manager
				.createQuery("from intramural where mkt is not null order by data DESC");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intramural> pesquisaNoticiasRh2() {
		this.query = this.manager
				.createQuery("from intramural where rh is not null order by data DESC");
		return this.query.getResultList();
	}

	public void mudaStatusNoticia(intramural mr) {
		this.query = this.manager
				.createQuery("update intramural set status = :p0 where codigo = :p1");
		this.query.setParameter("p0", mr.isStatus());
		this.query.setParameter("p1", mr.getCodigo());
		this.query.executeUpdate();
	}

	/*----------------------------------------------DELETE-----------------------------------------------*/

	public void excluiNoticia(int codigo) {
		this.query = this.manager
				.createQuery("delete from intramural where codigo = :par0");
		this.query.setParameter("par0", codigo);
		this.query.executeUpdate();
	}

}
