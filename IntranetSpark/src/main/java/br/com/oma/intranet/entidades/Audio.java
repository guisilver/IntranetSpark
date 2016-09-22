package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Audio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 931507060734046096L;
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(MAX)")
	private String nomeArquivo;
	@Lob
	private byte[] audio;

	public byte[] getAudio() {
		return audio;
	}

	public void setAudio(byte[] audio) {
		this.audio = audio;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(audio);
		result = prime * result + codigo;
		result = prime * result + ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
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
		Audio other = (Audio) obj;
		if (!Arrays.equals(audio, other.audio))
			return false;
		if (codigo != other.codigo)
			return false;
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null)
				return false;
		} else if (!nomeArquivo.equals(other.nomeArquivo))
			return false;
		return true;
	}

}
