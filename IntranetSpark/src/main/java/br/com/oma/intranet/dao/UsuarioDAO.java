package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.entidades.intra_grupo_depto;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_grupo_permissao;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_usuario;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class UsuarioDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4263013583543475085L;

	private EntityManager manager;
	private Query query;
	private Date data;
	private intra_log_geral ilg;

	public UsuarioDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void adiciona(intra_usuario user, SessaoMB sessao) {
		this.manager.persist(user);
		data = new Date();
		this.ilg = new intra_log_geral();
		this.ilg.setIp(sessao.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
		this.ilg.setTabela("intra_usuario");
		this.ilg.setInfoAnterior("Nome: " + user.getNome() + "/Email: "
				+ user.getEmail() + "/Perfil: " + user.getPerfil());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public List<intra_usuario> buscaTodos() {
		TypedQuery<intra_usuario> query = this.manager.createQuery(
				"select x from intra_usuario x", intra_usuario.class);
		return query.getResultList();
	}

	public void excluirUsuario(intra_usuario user, boolean valida, SessaoMB sessao) {

		if (valida) {
			data = new Date();
			this.ilg = new intra_log_geral();
			this.ilg.setIp(sessao.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(true);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
			this.ilg.setTabela("intra_usuario");
			this.ilg.setInfoAnterior("Nome: " + user.getNome() + "/Email: "
					+ user.getEmail() + "/Perfil: " + user.getPerfil());
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
			
			if (!user.getGrupoDepto().isEmpty()) {
				for (intra_grupo_depto depto : user.getGrupoDepto()) {
					this.query = this.manager
							.createNativeQuery("delete intra_usuario_intra_grupo_depto where  intra_usuario_email = :p1 and grupoDepto_nome = :p2");
					this.query.setParameter("p1", user.getEmail());
					this.query.setParameter("p2", depto.getNome());
					this.query.executeUpdate();
				}
			}
			if (!user.getGrupoGer().isEmpty()) {
				for (intra_grupo_gerente ger : user.getGrupoGer()) {
					this.query = this.manager
							.createNativeQuery("delete intra_usuario_intra_grupo_gerente where  intra_usuario_email = :p1 and grupoGer_codigo = :p2");
					this.query.setParameter("p1", user.getEmail());
					this.query.setParameter("p2", ger.getCodigo());
					this.query.executeUpdate();
				}
			}
			if (!user.getGrupoPer().isEmpty()) {
				for (intra_grupo_permissao per : user.getGrupoPer()) {
					this.query = this.manager
							.createNativeQuery("delete intra_usuario_intra_grupo_permissao where  intra_usuario_email = :p1 and grupoPer_nome_grupo = :p2");
					this.query.setParameter("p1", user.getEmail());
					this.query.setParameter("p2", per.getNomeGrupo());
					this.query.executeUpdate();
				}
			}
			this.query = this.manager
					.createNativeQuery("delete intra_usuario	 where  email = :p1");
			this.query.setParameter("p1", user.getEmail());
			this.query.executeUpdate();
		} else {

			if (!user.getGrupoDepto().isEmpty()) {
				for (intra_grupo_depto depto : user.getGrupoDepto()) {
					this.query = this.manager
							.createQuery("delete intra_usuario_intra_grupo_depto where  intra_usuario_email = :p1 and grupoDepto_nome = :p2");
					this.query.setParameter("p1", user.getEmail());
					this.query.setParameter("p2", depto.getNome());
					this.query.executeUpdate();
				}
			}
			if (!user.getGrupoGer().isEmpty()) {
				for (intra_grupo_gerente ger : user.getGrupoGer()) {
					this.query = this.manager
							.createQuery("delete intra_usuario_intra_grupo_gerente where  intra_usuario_email = :p1 and grupoGer_codigo = :p2");
					this.query.setParameter("p1", user.getEmail());
					this.query.setParameter("p2", ger.getCodigo());
					this.query.executeUpdate();
				}
			}
			if (!user.getGrupoPer().isEmpty()) {
				for (intra_grupo_permissao per : user.getGrupoPer()) {
					this.query = this.manager
							.createQuery("delete intra_usuario_intra_grupo_permissao where  intra_usuario_email = :p1 and grupoPer_nome_grupo = :p2");
					this.query.setParameter("p1", user.getEmail());
					this.query.setParameter("p2", per.getNomeGrupo());
					this.query.executeUpdate();
				}
			}

			this.manager.merge(user);
		}
	}

	public void alterarUsuario(intra_usuario user, SessaoMB sessao) {
		this.manager.merge(user);
		data = new Date();
		this.ilg = new intra_log_geral();
		this.ilg.setIp(sessao.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
		this.ilg.setTabela("intra_usuario");
		this.ilg.setInfoAnterior("Nome: " + user.getNome() + "/Email: "
				+ user.getEmail() + "/Perfil: " + user.getPerfil());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void alterarSenha(intra_usuario user, SessaoMB sessao) {
		this.manager.merge(user);
		data = new Date();
		this.ilg = new intra_log_geral();
		this.ilg.setIp(sessao.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessao.getUsuario().getEmail());
		this.ilg.setTabela("intra_usuario");
		this.ilg.setInfoAnterior("Alterdo Senha: "+"/Nome: " + user.getNome() + "/Email: "
				+ user.getEmail() + "/Perfil: " + user.getPerfil());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public boolean verificaUsuario(String email) {
		this.query = this.manager.createQuery("from intra_usuario where email = :p1");
		this.query.setParameter("p1", email);
		
		return this.query.getResultList().isEmpty();
	}

	public boolean replicarContas(int updateGerenCodigo) {
		boolean valida = false;
		this.query = this.manager.createNativeQuery("exec sp_update_sistemas @condominio = :p1");
		this.query.setParameter("p1", updateGerenCodigo);
		this.query.executeUpdate();
		valida = true;
		return valida;
	}
}
