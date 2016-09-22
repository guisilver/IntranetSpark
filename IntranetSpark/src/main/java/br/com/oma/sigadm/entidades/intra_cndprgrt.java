package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;

public class intra_cndprgrt implements Serializable{

	private static final long serialVersionUID = 1L;

	private int condominio;
	private int emissao;
	private Date vencimento;
	private int gerente;
	
	public int getCondominio() {
		return condominio;
	}
	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}
	public int getEmissao() {
		return emissao;
	}
	public void setEmissao(int emissao) {
		this.emissao = emissao;
	}
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public int getGerente() {
		return gerente;
	}
	public void setGerente(int gerente) {
		this.gerente = gerente;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + condominio;
		result = prime * result + emissao;
		result = prime * result + gerente;
		result = prime * result + ((vencimento == null) ? 0 : vencimento.hashCode());
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
		intra_cndprgrt other = (intra_cndprgrt) obj;
		if (condominio != other.condominio)
			return false;
		if (emissao != other.emissao)
			return false;
		if (gerente != other.gerente)
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}
	
}
