package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "omaonline.dbo")
public class sgimpos implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "int")
	private int nrolancto;

	@Id
	@Column(columnDefinition = "smallint")
	private int tipo_imposto;

	@Column(columnDefinition = "numeric(10,0)")
	private double cod_receita;

	@Column(columnDefinition = "datetime")
	private Date vencto;

	@Column(columnDefinition = "numeric(14,2)")
	private double percentual;

	@Column(columnDefinition = "numeric(12,2)")
	private double valor;

	@Column(columnDefinition = "char(15)")
	private String cod_ccm;

	@Column(columnDefinition = "varchar(40)")
	private String obs_1;

	@Column(columnDefinition = "varchar(40)")
	private String obs_2;

	@Column(columnDefinition = "int")
	private int lancto_gerado;

	@Column(columnDefinition = "datetime")
	private Date dt_base;

	@Column(columnDefinition = "int")
	private int cod_municip;

	@Column(columnDefinition = "int")
	private int controle;

	@Column(columnDefinition = "datetime")
	private Date vencto_guia;

	@Column(columnDefinition = "smallint")
	private int sistema;

	@Column(columnDefinition = "int")
	private int conta_contabil;

	@Column(columnDefinition = "char(1)")
	private String tipopagto;

	@Column(columnDefinition = "numeric(14,2)")
	private double valor_a_pagar;

	@Column(columnDefinition = "datetime")
	private Date data_alteracao;

	@Column(columnDefinition = "varchar(50)")
	private String numero_nf;

	@Column(columnDefinition = "int")
	private int item_nf;

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
	}

	public int getTipo_imposto() {
		return tipo_imposto;
	}

	public void setTipo_imposto(int tipo_imposto) {
		this.tipo_imposto = tipo_imposto;
	}

	public double getCod_receita() {
		return cod_receita;
	}

	public void setCod_receita(double cod_receita) {
		this.cod_receita = cod_receita;
	}

	public Date getVencto() {
		return vencto;
	}

	public void setVencto(Date vencto) {
		this.vencto = vencto;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getCod_ccm() {
		return cod_ccm;
	}

	public void setCod_ccm(String cod_ccm) {
		this.cod_ccm = cod_ccm;
	}

	public String getObs_1() {
		return obs_1;
	}

	public void setObs_1(String obs_1) {
		this.obs_1 = obs_1;
	}

	public String getObs_2() {
		return obs_2;
	}

	public void setObs_2(String obs_2) {
		this.obs_2 = obs_2;
	}

	public int getLancto_gerado() {
		return lancto_gerado;
	}

	public void setLancto_gerado(int lancto_gerado) {
		this.lancto_gerado = lancto_gerado;
	}

	public Date getDt_base() {
		return dt_base;
	}

	public void setDt_base(Date dt_base) {
		this.dt_base = dt_base;
	}

	public int getCod_municip() {
		return cod_municip;
	}

	public void setCod_municip(int cod_municip) {
		this.cod_municip = cod_municip;
	}

	public int getControle() {
		return controle;
	}

	public void setControle(int controle) {
		this.controle = controle;
	}

	public Date getVencto_guia() {
		return vencto_guia;
	}

	public void setVencto_guia(Date vencto_guia) {
		this.vencto_guia = vencto_guia;
	}

	public int getSistema() {
		return sistema;
	}

	public void setSistema(int sistema) {
		this.sistema = sistema;
	}

	public int getConta_contabil() {
		return conta_contabil;
	}

	public void setConta_contabil(int conta_contabil) {
		this.conta_contabil = conta_contabil;
	}

	public String getTipopagto() {
		return tipopagto;
	}

	public void setTipopagto(String tipopagto) {
		this.tipopagto = tipopagto;
	}

	public double getValor_a_pagar() {
		return valor_a_pagar;
	}

	public void setValor_a_pagar(double valor_a_pagar) {
		this.valor_a_pagar = valor_a_pagar;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public String getNumero_nf() {
		return numero_nf;
	}

	public void setNumero_nf(String numero_nf) {
		this.numero_nf = numero_nf;
	}

	public int getItem_nf() {
		return item_nf;
	}

	public void setItem_nf(int item_nf) {
		this.item_nf = item_nf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod_ccm == null) ? 0 : cod_ccm.hashCode());
		result = prime * result + cod_municip;
		long temp;
		temp = Double.doubleToLongBits(cod_receita);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + conta_contabil;
		result = prime * result + controle;
		result = prime * result + ((data_alteracao == null) ? 0 : data_alteracao.hashCode());
		result = prime * result + ((dt_base == null) ? 0 : dt_base.hashCode());
		result = prime * result + item_nf;
		result = prime * result + lancto_gerado;
		result = prime * result + nrolancto;
		result = prime * result + ((numero_nf == null) ? 0 : numero_nf.hashCode());
		result = prime * result + ((obs_1 == null) ? 0 : obs_1.hashCode());
		result = prime * result + ((obs_2 == null) ? 0 : obs_2.hashCode());
		temp = Double.doubleToLongBits(percentual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + sistema;
		result = prime * result + tipo_imposto;
		result = prime * result + ((tipopagto == null) ? 0 : tipopagto.hashCode());
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor_a_pagar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vencto == null) ? 0 : vencto.hashCode());
		result = prime * result + ((vencto_guia == null) ? 0 : vencto_guia.hashCode());
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
		sgimpos other = (sgimpos) obj;
		if (cod_ccm == null) {
			if (other.cod_ccm != null)
				return false;
		} else if (!cod_ccm.equals(other.cod_ccm))
			return false;
		if (cod_municip != other.cod_municip)
			return false;
		if (Double.doubleToLongBits(cod_receita) != Double.doubleToLongBits(other.cod_receita))
			return false;
		if (conta_contabil != other.conta_contabil)
			return false;
		if (controle != other.controle)
			return false;
		if (data_alteracao == null) {
			if (other.data_alteracao != null)
				return false;
		} else if (!data_alteracao.equals(other.data_alteracao))
			return false;
		if (dt_base == null) {
			if (other.dt_base != null)
				return false;
		} else if (!dt_base.equals(other.dt_base))
			return false;
		if (item_nf != other.item_nf)
			return false;
		if (lancto_gerado != other.lancto_gerado)
			return false;
		if (nrolancto != other.nrolancto)
			return false;
		if (numero_nf == null) {
			if (other.numero_nf != null)
				return false;
		} else if (!numero_nf.equals(other.numero_nf))
			return false;
		if (obs_1 == null) {
			if (other.obs_1 != null)
				return false;
		} else if (!obs_1.equals(other.obs_1))
			return false;
		if (obs_2 == null) {
			if (other.obs_2 != null)
				return false;
		} else if (!obs_2.equals(other.obs_2))
			return false;
		if (Double.doubleToLongBits(percentual) != Double.doubleToLongBits(other.percentual))
			return false;
		if (sistema != other.sistema)
			return false;
		if (tipo_imposto != other.tipo_imposto)
			return false;
		if (tipopagto == null) {
			if (other.tipopagto != null)
				return false;
		} else if (!tipopagto.equals(other.tipopagto))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (Double.doubleToLongBits(valor_a_pagar) != Double.doubleToLongBits(other.valor_a_pagar))
			return false;
		if (vencto == null) {
			if (other.vencto != null)
				return false;
		} else if (!vencto.equals(other.vencto))
			return false;
		if (vencto_guia == null) {
			if (other.vencto_guia != null)
				return false;
		} else if (!vencto_guia.equals(other.vencto_guia))
			return false;
		return true;
	}
	
}
