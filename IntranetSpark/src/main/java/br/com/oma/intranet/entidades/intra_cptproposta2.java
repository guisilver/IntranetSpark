package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_cptproposta2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2278306344778266971L;
	@Id
	@GeneratedValue
	private int id;
	private String indicadoPor;
	private String solicitadoPor;
	private String endereco;
	private String bairro;
	private String cidade;
	private int estado;
	private String cep;
	private String apartamento;
	private String telResidencial;
	private String telComercial;
	private String fax;
	private String celular;
	private String e_mail;
	private String entreguePor;
	private Date dataProposta;
	private Date inicioContrato;
	private String taxa;
	private String observacao;

	private int condo3;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIndicadoPor() {
		return indicadoPor;
	}

	public void setIndicadoPor(String indicadoPor) {
		this.indicadoPor = indicadoPor;
	}

	public String getSolicitadoPor() {
		return solicitadoPor;
	}

	public void setSolicitadoPor(String solicitadoPor) {
		this.solicitadoPor = solicitadoPor;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getApartamento() {
		return apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	public String getTelResidencial() {
		return telResidencial;
	}

	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}

	public String getTelComercial() {
		return telComercial;
	}

	public void setTelComercial(String telComercial) {
		this.telComercial = telComercial;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getEntreguePor() {
		return entreguePor;
	}

	public void setEntreguePor(String entreguePor) {
		this.entreguePor = entreguePor;
	}

	public Date getDataProposta() {
		return dataProposta;
	}

	public void setDataProposta(Date dataProposta) {
		this.dataProposta = dataProposta;
	}

	public Date getInicioContrato() {
		return inicioContrato;
	}

	public void setInicioContrato(Date inicioContrato) {
		this.inicioContrato = inicioContrato;
	}

	public String getTaxa() {
		return taxa;
	}

	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getCondo3() {
		return condo3;
	}

	public void setCondo3(int condo3) {
		this.condo3 = condo3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartamento == null) ? 0 : apartamento.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + condo3;
		result = prime * result + ((dataProposta == null) ? 0 : dataProposta.hashCode());
		result = prime * result + ((e_mail == null) ? 0 : e_mail.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((entreguePor == null) ? 0 : entreguePor.hashCode());
		result = prime * result + estado;
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + id;
		result = prime * result + ((indicadoPor == null) ? 0 : indicadoPor.hashCode());
		result = prime * result + ((inicioContrato == null) ? 0 : inicioContrato.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((solicitadoPor == null) ? 0 : solicitadoPor.hashCode());
		result = prime * result + ((taxa == null) ? 0 : taxa.hashCode());
		result = prime * result + ((telComercial == null) ? 0 : telComercial.hashCode());
		result = prime * result + ((telResidencial == null) ? 0 : telResidencial.hashCode());
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
		intra_cptproposta2 other = (intra_cptproposta2) obj;
		if (apartamento == null) {
			if (other.apartamento != null)
				return false;
		} else if (!apartamento.equals(other.apartamento))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (condo3 != other.condo3)
			return false;
		if (dataProposta == null) {
			if (other.dataProposta != null)
				return false;
		} else if (!dataProposta.equals(other.dataProposta))
			return false;
		if (e_mail == null) {
			if (other.e_mail != null)
				return false;
		} else if (!e_mail.equals(other.e_mail))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (entreguePor == null) {
			if (other.entreguePor != null)
				return false;
		} else if (!entreguePor.equals(other.entreguePor))
			return false;
		if (estado != other.estado)
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (id != other.id)
			return false;
		if (indicadoPor == null) {
			if (other.indicadoPor != null)
				return false;
		} else if (!indicadoPor.equals(other.indicadoPor))
			return false;
		if (inicioContrato == null) {
			if (other.inicioContrato != null)
				return false;
		} else if (!inicioContrato.equals(other.inicioContrato))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (solicitadoPor == null) {
			if (other.solicitadoPor != null)
				return false;
		} else if (!solicitadoPor.equals(other.solicitadoPor))
			return false;
		if (taxa == null) {
			if (other.taxa != null)
				return false;
		} else if (!taxa.equals(other.taxa))
			return false;
		if (telComercial == null) {
			if (other.telComercial != null)
				return false;
		} else if (!telComercial.equals(other.telComercial))
			return false;
		if (telResidencial == null) {
			if (other.telResidencial != null)
				return false;
		} else if (!telResidencial.equals(other.telResidencial))
			return false;
		return true;
	}

}
