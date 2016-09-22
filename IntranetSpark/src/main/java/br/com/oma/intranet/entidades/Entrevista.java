package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

public class Entrevista implements Serializable{

	private static final long serialVersionUID = 1L;

	private int codigoCandidato;
	private String nomeEntrevistado;
	private Date dataEntrevista;
	private Date horarioChegada;
	private boolean entrevistado;

	public int getCodigoCandidato() {
		return codigoCandidato;
	}

	public void setCodigoCandidato(int codigoCandidato) {
		this.codigoCandidato = codigoCandidato;
	}

	public String getNomeEntrevistado() {
		return nomeEntrevistado;
	}

	public void setNomeEntrevistado(String nomeEntrevistado) {
		this.nomeEntrevistado = nomeEntrevistado;
	}

	public Date getDataEntrevista() {
		return dataEntrevista;
	}

	public void setDataEntrevista(Date dataEntrevista) {
		this.dataEntrevista = dataEntrevista;
	}

	public Date getHorarioChegada() {
		return horarioChegada;
	}

	public void setHorarioChegada(Date horarioChegada) {
		this.horarioChegada = horarioChegada;
	}

	public boolean isEntrevistado() {
		return entrevistado;
	}

	public void setEntrevistado(boolean entrevistado) {
		this.entrevistado = entrevistado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoCandidato;
		result = prime * result + ((dataEntrevista == null) ? 0 : dataEntrevista.hashCode());
		result = prime * result + (entrevistado ? 1231 : 1237);
		result = prime * result + ((horarioChegada == null) ? 0 : horarioChegada.hashCode());
		result = prime * result + ((nomeEntrevistado == null) ? 0 : nomeEntrevistado.hashCode());
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
		Entrevista other = (Entrevista) obj;
		if (codigoCandidato != other.codigoCandidato)
			return false;
		if (dataEntrevista == null) {
			if (other.dataEntrevista != null)
				return false;
		} else if (!dataEntrevista.equals(other.dataEntrevista))
			return false;
		if (entrevistado != other.entrevistado)
			return false;
		if (horarioChegada == null) {
			if (other.horarioChegada != null)
				return false;
		} else if (!horarioChegada.equals(other.horarioChegada))
			return false;
		if (nomeEntrevistado == null) {
			if (other.nomeEntrevistado != null)
				return false;
		} else if (!nomeEntrevistado.equals(other.nomeEntrevistado))
			return false;
		return true;
	}
	
}
