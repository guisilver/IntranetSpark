package br.com.oma.intranet.entidades;

import java.io.Serializable;

public class ResponsavelVaga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4591014941695828930L;
	private String nome;
	private int qtd;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + qtd;
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
		ResponsavelVaga other = (ResponsavelVaga) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (qtd != other.qtd)
			return false;
		return true;
	}

}
