package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class intra_quiz_controle_respostas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "varchar(100)")
	private String emailSindico;
	@Column(columnDefinition = "int")
	private int condominio;
	@Column(columnDefinition = "varchar(100)")
	private String nomeSindico;
	@Column(columnDefinition = "bit")
	private boolean respondeu;

	public String getEmailSindico() {
		return emailSindico;
	}

	public void setEmailSindico(String emailSindico) {
		this.emailSindico = emailSindico;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public String getNomeSindico() {
		return nomeSindico;
	}

	public void setNomeSindico(String nomeSindico) {
		this.nomeSindico = nomeSindico;
	}

	public boolean isRespondeu() {
		return respondeu;
	}

	public void setRespondeu(boolean respondeu) {
		this.respondeu = respondeu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + condominio;
		result = prime * result + ((emailSindico == null) ? 0 : emailSindico.hashCode());
		result = prime * result + ((nomeSindico == null) ? 0 : nomeSindico.hashCode());
		result = prime * result + (respondeu ? 1231 : 1237);
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
		intra_quiz_controle_respostas other = (intra_quiz_controle_respostas) obj;
		if (condominio != other.condominio)
			return false;
		if (emailSindico == null) {
			if (other.emailSindico != null)
				return false;
		} else if (!emailSindico.equals(other.emailSindico))
			return false;
		if (nomeSindico == null) {
			if (other.nomeSindico != null)
				return false;
		} else if (!nomeSindico.equals(other.nomeSindico))
			return false;
		if (respondeu != other.respondeu)
			return false;
		return true;
	}

}
