package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

public class intra_previsao_projecao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="smallint")
	private int condominio;
	
	@Column(name="codigo_gerente")
	private int codigoGerente;
	
	@Column(columnDefinition="int")
	private int media;
	
	@Transient
	private boolean inadimplencia;

	@Transient
	private String[] mes = { "Jan", "Fev", "Mar", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez" };

	@Column(name="mes_projecao",columnDefinition="datetime")
	private Date mesProjecao;
	
	
}
