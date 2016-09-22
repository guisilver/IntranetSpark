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
public class intra_conciliacao_lf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 128419875104095932L;
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
		result = prime * result + (baixado ? 1231 : 1237);
		result = prime * result + codigo;
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		result = prime * result + ((credor == null) ? 0 : credor.hashCode());
		result = prime * result + ((dataBaixado == null) ? 0 : dataBaixado.hashCode());
		result = prime * result + ((dataPagto == null) ? 0 : dataPagto.hashCode());
		result = prime * result + ((nro_identificacao == null) ? 0 : nro_identificacao.hashCode());
		result = prime * result + nrolancto;
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + (possuiImg ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		intra_conciliacao_lf other = (intra_conciliacao_lf) obj;
		if (baixado != other.baixado)
			return false;
		if (codigo != other.codigo)
			return false;
		if (condominio == null) {
			if (other.condominio != null)
				return false;
		} else if (!condominio.equals(other.condominio))
			return false;
		if (credor == null) {
			if (other.credor != null)
				return false;
		} else if (!credor.equals(other.credor))
			return false;
		if (dataBaixado == null) {
			if (other.dataBaixado != null)
				return false;
		} else if (!dataBaixado.equals(other.dataBaixado))
			return false;
		if (dataPagto == null) {
			if (other.dataPagto != null)
				return false;
		} else if (!dataPagto.equals(other.dataPagto))
			return false;
		if (nro_identificacao == null) {
			if (other.nro_identificacao != null)
				return false;
		} else if (!nro_identificacao.equals(other.nro_identificacao))
			return false;
		if (nrolancto != other.nrolancto)
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (possuiImg != other.possuiImg)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}

}
