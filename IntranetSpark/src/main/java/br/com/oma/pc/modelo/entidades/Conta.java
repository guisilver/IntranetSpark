package br.com.oma.pc.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String nome;
	private List<Lancamento> lancamentos = new ArrayList<>();
	private char analit_sintet;
	private int mes;
	private int ano;
	private double valorToral;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public char getAnalit_sintet() {
		return analit_sintet;
	}

	public void setAnalit_sintet(char analit_sintet) {
		this.analit_sintet = analit_sintet;
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

	public double getValorToral() {
		return valorToral;
	}

	public void setValorToral(double valorToral) {
		this.valorToral = valorToral;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + analit_sintet;
		result = prime * result + ano;
		result = prime * result + codigo;
		result = prime * result
				+ ((lancamentos == null) ? 0 : lancamentos.hashCode());
		result = prime * result + mes;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Conta other = (Conta) obj;
		if (analit_sintet != other.analit_sintet)
			return false;
		if (ano != other.ano)
			return false;
		if (codigo != other.codigo)
			return false;
		if (lancamentos == null) {
			if (other.lancamentos != null)
				return false;
		} else if (!lancamentos.equals(other.lancamentos))
			return false;
		if (mes != other.mes)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

/*	public double getValorTotalConta(int mesSelecionado) {
		double valorTotal = 0;
		for (Lancamento lancamento : getLancamentos()) {
			if (lancamento.getMes() == new DateTime(mesSelecionado)
					.getMonthOfYear()
					&& lancamento.getAno() == new DateTime(mesSelecionado)
							.getYear()) {
				valorTotal += lancamento.getValor();
			}
		}
		BigDecimal bd = new BigDecimal(valorTotal);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}*/

}
