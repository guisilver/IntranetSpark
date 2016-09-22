package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_reservas_ti implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "bit")
	private boolean adaptador;
	@Column(columnDefinition = "bit")
	private boolean apresentador;
	@Column(columnDefinition = "bit")
	private boolean energia;
	@Column(columnDefinition = "bit")
	private boolean vga;
	@Column(columnDefinition = "bit")
	private boolean mala;
	@Column(columnDefinition = "bit")
	private boolean pendrive;
	@Column(columnDefinition = "bit")
	private boolean projetor;
	@Column(columnDefinition = "bit")
	private boolean tela;
	@Column(columnDefinition = "bit")
	private boolean notebook;
	@Column(columnDefinition = "varchar(15)")
	private String status;
	@Column(columnDefinition = "bit")
	private boolean cadeiras;
	@Column(columnDefinition = "bit")
	private boolean mesas;
	@Column(columnDefinition = "varchar(20)")
	private String tipo;
	@Column(columnDefinition = "datetime")
	private Date data;
	@Column(columnDefinition = "datetime")
	private Date horario;
	@Column(columnDefinition = "int")
	private int cod_gerente;
	@Column(columnDefinition = "varchar(100)")
	private String nome_gerente;
	@Column(columnDefinition = "int")
	private int cod_condominio;
	@Column(columnDefinition = "varchar(100)")
	private String nome_condominio;
	@Column(columnDefinition = "int")
	private int cod_edital;
	@Column(columnDefinition = "int")
	private int cod_assembleia;
	@Column(columnDefinition = "varchar(500)")
	private String obs;
	@Column(columnDefinition = "datetime")
	private Date data_inserido;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean isAdaptador() {
		return adaptador;
	}

	public void setAdaptador(boolean adaptador) {
		this.adaptador = adaptador;
	}

	public boolean isApresentador() {
		return apresentador;
	}

	public void setApresentador(boolean apresentador) {
		this.apresentador = apresentador;
	}

	public boolean isEnergia() {
		return energia;
	}

	public void setEnergia(boolean energia) {
		this.energia = energia;
	}

	public boolean isVga() {
		return vga;
	}

	public void setVga(boolean vga) {
		this.vga = vga;
	}

	public boolean isMala() {
		return mala;
	}

	public void setMala(boolean mala) {
		this.mala = mala;
	}

	public boolean isPendrive() {
		return pendrive;
	}

	public void setPendrive(boolean pendrive) {
		this.pendrive = pendrive;
	}

	public boolean isProjetor() {
		return projetor;
	}

	public void setProjetor(boolean projetor) {
		this.projetor = projetor;
	}

	public boolean isTela() {
		return tela;
	}

	public void setTela(boolean tela) {
		this.tela = tela;
	}

	public boolean isNotebook() {
		return notebook;
	}

	public void setNotebook(boolean notebook) {
		this.notebook = notebook;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isCadeiras() {
		return cadeiras;
	}

	public void setCadeiras(boolean cadeiras) {
		this.cadeiras = cadeiras;
	}

	public boolean isMesas() {
		return mesas;
	}

	public void setMesas(boolean mesas) {
		this.mesas = mesas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public int getCod_gerente() {
		return cod_gerente;
	}

	public void setCod_gerente(int cod_gerente) {
		this.cod_gerente = cod_gerente;
	}

	public String getNome_gerente() {
		return nome_gerente;
	}

	public void setNome_gerente(String nome_gerente) {
		this.nome_gerente = nome_gerente;
	}

	public int getCod_condominio() {
		return cod_condominio;
	}

	public void setCod_condominio(int cod_condominio) {
		this.cod_condominio = cod_condominio;
	}

	public String getNome_condominio() {
		return nome_condominio;
	}

	public void setNome_condominio(String nome_condominio) {
		this.nome_condominio = nome_condominio;
	}

	public int getCod_edital() {
		return cod_edital;
	}

	public void setCod_edital(int cod_edital) {
		this.cod_edital = cod_edital;
	}

	public int getCod_assembleia() {
		return cod_assembleia;
	}

	public void setCod_assembleia(int cod_assembleia) {
		this.cod_assembleia = cod_assembleia;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getData_inserido() {
		return data_inserido;
	}

	public void setData_inserido(Date data_inserido) {
		this.data_inserido = data_inserido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (adaptador ? 1231 : 1237);
		result = prime * result + (apresentador ? 1231 : 1237);
		result = prime * result + (cadeiras ? 1231 : 1237);
		result = prime * result + cod_assembleia;
		result = prime * result + cod_condominio;
		result = prime * result + cod_edital;
		result = prime * result + cod_gerente;
		result = prime * result + codigo;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((data_inserido == null) ? 0 : data_inserido.hashCode());
		result = prime * result + (energia ? 1231 : 1237);
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + (mala ? 1231 : 1237);
		result = prime * result + (mesas ? 1231 : 1237);
		result = prime * result
				+ ((nome_condominio == null) ? 0 : nome_condominio.hashCode());
		result = prime * result
				+ ((nome_gerente == null) ? 0 : nome_gerente.hashCode());
		result = prime * result + (notebook ? 1231 : 1237);
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + (pendrive ? 1231 : 1237);
		result = prime * result + (projetor ? 1231 : 1237);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (tela ? 1231 : 1237);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + (vga ? 1231 : 1237);
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
		intra_reservas_ti other = (intra_reservas_ti) obj;
		if (adaptador != other.adaptador)
			return false;
		if (apresentador != other.apresentador)
			return false;
		if (cadeiras != other.cadeiras)
			return false;
		if (cod_assembleia != other.cod_assembleia)
			return false;
		if (cod_condominio != other.cod_condominio)
			return false;
		if (cod_edital != other.cod_edital)
			return false;
		if (cod_gerente != other.cod_gerente)
			return false;
		if (codigo != other.codigo)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (data_inserido == null) {
			if (other.data_inserido != null)
				return false;
		} else if (!data_inserido.equals(other.data_inserido))
			return false;
		if (energia != other.energia)
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (mala != other.mala)
			return false;
		if (mesas != other.mesas)
			return false;
		if (nome_condominio == null) {
			if (other.nome_condominio != null)
				return false;
		} else if (!nome_condominio.equals(other.nome_condominio))
			return false;
		if (nome_gerente == null) {
			if (other.nome_gerente != null)
				return false;
		} else if (!nome_gerente.equals(other.nome_gerente))
			return false;
		if (notebook != other.notebook)
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (pendrive != other.pendrive)
			return false;
		if (projetor != other.projetor)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tela != other.tela)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (vga != other.vga)
			return false;
		return true;
	}

}
