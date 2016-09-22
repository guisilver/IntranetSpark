package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_fiscal_param implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int diaPCC;
	@Column(columnDefinition = "int")
	private int mesPCC;
	@Column(columnDefinition = "int")
	private int paramPCC;
	@Column(columnDefinition = "bit")
	private boolean usarParamPCC;
	@Column(columnDefinition = "int")
	private int diaINSS;
	@Column(columnDefinition = "int")
	private int mesINSS;
	@Column(columnDefinition = "int")
	private int paramINSS;
	@Column(columnDefinition = "bit")
	private boolean usarParamINSS;
	@Column(columnDefinition = "int")
	private int diaIRRF;
	@Column(columnDefinition = "int")
	private int mesIRRF;
	@Column(columnDefinition = "int")
	private int paramIRRF;
	@Column(columnDefinition = "bit")
	private boolean usarParamIRRF;
	@Column(columnDefinition = "int")
	private int diaISS;
	@Column(columnDefinition = "int")
	private int mesISS;
	@Column(columnDefinition = "int")
	private int paramISS;
	@Column(columnDefinition = "bit")
	private boolean usarParamISS;
	@Column(columnDefinition = "int")
	private int antecedenciaPCC;
	@Column(columnDefinition = "int")
	private int antecedenciaINSS;
	@Column(columnDefinition = "int")
	private int antecedenciaIRRF;
	@Column(columnDefinition = "int")
	private int antecedenciaISS;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getDiaPCC() {
		return diaPCC;
	}

	public void setDiaPCC(int diaPCC) {
		this.diaPCC = diaPCC;
	}

	public int getMesPCC() {
		return mesPCC;
	}

	public void setMesPCC(int mesPCC) {
		this.mesPCC = mesPCC;
	}

	public int getParamPCC() {
		return paramPCC;
	}

	public void setParamPCC(int paramPCC) {
		this.paramPCC = paramPCC;
	}

	public boolean isUsarParamPCC() {
		return usarParamPCC;
	}

	public void setUsarParamPCC(boolean usarParamPCC) {
		this.usarParamPCC = usarParamPCC;
	}

	public int getDiaINSS() {
		return diaINSS;
	}

	public void setDiaINSS(int diaINSS) {
		this.diaINSS = diaINSS;
	}

	public int getMesINSS() {
		return mesINSS;
	}

	public void setMesINSS(int mesINSS) {
		this.mesINSS = mesINSS;
	}

	public int getParamINSS() {
		return paramINSS;
	}

	public void setParamINSS(int paramINSS) {
		this.paramINSS = paramINSS;
	}

	public boolean isUsarParamINSS() {
		return usarParamINSS;
	}

	public void setUsarParamINSS(boolean usarParamINSS) {
		this.usarParamINSS = usarParamINSS;
	}

	public int getDiaIRRF() {
		return diaIRRF;
	}

	public void setDiaIRRF(int diaIRRF) {
		this.diaIRRF = diaIRRF;
	}

	public int getMesIRRF() {
		return mesIRRF;
	}

	public void setMesIRRF(int mesIRRF) {
		this.mesIRRF = mesIRRF;
	}

	public int getParamIRRF() {
		return paramIRRF;
	}

	public void setParamIRRF(int paramIRRF) {
		this.paramIRRF = paramIRRF;
	}

	public boolean isUsarParamIRRF() {
		return usarParamIRRF;
	}

	public void setUsarParamIRRF(boolean usarParamIRRF) {
		this.usarParamIRRF = usarParamIRRF;
	}

	public int getDiaISS() {
		return diaISS;
	}

	public void setDiaISS(int diaISS) {
		this.diaISS = diaISS;
	}

	public int getMesISS() {
		return mesISS;
	}

	public void setMesISS(int mesISS) {
		this.mesISS = mesISS;
	}

	public int getParamISS() {
		return paramISS;
	}

	public void setParamISS(int paramISS) {
		this.paramISS = paramISS;
	}

	public boolean isUsarParamISS() {
		return usarParamISS;
	}

	public void setUsarParamISS(boolean usarParamISS) {
		this.usarParamISS = usarParamISS;
	}

	public int getAntecedenciaPCC() {
		return antecedenciaPCC;
	}

	public void setAntecedenciaPCC(int antecedenciaPCC) {
		this.antecedenciaPCC = antecedenciaPCC;
	}

	public int getAntecedenciaINSS() {
		return antecedenciaINSS;
	}

	public void setAntecedenciaINSS(int antecedenciaINSS) {
		this.antecedenciaINSS = antecedenciaINSS;
	}

	public int getAntecedenciaIRRF() {
		return antecedenciaIRRF;
	}

	public void setAntecedenciaIRRF(int antecedenciaIRRF) {
		this.antecedenciaIRRF = antecedenciaIRRF;
	}

	public int getAntecedenciaISS() {
		return antecedenciaISS;
	}

	public void setAntecedenciaISS(int antecedenciaISS) {
		this.antecedenciaISS = antecedenciaISS;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + antecedenciaINSS;
		result = prime * result + antecedenciaIRRF;
		result = prime * result + antecedenciaISS;
		result = prime * result + antecedenciaPCC;
		result = prime * result + codigo;
		result = prime * result + diaINSS;
		result = prime * result + diaIRRF;
		result = prime * result + diaISS;
		result = prime * result + diaPCC;
		result = prime * result + mesINSS;
		result = prime * result + mesIRRF;
		result = prime * result + mesISS;
		result = prime * result + mesPCC;
		result = prime * result + paramINSS;
		result = prime * result + paramIRRF;
		result = prime * result + paramISS;
		result = prime * result + paramPCC;
		result = prime * result + (usarParamINSS ? 1231 : 1237);
		result = prime * result + (usarParamIRRF ? 1231 : 1237);
		result = prime * result + (usarParamISS ? 1231 : 1237);
		result = prime * result + (usarParamPCC ? 1231 : 1237);
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
		intra_fiscal_param other = (intra_fiscal_param) obj;
		if (antecedenciaINSS != other.antecedenciaINSS)
			return false;
		if (antecedenciaIRRF != other.antecedenciaIRRF)
			return false;
		if (antecedenciaISS != other.antecedenciaISS)
			return false;
		if (antecedenciaPCC != other.antecedenciaPCC)
			return false;
		if (codigo != other.codigo)
			return false;
		if (diaINSS != other.diaINSS)
			return false;
		if (diaIRRF != other.diaIRRF)
			return false;
		if (diaISS != other.diaISS)
			return false;
		if (diaPCC != other.diaPCC)
			return false;
		if (mesINSS != other.mesINSS)
			return false;
		if (mesIRRF != other.mesIRRF)
			return false;
		if (mesISS != other.mesISS)
			return false;
		if (mesPCC != other.mesPCC)
			return false;
		if (paramINSS != other.paramINSS)
			return false;
		if (paramIRRF != other.paramIRRF)
			return false;
		if (paramISS != other.paramISS)
			return false;
		if (paramPCC != other.paramPCC)
			return false;
		if (usarParamINSS != other.usarParamINSS)
			return false;
		if (usarParamIRRF != other.usarParamIRRF)
			return false;
		if (usarParamISS != other.usarParamISS)
			return false;
		if (usarParamPCC != other.usarParamPCC)
			return false;
		return true;
	}
	

}
