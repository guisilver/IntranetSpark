package br.com.oma.pc.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Capa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String nome;
	private List<Conta> contas = new ArrayList<>();
	private List<Grafico> listaGraficos = new ArrayList<>();
	private List<ElementoGrafico> listaElementoGrafico = new ArrayList<>();

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Grafico> getListaGraficos() {
		return listaGraficos;
	}

	public void setListaGraficos(List<Grafico> listaGraficos) {
		this.listaGraficos = listaGraficos;
	}

	public List<ElementoGrafico> getListaElementoGrafico() {
		return listaElementoGrafico;
	}

	public void setListaElementoGrafico(List<ElementoGrafico> listaElementoGrafico) {
		this.listaElementoGrafico = listaElementoGrafico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((contas == null) ? 0 : contas.hashCode());
		result = prime * result + ((listaElementoGrafico == null) ? 0 : listaElementoGrafico.hashCode());
		result = prime * result + ((listaGraficos == null) ? 0 : listaGraficos.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Capa other = (Capa) obj;
		if (codigo != other.codigo)
			return false;
		if (contas == null) {
			if (other.contas != null)
				return false;
		} else if (!contas.equals(other.contas))
			return false;
		if (listaElementoGrafico == null) {
			if (other.listaElementoGrafico != null)
				return false;
		} else if (!listaElementoGrafico.equals(other.listaElementoGrafico))
			return false;
		if (listaGraficos == null) {
			if (other.listaGraficos != null)
				return false;
		} else if (!listaGraficos.equals(other.listaGraficos))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
