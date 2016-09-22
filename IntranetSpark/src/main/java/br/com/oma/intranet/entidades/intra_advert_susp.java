package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class intra_advert_susp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3507554808138158399L;
	@Id
	@GeneratedValue
	private int id;
	private int funcionario;
	private int empresa;
	private String nome;
	private String funcao;
	private int diaSusp;
	@Column(columnDefinition = "varchar(200)")
	private String motSusp;
	@Column(columnDefinition = "datetime")
	private Date retSusp;
	private boolean tipo;
	private String setor;
	private Date dataFeito;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(int funcionario) {
		this.funcionario = funcionario;
	}

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public int getDiaSusp() {
		return diaSusp;
	}

	public void setDiaSusp(int diaSusp) {
		this.diaSusp = diaSusp;
	}

	public String getMotSusp() {
		return motSusp;
	}

	public void setMotSusp(String motSusp) {
		this.motSusp = motSusp;
	}

	public Date getRetSusp() {
		return retSusp;
	}

	public void setRetSusp(Date retSusp) {
		this.retSusp = retSusp;
	}

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Date getDataFeito() {
		return dataFeito;
	}

	public void setDataFeito(Date dataFeito) {
		this.dataFeito = dataFeito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFeito == null) ? 0 : dataFeito.hashCode());
		result = prime * result + diaSusp;
		result = prime * result + empresa;
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + funcionario;
		result = prime * result + id;
		result = prime * result + ((motSusp == null) ? 0 : motSusp.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((retSusp == null) ? 0 : retSusp.hashCode());
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
		result = prime * result + (tipo ? 1231 : 1237);
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
		intra_advert_susp other = (intra_advert_susp) obj;
		if (dataFeito == null) {
			if (other.dataFeito != null)
				return false;
		} else if (!dataFeito.equals(other.dataFeito))
			return false;
		if (diaSusp != other.diaSusp)
			return false;
		if (empresa != other.empresa)
			return false;
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		if (funcionario != other.funcionario)
			return false;
		if (id != other.id)
			return false;
		if (motSusp == null) {
			if (other.motSusp != null)
				return false;
		} else if (!motSusp.equals(other.motSusp))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (retSusp == null) {
			if (other.retSusp != null)
				return false;
		} else if (!retSusp.equals(other.retSusp))
			return false;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

}