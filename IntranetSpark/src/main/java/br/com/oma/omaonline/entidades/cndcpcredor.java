package br.com.oma.omaonline.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class cndcpcredor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="usualcred", columnDefinition="varchar(12)")
	private String usualcred;
	
	@Column(name="nome", columnDefinition="varchar(35)")
	private String nome;
	
	@Column(name="tipo_inscricao", columnDefinition="char(1)")
	private String tipoInscricao;
	
    @Column(name="inscricao", columnDefinition="numeric(14,0)")
	private long  inscricao;

	public String getUsualcred() {
		return usualcred;
	}

	public void setUsualcred(String usualcred) {
		this.usualcred = usualcred;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoInscricao() {
		return tipoInscricao;
	}

	public void setTipoInscricao(String tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}

	public long getInscricao() {
		return inscricao;
	}

	public void setInscricao(long inscricao) {
		this.inscricao = inscricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (inscricao ^ (inscricao >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipoInscricao == null) ? 0 : tipoInscricao.hashCode());
		result = prime * result + ((usualcred == null) ? 0 : usualcred.hashCode());
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
		cndcpcredor other = (cndcpcredor) obj;
		if (inscricao != other.inscricao)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipoInscricao == null) {
			if (other.tipoInscricao != null)
				return false;
		} else if (!tipoInscricao.equals(other.tipoInscricao))
			return false;
		if (usualcred == null) {
			if (other.usualcred != null)
				return false;
		} else if (!usualcred.equals(other.usualcred))
			return false;
		return true;
	} 
       
}
