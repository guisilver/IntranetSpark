package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.entidades.intra_emissor;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_lote;
import br.com.oma.intranet.util.JPAUtil;

public class ImpressaoBureauDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6416458196561333646L;
	private EntityManager manager;

	public ImpressaoBureauDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_lote> getListaTodosLotes(Date dataInicio, Date dataFim) {
		List<intra_lote> listaLotes = new ArrayList<>();
		Query query = this.manager
				.createNativeQuery("select codigo, conferido, dataEnviado, nomeLote, numeroLote, referencia, servico from intra_lote where dataEnviado between :p1 and :p2 order by dataEnviado DESC");
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		List<Object[]> listaRetorno = query.getResultList();
		intra_lote lote = null;
		for (Object[] aux : listaRetorno) {
			lote = new intra_lote();
			if (aux[0] != null) {
				lote.setCodigo(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				lote.setConferido(Boolean.parseBoolean(aux[1].toString()));
			}
			if (aux[2] != null) {
				lote.setDataEnviado((Date) aux[2]);
			}
			if (aux[3] != null) {
				lote.setNomeLote(aux[3].toString());
			}
			if (aux[4] != null) {
				lote.setNumeroLote(Integer.parseInt(aux[4].toString()));
			}
			if (aux[5] != null) {
				lote.setReferencia(aux[5].toString());
			}
			if (aux[6] != null) {
				lote.setServico(aux[6].toString());
			}
			listaLotes.add(lote);
		}
		return listaLotes;
	}

	@SuppressWarnings("unchecked")
	public List<intra_lote> pesquisaLoteComNomeArquivo(String nomeArquivo,
			Date dataInicio, Date dataFim) {
		List<intra_lote> listaLotes = new ArrayList<>();
		Query query = this.manager
				.createNativeQuery("select l.codigo, l.conferido, l.dataEnviado, l.nomeLote, l.numeroLote, l.referencia, l.servico from intra_lote l inner join intra_lote_intra_lote_arquivos x on l.codigo = x.intra_lote_codigo inner join intra_lote_arquivos a on x.arquivos_codigo = a.codigo where a.nomeArquivo like :p1 and l.dataEnviado between :p2 and :p3 order by l.dataEnviado DESC");
		query.setParameter("p1", "%" + nomeArquivo + "%");
		query.setParameter("p2", dataInicio);
		query.setParameter("p3", dataFim);
		List<Object[]> listaRetorno = query.getResultList();
		intra_lote lote = null;
		for (Object[] aux : listaRetorno) {
			lote = new intra_lote();
			if (aux[0] != null) {
				lote.setCodigo(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				lote.setConferido(Boolean.parseBoolean(aux[1].toString()));
			}
			if (aux[2] != null) {
				lote.setDataEnviado((Date) aux[2]);
			}
			if (aux[3] != null) {
				lote.setNomeLote(aux[3].toString());
			}
			if (aux[4] != null) {
				lote.setNumeroLote(Integer.parseInt(aux[4].toString()));
			}
			if (aux[5] != null) {
				lote.setReferencia(aux[5].toString());
			}
			if (aux[6] != null) {
				lote.setServico(aux[6].toString());
			}
			listaLotes.add(lote);
		}
		return listaLotes;
	}

	public void salvar(intra_lote loteSelecionado, String usuario, String ip) {
		intra_log_geral log = null;
		String resumoInfos = "Nome:"
				+ loteSelecionado.getNomeLote()
				+ ";Nº:"
				+ loteSelecionado.getNumeroLote()
				+ ";Servico:"
				+ loteSelecionado.getServico()
				+ ";Data:"
				+ loteSelecionado.getDataEnviado()
				+ ";Qtd:"
				+ (loteSelecionado.getArquivos() != null ? loteSelecionado
						.getArquivos().size() : 0);

		if (loteSelecionado.getCodigo() == 0) {
			this.manager.persist(loteSelecionado);
			log = new intra_log_geral(0, this.retornaIdUsuario(), "intra_lote",
					true, false, false, resumoInfos, new Date(), 0, null, null);
		} else {
			this.manager.merge(loteSelecionado);
			log = new intra_log_geral(0, this.retornaIdUsuario(), "intra_lote",
					false, true, false, resumoInfos, new Date(), 0, null, null);
		}
		log.setIp(ip);
		this.logGeral(log);
	}

	public void excluir(intra_lote loteSelecionado, String usuario, String ip) {
		loteSelecionado = this.manager.merge(loteSelecionado);
		intra_emissor emissor = this.manager.merge(loteSelecionado.getEmissor());
		loteSelecionado.setEmissor(null);
		emissor.setLote(null);
		this.manager.remove(emissor);
		this.manager.remove(loteSelecionado);

		String resumoInfos = "Nome:"
				+ loteSelecionado.getNomeLote()
				+ ";Nº:"
				+ loteSelecionado.getNumeroLote()
				+ ";Servico:"
				+ loteSelecionado.getServico()
				+ ";Data:"
				+ loteSelecionado.getDataEnviado()
				+ ";Qtd:"
				+ (loteSelecionado.getArquivos() != null ? loteSelecionado
						.getArquivos().size() : 0);

		intra_log_geral log = new intra_log_geral(0, this.retornaIdUsuario(),
				"intra_lote", false, false, true, resumoInfos, new Date(), 0,
				null, null);
		log.setIp(ip);
		this.logGeral(log);
	}

	public intra_lote pesquisaLotePorCodigo(int codigo) {
		return this.manager.find(intra_lote.class, codigo);
	}

	public void salvarListaLotes(List<intra_lote> listaLotes) {
		for (intra_lote aux : listaLotes) {
			if (aux.getCodigo() == 0) {
				this.manager.persist(aux);
			} else {
				this.manager.merge(aux);
			}
		}
	}

	public int pesquisaQtdArquivos(intra_lote lote) {
		Query query = this.manager
				.createNativeQuery("select intra_lote_codigo from intra_lote_intra_lote_arquivos where intra_lote_codigo = :p1");
		query.setParameter("p1", lote.getCodigo());
		return query.getResultList().size();
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}
	
	public intra_lote pesquisaGlobalPorCodigo(Integer codigo) {
		return this.manager.find(intra_lote.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<intra_emissor> pesquisaEmissorLote(intra_lote loteSelecionado) {
		loteSelecionado = this.manager.merge(loteSelecionado);
		Query query = this.manager
				.createQuery("from intra_emissor where lote.codigo = :p1");
		query.setParameter("p1", loteSelecionado.getCodigo());
		return query.getResultList();
	}
}
