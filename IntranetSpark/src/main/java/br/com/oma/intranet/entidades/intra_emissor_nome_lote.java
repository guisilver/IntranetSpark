package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class intra_emissor_nome_lote implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long codigo;
	@Version
	private long versao;
	@Column(columnDefinition = "varchar(20)")
	private String nomeLote;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public long getVersao() {
		return versao;
	}

	public void setVersao(long versao) {
		this.versao = versao;
	}

	public String getNomeLote() {
		return nomeLote;
	}

	public void setNomeLote(String nomeLote) {
		this.nomeLote = nomeLote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		result = prime * result + ((nomeLote == null) ? 0 : nomeLote.hashCode());
		result = prime * result + (int) (versao ^ (versao >>> 32));
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
		intra_emissor_nome_lote other = (intra_emissor_nome_lote) obj;
		if (codigo != other.codigo)
			return false;
		if (nomeLote == null) {
			if (other.nomeLote != null)
				return false;
		} else if (!nomeLote.equals(other.nomeLote))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}

}
