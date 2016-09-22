package br.com.oma.intranet.entidades;

import java.io.Serializable;

public class intra_emissao implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeCondominio;
	private int codigoCondominio;
	private String bloco;
	private String unidade;
	private int codigoGerente;
	private int vencto01;
	private int vencto02;
	private int conta;
	private String historico;
	private double valor;
	private char tipo;
	private double valorTotal;
	private int regular;
	private int complHistorico;
	private int tipoCobranca;
	private int mesInicial;
	private int anoInicial;
	private int mesFinal;
	private int anoFinal;
	private int cobrarCota;
	private int nroRateio;
	private int nro_lancto;
	private int cta_anl_financ;

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
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

	public int getVencto01() {
		return vencto01;
	}

	public void setVencto01(int vencto01) {
		this.vencto01 = vencto01;
	}

	public int getVencto02() {
		return vencto02;
	}

	public void setVencto02(int vencto02) {
		this.vencto02 = vencto02;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getRegular() {
		return regular;
	}

	public void setRegular(int regular) {
		this.regular = regular;
	}

	public int getComplHistorico() {
		return complHistorico;
	}

	public void setComplHistorico(int complHistorico) {
		this.complHistorico = complHistorico;
	}

	public int getTipoCobranca() {
		return tipoCobranca;
	}

	public void setTipoCobranca(int tipoCobranca) {
		this.tipoCobranca = tipoCobranca;
	}

	public int getMesInicial() {
		return mesInicial;
	}

	public void setMesInicial(int mesInicial) {
		this.mesInicial = mesInicial;
	}

	public int getAnoInicial() {
		return anoInicial;
	}

	public void setAnoInicial(int anoInicial) {
		this.anoInicial = anoInicial;
	}

	public int getMesFinal() {
		return mesFinal;
	}

	public void setMesFinal(int mesFinal) {
		this.mesFinal = mesFinal;
	}

	public int getAnoFinal() {
		return anoFinal;
	}

	public void setAnoFinal(int anoFinal) {
		this.anoFinal = anoFinal;
	}

	public int getCobrarCota() {
		return cobrarCota;
	}

	public void setCobrarCota(int cobrarCota) {
		this.cobrarCota = cobrarCota;
	}

	public int getNroRateio() {
		return nroRateio;
	}

	public void setNroRateio(int nroRateio) {
		this.nroRateio = nroRateio;
	}

	public int getNro_lancto() {
		return nro_lancto;
	}

	public void setNro_lancto(int nro_lancto) {
		this.nro_lancto = nro_lancto;
	}

	public int getCta_anl_financ() {
		return cta_anl_financ;
	}

	public void setCta_anl_financ(int cta_anl_financ) {
		this.cta_anl_financ = cta_anl_financ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anoFinal;
		result = prime * result + anoInicial;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + cobrarCota;
		result = prime * result + codigoCondominio;
		result = prime * result + codigoGerente;
		result = prime * result + complHistorico;
		result = prime * result + conta;
		result = prime * result + cta_anl_financ;
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + mesFinal;
		result = prime * result + mesInicial;
		result = prime * result + ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result + nroRateio;
		result = prime * result + nro_lancto;
		result = prime * result + regular;
		result = prime * result + tipo;
		result = prime * result + tipoCobranca;
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + vencto01;
		result = prime * result + vencto02;
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
		intra_emissao other = (intra_emissao) obj;
		if (anoFinal != other.anoFinal)
			return false;
		if (anoInicial != other.anoInicial)
			return false;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (cobrarCota != other.cobrarCota)
			return false;
		if (codigoCondominio != other.codigoCondominio)
			return false;
		if (codigoGerente != other.codigoGerente)
			return false;
		if (complHistorico != other.complHistorico)
			return false;
		if (conta != other.conta)
			return false;
		if (cta_anl_financ != other.cta_anl_financ)
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (mesFinal != other.mesFinal)
			return false;
		if (mesInicial != other.mesInicial)
			return false;
		if (nomeCondominio == null) {
			if (other.nomeCondominio != null)
				return false;
		} else if (!nomeCondominio.equals(other.nomeCondominio))
			return false;
		if (nroRateio != other.nroRateio)
			return false;
		if (nro_lancto != other.nro_lancto)
			return false;
		if (regular != other.regular)
			return false;
		if (tipo != other.tipo)
			return false;
		if (tipoCobranca != other.tipoCobranca)
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		if (vencto01 != other.vencto01)
			return false;
		if (vencto02 != other.vencto02)
			return false;
		return true;
	}

}