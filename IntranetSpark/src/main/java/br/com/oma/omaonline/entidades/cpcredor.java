package br.com.oma.omaonline.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class cpcredor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usualcred", columnDefinition = "varchar(12)")
	private String usualcred;

	@Column(name = "nome", columnDefinition = "varchar(35)")
	private String nome;

	@Column(name = "tipo_inscricao", columnDefinition = "char(1)")
	private String tipoInscricao;

	@Column(name = "inscricao", columnDefinition = "numeric(14,0)")
	private long inscricao;

	private double perc_pis;

	private double perc_cofins;

	private double perc_csll;

	private double perc_inss;

	private double perc_irrf;

	private double perc_iss;

	private boolean reter_impostos;

	private boolean reter_inss;

	private boolean reter_irrf;

	private boolean reter_iss;
	
	private String ccm;
	
	private int cod_munic;
	
	private String observacoes;

	public String getUsualcred() {
		return usualcred;
	}

	public void setUsualcred(String usualcred) {
		this.usualcred = usualcred;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoInscricao() {
		return tipoInscricao;
	}

	public void setTipoInscricao(String tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}

	public long getInscricao() {
		return inscricao;
	}

	public void setInscricao(long inscricao) {
		this.inscricao = inscricao;
	}

	public double getPerc_pis() {
		return perc_pis;
	}

	public void setPerc_pis(double perc_pis) {
		this.perc_pis = perc_pis;
	}

	public double getPerc_cofins() {
		return perc_cofins;
	}

	public void setPerc_cofins(double perc_cofins) {
		this.perc_cofins = perc_cofins;
	}

	public double getPerc_csll() {
		return perc_csll;
	}

	public void setPerc_csll(double perc_csll) {
		this.perc_csll = perc_csll;
	}

	public double getPerc_inss() {
		return perc_inss;
	}

	public void setPerc_inss(double perc_inss) {
		this.perc_inss = perc_inss;
	}

	public double getPerc_irrf() {
		return perc_irrf;
	}

	public void setPerc_irrf(double perc_irrf) {
		this.perc_irrf = perc_irrf;
	}

	public double getPerc_iss() {
		return perc_iss;
	}

	public void setPerc_iss(double perc_iss) {
		this.perc_iss = perc_iss;
	}

	public boolean isReter_impostos() {
		return reter_impostos;
	}

	public void setReter_impostos(boolean reter_impostos) {
		this.reter_impostos = reter_impostos;
	}

	public boolean isReter_inss() {
		return reter_inss;
	}

	public void setReter_inss(boolean reter_inss) {
		this.reter_inss = reter_inss;
	}

	public boolean isReter_irrf() {
		return reter_irrf;
	}

	public void setReter_irrf(boolean reter_irrf) {
		this.reter_irrf = reter_irrf;
	}

	public boolean isReter_iss() {
		return reter_iss;
	}

	public void setReter_iss(boolean reter_iss) {
		this.reter_iss = reter_iss;
	}

	public String getCcm() {
		return ccm;
	}

	public void setCcm(String ccm) {
		this.ccm = ccm;
	}

	public int getCod_munic() {
		return cod_munic;
	}

	public void setCod_munic(int cod_munic) {
		this.cod_munic = cod_munic;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ccm == null) ? 0 : ccm.hashCode());
		result = prime * result + cod_munic;
		result = prime * result + (int) (inscricao ^ (inscricao >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		long temp;
		temp = Double.doubleToLongBits(perc_cofins);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(perc_csll);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(perc_inss);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(perc_irrf);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(perc_iss);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(perc_pis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (reter_impostos ? 1231 : 1237);
		result = prime * result + (reter_inss ? 1231 : 1237);
		result = prime * result + (reter_irrf ? 1231 : 1237);
		result = prime * result + (reter_iss ? 1231 : 1237);
		result = prime * result + ((tipoInscricao == null) ? 0 : tipoInscricao.hashCode());
		result = prime * result + ((usualcred == null) ? 0 : usualcred.hashCode());
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
		cpcredor other = (cpcredor) obj;
		if (ccm == null) {
			if (other.ccm != null)
				return false;
		} else if (!ccm.equals(other.ccm))
			return false;
		if (cod_munic != other.cod_munic)
			return false;
		if (inscricao != other.inscricao)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (Double.doubleToLongBits(perc_cofins) != Double.doubleToLongBits(other.perc_cofins))
			return false;
		if (Double.doubleToLongBits(perc_csll) != Double.doubleToLongBits(other.perc_csll))
			return false;
		if (Double.doubleToLongBits(perc_inss) != Double.doubleToLongBits(other.perc_inss))
			return false;
		if (Double.doubleToLongBits(perc_irrf) != Double.doubleToLongBits(other.perc_irrf))
			return false;
		if (Double.doubleToLongBits(perc_iss) != Double.doubleToLongBits(other.perc_iss))
			return false;
		if (Double.doubleToLongBits(perc_pis) != Double.doubleToLongBits(other.perc_pis))
			return false;
		if (reter_impostos != other.reter_impostos)
			return false;
		if (reter_inss != other.reter_inss)
			return false;
		if (reter_irrf != other.reter_irrf)
			return false;
		if (reter_iss != other.reter_iss)
			return false;
		if (tipoInscricao == null) {
			if (other.tipoInscricao != null)
				return false;
		} else if (!tipoInscricao.equals(other.tipoInscricao))
			return false;
		if (usualcred == null) {
			if (other.usualcred != null)
				return false;
		} else if (!usualcred.equals(other.usualcred))
			return false;
		return true;
	}
	

}
