package br.com.oma.pc.control.managedbeans;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;

import br.com.oma.pc.model.dao.PlanoContasDAO;

@RequestScoped
@ManagedBean
public class DoctoServiceMB {

	public long getDate() {
		return System.currentTimeMillis();
	}

	public DefaultStreamedContent getDoctoLancto()
			throws FileNotFoundException, ClassNotFoundException, IOException,
			SQLException {
		FacesContext context = FacesContext.getCurrentInstance();
		String id = context.getExternalContext().getRequestParameterMap()
				.get("idDocto");
		if (id != null && !id.equals("")) {
			PlanoContasDAO dao = new PlanoContasDAO();
			byte[] arquivo = dao.pesquisaDocto(id);
			if (arquivo != null) {
				return new DefaultStreamedContent(new ByteArrayInputStream(
						arquivo), "application/pdf");
			} else {
				return new DefaultStreamedContent();
			}
		}
		return new DefaultStreamedContent();
	}
}
