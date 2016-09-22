package br.com.oma.sigadm.entidades;

import java.io.Serializable;

import javax.persistence.Column;

public class intra_endereco_unidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int condominio;
	private String bloco;
	private String unidade;
	private String nome;
	private String logradouro;
	private String endereco;
	private String nro_end;
	private String complemento;
	private String bairro;
	@Column(name = "tipo_corresp", columnDefinition = "char(1)")
	private String tipo_corresp;

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNro_end() {
		return nro_end;
	}

	public void setNro_end(String nro_end) {
		this.nro_end = nro_end;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTipo_corresp() {
		return tipo_corresp;
	}

	public void setTipo_corresp(String tipo_corresp) {
		this.tipo_corresp = tipo_corresp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + condominio;
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nro_end == null) ? 0 : nro_end.hashCode());
		result = prime * result + ((tipo_corresp == null) ? 0 : tipo_corresp.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		intra_endereco_unidade other = (intra_endereco_unidade) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (condominio != other.condominio)
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
		if (nro_end == null) {
			if (other.nro_end != null)
				return false;
		} else if (!nro_end.equals(other.nro_end))
			return false;
		if (tipo_corresp == null) {
			if (other.tipo_corresp != null)
				return false;
		} else if (!tipo_corresp.equals(other.tipo_corresp))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

}
