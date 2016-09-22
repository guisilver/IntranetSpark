package br.com.oma.sigadm.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class intra_iltelcli implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int cliente;
	@Column(columnDefinition="tinyint")
	private int tipo;
	@Column(columnDefinition="varchar(50)")
	private String telEmail;
	@Column(columnDefinition="varchar(50)")
	private String observacao;
	@Column(columnDefinition="tinyint")
	private int flagCadastro;
	@Column(columnDefinition="tinyint")
	private int boleto_envio;
	@Column(columnDefinition="int")
	private int dfrecnum;
	
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getTelEmail() {
		return telEmail;
	}
	public void setTelEmail(String telEmail) {
		this.telEmail = telEmail;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public int getFlagCadastro() {
		return flagCadastro;
	}
	public void setFlagCadastro(int flagCadastro) {
		this.flagCadastro = flagCadastro;
	}
	public int getBoleto_envio() {
		return boleto_envio;
	}
	public void setBoleto_envio(int boleto_envio) {
		this.boleto_envio = boleto_envio;
	}
	
	public int getDfrecnum() {
		return dfrecnum;
	}
	public void setDfrecnum(int dfrecnum) {
		this.dfrecnum = dfrecnum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boleto_envio;
		result = prime * result + cliente;
		result = prime * result + dfrecnum;
		result = prime * result + flagCadastro;
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((telEmail == null) ? 0 : telEmail.hashCode());
		result = prime * result + tipo;
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
		intra_iltelcli other = (intra_iltelcli) obj;
		if (boleto_envio != other.boleto_envio)
			return false;
		if (cliente != other.cliente)
			return false;
		if (dfrecnum != other.dfrecnum)
			return false;
		if (flagCadastro != other.flagCadastro)
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (telEmail == null) {
			if (other.telEmail != null)
				return false;
		} else if (!telEmail.equals(other.telEmail))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
}
