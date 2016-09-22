package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class intra_conciliacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5174463707613746059L;
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int nrolancto;
	@Column(columnDefinition = "bit")
	private boolean baixado;
	@Column(columnDefinition = "varchar(50)")
	private String nro_identificacao;
	@Column(columnDefinition = "float")
	private double valor;
	@Column(columnDefinition = "varchar(50)")
	private String condominio;
	@Column(columnDefinition = "varchar(50)")
	private String credor;
	@Temporal(TemporalType.DATE)
	private Date vencimento;
	@Temporal(TemporalType.DATE)
	private Date dataBaixado;
	@Temporal(TemporalType.DATE)
	private Date dataPagto;
	@Column(columnDefinition = "varchar(1000)")
	private String obs;

	@Transient
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	@Transient
	DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
	@Transient
	private String dataPagtoTxt, vencimentoTxt, valorTxt;

	@Transient
	private boolean possuiImg;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
	}

	public boolean isBaixado() {
		return baixado;
	}

	public void setBaixado(boolean baixado) {
		this.baixado = baixado;
	}

	public String getNro_identificacao() {
		return nro_identificacao;
	}

	public void setNro_identificacao(String nro_identificacao) {
		this.nro_identificacao = nro_identificacao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getCondominio() {
		return condominio;
	}

	public void setCondominio(String condominio) {
		this.condominio = condominio;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getDataBaixado() {
		return dataBaixado;
	}

	public void setDataBaixado(Date dataBaixado) {
		this.dataBaixado = dataBaixado;
	}

	public Date getDataPagto() {
		return dataPagto;
	}

	public void setDataPagto(Date dataPagto) {
		this.dataPagto = dataPagto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getDataPagtoTxt() {
		if (this.dataPagto != null) {
			this.dataPagtoTxt = this.sf.format(this.dataPagto);
		}
		return dataPagtoTxt;
	}

	public void setDataPagtoTxt(String dataPagtoTxt) {
		this.dataPagtoTxt = dataPagtoTxt;
	}

	public String getVencimentoTxt() {
		if (this.vencimento != null) {
			this.vencimentoTxt = this.sf.format(this.vencimento);
		}
		return vencimentoTxt;
	}

	public void setVencimentoTxt(String vencimentoTxt) {
		this.vencimentoTxt = vencimentoTxt;
	}

	public String getValorTxt() {
		if (this.valor > 0) {
			this.valorTxt = this.df.format(this.valor);
		}
		return valorTxt;
	}

	public void setValorTxt(String valorTxt) {
		this.valorTxt = valorTxt;
	}

	public boolean isPossuiImg() {
		return possuiImg;
	}

	public void setPossuiImg(boolean possuiImg) {
		this.possuiImg = possuiImg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nrolancto;
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
		intra_conciliacao other = (intra_conciliacao) obj;
		if (nrolancto != other.nrolancto)
			return false;
		return true;
	}

}
