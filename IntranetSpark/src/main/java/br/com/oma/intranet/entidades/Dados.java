package br.com.oma.intranet.entidades;

import java.io.Serializable;

public class Dados implements Serializable {

	private static final long serialVersionUID = 1L;

	private int Condominio;
	private String Bloco;
	private String Unidade;
	private String Nome;
	private String email;

	public String toString() {
		return Condominio + " " + Bloco + " " + Unidade + " " + Nome + " " + email;
	}

	public String getBloco() {
		return Bloco;
	}

	public void setBloco(String bloco) {
		this.Bloco = bloco;
	}

	public int getCondominio() {
		return Condominio;
	}

	public void setCondominio(int condominio) {
		this.Condominio = condominio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getUnidade() {
		return Unidade;
	}

	public void setUnidade(String unidade) {
		this.Unidade = unidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Bloco == null) ? 0 : Bloco.hashCode());
		result = prime * result + Condominio;
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		result = prime * result + ((Unidade == null) ? 0 : Unidade.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Dados other = (Dados) obj;
		if (Bloco == null) {
			if (other.Bloco != null)
				return false;
		} else if (!Bloco.equals(other.Bloco))
			return false;
		if (Condominio != other.Condominio)
			return false;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		if (Unidade == null) {
			if (other.Unidade != null)
				return false;
		} else if (!Unidade.equals(other.Unidade))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
}
