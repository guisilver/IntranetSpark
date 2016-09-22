package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "sigadm.dbo")
public class cndavlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5152203513725828832L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nrolancto;
	private int condominio;
	private String bloco;
	private int conta;
	private int cod_histo;
	private String historico;
	private double valor;
	private Date data;
	private Date data_baixa;
	private double contab_debito;
	private double contab_credito;
	private char deb_cre;
	private char cpmf;
	private int cta_anl_financ;
	private int baixado_caixa;
	private int incide_tx_adm;
	private int usuario;
	private String login_user;

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
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

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public int getCod_histo() {
		return cod_histo;
	}

	public void setCod_histo(int cod_histo) {
		this.cod_histo = cod_histo;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getData_baixa() {
		return data_baixa;
	}

	public void setData_baixa(Date data_baixa) {
		this.data_baixa = data_baixa;
	}

	public double getContab_debito() {
		return contab_debito;
	}

	public void setContab_debito(double contab_debito) {
		this.contab_debito = contab_debito;
	}

	public double getContab_credito() {
		return contab_credito;
	}

	public void setContab_credito(double contab_credito) {
		this.contab_credito = contab_credito;
	}

	public char getDeb_cre() {
		return deb_cre;
	}

	public void setDeb_cre(char deb_cre) {
		this.deb_cre = deb_cre;
	}

	public char getCpmf() {
		return cpmf;
	}

	public void setCpmf(char cpmf) {
		this.cpmf = cpmf;
	}

	public int getCta_anl_financ() {
		return cta_anl_financ;
	}

	public void setCta_anl_financ(int cta_anl_financ) {
		this.cta_anl_financ = cta_anl_financ;
	}

	public int getBaixado_caixa() {
		return baixado_caixa;
	}

	public void setBaixado_caixa(int baixado_caixa) {
		this.baixado_caixa = baixado_caixa;
	}

	public int getIncide_tx_adm() {
		return incide_tx_adm;
	}

	public void setIncide_tx_adm(int incide_tx_adm) {
		this.incide_tx_adm = incide_tx_adm;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public String getLogin_user() {
		return login_user;
	}

	public void setLogin_user(String login_user) {
		this.login_user = login_user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baixado_caixa;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + cod_histo;
		result = prime * result + condominio;
		result = prime * result + conta;
		long temp;
		temp = Double.doubleToLongBits(contab_credito);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(contab_debito);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + cpmf;
		result = prime * result + cta_anl_financ;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((data_baixa == null) ? 0 : data_baixa.hashCode());
		result = prime * result + deb_cre;
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + incide_tx_adm;
		result = prime * result + ((login_user == null) ? 0 : login_user.hashCode());
		result = prime * result + nrolancto;
		result = prime * result + usuario;
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
		cndavlan other = (cndavlan) obj;
		if (baixado_caixa != other.baixado_caixa)
			return false;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (cod_histo != other.cod_histo)
			return false;
		if (condominio != other.condominio)
			return false;
		if (conta != other.conta)
			return false;
		if (Double.doubleToLongBits(contab_credito) != Double.doubleToLongBits(other.contab_credito))
			return false;
		if (Double.doubleToLongBits(contab_debito) != Double.doubleToLongBits(other.contab_debito))
			return false;
		if (cpmf != other.cpmf)
			return false;
		if (cta_anl_financ != other.cta_anl_financ)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (data_baixa == null) {
			if (other.data_baixa != null)
				return false;
		} else if (!data_baixa.equals(other.data_baixa))
			return false;
		if (deb_cre != other.deb_cre)
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (incide_tx_adm != other.incide_tx_adm)
			return false;
		if (login_user == null) {
			if (other.login_user != null)
				return false;
		} else if (!login_user.equals(other.login_user))
			return false;
		if (nrolancto != other.nrolancto)
			return false;
		if (usuario != other.usuario)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}
