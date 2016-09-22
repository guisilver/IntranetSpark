package br.com.oma.intranet.util;

import java.io.Serializable;

public class PrevisaoCapaAUX implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6299240519597785226L;
	private String capa;
	private String despesa;

	private double valor;
	
	private int conta;
	private int codigoReduzido;
	
	public String getCapa() {
		return capa;
	}
	public void setCapa(String capa) {
		this.capa = capa;
	}
	public String getDespesa() {
		return despesa;
	}
	public void setDespesa(String despesa) {
		this.despesa = despesa;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getConta() {
		return conta;
	}
	public void setConta(int conta) {
		this.conta = conta;
	}
	public int getCodigoReduzido() {
		return codigoReduzido;
	}
	public void setCodigoReduzido(int codigoReduzido) {
		this.codigoReduzido = codigoReduzido;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capa == null) ? 0 : capa.hashCode());
		result = prime * result + codigoReduzido;
		result = prime * result + conta;
		result = prime * result + ((despesa == null) ? 0 : despesa.hashCode());
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
		PrevisaoCapaAUX other = (PrevisaoCapaAUX) obj;
		if (capa == null) {
			if (other.capa != null)
				return false;
		} else if (!capa.equals(other.capa))
			return false;
		if (codigoReduzido != other.codigoReduzido)
			return false;
		if (conta != other.conta)
			return false;
		if (despesa == null) {
			if (other.despesa != null)
				return false;
		} else if (!despesa.equals(other.despesa))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}
	
	
	
}
