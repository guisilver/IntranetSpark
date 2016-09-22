package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class intra_favorecido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="int")
	private int codigo;
	
	@Column(columnDefinition="varchar(50)")
	private String favorecido;
	
	@Column(columnDefinition="varchar(15)")
	private String cnpjCpf;
	
	@Column(columnDefinition="varchar(20)")
	private String banco;
	
	@Column(columnDefinition="varchar(10)")
	private String agencia;
	
	@Column(columnDefinition="varchar(10)")
	private String conta;
	
	@Column(columnDefinition="varchar(3)")
	private String digConta;
	
	@Column(columnDefinition="varchar(3)")
	private String tipoConta;

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
	

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public String getDigConta() {
		return digConta;
	}

	public void setDigConta(String digConta) {
		this.digConta = digConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + ((cnpjCpf == null) ? 0 : cnpjCpf.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result
				+ ((digConta == null) ? 0 : digConta.hashCode());
		result = prime * result
				+ ((favorecido == null) ? 0 : favorecido.hashCode());
		result = prime * result
				+ ((tipoConta == null) ? 0 : tipoConta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof intra_favorecido)) {
			return false;
		}
		intra_favorecido other = (intra_favorecido) obj;
		if (agencia == null) {
			if (other.agencia != null) {
				return false;
			}
		} else if (!agencia.equals(other.agencia)) {
			return false;
		}
		if (banco == null) {
			if (other.banco != null) {
				return false;
			}
		} else if (!banco.equals(other.banco)) {
			return false;
		}
		if (cnpjCpf == null) {
			if (other.cnpjCpf != null) {
				return false;
			}
		} else if (!cnpjCpf.equals(other.cnpjCpf)) {
			return false;
		}
		if (codigo != other.codigo) {
			return false;
		}
		if (conta == null) {
			if (other.conta != null) {
				return false;
			}
		} else if (!conta.equals(other.conta)) {
			return false;
		}
		if (digConta == null) {
			if (other.digConta != null) {
				return false;
			}
		} else if (!digConta.equals(other.digConta)) {
			return false;
		}
		if (favorecido == null) {
			if (other.favorecido != null) {
				return false;
			}
		} else if (!favorecido.equals(other.favorecido)) {
			return false;
		}
		if (tipoConta == null) {
			if (other.tipoConta != null) {
				return false;
			}
		} else if (!tipoConta.equals(other.tipoConta)) {
			return false;
		}
		return true;
	}
	
	
}
