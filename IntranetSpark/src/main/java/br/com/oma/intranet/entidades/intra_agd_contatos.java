package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_agd_contatos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	/*
	 * @ManyToOne private intra_agd_origem origem;
	 * 
	 * @ManyToOne private intra_grupo_depto departamento;
	 */
	@Column(columnDefinition = "int")
	private int condominio;
	@Column(columnDefinition = "varchar(50)")
	private String nome;
	@Column(columnDefinition = "varchar(50)")
	private String uf;
	@Column(columnDefinition = "varchar(50)")
	private String cidade;
	@Column(columnDefinition = "varchar(50)")
	private String cep;
	@Column(columnDefinition = "varchar(50)")
	private String bairro;
	@Column(columnDefinition = "varchar(250)")
	private String endereco;
	@Column(columnDefinition = "varchar(500)")
	private String obs;
	@Column(columnDefinition = "varchar(50)")
	private String celular;
	@Column(columnDefinition = "varchar(50)")
	private String comercial;
	@Column(columnDefinition = "varchar(50)")
	private String residencial;
	@Column(columnDefinition = "varchar(50)")
	private String email;
	@Column(columnDefinition = "varchar(50)")
	private String outros;
	@Column(columnDefinition = "varchar(50)")
	private String ramal;
	@Column(columnDefinition = "varchar(50)")
	private String email_oma;
	@Column(columnDefinition = "int")
	private int cod_gerente;
	@Column(columnDefinition = "bit")
	private boolean encarregado;
	@Column(columnDefinition = "bit")
	private boolean assistente;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public String getResidencial() {
		return residencial;
	}

	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOutros() {
		return outros;
	}

	public void setOutros(String outros) {
		this.outros = outros;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getEmail_oma() {
		return email_oma;
	}

	public void setEmail_oma(String email_oma) {
		this.email_oma = email_oma;
	}

	public int getCod_gerente() {
		return cod_gerente;
	}

	public void setCod_gerente(int cod_gerente) {
		this.cod_gerente = cod_gerente;
	}

	public boolean isEncarregado() {
		return encarregado;
	}

	public void setEncarregado(boolean encarregado) {
		this.encarregado = encarregado;
	}

	public boolean isAssistente() {
		return assistente;
	}

	public void setAssistente(boolean assistente) {
		this.assistente = assistente;
	}

}
