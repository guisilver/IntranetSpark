package br.com.oma.intranet.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class intra_netimoveis_proprietario implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2086159278792967922L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int captacao;
	@Column(columnDefinition = "varchar(100)")
	private String nome;
	@Column(columnDefinition = "varchar(50)")
	private String nacionalidade;
	@Column(columnDefinition = "varchar(20)")
	private String estadoCivil;
	@Column(columnDefinition = "varchar(50)")
	private String profissao;
	@Column(columnDefinition = "varchar(20)")
	private String cpfCnpj;
	@Column(columnDefinition = "varchar(30)")
	private String ci;
	@Column(columnDefinition = "datetime")
	private Date nascimento;
	@Column(columnDefinition = "varchar(15)")
	private String telResidencial;
	@Column(columnDefinition = "varchar(15)")
	private String telComercial;
	@Column(columnDefinition = "varchar(15)")
	private String fax;
	@Column(columnDefinition = "varchar(15)")
	private String celular;
	@Column(columnDefinition = "varchar(75)")
	private String email;
	@Column(columnDefinition = "varchar(100)")
	private String enderecoResidencial;
	@Column(columnDefinition = "varchar(50)")
	private String bairroResidencial;
	@Column(columnDefinition = "varchar(50)")
	private String cidadeResidencia;
	@Column(columnDefinition = "varchar(10)")
	private String cepResidencial;
	@Column(columnDefinition = "varchar(100)")
	private String enderecoComercial;
	@Column(columnDefinition = "varchar(50)")
	private String bairroComercial;
	@Column(columnDefinition = "varchar(50)")
	private String cidadeComercial;
	@Column(columnDefinition = "varchar(10)")
	private String cepComercial;
	@Column(columnDefinition = "varchar(75)")
	private String conjuge;
	@Column(columnDefinition = "varchar(50)")
	private String conjugeNacionalidade;
	@Column(columnDefinition = "varchar(20)")
	private String estadoCivilConjuge;
	@Column(columnDefinition = "varchar(50)")
	private String profissaoConjuge;
	@Column(columnDefinition = "varchar(20)")
	private String cpfConjuge;
	@Column(columnDefinition = "varchar(30)")
	private String ciConjuge;
	@Column(columnDefinition = "datetime")
	private Date nascimentoConjuge;
	@Column(columnDefinition = "varchar(100)")
	private String endResConjuge;
	@Column(columnDefinition = "varchar(15)")
	private String telResConjuge;
	@Column(columnDefinition = "varchar(15)")
	private String celularConjuge;
	@Column(columnDefinition = "varchar(75)")
	private String procurador;
	@Column(columnDefinition = "varchar(15)")
	private String telefoneProcurador;
	@Column(columnDefinition = "varchar(75)")
	private String emailProcurador;
	@Column(columnDefinition = "varchar(30)")
	private String banco;
	@Column(columnDefinition = "varchar(50)")
	private String nomeAgencia;
	@Column(columnDefinition = "varchar(50)")
	private String praca;
	private int agencia;
	private int contaCorrente;
	@Column(columnDefinition = "varchar(75)")
	private String correntista;
	@Column(columnDefinition = "varchar(30)")
	private String prazoContrato;
	@Column(columnDefinition = "varchar(30)")
	private String reajuste;
	@Column(columnDefinition = "varchar(15)")
	private String taxaAdm;
	@Column(columnDefinition = "varchar(30)")
	private String porcPropriedade;
	@Column(columnDefinition = "varchar(30)")
	private String taxaContrato;
	@Column(columnDefinition = "varchar(30)")
	private String assJuridica;
	@Column(columnDefinition = "varchar(20)")
	private String valorVenal;
	@Column(columnDefinition = "varchar(30)")
	private String controleGaragem;
	@Column(columnDefinition = "varchar(30)")
	private String marca;
	private boolean imovelAnuncio;
	private boolean pagaPA;
	@Column(columnDefinition = "varchar(350)")
	private String observacao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCaptacao() {
		return captacao;
	}
	public void setCaptacao(int captacao) {
		this.captacao = captacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public String getTelResidencial() {
		return telResidencial;
	}
	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}
	public String getTelComercial() {
		return telComercial;
	}
	public void setTelComercial(String telComercial) {
		this.telComercial = telComercial;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnderecoResidencial() {
		return enderecoResidencial;
	}
	public void setEnderecoResidencial(String enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}
	public String getBairroResidencial() {
		return bairroResidencial;
	}
	public void setBairroResidencial(String bairroResidencial) {
		this.bairroResidencial = bairroResidencial;
	}
	public String getCidadeResidencia() {
		return cidadeResidencia;
	}
	public void setCidadeResidencia(String cidadeResidencia) {
		this.cidadeResidencia = cidadeResidencia;
	}
	public String getCepResidencial() {
		return cepResidencial;
	}
	public void setCepResidencial(String cepResidencial) {
		this.cepResidencial = cepResidencial;
	}
	public String getEnderecoComercial() {
		return enderecoComercial;
	}
	public void setEnderecoComercial(String enderecoComercial) {
		this.enderecoComercial = enderecoComercial;
	}
	public String getBairroComercial() {
		return bairroComercial;
	}
	public void setBairroComercial(String bairroComercial) {
		this.bairroComercial = bairroComercial;
	}
	public String getCidadeComercial() {
		return cidadeComercial;
	}
	public void setCidadeComercial(String cidadeComercial) {
		this.cidadeComercial = cidadeComercial;
	}
	public String getCepComercial() {
		return cepComercial;
	}
	public void setCepComercial(String cepComercial) {
		this.cepComercial = cepComercial;
	}
	public String getConjuge() {
		return conjuge;
	}
	public void setConjuge(String conjuge) {
		this.conjuge = conjuge;
	}
	public String getConjugeNacionalidade() {
		return conjugeNacionalidade;
	}
	public void setConjugeNacionalidade(String conjugeNacionalidade) {
		this.conjugeNacionalidade = conjugeNacionalidade;
	}
	public String getEstadoCivilConjuge() {
		return estadoCivilConjuge;
	}
	public void setEstadoCivilConjuge(String estadoCivilConjuge) {
		this.estadoCivilConjuge = estadoCivilConjuge;
	}
	public String getProfissaoConjuge() {
		return profissaoConjuge;
	}
	public void setProfissaoConjuge(String profissaoConjuge) {
		this.profissaoConjuge = profissaoConjuge;
	}
	public String getCpfConjuge() {
		return cpfConjuge;
	}
	public void setCpfConjuge(String cpfConjuge) {
		this.cpfConjuge = cpfConjuge;
	}
	public String getCiConjuge() {
		return ciConjuge;
	}
	public void setCiConjuge(String ciConjuge) {
		this.ciConjuge = ciConjuge;
	}
	public Date getNascimentoConjuge() {
		return nascimentoConjuge;
	}
	public void setNascimentoConjuge(Date nascimentoConjuge) {
		this.nascimentoConjuge = nascimentoConjuge;
	}
	public String getEndResConjuge() {
		return endResConjuge;
	}
	public void setEndResConjuge(String endResConjuge) {
		this.endResConjuge = endResConjuge;
	}
	public String getTelResConjuge() {
		return telResConjuge;
	}
	public void setTelResConjuge(String telResConjuge) {
		this.telResConjuge = telResConjuge;
	}
	public String getCelularConjuge() {
		return celularConjuge;
	}
	public void setCelularConjuge(String celularConjuge) {
		this.celularConjuge = celularConjuge;
	}
	public String getProcurador() {
		return procurador;
	}
	public void setProcurador(String procurador) {
		this.procurador = procurador;
	}
	public String getTelefoneProcurador() {
		return telefoneProcurador;
	}
	public void setTelefoneProcurador(String telefoneProcurador) {
		this.telefoneProcurador = telefoneProcurador;
	}
	public String getEmailProcurador() {
		return emailProcurador;
	}
	public void setEmailProcurador(String emailProcurador) {
		this.emailProcurador = emailProcurador;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getNomeAgencia() {
		return nomeAgencia;
	}
	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}
	public String getPraca() {
		return praca;
	}
	public void setPraca(String praca) {
		this.praca = praca;
	}
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public int getContaCorrente() {
		return contaCorrente;
	}
	public void setContaCorrente(int contaCorrente) {
		this.contaCorrente = contaCorrente;
	}
	public String getCorrentista() {
		return correntista;
	}
	public void setCorrentista(String correntista) {
		this.correntista = correntista;
	}
	public String getPrazoContrato() {
		return prazoContrato;
	}
	public void setPrazoContrato(String prazoContrato) {
		this.prazoContrato = prazoContrato;
	}
	public String getReajuste() {
		return reajuste;
	}
	public void setReajuste(String reajuste) {
		this.reajuste = reajuste;
	}
	public String getTaxaAdm() {
		return taxaAdm;
	}
	public void setTaxaAdm(String taxaAdm) {
		this.taxaAdm = taxaAdm;
	}
	public String getPorcPropriedade() {
		return porcPropriedade;
	}
	public void setPorcPropriedade(String porcPropriedade) {
		this.porcPropriedade = porcPropriedade;
	}
	public String getTaxaContrato() {
		return taxaContrato;
	}
	public void setTaxaContrato(String taxaContrato) {
		this.taxaContrato = taxaContrato;
	}
	public String getAssJuridica() {
		return assJuridica;
	}
	public void setAssJuridica(String assJuridica) {
		this.assJuridica = assJuridica;
	}
	public String getValorVenal() {
		return valorVenal;
	}
	public void setValorVenal(String valorVenal) {
		this.valorVenal = valorVenal;
	}
	public String getControleGaragem() {
		return controleGaragem;
	}
	public void setControleGaragem(String controleGaragem) {
		this.controleGaragem = controleGaragem;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public boolean isImovelAnuncio() {
		return imovelAnuncio;
	}
	public void setImovelAnuncio(boolean imovelAnuncio) {
		this.imovelAnuncio = imovelAnuncio;
	}
	public boolean isPagaPA() {
		return pagaPA;
	}
	public void setPagaPA(boolean pagaPA) {
		this.pagaPA = pagaPA;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + agencia;
		result = prime * result + ((assJuridica == null) ? 0 : assJuridica.hashCode());
		result = prime * result + ((bairroComercial == null) ? 0 : bairroComercial.hashCode());
		result = prime * result + ((bairroResidencial == null) ? 0 : bairroResidencial.hashCode());
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime * result + captacao;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((celularConjuge == null) ? 0 : celularConjuge.hashCode());
		result = prime * result + ((cepComercial == null) ? 0 : cepComercial.hashCode());
		result = prime * result + ((cepResidencial == null) ? 0 : cepResidencial.hashCode());
		result = prime * result + ((ci == null) ? 0 : ci.hashCode());
		result = prime * result + ((ciConjuge == null) ? 0 : ciConjuge.hashCode());
		result = prime * result + ((cidadeComercial == null) ? 0 : cidadeComercial.hashCode());
		result = prime * result + ((cidadeResidencia == null) ? 0 : cidadeResidencia.hashCode());
		result = prime * result + ((conjuge == null) ? 0 : conjuge.hashCode());
		result = prime * result + ((conjugeNacionalidade == null) ? 0 : conjugeNacionalidade.hashCode());
		result = prime * result + contaCorrente;
		result = prime * result + ((controleGaragem == null) ? 0 : controleGaragem.hashCode());
		result = prime * result + ((correntista == null) ? 0 : correntista.hashCode());
		result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
		result = prime * result + ((cpfConjuge == null) ? 0 : cpfConjuge.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emailProcurador == null) ? 0 : emailProcurador.hashCode());
		result = prime * result + ((endResConjuge == null) ? 0 : endResConjuge.hashCode());
		result = prime * result + ((enderecoComercial == null) ? 0 : enderecoComercial.hashCode());
		result = prime * result + ((enderecoResidencial == null) ? 0 : enderecoResidencial.hashCode());
		result = prime * result + ((estadoCivil == null) ? 0 : estadoCivil.hashCode());
		result = prime * result + ((estadoCivilConjuge == null) ? 0 : estadoCivilConjuge.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + id;
		result = prime * result + (imovelAnuncio ? 1231 : 1237);
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nacionalidade == null) ? 0 : nacionalidade.hashCode());
		result = prime * result + ((nascimento == null) ? 0 : nascimento.hashCode());
		result = prime * result + ((nascimentoConjuge == null) ? 0 : nascimentoConjuge.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeAgencia == null) ? 0 : nomeAgencia.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + (pagaPA ? 1231 : 1237);
		result = prime * result + ((porcPropriedade == null) ? 0 : porcPropriedade.hashCode());
		result = prime * result + ((praca == null) ? 0 : praca.hashCode());
		result = prime * result + ((prazoContrato == null) ? 0 : prazoContrato.hashCode());
		result = prime * result + ((procurador == null) ? 0 : procurador.hashCode());
		result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
		result = prime * result + ((profissaoConjuge == null) ? 0 : profissaoConjuge.hashCode());
		result = prime * result + ((reajuste == null) ? 0 : reajuste.hashCode());
		result = prime * result + ((taxaAdm == null) ? 0 : taxaAdm.hashCode());
		result = prime * result + ((taxaContrato == null) ? 0 : taxaContrato.hashCode());
		result = prime * result + ((telComercial == null) ? 0 : telComercial.hashCode());
		result = prime * result + ((telResConjuge == null) ? 0 : telResConjuge.hashCode());
		result = prime * result + ((telResidencial == null) ? 0 : telResidencial.hashCode());
		result = prime * result + ((telefoneProcurador == null) ? 0 : telefoneProcurador.hashCode());
		result = prime * result + ((valorVenal == null) ? 0 : valorVenal.hashCode());
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
		intra_netimoveis_proprietario other = (intra_netimoveis_proprietario) obj;
		if (agencia != other.agencia)
			return false;
		if (assJuridica == null) {
			if (other.assJuridica != null)
				return false;
		} else if (!assJuridica.equals(other.assJuridica))
			return false;
		if (bairroComercial == null) {
			if (other.bairroComercial != null)
				return false;
		} else if (!bairroComercial.equals(other.bairroComercial))
			return false;
		if (bairroResidencial == null) {
			if (other.bairroResidencial != null)
				return false;
		} else if (!bairroResidencial.equals(other.bairroResidencial))
			return false;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (captacao != other.captacao)
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (celularConjuge == null) {
			if (other.celularConjuge != null)
				return false;
		} else if (!celularConjuge.equals(other.celularConjuge))
			return false;
		if (cepComercial == null) {
			if (other.cepComercial != null)
				return false;
		} else if (!cepComercial.equals(other.cepComercial))
			return false;
		if (cepResidencial == null) {
			if (other.cepResidencial != null)
				return false;
		} else if (!cepResidencial.equals(other.cepResidencial))
			return false;
		if (ci == null) {
			if (other.ci != null)
				return false;
		} else if (!ci.equals(other.ci))
			return false;
		if (ciConjuge == null) {
			if (other.ciConjuge != null)
				return false;
		} else if (!ciConjuge.equals(other.ciConjuge))
			return false;
		if (cidadeComercial == null) {
			if (other.cidadeComercial != null)
				return false;
		} else if (!cidadeComercial.equals(other.cidadeComercial))
			return false;
		if (cidadeResidencia == null) {
			if (other.cidadeResidencia != null)
				return false;
		} else if (!cidadeResidencia.equals(other.cidadeResidencia))
			return false;
		if (conjuge == null) {
			if (other.conjuge != null)
				return false;
		} else if (!conjuge.equals(other.conjuge))
			return false;
		if (conjugeNacionalidade == null) {
			if (other.conjugeNacionalidade != null)
				return false;
		} else if (!conjugeNacionalidade.equals(other.conjugeNacionalidade))
			return false;
		if (contaCorrente != other.contaCorrente)
			return false;
		if (controleGaragem == null) {
			if (other.controleGaragem != null)
				return false;
		} else if (!controleGaragem.equals(other.controleGaragem))
			return false;
		if (correntista == null) {
			if (other.correntista != null)
				return false;
		} else if (!correntista.equals(other.correntista))
			return false;
		if (cpfCnpj == null) {
			if (other.cpfCnpj != null)
				return false;
		} else if (!cpfCnpj.equals(other.cpfCnpj))
			return false;
		if (cpfConjuge == null) {
			if (other.cpfConjuge != null)
				return false;
		} else if (!cpfConjuge.equals(other.cpfConjuge))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailProcurador == null) {
			if (other.emailProcurador != null)
				return false;
		} else if (!emailProcurador.equals(other.emailProcurador))
			return false;
		if (endResConjuge == null) {
			if (other.endResConjuge != null)
				return false;
		} else if (!endResConjuge.equals(other.endResConjuge))
			return false;
		if (enderecoComercial == null) {
			if (other.enderecoComercial != null)
				return false;
		} else if (!enderecoComercial.equals(other.enderecoComercial))
			return false;
		if (enderecoResidencial == null) {
			if (other.enderecoResidencial != null)
				return false;
		} else if (!enderecoResidencial.equals(other.enderecoResidencial))
			return false;
		if (estadoCivil == null) {
			if (other.estadoCivil != null)
				return false;
		} else if (!estadoCivil.equals(other.estadoCivil))
			return false;
		if (estadoCivilConjuge == null) {
			if (other.estadoCivilConjuge != null)
				return false;
		} else if (!estadoCivilConjuge.equals(other.estadoCivilConjuge))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (id != other.id)
			return false;
		if (imovelAnuncio != other.imovelAnuncio)
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nacionalidade == null) {
			if (other.nacionalidade != null)
				return false;
		} else if (!nacionalidade.equals(other.nacionalidade))
			return false;
		if (nascimento == null) {
			if (other.nascimento != null)
				return false;
		} else if (!nascimento.equals(other.nascimento))
			return false;
		if (nascimentoConjuge == null) {
			if (other.nascimentoConjuge != null)
				return false;
		} else if (!nascimentoConjuge.equals(other.nascimentoConjuge))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeAgencia == null) {
			if (other.nomeAgencia != null)
				return false;
		} else if (!nomeAgencia.equals(other.nomeAgencia))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (pagaPA != other.pagaPA)
			return false;
		if (porcPropriedade == null) {
			if (other.porcPropriedade != null)
				return false;
		} else if (!porcPropriedade.equals(other.porcPropriedade))
			return false;
		if (praca == null) {
			if (other.praca != null)
				return false;
		} else if (!praca.equals(other.praca))
			return false;
		if (prazoContrato == null) {
			if (other.prazoContrato != null)
				return false;
		} else if (!prazoContrato.equals(other.prazoContrato))
			return false;
		if (procurador == null) {
			if (other.procurador != null)
				return false;
		} else if (!procurador.equals(other.procurador))
			return false;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		if (profissaoConjuge == null) {
			if (other.profissaoConjuge != null)
				return false;
		} else if (!profissaoConjuge.equals(other.profissaoConjuge))
			return false;
		if (reajuste == null) {
			if (other.reajuste != null)
				return false;
		} else if (!reajuste.equals(other.reajuste))
			return false;
		if (taxaAdm == null) {
			if (other.taxaAdm != null)
				return false;
		} else if (!taxaAdm.equals(other.taxaAdm))
			return false;
		if (taxaContrato == null) {
			if (other.taxaContrato != null)
				return false;
		} else if (!taxaContrato.equals(other.taxaContrato))
			return false;
		if (telComercial == null) {
			if (other.telComercial != null)
				return false;
		} else if (!telComercial.equals(other.telComercial))
			return false;
		if (telResConjuge == null) {
			if (other.telResConjuge != null)
				return false;
		} else if (!telResConjuge.equals(other.telResConjuge))
			return false;
		if (telResidencial == null) {
			if (other.telResidencial != null)
				return false;
		} else if (!telResidencial.equals(other.telResidencial))
			return false;
		if (telefoneProcurador == null) {
			if (other.telefoneProcurador != null)
				return false;
		} else if (!telefoneProcurador.equals(other.telefoneProcurador))
			return false;
		if (valorVenal == null) {
			if (other.valorVenal != null)
				return false;
		} else if (!valorVenal.equals(other.valorVenal))
			return false;
		return true;
	}



}