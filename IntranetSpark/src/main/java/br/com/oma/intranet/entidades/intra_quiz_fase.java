package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class intra_quiz_fase implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int fase;
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicioFase;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public Date getInicioFase() {
		return inicioFase;
	}

	public void setInicioFase(Date inicioFase) {
		this.inicioFase = inicioFase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + fase;
		result = prime * result + ((inicioFase == null) ? 0 : inicioFase.hashCode());
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
		intra_quiz_fase other = (intra_quiz_fase) obj;
		if (codigo != other.codigo)
			return false;
		if (fase != other.fase)
			return false;
		if (inicioFase == null) {
			if (other.inicioFase != null)
				return false;
		} else if (!inicioFase.equals(other.inicioFase))
			return false;
		return true;
	}
	
}
