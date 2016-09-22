package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_proc_jur_cob implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private int codigoCondominio;
	@Column(columnDefinition = "varchar(75)")
	private String nomeCondominio;
	@Column(columnDefinition = "varchar(5)")
	private String bloco;
	@Column(columnDefinition = "varchar(8)")
	private String unidade;
	private int codigoGerente;
	@Column(columnDefinition = "varchar(75)")
	private String nomeGerente;
	@Column(columnDefinition = "varchar(75)")
	private String nomeAdvogada;
	@Column(columnDefinition = "varchar(200)")
	private String parcelasAtrasadas;
	@Column(columnDefinition = "datetime")
	private Date pedProcuracao;
	@Column(columnDefinition = "datetime")
	private Date envProcuracao;
	@Column(columnDefinition = "datetime")
	private Date retornoSindico;
	@Column(columnDefinition = "varchar(500)")
	private String observacao;
	@Column(columnDefinition = "varchar(100)")
	private String status;
	@Column(columnDefinition = "datetime")
	private Date solicitacaoCRI;
	@Column(columnDefinition = "datetime")
	private Date retiradaCRI;
	@Column(columnDefinition = "varchar(100)")
	private String compraVenda;
	@Column(columnDefinition = "datetime")
	private Date dataIniciais;
	@Column(columnDefinition = "bit")
	private Short selecione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNomeAdvogada() {
		return nomeAdvogada;
	}

	public void setNomeAdvogada(String nomeAdvogada) {
		this.nomeAdvogada = nomeAdvogada;
	}

	public String getParcelasAtrasadas() {
		return parcelasAtrasadas;
	}

	public void setParcelasAtrasadas(String parcelasAtrasadas) {
		this.parcelasAtrasadas = parcelasAtrasadas;
	}

	public Date getPedProcuracao() {
		return pedProcuracao;
	}

	public void setPedProcuracao(Date pedProcuracao) {
		this.pedProcuracao = pedProcuracao;
	}

	public Date getEnvProcuracao() {
		return envProcuracao;
	}

	public void setEnvProcuracao(Date envProcuracao) {
		this.envProcuracao = envProcuracao;
	}

	public Date getRetornoSindico() {
		return retornoSindico;
	}

	public void setRetornoSindico(Date retornoSindico) {
		this.retornoSindico = retornoSindico;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSolicitacaoCRI() {
		return solicitacaoCRI;
	}

	public void setSolicitacaoCRI(Date solicitacaoCRI) {
		this.solicitacaoCRI = solicitacaoCRI;
	}

	public Date getRetiradaCRI() {
		return retiradaCRI;
	}

	public void setRetiradaCRI(Date retiradaCRI) {
		this.retiradaCRI = retiradaCRI;
	}

	public String getCompraVenda() {
		return compraVenda;
	}

	public void setCompraVenda(String compraVenda) {
		this.compraVenda = compraVenda;
	}

	public Date getDataIniciais() {
		return dataIniciais;
	}

	public void setDataIniciais(Date dataIniciais) {
		this.dataIniciais = dataIniciais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + codigoCondominio;
		result = prime * result + codigoGerente;
		result = prime * result + ((compraVenda == null) ? 0 : compraVenda.hashCode());
		result = prime * result + ((dataIniciais == null) ? 0 : dataIniciais.hashCode());
		result = prime * result + ((envProcuracao == null) ? 0 : envProcuracao.hashCode());
		result = prime * result + id;
		result = prime * result + ((nomeAdvogada == null) ? 0 : nomeAdvogada.hashCode());
		result = prime * result + ((nomeCondominio == null) ? 0 : nomeCondominio.hashCode());
		result = prime * result + ((nomeGerente == null) ? 0 : nomeGerente.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((parcelasAtrasadas == null) ? 0 : parcelasAtrasadas.hashCode());
		result = prime * result + ((pedProcuracao == null) ? 0 : pedProcuracao.hashCode());
		result = prime * result + ((retiradaCRI == null) ? 0 : retiradaCRI.hashCode());
		result = prime * result + ((retornoSindico == null) ? 0 : retornoSindico.hashCode());
		result = prime * result + ((solicitacaoCRI == null) ? 0 : solicitacaoCRI.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		intra_proc_jur_cob other = (intra_proc_jur_cob) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (codigoCondominio != other.codigoCondominio)
			return false;
		if (codigoGerente != other.codigoGerente)
			return false;
		if (compraVenda == null) {
			if (other.compraVenda != null)
				return false;
		} else if (!compraVenda.equals(other.compraVenda))
			return false;
		if (dataIniciais == null) {
			if (other.dataIniciais != null)
				return false;
		} else if (!dataIniciais.equals(other.dataIniciais))
			return false;
		if (envProcuracao == null) {
			if (other.envProcuracao != null)
				return false;
		} else if (!envProcuracao.equals(other.envProcuracao))
			return false;
		if (id != other.id)
			return false;
		if (nomeAdvogada == null) {
			if (other.nomeAdvogada != null)
				return false;
		} else if (!nomeAdvogada.equals(other.nomeAdvogada))
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
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (parcelasAtrasadas == null) {
			if (other.parcelasAtrasadas != null)
				return false;
		} else if (!parcelasAtrasadas.equals(other.parcelasAtrasadas))
			return false;
		if (pedProcuracao == null) {
			if (other.pedProcuracao != null)
				return false;
		} else if (!pedProcuracao.equals(other.pedProcuracao))
			return false;
		if (retiradaCRI == null) {
			if (other.retiradaCRI != null)
				return false;
		} else if (!retiradaCRI.equals(other.retiradaCRI))
			return false;
		if (retornoSindico == null) {
			if (other.retornoSindico != null)
				return false;
		} else if (!retornoSindico.equals(other.retornoSindico))
			return false;
		if (solicitacaoCRI == null) {
			if (other.solicitacaoCRI != null)
				return false;
		} else if (!solicitacaoCRI.equals(other.solicitacaoCRI))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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