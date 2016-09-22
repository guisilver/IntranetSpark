package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class intra_plano_contas implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	
	@Column(columnDefinition="int")
	private int conta;
	
	@Column(columnDefinition="int")
	private int subConta;
	
	@Column(columnDefinition="varchar(50)")
	private String nome;
	
	@Column(columnDefinition="varchar(10)")
	private String codReduzido;
	
	@Column(columnDefinition="varchar(40)")
	private String nomeRealizado;

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

	public int getSubConta() {
		return subConta;
	}

	public void setSubConta(int subConta) {
		this.subConta = subConta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodReduzido() {
		return codReduzido;
	}

	public void setCodReduzido(String codReduzido) {
		this.codReduzido = codReduzido;
	}

	public String getNomeRealizado() {
		return nomeRealizado;
	}

	public void setNomeRealizado(String nomeRealizado) {
		this.nomeRealizado = nomeRealizado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codReduzido == null) ? 0 : codReduzido.hashCode());
		result = prime * result + codigo;
		result = prime * result + conta;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((nomeRealizado == null) ? 0 : nomeRealizado.hashCode());
		result = prime * result + subConta;
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
		if (!(obj instanceof intra_plano_contas)) {
			return false;
		}
		intra_plano_contas other = (intra_plano_contas) obj;
		if (codReduzido == null) {
			if (other.codReduzido != null) {
				return false;
			}
		} else if (!codReduzido.equals(other.codReduzido)) {
			return false;
		}
		if (codigo != other.codigo) {
			return false;
		}
		if (conta != other.conta) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (nomeRealizado == null) {
			if (other.nomeRealizado != null) {
				return false;
			}
		} else if (!nomeRealizado.equals(other.nomeRealizado)) {
			return false;
		}
		if (subConta != other.subConta) {
			return false;
		}
		return true;
	}
	
	
}
