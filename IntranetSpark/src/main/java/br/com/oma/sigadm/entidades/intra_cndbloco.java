package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class intra_cndbloco implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int condominio;
	
	@Column(name="inicio_mandato",columnDefinition="datetime")
	private Date inicioMandato;
	
	@Column(name="final_mandato",columnDefinition="datetime")
	private Date finalMandato;
	
	@Column(name="prazo_assembl",columnDefinition="varchar(30)")
	private String prazoAssembl;
	
	@Column(name="quorum_primeira",columnDefinition="varchar(20)")
	private String quorumPrimeira;
	
	@Column(name="segunda_chamada",columnDefinition="varchar(20)")
	private String segundaChamada;
	
	@Column(name="quorum_segunda",columnDefinition="varchar(20)")
	private String quorumSegunda;
	
	@Column(name="dt_aprovacao",columnDefinition="datetime")
	private Date dtAprovacao;
	
	@Column(name="prx_aprovacao",columnDefinition="datetime")
	private Date prxAprovacao;
	
	@Column(name="obs_1",columnDefinition="varchar(512)")
	private String obs1;

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public Date getInicioMandato() {
		return inicioMandato;
	}

	public void setInicioMandato(Date inicioMandato) {
		this.inicioMandato = inicioMandato;
	}

	public Date getFinalMandato() {
		return finalMandato;
	}

	public void setFinalMandato(Date finalMandato) {
		this.finalMandato = finalMandato;
	}

	public String getPrazoAssembl() {
		return prazoAssembl;
	}

	public void setPrazoAssembl(String prazoAssembl) {
		this.prazoAssembl = prazoAssembl;
	}

	public String getSegundaChamada() {
		return segundaChamada;
	}

	public void setSegundaChamada(String segundaChamada) {
		this.segundaChamada = segundaChamada;
	}

	public String getQuorumSegunda() {
		return quorumSegunda;
	}

	public void setQuorumSegunda(String quorumSegunda) {
		this.quorumSegunda = quorumSegunda;
	}

	public Date getDtAprovacao() {
		return dtAprovacao;
	}

	public void setDtAprovacao(Date dtAprovacao) {
		this.dtAprovacao = dtAprovacao;
	}

	public Date getPrxAprovacao() {
		return prxAprovacao;
	}

	public void setPrxAprovacao(Date prxAprovacao) {
		this.prxAprovacao = prxAprovacao;
	}

	public String getObs1() {
		return obs1;
	}

	public void setObs1(String obs1) {
		this.obs1 = obs1;
	}

	public String getQuorumPrimeira() {
		return quorumPrimeira;
	}

	public void setQuorumPrimeira(String quorumPrimeira) {
		this.quorumPrimeira = quorumPrimeira;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + condominio;
		result = prime * result
				+ ((dtAprovacao == null) ? 0 : dtAprovacao.hashCode());
		result = prime * result
				+ ((finalMandato == null) ? 0 : finalMandato.hashCode());
		result = prime * result
				+ ((inicioMandato == null) ? 0 : inicioMandato.hashCode());
		result = prime * result + ((obs1 == null) ? 0 : obs1.hashCode());
		result = prime * result
				+ ((prazoAssembl == null) ? 0 : prazoAssembl.hashCode());
		result = prime * result
				+ ((prxAprovacao == null) ? 0 : prxAprovacao.hashCode());
		result = prime * result
				+ ((quorumPrimeira == null) ? 0 : quorumPrimeira.hashCode());
		result = prime * result
				+ ((quorumSegunda == null) ? 0 : quorumSegunda.hashCode());
		result = prime * result
				+ ((segundaChamada == null) ? 0 : segundaChamada.hashCode());
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
		intra_cndbloco other = (intra_cndbloco) obj;
		if (condominio != other.condominio)
			return false;
		if (dtAprovacao == null) {
			if (other.dtAprovacao != null)
				return false;
		} else if (!dtAprovacao.equals(other.dtAprovacao))
			return false;
		if (finalMandato == null) {
			if (other.finalMandato != null)
				return false;
		} else if (!finalMandato.equals(other.finalMandato))
			return false;
		if (inicioMandato == null) {
			if (other.inicioMandato != null)
				return false;
		} else if (!inicioMandato.equals(other.inicioMandato))
			return false;
		if (obs1 == null) {
			if (other.obs1 != null)
				return false;
		} else if (!obs1.equals(other.obs1))
			return false;
		if (prazoAssembl == null) {
			if (other.prazoAssembl != null)
				return false;
		} else if (!prazoAssembl.equals(other.prazoAssembl))
			return false;
		if (prxAprovacao == null) {
			if (other.prxAprovacao != null)
				return false;
		} else if (!prxAprovacao.equals(other.prxAprovacao))
			return false;
		if (quorumPrimeira == null) {
			if (other.quorumPrimeira != null)
				return false;
		} else if (!quorumPrimeira.equals(other.quorumPrimeira))
			return false;
		if (quorumSegunda == null) {
			if (other.quorumSegunda != null)
				return false;
		} else if (!quorumSegunda.equals(other.quorumSegunda))
			return false;
		if (segundaChamada == null) {
			if (other.segundaChamada != null)
				return false;
		} else if (!segundaChamada.equals(other.segundaChamada))
			return false;
		return true;
	}
	
}
