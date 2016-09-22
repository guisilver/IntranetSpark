package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.cndpagar;

public class PagamentoEletronicoContasDAO {

	private EntityManager manager;

	public PagamentoEletronicoContasDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<cndpagar> pesquisaLancamentosEnviar(Date dataInicio, Date dataFim, int conta) {
		List<cndpagar> listaLancamentos = new ArrayList<>();
		cndpagar lancamento = null;
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
			str.append(" and p.conta = :conta");
		}
		str.append(" order by conta_bancaria,tipopagto,p.condominio,nrolancto");
		Query query = this.manager.createNativeQuery(str.toString());
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		if (conta > 0) {
			query.setParameter("conta", conta);
		}
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			lancamento = new cndpagar();
			if (aux[1] != null) {
				lancamento.setNrolancto(Integer.parseInt(aux[1].toString()));
			}
			if (aux[4] != null) {
				lancamento.setCondominio(Short.parseShort(aux[4].toString()));
			}
			if (aux[5] != null) {
				lancamento.setConta(Integer.parseInt(aux[5].toString()));
			}
			if (aux[6] != null) {
				lancamento.setFavorecido(aux[6].toString());
			}
			if (aux[7] != null) {
				lancamento.setHistorico(aux[7].toString());
			}
			if (aux[8] != null) {
				lancamento.setValor(Double.parseDouble(aux[8].toString()));
			}
			if (aux[14] != null) {
				lancamento.setVencimento((Date) aux[14]);
			}
			listaLancamentos.add(lancamento);
		}
		return listaLancamentos;
	}

	public intra_condominios getNomeCondominio(int codigo) {
		return this.manager.find(intra_condominios.class, codigo);
	}

}
