package br.com.oma.omaonline.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.oma.intranet.managedbeans.SessaoMB;

@Entity
@Table(schema="omaonline.dbo")
public class black_list implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(columnDefinition="bigint")
	private int codigo;
	
	@Column
	private int condominio;
	
	@Column(name="codigo_gerente", columnDefinition="int")
	private int codigoGerente;
	
	@Column(name = "cnpj", columnDefinition = "numeric(14,0)")
	private double cnpj;
	
	@Column(name="conta_contabil")
	private int contaContabil;
	
	@Column(name="status_item", columnDefinition="bit")
	private boolean statusItem;

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

	public double getCnpj() {
		return cnpj;
	}

	public void setCnpj(double cnpj) {
		this.cnpj = cnpj;
	}

	public int getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(int contaContabil) {
		this.contaContabil = contaContabil;
	}
	
	public boolean isStatusItem() {
		return statusItem;
	}

	public void setStatusItem(boolean statusItem) {
		this.statusItem = statusItem;
	}

	public int getCodigoGerente() {
		return codigoGerente;
	}

	public void setCodigoGerente(int codigoGerente) {
		this.codigoGerente = codigoGerente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cnpj);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + codigo;
		result = prime * result + codigoGerente;
		result = prime * result + condominio;
		result = prime * result + contaContabil;
		result = prime * result + (statusItem ? 1231 : 1237);
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
		black_list other = (black_list) obj;
		if (Double.doubleToLongBits(cnpj) != Double.doubleToLongBits(other.cnpj))
			return false;
		if (codigo != other.codigo)
			return false;
		if (codigoGerente != other.codigoGerente)
			return false;
		if (condominio != other.condominio)
			return false;
		if (contaContabil != other.contaContabil)
			return false;
		if (statusItem != other.statusItem)
			return false;
		return true;
	}
	
	
}
