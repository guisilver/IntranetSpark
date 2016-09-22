package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "sigadm.dbo")
public class cndrteio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nro_rateio;
	private int condominio;
	private int conta;
	private String historico;
	private double taxa_adm;
	private double valor_total;
	private int lctos;
	private char exclusivo_prop;
	private int cta_anl_financ;
	private double perc_desc;
	private int comp_valor;
	private int nro_leitura;
	private Date data_alteracao;
	private int processado;
	private int nro_rateio_aux;
	private int tipo_desc;
	private int regular;
	private int compl_hist;
	private int tipo_cob;
	private int mes_ini;
	private int ano_ini;
	private int mes_fin;
	private int ano_fin;
	private int cobrar_cota;
	private int nro_coluna;
	private String nome_coluna;

	public int getNro_rateio() {
		return nro_rateio;
	}

	public void setNro_rateio(int nro_rateio) {
		this.nro_rateio = nro_rateio;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
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

	public double getTaxa_adm() {
		return taxa_adm;
	}

	public void setTaxa_adm(double taxa_adm) {
		this.taxa_adm = taxa_adm;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public int getLctos() {
		return lctos;
	}

	public void setLctos(int lctos) {
		this.lctos = lctos;
	}

	public char getExclusivo_prop() {
		return exclusivo_prop;
	}

	public void setExclusivo_prop(char exclusivo_prop) {
		this.exclusivo_prop = exclusivo_prop;
	}

	public int getCta_anl_financ() {
		return cta_anl_financ;
	}

	public void setCta_anl_financ(int cta_anl_financ) {
		this.cta_anl_financ = cta_anl_financ;
	}

	public double getPerc_desc() {
		return perc_desc;
	}

	public void setPerc_desc(double perc_desc) {
		this.perc_desc = perc_desc;
	}

	public int getComp_valor() {
		return comp_valor;
	}

	public void setComp_valor(int comp_valor) {
		this.comp_valor = comp_valor;
	}

	public int getNro_leitura() {
		return nro_leitura;
	}

	public void setNro_leitura(int nro_leitura) {
		this.nro_leitura = nro_leitura;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public int getProcessado() {
		return processado;
	}

	public void setProcessado(int processado) {
		this.processado = processado;
	}

	public int getNro_rateio_aux() {
		return nro_rateio_aux;
	}

	public void setNro_rateio_aux(int nro_rateio_aux) {
		this.nro_rateio_aux = nro_rateio_aux;
	}

	public int getTipo_desc() {
		return tipo_desc;
	}

	public void setTipo_desc(int tipo_desc) {
		this.tipo_desc = tipo_desc;
	}

	public int getRegular() {
		return regular;
	}

	public void setRegular(int regular) {
		this.regular = regular;
	}

	public int getCompl_hist() {
		return compl_hist;
	}

	public void setCompl_hist(int compl_hist) {
		this.compl_hist = compl_hist;
	}

	public int getTipo_cob() {
		return tipo_cob;
	}

	public void setTipo_cob(int tipo_cob) {
		this.tipo_cob = tipo_cob;
	}

	public int getMes_ini() {
		return mes_ini;
	}

	public void setMes_ini(int mes_ini) {
		this.mes_ini = mes_ini;
	}

	public int getAno_ini() {
		return ano_ini;
	}

	public void setAno_ini(int ano_ini) {
		this.ano_ini = ano_ini;
	}

	public int getMes_fin() {
		return mes_fin;
	}

	public void setMes_fin(int mes_fin) {
		this.mes_fin = mes_fin;
	}

	public int getAno_fin() {
		return ano_fin;
	}

	public void setAno_fin(int ano_fin) {
		this.ano_fin = ano_fin;
	}

	public int getCobrar_cota() {
		return cobrar_cota;
	}

	public void setCobrar_cota(int cobrar_cota) {
		this.cobrar_cota = cobrar_cota;
	}

	public int getNro_coluna() {
		return nro_coluna;
	}

	public void setNro_coluna(int nro_coluna) {
		this.nro_coluna = nro_coluna;
	}

	public String getNome_coluna() {
		return nome_coluna;
	}

	public void setNome_coluna(String nome_coluna) {
		this.nome_coluna = nome_coluna;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano_fin;
		result = prime * result + ano_ini;
		result = prime * result + cobrar_cota;
		result = prime * result + comp_valor;
		result = prime * result + compl_hist;
		result = prime * result + condominio;
		result = prime * result + conta;
		result = prime * result + cta_anl_financ;
		result = prime * result + ((data_alteracao == null) ? 0 : data_alteracao.hashCode());
		result = prime * result + exclusivo_prop;
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + lctos;
		result = prime * result + mes_fin;
		result = prime * result + mes_ini;
		result = prime * result + ((nome_coluna == null) ? 0 : nome_coluna.hashCode());
		result = prime * result + nro_coluna;
		result = prime * result + nro_leitura;
		result = prime * result + nro_rateio;
		result = prime * result + nro_rateio_aux;
		long temp;
		temp = Double.doubleToLongBits(perc_desc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + processado;
		result = prime * result + regular;
		temp = Double.doubleToLongBits(taxa_adm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + tipo_cob;
		result = prime * result + tipo_desc;
		temp = Double.doubleToLongBits(valor_total);
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
		cndrteio other = (cndrteio) obj;
		if (ano_fin != other.ano_fin)
			return false;
		if (ano_ini != other.ano_ini)
			return false;
		if (cobrar_cota != other.cobrar_cota)
			return false;
		if (comp_valor != other.comp_valor)
			return false;
		if (compl_hist != other.compl_hist)
			return false;
		if (condominio != other.condominio)
			return false;
		if (conta != other.conta)
			return false;
		if (cta_anl_financ != other.cta_anl_financ)
			return false;
		if (data_alteracao == null) {
			if (other.data_alteracao != null)
				return false;
		} else if (!data_alteracao.equals(other.data_alteracao))
			return false;
		if (exclusivo_prop != other.exclusivo_prop)
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (lctos != other.lctos)
			return false;
		if (mes_fin != other.mes_fin)
			return false;
		if (mes_ini != other.mes_ini)
			return false;
		if (nome_coluna == null) {
			if (other.nome_coluna != null)
				return false;
		} else if (!nome_coluna.equals(other.nome_coluna))
			return false;
		if (nro_coluna != other.nro_coluna)
			return false;
		if (nro_leitura != other.nro_leitura)
			return false;
		if (nro_rateio != other.nro_rateio)
			return false;
		if (nro_rateio_aux != other.nro_rateio_aux)
			return false;
		if (Double.doubleToLongBits(perc_desc) != Double.doubleToLongBits(other.perc_desc))
			return false;
		if (processado != other.processado)
			return false;
		if (regular != other.regular)
			return false;
		if (Double.doubleToLongBits(taxa_adm) != Double.doubleToLongBits(other.taxa_adm))
			return false;
		if (tipo_cob != other.tipo_cob)
			return false;
		if (tipo_desc != other.tipo_desc)
			return false;
		if (Double.doubleToLongBits(valor_total) != Double.doubleToLongBits(other.valor_total))
			return false;
		return true;
	}

}
