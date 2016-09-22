package br.com.oma.omaonline.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="cndpagar_aprovacao", schema="omaonline.dbo")
public class cndpagar_aprovacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int permissao;
	@Column(columnDefinition = "varchar(50)")
	private String aprovador;
	@Column(columnDefinition = "varchar(1)")
	private String status_;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getPermissao() {
		return permissao;
	}

	public void setPermissao(int permissao) {
		this.permissao = permissao;
	}

	public String getAprovador() {
		return aprovador;
	}

	public void setAprovador(String aprovador) {
		this.aprovador = aprovador;
	}

	public String getStatus_() {
		return status_;
	}

	public void setStatus_(String status_) {
		this.status_ = status_;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aprovador == null) ? 0 : aprovador.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + permissao;
		result = prime * result + ((status_ == null) ? 0 : status_.hashCode());
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
		cndpagar_aprovacao other = (cndpagar_aprovacao) obj;
		if (aprovador == null) {
			if (other.aprovador != null)
				return false;
		} else if (!aprovador.equals(other.aprovador))
			return false;
		if (codigo != other.codigo)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (permissao != other.permissao)
			return false;
		if (status_ == null) {
			if (other.status_ != null)
				return false;
		} else if (!status_.equals(other.status_))
			return false;
		return true;
	}
	
	

}
