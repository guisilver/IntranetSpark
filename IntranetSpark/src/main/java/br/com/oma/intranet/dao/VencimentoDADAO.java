package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.pc.modelo.entidades.Condominio;

public class VencimentoDADAO {

	private EntityManager manager;

	public VencimentoDADAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<DebitoAutomatico> getListaVencimentoDA(Date dataInicio, Date dataFim, int codGerente) {
		List<DebitoAutomatico> l = new ArrayList<>();
		StringBuffer strBf = new StringBuffer();
		DebitoAutomatico da = null;
		strBf.append("select p.condominio,d.id_contrato, p.nrolancto, p.vencimento,p.estimado, p.conta,"
				+ " p.historico, p.credor, p.valor, d.observacao,i.id_catalogo,d.nro_identificacao,d.cod_deb_autom, f.observacao as obs, f.id_fatura from sigadm.dbo.cndpagtosmensais d"
				+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
				+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto"
				+ " inner join sigadm.dbo.cndcondo c on p.condominio = c.codigo"
				+ " full join sigadm.dbo.glbCatalogo_Lcto i on p.nrolancto = i.id_lcto"
				+ " where p.vencimento between :dataInicio and :dataFim and p.tipopagto = '4'");
		if (codGerente > 1) {
			strBf.append(" and c.gerente = :codGerente");
		}
		strBf.append(" order by p.vencimento");
		Query query = this.manager.createNativeQuery(strBf.toString());
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		if (codGerente > 1) {
			query.setParameter("codGerente", codGerente);
		}
		List<Object[]> resultado = query.getResultList();
		for (Object[] aux : resultado) {
			da = new DebitoAutomatico();
			if (aux[0] != null) {
				da.setCondominio(Short.parseShort(aux[0].toString()));
				Condominio cnd = this.pesquisaCodNomeCnd(da.getCondominio());
				if (cnd != null) {
					da.setCndCodNome(cnd.getCodigo() + " - " + cnd.getNome());
				}
			}
			if (aux[1] != null) {
				da.setId_contrato(Integer.parseInt(aux[1].toString()));
			}
			if (aux[2] != null) {
				da.setNrolancto(Integer.parseInt(aux[2].toString()));
				da.setPossuiImg(this.possuiImg(da.getNrolancto(), "P"));
			}
			if (aux[3] != null) {
				da.setVencimento((Date) aux[3]);
			}
			if (aux[4] != null) {
				da.setEstimado(aux[4].toString());
			}
			if (aux[5] != null) {
				da.setConta(Integer.parseInt(aux[5].toString()));
			}
			if (aux[6] != null) {
				da.setHistorico(aux[6].toString());
			}
			if (aux[7] != null) {
				da.setCredor(aux[7].toString());
			}
			if (aux[8] != null) {
				da.setValor(Double.parseDouble(aux[8].toString()));
			}
			if (aux[9] != null) {
				da.setObs(aux[9].toString());
			}
			if (aux[10] != null) {
				da.setId_catalogo(Integer.parseInt(aux[10].toString()));
			}
			if (aux[11] != null) {
				da.setNro_identificacao(aux[11].toString());
			}
			if (aux[12] != null) {
				da.setCodDebAutom(StringUtils.stripStart(aux[12].toString(), "0"));
			}
			if (aux[13] != null) {
				da.setObsFM(aux[13].toString());
			}
			if (aux[14] != null) {
				da.setIdFatura(Integer.parseInt(aux[14].toString()));
			}
			l.add(da);
		}
		return l;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public int getListaVencimentoDAContador(Date dataInicio, Date dataFim, int codGerente) {
		int contador = 0;
		List<DebitoAutomatico> l = new ArrayList<>();
		StringBuffer strBf = new StringBuffer();
		DebitoAutomatico da = null;
		strBf.append("select count(p.condominio) from sigadm.dbo.cndpagtosmensais d"
				+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
				+ " inner join sigadm.dbo.cndpagar p on f.nrolcto_contas = p.nrolancto"
				+ " inner join sigadm.dbo.cndcondo c on p.condominio = c.codigo"
				+ " full join sigadm.dbo.glbCatalogo_Lcto i on p.nrolancto = i.id_lcto"
				+ " where p.vencimento between :dataInicio and :dataFim and p.tipopagto = '4'");
		if (codGerente > 1) {
			strBf.append(" and c.gerente = :codGerente");
		}
		Query query = this.manager.createNativeQuery(strBf.toString());
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		if (codGerente > 1) {
			query.setParameter("codGerente", codGerente);
		}
		contador = (int) query.getSingleResult();
		/*if(!resultado.isEmpty()){
			for (Integer objects : resultado) {
				contador = objects;
			}
		}*/
		return contador;
	}

	@SuppressWarnings("unchecked")
	public List<DebitoAutomatico> getListaVencimentoDAPagos(Date dataInicio, Date dataFim, int codGerente) {
		List<DebitoAutomatico> l = new ArrayList<>();
		StringBuffer strBf = new StringBuffer();
		DebitoAutomatico da = null;
		strBf.append("select p.condominio,d.id_contrato, p.nrolancto, p.vencimento,p.estimado, p.conta,"
				+ " p.historico, p.credor, p.valor, d.observacao,i.id_catalogo,d.nro_identificacao,d.cod_deb_autom, f.observacao as obs, f.id_fatura from sigadm.dbo.cndpagtosmensais d"
				+ " inner join sigadm.dbo.cndfaturasmensais f on d.id_contrato = f.id_contrato"
				+ " inner join sigadm.dbo.cndlanch p on f.nrolcto_contas = p.nrolancto"
				+ " inner join sigadm.dbo.cndcondo c on p.condominio = c.codigo"
				+ " full join sigadm.dbo.glbCatalogo_Lcto i on p.nrolancto = i.id_lcto"
				+ " where p.vencimento between :dataInicio and :dataFim and p.tipopagto = '4'");
		if (codGerente > 1) {
			strBf.append(" and c.gerente = :codGerente");
		}
		strBf.append(" order by p.vencimento");
		Query query = this.manager.createNativeQuery(strBf.toString());
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		if (codGerente > 1) {
			query.setParameter("codGerente", codGerente);
		}
		List<Object[]> resultado = query.getResultList();
		for (Object[] aux : resultado) {
			da = new DebitoAutomatico();
			if (aux[0] != null) {
				da.setCondominio(Short.parseShort(aux[0].toString()));
				Condominio cnd = this.pesquisaCodNomeCnd(da.getCondominio());
				if (cnd != null) {
					da.setCndCodNome(cnd.getCodigo() + " - " + cnd.getNome());
				}
			}
			if (aux[1] != null) {
				da.setId_contrato(Integer.parseInt(aux[1].toString()));
			}
			if (aux[2] != null) {
				da.setNrolancto(Integer.parseInt(aux[2].toString()));
				da.setPossuiImg(this.possuiImg(da.getNrolancto(), "P"));
			}
			if (aux[3] != null) {
				da.setVencimento((Date) aux[3]);
			}
			if (aux[4] != null) {
				da.setEstimado(aux[4].toString());
			}
			if (aux[5] != null) {
				da.setConta(Integer.parseInt(aux[5].toString()));
			}
			if (aux[6] != null) {
				da.setHistorico(aux[6].toString());
			}
			if (aux[7] != null) {
				da.setCredor(aux[7].toString());
			}
			if (aux[8] != null) {
				da.setValor(Double.parseDouble(aux[8].toString()));
			}
			if (aux[9] != null) {
				da.setObs(aux[9].toString());
			}
			if (aux[10] != null) {
				da.setId_catalogo(Integer.parseInt(aux[10].toString()));
			}
			if (aux[11] != null) {
				da.setNro_identificacao(aux[11].toString());
			}
			if (aux[12] != null) {
				da.setCodDebAutom(StringUtils.stripStart(aux[12].toString(), "0"));
			}
			if (aux[13] != null) {
				da.setObsFM(aux[13].toString());
			}
			if (aux[14] != null) {
				da.setIdFatura(Integer.parseInt(aux[14].toString()));
			}
			l.add(da);
		}
		return l;
	}

	@SuppressWarnings("unchecked")
	public Condominio pesquisaCodNomeCnd(int codigo) {
		Condominio cnd = null;
		Query query = this.manager.createQuery("select nome from intra_condominios where codigo = :codigo")
				.setMaxResults(1);
		query.setParameter("codigo", codigo);
		List<String> l = query.getResultList();
		for (String aux : l) {
			cnd = new Condominio();
			cnd.setCodigo(Short.parseShort(String.valueOf(codigo)));
			if (aux != null) {
				cnd.setNome(aux);
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

	public int updateObsFatura(int idFatura, String obs) {
		Query query = this.manager
				.createNativeQuery("update sigadm.dbo.cndfaturasmensais set observacao = :p1 where id_fatura = :p2");
		query.setParameter("p1", obs);
		query.setParameter("p2", idFatura);
		int i = query.executeUpdate();
		return i;
	}

}
