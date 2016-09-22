package br.com.oma.intranet.view;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Impostos {

	private boolean usarPCC, usarINSS, usarIRRF, usarISS;
	// private double PCC = 10.0, INSS = 3.5, IRRF = 5.0, ISS = 8.0;
	private double PCC, INSS, IRRF, ISS;
	private double basePCC, baseINSS, baseIRRF, baseISS;
	private double valorPCC, valorINSS, valorIRRF, valorISS;
	private Date vencPCC, vencINSS, vencIRRF, vencISS;
	private double valorBruto;
	private double valorLiquido;

	public boolean isUsarPCC() {
		return usarPCC;
	}

	public void setUsarPCC(boolean usarPCC) {
		this.usarPCC = usarPCC;
	}

	public boolean isUsarINSS() {
		return usarINSS;
	}

	public void setUsarINSS(boolean usarINSS) {
		this.usarINSS = usarINSS;
	}

	public boolean isUsarIRRF() {
		return usarIRRF;
	}

	public void setUsarIRRF(boolean usarIRRF) {
		this.usarIRRF = usarIRRF;
	}

	public boolean isUsarISS() {
		return usarISS;
	}

	public void setUsarISS(boolean usarISS) {
		this.usarISS = usarISS;
	}

	public double getPCC() {
		return PCC;
	}

	public void setPCC(double pCC) {
		PCC = pCC;
	}

	public double getINSS() {
		return INSS;
	}

	public void setINSS(double iNSS) {
		INSS = iNSS;
	}

	public double getIRRF() {
		return IRRF;
	}

	public void setIRRF(double iRRF) {
		IRRF = iRRF;
	}

	public double getISS() {
		return ISS;
	}

	public void setISS(double iSS) {
		ISS = iSS;
	}

	public double getBasePCC() {
		return basePCC;
	}

	public void setBasePCC(double basePCC) {
		this.basePCC = basePCC;
	}

	public double getBaseINSS() {
		return baseINSS;
	}

	public void setBaseINSS(double baseINSS) {
		this.baseINSS = baseINSS;
	}

	public double getBaseIRRF() {
		return baseIRRF;
	}

	public void setBaseIRRF(double baseIRRF) {
		this.baseIRRF = baseIRRF;
	}

	public double getBaseISS() {
		return baseISS;
	}

	public void setBaseISS(double baseISS) {
		this.baseISS = baseISS;
	}

	public double getValorPCC() {
		return valorPCC;
	}

	public void setValorPCC(double valorPCC) {
		this.valorPCC = valorPCC;
	}

	public double getValorINSS() {
		return valorINSS;
	}

	public void setValorINSS(double valorINSS) {
		this.valorINSS = valorINSS;
	}

	public double getValorIRRF() {
		return valorIRRF;
	}

	public void setValorIRRF(double valorIRRF) {
		this.valorIRRF = valorIRRF;
	}

	public double getValorISS() {
		return valorISS;
	}

	public void setValorISS(double valorISS) {
		this.valorISS = valorISS;
	}

	public Date getVencPCC() {
		return vencPCC;
	}

	public void setVencPCC(Date vencPCC) {
		this.vencPCC = vencPCC;
	}

	public Date getVencINSS() {
		return vencINSS;
	}

	public void setVencINSS(Date vencINSS) {
		this.vencINSS = vencINSS;
	}

	public Date getVencIRRF() {
		return vencIRRF;
	}

	public void setVencIRRF(Date vencIRRF) {
		this.vencIRRF = vencIRRF;
	}

	public Date getVencISS() {
		return vencISS;
	}

	public void setVencISS(Date vencISS) {
		this.vencISS = vencISS;
	}

	public double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public void calculaImpostos(double valor) {
		this.setBasePCC(valor);
		this.setBaseINSS(valor);
		this.setBaseIRRF(valor);
		this.setBaseISS(valor);
		this.valorBruto = valor;
		this.calculaValorImpostos();
	}

	public void calculaValorImpostos() {
		this.valorLiquido = this.valorBruto;
		this.setValorPCC((this.getBasePCC() / 100) * this.PCC);
		this.setValorINSS((this.getBaseINSS() / 100) * this.getINSS());
		this.setValorIRRF((this.getBaseIRRF() / 100) * this.getIRRF());
		this.setValorISS((this.getBaseISS() / 100) * this.getISS());
	}

	public void calculaValorLiquido() {
		this.valorLiquido = this.valorBruto;
		this.calculaPCC();
		this.calculaINSS();
		this.calculaIRRF();
		this.calculaISS();
	}

	public void trataAliq() {
		this.calculaValorImpostos();
		this.calculaValorLiquido();
	}

	public void trataValor() {
		this.PCC = (this.valorPCC * 100) / this.basePCC;
		this.INSS = (this.valorINSS * 100) / this.baseINSS;
		this.IRRF = (this.valorIRRF * 100) / this.baseIRRF;
		this.ISS = (this.valorISS * 100) / this.baseISS;
		this.calculaValorLiquido();
	}

	public void calculaPCC() {
		try {
			if (this.isUsarPCC()) {
				this.valorLiquido = this.valorLiquido - this.getValorPCC();
			}
			this.validaValor();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void calculaINSS() {
		try {
			if (this.isUsarINSS()) {
				this.valorLiquido = this.valorLiquido - this.getValorINSS();
			}
			this.validaValor();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void calculaIRRF() {
		try {
			if (this.isUsarIRRF()) {
				this.valorLiquido = this.valorLiquido - this.getValorIRRF();
			}
			this.validaValor();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void calculaISS() {
		try {
			if (this.isUsarISS()) {
				this.valorLiquido = this.valorLiquido - this.getValorISS();
			}
			this.validaValor();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void validaValor() throws Exception {
		if (this.valorLiquido < 0) {
			this.valorLiquido = 0;
			throw new Exception("O valor líquido não pode ser menor do que 0!");
		}
	}

}
