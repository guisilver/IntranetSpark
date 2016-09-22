package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_previsao_rateio implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "bigint")
	@GeneratedValue
	private int codigo;
	
	@Column(columnDefinition="smallint")
	private int condominio;
	
	@Column(name="emissao_mensal", columnDefinition="varchar(100)")
	private String emissaoMensal;
	
	@Column(name="emissao_Atual", columnDefinition="numeric(28,2)")
	private double emissaoAtual;
	
	@Column(name="emissao_necessaria", columnDefinition="numeric(28,2)")
	private double emissaoNecessaria;
	
	@Column(name="tipo_rateio", columnDefinition="char(1)")
	private String tipoRateio;

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

	public String getEmissaoMensal() {
		return emissaoMensal;
	}

	public void setEmissaoMensal(String emissaoMensal) {
		this.emissaoMensal = emissaoMensal;
	}

	public double getEmissaoAtual() {
		return emissaoAtual;
	}

	public void setEmissaoAtual(double emissaoAtual) {
		this.emissaoAtual = emissaoAtual;
	}

	public double getEmissaoNecessaria() {
		return emissaoNecessaria;
	}

	public void setEmissaoNecessaria(double emissaoNecessaria) {
		this.emissaoNecessaria = emissaoNecessaria;
	}

	public String getTipoRateio() {
		return tipoRateio;
	}

	public void setTipoRateio(String tipoRateio) {
		this.tipoRateio = tipoRateio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + condominio;
		long temp;
		temp = Double.doubleToLongBits(emissaoAtual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((emissaoMensal == null) ? 0 : emissaoMensal.hashCode());
		temp = Double.doubleToLongBits(emissaoNecessaria);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipoRateio == null) ? 0 : tipoRateio.hashCode());
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
		intra_previsao_rateio other = (intra_previsao_rateio) obj;
		if (codigo != other.codigo)
			return false;
		if (condominio != other.condominio)
			return false;
		if (Double.doubleToLongBits(emissaoAtual) != Double.doubleToLongBits(other.emissaoAtual))
			return false;
		if (emissaoMensal == null) {
			if (other.emissaoMensal != null)
				return false;
		} else if (!emissaoMensal.equals(other.emissaoMensal))
			return false;
		if (Double.doubleToLongBits(emissaoNecessaria) != Double.doubleToLongBits(other.emissaoNecessaria))
			return false;
		if (tipoRateio == null) {
			if (other.tipoRateio != null)
				return false;
		} else if (!tipoRateio.equals(other.tipoRateio))
			return false;
		return true;
	}
	
}
