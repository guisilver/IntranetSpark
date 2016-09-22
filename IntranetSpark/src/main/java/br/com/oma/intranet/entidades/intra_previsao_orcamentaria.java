package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_previsao_orcamentaria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(columnDefinition="bigint")
	private int codigo;
	
	@Column(columnDefinition="smallint")
	private int condominio;
	
	@Column(name="codigo_gerente", columnDefinition="int")
	private int codigoGerente;
	
	@Column(columnDefinition="varchar(100)")
	private String despesas;
	
	@Column(columnDefinition="varchar(50)")
	private String capa;
	
	@Column(columnDefinition="int")
	private int conta;
	
	@Column(name="codigo_despesa",columnDefinition="varchar(10)")
	private String codigoDespesa;
	
	@Column(columnDefinition="numeric(25,2)")
	private double media;
	
	@Column(columnDefinition="numeric(25,2)")
	private double inadimplencia;
	
	@Column(name="mes_janeiro", columnDefinition="numeric(25,2)")
	private double mesJaneiro;
	@Column(name="mes_fevereiro", columnDefinition="numeric(25,2)")
	private double mesFevereiro;
	@Column(name="mes_marco", columnDefinition="numeric(25,2)")
	private double mesMarco;
	@Column(name="mes_abril", columnDefinition="numeric(25,2)")
	private double mesAbril;
	@Column(name="mes_maio", columnDefinition="numeric(25,2)")
	private double mesMaio;
	@Column(name="mes_junho", columnDefinition="numeric(25,2)")
	private double mesJunho;
	@Column(name="mes_julho", columnDefinition="numeric(25,2)")
	private double mesJulho;
	@Column(name="mes_agosto", columnDefinition="numeric(25,2)")
	private double mesAgosto;
	@Column(name="mes_setembro", columnDefinition="numeric(25,2)")
	private double mesSetembro;
	@Column(name="mes_outubro", columnDefinition="numeric(25,2)")
	private double mesOutubro;
	@Column(name="mes_novembro", columnDefinition="numeric(25,2)")
	private double mesNovembro;
	@Column(name="mes_dezembro", columnDefinition="numeric(25,2)")
	private double mesDezembro;
	
	@Column(name="mes_projecao", columnDefinition="datetime")
	private Date mesProjecao;
	
	@Column(name="meses_projecao", columnDefinition="int")
	private int mesesProjecao;
	
	public intra_previsao_orcamentaria() {
	
	}
	
	public intra_previsao_orcamentaria(int condominio, String despesas, 
			double mes1,double mes2,double mes3,double mes4,double mes5,double mes6,
			double mes7,double mes8,double mes9,double mes10,double mes11,double mes12, double media) {
		this.condominio = condominio;
		this.despesas = despesas;
		this.mesJaneiro = mes1;
		this.mesFevereiro = mes2;
		this.mesMarco = mes3;
		this.mesAbril = mes4;
		this.mesMaio = mes5;
		this.mesJunho = mes6;
		this.mesJulho = mes7;
		this.mesAgosto = mes8;
		this.mesSetembro = mes9;
		this.mesOutubro = mes10;
		this.mesNovembro = mes11;
		this.mesDezembro = mes12;
		this.media = media;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public int getCodigoGerente() {
		return codigoGerente;
	}

	public void setCodigoGerente(int codigoGerente) {
		this.codigoGerente = codigoGerente;
	}

	public String getDespesas() {
		return despesas;
	}

	public void setDespesas(String despesas) {
		this.despesas = despesas;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double getMesJaneiro() {
		return mesJaneiro;
	}

	public void setMesJaneiro(double mesJaneiro) {
		this.mesJaneiro = mesJaneiro;
	}

	public double getMesFevereiro() {
		return mesFevereiro;
	}

	public void setMesFevereiro(double mesFevereiro) {
		this.mesFevereiro = mesFevereiro;
	}

	public double getMesMarco() {
		return mesMarco;
	}

	public void setMesMarco(double mesMarco) {
		this.mesMarco = mesMarco;
	}

	public double getMesAbril() {
		return mesAbril;
	}

	public void setMesAbril(double mesAbril) {
		this.mesAbril = mesAbril;
	}

	public double getMesMaio() {
		return mesMaio;
	}

	public void setMesMaio(double mesMaio) {
		this.mesMaio = mesMaio;
	}

	public double getMesJunho() {
		return mesJunho;
	}

	public void setMesJunho(double mesJunho) {
		this.mesJunho = mesJunho;
	}

	public double getMesJulho() {
		return mesJulho;
	}

	public void setMesJulho(double mesJulho) {
		this.mesJulho = mesJulho;
	}

	public double getMesAgosto() {
		return mesAgosto;
	}

	public void setMesAgosto(double mesAgosto) {
		this.mesAgosto = mesAgosto;
	}

	public double getMesSetembro() {
		return mesSetembro;
	}

	public void setMesSetembro(double mesSetembro) {
		this.mesSetembro = mesSetembro;
	}

	public double getMesOutubro() {
		return mesOutubro;
	}

	public void setMesOutubro(double mesOutubro) {
		this.mesOutubro = mesOutubro;
	}

	public double getMesNovembro() {
		return mesNovembro;
	}

	public void setMesNovembro(double mesNovembro) {
		this.mesNovembro = mesNovembro;
	}

	public double getMesDezembro() {
		return mesDezembro;
	}

	public void setMesDezembro(double mesDezembro) {
		this.mesDezembro = mesDezembro;
	}

	public Date getMesProjecao() {
		return mesProjecao;
	}

	public void setMesProjecao(Date mesProjecao) {
		this.mesProjecao = mesProjecao;
	}
	
	public String getCodigoDespesa() {
		return codigoDespesa;
	}

	public void setCodigoDespesa(String codigoDespesa) {
		this.codigoDespesa = codigoDespesa;
	}

	public double getInadimplencia() {
		return inadimplencia;
	}

	public void setInadimplencia(double inadimplencia) {
		this.inadimplencia = inadimplencia;
	}

	public int getMesesProjecao() {
		return mesesProjecao;
	}

	public void setMesesProjecao(int mesesProjecao) {
		this.mesesProjecao = mesesProjecao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capa == null) ? 0 : capa.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((codigoDespesa == null) ? 0 : codigoDespesa.hashCode());
		result = prime * result + codigoGerente;
		result = prime * result + condominio;
		result = prime * result + conta;
		result = prime * result + ((despesas == null) ? 0 : despesas.hashCode());
		long temp;
		temp = Double.doubleToLongBits(inadimplencia);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(media);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesAbril);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesAgosto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesDezembro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesFevereiro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesJaneiro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesJulho);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesJunho);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesMaio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesMarco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesNovembro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mesOutubro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((mesProjecao == null) ? 0 : mesProjecao.hashCode());
		temp = Double.doubleToLongBits(mesSetembro);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + mesesProjecao;
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
		intra_previsao_orcamentaria other = (intra_previsao_orcamentaria) obj;
		if (capa == null) {
			if (other.capa != null)
				return false;
		} else if (!capa.equals(other.capa))
			return false;
		if (codigo != other.codigo)
			return false;
		if (codigoDespesa == null) {
			if (other.codigoDespesa != null)
				return false;
		} else if (!codigoDespesa.equals(other.codigoDespesa))
			return false;
		if (codigoGerente != other.codigoGerente)
			return false;
		if (condominio != other.condominio)
			return false;
		if (conta != other.conta)
			return false;
		if (despesas == null) {
			if (other.despesas != null)
				return false;
		} else if (!despesas.equals(other.despesas))
			return false;
		if (Double.doubleToLongBits(inadimplencia) != Double.doubleToLongBits(other.inadimplencia))
			return false;
		if (Double.doubleToLongBits(media) != Double.doubleToLongBits(other.media))
			return false;
		if (Double.doubleToLongBits(mesAbril) != Double.doubleToLongBits(other.mesAbril))
			return false;
		if (Double.doubleToLongBits(mesAgosto) != Double.doubleToLongBits(other.mesAgosto))
			return false;
		if (Double.doubleToLongBits(mesDezembro) != Double.doubleToLongBits(other.mesDezembro))
			return false;
		if (Double.doubleToLongBits(mesFevereiro) != Double.doubleToLongBits(other.mesFevereiro))
			return false;
		if (Double.doubleToLongBits(mesJaneiro) != Double.doubleToLongBits(other.mesJaneiro))
			return false;
		if (Double.doubleToLongBits(mesJulho) != Double.doubleToLongBits(other.mesJulho))
			return false;
		if (Double.doubleToLongBits(mesJunho) != Double.doubleToLongBits(other.mesJunho))
			return false;
		if (Double.doubleToLongBits(mesMaio) != Double.doubleToLongBits(other.mesMaio))
			return false;
		if (Double.doubleToLongBits(mesMarco) != Double.doubleToLongBits(other.mesMarco))
			return false;
		if (Double.doubleToLongBits(mesNovembro) != Double.doubleToLongBits(other.mesNovembro))
			return false;
		if (Double.doubleToLongBits(mesOutubro) != Double.doubleToLongBits(other.mesOutubro))
			return false;
		if (mesProjecao == null) {
			if (other.mesProjecao != null)
				return false;
		} else if (!mesProjecao.equals(other.mesProjecao))
			return false;
		if (Double.doubleToLongBits(mesSetembro) != Double.doubleToLongBits(other.mesSetembro))
			return false;
		if (mesesProjecao != other.mesesProjecao)
			return false;
		return true;
	}
	
	
	
	
}
