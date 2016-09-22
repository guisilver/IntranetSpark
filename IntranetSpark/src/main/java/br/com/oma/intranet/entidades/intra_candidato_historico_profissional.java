package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class intra_candidato_historico_profissional implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(50)")
	private String condominio;
	@Column(columnDefinition = "varchar(100)")
	private String endereco;
	@Column(columnDefinition = "varchar(15)")
	private String telefone;
	@Column(columnDefinition = "varchar(50)")
	private String gestor;
	@Column(columnDefinition = "varchar(50)")
	private String cargo;
	@Column(columnDefinition = "float")
	private double salario;
	@Column(columnDefinition = "datetime")
	private Date dataAdimissao;
	@Column(columnDefinition = "datetime")
	private Date dataDemissao;
	@Column(columnDefinition = "varchar(500)")
	private String motivo;
	@Column(columnDefinition = "bit")
	private boolean imprimir;
	@ManyToOne
	private intra_candidato candidato;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCondominio() {
		return condominio;
	}

	public void setCondominio(String condominio) {
		this.condominio = condominio;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Date getDataAdimissao() {
		return dataAdimissao;
	}

	public void setDataAdimissao(Date dataAdimissao) {
		this.dataAdimissao = dataAdimissao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isImprimir() {
		return imprimir;
	}

	public void setImprimir(boolean imprimir) {
		this.imprimir = imprimir;
	}

	public intra_candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(intra_candidato candidato) {
		this.candidato = candidato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidato == null) ? 0 : candidato.hashCode());
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((condominio == null) ? 0 : condominio.hashCode());
		result = prime * result + ((dataAdimissao == null) ? 0 : dataAdimissao.hashCode());
		result = prime * result + ((dataDemissao == null) ? 0 : dataDemissao.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((gestor == null) ? 0 : gestor.hashCode());
		result = prime * result + (imprimir ? 1231 : 1237);
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salario);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		intra_candidato_historico_profissional other = (intra_candidato_historico_profissional) obj;
		if (candidato == null) {
			if (other.candidato != null)
				return false;
		} else if (!candidato.equals(other.candidato))
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (codigo != other.codigo)
			return false;
		if (condominio == null) {
			if (other.condominio != null)
				return false;
		} else if (!condominio.equals(other.condominio))
			return false;
		if (dataAdimissao == null) {
			if (other.dataAdimissao != null)
				return false;
		} else if (!dataAdimissao.equals(other.dataAdimissao))
			return false;
		if (dataDemissao == null) {
			if (other.dataDemissao != null)
				return false;
		} else if (!dataDemissao.equals(other.dataDemissao))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (gestor == null) {
			if (other.gestor != null)
				return false;
		} else if (!gestor.equals(other.gestor))
			return false;
		if (imprimir != other.imprimir)
			return false;
		if (motivo == null) {
			if (other.motivo != null)
				return false;
		} else if (!motivo.equals(other.motivo))
			return false;
		if (Double.doubleToLongBits(salario) != Double.doubleToLongBits(other.salario))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
