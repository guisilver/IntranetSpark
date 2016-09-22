package br.com.oma.sigadm.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "omaonline.dbo")
public class sgreten implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(columnDefinition = "int")
	private int nrolancto;

	@Column(columnDefinition = "tinyint")
	private int sistema;

	@Column(columnDefinition = "int")
	private int codigo;

	@Column(columnDefinition = "varchar(12)")
	private String credor;

	@Column(columnDefinition = "numeric(14,0)")
	private double cnpj;


	@Column(columnDefinition = "datetime")
	private Date vencimento;

	@Column(columnDefinition = "numeric(12,2)")
	private double valor;

	@Column(columnDefinition = "numeric(12,2)")
	private double valor_base;

	@Column(columnDefinition = "numeric(12,2)")
	private double valor_retencao;
	
	@Column(columnDefinition = "numeric(12,2)")
	private double valor_csll;
	
	@Column(columnDefinition = "numeric(12,2)")
	private double valor_cofins;
	
	@Column(columnDefinition = "numeric(12,2)")
	private double valor_pis;

	@Column(columnDefinition = "tinyint")
	private int origem;

	@Column(columnDefinition = "numeric(14,0)")
	private double cnpj_cliente;

	public int getSistema() {
		return sistema;
	}

	public void setSistema(int sistema) {
		this.sistema = sistema;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public double getCnpj() {
		return cnpj;
	}

	public void setCnpj(double cnpj) {
		this.cnpj = cnpj;
	}

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValor_base() {
		return valor_base;
	}

	public void setValor_base(double valor_base) {
		this.valor_base = valor_base;
	}

	public double getValor_retencao() {
		return valor_retencao;
	}

	public void setValor_retencao(double valor_retencao) {
		this.valor_retencao = valor_retencao;
	}

	public double getValor_csll() {
		return valor_csll;
	}

	public void setValor_csll(double valor_csll) {
		this.valor_csll = valor_csll;
	}

	public double getValor_cofins() {
		return valor_cofins;
	}

	public void setValor_cofins(double valor_cofins) {
		this.valor_cofins = valor_cofins;
	}

	public double getValor_pis() {
		return valor_pis;
	}

	public void setValor_pis(double valor_pis) {
		this.valor_pis = valor_pis;
	}

	public int getOrigem() {
		return origem;
	}

	public void setOrigem(int origem) {
		this.origem = origem;
	}

	public double getCnpj_cliente() {
		return cnpj_cliente;
	}

	public void setCnpj_cliente(double cnpj_cliente) {
		this.cnpj_cliente = cnpj_cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cnpj);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(cnpj_cliente);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + codigo;
		result = prime * result + ((credor == null) ? 0 : credor.hashCode());
		result = prime * result + nrolancto;
		result = prime * result + origem;
		result = prime * result + sistema;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor_base);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor_cofins);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor_csll);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor_pis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor_retencao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((vencimento == null) ? 0 : vencimento.hashCode());
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
		sgreten other = (sgreten) obj;
		if (Double.doubleToLongBits(cnpj) != Double.doubleToLongBits(other.cnpj))
			return false;
		if (Double.doubleToLongBits(cnpj_cliente) != Double.doubleToLongBits(other.cnpj_cliente))
			return false;
		if (codigo != other.codigo)
			return false;
		if (credor == null) {
			if (other.credor != null)
				return false;
		} else if (!credor.equals(other.credor))
			return false;
		if (nrolancto != other.nrolancto)
			return false;
		if (origem != other.origem)
			return false;
		if (sistema != other.sistema)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (Double.doubleToLongBits(valor_base) != Double.doubleToLongBits(other.valor_base))
			return false;
		if (Double.doubleToLongBits(valor_cofins) != Double.doubleToLongBits(other.valor_cofins))
			return false;
		if (Double.doubleToLongBits(valor_csll) != Double.doubleToLongBits(other.valor_csll))
			return false;
		if (Double.doubleToLongBits(valor_pis) != Double.doubleToLongBits(other.valor_pis))
			return false;
		if (Double.doubleToLongBits(valor_retencao) != Double.doubleToLongBits(other.valor_retencao))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}

}
