package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "sigadm.dbo")
public class cndleitu implements Serializable {

	private static final long serialVersionUID = -854804857721306877L;
	@Id
	private int numero;
	private int mes;
	private int ano;
	private Date data;
	private double total_consumo;
	private int tipo;
	private int lcto_cobranca;
	private double valor_total;
	private String bloco;
	private int importacao;
	private int condominio;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getTotal_consumo() {
		return total_consumo;
	}

	public void setTotal_consumo(double total_consumo) {
		this.total_consumo = total_consumo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getLcto_cobranca() {
		return lcto_cobranca;
	}

	public void setLcto_cobranca(int lcto_cobranca) {
		this.lcto_cobranca = lcto_cobranca;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public int getImportacao() {
		return importacao;
	}

	public void setImportacao(int importacao) {
		this.importacao = importacao;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + condominio;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + importacao;
		result = prime * result + lcto_cobranca;
		result = prime * result + mes;
		result = prime * result + numero;
		result = prime * result + tipo;
		long temp;
		temp = Double.doubleToLongBits(total_consumo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor_total);
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
		cndleitu other = (cndleitu) obj;
		if (ano != other.ano)
			return false;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (condominio != other.condominio)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (importacao != other.importacao)
			return false;
		if (lcto_cobranca != other.lcto_cobranca)
			return false;
		if (mes != other.mes)
			return false;
		if (numero != other.numero)
			return false;
		if (tipo != other.tipo)
			return false;
		if (Double.doubleToLongBits(total_consumo) != Double.doubleToLongBits(other.total_consumo))
			return false;
		if (Double.doubleToLongBits(valor_total) != Double.doubleToLongBits(other.valor_total))
			return false;
		return true;
	}

}
