package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_contabilizador;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_usuario;
import br.com.oma.intranet.util.JPAUtil;

@Stateless
public class ContabilizadorDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8352136534209128109L;
	private EntityManager manager;
	private Query query;

	public ContabilizadorDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
	}

	public void salvarNovoContabilizador(intra_contabilizador contabilizador) {
		this.manager.persist(contabilizador);
		intra_log_geral log = new intra_log_geral(contabilizador.getCodigo(), retornaIdUsuario(),
				"intra_contabilizador", true, false, false, contabilizador.toString(), new Date(), 0, null, null);
		this.logGeral(log);
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios() {
		this.query = this.manager.createQuery("from intra_condominios ");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_usuario> getListaUsuario() {
		Query query = this.manager.createQuery(" from intra_usuario");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_contabilizador> getListaContabilizador() {
		this.query = this.manager.createQuery(" from intra_contabilizador");
		return this.query.getResultList();
	}

	public intra_contabilizador pesquisaContabilizadorporCodigo(int codigoContabilizador) {
		return this.manager.find(intra_contabilizador.class, codigoContabilizador);
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

	@SuppressWarnings("unchecked")
	public List<intra_contabilizador> listarContabilizador(Date dataInicial, Date dataFinal,
			List<intra_condominios> condominio_codigo) {

		List<intra_contabilizador> lista = new ArrayList<>();
		for (intra_condominios c : condominio_codigo) {
			Query query = this.manager
					.createQuery("from intra_contabilizador where condominio_codigo = :p3 and dataInserido "
							+ "between :p1 and :p2 order by dataInserido DESC");
			query.setParameter("p1", dataInicial);
			query.setParameter("p2", dataFinal);
			query.setParameter("p3", c.getCodigo());
			lista.addAll(query.getResultList());
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<intra_contabilizador> listarContabilizadorU(Date dataInicial, Date dataFinal, List<intra_usuario> usuario) {
		
		List<intra_contabilizador> lista = new ArrayList<>();
		for(intra_usuario u : usuario){
		Query query = this.manager.createQuery(
				"from intra_contabilizador where usuario = :p3 and dataInserido "
						+ "between :p1 and :p2 order by dataInserido DESC");
		query.setParameter("p1", dataInicial);
		query.setParameter("p2", dataFinal);
		query.setParameter("p3", u.getNome());
		lista.addAll(query.getResultList());
		}
		return lista;
	}
}
