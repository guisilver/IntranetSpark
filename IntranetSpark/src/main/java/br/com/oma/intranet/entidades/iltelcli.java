package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class iltelcli implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private iltelcli_pk id;
	@Column(columnDefinition = "varchar(50)")
	private String tel_email;
	@Column(columnDefinition = "varchar(50)")
	private String observacao;
	@Column(columnDefinition = "tinyint")
	private Short boleto_envio;
	@Column(columnDefinition = "bit")
	private Boolean envio_pc;
	@Column(columnDefinition = "bit")
	private Boolean envio_carta;
	@Column(columnDefinition = "tinyint")
	private Short envio_sms;

	public iltelcli() {
	}

	public iltelcli(iltelcli_pk id, String tel_email, String observacao,
			Short boleto_envio, Boolean envio_pc, Boolean envio_carta,
			Short envio_sms) {
		this.id = id;
		this.tel_email = tel_email;
		this.observacao = observacao;
		this.boleto_envio = boleto_envio;
		this.envio_pc = envio_pc;
		this.envio_carta = envio_carta;
		this.envio_sms = envio_sms;
	}

	public iltelcli_pk getId() {
		return id;
	}

	public void setId(iltelcli_pk id) {
		this.id = id;
	}

	public String getTel_email() {
		return tel_email;
	}

	public void setTel_email(String tel_email) {
		this.tel_email = tel_email;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Short getBoleto_envio() {
		return boleto_envio;
	}

	public void setBoleto_envio(Short boleto_envio) {
		this.boleto_envio = boleto_envio;
	}

	public Boolean getEnvio_pc() {
		return envio_pc;
	}

	public void setEnvio_pc(Boolean envio_pc) {
		this.envio_pc = envio_pc;
	}

	public Boolean getEnvio_carta() {
		return envio_carta;
	}

	public void setEnvio_carta(Boolean envio_carta) {
		this.envio_carta = envio_carta;
	}

	public Short getEnvio_sms() {
		return envio_sms;
	}

	public void setEnvio_sms(Short envio_sms) {
		this.envio_sms = envio_sms;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((boleto_envio == null) ? 0 : boleto_envio.hashCode());
		result = prime * result
				+ ((envio_carta == null) ? 0 : envio_carta.hashCode());
		result = prime * result
				+ ((envio_pc == null) ? 0 : envio_pc.hashCode());
		result = prime * result
				+ ((envio_sms == null) ? 0 : envio_sms.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((tel_email == null) ? 0 : tel_email.hashCode());
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
		iltelcli other = (iltelcli) obj;
		if (boleto_envio == null) {
			if (other.boleto_envio != null)
				return false;
		} else if (!boleto_envio.equals(other.boleto_envio))
			return false;
		if (envio_carta == null) {
			if (other.envio_carta != null)
				return false;
		} else if (!envio_carta.equals(other.envio_carta))
			return false;
		if (envio_pc == null) {
			if (other.envio_pc != null)
				return false;
		} else if (!envio_pc.equals(other.envio_pc))
			return false;
		if (envio_sms == null) {
			if (other.envio_sms != null)
				return false;
		} else if (!envio_sms.equals(other.envio_sms))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (tel_email == null) {
			if (other.tel_email != null)
				return false;
		} else if (!tel_email.equals(other.tel_email))
			return false;
		return true;
	}

}
