package br.com.oma.intranet.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.cndespes;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.financeiro_imagem;

public class TrocaDeImagemDAO {

	private EntityManager manager;

	public TrocaDeImagemDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<cndespes> pesquisarLancamentos(int condominio, int conta, Date dataInicio, Date dataFim) {
		List<cndespes> lancamentos = new ArrayList<>();
		cndespes lancamento = null;
		StringBuffer strBf = new StringBuffer();
		strBf.append(
				"select e.numero, e.condominio, e.bloco, e.data, e.conta, e.sinal, e.valor, e.contra_partida, e.cpmf, e.valor_ipmf, e.historico, e.cta_anl_financ,"
						+ " e.codigo_cheque, e.id_gerador, e.copia_cheque, e.incide_tx_adm, e.reg_atualizado, e.rateio, e.nro_lanch, e.nro_id, e.nro_cotacao, c.nome"
						+ " from sigadm.dbo.cndespes e inner join sigadm.dbo.cndcondo c on e.condominio = c.codigo  where 1 = 1 and sinal = 'D'");
		if (condominio > 0) {
			strBf.append(" and condominio = :condominio");
		}
		if (conta > 0) {
			strBf.append(" and conta = :conta");
		}
		if (dataInicio != null && dataFim != null) {
			strBf.append(" and data between :dataInicio and :dataFim");
		}
		strBf.append(" order by data");
		Query query = this.manager.createNativeQuery(strBf.toString());
		if (condominio > 0) {
			query.setParameter("condominio", condominio);
		}
		if (conta > 0) {
			query.setParameter("conta", conta);
		}
		if (dataInicio != null && dataFim != null) {
			query.setParameter("dataInicio", dataInicio);
			query.setParameter("dataFim", dataFim);
		}
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			lancamento = new cndespes();
			if (aux[0] != null) {
				lancamento.setNumero(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				lancamento.setCondominio(Integer.parseInt(aux[1].toString()));
			}
			if (aux[2] != null) {
				lancamento.setBloco(aux[2].toString());
			}
			if (aux[3] != null) {
				lancamento.setData((Date) aux[3]);
			}
			if (aux[4] != null) {
				lancamento.setConta(Integer.parseInt(aux[4].toString()));
			}
			if (aux[5] != null) {
				lancamento.setSinal(aux[5].toString());
			}
			if (aux[6] != null) {
				lancamento.setValor(Double.parseDouble(aux[6].toString()));
			}
			if (aux[7] != null) {
				lancamento.setContra_partida(Integer.parseInt(aux[7].toString()));
			}
			if (aux[8] != null) {
				lancamento.setCpmf(aux[8].toString());
			}
			if (aux[9] != null) {
				lancamento.setValor_ipmf(Double.parseDouble(aux[9].toString()));
			}
			if (aux[10] != null) {
				lancamento.setHistorico(aux[10].toString());
			}
			if (aux[11] != null) {
				lancamento.setCta_anl_financ(Integer.parseInt(aux[11].toString()));
			}
			if (aux[12] != null) {
				lancamento.setCodigo_cheque(Integer.parseInt(aux[12].toString()));
			}
			if (aux[13] != null) {
				lancamento.setId_gerador(Integer.parseInt(aux[13].toString()));
			}
			if (aux[14] != null) {
				lancamento.setCopia_cheque(Integer.parseInt(aux[14].toString()));
			}
			if (aux[15] != null) {
				lancamento.setIncide_tx_adm(Integer.parseInt(aux[15].toString()));
			}
			if (aux[16] != null) {
				lancamento.setReg_atualizado(Integer.parseInt(aux[16].toString()));
			}
			if (aux[17] != null) {
				lancamento.setRateio(Integer.parseInt(aux[17].toString()));
			}
			if (aux[18] != null) {
				lancamento.setNro_lanch(Integer.parseInt(aux[18].toString()));
			}
			if (aux[19] != null) {
				lancamento.setNro_id(Integer.parseInt(aux[19].toString()));
			}
			if (aux[20] != null) {
				lancamento.setNro_cotacao(Integer.parseInt(aux[20].toString()));
			}
			if (aux[21] != null) {
				lancamento.setNomeCondominio(aux[21].toString());
			}
			lancamentos.add(lancamento);
		}
		return lancamentos;
	}

