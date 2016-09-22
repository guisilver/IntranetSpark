package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_func_ultimo_tipo_escala implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6527450355766706678L;
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int codigoCondominio;
	@Column(columnDefinition = "int")
	private int codigoFuncionario;
	@Column(columnDefinition = "int")
	private int tipo;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public int getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(int codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + codigoCondominio;
		result = prime * result + codigoFuncionario;
		result = prime * result + tipo;
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
		intra_func_ultimo_tipo_escala other = (intra_func_ultimo_tipo_escala) obj;
		if (codigo != other.codigo)
			return false;
		if (codigoCondominio != other.codigoCondominio)
			return false;
		if (codigoFuncionario != other.codigoFuncionario)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

}