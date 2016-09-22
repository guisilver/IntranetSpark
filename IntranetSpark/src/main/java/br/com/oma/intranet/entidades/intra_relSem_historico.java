package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class intra_relSem_historico implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(columnDefinition="bigint")
	private int codigo;
	
	@Column(name="condominio",columnDefinition="smallint")
	private int condominio;
	
	@Column(name="nome_condominio", columnDefinition="varchar(50)")
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
	private boolean contato_tel;
	
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
	
	@Transient
	private Date dataInicial;
	@Transient
	private Date dataFinal;
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the condominio
	 */
	public int getCondominio() {
		return condominio;
	}
	/**
	 * @param condominio the condominio to set
	 */
	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}
	/**
	 * @return the nomeCondominio
	 */
	public String getNomeCondominio() {
		return nomeCondominio;
	}
	/**
	 * @param nomeCondominio the nomeCondominio to set
	 */
	public void setNomeCondominio(String nomeCondominio) {
		this.nomeCondominio = nomeCondominio;
	}
	/**
	 * @return the nomeSindico
	 */
	public String getNomeSindico() {
		return nomeSindico;
	}
	/**
	 * @param nomeSindico the nomeSindico to set
	 */
	public void setNomeSindico(String nomeSindico) {
		this.nomeSindico = nomeSindico;
	}
	/**
	 * @return the nomeZelador
	 */
	public String getNomeZelador() {
		return nomeZelador;
	}
	/**
	 * @param nomeZelador the nomeZelador to set
	 */
	public void setNomeZelador(String nomeZelador) {
		this.nomeZelador = nomeZelador;
	}
	/**
	 * @return the receitaFederal
	 */
	public short getReceitaFederal() {
		return receitaFederal;
	}
	/**
	 * @param receitaFederal the receitaFederal to set
	 */
	public void setReceitaFederal(short receitaFederal) {
		this.receitaFederal = receitaFederal;
	}
	/**
	 * @return the obs
	 */
	public String getObs() {
		return obs;
	}
	/**
	 * @param obs the obs to set
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}
	/**
	 * @return the saldoAnterior
	 */
	public String getSaldoAnterior() {
		return saldoAnterior;
	}
	/**
	 * @param saldoAnterior the saldoAnterior to set
	 */
	public void setSaldoAnterior(String saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}
	/**
	 * @return the taxaAdm
	 */
	public double getTaxaAdm() {
		return taxaAdm;
	}
	/**
	 * @param taxaAdm the taxaAdm to set
	 */
	public void setTaxaAdm(double taxaAdm) {
		this.taxaAdm = taxaAdm;
	}
	/**
	 * @return the finalMandato
	 */
	public Date getFinalMandato() {
		return finalMandato;
	}
	/**
	 * @param finalMandato the finalMandato to set
	 */
	public void setFinalMandato(Date finalMandato) {
		this.finalMandato = finalMandato;
	}
	/**
	 * @return the proxAg
	 */
	public Date getProxAg() {
		return proxAg;
	}
	/**
	 * @param proxAg the proxAg to set
	 */
	public void setProxAg(Date proxAg) {
		this.proxAg = proxAg;
	}
	/**
	 * @return the contatoPessoal
	 */
	public boolean isContatoPessoal() {
		return contatoPessoal;
	}
	/**
	 * @param contatoPessoal the contatoPessoal to set
	 */
	public void setContatoPessoal(boolean contatoPessoal) {
		this.contatoPessoal = contatoPessoal;
	}
	/**
	 * @return the contato_tel
	 */
	public boolean isContato_tel() {
		return contato_tel;
	}
	/**
	 * @param contato_tel the contato_tel to set
	 */
	public void setContato_tel(boolean contato_tel) {
		this.contato_tel = contato_tel;
	}
	/**
	 * @return the contatoEmail
	 */
	public boolean isContatoEmail() {
		return contatoEmail;
	}
	/**
	 * @param contatoEmail the contatoEmail to set
	 */
	public void setContatoEmail(boolean contatoEmail) {
		this.contatoEmail = contatoEmail;
	}
	/**
	 * @return the satisfacao
	 */
	public short getSatisfacao() {
		return satisfacao;
	}
	/**
	 * @param satisfacao the satisfacao to set
	 */
	public void setSatisfacao(short satisfacao) {
		this.satisfacao = satisfacao;
	}
	/**
	 * @return the codigoGerente
	 */
	public int getCodigoGerente() {
		return codigoGerente;
	}
	/**
	 * @param codigoGerente the codigoGerente to set
	 */
	public void setCodigoGerente(int codigoGerente) {
		this.codigoGerente = codigoGerente;
	}
	/**
	 * @return the nomeGerente
	 */
	public String getNomeGerente() {
		return nomeGerente;
	}
	/**
	 * @param nomeGerente the nomeGerente to set
	 */
	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}
	/**
	 * @return the dataSalvou
	 */
	public Date getDataSalvou() {
		return dataSalvou;
	}
	/**
	 * @param dataSalvou the dataSalvou to set
	 */
	public void setDataSalvou(Date dataSalvou) {
		this.dataSalvou = dataSalvou;
	}
	
	/**
	 * @return the dataInicial
	 */
	public Date getDataInicial() {
		return dataInicial;
	}
	/**
	 * @param dataInicial the dataInicial to set
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	/**
	 * @return the dataFinal
	 */
	public Date getDataFinal() {
		return dataFinal;
	}
	/**
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + codigoGerente;
		result = prime * result + condominio;
		result = prime * result + (contatoEmail ? 1231 : 1237);
		result = prime * result + (contatoPessoal ? 1231 : 1237);
		result = prime * result + (contato_tel ? 1231 : 1237);
		result = prime * result
				+ ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result
				+ ((dataInicial == null) ? 0 : dataInicial.hashCode());
		result = prime * result
				+ ((dataSalvou == null) ? 0 : dataSalvou.hashCode());
		result = prime * result
				+ ((finalMandato == null) ? 0 : finalMandato.hashCode());
		result = prime * result
				+ ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result
				+ ((nomeGerente == null) ? 0 : nomeGerente.hashCode());
		result = prime * result
				+ ((nomeSindico == null) ? 0 : nomeSindico.hashCode());
		result = prime * result
				+ ((nomeZelador == null) ? 0 : nomeZelador.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((proxAg == null) ? 0 : proxAg.hashCode());
		result = prime * result + receitaFederal;
		result = prime * result
				+ ((saldoAnterior == null) ? 0 : saldoAnterior.hashCode());
		result = prime * result + satisfacao;
		long temp;
		temp = Double.doubleToLongBits(taxaAdm);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof intra_relSem_historico)) {
			return false;
		}
		intra_relSem_historico other = (intra_relSem_historico) obj;
		if (codigo != other.codigo) {
			return false;
		}
		if (codigoGerente != other.codigoGerente) {
			return false;
		}
		if (condominio != other.condominio) {
			return false;
		}
		if (contatoEmail != other.contatoEmail) {
			return false;
		}
		if (contatoPessoal != other.contatoPessoal) {
			return false;
		}
		if (contato_tel != other.contato_tel) {
			return false;
		}
		if (dataFinal == null) {
			if (other.dataFinal != null) {
				return false;
			}
		} else if (!dataFinal.equals(other.dataFinal)) {
			return false;
		}
		if (dataInicial == null) {
			if (other.dataInicial != null) {
				return false;
			}
		} else if (!dataInicial.equals(other.dataInicial)) {
			return false;
		}
		if (dataSalvou == null) {
			if (other.dataSalvou != null) {
				return false;
			}
		} else if (!dataSalvou.equals(other.dataSalvou)) {
			return false;
		}
		if (finalMandato == null) {
			if (other.finalMandato != null) {
				return false;
			}
		} else if (!finalMandato.equals(other.finalMandato)) {
			return false;
		}
		if (nomeCondominio == null) {
			if (other.nomeCondominio != null) {
				return false;
			}
		} else if (!nomeCondominio.equals(other.nomeCondominio)) {
			return false;
		}
		if (nomeGerente == null) {
			if (other.nomeGerente != null) {
				return false;
			}
		} else if (!nomeGerente.equals(other.nomeGerente)) {
			return false;
		}
		if (nomeSindico == null) {
			if (other.nomeSindico != null) {
				return false;
			}
		} else if (!nomeSindico.equals(other.nomeSindico)) {
			return false;
		}
		if (nomeZelador == null) {
			if (other.nomeZelador != null) {
				return false;
			}
		} else if (!nomeZelador.equals(other.nomeZelador)) {
			return false;
		}
		if (obs == null) {
			if (other.obs != null) {
				return false;
			}
		} else if (!obs.equals(other.obs)) {
			return false;
		}
		if (proxAg == null) {
			if (other.proxAg != null) {
				return false;
			}
		} else if (!proxAg.equals(other.proxAg)) {
			return false;
		}
		if (receitaFederal != other.receitaFederal) {
			return false;
		}
		if (saldoAnterior == null) {
			if (other.saldoAnterior != null) {
				return false;
			}
		} else if (!saldoAnterior.equals(other.saldoAnterior)) {
			return false;
		}
		if (satisfacao != other.satisfacao) {
			return false;
		}
		if (Double.doubleToLongBits(taxaAdm) != Double
				.doubleToLongBits(other.taxaAdm)) {
			return false;
		}
		return true;
	}

}
