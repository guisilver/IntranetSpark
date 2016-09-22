package br.com.oma.omaonline.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.dao.LogGeralDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.atbancos;
import br.com.oma.omaonline.entidades.cndcondo_param;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cndpagar_aprovacao;
import br.com.oma.omaonline.entidades.cndpagar_followup;
import br.com.oma.omaonline.entidades.cndplano;
import br.com.oma.omaonline.entidades.consulta_financeiro;
import br.com.oma.omaonline.entidades.controle_parcelado;
import br.com.oma.omaonline.entidades.controle_rateio;
import br.com.oma.omaonline.entidades.cpcredor;
import br.com.oma.omaonline.entidades.cpfavor;
import br.com.oma.omaonline.entidades.financeiro_imagem;
import br.com.oma.omaonline.entidades.rateio;

public class FinanceiroSIPDAO extends LogGeralDAO {

	private static final long serialVersionUID = 825419115040822026L;
	private EntityManager manager;
	private Query query;
	private PreparedStatement pmst;
	private Connection con;
	private ResultSet rs;

	public FinanceiroSIPDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<consulta_financeiro> listarGEDAprovados() {
		List<consulta_financeiro> cf = new ArrayList<consulta_financeiro>();

		this.query = this.manager.createNativeQuery(
				"select  distinct (condominio) from omaonline.dbo.cndpagar where  status_ = 'a' order by condominio");
		List<Short> condominios = this.query.getResultList();

		if (!condominios.isEmpty()) {
			for (Short obj : condominios) {
				consulta_financeiro condo = new consulta_financeiro();
				condo.setCondominio(Integer.valueOf(obj));
				this.query = this.manager.createNativeQuery(
						"select  count (status_) as statP from omaonline.dbo.cndpagar where  status_ = 'a' and condominio = :p1 group by condominio, status_");
				this.query.setParameter("p1", condo.getCondominio());
				List<Integer> listaP = this.query.getResultList();
				if (!listaP.isEmpty()) {
					condo.setQtdAprovados(Integer.valueOf(listaP.get(0).toString()));
				}
				cf.add(condo);
			}
		}

		return cf;
	}

	@SuppressWarnings("unchecked")
	public List<consulta_financeiro> listarGEDAprovados(int gerente) {
		List<consulta_financeiro> cf = new ArrayList<consulta_financeiro>();

		this.query = this.manager.createNativeQuery(
				"select  distinct (condominio) from omaonline.dbo.cndpagar p inner join omacorp.dbo.intra_condominios c on p.condominio = c.codigo  where c.codigoGerente = :p1 and  status_ = 'a'");
		this.query.setParameter("p1", gerente);
		List<Short> condominios = this.query.getResultList();

		if (!condominios.isEmpty()) {
			for (Short obj : condominios) {
				consulta_financeiro condo = new consulta_financeiro();
				condo.setCondominio(Integer.valueOf(obj));

				this.query = this.manager.createNativeQuery(
						"select  count (status_) as statP from omaonline.dbo.cndpagar where  status_ = 'a' and condominio = :p1 group by condominio, status_");
				this.query.setParameter("p1", condo.getCondominio());
				List<Integer> listaP = this.query.getResultList();
				if (!listaP.isEmpty()) {
					condo.setQtdAprovados(Integer.valueOf(listaP.get(0).toString()));
				}
				cf.add(condo);
			}
		}

		return cf;
	}

	@SuppressWarnings("unchecked")
	public List<consulta_financeiro> listarGED() {
		List<consulta_financeiro> cf = new ArrayList<consulta_financeiro>();

		this.query = this.manager.createNativeQuery(
				"select  distinct (condominio) from omaonline.dbo.cndpagar where  (status_ = 'p' or status_ = 'z' or status_ = 'r')");
		List<Short> condominios = this.query.getResultList();

		if (!condominios.isEmpty()) {
			for (Short obj : condominios) {
				consulta_financeiro condo = new consulta_financeiro();
				condo.setCondominio(Integer.valueOf(obj));

				this.query = this.manager.createNativeQuery(
						"select  count (status_) as statP from omaonline.dbo.cndpagar where  status_ = 'p' and condominio = :p1 group by condominio, status_");
				this.query.setParameter("p1", condo.getCondominio());
				List<Integer> listaP = this.query.getResultList();
				if (!listaP.isEmpty()) {
					condo.setQtdPendente(Integer.valueOf(listaP.get(0).toString()));
				}

				this.query = this.manager.createNativeQuery(
						"select  count (status_) as statP from omaonline.dbo.cndpagar where  status_ = 'z' and condominio = :p2 group by condominio, status_");
				this.query.setParameter("p2", condo.getCondominio());
				List<Integer> listaZ = this.query.getResultList();
				if (!listaZ.isEmpty()) {
					condo.setQtdAguardando(Integer.valueOf(listaZ.get(0).toString()));
				}

				this.query = this.manager.createNativeQuery(
						"select  count (status_) as statP from omaonline.dbo.cndpagar where  status_ = 'r' and condominio = :p3 and vencimento > (GETDATE() -15) group by condominio, status_");
				this.query.setParameter("p3", condo.getCondominio());
				List<Integer> listaR = this.query.getResultList();
				if (!listaR.isEmpty()) {
					condo.setQtdReprovado(Integer.valueOf(listaR.get(0).toString()));
				}
				cf.add(condo);

			}
		}

		return cf;
	}

