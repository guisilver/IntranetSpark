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
public class actavlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2439644080063593993L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	private int empresa;
	private Date data;
	private int cta_debito;
	private int cta_credito;
	private int ccusto_deb;
	private int ccusto_cre;
	private double valor;
	private String historico;
	private Date data_baixa;
	private Date data_inclusao;
	private int cod_usuario;
	private int baixado_caixa;
	private int usuario;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getCta_debito() {
		return cta_debito;
	}

	public void setCta_debito(int cta_debito) {
		this.cta_debito = cta_debito;
	}

	public int getCta_credito() {
		return cta_credito;
	}

	public void setCta_credito(int cta_credito) {
		this.cta_credito = cta_credito;
	}

	public int getCcusto_deb() {
		return ccusto_deb;
	}

	public void setCcusto_deb(int ccusto_deb) {
		this.ccusto_deb = ccusto_deb;
	}

	public int getCcusto_cre() {
		return ccusto_cre;
	}

	public void setCcusto_cre(int ccusto_cre) {
		this.ccusto_cre = ccusto_cre;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Date getData_baixa() {
		return data_baixa;
	}

	public void setData_baixa(Date data_baixa) {
		this.data_baixa = data_baixa;
	}

	public Date getData_inclusao() {
		return data_inclusao;
	}

	public void setData_inclusao(Date data_inclusao) {
		this.data_inclusao = data_inclusao;
	}

	public int getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(int cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public int getBaixado_caixa() {
		return baixado_caixa;
	}

	public void setBaixado_caixa(int baixado_caixa) {
		this.baixado_caixa = baixado_caixa;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baixado_caixa;
		result = prime * result + ccusto_cre;
		result = prime * result + ccusto_deb;
		result = prime * result + cod_usuario;
		result = prime * result + cta_credito;
		result = prime * result + cta_debito;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((data_baixa == null) ? 0 : data_baixa.hashCode());
		result = prime * result + ((data_inclusao == null) ? 0 : data_inclusao.hashCode());
		result = prime * result + empresa;
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + numero;
		result = prime * result + usuario;
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
		actavlan other = (actavlan) obj;
		if (baixado_caixa != other.baixado_caixa)
			return false;
		if (ccusto_cre != other.ccusto_cre)
			return false;
		if (ccusto_deb != other.ccusto_deb)
			return false;
		if (cod_usuario != other.cod_usuario)
			return false;
		if (cta_credito != other.cta_credito)
			return false;
		if (cta_debito != other.cta_debito)
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
		if (data_inclusao == null) {
			if (other.data_inclusao != null)
				return false;
		} else if (!data_inclusao.equals(other.data_inclusao))
			return false;
		if (empresa != other.empresa)
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (numero != other.numero)
			return false;
		if (usuario != other.usuario)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}
