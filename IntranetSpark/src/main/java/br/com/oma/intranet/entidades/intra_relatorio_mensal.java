package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class intra_relatorio_mensal implements Serializable {

	private static final long serialVersionUID = 1L;

	private String baixado;

	private Date dataBaixa;
	@Column(columnDefinition = "smallint")
	private int codigo;
	private String nomeCondominio;
	private String nomeGerente;
	@Column(columnDefinition = "smallint")
	private int qtde_aptos;

	private int qtde_cjtos;
	@Column(columnDefinition = "smallint")
	private int qtde_lojas;
	private double taxaAdm;

	private Date inicioAdm;
	private double totalCota;
	private String logradouro;
	private String endereco;
	private String nro_end;
	private String bairro;
	private double cep;
	private String estado;
	private String nomeSindico;
	@Column(columnDefinition = "smallint")
	private int diaAniSind;
	@Column(columnDefinition = "smallint")
	private int mesAniSind;
	@Column(columnDefinition = "smallint")
	private int anoAniSind;
	private String profissaoSind;

	private Date inicioMandSind;
	private char contaVinculada;

	public String getBaixado() {
		return baixado;
	}

	public void setBaixado(String baixado) {
		this.baixado = baixado;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public int getQtde_aptos() {
		return qtde_aptos;
	}

	public void setQtde_aptos(int qtde_aptos) {
		this.qtde_aptos = qtde_aptos;
	}

	public int getQtde_cjtos() {
		return qtde_cjtos;
	}

	public void setQtde_cjtos(int qtde_cjtos) {
		this.qtde_cjtos = qtde_cjtos;
	}

	public int getQtde_lojas() {
		return qtde_lojas;
	}

	public void setQtde_lojas(int qtde_lojas) {
		this.qtde_lojas = qtde_lojas;
	}

	public double getTaxaAdm() {
		return taxaAdm;
	}

	public void setTaxaAdm(double taxaAdm) {
		this.taxaAdm = taxaAdm;
	}

	public Date getInicioAdm() {
		return inicioAdm;
	}

	public void setInicioAdm(Date inicioAdm) {
		this.inicioAdm = inicioAdm;
	}

	public double getTotalCota() {
		return totalCota;
	}

	public void setTotalCota(double totalCota) {
		this.totalCota = totalCota;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNro_end() {
		return nro_end;
	}

	public void setNro_end(String nro_end) {
		this.nro_end = nro_end;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public double getCep() {
		return cep;
	}

	public void setCep(double cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNomeSindico() {
		return nomeSindico;
	}

	public void setNomeSindico(String nomeSindico) {
		this.nomeSindico = nomeSindico;
	}

	public int getDiaAniSind() {
		return diaAniSind;
	}

	public void setDiaAniSind(int diaAniSind) {
		this.diaAniSind = diaAniSind;
	}

	public int getMesAniSind() {
		return mesAniSind;
	}

	public void setMesAniSind(int mesAniSind) {
		this.mesAniSind = mesAniSind;
	}

	public int getAnoAniSind() {
		return anoAniSind;
	}

	public void setAnoAniSind(int anoAniSind) {
		this.anoAniSind = anoAniSind;
	}

	public String getProfissaoSind() {
		return profissaoSind;
	}

	public void setProfissaoSind(String profissaoSind) {
		this.profissaoSind = profissaoSind;
	}

	public Date getInicioMandSind() {
		return inicioMandSind;
	}

	public void setInicioMandSind(Date inicioMandSind) {
		this.inicioMandSind = inicioMandSind;
	}

	public char getContaVinculada() {
		return contaVinculada;
	}

	public void setContaVinculada(char contaVinculada) {
		this.contaVinculada = contaVinculada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anoAniSind;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((baixado == null) ? 0 : baixado.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cep);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + codigo;
		result = prime * result + contaVinculada;
		result = prime * result + ((dataBaixa == null) ? 0 : dataBaixa.hashCode());
		result = prime * result + diaAniSind;
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((inicioAdm == null) ? 0 : inicioAdm.hashCode());
		result = prime * result + ((inicioMandSind == null) ? 0 : inicioMandSind.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + mesAniSind;
		result = prime * result + ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result + ((nomeGerente == null) ? 0 : nomeGerente.hashCode());
		result = prime * result + ((nomeSindico == null) ? 0 : nomeSindico.hashCode());
		result = prime * result + ((nro_end == null) ? 0 : nro_end.hashCode());
		result = prime * result + ((profissaoSind == null) ? 0 : profissaoSind.hashCode());
		result = prime * result + qtde_aptos;
		result = prime * result + qtde_cjtos;
		result = prime * result + qtde_lojas;
		temp = Double.doubleToLongBits(taxaAdm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalCota);
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
		intra_relatorio_mensal other = (intra_relatorio_mensal) obj;
		if (anoAniSind != other.anoAniSind)
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (baixado == null) {
			if (other.baixado != null)
				return false;
		} else if (!baixado.equals(other.baixado))
			return false;
		if (Double.doubleToLongBits(cep) != Double.doubleToLongBits(other.cep))
			return false;
		if (codigo != other.codigo)
			return false;
		if (contaVinculada != other.contaVinculada)
			return false;
		if (dataBaixa == null) {
			if (other.dataBaixa != null)
				return false;
		} else if (!dataBaixa.equals(other.dataBaixa))
			return false;
		if (diaAniSind != other.diaAniSind)
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (inicioAdm == null) {
			if (other.inicioAdm != null)
				return false;
		} else if (!inicioAdm.equals(other.inicioAdm))
			return false;
		if (inicioMandSind == null) {
			if (other.inicioMandSind != null)
				return false;
		} else if (!inicioMandSind.equals(other.inicioMandSind))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (mesAniSind != other.mesAniSind)
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
		if (nomeSindico == null) {
			if (other.nomeSindico != null)
				return false;
		} else if (!nomeSindico.equals(other.nomeSindico))
			return false;
		if (nro_end == null) {
			if (other.nro_end != null)
				return false;
		} else if (!nro_end.equals(other.nro_end))
			return false;
		if (profissaoSind == null) {
			if (other.profissaoSind != null)
				return false;
		} else if (!profissaoSind.equals(other.profissaoSind))
			return false;
		if (qtde_aptos != other.qtde_aptos)
			return false;
		if (qtde_cjtos != other.qtde_cjtos)
			return false;
		if (qtde_lojas != other.qtde_lojas)
			return false;
		if (Double.doubleToLongBits(taxaAdm) != Double.doubleToLongBits(other.taxaAdm))
			return false;
		if (Double.doubleToLongBits(totalCota) != Double.doubleToLongBits(other.totalCota))
			return false;
		return true;
	}

}