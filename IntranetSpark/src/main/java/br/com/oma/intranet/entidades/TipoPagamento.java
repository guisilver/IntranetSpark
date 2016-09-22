package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.List;

public class TipoPagamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8289283898496426476L;
	private String tipoPagamento;
	private String descricao;
	private List<Lancamento> lancamentos;
	private int contador;
	private double valorTotalTipo;
	private boolean contaVinculada;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public double getValorTotalTipo() {
		return valorTotalTipo;
	}

	public void setValorTotalTipo(double valorTotalTipo) {
		this.valorTotalTipo = valorTotalTipo;
	}

	public boolean isContaVinculada() {
		return contaVinculada;
	}

	public void setContaVinculada(boolean contaVinculada) {
		this.contaVinculada = contaVinculada;
	}

}
