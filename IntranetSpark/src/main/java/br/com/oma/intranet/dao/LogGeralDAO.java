package br.com.oma.intranet.dao;

import javax.persistence.EntityManager;

import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.intranet.util.Mensagens;

public class LogGeralDAO extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1489307994207896404L;
	private EntityManager manager;
	
	public LogGeralDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	public void logGeral(intra_log_geral lg){
		this.manager.persist(lg);
	}
}
