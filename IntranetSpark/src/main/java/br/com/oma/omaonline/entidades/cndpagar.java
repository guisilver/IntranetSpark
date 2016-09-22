package br.com.oma.omaonline.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.thoughtworks.xstream.XStream;

@Entity
@Table(name = "cndpagar", schema = "omaonline.dbo")
public class cndpagar implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "codigo", columnDefinition = "int")
	private int codigo;

	@Column(name = "nrolancto", columnDefinition = "int")
	private int nrolancto;

	@Column(name = "status_", columnDefinition = "varchar(1)")
	private String status;

	// fk
	@Column(name = "condominio", columnDefinition = "smallint")
	private short condominio;

	// fk
	@Column(name = "bloco", columnDefinition = "varchar(4)")
	private String bloco;

	// fk
	@Column(name = "conta", columnDefinition = "int")
	private int conta;

	@Column(name = "codigo_hist", columnDefinition = "smallint")
	private short codigoHist;

	@Column(name = "historico", columnDefinition = "varchar(260)")
	private String historico;

	// fk
	@Column(name = "credor", columnDefinition = "varchar(12)")
	private String credor;

	@Column(name = "fornecedor_cnpj", columnDefinition = "varchar(20)")
	private String fornecedorCnpj;

	@Column(name = "valor", columnDefinition = "numeric(14,2)")
	private double valor;

	@Column(name = "vencimento", columnDefinition = "datetime")
	private Date vencimento;

	@Column(name = "dt_receb_bloq", columnDefinition = "datetime")
	private Date dtRecebBloq;

	@Column(name = "dt_receb_nf", columnDefinition = "datetime")
	private Date dtRecebNf;

	@Column(name = "favorecido", columnDefinition = "varchar(50)")
	private String favorecido;

	@Column(name = "tipopagto", columnDefinition = "char(1)")
	private String tipoPagto;

	@Column(name = "contab_empresa", columnDefinition = "smallint")
	private short contabEmpresa;

	@Column(name = "contab_debito", columnDefinition = "numeric(12,0)")
	private double contabDebito;

	@Column(name = "contab_credito", columnDefinition = "numeric(12,0)")
	private double contabCredito;

	// fk
	@Column(name = "conta_bancaria", columnDefinition = "smallint")
	private short contaBancaria;

	@Column(name = "estimado", columnDefinition = "char(1)")
	private String estimado;

	@Column(name = "prox_vencto", columnDefinition = "datetime")
	private Date proxVencto;

	@Column(name = "percentual", columnDefinition = "numeric(6,2)")
	private double percentual;

	@Column(name = "cpmf", columnDefinition = "char(1)")
	private String cpmf;

	@Column(name = "moeda", columnDefinition = "tinyint")
	private short moeda;

	@Column(name = "qtde_moeda", columnDefinition = "numeric(12,4)")
	private double qtdeMoeda;

	@Column(name = "nro_documento", columnDefinition = "varchar(15)")
	private String nroDocumento;

	@Column(name = "tipo_doc", columnDefinition = "char(2)")
	private String tipoDoc;

	@Column(name = "nota_fiscal", columnDefinition = "numeric(10,0)")
	private double notaFiscal;

	@Column(name = "serie_da_nf", columnDefinition = "char(2)")
	private String serieDaNf;

	@Column(name = "modal_pagto", columnDefinition = "char(2)")
	private String modalPagto;

	@Column(name = "banco_destino", columnDefinition = "smallint")
	private short bancoDestino;

	@Column(name = "agenc_destino", columnDefinition = "int")
	private int agencDestino;

	@Column(name = "conta_destino", columnDefinition = "varchar(15)")
	private String contaDestino;

	@Column(name = "nro_remessa", columnDefinition = "int")
	private int nroRemessa;

	@Column(name = "instrucoes_op", columnDefinition = "varchar(40)")
	private String instrucoesOp;

	@Column(name = "tipo_do_doc", columnDefinition = "char(1)")
	private String tipoDoDoc;

	@Column(name = "numero_do_doc", columnDefinition = "int")
	private int numeroDoDoc;

	@Column(name = "finalidade_doc", columnDefinition = "char(2)")
	private String finalidadeDoc;

	@Column(name = "ld_campo_1", columnDefinition = "numeric(12,6)")
	private double ldCampo1;

	@Column(name = "ld_campo_2", columnDefinition = "numeric(12,6)")
	private double ldCampo2;

	@Column(name = "ld_campo_3", columnDefinition = "numeric(12,6)")
	private double ldCampo3;

	@Column(name = "ld_dac", columnDefinition = "tinyint")
	private int ldDac;

	@Column(name = "usuario", columnDefinition = "int")
	private int usuario;

	@Column(name = "nome_agencia", columnDefinition = "varchar(20)")
	private String nomeAgencia;

	@Column(name = "tipo_carteira", columnDefinition = "char(4)")
	private String tipoCarteira;

	@Column(name = "especie_docto", columnDefinition = "char(3)")
	private String especieDocto;

	@Column(name = "cod_compensacao", columnDefinition = "smallint")
	private short codCompensacao;

	@Column(name = "ld_valor", columnDefinition = "numeric(14,0)")
	private double ldValor;

	@Column(name = "conf_remessa", columnDefinition = "int")
	private int confRemessa;

	@Column(name = "dig_age_dest", columnDefinition = "char(2)")
	private String digAgeDest;

	@Column(name = "nro_bancario", columnDefinition = "varchar(15)")
	private String nroBancario;

	@Column(name = "codigo_fav", columnDefinition = "int")
	private int codigoFav;

	@Column(name = "cta_anl_financ", columnDefinition = "int")
	private int ctaAnlFinanc;

	@Column(name = "cod_pagto", columnDefinition = "smallint")
	private short codPagto;

	@Column(name = "mes", columnDefinition = "tinyint")
	private int mes;

	@Column(name = "ano", columnDefinition = "smallint")
	private short ano;

	@Column(name = "cnpj", columnDefinition = "numeric(14,0)")
	private double cnpj;

	@Column(name = "vr_tributo", columnDefinition = "numeric(12,2)")
	private double vrTributo;

	@Column(name = "vr_outros", columnDefinition = "numeric(12,2)")
	private double vrOutros;

	@Column(name = "vr_atual_monet", columnDefinition = "numeric(12,2)")
	private double vrAtualMonet;

	@Column(name = "conc_segbarra1", columnDefinition = "numeric(12,0)")
	private double concSegbarra1;

	@Column(name = "conc_segbarra2", columnDefinition = "numeric(12,0)")
	private double concSegbarra2;

	@Column(name = "conc_segbarra3", columnDefinition = "numeric(12,0)")
	private double concSegbarra3;

	@Column(name = "conc_segbarra4", columnDefinition = "numeric(12,0)")
	private double concSegbarra4;

	@Column(name = "pg_credpoup", columnDefinition = "char(1)")
	private String pgCredpoup;

	@Column(name = "prev_nrolancto", columnDefinition = "int")
	private int prevNrolancto;

	@Column(name = "data_inclusao", columnDefinition = "datetime")
	private Date dataInclusao;

	@Column(name = "reter_imposto", columnDefinition = "char(1)")
	private String reterImposto;

	@Column(name = "nro_docto", columnDefinition = "char(20)")
	private String nroDocto;

	@Column(name = "dt_emiss_docto", columnDefinition = "datetime")
	private Date dtEmissDocto;

	@Column(name = "cod_receita", columnDefinition = "smallint")
	private short codReceita;

	@Column(name = "data_apuracao", columnDefinition = "datetime")
	private Date dataApuracao;

	@Column(name = "documento", columnDefinition = "varchar(10)")
	private String documento;

	@Column(name = "cod_movto", columnDefinition = "smallint")
	private short codMovto;

	@Column(name = "valor_lancto", columnDefinition = "numeric(14,2)")
	private double valorLancto;

	@Column(name = "valor_retencao", columnDefinition = "numeric(14,2)")
	private double valorRetencao;

	@Column(name = "incide_tx_adm", columnDefinition = "tinyint")
	private int incideTxAdm;

	@Column(name = "autorizacao", columnDefinition = "char(1)")
	private String autorizacao;

	@Column(name = "tipo_pessoa", columnDefinition = "char(1)")
	private String tipoPessoa;

	@Column(name = "cb_fich_fornec", columnDefinition = "tinyint")
	private int cbFichFornec;

	@Column(name = "cb_nota_fornec", columnDefinition = "tinyint")
	private int cbNotaFornec;

	@Column(name = "contribuinte", columnDefinition = "varchar(30)")
	private String contribuinte;

	@Column(name = "data_alteracao", columnDefinition = "datetime")
	private Date dataAlteracao;

	@Column(name = "enviado", columnDefinition = "tinyint")
	private int enviado;

	@Column(name = "origem_lancto", columnDefinition = "tinyint")
	private int origemLancto;

	@Column(name = "codigo_barra", columnDefinition = "varchar(44)")
	private String codigoBarra;

	@Column(name = "flag_imposto", columnDefinition = "tinyint")
	private int flagImposto;

	@Column(name = "cheq_em_process", columnDefinition = "tinyint")
	private int cheqEmProcess;

	@Column(name = "forne_gps", columnDefinition = "varchar(12)")
	private String forneGps;

	@Column(name = "docto_integra", columnDefinition = "varchar(50)")
	private String doctoIntegra;

	@Column(name = "tipo_conta", columnDefinition = "varchar(2)")
	private String tipoConta;

	@Column(name = "declaracao", columnDefinition = "tinyint")
	private int declaracao;

	@Column(name = "emissao_nf", columnDefinition = "datetime")
	private Date emissaoNf;

	@Column(name = "tipo_nf", columnDefinition = "varchar(5)")
	private String tipoNf;

	@Column(name = "numero_nf", columnDefinition = "bigint")
	private BigInteger numeroNf;

	@Column(name = "serie_nf", columnDefinition = "varchar(5)")
	private String serieNf;

	@Column(name = "valor_nf", columnDefinition = "numeric(12,2)")
	private double valorNf;

	@Column(name = "rateio", columnDefinition = "int")
	private int rateio;

	@Column(name = "nro_id", columnDefinition = "numeric(12,0)")
	private double nroId;

	@Column(name = "nro_cotacao", columnDefinition = "numeric(12,0)")
	private double nroCotacao;

	@Column(name = "local_serv", columnDefinition = "bit")
	private boolean localServ;

	@Column(name = "BASE_ISSQN", columnDefinition = "numeric(12,2)")
	private double baseIssqn;

	@Column(name = "BASE_INSS", columnDefinition = "numeric(12,2)")
	private double baseInss;

	@Column(name = "BASE_IR_CSLL", columnDefinition = "numeric(12,2)")
	private double baseIrCsll;

	@Column(name = "empresa", columnDefinition = "varchar(100)")
	private String empresa;

	@Column(name = "nf", columnDefinition = "varchar(100)")
	private String nf;

	@Column(name = "hist", columnDefinition = "varchar(max)")
	private String hist;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(schema = "omaonline.dbo")
	private Collection<financeiro_imagem> imagens = new ArrayList<financeiro_imagem>();

	@Column(name = "classificacao", columnDefinition = "varchar(20)")
	private String classificacao;

	@Column(name = "tipoDocumento", columnDefinition = "varchar(20)")
	private String tipoDocumento;

	@Column(name = "motivoReprovacao", columnDefinition = "varchar(500)")
	private String motivoReprovacao;

	@Column(name = "rateado", columnDefinition = "char(1)")
	private String rateado;

	@Column(name = "parcelado", columnDefinition = "char(1)")
	private String parcelado;

	@Column(name = "pc_inicial", columnDefinition = "int")
	private int pcInicial;

	@Column(name = "pc_final", columnDefinition = "int")
	private int pcFinal;

	@Column(name = "cod_rateio", columnDefinition = "int")
	private int codigoRateio;

	@Column(name = "adicionado_por", columnDefinition = "varchar(50)")
	private String adicionadoPor;

	@Column(name = "tipo_conta_bancaria", columnDefinition = "varchar(10)")
	private String tipoContaBancaria;

	@Column(columnDefinition = "char(1)")
	private String obs;

	@Column(name = "cod_parcelado", columnDefinition = "int")
	private int codigoParcelado;

	@Column(name = "valida_parcelado", columnDefinition = "bit")
	private boolean validaParcelado;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(schema = "omaonline.dbo")
	private Collection<cndpagar_aprovacao> aprovadores = new ArrayList<cndpagar_aprovacao>();

	@Column(name = "aguardando_completar_resumido", columnDefinition = "bit")
	private boolean aguardandoCompletarResumido = false;

	@Column(name = "pre_lancamento", columnDefinition = "bit")
	private boolean preLancamento = false;

	@Column(name = "feito_preLancto_sip", columnDefinition = "datetime")
	private Date feitoPreLanctoSIP;

	@Column(name = "feito_fiscal_sip", columnDefinition = "datetime")
	private Date feitoFiscalSIP;

	@Column(name = "feito_lancto_sip", columnDefinition = "datetime")
	private Date feitoLanctoSIP;

	@Column(name = "feito_gerente_sip", columnDefinition = "datetime")
	private Date feitoGerenteSIP;

	@Column(name = "finalizado_sip", columnDefinition = "datetime")
	private Date finalizadoSIP;

	@Column(name = "status_sip")
	private int statusSIP;

	@Column(name = "suspenso_gerente", columnDefinition = "smallint")
	private int suspensoGerente;

	@Column(name = "suspenso_contas", columnDefinition = "smallint")
	private int suspensoContas;

	@Column(name = "suspenso_fiscal", columnDefinition = "smallint")
	private int suspensoFiscal;

	@Column(name = "reprovado_fiscal", columnDefinition = "bit")
	private boolean reprovadoFiscal;

	@Column(name = "tipo_lancamento", columnDefinition = "smallint")
	private int tipoLancamento;

	@Column(name = "visto_gerente", columnDefinition = "bit")
	private boolean vistoGerente;

	@Column(name = "codigo_gerente", columnDefinition = "int")
	private int codigoGerente;

	@Column(columnDefinition = "bit")
	private boolean semelhante;

	@Transient
	private double consumo;

	@Transient
	private String cndCodNome;

	@Transient
	private String valorStr;

	@Transient
	DecimalFormat df = new DecimalFormat("###,###,###.00");

	public int getCodigoRateio() {
		return codigoRateio;
	}

	public void setCodigoRateio(int codigoRateio) {
		this.codigoRateio = codigoRateio;
	}

	public int getNrolancto() {
		return nrolancto;
	}

	public void setNrolancto(int nrolancto) {
		this.nrolancto = nrolancto;
	}

	public short getCondominio() {
		return condominio;
	}

	public void setCondominio(short condominio) {
		this.condominio = condominio;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public short getCodigoHist() {
		return codigoHist;
	}

	public void setCodigoHist(short codigoHist) {
		this.codigoHist = codigoHist;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public String getFornecedorCnpj() {
		return fornecedorCnpj;
	}

	public void setFornecedorCnpj(String fornecedorCnpj) {
		this.fornecedorCnpj = fornecedorCnpj;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Date getDtRecebBloq() {
		return dtRecebBloq;
	}

	public void setDtRecebBloq(Date dtRecebBloq) {
		this.dtRecebBloq = dtRecebBloq;
	}

	public Date getDtRecebNf() {
		return dtRecebNf;
	}

	public void setDtRecebNf(Date dtRecebNf) {
		this.dtRecebNf = dtRecebNf;
	}

	public String getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(String favorecido) {
		this.favorecido = favorecido;
	}

	public String getTipoPagto() {
		return tipoPagto;
	}

	public void setTipoPagto(String tipoPagto) {
		this.tipoPagto = tipoPagto;
	}

	public short getContabEmpresa() {
		return contabEmpresa;
	}

	public void setContabEmpresa(short contabEmpresa) {
		this.contabEmpresa = contabEmpresa;
	}

	public double getContabDebito() {
		return contabDebito;
	}

	public void setContabDebito(double contabDebito) {
		this.contabDebito = contabDebito;
	}

	public double getContabCredito() {
		return contabCredito;
	}

	public void setContab_credito(double contabCredito) {
		this.contabCredito = contabCredito;
	}

	public short getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(short contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public String getEstimado() {
		return estimado;
	}

	public void setEstimado(String estimado) {
		this.estimado = estimado;
	}

	public Date getProxVencto() {
		return proxVencto;
	}

	public void setProxVencto(Date proxVencto) {
		this.proxVencto = proxVencto;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

	public String getCpmf() {
		return cpmf;
	}

	public void setCpmf(String cpmf) {
		this.cpmf = cpmf;
	}

	public short getMoeda() {
		return moeda;
	}

	public void setMoeda(short moeda) {
		this.moeda = moeda;
	}

	public double getQtdeMoeda() {
		return qtdeMoeda;
	}

	public void setQtdeMoeda(double qtdeMoeda) {
		this.qtdeMoeda = qtdeMoeda;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public double getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(double notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getSerieDaNf() {
		return serieDaNf;
	}

	public void setSerieDaNf(String serieDaNf) {
		this.serieDaNf = serieDaNf;
	}

	public String getModalPagto() {
		return modalPagto;
	}

	public void setModalPagto(String modalPagto) {
		this.modalPagto = modalPagto;
	}

	public short getBancoDestino() {
		return bancoDestino;
	}

	public void setBancoDestino(short bancoDestino) {
		this.bancoDestino = bancoDestino;
	}

	public int getAgencDestino() {
		return agencDestino;
	}

	public void setAgencDestino(int agencDestino) {
		this.agencDestino = agencDestino;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public int getNroRemessa() {
		return nroRemessa;
	}

	public void setNroRemessa(int nroRemessa) {
		this.nroRemessa = nroRemessa;
	}

	public String getInstrucoesOp() {
		return instrucoesOp;
	}

	public void setInstrucoesOp(String instrucoesOp) {
		this.instrucoesOp = instrucoesOp;
	}

	public String getTipoDoDoc() {
		return tipoDoDoc;
	}

	public void setTipoDoDoc(String tipoDoDoc) {
		this.tipoDoDoc = tipoDoDoc;
	}

	public int getNumeroDoDoc() {
		return numeroDoDoc;
	}

	public void setNumeroDoDoc(int numeroDoDoc) {
		this.numeroDoDoc = numeroDoDoc;
	}

	public String getFinalidadeDoc() {
		return finalidadeDoc;
	}

	public void setFinalidadeDoc(String finalidadeDoc) {
		this.finalidadeDoc = finalidadeDoc;
	}

	public double getLdCampo1() {
		return ldCampo1;
	}

	public void setLdCampo1(double ldCampo1) {
		this.ldCampo1 = ldCampo1;
	}

	public double getLdCampo2() {
		return ldCampo2;
	}

	public void setLdCampo2(double ldCampo2) {
		this.ldCampo2 = ldCampo2;
	}

	public double getLdCampo3() {
		return ldCampo3;
	}

	public void setLdCampo3(double ldCampo3) {
		this.ldCampo3 = ldCampo3;
	}

	public int getLdDac() {
		return ldDac;
	}

	public void setLdDac(int ldDac) {
		this.ldDac = ldDac;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}

	public String getTipoCarteira() {
		return tipoCarteira;
	}

	public void setTipoCarteira(String tipoCarteira) {
		this.tipoCarteira = tipoCarteira;
	}

	public String getEspecieDocto() {
		return especieDocto;
	}

	public void setEspecieDocto(String especieDocto) {
		this.especieDocto = especieDocto;
	}

	public short getCodCompensacao() {
		return codCompensacao;
	}

	public void setCodCompensacao(short codCompensacao) {
		this.codCompensacao = codCompensacao;
	}

	public double getLdValor() {
		return ldValor;
	}

	public void setLdValor(double ldValor) {
		this.ldValor = ldValor;
	}

	public int getConfRemessa() {
		return confRemessa;
	}

	public void setConfRemessa(int confRemessa) {
		this.confRemessa = confRemessa;
	}

	public String getDigAgeDest() {
		return digAgeDest;
	}

	public void setDigAgeDest(String digAgeDest) {
		this.digAgeDest = digAgeDest;
	}

	public String getNroBancario() {
		return nroBancario;
	}

	public void setNroBancario(String nroBancario) {
		this.nroBancario = nroBancario;
	}

	public int getCodigoFav() {
		return codigoFav;
	}

	public void setCodigoFav(int codigoFav) {
		this.codigoFav = codigoFav;
	}

	public int getCtaAnlFinanc() {
		return ctaAnlFinanc;
	}

	public void setCtaAnlFinanc(int ctaAnlFinanc) {
		this.ctaAnlFinanc = ctaAnlFinanc;
	}

	public short getCodPagto() {
		return codPagto;
	}

	public void setCodPagto(short codPagto) {
		this.codPagto = codPagto;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public short getAno() {
		return ano;
	}

	public void setAno(short ano) {
		this.ano = ano;
	}

	public double getCnpj() {
		return cnpj;
	}

	public void setCnpj(double cnpj) {
		this.cnpj = cnpj;
	}

	public double getVrTributo() {
		return vrTributo;
	}

	public void setVrTributo(double vrTributo) {
		this.vrTributo = vrTributo;
	}

	public double getVrOutros() {
		return vrOutros;
	}

	public void setVrOutros(double vrOutros) {
		this.vrOutros = vrOutros;
	}

	public double getVrAtualMonet() {
		return vrAtualMonet;
	}

	public void setVrAtualMonet(double vrAtualMonet) {
		this.vrAtualMonet = vrAtualMonet;
	}

	public double getConcSegbarra1() {
		return concSegbarra1;
	}

	public void setConcSegbarra1(double concSegbarra1) {
		this.concSegbarra1 = concSegbarra1;
	}

	public double getConcSegbarra2() {
		return concSegbarra2;
	}

	public void setConcSegbarra2(double concSegbarra2) {
		this.concSegbarra2 = concSegbarra2;
	}

	public double getConcSegbarra3() {
		return concSegbarra3;
	}

	public void setConcSegbarra3(double concSegbarra3) {
		this.concSegbarra3 = concSegbarra3;
	}

	public double getConcSegbarra4() {
		return concSegbarra4;
	}

	public void setConcSegbarra4(double concSegbarra4) {
		this.concSegbarra4 = concSegbarra4;
	}

	public String getPgCredpoup() {
		return pgCredpoup;
	}

	public void setPgCredpoup(String pgCredpoup) {
		this.pgCredpoup = pgCredpoup;
	}

	public int getPrevNrolancto() {
		return prevNrolancto;
	}

	public void setPrevNrolancto(int prevNrolancto) {
		this.prevNrolancto = prevNrolancto;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getReterImposto() {
		return reterImposto;
	}

	public void setReterImposto(String reterImposto) {
		this.reterImposto = reterImposto;
	}

	public String getNroDocto() {
		return nroDocto;
	}

	public void setNroDocto(String nroDocto) {
		this.nroDocto = nroDocto;
	}

	public Date getDtEmissDocto() {
		return dtEmissDocto;
	}

	public void setDtEmissDocto(Date dtEmissDocto) {
		this.dtEmissDocto = dtEmissDocto;
	}

	public short getCodReceita() {
		return codReceita;
	}

	public void setCodReceita(short codReceita) {
		this.codReceita = codReceita;
	}

	public Date getDataApuracao() {
		return dataApuracao;
	}

	public void setDataApuracao(Date dataApuracao) {
		this.dataApuracao = dataApuracao;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public short getCodMovto() {
		return codMovto;
	}

	public void setCodMovto(short codMovto) {
		this.codMovto = codMovto;
	}

	public double getValorLancto() {
		return valorLancto;
	}

	public void setValorLancto(double valorLancto) {
		this.valorLancto = valorLancto;
	}

	public double getValorRetencao() {
		return valorRetencao;
	}

	public void setValorRetencao(double valorRetencao) {
		this.valorRetencao = valorRetencao;
	}

	public int getIncideTxAdm() {
		return incideTxAdm;
	}

	public void setIncideTxAdm(int incideTxAdm) {
		this.incideTxAdm = incideTxAdm;
	}

	public String getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public int getCbFichFornec() {
		return cbFichFornec;
	}

	public void setCbFichFornec(int cbFichFornec) {
		this.cbFichFornec = cbFichFornec;
	}

	public int getCbNotaFornec() {
		return cbNotaFornec;
	}

	public void setCbNotaFornec(int cbNotaFornec) {
		this.cbNotaFornec = cbNotaFornec;
	}

	public String getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(String contribuinte) {
		this.contribuinte = contribuinte;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public int getEnviado() {
		return enviado;
	}

	public void setEnviado(int enviado) {
		this.enviado = enviado;
	}

	public int getOrigemLancto() {
		return origemLancto;
	}

	public void setOrigemLancto(int origemLancto) {
		this.origemLancto = origemLancto;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public int getFlagImposto() {
		return flagImposto;
	}

	public void setFlagImposto(int flagImposto) {
		this.flagImposto = flagImposto;
	}

	public int getCheqEmProcess() {
		return cheqEmProcess;
	}

	public void setCheqEmProcess(int cheqEmProcess) {
		this.cheqEmProcess = cheqEmProcess;
	}

	public String getForneGps() {
		return forneGps;
	}

	public void setForneGps(String forneGps) {
		this.forneGps = forneGps;
	}

	public String getDoctoIntegra() {
		return doctoIntegra;
	}

	public void setDoctoIntegra(String doctoIntegra) {
		this.doctoIntegra = doctoIntegra;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public int getDeclaracao() {
		return declaracao;
	}

	public void setDeclaracao(int declaracao) {
		this.declaracao = declaracao;
	}

	public Date getEmissaoNf() {
		return emissaoNf;
	}

	public void setEmissaoNf(Date emissaoNf) {
		this.emissaoNf = emissaoNf;
	}

	public String getTipoNf() {
		return tipoNf;
	}

	public void setTipoNf(String tipoNf) {
		this.tipoNf = tipoNf;
	}

	public BigInteger getNumeroNf() {
		return numeroNf;
	}

	public void setNumeroNf(BigInteger numeroNf) {
		this.numeroNf = numeroNf;
	}

	public String getSerieNf() {
		return serieNf;
	}

	public void setSerieNf(String serieNf) {
		this.serieNf = serieNf;
	}

	public double getValorNf() {
		return valorNf;
	}

	public void setValorNf(double valorNf) {
		this.valorNf = valorNf;
	}

	public int getRateio() {
		return rateio;
	}

	public void setRateio(int rateio) {
		this.rateio = rateio;
	}

	public double getNroId() {
		return nroId;
	}

	public void setNroId(double nroId) {
		this.nroId = nroId;
	}

	public double getNroCotacao() {
		return nroCotacao;
	}

	public void setNroCotacao(double nroCotacao) {
		this.nroCotacao = nroCotacao;
	}

	public boolean isLocalServ() {
		return localServ;
	}

	public void setLocalServ(boolean localServ) {
		this.localServ = localServ;
	}

	public double getBaseIssqn() {
		return baseIssqn;
	}

	public void setBaseIssqn(double baseIssqn) {
		this.baseIssqn = baseIssqn;
	}

	public double getBaseInss() {
		return baseInss;
	}

	public void setBaseInss(double baseInss) {
		this.baseInss = baseInss;
	}

	public double getBaseIrCsll() {
		return baseIrCsll;
	}

	public void setBaseIrCsll(double baseIrCsll) {
		this.baseIrCsll = baseIrCsll;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<financeiro_imagem> getImagens() {
		return imagens;
	}

	public void setImagens(Collection<financeiro_imagem> imagens) {
		this.imagens = imagens;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getHist() {
		return hist;
	}

	public void setHist(String hist) {
		this.hist = hist;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getMotivoReprovacao() {
		return motivoReprovacao;
	}

	public void setMotivoReprovacao(String motivoReprovacao) {
		this.motivoReprovacao = motivoReprovacao;
	}

	public String getRateado() {
		return rateado;
	}

	public void setRateado(String rateado) {
		this.rateado = rateado;
	}

	public String getParcelado() {
		return parcelado;
	}

	public void setParcelado(String parcelado) {
		this.parcelado = parcelado;
	}

	public int getPcInicial() {
		return pcInicial;
	}

	public void setPcInicial(int pcInicial) {
		this.pcInicial = pcInicial;
	}

	public int getPcFinal() {
		return pcFinal;
	}

	public void setPcFinal(int pcFinal) {
		this.pcFinal = pcFinal;
	}

	public String getAdicionadoPor() {
		return adicionadoPor;
	}

	public void setAdicionadoPor(String adicionadoPor) {
		this.adicionadoPor = adicionadoPor;
	}

	public String getTipoContaBancaria() {
		return tipoContaBancaria;
	}

	public void setTipoContaBancaria(String tipoContaBancaria) {
		this.tipoContaBancaria = tipoContaBancaria;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getCodigoParcelado() {
		return codigoParcelado;
	}

	public void setCodigoParcelado(int codigoParcelado) {
		this.codigoParcelado = codigoParcelado;
	}

	public boolean isValidaParcelado() {
		return validaParcelado;
	}

	public void setValidaParcelado(boolean validaParcelado) {
		this.validaParcelado = validaParcelado;
	}

	public Collection<cndpagar_aprovacao> getAprovadores() {
		return aprovadores;
	}

	public void setAprovadores(Collection<cndpagar_aprovacao> aprovadores) {
		this.aprovadores = aprovadores;
	}

	public boolean isAguardandoCompletarResumido() {
		return aguardandoCompletarResumido;
	}

	public void setAguardandoCompletarResumido(boolean aguardandoCompletarResumido) {
		this.aguardandoCompletarResumido = aguardandoCompletarResumido;
	}

	public boolean isPreLancamento() {
		return preLancamento;
	}

	public void setPreLancamento(boolean preLancamento) {
		this.preLancamento = preLancamento;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	public String getCndCodNome() {
		return cndCodNome;
	}

	public void setCndCodNome(String cndCodNome) {
		this.cndCodNome = cndCodNome;
	}

	public String getValorStr() {
		if (this.valor > 0) {
			this.valorStr = df.format(this.valor);
		}
		return valorStr;
	}

	public void setValorStr(String valorStr) {
		this.valorStr = valorStr;
	}

	public int getStatusSIP() {
		return statusSIP;
	}

	public void setStatusSIP(int statusSIP) {
		this.statusSIP = statusSIP;
	}

	public Date getFeitoPreLanctoSIP() {
		return feitoPreLanctoSIP;
	}

	public void setFeitoPreLanctoSIP(Date feitoPreLanctoSIP) {
		this.feitoPreLanctoSIP = feitoPreLanctoSIP;
	}

	public Date getFeitoFiscalSIP() {
		return feitoFiscalSIP;
	}

	public void setFeitoFiscalSIP(Date feitoFiscalSIP) {
		this.feitoFiscalSIP = feitoFiscalSIP;
	}

	public Date getFeitoLanctoSIP() {
		return feitoLanctoSIP;
	}

	public void setFeitoLanctoSIP(Date feitoLanctoSIP) {
		this.feitoLanctoSIP = feitoLanctoSIP;
	}

	public Date getFeitoGerenteSIP() {
		return feitoGerenteSIP;
	}

	public void setFeitoGerenteSIP(Date feitoGerenteSIP) {
		this.feitoGerenteSIP = feitoGerenteSIP;
	}

	public Date getFinalizadoSIP() {
		return finalizadoSIP;
	}

	public void setFinalizadoSIP(Date finalizadoSIP) {
		this.finalizadoSIP = finalizadoSIP;
	}

	public int getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(int tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public boolean isVistoGerente() {
		return vistoGerente;
	}

	public void setVistoGerente(boolean vistoGerente) {
		this.vistoGerente = vistoGerente;
	}

	public int getSuspensoGerente() {
		return suspensoGerente;
	}

	public void setSuspensoGerente(int suspensoGerente) {
		this.suspensoGerente = suspensoGerente;
	}

	public int getSuspensoContas() {
		return suspensoContas;
	}

	public void setSuspensoContas(int suspensoContas) {
		this.suspensoContas = suspensoContas;
	}

	public int getSuspensoFiscal() {
		return suspensoFiscal;
	}

	public void setSuspensoFiscal(int suspensoFiscal) {
		this.suspensoFiscal = suspensoFiscal;
	}

	public boolean isReprovadoFiscal() {
		return reprovadoFiscal;
	}

	public void setReprovadoFiscal(boolean reprovadoFiscal) {
		this.reprovadoFiscal = reprovadoFiscal;
	}

	public int getCodigoGerente() {
		return codigoGerente;
	}

	public void setCodigoGerente(int codigoGerente) {
		this.codigoGerente = codigoGerente;
	}

	public boolean isSemelhante() {
		return semelhante;
	}

	public void setSemelhante(boolean semelhante) {
		this.semelhante = semelhante;
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
		cndpagar other = (cndpagar) obj;
		if (codigo != other.codigo)
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

	@Override
	public String toString() {
		XStream xstream = new XStream();
		return xstream.toXML(this);
	}

}
