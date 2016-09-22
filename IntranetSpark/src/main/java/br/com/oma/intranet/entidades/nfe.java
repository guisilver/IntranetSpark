package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class nfe implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(300)")
	private String nomeArquivo;
	@Column(columnDefinition = "varchar(30)")
	private String cnpjEmit;
	@Column(columnDefinition = "varchar(30)")
	private String cnpjDest;
	@Lob
	private byte[] xml;
	@Lob
	private byte[] pdf;
	@Column(columnDefinition = "bit")
	private boolean utilizado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataVencimento;
	@Column(columnDefinition = "varchar(300)")
	private String nomeDestinatario;
	@Column(columnDefinition = "float")
	private double valor;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getCnpjEmit() {
		return cnpjEmit;
	}

	public void setCnpjEmit(String cnpjEmit) {
		this.cnpjEmit = cnpjEmit;
	}

	public String getCnpjDest() {
		return cnpjDest;
	}

	public void setCnpjDest(String cnpjDest) {
		this.cnpjDest = cnpjDest;
	}

	public byte[] getXml() {
		return xml;
	}

	public void setXml(byte[] xml) {
		this.xml = xml;
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

	public boolean isUtilizado() {
		return utilizado;
	}

	public void setUtilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpjDest == null) ? 0 : cnpjDest.hashCode());
		result = prime * result + ((cnpjEmit == null) ? 0 : cnpjEmit.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		result = prime * result + ((nomeDestinatario == null) ? 0 : nomeDestinatario.hashCode());
		result = prime * result + Arrays.hashCode(pdf);
		result = prime * result + (utilizado ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(xml);
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
		nfe other = (nfe) obj;
		if (cnpjDest == null) {
			if (other.cnpjDest != null)
				return false;
		} else if (!cnpjDest.equals(other.cnpjDest))
			return false;
		if (cnpjEmit == null) {
			if (other.cnpjEmit != null)
				return false;
		} else if (!cnpjEmit.equals(other.cnpjEmit))
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null)
				return false;
		} else if (!nomeArquivo.equals(other.nomeArquivo))
			return false;
		if (nomeDestinatario == null) {
			if (other.nomeDestinatario != null)
				return false;
		} else if (!nomeDestinatario.equals(other.nomeDestinatario))
			return false;
		if (!Arrays.equals(pdf, other.pdf))
			return false;
		if (utilizado != other.utilizado)
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		if (!Arrays.equals(xml, other.xml))
			return false;
		return true;
	}
	

}
