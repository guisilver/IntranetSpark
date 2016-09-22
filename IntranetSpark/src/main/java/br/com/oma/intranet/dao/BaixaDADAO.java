package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.entidades.intra_conciliacao;
import br.com.oma.intranet.util.JPAUtil;

public class BaixaDADAO {

	private EntityManager manager;

	public BaixaDADAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<DebitoAutomatico> pesquisaBaixado(Date dataInicio, Date dataFim) {
		List<DebitoAutomatico> retorno = new ArrayList<>();
		DebitoAutomatico da = null;
		Query query = this.manager.createNativeQuery(
				"select d.nro_identificacao,p.nrolancto,p.vencimento,p.credor,p.valor,d.cod_deb_autom from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto where p.tipopagto = '4'"
						+ " and p.vencimento between :p1 and :p2 order by p.vencimento, d.id_contrato");
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			da = new DebitoAutomatico();
			if (aux[0] != null) {
				da.setNro_identificacao(aux[0].toString());
			}

			if (aux[1] != null) {
				da.setNrolancto(Integer.parseInt(aux[1].toString()));
			}

			if (aux[2] != null) {
				da.setVencimento((Date) aux[2]);
			}

			if (aux[3] != null) {
				da.setCredor(aux[3].toString());
			}

			if (aux[4] != null) {
				da.setValor(Double.parseDouble(aux[4].toString()));
			}

			if (aux[5] != null) {
				da.setCodDebAutom(aux[5].toString());
			}
			retorno.add(da);
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<DebitoAutomatico> pesquisaBaixado2(Date dataInicio, Date dataFim) {
		List<DebitoAutomatico> retorno = new ArrayList<>();
		DebitoAutomatico da = null;
		Query query = this.manager.createNativeQuery(
				"select d.nro_identificacao,p.nrolancto,p.vencimento,p.credor,p.valor,d.cod_deb_autom from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndlanch p on f.nrolcto_contas = p.nrolancto where p.tipopagto = '4'"
						+ " and p.vencimento between :p1 and :p2 order by p.vencimento, d.id_contrato");
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			da = new DebitoAutomatico();
			if (aux[0] != null) {
				da.setNro_identificacao(aux[0].toString());
			}

			if (aux[1] != null) {
				da.setNrolancto(Integer.parseInt(aux[1].toString()));
			}

			if (aux[2] != null) {
				da.setVencimento((Date) aux[2]);
			}

			if (aux[3] != null) {
				da.setCredor(aux[3].toString());
			}

			if (aux[4] != null) {
				da.setValor(Double.parseDouble(aux[4].toString()));
			}

			if (aux[5] != null) {
				da.setCodDebAutom(aux[5].toString());
			}
			retorno.add(da);
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public String pesquisaFornecedor(long nro_identificacao) {
		Query query = this.manager
				.createNativeQuery("select fornecedor from sigadm.dbo.cndpagtosmensais where cod_deb_autom like :p1");
		query.setParameter("p1", "%"+nro_identificacao+"%");
		List<String> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public String pesquisaCodNomeCnd(long id) {
		Query query = this.manager
				.createNativeQuery(
						"select c.codigo,c.nome from sigadm.dbo.cndpagtosmensais d inner join sigadm.dbo.cndcondo c on d.condominio = c.codigo where cod_deb_autom like :id")
				.setMaxResults(1);
		query.setParameter("id", "%"+id+"%");
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			return aux[0].toString() + " - " + aux[1].toString();
		}	
		return "";
	}

	@SuppressWarnings("unchecked")
	public boolean possuiImg(int id_lcto, String tipo_lcto) {
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

	public List<intra_conciliacao> pesquisarConciliados(Date dataInicio, Date dataFim) {
		TypedQuery<intra_conciliacao> query = this.manager
				.createQuery("from intra_conciliacao where dataPagto between :p1 and :p2", intra_conciliacao.class);
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		return query.getResultList();
	}

	public void baixarConciliados(List<intra_conciliacao> l) {
		if (l != null && l.size() > 0) {
			for (intra_conciliacao aux : l) {
				this.manager.persist(aux);
			}
		}
	}
}
