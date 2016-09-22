package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class intra_candidato_vaga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "int")
	private int maxCandidatos;
	@Column(columnDefinition = "float")
	private double salario;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAbertura;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEncerramento;
	@Column(columnDefinition = "varchar(50)")
	private String solicitante;
	@Column(columnDefinition = "varchar(MAX)")
	private String rh;
	@Column(columnDefinition = "varchar(MAX)")
	private String obs;
	@Column(columnDefinition = "varchar(MAX)")
	private String beneficios;
	@Column(columnDefinition = "varchar(100)")
	private String cargo;
	@Column(columnDefinition = "varchar(100)")
	private String nomeGerente;
	@Column(columnDefinition = "varchar(100)")
	private String responsavelVaga;
	@Column(columnDefinition = "varchar(MAX)")
	private String preRequisitos;
	@Column(columnDefinition = "int")
	private int situacao;
	@ManyToMany(mappedBy = "vagas")
	private List<intra_candidato> candidatos = new ArrayList<intra_candidato>();
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("data DESC")
	private List<intra_candidato_vaga_historico> historico = new ArrayList<intra_candidato_vaga_historico>();
	@Column(columnDefinition = "int")
	private int codigoCondominio;
	@Column(columnDefinition = "varchar(150)")
	private String nomeCondominio;

	@Transient
	private String vagaCnd, vagaLbl, dataAberturaStr, dataEncerramentoStr;

	@Transient
	private boolean possuiCdt;

	@Transient
	private String cndCodNome;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getMaxCandidatos() {
		return maxCandidatos;
	}

	public void setMaxCandidatos(int maxCandidatos) {
		this.maxCandidatos = maxCandidatos;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getRh() {
		return rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public String getResponsavelVaga() {
		return responsavelVaga;
	}

	public void setResponsavelVaga(String responsavelVaga) {
		this.responsavelVaga = responsavelVaga;
	}

	public String getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(String preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public List<intra_candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<intra_candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public List<intra_candidato_vaga_historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<intra_candidato_vaga_historico> historico) {
		this.historico = historico;
	}

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public String getVagaCnd() {
		if (this.codigoCondominio > 0 && this.cargo != null) {
			return this.codigoCondominio + " - " + this.cargo;
		} else {
			return null;
		}
	}

	public void setVagaCnd(String vagaCnd) {
		this.vagaCnd = vagaCnd;
	}

	public String getVagaLbl() {
		DecimalFormat frmt = new DecimalFormat("###,###,###.00");
		if (this.codigoCondominio > 0 && this.cargo != null) {
			return this.codigoCondominio + " - " + this.cargo + " - R$" + frmt.format(this.salario) + " - "
					+ this.responsavelVaga;
		} else {
			return null;
		}
	}

	public void setVagaLbl(String vagaLbl) {
		this.vagaLbl = vagaLbl;
	}

	public String getDataAberturaStr() {
		if (this.dataAbertura != null) {
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			this.dataAberturaStr = sf.format(this.dataAbertura);
		}
		return dataAberturaStr;
	}

	public void setDataAberturaStr(String dataAberturaStr) {
		this.dataAberturaStr = dataAberturaStr;
	}

	public String getDataEncerramentoStr() {
		if (this.dataEncerramento != null) {
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			this.dataEncerramentoStr = sf.format(this.dataEncerramento);
		}
		return dataEncerramentoStr;
	}

	public void setDataEncerramentoStr(String dataEncerramentoStr) {
		this.dataEncerramentoStr = dataEncerramentoStr;
	}

	public boolean isPossuiCdt() {
		return possuiCdt;
	}

	public void setPossuiCdt(boolean possuiCdt) {
		this.possuiCdt = possuiCdt;
	}

	public String getCndCodNome() {
		if (this.codigoCondominio > 0 && this.nomeCondominio != null && !this.nomeCondominio.isEmpty()) {
			this.cndCodNome = this.codigoCondominio + " - " + this.nomeCondominio;
		}
		return cndCodNome;
	}

	public void setCndCodNome(String cndCodNome) {
		this.cndCodNome = cndCodNome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
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
		intra_candidato_vaga other = (intra_candidato_vaga) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

}
