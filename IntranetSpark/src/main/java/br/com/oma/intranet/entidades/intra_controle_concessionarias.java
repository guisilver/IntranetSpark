package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class intra_controle_concessionarias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3633428100398340493L;
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "smallint")
	private short codigoCondominio;
	@Column(columnDefinition = "varchar(255)")
	private String fornecedor;
	@Column(columnDefinition = "varchar(50)")
	private String codigoDebito;
	@Column(columnDefinition = "varchar(500)")
	private String obs;
	@Column(columnDefinition = "bit")
	private boolean debitoAutomatico;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public short getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(short codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getCodigoDebito() {
		return codigoDebito;
	}

	public void setCodigoDebito(String codigoDebito) {
		this.codigoDebito = codigoDebito;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public boolean isDebitoAutomatico() {
		return debitoAutomatico;
	}

	public void setDebitoAutomatico(boolean debitoAutomatico) {
		this.debitoAutomatico = debitoAutomatico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + codigoCondominio;
		result = prime * result + ((codigoDebito == null) ? 0 : codigoDebito.hashCode());
		result = prime * result + (debitoAutomatico ? 1231 : 1237);
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
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
		intra_controle_concessionarias other = (intra_controle_concessionarias) obj;
		if (codigo != other.codigo)
			return false;
		if (codigoCondominio != other.codigoCondominio)
			return false;
		if (codigoDebito == null) {
			if (other.codigoDebito != null)
				return false;
		} else if (!codigoDebito.equals(other.codigoDebito))
			return false;
		if (debitoAutomatico != other.debitoAutomatico)
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		return true;
	}

}
