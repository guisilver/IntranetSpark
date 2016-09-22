package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class intra_sipo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	
	
	
	
}
