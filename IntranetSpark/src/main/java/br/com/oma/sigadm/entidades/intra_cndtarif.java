package br.com.oma.sigadm.entidades;

import java.io.Serializable;

public class intra_cndtarif implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String descricao;
	private String unidade_medida;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidade_medida() {
		return unidade_medida;
	}

	public void setUnidade_medida(String unidade_medida) {
		this.unidade_medida = unidade_medida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((unidade_medida == null) ? 0 : unidade_medida.hashCode());
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
		intra_cndtarif other = (intra_cndtarif) obj;
		if (codigo != other.codigo)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (unidade_medida == null) {
			if (other.unidade_medida != null)
				return false;
		} else if (!unidade_medida.equals(other.unidade_medida))
			return false;
		return true;
	}

}