	@SuppressWarnings("unchecked")
	public cndespes pesquisaLancamentoPorCodigo(int codigoLancamento) {
		List<cndespes> lancamentos = new ArrayList<>();
		cndespes lancamento = null;
		Query query = this.manager
				.createNativeQuery("select numero, condominio, bloco, data, conta, sinal, valor, contra_partida,"
						+ " cpmf, valor_ipmf, historico, cta_anl_financ, codigo_cheque, id_gerador,"
						+ " copia_cheque, incide_tx_adm, reg_atualizado, rateio, nro_lanch, nro_id,"
						+ " nro_cotacao from sigadm.dbo.cndespes where 1 = 1 and sinal = 'D' and numero = :numero  order by data");
		query.setParameter("numero", codigoLancamento);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			lancamento = new cndespes();
			if (aux[0] != null) {
				lancamento.setNumero(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				lancamento.setCondominio(Integer.parseInt(aux[1].toString()));
			}
			if (aux[2] != null) {
				lancamento.setBloco(aux[2].toString());
			}
			if (aux[3] != null) {
				lancamento.setData((Date) aux[3]);
			}
			if (aux[4] != null) {
				lancamento.setConta(Integer.parseInt(aux[4].toString()));
			}
			if (aux[5] != null) {
				lancamento.setSinal(aux[5].toString());
			}
			if (aux[6] != null) {
				lancamento.setValor(Double.parseDouble(aux[6].toString()));
			}
			if (aux[7] != null) {
				lancamento.setContra_partida(Integer.parseInt(aux[7].toString()));
			}
			if (aux[8] != null) {
				lancamento.setCpmf(aux[8].toString());
			}
			if (aux[9] != null) {
				lancamento.setValor_ipmf(Double.parseDouble(aux[9].toString()));
			}
			if (aux[10] != null) {
				lancamento.setHistorico(aux[10].toString());
			}
			if (aux[11] != null) {
				lancamento.setCta_anl_financ(Integer.parseInt(aux[11].toString()));
			}
			if (aux[12] != null) {
				lancamento.setCodigo_cheque(Integer.parseInt(aux[12].toString()));
			}
			if (aux[13] != null) {
				lancamento.setId_gerador(Integer.parseInt(aux[13].toString()));
			}
			if (aux[14] != null) {
				lancamento.setCopia_cheque(Integer.parseInt(aux[14].toString()));
			}
			if (aux[15] != null) {
				lancamento.setIncide_tx_adm(Integer.parseInt(aux[15].toString()));
			}
			if (aux[16] != null) {
				lancamento.setReg_atualizado(Integer.parseInt(aux[16].toString()));
			}
			if (aux[17] != null) {
				lancamento.setRateio(Integer.parseInt(aux[17].toString()));
			}
			if (aux[18] != null) {
				lancamento.setNro_lanch(Integer.parseInt(aux[18].toString()));
			}
			if (aux[19] != null) {
				lancamento.setNro_id(Integer.parseInt(aux[19].toString()));
			}
			if (aux[20] != null) {
				lancamento.setNro_cotacao(Integer.parseInt(aux[20].toString()));
			}
			lancamentos.add(lancamento);
		}
		if (lancamentos != null && lancamentos.size() > 0) {
			return lancamentos.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<financeiro_imagem> pesquisarImagens(int codigo) {
		List<financeiro_imagem> listaImagem = new ArrayList<>();
		financeiro_imagem imagem = null;
		Query query = this.manager.createNativeQuery(
				"select distinct(d.id_catalogo),d.nome_arquivo,d.identificacao_tipo_arq, l.id_lcto, x.id from"
						+ " sigadm.dbo.glbCatalogo_Docto d inner join sigadm.dbo.glbCatalogo_Lcto l on"
						+ " d.id_catalogo = l.id_catalogo inner join SIGADM_DOCUMENTOS.dbo.glbDocumento"
						+ " x on x.id = d.id where l.id_lcto = :codigo");
		query.setParameter("codigo", codigo);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			imagem = new financeiro_imagem();
			if (aux[0] != null) {
				imagem.setId(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				imagem.setNome_arquivo(aux[1].toString());
			}
			if (aux[2] != null) {
				imagem.setIdentificacaoTipoArq(aux[2].toString());
			}
			if (aux[3] != null) {
				imagem.setCodLanctoOma(Integer.parseInt(aux[3].toString()));
			}
			if (aux[4] != null) {
				imagem.setCodigo(Integer.parseInt(aux[4].toString()));
			}

			Double id = imagem.getId();
			long idLong = id.longValue();

			if (!listaJaPossuiImg(listaImagem, idLong)) {
				listaImagem.add(imagem);
			}
		}
		return listaImagem;
	}

	public boolean listaJaPossuiImg(List<financeiro_imagem> lista, long etiqueta) {
		boolean retorno = false;
		for (financeiro_imagem aux : lista) {
			if (aux.getId() == etiqueta) {
				retorno = true;
			}
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisarImagemPorCodigo(int codigo) {
		List<financeiro_imagem> listaImagem = new ArrayList<>();
		financeiro_imagem imagem = null;
		Query query = this.manager
				.createNativeQuery("select id,obj from SIGADM_DOCUMENTOS.dbo.glbDocumento where id = :id");
		query.setParameter("id", codigo);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			imagem = new financeiro_imagem();
			if (aux[0] != null) {
				imagem.setCodigo(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				imagem.setImagem((byte[]) aux[1]);
			}
			listaImagem.add(imagem);
		}
		if (listaImagem != null && listaImagem.size() > 0) {
			return listaImagem.get(0).getImagem();
		} else {
			return null;
		}
	}

	public void adicionarImagem(financeiro_imagem img, String usuario, int numero, int nro_lanch) {
		Double id = img.getId();
		long idLong = id.longValue();
		Query query = null;
		if (nro_lanch > 0) {
			query = this.manager.createNativeQuery(
					"INSERT INTO sigadm.dbo.glbCatalogo_Lcto(id_catalogo,id_lcto,tipo_lcto) VALUES(:id_catalogo,:id_lcto, 'P')");
			query.setParameter("id_catalogo", idLong);
			query.setParameter("id_lcto", nro_lanch);
			query.executeUpdate();
		}
		query = this.manager.createNativeQuery(
				"INSERT INTO sigadm.dbo.glbCatalogo_Lcto(id_catalogo,id_lcto,tipo_lcto) VALUES(:id_catalogo,:id_lcto, 'L')");
		query.setParameter("id_catalogo", idLong);
		query.setParameter("id_lcto", numero);
		query.executeUpdate();
	}

	public void excluirImagem(long idLong, int nro_lanch, int numero) {
		Query query = this.manager.createNativeQuery(
				"delete sigadm.dbo.glbCatalogo_Lcto where id_catalogo = :id_catalogo and id_lcto = :id_lcto");
		query.setParameter("id_catalogo", idLong);
		query.setParameter("id_lcto", nro_lanch);
		query.executeUpdate();

		query = this.manager.createNativeQuery(
				"delete sigadm.dbo.glbCatalogo_Lcto where id_catalogo = :id_catalogo and id_lcto = :id_lcto");
		query.setParameter("id_catalogo", idLong);
		query.setParameter("id_lcto", numero);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public boolean validaEtiquetaJaFoiUsada(Integer etiqueta) {
		Query query = this.manager
				.createNativeQuery("select * from sigadm.dbo.glbCatalogo_Docto where id_catalogo = :codigo");
		query.setParameter("codigo", etiqueta);
		List<Object[]> lista = query.getResultList();
		if (!lista.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean validaEtiquetaExiste(BigDecimal id) {
		Query query = this.manager.createNativeQuery("select id from sigadm.dbo.glbCatalogo where id = :id");
		query.setParameter("id", id);
		List<Object> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public financeiro_imagem pesquisaImagemEtiquetaSiga(long etiqueta) {
		financeiro_imagem imagem = null;
		Query query = this.manager.createNativeQuery(
				"select d.nome_arquivo,x.obj from sigadm.dbo.glbCatalogo_Docto d inner join sigadm.dbo.glbCatalogo_Lcto l on"
						+ " d.id_catalogo = l.id_catalogo inner join SIGADM_DOCUMENTOS.dbo.glbDocumento x on x.id = d.id where l.id_catalogo = :etiqueta");
		query.setParameter("etiqueta", etiqueta);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			imagem = new financeiro_imagem();
			if (aux[0] != null) {
				imagem.setNome_arquivo(aux[0].toString());
			}
			if (aux[1] != null) {
				imagem.setImagem((byte[]) aux[1]);
			}
		}
		return imagem;
	}

	@SuppressWarnings("unchecked")
	public boolean imagemExiste(long etiqueta) {
		Query query = this.manager.createNativeQuery(
				"select d.nome_arquivo from sigadm.dbo.glbCatalogo_Docto d inner join sigadm.dbo.glbCatalogo_Lcto l on"
						+ " d.id_catalogo = l.id_catalogo inner join SIGADM_DOCUMENTOS.dbo.glbDocumento x on x.id = d.id where l.id_catalogo = :etiqueta");
		query.setParameter("etiqueta", etiqueta);
		List<String> l = (List<String>) query.getResultList();
		if (l != null && l.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
