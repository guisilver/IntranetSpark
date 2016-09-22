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
public class intra_candidato_controle_encaminhamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrevista;
	@Column(columnDefinition = "varchar(500)")
	private String motivo;
	@ManyToOne
	private intra_condominios condominio;
	@ManyToOne
	private intra_candidato candidato;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getDataEntrevista() {
		return dataEntrevista;
	}

	public void setDataEntrevista(Date dataEntrevista) {
		this.dataEntrevista = dataEntrevista;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
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
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		result = prime * result + ((dataEntrevista == null) ? 0 : dataEntrevista.hashCode());
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
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
		intra_candidato_controle_encaminhamento other = (intra_candidato_controle_encaminhamento) obj;
		if (candidato == null) {
			if (other.candidato != null)
				return false;
		} else if (!candidato.equals(other.candidato))
			return false;
		if (codigo != other.codigo)
			return false;
		if (condominio == null) {
			if (other.condominio != null)
				return false;
		} else if (!condominio.equals(other.condominio))
			return false;
		if (dataEntrevista == null) {
			if (other.dataEntrevista != null)
				return false;
		} else if (!dataEntrevista.equals(other.dataEntrevista))
			return false;
		if (motivo == null) {
			if (other.motivo != null)
				return false;
		} else if (!motivo.equals(other.motivo))
			return false;
		return true;
	}
	
}
