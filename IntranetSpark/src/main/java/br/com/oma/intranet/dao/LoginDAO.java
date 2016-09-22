package br.com.oma.intranet.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_usuario;
import br.com.oma.intranet.util.JPAUtil;

public class LoginDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6619127245668642020L;
	private EntityManager manager;
	private Query query;
	
	public LoginDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	public List<intra_usuario> buscaTodos(intra_usuario login) {
		return this.manager.createQuery("from intra_usuario where email = '"+login.getEmail()+"' and senha = '"+login.getSenha()+"'", intra_usuario.class).getResultList();
	}
	
	public List<intra_usuario> esqueceuSenha(intra_usuario login) {
		return this.manager.createQuery("from intra_usuario where email = '"+login.getEmail()+"'", intra_usuario.class).getResultList();
	}
	
	public void updateSenha(intra_usuario senha){
		this.query = this.manager.createQuery("update intra_usuario set senha = :p1 where email = :p2");
		this.query.setParameter("p1", senha.getSenha());
		this.query.setParameter("p2", senha.getEmail());
		this.query.executeUpdate();
	}
}
