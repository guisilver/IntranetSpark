package br.com.oma.intranet.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import br.com.oma.intranet.entidades.intra_servicos;
import br.com.oma.intranet.util.JPAUtil;

public class ServicosDao implements Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager manager;

	public ServicosDao() {
		this.manager = JPAUtil.getManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<intra_servicos> listarServicos(Date periodoInicial, Date periodoFinal){
		
		List<intra_servicos> lista = new ArrayList<>();
		Query queryRecibo = this.manager.createNativeQuery("select r.condominio, count (distinct(r.nro_bancario)) "
				+ "from sigadm.dbo.cndrecib r  "
				+ "inner join sigadm.dbo.cndcondo c on r.condominio = c.codigo  "
				+ "where  r.data_geracao between :p1 and :p2 and (codigo_servico is null or codigo_servico = '') and c.data_baixa is null "
				+ "group by  r.condominio");
		queryRecibo.setParameter("p1", periodoInicial);
		queryRecibo.setParameter("p2", periodoFinal);
		List<Object[]> listaRecibo = queryRecibo.getResultList();
		if(!listaRecibo.isEmpty()){
			for (Object[] obj : listaRecibo) {
				intra_servicos servico = new intra_servicos();
				Query queryqtdaUnida = this.manager.createNativeQuery("select sum (isnull([qtde_aptos],0) + isnull([qtde_cjtos],0) + isnull([qtde_lojas],0)) as qtd_unidade "
						+ "from sigadm.dbo.cndcondo where codigo = :p1 and data_baixa is null");
				queryqtdaUnida.setParameter("p1", Integer.valueOf(obj[0].toString()));
				int unidade = Integer.valueOf(queryqtdaUnida.getResultList().get(0).toString());
				if(obj[0] != null){
					servico.setCondominio(Integer.valueOf(obj[0].toString()));
				}
				if(obj[1] != null) {
					int cotaC = Integer.valueOf(obj[1].toString());
					
					if(cotaC > unidade){
						int cotaE = Integer.valueOf(cotaC - unidade);
						servico.setCotaExtra(cotaE);
						servico.setCotaCondominio(cotaC - cotaE);
					}else{
						servico.setCotaCondominio(cotaC);
					}
				}
				lista.add(servico);
			}
		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public int retornaCodigoMsvcon() {
		int retorno = 0;
		Query query = this.manager.createNativeQuery("select ult_svmov from sigadm.dbo.cndmonit");
		List<Object[]> lista = query.getResultList();
		if(!lista.isEmpty()){
			for (Object obj : lista) {
				retorno = Integer.valueOf(String.valueOf(obj));
				this.manager.createNativeQuery("update sigadm.dbo.cndmonit set ult_svmov = "+(retorno+1)).executeUpdate();
			}
		}
		return retorno+1;
	}
	
	@SuppressWarnings("unchecked")
	public int retornaCodigoDoServico(int condominio, double tipoDoEmissor) {
		int valor = 0;
		Query query = this.manager.createNativeQuery(
				"select codigo from sigadm.dbo.cndsvcon where condominio = :p1 and codigo_serv = :p2");
		query.setParameter("p1", condominio);
		query.setParameter("p2", tipoDoEmissor);
		List<Object> lista = query.getResultList();
		for (Object is : lista) {
			valor = Integer.valueOf(String.valueOf(is));
		}
		return valor;
	}
	
	public void salvarCobrancaServico(int codigoMsvcon,int codigoDoServico, double qtdaImpressao, String historico, Date dataProcessamento) {
		Query query = this.manager.createNativeQuery(
				"insert into sigadm.dbo.cndsvmov (codigo, cod_svcon, qtde, historico, data) values ("+codigoMsvcon+","+codigoDoServico+", "+qtdaImpressao+",'"+historico+"', :p1)");
		query.setParameter("p1", dataProcessamento, TemporalType.TIMESTAMP);
		query.executeUpdate();
	}

	public void updateCndrecib(int condominio, Date periodoInicial, Date periodoFinal) {
		Query query = this.manager.createNativeQuery(
				"update sigadm.dbo.cndrecib set codigo_servico = '10' where condominio = :p1 and data_geracao between :p2 and :p3");
		query.setParameter("p1", condominio);
		query.setParameter("p2", periodoInicial);
		query.setParameter("p3", periodoFinal);
		query.executeUpdate();
	}
}
