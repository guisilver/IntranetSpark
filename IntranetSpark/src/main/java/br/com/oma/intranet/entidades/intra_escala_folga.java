package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class intra_escala_folga implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int codigoCondominio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date folga;
	@Column(columnDefinition = "varchar(3)")
	private String legenda;
	@Column(columnDefinition = "int")
	private int codigoFuncionario;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public Date getFolga() {
		return folga;
	}

	public void setFolga(Date folga) {
		this.folga = folga;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public int getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(int codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + codigoCondominio;
		result = prime * result + codigoFuncionario;
		result = prime * result + ((folga == null) ? 0 : folga.hashCode());
		result = prime * result + ((legenda == null) ? 0 : legenda.hashCode());
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
		intra_escala_folga other = (intra_escala_folga) obj;
		if (codigo != other.codigo)
			return false;
		if (codigoCondominio != other.codigoCondominio)
			return false;
		if (codigoFuncionario != other.codigoFuncionario)
			return false;
		if (folga == null) {
			if (other.folga != null)
				return false;
		} else if (!folga.equals(other.folga))
			return false;
		if (legenda == null) {
			if (other.legenda != null)
				return false;
		} else if (!legenda.equals(other.legenda))
			return false;
		return true;
	}

}
