package br.com.oma.omaonline.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="omaonline.dbo")
public class protocolo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "smallint")
	private short cd_cnd;
	@Column(columnDefinition = "datetime")
	private Date data;
	@Column(columnDefinition = "varchar(100)")
	private String feitoPor;
	@Column(columnDefinition = "varchar(MAX)")
	private String obs;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(schema="omaonline.dbo")
	private Collection<protocolo_lancamentos> lancamentos = new ArrayList<protocolo_lancamentos>();

	public protocolo() {

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public short getCd_cnd() {
		return cd_cnd;
	}

	public void setCd_cnd(short cd_cnd) {
		this.cd_cnd = cd_cnd;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getFeitoPor() {
		return feitoPor;
	}

	public void setFeitoPor(String feitoPor) {
		this.feitoPor = feitoPor;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Collection<protocolo_lancamentos> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(Collection<protocolo_lancamentos> lancamentos) {
		this.lancamentos = lancamentos;
	}

	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(),
				getCodigo());
	}
}
