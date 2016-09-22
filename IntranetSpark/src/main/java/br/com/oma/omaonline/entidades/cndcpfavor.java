package br.com.oma.omaonline.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class cndcpfavor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo", columnDefinition="int")
    private int codigo;
	   
	@Column(name="favorecido", columnDefinition="varchar(50)")
    private String favorecido;
	   
	@Column(name="tipo_pessoa", columnDefinition="char(1)")
    private String tipoPessoa;
	   
	@Column(name="cnpj_cpf", columnDefinition="numeric(14,0)")
    private long cnpjCpf;
	   
	@Column(name="banco", columnDefinition="smallint")
    private short banco;
	   
	@Column(name="agencia", columnDefinition="int")
    private int agencia;
	   
	@Column(name="conta_corrente", columnDefinition="varchar(15)")
    private String contaCorrente;
	   
	@Column(name="dac_conta", columnDefinition="varchar(2)")
    private String dacConta;
	   
	@Column(name="conta_poupanca", columnDefinition="char(1)")
    private String contaPoupanca;
	   
	@Column(name="tipo_conta", columnDefinition="varchar(2)")
    private String tipo_conta;
	
	@Column(name="nome_banco", columnDefinition="varchar(50)")
	private String nomeBanco;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(String favorecido) {
		this.favorecido = favorecido;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public long getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(long cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public short getBanco() {
		return banco;
	}

	public void setBanco(short banco) {
		this.banco = banco;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public String getDacConta() {
		return dacConta;
	}

	public void setDacConta(String dacConta) {
		this.dacConta = dacConta;
	}

	public String getContaPoupanca() {
		return contaPoupanca;
	}

	public void setContaPoupanca(String contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}

	public String getTipo_conta() {
		return tipo_conta;
	}

	public void setTipo_conta(String tipo_conta) {
		this.tipo_conta = tipo_conta;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + agencia;
		result = prime * result + banco;
		result = prime * result + (int) (cnpjCpf ^ (cnpjCpf >>> 32));
		result = prime * result + codigo;
		result = prime * result + ((contaCorrente == null) ? 0 : contaCorrente.hashCode());
		result = prime * result + ((contaPoupanca == null) ? 0 : contaPoupanca.hashCode());
		result = prime * result + ((dacConta == null) ? 0 : dacConta.hashCode());
		result = prime * result + ((favorecido == null) ? 0 : favorecido.hashCode());
		result = prime * result + ((nomeBanco == null) ? 0 : nomeBanco.hashCode());
		result = prime * result + ((tipoPessoa == null) ? 0 : tipoPessoa.hashCode());
		result = prime * result + ((tipo_conta == null) ? 0 : tipo_conta.hashCode());
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
		cndcpfavor other = (cndcpfavor) obj;
		if (agencia != other.agencia)
			return false;
		if (banco != other.banco)
			return false;
		if (cnpjCpf != other.cnpjCpf)
			return false;
		if (codigo != other.codigo)
			return false;
		if (contaCorrente == null) {
			if (other.contaCorrente != null)
				return false;
		} else if (!contaCorrente.equals(other.contaCorrente))
			return false;
		if (contaPoupanca == null) {
			if (other.contaPoupanca != null)
				return false;
		} else if (!contaPoupanca.equals(other.contaPoupanca))
			return false;
		if (dacConta == null) {
			if (other.dacConta != null)
				return false;
		} else if (!dacConta.equals(other.dacConta))
			return false;
		if (favorecido == null) {
			if (other.favorecido != null)
				return false;
		} else if (!favorecido.equals(other.favorecido))
			return false;
		if (nomeBanco == null) {
			if (other.nomeBanco != null)
				return false;
		} else if (!nomeBanco.equals(other.nomeBanco))
			return false;
		if (tipoPessoa == null) {
			if (other.tipoPessoa != null)
				return false;
		} else if (!tipoPessoa.equals(other.tipoPessoa))
			return false;
		if (tipo_conta == null) {
			if (other.tipo_conta != null)
				return false;
		} else if (!tipo_conta.equals(other.tipo_conta))
			return false;
		return true;
	}
	
}
