package br.com.oma.omaonline.entidades;

import java.io.Serializable;

public class consulta_financeiro implements Comparable<consulta_financeiro>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private int condominio;
	private int qtdPendente;
	private int qtdAguardando;
	private int qtdReprovado;
	private int qtdAprovados;
	
	public int getCondominio() {
		return condominio;
	}
	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}
	public int getQtdPendente() {
		return qtdPendente;
	}
	public void setQtdPendente(int qtdPendente) {
		this.qtdPendente = qtdPendente;
	}
	public int getQtdAguardando() {
		return qtdAguardando;
	}
	public void setQtdAguardando(int qtdAguardando) {
		this.qtdAguardando = qtdAguardando;
	}
	public int getQtdReprovado() {
		return qtdReprovado;
	}
	public void setQtdReprovado(int qtdReprovado) {
		this.qtdReprovado = qtdReprovado;
	}
	public int getQtdAprovados() {
		return qtdAprovados;
	}
	public void setQtdAprovados(int qtdAprovados) {
		this.qtdAprovados = qtdAprovados;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + condominio;
		result = prime * result + qtdAguardando;
		result = prime * result + qtdAprovados;
		result = prime * result + qtdPendente;
		result = prime * result + qtdReprovado;
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
		consulta_financeiro other = (consulta_financeiro) obj;
		if (condominio != other.condominio)
			return false;
		if (qtdAguardando != other.qtdAguardando)
			return false;
		if (qtdAprovados != other.qtdAprovados)
			return false;
		if (qtdPendente != other.qtdPendente)
			return false;
		if (qtdReprovado != other.qtdReprovado)
			return false;
		return true;
	}
	@Override
	public int compareTo(consulta_financeiro o) {
		if(this.condominio < o.condominio){
			return -1;
		}
		if(this.condominio > o.condominio){
			return 1;
		}
		return 0;
	}
	
	
		
}
