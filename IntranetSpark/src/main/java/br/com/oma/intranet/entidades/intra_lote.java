package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class intra_lote implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int numeroLote;
	@Column(columnDefinition = "varchar(50)")
	private String nomeLote;
	@Column(columnDefinition = "varchar(100)")
	private String servico;
	@Column(columnDefinition = "varchar(100)")
	private String referencia;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnviado;
	@Column(columnDefinition = "bit")
	private boolean conferido;
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@OrderBy("nomeArquivo ASC")
	private List<intra_lote_arquivos> arquivos = new ArrayList<intra_lote_arquivos>();
	@OneToOne(mappedBy = "lote", cascade = { CascadeType.ALL })
	private intra_emissor emissor;

	public intra_lote() {

	}

	public intra_lote(int codigo, int numeroLote, String nomeLote, String servico,
			String referencia, Date dataEnviado, boolean conferido,
			List<intra_lote_arquivos> arquivos, intra_emissor emissor) {
		this.codigo = codigo;
		this.numeroLote = numeroLote;
		this.nomeLote = nomeLote;
		this.servico = servico;
		this.referencia = referencia;
		this.dataEnviado = dataEnviado;
		this.conferido = conferido;
		this.emissor = emissor;
		this.setArquivos(arquivos);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(int numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getNomeLote() {
		return nomeLote;
	}

	public void setNomeLote(String nomeLote) {
		this.nomeLote = nomeLote;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Date getDataEnviado() {
		return dataEnviado;
	}

	public void setDataEnviado(Date dataEnviado) {
		this.dataEnviado = dataEnviado;
	}

	public boolean isConferido() {
		return conferido;
	}

	public void setConferido(boolean conferido) {
		this.conferido = conferido;
	}

	public List<intra_lote_arquivos> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<intra_lote_arquivos> arquivos) {
		this.arquivos = arquivos;
	}

	public intra_emissor getEmissor() {
		return emissor;
	}

	public void setEmissor(intra_emissor emissor) {
		this.emissor = emissor;
	}

	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(),
				getCodigo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivos == null) ? 0 : arquivos.hashCode());
		result = prime * result + codigo;
		result = prime * result + (conferido ? 1231 : 1237);
		result = prime * result + ((dataEnviado == null) ? 0 : dataEnviado.hashCode());
		result = prime * result + ((emissor == null) ? 0 : emissor.hashCode());
		result = prime * result + ((nomeLote == null) ? 0 : nomeLote.hashCode());
		result = prime * result + numeroLote;
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((servico == null) ? 0 : servico.hashCode());
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
		intra_lote other = (intra_lote) obj;
		if (arquivos == null) {
			if (other.arquivos != null)
				return false;
		} else if (!arquivos.equals(other.arquivos))
			return false;
		if (codigo != other.codigo)
			return false;
		if (conferido != other.conferido)
			return false;
		if (dataEnviado == null) {
			if (other.dataEnviado != null)
				return false;
		} else if (!dataEnviado.equals(other.dataEnviado))
			return false;
		if (emissor == null) {
			if (other.emissor != null)
				return false;
		} else if (!emissor.equals(other.emissor))
			return false;
		if (nomeLote == null) {
			if (other.nomeLote != null)
				return false;
		} else if (!nomeLote.equals(other.nomeLote))
			return false;
		if (numeroLote != other.numeroLote)
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (servico == null) {
			if (other.servico != null)
				return false;
		} else if (!servico.equals(other.servico))
			return false;
		return true;
	}
	

}