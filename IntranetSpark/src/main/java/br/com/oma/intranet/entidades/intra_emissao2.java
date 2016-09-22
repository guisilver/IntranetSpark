package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class intra_emissao2 implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private int codigo;
	private int conta;
	private String historico;
	private double valor;
	private char tipo;
	private int nroRateio;

	@ManyToOne
	private intra_emissao_completo rateio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public int getNroRateio() {
		return nroRateio;
	}

	public void setNroRateio(int nroRateio) {
		this.nroRateio = nroRateio;
	}

	public intra_emissao_completo getRateio() {
		return rateio;
	}

	public void setRateio(intra_emissao_completo rateio) {
		this.rateio = rateio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + conta;
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + id;
		result = prime * result + nroRateio;
		result = prime * result + ((rateio == null) ? 0 : rateio.hashCode());
		result = prime * result + tipo;
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
		intra_emissao2 other = (intra_emissao2) obj;
		if (codigo != other.codigo)
			return false;
		if (conta != other.conta)
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (id != other.id)
			return false;
		if (nroRateio != other.nroRateio)
			return false;
		if (rateio == null) {
			if (other.rateio != null)
				return false;
		} else if (!rateio.equals(other.rateio))
			return false;
		if (tipo != other.tipo)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Cloning not allowed.");
			return this;
		}
	}
}