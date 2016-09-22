package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class intra_quiz_controle_condominios implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int condominio;
	@Column(columnDefinition = "bit")
	private boolean questionarioEnviado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnviado;

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public boolean isQuestionarioEnviado() {
		return questionarioEnviado;
	}

	public void setQuestionarioEnviado(boolean questionarioEnviado) {
		this.questionarioEnviado = questionarioEnviado;
	}

	public Date getDataEnviado() {
		return dataEnviado;
	}

	public void setDataEnviado(Date dataEnviado) {
		this.dataEnviado = dataEnviado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + condominio;
		result = prime * result + ((dataEnviado == null) ? 0 : dataEnviado.hashCode());
		result = prime * result + (questionarioEnviado ? 1231 : 1237);
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
		intra_quiz_controle_condominios other = (intra_quiz_controle_condominios) obj;
		if (condominio != other.condominio)
			return false;
		if (dataEnviado == null) {
			if (other.dataEnviado != null)
				return false;
		} else if (!dataEnviado.equals(other.dataEnviado))
			return false;
		if (questionarioEnviado != other.questionarioEnviado)
			return false;
		return true;
	}
	

}
