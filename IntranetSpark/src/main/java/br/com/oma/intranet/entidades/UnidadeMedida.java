package br.com.oma.intranet.entidades;

import java.io.Serializable;

public class UnidadeMedida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1401873838203310228L;
	private String obs;
	private String unidade_medida;

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getUnidade_medida() {
		return unidade_medida;
	}

	public void setUnidade_medida(String unidade_medida) {
		this.unidade_medida = unidade_medida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((unidade_medida == null) ? 0 : unidade_medida.hashCode());
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
		UnidadeMedida other = (UnidadeMedida) obj;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (unidade_medida == null) {
			if (other.unidade_medida != null)
				return false;
		} else if (!unidade_medida.equals(other.unidade_medida))
			return false;
		return true;
	}

}
