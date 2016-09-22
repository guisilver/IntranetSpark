package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;

import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.entidades.intra_conciliacao_lf;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.pc.modelo.entidades.Condominio;

public class LancamentosFuturosDAO {

	private EntityManager manager;

	public LancamentosFuturosDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<DebitoAutomatico> pesquisaBaixado() {
		List<DebitoAutomatico> retorno = new ArrayList<>();
		Date hoje = new DateTime(new Date()).withMillisOfDay(0).toDate();
		DebitoAutomatico da = null;
		Query query = this.manager.createNativeQuery(
				"select d.nro_identificacao,p.nrolancto,p.vencimento,p.credor,p.valor,d.cod_deb_autom from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto where p.tipopagto = '4'"
						+ " and p.vencimento >= :p1 and p.estimado = 'R' order by p.vencimento, d.id_contrato");
		query.setParameter("p1", hoje);
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
		query.setParameter("p1", "%" + nro_identificacao + "%");
		List<String> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public Condominio pesquisaCodNomeCnd(long id) {
		Condominio cnd = null;
		Query query = this.manager
				.createNativeQuery(
						"select c.codigo,c.nome from sigadm.dbo.cndpagtosmensais d inner join sigadm.dbo.cndcondo c on d.condominio = c.codigo where cod_deb_autom like :id")
				.setMaxResults(1);
		query.setParameter("id", "%" + id + "%");
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			cnd = new Condominio();
			if (aux[0] != null) {
				cnd.setCodigo(Short.parseShort(aux[0].toString()));
				cnd.setCodigoCnd(Short.parseShort(aux[0].toString()));
			}
			if (aux[1] != null) {
				cnd.setNome(aux[1].toString());
			}
		}
		return cnd;
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

	public List<intra_conciliacao_lf> pesquisarConciliados() {
		Date hoje = new DateTime(new Date()).withMillisOfDay(0).toDate();
		TypedQuery<intra_conciliacao_lf> query = this.manager
				.createQuery("from intra_conciliacao_lf where dataPagto >= :p1", intra_conciliacao_lf.class);
		query.setParameter("p1", hoje);
		return query.getResultList();
	}

	public void baixarConciliados(List<intra_conciliacao_lf> l) {
		if (l != null && l.size() > 0) {
			for (intra_conciliacao_lf aux : l) {
				this.manager.persist(aux);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getListaCndSaida() {
		return (List<Integer>) this.manager.createQuery("select codigo from intra_condominios_saida order by dataSaida")
				.getResultList();
	}
}