	@SuppressWarnings("unchecked")
	public List<consulta_financeiro> listarGED(int gerente) {
		List<consulta_financeiro> cf = new ArrayList<consulta_financeiro>();

		this.query = this.manager.createNativeQuery(
				"select  distinct (condominio) from omaonline.dbo.cndpagar p inner join omacorp.dbo.intra_condominios c on p.condominio = c.codigo  where c.codigoGerente = :p1 and  (status_ = 'p' or status_ = 'z' or status_ = 'r')");
		this.query.setParameter("p1", gerente);
		List<Short> condominios = this.query.getResultList();

		if (!condominios.isEmpty()) {
			for (Short obj : condominios) {
				consulta_financeiro condo = new consulta_financeiro();
				condo.setCondominio(Integer.valueOf(obj));

				this.query = this.manager.createNativeQuery(
						"select  count (status_) as statP from omaonline.dbo.cndpagar where  status_ = 'p' and condominio = :p1 group by condominio, status_");
				this.query.setParameter("p1", condo.getCondominio());
				List<Integer> listaP = this.query.getResultList();
				if (!listaP.isEmpty()) {
					condo.setQtdPendente(Integer.valueOf(listaP.get(0).toString()));
				}

				this.query = this.manager.createNativeQuery(
						"select  count (status_) as statP from omaonline.dbo.cndpagar where  status_ = 'z' and condominio = :p2 group by condominio, status_");
				this.query.setParameter("p2", condo.getCondominio());
				List<Integer> listaZ = this.query.getResultList();
				if (!listaZ.isEmpty()) {
					condo.setQtdAguardando(Integer.valueOf(listaZ.get(0).toString()));
				}

				this.query = this.manager.createNativeQuery(
						"select  count (status_) as statP from omaonline.dbo.cndpagar where  status_ = 'r' and condominio = :p3 and vencimento > (GETDATE() -15) group by condominio, status_");
				this.query.setParameter("p3", condo.getCondominio());
				List<Integer> listaR = this.query.getResultList();
				if (!listaR.isEmpty()) {
					condo.setQtdReprovado(Integer.valueOf(listaR.get(0).toString()));
				}
				cf.add(condo);

			}
		}

		return cf;
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> listarCndPagarAprovados(short condominio) {
		this.query = this.manager
				.createQuery("from cndpagar where condominio = :p1 and status_ = 'A' order by vencimento desc");
		this.query.setParameter("p1", condominio);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> listarCndPagar(short condominio) {
		this.query = this.manager.createQuery(
				"from cndpagar where condominio = :p1 and (status_ = 'P' or status_ = 'Z' or status_ = 'R') order by vencimento desc");
		this.query.setParameter("p1", condominio);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> listarCndPagarContas(short condominio) {
		this.query = this.manager.createQuery(
				"from cndpagar where condominio = :p1 and (status_ = 'P' or status_ = 'Z') order by vencimento desc");
		this.query.setParameter("p1", condominio);
		return this.query.getResultList();
	}

	public String listarNomeCondominio(Short condominio) {
		this.query = this.manager.createQuery("select nome from intra_condominios where codigo = :p1");
		this.query.setParameter("p1", Integer.valueOf(condominio));
		return this.query.getResultList().toString().replace("[", "").replace("]", "");
	}

	public cndpagar pesqLancto(int codigo) {
		return this.manager.find(cndpagar.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> listarContasRateadas(int codigoRateio) {
		this.query = this.manager.createQuery("from cndpagar where cod_rateio = :p1");
		this.query.setParameter("p1", codigoRateio);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public cndplano listarPlano(int conta, short s) {
		cndplano p = new cndplano();
		if (s == 4241) {
			this.query = this.manager.createNativeQuery(
					"select  nome, cod_reduzido from sigadm.dbo.cndplano where codigo_plano = 4321 and cod_reduzido = "
							+ conta);
			List<Object[]> spconta = query.getResultList();
			for (Object[] obj : spconta) {
				p.setNome(obj[0].toString());
				p.setCod_reduzido(Integer.valueOf(obj[1].toString()) - 100000);
			}
		} else {
			this.query = this.manager.createNativeQuery(
					"select  nome, cod_reduzido from sigadm.dbo.cndplano where codigo_plano <> 4321 and cod_reduzido = "
							+ conta);
			List<Object[]> spconta = query.getResultList();
			for (Object[] obj : spconta) {
				p.setNome(obj[0].toString());
				p.setCod_reduzido(Integer.valueOf(obj[1].toString()));
			}
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar_followup> listarFollowUp(int nrolancto) {
		this.query = this.manager.createQuery("from cndpagar_followup where nrolancto = :p1 order by data desc");
		this.query.setParameter("p1", nrolancto);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaPDF(long id) {
		byte[] pdf = null;
		this.query = this.manager
				.createNativeQuery("select img from omaonline.dbo.financeiro_imagem where codigo = :p1");
		this.query.setParameter("p1", id);
		List<Object[]> lst = this.query.getResultList();
		for (Object aux : lst) {
			if (aux != null) {
				pdf = (byte[]) aux;
			}
		}
		return pdf;
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaImagemEtiqueta(Long etiqueta) {
		byte[] pdf = null;
		this.query = this.manager.createQuery("from financeiro_imagem where id = :p1");
		this.query.setParameter("p1", Double.valueOf(etiqueta));
		List<financeiro_imagem> lst = this.query.getResultList();
		for (financeiro_imagem aux : lst) {
			if (aux.getImagem() != null) {
				pdf = aux.getImagem();
			}
		}
		/*
		 * try { financeiro_imagem imagem = null; Query query =
		 * this.manager.createNativeQuery(
		 * "select arquivo, codigo_condominio, nome_arquivo  from robo_scan.dbo.leitura_arquivo where codigo_etiqueta = :etiqueta"
		 * ); query.setParameter("etiqueta", etiqueta); Object[] aux =
		 * (Object[]) query.getSingleResult(); imagem = new financeiro_imagem();
		 * imagem.setId(etiqueta);
		 * 
		 * if (aux[0] != null) { imagem.setImagem((byte[]) aux[0]); }
		 * 
		 * if (aux[1] != null) {
		 * imagem.setCdCnd(Short.parseShort(aux[1].toString())); }
		 * 
		 * if (aux[2] != null) { imagem.setNome_arquivo(aux[2].toString()); }
		 * 
		 * pdf = imagem.getImagem();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		return pdf;
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> listaDeCndpagarRateado(cndpagar cndpagar) {
		this.query = this.manager
				.createQuery("from cndpagar where condominio = :p1 and rateado = :p2 and cod_rateio = :p3");
		query.setParameter("p1", cndpagar.getCondominio());
		query.setParameter("p2", cndpagar.getRateado());
		query.setParameter("p3", cndpagar.getCodigoRateio());
		return query.getResultList();
	}

	public void atualizaAprovacaoLancamento(cndpagar cndpagar, cndpagar_aprovacao aprovacao, String usuario, String ip,
			String obsLancto) {
		String acao = null;
		if (aprovacao != null && aprovacao.getStatus_().equals("A")) {
			this.manager.persist(aprovacao);
			cndpagar.getAprovadores().add(aprovacao);
			acao = "Pré Aprovado";
			this.registrarFollowUp(cndpagar.getCodigo(), acao, usuario, ip, cndpagar.getCondominio(), obsLancto);
		}
		this.manager.merge(cndpagar);
	}

	public void registrarFollowUp(int nrolancto, String acao, String feitoPor, String ip, short cdCnd,
			String obsLancto) {
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

	public void removerAprovacoes(List<cndpagar_aprovacao> aprovadores) {
		for (cndpagar_aprovacao aux : aprovadores) {
			aux = this.manager.merge(aux);
			this.manager.remove(aux);
		}
	}

	public void reprovarLancamento(cndpagar cndpagar, String email, String ip, String obsLancto) {
		if (cndpagar.getCodigoRateio() > 0) {
			this.query = this.manager.createQuery(
					"update cndpagar set status_ = 'R', motivoReprovacao = :p1, obs = :p2 where cod_rateio = :p3");
			this.query.setParameter("p1", cndpagar.getMotivoReprovacao());
			this.query.setParameter("p2", cndpagar.getObs());
			this.query.setParameter("p3", cndpagar.getCodigoRateio()).executeUpdate();
		} else {
			this.query = this.manager.createQuery(
					"update cndpagar set status_ = 'R',motivoReprovacao = :p1, obs = :p2 where codigo = :p3");
			this.query.setParameter("p1", cndpagar.getMotivoReprovacao());
			this.query.setParameter("p2", cndpagar.getObs());
			this.query.setParameter("p3", cndpagar.getCodigo()).executeUpdate();
		}

		this.registrarFollowUp(cndpagar.getCodigo(), "Reprovado", email, ip, cndpagar.getCondominio(), obsLancto);
	}

	@SuppressWarnings("unchecked")
	public void excluirLancamento(cndpagar cndpagar, String email, String ip, String obsLancto) {
		this.registrarFollowUp(cndpagar.getCodigo(), "Excluído", email, ip, cndpagar.getCondominio(), obsLancto);
		if (cndpagar.getCodigoRateio() > 0 && cndpagar.getRateado().equals("S")) {
			this.query = this.manager.createQuery("from cndpagar where cod_rateio = :p1");
			this.query.setParameter("p1", cndpagar.getCodigoRateio());
			List<cndpagar> lstCndpagar = this.query.getResultList();
			for (cndpagar aux : lstCndpagar) {
				this.manager.remove(aux);
			}
		} else if (cndpagar.getCodigoParcelado() > 0 && cndpagar.getParcelado().equals("S")) {
			if (cndpagar.getPcInicial() != 0 && cndpagar.getPcInicial() > 1) {
				this.query = this.manager.createQuery("update cndpagar set valida_parcelado = 1 where cod_parcelado = "
						+ cndpagar.getCodigoParcelado() + " and pc_inicial = " + (cndpagar.getPcInicial() - 1));
				this.query.executeUpdate();
			}
			this.query = this.manager.createQuery("from cndpagar where cod_parcelado = :p1 and status_ <> 'A'");
			this.query.setParameter("p1", cndpagar.getCodigoParcelado());
			List<cndpagar> lstCndpagar = this.query.getResultList();
			for (cndpagar aux : lstCndpagar) {
				this.manager.remove(aux);
			}
		} else {
			cndpagar = this.manager.merge(cndpagar);
			this.manager.remove(cndpagar);
		}
	}

	@SuppressWarnings("unchecked")
	public cndcondo_param condominioParam(short condominio) {
		this.query = this.manager.createQuery("from cndcondo_param where codigo_cnd = :p1");
		this.query.setParameter("p1", condominio);
		List<cndcondo_param> lista = this.query.getResultList();
		cndcondo_param param = new cndcondo_param();
		if (!lista.isEmpty()) {
			for (cndcondo_param p : lista) {
				param = p;
			}
		}
		return param;
	}

	
	public int returnUltimoControlRat() {
		int nrorateio;
		controle_rateio c = this.manager.find(controle_rateio.class, 1L, LockModeType.PESSIMISTIC_WRITE);
		c.setVersao(c.getVersao() + 1);
		manager.persist(c);
		;
		nrorateio = Integer.valueOf(String.valueOf(c.getVersao()));
		return nrorateio;
	}

	@SuppressWarnings("unchecked")
	public boolean verificaImg(double codigo) {
		boolean val = false;
		this.query = this.manager
				.createNativeQuery("select * from sigadm.dbo.glbCatalogo_Docto where id_catalogo = " + codigo);
		List<Object[]> lista = query.getResultList();
		if (!lista.isEmpty()) {
			val = true;
		}
		return val;
	}

	public void salvarFavSiga(cndpagar obj) {
		int codigo;
		this.query = this.manager.createNativeQuery("select max(codigo) from sigadm.dbo.cpfavor");
		codigo = Integer.valueOf(this.query.getResultList().get(0).toString());
		codigo += 1;

		this.query = this.manager.createNativeQuery("INSERT INTO [sigadm].[dbo].[cpfavor]"
				+ "([codigo],[favorecido],[tipo_pessoa],[cnpj_cpf],[banco],[agencia],[conta_corrente],[dac_conta],[conta_poupanca],[tipo_conta])"
				+ "VALUES(" + codigo + ",'" + obj.getFavorecido() + "','" + obj.getTipoPessoa() + "'," + obj.getCnpj()
				+ "," + obj.getBancoDestino() + "," + obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','"
				+ obj.getDigAgeDest().trim() + "' ,'" + obj.getTipoContaBancaria().trim() + "',null)");
		this.query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public String adicionarLanctoSigaRateado(cndpagar obj, String email, String ip, String obsLancto) {

		String confirmacao = null;
		obj.setBloco("0");
		obj.setPercentual(0.00);
		obj.setCpmf("N");
		obj.setMoeda((short) 0);
		obj.setNomeAgencia("");
		obj.setCodCompensacao((short) 0);
		obj.setCodPagto((short) 0);
		obj.setMes(0);
		obj.setAno((short) 0);
		obj.setVrTributo(0.00);
		obj.setVrOutros(0.00);
		obj.setVrAtualMonet(0.00);
		obj.setPgCredpoup("N");
		obj.setReterImposto("N");
		obj.setCodReceita((short) 0);
		obj.setDocumento("");
		obj.setValorNf(obj.getValor());
		obj.setIncideTxAdm(0);
		obj.setAutorizacao("");
		obj.setContribuinte("");
		obj.setDeclaracao(0);
		obj.setNroId(0);
		obj.setNroCotacao(0);
		obj.setBaseIssqn(0);
		obj.setBaseInss(0);
		obj.setBaseIrCsll(0);
		obj.setTipoDoDoc("");
		obj.setValorRetencao(0.00);
		obj.setCbFichFornec(1);
		obj.setCbNotaFornec(1);
		obj.setEstimado("P");

		if (obj.getHistorico() != null) {
			obj.setHistorico(obj.getHistorico().replace("'", "''"));
		}
		if (obj.getEmpresa() != null) {
			obj.setEmpresa(obj.getEmpresa().replace("'", "''"));
		}
		if (obj.getFavorecido() != null) {
			obj.setFavorecido(obj.getFavorecido().replace("'", "''"));
		}
		if (obj.getNf() != null) {
			obj.setNf(obj.getNf().replace("'", "''"));
		}

		boolean val = false;
		double idGlbDocto = 0;
		//int lancamentoSiga = this.returnUltimoLancto();

		obj.setUsuario(575225);

		if (!obj.getStatus().equals("A")) {

			try {
				if (obj.getTipoPagto().equals("8")) {

					if (obj.getEmissaoNf() != null) {

						obj.setDigAgeDest("");
						obj.setNomeAgencia("");
						obj.setAgencDestino(0);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[emissao_nf],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],"
								+ "[incide_tx_adm],[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[estimado],"
								+ "[modal_pagto],[agenc_destino],[conta_destino],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ new java.sql.Date(obj.getEmissaoNf().getTime()) + "','" + obj.getDigAgeDest() + "',"
								+ obj.getPercentual() + ",'" + obj.getCpmf() + "'," + obj.getMoeda() + ","
								+ obj.getCodCompensacao() + "," + obj.getCodPagto() + "," + obj.getMes() + ","
								+ obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros() + ","
								+ obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','" + obj.getReterImposto()
								+ "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "'," + obj.getValorLancto()
								+ "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao() + "','"
								+ obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId() + ","
								+ obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + ",'" + obj.getEstimado() + "','" + obj.getModalPagto() + "',"
								+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','" + obj.getTipoDoDoc()
								+ "'," + obj.getValorRetencao() + "," + obj.getCbFichFornec() + ","
								+ obj.getCbNotaFornec() + ")");
						query.executeUpdate();
						val = true;
					} else {
						obj.setDigAgeDest("");
						obj.setNomeAgencia("");
						obj.setAgencDestino(0);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
								+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[estimado],"
								+ "[modal_pagto],[agenc_destino],[conta_destino],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ obj.getDigAgeDest() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
								+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
								+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros()
								+ "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','"
								+ obj.getReterImposto() + "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "',"
								+ obj.getValorLancto() + "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao()
								+ "','" + obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId()
								+ "," + obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + ",'" + obj.getEstimado() + "','" + obj.getModalPagto() + "',"
								+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','" + obj.getTipoDoDoc()
								+ "'," + obj.getValorRetencao() + "," + obj.getCbFichFornec() + ","
								+ obj.getCbNotaFornec() + ")");
						query.executeUpdate();
						val = true;
					}
				} else if (obj.getTipoPagto().equals("E")) {
					if (obj.getEmissaoNf() != null) {

						obj.setDigAgeDest("");
						obj.setAgencDestino(0);
						obj.setNomeAgencia(null);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[emissao_nf],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],"
								+ "[incide_tx_adm],[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[cb_fich_fornec],"
								+ "[cb_nota_fornec],[estimado],[agenc_destino],[conta_destino],[modal_pagto],[tipo_do_doc],[valor_retencao])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ new java.sql.Date(obj.getEmissaoNf().getTime()) + "','" + obj.getDigAgeDest() + "',"
								+ obj.getPercentual() + ",'" + obj.getCpmf() + "'," + obj.getMoeda() + ","
								+ obj.getCodCompensacao() + "," + obj.getCodPagto() + "," + obj.getMes() + ","
								+ obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros() + ","
								+ obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','" + obj.getReterImposto()
								+ "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "'," + obj.getValorLancto()
								+ "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao() + "','"
								+ obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId() + ","
								+ obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ",'"
								+ obj.getEstimado() + "'," + obj.getAgencDestino() + ",'" + obj.getContaDestino()
								+ "','" + obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "',"
								+ obj.getValorRetencao() + ")");
						query.executeUpdate();
						val = true;
					} else {
						obj.setDigAgeDest("");
						obj.setAgencDestino(0);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
								+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[cb_fich_fornec],"
								+ "[cb_nota_fornec],[estimado],[agenc_destino],[conta_destino],[modal_pagto],[tipo_do_doc],[valor_retencao])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ obj.getDigAgeDest() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
								+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
								+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros()
								+ "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','"
								+ obj.getReterImposto() + "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "',"
								+ obj.getValorLancto() + "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao()
								+ "','" + obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId()
								+ "," + obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ",'"
								+ obj.getEstimado() + "'," + obj.getAgencDestino() + ",'" + obj.getContaDestino()
								+ "','" + obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "',"
								+ obj.getValorRetencao() + ")");
						query.executeUpdate();
						val = true;
					}
				} else {
					if (obj.getTipoPagto().equals("7")) {
						if (obj.getEmissaoNf() != null) {
							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[emissao_nf],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','" + obj.getTipoConta()
									+ "','" + new java.sql.Date(obj.getEmissaoNf().getTime()) + "','"
									+ obj.getEstimado() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
									+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
									+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + ","
									+ obj.getVrOutros() + "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup()
									+ "','" + obj.getReterImposto() + "'," + obj.getCodReceita() + ",'"
									+ obj.getDocumento() + "'," + obj.getValorLancto() + "," + obj.getIncideTxAdm()
									+ ",'" + obj.getAutorizacao() + "','" + obj.getContribuinte() + "',"
									+ obj.getDeclaracao() + "," + obj.getNroId() + "," + obj.getNroCotacao() + ",0,"
									+ obj.getBaseIssqn() + "," + obj.getBaseInss() + "," + obj.getBaseIrCsll() + ",'"
									+ obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "'," + obj.getValorRetencao()
									+ "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							if (obj.getCodigoFav() == 0) {
								int codigo;
								this.query = this.manager
										.createNativeQuery("select max(codigo) from [sip_teste].[dbo].[cpfavor]");
								codigo = Integer.valueOf(this.query.getResultList().get(0).toString());
								codigo += 1;

								this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cpfavor]"
										+ "([codigo],[favorecido],[tipo_pessoa],[cnpj_cpf],[banco],[agencia],[conta_corrente],[dac_conta],[conta_poupanca],[tipo_conta])"
										+ "VALUES(" + codigo + ",'" + obj.getFavorecido() + "','" + obj.getTipoPessoa()
										+ "'," + obj.getCnpj() + "," + obj.getBancoDestino() + ","
										+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','"
										+ obj.getDigAgeDest().trim() + "' ,'" + obj.getTipoContaBancaria().trim()
										+ "', null)");
								this.query.executeUpdate();
							}

							val = true;
						} else {

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','" + obj.getTipoConta()
									+ "','" + obj.getEstimado() + "'," + obj.getPercentual() + ",'" + obj.getCpmf()
									+ "'," + obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto()
									+ "," + obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + ","
									+ obj.getVrOutros() + "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup()
									+ "','" + obj.getReterImposto() + "'," + obj.getCodReceita() + ",'"
									+ obj.getDocumento() + "'," + obj.getValorLancto() + "," + obj.getIncideTxAdm()
									+ ",'" + obj.getAutorizacao() + "','" + obj.getContribuinte() + "',"
									+ obj.getDeclaracao() + "," + obj.getNroId() + "," + obj.getNroCotacao() + ",0,"
									+ obj.getBaseIssqn() + "," + obj.getBaseInss() + "," + obj.getBaseIrCsll() + ",'"
									+ obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "'," + obj.getValorRetencao()
									+ "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							val = true;
						}
					} else {

						if (obj.getEmissaoNf() != null) {
							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[emissao_nf],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
									+ new java.sql.Date(obj.getEmissaoNf().getTime()) + "','" + obj.getEstimado() + "',"
									+ obj.getPercentual() + ",'" + obj.getCpmf() + "'," + obj.getMoeda() + ","
									+ obj.getCodCompensacao() + "," + obj.getCodPagto() + "," + obj.getMes() + ","
									+ obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros() + ","
									+ obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','" + obj.getReterImposto()
									+ "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "',"
									+ obj.getValorLancto() + "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao()
									+ "','" + obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId()
									+ "," + obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss()
									+ "," + obj.getBaseIrCsll() + ",'" + obj.getModalPagto() + "','"
									+ obj.getTipoDoDoc() + "'," + obj.getValorRetencao() + "," + obj.getCbFichFornec()
									+ "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							val = true;
						} else {
							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
									+ obj.getEstimado() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
									+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
									+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + ","
									+ obj.getVrOutros() + "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup()
									+ "','" + obj.getReterImposto() + "'," + obj.getCodReceita() + ",'"
									+ obj.getDocumento() + "'," + obj.getValorLancto() + "," + obj.getIncideTxAdm()
									+ ",'" + obj.getAutorizacao() + "','" + obj.getContribuinte() + "',"
									+ obj.getDeclaracao() + "," + obj.getNroId() + "," + obj.getNroCotacao() + ",0,"
									+ obj.getBaseIssqn() + "," + obj.getBaseInss() + "," + obj.getBaseIrCsll() + ",'"
									+ obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "'," + obj.getValorRetencao()
									+ "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							val = true;
						}
					}
				}
			} catch (Exception e) {
				System.out.println(
						e.getMessage() + ' ' + e.getStackTrace().toString() + ' ' + e.getLocalizedMessage().toString());
			}
			if (obj.getImagens() != null) {

				Collection<financeiro_imagem> c = obj.getImagens();
				double valor = 0;

				for (financeiro_imagem f : c) {
					valor = f.getId();
					if (val == true) {
						if (this.verificaImg(valor) == false) {

							this.query = this.manager.createNativeQuery(
									"UPDATE [sip_teste].[dbo].[glbCatalogo] SET [id_tipoarquivo] = 10 ,[titulo] = null,[descricao] = null  ,[usuario_vinculo_arq] = 'igor',[data_vinculo_arq] = getdate() "
											+ ",[usuario_vinculo_lcto] = 'igor',[data_vinculo_lcto] = getdate() ,[sistema] = 85"
											+ " ,[sequencia] = null ,[auxiliar] = null WHERE id = " + valor);
							this.query.executeUpdate();

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[glbCatalogo_Docto]"
									+ "([id_catalogo],[nome_arquivo] ,[identificacao_tipo_arq],[sequencia])" + "VALUES("
									+ f.getId() + "  ,'" + f.getNome_arquivo() + "' ,'.PDF' ,null)");
							this.query.executeUpdate();

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[glbCatalogo_Lcto] "
									+ "([id_catalogo],[id_lcto],[tipo_lcto])" + "VALUES(" + f.getId() + ","
									+ obj.getNrolancto() + ", 'P')");
							this.query.executeUpdate();

							this.query = this.manager
									.createNativeQuery("SELECT max([id]) FROM [sip_teste].[dbo].[glbCatalogo_Docto]");
							List<BigDecimal> lista5 = query.getResultList();
							for (BigDecimal obj2 : lista5) {
								idGlbDocto = Double.valueOf(String.valueOf(obj2));
							}

							String sql = "INSERT INTO [sigadm_documentos].[dbo].[glbdocumento] ([id], [obj])values(?,?)";

							try {
								this.con = Conexao.getConexaoSigaDocumentos();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} catch (SQLException e) {
								e.printStackTrace();
							}
							try {
								this.pmst = con.prepareStatement(sql);
								this.pmst.setDouble(1, idGlbDocto);
								this.pmst.setBytes(2, f.getImagem());
								//this.pmst.executeUpdate();
								this.pmst.close();
								this.con.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[glbCatalogo_Lcto] "
									+ "([id_catalogo],[id_lcto],[tipo_lcto])" + "VALUES(" + f.getId() + ","
									+ obj.getNrolancto() + ", 'P')");
							this.query.executeUpdate();
						}
					}
				}

			}

			if (val == true) {
				char obs = 'N';
				if (obsLancto != null && !obsLancto.isEmpty()) {
					obs = 'S';
				}
				this.query = this.manager
						.createNativeQuery("update omaonline.dbo.cndpagar set status_ = 'A', nrolancto = "
								+ obj.getNrolancto() + ", obs = '" + obs + "' where codigo = " + obj.getCodigo());
				this.query.executeUpdate();

				confirmacao = "A";
			} else {
				confirmacao = "R";
			}
		}

		// Registrar LOG
		this.registrarFollowUp(obj.getCodigo(), "Aprovado", email, ip, obj.getCondominio(), obsLancto);

		this.manager.detach(obj);
		obj.setCodigo(0);
		return confirmacao;
	}

	public int returnUltimoLancto() {
		int nrolancto = 0;
		String sql = "INSERT INTO [sip_teste].[dbo].sglctoscontas(origem)values(1)";
		try {
			rs = null;
			con = Conexao.getConexaoSIP();
			pmst = con.prepareStatement(sql);
			pmst.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = pmst.getGeneratedKeys();
			while (rs.next()) {
				nrolancto = rs.getInt(1);
			}
			pmst.close();
			con.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nrolancto;
	}

	@SuppressWarnings("unchecked")
	public String adicionarLanctoSiga(cndpagar obj, String email, String ip, String obsLancto) {
		String confirmacao = null;
		obj.setBloco("0");
		obj.setPercentual(0.00);
		obj.setCpmf("N");
		obj.setMoeda((short) 0);
		obj.setNomeAgencia("");
		obj.setCodCompensacao((short) 0);
		obj.setCodPagto((short) 0);
		obj.setMes(0);
		obj.setAno((short) 0);
		obj.setVrTributo(0.00);
		obj.setVrOutros(0.00);
		obj.setVrAtualMonet(0.00);
		obj.setPgCredpoup("N");
		obj.setReterImposto("N");
		obj.setCodReceita((short) 0);
		obj.setDocumento("");
		obj.setValorNf(obj.getValor());
		obj.setIncideTxAdm(0);
		obj.setAutorizacao("");
		obj.setContribuinte("");
		obj.setDeclaracao(0);
		obj.setNroId(0);
		obj.setNroCotacao(0);
		obj.setBaseIssqn(0);
		obj.setBaseInss(0);
		obj.setBaseIrCsll(0);
		obj.setTipoDoDoc("");
		obj.setValorRetencao(0.00);
		obj.setCbFichFornec(1);
		obj.setCbNotaFornec(1);
		obj.setEstimado("P");
		
		if (obj.getHistorico() != null) {
			obj.setHistorico(obj.getHistorico().replace("'", "''"));
		}
		if (obj.getEmpresa() != null) {
			obj.setEmpresa(obj.getEmpresa().replace("'", "''"));
		}
		if (obj.getFavorecido() != null) {
			obj.setFavorecido(obj.getFavorecido().replace("'", "''"));
		}
		if (obj.getNf() != null) {
			obj.setNf(obj.getNf().replace("'", "''"));
		}

		boolean val = false;
		double idGlbDocto = 0;
		//int lancamentoSiga = this.returnUltimoLancto();

		obj.setUsuario(575225);

		if (!obj.getStatus().equals("A")) {

			try {
				if (obj.getTipoPagto().equals("8")) {

					if (obj.getEmissaoNf() != null) {

						obj.setDigAgeDest("");
						obj.setNomeAgencia("");
						obj.setAgencDestino(0);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[emissao_nf],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],"
								+ "[incide_tx_adm],[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[estimado],"
								+ "[modal_pagto],[agenc_destino],[conta_destino],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ new java.sql.Date(obj.getEmissaoNf().getTime()) + "','" + obj.getDigAgeDest() + "',"
								+ obj.getPercentual() + ",'" + obj.getCpmf() + "'," + obj.getMoeda() + ","
								+ obj.getCodCompensacao() + "," + obj.getCodPagto() + "," + obj.getMes() + ","
								+ obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros() + ","
								+ obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','" + obj.getReterImposto()
								+ "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "'," + obj.getValorLancto()
								+ "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao() + "','"
								+ obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId() + ","
								+ obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + ",'" + obj.getEstimado() + "','" + obj.getModalPagto() + "',"
								+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','" + obj.getTipoDoDoc()
								+ "'," + obj.getValorRetencao() + "," + obj.getCbFichFornec() + ","
								+ obj.getCbNotaFornec() + ")");
						query.executeUpdate();
						val = true;
					} else {
						obj.setDigAgeDest("");
						obj.setNomeAgencia("");
						obj.setAgencDestino(0);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
								+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[estimado],"
								+ "[modal_pagto],[agenc_destino],[conta_destino],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ obj.getDigAgeDest() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
								+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
								+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros()
								+ "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','"
								+ obj.getReterImposto() + "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "',"
								+ obj.getValorLancto() + "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao()
								+ "','" + obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId()
								+ "," + obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + ",'" + obj.getEstimado() + "','" + obj.getModalPagto() + "',"
								+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','" + obj.getTipoDoDoc()
								+ "'," + obj.getValorRetencao() + "," + obj.getCbFichFornec() + ","
								+ obj.getCbNotaFornec() + ")");
						query.executeUpdate();
						val = true;
					}
				} else if (obj.getTipoPagto().equals("E")) {
					if (obj.getEmissaoNf() != null) {

						obj.setDigAgeDest("");
						obj.setAgencDestino(0);
						obj.setNomeAgencia(null);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[emissao_nf],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],"
								+ "[incide_tx_adm],[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[cb_fich_fornec],"
								+ "[cb_nota_fornec],[estimado],[agenc_destino],[conta_destino],[modal_pagto],[tipo_do_doc],[valor_retencao])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ new java.sql.Date(obj.getEmissaoNf().getTime()) + "','" + obj.getDigAgeDest() + "',"
								+ obj.getPercentual() + ",'" + obj.getCpmf() + "'," + obj.getMoeda() + ","
								+ obj.getCodCompensacao() + "," + obj.getCodPagto() + "," + obj.getMes() + ","
								+ obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros() + ","
								+ obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','" + obj.getReterImposto()
								+ "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "'," + obj.getValorLancto()
								+ "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao() + "','"
								+ obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId() + ","
								+ obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ",'"
								+ obj.getEstimado() + "'," + obj.getAgencDestino() + ",'" + obj.getContaDestino()
								+ "','" + obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "',"
								+ obj.getValorRetencao() + ")");
						query.executeUpdate();
						val = true;
					} else {
						obj.setDigAgeDest("");
						obj.setAgencDestino(0);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
								+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[cb_fich_fornec],"
								+ "[cb_nota_fornec],[estimado],[agenc_destino],[conta_destino],[modal_pagto],[tipo_do_doc],[valor_retencao])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ obj.getDigAgeDest() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
								+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
								+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros()
								+ "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','"
								+ obj.getReterImposto() + "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "',"
								+ obj.getValorLancto() + "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao()
								+ "','" + obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId()
								+ "," + obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ",'"
								+ obj.getEstimado() + "'," + obj.getAgencDestino() + ",'" + obj.getContaDestino()
								+ "','" + obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "',"
								+ obj.getValorRetencao() + ")");
						query.executeUpdate();
						val = true;
					}
				} else {
					if (obj.getTipoPagto().equals("7")) {
						if (obj.getEmissaoNf() != null) {
							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[emissao_nf],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','" + obj.getTipoConta()
									+ "','" + new java.sql.Date(obj.getEmissaoNf().getTime()) + "','"
									+ obj.getEstimado() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
									+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
									+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + ","
									+ obj.getVrOutros() + "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup()
									+ "','" + obj.getReterImposto() + "'," + obj.getCodReceita() + ",'"
									+ obj.getDocumento() + "'," + obj.getValorLancto() + "," + obj.getIncideTxAdm()
									+ ",'" + obj.getAutorizacao() + "','" + obj.getContribuinte() + "',"
									+ obj.getDeclaracao() + "," + obj.getNroId() + "," + obj.getNroCotacao() + ",0,"
									+ obj.getBaseIssqn() + "," + obj.getBaseInss() + "," + obj.getBaseIrCsll() + ",'"
									+ obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "'," + obj.getValorRetencao()
									+ "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							if (obj.getCodigoFav() == 0) {
								int codigo;
								this.query = this.manager
										.createNativeQuery("select max(codigo) from [sip_teste].[dbo].[cpfavor]");
								codigo = Integer.valueOf(this.query.getResultList().get(0).toString());
								codigo += 1;

								this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cpfavor]"
										+ "([codigo],[favorecido],[tipo_pessoa],[cnpj_cpf],[banco],[agencia],[conta_corrente],[dac_conta],[conta_poupanca],[tipo_conta])"
										+ "VALUES(" + codigo + ",'" + obj.getFavorecido() + "','" + obj.getTipoPessoa()
										+ "'," + obj.getCnpj() + "," + obj.getBancoDestino() + ","
										+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','"
										+ obj.getDigAgeDest().trim() + "' ,'" + obj.getTipoContaBancaria().trim()
										+ "',null)");
								this.query.executeUpdate();
							}

							val = true;
						} else {

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','" + obj.getTipoConta()
									+ "','" + obj.getEstimado() + "'," + obj.getPercentual() + ",'" + obj.getCpmf()
									+ "'," + obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto()
									+ "," + obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + ","
									+ obj.getVrOutros() + "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup()
									+ "','" + obj.getReterImposto() + "'," + obj.getCodReceita() + ",'"
									+ obj.getDocumento() + "'," + obj.getValorLancto() + "," + obj.getIncideTxAdm()
									+ ",'" + obj.getAutorizacao() + "','" + obj.getContribuinte() + "',"
									+ obj.getDeclaracao() + "," + obj.getNroId() + "," + obj.getNroCotacao() + ",0,"
									+ obj.getBaseIssqn() + "," + obj.getBaseInss() + "," + obj.getBaseIrCsll() + ",'"
									+ obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "'," + obj.getValorRetencao()
									+ "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							if (obj.getCodigoFav() == 0) {
								int codigo;
								this.query = this.manager
										.createNativeQuery("select max(codigo) from [sip_teste].[dbo].[cpfavor]");
								codigo = Integer.valueOf(this.query.getResultList().get(0).toString());
								codigo += 1;

								this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cpfavor]"
										+ "([codigo],[favorecido],[tipo_pessoa],[cnpj_cpf],[banco],[agencia],[conta_corrente],[dac_conta],[conta_poupanca],[tipo_conta])"
										+ "VALUES(" + codigo + ",'" + obj.getFavorecido() + "','" + obj.getTipoPessoa()
										+ "'," + obj.getCnpj() + "," + obj.getBancoDestino() + ","
										+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','"
										+ obj.getDigAgeDest().trim() + "' ,'" + obj.getTipoContaBancaria().trim()
										+ "', null)");
								this.query.executeUpdate();
							}

							val = true;
						}
					} else {

						if (obj.getEmissaoNf() != null) {

							obj.setDigAgeDest("");
							obj.setNomeAgencia("");
							obj.setAgencDestino(0);
							obj.setContaDestino("");
							obj.setModalPagto("");

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[emissao_nf],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
									+ new java.sql.Date(obj.getEmissaoNf().getTime()) + "','" + obj.getEstimado() + "',"
									+ obj.getPercentual() + ",'" + obj.getCpmf() + "'," + obj.getMoeda() + ","
									+ obj.getCodCompensacao() + "," + obj.getCodPagto() + "," + obj.getMes() + ","
									+ obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros() + ","
									+ obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','" + obj.getReterImposto()
									+ "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "',"
									+ obj.getValorLancto() + "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao()
									+ "','" + obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId()
									+ "," + obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss()
									+ "," + obj.getBaseIrCsll() + ",'" + obj.getModalPagto() + "','"
									+ obj.getTipoDoDoc() + "'," + obj.getValorRetencao() + "," + obj.getCbFichFornec()
									+ "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							val = true;
						} else {

							obj.setDigAgeDest("");
							obj.setNomeAgencia("");
							obj.setAgencDestino(0);
							obj.setContaDestino("");
							obj.setModalPagto("");

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
									+ obj.getEstimado() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
									+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
									+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + ","
									+ obj.getVrOutros() + "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup()
									+ "','" + obj.getReterImposto() + "'," + obj.getCodReceita() + ",'"
									+ obj.getDocumento() + "'," + obj.getValorLancto() + "," + obj.getIncideTxAdm()
									+ ",'" + obj.getAutorizacao() + "','" + obj.getContribuinte() + "',"
									+ obj.getDeclaracao() + "," + obj.getNroId() + "," + obj.getNroCotacao() + ",0,"
									+ obj.getBaseIssqn() + "," + obj.getBaseInss() + "," + obj.getBaseIrCsll() + ",'"
									+ obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "'," + obj.getValorRetencao()
									+ "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							val = true;
						}
					}
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
			if (obj.getImagens() != null) {
				Collection<financeiro_imagem> c = obj.getImagens();
				double valor = 0;

				for (financeiro_imagem f : c) {
					valor = f.getId();
					if (val == true) {
						if (this.verificaImg(valor) == false) {
							this.query = this.manager.createNativeQuery(
									"UPDATE [sip_teste].[dbo].[glbCatalogo] SET [id_tipoarquivo] = 10 ,[titulo] = null,[descricao] = null  ,[usuario_vinculo_arq] = 'igor',[data_vinculo_arq] = getdate() "
											+ ",[usuario_vinculo_lcto] = 'igor',[data_vinculo_lcto] = getdate() ,[sistema] = 85"
											+ " ,[sequencia] = null ,[auxiliar] = null WHERE id = " + valor);
							this.query.executeUpdate();

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[glbCatalogo_Docto]"
									+ "([id_catalogo],[nome_arquivo] ,[identificacao_tipo_arq],[sequencia])" + "VALUES("
									+ f.getId() + "  ,'" + f.getNome_arquivo() + "' ,'.PDF' ,null)");
							this.query.executeUpdate();

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[glbCatalogo_Lcto] "
									+ "([id_catalogo],[id_lcto],[tipo_lcto])" + "VALUES(" + f.getId() + ","
									+ obj.getNrolancto() + ", 'P')");
							this.query.executeUpdate();

							this.query = this.manager
									.createNativeQuery("SELECT max([id]) FROM [sip_tese].[dbo].[glbCatalogo_Docto]");
							List<BigDecimal> lista5 = query.getResultList();
							for (BigDecimal obj2 : lista5) {
								idGlbDocto = Double.valueOf(String.valueOf(obj2));
							}

							String sql = "INSERT INTO [sigadm_documentos].[dbo].[glbdocumento] ([id], [obj])values(?,?)";

							try {
								this.con = Conexao.getConexaoSigaDocumentos();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								this.pmst = con.prepareStatement(sql);
								this.pmst.setDouble(1, idGlbDocto);
								this.pmst.setBytes(2, f.getImagem());
								//this.pmst.executeUpdate();
								this.pmst.close();
								this.con.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[glbCatalogo_Lcto] "
									+ "([id_catalogo],[id_lcto],[tipo_lcto])" + "VALUES(" + f.getId() + ","
									+ obj.getNrolancto() + ", 'P')");
							this.query.executeUpdate();
						}
					}
				}
			}

			if (val == true) {
				if (obsLancto != null) {
					this.query = this.manager.createQuery("update cndpagar set status_ = 'A', nrolancto = "
							+ obj.getNrolancto() + ", obs = 'S' where codigo = :p1");
					this.query.setParameter("p1", obj.getCodigo()).executeUpdate();
					confirmacao = "A";
				} else {
					this.query = this.manager.createQuery("update cndpagar set status_ = 'A', nrolancto = "
							+ obj.getNrolancto() + ", obs = 'N' where codigo = :p1");
					this.query.setParameter("p1", obj.getCodigo()).executeUpdate();
					confirmacao = "A";
				}
			} else {
				confirmacao = "R";
			}
		}
		// Registrar LOG
		//this.registrarFollowUp(obj.getCodigo(), "Aprovado", email, ip, obj.getCondominio(), obsLancto);

		return confirmacao;
	}
	
	public String adicionarLanctoSigaLiberar(cndpagar obj, String email, String ip, String obsLancto) {
		String confirmacao = null;
		obj.setBloco("0");
		obj.setPercentual(0.00);
		obj.setCpmf("N");
		obj.setMoeda((short) 0);
		obj.setNomeAgencia("");
		obj.setCodCompensacao((short) 0);
		obj.setCodPagto((short) 0);
		obj.setMes(0);
		obj.setAno((short) 0);
		obj.setVrTributo(0.00);
		obj.setVrOutros(0.00);
		obj.setVrAtualMonet(0.00);
		obj.setPgCredpoup("N");
		obj.setReterImposto("N");
		obj.setCodReceita((short) 0);
		obj.setDocumento("");
		obj.setValorNf(obj.getValor());
		obj.setIncideTxAdm(0);
		obj.setAutorizacao("");
		obj.setContribuinte("");
		obj.setDeclaracao(0);
		obj.setNroId(0);
		obj.setNroCotacao(0);
		obj.setBaseIssqn(0);
		obj.setBaseInss(0);
		obj.setBaseIrCsll(0);
		obj.setTipoDoDoc("");
		obj.setValorRetencao(0.00);
		obj.setCbFichFornec(1);
		obj.setCbNotaFornec(1);
		obj.setEstimado("P");
		
		if (obj.getHistorico() != null) {
			obj.setHistorico(obj.getHistorico().replace("'", "''"));
		}
		if (obj.getEmpresa() != null) {
			obj.setEmpresa(obj.getEmpresa().replace("'", "''"));
		}
		if (obj.getFavorecido() != null) {
			obj.setFavorecido(obj.getFavorecido().replace("'", "''"));
		}
		if (obj.getNf() != null) {
			obj.setNf(obj.getNf().replace("'", "''"));
		}

		boolean val = false;
		double idGlbDocto = 0;
		//int lancamentoSiga = this.returnUltimoLancto();

		obj.setUsuario(575225);

		if (!obj.getStatus().equals("A")) {

			try {
				if (obj.getTipoPagto().equals("8")) {

					if (obj.getEmissaoNf() != null) {

						obj.setDigAgeDest("");
						obj.setNomeAgencia("");
						obj.setAgencDestino(0);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[emissao_nf],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],"
								+ "[incide_tx_adm],[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[estimado],"
								+ "[modal_pagto],[agenc_destino],[conta_destino],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ new java.sql.Date(obj.getEmissaoNf().getTime()) + "','" + obj.getDigAgeDest() + "',"
								+ obj.getPercentual() + ",'" + obj.getCpmf() + "'," + obj.getMoeda() + ","
								+ obj.getCodCompensacao() + "," + obj.getCodPagto() + "," + obj.getMes() + ","
								+ obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros() + ","
								+ obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','" + obj.getReterImposto()
								+ "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "'," + obj.getValorLancto()
								+ "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao() + "','"
								+ obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId() + ","
								+ obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + ",'" + obj.getEstimado() + "','" + obj.getModalPagto() + "',"
								+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','" + obj.getTipoDoDoc()
								+ "'," + obj.getValorRetencao() + "," + obj.getCbFichFornec() + ","
								+ obj.getCbNotaFornec() + ")");
						query.executeUpdate();
						val = true;
					} else {
						obj.setDigAgeDest("");
						obj.setNomeAgencia("");
						obj.setAgencDestino(0);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
								+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[estimado],"
								+ "[modal_pagto],[agenc_destino],[conta_destino],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ obj.getDigAgeDest() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
								+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
								+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros()
								+ "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','"
								+ obj.getReterImposto() + "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "',"
								+ obj.getValorLancto() + "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao()
								+ "','" + obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId()
								+ "," + obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + ",'" + obj.getEstimado() + "','" + obj.getModalPagto() + "',"
								+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','" + obj.getTipoDoDoc()
								+ "'," + obj.getValorRetencao() + "," + obj.getCbFichFornec() + ","
								+ obj.getCbNotaFornec() + ")");
						query.executeUpdate();
						val = true;
					}
				} else if (obj.getTipoPagto().equals("E")) {
					if (obj.getEmissaoNf() != null) {

						obj.setDigAgeDest("");
						obj.setAgencDestino(0);
						obj.setNomeAgencia(null);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[emissao_nf],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],"
								+ "[incide_tx_adm],[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[cb_fich_fornec],"
								+ "[cb_nota_fornec],[estimado],[agenc_destino],[conta_destino],[modal_pagto],[tipo_do_doc],[valor_retencao])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ new java.sql.Date(obj.getEmissaoNf().getTime()) + "','" + obj.getDigAgeDest() + "',"
								+ obj.getPercentual() + ",'" + obj.getCpmf() + "'," + obj.getMoeda() + ","
								+ obj.getCodCompensacao() + "," + obj.getCodPagto() + "," + obj.getMes() + ","
								+ obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros() + ","
								+ obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','" + obj.getReterImposto()
								+ "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "'," + obj.getValorLancto()
								+ "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao() + "','"
								+ obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId() + ","
								+ obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ",'"
								+ obj.getEstimado() + "'," + obj.getAgencDestino() + ",'" + obj.getContaDestino()
								+ "','" + obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "',"
								+ obj.getValorRetencao() + ")");
						query.executeUpdate();
						val = true;
					} else {
						obj.setDigAgeDest("");
						obj.setAgencDestino(0);
						obj.setContaDestino("");
						obj.setModalPagto("");

						this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
								+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
								+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
								+ "[nota_fiscal],[banco_destino],"
								+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
								+ "[ld_valor],[codigo_fav],[cta_anl_financ],"
								+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
								+ "[tipo_pessoa],[codigo_barra],"
								+ "[tipo_conta],[dig_age_dest],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
								+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
								+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],[cb_fich_fornec],"
								+ "[cb_nota_fornec],[estimado],[agenc_destino],[conta_destino],[modal_pagto],[tipo_do_doc],[valor_retencao])"
								+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco() + "',"
								+ obj.getConta() + ",'" + obj.getHistorico() + "','"
								+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
								+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido() + "','"
								+ obj.getTipoPagto() + "'," + obj.getContaBancaria() + "," + obj.getNotaFiscal() + ","
								+ obj.getBancoDestino() + "," + obj.getLdCampo1() + "," + obj.getLdCampo2() + ","
								+ obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
								+ obj.getNomeAgencia() + "'," + obj.getLdValor() + "," + obj.getCodigoFav() + ","
								+ obj.getCtaAnlFinanc() + "," + obj.getCnpj() + "," + obj.getConcSegbarra1() + ","
								+ obj.getConcSegbarra2() + "," + obj.getConcSegbarra3() + "," + obj.getConcSegbarra4()
								+ ",getdate(),'" + obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
								+ obj.getDigAgeDest() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
								+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
								+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros()
								+ "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','"
								+ obj.getReterImposto() + "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "',"
								+ obj.getValorLancto() + "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao()
								+ "','" + obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId()
								+ "," + obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss() + ","
								+ obj.getBaseIrCsll() + "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ",'"
								+ obj.getEstimado() + "'," + obj.getAgencDestino() + ",'" + obj.getContaDestino()
								+ "','" + obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "',"
								+ obj.getValorRetencao() + ")");
						query.executeUpdate();
						val = true;
					}
				} else {
					if (obj.getTipoPagto().equals("7")) {
						if (obj.getEmissaoNf() != null) {
							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[emissao_nf],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','" + obj.getTipoConta()
									+ "','" + new java.sql.Date(obj.getEmissaoNf().getTime()) + "','"
									+ obj.getEstimado() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
									+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
									+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + ","
									+ obj.getVrOutros() + "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup()
									+ "','" + obj.getReterImposto() + "'," + obj.getCodReceita() + ",'"
									+ obj.getDocumento() + "'," + obj.getValorLancto() + "," + obj.getIncideTxAdm()
									+ ",'" + obj.getAutorizacao() + "','" + obj.getContribuinte() + "',"
									+ obj.getDeclaracao() + "," + obj.getNroId() + "," + obj.getNroCotacao() + ",0,"
									+ obj.getBaseIssqn() + "," + obj.getBaseInss() + "," + obj.getBaseIrCsll() + ",'"
									+ obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "'," + obj.getValorRetencao()
									+ "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							if (obj.getCodigoFav() == 0) {
								int codigo;
								this.query = this.manager
										.createNativeQuery("select max(codigo) from [sip_teste].[dbo].[cpfavor]");
								codigo = Integer.valueOf(this.query.getResultList().get(0).toString());
								codigo += 1;

								this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cpfavor]"
										+ "([codigo],[favorecido],[tipo_pessoa],[cnpj_cpf],[banco],[agencia],[conta_corrente],[dac_conta],[conta_poupanca],[tipo_conta])"
										+ "VALUES(" + codigo + ",'" + obj.getFavorecido() + "','" + obj.getTipoPessoa()
										+ "'," + obj.getCnpj() + "," + obj.getBancoDestino() + ","
										+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','"
										+ obj.getDigAgeDest().trim() + "' ,'" + obj.getTipoContaBancaria().trim()
										+ "',null)");
								this.query.executeUpdate();
							}

							val = true;
						} else {

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','" + obj.getTipoConta()
									+ "','" + obj.getEstimado() + "'," + obj.getPercentual() + ",'" + obj.getCpmf()
									+ "'," + obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto()
									+ "," + obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + ","
									+ obj.getVrOutros() + "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup()
									+ "','" + obj.getReterImposto() + "'," + obj.getCodReceita() + ",'"
									+ obj.getDocumento() + "'," + obj.getValorLancto() + "," + obj.getIncideTxAdm()
									+ ",'" + obj.getAutorizacao() + "','" + obj.getContribuinte() + "',"
									+ obj.getDeclaracao() + "," + obj.getNroId() + "," + obj.getNroCotacao() + ",0,"
									+ obj.getBaseIssqn() + "," + obj.getBaseInss() + "," + obj.getBaseIrCsll() + ",'"
									+ obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "'," + obj.getValorRetencao()
									+ "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							if (obj.getCodigoFav() == 0) {
								int codigo;
								this.query = this.manager
										.createNativeQuery("select max(codigo) from [sip_teste].[dbo].[cpfavor]");
								codigo = Integer.valueOf(this.query.getResultList().get(0).toString());
								codigo += 1;

								this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cpfavor]"
										+ "([codigo],[favorecido],[tipo_pessoa],[cnpj_cpf],[banco],[agencia],[conta_corrente],[dac_conta],[conta_poupanca],[tipo_conta])"
										+ "VALUES(" + codigo + ",'" + obj.getFavorecido() + "','" + obj.getTipoPessoa()
										+ "'," + obj.getCnpj() + "," + obj.getBancoDestino() + ","
										+ obj.getAgencDestino() + ",'" + obj.getContaDestino() + "','"
										+ obj.getDigAgeDest().trim() + "' ,'" + obj.getTipoContaBancaria().trim()
										+ "', null)");
								this.query.executeUpdate();
							}

							val = true;
						}
					} else {

						if (obj.getEmissaoNf() != null) {

							obj.setDigAgeDest("");
							obj.setNomeAgencia("");
							obj.setAgencDestino(0);
							obj.setContaDestino("");
							obj.setModalPagto("");

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[emissao_nf],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
									+ new java.sql.Date(obj.getEmissaoNf().getTime()) + "','" + obj.getEstimado() + "',"
									+ obj.getPercentual() + ",'" + obj.getCpmf() + "'," + obj.getMoeda() + ","
									+ obj.getCodCompensacao() + "," + obj.getCodPagto() + "," + obj.getMes() + ","
									+ obj.getAno() + "," + obj.getVrTributo() + "," + obj.getVrOutros() + ","
									+ obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup() + "','" + obj.getReterImposto()
									+ "'," + obj.getCodReceita() + ",'" + obj.getDocumento() + "',"
									+ obj.getValorLancto() + "," + obj.getIncideTxAdm() + ",'" + obj.getAutorizacao()
									+ "','" + obj.getContribuinte() + "'," + obj.getDeclaracao() + "," + obj.getNroId()
									+ "," + obj.getNroCotacao() + ",0," + obj.getBaseIssqn() + "," + obj.getBaseInss()
									+ "," + obj.getBaseIrCsll() + ",'" + obj.getModalPagto() + "','"
									+ obj.getTipoDoDoc() + "'," + obj.getValorRetencao() + "," + obj.getCbFichFornec()
									+ "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							val = true;
						} else {

							obj.setDigAgeDest("");
							obj.setNomeAgencia("");
							obj.setAgencDestino(0);
							obj.setContaDestino("");
							obj.setModalPagto("");

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[cndpagar]"
									+ "([nrolancto],[condominio],[bloco],[conta],[historico],[credor],[valor],"
									+ "[vencimento],[favorecido],[tipopagto],[conta_bancaria],"
									+ "[nota_fiscal],[banco_destino],[agenc_destino],[conta_destino],"
									+ "[ld_campo_1],[ld_campo_2],[ld_campo_3],[ld_dac],[usuario],[nome_agencia],"
									+ "[ld_valor],[dig_age_dest],[codigo_fav],[cta_anl_financ],"
									+ "[cnpj],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],[conc_segbarra4],[data_inclusao],"
									+ "[tipo_pessoa],[codigo_barra],"
									+ "[tipo_conta],[estimado],[percentual],[cpmf],[moeda],[cod_compensacao],[cod_pagto],[mes],[ano],"
									+ "[vr_tributo],[vr_outros],[vr_atual_monet],[pg_credpoup],[reter_imposto],[cod_receita],[documento],[valor_lancto],[incide_tx_adm],"
									+ "[autorizacao],[contribuinte],[declaracao],[nro_id],[nro_cotacao],[local_serv],[base_issqn],[base_inss],[base_ir_csll],"
									+ "[modal_pagto],[tipo_do_doc],[valor_retencao],[cb_fich_fornec],[cb_nota_fornec])"
									+ "VALUES(" + obj.getNrolancto() + "," + obj.getCondominio() + ",'" + obj.getBloco()
									+ "'," + obj.getConta() + ",'" + obj.getHistorico() + "','"
									+ obj.getCredor().replace("'", "''") + "'," + obj.getValor() + ",'"
									+ new java.sql.Date(obj.getVencimento().getTime()) + "','" + obj.getFavorecido()
									+ "','" + obj.getTipoPagto() + "'," + obj.getContaBancaria() + ","
									+ obj.getNotaFiscal() + "," + obj.getBancoDestino() + "," + obj.getAgencDestino()
									+ ",'" + obj.getContaDestino() + "'," + obj.getLdCampo1() + "," + obj.getLdCampo2()
									+ "," + obj.getLdCampo3() + "," + obj.getLdDac() + "," + obj.getUsuario() + ",'"
									+ obj.getNomeAgencia() + "'," + obj.getLdValor() + ",'" + obj.getDigAgeDest() + "',"
									+ obj.getCodigoFav() + "," + obj.getCtaAnlFinanc() + "," + obj.getCnpj() + ","
									+ obj.getConcSegbarra1() + "," + obj.getConcSegbarra2() + ","
									+ obj.getConcSegbarra3() + "," + obj.getConcSegbarra4() + ",getdate(),'"
									+ obj.getTipoPessoa() + "','" + obj.getCodigoBarra() + "','01','"
									+ obj.getEstimado() + "'," + obj.getPercentual() + ",'" + obj.getCpmf() + "',"
									+ obj.getMoeda() + "," + obj.getCodCompensacao() + "," + obj.getCodPagto() + ","
									+ obj.getMes() + "," + obj.getAno() + "," + obj.getVrTributo() + ","
									+ obj.getVrOutros() + "," + obj.getVrAtualMonet() + ",'" + obj.getPgCredpoup()
									+ "','" + obj.getReterImposto() + "'," + obj.getCodReceita() + ",'"
									+ obj.getDocumento() + "'," + obj.getValorLancto() + "," + obj.getIncideTxAdm()
									+ ",'" + obj.getAutorizacao() + "','" + obj.getContribuinte() + "',"
									+ obj.getDeclaracao() + "," + obj.getNroId() + "," + obj.getNroCotacao() + ",0,"
									+ obj.getBaseIssqn() + "," + obj.getBaseInss() + "," + obj.getBaseIrCsll() + ",'"
									+ obj.getModalPagto() + "','" + obj.getTipoDoDoc() + "'," + obj.getValorRetencao()
									+ "," + obj.getCbFichFornec() + "," + obj.getCbNotaFornec() + ")");
							query.executeUpdate();

							val = true;
						}
					}
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
			/*if (obj.getImagens() != null) {
				Collection<financeiro_imagem> c = obj.getImagens();
				double valor = 0;

				for (financeiro_imagem f : c) {
					valor = f.getId();
					if (val == true) {
						if (this.verificaImg(valor) == false) {
							this.query = this.manager.createNativeQuery(
									"UPDATE [sip_teste].[dbo].[glbCatalogo] SET [id_tipoarquivo] = 10 ,[titulo] = null,[descricao] = null  ,[usuario_vinculo_arq] = 'igor',[data_vinculo_arq] = getdate() "
											+ ",[usuario_vinculo_lcto] = 'igor',[data_vinculo_lcto] = getdate() ,[sistema] = 85"
											+ " ,[sequencia] = null ,[auxiliar] = null WHERE id = " + valor);
							this.query.executeUpdate();

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[glbCatalogo_Docto]"
									+ "([id_catalogo],[nome_arquivo] ,[identificacao_tipo_arq],[sequencia])" + "VALUES("
									+ f.getId() + "  ,'" + f.getNome_arquivo() + "' ,'.PDF' ,null)");
							this.query.executeUpdate();

							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[glbCatalogo_Lcto] "
									+ "([id_catalogo],[id_lcto],[tipo_lcto])" + "VALUES(" + f.getId() + ","
									+ obj.getNrolancto() + ", 'P')");
							this.query.executeUpdate();

							this.query = this.manager
									.createNativeQuery("SELECT max([id]) FROM [sip_tese].[dbo].[glbCatalogo_Docto]");
							List<BigDecimal> lista5 = query.getResultList();
							for (BigDecimal obj2 : lista5) {
								idGlbDocto = Double.valueOf(String.valueOf(obj2));
							}

							String sql = "INSERT INTO [sigadm_documentos].[dbo].[glbdocumento] ([id], [obj])values(?,?)";

							try {
								this.con = Conexao.getConexaoSigaDocumentos();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								this.pmst = con.prepareStatement(sql);
								this.pmst.setDouble(1, idGlbDocto);
								this.pmst.setBytes(2, f.getImagem());
								//this.pmst.executeUpdate();
								this.pmst.close();
								this.con.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							this.query = this.manager.createNativeQuery("INSERT INTO [sip_teste].[dbo].[glbCatalogo_Lcto] "
									+ "([id_catalogo],[id_lcto],[tipo_lcto])" + "VALUES(" + f.getId() + ","
									+ obj.getNrolancto() + ", 'P')");
							this.query.executeUpdate();
						}
					}
				}
			}*/

		/*	if (val == true) {
				if (obsLancto != null) {
					this.query = this.manager.createQuery("update cndpagar set status_ = 'A', nrolancto = "
							+ obj.getNrolancto() + ", obs = 'S' where codigo = :p1");
					this.query.setParameter("p1", obj.getCodigo()).executeUpdate();
					confirmacao = "A";
				} else {
					this.query = this.manager.createQuery("update cndpagar set status_ = 'A', nrolancto = "
							+ obj.getNrolancto() + ", obs = 'N' where codigo = :p1");
					this.query.setParameter("p1", obj.getCodigo()).executeUpdate();
					confirmacao = "A";
				}
			} else {
				confirmacao = "R";
			}*/
		}
		// Registrar LOG
		//this.registrarFollowUp(obj.getCodigo(), "Aprovado", email, ip, obj.getCondominio(), obsLancto);

		return confirmacao;
	}


	/**
	 * metodo utilizado para listar o plano de contas o prametro que recebe é o
	 * codigo reduzido
	 * 
	 * @param codigo
	 * @param s
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<cndplano> listarPlanoConta(int codigoReduzido, short codigoCnd) {
		List<cndplano> lpconta = new ArrayList<cndplano>();
		if (codigoCnd == 4241) {
			codigoReduzido += 100000;
			this.query = this.manager.createNativeQuery(
					"select  cod_reduzido, codigo_grafico, conta, sub_conta, nome, nome_realizado, tipo, grau, codigo_plano "
							+ "from sigadm.dbo.cndplano where codigo_plano = 4321 and cod_reduzido = "
							+ codigoReduzido);
			List<Object[]> spconta = query.getResultList();
			for (Object[] obj : spconta) {
				cndplano p = new cndplano();

				p.setCod_reduzido(Integer.valueOf(obj[0].toString()));
				p.setCodigo_grafico(Integer.valueOf(obj[1].toString()));
				p.setConta(Integer.valueOf(obj[2].toString()));
				p.setSub_conta(Integer.valueOf(obj[3].toString()));
				p.setNome(obj[4].toString());
				p.setNome_realizado(obj[5].toString());
				p.setTipo(obj[6].toString());
				p.setGrau(Integer.valueOf(obj[7].toString()));
				p.setCodigo_plano(Short.valueOf(obj[8].toString()));

				lpconta.add(p);
			}
		} else {

			this.query = this.manager.createNativeQuery(
					"select  cod_reduzido, codigo_grafico, conta, sub_conta, nome, nome_realizado, tipo, grau, codigo_plano "
							+ "from sigadm.dbo.cndplano where codigo_plano <> 4321 and cod_reduzido = "
							+ codigoReduzido);
			List<Object[]> spconta = query.getResultList();
			for (Object[] obj : spconta) {
				cndplano p = new cndplano();

				p.setCod_reduzido(Integer.valueOf(obj[0].toString()));
				p.setCodigo_grafico(Integer.valueOf(obj[1].toString()));
				p.setConta(Integer.valueOf(obj[2].toString()));
				p.setSub_conta(Integer.valueOf(obj[3].toString()));
				p.setNome(obj[4].toString());
				p.setNome_realizado(obj[5].toString());
				p.setTipo(obj[6].toString());
				p.setGrau(Integer.valueOf(obj[7].toString()));
				p.setCodigo_plano(Short.valueOf(obj[8].toString()));

				lpconta.add(p);
			}
		}

		return lpconta;
	}

	/**
	 * metodo utilizado para listar o fornecedor na base siga
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public cpcredor listarFornecedor(double cnpjCpf) {
		List<cpcredor> lcredor = new ArrayList<cpcredor>();
		this.query = this.manager.createNativeQuery(
				"select usualcred, nome, tipo_inscricao, inscricao from sigadm.dbo.cpcredor where inscricao ="
						+ cnpjCpf);
		List<Object[]> scredor = query.getResultList();
		for (Object[] obj : scredor) {
			cpcredor cre = new cpcredor();
			cre.setUsualcred(obj[0].toString().replace("'", "''"));
			if (obj[1] != null) {
				cre.setNome(obj[1].toString());
			}
			if (obj[2] != null) {
				cre.setTipoInscricao(obj[2].toString());
			}
			if (obj[3] != null) {
				cre.setInscricao(Long.valueOf(obj[3].toString()));
			}

			lcredor.add(cre);
		}
		List<cpcredor> lista = lcredor;
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public atbancos pesquisarBanco(int codigo) {
		atbancos atb = new atbancos();
		this.query = this.manager.createNativeQuery(
				"select codbanco,nomebanco from [sigadm].[dbo].[atbancos] where codbanco = " + codigo);
		List<Object[]> listar = query.getResultList();
		for (Object[] obj : listar) {
			atbancos b = new atbancos();
			b.setCodBanco(Short.valueOf(obj[0].toString()));
			b.setNomeDoBanco(obj[1].toString());
			atb = b;
		}
		return atb;
	}

	/**
	 * metodo utilizado para listar o plano de contas o prametro que recebe é o
	 * nome da conta
	 * 
	 * @param s
	 * 
	 * @param codigo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<cndplano> listarPlanoContaNome(String nome, int s) {

		int codigoPlano = 0;
		this.query = this.manager.createNativeQuery("select codigo_plano from sigadm.dbo.cndcondo where codigo = :p1");
		query.setParameter("p1", s);
		for (short aux : (List<Short>) query.getResultList()) {
			codigoPlano = aux;
		}

		List<cndplano> lpconta = new ArrayList<cndplano>();

		this.query = this.manager.createNativeQuery(
				"select  p1.cod_reduzido, p1.codigo_grafico, p1.conta, p1.sub_conta, p1.nome, p1.nome_realizado, p1.tipo, p1.grau, p1.codigo_plano, p2.nome as nome_capa "
						+ "from sigadm.dbo.cndplano p1 inner join sigadm.dbo.cndplano p2 on p2.conta = p1.conta_grau1 where p1.codigo_plano = "
						+ codigoPlano + " and p2.codigo_plano = " + codigoPlano + " and p1.nome like '%" + nome
						+ "%' order by p1.conta,p1.sub_conta");
		List<Object[]> spconta = query.getResultList();
		for (Object[] obj : spconta) {
			cndplano p = new cndplano();
			p.setCod_reduzido(Integer.valueOf(obj[0].toString()));
			p.setCodigo_grafico(Integer.valueOf(obj[1].toString()));
			p.setConta(Integer.valueOf(obj[2].toString()));
			p.setSub_conta(Integer.valueOf(obj[3].toString()));
			p.setNome(obj[4].toString());
			p.setNome_realizado(obj[5].toString());
			p.setTipo(obj[6].toString());
			p.setGrau(Integer.valueOf(obj[7].toString()));
			p.setCodigo_plano(Short.valueOf(obj[8].toString()));
			p.setNome_capa(obj[9].toString());

			lpconta.add(p);
		}
		return lpconta;
	}

	@SuppressWarnings("unchecked")
	public List<cpcredor> listarFornecedoresCNPJ(String fornecedor) {
		List<cpcredor> lcredor = new ArrayList<cpcredor>();
		this.query = this.manager.createNativeQuery(
				"select usualcred, nome, tipo_inscricao, inscricao from sigadm.dbo.cpcredor where nome like  '%"
						+ fornecedor + "%'");
		List<Object[]> scredor = query.getResultList();
		for (Object[] obj : scredor) {
			cpcredor cre = new cpcredor();
			cre.setUsualcred(obj[0].toString().replace("'", "''"));
			if (obj[1] != null) {
				cre.setNome(obj[1].toString());
			}
			if (obj[2] != null) {
				cre.setTipoInscricao(obj[2].toString());
			}
			if (obj[3] != null) {
				cre.setInscricao(Long.valueOf(obj[3].toString()));
			}

			lcredor.add(cre);
		}
		return lcredor;
	}

	@SuppressWarnings("unchecked")
	public List<BigDecimal> validaIdImg() {
		this.query = this.manager.createNativeQuery("select id from sigadm.dbo.glbCatalogo where id > 10499");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<BigDecimal> validaIdImg2(BigDecimal id) {
		this.query = this.manager.createNativeQuery("select id from sigadm.dbo.glbCatalogo where id = :id");
		this.query.setParameter("id", id);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public financeiro_imagem pesquisaImagem(double idImagem, int codigoCnd) {
		this.query = this.manager.createQuery("from financeiro_imagem where id = :p1 and cd_cnd = :p2");
		query.setParameter("p1", idImagem);
		query.setParameter("p2", Short.valueOf(String.valueOf(codigoCnd)));
		List<financeiro_imagem> lst = query.getResultList();
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return new financeiro_imagem();
		}
	}

	/**
	 * metodo utilizado para listar os favorecidos na base siga
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<cpfavor> listarFavorecido(String nome) {
		List<cpfavor> lfavor = new ArrayList<cpfavor>();
		this.query = this.manager.createNativeQuery(
				"select [codigo],[favorecido],[tipo_pessoa],[cnpj_cpf],[banco],[agencia],[conta_corrente],[dac_conta],[conta_poupanca],[tipo_conta], b.nomebanco "
						+ "from sigadm.dbo.cpfavor f " + "inner join sigadm.dbo.atbancos b on f.banco = b.codbanco "
						+ "where  favorecido like '%" + nome + "%'");
		List<Object[]> sfavor = query.getResultList();

		for (Object[] obj : sfavor) {
			cpfavor fav = new cpfavor();

			fav.setCodigo(Integer.valueOf(obj[0].toString()));
			fav.setFavorecido(obj[1].toString());

			if (obj[2] != null) {
				fav.setTipoPessoa(obj[2].toString());
			}
			if (obj[3] != null) {
				fav.setCnpjCpf(Long.valueOf(obj[3].toString()));
			}
			if (obj[4] != null) {
				fav.setBanco(Short.valueOf(obj[4].toString()));
			}
			if (obj[5] != null) {
				fav.setAgencia(Integer.valueOf(obj[5].toString()));
			}
			if (obj[6] != null) {
				fav.setContaCorrente(obj[6].toString());
			}
			if (obj[7] != null) {
				fav.setDacConta(obj[7].toString());
			}
			if (obj[8] != null) {
				fav.setContaPoupanca(obj[8].toString());
			}
			if (obj[9] != null) {
				fav.setTipo_conta(obj[9].toString());
			}
			if (obj[10] != null) {
				fav.setNomeBanco(obj[10].toString());
			}
			lfavor.add(fav);
		}

		return lfavor;
	}

	@SuppressWarnings("unchecked")
	public List<atbancos> listarBancosPorNome(String nome) {
		atbancos banco = new atbancos();
		List<atbancos> lstBancos = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select codbanco, nomebanco from sigadm.dbo.atbancos where nomebanco like :p1");
		this.query.setParameter("p1", "%" + nome + "%");
		List<Object[]> lst = this.query.getResultList();
		for (Object[] aux : lst) {
			if (aux[0] != null) {
				banco.setCodBanco(Short.parseShort(aux[0].toString()));
			}
			if (aux[1] != null) {
				banco.setNomeDoBanco(aux[1].toString());
			}
			lstBancos.add(banco);
			banco = new atbancos();
		}
		return lstBancos;
	}

	@SuppressWarnings("unchecked")
	public cndpagar pesqDupliVCCB(cndpagar c) {
		cndpagar p = new cndpagar();
		this.query = this.manager
				.createQuery("from cndpagar where vencimento = :p1 and codigo_barra = :p2 and condominio = :p3");
		query.setParameter("p1", c.getVencimento());
		query.setParameter("p2", c.getCodigoBarra());
		query.setParameter("p3", c.getCondominio());
		List<cndpagar> lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (cndpagar aux : lista) {
				p = aux;
			}
		} else {
			p = null;
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	public cndpagar pesqDupliVCAGCCVL(cndpagar c, double valor2) {
		cndpagar p = new cndpagar();

		this.query = this.manager.createQuery(
				"from cndpagar where vencimento = :p1 and agenc_destino = :p2 and conta_destino = :p3 and valor = :p4 and condominio = "
						+ c.getCondominio());
		query.setParameter("p1", c.getVencimento());
		query.setParameter("p2", c.getAgencDestino());
		query.setParameter("p3", c.getContaDestino());
		query.setParameter("p4", valor2);
		List<cndpagar> lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (cndpagar aux : lista) {
				p = aux;
			}
		} else {
			p = null;
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	public short listContCondo(short codigoCnd) {
		short conta = 0;
		int codigo = Integer.valueOf(codigoCnd);
		this.query = this.manager
				.createNativeQuery("select cod_conta from sigadm.dbo.cndcondo where codigo = " + codigo);
		List<Short> lista = query.getResultList();
		for (short s : lista) {
			conta = s;
		}
		return conta;
	}

	@SuppressWarnings("unchecked")
	public atbancos listBancoOMA(short codigo) {
		atbancos atb = new atbancos();
		this.query = this.manager.createNativeQuery(
				"select cod_banco,titulo_conta from [sigadm].[dbo].[atbconta] where codigo = " + codigo);
		List<Object[]> listar = query.getResultList();
		for (Object[] obj : listar) {
			atbancos b = new atbancos();
			b.setCodBanco(Short.valueOf(obj[0].toString()));
			b.setNomeDoBanco(obj[1].toString());
			atb = b;
		}
		return atb;
	}

	@SuppressWarnings("unchecked")
	public atbancos listBancoOMA2(short codigo) {
		atbancos atb = new atbancos();
		this.query = this.manager.createNativeQuery(
				"select codbanco, nomebanco from [sigadm].[dbo].[atbancos] where codbanco = " + codigo);
		List<Object[]> listar = query.getResultList();
		for (Object[] obj : listar) {
			atbancos b = new atbancos();
			b.setCodBanco(Short.valueOf(obj[0].toString()));
			b.setNomeDoBanco(obj[1].toString());
			atb = b;
		}
		return atb;
	}

	public boolean adicionaLanctoOma(cndpagar p, String email, String ip, List<financeiro_imagem> listaArquivos,
			String obsLancto) {
		boolean valida = false;
		try {
			for (financeiro_imagem aux : listaArquivos) {
				if (aux.getCodigo() == 0) {
					this.manager.persist(aux);
				} else {
					this.manager.merge(aux);
				}
			}

			if (listaArquivos != null && listaArquivos.size() > 0) {
				p.getImagens().addAll(listaArquivos);
			}

			p.setRateado("N");
			if (p.getParcelado().equals("S")) {
				p.setValidaParcelado(true);
				p.setCodigoParcelado(this.returnUltimoControlParc());
				this.manager.persist(p);
			} else {
				this.manager.persist(p);
			}

			// Registrar LOG
			this.registrarFollowUp(p.getCodigo(), "Adicionado", email, ip, p.getCondominio(), obsLancto);
			valida = true;
			return valida;
		} catch (Exception e) {
			return valida;
		}
	}

	public boolean adicionaLanctoOmaRateado(cndpagar p, String email, String ip, List<rateio> listaDeRateio,
			List<financeiro_imagem> listaArquivos) {
		boolean valida = false;
		try {
			for (financeiro_imagem aux : listaArquivos) {
				if (aux.getCodigo() == 0) {
					this.manager.persist(aux);
				} else {
					this.manager.merge(aux);
				}
			}
			if (listaArquivos != null && listaArquivos.size() > 0) {
				p.getImagens().addAll(listaArquivos);
			}

			p.setCodigoRateio(this.returnUltimoControlRat());
			p.setAdicionadoPor(email);
			for (rateio r : listaDeRateio) {
				if (r.getValor1() != 0) {
					p.setValor(r.getValor1());
					p.setValorLancto(r.getValor1());
					p.setConta(r.getContaReduzida1());
					p.setCtaAnlFinanc(r.getContaGradico1());
					p.setHist(r.getComplemento1());
					p.setHistorico(r.getHistorico1());
					cndpagar clone1 = (cndpagar) p.clone();
					this.manager.persist(clone1);

					this.registrarFollowUp(clone1.getCodigo(), "Adicionado", clone1.getAdicionadoPor(), ip,
							clone1.getCondominio());
				} else if (r.getValor2() != 0) {
					p.setValor(r.getValor2());
					p.setValorLancto(r.getValor2());
					p.setConta(r.getContaReduzida2());
					p.setCtaAnlFinanc(r.getContaGradico2());
					p.setHist(r.getHistorico2());
					p.setHistorico(r.getHistorico2());
					cndpagar clone2 = (cndpagar) p.clone();
					this.manager.persist(clone2);

					this.registrarFollowUp(clone2.getCodigo(), "Adicionado", clone2.getAdicionadoPor(), ip,
							clone2.getCondominio());
				} else if (r.getValor3() != 0) {
					p.setValor(r.getValor3());
					p.setValorLancto(r.getValor3());
					p.setConta(r.getContaReduzida3());
					p.setCtaAnlFinanc(r.getContaGradico3());
					p.setHist(r.getHistorico3());
					p.setHistorico(r.getHistorico3());
					cndpagar clone3 = (cndpagar) p.clone();
					this.manager.merge(clone3);

					this.registrarFollowUp(clone3.getCodigo(), "Adicionado", clone3.getAdicionadoPor(), ip,
							clone3.getCondominio());
				} else if (r.getValor4() != 0) {
					p.setValor(r.getValor4());
					p.setValorLancto(r.getValor4());
					p.setConta(r.getContaReduzida4());
					p.setCtaAnlFinanc(r.getContaGradico4());
					p.setHist(r.getHistorico4());
					p.setHistorico(r.getHistorico4());
					cndpagar clone4 = (cndpagar) p.clone();
					this.manager.persist(clone4);

					this.registrarFollowUp(clone4.getCodigo(), "Adicionado", clone4.getAdicionadoPor(), ip,
							clone4.getCondominio());
				} else if (r.getValor5() != 0) {
					p.setValor(r.getValor5());
					p.setValorLancto(r.getValor5());
					p.setConta(r.getContaReduzida5());
					p.setCtaAnlFinanc(r.getContaGradico5());
					p.setHist(r.getHistorico5());
					p.setHistorico(r.getHistorico5());
					cndpagar clone5 = (cndpagar) p.clone();
					this.manager.persist(clone5);

					this.registrarFollowUp(clone5.getCodigo(), "Adicionado", clone5.getAdicionadoPor(), ip,
							clone5.getCondominio());
				} else if (r.getValor6() != 0) {
					p.setValor(r.getValor6());
					p.setValorLancto(r.getValor6());
					p.setConta(r.getContaReduzida6());
					p.setCtaAnlFinanc(r.getContaGradico6());
					p.setHist(r.getHistorico6());
					p.setHistorico(r.getHistorico6());
					cndpagar clone6 = (cndpagar) p.clone();
					this.manager.persist(clone6);

					this.registrarFollowUp(clone6.getCodigo(), "Adicionado", clone6.getAdicionadoPor(), ip,
							clone6.getCondominio());
				}
			}
			valida = true;
			return valida;
		} catch (Exception e) {
			return valida;
		}
	}

	public void registrarFollowUp(int nrolancto, String acao, String feitoPor, String ip, short cdCnd) {
		cndpagar_followup followup = new cndpagar_followup();
		followup.setData(new Date());
		followup.setNrolancto(nrolancto);
		followup.setAcao(acao);
		followup.setFeitoPor(feitoPor);
		followup.setIp(ip);
		followup.setCdCnd(cdCnd);
		this.manager.persist(followup);
	}

	/**
	 * metodo utilizado para listar a tabela cndpagar e retornar o ultimo codigo
	 * de lançamento + 1
	 * 
	 * @return
	 */
	public int returnUltimoControlParc() {
		int nrorateio;
		controle_parcelado p = this.manager.find(controle_parcelado.class, 1L, LockModeType.PESSIMISTIC_WRITE);
		p.setVersao(p.getVersao() + 1);
		manager.persist(p);
		;
		nrorateio = Integer.valueOf(String.valueOf(p.getVersao()));
		return nrorateio;
	}

	/**
	 * metodo utilizado para listar o plano de contas o prametro que recebe é o
	 * codigo reduzido
	 * 
	 * @param codigo
	 * @param s
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean listarPlanoConta2(int codigo, short s) {
		boolean valida = false;
		if (s == 4241) {
			if (codigo < 100000) {
				codigo += 100000;
			}
			this.query = this.manager.createNativeQuery(
					"select  cod_reduzido, codigo_grafico, conta, sub_conta, nome, nome_realizado, tipo, grau, codigo_plano "
							+ "from sigadm.dbo.cndplano where codigo_plano = 4321 and cod_reduzido = " + codigo);
			List<Object[]> spconta = query.getResultList();
			if (!spconta.isEmpty()) {
				valida = true;
			}
		} else {

			this.query = this.manager.createNativeQuery(
					"select  cod_reduzido, codigo_grafico, conta, sub_conta, nome, nome_realizado, tipo, grau, codigo_plano "
							+ "from sigadm.dbo.cndplano where codigo_plano <> 4321 and cod_reduzido = " + codigo);
			List<Object[]> spconta = query.getResultList();
			if (!spconta.isEmpty()) {
				valida = true;
			}
		}

		return valida;
	}

	@SuppressWarnings("unchecked")
	public cpcredor pesquisaFornecedorUsualcred(String usualcred) {
		List<cpcredor> lcredor = new ArrayList<cpcredor>();
		this.query = this.manager.createNativeQuery(
				"select usualcred, nome, tipo_inscricao, inscricao from sigadm.dbo.cpcredor where usualcred = '"
						+ usualcred + "'");
		List<Object[]> scredor = query.getResultList();
		for (Object[] obj : scredor) {
			cpcredor cre = new cpcredor();
			cre.setUsualcred(obj[0].toString().replace("'", "''"));
			if (obj[1] != null) {
				cre.setNome(obj[1].toString());
			}
			if (obj[2] != null) {
				cre.setTipoInscricao(obj[2].toString());
			}
			if (obj[3] != null) {
				cre.setInscricao(Long.valueOf(obj[3].toString()));
			}

			lcredor.add(cre);
		}
		List<cpcredor> lista = lcredor;
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<cpfavor> listarFavorecidoCodigo(int codigo) {
		List<cpfavor> lfavor = new ArrayList<cpfavor>();
		this.query = this.manager.createNativeQuery(
				"select [codigo],[favorecido],[tipo_pessoa],[cnpj_cpf],[banco],[agencia],[conta_corrente],[dac_conta],[conta_poupanca],[tipo_conta], b.nomebanco "
						+ "from sigadm.dbo.cpfavor f " + "inner join sigadm.dbo.atbancos b on f.banco = b.codbanco "
						+ "where  codigo = :p1");
		this.query.setParameter("p1", codigo);
		List<Object[]> sfavor = query.getResultList();

		for (Object[] obj : sfavor) {
			cpfavor fav = new cpfavor();

			fav.setCodigo(Integer.valueOf(obj[0].toString()));
			fav.setFavorecido(obj[1].toString());

			if (obj[2] != null) {
				fav.setTipoPessoa(obj[2].toString());
			}
			if (obj[3] != null) {
				fav.setCnpjCpf(Long.valueOf(obj[3].toString()));
			}
			if (obj[4] != null) {
				fav.setBanco(Short.valueOf(obj[4].toString()));
			}
			if (obj[5] != null) {
				fav.setAgencia(Integer.valueOf(obj[5].toString()));
			}
			if (obj[6] != null) {
				fav.setContaCorrente(obj[6].toString());
			}
			if (obj[7] != null) {
				fav.setDacConta(obj[7].toString());
			}
			if (obj[8] != null) {
				fav.setContaPoupanca(obj[8].toString());
			}
			if (obj[9] != null) {
				fav.setTipo_conta(obj[9].toString());
			}
			if (obj[10] != null) {
				fav.setNomeBanco(obj[10].toString());
			}
			lfavor.add(fav);
		}

		return lfavor;
	}

	public cndpagar alterarLancamentoParcelado(cndpagar cndpagar, String email, String ipUser,
			List<Integer> lstExcArquivo, List<financeiro_imagem> listaArquivos, String obsLancto) {

		for (financeiro_imagem aux : listaArquivos) {
			if (aux.getCodigo() == 0) {
				this.manager.persist(aux);
			} else {
				this.manager.merge(aux);
			}
			if (!cndpagar.getImagens().contains(aux)) {
				cndpagar.getImagens().add(aux);
			}
		}

		for (int aux : lstExcArquivo) {
			Iterator<financeiro_imagem> itr = cndpagar.getImagens().iterator();
			while (itr.hasNext()) {
				if (aux == itr.next().getCodigo()) {
					itr.remove();
				}
			}
		}

		this.query = this.manager
				.createQuery("update cndpagar set valida_parcelado = false where codigo = " + cndpagar.getCodigo());
		this.query.executeUpdate();

		cndpagar p2 = (cndpagar) cndpagar.clone();

		p2.setImagens(new ArrayList<financeiro_imagem>());

		if (p2.getImagens() != null && listaArquivos != null && listaArquivos.size() > 0) {
			p2.getImagens().addAll(listaArquivos);
		}

		p2.setCodigo(0);
		p2.setNrolancto(0);
		p2.setValidaParcelado(true);
		this.manager.persist(p2);
		this.registrarFollowUp(p2.getCodigo(), "Adicionado", email, ipUser, p2.getCondominio(), obsLancto);
		return p2;
	}

	public void alterarLancamento(cndpagar cndpagar, String email, String ipUser, List<Integer> lstExcArquivo,
			List<financeiro_imagem> listaArquivos, String obsLancto, int alterarParcelado) {

		for (financeiro_imagem aux : listaArquivos) {
			if (aux.getCodigo() == 0) {
				this.manager.persist(aux);
			} else {
				this.manager.merge(aux);
			}
			if (!cndpagar.getImagens().contains(aux)) {
				cndpagar.getImagens().add(aux);
			}
		}
		for (int aux : lstExcArquivo) {
			Iterator<financeiro_imagem> itr = cndpagar.getImagens().iterator();
			while (itr.hasNext()) {
				if (aux == itr.next().getCodigo()) {
					itr.remove();
				}
			}
		}
		cndpagar p2 = (cndpagar) cndpagar.clone();
		this.manager.merge(cndpagar);
		this.manager.flush();
		this.registrarFollowUp(cndpagar.getCodigo(), "Alterado", email, ipUser, cndpagar.getCondominio(), obsLancto);
		if (cndpagar.getParcelado().equals("S") && alterarParcelado == 1) {
			this.query = this.manager.createQuery("update cndpagar set pc_inicial = " + cndpagar.getPcInicial()
					+ " where cod_parcelado = " + cndpagar.getCodigoParcelado());
			this.query.executeUpdate();
			p2.setCodigo(0);
			p2.setNrolancto(0);
			this.manager.persist(p2);
			this.registrarFollowUp(p2.getCodigo(), "Adicionado", email, ipUser, p2.getCondominio(), obsLancto);
		}
	}

	public cndpagar pesquisarLancamento(int cdLancamento) {
		return this.manager.find(cndpagar.class, cdLancamento);
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> listarAprovados() {
		this.query = this.manager.createQuery("from cndpagar");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> listarAprovados(int gerente) {
		this.query = this.manager.createQuery(
				"SELECT p.codigo,[agenc_destino],[ano],[autorizacao],[banco_destino],[BASE_INSS],[BASE_IR_CSLL],"
						+ "[BASE_ISSQN],[bloco],[cb_fich_fornec],[cb_nota_fornec],[cheq_em_process],[classificacao],[cnpj],[cod_compensacao],[cod_movto],"
						+ "[cod_pagto],[cod_receita],[codigo_barra],[codigo_fav],[codigo_hist],[conc_segbarra1],[conc_segbarra2],[conc_segbarra3],"
						+ "[conc_segbarra4],[condominio],[conf_remessa],[conta],[conta_bancaria],[conta_destino],[contab_credito],[contab_debito],[contab_empresa],"
						+ "[contribuinte],[cpmf],[credor],[cta_anl_financ],[data_alteracao],[data_apuracao],[data_inclusao],[declaracao],[dig_age_dest],"
						+ "[docto_integra],[documento],[dt_emiss_docto],[dt_receb_bloq],[dt_receb_nf],[emissao_nf],[empresa],[enviado],[especie_docto],[estimado],"
						+ "[favorecido],[finalidade_doc],[flag_imposto],[forne_gps],[hist],[historico],[incide_tx_adm],[instrucoes_op],[ld_campo_1],[ld_campo_2],"
						+ "[ld_campo_3],[ld_dac],[ld_valor],[local_serv],[mes],[modal_pagto],[moeda],[motivoReprovacao],[nf],[nome_agencia],[nota_fiscal],[nro_bancario],"
						+ "[nro_cotacao],[nro_docto],[nro_documento],[nro_id],[nro_remessa],[nrolancto],[numero_do_doc],[numero_nf],[origem_lancto],[percentual],"
						+ "[pg_credpoup],[prev_nrolancto],[prox_vencto],[qtde_moeda],[rateio],[reter_imposto],[serie_da_nf],[serie_nf],[status_],[tipo_carteira],"
						+ "[tipo_conta],[tipo_do_doc],[tipo_doc],[tipoDocumento],[tipo_nf],[tipopagto],[tipo_pessoa],[usuario],[valor],[valor_lancto],[valor_nf],"
						+ "[valor_retencao],[vencimento],[vr_atual_monet],[vr_outros],[vr_tributo],[cod_rateio],[parcelado],[pc_inicial],[pc_final],[rateado],[adicionado_por],"
						+ "[obs],[tipo_conta_bancaria],[cod_parcelado],[valida_parcelado] "
						+ "FROM [omaonline].[dbo].[cndpagar] p  " + "inner join cndcondo c on p.condominio = c.codigo "
						+ "where c.gerente = :p1");
		this.query.setParameter("p1", gerente);
		return this.query.getResultList();
	}

	public void alterarLancamentoResumido(cndpagar cndpagar) {
		if (cndpagar.getCodigo() != 0) {
			this.manager.merge(cndpagar);
			this.manager.flush();
		}
	}

	public short listarContaBancaria(int conta) {
		this.query = this.manager
				.createNativeQuery("select conta_grau1 from sigadm.dbo.cndplano where cod_reduzido = :p1");
		this.query.setParameter("p1", conta);
		System.out.println(this.query.getResultList().get(0).toString());
		int contaGrau1 = Integer.valueOf(this.query.getResultList().get(0).toString());

		if (contaGrau1 == 44000) {
			return 4850;
		} else if (contaGrau1 == 47000) {
			return 4848;
		} else if (contaGrau1 == 93000) {
			return 4847;
		} else {
			return 4846;
		}
	}
	
	public List<cndpagar> listarLanctoOma(){
		TypedQuery<cndpagar> query = this.manager.createQuery("from cndpagar where status_sip > 0 and visto_gerente = false and tipo_lancamento = 1", cndpagar.class);
		return query.getResultList();
	}


	public String listarCredorNome(String credor) {
		Query query = this.manager.createNativeQuery("select nome from sigadm.dbo.cpcredor where usualcred = :p1");
		query.setParameter("p1", credor.trim());
		String nome = query.getResultList().get(0).toString();
		return nome;
	}
	
	public String listarCredorCNPJ(String credor) {
		Query query = this.manager.createNativeQuery("select inscricao from sigadm.dbo.cpcredor where usualcred = :p1");
		query.setParameter("p1", credor.trim());
		String cnpj = query.getResultList().get(0).toString();
		return cnpj;
	}

	public String listarHistoricoPadrao(int historicoPadrao) {
		String historico = "";
		Query query = this.manager.createNativeQuery("select historico from sigadm.dbo.atbpadr where codigo = "+historicoPadrao);
		@SuppressWarnings("unchecked")
		List<String> lista = query.getResultList();
		if(!lista.isEmpty()){
			for (String obj : lista) {
				historico = obj;
			}
		}
		return historico;
	}

	public void adicionaLanctoSIP(cndpagar pagar, SessaoMB sessao, String followUp) {
		intra_log_geral lg = new intra_log_geral(pagar.getCondominio(), sessao.getUsuario().getEmail(), "omaonline.dbo.cndpagar",
				false, true, false, "Update Lançamento GED - codigo: "+pagar.getCodigo(), new Date(), 0, null, null);
		this.logGeral(lg );
		this.manager.merge(pagar);
		this.registrarFollowUp(pagar.getCodigo(), "Aprovado", sessao.getUsuario().getEmail(), sessao.getIpUser(), pagar.getCondominio(), followUp);
	}
	
	public boolean adicionaRateioSIP(cndpagar p, SessaoMB sessao, String ip, List<rateio> listaDeRateio,
			List<financeiro_imagem> listaArquivos) {
		boolean valida = false;
		try {
			for (financeiro_imagem aux : listaArquivos) {
				if (aux.getCodigo() == 0) {
					this.manager.persist(aux);
				} else {
					this.manager.merge(aux);
				}
			}
			if (listaArquivos != null && listaArquivos.size() > 0) {
				p.getImagens().addAll(listaArquivos);
			}

			p.setCodigoRateio(this.returnUltimoControlRat());
			p.setAdicionadoPor(sessao.getUsuario().getEmail());
			for (rateio r : listaDeRateio) {
				if (r.getValor1() != 0) {
					p.setValor(r.getValor1());
					p.setValorLancto(r.getValor1());
					p.setConta(r.getContaReduzida1());
					p.setCtaAnlFinanc(r.getContaGradico1());
					p.setHist(r.getComplemento1());
					p.setHistorico(r.getHistorico1());
					//cndpagar clone1 = (cndpagar) p.clone();
					this.manager.merge(p);

					intra_log_geral lg = new intra_log_geral(p.getCondominio(), sessao.getUsuario().getEmail(), "omaonline.dbo.cndpagar",
							false, true, false, "Update Lançamento GED - codigo: "+p.getCodigo(), new Date(), 0, null, null);
					this.logGeral(lg);
					
					this.registrarFollowUp(p.getCodigo(), "Aprovado", sessao.getUsuario().getEmail(), ip, p.getCondominio()); 
					p.setCodigo(0);
					
				} else if (r.getValor2() != 0) {
					p.setValor(r.getValor2());
					p.setValorLancto(r.getValor2());
					p.setConta(r.getContaReduzida2());
					p.setCtaAnlFinanc(r.getContaGradico2());
					p.setHist(r.getHistorico2());
					p.setHistorico(r.getHistorico2());
					cndpagar clone2 = (cndpagar) p.clone();
					this.manager.persist(clone2);
					
					intra_log_geral lg = new intra_log_geral(p.getCondominio(), sessao.getUsuario().getEmail(), "omaonline.dbo.cndpagar",
							false, true, false, "Insert Lançamento rateado GED - codigo: "+clone2.getCodigo(), new Date(), 0, null, null);
					this.logGeral(lg);

					this.registrarFollowUp(clone2.getCodigo(), "Adicionado", sessao.getUsuario().getEmail(), ip,	clone2.getCondominio());
				} else if (r.getValor3() != 0) {
					p.setValor(r.getValor3());
					p.setValorLancto(r.getValor3());
					p.setConta(r.getContaReduzida3());
					p.setCtaAnlFinanc(r.getContaGradico3());
					p.setHist(r.getHistorico3());
					p.setHistorico(r.getHistorico3());
					cndpagar clone3 = (cndpagar) p.clone();
					this.manager.merge(clone3);
					
					intra_log_geral lg = new intra_log_geral(p.getCondominio(), sessao.getUsuario().getEmail(), "omaonline.dbo.cndpagar",
							false, true, false, "Insert Lançamento rateado GED - codigo: "+clone3.getCodigo(), new Date(), 0, null, null);
					this.logGeral(lg);

					this.registrarFollowUp(clone3.getCodigo(), "Adicionado", sessao.getUsuario().getEmail(), ip,
							clone3.getCondominio());
				} else if (r.getValor4() != 0) {
					p.setValor(r.getValor4());
					p.setValorLancto(r.getValor4());
					p.setConta(r.getContaReduzida4());
					p.setCtaAnlFinanc(r.getContaGradico4());
					p.setHist(r.getHistorico4());
					p.setHistorico(r.getHistorico4());
					cndpagar clone4 = (cndpagar) p.clone();
					this.manager.persist(clone4);
					
					intra_log_geral lg = new intra_log_geral(p.getCondominio(), sessao.getUsuario().getEmail(), "omaonline.dbo.cndpagar",
							false, true, false, "Insert Lançamento rateado GED - codigo: "+clone4.getCodigo(), new Date(), 0, null, null);
					this.logGeral(lg);

					this.registrarFollowUp(clone4.getCodigo(), "Adicionado", sessao.getUsuario().getEmail(), ip,
							clone4.getCondominio());
				} else if (r.getValor5() != 0) {
					p.setValor(r.getValor5());
					p.setValorLancto(r.getValor5());
					p.setConta(r.getContaReduzida5());
					p.setCtaAnlFinanc(r.getContaGradico5());
					p.setHist(r.getHistorico5());
					p.setHistorico(r.getHistorico5());
					cndpagar clone5 = (cndpagar) p.clone();
					this.manager.persist(clone5);
					
					intra_log_geral lg = new intra_log_geral(p.getCondominio(), sessao.getUsuario().getEmail(), "omaonline.dbo.cndpagar",
							false, true, false, "Insert Lançamento rateado GED - codigo: "+clone5.getCodigo(), new Date(), 0, null, null);
					this.logGeral(lg);

					this.registrarFollowUp(clone5.getCodigo(), "Adicionado", sessao.getUsuario().getEmail(), ip,
							clone5.getCondominio());
				} else if (r.getValor6() != 0) {
					p.setValor(r.getValor6());
					p.setValorLancto(r.getValor6());
					p.setConta(r.getContaReduzida6());
					p.setCtaAnlFinanc(r.getContaGradico6());
					p.setHist(r.getHistorico6());
					p.setHistorico(r.getHistorico6());
					cndpagar clone6 = (cndpagar) p.clone();
					this.manager.persist(clone6);
					
					intra_log_geral lg = new intra_log_geral(p.getCondominio(), sessao.getUsuario().getEmail(), "omaonline.dbo.cndpagar",
							false, true, false, "Insert Lançamento rateado GED - codigo: "+clone6.getCodigo(), new Date(), 0, null, null);
					this.logGeral(lg);

					this.registrarFollowUp(clone6.getCodigo(), "Adicionado", sessao.getUsuario().getEmail(), ip,	clone6.getCondominio());
				}
			}
			valida = true;
			return valida;
		} catch (Exception e) {
			return valida;
		}
	}

	public void updateSGLTIMP(cndpagar pagar) {
		Query query = this.manager.createNativeQuery("update sigadm.dbo.sgltimp set historico = :p1 where nrolancto = :p2");
		query.setParameter("p1", pagar.getHistorico());
		query.setParameter("p2", pagar.getNrolancto());
		query.executeUpdate();
	}

	public void updateSGIMPOS(cndpagar pagar) {
		Query query = this.manager.createNativeQuery("update sigadm.dbo.sgimpos set conta_contabil = :p1 where nrolancto = :p2");
		query.setParameter("p1", pagar.getConta());
		query.setParameter("p2", pagar.getNrolancto());
		query.executeUpdate();
	}


	public void liberarPagamento(cndpagar pagar, int valor, SessaoMB sessao, int setor) {
		Query query = null;
		if(setor == 1){
			query = this.manager.createNativeQuery("update omaonline.dbo.cndpagar set suspenso_contas = :p1 where codigo = :p2");
		}else if (setor == 2){
			query = this.manager.createNativeQuery("update omaonline.dbo.cndpagar set suspenso_gerente = :p1 where codigo = :p2");
		}
		query.setParameter("p1", valor);
		query.setParameter("p2", pagar.getCodigo());
		query.executeUpdate();
		
		intra_log_geral lg = new intra_log_geral(pagar.getCondominio(), sessao.getUsuario().getEmail(), "omaonline.dbo.cndpagar",
				false, true, false, "Liberar pagamento GED - codigo: "+pagar.getCodigo(), new Date(), 0, null, null);
		this.logGeral(lg );
		
	}
	
	@SuppressWarnings("unchecked")
	public List<cndpagar> getListaLancamentoContas(int filtro) {
		Query query= null;
		if(filtro == 0){
			query = this.manager.createQuery("from cndpagar where status_sip > 0 and suspenso_contas = 0 and feito_lancto_sip is null order by vencimento");
		}else if(filtro == 2){
			query = this.manager.createQuery("from cndpagar where status_sip > 0 and suspenso_contas = 0 and feito_lancto_sip is not null order by vencimento");
		}else{
			query = this.manager.createQuery("from cndpagar where status_sip > 0 and suspenso_contas = 3 order by vencimento");
		}
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<cndpagar> getListaLancamentoGerente(int filtro) {
		Query query= null;
		if(filtro == 0){
			query = this.manager.createQuery("from cndpagar where status_sip > 0 and suspenso_gerente = 0 and feito_gerente_sip is null order by vencimento");
		}else if(filtro == 2){
			query = this.manager.createQuery("from cndpagar where status_sip > 0 and suspenso_gerente = 0 and feito_gerente_sip is not null order by vencimento");
		}else{
			query = this.manager.createQuery("from cndpagar where status_sip > 0 and suspenso_gerente = 3 order by vencimento");
		}
		return query.getResultList();
	}

	public void suspenderLiberarLancto(cndpagar pagar, int acao, int setor, SessaoMB sessao, String followUp) {
		Query query = null;
		if (setor == 1) {
			if (acao == 1) {
				query = this.manager.createNativeQuery(
						"update omaonline.dbo.cndpagar set suspenso_contas = 3, feito_lancto_sip = getdate() where codigo = :p1");
				query.setParameter("p1", pagar.getCodigo());
				query.executeUpdate();
				this.registrarFollowUp(pagar.getCodigo(), "Suspenso", sessao.getUsuario().getEmail(), "oma",	pagar.getCondominio(), followUp);
			} else if (acao == 2) {
				query = this.manager.createNativeQuery(
						"update omaonline.dbo.cndpagar set suspenso_contas = 0, feito_lancto_sip = null where codigo = :p1");
				query.setParameter("p1", pagar.getCodigo());
				query.executeUpdate();
				this.registrarFollowUp(pagar.getCodigo(), "Liberado", sessao.getUsuario().getEmail(), "oma",	pagar.getCondominio(), followUp);
			}
		} else if (setor == 2) {
			if (acao == 1) {
				query = this.manager.createNativeQuery(
						"update omaonline.dbo.cndpagar set suspenso_gerente = 3, feito_gerente_sip = getdate() where codigo = :p1");
				query.setParameter("p1", pagar.getCodigo());
				query.executeUpdate();
				this.registrarFollowUp(pagar.getCodigo(), "Suspenso", sessao.getUsuario().getEmail(), "oma",	pagar.getCondominio(), followUp);
			} else if (acao == 2) {
				query = this.manager.createNativeQuery(
						"update omaonline.dbo.cndpagar set suspenso_gerente = 0, feito_gerente_sip = null where codigo = :p1");
				query.setParameter("p1", pagar.getCodigo());
				query.executeUpdate();
				this.registrarFollowUp(pagar.getCodigo(), "Liberado", sessao.getUsuario().getEmail(), "oma",	pagar.getCondominio(), followUp);
			}
		}
	}
	
	@SuppressWarnings("unused")
	public int listarSuspensoContas(){
		int resultado = 0;
		TypedQuery<cndpagar> query = this.manager.createQuery("from cndpagar where status_sip > 0 and suspenso_contas = 3 order by vencimento", cndpagar.class);
		List<cndpagar> lista = query.getResultList();
		if(!lista.isEmpty()){
			for (cndpagar pagar : lista) {
				resultado += 1;
			}
		}
		return resultado;
	}

	@SuppressWarnings("unused")
	public int listarSuspensoGerente(){
		int resultado = 0;
		TypedQuery<cndpagar> query = this.manager.createQuery("from cndpagar where status_sip > 0 and suspenso_gerente = 3 order by vencimento",cndpagar.class);
		List<cndpagar> lista = query.getResultList();
		if(!lista.isEmpty()){
			for (cndpagar pagar : lista) {
				resultado += 1;
			}
		}
		return resultado;
	}

}