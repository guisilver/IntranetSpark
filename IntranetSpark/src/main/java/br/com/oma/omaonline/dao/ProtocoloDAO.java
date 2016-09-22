package br.com.oma.omaonline.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.dao.LogGeralDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cndpagar_followup;
import br.com.oma.omaonline.entidades.financeiro_imagem;
import br.com.oma.omaonline.entidades.protocolo;
import br.com.oma.omaonline.entidades.protocolo_lancamentos;
import br.com.oma.omaonline.entidades.protocolo_obs;


public class ProtocoloDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4382131141900206197L;

	private EntityManager manager;
	private Query query;

	public ProtocoloDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public financeiro_imagem pesquisaImagem(short cdCnd, double idImagem) {
		this.query = this.manager
				.createQuery("from financeiro_imagem where id = :p1 and cd_cnd = :p2");
		this.query.setParameter("p1", idImagem);
		this.query.setParameter("p2", cdCnd);
		List<financeiro_imagem> lst = this.query.getResultList();
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return new financeiro_imagem();
		}
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> pesquisaLancto(financeiro_imagem img) {
		img = this.manager.merge(img);
		this.query = this.manager
				.createQuery("from cndpagar where :p1 MEMBER OF imagens");
		this.query.setParameter("p1", img);
		return this.query.getResultList();
	}

	public void salvarProtocolo(protocolo protocolo) {
		if (protocolo.getCodigo() == 0) {
			this.manager.persist(protocolo);
		} else {
			this.manager.merge(protocolo);
		}
	}

	@SuppressWarnings("unchecked")
	public List<protocolo> listarProtocolos(short codigoCnd) {
		this.query = this.manager
				.createQuery("from protocolo where cd_cnd = :p1 order by data");
		this.query.setParameter("p1", codigoCnd);
		return this.query.getResultList();
	}

	/*
	 * public void baixarProtocolo(protocolo protocolo, String email, String ip)
	 * { protocolo.setSituacao(true); this.manager.merge(protocolo); for
	 * (protocolo_lancamentos aux : protocolo.getLancamentos()) { String
	 * obsLancto = "C�d. Barras N�: " + aux.getIdImagem();
	 * this.registrarFollowUp(aux.getCdLancto(), "Protocolo Recebido", email,
	 * ip, protocolo.getCd_cnd(), obsLancto); } }
	 */

	public void registrarFollowUp(int nrolancto, String acao, String feitoPor,
			String ip, short cdCnd, String obsLancto) {
		cndpagar_followup followup = new cndpagar_followup();
		followup.setObs(obsLancto);
		followup.setData(new Date());
		followup.setNrolancto(nrolancto);
		followup.setAcao(acao);
		followup.setFeitoPor(feitoPor);
		followup.setIp(ip);
		followup.setCdCnd(cdCnd);
		this.manager.persist(followup);
	}

	public void baixarProtocoloLancamento(protocolo_lancamentos lancto) {
		this.manager.merge(lancto);
	}

	public void excluirProtocolo(protocolo protocolo) {
		protocolo = this.manager.merge(protocolo);
		this.manager.remove(protocolo);
	}

	public void salvarObs(protocolo_obs obs) {
		if (obs.getCodigo() == 0) {
			this.manager.persist(obs);
		} else {
			this.manager.merge(obs);
		}
	}

	@SuppressWarnings("unchecked")
	public protocolo_obs pesquisaObs(short codigoCnd) {
		this.query = this.manager
				.createQuery("from protocolo_obs where cd_cnd = :p1");
		this.query.setParameter("p1", codigoCnd);
		List<protocolo_obs> lst = this.query.getResultList();
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return new protocolo_obs();
		}
	}

	public protocolo pesquisaProtocolo(int codigoProtocolo) {
		protocolo result = this.manager.find(protocolo.class, codigoProtocolo);
		this.manager.detach(result);
		return result;
	}

}
