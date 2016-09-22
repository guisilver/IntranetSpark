package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_advert_mult;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class AdvertenciaMultaDAO extends LogGeralDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5557548245861943244L;
	
	private EntityManager manager;
	private Query query;
	
	public AdvertenciaMultaDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}
	
	public intra_advert_mult salvar(intra_advert_mult am, SessaoMB sessao){
		this.manager.persist(am);
		intra_log_geral lg = new intra_log_geral();
		lg.setCondominio(am.getCondominio());
		lg.setAlterar(false);
		lg.setInserir(true);
		lg.setDeletar(false);
		lg.setDataFeito(new Date());
		lg.setTabela("intra_advert_mult");
		lg.setFeitoPor(sessao.getUsuario().getEmail());
		lg.setInfoAnterior("salvar protocolo advertencia/multa codigo: "+am.getCodigo());
		this.logGeral(lg);
		return am;
	}

	@SuppressWarnings("unchecked")
	public List<intra_advert_mult> listarAdvmMul(int codigo) {
		this.query = this.manager.createQuery("from intra_advert_mult where codigo_gerente = :p1 order by codigo desc");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	public void alterarAdvm(intra_advert_mult amBEAN, SessaoMB sessao) {
		intra_log_geral lg = new intra_log_geral();
		lg.setCondominio(amBEAN.getCondominio());
		lg.setAlterar(true);
		lg.setInserir(false);
		lg.setDeletar(false);
		lg.setDataFeito(new Date());
		lg.setTabela("intra_advert_mult");
		lg.setFeitoPor(sessao.getUsuario().getEmail());
		lg.setInfoAnterior("Alterado advertencia/multa codigo: "+amBEAN.getCodigo());
		this.logGeral(lg);
		this.manager.merge(amBEAN);		
	}

	public void deletarAdvertencia(intra_advert_mult amBEAN, SessaoMB sessao) {
		intra_log_geral lg = new intra_log_geral();
		lg.setCondominio(amBEAN.getCondominio());
		lg.setAlterar(false);
		lg.setInserir(false);
		lg.setDeletar(true);
		lg.setDataFeito(new Date());
		lg.setTabela("intra_advert_mult");
		lg.setFeitoPor(sessao.getUsuario().getEmail());
		lg.setInfoAnterior("Excluido advertencia/multa codigo: "+amBEAN.getCodigo());
		this.logGeral(lg);
		this.query = this.manager.createQuery("delete intra_advert_mult where codigo = :p1");
		this.query.setParameter("p1", amBEAN.getCodigo());
		this.query.executeUpdate();
	}
	
	public void salvarProtocolo(intra_advert_mult advm, SessaoMB sessao) {
		this.manager.merge(advm);
		intra_log_geral lg = new intra_log_geral();
		lg.setCondominio(advm.getCondominio());
		lg.setAlterar(false);
		lg.setInserir(true);
		lg.setDeletar(false);
		lg.setDataFeito(new Date());
		lg.setTabela("intra_advert_mult");
		lg.setFeitoPor(sessao.getUsuario().getEmail());
		lg.setInfoAnterior("salvar protocolo advertencia/multa codigo: "+advm.getCodigo());
		this.logGeral(lg);
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaProtocolo(Integer id) {
		byte[] protocolo = null;
		this.query = this.manager
				.createQuery("from intra_advert_mult where codigo = :p1");
		this.query.setParameter("p1", id);
		List<intra_advert_mult> listaArea = this.query.getResultList();
		for (intra_advert_mult aux : listaArea) {
			if (aux.getProtocolo() != null) {
				protocolo = aux.getProtocolo();
			}
		}
		return protocolo;
	}

}
