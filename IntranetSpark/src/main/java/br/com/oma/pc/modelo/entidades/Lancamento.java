package br.com.oma.pc.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private short condominio;
	private int ano;
	private int mes;
	private int conta;
	private int numero;
	private double valor;
	private String historico;
	private Date data;
	private boolean sintetico;
	private boolean possuiDocs;

	public short getCondominio() {
		return condominio;
	}

	public void setCondominio(short condominio) {
		this.condominio = condominio;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isSintetico() {
		return sintetico;
	}

	public void setSintetico(boolean sintetico) {
		this.sintetico = sintetico;
	}

	public boolean isPossuiDocs() {
		return possuiDocs;
	}

	public void setPossuiDocs(boolean possuiDocs) {
		this.possuiDocs = possuiDocs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + condominio;
		result = prime * result + conta;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + mes;
		result = prime * result + numero;
		result = prime * result + (possuiDocs ? 1231 : 1237);
		result = prime * result + (sintetico ? 1231 : 1237);
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
		Lancamento other = (Lancamento) obj;
		if (ano != other.ano)
			return false;
		if (condominio != other.condominio)
			return false;
		if (conta != other.conta)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (mes != other.mes)
			return false;
		if (numero != other.numero)
			return false;
		if (possuiDocs != other.possuiDocs)
			return false;
		if (sintetico != other.sintetico)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}
