package br.com.oma.omaonline.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.dao.LogGeralDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.black_list;
import br.com.oma.omaonline.entidades.cndpagar;

public class BlackListDAO extends LogGeralDAO{

	private static final long serialVersionUID = 1L;

	private EntityManager manager;
	
	public BlackListDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public boolean verificaTodos(intra_condominios condoMB, black_list blackListMB) {
		Query query = this.manager.createQuery("from black_list where condominio = :p1 and cnpj = :p2 and conta_contabil = :p3 and codigo_gerente = :p4 and status_item = 1");
		query.setParameter("p1", condoMB.getCodigo());
		query.setParameter("p2", blackListMB.getCnpj());
		query.setParameter("p3", blackListMB.getContaContabil());
		query.setParameter("p4", condoMB.getCodigoGerente());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean verificaCondoCNPJ(intra_condominios condoMB, black_list blackListMB) {
		Query query = this.manager.createQuery("from black_list where condominio = :p1 and cnpj = :p2 and conta_contabil = 0 and codigo_gerente = :p3 and status_item = 1");
		query.setParameter("p1", condoMB.getCodigo());
		query.setParameter("p2", blackListMB.getCnpj());
		query.setParameter("p3", condoMB.getCodigoGerente());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean verificaCondoContabil(intra_condominios condoMB, black_list blackListMB) {
		Query query = this.manager.createQuery("from black_list where condominio = :p1 and conta_contabil = :p2 and cnpj = 0 and codigo_gerente = :p3 and status_item = 1");
		query.setParameter("p1", condoMB.getCodigo());
		query.setParameter("p2", blackListMB.getContaContabil());
		query.setParameter("p3", condoMB.getCodigoGerente());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean verificaCondo(intra_condominios condoMB) {
		Query query = this.manager.createQuery("from black_list where condominio = :p1 and cnpj = 0 and conta_contabil = 0 and codigo_gerente = :p2 and status_item = 1");
		query.setParameter("p1", condoMB.getCodigo());
		query.setParameter("p2", condoMB.getCodigoGerente());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificaCondo(black_list bl) {
		Query query = this.manager.createQuery("from black_list where condominio = :p1 and cnpj = 0 and conta_contabil = 0 and codigo_gerente = :p2 and status_item = 1");
		query.setParameter("p1", bl.getCondominio());
		query.setParameter("p2", bl.getCodigoGerente());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean verificaCNPJContabil(black_list blackListMB, SessaoMB sessao) {
		Query query = this.manager.createQuery("from black_list where cnpj = :p1 and conta_contabil = :p2 and condominio = 0 and codigo_gerente = :p3 and status_item = 1");
		query.setParameter("p1", blackListMB.getCnpj());
		query.setParameter("p2", blackListMB.getContaContabil());
		query.setParameter("p3", sessao.getUsuario().getGrupoGer().get(0).getCodigo());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificaCNPJContabil(black_list blackListMB, cndpagar pagar) {
		Query query = this.manager.createQuery("from black_list where cnpj = :p1 and conta_contabil = :p2 and condominio = 0 and codigo_gerente = :p3 and status_item = 1");
		query.setParameter("p1", blackListMB.getCnpj());
		query.setParameter("p2", blackListMB.getContaContabil());
		query.setParameter("p3", pagar.getCodigoGerente());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificaCNPJ(black_list blackListMB, SessaoMB sessao) {
		Query query = this.manager.createQuery("from black_list where cnpj = :p1 and condominio = 0 and conta_contabil = 0 and codigo_gerente = :p2 and status_item = 1");
		query.setParameter("p1", blackListMB.getCnpj());
		query.setParameter("p2", sessao.getUsuario().getGrupoGer().get(0).getCodigo());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificaCNPJ(black_list blackListMB, cndpagar pagar) {
		Query query = this.manager.createQuery("from black_list where cnpj = :p1 and condominio = 0 and conta_contabil = 0 and codigo_gerente = :p2 and status_item = 1");
		query.setParameter("p1", blackListMB.getCnpj());
		query.setParameter("p2", pagar.getCodigoGerente());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean verificaContabil(black_list blackListMB, SessaoMB sessao) {
		Query query = this.manager.createQuery("from black_list where conta_contabil = :p1 and condominio = 0 and cnpj = 0 and codigo_gerente = :p2 and status_item = 1");
		query.setParameter("p1", blackListMB.getContaContabil());
		query.setParameter("p2", sessao.getUsuario().getGrupoGer().get(0).getCodigo());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificaContabil(black_list blackListMB, cndpagar pagar) {
		Query query = this.manager.createQuery("from black_list where conta_contabil = :p1 and condominio = 0 and cnpj = 0 and codigo_gerente = :p2 and status_item = 1");
		query.setParameter("p1", blackListMB.getContaContabil());
		query.setParameter("p2", pagar.getCodigoGerente());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	public void salvarRegra(black_list blackListMB, SessaoMB sessaoMB) {
		this.manager.persist(blackListMB);
		intra_log_geral lg = new intra_log_geral();
		lg.setDataFeito(new Date());
		lg.setTabela("omaonline.dbo.black_list");
		lg.setAlterar(false);
		lg.setCondominio(blackListMB.getCondominio());
		lg.setDeletar(false);
		lg.setInserir(true);
		lg.setIp("oma");
		lg.setInfoAnterior("Inserido novo bloqueio");
		lg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.logGeral(lg);
	}

/*	@SuppressWarnings("unchecked")
	public List<black_list> listarBlackList(int	codigo) {
		Query query = this.manager.createQuery("from black_list where codigo_gerete = :p1");
		query.setParameter("p1", codigo);
		return query.getResultList();
	}*/

	@SuppressWarnings("unchecked")
	public List<black_list> listarBlackList(int i) {
		Query query = this.manager.createQuery("from black_list where codigo_gerente = :p1");
		query.setParameter("p1", i);
		return query.getResultList();
	}

	public void regraGeral(intra_condominios condoMB, SessaoMB sessaoMB) {
		Query query = this.manager.createQuery("update black_list set status_item = 0 where condominio = :p1 and codigo_gerente = :p2");
		query.setParameter("p1", condoMB.getCodigo());
		query.setParameter("p2", condoMB.getCodigoGerente());
		query.executeUpdate();
		
		intra_log_geral lg = new intra_log_geral();
		lg.setDataFeito(new Date());
		lg.setTabela("omaonline.dbo.black_list");
		lg.setAlterar(true);
		lg.setCondominio(condoMB.getCodigo());
		lg.setDeletar(false);
		lg.setInserir(false);
		lg.setIp("oma");
		lg.setInfoAnterior("Alterado bloqueio");
		lg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.logGeral(lg);
	}
	
	public void regraGeral2(intra_condominios condoMB, SessaoMB sessaoMB) {
		
		Query query = this.manager.createQuery("update black_list set status_item = 0 where condominio = :p1 and codigo_gerente = :p2 and cnpj > 0");
		query.setParameter("p1", condoMB.getCodigo());
		query.setParameter("p2", condoMB.getCodigoGerente());
		query.executeUpdate();
		
		Query query2 = this.manager.createQuery("update black_list set status_item = 0 where condominio = :p1 and codigo_gerente = :p2 and conta_contabil > 0");
		query2.setParameter("p1", condoMB.getCodigo());
		query2.setParameter("p2", condoMB.getCodigoGerente());
		query2.executeUpdate();
		
		Query query3 = this.manager.createQuery("update black_list set status_item = 1 where condominio = :p1 and codigo_gerente = :p2 and conta_contabil = 0 and cnpj = 0");
		query3.setParameter("p1", condoMB.getCodigo());
		query3.setParameter("p2", condoMB.getCodigoGerente());
		query3.executeUpdate();
		
		intra_log_geral lg = new intra_log_geral();
		lg.setDataFeito(new Date());
		lg.setTabela("omaonline.dbo.black_list");
		lg.setAlterar(true);
		lg.setCondominio(condoMB.getCodigo());
		lg.setDeletar(false);
		lg.setInserir(false);
		lg.setIp("oma");
		lg.setInfoAnterior("Alterado bloqueio");
		lg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.logGeral(lg);
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		Query query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1");
		query.setParameter("p1", codigo);
		return query.getResultList();
	}

	public void deleteBlackList(black_list bl, SessaoMB sessaoMB) {
		 Query query = this.manager.createQuery("delete black_list where codigo = :p1");
		query.setParameter("p1", bl.getCodigo());
		query.executeUpdate();
		
		intra_log_geral lg = new intra_log_geral();
		lg.setDataFeito(new Date());
		lg.setTabela("omaonline.dbo.black_list");
		lg.setAlterar(false);
		lg.setCondominio(bl.getCondominio());
		lg.setDeletar(true);
		lg.setInserir(false);
		lg.setIp("oma");
		lg.setInfoAnterior("excluido bloqueio");
		lg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.logGeral(lg);
	}

	public void update(black_list bl, SessaoMB sessaoMB) {
		this.manager.merge(bl);
		
		intra_log_geral lg = new intra_log_geral();
		lg.setDataFeito(new Date());
		lg.setTabela("omaonline.dbo.black_list");
		lg.setAlterar(true);
		lg.setCondominio(bl.getCondominio());
		lg.setDeletar(false);
		lg.setInserir(false);
		lg.setIp("oma");
		lg.setInfoAnterior("Alterado bloqueio");
		lg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.logGeral(lg);
	}

	@SuppressWarnings("unchecked")
	public boolean verificaCodigoPlano(intra_condominios condoMB, black_list blackListMB) {
		Query query = this.manager.createNativeQuery(""
				+ "select c.codigo from sigadm.dbo.cndcondo c "
				+ "inner join sigadm.dbo.cndplano p on c.codigo_plano = p.codigo_plano "
				+ "where c.codigo = :p1 and p.cod_reduzido = :p2");
		query.setParameter("p1", condoMB.getCodigo());
		query.setParameter("p2", blackListMB.getContaContabil());
		List<Object[]> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	
}
