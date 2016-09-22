package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class intra_condominios_saida implements Comparable<intra_condominios_saida>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7504655963949372840L;

	@Id
	@Column(columnDefinition = "smallint")
	private int codigo;

	@Column(columnDefinition = "varchar(50)")
	private String nome;

	@Column(columnDefinition = "int")
	private int codigoGerente;

	@Column(columnDefinition = "varchar(50)")
	private String endereco;

	@Column(columnDefinition = "varchar(10)")
	private String nro;

	@Column(columnDefinition = "varchar(40)")
	private String bairro;

	@Column(columnDefinition = "varchar(5)")
	private String logradouro;

	@Column(columnDefinition = "varchar(50)")
	private String nomeGerente;

	@Column(columnDefinition = "varchar(50)")
	private String emailGerente;

	@Temporal(TemporalType.DATE)
	private Date dataSaida;

	@Transient
	private String dataSaidaTxt;

	@Transient
	private String cndCodNome;

	@Transient
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

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

	public int getCodigoGerente() {
		return codigoGerente;
	}

	public void setCodigoGerente(int codigoGerente) {
		this.codigoGerente = codigoGerente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public String getEmailGerente() {
		return emailGerente;
	}

	public void setEmailGerente(String emailGerente) {
		this.emailGerente = emailGerente;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getDataSaidaTxt() {
		if (this.dataSaida != null) {
			this.dataSaidaTxt = this.sf.format(this.dataSaida);
		}
		return dataSaidaTxt;
	}

	public void setDataSaidaTxt(String dataSaidaTxt) {
		this.dataSaidaTxt = dataSaidaTxt;
	}

	public String getCndCodNome() {
		if (this.codigo > 0 && this.nome != null && !this.nome.isEmpty()) {
			this.cndCodNome = this.codigo + " - " + this.nome;
		}
		return cndCodNome;
	}

	public void setCndCodNome(String cndCodNome) {
		this.cndCodNome = cndCodNome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cndCodNome == null) ? 0 : cndCodNome.hashCode());
		result = prime * result + codigo;
		result = prime * result + codigoGerente;
		result = prime * result + ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = prime * result + ((emailGerente == null) ? 0 : emailGerente.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeGerente == null) ? 0 : nomeGerente.hashCode());
		result = prime * result + ((nro == null) ? 0 : nro.hashCode());
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
		intra_condominios_saida other = (intra_condominios_saida) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cndCodNome == null) {
			if (other.cndCodNome != null)
				return false;
		} else if (!cndCodNome.equals(other.cndCodNome))
			return false;
		if (codigo != other.codigo)
			return false;
		if (codigoGerente != other.codigoGerente)
			return false;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!dataSaida.equals(other.dataSaida))
			return false;
		if (emailGerente == null) {
			if (other.emailGerente != null)
				return false;
		} else if (!emailGerente.equals(other.emailGerente))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeGerente == null) {
			if (other.nomeGerente != null)
				return false;
		} else if (!nomeGerente.equals(other.nomeGerente))
			return false;
		if (nro == null) {
			if (other.nro != null)
				return false;
		} else if (!nro.equals(other.nro))
			return false;
		return true;
	}

	@Override
	public int compareTo(intra_condominios_saida o) {
		if (this.codigo < o.codigo) {
			return -1;
		}
		if (this.codigo > o.codigo) {
			return 1;
		}
		return 0;
	}

}
