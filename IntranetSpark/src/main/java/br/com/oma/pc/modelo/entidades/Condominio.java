package br.com.oma.pc.modelo.entidades;

import java.io.Serializable;

public class Condominio implements Serializable {

	private static final long serialVersionUID = 1L;

	private short codigo;
	private Short codigoCnd;
	private String nome;
	private int codigoPlano;
	private boolean analit_sitet;
	private String cndCodNome;

	public short getCodigo() {
		return codigo;
	}

	public void setCodigo(short codigo) {
		this.codigo = codigo;
	}

	public Short getCodigoCnd() {
		return codigoCnd;
	}

	public void setCodigoCnd(Short codigoCnd) {
		this.codigoCnd = codigoCnd;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigoPlano() {
		return codigoPlano;
	}

	public void setCodigoPlano(int codigoPlano) {
		this.codigoPlano = codigoPlano;
	}

	public boolean isAnalit_sitet() {
		return analit_sitet;
	}

	public void setAnalit_sitet(boolean analit_sitet) {
		this.analit_sitet = analit_sitet;
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
		result = prime * result + (analit_sitet ? 1231 : 1237);
		result = prime * result + ((cndCodNome == null) ? 0 : cndCodNome.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((codigoCnd == null) ? 0 : codigoCnd.hashCode());
		result = prime * result + codigoPlano;
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
		Condominio other = (Condominio) obj;
		if (analit_sitet != other.analit_sitet)
			return false;
		if (cndCodNome == null) {
			if (other.cndCodNome != null)
				return false;
		} else if (!cndCodNome.equals(other.cndCodNome))
			return false;
		if (codigo != other.codigo)
			return false;
		if (codigoCnd == null) {
			if (other.codigoCnd != null)
				return false;
		} else if (!codigoCnd.equals(other.codigoCnd))
			return false;
		if (codigoPlano != other.codigoPlano)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
