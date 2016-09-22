package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_cnduteio2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6040911685189484190L;

	@Id
	@GeneratedValue
	private int id;

	private int nro_rateio;
	private int nro_lancto;
	private int condominio;
	private String bloco;
	private String unidade;
	private double valor;
	private String comp_hist;
	private int dfrecnum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNro_rateio() {
		return nro_rateio;
	}

	public void setNro_rateio(int nro_rateio) {
		this.nro_rateio = nro_rateio;
	}

	public int getNro_lancto() {
		return nro_lancto;
	}

	public void setNro_lancto(int nro_lancto) {
		this.nro_lancto = nro_lancto;
	}

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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getComp_hist() {
		return comp_hist;
	}

	public void setComp_hist(String comp_hist) {
		this.comp_hist = comp_hist;
	}

	public int getDfrecnum() {
		return dfrecnum;
	}

	public void setDfrecnum(int dfrecnum) {
		this.dfrecnum = dfrecnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((comp_hist == null) ? 0 : comp_hist.hashCode());
		result = prime * result + condominio;
		result = prime * result + dfrecnum;
		result = prime * result + id;
		result = prime * result + nro_lancto;
		result = prime * result + nro_rateio;
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		intra_cnduteio2 other = (intra_cnduteio2) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (comp_hist == null) {
			if (other.comp_hist != null)
				return false;
		} else if (!comp_hist.equals(other.comp_hist))
			return false;
		if (condominio != other.condominio)
			return false;
		if (dfrecnum != other.dfrecnum)
			return false;
		if (id != other.id)
			return false;
		if (nro_lancto != other.nro_lancto)
			return false;
		if (nro_rateio != other.nro_rateio)
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
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
