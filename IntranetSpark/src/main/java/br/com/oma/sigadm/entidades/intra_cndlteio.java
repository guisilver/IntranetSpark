package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;

public class intra_cndlteio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int nro_rateio;
	private int nro_lancto;
	private String bloco;
	private char tipo;
	private double valor;
	private char seguro;
	private double valor_calculado;
	private char flag_calc;
	private char usar_fator_mult;
	private double perc_desc;
	private String historico;
	private Date data_alteracao;
	private int cobrar_por_metr;
	private int codigo_tarifa;
	private int desprezar_agreg;
	private int dfrecnum;

	public int getNro_rateio() {
		return nro_rateio;
	}

	public void setNro_rateio(int nro_rateio) {
		this.nro_rateio = nro_rateio;
	}

	public int getNro_lancto() {
		return nro_lancto;
	}

	public void setNro_lancto(int nro_lancto) {
		this.nro_lancto = nro_lancto;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public char getSeguro() {
		return seguro;
	}

	public void setSeguro(char seguro) {
		this.seguro = seguro;
	}

	public double getValor_calculado() {
		return valor_calculado;
	}

	public void setValor_calculado(double valor_calculado) {
		this.valor_calculado = valor_calculado;
	}

	public char getFlag_calc() {
		return flag_calc;
	}

	public void setFlag_calc(char flag_calc) {
		this.flag_calc = flag_calc;
	}

	public char getUsar_fator_mult() {
		return usar_fator_mult;
	}

	public void setUsar_fator_mult(char usar_fator_mult) {
		this.usar_fator_mult = usar_fator_mult;
	}

	public double getPerc_desc() {
		return perc_desc;
	}

	public void setPerc_desc(double perc_desc) {
		this.perc_desc = perc_desc;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public int getCobrar_por_metr() {
		return cobrar_por_metr;
	}

	public void setCobrar_por_metr(int cobrar_por_metr) {
		this.cobrar_por_metr = cobrar_por_metr;
	}

	public int getCodigo_tarifa() {
		return codigo_tarifa;
	}

	public void setCodigo_tarifa(int codigo_tarifa) {
		this.codigo_tarifa = codigo_tarifa;
	}

	public int getDesprezar_agreg() {
		return desprezar_agreg;
	}

	public void setDesprezar_agreg(int desprezar_agreg) {
		this.desprezar_agreg = desprezar_agreg;
	}

	public int getDfrecnum() {
		return dfrecnum;
	}

	public void setDfrecnum(int dfrecnum) {
		this.dfrecnum = dfrecnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + cobrar_por_metr;
		result = prime * result + codigo_tarifa;
		result = prime * result + ((data_alteracao == null) ? 0 : data_alteracao.hashCode());
		result = prime * result + desprezar_agreg;
		result = prime * result + dfrecnum;
		result = prime * result + flag_calc;
		result = prime * result + ((historico == null) ? 0 : historico.hashCode());
		result = prime * result + nro_lancto;
		result = prime * result + nro_rateio;
		long temp;
		temp = Double.doubleToLongBits(perc_desc);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + seguro;
		result = prime * result + tipo;
		result = prime * result + usar_fator_mult;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor_calculado);
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
		intra_cndlteio other = (intra_cndlteio) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (cobrar_por_metr != other.cobrar_por_metr)
			return false;
		if (codigo_tarifa != other.codigo_tarifa)
			return false;
		if (data_alteracao == null) {
			if (other.data_alteracao != null)
				return false;
		} else if (!data_alteracao.equals(other.data_alteracao))
			return false;
		if (desprezar_agreg != other.desprezar_agreg)
			return false;
		if (dfrecnum != other.dfrecnum)
			return false;
		if (flag_calc != other.flag_calc)
			return false;
		if (historico == null) {
			if (other.historico != null)
				return false;
		} else if (!historico.equals(other.historico))
			return false;
		if (nro_lancto != other.nro_lancto)
			return false;
		if (nro_rateio != other.nro_rateio)
			return false;
		if (Double.doubleToLongBits(perc_desc) != Double.doubleToLongBits(other.perc_desc))
			return false;
		if (seguro != other.seguro)
			return false;
		if (tipo != other.tipo)
			return false;
		if (usar_fator_mult != other.usar_fator_mult)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (Double.doubleToLongBits(valor_calculado) != Double.doubleToLongBits(other.valor_calculado))
			return false;
		return true;
	}
}
