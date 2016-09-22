package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Id;

public class glbDocumento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="numeric(20,0)")
	private BigDecimal id;
	
	private byte[] obj;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public byte[] getObj() {
		return obj;
	}

	public void setObj(byte[] obj) {
		this.obj = obj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(obj);
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
		glbDocumento other = (glbDocumento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(this.obj, other.obj))
			return false;
		return true;
	}
	
	
}
