package br.com.oma.omaonline.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class cndplano implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_reduzido", columnDefinition="int")
	private int cod_reduzido;
	
	@Column(name="conta", columnDefinition="int")
	private int conta;

	@Column(name="sub_conta", columnDefinition="tinyint")
	private int sub_conta;
	
	@Column(name="nome", columnDefinition="varchar(30)")
    private String nome;

	@Column(name = "nome_capa", columnDefinition = "varchar(30)")
	private String nome_capa;
	
	@Column(name="nome_realizado", columnDefinition="varchar(30)")
	private String nome_realizado;
   
	@Column(name="tipo", columnDefinition="char(1)")
	private String tipo;
	
	@Column(name="grau", columnDefinition="tinyint")
	private int grau;
   
    @Column(name="codigo_grafico", columnDefinition="int")           
    private int codigo_grafico;
   
    @Column(name="codigo_plano", columnDefinition="smallint")
    private short codigo_plano;

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public int getSub_conta() {
		return sub_conta;
	}

	public void setSub_conta(int sub_conta) {
		this.sub_conta = sub_conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome_capa() {
		return nome_capa;
	}

	public void setNome_capa(String nome_capa) {
		this.nome_capa = nome_capa;
	}

	public String getNome_realizado() {
		return nome_realizado;
	}

	public void setNome_realizado(String nome_realizado) {
		this.nome_realizado = nome_realizado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getGrau() {
		return grau;
	}

	public void setGrau(int grau) {
		this.grau = grau;
	}

	public int getCod_reduzido() {
		return cod_reduzido;
	}

	public void setCod_reduzido(int cod_reduzido) {
		this.cod_reduzido = cod_reduzido;
	}

	public int getCodigo_grafico() {
		return codigo_grafico;
	}

	public void setCodigo_grafico(int codigo_grafico) {
		this.codigo_grafico = codigo_grafico;
	}

	public short getCodigo_plano() {
		return codigo_plano;
	}

	public void setCodigo_plano(short codigo_plano) {
		this.codigo_plano = codigo_plano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_reduzido;
		result = prime * result + codigo_grafico;
		result = prime * result + codigo_plano;
		result = prime * result + conta;
		result = prime * result + grau;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nome_capa == null) ? 0 : nome_capa.hashCode());
		result = prime * result + ((nome_realizado == null) ? 0 : nome_realizado.hashCode());
		result = prime * result + sub_conta;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		cndplano other = (cndplano) obj;
		if (cod_reduzido != other.cod_reduzido)
			return false;
		if (codigo_grafico != other.codigo_grafico)
			return false;
		if (codigo_plano != other.codigo_plano)
			return false;
		if (conta != other.conta)
			return false;
		if (grau != other.grau)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nome_capa == null) {
			if (other.nome_capa != null)
				return false;
		} else if (!nome_capa.equals(other.nome_capa))
			return false;
		if (nome_realizado == null) {
			if (other.nome_realizado != null)
				return false;
		} else if (!nome_realizado.equals(other.nome_realizado))
			return false;
		if (sub_conta != other.sub_conta)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
    
}
