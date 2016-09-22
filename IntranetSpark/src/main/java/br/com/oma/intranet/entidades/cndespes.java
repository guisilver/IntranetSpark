package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class cndespes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4711023844667656826L;
	private int numero;
	private int condominio;
	private String bloco;
	private Date data;
	private int conta;
	private String sinal;
	private double valor;
	private int contra_partida;
	private String cpmf;
	private double valor_ipmf;
	private String historico;
	private int cta_anl_financ;
	private int codigo_cheque;
	private int id_gerador;
	private int copia_cheque;
	private int incide_tx_adm;
	private int reg_atualizado;
	private int rateio;
	private int nro_lanch;
	private long nro_id;
	private long nro_cotacao;
	private String nomeCondominio;
	private String cndCodNome;
	private String valorStr;
	DecimalFormat df = new DecimalFormat("###,###,###.00");

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getSinal() {
		return sinal;
	}

	public void setSinal(String sinal) {
		this.sinal = sinal;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getContra_partida() {
		return contra_partida;
	}

	public void setContra_partida(int contra_partida) {
		this.contra_partida = contra_partida;
	}

	public String getCpmf() {
		return cpmf;
	}

	public void setCpmf(String cpmf) {
		this.cpmf = cpmf;
	}

	public double getValor_ipmf() {
		return valor_ipmf;
	}

	public void setValor_ipmf(double valor_ipmf) {
		this.valor_ipmf = valor_ipmf;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public int getCta_anl_financ() {
		return cta_anl_financ;
	}

	public void setCta_anl_financ(int cta_anl_financ) {
		this.cta_anl_financ = cta_anl_financ;
	}

	public int getCodigo_cheque() {
		return codigo_cheque;
	}

	public void setCodigo_cheque(int codigo_cheque) {
		this.codigo_cheque = codigo_cheque;
	}

	public int getId_gerador() {
		return id_gerador;
	}

	public void setId_gerador(int id_gerador) {
		this.id_gerador = id_gerador;
	}

	public int getCopia_cheque() {
		return copia_cheque;
	}

	public void setCopia_cheque(int copia_cheque) {
		this.copia_cheque = copia_cheque;
	}

	public int getIncide_tx_adm() {
		return incide_tx_adm;
	}

	public void setIncide_tx_adm(int incide_tx_adm) {
		this.incide_tx_adm = incide_tx_adm;
	}

	public int getReg_atualizado() {
		return reg_atualizado;
	}

	public void setReg_atualizado(int reg_atualizado) {
		this.reg_atualizado = reg_atualizado;
	}

	public int getRateio() {
		return rateio;
	}

	public void setRateio(int rateio) {
		this.rateio = rateio;
	}

	public int getNro_lanch() {
		return nro_lanch;
	}

	public void setNro_lanch(int nro_lanch) {
		this.nro_lanch = nro_lanch;
	}

	public long getNro_id() {
		return nro_id;
	}

	public void setNro_id(long nro_id) {
		this.nro_id = nro_id;
	}

	public long getNro_cotacao() {
		return nro_cotacao;
	}

	public void setNro_cotacao(long nro_cotacao) {
		this.nro_cotacao = nro_cotacao;
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public String getCndCodNome() {
		if (this.condominio > 0 && this.nomeCondominio != null && !this.nomeCondominio.isEmpty()) {
			this.cndCodNome = this.condominio + " - " + this.nomeCondominio;
		}
		return cndCodNome;
	}

	public void setCndCodNome(String cndCodNome) {
		this.cndCodNome = cndCodNome;
	}

	public String getValorStr() {
		if (this.valor > 0) {
			this.valorStr = df.format(this.valor);
		}
		return valorStr;
	}

	public void setValorStr(String valorStr) {
		this.valorStr = valorStr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + ((cndCodNome == null) ? 0 : cndCodNome.hashCode());
		result = prime * result + codigo_cheque;
		result = prime * result + condominio;
		result = prime * result + conta;
		result = prime * result + contra_partida;
		result = prime * result + copia_cheque;
		result = prime * result + ((cpmf == null) ? 0 : cpmf.hashCode());
		result = prime * result + cta_anl_financ;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + id_gerador;
		result = prime * result + incide_tx_adm;
		result = prime * result + ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result + (int) (nro_cotacao ^ (nro_cotacao >>> 32));
		result = prime * result + (int) (nro_id ^ (nro_id >>> 32));
		result = prime * result + nro_lanch;
		result = prime * result + numero;
		result = prime * result + rateio;
		result = prime * result + reg_atualizado;
		result = prime * result + ((sinal == null) ? 0 : sinal.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((valorStr == null) ? 0 : valorStr.hashCode());
		temp = Double.doubleToLongBits(valor_ipmf);
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
		cndespes other = (cndespes) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (cndCodNome == null) {
			if (other.cndCodNome != null)
				return false;
		} else if (!cndCodNome.equals(other.cndCodNome))
			return false;
		if (codigo_cheque != other.codigo_cheque)
			return false;
		if (condominio != other.condominio)
			return false;
		if (conta != other.conta)
			return false;
		if (contra_partida != other.contra_partida)
			return false;
		if (copia_cheque != other.copia_cheque)
			return false;
		if (cpmf == null) {
			if (other.cpmf != null)
				return false;
		} else if (!cpmf.equals(other.cpmf))
			return false;
		if (cta_anl_financ != other.cta_anl_financ)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (id_gerador != other.id_gerador)
			return false;
		if (incide_tx_adm != other.incide_tx_adm)
			return false;
		if (nomeCondominio == null) {
			if (other.nomeCondominio != null)
				return false;
		} else if (!nomeCondominio.equals(other.nomeCondominio))
			return false;
		if (nro_cotacao != other.nro_cotacao)
			return false;
		if (nro_id != other.nro_id)
			return false;
		if (nro_lanch != other.nro_lanch)
			return false;
		if (numero != other.numero)
			return false;
		if (rateio != other.rateio)
			return false;
		if (reg_atualizado != other.reg_atualizado)
			return false;
		if (sinal == null) {
			if (other.sinal != null)
				return false;
		} else if (!sinal.equals(other.sinal))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (valorStr == null) {
			if (other.valorStr != null)
				return false;
		} else if (!valorStr.equals(other.valorStr))
			return false;
		if (Double.doubleToLongBits(valor_ipmf) != Double.doubleToLongBits(other.valor_ipmf))
			return false;
		return true;
	}

}
