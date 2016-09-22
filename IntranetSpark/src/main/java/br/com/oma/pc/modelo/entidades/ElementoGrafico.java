package br.com.oma.pc.modelo.entidades;

import java.io.Serializable;

public class ElementoGrafico implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nomeConta;
	private String mesAno;
	private double valor;

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mesAno == null) ? 0 : mesAno.hashCode());
		result = prime * result + ((nomeConta == null) ? 0 : nomeConta.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ElementoGrafico other = (ElementoGrafico) obj;
		if (mesAno == null) {
			if (other.mesAno != null)
				return false;
		} else if (!mesAno.equals(other.mesAno))
			return false;
		if (nomeConta == null) {
			if (other.nomeConta != null)
				return false;
		} else if (!nomeConta.equals(other.nomeConta))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}
