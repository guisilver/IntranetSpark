package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class intra_funcionario_oma implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(100)")
	private String nome;
	@Column(columnDefinition = "varchar(1)")
	private String sexo;
	@Column(columnDefinition = "varchar(50)")
	private String cargo;
	@Column(columnDefinition = "varchar(50)")
	private String departamento;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAdmissao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRecisao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	@OneToOne
	private intra_usuario usuario;
	@OneToOne
	private intra_contato contato;

	
}
