package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class intra_contabilizador implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInserido;
	@Column(columnDefinition="int")
	private int minutos;
	@Column(columnDefinition="varchar(500)")
	private String obs;
	@Column(columnDefinition="int")
	private int condominio_codigo;
	@Column(columnDefinition="varchar(75)")
	private String condominio_nome;
	@Column(columnDefinition="varchar(75)")
	private String usuario;
	@Column(columnDefinition="varchar(50)")
	private String usuario_email;
	@Column(columnDefinition="varchar(75)")
	private String nomeGerente;
	
	@Transient
	private Date dataInicial;
	@Transient
	private Date dataFinal;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDataInserido() {
		return dataInserido;
	}
	public void setDataInserido(Date dataInserido) {
		this.dataInserido = dataInserido;
	}
	public int getMinutos() {
		return minutos;
	}
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public int getCondominio_codigo() {
		return condominio_codigo;
	}
	public void setCondominio_codigo(int condominio_codigo) {
		this.condominio_codigo = condominio_codigo;
	}
	public String getCondominio_nome() {
		return condominio_nome;
	}
	public void setCondominio_nome(String condominio_nome) {
		this.condominio_nome = condominio_nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getUsuario_email() {
		return usuario_email;
	}
	public void setUsuario_email(String usuario_email) {
		this.usuario_email = usuario_email;
	}
	public String getNomeGerente() {
		return nomeGerente;
	}
	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + condominio_codigo;
		result = prime * result + ((condominio_nome == null) ? 0 : condominio_nome.hashCode());
		result = prime * result + ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result + ((dataInicial == null) ? 0 : dataInicial.hashCode());
		result = prime * result + ((dataInserido == null) ? 0 : dataInserido.hashCode());
		result = prime * result + minutos;
		result = prime * result + ((nomeGerente == null) ? 0 : nomeGerente.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((usuario_email == null) ? 0 : usuario_email.hashCode());
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
		intra_contabilizador other = (intra_contabilizador) obj;
		if (codigo != other.codigo)
			return false;
		if (condominio_codigo != other.condominio_codigo)
			return false;
		if (condominio_nome == null) {
			if (other.condominio_nome != null)
				return false;
		} else if (!condominio_nome.equals(other.condominio_nome))
			return false;
		if (dataFinal == null) {
			if (other.dataFinal != null)
				return false;
		} else if (!dataFinal.equals(other.dataFinal))
			return false;
		if (dataInicial == null) {
			if (other.dataInicial != null)
				return false;
		} else if (!dataInicial.equals(other.dataInicial))
			return false;
		if (dataInserido == null) {
			if (other.dataInserido != null)
				return false;
		} else if (!dataInserido.equals(other.dataInserido))
			return false;
		if (minutos != other.minutos)
			return false;
		if (nomeGerente == null) {
			if (other.nomeGerente != null)
				return false;
		} else if (!nomeGerente.equals(other.nomeGerente))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (usuario_email == null) {
			if (other.usuario_email != null)
				return false;
		} else if (!usuario_email.equals(other.usuario_email))
			return false;
		return true;
	}
	
	
}
