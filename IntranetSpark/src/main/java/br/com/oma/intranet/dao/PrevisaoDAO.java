package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_previsao_orcamentaria;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.intranet.util.PrevisaoImprimir;

public class PrevisaoDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995975827523329722L;

	private EntityManager manager;
	private Query query;
	
	public PrevisaoDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<intra_previsao_orcamentaria> listarPrevisao(int condominio, int codigoGerente){
		this.query = this.manager.createQuery("from intra_previsao_orcamentaria where condominio = :p1 and codigo_gerente = :p2 order by conta, codigo_despesa");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", codigoGerente);
		return this.query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	public double totalGeralMedia(int condominio) {
		this.query = this.manager.createNativeQuery("select sum(media) from intra_previsao_orcamentaria where condominio = :p1");
		this.query.setParameter("p1", condominio);
		String total = String.valueOf(this.query.getResultList().get(0));
		return Double.valueOf((total.equals("null") ? 0 : total).toString());
	}

	public void alterarPrevisao(intra_previsao_orcamentaria ipoAlterBEAN) {
		this.manager.merge(ipoAlterBEAN);		
	}

	public void excluirPrevisao(int codigo) {
		this.query = this.manager.createQuery("delete intra_previsao_orcamentaria where codigo = :p1");
		this.query.setParameter("p1", codigo);
		this.query.executeUpdate();
	}

	@SuppressWarnings({ "unchecked"})
	public List<PrevisaoImprimir> listarPrevisaoImprimir(int codigo) {
		List<PrevisaoImprimir> lista = new ArrayList<PrevisaoImprimir>();
		this.query = this.manager.createNativeQuery("SELECT p.codigo AS p_codigo, p.capa AS p_capa, p.codigo_gerente AS p_codigo_gerente, p.condominio AS p_condominio,"
				+ " p.conta AS p_conta, p.mes_projecao AS p_mes_projecao, p.despesas AS p_despesas,  p.mes_janeiro AS p_mes_janeiro, p.mes_fevereiro AS p_mes_fevereiro,"
				+ " p.mes_marco AS p_mes_marco, p.mes_abril AS p_mes_abril, p.mes_maio AS p_mes_maio, p.mes_junho AS p_mes_junho, p.mes_julho AS p_mes_julho, p.mes_agosto AS p_mes_agosto,"
				+ " p.mes_setembro AS p_mes_setembro, p.mes_outubro AS p_mes_outubro, p.mes_novembro AS p_mes_novembro, p.mes_dezembro AS p_mes_dezembro, p.media AS p_media,"
				+ " p.codigoDespesa AS p_codigoDespesa, p.inadimplencia AS p_inadimplencia, c.nome AS c_nomeCondominio, (SELECT sum(media) FROM intra_previsao_orcamentaria where condominio = :p1) AS p_totalMedia "
				+ "FROM intra_previsao_orcamentaria p INNER JOIN intra_condominios c ON p.condominio = c.codigo WHERE condominio = :p1 ORDER BY p.conta ASC, p.capa ASC");
		this.query.setParameter("p1", codigo);
		List<Object[]> aux = this.query.getResultList();
		for (Object[] obj : aux) {
			PrevisaoImprimir pi = new PrevisaoImprimir();
			pi.setP_codigo(Integer.valueOf(obj[0].toString()));
			pi.setP_capa(obj[1].toString());
			pi.setP_codigo_gerente(Integer.valueOf(obj[2].toString()));
			pi.setP_condominio(Integer.valueOf(obj[3].toString()));
			pi.setP_conta(Integer.valueOf(obj[4].toString()));
			pi.setP_mes_projecao((Date) obj[5]);
			pi.setP_despesas(obj[6].toString());
			
			pi.setP_mes1(retornaMes(obj, 0));
			pi.setP_mes2(retornaMes(obj, 1));
			pi.setP_mes3(retornaMes(obj, 2));
			pi.setP_mes4(retornaMes(obj, 3));
			pi.setP_mes5(retornaMes(obj, 4));
			pi.setP_mes6(retornaMes(obj, 5));
			pi.setP_mes7(retornaMes(obj, 6));
			pi.setP_mes8(retornaMes(obj, 7));
			pi.setP_mes9(retornaMes(obj, 8));
			pi.setP_mes10(retornaMes(obj, 9));
			pi.setP_mes11(retornaMes(obj, 10));
			pi.setP_mes12(retornaMes(obj, 11));
			pi.setP_media(Double.valueOf(obj[19].toString()));
			
			pi.setP_codigoDespesa(obj[20].toString());
			pi.setP_inadimplencia(Double.valueOf(obj[21].toString()));
			pi.setC_nomeCondominio(obj[22].toString());
			pi.setP_totalMedia(Double.valueOf(obj[23].toString()));
			
			lista.add(pi);
		}
		
		return lista;
	}
	
	public double retornaMes(Object[] obj, int mesProjecao){
		DateTime dt = new DateTime((Date) obj[5]);
		double valor = 0;
		switch (dt.plusMonths(mesProjecao).getMonthOfYear()) {
		case 1:	valor = Double.valueOf(obj[7].toString());break;
		case 2:	valor = Double.valueOf(obj[8].toString());break;
		case 3:	valor = Double.valueOf(obj[9].toString());break;
		case 4:	valor = Double.valueOf(obj[10].toString());break;
		case 5:	valor = Double.valueOf(obj[11].toString());break;
		case 6:	valor = Double.valueOf(obj[12].toString());break;
		case 7:	valor = Double.valueOf(obj[13].toString());break;
		case 8: valor = Double.valueOf(obj[14].toString());break;
		case 9:	valor = Double.valueOf(obj[15].toString());break;
		case 10:valor = Double.valueOf(obj[16].toString());break;
		case 11:valor = Double.valueOf(obj[17].toString());break;
		case 12:valor = Double.valueOf(obj[18].toString());break;
		}
		return valor;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<PrevisaoImprimir> listarPrevisaoImprimirGrafico(int codigo) {
		List<PrevisaoImprimir> lista = new ArrayList<PrevisaoImprimir>();
		this.query = this.manager.createNativeQuery("select p.capa as p_capa, p.mes_projecao, c.nome, sum (p.mes_janeiro) as p_mes1, sum (p.mes_fevereiro) as p_mes2, sum (p.mes_marco) as p_mes3, "
				+ "sum (p.mes_abril) as p_mes4, sum (p.mes_maio) as p_mes5, sum (p.mes_junho) as p_mes6, sum (p.mes_julho) as p_mes7, "
				+ "sum (p.mes_agosto) as p_mes8, sum (p.mes_setembro) as p_mes9, sum (p.mes_outubro) as p_mes10, sum (p.mes_novembro) as p_mes11, "
				+ "sum (p.mes_dezembro) as p_mes12, sum(p.media) as p_media from intra_previsao_orcamentaria p "
				+ "inner join intra_condominios c on p.condominio = c.codigo "
				+ "where conta <> 9999 and condominio = :p1 group by capa, conta, c.nome, mes_projecao  order by conta");
		this.query.setParameter("p1", codigo);
		List<Object[]> aux = this.query.getResultList();
		for (Object[] obj : aux) {
			PrevisaoImprimir pi = new PrevisaoImprimir();

			pi.setP_capa(obj[0].toString());
			pi.setP_mes_projecao((Date) obj[1]);
			pi.setC_nomeCondominio(obj[2].toString());
			
			pi.setP_mes1(retornaMesGraficos(obj, 0));
			pi.setP_mes2(retornaMesGraficos(obj, 1));
			pi.setP_mes3(retornaMesGraficos(obj, 2));
			pi.setP_mes4(retornaMesGraficos(obj, 3));
			pi.setP_mes5(retornaMesGraficos(obj, 4));
			pi.setP_mes6(retornaMesGraficos(obj, 5));
			pi.setP_mes7(retornaMesGraficos(obj, 6));
			pi.setP_mes8(retornaMesGraficos(obj, 7));
			pi.setP_mes9(retornaMesGraficos(obj, 8));
			pi.setP_mes10(retornaMesGraficos(obj, 9));
			pi.setP_mes11(retornaMesGraficos(obj, 10));
			pi.setP_mes12(retornaMesGraficos(obj, 11));
			pi.setP_media(Double.valueOf(obj[15].toString()));
			
			
			lista.add(pi);
		}
		
		return lista;
	}
	
	public double retornaMesGraficos(Object[] obj, int mesProjecao){
		DateTime dt = new DateTime((Date) obj[1]);
		double valor = 0;
		switch (dt.plusMonths(mesProjecao).getMonthOfYear()) {
		case 1:	valor = Double.valueOf(obj[3].toString());break;
		case 2:	valor = Double.valueOf(obj[4].toString());break;
		case 3:	valor = Double.valueOf(obj[5].toString());break;
		case 4:	valor = Double.valueOf(obj[6].toString());break;
		case 5:	valor = Double.valueOf(obj[7].toString());break;
		case 6:	valor = Double.valueOf(obj[8].toString());break;
		case 7:	valor = Double.valueOf(obj[9].toString());break;
		case 8: valor = Double.valueOf(obj[10].toString());break;
		case 9:	valor = Double.valueOf(obj[11].toString());break;
		case 10:valor = Double.valueOf(obj[12].toString());break;
		case 11:valor = Double.valueOf(obj[13].toString());break;
		case 12:valor = Double.valueOf(obj[14].toString());break;
		}
		return valor;
	}
	
}
