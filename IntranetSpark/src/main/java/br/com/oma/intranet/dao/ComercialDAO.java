package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_cptadministracao2;
import br.com.oma.intranet.entidades.intra_cptatendimento;
import br.com.oma.intranet.entidades.intra_cptcondo2;
import br.com.oma.intranet.entidades.intra_cptcondo_foto2;
import br.com.oma.intranet.entidades.intra_cptconstrutora2;
import br.com.oma.intranet.entidades.intra_cptproposta2;
import br.com.oma.intranet.entidades.intra_cptsindico2;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class ComercialDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7234584384466517888L;

	private Query query;
	private EntityManager manager;
	private intra_log_geral ilg = new intra_log_geral();
	private Date data;

	public ComercialDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
	}

	public void salvarCondominio(intra_cptcondo2 cp, SessaoMB sessaoMB) {
		if (cp.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Condomínio cadastrado " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptcondo2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Condomínio alterado " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptcondo2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void updateCondominio(intra_cptcondo2 cp) {
		this.query = this.manager
				.createNativeQuery("update omacorp.dbo.intra_cptcondo2 set condo = :p1 where id = :p2");
		this.query.setParameter("p1", cp.getId());
		this.query.setParameter("p2", cp.getId());
		this.query.executeUpdate();
	}

	public void excluirCondominio(intra_cptcondo2 cp, SessaoMB sessaoMB) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.manager.remove(cp);
		this.ilg.setCondominio(0);
		this.ilg.setInfoAnterior("Condomínio excluído " + cp.getId());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_cptcondo2");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public intra_cptcondo2 pesquisaCondominioPorCodigo(int id) {
		return this.manager.find(intra_cptcondo2.class, id);
	}

	public void salvarConstrutora(intra_cptconstrutora2 cp, SessaoMB sessaoMB) {
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
			this.ilg.setTabela("intra_cptconstrutora2");
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
			this.ilg.setTabela("intra_cptconstrutora2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void excluirConstrutora(intra_cptconstrutora2 cp, SessaoMB sessaoMB) {
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
		this.ilg.setTabela("intra_cptconstrutora2");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public intra_cptconstrutora2 pesquisaConstrutoraPorCodigo(int id) {
		return this.manager.find(intra_cptconstrutora2.class, id);
	}

	public void salvarAtendimento(intra_cptatendimento cp, SessaoMB sessaoMB) {
		if (cp.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Atendimento cadastrado do Condomínio: " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptatendimento2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Atendimento alterado do Condomínio: " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptatendimento2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void excluirAtendimento(intra_cptatendimento cp, SessaoMB sessaoMB) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.manager.remove(cp);
		this.ilg.setCondominio(0);
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_cptatendimento2");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public intra_cptatendimento pesquisaAtendimentoPorCodigo(int id) {
		return this.manager.find(intra_cptatendimento.class, id);
	}

	public void salvarAdministracao(intra_cptadministracao2 cp, SessaoMB sessaoMB) {
		if (cp.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Condomínio cadastrado " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptadministracao2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Condomínio alterado " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptadministracao2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void excluirAdministracao(intra_cptadministracao2 cp, SessaoMB sessaoMB) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.manager.remove(cp);
		this.ilg.setCondominio(0);
		this.ilg.setInfoAnterior("Condomínio excluído " + cp.getId());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_cptadministracao2");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public intra_cptadministracao2 pesquisaAdministracaoPorCodigo(int id) {
		return this.manager.find(intra_cptadministracao2.class, id);
	}

	public void salvarProposta(intra_cptproposta2 cp, SessaoMB sessaoMB) {
		if (cp.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Condomínio cadastrado " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptproposta2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Condomínio alterado " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptproposta2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void excluirProposta(intra_cptproposta2 cp, SessaoMB sessaoMB) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.manager.remove(cp);
		this.ilg.setCondominio(0);
		this.ilg.setInfoAnterior("Condomínio excluído " + cp.getId());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_cptproposta2");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public intra_cptproposta2 pesquisaPropostaPorCodigo(int id) {
		return this.manager.find(intra_cptproposta2.class, id);
	}

	public void salvarSindico(intra_cptsindico2 cp, SessaoMB sessaoMB) {
		if (cp.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Condomínio cadastrado " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptsindico2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(cp);
			this.ilg.setCondominio(0);
			this.ilg.setInfoAnterior("Condomínio alterado " + cp.getId());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_cptsindico2");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void excluirSindico(intra_cptsindico2 cp, SessaoMB sessaoMB) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.manager.remove(cp);
		this.ilg.setCondominio(0);
		this.ilg.setInfoAnterior("Condomínio excluído " + cp.getId());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_cptsindico2");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public intra_cptsindico2 pesquisaSindicoPorCodigo(int id) {
		return this.manager.find(intra_cptsindico2.class, id);
	}

	public void salvarFoto(intra_cptcondo_foto2 cp) {
		if (cp.getCodigo() == 0) {
			this.manager.persist(cp);
		} else {
			this.manager.merge(cp);
		}
	}

	public void excluirFotoCondo(int cp, SessaoMB sessaoMB) {
		this.query = this.manager.createQuery("delete intra_cptcondo_foto2 where codigoCondominio = :p1");
		this.query.setParameter("p1", cp).executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(0);
		this.ilg.setInfoAnterior("Condomínio excluído " + cp);
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_cptcondo_foto2");
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaFoto(Integer id) {
		byte[] foto = null;
		if (this.manager.isOpen()) {
			this.query = this.manager.createQuery("from intra_cptcondo_foto2 where codigoCondominio = :p1");
			this.query.setParameter("p1", id);
			List<intra_cptcondo_foto2> l = this.query.getResultList();
			for (intra_cptcondo_foto2 obj : l) {
				if (obj != null) {
					foto = obj.getFoto();
				}
			}
		}
		return foto;
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptcondo_foto2> listarImagem() {
		List<intra_cptcondo_foto2> listaFoto = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select codigo, descricao, codigoCondominio, foto from omacorp.dbo.intra_cptcondo_foto2");
		List<Object[]> l = this.query.getResultList();
		for (Object[] obj : l) {
			intra_cptcondo_foto2 fotos = new intra_cptcondo_foto2();
			if (obj[0] != null) {
				fotos.setCodigo(Integer.valueOf(obj[0].toString()));
			}
			if (obj[1] != null) {
				fotos.setDescricao(obj[1].toString());
			}
			if (obj[2] != null) {
				fotos.setCodigoCondominio(Integer.parseInt(obj[2].toString()));
			}
			if (obj[3] != null) {
				fotos.setFoto(obj[3].toString().getBytes());
			}
			listaFoto.add(fotos);
		}
		return listaFoto;
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptcondo_foto2> pesquisaFotoCondoPorCodigo(intra_cptcondo2 foto) {
		List<intra_cptcondo_foto2> listaFoto = new ArrayList<>();
		intra_cptcondo_foto2 fotos = null;
		this.query = this.manager.createNativeQuery(
				"select codigo, codigoCondominio, foto from intra_cptcondo_foto2 where codigoCondominio = :p1 order by codigo DESC");
		this.query.setParameter("p1", foto.getId());
		List<Object[]> l = this.query.getResultList();
		for (Object[] obj : l) {
			fotos = new intra_cptcondo_foto2();
			if (obj[0] != null) {
				fotos.setCodigo(Integer.parseInt(obj[0].toString()));
			}
			if (obj[1] != null) {
				fotos.setCodigoCondominio(Integer.parseInt(obj[1].toString()));
			}
			if (obj[2] != null) {
				fotos.setFoto(obj[2].toString().getBytes());
			}
			listaFoto.add(fotos);
		}
		return listaFoto;
	}

	@SuppressWarnings("unchecked")
	public List<String> listaCidades(int codigo) {
		this.query = this.manager.createNativeQuery("select nome from intra_cidade where codigo_estado = :p1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptcondo2> listaCondominios2() {
		List<intra_cptcondo2> listaCondo = new ArrayList<>();
		this.query = this.manager.createNativeQuery("select id, nome from intra_cptcondo2 order by dataCadastro DESC");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cptcondo2 c = new intra_cptcondo2();
				if (obj[0] != null) {
					c.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					c.setNome(obj[1].toString());
				}
				listaCondo.add(c);
			}
		}
		return listaCondo;
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptcondo2> listaCondominios() {
		this.query = this.manager.createQuery("from intra_cptcondo2 order by dataCadastro DESC");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptcondo2> listaCondominiosTop1000() {
		List<intra_cptcondo2> listaCondo = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select top 200 id, nome, dataCadastro, endereco, bairro, cidade, estado, cep, regiao, tipo "
						+ "from omacorp.dbo.intra_cptcondo2 order by dataCadastro desc");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cptcondo2 c = new intra_cptcondo2();
				if (obj[0] != null) {
					c.setId(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					c.setNome(obj[1].toString());
				}
				if (obj[2] != null) {
					c.setDataCadastro((Date) obj[2]);
				}
				if (obj[3] != null) {
					c.setEndereco(obj[3].toString());
				}
				if (obj[4] != null) {
					c.setBairro(obj[4].toString());
				}
				if (obj[5] != null) {
					c.setCidade(obj[5].toString());
				}
				if (obj[6] != null) {
					c.setEstado(Integer.valueOf(obj[6].toString()));
				}
				if (obj[7] != null) {
					c.setCep(obj[7].toString());
				}
				if (obj[8] != null) {
					c.setRegiao(obj[8].toString());
				}
				if (obj[9] != null) {
					c.setTipo(obj[9].toString());
				}
				listaCondo.add(c);
			}
		}
		return listaCondo;
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptconstrutora2> listaConstrutora() {
		this.query = this.manager.createQuery("from intra_cptconstrutora2 order by id DESC");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptconstrutora2> listaConstrutora(int idInicial, int idFinal, String nome) {
		if (idInicial > 0 && nome != null) {
			this.query = this.manager.createQuery(
					"from intra_cptconstrutora2 where id between :p1 and :p2 and nome = :p3 order by id DESC");
			this.query.setParameter("p1", idInicial);
			this.query.setParameter("p2", idFinal);
			this.query.setParameter("p3", nome);
		} else if (idInicial > 0 && nome == null) {
			this.query = this.manager
					.createQuery("from intra_cptconstrutora2 where id between :p1 and :p2 order by id DESC");
			this.query.setParameter("p1", idInicial);
			this.query.setParameter("p2", idFinal);
		} else {
			this.query = this.manager.createQuery("from intra_cptconstrutora2 where nome = :p1 order by id DESC");
			this.query.setParameter("p1", nome);
		}
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public intra_cptconstrutora2 nomeConstrutora(int codigo) {
		List<intra_cptconstrutora2> listaC = new ArrayList<intra_cptconstrutora2>();
		this.query = this.manager
				.createNativeQuery("select nome, responsavel from omacorp.dbo.intra_cptconstrutora2 where id = :p1");
		this.query.setParameter("p1", codigo);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cptconstrutora2 ic = new intra_cptconstrutora2();
				if (obj[0] != null) {
					ic.setNome(obj[0].toString());
				}
				if (obj[1] != null) {
					ic.setResponsavel(obj[1].toString());
				}
				listaC.add(ic);
			}
		}
		return listaC.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptatendimento> listaAtendimento() {
		this.query = this.manager.createQuery("from intra_cptatendimento order by id DESC");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptatendimento> listaAtendimento(int idInicial, int idFinal, SessaoMB sessaoMB, Date dataInicial,
			Date dataFinal, int codInicial, int codFinal) {
		if (idInicial != 0 && sessaoMB != null && dataInicial != null && codInicial != 0) {
			this.query = this.manager.createQuery(
					"from intra_cptatendimento where id between :p1 and :p2 and usuario = :p3 and data between :p4 and :p5 and codigoCondominio between :p6 and :p7 order by id DESC");
			this.query.setParameter("p1", idInicial);
			this.query.setParameter("p2", idFinal);
			this.query.setParameter("p3", sessaoMB);
			this.query.setParameter("p4", dataInicial);
			this.query.setParameter("p5", dataFinal);
			this.query.setParameter("p6", codInicial);
			this.query.setParameter("p7", codFinal);
		} else if (idInicial != 0 && sessaoMB != null && dataInicial != null && codInicial == 0) {
			this.query = this.manager.createQuery(
					"from intra_cptatendimento where id between :p1 and :p2 and usuario = :p3 and data between :p4 and :p5 order by id DESC");
			this.query.setParameter("p1", idInicial);
			this.query.setParameter("p2", idFinal);
			this.query.setParameter("p3", sessaoMB);
			this.query.setParameter("p4", dataInicial);
			this.query.setParameter("p5", dataFinal);
		} else if (idInicial != 0 && sessaoMB != null && dataInicial == null && codInicial == 0) {
			this.query = this.manager.createQuery(
					"from intra_cptatendimento where id between :p1 and :p2 and usuario = :p3 order by id DESC");
			this.query.setParameter("p1", idInicial);
			this.query.setParameter("p2", idFinal);
			this.query.setParameter("p3", sessaoMB);
		} else if (idInicial != 0 && sessaoMB == null && dataInicial == null && codInicial == 0) {
			this.query = this.manager
					.createQuery("from intra_cptatendimento where id between :p1 and :p2 order by id DESC");
			this.query.setParameter("p1", idInicial);
			this.query.setParameter("p2", idFinal);
		} else if (idInicial == 0 && sessaoMB != null && dataInicial != null && codInicial != 0) {
			this.query = this.manager.createQuery(
					"from intra_cptatendimento where usuario = :p1 and data between :p2 and :p3 and codigoCondominio between :p4 and :p5 order by id DESC");
			this.query.setParameter("p1", sessaoMB);
			this.query.setParameter("p2", dataInicial);
			this.query.setParameter("p3", dataFinal);
			this.query.setParameter("p4", codInicial);
			this.query.setParameter("p5", codFinal);
		} else if (idInicial == 0 && sessaoMB != null && dataInicial != null && codInicial == 0) {
			this.query = this.manager.createQuery(
					"from intra_cpatendimento where usuario = :p1 and data between :p2 and :p3 order by id DESC");
			this.query.setParameter("p1", sessaoMB);
			this.query.setParameter("p2", dataInicial);
			this.query.setParameter("p3", dataFinal);
		} else if (idInicial == 0 && sessaoMB != null && dataInicial == null && codInicial == 0) {
			this.query = this.manager.createQuery("from intra_cptatendimento where usuario = :p1 order by id DESC");
			this.query.setParameter("p1", sessaoMB);
		} else if (idInicial == 0 && sessaoMB == null && dataInicial != null && codInicial != 0) {
			this.query = this.manager.createQuery(
					"from intra_cptatendimento where data between :p1 and :p2 and codigoCondominio between :p3 and :p4 order by id DESC");
			this.query.setParameter("p1", dataInicial);
			this.query.setParameter("p2", dataFinal);
			this.query.setParameter("p3", codInicial);
			this.query.setParameter("p4", codFinal);
		} else if (idInicial == 0 && sessaoMB == null && dataInicial != null && codInicial == 0) {
			this.query = this.manager
					.createQuery("from intra_cptatendimento where data between :p1 and :p2 order by id DESC");
			this.query.setParameter("p1", dataInicial);
			this.query.setParameter("p2", dataFinal);
		} else {
			this.query = this.manager.createQuery(
					"from intra_cptatendimento where codigoCondominio between :p1 and :p2 order by id DESC");
			this.query.setParameter("p1", codInicial);
			this.query.setParameter("p2", codFinal);
		}
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptcondo2> listaCondominioPorRegiao(String regiao) {
		List<intra_cptcondo2> listaCondominio = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select nome, dataCadastro, endereco, bairro, cidade, estado, cep, regiao, "
						+ "tipo from omacorp.dbo.intra_cptcondo2 where estado = 26 and regiao = :p1 order by dataCadastro DESC");
		this.query.setParameter("p1", regiao);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cptcondo2 c = new intra_cptcondo2();
				if (obj[0] != null) {
					c.setNome(obj[0].toString());
				}
				if (obj[1] != null) {
					c.setDataCadastro((Date) obj[1]);
				}
				if (obj[2] != null) {
					c.setEndereco(obj[2].toString());
				}
				if (obj[3] != null) {
					c.setBairro(obj[3].toString());
				}
				if (obj[4] != null) {
					c.setCidade(obj[4].toString());
				}
				if (obj[5] != null) {
					c.setEstado(Integer.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					c.setCep(obj[6].toString());
				}
				if (obj[7] != null) {
					c.setRegiao(obj[7].toString());
				}
				if (obj[8] != null) {
					c.setTipo(obj[8].toString());
				}
				listaCondominio.add(c);
			}
		}
		return listaCondominio;
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptcondo2> listaCondominioPorCep(String cep1) {
		List<intra_cptcondo2> listaCondominio = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select nome, dataCadastro, endereco, bairro, cidade, estado, cep, regiao, "
						+ "tipo from omacorp.dbo.intra_cptcondo2 where cep = :p1 order by dataCadastro DESC");
		this.query.setParameter("p1", cep1);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cptcondo2 c = new intra_cptcondo2();
				if (obj[0] != null) {
					c.setNome(obj[0].toString());
				}
				if (obj[1] != null) {
					c.setDataCadastro((Date) obj[1]);
				}
				if (obj[2] != null) {
					c.setEndereco(obj[2].toString());
				}
				if (obj[3] != null) {
					c.setBairro(obj[3].toString());
				}
				if (obj[4] != null) {
					c.setCidade(obj[4].toString());
				}
				if (obj[5] != null) {
					c.setEstado(Integer.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					c.setCep(obj[6].toString());
				}
				if (obj[7] != null) {
					c.setRegiao(obj[7].toString());
				}
				if (obj[8] != null) {
					c.setTipo(obj[8].toString());
				}
				listaCondominio.add(c);
			}
		}
		return listaCondominio;
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptcondo2> listaCondominioPorTipo(String tipo) {
		List<intra_cptcondo2> listaCondominio = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select nome, dataCadastro, endereco, bairro, cidade, estado, cep, regiao, "
						+ "tipo from omacorp.dbo.intra_cptcondo2 where tipo = :p1 order by dataCadastro DESC");
		this.query.setParameter("p1", tipo);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cptcondo2 c = new intra_cptcondo2();
				if (obj[0] != null) {
					c.setNome(obj[0].toString());
				}
				if (obj[1] != null) {
					c.setDataCadastro((Date) obj[1]);
				}
				if (obj[2] != null) {
					c.setEndereco(obj[2].toString());
				}
				if (obj[3] != null) {
					c.setBairro(obj[3].toString());
				}
				if (obj[4] != null) {
					c.setCidade(obj[4].toString());
				}
				if (obj[5] != null) {
					c.setEstado(Integer.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					c.setCep(obj[6].toString());
				}
				if (obj[7] != null) {
					c.setRegiao(obj[7].toString());
				}
				if (obj[8] != null) {
					c.setTipo(obj[8].toString());
				}
				listaCondominio.add(c);
			}
		}
		return listaCondominio;
	}

	@SuppressWarnings("unchecked")
	public List<intra_cptatendimento> listaAtendimentosPorCodigo(intra_cptcondo2 cp) {
		List<intra_cptatendimento> lista = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select a.codigoCondominio, a.data, a.historico, a.solucionado, a.usuario, a.nomeCondominio from omacorp.dbo.intra_cptcondo2 c "
						+ "inner join omacorp.dbo.intra_cptatendimento a on a.codigoCondominio = c.id where c.id = :p1");
		this.query.setParameter("p1", cp.getId());
		List<Object[]> lista2 = this.query.getResultList();
		if (!lista2.isEmpty()) {
			for (Object[] obj : lista2) {
				intra_cptatendimento a = new intra_cptatendimento();
				if (obj[0] != null) {
					a.setCodigoCondominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					a.setData((Date) obj[1]);
				}
				if (obj[2] != null) {
					a.setHistorico(obj[2].toString());
				}
				if (obj[3] != null) {
					a.setSolucionado(Boolean.getBoolean(obj[3].toString()));
				}
				if (obj[4] != null) {
					a.setUsuario(obj[4].toString());
				}
				if (obj[5] != null) {
					a.setNomeCondominio(obj[5].toString());
				}
				lista.add(a);
			}
		}
		return lista;
	}

}