package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class intra_funcionario implements Comparable<Object>, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 923696060516230040L;
	private int codigo;
	private String nome;
	private String funcao;
	private int tipoEscala;
	private int situacao;
	private intra_condominios condominio;
	private List<intra_escala_folga> folgas = new ArrayList<intra_escala_folga>();

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

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public int getTipoEscala() {
		return tipoEscala;
	}

	public void setTipoEscala(int tipoEscala) {
		this.tipoEscala = tipoEscala;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public List<intra_escala_folga> getFolgas() {
		return folgas;
	}

	public void setFolgas(List<intra_escala_folga> folgas) {
		this.folgas = folgas;
	}

	/*
	 * @Override public int compareTo(Object o) { if (!(o instanceof
	 * intra_funcionario)) throw new ClassCastException(); intra_funcionario e =
	 * (intra_funcionario) o; return nome.compareTo(e.getNome()); }
	 */

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof intra_funcionario))
			throw new ClassCastException();
		intra_funcionario e = (intra_funcionario) o;
		return comparacao(funcao).compareTo(comparacao(e.getFuncao()));
	}

	public String comparacao(String situacao) {
		if (situacao.toLowerCase().contains("zelador")) {
			return "A";
		} else if (situacao.toLowerCase().contains("porteiro")
				&& situacao.toLowerCase().contains("diurno")) {
			return "B";
		} else if ((situacao.toLowerCase().contains("porteiro") && situacao
				.toLowerCase().contains("vespertino"))
				|| (situacao.toLowerCase().contains("porteiro") && situacao
						.toLowerCase().contains("tarde"))) {
			return "C";
		} else if (situacao.toLowerCase().contains("porteiro")
				&& situacao.toLowerCase().contains("noturno")) {
			return "D";
		} else if (situacao.toLowerCase().contains("folguista")) {
			return "E";
		} else if (situacao.toLowerCase().contains("aux")) {
			return "F";
		} else if (situacao.toLowerCase().contains("faxineiro")
				|| situacao.toLowerCase().contains("faxina")
				|| situacao.toLowerCase().contains("faxineira")) {
			return "G";
		} else {
			return "H";
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		result = prime * result + ((folgas == null) ? 0 : folgas.hashCode());
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + situacao;
		result = prime * result + tipoEscala;
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
		intra_funcionario other = (intra_funcionario) obj;
		if (codigo != other.codigo)
			return false;
		if (condominio == null) {
			if (other.condominio != null)
				return false;
		} else if (!condominio.equals(other.condominio))
			return false;
		if (folgas == null) {
			if (other.folgas != null)
				return false;
		} else if (!folgas.equals(other.folgas))
			return false;
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (situacao != other.situacao)
			return false;
		if (tipoEscala != other.tipoEscala)
			return false;
		return true;
	}
	
}
