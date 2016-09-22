package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_log_geral implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(columnDefinition = "bigint")
	private int codigo;

	@Column(columnDefinition = "smallint")
	private int condominio;

	@Column(columnDefinition = "varchar(50)")
	private String ip;

	@Column(name = "feito_por", columnDefinition = "varchar(50)")
	private String feitoPor;

	@Column(columnDefinition = "varchar(20)")
	private String tabela;

	private boolean inserir, alterar, deletar;

	@Column(name = "info_anterior", columnDefinition = "varchar(max)")
	private String infoAnterior;

	@Column(name = "data_feito", columnDefinition = "datetime")
	private Date dataFeito;

	@Column(name = "nro_lancto", columnDefinition = "int")
	private int nroLancto;

	@Column(name = "aprovado_por", columnDefinition = "varchar(50)")
	private String aprovadoPor;

	@Column(name = "data_aprovacao", columnDefinition = "datetime")
	private Date dataAprovacao;

	public intra_log_geral() {

	}

	public intra_log_geral(int condominio, String feitoPor, String tabela,
			boolean inserir, boolean alterar, boolean deletar,
			String infoAnterior, Date dataFeito, int nroLancto,
			String aprovadoPor, Date dataAprovacao) {
		this.condominio = condominio;
		this.feitoPor = feitoPor;
		this.tabela = tabela;
		this.inserir = inserir;
		this.alterar = alterar;
		this.deletar = deletar;
		this.infoAnterior = infoAnterior;
		this.dataFeito = dataFeito;
		this.nroLancto = nroLancto;
		this.aprovadoPor = aprovadoPor;
		this.dataAprovacao = dataAprovacao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFeitoPor() {
		return feitoPor;
	}

	public void setFeitoPor(String feitoPor) {
		this.feitoPor = feitoPor;
	}

	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	public boolean isInserir() {
		return inserir;
	}

	public void setInserir(boolean inserir) {
		this.inserir = inserir;
	}

	public boolean isAlterar() {
		return alterar;
	}

	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}

	public boolean isDeletar() {
		return deletar;
	}

	public void setDeletar(boolean deletar) {
		this.deletar = deletar;
	}

	public String getInfoAnterior() {
		return infoAnterior;
	}

	public void setInfoAnterior(String infoAnterior) {
		this.infoAnterior = infoAnterior;
	}

	public Date getDataFeito() {
		return dataFeito;
	}

	public void setDataFeito(Date dataFeito) {
		this.dataFeito = dataFeito;
	}

	public int getNroLancto() {
		return nroLancto;
	}

	public void setNroLancto(int nroLancto) {
		this.nroLancto = nroLancto;
	}

	public String getAprovadoPor() {
		return aprovadoPor;
	}

	public void setAprovadoPor(String aprovadoPor) {
		this.aprovadoPor = aprovadoPor;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alterar ? 1231 : 1237);
		result = prime * result + ((aprovadoPor == null) ? 0 : aprovadoPor.hashCode());
		result = prime * result + codigo;
		result = prime * result + condominio;
		result = prime * result + ((dataAprovacao == null) ? 0 : dataAprovacao.hashCode());
		result = prime * result + ((dataFeito == null) ? 0 : dataFeito.hashCode());
		result = prime * result + (deletar ? 1231 : 1237);
		result = prime * result + ((feitoPor == null) ? 0 : feitoPor.hashCode());
		result = prime * result + ((infoAnterior == null) ? 0 : infoAnterior.hashCode());
		result = prime * result + (inserir ? 1231 : 1237);
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + nroLancto;
		result = prime * result + ((tabela == null) ? 0 : tabela.hashCode());
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
		intra_log_geral other = (intra_log_geral) obj;
		if (alterar != other.alterar)
			return false;
		if (aprovadoPor == null) {
			if (other.aprovadoPor != null)
				return false;
		} else if (!aprovadoPor.equals(other.aprovadoPor))
			return false;
		if (codigo != other.codigo)
			return false;
		if (condominio != other.condominio)
			return false;
		if (dataAprovacao == null) {
			if (other.dataAprovacao != null)
				return false;
		} else if (!dataAprovacao.equals(other.dataAprovacao))
			return false;
		if (dataFeito == null) {
			if (other.dataFeito != null)
				return false;
		} else if (!dataFeito.equals(other.dataFeito))
			return false;
		if (deletar != other.deletar)
			return false;
		if (feitoPor == null) {
			if (other.feitoPor != null)
				return false;
		} else if (!feitoPor.equals(other.feitoPor))
			return false;
		if (infoAnterior == null) {
			if (other.infoAnterior != null)
				return false;
		} else if (!infoAnterior.equals(other.infoAnterior))
			return false;
		if (inserir != other.inserir)
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (nroLancto != other.nroLancto)
			return false;
		if (tabela == null) {
			if (other.tabela != null)
				return false;
		} else if (!tabela.equals(other.tabela))
			return false;
		return true;
	}

}
