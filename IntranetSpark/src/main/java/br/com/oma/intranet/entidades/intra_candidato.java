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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.joda.time.DateTime;
import org.joda.time.Years;

import com.thoughtworks.xstream.XStream;

@Entity
public class intra_candidato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int codigo;
	@Column(columnDefinition = "varchar(50)")
	private String cargoPretendido;
	@Column(columnDefinition = "varchar(500)")
	private String demaisCargos;
	@Column(columnDefinition = "float")
	private Double pretensaoSalarial;
	@Column(columnDefinition = "varchar(50)")
	private String nome;
	@Column(columnDefinition = "varchar(1)")
	private String sexo;
	@Column(columnDefinition = "varchar(3)")
	private String idade;
	@Column(columnDefinition = "varchar(15)")
	private String estadoCivil;
	@Column(columnDefinition = "varchar(50)")
	private String nacionalidade;
	@Column(columnDefinition = "varchar(50)")
	private String localNascimento;
	@Column(columnDefinition = "datetime")
	private Date dataNascimento;
	@Column(columnDefinition = "varchar(100)")
	private String endereco;
	@Column(columnDefinition = "varchar(20)")
	private String numeroEndereco;
	@Column(columnDefinition = "varchar(50)")
	private String complemento;
	@Column(columnDefinition = "varchar(10)")
	private String cep;
	@Column(columnDefinition = "varchar(100)")
	private String bairro;
	@Column(columnDefinition = "varchar(100)")
	private String cidade;
	@Column(columnDefinition = "varchar(15)")
	private String telResidencial;
	@Column(columnDefinition = "varchar(15)")
	private String telCelular;
	@Column(columnDefinition = "varchar(15)")
	private String telRecado;
	@Column(columnDefinition = "varchar(15)")
	private String rg;
	@Column(columnDefinition = "datetime")
	private Date dataExpedicaoRG;
	@Column(columnDefinition = "varchar(30)")
	private String email;
	@Column(columnDefinition = "varchar(20)")
	private String cpf;
	@Column(columnDefinition = "bit")
	private boolean possuiPis;
	@Column(columnDefinition = "varchar(20)")
	private String numeroPis;
	@Column(columnDefinition = "bit")
	private boolean possuiTituloEleitor;
	@Column(columnDefinition = "bit")
	private boolean possuiCertificadoMilitar;
	@Column(columnDefinition = "varchar(20)")
	private String numeroCtps;
	@Column(columnDefinition = "varchar(10)")
	private String serieCtps;
	@Column(columnDefinition = "datetime")
	private Date dataExpedicaoCtps;
	@Column(columnDefinition = "bit")
	private boolean dispensadoServicoMilitar;
	@Column(columnDefinition = "varchar(20)")
	private String pretendeResidirNoPredio;
	@Column(columnDefinition = "varchar(20)")
	private String periodoTrabalho;
	@Column(columnDefinition = "varchar(20)")
	private String cnh;
	@Column(columnDefinition = "varchar(10)")
	private String categoriaCnh;
	@Column(columnDefinition = "datetime")
	private Date dataValidadeCnh;
	@Column(columnDefinition = "bit")
	private boolean possuiDeficiencia;
	@Column(columnDefinition = "varchar(20)")
	private String tipoDeficiencia;
	@Column(columnDefinition = "bit")
	private boolean fumante;
	@Column(columnDefinition = "varchar(20)")
	private String zonaBairro;
	@Column(columnDefinition = "bit")
	private boolean possuiContaBancaria;
	@Column(columnDefinition = "varchar(30)")
	private String nomeBanco;
	@Column(columnDefinition = "varchar(1)")
	private String tipoConta;
	@Column(columnDefinition = "varchar(10)")
	private String numeroAgencia;
	@Column(columnDefinition = "varchar(10)")
	private String numeroConta;
	@Column(columnDefinition = "varchar(50)")
	private String nomeMae;
	@Column(columnDefinition = "bit")
	private boolean maeTrabalha;
	@Column(columnDefinition = "varchar(50)")
	private String profissaoMae;
	@Column(columnDefinition = "varchar(50)")
	private String nomePai;
	@Column(columnDefinition = "bit")
	private boolean paiTrabalha;
	@Column(columnDefinition = "varchar(50)")
	private String profissaoPai;
	@Column(columnDefinition = "varchar(50)")
	private String nomeConjuge;
	@Column(columnDefinition = "bit")
	private boolean conjugeTrabalha;
	@Column(columnDefinition = "varchar(50)")
	private String profissaoConjuge;
	@Column(columnDefinition = "bit")
	private boolean possuiFilhos;
	@Column(columnDefinition = "varchar(2)")
	private String qtdFilhos;
	@Column(columnDefinition = "varchar(50)")
	private String formacao;
	@Column(columnDefinition = "bit")
	private boolean estudaAtualmente;
	@Column(columnDefinition = "bit")
	private boolean possuiConhecimentoInformatica;
	@Column(columnDefinition = "varchar(20)")
	private String conhecimentoInformatica;
	@Column(columnDefinition = "bit")
	private boolean preCadastro;
	@Column(columnDefinition = "bit")
	private boolean entrevistado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrevista;
	@Temporal(TemporalType.TIMESTAMP)
	private Date horarioChegada;
	@Column(columnDefinition = "varchar(MAX)")
	private String parecerEntrevistador;
	@Column(columnDefinition = "varchar(500)")
	private String cursos;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLevantamento;
	@Column(columnDefinition = "bit")
	private boolean levantamentoFeito;
	@Column(columnDefinition = "bit")
	private boolean bloqLevantamentoCadastral;
	@Column(columnDefinition = "bit")
	private boolean bloqEntrevista;
	@Column(columnDefinition = "bit")
	private boolean bloqContato;
	@Column(columnDefinition = "bit")
	private boolean contratado;
	@Column(columnDefinition = "varchar(MAX)")
	private String obsLevantamento;
	@Column(columnDefinition = "varchar(20)")
	private String comoConheceuOma;
	@Column(columnDefinition = "varchar(255)")
	private String comoConheceuOmaObs;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPreenchimentoFicha;
	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<intra_candidato_prontuario> pronturario = new ArrayList<>();
	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("dataAdimissao ASC")
	private List<intra_candidato_historico_profissional> historicoProfissional = new ArrayList<>();
	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<intra_candidato_dependentes> dependente = new ArrayList<>();
	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<intra_candidato_controle_encaminhamento> controleEncaminhamento = new ArrayList<>();
	@OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<intra_candidato_contatos> contatos = new ArrayList<>();
	@ManyToMany
	private List<intra_candidato_vaga> vagas = new ArrayList<>();
	@Transient
	private String verificaAptoEntrevista, verificaAptoLevantamento;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCargoPretendido() {
		return cargoPretendido;
	}

	public void setCargoPretendido(String cargoPretendido) {
		this.cargoPretendido = cargoPretendido;
	}

	public String getDemaisCargos() {
		return demaisCargos;
	}

	public void setDemaisCargos(String demaisCargos) {
		this.demaisCargos = demaisCargos;
	}

	public Double getPretensaoSalarial() {
		return pretensaoSalarial;
	}

	public void setPretensaoSalarial(Double pretensaoSalarial) {
		this.pretensaoSalarial = pretensaoSalarial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdade() {
		if (this.dataNascimento != null) {
			this.idade = String.valueOf(
					Years.yearsBetween(new DateTime(this.getDataNascimento()), new DateTime(new Date())).getYears());
		}
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getLocalNascimento() {
		return localNascimento;
	}

	public void setLocalNascimento(String localNascimento) {
		this.localNascimento = localNascimento;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getTelResidencial() {
		return telResidencial;
	}

	public void setTelResidencial(String telResidencial) {
		this.telResidencial = telResidencial;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getTelRecado() {
		return telRecado;
	}

	public void setTelRecado(String telRecado) {
		this.telRecado = telRecado;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataExpedicaoRG() {
		return dataExpedicaoRG;
	}

	public void setDataExpedicaoRG(Date dataExpedicaoRG) {
		this.dataExpedicaoRG = dataExpedicaoRG;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isPossuiPis() {
		return possuiPis;
	}

	public void setPossuiPis(boolean possuiPis) {
		this.possuiPis = possuiPis;
	}

	public String getNumeroPis() {
		return numeroPis;
	}

	public void setNumeroPis(String numeroPis) {
		this.numeroPis = numeroPis;
	}

	public boolean isPossuiTituloEleitor() {
		return possuiTituloEleitor;
	}

	public void setPossuiTituloEleitor(boolean possuiTituloEleitor) {
		this.possuiTituloEleitor = possuiTituloEleitor;
	}

	public boolean isPossuiCertificadoMilitar() {
		return possuiCertificadoMilitar;
	}

	public void setPossuiCertificadoMilitar(boolean possuiCertificadoMilitar) {
		this.possuiCertificadoMilitar = possuiCertificadoMilitar;
	}

	public String getNumeroCtps() {
		return numeroCtps;
	}

	public void setNumeroCtps(String numeroCtps) {
		this.numeroCtps = numeroCtps;
	}

	public String getSerieCtps() {
		return serieCtps;
	}

	public void setSerieCtps(String serieCtps) {
		this.serieCtps = serieCtps;
	}

	public Date getDataExpedicaoCtps() {
		return dataExpedicaoCtps;
	}

	public void setDataExpedicaoCtps(Date dataExpedicaoCtps) {
		this.dataExpedicaoCtps = dataExpedicaoCtps;
	}

	public boolean isDispensadoServicoMilitar() {
		return dispensadoServicoMilitar;
	}

	public void setDispensadoServicoMilitar(boolean dispensadoServicoMilitar) {
		this.dispensadoServicoMilitar = dispensadoServicoMilitar;
	}

	public String getPretendeResidirNoPredio() {
		return pretendeResidirNoPredio;
	}

	public void setPretendeResidirNoPredio(String pretendeResidirNoPredio) {
		this.pretendeResidirNoPredio = pretendeResidirNoPredio;
	}

	public String getPeriodoTrabalho() {
		return periodoTrabalho;
	}

	public void setPeriodoTrabalho(String periodoTrabalho) {
		this.periodoTrabalho = periodoTrabalho;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getCategoriaCnh() {
		return categoriaCnh;
	}

	public void setCategoriaCnh(String categoriaCnh) {
		this.categoriaCnh = categoriaCnh;
	}

	public Date getDataValidadeCnh() {
		return dataValidadeCnh;
	}

	public void setDataValidadeCnh(Date dataValidadeCnh) {
		this.dataValidadeCnh = dataValidadeCnh;
	}

	public boolean isPossuiDeficiencia() {
		return possuiDeficiencia;
	}

	public void setPossuiDeficiencia(boolean possuiDeficiencia) {
		this.possuiDeficiencia = possuiDeficiencia;
	}

	public String getTipoDeficiencia() {
		return tipoDeficiencia;
	}

	public void setTipoDeficiencia(String tipoDeficiencia) {
		this.tipoDeficiencia = tipoDeficiencia;
	}

	public boolean isFumante() {
		return fumante;
	}

	public void setFumante(boolean fumante) {
		this.fumante = fumante;
	}

	public String getZonaBairro() {
		return zonaBairro;
	}

	public void setZonaBairro(String zonaBairro) {
		this.zonaBairro = zonaBairro;
	}

	public boolean isPossuiContaBancaria() {
		return possuiContaBancaria;
	}

	public void setPossuiContaBancaria(boolean possuiContaBancaria) {
		this.possuiContaBancaria = possuiContaBancaria;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public boolean isMaeTrabalha() {
		return maeTrabalha;
	}

	public void setMaeTrabalha(boolean maeTrabalha) {
		this.maeTrabalha = maeTrabalha;
	}

	public String getProfissaoMae() {
		return profissaoMae;
	}

	public void setProfissaoMae(String profissaoMae) {
		this.profissaoMae = profissaoMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public boolean isPaiTrabalha() {
		return paiTrabalha;
	}

	public void setPaiTrabalha(boolean paiTrabalha) {
		this.paiTrabalha = paiTrabalha;
	}

	public String getProfissaoPai() {
		return profissaoPai;
	}

	public void setProfissaoPai(String profissaoPai) {
		this.profissaoPai = profissaoPai;
	}

	public String getNomeConjuge() {
		return nomeConjuge;
	}

	public void setNomeConjuge(String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}

	public boolean isConjugeTrabalha() {
		return conjugeTrabalha;
	}

	public void setConjugeTrabalha(boolean conjugeTrabalha) {
		this.conjugeTrabalha = conjugeTrabalha;
	}

	public String getProfissaoConjuge() {
		return profissaoConjuge;
	}

	public void setProfissaoConjuge(String profissaoConjuge) {
		this.profissaoConjuge = profissaoConjuge;
	}

	public boolean isPossuiFilhos() {
		return possuiFilhos;
	}

	public void setPossuiFilhos(boolean possuiFilhos) {
		this.possuiFilhos = possuiFilhos;
	}

	public String getQtdFilhos() {
		return qtdFilhos;
	}

	public void setQtdFilhos(String qtdFilhos) {
		this.qtdFilhos = qtdFilhos;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public boolean isEstudaAtualmente() {
		return estudaAtualmente;
	}

	public void setEstudaAtualmente(boolean estudaAtualmente) {
		this.estudaAtualmente = estudaAtualmente;
	}

	public boolean isPossuiConhecimentoInformatica() {
		return possuiConhecimentoInformatica;
	}

	public void setPossuiConhecimentoInformatica(boolean possuiConhecimentoInformatica) {
		this.possuiConhecimentoInformatica = possuiConhecimentoInformatica;
	}

	public String getConhecimentoInformatica() {
		return conhecimentoInformatica;
	}

	public void setConhecimentoInformatica(String conhecimentoInformatica) {
		this.conhecimentoInformatica = conhecimentoInformatica;
	}

	public boolean isPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(boolean preCadastro) {
		this.preCadastro = preCadastro;
	}

	public boolean isEntrevistado() {
		return entrevistado;
	}

	public void setEntrevistado(boolean entrevistado) {
		this.entrevistado = entrevistado;
	}

	public Date getDataEntrevista() {
		return dataEntrevista;
	}

	public void setDataEntrevista(Date dataEntrevista) {
		this.dataEntrevista = dataEntrevista;
	}

	public Date getHorarioChegada() {
		return horarioChegada;
	}

	public void setHorarioChegada(Date horarioChegada) {
		this.horarioChegada = horarioChegada;
	}

	public String getParecerEntrevistador() {
		return parecerEntrevistador;
	}

	public void setParecerEntrevistador(String parecerEntrevistador) {
		this.parecerEntrevistador = parecerEntrevistador;
	}

	public String getCursos() {
		return cursos;
	}

	public void setCursos(String cursos) {
		this.cursos = cursos;
	}

	public Date getDataLevantamento() {
		return dataLevantamento;
	}

	public void setDataLevantamento(Date dataLevantamento) {
		this.dataLevantamento = dataLevantamento;
	}

	public boolean isLevantamentoFeito() {
		return levantamentoFeito;
	}

	public void setLevantamentoFeito(boolean levantamentoFeito) {
		this.levantamentoFeito = levantamentoFeito;
	}

	public boolean isBloqLevantamentoCadastral() {
		return bloqLevantamentoCadastral;
	}

	public void setBloqLevantamentoCadastral(boolean bloqLevantamentoCadastral) {
		this.bloqLevantamentoCadastral = bloqLevantamentoCadastral;
	}

	public boolean isBloqEntrevista() {
		return bloqEntrevista;
	}

	public void setBloqEntrevista(boolean bloqEntrevista) {
		this.bloqEntrevista = bloqEntrevista;
	}

	public boolean isBloqContato() {
		return bloqContato;
	}

	public void setBloqContato(boolean bloqContato) {
		this.bloqContato = bloqContato;
	}

	public boolean isContratado() {
		return contratado;
	}

	public void setContratado(boolean contratado) {
		this.contratado = contratado;
	}

	public String getObsLevantamento() {
		return obsLevantamento;
	}

	public void setObsLevantamento(String obsLevantamento) {
		this.obsLevantamento = obsLevantamento;
	}

	public String getComoConheceuOma() {
		return comoConheceuOma;
	}

	public void setComoConheceuOma(String comoConheceuOma) {
		this.comoConheceuOma = comoConheceuOma;
	}

	public String getComoConheceuOmaObs() {
		return comoConheceuOmaObs;
	}

	public void setComoConheceuOmaObs(String comoConheceuOmaObs) {
		this.comoConheceuOmaObs = comoConheceuOmaObs;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataPreenchimentoFicha() {
		return dataPreenchimentoFicha;
	}

	public void setDataPreenchimentoFicha(Date dataPreenchimentoFicha) {
		this.dataPreenchimentoFicha = dataPreenchimentoFicha;
	}

	public List<intra_candidato_prontuario> getPronturario() {
		return pronturario;
	}

	public void setPronturario(List<intra_candidato_prontuario> pronturario) {
		this.pronturario = pronturario;
	}

	public List<intra_candidato_historico_profissional> getHistoricoProfissional() {
		return historicoProfissional;
	}

	public void setHistoricoProfissional(List<intra_candidato_historico_profissional> historicoProfissional) {
		this.historicoProfissional = historicoProfissional;
	}

	public List<intra_candidato_dependentes> getDependente() {
		return dependente;
	}

	public void setDependente(List<intra_candidato_dependentes> dependente) {
		this.dependente = dependente;
	}

	public List<intra_candidato_controle_encaminhamento> getControleEncaminhamento() {
		return controleEncaminhamento;
	}

	public void setControleEncaminhamento(List<intra_candidato_controle_encaminhamento> controleEncaminhamento) {
		this.controleEncaminhamento = controleEncaminhamento;
	}

	public List<intra_candidato_contatos> getContatos() {
		return contatos;
	}

	public void setContatos(List<intra_candidato_contatos> contatos) {
		this.contatos = contatos;
	}

	public List<intra_candidato_vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<intra_candidato_vaga> vagas) {
		this.vagas = vagas;
	}

	public String getVerificaAptoEntrevista() {
		this.verificaAptoEntrevista = "";
		if (this.bloqEntrevista && this.entrevistado) {
			this.verificaAptoEntrevista = "SIM";
		}
		if (this.bloqEntrevista && !this.entrevistado) {
			this.verificaAptoEntrevista = "NÃO";
		}
		if (!this.bloqEntrevista && !this.entrevistado) {
			this.verificaAptoEntrevista = "NÃO";
		}
		if (!this.bloqEntrevista && this.entrevistado) {
			this.verificaAptoEntrevista = "INAPTO";
		}
		return this.verificaAptoEntrevista;
	}

	public void setVerificaAptoEntrevista(String verificaAptoEntrevista) {
		this.verificaAptoEntrevista = verificaAptoEntrevista;
	}

	public String getVerificaAptoLevantamento() {
		this.verificaAptoLevantamento = "";
		if (this.bloqLevantamentoCadastral && this.levantamentoFeito) {
			this.verificaAptoLevantamento = "SIM";
		}
		if (this.bloqLevantamentoCadastral && !this.levantamentoFeito) {
			this.verificaAptoLevantamento = "NÃO";
		}
		if (!this.bloqLevantamentoCadastral && !this.levantamentoFeito) {
			this.verificaAptoLevantamento = "NÃO";
		}
		if (!this.bloqLevantamentoCadastral && this.levantamentoFeito) {
			this.verificaAptoLevantamento = "INAPTO";
		}
		return verificaAptoLevantamento;
	}

	public void setVerificaAptoLevantamento(String verificaAptoLevantamento) {
		this.verificaAptoLevantamento = verificaAptoLevantamento;
	}

	public String getColuna() {
		return "<b>Pretensão Salarial:</b> " + this.pretensaoSalarial + "<br/>" + "<b>Cargo:</b> "
				+ this.cargoPretendido + "<br/>" + "<b>Nome:</b> " + this.nome + "<br/>" + "<b>Idade:</b> "
				+ this.idade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
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
		intra_candidato other = (intra_candidato) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		XStream xstream = new XStream();
		return xstream.toXML(this);
	}

}
