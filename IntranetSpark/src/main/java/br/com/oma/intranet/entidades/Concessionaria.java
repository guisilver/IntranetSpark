package br.com.oma.intranet.entidades;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Concessionaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6883814029425234600L;
	private intra_controle_concessionarias controle;
	private int codigoJan;
	private double valorJan;
	private int imgJan;
	private boolean possuiImgJan;
	private int diaJan;
	private String sitJan = "";
	private double consumoJan;

	private int codigoFev;
	private double valorFev;
	private int imgFev;
	private boolean possuiImgFev;
	private int diaFev;
	private String sitFev = "";
	private double consumoFev;

	private int codigoMar;
	private double valorMar;
	private int imgMar;
	private boolean possuiImgMar;
	private int diaMar;
	private String sitMar = "";
	private double consumoMar;

	private int codigoAbr;
	private double valorAbr;
	private int imgAbr;
	private boolean possuiImgAbr;
	private int diaAbr;
	private String sitAbr = "";
	private double consumoAbr;

	private int codigoMai;
	private double valorMai;
	private int imgMai;
	private boolean possuiImgMai;
	private int diaMai;
	private String sitMai = "";
	private double consumoMai;

	private int codigoJun;
	private double valorJun;
	private int imgJun;
	private boolean possuiImgJun;
	private int diaJun;
	private String sitJun = "";
	private double consumoJun;

	private int codigoJul;
	private double valorJul;
	private int imgJul;
	private boolean possuiImgJul;
	private int diaJul;
	private String sitJul = "";
	private double consumoJul;

	private int codigoAgo;
	private double valorAgo;
	private int imgAgo;
	private boolean possuiImgAgo;
	private int diaAgo;
	private String sitAgo = "";
	private double consumoAgo;

	private int codigoSet;
	private double valorSet;
	private int imgSet;
	private boolean possuiImgSet;
	private int diaSet;
	private String sitSet = "";
	private double consumoSet;

	private int codigoOut;
	private double valorOut;
	private int imgOut;
	private boolean possuiImgOut;
	private int diaOut;
	private String sitOut = "";
	private double consumoOut;

	private int codigoNov;
	private double valorNov;
	private int imgNov;
	private boolean possuiImgNov;
	private int diaNov;
	private String sitNov = "";
	private double consumoNov;

	private int codigoDez;
	private double valorDez;
	private int imgDez;
	private boolean possuiImgDez;
	private int diaDez;
	private String sitDez = "";
	private double consumoDez;

	public boolean possuiPendencia;

	public intra_controle_concessionarias getControle() {
		return controle;
	}

	public void setControle(intra_controle_concessionarias controle) {
		this.controle = controle;
	}

	public int getCodigoJan() {
		return codigoJan;
	}

	public void setCodigoJan(int codigoJan) {
		this.codigoJan = codigoJan;
	}

	public double getValorJan() {
		return valorJan;
	}

	public void setValorJan(double valorJan) {
		this.valorJan = valorJan;
	}

	public int getImgJan() {
		return imgJan;
	}

	public void setImgJan(int imgJan) {
		this.imgJan = imgJan;
	}

	public boolean isPossuiImgJan() {
		return possuiImgJan;
	}

	public void setPossuiImgJan(boolean possuiImgJan) {
		this.possuiImgJan = possuiImgJan;
	}

	public int getDiaJan() {
		return diaJan;
	}

	public void setDiaJan(int diaJan) {
		this.diaJan = diaJan;
	}

	public String getSitJan() {
		return sitJan;
	}

	public void setSitJan(String sitJan) {
		this.sitJan = sitJan;
	}

	public double getConsumoJan() {
		return consumoJan;
	}

	public void setConsumoJan(double consumoJan) {
		this.consumoJan = consumoJan;
	}

	public int getCodigoFev() {
		return codigoFev;
	}

	public void setCodigoFev(int codigoFev) {
		this.codigoFev = codigoFev;
	}

	public double getValorFev() {
		return valorFev;
	}

	public void setValorFev(double valorFev) {
		this.valorFev = valorFev;
	}

	public int getImgFev() {
		return imgFev;
	}

	public void setImgFev(int imgFev) {
		this.imgFev = imgFev;
	}

	public boolean isPossuiImgFev() {
		return possuiImgFev;
	}

	public void setPossuiImgFev(boolean possuiImgFev) {
		this.possuiImgFev = possuiImgFev;
	}

	public int getDiaFev() {
		return diaFev;
	}

	public void setDiaFev(int diaFev) {
		this.diaFev = diaFev;
	}

	public String getSitFev() {
		return sitFev;
	}

	public void setSitFev(String sitFev) {
		this.sitFev = sitFev;
	}

	public double getConsumoFev() {
		return consumoFev;
	}

	public void setConsumoFev(double consumoFev) {
		this.consumoFev = consumoFev;
	}

	public int getCodigoMar() {
		return codigoMar;
	}

	public void setCodigoMar(int codigoMar) {
		this.codigoMar = codigoMar;
	}

	public double getValorMar() {
		return valorMar;
	}

	public void setValorMar(double valorMar) {
		this.valorMar = valorMar;
	}

	public int getImgMar() {
		return imgMar;
	}

	public void setImgMar(int imgMar) {
		this.imgMar = imgMar;
	}

	public boolean isPossuiImgMar() {
		return possuiImgMar;
	}

	public void setPossuiImgMar(boolean possuiImgMar) {
		this.possuiImgMar = possuiImgMar;
	}

	public int getDiaMar() {
		return diaMar;
	}

	public void setDiaMar(int diaMar) {
		this.diaMar = diaMar;
	}

	public String getSitMar() {
		return sitMar;
	}

	public void setSitMar(String sitMar) {
		this.sitMar = sitMar;
	}

	public double getConsumoMar() {
		return consumoMar;
	}

	public void setConsumoMar(double consumoMar) {
		this.consumoMar = consumoMar;
	}

	public int getCodigoAbr() {
		return codigoAbr;
	}

	public void setCodigoAbr(int codigoAbr) {
		this.codigoAbr = codigoAbr;
	}

	public double getValorAbr() {
		return valorAbr;
	}

	public void setValorAbr(double valorAbr) {
		this.valorAbr = valorAbr;
	}

	public int getImgAbr() {
		return imgAbr;
	}

	public void setImgAbr(int imgAbr) {
		this.imgAbr = imgAbr;
	}

	public boolean isPossuiImgAbr() {
		return possuiImgAbr;
	}

	public void setPossuiImgAbr(boolean possuiImgAbr) {
		this.possuiImgAbr = possuiImgAbr;
	}

	public int getDiaAbr() {
		return diaAbr;
	}

	public void setDiaAbr(int diaAbr) {
		this.diaAbr = diaAbr;
	}

	public String getSitAbr() {
		return sitAbr;
	}

	public void setSitAbr(String sitAbr) {
		this.sitAbr = sitAbr;
	}

	public double getConsumoAbr() {
		return consumoAbr;
	}

	public void setConsumoAbr(double consumoAbr) {
		this.consumoAbr = consumoAbr;
	}

	public int getCodigoMai() {
		return codigoMai;
	}

	public void setCodigoMai(int codigoMai) {
		this.codigoMai = codigoMai;
	}

	public double getValorMai() {
		return valorMai;
	}

	public void setValorMai(double valorMai) {
		this.valorMai = valorMai;
	}

	public int getImgMai() {
		return imgMai;
	}

	public void setImgMai(int imgMai) {
		this.imgMai = imgMai;
	}

	public boolean isPossuiImgMai() {
		return possuiImgMai;
	}

	public void setPossuiImgMai(boolean possuiImgMai) {
		this.possuiImgMai = possuiImgMai;
	}

	public int getDiaMai() {
		return diaMai;
	}

	public void setDiaMai(int diaMai) {
		this.diaMai = diaMai;
	}

	public String getSitMai() {
		return sitMai;
	}

	public void setSitMai(String sitMai) {
		this.sitMai = sitMai;
	}

	public double getConsumoMai() {
		return consumoMai;
	}

	public void setConsumoMai(double consumoMai) {
		this.consumoMai = consumoMai;
	}

	public int getCodigoJun() {
		return codigoJun;
	}

	public void setCodigoJun(int codigoJun) {
		this.codigoJun = codigoJun;
	}

	public double getValorJun() {
		return valorJun;
	}

	public void setValorJun(double valorJun) {
		this.valorJun = valorJun;
	}

	public int getImgJun() {
		return imgJun;
	}

	public void setImgJun(int imgJun) {
		this.imgJun = imgJun;
	}

	public boolean isPossuiImgJun() {
		return possuiImgJun;
	}

	public void setPossuiImgJun(boolean possuiImgJun) {
		this.possuiImgJun = possuiImgJun;
	}

	public int getDiaJun() {
		return diaJun;
	}

	public void setDiaJun(int diaJun) {
		this.diaJun = diaJun;
	}

	public String getSitJun() {
		return sitJun;
	}

	public void setSitJun(String sitJun) {
		this.sitJun = sitJun;
	}

	public double getConsumoJun() {
		return consumoJun;
	}

	public void setConsumoJun(double consumoJun) {
		this.consumoJun = consumoJun;
	}

	public int getCodigoJul() {
		return codigoJul;
	}

	public void setCodigoJul(int codigoJul) {
		this.codigoJul = codigoJul;
	}

	public double getValorJul() {
		return valorJul;
	}

	public void setValorJul(double valorJul) {
		this.valorJul = valorJul;
	}

	public int getImgJul() {
		return imgJul;
	}

	public void setImgJul(int imgJul) {
		this.imgJul = imgJul;
	}

	public boolean isPossuiImgJul() {
		return possuiImgJul;
	}

	public void setPossuiImgJul(boolean possuiImgJul) {
		this.possuiImgJul = possuiImgJul;
	}

	public int getDiaJul() {
		return diaJul;
	}

	public void setDiaJul(int diaJul) {
		this.diaJul = diaJul;
	}

	public String getSitJul() {
		return sitJul;
	}

	public void setSitJul(String sitJul) {
		this.sitJul = sitJul;
	}

	public double getConsumoJul() {
		return consumoJul;
	}

	public void setConsumoJul(double consumoJul) {
		this.consumoJul = consumoJul;
	}

	public int getCodigoAgo() {
		return codigoAgo;
	}

	public void setCodigoAgo(int codigoAgo) {
		this.codigoAgo = codigoAgo;
	}

	public double getValorAgo() {
		return valorAgo;
	}

	public void setValorAgo(double valorAgo) {
		this.valorAgo = valorAgo;
	}

	public int getImgAgo() {
		return imgAgo;
	}

	public void setImgAgo(int imgAgo) {
		this.imgAgo = imgAgo;
	}

	public boolean isPossuiImgAgo() {
		return possuiImgAgo;
	}

	public void setPossuiImgAgo(boolean possuiImgAgo) {
		this.possuiImgAgo = possuiImgAgo;
	}

	public int getDiaAgo() {
		return diaAgo;
	}

	public void setDiaAgo(int diaAgo) {
		this.diaAgo = diaAgo;
	}

	public String getSitAgo() {
		return sitAgo;
	}

	public void setSitAgo(String sitAgo) {
		this.sitAgo = sitAgo;
	}

	public double getConsumoAgo() {
		return consumoAgo;
	}

	public void setConsumoAgo(double consumoAgo) {
		this.consumoAgo = consumoAgo;
	}

	public int getCodigoSet() {
		return codigoSet;
	}

	public void setCodigoSet(int codigoSet) {
		this.codigoSet = codigoSet;
	}

	public double getValorSet() {
		return valorSet;
	}

	public void setValorSet(double valorSet) {
		this.valorSet = valorSet;
	}

	public int getImgSet() {
		return imgSet;
	}

	public void setImgSet(int imgSet) {
		this.imgSet = imgSet;
	}

	public boolean isPossuiImgSet() {
		return possuiImgSet;
	}

	public void setPossuiImgSet(boolean possuiImgSet) {
		this.possuiImgSet = possuiImgSet;
	}

	public int getDiaSet() {
		return diaSet;
	}

	public void setDiaSet(int diaSet) {
		this.diaSet = diaSet;
	}

	public String getSitSet() {
		return sitSet;
	}

	public void setSitSet(String sitSet) {
		this.sitSet = sitSet;
	}

	public double getConsumoSet() {
		return consumoSet;
	}

	public void setConsumoSet(double consumoSet) {
		this.consumoSet = consumoSet;
	}

	public int getCodigoOut() {
		return codigoOut;
	}

	public void setCodigoOut(int codigoOut) {
		this.codigoOut = codigoOut;
	}

	public double getValorOut() {
		return valorOut;
	}

	public void setValorOut(double valorOut) {
		this.valorOut = valorOut;
	}

	public int getImgOut() {
		return imgOut;
	}

	public void setImgOut(int imgOut) {
		this.imgOut = imgOut;
	}

	public boolean isPossuiImgOut() {
		return possuiImgOut;
	}

	public void setPossuiImgOut(boolean possuiImgOut) {
		this.possuiImgOut = possuiImgOut;
	}

	public int getDiaOut() {
		return diaOut;
	}

	public void setDiaOut(int diaOut) {
		this.diaOut = diaOut;
	}

	public String getSitOut() {
		return sitOut;
	}

	public void setSitOut(String sitOut) {
		this.sitOut = sitOut;
	}

	public double getConsumoOut() {
		return consumoOut;
	}

	public void setConsumoOut(double consumoOut) {
		this.consumoOut = consumoOut;
	}

	public int getCodigoNov() {
		return codigoNov;
	}

	public void setCodigoNov(int codigoNov) {
		this.codigoNov = codigoNov;
	}

	public double getValorNov() {
		return valorNov;
	}

	public void setValorNov(double valorNov) {
		this.valorNov = valorNov;
	}

	public int getImgNov() {
		return imgNov;
	}

	public void setImgNov(int imgNov) {
		this.imgNov = imgNov;
	}

	public boolean isPossuiImgNov() {
		return possuiImgNov;
	}

	public void setPossuiImgNov(boolean possuiImgNov) {
		this.possuiImgNov = possuiImgNov;
	}

	public int getDiaNov() {
		return diaNov;
	}

	public void setDiaNov(int diaNov) {
		this.diaNov = diaNov;
	}

	public String getSitNov() {
		return sitNov;
	}

	public void setSitNov(String sitNov) {
		this.sitNov = sitNov;
	}

	public double getConsumoNov() {
		return consumoNov;
	}

	public void setConsumoNov(double consumoNov) {
		this.consumoNov = consumoNov;
	}

	public int getCodigoDez() {
		return codigoDez;
	}

	public void setCodigoDez(int codigoDez) {
		this.codigoDez = codigoDez;
	}

	public double getValorDez() {
		return valorDez;
	}

	public void setValorDez(double valorDez) {
		this.valorDez = valorDez;
	}

	public int getImgDez() {
		return imgDez;
	}

	public void setImgDez(int imgDez) {
		this.imgDez = imgDez;
	}

	public boolean isPossuiImgDez() {
		return possuiImgDez;
	}

	public void setPossuiImgDez(boolean possuiImgDez) {
		this.possuiImgDez = possuiImgDez;
	}

	public int getDiaDez() {
		return diaDez;
	}

	public void setDiaDez(int diaDez) {
		this.diaDez = diaDez;
	}

	public String getSitDez() {
		return sitDez;
	}

	public void setSitDez(String sitDez) {
		this.sitDez = sitDez;
	}

	public double getConsumoDez() {
		return consumoDez;
	}

	public void setConsumoDez(double consumoDez) {
		this.consumoDez = consumoDez;
	}

	public boolean isPossuiPendencia() {
		return possuiPendencia;
	}

	public void setPossuiPendencia(boolean possuiPendencia) {
		this.possuiPendencia = possuiPendencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoAbr;
		result = prime * result + codigoAgo;
		result = prime * result + codigoDez;
		result = prime * result + codigoFev;
		result = prime * result + codigoJan;
		result = prime * result + codigoJul;
		result = prime * result + codigoJun;
		result = prime * result + codigoMai;
		result = prime * result + codigoMar;
		result = prime * result + codigoNov;
		result = prime * result + codigoOut;
		result = prime * result + codigoSet;
		long temp;
		temp = Double.doubleToLongBits(consumoAbr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoAgo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoDez);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoFev);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoJan);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoJul);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoJun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoMai);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoMar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoNov);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoOut);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumoSet);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((controle == null) ? 0 : controle.hashCode());
		result = prime * result + diaAbr;
		result = prime * result + diaAgo;
		result = prime * result + diaDez;
		result = prime * result + diaFev;
		result = prime * result + diaJan;
		result = prime * result + diaJul;
		result = prime * result + diaJun;
		result = prime * result + diaMai;
		result = prime * result + diaMar;
		result = prime * result + diaNov;
		result = prime * result + diaOut;
		result = prime * result + diaSet;
		result = prime * result + imgAbr;
		result = prime * result + imgAgo;
		result = prime * result + imgDez;
		result = prime * result + imgFev;
		result = prime * result + imgJan;
		result = prime * result + imgJul;
		result = prime * result + imgJun;
		result = prime * result + imgMai;
		result = prime * result + imgMar;
		result = prime * result + imgNov;
		result = prime * result + imgOut;
		result = prime * result + imgSet;
		result = prime * result + (possuiImgAbr ? 1231 : 1237);
		result = prime * result + (possuiImgAgo ? 1231 : 1237);
		result = prime * result + (possuiImgDez ? 1231 : 1237);
		result = prime * result + (possuiImgFev ? 1231 : 1237);
		result = prime * result + (possuiImgJan ? 1231 : 1237);
		result = prime * result + (possuiImgJul ? 1231 : 1237);
		result = prime * result + (possuiImgJun ? 1231 : 1237);
		result = prime * result + (possuiImgMai ? 1231 : 1237);
		result = prime * result + (possuiImgMar ? 1231 : 1237);
		result = prime * result + (possuiImgNov ? 1231 : 1237);
		result = prime * result + (possuiImgOut ? 1231 : 1237);
		result = prime * result + (possuiImgSet ? 1231 : 1237);
		result = prime * result + (possuiPendencia ? 1231 : 1237);
		result = prime * result + ((sitAbr == null) ? 0 : sitAbr.hashCode());
		result = prime * result + ((sitAgo == null) ? 0 : sitAgo.hashCode());
		result = prime * result + ((sitDez == null) ? 0 : sitDez.hashCode());
		result = prime * result + ((sitFev == null) ? 0 : sitFev.hashCode());
		result = prime * result + ((sitJan == null) ? 0 : sitJan.hashCode());
		result = prime * result + ((sitJul == null) ? 0 : sitJul.hashCode());
		result = prime * result + ((sitJun == null) ? 0 : sitJun.hashCode());
		result = prime * result + ((sitMai == null) ? 0 : sitMai.hashCode());
		result = prime * result + ((sitMar == null) ? 0 : sitMar.hashCode());
		result = prime * result + ((sitNov == null) ? 0 : sitNov.hashCode());
		result = prime * result + ((sitOut == null) ? 0 : sitOut.hashCode());
		result = prime * result + ((sitSet == null) ? 0 : sitSet.hashCode());
		temp = Double.doubleToLongBits(valorAbr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorAgo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorDez);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorFev);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorJan);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorJul);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorJun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorMai);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorMar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorNov);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorOut);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorSet);
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
		Concessionaria other = (Concessionaria) obj;
		if (codigoAbr != other.codigoAbr)
			return false;
		if (codigoAgo != other.codigoAgo)
			return false;
		if (codigoDez != other.codigoDez)
			return false;
		if (codigoFev != other.codigoFev)
			return false;
		if (codigoJan != other.codigoJan)
			return false;
		if (codigoJul != other.codigoJul)
			return false;
		if (codigoJun != other.codigoJun)
			return false;
		if (codigoMai != other.codigoMai)
			return false;
		if (codigoMar != other.codigoMar)
			return false;
		if (codigoNov != other.codigoNov)
			return false;
		if (codigoOut != other.codigoOut)
			return false;
		if (codigoSet != other.codigoSet)
			return false;
		if (Double.doubleToLongBits(consumoAbr) != Double.doubleToLongBits(other.consumoAbr))
			return false;
		if (Double.doubleToLongBits(consumoAgo) != Double.doubleToLongBits(other.consumoAgo))
			return false;
		if (Double.doubleToLongBits(consumoDez) != Double.doubleToLongBits(other.consumoDez))
			return false;
		if (Double.doubleToLongBits(consumoFev) != Double.doubleToLongBits(other.consumoFev))
			return false;
		if (Double.doubleToLongBits(consumoJan) != Double.doubleToLongBits(other.consumoJan))
			return false;
		if (Double.doubleToLongBits(consumoJul) != Double.doubleToLongBits(other.consumoJul))
			return false;
		if (Double.doubleToLongBits(consumoJun) != Double.doubleToLongBits(other.consumoJun))
			return false;
		if (Double.doubleToLongBits(consumoMai) != Double.doubleToLongBits(other.consumoMai))
			return false;
		if (Double.doubleToLongBits(consumoMar) != Double.doubleToLongBits(other.consumoMar))
			return false;
		if (Double.doubleToLongBits(consumoNov) != Double.doubleToLongBits(other.consumoNov))
			return false;
		if (Double.doubleToLongBits(consumoOut) != Double.doubleToLongBits(other.consumoOut))
			return false;
		if (Double.doubleToLongBits(consumoSet) != Double.doubleToLongBits(other.consumoSet))
			return false;
		if (controle == null) {
			if (other.controle != null)
				return false;
		} else if (!controle.equals(other.controle))
			return false;
		if (diaAbr != other.diaAbr)
			return false;
		if (diaAgo != other.diaAgo)
			return false;
		if (diaDez != other.diaDez)
			return false;
		if (diaFev != other.diaFev)
			return false;
		if (diaJan != other.diaJan)
			return false;
		if (diaJul != other.diaJul)
			return false;
		if (diaJun != other.diaJun)
			return false;
		if (diaMai != other.diaMai)
			return false;
		if (diaMar != other.diaMar)
			return false;
		if (diaNov != other.diaNov)
			return false;
		if (diaOut != other.diaOut)
			return false;
		if (diaSet != other.diaSet)
			return false;
		if (imgAbr != other.imgAbr)
			return false;
		if (imgAgo != other.imgAgo)
			return false;
		if (imgDez != other.imgDez)
			return false;
		if (imgFev != other.imgFev)
			return false;
		if (imgJan != other.imgJan)
			return false;
		if (imgJul != other.imgJul)
			return false;
		if (imgJun != other.imgJun)
			return false;
		if (imgMai != other.imgMai)
			return false;
		if (imgMar != other.imgMar)
			return false;
		if (imgNov != other.imgNov)
			return false;
		if (imgOut != other.imgOut)
			return false;
		if (imgSet != other.imgSet)
			return false;
		if (possuiImgAbr != other.possuiImgAbr)
			return false;
		if (possuiImgAgo != other.possuiImgAgo)
			return false;
		if (possuiImgDez != other.possuiImgDez)
			return false;
		if (possuiImgFev != other.possuiImgFev)
			return false;
		if (possuiImgJan != other.possuiImgJan)
			return false;
		if (possuiImgJul != other.possuiImgJul)
			return false;
		if (possuiImgJun != other.possuiImgJun)
			return false;
		if (possuiImgMai != other.possuiImgMai)
			return false;
		if (possuiImgMar != other.possuiImgMar)
			return false;
		if (possuiImgNov != other.possuiImgNov)
			return false;
		if (possuiImgOut != other.possuiImgOut)
			return false;
		if (possuiImgSet != other.possuiImgSet)
			return false;
		if (possuiPendencia != other.possuiPendencia)
			return false;
		if (sitAbr == null) {
			if (other.sitAbr != null)
				return false;
		} else if (!sitAbr.equals(other.sitAbr))
			return false;
		if (sitAgo == null) {
			if (other.sitAgo != null)
				return false;
		} else if (!sitAgo.equals(other.sitAgo))
			return false;
		if (sitDez == null) {
			if (other.sitDez != null)
				return false;
		} else if (!sitDez.equals(other.sitDez))
			return false;
		if (sitFev == null) {
			if (other.sitFev != null)
				return false;
		} else if (!sitFev.equals(other.sitFev))
			return false;
		if (sitJan == null) {
			if (other.sitJan != null)
				return false;
		} else if (!sitJan.equals(other.sitJan))
			return false;
		if (sitJul == null) {
			if (other.sitJul != null)
				return false;
		} else if (!sitJul.equals(other.sitJul))
			return false;
		if (sitJun == null) {
			if (other.sitJun != null)
				return false;
		} else if (!sitJun.equals(other.sitJun))
			return false;
		if (sitMai == null) {
			if (other.sitMai != null)
				return false;
		} else if (!sitMai.equals(other.sitMai))
			return false;
		if (sitMar == null) {
			if (other.sitMar != null)
				return false;
		} else if (!sitMar.equals(other.sitMar))
			return false;
		if (sitNov == null) {
			if (other.sitNov != null)
				return false;
		} else if (!sitNov.equals(other.sitNov))
			return false;
		if (sitOut == null) {
			if (other.sitOut != null)
				return false;
		} else if (!sitOut.equals(other.sitOut))
			return false;
		if (sitSet == null) {
			if (other.sitSet != null)
				return false;
		} else if (!sitSet.equals(other.sitSet))
			return false;
		if (Double.doubleToLongBits(valorAbr) != Double.doubleToLongBits(other.valorAbr))
			return false;
		if (Double.doubleToLongBits(valorAgo) != Double.doubleToLongBits(other.valorAgo))
			return false;
		if (Double.doubleToLongBits(valorDez) != Double.doubleToLongBits(other.valorDez))
			return false;
		if (Double.doubleToLongBits(valorFev) != Double.doubleToLongBits(other.valorFev))
			return false;
		if (Double.doubleToLongBits(valorJan) != Double.doubleToLongBits(other.valorJan))
			return false;
		if (Double.doubleToLongBits(valorJul) != Double.doubleToLongBits(other.valorJul))
			return false;
		if (Double.doubleToLongBits(valorJun) != Double.doubleToLongBits(other.valorJun))
			return false;
		if (Double.doubleToLongBits(valorMai) != Double.doubleToLongBits(other.valorMai))
			return false;
		if (Double.doubleToLongBits(valorMar) != Double.doubleToLongBits(other.valorMar))
			return false;
		if (Double.doubleToLongBits(valorNov) != Double.doubleToLongBits(other.valorNov))
			return false;
		if (Double.doubleToLongBits(valorOut) != Double.doubleToLongBits(other.valorOut))
			return false;
		if (Double.doubleToLongBits(valorSet) != Double.doubleToLongBits(other.valorSet))
			return false;
		return true;
	}

	public void populaPendencia() {
		DateTime data = new DateTime().withMillisOfDay(0);
		this.possuiPendencia = false;
		if (this.diaJan > 0) {
			if (data.withMonthOfYear(1).withDayOfMonth(this.diaJan).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(1).withDayOfMonth(this.diaJan).isBefore(data.plusDays(10))
					&& !this.possuiImgJan) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaFev > 0) {
			if (data.withMonthOfYear(2).withDayOfMonth(this.diaFev).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(2).withDayOfMonth(this.diaFev).isBefore(data.plusDays(10))
					&& !this.possuiImgFev) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaMar > 0) {
			if (data.withMonthOfYear(3).withDayOfMonth(this.diaMar).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(3).withDayOfMonth(this.diaMar).isBefore(data.plusDays(10))
					&& !this.possuiImgMar) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaAbr > 0) {
			if (data.withMonthOfYear(4).withDayOfMonth(this.diaAbr).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(4).withDayOfMonth(this.diaAbr).isBefore(data.plusDays(10))
					&& !this.possuiImgAbr) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaMai > 0) {
			if (data.withMonthOfYear(5).withDayOfMonth(this.diaMai).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(5).withDayOfMonth(this.diaMai).isBefore(data.plusDays(10))
					&& !this.possuiImgMai) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaJun > 0) {
			if (data.withMonthOfYear(6).withDayOfMonth(this.diaJun).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(6).withDayOfMonth(this.diaJun).isBefore(data.plusDays(10))
					&& !this.possuiImgJun) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaJul > 0) {
			if (data.withMonthOfYear(7).withDayOfMonth(this.diaJul).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(7).withDayOfMonth(this.diaJul).isBefore(data.plusDays(10))
					&& !this.possuiImgJul) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaAgo > 0) {
			if (data.withMonthOfYear(8).withDayOfMonth(this.diaAgo).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(8).withDayOfMonth(this.diaAgo).isBefore(data.plusDays(10))
					&& !this.possuiImgAgo) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaSet > 0) {
			if (data.withMonthOfYear(9).withDayOfMonth(this.diaSet).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(9).withDayOfMonth(this.diaSet).isBefore(data.plusDays(10))
					&& !this.possuiImgSet) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaOut > 0) {
			if (data.withMonthOfYear(10).withDayOfMonth(this.diaOut).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(10).withDayOfMonth(this.diaOut).isBefore(data.plusDays(10))
					&& !this.possuiImgOut) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaNov > 0) {
			if (data.withMonthOfYear(11).withDayOfMonth(this.diaNov).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(11).withDayOfMonth(this.diaNov).isBefore(data.plusDays(10))
					&& !this.possuiImgNov) {
				this.possuiPendencia = true;
			}
		}

		if (this.diaDez > 0) {
			if (data.withMonthOfYear(12).withDayOfMonth(this.diaDez).isAfter(data.minusDays(1))
					&& data.withMonthOfYear(12).withDayOfMonth(this.diaDez).isBefore(data.plusDays(10))
					&& !this.possuiImgDez) {
				this.possuiPendencia = true;
			}
		}
	}

}
