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
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_prepax;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.sigadm.entidades.actavlan;
import br.com.oma.sigadm.entidades.cndavlan;
import br.com.oma.intranet.util.JPAUtil;

public class PrepaxDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3326406135692777723L;
	private Query query;
	private EntityManager manager;
	private intra_log_geral ilg = new intra_log_geral();
	private Date data;
	private PreparedStatement pmst;
	private Connection con;
	private ResultSet rs;

	public PrepaxDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios() {
		this.query = this.manager.createQuery("from intra_condominios ");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_prepax> listaRelatorio900(int mesP, int mesI, int ano, Date dataPagto, int codigo) {
		List<intra_prepax> listaPrepax = new ArrayList<intra_prepax>();
		this.query = this.manager.createNativeQuery(
				"select f.conta_bancaria,c.cpf, p.valor, f.empresa,f.funcionario,c.nome, p.codigo, p.mes as RP, i.mes as C, i.ano, x.cod_conta, i.data_pagto, "
						+ "i.imposto_renda, i.arredondamento from sigadm.dbo.gsffolha p "
						+ "inner join sigadm.dbo.gsffunc f on p.funcionario = f.funcionario "
						+ "inner join sigadm.dbo.gsfpessoa c on f.cod_pessoa = c.codigo "
						+ "inner join sigadm.dbo.gsfempre e on p.empresa = e.codigo "
						+ "inner join sigadm.dbo.gsfirend i on f.empresa = i.empresa "
						+ "inner join sigadm.dbo.cndcondo x on x.codigo = i.empresa "
						+ "where f.agencia_salario = '111' and p.mes = :p1 and  i.mes = :p2 and p.ano = :p3 and i.ano = :p3 and p.codigo = :p5 "
						+ "and p.empresa = f.empresa and i.funcionario = p.funcionario and i.data_pagto = :p4 "
						+ "order by f.empresa,f.funcionario,i.ano, i.mes ");
		this.query.setParameter("p1", mesP);
		this.query.setParameter("p2", mesI);
		this.query.setParameter("p3", ano);
		this.query.setParameter("p4", dataPagto);
		this.query.setParameter("p5", codigo);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_prepax ip = new intra_prepax();
				if (obj[0] != null) {
					ip.setConta_bancaria(obj[0].toString());
				}
				if (obj[1] != null) {
					if (obj[1].toString().length() == 11) {
						ip.setCpf(obj[1].toString());
					} else if (obj[1].toString().length() == 10) {
						ip.setCpf("0" + obj[1].toString());
					} else if (obj[1].toString().length() == 9) {
						ip.setCpf("00" + obj[1].toString());
					} else if (obj[1].toString().length() == 8) {
						ip.setCpf("000" + obj[1].toString());
					} else if (obj[1].toString().length() == 7) {
						ip.setCpf("0000" + obj[1].toString());
					} else if (obj[1].toString().length() == 6) {
						ip.setCpf("00000" + obj[1].toString());
					} else if (obj[1].toString().length() == 5) {
						ip.setCpf("000000" + obj[1].toString());
					}

				}
				if (obj[2] != null) {
					ip.setValor(Double.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					ip.setEmpresa(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					ip.setFuncionario(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					ip.setNome(obj[5].toString());
				}
				if (obj[6] != null) {
					ip.setCodigo(Integer.valueOf(obj[6].toString()));
				}
				if (obj[7] != null) {
					ip.setMesP(Integer.valueOf(obj[7].toString()));
				}
				if (obj[8] != null) {
					ip.setMesI(Integer.valueOf(obj[8].toString()));
				}
				if (obj[9] != null) {
					ip.setAno(Integer.valueOf(obj[9].toString()));
				}
				if (obj[10] != null) {
					ip.setCod_conta(Integer.valueOf(obj[10].toString()));
				}
				if (obj[11] != null) {
					ip.setData_pagto((Date) obj[11]);
				}
				if (obj[12] != null) {
					ip.setImposto_renda(Double.valueOf(obj[12].toString()));
				} else {
					ip.setImposto_renda(0);
				}
				if (obj[13] != null) {
					ip.setArredondamento(Double.valueOf(obj[13].toString()));
				} else {
					ip.setArredondamento(0);
				}
				listaPrepax.add(ip);
			}
		}
		return listaPrepax;
	}

	@SuppressWarnings("unchecked")
	public List<intra_prepax> listaRelatorio123(int mesP, int mesI, int ano, Date dataPagto, int codigo) {
		List<intra_prepax> listaPrepax = new ArrayList<intra_prepax>();
		this.query = this.manager.createNativeQuery(
				"select f.conta_bancaria,c.cpf, i.rendimentos, f.empresa,f.funcionario,c.nome, p.codigo, p.mes as RP, i.mes as C, i.ano, x.cod_conta, i.data_pagto, "
						+ "i.imposto_renda, i.arredondamento from sigadm.dbo.gsffolha p "
						+ "inner join sigadm.dbo.gsffunc f on p.funcionario = f.funcionario "
						+ "inner join sigadm.dbo.gsfpessoa c on f.cod_pessoa = c.codigo "
						+ "inner join sigadm.dbo.gsfempre e on p.empresa = e.codigo "
						+ "inner join sigadm.dbo.gsfirend i on f.empresa = i.empresa "
						+ "inner join sigadm.dbo.cndcondo x on x.codigo = i.empresa "
						+ "where f.agencia_salario = '111' and p.mes = :p1 and  i.mes = :p2 and p.ano = :p3 and i.ano = :p3 and p.codigo = :p5 "
						+ "and p.empresa = f.empresa and i.funcionario = p.funcionario and i.data_pagto = :p4 "
						+ "order by f.empresa,f.funcionario,i.ano, i.mes ");
		this.query.setParameter("p1", mesP);
		this.query.setParameter("p2", mesI);
		this.query.setParameter("p3", ano);
		this.query.setParameter("p4", dataPagto);
		this.query.setParameter("p5", codigo);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_prepax ip = new intra_prepax();
				if (obj[0] != null) {
					ip.setConta_bancaria(obj[0].toString());
				}
				if (obj[1] != null) {
					if (obj[1].toString().length() == 11) {
						ip.setCpf(obj[1].toString());
					} else if (obj[1].toString().length() == 10) {
						ip.setCpf("0" + obj[1].toString());
					} else if (obj[1].toString().length() == 9) {
						ip.setCpf("00" + obj[1].toString());
					} else if (obj[1].toString().length() == 8) {
						ip.setCpf("000" + obj[1].toString());
					} else if (obj[1].toString().length() == 7) {
						ip.setCpf("0000" + obj[1].toString());
					} else if (obj[1].toString().length() == 6) {
						ip.setCpf("00000" + obj[1].toString());
					} else if (obj[1].toString().length() == 5) {
						ip.setCpf("000000" + obj[1].toString());
					}
				}
				if (obj[2] != null) {
					double valor = 0;
					if (obj[12] == null && obj[13] == null) {
						ip.setImposto_renda(0);
						ip.setArredondamento(0);
						valor = ((Double.valueOf(obj[2].toString()) + ip.getArredondamento()) - ip.getImposto_renda());
						ip.setValor(valor);

					} else if (obj[12] == null && obj[13] != null) {
						ip.setImposto_renda(0);
						valor = (Double.valueOf(obj[2].toString()) + Double.valueOf(obj[13].toString())
								- ip.getImposto_renda());
						ip.setValor(valor);

					} else if (obj[12] != null && obj[13] == null) {
						ip.setArredondamento(0);
						valor = ((Double.valueOf(obj[2].toString()) + ip.getArredondamento())
								- Double.valueOf(obj[12].toString()));
						ip.setValor(valor);

					} else {
						valor = (Double.valueOf(obj[2].toString()) + Double.valueOf(obj[13].toString())
								- Double.valueOf(obj[12].toString()));
						ip.setValor(valor);
					}
				}
				if (obj[3] != null) {
					ip.setEmpresa(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					ip.setFuncionario(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					ip.setNome(obj[5].toString());
				}
				if (obj[6] != null) {
					ip.setCodigo(Integer.valueOf(obj[6].toString()));
				}
				if (obj[7] != null) {
					ip.setMesP(Integer.valueOf(obj[7].toString()));
				}
				if (obj[8] != null) {
					ip.setMesI(Integer.valueOf(obj[8].toString()));
				}
				if (obj[9] != null) {
					ip.setAno(Integer.valueOf(obj[9].toString()));
				}
				if (obj[10] != null) {
					ip.setCod_conta(Integer.valueOf(obj[10].toString()));
				}
				if (obj[11] != null) {
					ip.setData_pagto((Date) obj[11]);
				}
				if (obj[12] != null) {
					ip.setImposto_renda(Double.valueOf(obj[12].toString()));
				} else {
					ip.setImposto_renda(0);
				}
				if (obj[13] != null) {
					ip.setArredondamento(Double.valueOf(obj[13].toString()));
				} else {
					ip.setArredondamento(0);
				}
				listaPrepax.add(ip);
			}
		}
		return listaPrepax;

	}

	/*
	 * public void adicionarCndespes(cndespes cndespes, SessaoMB sessaoMB) {
	 * 
	 * this.query = this.manager.createNativeQuery(
	 * "insert into sigadm.dbo.cndespes (condominio, bloco, data, conta, sinal, valor, contra_partida, cpmf, historico, cta_anl_financ) "
	 * + "values (:p1, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9, :p10) ");
	 * this.query.setParameter("p1", cndespes.getCondominio());
	 * this.query.setParameter("p2", cndespes.getBloco());
	 * this.query.setParameter("p3", cndespes.getData());
	 * this.query.setParameter("p4", cndespes.getConta());
	 * this.query.setParameter("p5", cndespes.getSinal());
	 * this.query.setParameter("p6", cndespes.getValor());
	 * this.query.setParameter("p7", cndespes.getConta_partida());
	 * this.query.setParameter("p8", cndespes.getCpmf());
	 * this.query.setParameter("p9", cndespes.getHistorico());
	 * this.query.setParameter("p10", cndespes.getCta_anl_financ());
	 * this.query.executeUpdate();
	 * 
	 * this.ilg = new intra_log_geral(); data = new Date();
	 * this.ilg.setCondominio(cndespes.getCondominio());
	 * this.ilg.setIp(sessaoMB.getIpUser()); this.ilg.setDataFeito(data);
	 * this.ilg.setDeletar(false); this.ilg.setAlterar(false);
	 * 
	 * this.ilg.setInserir(true);
	 * this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
	 * this.ilg.setTabela("sigadm.dbo.cndespes"); this.ilg.setInfoAnterior(
	 * "Adicionado " + cndespes.getNumero()); this.logGeral(this.ilg); this.ilg
	 * = new intra_log_geral();
	 * 
	 * }
	 */

	public int returnUltimoLancto() {
		int nrolancto = 0;
		String sql = "INSERT INTO sglctoscontas(origem)values(9)";
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

	public void adicionarCndavlan(cndavlan c, SessaoMB s) {
		this.query = this.manager
				.createNativeQuery("insert into sigadm.dbo.cndavlan (nrolancto, condominio, bloco, conta, cod_histo, "
						+ "valor, data, contab_debito, contab_credito, deb_cre, cpmf, cta_anl_financ, baixado_caixa, incide_tx_adm, "
						+ "usuario, login_user, historico) values (:p1, :p2, :p3, :p4, :p5, :p6, :p7, :p9, :p10, :p11, :p12, :p13, :p14, :p15, :p16, :p17, :p18)");
		this.query.setParameter("p1", c.getNrolancto());
		this.query.setParameter("p2", c.getCondominio());
		this.query.setParameter("p3", c.getBloco());
		this.query.setParameter("p4", c.getConta());
		this.query.setParameter("p5", c.getCod_histo());
		this.query.setParameter("p6", c.getValor());
		this.query.setParameter("p7", c.getData());
		this.query.setParameter("p9", c.getContab_debito());
		this.query.setParameter("p10", c.getContab_credito());
		this.query.setParameter("p11", c.getDeb_cre());
		this.query.setParameter("p12", c.getCpmf());
		this.query.setParameter("p13", c.getCta_anl_financ());
		this.query.setParameter("p14", c.getBaixado_caixa());
		this.query.setParameter("p15", c.getIncide_tx_adm());
		this.query.setParameter("p16", c.getUsuario());
		this.query.setParameter("p17", c.getLogin_user());
		this.query.setParameter("p18", c.getHistorico());
		this.query.executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(c.getCondominio());
		this.ilg.setIp(s.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(s.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndavlan");
		this.ilg.setInfoAnterior("Lançamento PREPAX adicionado " + c.getNrolancto());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public int returnUltimoLanctoOMA() {
		int nrolancto = 0;
		String sql = "INSERT INTO sglctoscontas(origem)values(11)";
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

	public void adicionarCndavlanOMA(actavlan a, SessaoMB s) {
		this.query = this.manager.createNativeQuery(
				"insert into sigadm.dbo.actavlan (numero, empresa, data, cta_debito, cta_credito, ccusto_deb, ccusto_cre, valor, historico, "
						+ "data_inclusao, cod_usuario, usuario) values (:p1, :p2, :p3, :p4, :p5, :p6, :p7, :p8, :p9, :p11, :p12, :p14)");
		this.query.setParameter("p1", a.getNumero());
		this.query.setParameter("p2", a.getEmpresa());
		this.query.setParameter("p3", a.getData());
		this.query.setParameter("p4", a.getCta_debito());
		this.query.setParameter("p5", a.getCta_credito());
		this.query.setParameter("p6", a.getCcusto_deb());
		this.query.setParameter("p7", a.getCcusto_cre());
		this.query.setParameter("p8", a.getValor());
		this.query.setParameter("p9", a.getHistorico());
		this.query.setParameter("p11", a.getData_inclusao());
		this.query.setParameter("p12", a.getCod_usuario());
		this.query.setParameter("p14", a.getUsuario());
		this.query.executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(a.getEmpresa());
		this.ilg.setIp(s.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(s.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.actavlan");
		this.ilg.setInfoAnterior("Lançamento PREPAX adicionado " + a.getNumero());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	@SuppressWarnings("unchecked")
	public List<cndavlan> listaDebito(Date data, int conta) {
		List<cndavlan> listaDebito = new ArrayList<cndavlan>();
		this.query = this.manager.createNativeQuery(
				"select nrolancto, historico, valor from sigadm.dbo.cndavlan where data = :p1 and conta = :p2");
		this.query.setParameter("p1", data);
		this.query.setParameter("p2", conta);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				cndavlan c = new cndavlan();
				if (obj[0] != null) {
					c.setNrolancto(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					c.setHistorico(obj[1].toString());
				}
				if (obj[2] != null) {
					c.setValor(Double.valueOf(obj[2].toString()));
				}
				listaDebito.add(c);
			}
		}
		return listaDebito;
	}

	@SuppressWarnings("unchecked")
	public List<actavlan> listaCredito(Date data) {
		List<actavlan> listaCredito = new ArrayList<actavlan>();
		this.query = this.manager.createNativeQuery(
				"select numero, historico, valor from sigadm.dbo.actavlan where data = :p1 and cod_usuario = 763091");
		this.query.setParameter("p1", data);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				actavlan a = new actavlan();
				if (obj[0] != null) {
					a.setNumero(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					a.setHistorico(obj[1].toString());
				}
				if (obj[2] != null) {
					a.setValor(Double.valueOf(obj[2].toString()));
				}
				listaCredito.add(a);
			}
		}
		return listaCredito;
	}
}