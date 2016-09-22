package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class intra_cndpruni implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="condominio",columnDefinition="smallint")
	private int condominio;
	@Column(name="bloco",columnDefinition="varchar(4)")
	private String bloco;
	@Column(name="unidade",columnDefinition="varchar(6)")
	private String unidade;
	@Column(name="sequencia",columnDefinition="smallint")
	private int sequencia;
	@Column(name="titulo",columnDefinition="varchar(40)")
	private String titulo;
	@Column(name="tipo",columnDefinition="tinyint")
	private int tipo;
	@Column(name="data_inclusao",columnDefinition="datetime")
	private Date data_inclusao;
	@Column(name="descricao",columnDefinition="varchar(7160)")
	private String descricao;
	@Column(name="tipo_pront",columnDefinition="varchar(1)")
	private String tipo_pront;
	@Column(name="nao_public",columnDefinition="tinyint")
	private int nao_public;
	@Column(name="dfrecnum",columnDefinition="int")
	private int dfrecnum;
	
	public int getCondominio() {
		return condominio;
	}
	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public int getSequencia() {
		return sequencia;
	}
	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Date getData_inclusao() {
		return data_inclusao;
	}
	public void setData_inclusao(Date data_inclusao) {
		this.data_inclusao = data_inclusao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo_pront() {
		return tipo_pront;
	}
	public void setTipo_pront(String tipo_pront) {
		this.tipo_pront = tipo_pront;
	}
	public int getNao_public() {
		return nao_public;
	}
	public void setNao_public(int nao_public) {
		this.nao_public = nao_public;
	}
	public int getDfrecnum() {
		return dfrecnum;
	}
	public void setDfrecnum(int dfrecnum) {
		this.dfrecnum = dfrecnum;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + condominio;
		result = prime * result + ((data_inclusao == null) ? 0 : data_inclusao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + dfrecnum;
		result = prime * result + nao_public;
		result = prime * result + sequencia;
		result = prime * result + tipo;
		result = prime * result + ((tipo_pront == null) ? 0 : tipo_pront.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		intra_cndpruni other = (intra_cndpruni) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (condominio != other.condominio)
			return false;
		if (data_inclusao == null) {
			if (other.data_inclusao != null)
				return false;
		} else if (!data_inclusao.equals(other.data_inclusao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dfrecnum != other.dfrecnum)
			return false;
		if (nao_public != other.nao_public)
			return false;
		if (sequencia != other.sequencia)
			return false;
		if (tipo != other.tipo)
			return false;
		if (tipo_pront == null) {
			if (other.tipo_pront != null)
				return false;
		} else if (!tipo_pront.equals(other.tipo_pront))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}
	
}
