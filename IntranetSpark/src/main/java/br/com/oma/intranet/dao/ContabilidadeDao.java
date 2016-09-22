package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.financeiro_imagem;

public class ContabilidadeDao extends LogGeralDAO {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public ContabilidadeDao() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public boolean salvar(int cdFinancImagem, SessaoMB sessaoMB) {
		boolean recebido = false;
		Query query1 = this.manager.createNativeQuery(
				"select cd_cnd, recebido_contabilidade, data_rec_contab from omaonline.dbo.financeiro_imagem where id = "
						+ cdFinancImagem);
		List<Object[]> lista = query1.getResultList();
		if (lista != null) {
			for (Object[] obj : lista) {
				financeiro_imagem fi = new financeiro_imagem();
				if (obj[0] != null) {
					fi.setCdCnd(Short.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					fi.setRecebidoContabilidade(obj[1].toString());
				}
				if (obj[2] != null) {
					fi.setDataRecebidoContabilidade((Date) obj[2]);
				}
				if (fi.getDataRecebidoContabilidade() == null) {
					Query query2 = this.manager.createNativeQuery(
							"update omaonline.dbo.financeiro_imagem set recebido_contabilidade = 'S', data_rec_contab = getdate() where id = "
									+ cdFinancImagem);
					query2.executeUpdate();
					intra_log_geral lg = new intra_log_geral(fi.getCdCnd(), sessaoMB.getUsuario().getEmail(),
							"omaonline.dbo.financeiro_imagem", false, true, false,
							"Update financeiro imagem data recebimento etiqueta: " + cdFinancImagem, new Date(), 0, "",
							null);
					this.logGeral(lg);
				} else {
					recebido = true;
				}
			}
		}
		return recebido;
	}

}
