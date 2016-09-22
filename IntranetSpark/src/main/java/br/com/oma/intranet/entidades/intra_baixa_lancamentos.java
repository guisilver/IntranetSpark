package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class intra_baixa_lancamentos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1036752449051737010L;
	@Id
	@Column(name = "codigo", columnDefinition = "int")
	private int codigo;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
