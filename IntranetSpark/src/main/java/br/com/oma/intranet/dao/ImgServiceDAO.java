package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.util.JPAUtil;

public class ImgServiceDAO {

	private EntityManager manager;

	public ImgServiceDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaImagemEtiquetaSiga(long etiqueta) {
		Query query = this.manager.createNativeQuery(
				"select x.obj from sigadm.dbo.glbCatalogo_Docto d inner join sigadm.dbo.glbCatalogo_Lcto l on"
						+ " d.id_catalogo = l.id_catalogo inner join SIGADM_DOCUMENTOS.dbo.glbDocumento x on x.id = d.id where l.id_catalogo = :etiqueta");
		query.setParameter("etiqueta", etiqueta);
		List<byte[]> l = (List<byte[]>) query.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}
}
