package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

@Entity
public class intra_candidato_vaga_historico implements Serializable, Comparable<intra_candidato_vaga_historico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4827028124895564778L;
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(MAX)")
	private String texto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new Date();

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
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
		intra_candidato_vaga_historico other = (intra_candidato_vaga_historico) obj;
		if (codigo != other.codigo)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	@Override
	public int compareTo(intra_candidato_vaga_historico o) {
		if (!(o instanceof intra_candidato_vaga_historico))
			throw new ClassCastException();
		intra_candidato_vaga_historico e = (intra_candidato_vaga_historico) o;
		return new DateTime(this.data).compareTo(new DateTime(e.getData()));
	}

}
