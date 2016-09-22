package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class intra_emissor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 836073712460597758L;
	@Id
	@GeneratedValue
	private Integer codigo;
	@Column(columnDefinition = "smallint")
	private Short codCondominio;
	@Column(columnDefinition = "varchar(100)")
	private String nomeCondominio;
	@Column(columnDefinition = "smallint")
	private Integer codGerente;
	@Column(columnDefinition = "varchar(250)")
	private String referencia;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnviado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConferido;
	@Column(columnDefinition = "varchar(15)")
	private String situacao;
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "emissor", orphanRemoval = true)
	private intra_emissor_arquivos arquivos;
	@OneToOne(cascade = { CascadeType.ALL })
	private intra_lote lote;

	@Column(name = "qtda_paginas_impressa", columnDefinition = "int")
	private int qtdaPaginasImpressa;

	@Transient
	private String cndCodNome;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Short getCodCondominio() {
		return codCondominio;
	}

	public void setCodCondominio(Short codCondominio) {
		this.codCondominio = codCondominio;
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public Integer getCodGerente() {
		return codGerente;
	}

	public void setCodGerente(Integer codGerente) {
		this.codGerente = codGerente;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Date getDataEnviado() {
		return dataEnviado;
	}

	public void setDataEnviado(Date dataEnviado) {
		this.dataEnviado = dataEnviado;
	}

	public Date getDataConferido() {
		return dataConferido;
	}

	public void setDataConferido(Date dataConferido) {
		this.dataConferido = dataConferido;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public intra_emissor_arquivos getArquivos() {
		return arquivos;
	}

	public void setArquivos(intra_emissor_arquivos arquivos) {
		this.arquivos = arquivos;
	}

	public intra_lote getLote() {
		return lote;
	}

	public void setLote(intra_lote lote) {
		this.lote = lote;
	}

	public int getQtdaPaginasImpressa() {
		return qtdaPaginasImpressa;
	}

	public void setQtdaPaginasImpressa(int qtdaPaginasImpressa) {
		this.qtdaPaginasImpressa = qtdaPaginasImpressa;
	}

	public String getCndCodNome() {
		if (this.codCondominio > 0 && this.nomeCondominio != null && !this.nomeCondominio.isEmpty()) {
			this.cndCodNome = this.codCondominio + " - " + this.nomeCondominio;
		}
		return cndCodNome;
	}

	public void setCndCodNome(String cndCodNome) {
		this.cndCodNome = cndCodNome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivos == null) ? 0 : arquivos.hashCode());
		result = prime * result + ((cndCodNome == null) ? 0 : cndCodNome.hashCode());
		result = prime * result + ((codCondominio == null) ? 0 : codCondominio.hashCode());
		result = prime * result + ((codGerente == null) ? 0 : codGerente.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((dataConferido == null) ? 0 : dataConferido.hashCode());
		result = prime * result + ((dataEnviado == null) ? 0 : dataEnviado.hashCode());
		result = prime * result + ((lote == null) ? 0 : lote.hashCode());
		result = prime * result + ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result + qtdaPaginasImpressa;
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
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
		intra_emissor other = (intra_emissor) obj;
		if (arquivos == null) {
			if (other.arquivos != null)
				return false;
		} else if (!arquivos.equals(other.arquivos))
			return false;
		if (cndCodNome == null) {
			if (other.cndCodNome != null)
				return false;
		} else if (!cndCodNome.equals(other.cndCodNome))
			return false;
		if (codCondominio == null) {
			if (other.codCondominio != null)
				return false;
		} else if (!codCondominio.equals(other.codCondominio))
			return false;
		if (codGerente == null) {
			if (other.codGerente != null)
				return false;
		} else if (!codGerente.equals(other.codGerente))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataConferido == null) {
			if (other.dataConferido != null)
				return false;
		} else if (!dataConferido.equals(other.dataConferido))
			return false;
		if (dataEnviado == null) {
			if (other.dataEnviado != null)
				return false;
		} else if (!dataEnviado.equals(other.dataEnviado))
			return false;
		if (lote == null) {
			if (other.lote != null)
				return false;
		} else if (!lote.equals(other.lote))
			return false;
		if (nomeCondominio == null) {
			if (other.nomeCondominio != null)
				return false;
		} else if (!nomeCondominio.equals(other.nomeCondominio))
			return false;
		if (qtdaPaginasImpressa != other.qtdaPaginasImpressa)
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}

}
