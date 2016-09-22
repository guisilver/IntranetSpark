package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_escala_param implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2137979493993401542L;
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int codigoCondominio;
	@Column(columnDefinition = "varchar(MAX)")
	private String obs1;
	@Column(columnDefinition = "varchar(MAX)")
	private String obs2;
	@Column(columnDefinition = "varchar(MAX)")
	private String obs3;
	@Column(columnDefinition = "varchar(MAX)")
	private String obs4;
	@Column(columnDefinition = "varchar(MAX)")
	private String obs5;
	@Column(columnDefinition = "varchar(MAX)")
	private String obs6;
	@Column(columnDefinition = "varchar(50)")
	private String manha;
	@Column(columnDefinition = "varchar(50)")
	private String tarde;
	@Column(columnDefinition = "varchar(50)")
	private String noite;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public String getObs1() {
		return obs1;
	}

	public void setObs1(String obs1) {
		this.obs1 = obs1;
	}

	public String getObs2() {
		return obs2;
	}

	public void setObs2(String obs2) {
		this.obs2 = obs2;
	}

	public String getObs3() {
		return obs3;
	}

	public void setObs3(String obs3) {
		this.obs3 = obs3;
	}

	public String getObs4() {
		return obs4;
	}

	public void setObs4(String obs4) {
		this.obs4 = obs4;
	}

	public String getObs5() {
		return obs5;
	}

	public void setObs5(String obs5) {
		this.obs5 = obs5;
	}

	public String getObs6() {
		return obs6;
	}

	public void setObs6(String obs6) {
		this.obs6 = obs6;
	}

	public String getManha() {
		return manha;
	}

	public void setManha(String manha) {
		this.manha = manha;
	}

	public String getTarde() {
		return tarde;
	}

	public void setTarde(String tarde) {
		this.tarde = tarde;
	}

	public String getNoite() {
		return noite;
	}

	public void setNoite(String noite) {
		this.noite = noite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + codigoCondominio;
		result = prime * result + ((manha == null) ? 0 : manha.hashCode());
		result = prime * result + ((noite == null) ? 0 : noite.hashCode());
		result = prime * result + ((obs1 == null) ? 0 : obs1.hashCode());
		result = prime * result + ((obs2 == null) ? 0 : obs2.hashCode());
		result = prime * result + ((obs3 == null) ? 0 : obs3.hashCode());
		result = prime * result + ((obs4 == null) ? 0 : obs4.hashCode());
		result = prime * result + ((obs5 == null) ? 0 : obs5.hashCode());
		result = prime * result + ((obs6 == null) ? 0 : obs6.hashCode());
		result = prime * result + ((tarde == null) ? 0 : tarde.hashCode());
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
		intra_escala_param other = (intra_escala_param) obj;
		if (codigo != other.codigo)
			return false;
		if (codigoCondominio != other.codigoCondominio)
			return false;
		if (manha == null) {
			if (other.manha != null)
				return false;
		} else if (!manha.equals(other.manha))
			return false;
		if (noite == null) {
			if (other.noite != null)
				return false;
		} else if (!noite.equals(other.noite))
			return false;
		if (obs1 == null) {
			if (other.obs1 != null)
				return false;
		} else if (!obs1.equals(other.obs1))
			return false;
		if (obs2 == null) {
			if (other.obs2 != null)
				return false;
		} else if (!obs2.equals(other.obs2))
			return false;
		if (obs3 == null) {
			if (other.obs3 != null)
				return false;
		} else if (!obs3.equals(other.obs3))
			return false;
		if (obs4 == null) {
			if (other.obs4 != null)
				return false;
		} else if (!obs4.equals(other.obs4))
			return false;
		if (obs5 == null) {
			if (other.obs5 != null)
				return false;
		} else if (!obs5.equals(other.obs5))
			return false;
		if (obs6 == null) {
			if (other.obs6 != null)
				return false;
		} else if (!obs6.equals(other.obs6))
			return false;
		if (tarde == null) {
			if (other.tarde != null)
				return false;
		} else if (!tarde.equals(other.tarde))
			return false;
		return true;
	}
}
