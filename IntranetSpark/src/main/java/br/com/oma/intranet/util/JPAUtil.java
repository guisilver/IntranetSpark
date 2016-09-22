package br.com.oma.intranet.util;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class JPAUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6802880726744642364L;

	/**
	 * RECUPERA A CONEX�O COM BANCO DE DADOS ARMAZENADA NA SESSAO
	 */
	public static EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

}
