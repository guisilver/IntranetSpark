package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class intra_bd_es implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(columnDefinition = "bigint")
	private int id;

	@Column(columnDefinition = "int")
	private int codigo;

	@Column(columnDefinition = "smallint")
	private int condominio;

	@Column(name = "nome_condo", columnDefinition = "varchar(50)")
	private String nomeCondo;

	@Column(columnDefinition = "datetime")
	private Date vencimento;

	@Column(name = "cod_lancamento", columnDefinition = "varchar(10)")
	private String codLancamento;

	@Column(columnDefinition = "varchar(500)")
	private String referencia;

	@Column(columnDefinition = "varchar(40)")
	private String nf;

	@Column(name = "nome_cedente", columnDefinition = "varchar(60)")
	private String nomeCedente;

	@Column(columnDefinition = "varchar(100)")
	private String favorecido;

	@Column(name = "cpf_cnpj", columnDefinition = "varchar(20)")
	private String cpfCnpj;

	@Column(columnDefinition = "varchar(20)")
	private String banco;

	@Column(columnDefinition = "varchar(20)")
	private String agencia;

	@Column(name = "dig_ag", columnDefinition = "varchar(5)")
	private String digAg;

	@Column(columnDefinition = "varchar(20)")
	private String conta;

	@Column(name = "tipo_conta", columnDefinition = "varchar(20)")
	private String tipoConta;

	@Column(name = "dig_conta", columnDefinition = "varchar(5)")
	private String digConta;

	@Column(columnDefinition = "varchar(max)")
	private String obs;

	@Column(columnDefinition = "varchar(20)")
	private String valor;

	@Column(columnDefinition = "datetime")
	private Date data;

	@Column(name = "cod_gerente", columnDefinition = "int")
	private int codGerente;

	@Column(name = "nome_gerente", columnDefinition = "varchar(50)")
	private String nomeGerente;

	@Column(columnDefinition = "varchar(50)")
	private String email;

	@Column(columnDefinition = "tinyint")
	private int tipo;

	@Transient
	private String vencimentoString;

	@Transient
	private String cndCodNome;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the condominio
	 */
	public int getCondominio() {
		return condominio;
	}

	/**
	 * @param condominio
	 *            the condominio to set
	 */
	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	/**
	 * @return the nomeCondo
	 */
	public String getNomeCondo() {
		return nomeCondo;
	}

	/**
	 * @param nomeCondo
	 *            the nomeCondo to set
	 */
	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	/**
	 * @return the vencimento
	 */
	public Date getVencimento() {
		return vencimento;
	}

	/**
	 * @param vencimento
	 *            the vencimento to set
	 */
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	/**
	 * @return the codLancamento
	 */
	public String getCodLancamento() {
		return codLancamento;
	}

	/**
	 * @param codLancamento
	 *            the codLancamento to set
	 */
	public void setCodLancamento(String codLancamento) {
		this.codLancamento = codLancamento;
	}

	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia
	 *            the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the nf
	 */
	public String getNf() {
		return nf;
	}

	/**
	 * @param nf
	 *            the nf to set
	 */
	public void setNf(String nf) {
		this.nf = nf;
	}

	/**
	 * @return the nomeCedente
	 */
	public String getNomeCedente() {
		return nomeCedente;
	}

	/**
	 * @param nomeCedente
	 *            the nomeCedente to set
	 */
	public void setNomeCedente(String nomeCedente) {
		this.nomeCedente = nomeCedente;
	}

	/**
	 * @return the favorecido
	 */
	public String getFavorecido() {
		return favorecido;
	}

	/**
	 * @param favorecido
	 *            the favorecido to set
	 */
	public void setFavorecido(String favorecido) {
		this.favorecido = favorecido;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj
	 *            the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco
	 *            the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return the agencia
	 */
	public String getAgencia() {
		return agencia;
	}

	/**
	 * @param agencia
	 *            the agencia to set
	 */
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	/**
	 * @return the digAg
	 */
	public String getDigAg() {
		return digAg;
	}

	/**
	 * @param digAg
	 *            the digAg to set
	 */
	public void setDigAg(String digAg) {
		this.digAg = digAg;
	}

	/**
	 * @return the conta
	 */
	public String getConta() {
		return conta;
	}

	/**
	 * @param conta
	 *            the conta to set
	 */
	public void setConta(String conta) {
		this.conta = conta;
	}

	/**
	 * @return the tipoConta
	 */
	public String getTipoConta() {
		return tipoConta;
	}

	/**
	 * @param tipoConta
	 *            the tipoConta to set
	 */
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	/**
	 * @return the digConta
	 */
	public String getDigConta() {
		return digConta;
	}

	/**
	 * @param digConta
	 *            the digConta to set
	 */
	public void setDigConta(String digConta) {
		this.digConta = digConta;
	}

	/**
	 * @return the obs
	 */
	public String getObs() {
		return obs;
	}

	/**
	 * @param obs
	 *            the obs to set
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the codGerente
	 */
	public int getCodGerente() {
		return codGerente;
	}

	/**
	 * @param codGerente
	 *            the codGerente to set
	 */
	public void setCodGerente(int codGerente) {
		this.codGerente = codGerente;
	}

	/**
	 * @return the nomeGerente
	 */
	public String getNomeGerente() {
		return nomeGerente;
	}

	/**
	 * @param nomeGerente
	 *            the nomeGerente to set
	 */
	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getVencimentoString() {
		if (this.vencimento != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			this.vencimentoString = dateFormat.format(this.vencimento);
		}
		return vencimentoString;
	}

	public void setVencimentoString(String vencimentoString) {
		this.vencimentoString = vencimentoString;
	}

	public String getCndCodNome() {
		if (this.condominio > 0 && this.nomeCondo != null
				&& !this.nomeCondo.isEmpty()) {
			this.cndCodNome = this.condominio + " - " + this.nomeCondo;
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
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + codGerente;
		result = prime * result
				+ ((codLancamento == null) ? 0 : codLancamento.hashCode());
		result = prime * result + codigo;
		result = prime * result + condominio;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((digAg == null) ? 0 : digAg.hashCode());
		result = prime * result
				+ ((digConta == null) ? 0 : digConta.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((favorecido == null) ? 0 : favorecido.hashCode());
		result = prime * result + id;
		result = prime * result + ((nf == null) ? 0 : nf.hashCode());
		result = prime * result
				+ ((nomeCedente == null) ? 0 : nomeCedente.hashCode());
		result = prime * result
				+ ((nomeCondo == null) ? 0 : nomeCondo.hashCode());
		result = prime * result
				+ ((nomeGerente == null) ? 0 : nomeGerente.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result
				+ ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + tipo;
		result = prime * result
				+ ((tipoConta == null) ? 0 : tipoConta.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result
				+ ((vencimento == null) ? 0 : vencimento.hashCode());
		result = prime
				* result
				+ ((vencimentoString == null) ? 0 : vencimentoString.hashCode());
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
		intra_bd_es other = (intra_bd_es) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (codGerente != other.codGerente)
			return false;
		if (codLancamento == null) {
			if (other.codLancamento != null)
				return false;
		} else if (!codLancamento.equals(other.codLancamento))
			return false;
		if (codigo != other.codigo)
			return false;
		if (condominio != other.condominio)
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null)
				return false;
		} else if (!cpfCnpj.equals(other.cpfCnpj))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (digAg == null) {
			if (other.digAg != null)
				return false;
		} else if (!digAg.equals(other.digAg))
			return false;
		if (digConta == null) {
			if (other.digConta != null)
				return false;
		} else if (!digConta.equals(other.digConta))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (favorecido == null) {
			if (other.favorecido != null)
				return false;
		} else if (!favorecido.equals(other.favorecido))
			return false;
		if (id != other.id)
			return false;
		if (nf == null) {
			if (other.nf != null)
				return false;
		} else if (!nf.equals(other.nf))
			return false;
		if (nomeCedente == null) {
			if (other.nomeCedente != null)
				return false;
		} else if (!nomeCedente.equals(other.nomeCedente))
			return false;
		if (nomeCondo == null) {
			if (other.nomeCondo != null)
				return false;
		} else if (!nomeCondo.equals(other.nomeCondo))
			return false;
		if (nomeGerente == null) {
			if (other.nomeGerente != null)
				return false;
		} else if (!nomeGerente.equals(other.nomeGerente))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (tipo != other.tipo)
			return false;
		if (tipoConta == null) {
			if (other.tipoConta != null)
				return false;
		} else if (!tipoConta.equals(other.tipoConta))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		if (vencimentoString == null) {
			if (other.vencimentoString != null)
				return false;
		} else if (!vencimentoString.equals(other.vencimentoString))
			return false;
		return true;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Cloning not allowed.");
			return this;
		}

	}

}
