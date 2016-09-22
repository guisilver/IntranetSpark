package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_previsao_orcamentaria;
import br.com.oma.intranet.entidades.intra_projetar_orcamento;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class ProjetarPrevisaoDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5071509785311212717L;

	private EntityManager manager;
	private Query query;
	
	public ProjetarPrevisaoDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listarUltimoMes(int condominio, Date dataInicial, Date dataFinal){
		
		this.query = this.manager.createNativeQuery("select distinct p.nome, sum(d.valor) as valor,p.conta, p.sub_conta, p.cod_reduzido from sigadm.dbo.cndespes d "
				+ "inner join sigadm.dbo.cndplano p on d.conta = p.cod_reduzido where condominio = :p1 and "
				+ "data between :p2 and :p3 and p.conta between 1400 and 1411 and p.codigo_plano = 1 "
				+ "group by p.nome, p.conta, p.sub_conta, p.cod_reduzido order by p.conta, p.sub_conta");	
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", dataInicial);
		this.query.setParameter("p3", dataFinal);
		
		return this.query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listarUltimo3Meses(int condominio, Date dataInicial, Date dataFinal){
		
		this.query = this.manager.createNativeQuery("select distinct p.nome, CAST(sum(d.valor/3)as decimal(30,2)) as valor,p.conta, p.sub_conta, p.cod_reduzido from sigadm.dbo.cndespes d "
				+ "inner join sigadm.dbo.cndplano p on d.conta = p.cod_reduzido where condominio = :p1 and "
				+ "data between :p2 and :p3 and p.conta between 1400 and 1411 and p.codigo_plano = 1 "
				+ "group by p.nome, p.conta, p.sub_conta, p.cod_reduzido  order by p.conta, p.sub_conta");	
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", dataInicial);
		this.query.setParameter("p3", dataFinal);
		
		return this.query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listarUltimo6Meses(int condominio, Date dataInicial, Date dataFinal){
		
		this.query = this.manager.createNativeQuery("select distinct p.nome, CAST(sum(d.valor/6)as decimal(30,2)) as valor,p.conta, p.sub_conta, p.cod_reduzido from sigadm.dbo.cndespes d "
				+ "inner join sigadm.dbo.cndplano p on d.conta = p.cod_reduzido where condominio = :p1 and "
				+ "data between :p2 and :p3 and p.conta between 1400 and 1411 and p.codigo_plano = 1 "
				+ "group by p.nome, p.conta, p.sub_conta, p.cod_reduzido order by p.conta, p.sub_conta");	
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", dataInicial);
		this.query.setParameter("p3", dataFinal);
		
		return this.query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> listarUltimo12Meses(int condominio, Date dataInicial, Date dataFinal){
		
		this.query = this.manager.createNativeQuery("select distinct p.nome, CAST(sum(d.valor/12)as decimal(30,2)) as valor,p.conta, p.sub_conta, p.cod_reduzido from sigadm.dbo.cndespes d "
				+ "inner join sigadm.dbo.cndplano p on d.conta = p.cod_reduzido where condominio = :p1 and "
				+ "data between :p2 and :p3 and p.conta between 1400 and 1411 and p.codigo_plano = 1 "
				+ "group by p.nome, p.conta, p.sub_conta, p.cod_reduzido order by p.conta, p.sub_conta");	
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", dataInicial);
		this.query.setParameter("p3", dataFinal);
		
		return this.query.getResultList();
	}

	public void salvarPrevisao(intra_previsao_orcamentaria prevBEAN, SessaoMB sessao) {
		this.manager.persist(prevBEAN);
		
		intra_log_geral lg = new intra_log_geral();
		lg.setCondominio(prevBEAN.getCondominio());
		lg.setAlterar(false);
		lg.setInserir(true);
		lg.setDeletar(false);
		lg.setDataFeito(new Date());
		lg.setTabela("intra_advert_mult");
		lg.setFeitoPor(sessao.getUsuario().getEmail());
		lg.setInfoAnterior("salvar previsao codigo: "+prevBEAN.getCodigo());
		this.logGeral(lg);
	}
	
	public void salvarPrevisaoMerge(intra_previsao_orcamentaria prevBEAN, SessaoMB sessao) {
		this.manager.merge(prevBEAN);
		
		intra_log_geral lg = new intra_log_geral();
		lg.setCondominio(prevBEAN.getCondominio());
		lg.setAlterar(true);
		lg.setInserir(true);
		lg.setDeletar(false);
		lg.setDataFeito(new Date());
		lg.setTabela("intra_advert_mult");
		lg.setFeitoPor(sessao.getUsuario().getEmail());
		lg.setInfoAnterior("salvar / alterar previsao codigo: "+prevBEAN.getCodigo());
		this.logGeral(lg);
	}

/*	public void deletarAlterior(int codigo) {
		this.query = this.manager.createNativeQuery("delete intra_previsao_orcamentaria where condominio = :p1");
		this.query.setParameter("p1", codigo);
		this.query.executeUpdate();
	}*/

	@SuppressWarnings("unchecked")
	public List<intra_previsao_orcamentaria> listarPrevisao(int codigo) {
		this.query = this.manager.createQuery("from intra_previsao_orcamentaria where condominio = :p1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public intra_previsao_orcamentaria listarPrevisao(int condominio, String codigoDespesa) {
		intra_previsao_orcamentaria i = new intra_previsao_orcamentaria();
		this.query = this.manager.createQuery("from intra_previsao_orcamentaria where condominio = :p1 and codigoDespesa = :p2");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", codigoDespesa);
		List<intra_previsao_orcamentaria> lista = this.query.getResultList();
		if(!lista.isEmpty()){
			for (intra_previsao_orcamentaria ipo : lista) {
				i = ipo;
			}
		}
		return i;
	}

	public void salvarProjecao(intra_projetar_orcamento ipoBEAN, SessaoMB sessao) {
		this.manager.merge(ipoBEAN);
		
		intra_log_geral lg = new intra_log_geral();
		lg.setCondominio(ipoBEAN.getCondominio());
		lg.setAlterar(false);
		lg.setInserir(true);
		lg.setDeletar(false);
		lg.setDataFeito(new Date());
		lg.setTabela("intra_advert_mult");
		lg.setFeitoPor(sessao.getUsuario().getEmail());
		lg.setInfoAnterior("salvar / alterar projeção codigo: "+ipoBEAN.getCondominio());
		this.logGeral(lg);
		
	}

	public void excluirUltimaProjecao(int codigo, SessaoMB sessao) {
		this.query = this.manager.createNativeQuery("delete intra_previsao_orcamentaria where condominio = :p1");
		this.query.setParameter("p1", codigo);
		this.query.executeUpdate();
		
		intra_log_geral lg = new intra_log_geral();
		lg.setCondominio(codigo);
		lg.setAlterar(false);
		lg.setInserir(false);
		lg.setDeletar(true);
		lg.setDataFeito(new Date());
		lg.setTabela("intra_advert_mult");
		lg.setFeitoPor(sessao.getUsuario().getEmail());
		lg.setInfoAnterior("Exclussão da previsão condominio: "+codigo);
		this.logGeral(lg);
	}

	
}
