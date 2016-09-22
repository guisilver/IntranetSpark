package br.com.oma.omaonline.entidades;

import java.io.Serializable;

public class atbancos implements Serializable {

	private static final long serialVersionUID = 1L;

	private short codBanco;
	
	private String nomeDoBanco;

	public short getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(short codBanco) {
		this.codBanco = codBanco;
	}

	public String getNomeDoBanco() {
		return nomeDoBanco;
	}

	public void setNomeDoBanco(String nomeDoBanco) {
		this.nomeDoBanco = nomeDoBanco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codBanco;
		result = prime * result + ((nomeDoBanco == null) ? 0 : nomeDoBanco.hashCode());
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
		atbancos other = (atbancos) obj;
		if (codBanco != other.codBanco)
			return false;
		if (nomeDoBanco == null) {
			if (other.nomeDoBanco != null)
				return false;
		} else if (!nomeDoBanco.equals(other.nomeDoBanco))
			return false;
		return true;
	}
	
}
