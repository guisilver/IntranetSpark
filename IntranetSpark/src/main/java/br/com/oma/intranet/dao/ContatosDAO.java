package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.entidades.intra_agd_contatos;
import br.com.oma.intranet.entidades.intra_agd_ddd;
import br.com.oma.intranet.entidades.intra_agd_origem;
import br.com.oma.intranet.entidades.intra_agd_rel_cel;
import br.com.oma.intranet.entidades.intra_agd_rel_tel;
import br.com.oma.intranet.entidades.intra_cidade;
import br.com.oma.intranet.entidades.intra_grupo_depto;
import br.com.oma.intranet.entidades.intra_uf;

public class ContatosDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7497672997170381370L;
	private EntityManager manager;
	private Query query;

	public ContatosDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void adicionarContato(intra_agd_contatos i) {
		this.manager.persist(i);
	}

	public void adicionaCelular(intra_agd_rel_cel i) {
		try {
			this.manager.persist(i);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Adicionado com sucesso!", ""));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao salvar!", "Entre em contato com o administrador!"));
		}
	}

	public void adicionaResidencial(intra_agd_rel_tel i) {
		try {
			this.manager.persist(i);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Adicionado com sucesso!", ""));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao salvar!", "Entre em contato com o administrador!"));
		}
	}
	
	public void adicionaDDD(intra_agd_ddd i){
		try {
			this.manager.persist(i);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Adicionado com sucesso!", ""));
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Erro ao adicionar!",
							"Entre em contato com o administrador!"));
		}
	}
	
	/*************** READ ***************/

	@SuppressWarnings("unchecked")
	public List<intra_agd_contatos> listaContatos() {
		this.query = this.manager
				.createQuery("from intra_agd_contatos order by nome");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_origem> listaOrigem() {
		this.query = this.manager.createQuery("from intra_agd_origem");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_uf> listaUF() {
		this.query = this.manager.createQuery("from intra_uf");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_cidade> listaCidades(intra_uf estado) {
		this.query = this.manager
				.createQuery("from intra_cidade where fk_uf = :p0");
		this.query.setParameter("p0", estado);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_ddd> listaDDD() {
		this.query = this.manager.createQuery("from intra_agd_ddd");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_grupo_depto> listaDeptos() {
		this.query = this.manager.createQuery("from intra_grupo_depto");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_contatos> filtraNome(String filtro_nome) {
		String query = "select * from  intra_agd_contatos where nome COLLATE Latin1_general_CI_AI like '%"
				+ filtro_nome + "%' order by nome";
		TypedQuery<intra_agd_contatos> typed_query = (TypedQuery<intra_agd_contatos>) this.manager
				.createNativeQuery(query, intra_agd_contatos.class);
		List<intra_agd_contatos> lista = typed_query.getResultList();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_contatos> pesquisaContato(int codigo) {
		this.query = this.manager
				.createQuery("from intra_agd_contatos where codigo = :p0");
		this.query.setParameter("p0", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_contatos> pesquisaTelefone(
			intra_agd_contatos linha_selecionada) {
		this.query = this.manager
				.createQuery("from intra_agd_contatos where codigo = :p0");
		this.query.setParameter("p0", linha_selecionada.getCodigo());
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public String pesquisaUF(int ddd) {
		String uf_nome = null;
		this.query = this.manager
				.createQuery("from intra_agd_ddd where ddd = :p0");
		this.query.setParameter("p0", ddd);
		List<intra_agd_ddd> lista_ddd = this.query.getResultList();
		for (intra_agd_ddd aux : lista_ddd) {
			uf_nome = aux.getUf();
		}
		return uf_nome;
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_contatos> pesquisaRamaisDeptos() {
		this.query = this.manager
				.createNativeQuery("select distinct fk_depto from intra_agd_contatos where fk_depto is not null and fk_depto not like '' and fk_depto not like 'Motoqueiro'  order by fk_depto");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> pesquisaRamais(String departamento) {
		this.query = this.manager
				.createNativeQuery("select nome,ramal,email_oma from intra_agd_contatos where fk_depto is not null and fk_depto not like '' and fk_depto = :p0  and ramal is not null and ramal not like '' order by fk_depto,nome");
		this.query.setParameter("p0", departamento);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_rel_tel> relatorioTel(Date inicio, Date fim) {
		this.query = this.manager
				.createQuery("from intra_agd_rel_tel where data between :p0 and :p1 order by data");
		this.query.setParameter("p0", inicio);
		this.query.setParameter("p1", fim);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_rel_cel> relatorioCel(Date inicio, Date fim) {
		this.query = this.manager
				.createQuery("from intra_agd_rel_cel where data between :p0 and :p1 order by data");
		this.query.setParameter("p0", inicio);
		this.query.setParameter("p1", fim);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_rel_cel> pesquisaCelular() {
		this.query = this.manager.createQuery("from intra_agd_rel_cel");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_agd_rel_tel> pesquisaInterurbano() {
		this.query = this.manager.createQuery("from intra_agd_rel_tel");
		return this.query.getResultList();
	}
	
	/*************** UPDATE ***************/
	
	public void atualizarContato(intra_agd_contatos contato2) {
		this.manager.merge(contato2);
	}
	
	/*************** DELETE ***************/
	
	public boolean excluiContato(intra_agd_contatos[] linha_selecionada) {
		int i = 0;
		for (intra_agd_contatos aux : linha_selecionada) {
			this.query = this.manager
					.createQuery("delete from intra_agd_contatos where codigo = :p0 ");
			this.query.setParameter("p0", aux.getCodigo());
			i = this.query.executeUpdate();
		}
		if (i > 0) {
			return true;
		} else if (i == 0) {
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public intra_agd_origem pesquisaOrigem(int codigo_origem) {
		this.query = this.manager
				.createQuery("from intra_agd_origem where codigo = :p0 ");
		this.query.setParameter("p0", codigo_origem);
		List<intra_agd_origem> lista_origem = this.query.getResultList();
		intra_agd_origem origem = null;
		for (intra_agd_origem aux : lista_origem) {
			origem = new intra_agd_origem();
			origem = aux;
		}
		return origem;
	}

	@SuppressWarnings("unchecked")
	public intra_grupo_depto pesquisaDeptos(int codigo_depto) {
		this.query = this.manager
				.createQuery("from intra_grupo_depto where codigo = :p0");
		this.query.setParameter("p0", codigo_depto);
		List<intra_grupo_depto> lista_depto = this.query.getResultList();
		intra_grupo_depto depto = null;
		for (intra_grupo_depto aux : lista_depto) {
			depto = new intra_grupo_depto();
			depto = aux;
		}
		return depto;
	}

	public boolean excluiCelular(intra_agd_rel_cel[] linha_selecionada2) {
		int i = 0;
		for (intra_agd_rel_cel aux : linha_selecionada2) {
			this.query = this.manager
					.createQuery("delete from intra_agd_rel_cel where codigo = :p0 ");
			this.query.setParameter("p0", aux.getCodigo());
			i = this.query.executeUpdate();
		}
		if (i > 0) {
			return true;
		} else if (i == 0) {
			return false;
		}
		return false;
	}

	public boolean excluiResidencial(intra_agd_rel_tel[] linha_selecionada3) {
		int i = 0;
		for (intra_agd_rel_tel aux : linha_selecionada3) {
			this.query = this.manager
					.createQuery("delete from intra_agd_rel_tel where codigo = :p0 ");
			this.query.setParameter("p0", aux.getCodigo());
			i = this.query.executeUpdate();
		}
		if (i > 0) {
			return true;
		} else if (i == 0) {
			return false;
		}
		return false;
	}

	public boolean excluiDDD(intra_agd_ddd[] linha_selecionada4) {
		int i = 0;
		for (intra_agd_ddd aux : linha_selecionada4) {
			this.query = this.manager
					.createQuery("delete from intra_agd_ddd where ddd = :p0 ");
			this.query.setParameter("p0", aux.getDdd());
			i = this.query.executeUpdate();
		}
		if (i > 0) {
			return true;
		} else if (i == 0) {
			return false;
		}
		return false;
	}
}