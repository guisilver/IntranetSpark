package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_emissor;
import br.com.oma.intranet.entidades.intra_emissor_arquivos;
import br.com.oma.intranet.entidades.intra_emissor_nome_lote;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_lote;
import br.com.oma.intranet.util.JPAUtil;

public class EmissorDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3909159843691518979L;
	private EntityManager manager;

	public EmissorDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvar(intra_emissor emissor, intra_emissor_arquivos emissorArquivos, String usuario, String ip) {
		this.manager.persist(emissorArquivos);
		emissor.setArquivos(emissorArquivos);
		this.manager.persist(emissor);

		String resumoInfos = "Nome CND: " + emissor.getNomeCondominio() + "; Cod. CND: " + emissor.getCodCondominio()
				+ "; Cod. Gerente: " + emissor.getCodGerente() + "; Referencia: " + emissor.getReferencia()
				+ "; Situacao: " + emissor.getSituacao() + "; Cod. Emissor: " + emissor.getCodigo()
				+ "; Data Conferido: " + emissor.getDataConferido() + "; Data Enviado: " + emissor.getDataEnviado();

		intra_log_geral log = new intra_log_geral(emissor.getCodCondominio(), this.retornaIdUsuario(), "intra_emissor",
				true, false, false, resumoInfos, new Date(), 0, null, null);
		log.setIp(ip);
		this.logGeral(log);
	}

	public String pesquisaNomeCond(short codCondominio) {
		Query query = this.manager.createQuery("select nome from intra_condominios where codigo = :codCondominio");
		query.setParameter("codCondominio", (int) codCondominio);
		return (String) query.getSingleResult();
	}

	@SuppressWarnings({ "unchecked" })
	public List<intra_emissor> listaTodosEmissores(Date dataInicio, Date dataFim) {
		List<intra_emissor> listaEmissores = new ArrayList<>();
		Query query = this.manager.createNativeQuery(
				"select codigo,codCondominio,codGerente,dataConferido,dataEnviado,nomeCondominio,referencia,situacao,lote_codigo from intra_emissor where dataEnviado between :dataInicio and :dataFim  order by dataEnviado DESC");
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		intra_emissor emissor = null;
		for (Object[] aux : (List<Object[]>) query.getResultList()) {
			emissor = new intra_emissor();
			if (aux[0] != null) {
				emissor.setCodigo(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				emissor.setCodCondominio(Short.parseShort(aux[1].toString()));
			}
			if (aux[2] != null) {
				emissor.setCodGerente(Integer.parseInt(aux[2].toString()));
			}
			if (aux[3] != null) {
				emissor.setDataConferido((Date) aux[3]);
			}
			if (aux[4] != null) {
				emissor.setDataEnviado((Date) aux[4]);
			}
			if (aux[5] != null) {
				emissor.setNomeCondominio(aux[5].toString());
			}
			if (aux[6] != null) {
				emissor.setReferencia(aux[6].toString());
			}
			if (aux[7] != null) {
				emissor.setSituacao(aux[7].toString());
			}
			listaEmissores.add(emissor);
		}
		return listaEmissores;
	}

	@SuppressWarnings({ "unchecked" })
	public List<intra_emissor> listaEmissoresGerente(int codGerente, Date dataInicio, Date dataFim) {
		List<intra_emissor> listaEmissores = new ArrayList<>();
		Query query = this.manager.createNativeQuery(
				"select codigo,codCondominio,codGerente,dataConferido,dataEnviado,nomeCondominio,referencia,situacao from intra_emissor where codGerente = :codGerente and dataEnviado between :dataInicio and :dataFim  order by dataEnviado DESC");
		query.setParameter("codGerente", codGerente);
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		intra_emissor emissor = null;
		for (Object[] aux : (List<Object[]>) query.getResultList()) {
			emissor = new intra_emissor();
			if (aux[0] != null) {
				emissor.setCodigo(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				emissor.setCodCondominio(Short.parseShort(aux[1].toString()));
			}
			if (aux[2] != null) {
				emissor.setCodGerente(Integer.parseInt(aux[2].toString()));
			}
			if (aux[3] != null) {
				emissor.setDataConferido((Date) aux[3]);
			}
			if (aux[4] != null) {
				emissor.setDataEnviado((Date) aux[4]);
			}
			if (aux[5] != null) {
				emissor.setNomeCondominio(aux[5].toString());
			}
			if (aux[6] != null) {
				emissor.setReferencia(aux[6].toString());
			}
			if (aux[7] != null) {
				emissor.setSituacao(aux[7].toString());
			}
			listaEmissores.add(emissor);
		}
		return listaEmissores;
	}

	@SuppressWarnings("unchecked")
	public List<Short> listaTodosCnd() {
		Query query = this.manager.createQuery("select codigo from intra_condominios order by codigo");
		List<Short> lst = query.getResultList();
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<Short> listaCndGerente(int codgeren) {
		Query query = this.manager
				.createQuery("select codigo from intra_condominios where codigoGerente = :codgeren order by codigo");
		query.setParameter("codgeren", (int) codgeren);
		List<Short> lst = query.getResultList();
		return lst;
	}

	public void salvarLote(intra_lote lote) {
		this.manager.persist(lote);
	}

	public void excluir(intra_emissor emissorSelecionado, String usuario, String ip) {
		emissorSelecionado = this.manager.merge(emissorSelecionado);
		this.manager.remove(emissorSelecionado);

		String resumoInfos = "Nome CND: " + emissorSelecionado.getNomeCondominio() + "; Cod. CND: "
				+ emissorSelecionado.getCodCondominio() + "; Cod. Gerente: " + emissorSelecionado.getCodGerente()
				+ "; Referencia: " + emissorSelecionado.getReferencia() + "; Situacao: "
				+ emissorSelecionado.getSituacao() + "; Cod. Emissor: " + emissorSelecionado.getCodigo()
				+ "; Data Conferido: " + emissorSelecionado.getDataConferido() + "; Data Enviado: "
				+ emissorSelecionado.getDataEnviado();

		intra_log_geral log = new intra_log_geral(emissorSelecionado.getCodCondominio(), this.retornaIdUsuario(),
				"intra_emissor", false, false, true, resumoInfos, new Date(), 0, null, null);
		log.setIp(ip);
		this.logGeral(log);
	}

	public boolean verificaNomeLoteValido(String nomeGerado) {
		intra_emissor_nome_lote e = this.manager.find(intra_emissor_nome_lote.class, 1L,
				LockModeType.PESSIMISTIC_WRITE);
		if (e == null) {
			e = new intra_emissor_nome_lote();
			e.setNomeLote(nomeGerado);
			e.setVersao(e.getVersao() + 1);
			this.manager.persist(e);
			return true;
		} else if (e.getNomeLote() != null && !e.getNomeLote().equals(nomeGerado)) {
			e.setNomeLote(nomeGerado);
			e.setVersao(e.getVersao() + 1);
			this.manager.persist(e);
			return true;
		} else {
			return false;
		}
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

	@SuppressWarnings("unchecked")
	public List<intra_emissor_arquivos> pesquisaArquivosEmissor(intra_emissor emissorSelecionado) {
		Query query = this.manager.createQuery("from intra_emissor_arquivos where emissor.codigo = :p1");
		query.setParameter("p1", emissorSelecionado.getCodigo());
		return query.getResultList();
	}

	public intra_emissor pesquisaEmissorPorCodigo(Integer codigo) {
		return this.manager.find(intra_emissor.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public int retornaCodigoDoServico(intra_condominios condominio, double tipoDoEmissor) {
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

	public void salvarCobrancaServico(int codigoMsvcon,int codigoDoServico, double qtdaImpressao, String historico) {
		Query query = this.manager.createNativeQuery(
				"insert into sigadm.dbo.cndsvmov (codigo, cod_svcon, qtde, historico, data) values ("+codigoMsvcon+","+codigoDoServico+", "+qtdaImpressao+",'"+historico+"',getdate())");
		query.executeUpdate();
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
}
