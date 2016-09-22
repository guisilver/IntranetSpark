package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import javax.persistence.Column;

public class intra_cndconsu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int numero;
	@Column(name = "condominio", columnDefinition = "smallint")
	private int condominio;
	@Column(name = "bloco", columnDefinition = "varchar(4)")
	private String bloco;
	@Column(name = "unidade", columnDefinition = "varchar(6)")
	private String unidade;
	@Column(name = "mes", columnDefinition = "tinyint")
	private int mes;
	@Column(name = "ano", columnDefinition = "smallint")
	private int ano;
	@Column(name = "anterior", columnDefinition = "numeric(12,4)")
	private double anterior;
	@Column(name = "atual", columnDefinition = "numeric(12,4)")
	private double atual;
	@Column(name = "consumo_orig", columnDefinition = "numeric(12,4)")
	private double consumo_orig;
	@Column(name = "consumo_conv", columnDefinition = "numeric(12,4)")
	private double consumo_conv;
	@Column(name = "media", columnDefinition = "numeric(12,4)")
	private double media;
	@Column(name = "cobrar_media", columnDefinition = "char(1)")
	private String cobrar_media;
	@Column(name = "valor", columnDefinition = "numeric(10,2)")
	private double valor;
	@Column(name = "dfrecnum", columnDefinition = "int")
	private int dfrecnum;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getCondominio() {
		return condominio;
	}
	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public double getAnterior() {
		return anterior;
	}
	public void setAnterior(double anterior) {
		this.anterior = anterior;
	}
	public double getAtual() {
		return atual;
	}
	public void setAtual(double atual) {
		this.atual = atual;
	}
	public double getConsumo_orig() {
		return consumo_orig;
	}
	public void setConsumo_orig(double consumo_orig) {
		this.consumo_orig = consumo_orig;
	}
	public double getConsumo_conv() {
		return consumo_conv;
	}
	public void setConsumo_conv(double consumo_conv) {
		this.consumo_conv = consumo_conv;
	}
	public double getMedia() {
		return media;
	}
	public void setMedia(double media) {
		this.media = media;
	}
	public String getCobrar_media() {
		return cobrar_media;
	}
	public void setCobrar_media(String cobrar_media) {
		this.cobrar_media = cobrar_media;
	}
	public int getDfrecnum() {
		return dfrecnum;
	}
	public void setDfrecnum(int dfrecnum) {
		this.dfrecnum = dfrecnum;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		long temp;
		temp = Double.doubleToLongBits(anterior);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(atual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((cobrar_media == null) ? 0 : cobrar_media.hashCode());
		result = prime * result + condominio;
		temp = Double.doubleToLongBits(consumo_conv);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumo_orig);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + dfrecnum;
		temp = Double.doubleToLongBits(media);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + mes;
		result = prime * result + numero;
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		temp = Double.doubleToLongBits(valor);
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
		intra_cndconsu other = (intra_cndconsu) obj;
		if (ano != other.ano)
			return false;
		if (Double.doubleToLongBits(anterior) != Double.doubleToLongBits(other.anterior))
			return false;
		if (Double.doubleToLongBits(atual) != Double.doubleToLongBits(other.atual))
			return false;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (cobrar_media == null) {
			if (other.cobrar_media != null)
				return false;
		} else if (!cobrar_media.equals(other.cobrar_media))
			return false;
		if (condominio != other.condominio)
			return false;
		if (Double.doubleToLongBits(consumo_conv) != Double.doubleToLongBits(other.consumo_conv))
			return false;
		if (Double.doubleToLongBits(consumo_orig) != Double.doubleToLongBits(other.consumo_orig))
			return false;
		if (dfrecnum != other.dfrecnum)
			return false;
		if (Double.doubleToLongBits(media) != Double.doubleToLongBits(other.media))
			return false;
		if (mes != other.mes)
			return false;
		if (numero != other.numero)
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}

}
