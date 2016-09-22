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
public class intra_requisicao_cobranca implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;

	@Column(columnDefinition = "varchar(75)")
	private String aosCuidados;

	@Column(columnDefinition = "varchar(15)")
	private String diaMalote;

	@Column(columnDefinition = "varchar(15)")
	private String correio;

	@Column(columnDefinition = "varchar(100)")
	private String endereco;

	@Column(columnDefinition = "varchar(130)")
	private String referencia;

	@Column(columnDefinition = "varchar(100)")
	private String cond_cod;

	@Column(columnDefinition = "int")
	private int codPredio;

	@Column(columnDefinition = "datetime")
	private Date feitoem;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConferido;

	@Column(columnDefinition = "smallint")
	private int codGerente;

	@Column(columnDefinition = "varchar(75)")
	private String nomGerente;

	@Column(columnDefinition = "varchar(15)")
	private String impressao;

	@Column(columnDefinition = "varchar(2)")
	private String sexo = "F";

	@Column(columnDefinition = "varchar(4)")
	private String bloco;

	@Column(columnDefinition = "varchar(6)")
	private String unidade;

	@Column(columnDefinition = "varchar(1)")
	private String tipoAC = "O";

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getAosCuidados() {
		return aosCuidados;
	}

	public void setAosCuidados(String aosCuidados) {
		this.aosCuidados = aosCuidados;
	}

	public String getDiaMalote() {
		return diaMalote;
	}

	public void setDiaMalote(String diaMalote) {
		this.diaMalote = diaMalote;
	}

	public String getCorreio() {
		return correio;
	}

	public void setCorreio(String correio) {
		this.correio = correio;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getcond_cod() {
		return cond_cod;
	}

	public void setcond_cod(String cond_cod) {
		this.cond_cod = cond_cod;
	}

	public Date getFeitoem() {
		return feitoem;
	}

	public void setFeitoem(Date feitoem) {
		this.feitoem = feitoem;
	}

	public Date getDataConferido() {
		return dataConferido;
	}

	public void setDataConferido(Date dataConferido) {
		this.dataConferido = dataConferido;
	}

	public int getCodPredio() {
		return codPredio;
	}

	public void setCodPredio(int codPredio) {
		this.codPredio = codPredio;
	}

	public int getCodGerente() {
		return codGerente;
	}

	public void setCodGerente(int codGerente) {
		this.codGerente = codGerente;
	}

	public String getNomGerente() {
		return nomGerente;
	}

	public void setNomGerente(String nomGerente) {
		this.nomGerente = nomGerente;
	}

	public String getImpressao() {
		return impressao;
	}

	public void setImpressao(String impressao) {
		this.impressao = impressao;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getTipoAC() {
		return tipoAC;
	}

	public void setTipoAC(String tipoAC) {
		this.tipoAC = tipoAC;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aosCuidados == null) ? 0 : aosCuidados.hashCode());
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + codGerente;
		result = prime * result + codPredio;
		result = prime * result + codigo;
		result = prime * result
				+ ((cond_cod == null) ? 0 : cond_cod.hashCode());
		result = prime * result + ((correio == null) ? 0 : correio.hashCode());
		result = prime * result
				+ ((dataConferido == null) ? 0 : dataConferido.hashCode());
		result = prime * result
				+ ((diaMalote == null) ? 0 : diaMalote.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((feitoem == null) ? 0 : feitoem.hashCode());
		result = prime * result
				+ ((impressao == null) ? 0 : impressao.hashCode());
		result = prime * result
				+ ((nomGerente == null) ? 0 : nomGerente.hashCode());
		result = prime * result
				+ ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((tipoAC == null) ? 0 : tipoAC.hashCode());
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
		intra_requisicao_cobranca other = (intra_requisicao_cobranca) obj;
		if (aosCuidados == null) {
			if (other.aosCuidados != null)
				return false;
		} else if (!aosCuidados.equals(other.aosCuidados))
			return false;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (codGerente != other.codGerente)
			return false;
		if (codPredio != other.codPredio)
			return false;
		if (codigo != other.codigo)
			return false;
		if (cond_cod == null) {
			if (other.cond_cod != null)
				return false;
		} else if (!cond_cod.equals(other.cond_cod))
			return false;
		if (correio == null) {
			if (other.correio != null)
				return false;
		} else if (!correio.equals(other.correio))
			return false;
		if (dataConferido == null) {
			if (other.dataConferido != null)
				return false;
		} else if (!dataConferido.equals(other.dataConferido))
			return false;
		if (diaMalote == null) {
			if (other.diaMalote != null)
				return false;
		} else if (!diaMalote.equals(other.diaMalote))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (feitoem == null) {
			if (other.feitoem != null)
				return false;
		} else if (!feitoem.equals(other.feitoem))
			return false;
		if (impressao == null) {
			if (other.impressao != null)
				return false;
		} else if (!impressao.equals(other.impressao))
			return false;
		if (nomGerente == null) {
			if (other.nomGerente != null)
				return false;
		} else if (!nomGerente.equals(other.nomGerente))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (tipoAC == null) {
			if (other.tipoAC != null)
				return false;
		} else if (!tipoAC.equals(other.tipoAC))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Cloning not allowed.");
			return this;
		}
	}

}