package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;


public class intra_procuracao_juridico implements Serializable{


	private static final long serialVersionUID = 1L;

	private int codigoCondominio;
	@Column(columnDefinition = "varchar(75)")
	private String nomeCondominio;
	@Column(columnDefinition = "varchar(5)")
	private String bloco;
	@Column(columnDefinition = "varchar(8)")
	private String unidade;
	private int codigoGerente;
	@Column(columnDefinition = "varchar(75)")
	private String nomeGerente;
	private int recibo;
	private int emissao;
	@Column(name="valor_recibo",columnDefinition="numeric(12,2")
	private double valor_recibo;
	@Column(name="data_recbto",columnDefinition="datetime")
	private Date data_recbto;
	@Column(name="vencto",columnDefinition="datetime")
	private Date vencto;
	@Column(name="juridico",columnDefinition="char(1)")
	private String juridico;
	private char flag_situacao;

	
	public int getCodigoCondominio() {
		return codigoCondominio;
	}
	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}
	public String getNomeCondominio() {
		return nomeCondominio;
	}
	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
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
	public int getCodigoGerente() {
		return codigoGerente;
	}
	public void setCodigoGerente(int codigoGerente) {
		this.codigoGerente = codigoGerente;
	}
	public String getNomeGerente() {
		return nomeGerente;
	}
	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}
	public int getRecibo() {
		return recibo;
	}
	public void setRecibo(int recibo) {
		this.recibo = recibo;
	}
	public int getEmissao() {
		return emissao;
	}
	public void setEmissao(int emissao) {
		this.emissao = emissao;
	}
	public double getValor_recibo() {
		return valor_recibo;
	}
	public void setValor_recibo(double valor_recibo) {
		this.valor_recibo = valor_recibo;
	}
	public Date getData_recbto() {
		return data_recbto;
	}
	public void setData_recbto(Date data_recbto) {
		this.data_recbto = data_recbto;
	}
	public Date getVencto() {
		return vencto;
	}
	public void setVencto(Date vencto) {
		this.vencto = vencto;
	}
	public String getJuridico() {
		return juridico;
	}
	public void setJuridico(String juridico) {
		this.juridico = juridico;
	}
	public char getFlag_situacao() {
		return flag_situacao;
	}
	public void setFlag_situacao(char c) {
		this.flag_situacao = c;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + codigoCondominio;
		result = prime * result + codigoGerente;
		result = prime * result + ((data_recbto == null) ? 0 : data_recbto.hashCode());
		result = prime * result + emissao;
		result = prime * result + flag_situacao;
		result = prime * result + ((juridico == null) ? 0 : juridico.hashCode());
		result = prime * result + ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result + ((nomeGerente == null) ? 0 : nomeGerente.hashCode());
		result = prime * result + recibo;
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor_recibo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vencto == null) ? 0 : vencto.hashCode());
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
		intra_procuracao_juridico other = (intra_procuracao_juridico) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (codigoCondominio != other.codigoCondominio)
			return false;
		if (codigoGerente != other.codigoGerente)
			return false;
		if (data_recbto == null) {
			if (other.data_recbto != null)
				return false;
		} else if (!data_recbto.equals(other.data_recbto))
			return false;
		if (emissao != other.emissao)
			return false;
		if (flag_situacao != other.flag_situacao)
			return false;
		if (juridico == null) {
			if (other.juridico != null)
				return false;
		} else if (!juridico.equals(other.juridico))
			return false;
		if (nomeCondominio == null) {
			if (other.nomeCondominio != null)
				return false;
		} else if (!nomeCondominio.equals(other.nomeCondominio))
			return false;
		if (nomeGerente == null) {
			if (other.nomeGerente != null)
				return false;
		} else if (!nomeGerente.equals(other.nomeGerente))
			return false;
		if (recibo != other.recibo)
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (Double.doubleToLongBits(valor_recibo) != Double.doubleToLongBits(other.valor_recibo))
			return false;
		if (vencto == null) {
			if (other.vencto != null)
				return false;
		} else if (!vencto.equals(other.vencto))
			return false;
		return true;
	}
	
	
}