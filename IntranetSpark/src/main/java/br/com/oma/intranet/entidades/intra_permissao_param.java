package br.com.oma.intranet.entidades;

import java.io.Serializable;

public class intra_permissao_param implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int nivelAdm;
	private int nivelIAC;
	private int nivelIC;
	private int nivelAC;
	private int nivelC;
	private int nivelE;
	
	public int getNivelAdm() {
		return nivelAdm;
	}
	public void setNivelAdm(int nivelAdm) {
		this.nivelAdm = nivelAdm;
	}
	public int getNivelIAC() {
		return nivelIAC;
	}
	public void setNivelIAC(int nivelIAC) {
		this.nivelIAC = nivelIAC;
	}
	public int getNivelIC() {
		return nivelIC;
	}
	public void setNivelIC(int nivelIC) {
		this.nivelIC = nivelIC;
	}
	public int getNivelAC() {
		return nivelAC;
	}
	public void setNivelAC(int nivelAC) {
		this.nivelAC = nivelAC;
	}
	public int getNivelC() {
		return nivelC;
	}
	public void setNivelC(int nivelC) {
		this.nivelC = nivelC;
	}
	public int getNivelE() {
		return nivelE;
	}
	public void setNivelE(int nivelE) {
		this.nivelE = nivelE;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nivelAC;
		result = prime * result + nivelAdm;
		result = prime * result + nivelC;
		result = prime * result + nivelE;
		result = prime * result + nivelIAC;
		result = prime * result + nivelIC;
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
		intra_permissao_param other = (intra_permissao_param) obj;
		if (nivelAC != other.nivelAC)
			return false;
		if (nivelAdm != other.nivelAdm)
			return false;
		if (nivelC != other.nivelC)
			return false;
		if (nivelE != other.nivelE)
			return false;
		if (nivelIAC != other.nivelIAC)
			return false;
		if (nivelIC != other.nivelIC)
			return false;
		return true;
	}
	
	
}
