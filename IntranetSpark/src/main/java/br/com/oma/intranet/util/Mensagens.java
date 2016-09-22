package br.com.oma.intranet.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagens extends StringUtil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2602114843345106699L;

	public void msgAdicinado(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Adicionado!"));
	}
	
	public void msgSalvo(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo!", "Salvo!"));
	}
	
	public void msgAlterado(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado!", "Alterado!"));
	}
	
	public void msgErro(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro - Entre em contato com Administrador!"));
	}
	
	public void msgExiste(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Esse GRUPO já Existe!"));
	}
	
	public void msgExclusao(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído!", "Excluído!"));
	}
	
	public void msgObrigatorio(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Preencha campo obrigatório!"));
	}
	
	public void msgPermSubCat(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Sub-Categoria já criada para esse modulo!"));
	}
	
	public void msgLoginInvalido(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Login ou Senha incorreto!"));
	}
	
	public void msgLoginNaoEncontrado(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Usuário não encontrado!"));
	}
	
	public void msgAssociado(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Não foi possível excluir esse Grupo existe usuários associados !"));
	}
	
	public void msgEmail(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "E-mail incorreto !"));
	}
	
	public void msgInteiro(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Só é permitido números !"));
	}
	
	public void msgCondominio(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione o condomínio !"));
	}
	
	public void msgRegistro(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione um Registro para Exclussão !"));
	}
	
	public void msgExcluirConta(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione uma Conta para Exclussão !"));
	}
	
	public void msgSelecione(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione um item !"));
	}
	
	public void msgSelecioneAno(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione o Ano de projeção !"));
	}
	
	public void msgSelecioneRateio(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione o tipo de Rateio !"));
	}
	
	public void msgUsuario(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Esse usuário já existe !"));
	}
	
	public void msgAtualizado(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Atualizado com sucesso !"));
	}
	
	public void msgSelectLancto(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione um Lançamento !"));
	}
	
	public void msgReprovaLancto(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Lançamento reprovado !"));
	}
	
	public void msgMotivoReprovar(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Insira um motivo para a reprovação !"));
	}
	
	public void msgAbriFollowUp(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Não há nenhum Follow Up para este lançamento !"));
	}
	
	public void msgCodigoBarraIncorreto(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Codigo de barras incorreto !"));
	}
	
	public void msgCodigoBarraInserir(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Insira o codigo de barras !"));
	}
	
	public void msgAprovado(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Aprovado !"));
	}
	
	public void msgErroLanctoAprovado(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Erros - Esse laçamento já consta aprovado !"));
	}
	
	public void msgTenteNovamente(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Ocorreu um erro, tente novamente !"));
	}
	
	public void msgErroLanctoNaoAprovado(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Erros - Não foi possível aprovado !"));
	}
	
	public void msgSemPermissao(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Você não tem permissão para aprovar este lançamento !"));
	}
	
	public void msgJaAprovou(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Você já aprovou este lançamento! Aguarde a aprovação dos outros responsáveis. !"));
	}
	
	public void msgCodigoEtiqueta(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Insira o codigo da Etiqueta !"));
	}
	
	public void msgProtJaExiste(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Este arquivo já foi inserido neste protocolo !"));
	}
	
	public void msgProtEtiquetaN(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Etiqueta não encontrada !"));
	}
	
	public void msgProtLanctoN(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Lançamento não encontrado !"));
	}
	
	public void msgProtInserirE(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Insira o número da etiqueta que deseja adicionar !"));
	}
	
	public void msgProtBaixarR(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Insira o número da etiqueta que deseja receber !"));
	}
	
	public void msgProtBaixarTR(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Todos os lançamentos deste protocolo já foram recebidos !"));
	}
	
	public void msgProtErro(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro - Não foi possível receber !"));
	}
	
	public void msgProtErroS(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro - Não foi possível salvar !"));
	}
	
	public void msgProtErroSV(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro - Não é possível salvar um protocolo vazio !"));
	}
	
	public void msgProtSelec(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Selecione um ou mais protocolos para excluir !"));
	}
	
	public void msgContaN(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Conta não encontrada !"));
	}
	public void msgCPFInvalido(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "CPF inválido !"));
	}
	public void msgCPFValido(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "CPF válido !"));
	}
	public void msgCNPJInvalido(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "CNPJ inválido !"));
	}
	public void msgCNPJValido(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "CNPJ válido !"));
	}
	public void reciboCancelado(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Recibo cancelado!"));
	}
	
	public void msgExclusaoEtiquetaErro(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Arquivo não pode ser excluído, vinculado a Lançamento OMA!"));
	}
}
