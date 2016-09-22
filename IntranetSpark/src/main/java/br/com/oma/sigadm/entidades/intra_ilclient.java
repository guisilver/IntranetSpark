package br.com.oma.sigadm.entidades;

import javax.persistence.Column;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

public class intra_ilclient implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private int codigo;	
	
	@Column(columnDefinition="varchar(35)")
	private String nome;
	
	@Column(columnDefinition="char(5)")
	private String logradouro;	
	
	@Column(columnDefinition="varchar(35)")
	private String endereco;	
	
	@Column(columnDefinition="varchar(35)")
	private String complemento;	
	
	@Column(columnDefinition="varchar(20)")
	private String bairro;	
	
	@Column(columnDefinition="varchar(20)")
	private String cidade;	
	
	@Column(columnDefinition="char(2)")
	private String estado;	
	
	@Column(columnDefinition="numeric(8,0)")
	private double cep;	
	
	@Column(name="tipo_pessoa",columnDefinition="char(1)")
	private String tipoPessoa;	
	
	@Column(name="cnpj_cpf",columnDefinition="numeric(14,0)")
	private double cnpjCpf;
	
	@Column(name="data_nascimento",columnDefinition="datetime")
	private Date dataNascimento;	
	
	@Column(name="data_cadastro",columnDefinition="datetime")
	private Date dataCadastro;	
	
	@Column(columnDefinition="varchar(20)")
	private String profissao;	
	
	@Column(columnDefinition="varchar(15)")
	private String nacionalidade;	
	
	@Column(name="tipo_identidade",columnDefinition="char(5)")
	private String tipoIdentidade;	
	
	@Column(name="nro_identidade",columnDefinition="varchar(15")
	private String nroIdentidade;
	
	@Column(name="orgao_emissor",columnDefinition="varchar(10)")
	private String orgaoEmissor;	
	
	@Column(name="estado_civil",columnDefinition="tinyint")
	private int estadoCivil;	
	
	@Column(name="end_corresp",columnDefinition="char(1)")
	private String endCorresp;	
	
	@Column(name="imp_etiq",columnDefinition="char(1)")
	private String impEtiq;	
	
	@Column(name="reside_exterior",columnDefinition="char(1)")
	private String resideExterior;	
	
	@Column(name="nro_end",columnDefinition="varchar(10)")
	private String nroEnd;	
	
	@Column(name="dt_alteracao",columnDefinition="datetime")
	private Date dtAlteracao;	
	
	@Column(columnDefinition="int")
	private int usuario;	
	
	@Column(columnDefinition="smallint")
	private short religiao = 9999;	
	
	@Column(columnDefinition="char(1)")
	private String sexo;
	
	@Column(name="dia_ani",columnDefinition="int")
	private int diaAni;
	
	@Column(name="mes_ani",columnDefinition="int")
	private int mesAni;	
	
	@Column(name="ano_ani",columnDefinition="smallint")
	private short anoAni;	
	
	@Column(columnDefinition="varchar(20)")
	private String pais = "BRASIL";	
	
	@Column(name="cod_pais",columnDefinition="int")
	private int codPais = 1058;	
	
	@Column(columnDefinition="int")
	private int municipio;
	
	@Column(name="cartorio_1",columnDefinition="varchar(50)")
	private String cartorio1;
	
	@Column(name="cartorio_2",columnDefinition="varchar(50)")
	private String cartorio2;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getCep() {
		return cep;
	}

	public void setCep(double cep) {
		this.cep = cep;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public double getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(double cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getTipoIdentidade() {
		return tipoIdentidade;
	}

	public void setTipoIdentidade(String tipoIdentidade) {
		this.tipoIdentidade = tipoIdentidade;
	}

	public String getNroIdentidade() {
		return nroIdentidade;
	}

	public void setNroIdentidade(String nroIdentidade) {
		this.nroIdentidade = nroIdentidade;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public int getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(int estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEndCorresp() {
		return endCorresp;
	}

	public void setEndCorresp(String endCorresp) {
		this.endCorresp = endCorresp;
	}

	public String getImpEtiq() {
		return impEtiq;
	}

	public void setImpEtiq(String impEtiq) {
		this.impEtiq = impEtiq;
	}

	public String getResideExterior() {
		return resideExterior;
	}

	public void setResideExterior(String resideExterior) {
		this.resideExterior = resideExterior;
	}

	public String getNroEnd() {
		return nroEnd;
	}

	public void setNroEnd(String nroEnd) {
		this.nroEnd = nroEnd;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public short getReligiao() {
		return religiao;
	}

	public void setReligiao(short religiao) {
		this.religiao = religiao;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getDiaAni() {
		return diaAni;
	}

	public void setDiaAni(int diaAni) {
		this.diaAni = diaAni;
	}

	public int getMesAni() {
		return mesAni;
	}

	public void setMesAni(int mesAni) {
		this.mesAni = mesAni;
	}

	public short getAnoAni() {
		return anoAni;
	}

	public void setAnoAni(short anoAni) {
		this.anoAni = anoAni;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCodPais() {
		return codPais;
	}

	public void setCodPais(int codPais) {
		this.codPais = codPais;
	}

	public int getMunicipio() {
		return municipio;
	}

	public void setMunicipio(int municipio) {
		this.municipio = municipio;
	}

	public String getCartorio1() {
		return cartorio1;
	}

	public void setCartorio1(String cartorio1) {
		this.cartorio1 = cartorio1;
	}

	public String getCartorio2() {
		return cartorio2;
	}

	public void setCartorio2(String cartorio2) {
		this.cartorio2 = cartorio2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anoAni;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cartorio1 == null) ? 0 : cartorio1.hashCode());
		result = prime * result + ((cartorio2 == null) ? 0 : cartorio2.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cep);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		temp = Double.doubleToLongBits(cnpjCpf);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + codPais;
		result = prime * result + codigo;
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + diaAni;
		result = prime * result + ((dtAlteracao == null) ? 0 : dtAlteracao.hashCode());
		result = prime * result + ((endCorresp == null) ? 0 : endCorresp.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + estadoCivil;
		result = prime * result + ((impEtiq == null) ? 0 : impEtiq.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + mesAni;
		result = prime * result + municipio;
		result = prime * result + ((nacionalidade == null) ? 0 : nacionalidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nroEnd == null) ? 0 : nroEnd.hashCode());
		result = prime * result + ((nroIdentidade == null) ? 0 : nroIdentidade.hashCode());
		result = prime * result + ((orgaoEmissor == null) ? 0 : orgaoEmissor.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
		result = prime * result + religiao;
		result = prime * result + ((resideExterior == null) ? 0 : resideExterior.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((tipoIdentidade == null) ? 0 : tipoIdentidade.hashCode());
		result = prime * result + ((tipoPessoa == null) ? 0 : tipoPessoa.hashCode());
		result = prime * result + usuario;
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
		intra_ilclient other = (intra_ilclient) obj;
		if (anoAni != other.anoAni)
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cartorio1 == null) {
			if (other.cartorio1 != null)
				return false;
		} else if (!cartorio1.equals(other.cartorio1))
			return false;
		if (cartorio2 == null) {
			if (other.cartorio2 != null)
				return false;
		} else if (!cartorio2.equals(other.cartorio2))
			return false;
		if (Double.doubleToLongBits(cep) != Double.doubleToLongBits(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (Double.doubleToLongBits(cnpjCpf) != Double.doubleToLongBits(other.cnpjCpf))
			return false;
		if (codPais != other.codPais)
			return false;
		if (codigo != other.codigo)
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (diaAni != other.diaAni)
			return false;
		if (dtAlteracao == null) {
			if (other.dtAlteracao != null)
				return false;
		} else if (!dtAlteracao.equals(other.dtAlteracao))
			return false;
		if (endCorresp == null) {
			if (other.endCorresp != null)
				return false;
		} else if (!endCorresp.equals(other.endCorresp))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (estadoCivil != other.estadoCivil)
			return false;
		if (impEtiq == null) {
			if (other.impEtiq != null)
				return false;
		} else if (!impEtiq.equals(other.impEtiq))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (mesAni != other.mesAni)
			return false;
		if (municipio != other.municipio)
			return false;
		if (nacionalidade == null) {
			if (other.nacionalidade != null)
				return false;
		} else if (!nacionalidade.equals(other.nacionalidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nroEnd == null) {
			if (other.nroEnd != null)
				return false;
		} else if (!nroEnd.equals(other.nroEnd))
			return false;
		if (nroIdentidade == null) {
			if (other.nroIdentidade != null)
				return false;
		} else if (!nroIdentidade.equals(other.nroIdentidade))
			return false;
		if (orgaoEmissor == null) {
			if (other.orgaoEmissor != null)
				return false;
		} else if (!orgaoEmissor.equals(other.orgaoEmissor))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		if (religiao != other.religiao)
			return false;
		if (resideExterior == null) {
			if (other.resideExterior != null)
				return false;
		} else if (!resideExterior.equals(other.resideExterior))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		if (tipoIdentidade == null) {
			if (other.tipoIdentidade != null)
				return false;
		} else if (!tipoIdentidade.equals(other.tipoIdentidade))
			return false;
		if (tipoPessoa == null) {
			if (other.tipoPessoa != null)
				return false;
		} else if (!tipoPessoa.equals(other.tipoPessoa))
			return false;
		if (usuario != other.usuario)
			return false;
		return true;
	}
	
		
}
