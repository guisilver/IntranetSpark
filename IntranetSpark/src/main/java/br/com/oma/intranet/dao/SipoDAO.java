package br.com.oma.intranet.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.financeiro_imagem;

@Stateless
public class SipoDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4745766239027804054L;
	private EntityManager manager;
	
	private Query query;

	public SipoDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios() {
		this.query = this.manager.createQuery("from intra_condominios ");
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios(int codigoGerente) {
		this.query = this.manager
				.createQuery("from intra_condominios where codigoGerente = :p1 and codigo <> 1 order by codigo");
		this.query.setParameter("p1", codigoGerente);
		return this.query.getResultList();
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

	// ↓ MÓDULO GED ↓

	public void salvarImagemGED(financeiro_imagem codigo) {
		if (codigo.getCodigo() == 0) {
			this.manager.persist(codigo);
			intra_log_geral log = new intra_log_geral(codigo.getCodigo(), retornaIdUsuario(), "financeiro_imagem", true,
					false, false, codigo.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		} else {
			this.manager.merge(codigo);
			intra_log_geral log = new intra_log_geral(codigo.getCodigo(), retornaIdUsuario(), "financeiro_imagem",
					false, true, false, codigo.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		}
	}

	@SuppressWarnings("unchecked")
	public List<financeiro_imagem> listarImagem() {
		List<financeiro_imagem> lfi = new ArrayList<financeiro_imagem>();
		this.query = this.manager.createNativeQuery("select codigo, data_vinculo_arq, id, cd_cnd, recebido_contabilidade, data_rec_contab "
				+ "from omaonline.dbo.financeiro_imagem where usuario_vinculo_arq = 'ged' order by codigo DESC");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				financeiro_imagem fi = new financeiro_imagem();
				if (obj[0] != null) {
					fi.setCodigo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					fi.setDataVinculoArq((Timestamp) (obj[1]));
				}
				if (obj[2] != null) {
					fi.setId(Double.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					fi.setCdCnd(Short.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					fi.setRecebidoContabilidade(obj[4].toString());
				}
				if (obj[5] != null) {
					fi.setDataRecebidoContabilidade((Date) obj[5]);
				}
				lfi.add(fi);
			}
		}
		return lfi;
	}

	@SuppressWarnings("unchecked")
	public List<financeiro_imagem> listarImagem(Date dataInicio, Date dataFim) {
		List<financeiro_imagem> lfi = new ArrayList<financeiro_imagem>();
		this.query = this.manager.createNativeQuery(
				"select codigo, data_vinculo_arq, id, cd_cnd " + "from omaonline.dbo.financeiro_imagem "
						+ "where data_vinculo_arq between :p1 and :p2 order by codigo DESC");
/*		this.query.setParameter("p1", new java.sql.Date(dataInicio.getTime()));
		this.query.setParameter("p2", new java.sql.Date(dataFim.getTime()));*/
		this.query.setParameter("p1", dataInicio);
		this.query.setParameter("p2", dataFim);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				financeiro_imagem fi = new financeiro_imagem();
				if (obj[0] != null) {
					fi.setCodigo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					fi.setDataVinculoArq((Timestamp) (obj[1]));
				}
				if (obj[2] != null) {
					fi.setId(Double.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					fi.setCdCnd(Short.valueOf(obj[3].toString()));
				}
				lfi.add(fi);
			}
		}
		return lfi;
	}

	@SuppressWarnings("unchecked")
	public financeiro_imagem pesquisaImagem(double etiqueta, int condominio) {
		this.query = this.manager.createQuery("from omaonline.dbo.financeiro_imagem where id = :p1 and cd_cnd = :p2 ");
		this.query.setParameter("p1", etiqueta);
		this.query.setParameter("p2", Short.valueOf(String.valueOf(condominio)));
		List<financeiro_imagem> lst = query.getResultList();
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return new financeiro_imagem();
		}
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaPDF(Integer id) {
		byte[] pdf = null;
		this.query = this.manager.createQuery("from omaonline.dbo.financeiro_imagem where codigo = :p1 ");
		this.query.setParameter("p1", id);
		List<financeiro_imagem> lst = this.query.getResultList();
		for (financeiro_imagem aux : lst) {
			if (aux.getImagem() != null) {
				pdf = aux.getImagem();
			}
		}
		return pdf;
	}

	@SuppressWarnings("unchecked")
	public List<BigDecimal> validaIdImagem() {
		this.query = this.manager.createNativeQuery("select id from sigadm.dbo.glbCatalogo where id > 10499");
		return this.query.getResultList();
	}

	public financeiro_imagem pesquisaImagemPorCodigo(int img) {
		return this.manager.find(financeiro_imagem.class, img);
	}

	public void excluir(financeiro_imagem img, String usuario, String ip) {
		
		double id = this.retornaIdCatalogoDocto(img.getId());
		
		this.query = this.manager.createNativeQuery("delete omaonline.dbo.financeiro_imagem where codigo = "+img.getCodigo());
		this.query.executeUpdate();
		
		this.query = this.manager.createNativeQuery("delete sigadm_documentos.dbo.glbDocumento where  id = "+id);
		this.query.executeUpdate();
		
		this.query = this.manager.createNativeQuery("delete sigadm.dbo.glbCatalogo_Docto where id_catalogo = "+ id);
		this.query.executeUpdate();
		
		String resumoInfos = "Código: " + img.getCodigo() + " ; Código Cond. " + img.getCdCnd() + " ; Etiqueta: "
				+ img.getId();
		intra_log_geral log = new intra_log_geral(img.getCodigo(), this.retornaIdUsuario(), "financeiro_imagem", false,
				false, true, resumoInfos, new Date(), 0, null, null);
		log.setIp(ip);
		this.logGeral(log);
		
	}
	
	@SuppressWarnings("unchecked")
	public double retornaIdCatalogoDocto(double etiqueta){
		double id = 0;
		this.query = this.manager.createNativeQuery("select id from sigadm.dbo.glbCatalogo_Docto where id_catalogo = "+etiqueta);
		List<Object[]> lista = this.query.getResultList();
		if(lista.isEmpty()){
		for (Object obj : lista) {
			id = (double) obj;
		}
		}
		return id;
	}

	public boolean verificaExclusao(double id) {
		this.query = this.manager.createNativeQuery("select * from sigadm.dbo.glbCatalogo_Lcto where id_catalogo = " + id);
		return this.query.getResultList().isEmpty();
	}

}
