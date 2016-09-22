package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cndpagar_followup;
import br.com.oma.omaonline.entidades.cpcredor;
import br.com.oma.sigadm.entidades.sgimpos;
import br.com.oma.sigadm.entidades.sgltimp;
import br.com.oma.sigadm.entidades.sgreten;

public class AnaliseFiscalDAO {

	private EntityManager manager;
	private Connection con;
	private PreparedStatement pmst;
	private ResultSet rs;

	@Inject
	UserTransaction tx;

	public AnaliseFiscalDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void aprovarAnaliseFiscal(cndpagar lancamento, SessaoMB sessao) {
		this.manager.merge(lancamento);
		this.registrarFollowUp(lancamento.getCodigo(), "Inserido", sessao.getUsuario().getEmail(), "oma",
				lancamento.getCondominio(), "Fiscal");
	}

	public cndpagar getLancamento(int codigoLancto) {
		return this.manager.find(cndpagar.class, codigoLancto);
	}

	public void excluirLancamento(cndpagar lancamento) {
		lancamento = this.manager.merge(lancamento);
		this.manager.remove(lancamento);
	}

	@SuppressWarnings("unchecked")
	public cpcredor pesquisarFornecedor(String inscricao) {
		Query query = this.manager.createQuery("from cpcredor where inscricao = :p1");
		query.setParameter("p1", inscricao);
		List<cpcredor> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	/**
	 * metodo utilizado para listar o fornecedor na base siga
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public cpcredor pesquisarFornecedorSiga(String usualcred) {
		List<cpcredor> lcredor = new ArrayList<cpcredor>();
		Query query = this.manager.createNativeQuery("select usualcred, nome, tipo_inscricao, inscricao, perc_pis,"
				+ " perc_cofins, perc_csll, perc_inss, perc_irrf,"
				+ " reter_impostos, reter_inss, reter_irrf, reter_iss, ccm, cod_munic, observacoes from sigadm.dbo.cpcredor where usualcred = :p1");
		query.setParameter("p1", usualcred);
		// query.setParameter("p2", new
		// DateTime().withYear(2010).withMonthOfYear(5).withDayOfMonth(20).toDate());
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

			if (obj[4] != null) {
				cre.setPerc_pis(Double.parseDouble(obj[4].toString()));
			}

			if (obj[5] != null) {
				cre.setPerc_cofins(Double.parseDouble(obj[5].toString()));
			}

			if (obj[6] != null) {
				cre.setPerc_csll(Double.parseDouble(obj[6].toString()));
			}

			if (obj[7] != null) {
				cre.setPerc_inss(Double.parseDouble(obj[7].toString()));
			}

			if (obj[8] != null) {
				cre.setPerc_irrf(Double.parseDouble(obj[8].toString()));
			}

			if (obj[9] != null && obj[9].toString().trim().equals("S")) {
				cre.setReter_impostos(true);
			}

			if (obj[10] != null) {
				cre.setReter_inss(Boolean.parseBoolean(obj[10].toString()));
			}

			if (obj[11] != null) {
				cre.setReter_irrf(Boolean.parseBoolean(obj[11].toString()));
			}

			if (obj[12] != null) {
				cre.setReter_iss(Boolean.parseBoolean(obj[12].toString()));
			}
			if (obj[13] != null) {
				cre.setCcm(obj[13].toString());
			}
			if (obj[14] != null) {
				cre.setCod_munic(Integer.valueOf(obj[14].toString()));
			}
			if (obj[15] != null) {
				cre.setObservacoes(obj[15].toString());
			}

			lcredor.add(cre);
		}
		List<cpcredor> lista = lcredor;
		if (!lista.isEmpty()) {
			return lista.get(lista.size() - 1);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> getListaPreLancamento() {
		Query query = this.manager.createQuery("from cndpagar where status_sip > 0 order by vencimento");
		return query.getResultList();
	}

	public int returnUltimoLancto() {
		int nrolancto = 0;
		String sql = "INSERT INTO sglctoscontas(origem)values(1)";
		try {
			rs = null;
			// con = Conexao.getConexaoSiga();
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
	public double pesquisarCondominioCNPJ(short condominio) {
		double cnpjCondominio = 0.0;
		Query query = this.manager
				.createNativeQuery("select cgc from sigadm.dbo.cndcondo where codigo = " + condominio);
		List<Object> lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (Object obj : lista) {
				cnpjCondominio = Double.valueOf(obj.toString());
			}
		}
		return cnpjCondominio;
	}

	public void salvarSGRETEN(sgreten retenBean) {
		this.manager.persist(retenBean);
	}

	public void salvarSGLTIMP(sgltimp timpBean) {
		try {
			String sql = "INSERT INTO sgltimp ([nrolancto],[data_inclusao],[codigo],[bloco],[vencimento],[valor],[historico],[cta_bancaria],[credor],"
					+ "[data_base],[documento],[total_retencao],[sistema],[contrato],[imovel],[repassar],[div_propriet],[cpmf],[favorecido],[data_alteracao],[nrolancto_aux])"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)";

			// con = Conexao.getConexaoSiga();
			// con = Conexao.getConexaoSIP();
			con = Conexao.getConexaoOmaonline();

			pmst = con.prepareStatement(sql);
			pmst.setInt(1, timpBean.getNrolancto());
			pmst.setDate(2, new java.sql.Date(timpBean.getData_inclusao().getTime()));
			pmst.setInt(3, timpBean.getCodigo());
			pmst.setString(4, timpBean.getBloco());
			pmst.setDate(5, new java.sql.Date(timpBean.getVencimento().getTime()));
			pmst.setDouble(6, timpBean.getValor());
			pmst.setString(7, timpBean.getHistorico());
			pmst.setInt(8, timpBean.getCta_bancaria());
			pmst.setString(9, timpBean.getCredor());
			pmst.setDate(10, new java.sql.Date(timpBean.getData_base().getTime()));
			pmst.setString(11, timpBean.getDocumento());
			pmst.setDouble(12, timpBean.getTotal_retencao());
			pmst.setInt(13, timpBean.getSistema());
			pmst.setInt(14, timpBean.getContrato());
			pmst.setInt(15, timpBean.getImovel());
			pmst.setString(16, timpBean.getRepassar());
			pmst.setString(17, timpBean.getDiv_propriet());
			pmst.setString(18, timpBean.getCpmf());
			pmst.setString(19, timpBean.getFavorecido());
			pmst.setDate(20, null);

			pmst.executeUpdate();
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

		// this.manager.persist(timpBean);
	}

	public void salvarSGIMPOS(sgimpos imposBean) {
		try {
			String sql = "INSERT INTO sgimpos ([nrolancto],[tipo_imposto],[cod_receita],[vencto],[percentual],[valor],[cod_ccm],"
					+ "[obs_1],[obs_2],[lancto_gerado],[dt_base],[cod_municip],[controle],[vencto_guia],[sistema],[conta_contabil],[tipopagto],"
					+ "[valor_a_pagar],[data_alteracao],[numero_nf],[item_nf]) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// con = Conexao.getConexaoSiga();
			// con = Conexao.getConexaoSIP();
			con = Conexao.getConexaoOmaonline();

			pmst = con.prepareStatement(sql);
			pmst.setInt(1, imposBean.getNrolancto());
			pmst.setInt(2, imposBean.getTipo_imposto());
			pmst.setDouble(3, imposBean.getCod_receita());
			pmst.setDate(4, new java.sql.Date(imposBean.getVencto().getTime()));
			pmst.setDouble(5, imposBean.getPercentual());
			pmst.setDouble(6, imposBean.getValor());
			pmst.setString(7, imposBean.getCod_ccm());
			pmst.setString(8, imposBean.getObs_1());
			pmst.setString(9, imposBean.getObs_2());
			pmst.setInt(10, imposBean.getLancto_gerado());
			pmst.setDate(11, new java.sql.Date(imposBean.getDt_base().getTime()));
			pmst.setInt(12, imposBean.getCod_municip());
			pmst.setInt(13, imposBean.getControle());
			pmst.setDate(14, null);
			pmst.setInt(15, imposBean.getSistema());
			pmst.setInt(16, imposBean.getConta_contabil());
			pmst.setString(17, imposBean.getTipopagto());
			pmst.setDouble(18, imposBean.getValor_a_pagar());
			pmst.setDate(19, null);
			pmst.setString(20, imposBean.getNumero_nf());
			pmst.setInt(21, imposBean.getItem_nf());

			pmst.executeUpdate();
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

		// this.manager.persist(imposBean);
	}

	public void excluirImpostosExistentes(int nrolancto) {
		try {
			String sgreten = "delete sgreten where nrolancto = ?";
			// con = Conexao.getConexaoSiga();
			con = Conexao.getConexaoSIP();
			pmst = con.prepareStatement(sgreten);
			pmst.setInt(1, nrolancto);

			pmst.executeUpdate();
			pmst.close();
			con.close();

			String sgimpos = "delete sgimpos where nrolancto = ?";
			// con = Conexao.getConexaoSiga();
			con = Conexao.getConexaoSIP();
			pmst = con.prepareStatement(sgimpos);
			pmst.setInt(1, nrolancto);

			pmst.executeUpdate();
			pmst.close();
			con.close();

			String sgltimp = "delete sgltimp where nrolancto = ?";
			// con = Conexao.getConexaoSiga();
			con = Conexao.getConexaoSIP();
			pmst = con.prepareStatement(sgltimp);
			pmst.setInt(1, nrolancto);

			pmst.executeUpdate();
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

	}

	public void updateLancamento(cndpagar lancamento) {
		this.manager.merge(lancamento);
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
	public List<sgreten> listarReten(int nrolancto) {
		Query query = this.manager.createQuery("from sgreten where nrolancto = :p1");
		query.setParameter("p1", nrolancto);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<sgltimp> listarTimp(int nrolancto) {
		Query query = this.manager.createQuery("from sgltimp where nrolancto = :p1");
		query.setParameter("p1", nrolancto);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<sgimpos> listarImpos(int nrolancto) {
		Query query = this.manager.createQuery("from sgimpos where nrolancto = :p1");
		query.setParameter("p1", nrolancto);
		return query.getResultList();
	}

	public void reprovar(cndpagar lancamento, SessaoMB sessaoMB, String obs) {
		this.manager.merge(lancamento);
		this.registrarFollowUp(lancamento.getCodigo(), "Reprovado", sessaoMB.getUsuario().getEmail(), "oma",
				lancamento.getCondominio(), obs);
	}

}
