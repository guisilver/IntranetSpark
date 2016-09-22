package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class intra_relatorio_semanal implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="smallint")
	private int codigo;
	
	@Column(name="nome_condominio",columnDefinition="varchar(50)")
	private String nomeCondominio;
	
	@Column(name="nome_sindico",columnDefinition="varchar(50)")
	private String nomeSindico;
	
	@Column(name="nome_zelador",columnDefinition="varchar(50)")
	private String nomeZelador;
	
	@Column(name="receita_Federal",columnDefinition="tinyint")
	private short receitaFederal;
	
	@Column(columnDefinition="varchar(max)")
	private String obs;
	
	@Column(name="saldo_anterior",columnDefinition="varchar(30)")
	private String saldoAnterior;
	
	@Column(name="taxa_adm",columnDefinition="numeric(10,2)")
	private double taxaAdm;
	
	@Column(name="final_mandato",columnDefinition="datetime")
	private Date finalMandato;
	
	@Column(name="prox_ag",columnDefinition="datetime")
	private Date proxAg;
	
	@Column(name="contato_pessoal",columnDefinition="bit")
	private boolean contatoPessoal;
	
	@Column(name="contato_tel",columnDefinition="bit")
	private boolean contatoTel;
	
	@Column(name="contato_email",columnDefinition="bit")
	private boolean contatoEmail;
	
	@Column(columnDefinition="tinyint")
	private short satisfacao;
	
	@Column(name="codigo_gerente", columnDefinition="int")
	private int codigoGerente;
	
	@Column(name="nome_gerente",columnDefinition="varchar(50)")
	private String nomeGerente;

	@Column(name="dataSalvou", columnDefinition="datetime")
	private Date dataSalvou;
	
	@Column(name="ideia_condominio", columnDefinition="varchar(max)")
	private String ideiaCondominio;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeCondominio() {
		return nomeCondominio;
	}

	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}

	public String getNomeSindico() {
		return nomeSindico;
	}

	public void setNomeSindico(String nomeSindico) {
		this.nomeSindico = nomeSindico;
	}

	public String getNomeZelador() {
		return nomeZelador;
	}

	public void setNomeZelador(String nomeZelador) {
		this.nomeZelador = nomeZelador;
	}

	public short getReceitaFederal() {
		return receitaFederal;
	}

	public void setReceitaFederal(short receitaFederal) {
		this.receitaFederal = receitaFederal;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(String saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public double getTaxaAdm() {
		return taxaAdm;
	}

	public void setTaxaAdm(double taxaAdm) {
		this.taxaAdm = taxaAdm;
	}

	public Date getFinalMandato() {
		return finalMandato;
	}

	public void setFinalMandato(Date finalMandato) {
		this.finalMandato = finalMandato;
	}

	public Date getProxAg() {
		return proxAg;
	}

	public void setProxAg(Date proxAg) {
		this.proxAg = proxAg;
	}

	public boolean isContatoPessoal() {
		return contatoPessoal;
	}

	public void setContatoPessoal(boolean contatoPessoal) {
		this.contatoPessoal = contatoPessoal;
	}

	public boolean isContatoTel() {
		return contatoTel;
	}

	public void setContatoTel(boolean contatoTel) {
		this.contatoTel = contatoTel;
	}

	public boolean isContatoEmail() {
		return contatoEmail;
	}

	public void setContatoEmail(boolean contatoEmail) {
		this.contatoEmail = contatoEmail;
	}

	public short getSatisfacao() {
		return satisfacao;
	}

	public void setSatisfacao(short satisfacao) {
		this.satisfacao = satisfacao;
	}

	public int getCodigoGerente() {
		return codigoGerente;
	}

	public void setCodigoGerente(int codigoGerente) {
		this.codigoGerente = codigoGerente;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public Date getDataSalvou() {
		return dataSalvou;
	}

	public void setDataSalvou(Date dataSalvou) {
		this.dataSalvou = dataSalvou;
	}

	public String getIdeiaCondominio() {
		return ideiaCondominio;
	}

	public void setIdeiaCondominio(String ideiaCondominio) {
		this.ideiaCondominio = ideiaCondominio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + codigoGerente;
		result = prime * result + (contatoEmail ? 1231 : 1237);
		result = prime * result + (contatoPessoal ? 1231 : 1237);
		result = prime * result + (contatoTel ? 1231 : 1237);
		result = prime * result + ((dataSalvou == null) ? 0 : dataSalvou.hashCode());
		result = prime * result + ((finalMandato == null) ? 0 : finalMandato.hashCode());
		result = prime * result + ((ideiaCondominio == null) ? 0 : ideiaCondominio.hashCode());
		result = prime * result + ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result + ((nomeGerente == null) ? 0 : nomeGerente.hashCode());
		result = prime * result + ((nomeSindico == null) ? 0 : nomeSindico.hashCode());
		result = prime * result + ((nomeZelador == null) ? 0 : nomeZelador.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((proxAg == null) ? 0 : proxAg.hashCode());
		result = prime * result + receitaFederal;
		result = prime * result + ((saldoAnterior == null) ? 0 : saldoAnterior.hashCode());
		result = prime * result + satisfacao;
		long temp;
		temp = Double.doubleToLongBits(taxaAdm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		intra_relatorio_semanal other = (intra_relatorio_semanal) obj;
		if (codigo != other.codigo)
			return false;
		if (codigoGerente != other.codigoGerente)
			return false;
		if (contatoEmail != other.contatoEmail)
			return false;
		if (contatoPessoal != other.contatoPessoal)
			return false;
		if (contatoTel != other.contatoTel)
			return false;
		if (dataSalvou == null) {
			if (other.dataSalvou != null)
				return false;
		} else if (!dataSalvou.equals(other.dataSalvou))
			return false;
		if (finalMandato == null) {
			if (other.finalMandato != null)
				return false;
		} else if (!finalMandato.equals(other.finalMandato))
			return false;
		if (ideiaCondominio == null) {
			if (other.ideiaCondominio != null)
				return false;
		} else if (!ideiaCondominio.equals(other.ideiaCondominio))
			return false;
		if (nomeCondominio == null) {
			if (other.nomeCondominio != null)
				return false;
		} else if (!nomeCondominio.equals(other.nomeCondominio))
			return false;
		if (nomeGerente == null) {
			if (other.nomeGerente != null)
				return false;
		} else if (!nomeGerente.equals(other.nomeGerente))
			return false;
		if (nomeSindico == null) {
			if (other.nomeSindico != null)
				return false;
		} else if (!nomeSindico.equals(other.nomeSindico))
			return false;
		if (nomeZelador == null) {
			if (other.nomeZelador != null)
				return false;
		} else if (!nomeZelador.equals(other.nomeZelador))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (proxAg == null) {
			if (other.proxAg != null)
				return false;
		} else if (!proxAg.equals(other.proxAg))
			return false;
		if (receitaFederal != other.receitaFederal)
			return false;
		if (saldoAnterior == null) {
			if (other.saldoAnterior != null)
				return false;
		} else if (!saldoAnterior.equals(other.saldoAnterior))
			return false;
		if (satisfacao != other.satisfacao)
			return false;
		if (Double.doubleToLongBits(taxaAdm) != Double.doubleToLongBits(other.taxaAdm))
			return false;
		return true;
	}

	

}
