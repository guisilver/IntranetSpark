package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.entidades.intra_controle_contas;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class ControleContasDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2519039679643550484L;

	private Query query;
	private EntityManager manager;
	private intra_log_geral ilg;
	private Date data;

	public ControleContasDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void create(intra_controle_contas icc) {
		this.manager.persist(icc);
	}

	@SuppressWarnings("unchecked")
	public List<intra_controle_contas> listarContas(int cod_geren, int ano) {
		this.query = this.manager.createQuery(
				"from intra_controle_contas where cod_geren = :p1 and ano = :p2 and cod_geren <> 99  order by codigo desc");
		query.setParameter("p1", cod_geren);
		query.setParameter("p2", ano);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<intra_controle_contas> listarContas(int ano) {
		this.query = this.manager.createQuery("from intra_controle_contas where ano = :p1 order by codigo desc");
		query.setParameter("p1", ano);

		return query.getResultList();
	}

	public void updateControleContas(int codigo, int vencimento, String status, SessaoMB sessaoMB) {
		this.query = this.manager
				.createQuery("update intra_controle_contas set " + status + " = :p1 where codigo = :p2");
		query.setParameter("p1", vencimento);
		query.setParameter("p2", codigo);
		query.executeUpdate();

		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_controle_contas");
		this.ilg.setInfoAnterior("Alterado controle de contas codigo: " + codigo + "/ Vencimento: " + vencimento
				+ "/ Status: " + status);
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void updateControleContasNull(int codigo, int vencimento, String status, String mes, SessaoMB sessaoMB) {
		this.query = this.manager.createQuery(
				"update intra_controle_contas set " + status + " = :p1, " + mes + " = null where codigo = :p2");
		query.setParameter("p1", vencimento);
		query.setParameter("p2", codigo);
		query.executeUpdate();

		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_controle_contas");
		this.ilg.setInfoAnterior("Alterado controle de contas codigo: " + codigo);
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void updateobject(intra_controle_contas icc) {
		this.manager.merge(icc);
		this.manager.detach(icc);
	}

	public void updateobject2(intra_controle_contas icc, SessaoMB sessaoMB) {
		if (icc != null) {

			this.query = this.manager.createQuery(
					"update intra_controle_contas set ano = :p1,cod_geren = :p2, condominio = :p3, despesa = :p4,"
							+ "historico = :p5, mes1 = :p6, mes2 = :p7, mes3 = :p8, mes4 = :p9, mes5 = :p10, mes6 = :p11, mes7 = :p12, mes8 = :p13, mes9 = :p14, mes10 = :p15, mes11 = :p16, mes12 = :p17,"
							+ "status_jan = :p18, status_fev = :p19, status_mar = :p20, status_abr = :p21, status_mai = :p22, status_jun = :p23, status_jul = :p24, status_ago = :p25,"
							+ "status_set = :p26, status_out = :p27, status_nov = :p28, status_dez = :p29, obs = :p30, tipo = :p31, vencimento = :p32, mes_atual = :p33 where codigo = :p34");

			query.setParameter("p1", icc.getAno());
			query.setParameter("p2", icc.getCod_geren());
			query.setParameter("p3", icc.getCondominio());
			query.setParameter("p4", icc.getDespesa());
			query.setParameter("p5", icc.getHistorico());
			query.setParameter("p6", icc.getMes1());
			query.setParameter("p7", icc.getMes2());
			query.setParameter("p8", icc.getMes3());
			query.setParameter("p9", icc.getMes4());
			query.setParameter("p10", icc.getMes5());
			query.setParameter("p11", icc.getMes6());
			query.setParameter("p12", icc.getMes7());
			query.setParameter("p13", icc.getMes8());
			query.setParameter("p14", icc.getMes9());
			query.setParameter("p15", icc.getMes10());
			query.setParameter("p16", icc.getMes11());
			query.setParameter("p17", icc.getMes12());
			query.setParameter("p18", icc.getStatus_jan());
			query.setParameter("p19", icc.getStatus_fev());
			query.setParameter("p20", icc.getStatus_mar());
			query.setParameter("p21", icc.getStatus_abr());
			query.setParameter("p22", icc.getStatus_mai());
			query.setParameter("p23", icc.getStatus_jun());
			query.setParameter("p24", icc.getStatus_jul());
			query.setParameter("p25", icc.getStatus_ago());
			query.setParameter("p26", icc.getStatus_set());
			query.setParameter("p27", icc.getStatus_out());
			query.setParameter("p28", icc.getStatus_nov());
			query.setParameter("p29", icc.getStatus_dez());
			query.setParameter("p30", icc.getObs());
			query.setParameter("p31", icc.getTipo());
			query.setParameter("p32", icc.getVencimento());
			query.setParameter("p33", icc.getMes_atual());
			query.setParameter("p34", icc.getCodigo());
			query.executeUpdate();

			this.ilg = new intra_log_geral();
			data = new Date();
			this.ilg.setCondominio(icc.getCondominio());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(false);
			this.ilg.setInserir(true);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_controle_contas");
			this.ilg.setInfoAnterior("Alterado conta codigo: " + icc.getCodigo());
			this.logGeral(this.ilg);
			this.ilg = new intra_log_geral();

		}
	}

	public boolean alteraConta(intra_controle_contas icc, SessaoMB sessaoMB) {
		boolean val = false;
		this.ilg = new intra_log_geral();
		if (icc != null) {
			this.manager.merge(icc);
			this.ilg.setCondominio(icc.getCondominio());
			this.ilg.setIp(sessaoMB.getIpUser());
			this.ilg.setDataFeito(data);
			this.ilg.setDeletar(false);
			this.ilg.setAlterar(true);
			this.ilg.setInserir(false);
			this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
			this.ilg.setTabela("intra_controle_contas");
			this.ilg.setInfoAnterior("Alterado conta codigo: " + icc.getCodigo());
			this.logGeral(this.ilg);
			val = true;
		}
		return val;
	}

	public boolean excluiConta(intra_controle_contas icc, SessaoMB sessaoMB) {
		int i = 0;
		this.query = this.manager.createQuery("delete from intra_controle_contas where codigo = :p1");
		query.setParameter("p1", icc.getCodigo());
		i = query.executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(icc.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_controle_contas");
		this.ilg.setInfoAnterior("Excluido conta codigo: " + icc.getCodigo());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public int listarStatusVerde(int cod_geren, int mes_atual, int ano) {
		List<Object> lista = new ArrayList<>();
		int valor = 0;
		this.query = this.manager.createNativeQuery("select COUNT(*) as verde " + "from intra_controle_contas "
				+ "where (" + this.mes(mes_atual) + "= 1 ) " + "and cod_geren = :p1 and ano = :p3");
		query.setParameter("p1", cod_geren);
		query.setParameter("p3", ano);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				valor = (int) lista.get(i);
			}
		}
		return valor;
	}

	@SuppressWarnings("unchecked")
	public int listarStatusOrange(int cod_geren, int mes_atual, int ano) {
		List<Object> lista = new ArrayList<>();
		int valor = 0;
		this.query = this.manager.createNativeQuery("select COUNT(*) as orange " + "from intra_controle_contas "
				+ "where (" + this.mes(mes_atual) + "= 2 ) " + "and cod_geren = :p1 and ano = :p3");
		query.setParameter("p1", cod_geren);
		query.setParameter("p3", ano);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				valor = (int) lista.get(i);
			}
		}
		return valor;
	}

	@SuppressWarnings("unchecked")
	public int listarStatusRed(int cod_geren, int mes_atual, int ano) {
		List<Object> lista = new ArrayList<>();
		int valor = 0;
		this.query = this.manager.createNativeQuery("select COUNT(*) as re " + "from intra_controle_contas " + "where ("
				+ this.mes(mes_atual) + "= 3 ) " + "and cod_geren = :p1 and ano = :p3");
		query.setParameter("p1", cod_geren);
		query.setParameter("p3", ano);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				valor = (int) lista.get(i);
			}
		}
		return valor;
	}

	@SuppressWarnings("unchecked")
	public int listarStatusBlack(int cod_geren, int mes_atual, int ano) {
		List<Object> lista = new ArrayList<>();
		int valor = 0;
		this.query = this.manager.createNativeQuery("select COUNT(*) as orange " + "from intra_controle_contas "
				+ "where (" + this.mes(mes_atual) + "= 4 ) " + "and cod_geren = :p1 and ano = :p3");
		query.setParameter("p1", cod_geren);
		query.setParameter("p3", ano);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				valor = (int) lista.get(i);
			}
		}
		return valor;
	}
	
	@SuppressWarnings("unchecked")
	public int listarStatus(int cod_geren, int mes_atual, int ano, String perfil, int tipo) {
		List<Object> lista = new ArrayList<>();
		int valor = 0;
		if(perfil.trim().equals("5")){
			this.query = this.manager.createNativeQuery("select COUNT(*) as orange " + "from intra_controle_contas "
					+ "where (" + this.mes(mes_atual) + "= "+tipo+" )  and ano = :p3");
		}else{
			this.query = this.manager.createNativeQuery("select COUNT(*) as orange " + "from intra_controle_contas "
					+ "where (" + this.mes(mes_atual) + "= "+tipo+" ) and cod_geren = :p1 and ano = :p3");
			query.setParameter("p1", cod_geren);
			
		}
		query.setParameter("p3", ano);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				valor = (int) lista.get(i);
			}
		}
		return valor;
	}

	public String mes(int periodo) {
		String mes = "";
		switch (periodo) {
		case 1:
			mes = "status_jan";
			break;
		case 2:
			mes = "status_fev";
			break;
		case 3:
			mes = "status_mar";
			break;
		case 4:
			mes = "status_abr";
			break;
		case 5:
			mes = "status_mai";
			break;
		case 6:
			mes = "status_jun";
			break;
		case 7:
			mes = "status_jul";
			break;
		case 8:
			mes = "status_ago";
			break;
		case 9:
			mes = "status_set";
			break;
		case 10:
			mes = "status_out";
			break;
		case 11:
			mes = "status_nov";
			break;
		case 12:
			mes = "status_dez";
			break;
		}
		return mes;
	}

	public boolean salvar(intra_controle_contas icc, SessaoMB sessaoMB) {
		boolean val = false;
		if (icc != null) {
			this.manager.persist(icc);
			val = true;
		}
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(icc.getCondominio());
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_controle_contas");
		this.ilg.setInfoAnterior("Adicionado conta codigo: " + icc.getCodigo());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
		return val;
	}

	public List<intra_controle_contas> geraRelatorioContasAVencer(int codigoGerente, int ano) {
		TypedQuery<intra_controle_contas> query = this.manager.createQuery(
				"from intra_controle_contas where cod_geren = :p1 and ano = :p2 order by condominio,tipo",
				intra_controle_contas.class);
		query.setParameter("p1", codigoGerente);
		query.setParameter("p2", ano);
		return query.getResultList();
	}

	public void excluirConta(intra_controle_contas icc, SessaoMB sessaoMB) {
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("intra_controle_contas");
		this.ilg.setInfoAnterior(
				"Excluido controle de contas codigo: " + icc.getCodigo() + "/ Vencimento: " + icc.getVencimento());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();

		this.query = this.manager.createQuery("delete intra_controle_contas where codigo = :p1");
		this.query.setParameter("p1", icc.getCodigo());
		this.query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> listarContaVinculada() {
		Query query = this.manager.createNativeQuery(
				"select codigo, conta_vinculada from sigadm.dbo.cndcondo where codigo between 111 and 4241 and data_baixa is null");
		return query.getResultList();
	}

	public int listarCodigoGerente(int condominio) {
		this.query = this.manager.createNativeQuery("select gerente from sigadm.dbo.cndcondo where codigo = :p1");
		this.query.setParameter("p1", condominio);
		int valor = Integer.valueOf(query.getResultList().get(0).toString()); 
		return valor;
	}

	
}
