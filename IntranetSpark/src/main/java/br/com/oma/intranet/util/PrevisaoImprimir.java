package br.com.oma.intranet.util;

import java.util.Date;

public class PrevisaoImprimir {

	private int p_codigo;
	private String p_capa;
	private int p_codigo_gerente;
	private int p_condominio;
	private int p_conta;
	private Date p_mes_projecao;
	private String p_despesas;
	private double p_mes1;
	private double p_mes2;
	private double p_mes3;
	private double p_mes4;
	private double p_mes5;
	private double p_mes6;
	private double p_mes7;
	private double p_mes8;
	private double p_mes9;
	private double p_mes10;
	private double p_mes11;
	private double p_mes12;
	private double p_media;
	private String p_codigoDespesa;
	private double p_inadimplencia;
	private String c_nomeCondominio;
	private double p_totalMedia;
	public int getP_codigo() {
		return p_codigo;
	}
	public void setP_codigo(int p_codigo) {
		this.p_codigo = p_codigo;
	}
	public String getP_capa() {
		return p_capa;
	}
	public void setP_capa(String p_capa) {
		this.p_capa = p_capa;
	}
	public int getP_codigo_gerente() {
		return p_codigo_gerente;
	}
	public void setP_codigo_gerente(int p_codigo_gerente) {
		this.p_codigo_gerente = p_codigo_gerente;
	}
	public int getP_condominio() {
		return p_condominio;
	}
	public void setP_condominio(int p_condominio) {
		this.p_condominio = p_condominio;
	}
	public int getP_conta() {
		return p_conta;
	}
	public void setP_conta(int p_conta) {
		this.p_conta = p_conta;
	}
	public Date getP_mes_projecao() {
		return p_mes_projecao;
	}
	public void setP_mes_projecao(Date p_mes_projecao) {
		this.p_mes_projecao = p_mes_projecao;
	}
	public String getP_despesas() {
		return p_despesas;
	}
	public void setP_despesas(String p_despesas) {
		this.p_despesas = p_despesas;
	}
	public double getP_mes1() {
		return p_mes1;
	}
	public void setP_mes1(double p_mes1) {
		this.p_mes1 = p_mes1;
	}
	public double getP_mes2() {
		return p_mes2;
	}
	public void setP_mes2(double p_mes2) {
		this.p_mes2 = p_mes2;
	}
	public double getP_mes3() {
		return p_mes3;
	}
	public void setP_mes3(double p_mes3) {
		this.p_mes3 = p_mes3;
	}
	public double getP_mes4() {
		return p_mes4;
	}
	public void setP_mes4(double p_mes4) {
		this.p_mes4 = p_mes4;
	}
	public double getP_mes5() {
		return p_mes5;
	}
	public void setP_mes5(double p_mes5) {
		this.p_mes5 = p_mes5;
	}
	public double getP_mes6() {
		return p_mes6;
	}
	public void setP_mes6(double p_mes6) {
		this.p_mes6 = p_mes6;
	}
	public double getP_mes7() {
		return p_mes7;
	}
	public void setP_mes7(double p_mes7) {
		this.p_mes7 = p_mes7;
	}
	public double getP_mes8() {
		return p_mes8;
	}
	public void setP_mes8(double p_mes8) {
		this.p_mes8 = p_mes8;
	}
	public double getP_mes9() {
		return p_mes9;
	}
	public void setP_mes9(double p_mes9) {
		this.p_mes9 = p_mes9;
	}
	public double getP_mes10() {
		return p_mes10;
	}
	public void setP_mes10(double p_mes10) {
		this.p_mes10 = p_mes10;
	}
	public double getP_mes11() {
		return p_mes11;
	}
	public void setP_mes11(double p_mes11) {
		this.p_mes11 = p_mes11;
	}
	public double getP_mes12() {
		return p_mes12;
	}
	public void setP_mes12(double p_mes12) {
		this.p_mes12 = p_mes12;
	}
	public double getP_media() {
		return p_media;
	}
	public void setP_media(double p_media) {
		this.p_media = p_media;
	}
	public String getP_codigoDespesa() {
		return p_codigoDespesa;
	}
	public void setP_codigoDespesa(String p_codigoDespesa) {
		this.p_codigoDespesa = p_codigoDespesa;
	}
	public double getP_inadimplencia() {
		return p_inadimplencia;
	}
	public void setP_inadimplencia(double p_inadimplencia) {
		this.p_inadimplencia = p_inadimplencia;
	}
	public String getC_nomeCondominio() {
		return c_nomeCondominio;
	}
	public void setC_nomeCondominio(String c_nomeCondominio) {
		this.c_nomeCondominio = c_nomeCondominio;
	}
	public double getP_totalMedia() {
		return p_totalMedia;
	}
	public void setP_totalMedia(double p_totalMedia) {
		this.p_totalMedia = p_totalMedia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((c_nomeCondominio == null) ? 0 : c_nomeCondominio.hashCode());
		result = prime * result + ((p_capa == null) ? 0 : p_capa.hashCode());
		result = prime * result + p_codigo;
		result = prime * result
				+ ((p_codigoDespesa == null) ? 0 : p_codigoDespesa.hashCode());
		result = prime * result + p_codigo_gerente;
		result = prime * result + p_condominio;
		result = prime * result + p_conta;
		result = prime * result
				+ ((p_despesas == null) ? 0 : p_despesas.hashCode());
		long temp;
		temp = Double.doubleToLongBits(p_inadimplencia);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes10);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes11);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes12);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes3);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes4);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes5);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes6);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes7);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes8);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_mes9);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((p_mes_projecao == null) ? 0 : p_mes_projecao.hashCode());
		temp = Double.doubleToLongBits(p_media);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(p_totalMedia);
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
		PrevisaoImprimir other = (PrevisaoImprimir) obj;
		if (c_nomeCondominio == null) {
			if (other.c_nomeCondominio != null)
				return false;
		} else if (!c_nomeCondominio.equals(other.c_nomeCondominio))
			return false;
		if (p_capa == null) {
			if (other.p_capa != null)
				return false;
		} else if (!p_capa.equals(other.p_capa))
			return false;
		if (p_codigo != other.p_codigo)
			return false;
		if (p_codigoDespesa == null) {
			if (other.p_codigoDespesa != null)
				return false;
		} else if (!p_codigoDespesa.equals(other.p_codigoDespesa))
			return false;
		if (p_codigo_gerente != other.p_codigo_gerente)
			return false;
		if (p_condominio != other.p_condominio)
			return false;
		if (p_conta != other.p_conta)
			return false;
		if (p_despesas == null) {
			if (other.p_despesas != null)
				return false;
		} else if (!p_despesas.equals(other.p_despesas))
			return false;
		if (Double.doubleToLongBits(p_inadimplencia) != Double
				.doubleToLongBits(other.p_inadimplencia))
			return false;
		if (Double.doubleToLongBits(p_mes1) != Double
				.doubleToLongBits(other.p_mes1))
			return false;
		if (Double.doubleToLongBits(p_mes10) != Double
				.doubleToLongBits(other.p_mes10))
			return false;
		if (Double.doubleToLongBits(p_mes11) != Double
				.doubleToLongBits(other.p_mes11))
			return false;
		if (Double.doubleToLongBits(p_mes12) != Double
				.doubleToLongBits(other.p_mes12))
			return false;
		if (Double.doubleToLongBits(p_mes2) != Double
				.doubleToLongBits(other.p_mes2))
			return false;
		if (Double.doubleToLongBits(p_mes3) != Double
				.doubleToLongBits(other.p_mes3))
			return false;
		if (Double.doubleToLongBits(p_mes4) != Double
				.doubleToLongBits(other.p_mes4))
			return false;
		if (Double.doubleToLongBits(p_mes5) != Double
				.doubleToLongBits(other.p_mes5))
			return false;
		if (Double.doubleToLongBits(p_mes6) != Double
				.doubleToLongBits(other.p_mes6))
			return false;
		if (Double.doubleToLongBits(p_mes7) != Double
				.doubleToLongBits(other.p_mes7))
			return false;
		if (Double.doubleToLongBits(p_mes8) != Double
				.doubleToLongBits(other.p_mes8))
			return false;
		if (Double.doubleToLongBits(p_mes9) != Double
				.doubleToLongBits(other.p_mes9))
			return false;
		if (p_mes_projecao == null) {
			if (other.p_mes_projecao != null)
				return false;
		} else if (!p_mes_projecao.equals(other.p_mes_projecao))
			return false;
		if (Double.doubleToLongBits(p_media) != Double
				.doubleToLongBits(other.p_media))
			return false;
		if (Double.doubleToLongBits(p_totalMedia) != Double
				.doubleToLongBits(other.p_totalMedia))
			return false;
		return true;
	}
	
	
	
	
}
