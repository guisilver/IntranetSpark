package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.oma.intranet.dao.GrupoDeptoDAO;
import br.com.oma.intranet.dao.GrupoGerDAO;
import br.com.oma.intranet.dao.GrupoPermissaoDAO;
import br.com.oma.intranet.dao.LDAPDAO;
import br.com.oma.intranet.dao.UsuarioDAO;
import br.com.oma.intranet.entidades.intra_grupo_depto;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_grupo_permissao;
import br.com.oma.intranet.entidades.intra_usuario;
import br.com.oma.intranet.util.ADException;
import br.com.oma.intranet.util.CriptografaSenha;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.StringUtil;

@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioMB extends Mensagens implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2499983268864854756L;
	
	//DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private UsuarioDAO userDAO;

	private GrupoDeptoDAO deptoDAO;

	private GrupoGerDAO gerDAO;

	private GrupoPermissaoDAO permissoesDAO;

	private intra_usuario user = new intra_usuario();
	private intra_usuario usuarioBean = new intra_usuario();

	// ATRIBUTOS
	private List<String> nomesDosGrupos;

	private List<String> nomesPermissoes;

	private List<String> nomesDosGerentes;

	private List<intra_usuario> listaUsuarios, filtroUsuarios;

	private List<intra_grupo_depto> gruposDepto;

	private List<intra_grupo_gerente> gruposGer, gerente;

	private List<intra_grupo_permissao> gruposPerm, permissao;
	
	private List<String> listaDeDepto;
	
	private List<String> listaDeGerente;
	
	private List<String> listaDePermissao;

	private String nomeLDAP;

	private TreeNode raiz;
	private TreeNode confirm;
	private TreeNode[] selectedNodes2;
	private boolean excluirUsuario;
	private String novaSenha;

	private int nenhum;
	private int trocaPagina;
	private int updateGerenCodigo;
	


	// GET X SET
	/**
	 * @param sessaoMB the sessaoMB to set
	 */
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public List<intra_grupo_depto> getGruposDepto() {
		if (this.gruposDepto == null) {
			this.deptoDAO = new GrupoDeptoDAO();
			this.gruposDepto = this.deptoDAO.buscaTodos();
		}
		return this.gruposDepto;
	}

	public intra_usuario getUser() {
		return user;
	}

	public void setUser(intra_usuario user) {
		this.user = user;
	}

	public intra_usuario getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(intra_usuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public List<intra_grupo_gerente> getGruposGer() {
		if (this.gruposGer == null) {
			this.gerDAO = new GrupoGerDAO();
			this.gruposGer = this.gerDAO.buscaTodos();
		}
		return gruposGer;
	}

	public List<intra_usuario> getListaUsuarios() {
		if (this.listaUsuarios == null) {
			this.userDAO = new UsuarioDAO();
			this.listaUsuarios = this.userDAO.buscaTodos();
		}
		return this.listaUsuarios;
	}

	public List<String> getNomesDosGrupos() {
		return nomesDosGrupos;
	}

	public void setNomesDosGrupos(List<String> nomesDosGrupos) {
		this.nomesDosGrupos = nomesDosGrupos;
	}

	public String getNomeLDAP() {
		return nomeLDAP;
	}

	public void setNomeLDAP(String nomeLDAP) {
		this.nomeLDAP = nomeLDAP;
	}

	public void setNomesDosGerentes(List<String> nomesDosGerentes) {
		this.nomesDosGerentes = nomesDosGerentes;
	}

	public List<String> getNomesDosGerentes() {
		return nomesDosGerentes;
	}

	public List<intra_grupo_permissao> getGruposPerm() {
		if (this.gruposPerm == null) {
			this.permissoesDAO = new GrupoPermissaoDAO();
			this.gruposPerm = this.permissoesDAO.buscaTodos();
		}
		return gruposPerm;
	}

	public void setGruposPerm(List<intra_grupo_permissao> gruposPerm) {
		this.gruposPerm = gruposPerm;
	}

	public int getNenhum() {
		return nenhum;
	}

	public void setNenhum(int nenhum) {
		this.nenhum = nenhum;
	}

	public List<String> getNomesPermissoes() {
		return nomesPermissoes;
	}

	public void setNomesPermissoes(List<String> nomesPermissoes) {
		this.nomesPermissoes = nomesPermissoes;
	}

	public List<intra_usuario> getFiltroUsuarios() {
		return filtroUsuarios;
	}

	public void setFiltroUsuarios(List<intra_usuario> filtroUsuarios) {
		this.filtroUsuarios = filtroUsuarios;
	}

	public void setListaUsuarios(List<intra_usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	@SuppressWarnings("unused")
	public TreeNode getRaiz() {
		if (this.nomesPermissoes != null) {
			this.raiz = new DefaultTreeNode("Root", null);

			for (String permissao : this.nomesPermissoes) {

				for (intra_grupo_permissao per : this.gruposPerm) {
					if (per.getNomeGrupo().trim().equals(permissao)) {

						TreeNode node0 = new DefaultTreeNode(per.getNomeGrupo(), this.raiz);

						TreeNode node1 = new DefaultTreeNode("Administrador", node0);
						TreeNode node12 = new DefaultTreeNode(per.isSysAdmin() == true ? "Sim" : "Não", node1);

						TreeNode node2 = new DefaultTreeNode("Assembleias",	node0);
						TreeNode node21 = new DefaultTreeNode("Acessar - "	+ (per.isSysAssembleia() == true ? "Sim" : "Não"),	node2);
						TreeNode node22 = new DefaultTreeNode("Inserir - "	+ (per.isAssembleiaAdd() == true ? "Sim" : "Não"),	node2);
						TreeNode node23 = new DefaultTreeNode("Alterar - "	+ (per.isAssembleiaAdd() == true ? "Sim" : "Não"), node2);
						TreeNode node24 = new DefaultTreeNode("Consultar - " + (per.isAssembleiaAdd() == true ? "Sim"	: "Não"), node2);
						TreeNode node25 = new DefaultTreeNode("Excluir - "	+ (per.isAssembleiaAdd() == true ? "Sim" : "Não"), node2);

					}
				}

			}
		}
		return raiz;
	}

	@SuppressWarnings("unused")
	public TreeNode getConfirm() {
		if (this.nomesPermissoes != null) {
			this.confirm = new DefaultTreeNode("Root", null);

			for (String permissao : this.nomesPermissoes) {

				for (intra_grupo_permissao per : this.gruposPerm) {
					if (per.getNomeGrupo().trim().equals(permissao)) {

						TreeNode node0 = new DefaultTreeNode(per.getNomeGrupo(), this.confirm);

						/** ADMINISTRADOR **/
						TreeNode nodeAdm = new DefaultTreeNode("Administrador", node0);
						TreeNode nodeAdm1 = new DefaultTreeNode(per.isSysAdmin() == true ? "Sim" : "Não", nodeAdm);
						
						/** Advertencia **/
						TreeNode nodeAdvm = new DefaultTreeNode("Advertencia/Multa",	node0);
						if (per.isSysAdvertMult()) {
							TreeNode nodeAdvm1 = new DefaultTreeNode("Acessar - "	+ (per.isSysAdvertMult() == true ? "Sim" : "Não"), nodeAdvm);
						}else{
							TreeNode nodeAdvm1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeAdvm);
						}
						if (per.isAdvertMultAdd()) {
							TreeNode nodeAdvm2 = new DefaultTreeNode("Inserir - "	+ (per.isAdvertMultAdd() == true ? "Sim" : "Não"), nodeAdvm);
						}
						if (per.isAdvertMultChange()) {
							TreeNode nodeAdvm3 = new DefaultTreeNode("Alterar - "	+ (per.isAdvertMultChange() == true ? "Sim" : "Não"), nodeAdvm);
						}
						if (per.isAdvertMultConsult()) {
							TreeNode nodeAdvm4 = new DefaultTreeNode("Consultar - "	+ (per.isAdvertMultConsult() == true ? "Sim" : "Não"), nodeAdvm);
						}
						if (per.isAdvertMultRemove()) {
							TreeNode nodeAdvm5 = new DefaultTreeNode("Remover - "	+ (per.isAdvertMultRemove() == true ? "Sim" : "Não"), nodeAdvm);
						}
						
						/** ASSERMBLEIA **/
						TreeNode nodeAssembleia = new DefaultTreeNode("Assembleias",	node0);
						if (per.isSysAssembleia()) {
							TreeNode nodeAss1 = new DefaultTreeNode("Acessar - "	+ (per.isSysAssembleia() == true ? "Sim" : "Não"), nodeAssembleia);
						}else{
							TreeNode nodeAss1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeAssembleia);
						}
						if (per.isAssembleiaAdd()) {
							TreeNode nodeAss2 = new DefaultTreeNode("Inserir - "	+ (per.isAssembleiaAdd() == true ? "Sim" : "Não"), nodeAssembleia);
						}
						if (per.isAssembleiaChange()) {
							TreeNode nodeAss3 = new DefaultTreeNode("Alterar - "	+ (per.isAssembleiaChange() == true ? "Sim" : "Não"), nodeAssembleia);
						}
						if (per.isAssembleiaConsult()) {
							TreeNode nodeAss4 = new DefaultTreeNode("Consultar - "	+ (per.isAssembleiaConsult() == true ? "Sim" : "Não"), nodeAssembleia);
						}
						if (per.isAssembleiaRemove()) {
							TreeNode nodeAss5 = new DefaultTreeNode("Remover - "	+ (per.isAssembleiaRemove() == true ? "Sim" : "Não"), nodeAssembleia);
						}
						
						/** Contabilidade **/
						TreeNode nodeContabil = new DefaultTreeNode("Baixar Imagem GED",	node0);
						if (per.isSysControleContas()) {
							TreeNode nodeContabil1 = new DefaultTreeNode("Acessar - "	+ (per.isSysContabilidade() == true ? "Sim" : "Não"), nodeContabil);
						}else{
							TreeNode nodeContabil2 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeContabil);
						}
						if (per.isImagemRecebidaAdd()) {
							TreeNode nodeContabil3 = new DefaultTreeNode("Inserir - "	+ (per.isImagemRecebidaAdd() == true ? "Sim" : "Não"), nodeContabil);
						}
						if (per.isImagemRecebidaConsult()) {
							TreeNode nodeContabil4 = new DefaultTreeNode("Consultar - "	+ (per.isImagemRecebidaConsult() == true ? "Sim" : "Não"), nodeContabil);
						}
						
						
						/** Controle de Contas **/
						TreeNode nodeCC = new DefaultTreeNode("Controle de Contas",	node0);
						if (per.isSysControleContas()) {
							TreeNode nodeCC1 = new DefaultTreeNode("Acessar - "	+ (per.isSysControleContas() == true ? "Sim" : "Não"), nodeCC);
						}else{
							TreeNode nodeCC1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeCC);
						}
						if (per.isControleContasAdd()) {
							TreeNode nodeCC2 = new DefaultTreeNode("Inserir - "	+ (per.isControleContasAdd() == true ? "Sim" : "Não"), nodeCC);
						}
						if (per.isControleContasChange()) {
							TreeNode nodeCC3 = new DefaultTreeNode("Alterar - "	+ (per.isControleContasChange() == true ? "Sim" : "Não"), nodeCC);
						}
						if (per.isControleContasConsult()) {
							TreeNode nodeCC4 = new DefaultTreeNode("Consultar - "	+ (per.isControleContasConsult() == true ? "Sim" : "Não"), nodeCC);
						}
						if (per.isControleContasRemove()) {
							TreeNode nodeCC5 = new DefaultTreeNode("Remover - "	+ (per.isControleContasRemove() == true ? "Sim" : "Não"), nodeCC);
						}
						
						/** Corpo Diretivo **/
						TreeNode nodeCD = new DefaultTreeNode("Controle de Contas",	node0);
						if (per.isSysCorpoDiretivo()) {
							TreeNode nodeCD1 = new DefaultTreeNode("Acessar - "	+ (per.isSysCorpoDiretivo() == true ? "Sim" : "Não"), nodeCD);
						}else{
							TreeNode nodeCD1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeCD);
						}
						if (per.isCorpoDiretivoAdd()) {
							TreeNode nodeCD2 = new DefaultTreeNode("Inserir - "	+ (per.isCorpoDiretivoAdd() == true ? "Sim" : "Não"), nodeCD);
						}
						if (per.isCorpoDiretivoChange()) {
							TreeNode nodeCD3 = new DefaultTreeNode("Alterar - "	+ (per.isCorpoDiretivoChange() == true ? "Sim" : "Não"), nodeCD);
						}
						if (per.isCorpoDiretivoConsult()) {
							TreeNode nodeCD4 = new DefaultTreeNode("Consultar - "	+ (per.isCorpoDiretivoConsult() == true ? "Sim" : "Não"), nodeCD);
						}
						if (per.isCorpoDiretivoRemove()) {
							TreeNode nodeCD5 = new DefaultTreeNode("Remover - "	+ (per.isCorpoDiretivoRemove() == true ? "Sim" : "Não"), nodeCD);
						}
						
						/** DP **/
						TreeNode nodeDP = new DefaultTreeNode("DP",	node0);
						if (per.isSysDP()) {
							TreeNode nodeDP1 = new DefaultTreeNode("Acessar - "	+ (per.isSysDP() == true ? "Sim" : "Não"), nodeDP);
						}else{
							TreeNode nodeDP1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeDP);
						}
						if (per.isDpAdd()) {
							TreeNode nodeDP2 = new DefaultTreeNode("Inserir - "	+ (per.isDpAdd() == true ? "Sim" : "Não"), nodeDP);
						}
						if (per.isDpChange()) {
							TreeNode nodeDP3 = new DefaultTreeNode("Alterar - "	+ (per.isDpChange() == true ? "Sim" : "Não"), nodeDP);
						}
						if (per.isDpConsult()) {
							TreeNode nodeDP4 = new DefaultTreeNode("Consultar - "	+ (per.isDpConsult() == true ? "Sim" : "Não"), nodeDP);
						}
						if (per.isDpRemove()) {
							TreeNode nodeDP5 = new DefaultTreeNode("Remover - "	+ (per.isDpRemove() == true ? "Sim" : "Não"), nodeDP);
						}
						
						/** Emissao **/
						TreeNode nodeEmissao = new DefaultTreeNode("Emissão",	node0);
						if (per.isSysEmissao()) {
							TreeNode nodeEmissao1 = new DefaultTreeNode("Acessar - "	+ (per.isSysEmissao() == true ? "Sim" : "Não"), nodeEmissao);
						}else{
							TreeNode nodeEmissao1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeEmissao);
						}
						if (per.isEmissaoAdd()) {
							TreeNode nodeEmissao2 = new DefaultTreeNode("Inserir - "	+ (per.isEmissaoAdd() == true ? "Sim" : "Não"), nodeEmissao);
						}
						if (per.isEmissaoChange()) {
							TreeNode nodeEmissao3 = new DefaultTreeNode("Alterar - "	+ (per.isEmissaoChange() == true ? "Sim" : "Não"), nodeEmissao);
						}
						if (per.isEmissaoConsult()) {
							TreeNode nodeEmissao4 = new DefaultTreeNode("Consultar - "	+ (per.isEmissaoConsult() == true ? "Sim" : "Não"), nodeEmissao);
						}
						if (per.isEmissaoRemove()) {
							TreeNode nodeEmissao5 = new DefaultTreeNode("Remover - "	+ (per.isEmissaoRemove() == true ? "Sim" : "Não"), nodeEmissao);
						}
						
						/** ES **/
						TreeNode nodeES = new DefaultTreeNode("Entrada/Saída",	node0);
						if (per.isSysES()) {
							TreeNode nodeES1 = new DefaultTreeNode("Acessar - "	+ (per.isSysES() == true ? "Sim" : "Não"), nodeES);
						}else{
							TreeNode nodeES1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeES);
						}
						if (per.isEsAdd()) {
							TreeNode nodeES2 = new DefaultTreeNode("Inserir - "	+ (per.isEsAdd() == true ? "Sim" : "Não"), nodeES);
						}
						if (per.isEsChange()) {
							TreeNode nodeES3 = new DefaultTreeNode("Alterar - "	+ (per.isEsChange() == true ? "Sim" : "Não"), nodeES);
						}
						if (per.isEsConsult()) {
							TreeNode nodeES4 = new DefaultTreeNode("Consultar - "	+ (per.isEsConsult() == true ? "Sim" : "Não"), nodeES);
						}
						if (per.isEsRemove()) {
							TreeNode nodeES5 = new DefaultTreeNode("Remover - "	+ (per.isEsRemove() == true ? "Sim" : "Não"), nodeES);
						}
						
						/** Emissor **/
						TreeNode nodeEmissor = new DefaultTreeNode("Emissor",	node0);
						if (per.isSysEmissor()) {
							TreeNode nodeEmissor1 = new DefaultTreeNode("Acessar - "	+ (per.isSysEmissor() == true ? "Sim" : "Não"), nodeEmissor);
						}else{
							TreeNode nodeEmissor1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeEmissor);
						}
						if (per.isEmissorAdd()) {
							TreeNode nodeEmissor2 = new DefaultTreeNode("Inserir - "	+ (per.isEmissorAdd() == true ? "Sim" : "Não"), nodeEmissor);
						}
						if (per.isEmissorChange()) {
							TreeNode nodeEmissor3 = new DefaultTreeNode("Alterar - "	+ (per.isEmissorChange() == true ? "Sim" : "Não"), nodeEmissor);
						}
						if (per.isEmissorConsult()) {
							TreeNode nodeEmissor4 = new DefaultTreeNode("Consultar - "	+ (per.isEmissorConsult() == true ? "Sim" : "Não"), nodeEmissor);
						}
						if (per.isEmissorRemove()) {
							TreeNode nodeEmissor5 = new DefaultTreeNode("Remover - "	+ (per.isEmissorRemove() == true ? "Sim" : "Não"), nodeEmissor);
						}
						
						/** FINANCEIRO **/
						TreeNode nodeFinanceiro = new DefaultTreeNode("Financeiro",	node0);
						if (per.isSysFinanceiro()) {
							TreeNode nodeFinanceiro1 = new DefaultTreeNode("Acessar Financeiro - "	+ (per.isSysFinanceiro() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro1 = new DefaultTreeNode("Acessar Financeiro - "	+ ("Não"), nodeFinanceiro);
						}
						if (per.isLancamento()) {
							TreeNode nodeFinanceiro2 = new DefaultTreeNode("Acessar Lancto. - "	+ (per.isLancamento() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro2 = new DefaultTreeNode("Acessar Lancto. - "	+ ("Não"), nodeFinanceiro);
						}
						if (per.isConsultarLancamento()) {
							TreeNode nodeFinanceiro3 = new DefaultTreeNode("Consultar Lancto. - "	+ (per.isConsultarLancamento() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro3 = new DefaultTreeNode("Consultar Lancto. - "	+ ("Não"), nodeFinanceiro);
						}
						if (per.isHistoricoLancamento()) {
							TreeNode nodeFinanceiro4 = new DefaultTreeNode("Consultar Histórico Lancto. - "	+ (per.isHistoricoLancamento() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro4 = new DefaultTreeNode("Consultar Histórico Lancto. - "	+ ("Não"), nodeFinanceiro);
						}
						if (per.isAlterarLancto()) {
							TreeNode nodeFinanceiro5 = new DefaultTreeNode("Alterar Lancto. - "	+ (per.isAlterarLancto() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro5 = new DefaultTreeNode("Alterar Lancto. - "	+ ("Não"), nodeFinanceiro);
						}
						if (per.isPreAprovarLancto()) {
							TreeNode nodeFinanceiro6 = new DefaultTreeNode("Pré-Aprovar Lancto. - "	+ (per.isPreAprovarLancto() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro6 = new DefaultTreeNode("Pré-Aprovar Lancto. - "	+ ("Não"), nodeFinanceiro);
						}
						if (per.isAprovarLancto()) {
							TreeNode nodeFinanceiro7 = new DefaultTreeNode("Aprovar Lancto. - "	+ (per.isAprovarLancto() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro7 = new DefaultTreeNode("Aprovar Lancto. - "	+ ("Não"), nodeFinanceiro);
						}
						if (per.isExcluirLancto()) {
							TreeNode nodeFinanceiro8 = new DefaultTreeNode("Excluir Lancto. - "	+ (per.isExcluirLancto() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro8 = new DefaultTreeNode("Excluir Lancto. - "	+ ("Não"), nodeFinanceiro);
						}
						if (per.isReprovarLancto()) {
							TreeNode nodeFinanceiro9 = new DefaultTreeNode("Reprovar Lancto. - "	+ (per.isReprovarLancto() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro9 = new DefaultTreeNode("Reprovar Lancto. - "	+ ("Não"), nodeFinanceiro);
						}
						if (per.isProtocolos()) {
							TreeNode nodeFinanceiro10 = new DefaultTreeNode("Acessar Protocolos - "	+ (per.isProtocolos() == true ? "Sim" : "Não"), nodeFinanceiro);
						}else{
							TreeNode nodeFinanceiro10 = new DefaultTreeNode("Acessar Protocolos - "	+ ("Não"), nodeFinanceiro);
						}
						
						/** Global **/
						TreeNode nodeGlobal = new DefaultTreeNode("Global",	node0);
						if (per.isSysGlobal()) {
							TreeNode nodeGlobal1 = new DefaultTreeNode("Acessar - "	+ (per.isSysGlobal() == true ? "Sim" : "Não"), nodeGlobal);
						}else{
							TreeNode nodeGlobal1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeGlobal);
						}
						if (per.isGlobalAdd()) {
							TreeNode nodeGlobal2 = new DefaultTreeNode("Inserir - "	+ (per.isGlobalAdd() == true ? "Sim" : "Não"), nodeGlobal);
						}
						if (per.isGlobalChange()) {
							TreeNode nodeGlobal3 = new DefaultTreeNode("Alterar - "	+ (per.isGlobalChange() == true ? "Sim" : "Não"), nodeGlobal);
						}
						if (per.isGlobalConsult()) {
							TreeNode nodeGlobal4 = new DefaultTreeNode("Consultar - "	+ (per.isGlobalConsult() == true ? "Sim" : "Não"), nodeGlobal);
						}
						if (per.isGlobalRemove()) {
							TreeNode nodeGlobal5 = new DefaultTreeNode("Remover - "	+ (per.isGlobalRemove() == true ? "Sim" : "Não"), nodeGlobal);
						}
						
						/** Papercut **/
						TreeNode nodePapercut = new DefaultTreeNode("Papercut",	node0);
						if (per.isSysPapercut()) {
							TreeNode nodePapercut1 = new DefaultTreeNode("Acessar - "	+ (per.isSysPapercut() == true ? "Sim" : "Não"), nodePapercut);
						}else{
							TreeNode nodePapercut1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodePapercut);
						}
						if (per.isPapercutAdd()) {
							TreeNode nodePapercut2 = new DefaultTreeNode("Inserir - "	+ (per.isPapercutAdd() == true ? "Sim" : "Não"), nodePapercut);
						}
						if (per.isPapercutChange()) {
							TreeNode nodePapercut3 = new DefaultTreeNode("Alterar - "	+ (per.isPapercutChange() == true ? "Sim" : "Não"), nodePapercut);
						}
						if (per.isPapercutConsult()) {
							TreeNode nodePapercut4 = new DefaultTreeNode("Consultar - "	+ (per.isPapercutConsult() == true ? "Sim" : "Não"), nodePapercut);
						}
						if (per.isPapercutRemove()) {
							TreeNode nodePapercut5 = new DefaultTreeNode("Remover - "	+ (per.isPapercutRemove() == true ? "Sim" : "Não"), nodePapercut);
						}
						
						/** Taxa Inadimplencia**/
						TreeNode nodeTaxaInad = new DefaultTreeNode("Taxa Inadimplência",	node0);
						if (per.isSysTaxaInadimplencia()) {
							TreeNode nodeRS1 = new DefaultTreeNode("Acessar - "	+ (per.isSysTaxaInadimplencia() == true ? "Sim" : "Não"), nodeTaxaInad);
						}else{
							TreeNode nodeRS1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeTaxaInad);
						}
						
						/** Previsao **/
						TreeNode nodePrevisao = new DefaultTreeNode("Previsão Orçamentaria",	node0);
						if (per.isSysPrevisao()) {
							TreeNode nodePrevisao1 = new DefaultTreeNode("Acessar - "	+ (per.isSysPrevisao() == true ? "Sim" : "Não"), nodePrevisao);
						}else{
							TreeNode nodePrevisao1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodePrevisao);
						}
						if (per.isPrevisaoAdd()) {
							TreeNode nodePrevisao2 = new DefaultTreeNode("Inserir - "	+ (per.isPrevisaoAdd() == true ? "Sim" : "Não"), nodePrevisao);
						}
						if (per.isPrevisaoChange()) {
							TreeNode nodePrevisao3 = new DefaultTreeNode("Alterar - "	+ (per.isPrevisaoChange() == true ? "Sim" : "Não"), nodePrevisao);
						}
						if (per.isPrevisaoConsult()) {
							TreeNode nodePrevisao4 = new DefaultTreeNode("Consultar - "	+ (per.isPrevisaoConsult() == true ? "Sim" : "Não"), nodePrevisao);
						}
						if (per.isPrevisaoRemove()) {
							TreeNode nodePrevisao5 = new DefaultTreeNode("Remover - "	+ (per.isPrevisaoRemove() == true ? "Sim" : "Não"), nodePrevisao);
						}
						
						/** Relatorio Semanal **/
						TreeNode nodeRS = new DefaultTreeNode("Relatorio Semanal",	node0);
						if (per.isSysRelatorioSemanal()) {
							TreeNode nodeRS1 = new DefaultTreeNode("Acessar - "	+ (per.isSysRelatorioSemanal() == true ? "Sim" : "Não"), nodeRS);
						}else{
							TreeNode nodeRS1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeRS);
						}
						if (per.isRelatorioSemanalAdd()) {
							TreeNode nodeRS2 = new DefaultTreeNode("Inserir - "	+ (per.isRelatorioSemanalAdd() == true ? "Sim" : "Não"), nodeRS);
						}
						if (per.isRelatorioSemanalConsult()) {
							TreeNode nodeRS3 = new DefaultTreeNode("Consultar - "	+ (per.isRelatorioSemanalConsult() == true ? "Sim" : "Não"), nodeRS);
						}
						
						/** Repasse de Servicos **/
						TreeNode nodeServicos = new DefaultTreeNode("Repasse de Serviços",	node0);
						if (per.isSysServicos()) {
							TreeNode nodeServicos1 = new DefaultTreeNode("Acessar - "	+ (per.isSysServicos() == true ? "Sim" : "Não"), nodeServicos);
						}else{
							TreeNode nodeServicos1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeServicos);
						}
						if (per.isServicosAdd()) {
							TreeNode nodeServicos2 = new DefaultTreeNode("Inserir - "	+ (per.isServicosAdd() == true ? "Sim" : "Não"), nodeServicos);
						}
						if (per.isServicosChange()) {
							TreeNode nodeServicos3 = new DefaultTreeNode("Alterar - "	+ (per.isServicosChange() == true ? "Sim" : "Não"), nodeServicos);
						}
						if (per.isServicosConsult()) {
							TreeNode nodeServicos4 = new DefaultTreeNode("Consultar - "	+ (per.isServicosConsult() == true ? "Sim" : "Não"), nodeServicos);
						}
						if (per.isServicosRemove()) {
							TreeNode nodeServicos5 = new DefaultTreeNode("Remover - "	+ (per.isServicosRemove() == true ? "Sim" : "Não"), nodeServicos);
						}
						
						/** TI **/
						TreeNode nodeTI = new DefaultTreeNode("TI",	node0);
						if (per.isSysTI()) {
							TreeNode nodeTI1 = new DefaultTreeNode("Acessar - "	+ (per.isSysTI() == true ? "Sim" : "Não"), nodeTI);
						}else{
							TreeNode nodeTI1 = new DefaultTreeNode("Acessar - "	+ ("Não"), nodeTI);
						}
						if (per.isTiAdd()) {
							TreeNode nodeTI2 = new DefaultTreeNode("Inserir - "	+ (per.isTiAdd() == true ? "Sim" : "Não"), nodeTI);
						}
						if (per.isTiChange()) {
							TreeNode nodeTI3 = new DefaultTreeNode("Alterar - "	+ (per.isTiChange() == true ? "Sim" : "Não"), nodeTI);
						}
						if (per.isTiConsult()) {
							TreeNode nodeTI4 = new DefaultTreeNode("Consultar - "	+ (per.isTiConsult() == true ? "Sim" : "Não"), nodeTI);
						}
						if (per.isTiRemove()) {
							TreeNode nodeTI5 = new DefaultTreeNode("Remover - "	+ (per.isTiRemove() == true ? "Sim" : "Não"), nodeTI);
						}
					}
				}

			}
		}
		return confirm;
	}

	public void setConfirm(TreeNode confirm) {
		this.confirm = confirm;
	}

	public void setRaiz(TreeNode raiz) {
		this.raiz = raiz;
	}

	public TreeNode[] getSelectedNodes2() {
		return selectedNodes2;
	}

	public void setSelectedNodes2(TreeNode[] selectedNodes2) {
		this.selectedNodes2 = selectedNodes2;
	}

	public boolean isExcluirUsuario() {
		return excluirUsuario;
	}

	public void setExcluirUsuario(boolean excluirUsuario) {
		this.excluirUsuario = excluirUsuario;
	}

	/**
	 * @return the novaSenha
	 */
	public String getNovaSenha() {
		return novaSenha;
	}

	/**
	 * @param novaSenha
	 *            the novaSenha to set
	 */
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	/**
	 * @return the listaDeDepto
	 */
	public List<String> getListaDeDepto() {
		return listaDeDepto;
	}

	/**
	 * @param listaDeDepto the listaDeDepto to set
	 */
	public void setListaDeDepto(List<String> listaDeDepto) {
		this.listaDeDepto = listaDeDepto;
	}

	/**
	 * @return the listaDeGerente
	 */
	public List<String> getListaDeGerente() {
		return listaDeGerente;
	}

	/**
	 * @param listaDeGerente the listaDeGerente to set
	 */
	public void setListaDeGerente(List<String> listaDeGerente) {
		this.listaDeGerente = listaDeGerente;
	}

	/**
	 * @return the gerente
	 */
	public List<intra_grupo_gerente> getGerente() {
		return gerente;
	}

	/**
	 * @param gerente the gerente to set
	 */
	public void setGerente(List<intra_grupo_gerente> gerente) {
		this.gerente = gerente;
	}
	
	/**
	 * @return the trocaPagina
	 */
	public int getTrocaPagina() {
		return trocaPagina;
	}

	/**
	 * @param trocaPagina the trocaPagina to set
	 */
	public void setTrocaPagina(int trocaPagina) {
		this.trocaPagina = trocaPagina;
	}

	/**
	 * @return the permissao
	 */
	public List<intra_grupo_permissao> getPermissao() {
		return permissao;
	}

	/**
	 * @param permissao the permissao to set
	 */
	public void setPermissao(List<intra_grupo_permissao> permissao) {
		this.permissao = permissao;
	}

	/**
	 * @return the listaDePermissao
	 */
	public List<String> getListaDePermissao() {
		return listaDePermissao;
	}

	/**
	 * @param listaDePermissao the listaDePermissao to set
	 */
	public void setListaDePermissao(List<String> listaDePermissao) {
		this.listaDePermissao = listaDePermissao;
	}

	public int getUpdateGerenCodigo() {
		return updateGerenCodigo;
	}

	public void setUpdateGerenCodigo(int updateGerenCodigo) {
		this.updateGerenCodigo = updateGerenCodigo;
	}

	// METODOS
	public void adiciona() throws NoSuchAlgorithmException {
		// Associando os Grupos ao novo Usuário
		this.userDAO = new UsuarioDAO();
		CriptografaSenha cgs = new CriptografaSenha();
		for (String nomeDoGrupo : this.nomesDosGrupos) {
			intra_grupo_depto g = new intra_grupo_depto();
			g.setNome(nomeDoGrupo);
			this.user.getGrupoDepto().add(g);
		}

		if (!this.nomesDosGerentes.isEmpty()) {
			for (intra_grupo_gerente ger : this.gruposGer) {
				for (String codGer : this.nomesDosGerentes) {
					if (ger.getCodigo() == Integer.parseInt(codGer)) {
						intra_grupo_gerente g = new intra_grupo_gerente();
						g = ger;
						this.user.getGrupoGer().add(g);
					}
				}
			}
		}

		if (!this.nomesPermissoes.isEmpty()) {
			for (String per : nomesPermissoes) {
				intra_grupo_permissao p = new intra_grupo_permissao();
				p.setNomeGrupo(per);
				this.user.getGrupoPer().add(p);
			}
		}

		this.user.setSenha(cgs.retornaSenhaCript(String.valueOf(this.user.getSenha())));
		this.user.setNome(StringUtil.trataNomeComposto(this.user.getNome()));
		this.user.setPerfil(String.valueOf(this.nenhum));

		// Salvando o usuário
		if (this.validaEmail(this.user.getEmail())) {
			if(this.userDAO.verificaUsuario(this.user.getEmail())){
				
			this.userDAO.adiciona(this.user, this.sessaoMB);
			this.user = new intra_usuario();
			this.listaUsuarios = null;
			this.gruposDepto = null;
			this.gruposGer = null;
			this.gruposPerm = null;
			this.nomesDosGrupos = null;
			this.nomesDosGerentes = null;
			this.nomesPermissoes = null;
			this.msgAdicinado();
			}else{
				this.msgUsuario();
			}
		} else {
			this.msgEmail();
		}
	}
	
	public void alterarSenha() throws NoSuchAlgorithmException{
		CriptografaSenha cgs = new CriptografaSenha();
		this.userDAO = new UsuarioDAO();
		this.user = this.sessaoMB.getUsuario();
		this.user.setSenha(cgs.retornaSenhaCript(this.usuarioBean.getSenha()));
		this.userDAO.alterarSenha(user, this.sessaoMB);
		this.msgAlterado();
	}

	public void excluirUsuario() {
		this.userDAO = new UsuarioDAO();
		CriptografaSenha cgs = new CriptografaSenha();
		try {
			if (this.excluirUsuario) {

				this.userDAO.excluirUsuario(this.user, this.excluirUsuario, sessaoMB);
				this.user = new intra_usuario();
				this.listaUsuarios = null;
				this.trocaPagina = 0;
				this.msgExclusao();
			} else {
				this.usuarioBean.setEmail(this.user.getEmail());
				this.usuarioBean.setNome(this.user.getNome());
				if (!novaSenha.trim().equals("")) {
					this.usuarioBean.setSenha(cgs
							.retornaSenhaCript(this.novaSenha));
				} else {
					this.usuarioBean.setSenha(this.user.getSenha());
				}
				
				
				this.gruposDepto = new ArrayList<intra_grupo_depto>();
					for(String val :this.listaDeDepto){
						intra_grupo_depto d = new intra_grupo_depto();
						d.setNome(val);
						this.gruposDepto.add(d);
						this.usuarioBean.setGrupoDepto(this.gruposDepto);
					}
				
				this.gerente = new ArrayList<>();
				for(String val : this.listaDeGerente){
					for(intra_grupo_gerente g : this.gruposGer){
						if(Integer.valueOf(val) == g.getCodigo()){
							intra_grupo_gerente geren = new intra_grupo_gerente();
							geren.setCodigo(g.getCodigo());
							geren.setNome(g.getNome());
							geren.setEmail(g.getEmail());
							this.gerente.add(geren);
							this.usuarioBean.setGrupoGer(this.gerente);
						}
					}
				}
				
				this.gruposPerm = new ArrayList<intra_grupo_permissao>();
				for(String val :this.listaDePermissao){
					intra_grupo_permissao d = new intra_grupo_permissao();
					d.setNomeGrupo(val);
					this.gruposPerm.add(d);
					this.usuarioBean.setGrupoPer(this.gruposPerm);
				}
				
			
				this.usuarioBean.setPerfil(this.user.getPerfil());
				this.usuarioBean.setGerente(this.user.getGerente());
				this.userDAO.alterarUsuario(this.usuarioBean, this.sessaoMB);

				this.usuarioBean = new intra_usuario();
				this.listaUsuarios = null;
				this.gruposDepto = null;
				this.gruposGer = null;
				this.gruposPerm = null;
				this.msgAlterado();
				this.trocaPagina = 0;
			}
		} catch (Exception e) {
			this.msgErro();
		}
	}

	public void carregaUsuario() throws ADException {
		LDAPDAO ldap = new LDAPDAO();
		if (this.nomeLDAP != null) {
			if (!this.nomeLDAP.trim().isEmpty()) {
				this.user = ldap.validaUsuarioAD(this.nomeLDAP, "oma.local","ldap://192.1.7.8:389");
				if (this.user == null) {
					this.user = new intra_usuario();
					this.nomeLDAP = "";
				}
			}
		}
	}

	public String nomeGerente(String nome) {
		String nomeGer = "";
		if (nome != null) {
			for (intra_grupo_gerente ger : this.gruposGer) {
				if (ger.getCodigo() == Integer.parseInt(nome)) {
					nomeGer = ger.getNome();
				}
			}
		} else {
			nomeGer = null;
		}
		return nomeGer;
	}

	public String retornaNomePermissao() {
		String nomePerm = "";
		if (this.selectedNodes2 != null) {
			for (TreeNode per : this.selectedNodes2) {
				nomePerm = per.getChildren().get(0).getData().toString();
			}
		} else {
			nomePerm = null;
		}
		return nomePerm;
	}

	public void atualizaGrupos(){
		this.listaDeDepto = new ArrayList<String>();
		for(intra_grupo_depto dep : this.user.getGrupoDepto()){
			this.listaDeDepto.add(dep.getNome().toString());
		}
		this.listaDeGerente = new ArrayList<>();
		for(intra_grupo_gerente g : this.user.getGrupoGer()){
			this.listaDeGerente.add(String.valueOf(g.getCodigo()));
		}
		this.listaDePermissao = new ArrayList<>();
		for(intra_grupo_permissao g : this.user.getGrupoPer()){
			this.listaDePermissao.add(g.getNomeGrupo());
		}
		this.trocaPagina = 1;
	}
	
	public boolean navegar(int valor){
		if(valor == 1){
			return true;
		}
		return false;
	}
	
	public void voltar(){
		DataTable d2 = (DataTable) FacesContext.getCurrentInstance()
				.getViewRoot().findComponent("frmManutUsuario:tblUsuarios");
		d2.setValue(null);
		this.listaUsuarios = null;
		this.filtroUsuarios = null;
				
		RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
		this.trocaPagina = 0;
	}
	
	public void atualizarSistemas(){
		this.userDAO = new UsuarioDAO();
		boolean valida = this.userDAO.replicarContas(this.updateGerenCodigo);
		if(valida){
			this.msgAtualizado();
		}else{
			this.msgErro();
		}
				
	}
	
}