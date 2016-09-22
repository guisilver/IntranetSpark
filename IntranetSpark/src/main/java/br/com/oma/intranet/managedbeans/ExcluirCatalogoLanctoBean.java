package br.com.oma.intranet.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.oma.intranet.dao.ExcluirCatalogoLanctoDAO;
import br.com.oma.intranet.util.Mensagens;

@ManagedBean(name="catalogoMB")
public class ExcluirCatalogoLanctoBean extends Mensagens{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5534941116478860592L;

	// DEPENDENCIA
		@ManagedProperty(value = "#{SessaoMB}")
		private SessaoMB sessaoMB;

		// OBJETO
		private ExcluirCatalogoLanctoDAO lanctoDAO;

		// ATRIBUTOS
		private long codigoEtiqueta;

		public long getCodigoEtiqueta() {
			return codigoEtiqueta;
		}

		public void setCodigoEtiqueta(long codigoEtiqueta) {
			this.codigoEtiqueta = codigoEtiqueta;
		}

		public void setSessaoMB(SessaoMB sessaoMB) {
			this.sessaoMB = sessaoMB;
		}

		// METODO
		public void excluir() {
			this.lanctoDAO = new ExcluirCatalogoLanctoDAO();
			if (this.codigoEtiqueta > 0) {
				this.lanctoDAO.excluirCatalogo(this.codigoEtiqueta, this.sessaoMB);
				this.msgExclusao();
			} else {
				this.msgCodigoEtiqueta();
			}
		}
}
