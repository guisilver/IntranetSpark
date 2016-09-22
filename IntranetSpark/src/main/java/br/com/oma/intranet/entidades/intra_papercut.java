package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

public class intra_papercut implements Serializable{

	private static final long serialVersionUID = 1L;

	private double id;
	
	private String nome;
	
	private String subNome;
	
	private String documentoNome;
	
	private double totalPage;
	
	private String usuario;
	
	private Date data;

	
	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSubNome() {
		return subNome;
	}

	public void setSubNome(String subNome) {
		this.subNome = subNome;
	}

	public String getDocumentoNome() {
		return documentoNome;
	}

	public void setDocumentoNome(String documentoNome) {
		this.documentoNome = documentoNome;
	}

	public double getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(double totalPage) {
		this.totalPage = totalPage;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((documentoNome == null) ? 0 : documentoNome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(id);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((subNome == null) ? 0 : subNome.hashCode());
		temp = Double.doubleToLongBits(totalPage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		intra_papercut other = (intra_papercut) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (documentoNome == null) {
			if (other.documentoNome != null)
				return false;
		} else if (!documentoNome.equals(other.documentoNome))
			return false;
		if (Double.doubleToLongBits(id) != Double.doubleToLongBits(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (subNome == null) {
			if (other.subNome != null)
				return false;
		} else if (!subNome.equals(other.subNome))
			return false;
		if (Double.doubleToLongBits(totalPage) != Double.doubleToLongBits(other.totalPage))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
}
