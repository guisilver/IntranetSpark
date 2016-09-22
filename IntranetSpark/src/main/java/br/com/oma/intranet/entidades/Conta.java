package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.List;

public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3801577028409313434L;
	private int id_contrato;
	private int contaBancaria;
	private int codBanco;
	private int condominio;
	private int contador;
	private String nomeBanco;
	private String codAgencia;
	private String nomeAgencia;
	private String CC;
	private List<TipoPagamento> tipoPagamento;
	private double valorTotal;
	private boolean contaVinculada;

	public int getId_contrato() {
		return id_contrato;
	}

	public void setId_contrato(int id_contrato) {
		this.id_contrato = id_contrato;
	}

	public int getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(int contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public int getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(int codBanco) {
		this.codBanco = codBanco;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getCC() {
		return CC;
	}

	public void setCC(String cC) {
		CC = cC;
	}

	public List<TipoPagamento> getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(List<TipoPagamento> tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isContaVinculada() {
		return contaVinculada;
	}

	public void setContaVinculada(boolean contaVinculada) {
		this.contaVinculada = contaVinculada;
	}

}
