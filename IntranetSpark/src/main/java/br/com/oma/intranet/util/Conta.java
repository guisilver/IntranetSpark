package br.com.oma.intranet.util;

import java.io.Serializable;
import java.util.Date;

public class Conta implements Comparable<Object>, Serializable {

	private static final long serialVersionUID = 1L;

	private int conta;
	private String nomeConta;
	private int codigoCondominio;
	private String situacao;
	private Date vencimento;
	private String valor;

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Conta))
			throw new ClassCastException();
		Conta e = (Conta) o;
		return comparacao(situacao).compareTo(comparacao(e.getSituacao()));
	}

	public String comparacao(String situacao) {
		if (situacao.equals("A vencer")) {
			return "A";
		} else if (situacao.equals("Vencido")) {
			return "B";
		} else {
			return "C";
		}
	}

}
