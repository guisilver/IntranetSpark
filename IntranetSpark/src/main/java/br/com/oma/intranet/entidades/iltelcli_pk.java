package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class iltelcli_pk implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "int")
	private Integer cliente;
	@Column(columnDefinition = "tinyint")
	private Short tipo;
	@Column(columnDefinition = "tinyint")
	private Short flag_cadastro;
	@Column(columnDefinition = "int")
	private Integer dfrecnum;

	public iltelcli_pk() {

	}

	public iltelcli_pk(Integer cliente, Short tipo, Short flag_cadastro,
			Integer dfrecnum) {
		this.cliente = cliente;
		this.tipo = tipo;
		this.flag_cadastro = flag_cadastro;
		this.dfrecnum = dfrecnum;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Short getTipo() {
		return tipo;
	}

	public void setTipo(Short tipo) {
		this.tipo = tipo;
	}

	public Short getFlag_cadastro() {
		return flag_cadastro;
	}

	public void setFlag_cadastro(Short flag_cadastro) {
		this.flag_cadastro = flag_cadastro;
	}

	public Integer getDfrecnum() {
		return dfrecnum;
	}

	public void setDfrecnum(Integer dfrecnum) {
		this.dfrecnum = dfrecnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((dfrecnum == null) ? 0 : dfrecnum.hashCode());
		result = prime * result
				+ ((flag_cadastro == null) ? 0 : flag_cadastro.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		iltelcli_pk other = (iltelcli_pk) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dfrecnum == null) {
			if (other.dfrecnum != null)
				return false;
		} else if (!dfrecnum.equals(other.dfrecnum))
			return false;
		if (flag_cadastro == null) {
			if (other.flag_cadastro != null)
				return false;
		} else if (!flag_cadastro.equals(other.flag_cadastro))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
