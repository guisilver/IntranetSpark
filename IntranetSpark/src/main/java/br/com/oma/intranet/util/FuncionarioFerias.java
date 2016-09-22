package br.com.oma.intranet.util;

import java.io.Serializable;
import java.util.Date;

public class FuncionarioFerias implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date inicio_gozo;
	private Date final_gozo;

	public Date getInicio_gozo() {
		return inicio_gozo;
	}

	public void setInicio_gozo(Date inicio_gozo) {
		this.inicio_gozo = inicio_gozo;
	}

	public Date getFinal_gozo() {
		return final_gozo;
	}

	public void setFinal_gozo(Date final_gozo) {
		this.final_gozo = final_gozo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((final_gozo == null) ? 0 : final_gozo.hashCode());
		result = prime * result + ((inicio_gozo == null) ? 0 : inicio_gozo.hashCode());
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
		FuncionarioFerias other = (FuncionarioFerias) obj;
		if (final_gozo == null) {
			if (other.final_gozo != null)
				return false;
		} else if (!final_gozo.equals(other.final_gozo))
			return false;
		if (inicio_gozo == null) {
			if (other.inicio_gozo != null)
				return false;
		} else if (!inicio_gozo.equals(other.inicio_gozo))
			return false;
		return true;
	}

}
