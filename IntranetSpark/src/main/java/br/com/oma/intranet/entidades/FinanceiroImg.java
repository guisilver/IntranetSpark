package br.com.oma.intranet.entidades;

import java.io.Serializable;

import br.com.oma.omaonline.entidades.cndpagar;

public class FinanceiroImg implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6019338160952939242L;
	private int id_contrato;
	private int codBanco;
	private int condominio;
	private String nomeBanco;
	private String codAgencia;
	private String nomeAgencia;
	private String CC;
	private cndpagar cndpagar = new cndpagar();
	private boolean possuiImg;
	private String contaVinculada;

	public int getId_contrato() {
		return id_contrato;
	}

	public void setId_contrato(int id_contrato) {
		this.id_contrato = id_contrato;
	}

	public int getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(int codBanco) {
		this.codBanco = codBanco;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getCC() {
		return CC;
	}

	public void setCC(String cC) {
		CC = cC;
	}

	public cndpagar getCndpagar() {
		return cndpagar;
	}

	public void setCndpagar(cndpagar cndpagar) {
		this.cndpagar = cndpagar;
	}

	public boolean isPossuiImg() {
		return possuiImg;
	}

	public void setPossuiImg(boolean possuiImg) {
		this.possuiImg = possuiImg;
	}

	public String getContaVinculada() {
		return contaVinculada;
	}

	public void setContaVinculada(String contaVinculada) {
		this.contaVinculada = contaVinculada;
	}

}
