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
public class intra_quiz_param implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int repeticao;
	@Column(columnDefinition = "int")
	private int qtdEnvios;
	@Column(columnDefinition = "bit")
	private boolean autorizado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getRepeticao() {
		return repeticao;
	}

	public void setRepeticao(int repeticao) {
		this.repeticao = repeticao;
	}

	public int getQtdEnvios() {
		return qtdEnvios;
	}

	public void setQtdEnvios(int qtdEnvios) {
		this.qtdEnvios = qtdEnvios;
	}

	public boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (autorizado ? 1231 : 1237);
		result = prime * result + codigo;
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + qtdEnvios;
		result = prime * result + repeticao;
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
		intra_quiz_param other = (intra_quiz_param) obj;
		if (autorizado != other.autorizado)
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (qtdEnvios != other.qtdEnvios)
			return false;
		if (repeticao != other.repeticao)
			return false;
		return true;
	}
	

}
