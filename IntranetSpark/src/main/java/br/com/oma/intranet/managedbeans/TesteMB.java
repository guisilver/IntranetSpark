package br.com.oma.intranet.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oma.intranet.dao.LancamentoDAO;
import br.com.oma.omaonline.entidades.financeiro_imagem;

@ViewScoped
@ManagedBean
public class TesteMB {

	private String corTeste = "red";

	public String getCorTeste() {
		return corTeste;
	}

	public void setCorTeste(String corTeste) {
		this.corTeste = corTeste;
	}

	public void teste() {
		LancamentoDAO dao = new LancamentoDAO();
		financeiro_imagem fi = dao.pesquisarImagemPorEtiqueta(11481);
		System.out.println(fi.getCodigo());
	}
	
}
