package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class intra_candidato_contatos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(20)")
	private String telefone;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataContato;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInserido;
	@Column(columnDefinition = "varchar(500)")
	private String obs;
	@ManyToOne
	private intra_candidato candidato;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataContato() {
		return dataContato;
	}

	public void setDataContato(Date dataContato) {
		this.dataContato = dataContato;
	}

	public Date getDataInserido() {
		return dataInserido;
	}

	public void setDataInserido(Date dataInserido) {
		this.dataInserido = dataInserido;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public intra_candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(intra_candidato candidato) {
		this.candidato = candidato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidato == null) ? 0 : candidato.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((dataContato == null) ? 0 : dataContato.hashCode());
		result = prime * result + ((dataInserido == null) ? 0 : dataInserido.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		intra_candidato_contatos other = (intra_candidato_contatos) obj;
		if (candidato == null) {
			if (other.candidato != null)
				return false;
		} else if (!candidato.equals(other.candidato))
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataContato == null) {
			if (other.dataContato != null)
				return false;
		} else if (!dataContato.equals(other.dataContato))
			return false;
		if (dataInserido == null) {
			if (other.dataInserido != null)
				return false;
		} else if (!dataInserido.equals(other.dataInserido))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
