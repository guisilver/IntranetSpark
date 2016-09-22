package br.com.oma.intranet.managedbeans;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;

import br.com.oma.intranet.dao.AdvertenciaMultaDAO;
import br.com.oma.intranet.dao.CadastroRecepcaoDAO;
import br.com.oma.intranet.dao.CandidatosDAO;
import br.com.oma.intranet.dao.ComercialDAO;
import br.com.oma.intranet.dao.ConcessionariaDAO;
import br.com.oma.intranet.dao.ImgServiceDAO;
import br.com.oma.omaonline.dao.FinanceiroDAO;

@RequestScoped
@ManagedBean(name = "ImgServiceBean")
public class ImgServiceBean {

	public long getDate() {
		return System.currentTimeMillis();
	}

	public DefaultStreamedContent getProtocolo() {
		AdvertenciaMultaDAO amDAO = new AdvertenciaMultaDAO();
		FacesContext context = FacesContext.getCurrentInstance();
		String id = context.getExternalContext().getRequestParameterMap().get("codigo");
		byte[] arquivo = null;
		if (id != null && !id.equals("")) {
			arquivo = amDAO.pesquisaProtocolo(Integer.valueOf(id));
			if (arquivo != null) {
				return new DefaultStreamedContent(new ByteArrayInputStream(arquivo), "application/pdf");
			} else {
				return new DefaultStreamedContent();
			}
		} else {
			return new DefaultStreamedContent();
		}
	}

