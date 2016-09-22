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

import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_proc_jur_cob;
import br.com.oma.intranet.entidades.intra_procuracao_juridico;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.sigadm.entidades.intra_cndpruni;

@Stateless
public class ProcuracoesJuridicoDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6109215063380842225L;
	private Query query;
	private EntityManager manager;
	private intra_log_geral ilg = new intra_log_geral();
	private Date data;

	public ProcuracoesJuridicoDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
	}

	public void salvarInformacao(intra_proc_jur_cob id) {
		if (id.getId() == 0) {
			this.manager.persist(id);
			intra_log_geral log = new intra_log_geral(id.getId(), retornaIdUsuario(), "intra_proc_jur_cob", true, false,
					false, id.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		} else {
			this.manager.merge(id);
			intra_log_geral log = new intra_log_geral(id.getId(), retornaIdUsuario(), "intra_proc_jur_cob", false, true,
					false, id.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager
				.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1 order by codigo");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios() {
		this.query = this.manager.createQuery("from intra_condominios ");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_proc_jur_cob> listarTabela() {
		this.query = this.manager.createQuery("from intra_proc_jur_cob order by id DESC");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_proc_jur_cob> listarTabela2(List<intra_condominios> condominio, Date dataInicial, Date dataFinal) {
		for(intra_condominios c : condominio){
		this.query = this.manager
				.createQuery("from intra_proc_jur_cob where pedProcuracao between :p1 and :p2 and codigoCondominio = :p3 order by id DESC");
		this.query.setParameter("p1", dataInicial);
		this.query.setParameter("p2", dataFinal);
		this.query.setParameter("p3", c.getCodigo());
		}
		return this.query.getResultList();
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

	public intra_proc_jur_cob pesquisaIdPorCodigo(int id) {
		return this.manager.find(intra_proc_jur_cob.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<intra_procuracao_juridico> pesquisarCotasAberto(int condominio) {
		List<intra_procuracao_juridico> listaProcuracao = new ArrayList<intra_procuracao_juridico>();

		this.query = this.manager.createNativeQuery(
				" select r.condominio, c.nome as nomeCondominio, r.bloco, r.unidade, g.codigo, g.nome as nomeGerente, r.emissao, "
						+ "r.recibo, r.valor_recibo, r.data_recbto, r.vencto, r.juridico from sigadm.dbo.cndrecib r "
						+ "inner join sigadm.dbo.cndcondo c on c.codigo = r.condominio "
						+ "inner join sigadm.dbo.cndunida u on u.condominio = r.condominio "
						+ "inner join sigadm.dbo.cndgeren g on g.codigo = c.gerente "
						+ "where r.condominio = :p1 and r.data_recbto is null and juridico = '' and vencto < GETDATE()-30 "
						+ "group by r.condominio, c.nome, r.bloco, r.unidade, g.codigo, g.nome, r.emissao, r.recibo, r.valor_recibo, r.data_recbto, "
						+ "r.vencto, r.juridico order by r.condominio, r.bloco, r.unidade ");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_procuracao_juridico ipj = new intra_procuracao_juridico();
				if (obj[0] != null) {
					ipj.setCodigoCondominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ipj.setNomeCondominio(obj[1].toString());
				}
				if (obj[2] != null) {
					ipj.setBloco(obj[2].toString());
				}
				if (obj[3] != null) {
					ipj.setUnidade(obj[3].toString());
				}
				if (obj[4] != null) {
					ipj.setCodigoGerente(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					ipj.setNomeGerente(obj[5].toString());
				}
				if (obj[6] != null) {
					ipj.setEmissao(Integer.valueOf(obj[6].toString()));
				}
				if (obj[7] != null) {
					ipj.setRecibo(Integer.valueOf(obj[7].toString()));
				}
				if (obj[8] != null) {
					ipj.setValor_recibo(Double.valueOf(obj[8].toString()));
				}
				if (obj[9] != null) {
					ipj.setData_recbto((Date) (obj[9]));
				}
				if (obj[10] != null) {
					ipj.setVencto((Date) (obj[10]));
				}
				if (obj[11] != null) {
					ipj.setJuridico(obj[11].toString());
				}
				listaProcuracao.add(ipj);

			}
		}
		return listaProcuracao;
	}

	@SuppressWarnings("unchecked")
	public List<intra_procuracao_juridico> pesquisarCotasAberto2(int condominio, String bloco) {
		List<intra_procuracao_juridico> listaProcuracao = new ArrayList<intra_procuracao_juridico>();
		this.query = this.manager.createNativeQuery(
				" select r.condominio, c.nome as nomeCondominio, r.bloco, r.unidade, g.codigo, g.nome as nomeGerente, r.emissao, "
						+ "r.recibo, r.valor_recibo, r.data_recbto, r.vencto, r.juridico from sigadm.dbo.cndrecib r "
						+ "inner join sigadm.dbo.cndcondo c on c.codigo = r.condominio "
						+ "inner join sigadm.dbo.cndunida u on u.condominio = r.condominio "
						+ "inner join sigadm.dbo.cndgeren g on g.codigo = c.gerente "
						+ "where r.condominio = :p1 and r.bloco = :p2 and r.data_recbto is null and juridico = '' and vencto < GETDATE()-60 "
						+ "group by r.condominio, c.nome, r.bloco, r.unidade, g.codigo, g.nome, r.emissao, r.recibo, r.valor_recibo, r.data_recbto, "
						+ "r.vencto, r.juridico order by r.condominio, r.bloco, r.unidade ");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", bloco);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_procuracao_juridico ipj = new intra_procuracao_juridico();
				if (obj[0] != null) {
					ipj.setCodigoCondominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ipj.setNomeCondominio(obj[1].toString());
				}
				if (obj[2] != null) {
					ipj.setBloco(obj[2].toString());
				}
				if (obj[3] != null) {
					ipj.setUnidade(obj[3].toString());
				}
				if (obj[4] != null) {
					ipj.setCodigoGerente(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					ipj.setNomeGerente(obj[5].toString());
				}
				if (obj[6] != null) {
					ipj.setEmissao(Integer.valueOf(obj[6].toString()));
				}
				if (obj[7] != null) {
					ipj.setRecibo(Integer.valueOf(obj[7].toString()));
				}
				if (obj[8] != null) {
					ipj.setValor_recibo(Double.valueOf(obj[8].toString()));
				}
				if (obj[9] != null) {
					ipj.setData_recbto((Date) (obj[9]));
				}
				if (obj[10] != null) {
					ipj.setVencto((Date) (obj[10]));
				}
				if (obj[11] != null) {
					ipj.setJuridico(obj[11].toString());
				}
				listaProcuracao.add(ipj);
			}
		}
		return listaProcuracao;
	}

	@SuppressWarnings("unchecked")
	public List<intra_procuracao_juridico> pesquisarCotasAberto3(int condominio, String bloco, String unidade) {
		List<intra_procuracao_juridico> listaProcuracao = new ArrayList<intra_procuracao_juridico>();

		this.query = this.manager.createNativeQuery(
				" select r.condominio, c.nome as nomeCondominio, r.bloco, r.unidade, g.codigo, g.nome as nomeGerente, r.emissao, "
						+ "r.recibo, r.valor_recibo, r.data_recbto, r.vencto, r.juridico from sigadm.dbo.cndrecib r "
						+ "inner join sigadm.dbo.cndcondo c on c.codigo = r.condominio "
						+ "inner join sigadm.dbo.cndunida u on u.condominio = r.condominio "
						+ "inner join sigadm.dbo.cndgeren g on g.codigo = c.gerente "
						+ "where r.condominio = :p1 and r.bloco = :p2 and r.unidade = :p3 and r.data_recbto is null and juridico = '' and vencto < GETDATE()-60 "
						+ "group by r.condominio, c.nome, r.bloco, r.unidade, g.codigo, g.nome, r.emissao, r.recibo, r.valor_recibo, r.data_recbto, "
						+ "r.vencto, r.juridico order by r.condominio, r.bloco, r.unidade ");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", bloco);
		this.query.setParameter("p3", unidade);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_procuracao_juridico ipj = new intra_procuracao_juridico();
				if (obj[0] != null) {
					ipj.setCodigoCondominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ipj.setNomeCondominio(obj[1].toString());
				}
				if (obj[2] != null) {
					ipj.setBloco(obj[2].toString());
				}
				if (obj[3] != null) {
					ipj.setUnidade(obj[3].toString());
				}
				if (obj[4] != null) {
					ipj.setCodigoGerente(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					ipj.setNomeGerente(obj[5].toString());
				}
				if (obj[6] != null) {
					ipj.setEmissao(Integer.valueOf(obj[6].toString()));
				}
				if (obj[7] != null) {
					ipj.setRecibo(Integer.valueOf(obj[7].toString()));
				}
				if (obj[8] != null) {
					ipj.setValor_recibo(Double.valueOf(obj[8].toString()));
				}
				if (obj[9] != null) {
					ipj.setData_recbto((Date) (obj[9]));
				}
				if (obj[10] != null) {
					ipj.setVencto((Date) (obj[10]));
				}
				if (obj[11] != null) {
					ipj.setJuridico(obj[11].toString());
				}
				listaProcuracao.add(ipj);
			}
		}
		return listaProcuracao;
	}

	@SuppressWarnings("unchecked")
	public List<intra_procuracao_juridico> unidadesDevedoras(List<intra_condominios> condominio, Date dataInicial) throws SQLException {
		List<intra_procuracao_juridico> listaProcuracao = new ArrayList<intra_procuracao_juridico>();
		for (intra_condominios c : condominio) {
			this.query = this.manager.createNativeQuery(
					"select r.condominio, c.nome as nomeCondominio, r.bloco, r.unidade, g.codigo, g.nome as nomeGerente, r.juridico "
							+ "from sigadm.dbo.cndrecib r "
							+ "inner join sigadm.dbo.cndcondo c on c.codigo = r.condominio "
							+ "inner join sigadm.dbo.cndunida u on u.condominio = r.condominio "
							+ "inner join sigadm.dbo.cndgeren g on g.codigo = c.gerente "
							+ "where r.condominio = :p1 and r.data_recbto is null and juridico = '' and vencto < :p2 "
							+ "group by r.condominio, c.nome, r.bloco, r.unidade, g.codigo, g.nome, r.juridico "
							+ "order by r.condominio, r.bloco, r.unidade ");
			this.query.setParameter("p1", c.getCodigo());
			this.query.setParameter("p2", dataInicial);
			List<Object[]> lista = this.query.getResultList();
			if (!lista.isEmpty()) {
				for (Object[] obj : lista) {
					intra_procuracao_juridico ipj = new intra_procuracao_juridico();
					if (obj[0] != null) {
						ipj.setCodigoCondominio(Integer.valueOf(obj[0].toString()));
					}
					if (obj[1] != null) {
						ipj.setNomeCondominio(obj[1].toString());
					}
					if (obj[2] != null) {
						ipj.setBloco(obj[2].toString());
					}
					if (obj[3] != null) {
						ipj.setUnidade(obj[3].toString());
					}
					if (obj[4] != null) {
						ipj.setCodigoGerente(Integer.valueOf(obj[4].toString()));
					}
					if (obj[5] != null) {
						ipj.setNomeGerente(obj[5].toString());
					}
					if (obj[6] != null) {
						ipj.setJuridico(obj[6].toString());
					}
					listaProcuracao.add(ipj);
				}
			}
		}
		return listaProcuracao;
	}

	@SuppressWarnings("unchecked")
	public intra_cndpruni listarProntuarioJ(int condominio, String bloco, String unidade) {
		intra_cndpruni cndpruni = new intra_cndpruni();
		this.query = this.manager.createNativeQuery("select bloco, unidade, tipo, data_inclusao, sequencia, titulo, "
				+ "descricao, tipo_pront, nao_public from sigadm.dbo.cndpruni where condominio = :p1 and bloco = :p2 and unidade = :p3 and tipo_pront = 'J' and titulo = 'cobrança'");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", bloco);
		this.query.setParameter("p3", unidade);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				cndpruni.setCondominio(condominio);
				if (obj[0] != null) {
					cndpruni.setBloco(obj[0].toString());
				}
				if (obj[1] != null) {
					cndpruni.setUnidade(obj[1].toString());
				}
				if (obj[2] != null) {
					cndpruni.setTipo(Integer.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					cndpruni.setData_inclusao((Date) obj[3]);
				}
				if (obj[4] != null) {
					cndpruni.setSequencia(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					cndpruni.setTitulo(obj[5].toString());
				}
				if (obj[6] != null) {
					cndpruni.setDescricao(obj[6].toString());
				}
				if (obj[7] != null) {
					cndpruni.setTipo_pront(obj[7].toString());
				}
				if (obj[8] != null) {
					cndpruni.setNao_public(Integer.valueOf(obj[8].toString()));
				}
			}
		}
		return cndpruni;
	}

	public void adicionarInfProntuario(intra_cndpruni cndpruni, SessaoMB sessaoMB) {
		this.query = this.manager
				.createNativeQuery("insert into sigadm.dbo.cndpruni (condominio, bloco, unidade, sequencia, titulo, "
						+ "tipo, data_inclusao, descricao, tipo_pront, nao_public) "
						+ "values(:p1, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9, :p10)");
		this.query.setParameter("p1", cndpruni.getCondominio());
		this.query.setParameter("p2", cndpruni.getBloco());
		this.query.setParameter("p3", cndpruni.getUnidade());
		this.query.setParameter("p4", cndpruni.getSequencia());
		this.query.setParameter("p5", cndpruni.getTitulo());
		this.query.setParameter("p6", cndpruni.getTipo());
		this.query.setParameter("p7", cndpruni.getData_inclusao());
		this.query.setParameter("p8", cndpruni.getDescricao());
		this.query.setParameter("p9", cndpruni.getTipo_pront());
		this.query.setParameter("p10", cndpruni.getNao_public());
		this.query.executeUpdate();

		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(cndpruni.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndpruni");
		this.ilg.setInfoAnterior("Adicionado no Prontuário: " + cndpruni.getDfrecnum());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void alterarInfProntuario(intra_cndpruni cndpruni, SessaoMB sessaoMB) {
		this.query = this.manager.createNativeQuery(
				"update sigadm.dbo.cndpruni set titulo = :p1, tipo = :p2, data_inclusao = :p3, descricao = :p4 "
						+ "where condominio = :p5 and bloco = :p6 and unidade = :p7 and sequencia = :p8 and tipo_pront = :p9 and nao_public = :p10 ");

		this.query.setParameter("p1", cndpruni.getTitulo());
		this.query.setParameter("p2", cndpruni.getTipo());
		this.query.setParameter("p3", cndpruni.getData_inclusao());
		this.query.setParameter("p4", cndpruni.getDescricao());
		this.query.setParameter("p5", cndpruni.getCondominio());
		this.query.setParameter("p6", cndpruni.getBloco());
		this.query.setParameter("p7", cndpruni.getUnidade());
		this.query.setParameter("p8", cndpruni.getSequencia());
		this.query.setParameter("p9", cndpruni.getTipo_pront());
		this.query.setParameter("p10", cndpruni.getNao_public());
		this.query.executeUpdate();

		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(cndpruni.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndpruni");
		this.ilg.setInfoAnterior("Prontuário alterado: " + cndpruni.getDfrecnum());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}
}
