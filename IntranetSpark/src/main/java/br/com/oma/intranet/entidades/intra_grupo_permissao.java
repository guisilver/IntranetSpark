package br.com.oma.intranet.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class intra_grupo_permissao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3170916396051067701L;

	@Id
	@Column(name = "nome_grupo", columnDefinition = "varchar(50)")
	private String nomeGrupo;

	// MENU ADMINISTRADOR
	@Column(name = "sys_admin")
	private boolean sysAdmin;

	// MENU COBRANCA
	@Column(name = "sys_cobranca")
	private boolean sysCobranca;
	@Column(name = "cobranca_add")
	private boolean cobrancaAdd;
	@Column(name = "cobranca_change")
	private boolean cobrancaChange;
	@Column(name = "cobranca_Consult")
	private boolean cobrancaConsult;
	@Column(name = "cobranca_remove")
	private boolean cobrancaRemove;
	// MENU COMENRCIAL

	// MENU CONDOMINIO
	@Column(name = "sys_condominio")
	private boolean sysCondominio;
	@Column(name = "condominio_add")
	private boolean condominioAdd;
	@Column(name = "condominio_change")
	private boolean condominioChange;
	@Column(name = "condominio_Consult")
	private boolean condominioConsult;
	@Column(name = "condominio_remove")
	private boolean condominioRemove;

	// MENU CONTABILIDADE
	@Column(name = "sys_contabilidade")
	private boolean sysContabilidade;

	/** SISTEMA BAIXAR DOCUMENTOS **/
	@Column(name = "img_recebido_add")
	private boolean imagemRecebidaAdd;
	@Column(name = "img_recebido_consult")
	private boolean imagemRecebidaConsult;

	// MENU CONTAS
	/** SISTEMA DE Contas **/
	@Column(name = "sys_Contas")
	private boolean sysContas;

	// MENU CPD

	// MENU DIRETORIA
	/** SISTEMA DE DIRETORIA **/
	@Column(name = "sys_diretoria")
	private boolean sysDiretoria;

	@Column(name = "diretoria_add")
	private boolean diretoriaAdd;
	@Column(name = "diretoria_change")
	private boolean diretoriaChange;
	@Column(name = "diretoria_consult")
	private boolean diretoriaConsult;
	@Column(name = "diretoria_remove")
	private boolean diretoriaRemove;

	/** SITEMA PAPERCUT **/
	@Column(name = "sys_papercut")
	private boolean sysPapercut;
	@Column(name = "papercut_add")
	private boolean papercutAdd;
	@Column(name = "papercut_change")
	private boolean papercutChange;
	@Column(name = "papercut_consult")
	private boolean papercutConsult;
	@Column(name = "papercut_remove")
	private boolean papercutRemove;

	/** SITEMA SERVIÇOS **/
	@Column(name = "sys_servicos")
	private boolean sysServicos;
	@Column(name = "servicos_add")
	private boolean servicosAdd;
	@Column(name = "servicos_change")
	private boolean servicosChange;
	@Column(name = "servicos_consult")
	private boolean servicosConsult;
	@Column(name = "servicos_remove")
	private boolean servicosRemove;

	// MENU DP
	/** SISTEMA DE DP **/
	@Column(name = "sys_DP")
	private boolean sysDP;
	@Column(name = "dp_add")
	private boolean dpAdd;
	@Column(name = "dp_change")
	private boolean dpChange;
	@Column(name = "dp_consult")
	private boolean dpConsult;
	@Column(name = "dp_remove")
	private boolean dpRemove;

	// MENU EMISSAO

	@Column(name = "sys_emissao")
	private boolean sysEmissao;

	@Column(name = "emissao_add")
	private boolean emissaoAdd;
	@Column(name = "emissao_change")
	private boolean emissaoChange;
	@Column(name = "emissao_consult")
	private boolean emissaoConsult;
	@Column(name = "emissao_remove")
	private boolean emissaoRemove;

	// MENU EXPEDICAO

	// Financeiro
	@Column(name = "sys_financeiro", columnDefinition = "bit")
	private boolean sysFinanceiro;
	@Column(columnDefinition = "bit")
	private boolean lancamento;
	@Column(name = "consultar_lancamento", columnDefinition = "bit")
	private boolean consultarLancamento;
	@Column(name = "historico_lancamento", columnDefinition = "bit")
	private boolean historicoLancamento;
	@Column(name = "alterar_lancto", columnDefinition = "bit")
	private boolean alterarLancto;
	@Column(name = "pre_aprovar_lancto", columnDefinition = "bit")
	private boolean preAprovarLancto;
	@Column(name = "aprovar_lancto", columnDefinition = "bit")
	private boolean aprovarLancto;
	@Column(name = "excluir_lancto", columnDefinition = "bit")
	private boolean excluirLancto;
	@Column(name = "reprovar_lancto", columnDefinition = "bit")
	private boolean reprovarLancto;
	@Column(name = "protocolos", columnDefinition = "bit")
	private boolean protocolos;
	@Column(name = "adicionar_parcela", columnDefinition = "bit")
	private boolean adicionarParcela;
	@Column(name = "excluir_etiqueta_siga", columnDefinition = "bit")
	private boolean excluirEtiquetaSiga;

	// MENU GERENCIA
	/** SISTEMA DE ADVETENCIA E MULTA **/
	@Column(name = "sys_advert_mult")
	private boolean sysAdvertMult;

	@Column(name = "advert_mult_add")
	private boolean advertMultAdd;
	@Column(name = "advert_mult_change")
	private boolean advertMultChange;
	@Column(name = "advert_mult_consult")
	private boolean advertMultConsult;
	@Column(name = "advert_mult_remove")
	private boolean advertMultRemove;

	/** SISTEMA DE ASSEMBLEIA **/
	@Column(name = "sys_assembleia")
	private boolean sysAssembleia;
	@Column(name = "assembleia_add")
	private boolean assembleiaAdd;
	@Column(name = "assembleia_change")
	private boolean assembleiaChange;
	@Column(name = "assembleia_consult")
	private boolean assembleiaConsult;
	@Column(name = "assembleia_remove")
	private boolean assembleiaRemove;

	/** SISTEMA CONTROLE DE CONTAS **/
	@Column(name = "sys_controle_contas")
	private boolean sysControleContas;
	@Column(name = "controle_contas_add")
	private boolean controleContasAdd;
	@Column(name = "controle_contas_change")
	private boolean controleContasChange;
	@Column(name = "controle_contas_consult")
	private boolean controleContasConsult;
	@Column(name = "controle_contas_remove")
	private boolean controleContasRemove;

	/** SISTEMA CORPO DIRETIVO **/
	@Column(name = "sys_corpo_diretivo")
	private boolean sysCorpoDiretivo;
	@Column(name = "corpo_diretivo_add")
	private boolean corpoDiretivoAdd;
	@Column(name = "corpo_diretivo_change")
	private boolean corpoDiretivoChange;
	@Column(name = "corpo_Diretivo_consult")
	private boolean corpoDiretivoConsult;
	@Column(name = "corpo_diretivo_remove")
	private boolean corpoDiretivoRemove;

	/** SISTEMA CONTABILIZADOR **/
	@Column(name = "sys_contabilizador")
	private boolean sysContabilizador;
	@Column(name = "contabilizador_add")
	private boolean contabilizadorAdd;
	@Column(name = "contabilizador_change")
	private boolean contabilizadorChange;
	@Column(name = "contabilizador_consult")
	private boolean contabilizadorConsult;
	@Column(name = "contabilizador_remove")
	private boolean contabilizadorRemove;

	/** SISTEMA ENTRADA E SAIDA **/
	@Column(name = "sys_es")
	private boolean sysES;
	@Column(name = "es_add")
	private boolean esAdd;
	@Column(name = "es_change")
	private boolean esChange;
	@Column(name = "es_consult")
	private boolean esConsult;
	@Column(name = "es_remove")
	private boolean esRemove;

	/** SISTEMA EMISSÃO **/
	@Column(name = "sys_sem")
	private boolean sysSEM;
	@Column(name = "sem_add")
	private boolean semAdd;
	@Column(name = "sem_change")
	private boolean semChange;
	@Column(name = "sem_consult")
	private boolean semConsult;
	@Column(name = "sem_remove")
	private boolean semRemove;

	/** SISTEMA EMISSOR **/
	@Column(name = "sys_emissor")
	private boolean sysEmissor;
	@Column(name = "emisor_add")
	private boolean emissorAdd;
	@Column(name = "emissor_change")
	private boolean emissorChange;
	@Column(name = "emissor_consult")
	private boolean emissorConsult;
	@Column(name = "emissor_remove")
	private boolean emissorRemove;

	/** SISTEMA GLOBAL **/
	@Column(name = "sys_global")
	private boolean sysGlobal;
	@Column(name = "global_add")
	private boolean globalAdd;
	@Column(name = "global_change")
	private boolean globalChange;
	@Column(name = "global_consult")
	private boolean globalConsult;
	@Column(name = "global_remove")
	private boolean globalRemove;

	/** SISTEMA RELATORIO SEMANAL **/
	@Column(name = "sys_relatorio_semanal")
	private boolean sysRelatorioSemanal;
	@Column(name = "relatorio_semanal_add")
	private boolean RelatorioSemanalAdd;
	@Column(name = "relatorio_semanal_consult")
	private boolean RelatorioSemanalConsult;

	/** SISTEMA TAXA INADIMPLENCIA **/
	@Column(name = "sys_taxa_inadimplencia")
	private boolean sysTaxaInadimplencia;

	/** SISTEMA LISTA PRESENCA **/
	@Column(name = "sys_listaPresenca")
	private boolean syslistaPresenca;
	@Column(name = "listaPresenca_add")
	private boolean listaPresencaAdd;
	@Column(name = "listaPresenca_change")
	private boolean listaPresencaChange;
	@Column(name = "listaPresenca_consult")
	private boolean listaPresencaConsult;
	@Column(name = "listaPresenca_remove")
	private boolean listaPresencaRemove;

	/** SISTEMA REQUISICAO DE MALOTE **/
	@Column(name = "sys_requisicaoProtocolo")
	private boolean sysrequisicaoProtocolo;
	@Column(name = "requisicaoProtocolo_add")
	private boolean requisicaoProtocoloAdd;
	@Column(name = "requisicaoProtocolo_change")
	private boolean requisicaoProtocoloChange;
	@Column(name = "requisicaoProtocolo_consult")
	private boolean requisicaoProtocoloConsult;
	@Column(name = "requisicaoProtocolo_remove")
	private boolean requisicaoProtocoloRemove;

	/** SISTEMA PREVISAO ORCAMENTARIA **/
	@Column(name = "sys_previsao")
	private boolean sysPrevisao;
	@Column(name = "previsao_add")
	private boolean previsaoAdd;
	@Column(name = "previsao_change")
	private boolean previsaoChange;
	@Column(name = "previsao_consult")
	private boolean previsaoConsult;
	@Column(name = "previsao_remove")
	private boolean previsaoRemove;

	/** SISTEMA QUIZ **/
	@Column(name = "sys_quiz")
	private boolean sysQuiz;

	// MENU VISTORIA
	/** SISTEMA TI **/
	@Column(name = "sys_ti")
	private boolean sysTI;
	@Column(name = "ti_add")
	private boolean tiAdd;
	@Column(name = "ti_change")
	private boolean tiChange;
	@Column(name = "ti_consult")
	private boolean tiConsult;
	@Column(name = "ti_remove")
	private boolean tiRemove;

	// MENU JURIDICO
	@Column(name = "sys_juridico")
	private boolean sysJuridico;
	@Column(name = "juridico_add")
	private boolean juridicoAdd;
	@Column(name = "juridico_change")
	private boolean juridicoChange;
	@Column(name = "juridico_consult")
	private boolean juridicoConsult;
	@Column(name = "juridico_remove")
	private boolean juridicoRemove;

	// MENU LOCACAO

	// MENU MARKETING
	@Column(name = "sys_querym")
	private boolean sysQuerym;
	@Column(name = "querym_add")
	private boolean querymAdd;
	@Column(name = "querym_change")
	private boolean querymChange;
	@Column(name = "querym_Consult")
	private boolean querymConsult;
	@Column(name = "querym_remove")
	private boolean querymRemove;

	// MENU SIP
	/** SISTEMA SIP **/
	@Column(name = "sys_sip")
	private boolean sysSip;

	// GED
	@Column(name = "sys_sip_ged")
	private boolean sysSipGed;
	@Column(name = "sys_sip_ged_add")
	private boolean sysSipGEDAdd;
	@Column(name = "sys_sip_ged_change")
	private boolean sysSipGEDChange;
	@Column(name = "sys_sip_ged_consult")
	private boolean sysSipGEDConsult;
	@Column(name = "sys_sip_ged_remove")
	private boolean sysSipGEDRemove;
	// FISCAL
	@Column(name = "sys_sip_fiscal")
	private boolean sysSipFiscal;
	@Column(name = "sys_sip_fiscal_add")
	private boolean sysSipFiscalAdd;
	@Column(name = "sys_sip_fiscal_change")
	private boolean sysSipFiscalChange;
	@Column(name = "sys_sip_fiscal_consult")
	private boolean sysSipFiscalConsult;
	@Column(name = "sys_sip_fiscal_remove")
	private boolean sysSipFiscalRemove;

	// GERENCIA
	@Column(name = "sys_sip_gerencia")
	private boolean sysSipGerencia;
	@Column(name = "sys_sip_gerencia_add")
	private boolean sysSipGerenciaAdd;
	@Column(name = "sys_sip_gerencia_change")
	private boolean sysSipGerenciaChange;
	@Column(name = "sys_sip_gerencia_consult")
	private boolean sysSipGerenciaConsult;
	@Column(name = "sys_sip_gerencia_remove")
	private boolean sysSipGerenciaRemove;
	// PRE LANCAMENTO
	@Column(name = "sys_sip_pre_lancamento")
	private boolean sysSipPreLancamento;
	@Column(name = "sys_sip_pre_lancamento_add")
	private boolean sysSipPreLancamentoAdd;
	@Column(name = "sys_sip_pre_lancamento_change")
	private boolean sysSipPreLancamentoChange;
	@Column(name = "sys_sip_pre_lancamento_consult")
	private boolean sysSipPreLancamentoConsult;
	@Column(name = "sys_sip_pre_lancamento_remove")
	private boolean sysSipPreLancamentoRemove;
	// LANCAMENTO
	@Column(name = "sys_sip_lancamento")
	private boolean sysSipLancamento;
	@Column(name = "sys_sip_lancamento_add")
	private boolean sysSipLancamentoAdd;
	@Column(name = "sys_sip_lancamento_change")
	private boolean sysSipLancamentoChange;
	@Column(name = "sys_sip_lancamento_consult")
	private boolean sysSipLancamentoConsult;
	@Column(name = "sys_sip_lancamento_remove")
	private boolean sysSipLancamentoRemove;

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public boolean isSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(boolean sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	public boolean isSysCobranca() {
		return sysCobranca;
	}

	public void setSysCobranca(boolean sysCobranca) {
		this.sysCobranca = sysCobranca;
	}

	public boolean isCobrancaAdd() {
		return cobrancaAdd;
	}

	public void setCobrancaAdd(boolean cobrancaAdd) {
		this.cobrancaAdd = cobrancaAdd;
	}

	public boolean isCobrancaChange() {
		return cobrancaChange;
	}

	public void setCobrancaChange(boolean cobrancaChange) {
		this.cobrancaChange = cobrancaChange;
	}

	public boolean isCobrancaConsult() {
		return cobrancaConsult;
	}

	public void setCobrancaConsult(boolean cobrancaConsult) {
		this.cobrancaConsult = cobrancaConsult;
	}

	public boolean isCobrancaRemove() {
		return cobrancaRemove;
	}

	public void setCobrancaRemove(boolean cobrancaRemove) {
		this.cobrancaRemove = cobrancaRemove;
	}

	public boolean isSysCondominio() {
		return sysCondominio;
	}

	public void setSysCondominio(boolean sysCondominio) {
		this.sysCondominio = sysCondominio;
	}

	public boolean isCondominioAdd() {
		return condominioAdd;
	}

	public void setCondominioAdd(boolean condominioAdd) {
		this.condominioAdd = condominioAdd;
	}

	public boolean isCondominioChange() {
		return condominioChange;
	}

	public void setCondominioChange(boolean condominioChange) {
		this.condominioChange = condominioChange;
	}

	public boolean isCondominioConsult() {
		return condominioConsult;
	}

	public void setCondominioConsult(boolean condominioConsult) {
		this.condominioConsult = condominioConsult;
	}

	public boolean isCondominioRemove() {
		return condominioRemove;
	}

	public void setCondominioRemove(boolean condominioRemove) {
		this.condominioRemove = condominioRemove;
	}

	public boolean isSysContabilidade() {
		return sysContabilidade;
	}

	public void setSysContabilidade(boolean sysContabilidade) {
		this.sysContabilidade = sysContabilidade;
	}

	public boolean isImagemRecebidaAdd() {
		return imagemRecebidaAdd;
	}

	public void setImagemRecebidaAdd(boolean imagemRecebidaAdd) {
		this.imagemRecebidaAdd = imagemRecebidaAdd;
	}

	public boolean isImagemRecebidaConsult() {
		return imagemRecebidaConsult;
	}

	public void setImagemRecebidaConsult(boolean imagemRecebidaConsult) {
		this.imagemRecebidaConsult = imagemRecebidaConsult;
	}

	public boolean isSysContas() {
		return sysContas;
	}

	public void setSysContas(boolean sysContas) {
		this.sysContas = sysContas;
	}

	public boolean isSysDiretoria() {
		return sysDiretoria;
	}

	public void setSysDiretoria(boolean sysDiretoria) {
		this.sysDiretoria = sysDiretoria;
	}

	public boolean isDiretoriaAdd() {
		return diretoriaAdd;
	}

	public void setDiretoriaAdd(boolean diretoriaAdd) {
		this.diretoriaAdd = diretoriaAdd;
	}

	public boolean isDiretoriaChange() {
		return diretoriaChange;
	}

	public void setDiretoriaChange(boolean diretoriaChange) {
		this.diretoriaChange = diretoriaChange;
	}

	public boolean isDiretoriaConsult() {
		return diretoriaConsult;
	}

	public void setDiretoriaConsult(boolean diretoriaConsult) {
		this.diretoriaConsult = diretoriaConsult;
	}

	public boolean isDiretoriaRemove() {
		return diretoriaRemove;
	}

	public void setDiretoriaRemove(boolean diretoriaRemove) {
		this.diretoriaRemove = diretoriaRemove;
	}

	public boolean isSysPapercut() {
		return sysPapercut;
	}

	public void setSysPapercut(boolean sysPapercut) {
		this.sysPapercut = sysPapercut;
	}

	public boolean isPapercutAdd() {
		return papercutAdd;
	}

	public void setPapercutAdd(boolean papercutAdd) {
		this.papercutAdd = papercutAdd;
	}

	public boolean isPapercutChange() {
		return papercutChange;
	}

	public void setPapercutChange(boolean papercutChange) {
		this.papercutChange = papercutChange;
	}

	public boolean isPapercutConsult() {
		return papercutConsult;
	}

	public void setPapercutConsult(boolean papercutConsult) {
		this.papercutConsult = papercutConsult;
	}

	public boolean isPapercutRemove() {
		return papercutRemove;
	}

	public void setPapercutRemove(boolean papercutRemove) {
		this.papercutRemove = papercutRemove;
	}

	public boolean isSysServicos() {
		return sysServicos;
	}

	public void setSysServicos(boolean sysServicos) {
		this.sysServicos = sysServicos;
	}

	public boolean isServicosAdd() {
		return servicosAdd;
	}

	public void setServicosAdd(boolean servicosAdd) {
		this.servicosAdd = servicosAdd;
	}

	public boolean isServicosChange() {
		return servicosChange;
	}

	public void setServicosChange(boolean servicosChange) {
		this.servicosChange = servicosChange;
	}

	public boolean isServicosConsult() {
		return servicosConsult;
	}

	public void setServicosConsult(boolean servicosConsult) {
		this.servicosConsult = servicosConsult;
	}

	public boolean isServicosRemove() {
		return servicosRemove;
	}

	public void setServicosRemove(boolean servicosRemove) {
		this.servicosRemove = servicosRemove;
	}

	public boolean isSysDP() {
		return sysDP;
	}

	public void setSysDP(boolean sysDP) {
		this.sysDP = sysDP;
	}

	public boolean isDpAdd() {
		return dpAdd;
	}

	public void setDpAdd(boolean dpAdd) {
		this.dpAdd = dpAdd;
	}

	public boolean isDpChange() {
		return dpChange;
	}

	public void setDpChange(boolean dpChange) {
		this.dpChange = dpChange;
	}

	public boolean isDpConsult() {
		return dpConsult;
	}

	public void setDpConsult(boolean dpConsult) {
		this.dpConsult = dpConsult;
	}

	public boolean isDpRemove() {
		return dpRemove;
	}

	public void setDpRemove(boolean dpRemove) {
		this.dpRemove = dpRemove;
	}

	public boolean isSysEmissao() {
		return sysEmissao;
	}

	public void setSysEmissao(boolean sysEmissao) {
		this.sysEmissao = sysEmissao;
	}

	public boolean isEmissaoAdd() {
		return emissaoAdd;
	}

	public void setEmissaoAdd(boolean emissaoAdd) {
		this.emissaoAdd = emissaoAdd;
	}

	public boolean isEmissaoChange() {
		return emissaoChange;
	}

	public void setEmissaoChange(boolean emissaoChange) {
		this.emissaoChange = emissaoChange;
	}

	public boolean isEmissaoConsult() {
		return emissaoConsult;
	}

	public void setEmissaoConsult(boolean emissaoConsult) {
		this.emissaoConsult = emissaoConsult;
	}

	public boolean isEmissaoRemove() {
		return emissaoRemove;
	}

	public void setEmissaoRemove(boolean emissaoRemove) {
		this.emissaoRemove = emissaoRemove;
	}

	public boolean isSysFinanceiro() {
		return sysFinanceiro;
	}

	public void setSysFinanceiro(boolean sysFinanceiro) {
		this.sysFinanceiro = sysFinanceiro;
	}

	public boolean isLancamento() {
		return lancamento;
	}

	public void setLancamento(boolean lancamento) {
		this.lancamento = lancamento;
	}

	public boolean isConsultarLancamento() {
		return consultarLancamento;
	}

	public void setConsultarLancamento(boolean consultarLancamento) {
		this.consultarLancamento = consultarLancamento;
	}

	public boolean isHistoricoLancamento() {
		return historicoLancamento;
	}

	public void setHistoricoLancamento(boolean historicoLancamento) {
		this.historicoLancamento = historicoLancamento;
	}

	public boolean isAlterarLancto() {
		return alterarLancto;
	}

	public void setAlterarLancto(boolean alterarLancto) {
		this.alterarLancto = alterarLancto;
	}

	public boolean isPreAprovarLancto() {
		return preAprovarLancto;
	}

	public void setPreAprovarLancto(boolean preAprovarLancto) {
		this.preAprovarLancto = preAprovarLancto;
	}

	public boolean isAprovarLancto() {
		return aprovarLancto;
	}

	public void setAprovarLancto(boolean aprovarLancto) {
		this.aprovarLancto = aprovarLancto;
	}

	public boolean isExcluirLancto() {
		return excluirLancto;
	}

	public void setExcluirLancto(boolean excluirLancto) {
		this.excluirLancto = excluirLancto;
	}

	public boolean isReprovarLancto() {
		return reprovarLancto;
	}

	public void setReprovarLancto(boolean reprovarLancto) {
		this.reprovarLancto = reprovarLancto;
	}

	public boolean isProtocolos() {
		return protocolos;
	}

	public void setProtocolos(boolean protocolos) {
		this.protocolos = protocolos;
	}

	public boolean isAdicionarParcela() {
		return adicionarParcela;
	}

	public void setAdicionarParcela(boolean adicionarParcela) {
		this.adicionarParcela = adicionarParcela;
	}

	public boolean isExcluirEtiquetaSiga() {
		return excluirEtiquetaSiga;
	}

	public void setExcluirEtiquetaSiga(boolean excluirEtiquetaSiga) {
		this.excluirEtiquetaSiga = excluirEtiquetaSiga;
	}

	public boolean isSysAdvertMult() {
		return sysAdvertMult;
	}

	public void setSysAdvertMult(boolean sysAdvertMult) {
		this.sysAdvertMult = sysAdvertMult;
	}

	public boolean isAdvertMultAdd() {
		return advertMultAdd;
	}

	public void setAdvertMultAdd(boolean advertMultAdd) {
		this.advertMultAdd = advertMultAdd;
	}

	public boolean isAdvertMultChange() {
		return advertMultChange;
	}

	public void setAdvertMultChange(boolean advertMultChange) {
		this.advertMultChange = advertMultChange;
	}

	public boolean isAdvertMultConsult() {
		return advertMultConsult;
	}

	public void setAdvertMultConsult(boolean advertMultConsult) {
		this.advertMultConsult = advertMultConsult;
	}

	public boolean isAdvertMultRemove() {
		return advertMultRemove;
	}

	public void setAdvertMultRemove(boolean advertMultRemove) {
		this.advertMultRemove = advertMultRemove;
	}

	public boolean isSysAssembleia() {
		return sysAssembleia;
	}

	public void setSysAssembleia(boolean sysAssembleia) {
		this.sysAssembleia = sysAssembleia;
	}

	public boolean isAssembleiaAdd() {
		return assembleiaAdd;
	}

	public void setAssembleiaAdd(boolean assembleiaAdd) {
		this.assembleiaAdd = assembleiaAdd;
	}

	public boolean isAssembleiaChange() {
		return assembleiaChange;
	}

	public void setAssembleiaChange(boolean assembleiaChange) {
		this.assembleiaChange = assembleiaChange;
	}

	public boolean isAssembleiaConsult() {
		return assembleiaConsult;
	}

	public void setAssembleiaConsult(boolean assembleiaConsult) {
		this.assembleiaConsult = assembleiaConsult;
	}

	public boolean isAssembleiaRemove() {
		return assembleiaRemove;
	}

	public void setAssembleiaRemove(boolean assembleiaRemove) {
		this.assembleiaRemove = assembleiaRemove;
	}

	public boolean isSysControleContas() {
		return sysControleContas;
	}

	public void setSysControleContas(boolean sysControleContas) {
		this.sysControleContas = sysControleContas;
	}

	public boolean isControleContasAdd() {
		return controleContasAdd;
	}

	public void setControleContasAdd(boolean controleContasAdd) {
		this.controleContasAdd = controleContasAdd;
	}

	public boolean isControleContasChange() {
		return controleContasChange;
	}

	public void setControleContasChange(boolean controleContasChange) {
		this.controleContasChange = controleContasChange;
	}

	public boolean isControleContasConsult() {
		return controleContasConsult;
	}

	public void setControleContasConsult(boolean controleContasConsult) {
		this.controleContasConsult = controleContasConsult;
	}

	public boolean isControleContasRemove() {
		return controleContasRemove;
	}

	public void setControleContasRemove(boolean controleContasRemove) {
		this.controleContasRemove = controleContasRemove;
	}

	public boolean isSysCorpoDiretivo() {
		return sysCorpoDiretivo;
	}

	public void setSysCorpoDiretivo(boolean sysCorpoDiretivo) {
		this.sysCorpoDiretivo = sysCorpoDiretivo;
	}

	public boolean isCorpoDiretivoAdd() {
		return corpoDiretivoAdd;
	}

	public void setCorpoDiretivoAdd(boolean corpoDiretivoAdd) {
		this.corpoDiretivoAdd = corpoDiretivoAdd;
	}

	public boolean isCorpoDiretivoChange() {
		return corpoDiretivoChange;
	}

	public void setCorpoDiretivoChange(boolean corpoDiretivoChange) {
		this.corpoDiretivoChange = corpoDiretivoChange;
	}

	public boolean isCorpoDiretivoConsult() {
		return corpoDiretivoConsult;
	}

	public void setCorpoDiretivoConsult(boolean corpoDiretivoConsult) {
		this.corpoDiretivoConsult = corpoDiretivoConsult;
	}

	public boolean isCorpoDiretivoRemove() {
		return corpoDiretivoRemove;
	}

	public void setCorpoDiretivoRemove(boolean corpoDiretivoRemove) {
		this.corpoDiretivoRemove = corpoDiretivoRemove;
	}

	public boolean isSysContabilizador() {
		return sysContabilizador;
	}

	public void setSysContabilizador(boolean sysContabilizador) {
		this.sysContabilizador = sysContabilizador;
	}

	public boolean isContabilizadorAdd() {
		return contabilizadorAdd;
	}

	public void setContabilizadorAdd(boolean contabilizadorAdd) {
		this.contabilizadorAdd = contabilizadorAdd;
	}

	public boolean isContabilizadorChange() {
		return contabilizadorChange;
	}

	public void setContabilizadorChange(boolean contabilizadorChange) {
		this.contabilizadorChange = contabilizadorChange;
	}

	public boolean isContabilizadorConsult() {
		return contabilizadorConsult;
	}

	public void setContabilizadorConsult(boolean contabilizadorConsult) {
		this.contabilizadorConsult = contabilizadorConsult;
	}

	public boolean isContabilizadorRemove() {
		return contabilizadorRemove;
	}

	public void setContabilizadorRemove(boolean contabilizadorRemove) {
		this.contabilizadorRemove = contabilizadorRemove;
	}

	public boolean isSysES() {
		return sysES;
	}

	public void setSysES(boolean sysES) {
		this.sysES = sysES;
	}

	public boolean isEsAdd() {
		return esAdd;
	}

	public void setEsAdd(boolean esAdd) {
		this.esAdd = esAdd;
	}

	public boolean isEsChange() {
		return esChange;
	}

	public void setEsChange(boolean esChange) {
		this.esChange = esChange;
	}

	public boolean isEsConsult() {
		return esConsult;
	}

	public void setEsConsult(boolean esConsult) {
		this.esConsult = esConsult;
	}

	public boolean isEsRemove() {
		return esRemove;
	}

	public void setEsRemove(boolean esRemove) {
		this.esRemove = esRemove;
	}

	public boolean isSysSEM() {
		return sysSEM;
	}

	public void setSysSEM(boolean sysSEM) {
		this.sysSEM = sysSEM;
	}

	public boolean isSemAdd() {
		return semAdd;
	}

	public void setSemAdd(boolean semAdd) {
		this.semAdd = semAdd;
	}

	public boolean isSemChange() {
		return semChange;
	}

	public void setSemChange(boolean semChange) {
		this.semChange = semChange;
	}

	public boolean isSemConsult() {
		return semConsult;
	}

	public void setSemConsult(boolean semConsult) {
		this.semConsult = semConsult;
	}

	public boolean isSemRemove() {
		return semRemove;
	}

	public void setSemRemove(boolean semRemove) {
		this.semRemove = semRemove;
	}

	public boolean isSysEmissor() {
		return sysEmissor;
	}

	public void setSysEmissor(boolean sysEmissor) {
		this.sysEmissor = sysEmissor;
	}

	public boolean isEmissorAdd() {
		return emissorAdd;
	}

	public void setEmissorAdd(boolean emissorAdd) {
		this.emissorAdd = emissorAdd;
	}

	public boolean isEmissorChange() {
		return emissorChange;
	}

	public void setEmissorChange(boolean emissorChange) {
		this.emissorChange = emissorChange;
	}

	public boolean isEmissorConsult() {
		return emissorConsult;
	}

	public void setEmissorConsult(boolean emissorConsult) {
		this.emissorConsult = emissorConsult;
	}

	public boolean isEmissorRemove() {
		return emissorRemove;
	}

	public void setEmissorRemove(boolean emissorRemove) {
		this.emissorRemove = emissorRemove;
	}

	public boolean isSysGlobal() {
		return sysGlobal;
	}

	public void setSysGlobal(boolean sysGlobal) {
		this.sysGlobal = sysGlobal;
	}

	public boolean isGlobalAdd() {
		return globalAdd;
	}

	public void setGlobalAdd(boolean globalAdd) {
		this.globalAdd = globalAdd;
	}

	public boolean isGlobalChange() {
		return globalChange;
	}

	public void setGlobalChange(boolean globalChange) {
		this.globalChange = globalChange;
	}

	public boolean isGlobalConsult() {
		return globalConsult;
	}

	public void setGlobalConsult(boolean globalConsult) {
		this.globalConsult = globalConsult;
	}

	public boolean isGlobalRemove() {
		return globalRemove;
	}

	public void setGlobalRemove(boolean globalRemove) {
		this.globalRemove = globalRemove;
	}

	public boolean isSysRelatorioSemanal() {
		return sysRelatorioSemanal;
	}

	public void setSysRelatorioSemanal(boolean sysRelatorioSemanal) {
		this.sysRelatorioSemanal = sysRelatorioSemanal;
	}

	public boolean isRelatorioSemanalAdd() {
		return RelatorioSemanalAdd;
	}

	public void setRelatorioSemanalAdd(boolean relatorioSemanalAdd) {
		RelatorioSemanalAdd = relatorioSemanalAdd;
	}

	public boolean isRelatorioSemanalConsult() {
		return RelatorioSemanalConsult;
	}

	public void setRelatorioSemanalConsult(boolean relatorioSemanalConsult) {
		RelatorioSemanalConsult = relatorioSemanalConsult;
	}

	public boolean isSysTaxaInadimplencia() {
		return sysTaxaInadimplencia;
	}

	public void setSysTaxaInadimplencia(boolean sysTaxaInadimplencia) {
		this.sysTaxaInadimplencia = sysTaxaInadimplencia;
	}

	public boolean isSyslistaPresenca() {
		return syslistaPresenca;
	}

	public void setSyslistaPresenca(boolean syslistaPresenca) {
		this.syslistaPresenca = syslistaPresenca;
	}

	public boolean isListaPresencaAdd() {
		return listaPresencaAdd;
	}

	public void setListaPresencaAdd(boolean listaPresencaAdd) {
		this.listaPresencaAdd = listaPresencaAdd;
	}

	public boolean isListaPresencaChange() {
		return listaPresencaChange;
	}

	public void setListaPresencaChange(boolean listaPresencaChange) {
		this.listaPresencaChange = listaPresencaChange;
	}

	public boolean isListaPresencaConsult() {
		return listaPresencaConsult;
	}

	public void setListaPresencaConsult(boolean listaPresencaConsult) {
		this.listaPresencaConsult = listaPresencaConsult;
	}

	public boolean isListaPresencaRemove() {
		return listaPresencaRemove;
	}

	public void setListaPresencaRemove(boolean listaPresencaRemove) {
		this.listaPresencaRemove = listaPresencaRemove;
	}

	public boolean isSysrequisicaoProtocolo() {
		return sysrequisicaoProtocolo;
	}

	public void setSysrequisicaoProtocolo(boolean sysrequisicaoProtocolo) {
		this.sysrequisicaoProtocolo = sysrequisicaoProtocolo;
	}

	public boolean isRequisicaoProtocoloAdd() {
		return requisicaoProtocoloAdd;
	}

	public void setRequisicaoProtocoloAdd(boolean requisicaoProtocoloAdd) {
		this.requisicaoProtocoloAdd = requisicaoProtocoloAdd;
	}

	public boolean isRequisicaoProtocoloChange() {
		return requisicaoProtocoloChange;
	}

	public void setRequisicaoProtocoloChange(boolean requisicaoProtocoloChange) {
		this.requisicaoProtocoloChange = requisicaoProtocoloChange;
	}

	public boolean isRequisicaoProtocoloConsult() {
		return requisicaoProtocoloConsult;
	}

	public void setRequisicaoProtocoloConsult(boolean requisicaoProtocoloConsult) {
		this.requisicaoProtocoloConsult = requisicaoProtocoloConsult;
	}

	public boolean isRequisicaoProtocoloRemove() {
		return requisicaoProtocoloRemove;
	}

	public void setRequisicaoProtocoloRemove(boolean requisicaoProtocoloRemove) {
		this.requisicaoProtocoloRemove = requisicaoProtocoloRemove;
	}

	public boolean isSysPrevisao() {
		return sysPrevisao;
	}

	public void setSysPrevisao(boolean sysPrevisao) {
		this.sysPrevisao = sysPrevisao;
	}

	public boolean isPrevisaoAdd() {
		return previsaoAdd;
	}

	public void setPrevisaoAdd(boolean previsaoAdd) {
		this.previsaoAdd = previsaoAdd;
	}

	public boolean isPrevisaoChange() {
		return previsaoChange;
	}

	public void setPrevisaoChange(boolean previsaoChange) {
		this.previsaoChange = previsaoChange;
	}

	public boolean isPrevisaoConsult() {
		return previsaoConsult;
	}

	public void setPrevisaoConsult(boolean previsaoConsult) {
		this.previsaoConsult = previsaoConsult;
	}

	public boolean isPrevisaoRemove() {
		return previsaoRemove;
	}

	public void setPrevisaoRemove(boolean previsaoRemove) {
		this.previsaoRemove = previsaoRemove;
	}

	public boolean isSysQuiz() {
		return sysQuiz;
	}

	public void setSysQuiz(boolean sysQuiz) {
		this.sysQuiz = sysQuiz;
	}

	public boolean isSysTI() {
		return sysTI;
	}

	public void setSysTI(boolean sysTI) {
		this.sysTI = sysTI;
	}

	public boolean isTiAdd() {
		return tiAdd;
	}

	public void setTiAdd(boolean tiAdd) {
		this.tiAdd = tiAdd;
	}

	public boolean isTiChange() {
		return tiChange;
	}

	public void setTiChange(boolean tiChange) {
		this.tiChange = tiChange;
	}

	public boolean isTiConsult() {
		return tiConsult;
	}

	public void setTiConsult(boolean tiConsult) {
		this.tiConsult = tiConsult;
	}

	public boolean isTiRemove() {
		return tiRemove;
	}

	public void setTiRemove(boolean tiRemove) {
		this.tiRemove = tiRemove;
	}

	public boolean isSysJuridico() {
		return sysJuridico;
	}

	public void setSysJuridico(boolean sysJuridico) {
		this.sysJuridico = sysJuridico;
	}

	public boolean isJuridicoAdd() {
		return juridicoAdd;
	}

	public void setJuridicoAdd(boolean juridicoAdd) {
		this.juridicoAdd = juridicoAdd;
	}

	public boolean isJuridicoChange() {
		return juridicoChange;
	}

	public void setJuridicoChange(boolean juridicoChange) {
		this.juridicoChange = juridicoChange;
	}

	public boolean isJuridicoConsult() {
		return juridicoConsult;
	}

	public void setJuridicoConsult(boolean juridicoConsult) {
		this.juridicoConsult = juridicoConsult;
	}

	public boolean isJuridicoRemove() {
		return juridicoRemove;
	}

	public void setJuridicoRemove(boolean juridicoRemove) {
		this.juridicoRemove = juridicoRemove;
	}

	public boolean isSysQuerym() {
		return sysQuerym;
	}

	public void setSysQuerym(boolean sysQuerym) {
		this.sysQuerym = sysQuerym;
	}

	public boolean isQuerymAdd() {
		return querymAdd;
	}

	public void setQuerymAdd(boolean querymAdd) {
		this.querymAdd = querymAdd;
	}

	public boolean isQuerymChange() {
		return querymChange;
	}

	public void setQuerymChange(boolean querymChange) {
		this.querymChange = querymChange;
	}

	public boolean isQuerymConsult() {
		return querymConsult;
	}

	public void setQuerymConsult(boolean querymConsult) {
		this.querymConsult = querymConsult;
	}

	public boolean isQuerymRemove() {
		return querymRemove;
	}

	public void setQuerymRemove(boolean querymRemove) {
		this.querymRemove = querymRemove;
	}

	public boolean isSysSip() {
		return sysSip;
	}

	public void setSysSip(boolean sysSip) {
		this.sysSip = sysSip;
	}

	public boolean isSysSipGed() {
		return sysSipGed;
	}

	public void setSysSipGed(boolean sysSipGed) {
		this.sysSipGed = sysSipGed;
	}

	public boolean isSysSipGEDAdd() {
		return sysSipGEDAdd;
	}

	public void setSysSipGEDAdd(boolean sysSipGEDAdd) {
		this.sysSipGEDAdd = sysSipGEDAdd;
	}

	public boolean isSysSipGEDChange() {
		return sysSipGEDChange;
	}

	public void setSysSipGEDChange(boolean sysSipGEDChange) {
		this.sysSipGEDChange = sysSipGEDChange;
	}

	public boolean isSysSipGEDConsult() {
		return sysSipGEDConsult;
	}

	public void setSysSipGEDConsult(boolean sysSipGEDConsult) {
		this.sysSipGEDConsult = sysSipGEDConsult;
	}

	public boolean isSysSipGEDRemove() {
		return sysSipGEDRemove;
	}

	public void setSysSipGEDRemove(boolean sysSipGEDRemove) {
		this.sysSipGEDRemove = sysSipGEDRemove;
	}

	public boolean isSysSipFiscal() {
		return sysSipFiscal;
	}

	public void setSysSipFiscal(boolean sysSipFiscal) {
		this.sysSipFiscal = sysSipFiscal;
	}

	public boolean isSysSipFiscalAdd() {
		return sysSipFiscalAdd;
	}

	public void setSysSipFiscalAdd(boolean sysSipFiscalAdd) {
		this.sysSipFiscalAdd = sysSipFiscalAdd;
	}

	public boolean isSysSipFiscalChange() {
		return sysSipFiscalChange;
	}

	public void setSysSipFiscalChange(boolean sysSipFiscalChange) {
		this.sysSipFiscalChange = sysSipFiscalChange;
	}

	public boolean isSysSipFiscalConsult() {
		return sysSipFiscalConsult;
	}

	public void setSysSipFiscalConsult(boolean sysSipFiscalConsult) {
		this.sysSipFiscalConsult = sysSipFiscalConsult;
	}

	public boolean isSysSipFiscalRemove() {
		return sysSipFiscalRemove;
	}

	public void setSysSipFiscalRemove(boolean sysSipFiscalRemove) {
		this.sysSipFiscalRemove = sysSipFiscalRemove;
	}

	public boolean isSysSipGerencia() {
		return sysSipGerencia;
	}

	public void setSysSipGerencia(boolean sysSipGerencia) {
		this.sysSipGerencia = sysSipGerencia;
	}

	public boolean isSysSipGerenciaAdd() {
		return sysSipGerenciaAdd;
	}

	public void setSysSipGerenciaAdd(boolean sysSipGerenciaAdd) {
		this.sysSipGerenciaAdd = sysSipGerenciaAdd;
	}

	public boolean isSysSipGerenciaChange() {
		return sysSipGerenciaChange;
	}

	public void setSysSipGerenciaChange(boolean sysSipGerenciaChange) {
		this.sysSipGerenciaChange = sysSipGerenciaChange;
	}

	public boolean isSysSipGerenciaConsult() {
		return sysSipGerenciaConsult;
	}

	public void setSysSipGerenciaConsult(boolean sysSipGerenciaConsult) {
		this.sysSipGerenciaConsult = sysSipGerenciaConsult;
	}

	public boolean isSysSipGerenciaRemove() {
		return sysSipGerenciaRemove;
	}

	public void setSysSipGerenciaRemove(boolean sysSipGerenciaRemove) {
		this.sysSipGerenciaRemove = sysSipGerenciaRemove;
	}

	public boolean isSysSipPreLancamento() {
		return sysSipPreLancamento;
	}

	public void setSysSipPreLancamento(boolean sysSipPreLancamento) {
		this.sysSipPreLancamento = sysSipPreLancamento;
	}

	public boolean isSysSipPreLancamentoAdd() {
		return sysSipPreLancamentoAdd;
	}

	public void setSysSipPreLancamentoAdd(boolean sysSipPreLancamentoAdd) {
		this.sysSipPreLancamentoAdd = sysSipPreLancamentoAdd;
	}

	public boolean isSysSipPreLancamentoChange() {
		return sysSipPreLancamentoChange;
	}

	public void setSysSipPreLancamentoChange(boolean sysSipPreLancamentoChange) {
		this.sysSipPreLancamentoChange = sysSipPreLancamentoChange;
	}

	public boolean isSysSipPreLancamentoConsult() {
		return sysSipPreLancamentoConsult;
	}

	public void setSysSipPreLancamentoConsult(boolean sysSipPreLancamentoConsult) {
		this.sysSipPreLancamentoConsult = sysSipPreLancamentoConsult;
	}

	public boolean isSysSipPreLancamentoRemove() {
		return sysSipPreLancamentoRemove;
	}

	public void setSysSipPreLancamentoRemove(boolean sysSipPreLancamentoRemove) {
		this.sysSipPreLancamentoRemove = sysSipPreLancamentoRemove;
	}

	public boolean isSysSipLancamento() {
		return sysSipLancamento;
	}

	public void setSysSipLancamento(boolean sysSipLancamento) {
		this.sysSipLancamento = sysSipLancamento;
	}

	public boolean isSysSipLancamentoAdd() {
		return sysSipLancamentoAdd;
	}

	public void setSysSipLancamentoAdd(boolean sysSipLancamentoAdd) {
		this.sysSipLancamentoAdd = sysSipLancamentoAdd;
	}

	public boolean isSysSipLancamentoChange() {
		return sysSipLancamentoChange;
	}

	public void setSysSipLancamentoChange(boolean sysSipLancamentoChange) {
		this.sysSipLancamentoChange = sysSipLancamentoChange;
	}

	public boolean isSysSipLancamentoConsult() {
		return sysSipLancamentoConsult;
	}

	public void setSysSipLancamentoConsult(boolean sysSipLancamentoConsult) {
		this.sysSipLancamentoConsult = sysSipLancamentoConsult;
	}

	public boolean isSysSipLancamentoRemove() {
		return sysSipLancamentoRemove;
	}

	public void setSysSipLancamentoRemove(boolean sysSipLancamentoRemove) {
		this.sysSipLancamentoRemove = sysSipLancamentoRemove;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (RelatorioSemanalAdd ? 1231 : 1237);
		result = prime * result + (RelatorioSemanalConsult ? 1231 : 1237);
		result = prime * result + (adicionarParcela ? 1231 : 1237);
		result = prime * result + (advertMultAdd ? 1231 : 1237);
		result = prime * result + (advertMultChange ? 1231 : 1237);
		result = prime * result + (advertMultConsult ? 1231 : 1237);
		result = prime * result + (advertMultRemove ? 1231 : 1237);
		result = prime * result + (alterarLancto ? 1231 : 1237);
		result = prime * result + (aprovarLancto ? 1231 : 1237);
		result = prime * result + (assembleiaAdd ? 1231 : 1237);
		result = prime * result + (assembleiaChange ? 1231 : 1237);
		result = prime * result + (assembleiaConsult ? 1231 : 1237);
		result = prime * result + (assembleiaRemove ? 1231 : 1237);
		result = prime * result + (cobrancaAdd ? 1231 : 1237);
		result = prime * result + (cobrancaChange ? 1231 : 1237);
		result = prime * result + (cobrancaConsult ? 1231 : 1237);
		result = prime * result + (cobrancaRemove ? 1231 : 1237);
		result = prime * result + (condominioAdd ? 1231 : 1237);
		result = prime * result + (condominioChange ? 1231 : 1237);
		result = prime * result + (condominioConsult ? 1231 : 1237);
		result = prime * result + (condominioRemove ? 1231 : 1237);
		result = prime * result + (consultarLancamento ? 1231 : 1237);
		result = prime * result + (contabilizadorAdd ? 1231 : 1237);
		result = prime * result + (contabilizadorChange ? 1231 : 1237);
		result = prime * result + (contabilizadorConsult ? 1231 : 1237);
		result = prime * result + (contabilizadorRemove ? 1231 : 1237);
		result = prime * result + (controleContasAdd ? 1231 : 1237);
		result = prime * result + (controleContasChange ? 1231 : 1237);
		result = prime * result + (controleContasConsult ? 1231 : 1237);
		result = prime * result + (controleContasRemove ? 1231 : 1237);
		result = prime * result + (corpoDiretivoAdd ? 1231 : 1237);
		result = prime * result + (corpoDiretivoChange ? 1231 : 1237);
		result = prime * result + (corpoDiretivoConsult ? 1231 : 1237);
		result = prime * result + (corpoDiretivoRemove ? 1231 : 1237);
		result = prime * result + (diretoriaAdd ? 1231 : 1237);
		result = prime * result + (diretoriaChange ? 1231 : 1237);
		result = prime * result + (diretoriaConsult ? 1231 : 1237);
		result = prime * result + (diretoriaRemove ? 1231 : 1237);
		result = prime * result + (dpAdd ? 1231 : 1237);
		result = prime * result + (dpChange ? 1231 : 1237);
		result = prime * result + (dpConsult ? 1231 : 1237);
		result = prime * result + (dpRemove ? 1231 : 1237);
		result = prime * result + (emissaoAdd ? 1231 : 1237);
		result = prime * result + (emissaoChange ? 1231 : 1237);
		result = prime * result + (emissaoConsult ? 1231 : 1237);
		result = prime * result + (emissaoRemove ? 1231 : 1237);
		result = prime * result + (emissorAdd ? 1231 : 1237);
		result = prime * result + (emissorChange ? 1231 : 1237);
		result = prime * result + (emissorConsult ? 1231 : 1237);
		result = prime * result + (emissorRemove ? 1231 : 1237);
		result = prime * result + (esAdd ? 1231 : 1237);
		result = prime * result + (esChange ? 1231 : 1237);
		result = prime * result + (esConsult ? 1231 : 1237);
		result = prime * result + (esRemove ? 1231 : 1237);
		result = prime * result + (excluirEtiquetaSiga ? 1231 : 1237);
		result = prime * result + (excluirLancto ? 1231 : 1237);
		result = prime * result + (globalAdd ? 1231 : 1237);
		result = prime * result + (globalChange ? 1231 : 1237);
		result = prime * result + (globalConsult ? 1231 : 1237);
		result = prime * result + (globalRemove ? 1231 : 1237);
		result = prime * result + (historicoLancamento ? 1231 : 1237);
		result = prime * result + (imagemRecebidaAdd ? 1231 : 1237);
		result = prime * result + (imagemRecebidaConsult ? 1231 : 1237);
		result = prime * result + (juridicoAdd ? 1231 : 1237);
		result = prime * result + (juridicoChange ? 1231 : 1237);
		result = prime * result + (juridicoConsult ? 1231 : 1237);
		result = prime * result + (juridicoRemove ? 1231 : 1237);
		result = prime * result + (lancamento ? 1231 : 1237);
		result = prime * result + (listaPresencaAdd ? 1231 : 1237);
		result = prime * result + (listaPresencaChange ? 1231 : 1237);
		result = prime * result + (listaPresencaConsult ? 1231 : 1237);
		result = prime * result + (listaPresencaRemove ? 1231 : 1237);
		result = prime * result + ((nomeGrupo == null) ? 0 : nomeGrupo.hashCode());
		result = prime * result + (papercutAdd ? 1231 : 1237);
		result = prime * result + (papercutChange ? 1231 : 1237);
		result = prime * result + (papercutConsult ? 1231 : 1237);
		result = prime * result + (papercutRemove ? 1231 : 1237);
		result = prime * result + (preAprovarLancto ? 1231 : 1237);
		result = prime * result + (previsaoAdd ? 1231 : 1237);
		result = prime * result + (previsaoChange ? 1231 : 1237);
		result = prime * result + (previsaoConsult ? 1231 : 1237);
		result = prime * result + (previsaoRemove ? 1231 : 1237);
		result = prime * result + (protocolos ? 1231 : 1237);
		result = prime * result + (querymAdd ? 1231 : 1237);
		result = prime * result + (querymChange ? 1231 : 1237);
		result = prime * result + (querymConsult ? 1231 : 1237);
		result = prime * result + (querymRemove ? 1231 : 1237);
		result = prime * result + (reprovarLancto ? 1231 : 1237);
		result = prime * result + (requisicaoProtocoloAdd ? 1231 : 1237);
		result = prime * result + (requisicaoProtocoloChange ? 1231 : 1237);
		result = prime * result + (requisicaoProtocoloConsult ? 1231 : 1237);
		result = prime * result + (requisicaoProtocoloRemove ? 1231 : 1237);
		result = prime * result + (semAdd ? 1231 : 1237);
		result = prime * result + (semChange ? 1231 : 1237);
		result = prime * result + (semConsult ? 1231 : 1237);
		result = prime * result + (semRemove ? 1231 : 1237);
		result = prime * result + (servicosAdd ? 1231 : 1237);
		result = prime * result + (servicosChange ? 1231 : 1237);
		result = prime * result + (servicosConsult ? 1231 : 1237);
		result = prime * result + (servicosRemove ? 1231 : 1237);
		result = prime * result + (sysAdmin ? 1231 : 1237);
		result = prime * result + (sysAdvertMult ? 1231 : 1237);
		result = prime * result + (sysAssembleia ? 1231 : 1237);
		result = prime * result + (sysCobranca ? 1231 : 1237);
		result = prime * result + (sysCondominio ? 1231 : 1237);
		result = prime * result + (sysContabilidade ? 1231 : 1237);
		result = prime * result + (sysContabilizador ? 1231 : 1237);
		result = prime * result + (sysContas ? 1231 : 1237);
		result = prime * result + (sysControleContas ? 1231 : 1237);
		result = prime * result + (sysCorpoDiretivo ? 1231 : 1237);
		result = prime * result + (sysDP ? 1231 : 1237);
		result = prime * result + (sysDiretoria ? 1231 : 1237);
		result = prime * result + (sysES ? 1231 : 1237);
		result = prime * result + (sysEmissao ? 1231 : 1237);
		result = prime * result + (sysEmissor ? 1231 : 1237);
		result = prime * result + (sysFinanceiro ? 1231 : 1237);
		result = prime * result + (sysGlobal ? 1231 : 1237);
		result = prime * result + (sysJuridico ? 1231 : 1237);
		result = prime * result + (sysPapercut ? 1231 : 1237);
		result = prime * result + (sysPrevisao ? 1231 : 1237);
		result = prime * result + (sysQuerym ? 1231 : 1237);
		result = prime * result + (sysQuiz ? 1231 : 1237);
		result = prime * result + (sysRelatorioSemanal ? 1231 : 1237);
		result = prime * result + (sysSEM ? 1231 : 1237);
		result = prime * result + (sysServicos ? 1231 : 1237);
		result = prime * result + (sysSip ? 1231 : 1237);
		result = prime * result + (sysSipFiscal ? 1231 : 1237);
		result = prime * result + (sysSipFiscalAdd ? 1231 : 1237);
		result = prime * result + (sysSipFiscalChange ? 1231 : 1237);
		result = prime * result + (sysSipFiscalConsult ? 1231 : 1237);
		result = prime * result + (sysSipFiscalRemove ? 1231 : 1237);
		result = prime * result + (sysSipGEDAdd ? 1231 : 1237);
		result = prime * result + (sysSipGEDChange ? 1231 : 1237);
		result = prime * result + (sysSipGEDConsult ? 1231 : 1237);
		result = prime * result + (sysSipGEDRemove ? 1231 : 1237);
		result = prime * result + (sysSipGed ? 1231 : 1237);
		result = prime * result + (sysSipGerencia ? 1231 : 1237);
		result = prime * result + (sysSipGerenciaAdd ? 1231 : 1237);
		result = prime * result + (sysSipGerenciaChange ? 1231 : 1237);
		result = prime * result + (sysSipGerenciaConsult ? 1231 : 1237);
		result = prime * result + (sysSipGerenciaRemove ? 1231 : 1237);
		result = prime * result + (sysSipLancamento ? 1231 : 1237);
		result = prime * result + (sysSipLancamentoAdd ? 1231 : 1237);
		result = prime * result + (sysSipLancamentoChange ? 1231 : 1237);
		result = prime * result + (sysSipLancamentoConsult ? 1231 : 1237);
		result = prime * result + (sysSipLancamentoRemove ? 1231 : 1237);
		result = prime * result + (sysSipPreLancamento ? 1231 : 1237);
		result = prime * result + (sysSipPreLancamentoAdd ? 1231 : 1237);
		result = prime * result + (sysSipPreLancamentoChange ? 1231 : 1237);
		result = prime * result + (sysSipPreLancamentoConsult ? 1231 : 1237);
		result = prime * result + (sysSipPreLancamentoRemove ? 1231 : 1237);
		result = prime * result + (sysTI ? 1231 : 1237);
		result = prime * result + (sysTaxaInadimplencia ? 1231 : 1237);
		result = prime * result + (syslistaPresenca ? 1231 : 1237);
		result = prime * result + (sysrequisicaoProtocolo ? 1231 : 1237);
		result = prime * result + (tiAdd ? 1231 : 1237);
		result = prime * result + (tiChange ? 1231 : 1237);
		result = prime * result + (tiConsult ? 1231 : 1237);
		result = prime * result + (tiRemove ? 1231 : 1237);
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
		intra_grupo_permissao other = (intra_grupo_permissao) obj;
		if (RelatorioSemanalAdd != other.RelatorioSemanalAdd)
			return false;
		if (RelatorioSemanalConsult != other.RelatorioSemanalConsult)
			return false;
		if (adicionarParcela != other.adicionarParcela)
			return false;
		if (advertMultAdd != other.advertMultAdd)
			return false;
		if (advertMultChange != other.advertMultChange)
			return false;
		if (advertMultConsult != other.advertMultConsult)
			return false;
		if (advertMultRemove != other.advertMultRemove)
			return false;
		if (alterarLancto != other.alterarLancto)
			return false;
		if (aprovarLancto != other.aprovarLancto)
			return false;
		if (assembleiaAdd != other.assembleiaAdd)
			return false;
		if (assembleiaChange != other.assembleiaChange)
			return false;
		if (assembleiaConsult != other.assembleiaConsult)
			return false;
		if (assembleiaRemove != other.assembleiaRemove)
			return false;
		if (cobrancaAdd != other.cobrancaAdd)
			return false;
		if (cobrancaChange != other.cobrancaChange)
			return false;
		if (cobrancaConsult != other.cobrancaConsult)
			return false;
		if (cobrancaRemove != other.cobrancaRemove)
			return false;
		if (condominioAdd != other.condominioAdd)
			return false;
		if (condominioChange != other.condominioChange)
			return false;
		if (condominioConsult != other.condominioConsult)
			return false;
		if (condominioRemove != other.condominioRemove)
			return false;
		if (consultarLancamento != other.consultarLancamento)
			return false;
		if (contabilizadorAdd != other.contabilizadorAdd)
			return false;
		if (contabilizadorChange != other.contabilizadorChange)
			return false;
		if (contabilizadorConsult != other.contabilizadorConsult)
			return false;
		if (contabilizadorRemove != other.contabilizadorRemove)
			return false;
		if (controleContasAdd != other.controleContasAdd)
			return false;
		if (controleContasChange != other.controleContasChange)
			return false;
		if (controleContasConsult != other.controleContasConsult)
			return false;
		if (controleContasRemove != other.controleContasRemove)
			return false;
		if (corpoDiretivoAdd != other.corpoDiretivoAdd)
			return false;
		if (corpoDiretivoChange != other.corpoDiretivoChange)
			return false;
		if (corpoDiretivoConsult != other.corpoDiretivoConsult)
			return false;
		if (corpoDiretivoRemove != other.corpoDiretivoRemove)
			return false;
		if (diretoriaAdd != other.diretoriaAdd)
			return false;
		if (diretoriaChange != other.diretoriaChange)
			return false;
		if (diretoriaConsult != other.diretoriaConsult)
			return false;
		if (diretoriaRemove != other.diretoriaRemove)
			return false;
		if (dpAdd != other.dpAdd)
			return false;
		if (dpChange != other.dpChange)
			return false;
		if (dpConsult != other.dpConsult)
			return false;
		if (dpRemove != other.dpRemove)
			return false;
		if (emissaoAdd != other.emissaoAdd)
			return false;
		if (emissaoChange != other.emissaoChange)
			return false;
		if (emissaoConsult != other.emissaoConsult)
			return false;
		if (emissaoRemove != other.emissaoRemove)
			return false;
		if (emissorAdd != other.emissorAdd)
			return false;
		if (emissorChange != other.emissorChange)
			return false;
		if (emissorConsult != other.emissorConsult)
			return false;
		if (emissorRemove != other.emissorRemove)
			return false;
		if (esAdd != other.esAdd)
			return false;
		if (esChange != other.esChange)
			return false;
		if (esConsult != other.esConsult)
			return false;
		if (esRemove != other.esRemove)
			return false;
		if (excluirEtiquetaSiga != other.excluirEtiquetaSiga)
			return false;
		if (excluirLancto != other.excluirLancto)
			return false;
		if (globalAdd != other.globalAdd)
			return false;
		if (globalChange != other.globalChange)
			return false;
		if (globalConsult != other.globalConsult)
			return false;
		if (globalRemove != other.globalRemove)
			return false;
		if (historicoLancamento != other.historicoLancamento)
			return false;
		if (imagemRecebidaAdd != other.imagemRecebidaAdd)
			return false;
		if (imagemRecebidaConsult != other.imagemRecebidaConsult)
			return false;
		if (juridicoAdd != other.juridicoAdd)
			return false;
		if (juridicoChange != other.juridicoChange)
			return false;
		if (juridicoConsult != other.juridicoConsult)
			return false;
		if (juridicoRemove != other.juridicoRemove)
			return false;
		if (lancamento != other.lancamento)
			return false;
		if (listaPresencaAdd != other.listaPresencaAdd)
			return false;
		if (listaPresencaChange != other.listaPresencaChange)
			return false;
		if (listaPresencaConsult != other.listaPresencaConsult)
			return false;
		if (listaPresencaRemove != other.listaPresencaRemove)
			return false;
		if (nomeGrupo == null) {
			if (other.nomeGrupo != null)
				return false;
		} else if (!nomeGrupo.equals(other.nomeGrupo))
			return false;
		if (papercutAdd != other.papercutAdd)
			return false;
		if (papercutChange != other.papercutChange)
			return false;
		if (papercutConsult != other.papercutConsult)
			return false;
		if (papercutRemove != other.papercutRemove)
			return false;
		if (preAprovarLancto != other.preAprovarLancto)
			return false;
		if (previsaoAdd != other.previsaoAdd)
			return false;
		if (previsaoChange != other.previsaoChange)
			return false;
		if (previsaoConsult != other.previsaoConsult)
			return false;
		if (previsaoRemove != other.previsaoRemove)
			return false;
		if (protocolos != other.protocolos)
			return false;
		if (querymAdd != other.querymAdd)
			return false;
		if (querymChange != other.querymChange)
			return false;
		if (querymConsult != other.querymConsult)
			return false;
		if (querymRemove != other.querymRemove)
			return false;
		if (reprovarLancto != other.reprovarLancto)
			return false;
		if (requisicaoProtocoloAdd != other.requisicaoProtocoloAdd)
			return false;
		if (requisicaoProtocoloChange != other.requisicaoProtocoloChange)
			return false;
		if (requisicaoProtocoloConsult != other.requisicaoProtocoloConsult)
			return false;
		if (requisicaoProtocoloRemove != other.requisicaoProtocoloRemove)
			return false;
		if (semAdd != other.semAdd)
			return false;
		if (semChange != other.semChange)
			return false;
		if (semConsult != other.semConsult)
			return false;
		if (semRemove != other.semRemove)
			return false;
		if (servicosAdd != other.servicosAdd)
			return false;
		if (servicosChange != other.servicosChange)
			return false;
		if (servicosConsult != other.servicosConsult)
			return false;
		if (servicosRemove != other.servicosRemove)
			return false;
		if (sysAdmin != other.sysAdmin)
			return false;
		if (sysAdvertMult != other.sysAdvertMult)
			return false;
		if (sysAssembleia != other.sysAssembleia)
			return false;
		if (sysCobranca != other.sysCobranca)
			return false;
		if (sysCondominio != other.sysCondominio)
			return false;
		if (sysContabilidade != other.sysContabilidade)
			return false;
		if (sysContabilizador != other.sysContabilizador)
			return false;
		if (sysContas != other.sysContas)
			return false;
		if (sysControleContas != other.sysControleContas)
			return false;
		if (sysCorpoDiretivo != other.sysCorpoDiretivo)
			return false;
		if (sysDP != other.sysDP)
			return false;
		if (sysDiretoria != other.sysDiretoria)
			return false;
		if (sysES != other.sysES)
			return false;
		if (sysEmissao != other.sysEmissao)
			return false;
		if (sysEmissor != other.sysEmissor)
			return false;
		if (sysFinanceiro != other.sysFinanceiro)
			return false;
		if (sysGlobal != other.sysGlobal)
			return false;
		if (sysJuridico != other.sysJuridico)
			return false;
		if (sysPapercut != other.sysPapercut)
			return false;
		if (sysPrevisao != other.sysPrevisao)
			return false;
		if (sysQuerym != other.sysQuerym)
			return false;
		if (sysQuiz != other.sysQuiz)
			return false;
		if (sysRelatorioSemanal != other.sysRelatorioSemanal)
			return false;
		if (sysSEM != other.sysSEM)
			return false;
		if (sysServicos != other.sysServicos)
			return false;
		if (sysSip != other.sysSip)
			return false;
		if (sysSipFiscal != other.sysSipFiscal)
			return false;
		if (sysSipFiscalAdd != other.sysSipFiscalAdd)
			return false;
		if (sysSipFiscalChange != other.sysSipFiscalChange)
			return false;
		if (sysSipFiscalConsult != other.sysSipFiscalConsult)
			return false;
		if (sysSipFiscalRemove != other.sysSipFiscalRemove)
			return false;
		if (sysSipGEDAdd != other.sysSipGEDAdd)
			return false;
		if (sysSipGEDChange != other.sysSipGEDChange)
			return false;
		if (sysSipGEDConsult != other.sysSipGEDConsult)
			return false;
		if (sysSipGEDRemove != other.sysSipGEDRemove)
			return false;
		if (sysSipGed != other.sysSipGed)
			return false;
		if (sysSipGerencia != other.sysSipGerencia)
			return false;
		if (sysSipGerenciaAdd != other.sysSipGerenciaAdd)
			return false;
		if (sysSipGerenciaChange != other.sysSipGerenciaChange)
			return false;
		if (sysSipGerenciaConsult != other.sysSipGerenciaConsult)
			return false;
		if (sysSipGerenciaRemove != other.sysSipGerenciaRemove)
			return false;
		if (sysSipLancamento != other.sysSipLancamento)
			return false;
		if (sysSipLancamentoAdd != other.sysSipLancamentoAdd)
			return false;
		if (sysSipLancamentoChange != other.sysSipLancamentoChange)
			return false;
		if (sysSipLancamentoConsult != other.sysSipLancamentoConsult)
			return false;
		if (sysSipLancamentoRemove != other.sysSipLancamentoRemove)
			return false;
		if (sysSipPreLancamento != other.sysSipPreLancamento)
			return false;
		if (sysSipPreLancamentoAdd != other.sysSipPreLancamentoAdd)
			return false;
		if (sysSipPreLancamentoChange != other.sysSipPreLancamentoChange)
			return false;
		if (sysSipPreLancamentoConsult != other.sysSipPreLancamentoConsult)
			return false;
		if (sysSipPreLancamentoRemove != other.sysSipPreLancamentoRemove)
			return false;
		if (sysTI != other.sysTI)
			return false;
		if (sysTaxaInadimplencia != other.sysTaxaInadimplencia)
			return false;
		if (syslistaPresenca != other.syslistaPresenca)
			return false;
		if (sysrequisicaoProtocolo != other.sysrequisicaoProtocolo)
			return false;
		if (tiAdd != other.tiAdd)
			return false;
		if (tiChange != other.tiChange)
			return false;
		if (tiConsult != other.tiConsult)
			return false;
		if (tiRemove != other.tiRemove)
			return false;
		return true;
	}

}
