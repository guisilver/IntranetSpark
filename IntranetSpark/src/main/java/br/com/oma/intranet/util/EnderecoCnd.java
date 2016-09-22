package br.com.oma.intranet.util;

import java.io.Serializable;

public class EnderecoCnd implements Serializable{

	private static final long serialVersionUID = 1L;

	private int cndCep;
	private long cndCnpj;
	private String cndCidade;
	private String cndEstado;

	public int getCndCep() {
		return cndCep;
	}

	public void setCndCep(int cndCep) {
		this.cndCep = cndCep;
	}

	public long getCndCnpj() {
		return cndCnpj;
	}

	public void setCndCnpj(long cndCnpj) {
		this.cndCnpj = cndCnpj;
	}

	public String getCndCidade() {
		return cndCidade;
	}

	public void setCndCidade(String cndCidade) {
		this.cndCidade = cndCidade;
	}

	public String getCndEstado() {
		return cndEstado;
	}

	public void setCndEstado(String cndEstado) {
		this.cndEstado = cndEstado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cndCep;
		result = prime * result + ((cndCidade == null) ? 0 : cndCidade.hashCode());
		result = prime * result + (int) (cndCnpj ^ (cndCnpj >>> 32));
		result = prime * result + ((cndEstado == null) ? 0 : cndEstado.hashCode());
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
		EnderecoCnd other = (EnderecoCnd) obj;
		if (cndCep != other.cndCep)
			return false;
		if (cndCidade == null) {
			if (other.cndCidade != null)
				return false;
		} else if (!cndCidade.equals(other.cndCidade))
			return false;
		if (cndCnpj != other.cndCnpj)
			return false;
		if (cndEstado == null) {
			if (other.cndEstado != null)
				return false;
		} else if (!cndEstado.equals(other.cndEstado))
			return false;
		return true;
	}

	

}
