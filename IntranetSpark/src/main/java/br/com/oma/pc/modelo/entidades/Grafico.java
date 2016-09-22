package br.com.oma.pc.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Grafico implements Serializable{

	private static final long serialVersionUID = 1L;

	private Capa capa;
	private String nomeConta;
	private List<ElementoGrafico> elementos = new ArrayList<ElementoGrafico>();
	private JRBeanCollectionDataSource jrds;

	public Capa getCapa() {
		return capa;
	}

	public void setCapa(Capa capa) {
		this.capa = capa;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public List<ElementoGrafico> getElementos() {
		return elementos;
	}

	public void setElementos(List<ElementoGrafico> elementos) {
		this.elementos = elementos;
	}

	public JRBeanCollectionDataSource getJrds() {
		return jrds;
	}

	public void setJrds(JRBeanCollectionDataSource jrds) {
		this.jrds = jrds;
	}
	

}
