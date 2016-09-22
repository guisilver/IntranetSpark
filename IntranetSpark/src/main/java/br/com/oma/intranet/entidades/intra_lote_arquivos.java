package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class intra_lote_arquivos implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(250)")
	private String nomeArquivo;
	@Column(columnDefinition = "varchar(500)")
	private String obs;
	@Column(columnDefinition = "varchar(15)")
	private String situacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConferido;
	@Column(columnDefinition = "varbinary(MAX)")
	private byte[] arquivo;

	public intra_lote_arquivos() {

	}

	public intra_lote_arquivos(int codigo, String nomeArquivo, String obs,
			Date dataConferido, byte[] arquivo) {
		this.codigo = codigo;
		this.nomeArquivo = nomeArquivo;
		this.obs = obs;
		this.situacao = "Pendente";
		this.dataConferido = dataConferido;
		this.arquivo = arquivo;
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

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDataConferido() {
		return dataConferido;
	}

	public void setDataConferido(Date dataConferido) {
		this.dataConferido = dataConferido;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arquivo);
		result = prime * result + codigo;
		result = prime * result + ((dataConferido == null) ? 0 : dataConferido.hashCode());
		result = prime * result + ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
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
		intra_lote_arquivos other = (intra_lote_arquivos) obj;
		if (!Arrays.equals(arquivo, other.arquivo))
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataConferido == null) {
			if (other.dataConferido != null)
				return false;
		} else if (!dataConferido.equals(other.dataConferido))
			return false;
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null)
				return false;
		} else if (!nomeArquivo.equals(other.nomeArquivo))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}

}
