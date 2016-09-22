package br.com.oma.sigadm.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class intra_cndmemb implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition="smallint")
	private int condominio; 

	@Column(columnDefinition="varchar(4)")
	private String bloco; 
	
	@Column(columnDefinition="smallint")
	private int cargo; 
	
	@Column(columnDefinition="int")
	private int cliente;
	
	@Column(name="cli_bloco",columnDefinition="varchar(50)")
	private String cliBloco; 
	
	@Column(name="cliUnidade",columnDefinition="varchar(50)")
	private String cliUnidade;
	
	@Column(columnDefinition="int")
	private int termo;
	
	@Column(columnDefinition="int")
	private int situacao;
	
	@Column(name="cond_resp",columnDefinition="int")
	private int condResp;
	
	@Column(name="valor_base",columnDefinition="numeric(12,2")
	private double valorBase;
	
	@Column(name="valor_outros",columnDefinition="numeric(12,2")
	private double valorOutros;
	
	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public String getCliBloco() {
		return cliBloco;
	}

	public void setCliBloco(String cliBloco) {
		this.cliBloco = cliBloco;
	}
	
	public String getCliUnidade() {
		return cliUnidade;
	}

	public void setCliUnidade(String cliUnidade) {
		this.cliUnidade = cliUnidade;
	}

	public int getTermo() {
		return termo;
	}

	public void setTermo(int termo) {
		this.termo = termo;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public int getCondResp() {
		return condResp;
	}

	public void setCondResp(int condResp) {
		this.condResp = condResp;
	}

	public double getValorBase() {
		return valorBase;
	}

	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}

	public double getValorOutros() {
		return valorOutros;
	}

	public void setValorOutros(double valorOutros) {
		this.valorOutros = valorOutros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloco == null) ? 0 : bloco.hashCode());
		result = prime * result + cargo;
		result = prime * result
				+ ((cliBloco == null) ? 0 : cliBloco.hashCode());
		result = prime * result
				+ ((cliUnidade == null) ? 0 : cliUnidade.hashCode());
		result = prime * result + cliente;
		result = prime * result + condResp;
		result = prime * result + condominio;
		result = prime * result + situacao;
		result = prime * result + termo;
		long temp;
		temp = Double.doubleToLongBits(valorBase);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorOutros);
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
		intra_cndmemb other = (intra_cndmemb) obj;
		if (bloco == null) {
			if (other.bloco != null)
				return false;
		} else if (!bloco.equals(other.bloco))
			return false;
		if (cargo != other.cargo)
			return false;
		if (cliBloco == null) {
			if (other.cliBloco != null)
				return false;
		} else if (!cliBloco.equals(other.cliBloco))
			return false;
		if (cliUnidade == null) {
			if (other.cliUnidade != null)
				return false;
		} else if (!cliUnidade.equals(other.cliUnidade))
			return false;
		if (cliente != other.cliente)
			return false;
		if (condResp != other.condResp)
			return false;
		if (condominio != other.condominio)
			return false;
		if (situacao != other.situacao)
			return false;
		if (termo != other.termo)
			return false;
		if (Double.doubleToLongBits(valorBase) != Double
				.doubleToLongBits(other.valorBase))
			return false;
		if (Double.doubleToLongBits(valorOutros) != Double
				.doubleToLongBits(other.valorOutros))
			return false;
		return true;
	}
	
}
