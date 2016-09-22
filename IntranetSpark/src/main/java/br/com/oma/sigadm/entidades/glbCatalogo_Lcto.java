package br.com.oma.sigadm.entidades;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;

public class glbCatalogo_Lcto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double id_catalogo;
	private int id_lcto;
	private String tipo_lcto;

	public double getId_catalogo() {
		return id_catalogo;
	}

	public void setId_catalogo(double id_catalogo) {
		this.id_catalogo = id_catalogo;
	}

	public int getId_lcto() {
		return id_lcto;
	}

	public void setId_lcto(int id_lcto) {
		this.id_lcto = id_lcto;
	}

	public String getTipo_lcto() {
		return tipo_lcto;
	}

	public void setTipo_lcto(String tipo_lcto) {
		this.tipo_lcto = tipo_lcto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(id_catalogo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id_lcto;
		result = prime * result + ((tipo_lcto == null) ? 0 : tipo_lcto.hashCode());
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
		glbCatalogo_Lcto other = (glbCatalogo_Lcto) obj;
		if (Double.doubleToLongBits(id_catalogo) != Double.doubleToLongBits(other.id_catalogo))
			return false;
		if (id_lcto != other.id_lcto)
			return false;
		if (tipo_lcto == null) {
			if (other.tipo_lcto != null)
				return false;
		} else if (!tipo_lcto.equals(other.tipo_lcto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		XStream xstream = new XStream();
		return xstream.toXML(this);
	}

}
