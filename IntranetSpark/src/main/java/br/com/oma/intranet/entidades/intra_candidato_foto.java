package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class intra_candidato_foto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int codigoCandidato;
	@Lob
	private byte[] foto;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoCandidato() {
		return codigoCandidato;
	}

	public void setCodigoCandidato(int codigoCandidato) {
		this.codigoCandidato = codigoCandidato;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + codigoCandidato;
		result = prime * result + Arrays.hashCode(foto);
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
		intra_candidato_foto other = (intra_candidato_foto) obj;
		if (codigo != other.codigo)
			return false;
		if (codigoCandidato != other.codigoCandidato)
			return false;
		if (!Arrays.equals(foto, other.foto))
			return false;
		return true;
	}

	
}
