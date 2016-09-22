package br.com.oma.intranet.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.nfe;
import br.com.oma.intranet.util.CodigoBarras;
import br.com.oma.omaonline.entidades.atbancos;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cndplano;
import br.com.oma.omaonline.entidades.cpcredor;
import br.com.oma.omaonline.entidades.cpfavor;
import br.com.oma.omaonline.entidades.financeiro_imagem;

public class LancamentoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean usarTED;
	private boolean leitorOptico;
	private boolean semelhante;
	private int codigoCondominio;
	private int validaAlterar;
	private String etiqueta;
	private String cnpj;
	private String valor;
	private String nomeConta;
	private String notaFiscal;
	private String codBanco;
	private String nomeDoBanco;
	private String contaPoupanca;
	private String codAgencia;
	private String cc;
	private String dac;
	private String tipoPessoa;
	private String cpf_cnpj;
	private String tipoPagto;
	private String codMovimento;
	private String codCompensacao;
	private String codigoBarras;

	private String ldInicial;

	private String ldCampo1;
	private String ldCampo2;
	private String ldCampo3;
	private String ldDac;
	private String ldValor;
	private String concSegbarra1;
	private String concSegbarra2;
	private String concSegbarra3;
	private String concSegbarra4;
	private String codBarras;

	private String nomeFavorecido;
	private String idLancamento;
	
	private Date vencimento;
	private String complemento;
	private String complemento1;
	private String complemento2;
	private String complemento3;
	private String complemento4;
	private String complemento5;
	private String complemento6;
	private Date dataEmissaoNF;
	private String empresa;
	private String tipoPagamento;
	private String classificacao;
	private String tipoDocumento = "N.F";
	private String tipoDocumentoPesquisa;
	private String linhaDigitavel;
	private String numeroNotaFiscal;

	private financeiro_imagem imagem;
	private cpcredor fornecedor = new cpcredor();
	private cpfavor favorecido;
	private atbancos banco;
	private nfe nfe;
	private intra_condominios condominio;
	private cndplano contaContabil = new cndplano();
	private CodigoBarras codigoBarrasUtil = new CodigoBarras();
	private cndpagar lancamento = new cndpagar();
	private DebitoAutomatico debitoAutomatico;

	private List<cpfavor> listaFavorecido;
	private List<cndplano> listaConta;
	private List<atbancos> listaBancos;
	private List<nfe> listaXML;
	private List<intra_condominios> listaCondominios;
	private List<DebitoAutomatico> listaDA, fltrDA;

	private String parcelamento = "N";
	private int tipoLancamento = 1;
	private int pcInicial;
	private int pcFinal;
	
	private String pci;
	private String pcf;

	public boolean isUsarTED() {
		return usarTED;
	}

	public void setUsarTED(boolean usarTED) {
		this.usarTED = usarTED;
	}

	public boolean isLeitorOptico() {
		return leitorOptico;
	}

	public void setLeitorOptico(boolean leitorOptico) {
		this.leitorOptico = leitorOptico;
	}

	public int getCodigoCondominio() {
		return codigoCondominio;
	}

	public void setCodigoCondominio(int codigoCondominio) {
		this.codigoCondominio = codigoCondominio;
	}

	public int getValidaAlterar() {
		return validaAlterar;
	}

	public void setValidaAlterar(int validaAlterar) {
		this.validaAlterar = validaAlterar;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	public String getNomeDoBanco() {
		return nomeDoBanco;
	}

	public void setNomeDoBanco(String nomeDoBanco) {
		this.nomeDoBanco = nomeDoBanco;
	}

	public String getContaPoupanca() {
		return contaPoupanca;
	}

	public void setContaPoupanca(String contaPoupanca) {
		this.contaPoupanca = contaPoupanca;
	}

	public String getCodAgencia() {
		return codAgencia;
	}

	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getDac() {
		return dac;
	}

	public void setDac(String dac) {
		this.dac = dac;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getTipoPagto() {
		return tipoPagto;
	}

	public void setTipoPagto(String tipoPagto) {
		this.tipoPagto = tipoPagto;
	}

	public String getCodMovimento() {
		return codMovimento;
	}

	public void setCodMovimento(String codMovimento) {
		this.codMovimento = codMovimento;
	}

	public String getCodCompensacao() {
		return codCompensacao;
	}

	public void setCodCompensacao(String codCompensacao) {
		this.codCompensacao = codCompensacao;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getLdInicial() {
		return ldInicial;
	}

	public void setLdInicial(String ldInicial) {
		this.ldInicial = ldInicial;
	}

	public String getLdCampo1() {
		return ldCampo1;
	}

	public void setLdCampo1(String ldCampo1) {
		this.ldCampo1 = ldCampo1;
	}

	public String getLdCampo2() {
		return ldCampo2;
	}

	public void setLdCampo2(String ldCampo2) {
		this.ldCampo2 = ldCampo2;
	}

	public String getLdCampo3() {
		return ldCampo3;
	}

	public void setLdCampo3(String ldCampo3) {
		this.ldCampo3 = ldCampo3;
	}

	public String getLdDac() {
		return ldDac;
	}

	public void setLdDac(String ldDac) {
		this.ldDac = ldDac;
	}

	public String getLdValor() {
		return ldValor;
	}

	public void setLdValor(String ldValor) {
		this.ldValor = ldValor;
	}

	public String getConcSegbarra1() {
		return concSegbarra1;
	}

	public void setConcSegbarra1(String concSegbarra1) {
		this.concSegbarra1 = concSegbarra1;
	}

	public String getConcSegbarra2() {
		return concSegbarra2;
	}

	public void setConcSegbarra2(String concSegbarra2) {
		this.concSegbarra2 = concSegbarra2;
	}

	public String getConcSegbarra3() {
		return concSegbarra3;
	}

	public void setConcSegbarra3(String concSegbarra3) {
		this.concSegbarra3 = concSegbarra3;
	}

	public String getConcSegbarra4() {
		return concSegbarra4;
	}

	public void setConcSegbarra4(String concSegbarra4) {
		this.concSegbarra4 = concSegbarra4;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public String getNomeFavorecido() {
		return nomeFavorecido;
	}

	public void setNomeFavorecido(String nomeFavorecido) {
		this.nomeFavorecido = nomeFavorecido;
	}

	public String getIdLancamento() {
		return idLancamento;
	}

	public void setIdLancamento(String idLancamento) {
		this.idLancamento = idLancamento;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplemento1() {
		return complemento1;
	}

	public void setComplemento1(String complemento1) {
		this.complemento1 = complemento1;
	}

	public String getComplemento2() {
		return complemento2;
	}

	public void setComplemento2(String complemento2) {
		this.complemento2 = complemento2;
	}

	public String getComplemento3() {
		return complemento3;
	}

	public void setComplemento3(String complemento3) {
		this.complemento3 = complemento3;
	}

	public String getComplemento4() {
		return complemento4;
	}

	public void setComplemento4(String complemento4) {
		this.complemento4 = complemento4;
	}

	public String getComplemento5() {
		return complemento5;
	}

	public void setComplemento5(String complemento5) {
		this.complemento5 = complemento5;
	}

	public String getComplemento6() {
		return complemento6;
	}

	public void setComplemento6(String complemento6) {
		this.complemento6 = complemento6;
	}

	public Date getDataEmissaoNF() {
		return dataEmissaoNF;
	}

	public void setDataEmissaoNF(Date dataEmissaoNF) {
		this.dataEmissaoNF = dataEmissaoNF;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
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

	public String getTipoDocumentoPesquisa() {
		return tipoDocumentoPesquisa;
	}

	public void setTipoDocumentoPesquisa(String tipoDocumentoPesquisa) {
		this.tipoDocumentoPesquisa = tipoDocumentoPesquisa;
	}

	public String getLinhaDigitavel() {
		return linhaDigitavel;
	}

	public void setLinhaDigitavel(String linhaDigitavel) {
		this.linhaDigitavel = linhaDigitavel;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public financeiro_imagem getImagem() {
		return imagem;
	}

	public void setImagem(financeiro_imagem imagem) {
		this.imagem = imagem;
	}

	public cpcredor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(cpcredor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public cpfavor getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(cpfavor favorecido) {
		this.favorecido = favorecido;
	}

	public atbancos getBanco() {
		return banco;
	}

	public void setBanco(atbancos banco) {
		this.banco = banco;
	}

	public nfe getNfe() {
		return nfe;
	}

	public void setNfe(nfe nfe) {
		this.nfe = nfe;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public cndplano getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(cndplano contaContabil) {
		this.contaContabil = contaContabil;
	}

	public CodigoBarras getCodigoBarrasUtil() {
		return codigoBarrasUtil;
	}

	public void setCodigoBarrasUtil(CodigoBarras codigoBarrasUtil) {
		this.codigoBarrasUtil = codigoBarrasUtil;
	}

	public cndpagar getLancamento() {
		return lancamento;
	}

	public void setLancamento(cndpagar lancamento) {
		this.lancamento = lancamento;
	}

	public DebitoAutomatico getDebitoAutomatico() {
		return debitoAutomatico;
	}

	public void setDebitoAutomatico(DebitoAutomatico debitoAutomatico) {
		this.debitoAutomatico = debitoAutomatico;
	}

	public List<cpfavor> getListaFavorecido() {
		return listaFavorecido;
	}

	public void setListaFavorecido(List<cpfavor> listaFavorecido) {
		this.listaFavorecido = listaFavorecido;
	}

	public List<cndplano> getListaConta() {
		return listaConta;
	}

	public void setListaConta(List<cndplano> listaConta) {
		this.listaConta = listaConta;
	}

	public List<atbancos> getListaBancos() {
		return listaBancos;
	}

	public void setListaBancos(List<atbancos> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public List<nfe> getListaXML() {
		return listaXML;
	}

	public void setListaXML(List<nfe> listaXML) {
		this.listaXML = listaXML;
	}

	public List<intra_condominios> getListaCondominios() {
		return listaCondominios;
	}

	public void setListaCondominios(List<intra_condominios> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public List<DebitoAutomatico> getListaDA() {
		return listaDA;
	}

	public void setListaDA(List<DebitoAutomatico> listaDA) {
		this.listaDA = listaDA;
	}

	public List<DebitoAutomatico> getFltrDA() {
		return fltrDA;
	}

	public void setFltrDA(List<DebitoAutomatico> fltrDA) {
		this.fltrDA = fltrDA;
	}

	public int getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(int tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
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

	public String getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(String parcelamento) {
		this.parcelamento = parcelamento;
	}

	public String getPci() {
		return pci;
	}

	public void setPci(String pci) {
		this.pci = pci;
	}

	public String getPcf() {
		return pcf;
	}

	public void setPcf(String pcf) {
		this.pcf = pcf;
	}

	public boolean isSemelhante() {
		return semelhante;
	}

	public void setSemelhante(boolean semelhante) {
		this.semelhante = semelhante;
	}
	

}
