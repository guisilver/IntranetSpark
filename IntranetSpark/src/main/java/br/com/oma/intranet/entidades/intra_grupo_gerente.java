package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class intra_grupo_gerente implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7784087840654652364L;

	@Id
	private int codigo;

	@Column(columnDefinition="varchar(50)")
	private String email;

	@Column(columnDefinition="varchar(50)")
	private String nome;
	
	@Column()
	private boolean baixar;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isBaixar() {
		return baixar;
	}

	public void setBaixar(boolean baixar) {
		this.baixar = baixar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (baixar ? 1231 : 1237);
		result = prime * result + codigo;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		intra_grupo_gerente other = (intra_grupo_gerente) obj;
		if (baixar != other.baixar)
			return false;
		if (codigo != other.codigo)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
