package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

public class intra_prepax implements Serializable {

	private static final long serialVersionUID = 1L;

	private String conta_bancaria;
	private String cpf;
	private double valor;
	private int empresa;
	private int funcionario;
	private String nome;
	private int codigo;
	private int mesP;
	private int mesI;
	private int ano;
	private Date data_pagto;
	private int cod_conta;
	private double imposto_renda;
	private double arredondamento;

	public String getConta_bancaria() {
		return conta_bancaria;
	}

	public void setConta_bancaria(String conta_bancaria) {
		this.conta_bancaria = conta_bancaria;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	public int getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(int funcionario) {
		this.funcionario = funcionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getMesP() {
		return mesP;
	}

	public void setMesP(int mesP) {
		this.mesP = mesP;
	}

	public int getMesI() {
		return mesI;
	}

	public void setMesI(int mesI) {
		this.mesI = mesI;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Date getData_pagto() {
		return data_pagto;
	}

	public void setData_pagto(Date data_pagto) {
		this.data_pagto = data_pagto;
	}

	public int getCod_conta() {
		return cod_conta;
	}

	public void setCod_conta(int cod_conta) {
		this.cod_conta = cod_conta;
	}

	public double getImposto_renda() {
		return imposto_renda;
	}

	public void setImposto_renda(double imposto_renda) {
		this.imposto_renda = imposto_renda;
	}

	public double getArredondamento() {
		return arredondamento;
	}

	public void setArredondamento(double arredondamento) {
		this.arredondamento = arredondamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		long temp;
		temp = Double.doubleToLongBits(arredondamento);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + cod_conta;
		result = prime * result + codigo;
		result = prime * result + ((conta_bancaria == null) ? 0 : conta_bancaria.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((data_pagto == null) ? 0 : data_pagto.hashCode());
		result = prime * result + empresa;
		result = prime * result + funcionario;
		temp = Double.doubleToLongBits(imposto_renda);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + mesI;
		result = prime * result + mesP;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		intra_prepax other = (intra_prepax) obj;
		if (ano != other.ano)
			return false;
		if (Double.doubleToLongBits(arredondamento) != Double.doubleToLongBits(other.arredondamento))
			return false;
		if (cod_conta != other.cod_conta)
			return false;
		if (codigo != other.codigo)
			return false;
		if (conta_bancaria == null) {
			if (other.conta_bancaria != null)
				return false;
		} else if (!conta_bancaria.equals(other.conta_bancaria))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (data_pagto == null) {
			if (other.data_pagto != null)
				return false;
		} else if (!data_pagto.equals(other.data_pagto))
			return false;
		if (empresa != other.empresa)
			return false;
		if (funcionario != other.funcionario)
			return false;
		if (Double.doubleToLongBits(imposto_renda) != Double.doubleToLongBits(other.imposto_renda))
			return false;
		if (mesI != other.mesI)
			return false;
		if (mesP != other.mesP)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}