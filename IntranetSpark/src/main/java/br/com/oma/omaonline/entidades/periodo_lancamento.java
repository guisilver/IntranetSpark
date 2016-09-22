package br.com.oma.omaonline.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="omaonline.dbo")
public class periodo_lancamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	
	@Column(name="primeiro_periodo", columnDefinition="int")
	private int primeiroPeriodo;
	
	@Column(name="segundo_periodo", columnDefinition="int")
	private int segundoPeriodo;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getPrimeiroPeriodo() {
		return primeiroPeriodo;
	}

	public void setPrimeiroPeriodo(int primeiroPeriodo) {
		this.primeiroPeriodo = primeiroPeriodo;
	}

	public int getSegundoPeriodo() {
		return segundoPeriodo;
	}

	public void setSegundoPeriodo(int segundoPeriodo) {
		this.segundoPeriodo = segundoPeriodo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + primeiroPeriodo;
		result = prime * result + segundoPeriodo;
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
		periodo_lancamento other = (periodo_lancamento) obj;
		if (codigo != other.codigo)
			return false;
		if (primeiroPeriodo != other.primeiroPeriodo)
			return false;
		if (segundoPeriodo != other.segundoPeriodo)
			return false;
		return true;
	}
	
}
