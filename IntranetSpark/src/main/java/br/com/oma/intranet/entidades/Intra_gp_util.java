package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Transient;

public class Intra_gp_util implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Transient
	private String sistemaPai;
	
	@Transient
	private String subCategoria;

	@Transient
	private boolean inserir;
	
	@Transient
	private boolean	alterar; 
	
	@Transient
	private boolean consultar; 
	
	@Transient
	private boolean excluir;

	
	public boolean isInserir() {
		return inserir;
	}

	public void setInserir(boolean inserir) {
		this.inserir = inserir;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public boolean isConsultar() {
		return consultar;
	}

	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}

	public boolean isExcluir() {
		return excluir;
	}

	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}

	public String getSistemaPai() {
		return sistemaPai;
	}

	public void setSistemaPai(String sistemaPai) {
		this.sistemaPai = sistemaPai;
	}
	

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alterar ? 1231 : 1237);
		result = prime * result + (consultar ? 1231 : 1237);
		result = prime * result + (excluir ? 1231 : 1237);
		result = prime * result + (inserir ? 1231 : 1237);
		result = prime * result
				+ ((sistemaPai == null) ? 0 : sistemaPai.hashCode());
		result = prime * result
				+ ((subCategoria == null) ? 0 : subCategoria.hashCode());
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
		Intra_gp_util other = (Intra_gp_util) obj;
		if (alterar != other.alterar)
			return false;
		if (consultar != other.consultar)
			return false;
		if (excluir != other.excluir)
			return false;
		if (inserir != other.inserir)
			return false;
		if (sistemaPai == null) {
			if (other.sistemaPai != null)
				return false;
		} else if (!sistemaPai.equals(other.sistemaPai))
			return false;
		if (subCategoria == null) {
			if (other.subCategoria != null)
				return false;
		} else if (!subCategoria.equals(other.subCategoria))
			return false;
		return true;
	}
	
	
}