	/**
	 * METODO UTILIZADO PARA PESQUISAR A FOTO DO AREACOMUM
	 * 
	 * @return
	 * @throws IOException
	 */
	public DefaultStreamedContent getFotoCandidato() throws IOException {
		DefaultStreamedContent content = null;
		try {
			CandidatosDAO dao = new CandidatosDAO();
			FacesContext context = FacesContext.getCurrentInstance();
			if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				content = this.retornaSemFoto();
			} else {
				String id = context.getExternalContext().getRequestParameterMap().get("id");
				if (id != null && !id.equals("")) {
					byte[] image = dao.pesquisaFoto(Integer.valueOf(id));
					if (image != null) {
						content = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/jpeg");
					} else {
						content = this.retornaSemFoto();
					}
				} else {
					content = this.retornaSemFoto();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public DefaultStreamedContent getFotoCondominio() throws IOException {
		DefaultStreamedContent content = null;
		try {
			ComercialDAO dao = new ComercialDAO();
			FacesContext context = FacesContext.getCurrentInstance();
			if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				content = this.retornaSemFoto();
			} else {
				String id = context.getExternalContext().getRequestParameterMap().get("id");
				if (id != null && !id.equals("")) {
					byte[] image = dao.pesquisaFoto(Integer.valueOf(id));
					if (image != null) {
						content = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/jpeg");
					} else {
						content = this.retornaSemFoto();
					}
				} else {
					content = this.retornaSemFoto();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public DefaultStreamedContent retornaSemFoto() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		File file = new File(servletContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens"
				+ File.separator + "placeholders.png");
		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			return new DefaultStreamedContent(new ByteArrayInputStream(bFile), "image/jpeg");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DefaultStreamedContent retornaSilhueta() {
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		File file = new File(servletContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens"
				+ File.separator + "silhueta.png");
		FileInputStream fileInputStream = null;
		byte[] bFile = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
			return new DefaultStreamedContent(new ByteArrayInputStream(bFile), "image/jpeg");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DefaultStreamedContent getPdfLancamento() {
		FinanceiroDAO fncDAO = new FinanceiroDAO();
		FacesContext context = FacesContext.getCurrentInstance();
		String id = context.getExternalContext().getRequestParameterMap().get("cdFinancImagem");
		byte[] arquivo = null;
		if (id != null && !id.equals("") && !id.equals("0")) {
			arquivo = fncDAO.pesquisaPDF(Integer.valueOf(id));
			if (arquivo != null) {
				return new DefaultStreamedContent(new ByteArrayInputStream(arquivo), "application/pdf");
			} else {
				return new DefaultStreamedContent();
			}
		} else {
			return new DefaultStreamedContent();
		}
	}

	public DefaultStreamedContent getPdfImgFinanceiro() {
		ConcessionariaDAO dao = new ConcessionariaDAO();
		FacesContext context = FacesContext.getCurrentInstance();
		String id = context.getExternalContext().getRequestParameterMap().get("idImg");
		byte[] arquivo = null;
		if (id != null && !id.equals("") && !id.equals("0")) {
			if (arquivo == null) {
				arquivo = dao.pesquisarPDF2(Integer.valueOf(id));
			}
			if (arquivo != null) {
				return new DefaultStreamedContent(new ByteArrayInputStream(arquivo), "application/pdf");
			} else {
				return new DefaultStreamedContent();
			}
		} else {
			return new DefaultStreamedContent();
		}
	}

	public DefaultStreamedContent getPdfLancamentoEtiqueta() {
		FinanceiroDAO fncDAO = new FinanceiroDAO();
		FacesContext context = FacesContext.getCurrentInstance();
		String id = context.getExternalContext().getRequestParameterMap().get("etiqueta");
		byte[] arquivo = null;
		if (id != null && !id.equals("") && !id.equals("0")) {
			arquivo = fncDAO.pesquisaImagemEtiqueta(Long.parseLong(id));
			if (arquivo != null) {
				return new DefaultStreamedContent(new ByteArrayInputStream(arquivo), "application/pdf");
			} else {
				return new DefaultStreamedContent();
			}
		} else {
			return new DefaultStreamedContent();
		}
	}

	public DefaultStreamedContent getPdfLancamentoEtiquetaSiga() {
		ImgServiceDAO dao = new ImgServiceDAO();
		FacesContext context = FacesContext.getCurrentInstance();
		String id = context.getExternalContext().getRequestParameterMap().get("etiqueta");
		byte[] arquivo = null;
		if (id != null && !id.equals("") && !id.equals("0")) {
			arquivo = dao.pesquisaImagemEtiquetaSiga(Long.parseLong(id));
			if (arquivo != null) {
				return new DefaultStreamedContent(new ByteArrayInputStream(arquivo), "application/pdf");
			} else {
				return new DefaultStreamedContent();
			}
		} else {
			return new DefaultStreamedContent();
		}
	}

	public DefaultStreamedContent Lancamento(int cod) {
		FinanceiroDAO fncDAO = new FinanceiroDAO();
		byte[] arquivo = null;
		if (cod != 0) {
			arquivo = fncDAO.pesquisaPDF(Integer.valueOf(cod));
			if (arquivo != null) {
				return new DefaultStreamedContent(new ByteArrayInputStream(arquivo), "application/pdf",
						"Arquivo GED - " + cod + ".pdf");
			} else {
				return new DefaultStreamedContent();
			}
		} else {
			return new DefaultStreamedContent();
		}
	}

	/**
	 * METODO UTILIZADO PARA PESQUISAR A FOTO DO AREACOMUM
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public DefaultStreamedContent getFotoCadastroPortaria() throws FileNotFoundException {
		DefaultStreamedContent content = null;
		CadastroRecepcaoDAO dao = new CadastroRecepcaoDAO();
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			content = this.retornaSemFoto();
		} else {
			String id = context.getExternalContext().getRequestParameterMap().get("id");
			if (id != null && !id.equals("")) {
				byte[] image = dao.pesquisaFoto(Integer.valueOf(id));
				if (image != null) {
					content = new DefaultStreamedContent(new ByteArrayInputStream(image), "image/jpeg");
				} else {
					content = this.retornaSemFoto();
				}
			} else {
				content = this.retornaSemFoto();
			}
		}
		return content;
	}

}
