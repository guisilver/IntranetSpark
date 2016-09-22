package br.com.oma.omaonline.entidades;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="omaonline.dbo")
public class protocolo_obs implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "smallint")
	private short cd_cnd;
	@Column(columnDefinition = "varchar(MAX)")
	private String obs;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public short getCd_cnd() {
		return cd_cnd;
	}

	public void setCd_cnd(short cd_cnd) {
		this.cd_cnd = cd_cnd;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cd_cnd;
		result = prime * result + codigo;
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
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
		protocolo_obs other = (protocolo_obs) obj;
		if (cd_cnd != other.cd_cnd)
			return false;
		if (codigo != other.codigo)
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		return true;
	}

}
