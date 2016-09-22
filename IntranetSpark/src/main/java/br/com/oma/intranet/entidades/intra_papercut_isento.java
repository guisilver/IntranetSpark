package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.thoughtworks.xstream.XStream;

@Entity
public class intra_papercut_isento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="smallint")
	private int condominio;
	
	private boolean geral;
	
	private boolean ata;
	
	private boolean balancete;
	
	private boolean convocacao;
	
	@Column(name="impresao_colorida")
	private boolean impressaoColorida;
	
	private boolean circulares;
	
	@Column(name="convencao_regulamento")
	private boolean convencaoRegulamento;

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public boolean isGeral() {
		return geral;
	}

	public void setGeral(boolean geral) {
		this.geral = geral;
	}

	public boolean isAta() {
		return ata;
	}

	public void setAta(boolean ata) {
		this.ata = ata;
	}

	public boolean isBalancete() {
		return balancete;
	}

	public void setBalancete(boolean balancete) {
		this.balancete = balancete;
	}

	public boolean isConvocacao() {
		return convocacao;
	}

	public void setConvocacao(boolean convocacao) {
		this.convocacao = convocacao;
	}

	public boolean isImpressaoColorida() {
		return impressaoColorida;
	}

	public void setImpressaoColorida(boolean impressaoColorida) {
		this.impressaoColorida = impressaoColorida;
	}

	public boolean isCirculares() {
		return circulares;
	}

	public void setCirculares(boolean circulares) {
		this.circulares = circulares;
	}

	public boolean isConvencaoRegulamento() {
		return convencaoRegulamento;
	}

	public void setConvencaoRegulamento(boolean convencaoRegulamento) {
		this.convencaoRegulamento = convencaoRegulamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ata ? 1231 : 1237);
		result = prime * result + (balancete ? 1231 : 1237);
		result = prime * result + (circulares ? 1231 : 1237);
		result = prime * result + condominio;
		result = prime * result + (convencaoRegulamento ? 1231 : 1237);
		result = prime * result + (convocacao ? 1231 : 1237);
		result = prime * result + (geral ? 1231 : 1237);
		result = prime * result + (impressaoColorida ? 1231 : 1237);
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
		intra_papercut_isento other = (intra_papercut_isento) obj;
		if (ata != other.ata)
			return false;
		if (balancete != other.balancete)
			return false;
		if (circulares != other.circulares)
			return false;
		if (condominio != other.condominio)
			return false;
		if (convencaoRegulamento != other.convencaoRegulamento)
			return false;
		if (convocacao != other.convocacao)
			return false;
		if (geral != other.geral)
			return false;
		if (impressaoColorida != other.impressaoColorida)
			return false;
		return true;
	}

	@Override
	public String toString() {
		XStream xstream = new XStream();
		return xstream.toXML(this);
	}
	
}
