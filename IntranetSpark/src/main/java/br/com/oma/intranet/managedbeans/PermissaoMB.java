package br.com.oma.intranet.managedbeans;

import java.util.List;

import br.com.oma.intranet.entidades.intra_grupo_permissao;

public class PermissaoMB {

	public intra_grupo_permissao retornaPermissao(List<intra_grupo_permissao> lista) {
		intra_grupo_permissao igp = new intra_grupo_permissao();

		for (intra_grupo_permissao permissao : lista) {

			/** Contas **/
			if (permissao.isSysContas()) {
				igp.setSysContas(true);
			}

			/** DP **/
			if (permissao.isSysDP()) {
				igp.setSysDP(true);
			}
			if (permissao.isDpAdd()) {
				igp.setDpAdd(true);
			}
			if (permissao.isDpChange()) {
				igp.setDpChange(true);
			}
			if (permissao.isDpConsult()) {
				igp.setDpConsult(true);
			}
			if (permissao.isDpRemove()) {
				igp.setDpRemove(true);
			}

			/** Diretoria **/
			if (permissao.isSysDiretoria()) {
				igp.setSysDiretoria(true);
			}

			/** Contabilidade **/
			if (permissao.isSysContabilidade()) {
				igp.setSysContabilidade(true);
			}
			if (permissao.isImagemRecebidaAdd()) {
				igp.setImagemRecebidaAdd(true);
			}
			if (permissao.isImagemRecebidaConsult()) {
				igp.setImagemRecebidaConsult(true);
			}

			/** Cobrança **/
			if (permissao.isSysCobranca()) {
				igp.setSysCobranca(true);
			}
			if (permissao.isCobrancaAdd()) {
				igp.setCobrancaAdd(true);
			}
			if (permissao.isCobrancaChange()) {
				igp.setCobrancaChange(true);
			}
			if (permissao.isCobrancaConsult()) {
				igp.setCobrancaConsult(true);
			}
			if (permissao.isCobrancaRemove()) {
				igp.setCobrancaRemove(true);
			}

			/** Advertencia / Multa **/
			if (permissao.isSysAdvertMult()) {
				igp.setSysAdvertMult(true);
			}
			if (permissao.isAdvertMultAdd()) {
				igp.setAdvertMultAdd(true);
			}
			if (permissao.isAdvertMultChange()) {
				igp.setAdvertMultChange(true);
			}
			if (permissao.isAdvertMultConsult()) {
				igp.setAdvertMultConsult(true);
			}
			if (permissao.isAdvertMultRemove()) {
				igp.setAdvertMultRemove(true);
			}

			/** Administrador **/
			if (permissao.isSysAdmin()) {
				igp.setSysAdmin(true);
			}
			/** Gerencia **/
			if (permissao.isSysAssembleia()) {
				igp.setSysAssembleia(true);
			}
			if (permissao.isAssembleiaAdd()) {
				igp.setAssembleiaAdd(true);
			}
			if (permissao.isAssembleiaChange()) {
				igp.setAssembleiaChange(true);
			}
			if (permissao.isAssembleiaConsult()) {
				igp.setAssembleiaConsult(true);
			}
			if (permissao.isAssembleiaRemove()) {
				igp.setAssembleiaRemove(true);
			}

			/** Contabilizador **/
			if (permissao.isSysContabilizador()) {
				igp.setSysContabilizador(true);
			}
			if (permissao.isContabilizadorAdd()) {
				igp.setContabilizadorAdd(true);
			}
			if (permissao.isContabilizadorChange()) {
				igp.setContabilizadorChange(true);
			}
			if (permissao.isContabilizadorConsult()) {
				igp.setContabilizadorConsult(true);
			}
			if (permissao.isContabilizadorRemove()) {
				igp.setContabilizadorRemove(true);
			}

			/** Controle de Contas **/
			if (permissao.isSysControleContas()) {
				igp.setSysControleContas(true);
			}
			if (permissao.isControleContasAdd()) {
				igp.setControleContasAdd(true);
			}
			if (permissao.isControleContasChange()) {
				igp.setControleContasChange(true);
			}
			if (permissao.isControleContasConsult()) {
				igp.setControleContasConsult(true);
			}
			if (permissao.isControleContasRemove()) {
				igp.setControleContasRemove(true);
			}

			/** Corpo Diretivo **/
			if (permissao.isSysCorpoDiretivo()) {
				igp.setSysCorpoDiretivo(true);
			}
			if (permissao.isCorpoDiretivoAdd()) {
				igp.setCorpoDiretivoAdd(true);
			}
			if (permissao.isCorpoDiretivoChange()) {
				igp.setCorpoDiretivoChange(true);
			}
			if (permissao.isCorpoDiretivoConsult()) {
				igp.setCorpoDiretivoConsult(true);
			}
			if (permissao.isCorpoDiretivoRemove()) {
				igp.setCorpoDiretivoRemove(true);
			}

			/** Emissor **/
			if (permissao.isSysEmissor()) {
				igp.setSysEmissor(true);
			}
			if (permissao.isEmissorAdd()) {
				igp.setEmissorAdd(true);
			}
			if (permissao.isEmissorChange()) {
				igp.setEmissorChange(true);
			}
			if (permissao.isEmissorConsult()) {
				igp.setEmissorConsult(true);
			}
			if (permissao.isEmissorRemove()) {
				igp.setEmissorRemove(true);
			}

			/** Global **/
			if (permissao.isSysGlobal()) {
				igp.setSysGlobal(true);
			}
			if (permissao.isGlobalAdd()) {
				igp.setGlobalAdd(true);
			}
			if (permissao.isGlobalChange()) {
				igp.setGlobalChange(true);
			}
			if (permissao.isGlobalConsult()) {
				igp.setGlobalConsult(true);
			}
			if (permissao.isGlobalRemove()) {
				igp.setGlobalRemove(true);
			}

			/** Relatorio Semanal **/
			if (permissao.isSysRelatorioSemanal()) {
				igp.setSysRelatorioSemanal(true);
			}
			if (permissao.isRelatorioSemanalAdd()) {
				igp.setRelatorioSemanalAdd(true);
			}
			if (permissao.isRelatorioSemanalConsult()) {
				igp.setRelatorioSemanalConsult(true);
			}

			/** Taxa Inadimplencia **/
			if (permissao.isSysTaxaInadimplencia()) {
				igp.setSysTaxaInadimplencia(true);
			}

			/** Lista de Presenca **/
			if (permissao.isSyslistaPresenca()) {
				igp.setSyslistaPresenca(true);
			}
			if (permissao.isListaPresencaAdd()) {
				igp.setListaPresencaAdd(true);
			}
			if (permissao.isListaPresencaChange()) {
				igp.setListaPresencaChange(true);
			}
			if (permissao.isListaPresencaConsult()) {
				igp.setListaPresencaConsult(true);
			}
			if (permissao.isListaPresencaRemove()) {
				igp.setListaPresencaRemove(true);
			}

			/** Requisição de Malote **/
			if (permissao.isSysrequisicaoProtocolo()) {
				igp.setSysrequisicaoProtocolo(true);
			}
			if (permissao.isRequisicaoProtocoloAdd()) {
				igp.setRequisicaoProtocoloAdd(true);
			}
			if (permissao.isRequisicaoProtocoloChange()) {
				igp.setRequisicaoProtocoloChange(true);
			}
			if (permissao.isRequisicaoProtocoloConsult()) {
				igp.setRequisicaoProtocoloConsult(true);
			}
			if (permissao.isRequisicaoProtocoloRemove()) {
				igp.setRequisicaoProtocoloRemove(true);
			}

			/** Previsao Orcamentaria **/
			if (permissao.isSysPrevisao()) {
				igp.setSysPrevisao(true);
			}
			if (permissao.isPrevisaoAdd()) {
				igp.setPrevisaoAdd(true);
			}
			if (permissao.isPrevisaoChange()) {
				igp.setPrevisaoChange(true);
			}
			if (permissao.isPrevisaoConsult()) {
				igp.setPrevisaoConsult(true);
			}
			if (permissao.isPrevisaoRemove()) {
				igp.setPrevisaoRemove(true);
			}

			/** Entrada e Saida **/
			if (permissao.isSysES()) {
				igp.setSysES(true);
			}
			if (permissao.isEsAdd()) {
				igp.setEsAdd(true);
			}
			if (permissao.isEsChange()) {
				igp.setEsChange(true);
			}
			if (permissao.isEsConsult()) {
				igp.setEsConsult(true);
			}
			if (permissao.isEsRemove()) {
				igp.setEsRemove(true);
			}

			/** Finaceiro **/
			if (permissao.isSysFinanceiro()) {
				igp.setSysFinanceiro(true);
			}
			if (permissao.isLancamento()) {
				igp.setLancamento(true);
			}
			if (permissao.isConsultarLancamento()) {
				igp.setConsultarLancamento(true);
			}
			if (permissao.isHistoricoLancamento()) {
				igp.setHistoricoLancamento(true);
			}
			if (permissao.isAlterarLancto()) {
				igp.setAlterarLancto(true);
			}
			if (permissao.isPreAprovarLancto()) {
				igp.setPreAprovarLancto(true);
			}
			if (permissao.isAprovarLancto()) {
				igp.setAprovarLancto(true);
			}
			if (permissao.isExcluirLancto()) {
				igp.setExcluirLancto(true);
			}
			if (permissao.isReprovarLancto()) {
				igp.setReprovarLancto(true);
			}
			if (permissao.isExcluirLancto()) {
				igp.setExcluirLancto(true);
			}
			if (permissao.isAdicionarParcela()) {
				igp.setAdicionarParcela(true);
			}
			if (permissao.isExcluirEtiquetaSiga()) {
				igp.setExcluirEtiquetaSiga(true);
			}

			/** Query E-mail **/
			if (permissao.isSysQuerym()) {
				igp.setSysQuerym(true);
			}
			if (permissao.isQuerymAdd()) {
				igp.setQuerymAdd(true);
			}
			if (permissao.isQuerymChange()) {
				igp.setQuerymChange(true);
			}
			if (permissao.isQuerymConsult()) {
				igp.setQuerymConsult(true);
			}
			if (permissao.isQuerymRemove()) {
				igp.setQuerymRemove(true);
			}

			/** Juridico **/
			if (permissao.isSysJuridico()) {
				igp.setSysJuridico(true);
			}
			if (permissao.isJuridicoAdd()) {
				igp.setJuridicoAdd(true);
			}
			if (permissao.isJuridicoChange()) {
				igp.setJuridicoChange(true);
			}
			if (permissao.isJuridicoConsult()) {
				igp.setJuridicoConsult(true);
			}
			if (permissao.isJuridicoRemove()) {
				igp.setJuridicoRemove(true);
			}

			/** Contabilizador **/
			if (permissao.isSysContabilizador()) {
				igp.setSysContabilizador(true);
			}
			if (permissao.isContabilizadorAdd()) {
				igp.setContabilizadorAdd(true);
			}
			if (permissao.isContabilizadorChange()) {
				igp.setContabilizadorChange(true);
			}
			if (permissao.isContabilizadorConsult()) {
				igp.setContabilizadorConsult(true);
			}
			if (permissao.isContabilizadorRemove()) {
				igp.setContabilizadorRemove(true);
			}

			/** Papercut **/
			if (permissao.isSysPapercut()) {
				igp.setSysPapercut(true);
			}
			if (permissao.isPapercutAdd()) {
				igp.setPapercutAdd(true);
			}
			if (permissao.isPapercutChange()) {
				igp.setPapercutChange(true);
			}
			if (permissao.isPapercutConsult()) {
				igp.setPapercutConsult(true);
			}
			if (permissao.isPapercutRemove()) {
				igp.setPapercutRemove(true);
			}

			/** Repasse de Serviços **/
			if (permissao.isSysServicos()) {
				igp.setSysServicos(true);
			}
			if (permissao.isServicosAdd()) {
				igp.setServicosAdd(true);
			}
			if (permissao.isServicosChange()) {
				igp.setServicosChange(true);
			}
			if (permissao.isServicosConsult()) {
				igp.setServicosConsult(true);
			}
			if (permissao.isServicosRemove()) {
				igp.setServicosRemove(true);
			}

			/** SIP **/
			if (permissao.isSysSip()) {
				igp.setSysSip(true);
			}
			
			// GED
			
			if (permissao.isSysSipGed()) {
				igp.setSysSipGed(true);
			}
			if (permissao.isSysSipGEDAdd()) {
				igp.setSysSipGEDAdd(true);
			}
			if (permissao.isSysSipGEDChange()) {
				igp.setSysSipGEDChange(true);
			}
			if (permissao.isSysSipGEDConsult()) {
				igp.setSysSipGEDConsult(true);
			}
			if (permissao.isSysSipGEDRemove()) {
				igp.setSysSipGEDRemove(true);
			}
			
			// FISCAL
			
			if (permissao.isSysSipFiscal()) {
				igp.setSysSipFiscal(true);
			}
			if (permissao.isSysSipFiscalAdd()) {
				igp.setSysSipFiscalAdd(true);
			}
			if (permissao.isSysSipFiscalChange()) {
				igp.setSysSipFiscalChange(true);
			}
			if (permissao.isSysSipFiscalConsult()) {
				igp.setSysSipFiscalConsult(true);
			}
			if (permissao.isSysSipFiscalRemove()) {
				igp.setSysSipFiscalRemove(true);
			}
			
			// GERENCIA
			
			if (permissao.isSysSipGerencia()) {
				igp.setSysSipGerencia(true);
			}
			if (permissao.isSysSipGerenciaAdd()) {
				igp.setSysSipGerenciaAdd(true);
			}
			if (permissao.isSysSipGerenciaChange()) {
				igp.setSysSipGerenciaChange(true);
			}
			if (permissao.isSysSipGerenciaConsult()) {
				igp.setSysSipGerenciaConsult(true);
			}
			if (permissao.isSysSipGerenciaRemove()) {
				igp.setSysSipGerenciaRemove(true);
			}
			
			// PRE LANCAMENTO
			
			if(permissao.isSysSipPreLancamento()){
				igp.setSysSipPreLancamento(true);
			}
			if(permissao.isSysSipPreLancamentoAdd()){
				igp.setSysSipPreLancamentoAdd(true);
			}
			if(permissao.isSysSipPreLancamentoChange()){
				igp.setSysSipPreLancamentoChange(true);
			}
			if(permissao.isSysSipPreLancamentoConsult()){
				igp.setSysSipPreLancamentoConsult(true);
			}
			if(permissao.isSysSipPreLancamentoRemove()){
				igp.setSysSipPreLancamentoRemove(true);
			}
			
			// LANCAMENTO
			
			if(permissao.isSysSipLancamento()){
				igp.setSysSipLancamento(true);
			}
			if(permissao.isSysSipLancamentoAdd()){
				igp.setSysSipLancamentoAdd(true);
			}
			if(permissao.isSysSipLancamentoChange()){
				igp.setSysSipLancamentoChange(true);
			}
			if(permissao.isSysSipLancamentoConsult()){
				igp.setSysSipLancamentoConsult(true);
			}
			if(permissao.isSysSipLancamentoRemove()){
				igp.setSysSipLancamentoRemove(true);
			}
			
			/** SISTEMA EMISSÃO **/
			if (permissao.isSysSEM()) {
				igp.setSysSEM(true);
			}
			if (permissao.isSemAdd()) {
				igp.setSemAdd(true);
			}
			if (permissao.isSemChange()) {
				igp.setSemChange(true);
			}
			if (permissao.isSemConsult()) {
				igp.setSemConsult(true);
			}
			if (permissao.isSemRemove()) {
				igp.setSemRemove(true);
			}

		}

		return igp;
	}
}
