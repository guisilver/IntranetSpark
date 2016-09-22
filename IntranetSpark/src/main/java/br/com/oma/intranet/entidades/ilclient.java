package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.com.oma.intranet.dao.RecadastramentoDAO;
import br.com.oma.omaonline.entidades.cndunida;

@Entity
public class ilclient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4034798562929405928L;
	@Id
	private Integer codigo;
	@NotNull
	@Column(columnDefinition = "varchar(35)")
	private String nome;
	@NotNull
	@Column(columnDefinition = "numeric(14,0)")
	private Long cnpj_cpf;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_nascimento;
	@Column(columnDefinition = "varchar(20)")
	private String profissao;
	@Column(columnDefinition = "varchar(15)")
	private String nro_identidade;
	@Column(columnDefinition = "tinyint")
	private Short dia_ani;
	@Column(columnDefinition = "tinyint")
	private Short mes_ani;
	@Column(columnDefinition = "smallint")
	private Short ano_ani;
	@Transient
	private cndunida unidade;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCnpj_cpf() {
		return cnpj_cpf;
	}

	public void setCnpj_cpf(Long cnpj_cpf) {
		this.cnpj_cpf = cnpj_cpf;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getNro_identidade() {
		return nro_identidade;
	}

	public void setNro_identidade(String nro_identidade) {
		this.nro_identidade = nro_identidade;
	}

	public Short getDia_ani() {
		return dia_ani;
	}

	public void setDia_ani(Short dia_ani) {
		this.dia_ani = dia_ani;
	}

	public Short getMes_ani() {
		return mes_ani;
	}

	public void setMes_ani(Short mes_ani) {
		this.mes_ani = mes_ani;
	}

	public Short getAno_ani() {
		return ano_ani;
	}

	public void setAno_ani(Short ano_ani) {
		this.ano_ani = ano_ani;
	}

	public cndunida getUnidade() {
		if (this.codigo > 0 && this.unidade == null) {
			RecadastramentoDAO dao = new RecadastramentoDAO();
			this.unidade = dao.pesquisaUnidadeCliente(this.codigo);
		}
		return unidade;
	}

	public void setUnidade(cndunida unidade) {
		this.unidade = unidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano_ani == null) ? 0 : ano_ani.hashCode());
		result = prime * result + ((cnpj_cpf == null) ? 0 : cnpj_cpf.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((data_nascimento == null) ? 0 : data_nascimento.hashCode());
		result = prime * result + ((dia_ani == null) ? 0 : dia_ani.hashCode());
		result = prime * result + ((mes_ani == null) ? 0 : mes_ani.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nro_identidade == null) ? 0 : nro_identidade.hashCode());
		result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		ilclient other = (ilclient) obj;
		if (ano_ani == null) {
			if (other.ano_ani != null)
				return false;
		} else if (!ano_ani.equals(other.ano_ani))
			return false;
		if (cnpj_cpf == null) {
			if (other.cnpj_cpf != null)
				return false;
		} else if (!cnpj_cpf.equals(other.cnpj_cpf))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (data_nascimento == null) {
			if (other.data_nascimento != null)
				return false;
		} else if (!data_nascimento.equals(other.data_nascimento))
			return false;
		if (dia_ani == null) {
			if (other.dia_ani != null)
				return false;
		} else if (!dia_ani.equals(other.dia_ani))
			return false;
		if (mes_ani == null) {
			if (other.mes_ani != null)
				return false;
		} else if (!mes_ani.equals(other.mes_ani))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nro_identidade == null) {
			if (other.nro_identidade != null)
				return false;
		} else if (!nro_identidade.equals(other.nro_identidade))
			return false;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

}