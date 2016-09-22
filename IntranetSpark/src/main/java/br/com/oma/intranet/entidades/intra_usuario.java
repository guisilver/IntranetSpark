package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

@Entity
public class intra_usuario implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1043160143404840130L;

	@Id
	@Column(columnDefinition="varchar(50)")
	private String email;

	@Column(columnDefinition="varchar(50)")
	private String nome;
	
	@Column(columnDefinition="varchar(50)")
	private String senha;
	
	@Column(columnDefinition="varchar(10)")
	private String perfil;
	
	@Column(columnDefinition="int")
	private int gerente;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<intra_grupo_depto> grupoDepto = new ArrayList<intra_grupo_depto>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	@OrderBy("nome ASC")
	private List<intra_grupo_gerente> grupoGer = new ArrayList<intra_grupo_gerente>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<intra_grupo_permissao> grupoPer = new ArrayList<intra_grupo_permissao>();

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<intra_grupo_depto> getGrupoDepto() {
		return grupoDepto;
	}

	public void setGrupoDepto(List<intra_grupo_depto> grupoDepto) {
		this.grupoDepto = grupoDepto;
	}

	public List<intra_grupo_gerente> getGrupoGer() {
		return grupoGer;
	}

	public void setGrupoGer(List<intra_grupo_gerente> grupoGer) {
		this.grupoGer = grupoGer;
	}

	public List<intra_grupo_permissao> getGrupoPer() {
		return grupoPer;
	}

	public void setGrupoPer(List<intra_grupo_permissao> grupoPer) {
		this.grupoPer = grupoPer;
	}

	public int getGerente() {
		return gerente;
	}

	public void setGerente(int gerente) {
		this.gerente = gerente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + gerente;
		result = prime * result + ((grupoDepto == null) ? 0 : grupoDepto.hashCode());
		result = prime * result + ((grupoGer == null) ? 0 : grupoGer.hashCode());
		result = prime * result + ((grupoPer == null) ? 0 : grupoPer.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		intra_usuario other = (intra_usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gerente != other.gerente)
			return false;
		if (grupoDepto == null) {
			if (other.grupoDepto != null)
				return false;
		} else if (!grupoDepto.equals(other.grupoDepto))
			return false;
		if (grupoGer == null) {
			if (other.grupoGer != null)
				return false;
		} else if (!grupoGer.equals(other.grupoGer))
			return false;
		if (grupoPer == null) {
			if (other.grupoPer != null)
				return false;
		} else if (!grupoPer.equals(other.grupoPer))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	
}
