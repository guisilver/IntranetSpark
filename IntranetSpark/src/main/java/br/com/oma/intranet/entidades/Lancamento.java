package br.com.oma.intranet.entidades;

import java.io.Serializable;

import br.com.oma.omaonline.entidades.cndpagar;

public class Lancamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4123027344686598925L;
	private cndpagar cndpagar;
	private boolean possuiImg;
	private boolean contaVinculada;

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

	public boolean isContaVinculada() {
		return contaVinculada;
	}

	public void setContaVinculada(boolean contaVinculada) {
		this.contaVinculada = contaVinculada;
	}

}
