package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_netimoveis_captacao2;
import br.com.oma.intranet.entidades.intra_netimoveis_proprietario;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class NetimoveisDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7110766414199348736L;
	private Query query;
	private EntityManager manager;
	private intra_log_geral ilg = new intra_log_geral();
	private Date data;

	public NetimoveisDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
	}

	public void salvarCaptacao(intra_netimoveis_captacao2 cp, SessaoMB sessaoMB) {
		if (cp.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(cp);
			this.ilg.setCondominio(cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_netimoveis_captacao2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(cp);
			this.ilg.setCondominio(cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_netimoveis_captacao2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void excluirCaptacao(intra_netimoveis_captacao2 cp, SessaoMB sessaoMB) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.manager.remove(cp);
		this.ilg.setCondominio(cp.getId());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_netimoveis_captacao2");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();

	}

	public intra_netimoveis_captacao2 pesquisaCondominioPorCodigo(int id) {
		return this.manager.find(intra_netimoveis_captacao2.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<intra_netimoveis_captacao2> listaCaptacao() {
		this.query = this.manager.createQuery("from intra_netimoveis_captacao2 ");
		return this.query.getResultList();
	}

	public void salvarProprietario(intra_netimoveis_proprietario cp, SessaoMB sessaoMB) {
		if (cp.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(cp);
			this.ilg.setCondominio(cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_netimoveis_proprietario");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(cp);
			this.ilg.setCondominio(cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_netimoveis_proprietario");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void excluirProprietario(intra_netimoveis_proprietario cp, SessaoMB sessaoMB) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.manager.remove(cp);
		this.ilg.setCondominio(cp.getId());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_netimoveis_proprietario");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public intra_netimoveis_proprietario pesquisaProprietarioPorCodigo(int id) {
		return this.manager.find(intra_netimoveis_proprietario.class, id);
	}

}