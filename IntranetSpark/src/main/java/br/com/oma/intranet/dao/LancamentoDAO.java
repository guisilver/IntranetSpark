package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.entidades.UnidadeMedida;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.nfe;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.intranet.view.LancamentoView;
import br.com.oma.omaonline.entidades.atbancos;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cndpagar_followup;
import br.com.oma.omaonline.entidades.cndplano;
import br.com.oma.omaonline.entidades.cpcredor;
import br.com.oma.omaonline.entidades.cpfavor;
import br.com.oma.omaonline.entidades.financeiro_imagem;
import br.com.oma.omaonline.entidades.periodo_lancamento;
import br.com.oma.sigadm.entidades.glbCatalogo_Lcto;

public class LancamentoDAO extends LogGeralDAO {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;
	private PreparedStatement pmst;
	private Connection con;
	private ResultSet rs;

	public LancamentoDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public cndpagar pesquisaLancamentoPorCodigo(int codigo) {
		Query query = this.manager.createNativeQuery("select * from omaonline.dbo.cndpagar where codigo = :codigo",
				cndpagar.class);
		query.setParameter("codigo", codigo);
		List<cndpagar> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	public intra_condominios pesquisaCondPorCodigo(int codigo) {
		return this.manager.find(intra_condominios.class, codigo);
	}

	public void salvarNovoLancamento(cndpagar lancamento, SessaoMB sessao) {
		if (lancamento.getNrolancto() == 0) {
			lancamento.setNrolancto(this.returnUltimoLancto());
		}
		this.manager.persist(lancamento);

		this.registrarFollowUp(lancamento.getCodigo(), "Inserido", sessao.getUsuario().getEmail(), "oma",
				lancamento.getCondominio(), "Pre-Lançamento");

		this.manager.flush();

		// LOGGING
		intra_log_geral log = new intra_log_geral((int) lancamento.getCondominio(), retornaIdUsuario(), "cndpagar",
				true, false, false, lancamento.toString(), new Date(), 0, String.valueOf(lancamento.getNrolancto()),
				null);
		this.log(log);
	}

	public cndpagar salvarAlteracoesLancamento(cndpagar lancamento, SessaoMB sessao) {
		lancamento = this.manager.merge(lancamento);
		this.manager.flush();

		this.registrarFollowUp(lancamento.getCodigo(), "Alterado", sessao.getUsuario().getEmail(), "oma",
				lancamento.getCondominio(), "Pre-Lançamento");

		// LOGGING
		intra_log_geral log = new intra_log_geral((int) lancamento.getCondominio(), retornaIdUsuario(), "cndpagar",
				false, true, false, lancamento.toString(), new Date(), 0, String.valueOf(lancamento.getNrolancto()),
				null);
		this.log(log);

		return lancamento;
	}

	public void excluirLancamento(cndpagar lancamento) {
		lancamento = this.manager.merge(lancamento);
		this.manager.remove(lancamento);
		this.manager.flush();

		// LOGGING
		intra_log_geral log = new intra_log_geral((int) lancamento.getCondominio(), retornaIdUsuario(), "cndpagar",
				false, false, true, lancamento.toString(), new Date(), 0, String.valueOf(lancamento.getNrolancto()),
				null);
		this.log(log);
	}

	public financeiro_imagem pesquisarImagemPorEtiqueta(long etiqueta) {
		try {
			financeiro_imagem imagem = null;
			Query query = this.manager.createNativeQuery(
					"select codigo, cod_lancto_oma, data_vinculo_arq, id, id_tipoarquivo, identificacao_tipo_arq,"
							+ " img, nome_arquivo, usuario_vinculo_arq, cd_cnd  from omaonline.dbo.financeiro_imagem where id = :etiqueta");
			query.setParameter("etiqueta", etiqueta);
			Object[] aux = (Object[]) query.getSingleResult();
			imagem = new financeiro_imagem();
			if (aux[0] != null) {
				imagem.setCodigo(Integer.parseInt(aux[0].toString()));
			}

			if (aux[1] != null) {
				imagem.setCodLanctoOma(Integer.parseInt(aux[1].toString()));
			}

			if (aux[2] != null) {
				imagem.setDataVinculoArq((Date) aux[2]);
			}

			if (aux[3] != null) {
				imagem.setId(etiqueta);
			}

			if (aux[4] != null) {
				imagem.setId_tipoarquivo(Short.parseShort(aux[4].toString()));
			}

			if (aux[5] != null) {
				imagem.setIdentificacaoTipoArq(aux[5].toString());
			}

			if (aux[6] != null) {
				imagem.setImagem((byte[]) aux[6]);
			}

			if (aux[7] != null) {
				imagem.setNome_arquivo(aux[7].toString());
			}

			if (aux[8] != null) {
				imagem.setSuarioVinculoArq(aux[8].toString());
			}

			if (aux[9] != null) {
				imagem.setCdCnd(Short.parseShort(aux[9].toString()));
			}
			return imagem;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<nfe> pesquisaXML(String cnpj) {
		Query query = this.manager.createQuery("from nfe where cnpjEmit = :cnpj");
		query.setParameter("cnpj", cnpj);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios() {
		return this.manager.createQuery("from intra_condominios order by codigo").getResultList();
	}

	/**
	 * metodo utilizado para listar o fornecedor na base siga
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public cpcredor pesquisarFornecedorSiga(long cnpjCpf) {
		List<cpcredor> lcredor = new ArrayList<cpcredor>();
		Query query = this.manager.createNativeQuery(
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
	public List<atbancos> pesquisarBancosPorNome(String nome) {
		atbancos banco = new atbancos();
		List<atbancos> lstBancos = new ArrayList<>();
		Query query = this.manager
				.createNativeQuery("select codbanco, nomebanco from sigadm.dbo.atbancos where nomebanco like :p1");
		query.setParameter("p1", "%" + nome + "%");
		List<Object[]> lst = query.getResultList();
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
	public short listContCondo(short codigoCnd) {
		short conta = 0;
		int codigo = Integer.valueOf(codigoCnd);
		Query query = this.manager
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
		Query query = this.manager.createNativeQuery(
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
		Query query = this.manager.createNativeQuery(
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

	public int returnUltimoLancto() {
		int nrolancto = 0;
		String sql = "INSERT INTO [sigadm].[dbo].[sglctoscontas](origem)values(1)";
		try {
			rs = null;
			con = Conexao.getConexaoSiga();
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
	public List<DebitoAutomatico> pesquisaDebitoAutomatico(short condominio) {
		DebitoAutomatico da = null;
		List<DebitoAutomatico> listaDA = new ArrayList<>();
		Query query = this.manager.createNativeQuery(
				"select d.id_contrato, d.nro_identificacao, p.nrolancto, p.vencimento,p.estimado,p.condominio,"
						+ " p.conta, p.historico, p.credor, p.valor, f.id_fatura, f.mes, f.ano, d.id_servico from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto"
						+ " where p.vencimento >= GETDATE()-1 and p.tipopagto = '4' and d.condominio = :condominio"
						+ " order by p.vencimento DESC, d.id_contrato");
		query.setParameter("condominio", condominio);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			da = new DebitoAutomatico();

			if (aux[0] != null) {
				da.setId_contrato(Integer.parseInt(aux[0].toString()));
			}

			if (aux[1] != null) {
				da.setNro_identificacao(aux[1].toString());
			}

			if (aux[2] != null) {
				da.setNrolancto(Integer.parseInt(aux[2].toString()));
			}

			if (aux[3] != null) {
				da.setVencimento((Date) aux[3]);
			}

			if (aux[4] != null) {
				da.setEstimado(aux[4].toString());
			}

			if (aux[5] != null) {
				da.setCondominio(Short.parseShort(aux[5].toString()));
			}

			if (aux[6] != null) {
				da.setConta(Integer.parseInt(aux[6].toString()));
			}

			if (aux[7] != null) {
				da.setHistorico(aux[7].toString());
			}

			if (aux[8] != null) {
				da.setCredor(aux[8].toString());
			}

			if (aux[9] != null) {
				da.setValor(Double.parseDouble(aux[9].toString()));
			}

			if (aux[10] != null) {
				da.setIdFatura(Integer.parseInt(aux[10].toString()));
			}

			if (aux[11] != null) {
				da.setMes(Integer.parseInt(aux[11].toString()));
			}

			if (aux[12] != null) {
				da.setAno(Integer.parseInt(aux[12].toString()));
			}

			if (aux[13] != null) {
				da.setId_servico(Integer.parseInt(aux[13].toString()));
				da.setUm(this.pesquisaHistorico(da.getId_servico()));
				da.setComplemento(this.pesquisaComplemento(da.getId_contrato()));
			}

			listaDA.add(da);
		}
		return listaDA;
	}

	@SuppressWarnings("unchecked")
	public List<DebitoAutomatico> pesquisaDebitoAutomaticoAB(short condominio) {
		DebitoAutomatico da = null;
		List<DebitoAutomatico> listaDA = new ArrayList<>();
		Query query = this.manager.createNativeQuery(
				"select d.id_contrato, d.nro_identificacao, p.nrolancto, p.vencimento,p.estimado,p.condominio,"
						+ " p.conta, p.historico, p.credor, p.valor, f.id_fatura, f.mes, f.ano, d.id_servico from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto"
						+ " where p.vencimento between GETDATE()-10 and GETDATE()-1 and p.tipopagto = '4' and d.condominio = :condominio"
						+ " order by p.vencimento DESC, d.id_contrato");
		query.setParameter("condominio", condominio);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			da = new DebitoAutomatico();

			if (aux[0] != null) {
				da.setId_contrato(Integer.parseInt(aux[0].toString()));
			}

			if (aux[1] != null) {
				da.setNro_identificacao(aux[1].toString());
			}

			if (aux[2] != null) {
				da.setNrolancto(Integer.parseInt(aux[2].toString()));
			}

			if (aux[3] != null) {
				da.setVencimento((Date) aux[3]);
			}

			if (aux[4] != null) {
				da.setEstimado(aux[4].toString());
			}

			if (aux[5] != null) {
				da.setCondominio(Short.parseShort(aux[5].toString()));
			}

			if (aux[6] != null) {
				da.setConta(Integer.parseInt(aux[6].toString()));
			}

			if (aux[7] != null) {
				da.setHistorico(aux[7].toString());
			}

			if (aux[8] != null) {
				da.setCredor(aux[8].toString());
			}

			if (aux[9] != null) {
				da.setValor(Double.parseDouble(aux[9].toString()));
			}

			if (aux[10] != null) {
				da.setIdFatura(Integer.parseInt(aux[10].toString()));
			}

			if (aux[11] != null) {
				da.setMes(Integer.parseInt(aux[11].toString()));
			}

			if (aux[12] != null) {
				da.setAno(Integer.parseInt(aux[12].toString()));
			}

			if (aux[13] != null) {
				da.setId_servico(Integer.parseInt(aux[13].toString()));
				da.setUm(this.pesquisaHistorico(da.getId_servico()));
				da.setComplemento(this.pesquisaComplemento(da.getId_contrato()));
			}

			da.setAguardandoBaixa(true);

			listaDA.add(da);

		}
		return listaDA;
	}

	@SuppressWarnings("unchecked")
	public List<DebitoAutomatico> pesquisaDebitoAutomaticoPagos(short condominio) {
		DebitoAutomatico da = null;
		List<DebitoAutomatico> listaDA = new ArrayList<>();
		Query query = this.manager.createNativeQuery(
				"select d.id_contrato, d.nro_identificacao, l.nrolancto, g.numero,l.vencimento,l.condominio, l.conta,"
						+ " l.historico, l.credor, l.valor, f.id_fatura, f.mes, f.ano, d.id_servico from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndlanch l on f.nrolcto_contas = l.nrolancto"
						+ " inner join sigadm.dbo.cndespes g on l.nro_copia = g.copia_cheque"
						+ " where (l.vencimento >= GETDATE() -30 and g.data>= GETDATE() -30"
						+ " and f.vencimento >= GETDATE() -30 and l.tipopagto = '4' and d.condominio = :condominio"
						+ " and f.nrolcto_contas = g.nro_lanch) order by g.numero,d.id_contrato, l.vencimento DESC");
		query.setParameter("condominio", condominio);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			da = new DebitoAutomatico();

			if (aux[0] != null) {
				da.setId_contrato(Integer.parseInt(aux[0].toString()));
			}

			if (aux[1] != null) {
				da.setNro_identificacao(aux[1].toString());
			}

			if (aux[2] != null) {
				da.setNrolancto(Integer.parseInt(aux[2].toString()));
			}

			if (aux[3] != null) {
				da.setNroPagto(Integer.parseInt(aux[3].toString()));
			}

			if (aux[4] != null) {
				da.setVencimento((Date) aux[4]);
			}

			da.setEstimado("L");

			if (aux[5] != null) {
				da.setCondominio(Short.parseShort(aux[5].toString()));
			}

			if (aux[6] != null) {
				da.setConta(Integer.parseInt(aux[6].toString()));
			}

			if (aux[7] != null) {
				da.setHistorico(aux[7].toString());
			}

			if (aux[8] != null) {
				da.setCredor(aux[8].toString());
			}

			if (aux[9] != null) {
				da.setValor(Double.parseDouble(aux[9].toString()));
			}

			if (aux[10] != null) {
				da.setIdFatura(Integer.parseInt(aux[10].toString()));
			}

			if (aux[11] != null) {
				da.setMes(Integer.parseInt(aux[11].toString()));
			}

			if (aux[12] != null) {
				da.setAno(Integer.parseInt(aux[12].toString()));
			}

			if (aux[13] != null) {
				da.setId_servico(Integer.parseInt(aux[13].toString()));
				da.setUm(this.pesquisaHistorico(da.getId_servico()));
				da.setComplemento(this.pesquisaComplemento(da.getId_contrato()));
			}

			listaDA.add(da);
		}
		return listaDA;
	}

	public int salvarDebitoAutomatico(glbCatalogo_Lcto gcl) {
		int i = 0;
		if (!this.verificaExisteImagem(gcl.getId_lcto(), gcl.getTipo_lcto())) {
			Query query = this.manager.createNativeQuery(
					"INSERT INTO sigadm.dbo.glbCatalogo_Lcto(id_catalogo, id_lcto, tipo_lcto) VALUES (:p1,:p2,:p3)");
			query.setParameter("p1", gcl.getId_catalogo());
			query.setParameter("p2", gcl.getId_lcto());
			query.setParameter("p3", gcl.getTipo_lcto());
			i = query.executeUpdate();
		}

		// LOGGING
		intra_log_geral log = new intra_log_geral(0, retornaIdUsuario(), "sigadm.dbo.glbCatalogo_Lcto", true, false,
				false, gcl.toString(), new Date(), 0, null, null);
		this.log(log);

		return i;
	}

	@SuppressWarnings("unchecked")
	public boolean verificaExisteImagem(int id_lcto, String tipo_lcto) {
		Query query = this.manager
				.createNativeQuery("select * from sigadm.dbo.glbCatalogo_Lcto where id_lcto = :p1 and tipo_lcto = :p2");
		query.setParameter("p1", id_lcto);
		query.setParameter("p2", tipo_lcto);
		List<Object[]> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void updateConsumo1(double consumo, int mes, int ano, int nrolancto) {
		Query query = this.manager
				.createNativeQuery("update sigadm.dbo.cndfaturasmensais set consumo = :p1, mes = :p2, ano = :p3"
						+ " from sigadm.dbo.cndfaturasmensais f inner join sigadm.dbo.cndpagtosmensais d on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto where p.nrolancto = :p4");
		query.setParameter("p1", consumo);
		query.setParameter("p2", mes);
		query.setParameter("p3", ano);
		query.setParameter("p4", nrolancto);
		query.executeUpdate();

		// LOGGING
		intra_log_geral log = new intra_log_geral(0, retornaIdUsuario(), "sigadm.dbo.cndfaturasmensais", false, true,
				false, "UPDATE CONSUMO 1:" + consumo + ";mes:" + mes + ";ano:" + ano + ";nrolancto:" + nrolancto,
				new Date(), 0, String.valueOf(nrolancto), null);
		this.log(log);
	}

	public void updateHistorico1(int nrolancto, String historico) {
		Query query = this.manager
				.createNativeQuery("update sigadm.dbo.cndpagar set historico = :p1 where nrolancto = :p2");
		query.setParameter("p1", historico);
		query.setParameter("p2", nrolancto);
		query.executeUpdate();

		// LOGGING
		intra_log_geral log = new intra_log_geral(0, retornaIdUsuario(), "sigadm.dbo.cndpagar", false, true, false,
				"UPDATE HISTÓRICO 1:" + historico + ";nrolancto:" + nrolancto, new Date(), 0, String.valueOf(nrolancto),
				null);
		this.log(log);
	}

	public void updateConsumo2(double consumo, int mes, int ano, int nrolancto) {
		Query query = this.manager
				.createNativeQuery("update sigadm.dbo.cndfaturasmensais set consumo = :p1, mes = :p2, ano = :p3"
						+ " from sigadm.dbo.cndfaturasmensais f inner join sigadm.dbo.cndpagtosmensais d on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndlanch p on f.nrolcto_contas = p.nrolancto where p.nrolancto = :p4");
		query.setParameter("p1", consumo);
		query.setParameter("p2", mes);
		query.setParameter("p3", ano);
		query.setParameter("p4", nrolancto);
		query.executeUpdate();

		// LOGGING
		intra_log_geral log = new intra_log_geral(0, retornaIdUsuario(), "sigadm.dbo.cndfaturasmensais", false, true,
				false, "UPDATE CONSUMO 2:" + consumo + ";mes:" + mes + ";ano:" + ano + ";nrolancto:" + nrolancto,
				new Date(), 0, String.valueOf(nrolancto), null);
		this.log(log);
	}

	public void updateHistorico2(int nrolancto, String historico) {
		Query query = this.manager
				.createNativeQuery("update sigadm.dbo.cndlanch set historico = :p1 where nrolancto = :p2");
		query.setParameter("p1", historico);
		query.setParameter("p2", nrolancto);
		query.executeUpdate();

		query = this.manager.createNativeQuery("update sigadm.dbo.cndespes set historico = :p1 where nro_lanch = :p2");
		query.setParameter("p1", historico);
		query.setParameter("p2", nrolancto);
		query.executeUpdate();

		// LOGGING
		intra_log_geral log = new intra_log_geral(0, retornaIdUsuario(), "sigadm.dbo.cndpagar", false, true, false,
				"UPDATE HISTÓRICO 2:" + historico + ";nrolancto:" + nrolancto, new Date(), 0, String.valueOf(nrolancto),
				null);
		this.log(log);
	}

	@SuppressWarnings("unchecked")
	public UnidadeMedida pesquisaHistorico(int id_servico) {
		UnidadeMedida u = null;
		Query query = this.manager
				.createNativeQuery("select distinct(t.obs), t.unidade_medida from sigadm.dbo.cndfaturasmensais f"
						+ " inner join sigadm.dbo.cndpagtosmensais p on f.id_contrato = p.id_contrato"
						+ " inner join sigadm.dbo.cndtpsrv t on p.id_servico = t.codigo where p.id_servico = :p1");
		query.setParameter("p1", id_servico);
		for (Object[] aux : (List<Object[]>) query.getResultList()) {
			u = new UnidadeMedida();
			if (aux[0] != null) {
				u.setObs(aux[0].toString());
			}
			if (aux[1] != null) {
				u.setUnidade_medida(aux[1].toString());
			}
		}
		return u;
	}

	public void log(intra_log_geral log) {
		this.manager.persist(log);
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

	public List<cndpagar> pesquisarDuplicidadeTotal(LancamentoView lv) {
		short condo = (short) lv.getCondominio().getCodigo();
		double valor = 0;
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		try {
			if (!lv.getValor().trim().isEmpty()) {
				lv.setValor(lv.getValor().replace("R$ ", ""));
				valor = df.parse(lv.getValor()).doubleValue();
			} else {
				valor = 0.0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		TypedQuery<cndpagar> query2 = this.manager.createQuery(
				"from cndpagar where condominio = :p1 and fornecedor_cnpj = :p2 and vencimento = :p3 and numero_nf = :p4 and valor_lancto = :p5",
				cndpagar.class);
		query2.setParameter("p1", condo);
		query2.setParameter("p2", lv.getCnpj());
		query2.setParameter("p3", lv.getVencimento());
		if (lv.getNumeroNotaFiscal() == null || lv.getNumeroNotaFiscal().trim().isEmpty()) {
			query2.setParameter("p4", 0);
		} else {
			query2.setParameter("p4", BigInteger.valueOf(Long.valueOf(lv.getNumeroNotaFiscal())));
		}
		query2.setParameter("p5", valor);

		return query2.getResultList();
	}

	public List<cndpagar> pesquisarDuplicidade(LancamentoView lv) {
		short condo = (short) lv.getCondominio().getCodigo();
		double valor = 0;
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		try {
			if (!lv.getValor().trim().isEmpty()) {
				lv.setValor(lv.getValor().replace("R$ ", ""));
				valor = df.parse(lv.getValor()).doubleValue();
			} else {
				valor = 0.0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		TypedQuery<cndpagar> query3 = this.manager.createQuery(
				"from cndpagar where condominio = :p1 and fornecedor_cnpj = :p2 and vencimento = :p3 and numero_nf = :p4",
				cndpagar.class);
		query3.setParameter("p1", condo);
		query3.setParameter("p2", lv.getCnpj());
		query3.setParameter("p3", lv.getVencimento());
		if (lv.getNumeroNotaFiscal() == null || lv.getNumeroNotaFiscal().trim().isEmpty()) {
			query3.setParameter("p4", 0);
		} else {
			query3.setParameter("p4", BigInteger.valueOf(Long.valueOf(lv.getNumeroNotaFiscal())));
		}

		TypedQuery<cndpagar> query4 = this.manager.createQuery(
				"from cndpagar where condominio = :p1 and fornecedor_cnpj = :p2 and vencimento = :p3 and valor_lancto = :p4",
				cndpagar.class);
		query4.setParameter("p1", condo);
		query4.setParameter("p2", lv.getCnpj());
		query4.setParameter("p3", lv.getVencimento());
		query4.setParameter("p4", valor);

		TypedQuery<cndpagar> query5 = this.manager.createQuery(
				"from cndpagar where condominio = :p1 and fornecedor_cnpj = :p2 and vencimento = :p3", cndpagar.class);
		query5.setParameter("p1", condo);
		query5.setParameter("p2", lv.getCnpj());
		query5.setParameter("p3", lv.getVencimento());

		TypedQuery<cndpagar> query1 = this.manager
				.createQuery("from cndpagar where condominio = :p1 and fornecedor_cnpj = :p2", cndpagar.class);
		query1.setParameter("p1", condo);
		query1.setParameter("p2", lv.getCnpj());

		if (!query3.getResultList().isEmpty()) {
			return query3.getResultList();
		} else if (!query4.getResultList().isEmpty()) {
			return query4.getResultList();
		} else if (!query5.getResultList().isEmpty()) {
			return query5.getResultList();
		}
		if (!query1.getResultList().isEmpty()) {
			return query1.getResultList();
		} else {
			return query1.getResultList();
		}

	}

	@SuppressWarnings("unchecked")
	private String pesquisaComplemento(int id_servico) {
		String complemento = "";
		Query query = this.manager.createNativeQuery("select p.cp_hist_complemento from sigadm.dbo.cndfaturasmensais f"
				+ " inner join sigadm.dbo.cndpagtosmensais p on f.id_contrato = p.id_contrato"
				+ " inner join sigadm.dbo.cndtpsrv t on p.id_servico = t.codigo where p.id_contrato = :p1");
		query.setParameter("p1", id_servico);
		for (Object aux : (List<Object>) query.getResultList()) {
			if (aux != null) {
				complemento = aux.toString();
			}
			System.out.println(aux);
		}
		return complemento;
	}

	public List<cndpagar> listarPendencias(intra_condominios intra_condominios) {
		short condo = (short) intra_condominios.getCodigo();
		TypedQuery<cndpagar> query = this.manager.createQuery(
				"from cndpagar where condominio = :p1 and tipo_lancamento between 2 and 3", cndpagar.class);
		query.setParameter("p1", condo);
		return query.getResultList();
	}

	public void unificarLancto(cndpagar pagar) {
		this.manager.merge(pagar);
	}

	public periodo_lancamento pesquisarPeriodo() {
		TypedQuery<periodo_lancamento> query = this.manager.createQuery("from periodo_lancamento",
				periodo_lancamento.class);
		return query.getSingleResult();
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

	@SuppressWarnings("unchecked")
	public cndplano pesquisaContaContabil(int conta) {
		List<cndplano> lpconta = new ArrayList<cndplano>();
		Query query = this.manager.createNativeQuery(
				"select  cod_reduzido, codigo_grafico, conta, sub_conta, nome, nome_realizado, tipo, grau, codigo_plano from sigadm.dbo.cndplano where conta = :conta");
		query.setParameter("conta", conta);
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
		if (lpconta != null && lpconta.size() > 0) {
			return lpconta.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public cpcredor pesquisarFornecedorUsualcred(String usualcred) {
		List<cpcredor> lcredor = new ArrayList<cpcredor>();
		Query query = this.manager.createNativeQuery(
				"select usualcred, nome, tipo_inscricao, inscricao from sigadm.dbo.cpcredor where usualcred = :usualcred");
		query.setParameter("usualcred", usualcred);
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
	public cpfavor pesquisarFavorecido(int codigo) {
		List<cpfavor> lfavor = new ArrayList<cpfavor>();
		Query query = this.manager.createNativeQuery(
				"select [codigo],[favorecido],[tipo_pessoa],[cnpj_cpf],[banco],[agencia],[conta_corrente],[dac_conta],[conta_poupanca],[tipo_conta], b.nomebanco "
						+ "from sigadm.dbo.cpfavor f inner join sigadm.dbo.atbancos b on f.banco = b.codbanco "
						+ "where  codigo = :p1");
		query.setParameter("p1", codigo);
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
		if (lfavor != null && lfavor.size() > 0) {
			return lfavor.get(0);
		} else {
			return null;
		}
	}
}
