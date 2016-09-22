package br.com.oma.intranet.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;

public class ExcluirCatalogoLanctoDAO extends LogGeralDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5287978099200806870L;
	private EntityManager manager;
	private Query query;
	
	public ExcluirCatalogoLanctoDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void excluirCatalogo(long codigoEtiqueta, SessaoMB sessaoBean) {
		short condo = 0;
		this.createLog(sessaoBean.getIpUser(), condo ,  sessaoBean.getUsuario().getEmail(), "glbCatalogo_Lcto", false, false, true, "Exclusão do vinculo catalogo no siga, numero da etiqueta:"+codigoEtiqueta);
		
		this.query = this.manager.createNativeQuery("delete sigadm.dbo.glbCatalogo_Lcto where id_catalogo = :p1");
		this.query.setParameter("p1", codigoEtiqueta);
		this.query.executeUpdate();
		this.manager.flush();
	}
	
	public void createLog(String ip, short condominio, String feitoPor,
			String nomeTabela, boolean insert, boolean update, boolean delet, String infoAnterior) {
		
		this.query = this.manager.createNativeQuery("insert into omaonline.dbo.log_geral (ip, condominio, feito_por, tabela, insert_, update_, del_, info_anterior, nro_lancto)values('"+ip+"',"
		+condominio+",'"+feitoPor+"','"+nomeTabela+"',0,0,1,'"+infoAnterior+"',0)");
		this.query.executeUpdate();
		
		/*log_geral lg = new log_geral();
		lg.setCondominio(condominio);
		lg.setFeitoPor(feitoPor);
		lg.setTabela(nomeTabela);
		lg.setInsert_(insert);
		lg.setUpdate_(update);
		lg.setDel_(delet);
		lg.setInfoAnterior(infoAnterior);
		lg.setData_(new Date());
		lg.setIp(ip);
		this.manager.persist(lg);*/
	}
	
}
