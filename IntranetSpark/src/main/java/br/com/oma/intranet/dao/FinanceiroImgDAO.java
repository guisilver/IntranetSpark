package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.FinanceiroImg;
import br.com.oma.intranet.util.JPAUtil;

public class FinanceiroImgDAO {

	private EntityManager manager;

	public FinanceiroImgDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<FinanceiroImg> pesquisaLancamentosEnviar(Date dataInicio, Date dataFim, int conta) {
		List<FinanceiroImg> listaImg = new ArrayList<>();
		FinanceiroImg img = null;

		StringBuffer str = new StringBuffer();

		str.append(
				"select d.id_catalogo,p.nrolancto,p.conta_bancaria,p.tipopagto,p.condominio,p.conta,p.favorecido,p.historico,p.valor,"
						+ " b.nomebanco,c.cod_agencia,c.nome_agencia,c.conta_corrente,c.cod_banco,p.vencimento,c.condominio as cnd,x.conta_vinculada  from sigadm.dbo.cndpagar p"
						+ " full join sigadm.dbo.glbCatalogo_Lcto d on p.nrolancto = d.id_lcto"
						+ " full join sigadm.dbo.atbconta c on p.conta_bancaria = c.codigo"
						+ " inner join sigadm.dbo.atbancos b  on c.cod_banco = b.codbanco"
						+ " inner join sigadm.dbo.cndcondo x on p.condominio = x.codigo"
						+ " where vencimento between :p1 and :p2 and tipopagto <> '0'"
						+ " and tipopagto <> '1' and tipopagto <> '2' and tipopagto <> '3' and tipopagto <> '4' and tipopagto <> '9' and p.estimado = 'R'"
						+ " and (nro_remessa = 0 or nro_remessa is null)");

		if (conta > 0) {
			str.append(" and p.conta_bancaria = :conta_bancaria");
		}

		str.append(" order by conta_bancaria,tipopagto,p.condominio,nrolancto");

		Query query = this.manager.createNativeQuery(str.toString());
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		if (conta > 0) {
			query.setParameter("conta_bancaria", conta);
		}
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			img = new FinanceiroImg();
			if (aux[0] != null) {
				img.setId_contrato(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				img.getCndpagar().setNrolancto(Integer.parseInt(aux[1].toString()));
			}
			if (aux[2] != null) {
				img.getCndpagar().setContaBancaria(Short.parseShort(aux[2].toString()));
			}
			if (aux[3] != null) {
				img.getCndpagar().setTipoPagto(aux[3].toString());
			}
			if (aux[4] != null) {
				img.getCndpagar().setCondominio(Short.parseShort(aux[4].toString()));
			}
			if (aux[5] != null) {
				img.getCndpagar().setConta(Integer.parseInt(aux[5].toString()));
			}
			if (aux[6] != null) {
				img.getCndpagar().setFavorecido(aux[6].toString());
			}
			if (aux[7] != null) {
				img.getCndpagar().setHistorico(aux[7].toString());
			}
			if (aux[8] != null) {
				img.getCndpagar().setValor(Double.parseDouble(aux[8].toString()));
			}
			if (aux[9] != null) {
				img.setNomeBanco(aux[9].toString());
			}
			if (aux[10] != null) {
				img.setCodAgencia(aux[10].toString());
			}
			if (aux[11] != null) {
				img.setNomeAgencia(aux[11].toString());
			}
			if (aux[12] != null) {
				img.setCC(aux[12].toString());
			}
			if (aux[13] != null) {
				img.setCodBanco(Integer.parseInt(aux[13].toString()));
			}
			if (aux[14] != null) {
				img.getCndpagar().setVencimento((Date) aux[14]);
			}
			if (aux[15] != null) {
				img.setCondominio(Integer.parseInt(aux[15].toString()));
			}
			if (aux[16] != null) {
				img.setContaVinculada(aux[16].toString());
			}
			listaImg.add(img);
		}
		return listaImg;
	}

	@SuppressWarnings("unchecked")
	public List<FinanceiroImg> pesquisaLancamentosEnviados(int nro_remessa) {
		List<FinanceiroImg> listaImg = new ArrayList<>();
		FinanceiroImg img = null;
		Query query = this.manager.createNativeQuery(
				"select d.id_catalogo,p.nrolancto,p.conta_bancaria,p.tipopagto,p.condominio,p.conta,p.favorecido,p.historico,p.valor,"
						+ " b.nomebanco,c.cod_agencia,c.nome_agencia,c.conta_corrente,c.cod_banco,p.vencimento,c.condominio as cnd from sigadm.dbo.cndpagar p"
						+ " full join sigadm.dbo.glbCatalogo_Lcto d on p.nrolancto = d.id_lcto"
						+ " full join sigadm.dbo.atbconta c on p.conta_bancaria = c.codigo"
						+ " inner join sigadm.dbo.atbancos b  on c.cod_banco = b.codbanco"
						+ " where nro_remessa = :p1 order by conta_bancaria, tipopagto, condominio,nrolancto");
		query.setParameter("p1", nro_remessa);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			img = new FinanceiroImg();
			if (aux[0] != null) {
				img.setId_contrato(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				img.getCndpagar().setNrolancto(Integer.parseInt(aux[1].toString()));
			}
			if (aux[2] != null) {
				img.getCndpagar().setContaBancaria(Short.parseShort(aux[2].toString()));
			}
			if (aux[3] != null) {
				img.getCndpagar().setTipoPagto(aux[3].toString());
			}
			if (aux[4] != null) {
				img.getCndpagar().setCondominio(Short.parseShort(aux[4].toString()));
			}
			if (aux[5] != null) {
				img.getCndpagar().setConta(Integer.parseInt(aux[5].toString()));
			}
			if (aux[6] != null) {
				img.getCndpagar().setFavorecido(aux[6].toString());
			}
			if (aux[7] != null) {
				img.getCndpagar().setHistorico(aux[7].toString());
			}
			if (aux[8] != null) {
				img.getCndpagar().setValor(Double.parseDouble(aux[8].toString()));
			}
			if (aux[9] != null) {
				img.setNomeBanco(aux[9].toString());
			}
			if (aux[10] != null) {
				img.setCodAgencia(aux[10].toString());
			}
			if (aux[11] != null) {
				img.setNomeAgencia(aux[11].toString());
			}
			if (aux[12] != null) {
				img.setCC(aux[12].toString());
			}
			if (aux[13] != null) {
				img.setCodBanco(Integer.parseInt(aux[13].toString()));
			}
			if (aux[14] != null) {
				img.getCndpagar().setVencimento((Date) aux[14]);
			}
			if (aux[15] != null) {
				img.setCondominio(Integer.parseInt(aux[15].toString()));
			}
			listaImg.add(img);
		}
		return listaImg;
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

}
