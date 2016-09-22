package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

public class glbCatalogo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="numeric(14,0)")
	private BigDecimal id;
	
	@JoinColumn(table="glbTipoArquivo", foreignKey=@ForeignKey(name="id"))
	@Column(columnDefinition="smallint")
	private short id_tipoarquivo;
	
	@Column(columnDefinition="varchar(100)")
	private String titulo;
	
	@Column(columnDefinition="varchar(1000)")
	private String descricao;
	
	@Column(columnDefinition="varchar(100)")
	private String usuario_vinculo_arq;
	
	@Column(columnDefinition="datetime")
	private Date data_vinculo_arq;
	
	@Column(columnDefinition="varchar(100)")
	private String usuario_vinculo_lcto;
	
	@Column(columnDefinition="datetime")
	private Date data_vinculo_lcto;
	
	@Column(columnDefinition="tinyint")
	private int sistema;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public short getId_tipoarquivo() {
		return id_tipoarquivo;
	}

	public void setId_tipoarquivo(short id_tipoarquivo) {
		this.id_tipoarquivo = id_tipoarquivo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUsuario_vinculo_arq() {
		return usuario_vinculo_arq;
	}

	public void setUsuario_vinculo_arq(String usuario_vinculo_arq) {
		this.usuario_vinculo_arq = usuario_vinculo_arq;
	}

	public Date getData_vinculo_arq() {
		return data_vinculo_arq;
	}

	public void setData_vinculo_arq(Date data_vinculo_arq) {
		this.data_vinculo_arq = data_vinculo_arq;
	}

	public String getUsuario_vinculo_lcto() {
		return usuario_vinculo_lcto;
	}

	public void setUsuario_vinculo_lcto(String usuario_vinculo_lcto) {
		this.usuario_vinculo_lcto = usuario_vinculo_lcto;
	}

	public Date getData_vinculo_lcto() {
		return data_vinculo_lcto;
	}

	public void setData_vinculo_lcto(Date data_vinculo_lcto) {
		this.data_vinculo_lcto = data_vinculo_lcto;
	}

	public int getSistema() {
		return sistema;
	}

	public void setSistema(int sistema) {
		this.sistema = sistema;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data_vinculo_arq == null) ? 0 : data_vinculo_arq.hashCode());
		result = prime * result + ((data_vinculo_lcto == null) ? 0 : data_vinculo_lcto.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + id_tipoarquivo;
		result = prime * result + sistema;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((usuario_vinculo_arq == null) ? 0 : usuario_vinculo_arq.hashCode());
		result = prime * result + ((usuario_vinculo_lcto == null) ? 0 : usuario_vinculo_lcto.hashCode());
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
		glbCatalogo other = (glbCatalogo) obj;
		if (data_vinculo_arq == null) {
			if (other.data_vinculo_arq != null)
				return false;
		} else if (!data_vinculo_arq.equals(other.data_vinculo_arq))
			return false;
		if (data_vinculo_lcto == null) {
			if (other.data_vinculo_lcto != null)
				return false;
		} else if (!data_vinculo_lcto.equals(other.data_vinculo_lcto))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_tipoarquivo != other.id_tipoarquivo)
			return false;
		if (sistema != other.sistema)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (usuario_vinculo_arq == null) {
			if (other.usuario_vinculo_arq != null)
				return false;
		} else if (!usuario_vinculo_arq.equals(other.usuario_vinculo_arq))
			return false;
		if (usuario_vinculo_lcto == null) {
			if (other.usuario_vinculo_lcto != null)
				return false;
		} else if (!usuario_vinculo_lcto.equals(other.usuario_vinculo_lcto))
			return false;
		return true;
	}
	
	
}
