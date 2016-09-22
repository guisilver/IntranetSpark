package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_controle_concessionarias;
import br.com.oma.intranet.util.JPAUtil;

public class ConcessionariaDAO {

	private EntityManager manager;

	public ConcessionariaDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvarNovaConcessionaria(intra_controle_concessionarias concessionaria) {
		this.manager.persist(concessionaria);
	}

	@SuppressWarnings("unchecked")
	public List<intra_controle_concessionarias> pesquisarConcessionarias(int ano, List<Short> condominios) {
		List<intra_controle_concessionarias> l = new ArrayList<>();
		intra_controle_concessionarias c = null;
		Query query = this.manager.createNativeQuery(
				"select distinct(d.nro_identificacao),d.fornecedor,p.condominio,d.observacao from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto"
						+ " where p.tipopagto = '4' and d.condominio IN (:condominio) and YEAR(p.vencimento) = :ano order by p.condominio");
		query.setParameter("condominio", condominios);
		query.setParameter("ano", ano);
		List<Object[]> r = query.getResultList();
		for (Object[] aux : r) {
			c = new intra_controle_concessionarias();
			if (aux[0] != null) {
				c.setCodigoDebito(aux[0].toString());
			}
			if (aux[1] != null) {
				c.setFornecedor(aux[1].toString());
			}
			if (aux[2] != null) {
				c.setCodigoCondominio(Short.parseShort(aux[2].toString()));
			}
			if (aux[3] != null) {
				c.setObs(aux[3].toString());
			}
			c.setDebitoAutomatico(true);
			l.add(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	public List<intra_controle_concessionarias> pesquisarConcessionariasCnd(int ano, short condominio) {
		List<intra_controle_concessionarias> l = new ArrayList<>();
		intra_controle_concessionarias c = null;
		Query query = this.manager.createNativeQuery(
				"select distinct(d.nro_identificacao),d.fornecedor,p.condominio,d.observacao from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto"
						+ " where p.tipopagto = '4' and d.condominio = :condominio and YEAR(p.vencimento) = :ano order by p.condominio");
		query.setParameter("condominio", condominio);
		query.setParameter("ano", ano);
		List<Object[]> r = query.getResultList();
		for (Object[] aux : r) {
			c = new intra_controle_concessionarias();
			if (aux[0] != null) {
				c.setCodigoDebito(aux[0].toString());
			}
			if (aux[1] != null) {
				c.setFornecedor(aux[1].toString());
			}
			if (aux[2] != null) {
				c.setCodigoCondominio(Short.parseShort(aux[2].toString()));
			}
			if (aux[3] != null) {
				c.setObs(aux[3].toString());
			}
			c.setDebitoAutomatico(true);
			l.add(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	public List<intra_controle_concessionarias> pesquisarConcessionariasPagas(int ano, List<Short> condominios) {
		List<intra_controle_concessionarias> l = new ArrayList<>();
		intra_controle_concessionarias c = null;
		Query query = this.manager.createNativeQuery(
				"select distinct(d.nro_identificacao),d.fornecedor,p.condominio,d.observacao from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndlanch p on f.nrolcto_contas = p.nrolancto"
						+ " where p.tipopagto = '4' and d.condominio IN (:condominio) and YEAR(p.vencimento) = :ano order by p.condominio");
		query.setParameter("condominio", condominios);
		query.setParameter("ano", ano);
		List<Object[]> r = query.getResultList();
		for (Object[] aux : r) {
			c = new intra_controle_concessionarias();
			if (aux[0] != null) {
				c.setCodigoDebito(aux[0].toString());
			}
			if (aux[1] != null) {
				c.setFornecedor(aux[1].toString());
			}
			if (aux[2] != null) {
				c.setCodigoCondominio(Short.parseShort(aux[2].toString()));
			}
			if (aux[3] != null) {
				c.setObs(aux[3].toString());
			}
			c.setDebitoAutomatico(true);
			l.add(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	public List<intra_controle_concessionarias> pesquisarConcessionariasPagasCnd(int ano, short condominio) {
		List<intra_controle_concessionarias> l = new ArrayList<>();
		intra_controle_concessionarias c = null;
		Query query = this.manager.createNativeQuery(
				"select distinct(d.nro_identificacao),d.fornecedor,p.condominio,d.observacao from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndlanch p on f.nrolcto_contas = p.nrolancto"
						+ " where p.tipopagto = '4' and d.condominio = :condominio and YEAR(p.vencimento) = :ano order by p.condominio");
		query.setParameter("condominio", condominio);
		query.setParameter("ano", ano);
		List<Object[]> r = query.getResultList();
		for (Object[] aux : r) {
			c = new intra_controle_concessionarias();
			if (aux[0] != null) {
				c.setCodigoDebito(aux[0].toString());
			}
			if (aux[1] != null) {
				c.setFornecedor(aux[1].toString());
			}
			if (aux[2] != null) {
				c.setCodigoCondominio(Short.parseShort(aux[2].toString()));
			}
			if (aux[3] != null) {
				c.setObs(aux[3].toString());
			}
			c.setDebitoAutomatico(true);
			l.add(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	public List<DebitoAutomatico> pesquisarLanctoConc(long id, short condominio, int ano) {
		List<DebitoAutomatico> l = new ArrayList<>();
		DebitoAutomatico c = null;
		Query query = this.manager.createNativeQuery(
				"select p.vencimento,p.estimado,p.valor,p.historico,p.nrolancto,f.consumo from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto"
						+ " where p.tipopagto = '4' and d.condominio = :condominio and nro_identificacao = :nro_identificacao and YEAR(p.vencimento) = :ano"
						+ " order by p.vencimento, d.id_contrato");
		query.setParameter("nro_identificacao", id);
		query.setParameter("condominio", condominio);
		query.setParameter("ano", ano);
		List<Object[]> r = query.getResultList();
		for (Object[] aux : r) {
			c = new DebitoAutomatico();
			if (aux[0] != null) {
				c.setVencimento((Date) aux[0]);
			}
			if (aux[1] != null) {
				c.setEstimado("P");
			}
			if (aux[2] != null) {
				c.setValor(Double.parseDouble(aux[2].toString()));
			}
			if (aux[3] != null) {
				c.setHistorico(aux[3].toString());
			}
			if (aux[4] != null) {
				c.setNrolancto(Integer.parseInt(aux[4].toString()));
			}
			if (aux[5] != null) {
				c.setConsumoDouble(Double.valueOf(aux[5].toString()));
			}
			l.add(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	public List<DebitoAutomatico> pesquisarLanctoConcPagos(long id, short condominio, int ano) {
		List<DebitoAutomatico> l = new ArrayList<>();
		DebitoAutomatico c = null;
		Query query = this.manager.createNativeQuery(
				"select p.vencimento,p.estimado,p.valor,p.historico,p.nrolancto,f.consumo from sigadm.dbo.cndpagtosmensais d"
						+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
						+ " inner join sigadm.dbo.cndlanch p on f.nrolcto_contas = p.nrolancto"
						+ " where p.tipopagto = '4' and d.condominio = :condominio and nro_identificacao = :nro_identificacao and YEAR(p.vencimento) = :ano"
						+ " order by p.vencimento, d.id_contrato");
		query.setParameter("nro_identificacao", id);
		query.setParameter("condominio", condominio);
		query.setParameter("ano", ano);
		List<Object[]> r = query.getResultList();
		for (Object[] aux : r) {
			c = new DebitoAutomatico();
			if (aux[0] != null) {
				c.setVencimento((Date) aux[0]);
			}
			if (aux[1] != null) {
				c.setEstimado("P");
			}
			if (aux[2] != null) {
				c.setValor(Double.parseDouble(aux[2].toString()));
			}
			if (aux[3] != null) {
				c.setHistorico(aux[3].toString());
			}
			if (aux[4] != null) {
				c.setNrolancto(Integer.parseInt(aux[4].toString()));
			}
			if (aux[5] != null) {
				c.setConsumoDouble(Double.valueOf(aux[5].toString()));
			}
			c.setPago(true);
			l.add(c);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisarPDF1(long id) {
		byte[] pdf = null;
		Query query = this.manager.createNativeQuery(
				"select a.obj from SIGADM_DOCUMENTOS.dbo.glbDocumento a inner join sigadm.dbo.glbCatalogo_Docto b on a.id = b.id inner join sigadm.dbo.glbCatalogo_Lcto c on b.id_catalogo = c.id_catalogo"
						+ " inner join sigadm.dbo.cndlanch d on c.id_lcto = d.nrolancto where d.nrolancto = :nrolancto");
		query.setParameter("nrolancto", id);
		List<Object[]> lst = query.getResultList();
		for (Object aux : lst) {
			if (aux != null) {
				pdf = (byte[]) aux;
			}
		}
		return pdf;
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisarPDF2(long id) {
		byte[] pdf = null;
		Query query = this.manager.createNativeQuery(
				"select a.obj from SIGADM_DOCUMENTOS.dbo.glbDocumento a inner join sigadm.dbo.glbCatalogo_Docto b on a.id = b.id inner join sigadm.dbo.glbCatalogo_Lcto c on b.id_catalogo = c.id_catalogo"
						+ " inner join sigadm.dbo.cndpagar d on c.id_lcto = d.nrolancto where d.nrolancto = :nrolancto");
		query.setParameter("nrolancto", id);
		List<Object[]> lst = query.getResultList();
		for (Object aux : lst) {
			if (aux != null) {
				pdf = (byte[]) aux;
			}
		}
		return pdf;
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios(int codigo) {
		Query query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1");
		query.setParameter("p1", codigo);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaTodosCondominios() {
		Query query = this.manager
				.createQuery("from intra_condominios where codigo between 111 and 4242 order by codigo");
		return query.getResultList();
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
}
