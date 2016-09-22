package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.oma.pc.modelo.entidades.Condominio;

public class DACSV implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6625115212294772874L;
	private Date dataPagto;
	private String codigoDebito;
	private double valor;
	private double valorBanco;
	private boolean baixado;
	private short codigoCnd;
	private String condominio;
	private int nrolancto;
	private String credor;
	private Date vencimento;
	private boolean possuiImg;
	private String obs;
	private Condominio cnd;
	public boolean saidaCnd;

	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
	private String dataPagtoTxt, vencimentoTxt, valorTxt;

	public Date getDataPagto() {
		return dataPagto;
	}

	public void setDataPagto(Date dataPagto) {
		this.dataPagto = dataPagto;
	}

	public String getCodigoDebito() {
		return codigoDebito;
	}

	public void setCodigoDebito(String codigoDebito) {
		this.codigoDebito = codigoDebito;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorBanco() {
		return valorBanco;
	}

	public void setValorBanco(double valorBanco) {
		this.valorBanco = valorBanco;
	}

	public boolean isBaixado() {
		return baixado;
	}

	public void setBaixado(boolean baixado) {
		this.baixado = baixado;
	}

	public short getCodigoCnd() {
		return codigoCnd;
	}

	public void setCodigoCnd(short codigoCnd) {
		this.codigoCnd = codigoCnd;
	}

	public String getCondominio() {
		return condominio;
	}

	public void setCondominio(String condominio) {
		this.condominio = condominio;
	}

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
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

	public boolean isPossuiImg() {
		return possuiImg;
	}

	public void setPossuiImg(boolean possuiImg) {
		this.possuiImg = possuiImg;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Condominio getCnd() {
		return cnd;
	}

	public void setCnd(Condominio cnd) {
		this.cnd = cnd;
	}

	public boolean isSaidaCnd() {
		return saidaCnd;
	}

	public void setSaidaCnd(boolean saidaCnd) {
		this.saidaCnd = saidaCnd;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (baixado ? 1231 : 1237);
		result = prime * result + ((cnd == null) ? 0 : cnd.hashCode());
		result = prime * result + codigoCnd;
		result = prime * result + ((codigoDebito == null) ? 0 : codigoDebito.hashCode());
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		result = prime * result + ((credor == null) ? 0 : credor.hashCode());
		result = prime * result + ((dataPagto == null) ? 0 : dataPagto.hashCode());
		result = prime * result + nrolancto;
		result = prime * result + (possuiImg ? 1231 : 1237);
		result = prime * result + (saidaCnd ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorBanco);
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
		DACSV other = (DACSV) obj;
		if (baixado != other.baixado)
			return false;
		if (cnd == null) {
			if (other.cnd != null)
				return false;
		} else if (!cnd.equals(other.cnd))
			return false;
		if (codigoCnd != other.codigoCnd)
			return false;
		if (codigoDebito == null) {
			if (other.codigoDebito != null)
				return false;
		} else if (!codigoDebito.equals(other.codigoDebito))
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
		if (dataPagto == null) {
			if (other.dataPagto != null)
				return false;
		} else if (!dataPagto.equals(other.dataPagto))
			return false;
		if (nrolancto != other.nrolancto)
			return false;
		if (possuiImg != other.possuiImg)
			return false;
		if (saidaCnd != other.saidaCnd)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (Double.doubleToLongBits(valorBanco) != Double.doubleToLongBits(other.valorBanco))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}

}
