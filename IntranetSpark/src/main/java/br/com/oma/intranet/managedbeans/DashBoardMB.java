package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oma.intranet.dao.NoticiasMktDAO;
import br.com.oma.intranet.entidades.intra_noticias_mkt;

@ViewScoped
@ManagedBean
public class DashBoardMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5777708282618994195L;
	private intra_noticias_mkt noticiaMkt = new intra_noticias_mkt();
	private String enderecoPastaNoticiaMkt;

	public DashBoardMB() {
		this.enderecoPastaNoticiaMkt = "http://10.1.1.20:8080/arquivos/intranet/noticias";
	}

	public intra_noticias_mkt getNoticiaMkt() {
		NoticiasMktDAO dao = new NoticiasMktDAO();
		List<intra_noticias_mkt> listaNoticias = dao.getListaNoticias();
		if (listaNoticias != null && listaNoticias.size() > 0) {
			this.noticiaMkt = listaNoticias.get(0);
		}
		return noticiaMkt;
	}

	public void setNoticiaMkt(intra_noticias_mkt noticiaMkt) {
		this.noticiaMkt = noticiaMkt;
	}

	public String getEnderecoPastaNoticiaMkt() {
		return enderecoPastaNoticiaMkt;
	}

	public void setEnderecoPastaNoticiaMkt(String enderecoPastaNoticiaMkt) {
		this.enderecoPastaNoticiaMkt = enderecoPastaNoticiaMkt;
	}

}
