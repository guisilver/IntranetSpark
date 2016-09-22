package br.com.oma.intranet.entidades;

import java.io.Serializable;

public class TaxaInadimplencia implements Serializable{

	private static final long serialVersionUID = 1L;

	private int codigoCondominio;
	private int anoVencimento;
	private int mesVencimento;
	private double recibo;
	private double reciboValor;
	private double reciboPago;
	private double reciboValorPago;
	private double inadimplenciaQtd;
	private double inadimplenciaValor;
	private double porcentagemValor;
	private double porcentagemQtd;

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public int getAnoVencimento() {
		return anoVencimento;
	}

	public void setAnoVencimento(int anoVencimento) {
		this.anoVencimento = anoVencimento;
	}

	public int getMesVencimento() {
		return mesVencimento;
	}

	public void setMesVencimento(int mesVencimento) {
		this.mesVencimento = mesVencimento;
	}

	public double getRecibo() {
		return recibo;
	}

	public void setRecibo(double recibo) {
		this.recibo = recibo;
	}

	public double getReciboValor() {
		return reciboValor;
	}

	public void setReciboValor(double reciboValor) {
		this.reciboValor = reciboValor;
	}

	public double getReciboPago() {
		return reciboPago;
	}

	public void setReciboPago(double reciboPago) {
		this.reciboPago = reciboPago;
	}

	public double getReciboValorPago() {
		return reciboValorPago;
	}

	public void setReciboValorPago(double reciboValorPago) {
		this.reciboValorPago = reciboValorPago;
	}

	public double getInadimplenciaQtd() {
		return inadimplenciaQtd;
	}

	public void setInadimplenciaQtd(double inadimplenciaQtd) {
		this.inadimplenciaQtd = inadimplenciaQtd;
	}

	public double getInadimplenciaValor() {
		return inadimplenciaValor;
	}

	public void setInadimplenciaValor(double inadimplenciaValor) {
		this.inadimplenciaValor = inadimplenciaValor;
	}

	public double getPorcentagemValor() {
		return porcentagemValor;
	}

	public void setPorcentagemValor(double porcentagemValor) {
		this.porcentagemValor = porcentagemValor;
	}

	public double getPorcentagemQtd() {
		return porcentagemQtd;
	}

	public void setPorcentagemQtd(double porcentagemQtd) {
		this.porcentagemQtd = porcentagemQtd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anoVencimento;
		result = prime * result + codigoCondominio;
		long temp;
		temp = Double.doubleToLongBits(inadimplenciaQtd);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(inadimplenciaValor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + mesVencimento;
		temp = Double.doubleToLongBits(porcentagemQtd);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(porcentagemValor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(recibo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(reciboPago);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(reciboValor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(reciboValorPago);
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
		TaxaInadimplencia other = (TaxaInadimplencia) obj;
		if (anoVencimento != other.anoVencimento)
			return false;
		if (codigoCondominio != other.codigoCondominio)
			return false;
		if (Double.doubleToLongBits(inadimplenciaQtd) != Double.doubleToLongBits(other.inadimplenciaQtd))
			return false;
		if (Double.doubleToLongBits(inadimplenciaValor) != Double.doubleToLongBits(other.inadimplenciaValor))
			return false;
		if (mesVencimento != other.mesVencimento)
			return false;
		if (Double.doubleToLongBits(porcentagemQtd) != Double.doubleToLongBits(other.porcentagemQtd))
			return false;
		if (Double.doubleToLongBits(porcentagemValor) != Double.doubleToLongBits(other.porcentagemValor))
			return false;
		if (Double.doubleToLongBits(recibo) != Double.doubleToLongBits(other.recibo))
			return false;
		if (Double.doubleToLongBits(reciboPago) != Double.doubleToLongBits(other.reciboPago))
			return false;
		if (Double.doubleToLongBits(reciboValor) != Double.doubleToLongBits(other.reciboValor))
			return false;
		if (Double.doubleToLongBits(reciboValorPago) != Double.doubleToLongBits(other.reciboValorPago))
			return false;
		return true;
	}
	

}