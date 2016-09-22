package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.entidades.intra_cndlteio2;
import br.com.oma.intranet.entidades.intra_cndrteio2;
import br.com.oma.intranet.entidades.intra_cnduteio2;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_emissao;
import br.com.oma.intranet.entidades.intra_emissao2;
import br.com.oma.intranet.entidades.intra_emissao_completo;
import br.com.oma.intranet.entidades.intra_emissao_tipo;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_procuracao_juridico;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.sigadm.entidades.intra_cndlteio;
import br.com.oma.sigadm.entidades.intra_cndprgrt;
import br.com.oma.sigadm.entidades.cndleitu;
import br.com.oma.sigadm.entidades.cndrteio;
import br.com.oma.sigadm.entidades.intra_cndconsu;
import br.com.oma.sigadm.entidades.intra_cndunida;
import br.com.oma.sigadm.entidades.intra_cnduteio;
import br.com.oma.sigadm.entidades.intra_cndtarif;

public class EmissaoDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7244109461703852353L;

	private Query query;
	private EntityManager manager;
	private intra_log_geral ilg = new intra_log_geral();
	private Date data;
	private PreparedStatement pst;
	private Connection con;
	private ResultSet rs;

	public EmissaoDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
	}

	/********************************************************
	 * SALVAR NO OMACORP
	 **********************************************************************************/
	public void salvarEmissao(intra_emissao2 ie, SessaoMB sessaoMB) {
		if (ie.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(ie);
			this.ilg.setCondominio(ie.getCodigo());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_emissao2");
			this.ilg.setInfoAnterior("Código: " + ie.getCodigo() + ", Conta: " + ie.getConta() + ", Histórico: "
					+ ie.getHistorico() + ", Valor: " + ie.getValor() + ", Tipo: " + ie.getTipo() + ", Nº Rateio: "
					+ ie.getNroRateio());
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(ie);
			this.ilg.setCondominio(ie.getCodigo());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_emissao2");
			this.ilg.setInfoAnterior("Código: " + ie.getCodigo() + ", Rateio ID: " + ie.getRateio() + ", Conta: "
					+ ie.getConta() + ", Histórico: " + ie.getHistorico() + ", Valor: " + ie.getValor() + ", Tipo: "
					+ ie.getTipo() + ", Nº Rateio: " + ie.getNroRateio());
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void salvarEmissao2(intra_emissao_completo iec, SessaoMB sessaoMB) {
		if (iec.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(iec);
			this.ilg.setCondominio(iec.getCodigo_condominio());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_emissao_completo");
			this.ilg.setInfoAnterior("Código: " + iec.getCodigo_condominio() + ", Nome Condomínio: "
					+ iec.getNome_condominio() + ", Gerente: " + iec.getCodigo_gerente() + ", Vencimento: "
					+ iec.getVencimento() + ", Número Emissão: " + iec.getNroEmissao() + ", Correio: " + iec.isCorreio()
					+ ", Observação: " + iec.getObservacao() + ", Tipo: " + iec.getTipo());
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(iec);
			this.ilg.setCondominio(iec.getCodigo_condominio());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_emissao_completo");
			this.ilg.setInfoAnterior("Código: " + iec.getCodigo_condominio() + ", Nome Condomínio: "
					+ iec.getNome_condominio() + ", Gerente: " + iec.getCodigo_gerente() + ", Vencimento: "
					+ iec.getVencimento() + ", Número Emissão: " + iec.getNroEmissao() + ", Correio: " + iec.isCorreio()
					+ ", Observação: " + iec.getObservacao() + ", Tipo: " + iec.getTipo());
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void atualizarEmissao(intra_emissao_completo ie) {
		this.manager.detach(ie);
	}

	public void atualizarRateio(intra_emissao2 ie) {
		this.manager.detach(ie);
	}

	public void atualizarTipo(intra_emissao_tipo ie) {
		this.manager.detach(ie);
	}

	public void atualizarUnidade(intra_cnduteio2 i) {
		this.manager.detach(i);
	}

	public intra_emissao_completo pesquisaEmissaoPorCodigo(int id) {
		return this.manager.find(intra_emissao_completo.class, id);
	}

	public intra_emissao2 pesquisaRateioPorCodigo(int id) {
		return this.manager.find(intra_emissao2.class, id);
	}

	public List<intra_emissao> listarUnidadeSelect() {
		TypedQuery<intra_emissao> query = this.manager.createQuery("from intra_emissao", intra_emissao.class);
		return query.getResultList();
	}

	public void salvarTipo(intra_emissao_tipo tipo, SessaoMB sessaoMB) {
		if (tipo.getId() == 0) {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.persist(tipo);
			this.ilg.setCondominio(tipo.getCodigo());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_emissao_tipo");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		} else {
			this.ilg = new intra_log_geral();
			data = new Date();
			this.manager.merge(tipo);
			this.ilg.setCondominio(tipo.getCodigo());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_emissao_tipo");
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();
		}
	}

	public void salvarCndrteio(intra_cndrteio2 i) {
		this.manager.persist(i);
	}

	public void salvarCndlteio(intra_cndlteio2 i) {
		this.manager.persist(i);
	}

	public void salvarCnduteio(intra_cnduteio2 i) {
		this.manager.persist(i);
	}

	/********************************************************
	 * RETORNAR ID USUÁRIO
	 **********************************************************************************/
	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

	/********************************************************
	 * LISTAR TABELAS
	 **********************************************************************************/
	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 order by codigo ");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios() {
		this.query = this.manager.createQuery("from intra_condominios order by codigo ");
		return this.query.getResultList();
	}

	// LISTAR RATEIOS (OMACORP)
	@SuppressWarnings("unchecked")
	public List<intra_emissao2> listarRateios() {
		this.query = this.manager.createQuery("from intra_emissao2 ");
		return this.query.getResultList();
	}

	// LISTAR RATEIOS (OMACORP)
	@SuppressWarnings("unchecked")
	public List<intra_emissao2> listarRateiosPorCondominio(int rateioId) {
		this.query = this.manager.createQuery("from intra_emissao2 where rateio_id = :p1");
		this.query.setParameter("p1", rateioId);
		return this.query.getResultList();
	}

	// LISTAR EMISSÃO (OMACORP)
	@SuppressWarnings("unchecked")
	public List<intra_emissao_completo> listarEmissoes(Date dataInicial, Date dataFinal) {
		this.query = this.manager.createQuery(
				"from intra_emissao_completo where vencimento between :p1 and :p2 order by codigo_condominio, vencimento ");
		this.query.setParameter("p1", dataInicial);
		this.query.setParameter("p2", dataFinal);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_emissao_completo> listarEmissoesP(Date dataInicial, Date dataFinal) {
		this.query = this.manager.createQuery(
				"from intra_emissao_completo where vencimento between :p1 and :p2 and tipo_impressao is null order by codigo_condominio, vencimento ");
		this.query.setParameter("p1", dataInicial);
		this.query.setParameter("p2", dataFinal);
		return this.query.getResultList();

	}

	// LISTAR EMISSAO (OMACORP)
	@SuppressWarnings("unchecked")
	public List<intra_emissao_completo> listarEmissoes(int gerente, Date dataInicial, Date dataFinal) {
		this.query = this.manager.createQuery(
				"from intra_emissao_completo where codigo_gerente = :p1 and vencimento between :p2 and :p3 order by codigo_condominio, vencimento");
		this.query.setParameter("p1", gerente);
		this.query.setParameter("p2", dataInicial);
		this.query.setParameter("p3", dataFinal);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_emissao_tipo> listarTipo(int codigo) {
		this.query = this.manager.createQuery("from intra_emissao_tipo where codigo = :p1 order by codigo, tipo");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_emissao_tipo> listarTipo() {
		this.query = this.manager.createQuery("from intra_emissao_tipo order by codigo, tipo");
		return this.query.getResultList();
	}

	// RETORNAR FRAÇÃO PARA EFETUAR CÁLCULO - MÉTODO: adicionarRateio e
	// atualizarRateio
	@SuppressWarnings("unchecked")
	public List<intra_cndunida> retornarFracao(int condominio) {
		List<intra_cndunida> listaUnida = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select bloco, unidade, nome, tipo_unidade, fracao_unidade, fracao_garagem, fracao_extra, cod_cliente, "
						+ "classificacao from sigadm.dbo.cndunida where condominio = :p1");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cndunida cndunida = new intra_cndunida();
				cndunida.setCondominio(condominio);
				if (obj[0] != null) {
					cndunida.setBloco(obj[0].toString());
				}
				if (obj[1] != null) {
					cndunida.setUnidade(obj[1].toString());
				}
				if (obj[2] != null) {
					cndunida.setNome(obj[2].toString());
				}
				if (obj[3] != null) {
					cndunida.setTipo_unidade((char) obj[3]);
				}
				if (obj[4] != null) {
					cndunida.setFracao_unidade(Double.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					cndunida.setFracao_garagem(Double.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					cndunida.setFraca_extra(Double.valueOf(obj[6].toString()));
				}
				if (obj[7] != null) {
					cndunida.setCod_cliente(Integer.valueOf(obj[7].toString()));
				}
				if (obj[8] != null) {
					cndunida.setClassificacao(Integer.valueOf(obj[8].toString()));
				}
				listaUnida.add(cndunida);
			}
		}
		return listaUnida;
	}

	@SuppressWarnings("unchecked")
	public List<intra_emissao_completo> listarEmissoesCanceladas() {
		List<intra_emissao_completo> listaCancelada = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select condominio, data_feito, info_anterior, feito_por from omacorp.dbo.intra_log_geral where tabela = 'intra_emissao_completo' and deletar = 1 order by data_feito DESC");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_emissao_completo iec = new intra_emissao_completo();
				if (obj[0] != null) {
					iec.setCodigo_condominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					iec.setData_impressao((Date) obj[1]);
				}
				if (obj[2] != null) {
					iec.setMotivo(obj[2].toString());
				}
				if (obj[3] != null) {
					iec.setObservacao(obj[3].toString());
				}
				listaCancelada.add(iec);
			}
		}
		return listaCancelada;
	}

	// RETORNAR FRAÇÃO (POR BLOCO) PARA EFETUAR CÁLCULO - MÉTODO:
	// adicionarRateio e atualizarRateio
	@SuppressWarnings("unchecked")
	public List<intra_cndunida> retornarFracaoPorBloco(int condominio, String bloco) {
		List<intra_cndunida> listaUnidaBloco = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select bloco, SUM(fracao_unidade)fracao, SUM(fracao_garagem)garagem, SUM(fracao_extra)extra "
						+ "from sigadm.dbo.cndunida where condominio = :p1 and bloco = :p2 group by bloco");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", bloco);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cndunida cndunida = new intra_cndunida();
				cndunida.setCondominio(condominio);
				if (obj[0] != null) {
					cndunida.setBloco(obj[0].toString());
				}
				if (obj[1] != null) {
					cndunida.setFraca_extra(Double.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					cndunida.setFracao_extra3(Double.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					cndunida.setFracao_extra5(Double.valueOf(obj[3].toString()));
				}
				listaUnidaBloco.add(cndunida);
			}
		}
		return listaUnidaBloco;
	}

	// RETORNAR FRAÇÃO (QUANDO BLOCO != 0) PARA EFETUAR CÁLCULO - MÉTODO:
	// adicionarRateio e atualizarRateio
	@SuppressWarnings("unchecked")
	public List<intra_cndunida> retornaFracaoPredio(int condominio) {
		List<intra_cndunida> listaPredio = new ArrayList<>();
		this.query = this.manager.createNativeQuery(
				"select fracao_apto, fracao_cjto, fracao_garagem, fracao_loja, fracao_outro from sigadm.dbo.cndcondo where codigo = :p1");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cndunida cndunida = new intra_cndunida();
				cndunida.setCondominio(condominio);
				if (obj[0] != null) {
					cndunida.setFraca_extra(Double.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					cndunida.setFracao_extra2(Double.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					cndunida.setFracao_extra3(Double.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					cndunida.setFracao_extra4(Double.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					cndunida.setFracao_extra5(Double.valueOf(obj[4].toString()));
				}
				listaPredio.add(cndunida);
			}
		}
		return listaPredio;
	}

	// LISTAR RATEIOS CNDRTEIO E CNDLTEIO (SIGADM)
	@SuppressWarnings("unchecked")
	public List<intra_emissao> listarRateios(int condominio) throws SQLException {
		List<intra_emissao> listaEmissao = new ArrayList<intra_emissao>();
		this.query = this.manager
				.createNativeQuery("select c.nome, c.codigo, c.gerente, c.vencto_cota_1, c.vencto_cota_2, "
						+ "r.conta, r.historico, l.valor, l.tipo, r.valor_total,  r.regular, r.compl_hist, r.tipo_cob, r.mes_ini, r.ano_ini, "
						+ "r.mes_fin, r.ano_fin, r.cobrar_cota, r.nro_rateio, l.nro_lancto, r.cta_anl_financ, l.bloco from sigadm.dbo.cndcondo c "
						+ "inner join sigadm.dbo.cndrteio r on r.condominio = c.codigo "
						+ "inner join sigadm.dbo.cndlteio l on l.nro_rateio = r.nro_rateio " + "where c.codigo = :p1 ");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_emissao ie = new intra_emissao();
				if (obj[0] != null) {
					ie.setNomeCondominio(obj[0].toString());
				}
				if (obj[1] != null) {
					ie.setCodigoCondominio(Integer.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					ie.setCodigoGerente(Integer.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					ie.setVencto01(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					ie.setVencto02(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					ie.setConta(Integer.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					ie.setHistorico(obj[6].toString());
				}
				if (obj[7] != null) {
					ie.setValor(Double.valueOf(obj[7].toString()));
				}
				if (obj[8] != null) {
					ie.setTipo((char) obj[8]);
				}
				if (obj[9] != null) {
					ie.setValorTotal(Double.valueOf(obj[9].toString()));
				}
				if (obj[10] != null) {
					ie.setRegular(Integer.valueOf(obj[10].toString()));
				}
				if (obj[11] != null) {
					ie.setComplHistorico(Integer.valueOf(obj[11].toString()));
				}
				if (obj[12] != null) {
					ie.setTipoCobranca(Integer.valueOf(obj[12].toString()));
				}
				if (obj[13] != null) {
					ie.setMesInicial(Integer.valueOf(obj[13].toString()));
				}
				if (obj[14] != null) {
					ie.setAnoInicial(Integer.valueOf(obj[14].toString()));
				}
				if (obj[15] != null) {
					ie.setMesFinal(Integer.valueOf(obj[15].toString()));
				}
				if (obj[16] != null) {
					ie.setAnoFinal(Integer.valueOf(obj[16].toString()));
				}
				if (obj[17] != null) {
					ie.setCobrarCota(Integer.valueOf(obj[17].toString()));
				}
				if (obj[18] != null) {
					ie.setNroRateio(Integer.valueOf(obj[18].toString()));
				}
				if (obj[19] != null) {
					ie.setNro_lancto(Integer.valueOf(obj[19].toString()));
				}
				if (obj[20] != null) {
					ie.setCta_anl_financ(Integer.valueOf(obj[20].toString()));
				}
				if (obj[21] != null) {
					ie.setBloco(obj[21].toString());
				}
				listaEmissao.add(ie);
			}
		}
		return listaEmissao;
	}

	// LISTAR VENCIMENTO DO CONDOMÍNIO (SIGADM)
	@SuppressWarnings("unchecked")
	public List<intra_cndprgrt> listarVencimento(int condominio) throws SQLException {
		List<intra_cndprgrt> listaEmissao = new ArrayList<intra_cndprgrt>();
		this.query = this.manager
				.createNativeQuery("select c.condominio, c.emissao, c.vencimento, c.gerente from sigadm.dbo.cndprgrt c "
						+ "inner join omacorp.dbo.intra_condominios t on t.codigo = c.condominio "
						+ "where c.condominio = :p1 and c.vencimento > '2015-12-31' order by c.condominio, c.vencimento DESC ");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cndprgrt ic = new intra_cndprgrt();
				if (obj[0] != null) {
					ic.setCondominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ic.setEmissao(Integer.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					ic.setVencimento((Date) obj[2]);
				}
				if (obj[3] != null) {
					ic.setGerente(Integer.valueOf(obj[3].toString()));
				}
				listaEmissao.add(ic);
			}
		}
		return listaEmissao;
	}

	// LISTAR NÚMERO DA EMISSÃO (SIGADM)
	@SuppressWarnings("unchecked")
	public int listarNroEmissao(int condominio, Date vencimento) {
		int retorno = 0;
		this.query = this.manager.createNativeQuery(
				"select emissao from sigadm.dbo.cndprgrt where condominio = :p1 and vencimento = :p2");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", vencimento);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object is : lista) {
				retorno = Integer.valueOf(String.valueOf(is));
			}
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public int listarVencto01(int condominio) {
		int vencimento = 0;
		this.query = this.manager
				.createNativeQuery("select vencto_cota_1 from sigadm.dbo.cndcondo where codigo = :p1");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object is : lista) {
				vencimento = Integer.valueOf(String.valueOf(is));
			}
		}
		return vencimento;
	}

	@SuppressWarnings("unchecked")
	public int listarVencto02(int condominio) {
		int vencimento = 0;
		this.query = this.manager
				.createNativeQuery("select vencto_cota_2 from sigadm.dbo.cndcondo where codigo = :p1");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object is : lista) {
				vencimento = Integer.valueOf(String.valueOf(is));
			}
		}
		return vencimento;
	}

	// LISTAR LEITURA PARA VERIFICAR SE JÁ EXISTE LEITURA ANTERIOR (SIGADM)
	@SuppressWarnings("unchecked")
	public List<cndleitu> listaLeitura(int condominio, int ano, int mes, int tipo) {
		List<cndleitu> listaLeitura = new ArrayList<cndleitu>();
		this.query = this.manager.createNativeQuery(
				"select numero, bloco, tipo, ano, mes from sigadm.dbo.cndleitu where condominio = :p1 and ano = :p2 and mes = :p3 and tipo = :p4");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", ano);
		this.query.setParameter("p3", mes);
		this.query.setParameter("p4", tipo);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				cndleitu cl = new cndleitu();
				cl.setCondominio(condominio);
				if (obj[0] != null) {
					cl.setNumero(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					cl.setBloco(obj[1].toString());
				}
				if (obj[2] != null) {
					cl.setTipo(Integer.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					cl.setAno(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					cl.setMes(Integer.valueOf(obj[4].toString()));
				}
				listaLeitura.add(cl);
			}
		}
		return listaLeitura;
	}

	// ATUALIZAR NA CNDMONIT O CÓDIGO DA COLUNA CONSUMO (SIGADM) - Para inserir
	// valores na cndleitu
	public int retornaCodigoConsumo() {
		int retorno = 0;
		try {
			String consulta = "select consumo from [sigadm].[dbo].[cndmonit]";
			this.con = Conexao.getConexaoSiga();
			this.pst = con.prepareStatement(consulta);
			this.rs = this.pst.executeQuery();
			while (this.rs.next()) {
				if (this.rs.getInt("consumo") > 0) {
					retorno = this.rs.getInt("consumo");
					retorno += 1;
					String update = "update sigadm.dbo.cndmonit set consumo = " + (retorno);
					this.pst = this.con.prepareStatement(update);
					this.pst.executeUpdate();
				}
			}
			this.pst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("Insert na cndmonit, na coluna de Controle de Consumo " + e.getMessage());
		}
		return retorno;
	}

	// LISTAR TARIFAS PARA LANÇAR CONSUMO (SIGADM)
	@SuppressWarnings("unchecked")
	public List<intra_cndtarif> listarTarifas() {
		List<intra_cndtarif> listaTarifas = new ArrayList<intra_cndtarif>();
		this.query = this.manager
				.createNativeQuery("select codigo, descricao, unidade_medida from sigadm.dbo.cndtarif");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cndtarif ic = new intra_cndtarif();
				if (obj[0] != null) {
					ic.setCodigo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					ic.setDescricao(obj[1].toString());
				}
				if (obj[2] != null) {
					ic.setUnidade_medida(obj[2].toString());
				}
				listaTarifas.add(ic);
			}
		}
		return listaTarifas;
	}

	/******************************************************** ATUALIZAÇÕES **********************************************************************************/

	// CANCELAMENTO DE BOLETO
	public void cancelarBoleto(intra_procuracao_juridico ipj, SessaoMB sessaoMB) {
		this.query = this.manager
				.createNativeQuery("update sigadm.dbo.cndrecib set flag_situacao = 9, data_recbto = GETDATE()"
						+ "where condominio = :p2 and emissao = :p3 and recibo = :p4 and vencto = :p5 ");
		this.query.setParameter("p2", ipj.getCodigoCondominio());
		this.query.setParameter("p3", ipj.getEmissao());
		this.query.setParameter("p4", ipj.getRecibo());
		this.query.setParameter("p5", ipj.getVencto());
		this.query.executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(ipj.getCodigoCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndrecib");
		this.ilg.setInfoAnterior(
				"Cancelamento efetuado - Emissão: " + ipj.getEmissao() + " Recibo: " + ipj.getRecibo());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	// INSERÇÃO NAS TABELAS CNDRTEIO E CNDLTEIO
	public void adicionarRateio(cndrteio cndrteio, intra_cndlteio cndlteio, SessaoMB sessaoMB) {

		this.manager.persist(cndrteio);
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(cndrteio.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndrteio");
		this.ilg.setInfoAnterior("Rateio criado:" + cndrteio.getNro_rateio());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();

		this.query = this.manager
				.createNativeQuery("insert into sigadm.dbo.cndlteio(nro_rateio, nro_lancto, bloco, tipo, "
						+ "valor, seguro, valor_calculado, flag_calc, usar_fator_mult, perc_desc, historico, data_alteracao, "
						+ "cobrar_por_metr, codigo_tarifa, desprezar_agreg)"
						+ "values (:p1, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9, :p10, :p11, :p12, :p13, :p14, :p15)");
		this.query.setParameter("p1", cndrteio.getNro_rateio());
		this.query.setParameter("p2", cndlteio.getNro_lancto());
		this.query.setParameter("p3", cndlteio.getBloco());
		this.query.setParameter("p4", cndlteio.getTipo());
		this.query.setParameter("p5", cndlteio.getValor());
		this.query.setParameter("p6", cndlteio.getSeguro());
		this.query.setParameter("p7", cndlteio.getValor_calculado());
		this.query.setParameter("p8", cndlteio.getFlag_calc());
		this.query.setParameter("p9", cndlteio.getUsar_fator_mult());
		this.query.setParameter("p10", cndlteio.getPerc_desc());
		this.query.setParameter("p11", cndlteio.getHistorico());
		this.query.setParameter("p12", cndlteio.getData_alteracao());
		this.query.setParameter("p13", cndlteio.getCobrar_por_metr());
		this.query.setParameter("p14", cndlteio.getCodigo_tarifa());
		this.query.setParameter("p15", cndlteio.getDesprezar_agreg());
		this.query.executeUpdate();

		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(cndrteio.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndlteio");
		this.ilg.setInfoAnterior("Lançamento criado de nº: " + cndlteio.getNro_lancto() + ", do rateio de nº: "
				+ cndrteio.getNro_rateio());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();

		this.query = this.manager.createNativeQuery(
				"update sigadm.dbo.cndrteio set valor_total = :p1, lctos = :p2 where nro_rateio = :p3");
		this.query.setParameter("p1", cndlteio.getValor());
		this.query.setParameter("p2", cndlteio.getNro_lancto());
		this.query.setParameter("p3", cndrteio.getNro_rateio());
		this.query.executeUpdate();

	}

	// INSERÇÃO NA TABELA CNDUTEIO
	public void salvarUnidade(cndrteio cndrteio, intra_cndlteio cndlteio, intra_cnduteio cnduteio, SessaoMB sessaoMB) {
		this.query = this.manager.createNativeQuery("insert into sigadm.dbo.cnduteio (nro_rateio, nro_lancto, "
				+ "condominio, bloco, unidade, valor, comp_hist) values (:p1, :p2, :p3, :p4, :p5, :p6, :p7)");
		this.query.setParameter("p1", cndrteio.getNro_rateio());
		this.query.setParameter("p2", cndlteio.getNro_lancto());
		this.query.setParameter("p3", cndrteio.getCondominio());
		this.query.setParameter("p4", cnduteio.getBloco());
		this.query.setParameter("p5", cnduteio.getUnidade());
		this.query.setParameter("p6", cnduteio.getValor());
		this.query.setParameter("p7", cnduteio.getComp_hist());
		this.query.executeUpdate();
	}

	// INSERÇÃO NA TABELA CNDUTEIO (QUANDO BLOCO == 0)
	public void salvarUnidade(cndrteio cndrteio, intra_cndlteio cndlteio, intra_cnduteio cnduteio, SessaoMB sessaoMB,
			String bloco) {
		this.query = this.manager.createNativeQuery("insert into sigadm.dbo.cnduteio (nro_rateio, nro_lancto, "
				+ "condominio, bloco, unidade, valor, comp_hist) values (:p1, :p2, :p3, :p4, :p5, :p6, :p7)");
		this.query.setParameter("p1", cndrteio.getNro_rateio());
		this.query.setParameter("p2", cndlteio.getNro_lancto());
		this.query.setParameter("p3", cndrteio.getCondominio());
		this.query.setParameter("p4", bloco);
		this.query.setParameter("p5", cnduteio.getUnidade());
		this.query.setParameter("p6", cnduteio.getValor());
		this.query.setParameter("p7", cnduteio.getComp_hist());
		this.query.executeUpdate();
	}

	// ATUALIZAR O VALOR CALCULADA DA TABELA CNDLTEIO
	public void salvarValorTotalUnidade(double valorTotal, cndrteio cndrteio) {
		this.query = this.manager.createNativeQuery(
				"update sigadm.dbo.cndlteio set valor_calculado = :p1, valor = :p2 where nro_rateio = :p3");
		this.query.setParameter("p1", valorTotal);
		this.query.setParameter("p2", valorTotal);
		this.query.setParameter("p3", cndrteio.getNro_rateio());
		this.query.executeUpdate();

		this.query = this.manager
				.createNativeQuery("update sigadm.dbo.cndrteio set valor_total = :p1 where nro_rateio = :p2");
		this.query.setParameter("p1", valorTotal);
		this.query.setParameter("p2", cndrteio.getNro_rateio());
		this.query.executeUpdate();
	}

	// ATUALIZAR AS TABELAS CNDRTEIO E CNDLTEIO
	public void atualizarRateio(cndrteio cndrteio, intra_cndlteio cndlteio, SessaoMB sessaoMB) {

		this.query = this.manager.createNativeQuery(
				"update sigadm.dbo.cndrteio set historico = :p1, conta = :p2 where nro_rateio = :p3 and lctos = :p4 and condominio = :p5");
		this.query.setParameter("p1", cndrteio.getHistorico());
		this.query.setParameter("p2", cndrteio.getConta());
		this.query.setParameter("p3", cndrteio.getNro_rateio());
		this.query.setParameter("p4", cndlteio.getNro_lancto());
		this.query.setParameter("p5", cndrteio.getCondominio());
		this.query.executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(cndrteio.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndrteio");
		this.ilg.setInfoAnterior("Rateio atualizado de nº:" + cndrteio.getNro_rateio());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();

		this.query = this.manager
				.createNativeQuery("update sigadm.dbo.cndlteio set valor = :p1 where nro_rateio = :p2 and tipo = :p3");
		this.query.setParameter("p1", cndlteio.getValor());
		this.query.setParameter("p2", cndrteio.getNro_rateio());
		this.query.setParameter("p3", cndlteio.getTipo());
		this.query.executeUpdate();

		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(cndrteio.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndlteio");
		this.ilg.setInfoAnterior("Lançamento de nº " + cndlteio.getNro_lancto() + " alterado. Do rateio de nº: "
				+ cndrteio.getNro_rateio());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();

		this.query = this.manager.createNativeQuery(
				"update sigadm.dbo.cndrteio set valor_total = :p1, lctos = :p2 where nro_rateio = :p3");
		this.query.setParameter("p1", cndlteio.getValor());
		this.query.setParameter("p2", cndlteio.getNro_lancto());
		this.query.setParameter("p3", cndrteio.getNro_rateio());
		this.query.executeUpdate();

	}

	// ATUALIZAR A TABELA CNDUTEIO
	public void atualizarUnidade(cndrteio cndrteio, intra_cndlteio cndlteio, intra_cnduteio cnduteio,
			SessaoMB sessaoMB) {
		this.query = this.manager.createNativeQuery(
				"update sigadm.dbo.cnduteio set valor = :p1 where nro_rateio = :p2 and nro_lancto = :p3 "
						+ "and condominio = :p4 and bloco = :p5 and unidade = :p6");
		this.query.setParameter("p1", cnduteio.getValor());
		this.query.setParameter("p2", cndrteio.getNro_rateio());
		this.query.setParameter("p3", cndlteio.getNro_lancto());
		this.query.setParameter("p4", cndrteio.getCondominio());
		this.query.setParameter("p5", cnduteio.getBloco());
		this.query.setParameter("p6", cnduteio.getUnidade());
		this.query.executeUpdate();
	}

	// ATUALIZAR A TABELA CNDUTEIO (QUANDO O TIPO == I)
	public void atualizarUnidadeTipoI(cndrteio cndrteio, intra_cndlteio cndlteio, intra_cnduteio cnduteio,
			SessaoMB sessaoMB) {
		this.query = this.manager
				.createNativeQuery("update sigadm.dbo.cnduteio set unidade = :p1, bloco = :p2, valor = :p3 "
						+ "where nro_rateio = :p4 and nro_lancto = :p5 and condominio = :p6");
		this.query.setParameter("p2", cnduteio.getBloco());
		this.query.setParameter("p1", cnduteio.getUnidade());
		this.query.setParameter("p3", cnduteio.getValor());
		this.query.setParameter("p4", cndrteio.getNro_rateio());
		this.query.setParameter("p5", cndlteio.getNro_lancto());
		this.query.setParameter("p6", cndrteio.getCondominio());
		this.query.executeUpdate();
	}

	// INSERÇÃO NA TABELA CNDLEITU
	public void adicionarLeitura(cndleitu cndleitu, SessaoMB sessaoMB) {
		this.query = this.manager.createNativeQuery(
				"insert into sigadm.dbo.cndleitu (condominio, mes, ano, data, tipo, importacao, bloco, numero) values (:p1, :p2, :p3, :p4, :p5, :p6, :p7, :p8) ");
		this.query.setParameter("p1", cndleitu.getCondominio());
		this.query.setParameter("p2", cndleitu.getMes());
		this.query.setParameter("p3", cndleitu.getAno());
		this.query.setParameter("p4", cndleitu.getData());
		this.query.setParameter("p5", cndleitu.getTipo());
		this.query.setParameter("p6", cndleitu.getImportacao());
		this.query.setParameter("p7", cndleitu.getBloco());
		this.query.setParameter("p8", cndleitu.getNumero());
		this.query.executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(cndleitu.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndleitu");
		this.ilg.setInfoAnterior("Leitura Nº " + cndleitu.getNumero());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	// INSERÇÃO NA TABELA CNDCONSU
	public void adicionarLeituraUnidade(cndleitu cndleitu, intra_cndconsu cndconsu, SessaoMB sessaoMB) {
		this.query = this.manager.createNativeQuery(
				"insert into sigadm.dbo.cndconsu (numero, condominio, bloco, unidade, mes, ano, anterior, atual, consumo_orig, consumo_conv, "
						+ "cobrar_media, valor) values (:p1, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9, :p10, :p11, :p12) ");
		this.query.setParameter("p1", cndleitu.getNumero());
		this.query.setParameter("p2", cndconsu.getCondominio());
		this.query.setParameter("p3", cndconsu.getBloco());
		this.query.setParameter("p4", cndconsu.getUnidade());
		this.query.setParameter("p5", cndconsu.getMes());
		this.query.setParameter("p6", cndconsu.getAno());
		this.query.setParameter("p7", cndconsu.getAnterior());
		this.query.setParameter("p8", cndconsu.getAtual());
		this.query.setParameter("p9", cndconsu.getConsumo_orig());
		this.query.setParameter("p10", cndconsu.getConsumo_conv());
		this.query.setParameter("p11", cndconsu.getCobrar_media());
		this.query.setParameter("p12", cndconsu.getValor());
		this.query.executeUpdate();
	}

	// EXCLUSÃO NA TABELA CNDCONSU
	public void deletarCndConsu(cndleitu cndleitu) {
		this.query = this.manager.createNativeQuery("delete from sigadm.dbo.cndconsu where numero = :p1");
		this.query.setParameter("p1", cndleitu.getNumero());
		this.query.executeUpdate();
	}

	// EXCLUSÃO NA TABELA CNDLEITU
	public void deletarCndLeitu(cndleitu cndleitu, SessaoMB sessaoMB) {
		this.query = this.manager.createNativeQuery(
				"delete from sigadm.dbo.cndleitu where condominio = :p1 and ano = :p2 and mes = :p3 and tipo = :p4 ");
		this.query.setParameter("p1", cndleitu.getCondominio());
		this.query.setParameter("p2", cndleitu.getAno());
		this.query.setParameter("p3", cndleitu.getMes());
		this.query.setParameter("p4", cndleitu.getTipo());
		this.query.executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(cndleitu.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndleitu");
		this.ilg.setInfoAnterior("Leitura Nº " + cndleitu.getNumero());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();

	}

	// ATUALIZAR VALOR TOTAL E TOTAL DO CONSUMO NA TABELA CNDLEITU
	public void salvarValorEConsumoTotal(double valorTotal, double totalConsumo, cndleitu cndleitu) {
		this.query = this.manager.createNativeQuery(
				"update sigadm.dbo.cndleitu set total_consumo = :p1, valor_total = :p2 where numero = :p3 and condominio = :p4");
		this.query.setParameter("p2", valorTotal);
		this.query.setParameter("p1", totalConsumo);
		this.query.setParameter("p3", cndleitu.getNumero());
		this.query.setParameter("p4", cndleitu.getCondominio());
		this.query.executeUpdate();
	}

	// ATUALIZAR LCTO_COBRANÇA NA TABELA CNDLEITU
	public void salvarRateioConsumo(cndrteio cndrteio, cndleitu cndleitu) {
		this.query = this.manager
				.createNativeQuery("update sigadm.dbo.cndleitu set lcto_cobranca = :p1 where condominio = :p2");
		this.query.setParameter("p1", cndrteio.getNro_rateio());
		this.query.setParameter("p2", cndrteio.getCondominio());
		this.query.executeUpdate();
	}

	// EXCLUIR EMISSÃO
	public void excluirRateio(intra_emissao_completo iec, SessaoMB sessaoMB) {
		this.query = this.manager.createNativeQuery("delete from omacorp.dbo.intra_emissao2 where rateio_id = :p1");
		this.query.setParameter("p1", iec.getId());
		this.query.executeUpdate();

		this.query = this.manager.createNativeQuery("delete from omacorp.dbo.intra_emissao_completo where id = :p1");
		this.query.setParameter("p1", iec.getId());
		this.query.executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(iec.getCodigo_condominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_emissao_completo");
		this.ilg.setInfoAnterior("Vencimento: " + iec.getVencimento() + " - Motivo: " + iec.getMotivo()
				+ " - Excluído Por:" + sessaoMB.getUsuario().getNome());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

}