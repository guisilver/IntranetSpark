package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class intra_candidato_prontuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(100)")
	private String tipoArquivo;
	@Column(columnDefinition = "varchar(100)")
	private String nomeArquivo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnviado;
	@Lob
	private byte[] arquivo;
	@ManyToOne
	private intra_candidato candidato;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Date getDataEnviado() {
		return dataEnviado;
	}

	public void setDataEnviado(Date dataEnviado) {
		this.dataEnviado = dataEnviado;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public intra_candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(intra_candidato candidato) {
		this.candidato = candidato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arquivo);
		result = prime * result + ((candidato == null) ? 0 : candidato.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((dataEnviado == null) ? 0 : dataEnviado.hashCode());
		result = prime * result + ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		result = prime * result + ((tipoArquivo == null) ? 0 : tipoArquivo.hashCode());
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
		intra_candidato_prontuario other = (intra_candidato_prontuario) obj;
		if (!Arrays.equals(arquivo, other.arquivo))
			return false;
		if (candidato == null) {
			if (other.candidato != null)
				return false;
		} else if (!candidato.equals(other.candidato))
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataEnviado == null) {
			if (other.dataEnviado != null)
				return false;
		} else if (!dataEnviado.equals(other.dataEnviado))
			return false;
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null)
				return false;
		} else if (!nomeArquivo.equals(other.nomeArquivo))
			return false;
		if (tipoArquivo == null) {
			if (other.tipoArquivo != null)
				return false;
		} else if (!tipoArquivo.equals(other.tipoArquivo))
			return false;
		return true;
	}
	
}
