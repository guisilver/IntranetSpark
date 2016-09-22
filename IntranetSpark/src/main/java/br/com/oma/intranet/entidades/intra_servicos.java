package br.com.oma.intranet.entidades;

import java.io.Serializable;

public class intra_servicos implements Serializable{

	private static final long serialVersionUID = 1L;

	private int condominio;
	private int cotaCondominio;
	private int cotaExtra;
	
	public int getCondominio() {
		return condominio;
	}
	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}
	public int getCotaCondominio() {
		return cotaCondominio;
	}
	public void setCotaCondominio(int cotaCondominio) {
		this.cotaCondominio = cotaCondominio;
	}
	public int getCotaExtra() {
		return cotaExtra;
	}
	public void setCotaExtra(int cotaExtra) {
		this.cotaExtra = cotaExtra;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + condominio;
		result = prime * result + cotaCondominio;
		result = prime * result + cotaExtra;
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
		intra_servicos other = (intra_servicos) obj;
		if (condominio != other.condominio)
			return false;
		if (cotaCondominio != other.cotaCondominio)
			return false;
		if (cotaExtra != other.cotaExtra)
			return false;
		return true;
	}
	
	
}
