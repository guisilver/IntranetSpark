package br.com.oma.pc.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class cndcondo implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(columnDefinition = "smallint")
	private short codigo;
	@Column(columnDefinition = "varchar(40)")
	private String nome;
	@Column(columnDefinition = "varchar(2)")
	private String estado;
	@Column(columnDefinition = "varchar(25)")
	private String cidade;
	@Column(columnDefinition = "varchar(25)")
	private String bairro;
	@Column(columnDefinition = "varchar(50)")
	private String endereco;
	@Column(columnDefinition = "numeric(8,0)")
	private long cep;
	@Column(name="nro_end",columnDefinition = "varchar(10)")
	private String nroEnd;
	@Column(columnDefinition = "smallint")
	private short gerente;
	
	@Column(columnDefinition="varchar(10)")
	private String logradouro;

	public short getCodigo() {
		return codigo;
	}

	public void setCodigo(short codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public long getCep() {
		return cep;
	}

	public void setCep(long cep) {
		this.cep = cep;
	}

	public String getNroEnd() {
		return nroEnd;
	}

	public void setNroEnd(String nroEnd) {
		this.nroEnd = nroEnd;
	}

	public short getGerente() {
		return gerente;
	}

	public void setGerente(short gerente) {
		this.gerente = gerente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + (int) (cep ^ (cep >>> 32));
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + codigo;
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + gerente;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nroEnd == null) ? 0 : nroEnd.hashCode());
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
		cndcondo other = (cndcondo) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep != other.cep)
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (codigo != other.codigo)
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (gerente != other.gerente)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nroEnd == null) {
			if (other.nroEnd != null)
				return false;
		} else if (!nroEnd.equals(other.nroEnd))
			return false;
		return true;
	}

}
