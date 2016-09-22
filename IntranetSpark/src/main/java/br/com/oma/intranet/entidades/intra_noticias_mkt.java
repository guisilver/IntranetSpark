package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class intra_noticias_mkt implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(50)")
	private String titulo;
	@Column(columnDefinition = "varchar(100)")
	private String nomeArquivo;
	@Column(columnDefinition = "varchar(100)")
	private String capa;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInserido;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNoticia;
	@Column(columnDefinition = "bit")
	private boolean ativo;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public Date getDataInserido() {
		return dataInserido;
	}

	public void setDataInserido(Date dataInserido) {
		this.dataInserido = dataInserido;
	}

	public Date getDataNoticia() {
		return dataNoticia;
	}

	public void setDataNoticia(Date dataNoticia) {
		this.dataNoticia = dataNoticia;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((capa == null) ? 0 : capa.hashCode());
		result = prime * result + codigo;
		result = prime * result
				+ ((dataInserido == null) ? 0 : dataInserido.hashCode());
		result = prime * result
				+ ((dataNoticia == null) ? 0 : dataNoticia.hashCode());
		result = prime * result
				+ ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		intra_noticias_mkt other = (intra_noticias_mkt) obj;
		if (ativo != other.ativo)
			return false;
		if (capa == null) {
			if (other.capa != null)
				return false;
		} else if (!capa.equals(other.capa))
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataInserido == null) {
			if (other.dataInserido != null)
				return false;
		} else if (!dataInserido.equals(other.dataInserido))
			return false;
		if (dataNoticia == null) {
			if (other.dataNoticia != null)
				return false;
		} else if (!dataNoticia.equals(other.dataNoticia))
			return false;
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null)
				return false;
		} else if (!nomeArquivo.equals(other.nomeArquivo))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	

}
