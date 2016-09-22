package br.com.oma.omaonline.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cndpagar_historico", schema="omaonline.dbo")
public class cndpagar_historico implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;

	@Column(name = "cd_cnd", columnDefinition = "smallint")
	private short cdCnd;
	@Column(name = "nrolancto", columnDefinition = "int")
	private int nrolancto;
	@Column(name = "data", columnDefinition = "datetime")
	private Date data;
	@Column(name = "acao", columnDefinition = "varchar(20)")
	private String acao;
	@Column(name = "feito_por", columnDefinition = "varchar(50)")
	private String feitoPor;
	@Column(name = "ip", columnDefinition = "varchar(50)")
	private String ip;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
	}

	public short getCdCnd() {
		return cdCnd;
	}

	public void setCdCnd(short cdCnd) {
		this.cdCnd = cdCnd;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getFeitoPor() {
		return feitoPor;
	}

	public void setFeitoPor(String feitoPor) {
		this.feitoPor = feitoPor;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acao == null) ? 0 : acao.hashCode());
		result = prime * result + cdCnd;
		result = prime * result + codigo;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((feitoPor == null) ? 0 : feitoPor.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + nrolancto;
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
		cndpagar_historico other = (cndpagar_historico) obj;
		if (acao == null) {
			if (other.acao != null)
				return false;
		} else if (!acao.equals(other.acao))
			return false;
		if (cdCnd != other.cdCnd)
			return false;
		if (codigo != other.codigo)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (feitoPor == null) {
			if (other.feitoPor != null)
				return false;
		} else if (!feitoPor.equals(other.feitoPor))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (nrolancto != other.nrolancto)
			return false;
		return true;
	}

}
