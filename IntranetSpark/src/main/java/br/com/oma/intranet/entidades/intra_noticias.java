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
public class intra_noticias implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(100)")
	private String titulo;
	@Column(columnDefinition = "varchar(MAX)")
	private String html;
	@Column(columnDefinition = "int")
	private int coluna;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInserido;

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

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public Date getDataInserido() {
		return dataInserido;
	}

	public void setDataInserido(Date dataInserido) {
		this.dataInserido = dataInserido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + coluna;
		result = prime * result + ((dataInserido == null) ? 0 : dataInserido.hashCode());
		result = prime * result + ((html == null) ? 0 : html.hashCode());
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
		intra_noticias other = (intra_noticias) obj;
		if (codigo != other.codigo)
			return false;
		if (coluna != other.coluna)
			return false;
		if (dataInserido == null) {
			if (other.dataInserido != null)
				return false;
		} else if (!dataInserido.equals(other.dataInserido))
			return false;
		if (html == null) {
			if (other.html != null)
				return false;
		} else if (!html.equals(other.html))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

}
