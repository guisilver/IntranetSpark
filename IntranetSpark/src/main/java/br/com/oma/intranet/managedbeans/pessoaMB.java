package br.com.oma.intranet.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.oma.intranet.entidades.PessoaBean;

@ManagedBean(name="pessoa")
public class pessoaMB {

	private List<PessoaBean> lista, filtro;
	private PessoaBean p = new PessoaBean();
	
	
	

	/**
	 * @return the p
	 */
	public PessoaBean getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(PessoaBean p) {
		this.p = p;
	}

	public pessoaMB() {
		if(lista == null){
			lista = new ArrayList<PessoaBean>();
			for (int i = 0; i < 30; i++) {
				PessoaBean c = new PessoaBean();
				c.setNome("Nome  Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome Nome " + i);
				c.setAno("Ano "+ i);
				c.setCor("Cor "+i);
				c.setIdadde("Idade "+i);
				c.setId(i);
				lista.add(c);
			}
		}
	}

	/**
	 * @return the lista
	 */
	public List<PessoaBean> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<PessoaBean> lista) {
		this.lista = lista;
	}

	/**
	 * @return the filtro
	 */
	public List<PessoaBean> getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<PessoaBean> filtro) {
		this.filtro = filtro;
	}
	
	
}
