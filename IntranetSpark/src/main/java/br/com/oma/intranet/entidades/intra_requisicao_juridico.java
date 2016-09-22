package br.com.oma.intranet.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class intra_requisicao_juridico implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	
	@Column(columnDefinition = "smallint")
	private int codGerente;
	
	@Column(columnDefinition = "varchar(75)")
	private String nomJuridico;

	@Column(columnDefinition = "varchar(50)")
	private String nomJuridico_email;
	
	@Column(columnDefinition = "varchar(75)")
	private String sindico;
	
	@Column(columnDefinition = "int")
	private int codPredio;
	
	@Column(columnDefinition = "varchar(100)")
	private String cond_cod;

	@Column(columnDefinition = "varchar(15)")
	private String diaMalote;

	@Column(columnDefinition = "varchar(15)")
	private String correio;
	
	@Column(columnDefinition = "varchar(100)")
	private String endereco;

	@Column(columnDefinition = "varchar(130)")
	private String referencia;

	@Column(columnDefinition = "varchar(15)")
	private String impressao;

	@Column(columnDefinition = "varchar(2)")
	private String sexo = "F";

	@Column(columnDefinition = "varchar(1)")
	private String tipoAC = "O";

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodGerente() {
		return codGerente;
	}

	public void setCodGerente(int codGerente) {
		this.codGerente = codGerente;
	}

	public String getNomJuridico() {
		return nomJuridico;
	}

	public void setNomJuridico(String nomJuridico) {
		this.nomJuridico = nomJuridico;
	}

	public String getNomJuridico_email() {
		return nomJuridico_email;
	}

	public void setNomJuridico_email(String nomJuridico_email) {
		this.nomJuridico_email = nomJuridico_email;
	}

	public String getSindico() {
		return sindico;
	}

	public void setSindico(String sindico) {
		this.sindico = sindico;
	}

	public int getCodPredio() {
		return codPredio;
	}

	public void setCodPredio(int codPredio) {
		this.codPredio = codPredio;
	}

	public String getCond_cod() {
		return cond_cod;
	}

	public void setCond_cod(String cond_cod) {
		this.cond_cod = cond_cod;
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

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
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

	public String getTipoAC() {
		return tipoAC;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codGerente;
		result = prime * result + codPredio;
		result = prime * result + codigo;
		result = prime * result + ((cond_cod == null) ? 0 : cond_cod.hashCode());
		result = prime * result + ((correio == null) ? 0 : correio.hashCode());
		result = prime * result + ((diaMalote == null) ? 0 : diaMalote.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((impressao == null) ? 0 : impressao.hashCode());
		result = prime * result + ((nomJuridico == null) ? 0 : nomJuridico.hashCode());
		result = prime * result + ((nomJuridico_email == null) ? 0 : nomJuridico_email.hashCode());
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((sindico == null) ? 0 : sindico.hashCode());
		result = prime * result + ((tipoAC == null) ? 0 : tipoAC.hashCode());
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
		intra_requisicao_juridico other = (intra_requisicao_juridico) obj;
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
		if (impressao == null) {
			if (other.impressao != null)
				return false;
		} else if (!impressao.equals(other.impressao))
			return false;
		if (nomJuridico == null) {
			if (other.nomJuridico != null)
				return false;
		} else if (!nomJuridico.equals(other.nomJuridico))
			return false;
		if (nomJuridico_email == null) {
			if (other.nomJuridico_email != null)
				return false;
		} else if (!nomJuridico_email.equals(other.nomJuridico_email))
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
		if (sindico == null) {
			if (other.sindico != null)
				return false;
		} else if (!sindico.equals(other.sindico))
			return false;
		if (tipoAC == null) {
			if (other.tipoAC != null)
				return false;
		} else if (!tipoAC.equals(other.tipoAC))
			return false;
		return true;
	}

	public void setTipoAC(String tipoAC) {
		this.tipoAC = tipoAC;
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