package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class intra_quiz_controle_fase_geral implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int fase;
	@Column(columnDefinition = "int")
	private int qtdFases;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicioFase;

	@Transient
	private String faseData;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public int getQtdFases() {
		return qtdFases;
	}

	public void setQtdFases(int qtdFases) {
		this.qtdFases = qtdFases;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getInicioFase() {
		return inicioFase;
	}

	public void setInicioFase(Date inicioFase) {
		this.inicioFase = inicioFase;
	}

	public String getFaseData() {
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		if (this.codigo > 0) {
			this.faseData = this.codigo + " - " + sf.format(this.dataInicio);
		}
		return faseData;
	}

	public void setFaseData(String faseData) {
		this.faseData = faseData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + fase;
		result = prime * result + ((faseData == null) ? 0 : faseData.hashCode());
		result = prime * result + ((inicioFase == null) ? 0 : inicioFase.hashCode());
		result = prime * result + qtdFases;
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
		intra_quiz_controle_fase_geral other = (intra_quiz_controle_fase_geral) obj;
		if (codigo != other.codigo)
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (fase != other.fase)
			return false;
		if (faseData == null) {
			if (other.faseData != null)
				return false;
		} else if (!faseData.equals(other.faseData))
			return false;
		if (inicioFase == null) {
			if (other.inicioFase != null)
				return false;
		} else if (!inicioFase.equals(other.inicioFase))
			return false;
		if (qtdFases != other.qtdFases)
			return false;
		return true;
	}
	
}
