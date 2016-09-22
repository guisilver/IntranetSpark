package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class glbCatalogo_Docto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition="numeric(20,0)")
	private BigDecimal id;

	@Column(columnDefinition="numeric(14, 0)")
	private BigDecimal id_catalogo;
	
	@Column(columnDefinition="varchar(200)")
	private String nome_arquivo;
	
	@Column(columnDefinition="char(5)")
	private String identificacao_tipo_arq;
	
	@Column(columnDefinition="int")
	private int sequencia;
	
	@Column(columnDefinition="bit")
	private boolean comprovante;


	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getId_catalogo() {
		return id_catalogo;
	}

	public void setId_catalogo(BigDecimal id_catalogo) {
		this.id_catalogo = id_catalogo;
	}

	public String getNome_arquivo() {
		return nome_arquivo;
	}

	public void setNome_arquivo(String nome_arquivo) {
		this.nome_arquivo = nome_arquivo;
	}

	public String getIdentificacao_tipo_arq() {
		return identificacao_tipo_arq;
	}

	public void setIdentificacao_tipo_arq(String identificacao_tipo_arq) {
		this.identificacao_tipo_arq = identificacao_tipo_arq;
	}

	public int getSequencia() {
		return sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}

	public boolean isComprovante() {
		return comprovante;
	}

	public void setComprovante(boolean comprovante) {
		this.comprovante = comprovante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (comprovante ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_catalogo == null) ? 0 : id_catalogo.hashCode());
		result = prime * result + ((identificacao_tipo_arq == null) ? 0 : identificacao_tipo_arq.hashCode());
		result = prime * result + ((nome_arquivo == null) ? 0 : nome_arquivo.hashCode());
		result = prime * result + sequencia;
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
		glbCatalogo_Docto other = (glbCatalogo_Docto) obj;
		if (comprovante != other.comprovante)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_catalogo == null) {
			if (other.id_catalogo != null)
				return false;
		} else if (!id_catalogo.equals(other.id_catalogo))
			return false;
		if (identificacao_tipo_arq == null) {
			if (other.identificacao_tipo_arq != null)
				return false;
		} else if (!identificacao_tipo_arq.equals(other.identificacao_tipo_arq))
			return false;
		if (nome_arquivo == null) {
			if (other.nome_arquivo != null)
				return false;
		} else if (!nome_arquivo.equals(other.nome_arquivo))
			return false;
		if (sequencia != other.sequencia)
			return false;
		return true;
	}
	
	
		
}
