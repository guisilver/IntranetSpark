package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class intra_emissao_completo implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2227086032008058309L;
	@Id
	@GeneratedValue
	private int id;
	private int codigo_gerente;
	private int codigo_condominio;
	private String nome_condominio;
	private Date vencimento;
	private int nroEmissao;
	@Column(columnDefinition = "varchar(750)")
	private String observacao;
	private boolean correio;
	private int tipo;
	private Date liberacao_gerente;
	private Date data_impressao;
	private String tipo_impressao;
	private Date liberacao_expedicao;
	private Date envio_predio;
	private String proximoMes;
	@Column(columnDefinition = "varchar(300)")
	private String motivo;

	@OneToMany(mappedBy = "rateio", cascade = CascadeType.ALL)
	private List<intra_emissao2> listaRateios = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo_gerente() {
		return codigo_gerente;
	}

	public void setCodigo_gerente(int codigo_gerente) {
		this.codigo_gerente = codigo_gerente;
	}

	public int getCodigo_condominio() {
		return codigo_condominio;
	}

	public void setCodigo_condominio(int codigo_condominio) {
		this.codigo_condominio = codigo_condominio;
	}

	public String getNome_condominio() {
		return nome_condominio;
	}

	public void setNome_condominio(String nome_condominio) {
		this.nome_condominio = nome_condominio;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public int getNroEmissao() {
		return nroEmissao;
	}

	public void setNroEmissao(int nroEmissao) {
		this.nroEmissao = nroEmissao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isCorreio() {
		return correio;
	}

	public void setCorreio(boolean correio) {
		this.correio = correio;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Date getLiberacao_gerente() {
		return liberacao_gerente;
	}

	public void setLiberacao_gerente(Date liberacao_gerente) {
		this.liberacao_gerente = liberacao_gerente;
	}

	public Date getData_impressao() {
		return data_impressao;
	}

	public void setData_impressao(Date data_impressao) {
		this.data_impressao = data_impressao;
	}

	public String getTipo_impressao() {
		return tipo_impressao;
	}

	public void setTipo_impressao(String tipo_impressao) {
		this.tipo_impressao = tipo_impressao;
	}

	public Date getLiberacao_expedicao() {
		return liberacao_expedicao;
	}

	public void setLiberacao_expedicao(Date liberacao_expedicao) {
		this.liberacao_expedicao = liberacao_expedicao;
	}

	public Date getEnvio_predio() {
		return envio_predio;
	}

	public void setEnvio_predio(Date envio_predio) {
		this.envio_predio = envio_predio;
	}

	public String getProximoMes() {
		return proximoMes;
	}

	public void setProximoMes(String proximoMes) {
		this.proximoMes = proximoMes;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<intra_emissao2> getListaRateios() {
		return listaRateios;
	}

	public void setListaRateios(List<intra_emissao2> listaRateios) {
		this.listaRateios = listaRateios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo_condominio;
		result = prime * result + codigo_gerente;
		result = prime * result + (correio ? 1231 : 1237);
		result = prime * result + ((data_impressao == null) ? 0 : data_impressao.hashCode());
		result = prime * result + ((envio_predio == null) ? 0 : envio_predio.hashCode());
		result = prime * result + id;
		result = prime * result + ((liberacao_expedicao == null) ? 0 : liberacao_expedicao.hashCode());
		result = prime * result + ((liberacao_gerente == null) ? 0 : liberacao_gerente.hashCode());
		result = prime * result + ((listaRateios == null) ? 0 : listaRateios.hashCode());
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
		result = prime * result + ((nome_condominio == null) ? 0 : nome_condominio.hashCode());
		result = prime * result + nroEmissao;
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((proximoMes == null) ? 0 : proximoMes.hashCode());
		result = prime * result + tipo;
		result = prime * result + ((tipo_impressao == null) ? 0 : tipo_impressao.hashCode());
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
		intra_emissao_completo other = (intra_emissao_completo) obj;
		if (codigo_condominio != other.codigo_condominio)
			return false;
		if (codigo_gerente != other.codigo_gerente)
			return false;
		if (correio != other.correio)
			return false;
		if (data_impressao == null) {
			if (other.data_impressao != null)
				return false;
		} else if (!data_impressao.equals(other.data_impressao))
			return false;
		if (envio_predio == null) {
			if (other.envio_predio != null)
				return false;
		} else if (!envio_predio.equals(other.envio_predio))
			return false;
		if (id != other.id)
			return false;
		if (liberacao_expedicao == null) {
			if (other.liberacao_expedicao != null)
				return false;
		} else if (!liberacao_expedicao.equals(other.liberacao_expedicao))
			return false;
		if (liberacao_gerente == null) {
			if (other.liberacao_gerente != null)
				return false;
		} else if (!liberacao_gerente.equals(other.liberacao_gerente))
			return false;
		if (listaRateios == null) {
			if (other.listaRateios != null)
				return false;
		} else if (!listaRateios.equals(other.listaRateios))
			return false;
		if (motivo == null) {
			if (other.motivo != null)
				return false;
		} else if (!motivo.equals(other.motivo))
			return false;
		if (nome_condominio == null) {
			if (other.nome_condominio != null)
				return false;
		} else if (!nome_condominio.equals(other.nome_condominio))
			return false;
		if (nroEmissao != other.nroEmissao)
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (proximoMes == null) {
			if (other.proximoMes != null)
				return false;
		} else if (!proximoMes.equals(other.proximoMes))
			return false;
		if (tipo != other.tipo)
			return false;
		if (tipo_impressao == null) {
			if (other.tipo_impressao != null)
				return false;
		} else if (!tipo_impressao.equals(other.tipo_impressao))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Cloning not allowed.");
			return this;
		}
	}

}