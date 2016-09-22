package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intramural implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = ("varchar(50)"))
	private String titulo;
	@Column(columnDefinition = ("varchar(500)"))
	private String urlMateria;
	@Column(columnDefinition = ("varchar(MAX)"))
	private String mkt;
	@Column(columnDefinition = ("varchar(MAX)"))
	private String rh;
	@Column(columnDefinition = ("datetime"))
	private Date data;
	@Column(columnDefinition = ("bit"))
	private boolean status;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrlMateria() {
		return urlMateria;
	}

	public void setUrlMateria(String urlMateria) {
		this.urlMateria = urlMateria;
	}

	public String getMkt() {
		return mkt;
	}

	public void setMkt(String mkt) {
		this.mkt = mkt;
	}

	public String getRh() {
		return rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((mkt == null) ? 0 : mkt.hashCode());
		result = prime * result + ((rh == null) ? 0 : rh.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((urlMateria == null) ? 0 : urlMateria.hashCode());
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
		intramural other = (intramural) obj;
		if (codigo != other.codigo)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (mkt == null) {
			if (other.mkt != null)
				return false;
		} else if (!mkt.equals(other.mkt))
			return false;
		if (rh == null) {
			if (other.rh != null)
				return false;
		} else if (!rh.equals(other.rh))
			return false;
		if (status != other.status)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (urlMateria == null) {
			if (other.urlMateria != null)
				return false;
		} else if (!urlMateria.equals(other.urlMateria))
			return false;
		return true;
	}
}