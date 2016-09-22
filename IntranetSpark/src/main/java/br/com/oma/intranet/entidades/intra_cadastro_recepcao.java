package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class intra_cadastro_recepcao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(15)")
	private String rg;
	@Column(columnDefinition = "varchar(50)")
	private String nome;
	@Column(columnDefinition = "varchar(11)")
	private String cpf;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	@Column(columnDefinition = "varchar(50)")
	private String empresa;
	@Column(columnDefinition = "varchar(15)")
	private String celular;
	@Column(columnDefinition = "varchar(15)")
	private String telResidencial;
	@Column(columnDefinition = "varchar(500)")
	private String obs;
	@Column(columnDefinition = "varchar(50)")
	private String tipoVisita;
	@Column(columnDefinition = "varchar(50)")
	private String cargoPretendido;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrada;

	@Transient
	private String vencimentoString;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelResidencial() {
		return telResidencial;
	}

	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getTipoVisita() {
		return tipoVisita;
	}

	public void setTipoVisita(String tipoVisita) {
		this.tipoVisita = tipoVisita;
	}

	public String getCargoPretendido() {
		return cargoPretendido;
	}

	public void setCargoPretendido(String cargoPretendido) {
		this.cargoPretendido = cargoPretendido;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataEntradaString() {
		if (this.dataEntrada != null) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			this.vencimentoString = dateFormat.format(this.dataEntrada);
		}
		return vencimentoString;
	}

	public void setDataEntradaString(String vencimentoString) {
		this.vencimentoString = vencimentoString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargoPretendido == null) ? 0 : cargoPretendido.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((telResidencial == null) ? 0 : telResidencial.hashCode());
		result = prime * result + ((tipoVisita == null) ? 0 : tipoVisita.hashCode());
		result = prime * result + ((vencimentoString == null) ? 0 : vencimentoString.hashCode());
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
		intra_cadastro_recepcao other = (intra_cadastro_recepcao) obj;
		if (cargoPretendido == null) {
			if (other.cargoPretendido != null)
				return false;
		} else if (!cargoPretendido.equals(other.cargoPretendido))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (codigo != other.codigo)
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (telResidencial == null) {
			if (other.telResidencial != null)
				return false;
		} else if (!telResidencial.equals(other.telResidencial))
			return false;
		if (tipoVisita == null) {
			if (other.tipoVisita != null)
				return false;
		} else if (!tipoVisita.equals(other.tipoVisita))
			return false;
		if (vencimentoString == null) {
			if (other.vencimentoString != null)
				return false;
		} else if (!vencimentoString.equals(other.vencimentoString))
			return false;
		return true;
	}

}
