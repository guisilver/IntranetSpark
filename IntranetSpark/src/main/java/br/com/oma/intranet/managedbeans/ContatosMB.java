package br.com.oma.intranet.managedbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.ContatosDAO;
import br.com.oma.intranet.entidades.intra_agd_contatos;
import br.com.oma.intranet.entidades.intra_agd_ddd;
import br.com.oma.intranet.entidades.intra_agd_origem;
import br.com.oma.intranet.entidades.intra_agd_rel_cel;
import br.com.oma.intranet.entidades.intra_agd_rel_tel;
import br.com.oma.intranet.entidades.intra_cidade;
import br.com.oma.intranet.entidades.intra_grupo_depto;
import br.com.oma.intranet.entidades.intra_uf;
import br.com.oma.intranet.entidades.intra_usuario;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.PdfUtil;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "contatosMB")
@ViewScoped
public class ContatosMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8481596953508779279L;

	/* OBJETOS */
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private intra_agd_contatos contato = new intra_agd_contatos();
	private intra_agd_contatos contato2 = new intra_agd_contatos();
	private intra_agd_rel_tel rel_tel = new intra_agd_rel_tel();
	private intra_agd_rel_cel rel_cel = new intra_agd_rel_cel();
	private intra_agd_ddd ddd = new intra_agd_ddd();
	private intra_agd_contatos[] linha_selecionada;
	private intra_agd_rel_cel[] linha_selecionada2;
	private intra_agd_rel_tel[] linha_selecionada3;
	private intra_agd_ddd[] linha_selecionada4;
	private ContatosDAO repositorio;

	/* LISTAS */
	private List<intra_agd_contatos> lista_contatos;
	private List<intra_agd_contatos> lista_filtrada;
	private List<intra_agd_rel_cel> lista_filtrada2;
	private List<intra_agd_rel_tel> lista_filtrada3;
	private List<intra_agd_ddd> lista_filtrada4;
	private List<intra_uf> lista_uf;
	private List<intra_cidade> lista_cidade;
	private List<intra_agd_origem> lista_origem;
	private List<intra_agd_ddd> lista_ddd;
	private List<intra_grupo_depto> lista_deptos;
	private List<intra_agd_rel_cel> lista_celular;
	private List<intra_agd_rel_tel> lista_interurbano;

	/* ATRIBUTOS */
	private int codigo_origem, codigo_origem2, codigo_depto, codigo_depto2, ligacao = 1, num_ddd, condominio;
	private String filtro_nome, uf_nome, numero, solicitante, obs, nome_pdf = "teste.pdf", nome_relatorio;
	private boolean assistente, assistente2, tabela_ligacao;
	private Date inicio, fim;

	/* GET X SET */
	public intra_agd_contatos getContato() {
		return contato;
	}

	public void setContato(intra_agd_contatos contato) {
		this.contato = contato;
	}

	public intra_agd_contatos getContato2() {
		return contato2;
	}

	public void setContato2(intra_agd_contatos contato2) {
		this.contato2 = contato2;
	}

	public intra_agd_rel_tel getRel_tel() {
		return rel_tel;
	}

	public void setRel_tel(intra_agd_rel_tel rel_tel) {
		this.rel_tel = rel_tel;
	}

	public intra_agd_rel_cel getRel_cel() {
		return rel_cel;
	}

	public void setRel_cel(intra_agd_rel_cel rel_cel) {
		this.rel_cel = rel_cel;
	}

	public intra_agd_ddd getDdd() {
		return ddd;
	}

	public void setDdd(intra_agd_ddd ddd) {
		this.ddd = ddd;
	}

	public intra_agd_contatos[] getLinha_selecionada() {
		return linha_selecionada;
	}

	public void setLinha_selecionada(intra_agd_contatos[] linha_selecionada) {
		this.linha_selecionada = linha_selecionada;
	}

	public intra_agd_rel_cel[] getLinha_selecionada2() {
		return linha_selecionada2;
	}

	public void setLinha_selecionada2(intra_agd_rel_cel[] linha_selecionada2) {
		this.linha_selecionada2 = linha_selecionada2;
	}

	public intra_agd_rel_tel[] getLinha_selecionada3() {
		return linha_selecionada3;
	}

	public void setLinha_selecionada3(intra_agd_rel_tel[] linha_selecionada3) {
		this.linha_selecionada3 = linha_selecionada3;
	}

	public intra_agd_ddd[] getLinha_selecionada4() {
		return linha_selecionada4;
	}

	public void setLinha_selecionada4(intra_agd_ddd[] linha_selecionada4) {
		this.linha_selecionada4 = linha_selecionada4;
	}

	public ContatosDAO getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(ContatosDAO repositorio) {
		this.repositorio = repositorio;
	}

	/* PESQUISA E CARREGA LISTA DE CONTATOS DO PABX */
	public List<intra_agd_contatos> getLista_contatos() {
		if (this.lista_contatos == null || this.lista_contatos.isEmpty()) {
			this.repositorio = new ContatosDAO(this.getManager());
			this.lista_contatos = this.repositorio.listaContatos();
		}
		return lista_contatos;
	}

	public void setLista_contatos(List<intra_agd_contatos> lista_contatos) {
		this.lista_contatos = lista_contatos;
	}

	public List<intra_agd_contatos> getLista_filtrada() {
		return lista_filtrada;
	}

	public void setLista_filtrada(List<intra_agd_contatos> lista_filtrada) {
		this.lista_filtrada = lista_filtrada;
	}

	public List<intra_agd_rel_cel> getLista_filtrada2() {
		return lista_filtrada2;
	}

	public void setLista_filtrada2(List<intra_agd_rel_cel> lista_filtrada2) {
		this.lista_filtrada2 = lista_filtrada2;
	}

	public List<intra_agd_rel_tel> getLista_filtrada3() {
		return lista_filtrada3;
	}

	public void setLista_filtrada3(List<intra_agd_rel_tel> lista_filtrada3) {
		this.lista_filtrada3 = lista_filtrada3;
	}

	public List<intra_agd_ddd> getLista_filtrada4() {
		return lista_filtrada4;
	}

	public void setLista_filtrada4(List<intra_agd_ddd> lista_filtrada4) {
		this.lista_filtrada4 = lista_filtrada4;
	}

	public List<intra_uf> getLista_uf() {
		return lista_uf;
	}

	public void setLista_uf(List<intra_uf> lista_uf) {
		this.lista_uf = lista_uf;
	}

	public List<intra_cidade> getLista_cidade() {
		return lista_cidade;
	}

	public void setLista_cidade(List<intra_cidade> lista_cidade) {
		this.lista_cidade = lista_cidade;
	}

	public List<intra_agd_origem> getLista_origem() {
		return lista_origem;
	}

	public void setLista_origem(List<intra_agd_origem> lista_origem) {
		this.lista_origem = lista_origem;
	}

	public List<intra_agd_ddd> getLista_ddd() {
		return lista_ddd;
	}

	public void setLista_ddd(List<intra_agd_ddd> lista_ddd) {
		this.lista_ddd = lista_ddd;
	}

	public List<intra_grupo_depto> getLista_deptos() {
		return lista_deptos;
	}

	public void setLista_deptos(List<intra_grupo_depto> lista_deptos) {
		this.lista_deptos = lista_deptos;
	}

	public List<intra_agd_rel_cel> getLista_celular() {
		if (this.lista_celular == null || this.lista_celular.isEmpty()) {
			this.repositorio = new ContatosDAO(this.getManager());
			this.lista_celular = this.repositorio.pesquisaCelular();
		}
		return lista_celular;
	}

	public void setLista_celular(List<intra_agd_rel_cel> lista_celular) {
		this.lista_celular = lista_celular;
	}

	public List<intra_agd_rel_tel> getLista_interurbano() {
		if (this.lista_interurbano == null || this.lista_interurbano.isEmpty()) {
			this.repositorio = new ContatosDAO(this.getManager());
			this.lista_interurbano = this.repositorio.pesquisaInterurbano();
		}
		return lista_interurbano;
	}

	public void setLista_interurbano(List<intra_agd_rel_tel> lista_interurbano) {
		this.lista_interurbano = lista_interurbano;
	}

	public int getCodigo_origem() {
		return codigo_origem;
	}

	public void setCodigo_origem(int codigo_origem) {
		this.codigo_origem = codigo_origem;
	}

	public int getCodigo_origem2() {
		return codigo_origem2;
	}

	public void setCodigo_origem2(int codigo_origem2) {
		this.codigo_origem2 = codigo_origem2;
	}

	public int getCodigo_depto() {
		return codigo_depto;
	}

	public void setCodigo_depto(int codigo_depto) {
		this.codigo_depto = codigo_depto;
	}

	public int getCodigo_depto2() {
		return codigo_depto2;
	}

	public void setCodigo_depto2(int codigo_depto2) {
		this.codigo_depto2 = codigo_depto2;
	}

	public int getLigacao() {
		return ligacao;
	}

	public void setLigacao(int ligacao) {
		this.ligacao = ligacao;
	}

	public int getNum_ddd() {
		return num_ddd;
	}

	public void setNum_ddd(int num_ddd) {
		this.num_ddd = num_ddd;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public String getFiltro_nome() {
		return filtro_nome;
	}

	public void setFiltro_nome(String filtro_nome) {
		this.filtro_nome = filtro_nome;
	}

	public String getUf_nome() {
		return uf_nome;
	}

	public void setUf_nome(String uf_nome) {
		this.uf_nome = uf_nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getNome_pdf() {
		return nome_pdf;
	}

	public void setNome_pdf(String nome_pdf) {
		this.nome_pdf = nome_pdf;
	}

	public String getNome_relatorio() {
		return nome_relatorio;
	}

	public void setNome_relatorio(String nome_relatorio) {
		this.nome_relatorio = nome_relatorio;
	}

	public boolean isAssistente() {
		return assistente;
	}

	public void setAssistente(boolean assistente) {
		this.assistente = assistente;
	}

	public boolean isAssistente2() {
		return assistente2;
	}

	public void setAssistente2(boolean assistente2) {
		this.assistente2 = assistente2;
	}

	public boolean isTabela_ligacao() {
		return tabela_ligacao;
	}

	public void setTabela_ligacao(boolean tabela_ligacao) {
		this.tabela_ligacao = tabela_ligacao;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	/* MÉTODOS */

	/* RECUPERA ENTITY MANAGER */
	public EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

	/* LIMPA OS DADOS ARMAZENADOS NO MANAGED BEAN */
	public void limpar() {
		this.contato = new intra_agd_contatos();
		this.contato2 = new intra_agd_contatos();
		this.rel_cel = new intra_agd_rel_cel();
		this.rel_tel = new intra_agd_rel_tel();
		this.ddd = new intra_agd_ddd();
		this.linha_selecionada = new intra_agd_contatos[] { null };
		this.lista_contatos = null;
		this.lista_filtrada = null;
		this.lista_filtrada2 = null;
		this.lista_filtrada3 = null;
		this.lista_filtrada4 = null;
		this.lista_celular = null;
		this.lista_interurbano = null;
		this.codigo_origem = 0;
		this.codigo_origem2 = 0;
		this.codigo_depto = 0;
		this.codigo_depto2 = 0;
		this.num_ddd = 0;
		this.ligacao = 1;
		this.filtro_nome = "";
		this.uf_nome = "";
		this.numero = "";
		this.solicitante = "";
		this.obs = "";
	}

	/* GERA UM NOME ALEATÓRIO */
	public String getRandomName() {
		int i = (int) (Math.random() * 100000);
		return String.valueOf(i);
	}

	/*************** CREATE ***************/

	/* CADASTRO NOVO CONTATO */
	public void adicionar() {
		if (this.contato.getNome() != null && this.contato.getNome() != "") {
			try {
				this.contato.setCod_gerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
				this.contato.setAssistente(this.assistente);
				this.repositorio = new ContatosDAO(this.getManager());
				// this.contato.setOrigem(this.repositorio.pesquisaOrigem(this.codigo_origem));
				// this.contato.setDepartamento(this.repositorio.pesquisaDeptos(this.codigo_depto));

				if (this.contato.isAssistente()) {
					this.contato.setEncarregado(false);
				}
				/*
				 * if (this.contato.getFk_depto() != null &&
				 * !this.contato.getFk_depto().getDepartamento().equals(
				 * "1 - Sala de Reunião") &&
				 * !this.contato.getFk_depto().getDepartamento().equals(
				 * "Auditório")) { intraad ad = new intraad();
				 * ad.setNome(this.contato.getNome());
				 * ad.setCod_assist(this.contato.getCod_gerente());
				 * ad.setCod_geren(this.contato.getCod_gerente());
				 * ad.setGrupo(this.contato.getFk_depto().getDepartamento());
				 * ad.setEmail_assist(this.contato.getEmail_oma());
				 * ad.setNome(this.contato.getNome());
				 * this.contato.setFk_intraad(this.repositorio.adicionaAd(ad));
				 * 
				 * }
				 */
				this.repositorio.adicionarContato(this.contato);
				this.limpar();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Adicionado com sucesso!", ""));
				RequestContext.getCurrentInstance().execute("dialogContato.hide();");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocorreu um erro!", "Contate o administrador."));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Insira um nome para o contato!", ""));
		}
	}

	/* CADASTRA NOVA LIGAÇÂO */
	public void novaLigacao() {
		if (this.numero != null && this.numero != "") {
			if (this.solicitante != null && this.solicitante != "") {
				this.repositorio = new ContatosDAO(this.getManager());
				switch (this.ligacao) {
				case 1:
					this.rel_cel.setCelular(this.numero);
					this.rel_cel.setCondominio(this.condominio);
					this.rel_cel.setSolicitante(this.solicitante);
					this.rel_cel.setObs(this.obs);
					this.rel_cel.setData(new Date());
					this.repositorio.adicionaCelular(this.rel_cel);
					break;
				case 2:
					this.rel_tel.setNumero(this.numero);
					this.rel_tel.setCondominio(this.condominio);
					this.rel_tel.setSolicitante(this.solicitante);
					this.rel_tel.setObs(this.obs);
					this.rel_tel.setDdd(this.num_ddd);
					this.rel_tel.setData(new Date());
					this.repositorio.adicionaResidencial(this.rel_tel);
					break;
				default:
					break;
				}
				RequestContext.getCurrentInstance().execute("dialogLigacao.hide();");
				this.limpar();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Insira um solicitante para a ligação!", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Insira um número para a ligação!", ""));
		}
	}

	/* ADICIONA NOVO DDD */
	public void adicionaDDD() {
		this.repositorio = new ContatosDAO(this.getManager());
		this.repositorio.adicionaDDD(this.ddd);
		this.limpar();
	}

	/*************** READ ***************/

	/* PESQUISA NOME IGNORANDO ACENTOS */
	public void filtraNome() {
		this.lista_filtrada = null;
		this.repositorio = new ContatosDAO(this.getManager());
		this.lista_contatos = this.repositorio.filtraNome(this.filtro_nome);
		if (this.lista_contatos.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum registro encontrado com esse nome!", ""));
		}
	}

	/* PESQUISA E CARREGA LISTA DE ORIGEM */
	public void listaOrigem() {
		if (this.lista_origem == null || this.lista_origem.isEmpty()) {
			this.repositorio = new ContatosDAO(this.getManager());
			this.lista_origem = this.repositorio.listaOrigem();
		}
	}

	/* PESQUISA E CARREGA LISTA DE ESTADOS */
	public void listaUF() {
		this.repositorio = new ContatosDAO(this.getManager());
		this.lista_uf = this.repositorio.listaUF();
	}

	/* PESQUISA E CARREGA LISTA DE DDD */
	public void listaDDD() {
		this.repositorio = new ContatosDAO(this.getManager());
		this.lista_ddd = this.repositorio.listaDDD();
	}

	/* PESQUISA E CARREGA LISTA DE DEPTOS */
	public void listaDeptos() {
		this.repositorio = new ContatosDAO(this.getManager());
		this.lista_deptos = this.repositorio.listaDeptos();
	}

	/* PESQUISA E CARREGA CONTATO SELECIONADO NA TABELA */
	public void pesquisaContato() {
		if (this.linha_selecionada != null && this.linha_selecionada.length > 0) {
			if (this.linha_selecionada.length == 1) {
				this.repositorio = new ContatosDAO(this.getManager());
				List<intra_agd_contatos> lista_contatos = this.repositorio
						.pesquisaContato(this.linha_selecionada[0].getCodigo());
				for (intra_agd_contatos aux : lista_contatos) {
					this.contato2 = new intra_agd_contatos();
					this.contato2 = aux;
				}
				/*
				 * if (this.contato2.getFk_origem() != null) {
				 * this.codigo_origem2 = this.contato2.getFk_origem()
				 * .getCodigo(); } else { this.codigo_origem2 = 0; }
				 * 
				 * if (this.contato2.getFk_depto() != null) { this.codigo_depto2
				 * = this.contato2.getFk_depto() .getCodigo(); } else {
				 * this.codigo_depto2 = 0; }
				 */
				this.assistente2 = this.contato2.isAssistente();
				// this.cod_gerente2 = this.contato2.getCod_gerente();
				RequestContext.getCurrentInstance().execute("dialogAltera.show();");
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Só é possível alterar um contato por vez!", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione um contato para alterar!", ""));
		}
	}

	/* PESQUISA ESTADO PERTINENTE AO DDD SELECIONADO */
	public void pesquisaUF() {
		this.repositorio = new ContatosDAO(this.getManager());
		this.uf_nome = this.repositorio.pesquisaUF(this.num_ddd);
	}

	/*************** UPDATE ***************/

	/* SALVA ALTERAÇÔES DE UM CONTATO */
	public void salvar() {
		if (this.contato2.getNome() != null && this.contato2.getNome() != "") {
			try {
				this.contato2.setCod_gerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
				this.contato.setAssistente(this.assistente2);
				this.repositorio = new ContatosDAO(this.getManager());

				// this.contato2.setFk_origem(this.repositorio.pesquisaOrigem(this.codigo_origem2));
				// this.contato2.setFk_depto(this.repositorio.pesquisaDeptos(this.codigo_depto2));

				if (this.contato2.isAssistente()) {
					this.contato2.setEncarregado(false);
				}

				/*
				 * //Altera codigo de gerente na tabela "intraad" if
				 * (this.contato2.getFk_depto() != null &&
				 * !this.contato2.getFk_depto().getDepartamento() .equals(
				 * "1 - Sala de Reunião") &&
				 * !this.contato2.getFk_depto().getDepartamento()
				 * .equals("Auditório")) { intraad ad2 = new intraad();
				 * ad2.setCodigo(this.contato2.getFk_intraad());
				 * ad2.setNome(this.contato2.getNome());
				 * ad2.setCod_assist(this.contato2.getCod_gerente());
				 * ad2.setCod_geren(this.contato.getCod_gerente());
				 * ad2.setGrupo(this.contato2.getFk_depto().getDepartamento());
				 * ad2.setEmail_assist(this.contato.getEmail_oma());
				 * ad2.setNome(this.contato2.getNome());
				 * this.repositorio.alteraAd(ad2); }
				 */
				this.repositorio.atualizarContato(this.contato2);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atualizado com sucesso!", ""));
				RequestContext.getCurrentInstance().execute("dialogAltera.hide();");
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocorreu um erro!", "Contate o administrador."));
			}
			this.limpar();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Insira um nome para o contato!", ""));
		}
		this.linha_selecionada = new intra_agd_contatos[] { null };
	}

	/*************** DELETE ***************/

	/* EXCLUI UM CONTATO */
	public void excluiContato() {
		if (this.linha_selecionada != null && this.linha_selecionada.length > 0) {
			this.repositorio = new ContatosDAO(this.getManager());
			boolean sucesso = this.repositorio.excluiContato(this.linha_selecionada);
			if (sucesso) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Ocorreu um erro ao excluir!", "Contate o administrador."));
			}
			this.limpar();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione um contato para excluir!", ""));
		}
		this.linha_selecionada = new intra_agd_contatos[] { null };
	}

	public void excluiLigacao() {
		this.repositorio = new ContatosDAO(this.getManager());
		if (!this.tabela_ligacao) {
			if (this.linha_selecionada2 != null) {
				this.repositorio.excluiCelular(this.linha_selecionada2);
			}
		} else {
			if (this.linha_selecionada3 != null) {
				this.repositorio.excluiResidencial(this.linha_selecionada3);
			}
		}
		this.limpar();
	}

	public void excluiDDD() {
		this.repositorio = new ContatosDAO(this.getManager());
		if (this.linha_selecionada4 != null) {
			this.repositorio.excluiDDD(this.linha_selecionada4);
		}
		this.limpar();
	}

	/*************** RELATÓRIOS ***************/

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void geraRamais() throws IOException, SQLException {
		try {
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator;

			String caminho_imagem = servletContext.getRealPath("") + File.separator + "resources" + File.separator
					+ "imagens" + File.separator;
			HashMap parametros = new HashMap();
			parametros.put("SUBREPORT_DIR", caminho_relatorio);
			byte[] relatorio = rju.geraRel2(parametros, "departamento", "Ramais", 1);
			this.nome_relatorio = this.exportarRelatorio(relatorio);
			RequestContext.getCurrentInstance().execute("dialogRelatorio.show();");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "" + e + "", ""));
		}

	}

	/* GERA RELATÓRIO DE LIGAÇÕES COM JASPER REPORTS VIA BEAN COLLECTION */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public void geraControleLigacoes() throws JRException, IOException {
		/*
		 * JRExporter tipo_exportado = new JRPdfExporter(); FacesContext context
		 * = FacesContext.getCurrentInstance(); File gerado = null; String
		 * extensao_exportada = "pdf";
		 */
		String nome_saida = "";

		if (this.inicio != null) {
			if (this.fim != null) {
				this.repositorio = new ContatosDAO(this.getManager());
				JRBeanCollectionDataSource beanCollectionDataSource = null;
				String reportPath = null;
				JasperPrint jasperPrint = new JasperPrint();
				switch (this.ligacao) {
				case 1:
					List<intra_agd_rel_cel> lista_rel_cel = this.repositorio.relatorioCel(this.inicio, this.fim);
					beanCollectionDataSource = new JRBeanCollectionDataSource(lista_rel_cel);
					reportPath = FacesContext.getCurrentInstance().getExternalContext()
							.getRealPath("/relatorios/ControleCelular.jasper");
					nome_saida = "ControleCelular";
					break;
				case 2:
					List<intra_agd_rel_tel> lista_rel_tel = this.repositorio.relatorioTel(this.inicio, this.fim);
					beanCollectionDataSource = new JRBeanCollectionDataSource(lista_rel_tel);
					reportPath = FacesContext.getCurrentInstance().getExternalContext()
							.getRealPath("/relatorios/ControleInterurbano.jasper");
					nome_saida = "ControleInterurbano";
					break;
				}

				jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
				/*
				 * String caminho_arq_rel = context.getExternalContext()
				 * .getRealPath("relatorios") + File.separator + nome_saida +
				 * "." + extensao_exportada; gerado = new
				 * java.io.File(caminho_arq_rel);
				 * tipo_exportado.setParameter(JRExporterParameter.JASPER_PRINT,
				 * jasperPrint);
				 * tipo_exportado.setParameter(JRExporterParameter.OUTPUT_FILE,
				 * gerado); tipo_exportado.exportReport();
				 */

				/*
				 * InputStream conteudo = new FileInputStream(gerado);
				 * StreamedContent retorno = new
				 * DefaultStreamedContent(conteudo, "application/" +
				 * extensao_exportada, nome_saida + "." + extensao_exportada);
				 */

				byte[] relatorio = JasperExportManager.exportReportToPdf(jasperPrint);
				this.nome_relatorio = this.exportarRelatorio(relatorio);
				RequestContext.getCurrentInstance().execute("dialogRelatorio.show();");

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Insira uma data de fim para o relatório!", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Insira uma data de início para o relatório!", ""));
		}
	}

	/**
	 * GERA RELATÓRIO DE LIGAÇÕES COM JASPER REPORTS VIA BEAN COLLECTION
	 */
	/* @SuppressWarnings({ "unchecked", "rawtypes" }) */
	/*
	 * public byte[] geraControleLigacoes() throws JRException, IOException { if
	 * (this.inicio != null) { if (this.fim != null) { this.repositorio = new
	 * ContatosRepositorio(this.getManager()); JRBeanCollectionDataSource
	 * beanCollectionDataSource = null; String reportPath = null; JasperPrint
	 * jasperPrint = new JasperPrint(); switch (this.ligacao) { case 1:
	 * List<intra_agd_rel_cel> lista_rel_cel = this.repositorio
	 * .relatorioCel(this.inicio, this.fim); beanCollectionDataSource = new
	 * JRBeanCollectionDataSource( lista_rel_cel); reportPath =
	 * FacesContext.getCurrentInstance() .getExternalContext()
	 * .getRealPath("/relatorios/ControleCelular.jasper"); break; case 2:
	 * List<intra_agd_rel_tel> lista_rel_tel = this.repositorio
	 * .relatorioTel(this.inicio, this.fim); beanCollectionDataSource = new
	 * JRBeanCollectionDataSource( lista_rel_tel); reportPath = FacesContext
	 * .getCurrentInstance() .getExternalContext() .getRealPath(
	 * "/relatorios/ControleInterurbano.jasper"); break; } jasperPrint =
	 * JasperFillManager.fillReport(reportPath,new HashMap(),
	 * beanCollectionDataSource); return (byte[])
	 * JasperExportManager.exportReportToPdf(jasperPrint); } else { FacesContext
	 * .getCurrentInstance() .addMessage( null, new FacesMessage(
	 * FacesMessage.SEVERITY_WARN, "Insira uma data de fim para o relatório!",
	 * "")); return null; } } else {
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_WARN,
	 * "Insira uma data de início para o relatório!", "")); return null; } }
	 */

	/*
	 * @SuppressWarnings({ "rawtypes", "unused" }) public byte[] geraRamais()
	 * throws IOException, SQLException { RelatorioJasperUtil rju = new
	 * RelatorioJasperUtil(); Map parametros = new HashMap(); byte[] arquivo =
	 * null; return rju.geraRel2(parametros, "departamento", "departamento", 1);
	 * }
	 */

	/**
	 * GERA RELATÓRIO DE RAMAIS COM JASPER REPORTS VIA CONEXÃO JDBC
	 */
	/*
	 * @SuppressWarnings("rawtypes") public void geraRamaisJDBC() throws
	 * IOException, SQLException { RelatorioJasperUtil rju = new
	 * RelatorioJasperUtil(); Map parametros = new HashMap(); byte[] arquivo =
	 * rju.geraRel2(parametros, "departamento", "departamento", 1);
	 * this.nome_pdf = exportarRelatorio2(arquivo); }
	 */

	/* EXPORTA RELATÓRIO BYTE PARA UM ARQUIVO PDF */
	public String exportarRelatorio(byte[] relatorio) throws IOException {
		String nome_relatorio = this.getRandomName();
		String caminho_relatorio = "";
		if (relatorio != null) {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator
					+ nome_relatorio + ".pdf";
			try {
				FileOutputStream output;
				output = new FileOutputStream(new File(caminho_relatorio));
				output.write(relatorio);
				output.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			caminho_relatorio = "vazio";
		}
		return nome_relatorio;
	}

	/**
	 * EXPORTA RELATÓRIO BYTE PARA UM ARQUIVO PDF
	 */
	/*
	 * public String exportarRelatorio2(byte[] arquivo) throws IOException {
	 * String nome_relatorio = this.getRandomName(); String caminho_relatorio =
	 * ""; if (arquivo != null) { ServletContext servletContext =
	 * (ServletContext) FacesContext
	 * .getCurrentInstance().getExternalContext().getContext();
	 * caminho_relatorio = servletContext.getRealPath("") + File.separator +
	 * "relatorios" + File.separator + nome_relatorio + ".pdf"; try {
	 * FileOutputStream output; output = new FileOutputStream(new
	 * File(caminho_relatorio)); output.write(arquivo); output.close(); } catch
	 * (Exception e) { System.out.println(e); } } else { caminho_relatorio =
	 * "vazio"; } return nome_relatorio; }
	 */

	/*
	 * public void excluiRelatorio() { ServletContext servletContext =
	 * (ServletContext) FacesContext
	 * .getCurrentInstance().getExternalContext().getContext(); String
	 * caminho_relatorio = servletContext.getRealPath("") + File.separator +
	 * "relatorios" + File.separator + this.nome_pdf + ".pdf"; File file = new
	 * File(caminho_relatorio); if (file.exists()) { file.delete(); } }
	 */

	/*
	 * GERA RAMAIS E ABRE EM NOVA PÁGINA
	 * 
	 * @SuppressWarnings({ "rawtypes", "unused" }) public void geraRamais()
	 * throws IOException, SQLException { RelatorioJasperUtil rju = new
	 * RelatorioJasperUtil(); Map parametros = new HashMap(); byte[] arquivo =
	 * null; this.relatorio_pdf = rju.geraRel2(parametros, "departamento",
	 * "departamento", 1); HttpServletResponse res = (HttpServletResponse)
	 * FacesContext .getCurrentInstance().getExternalContext().getResponse();
	 * res.setContentType("application/pdf");
	 * res.setHeader("Content-disposition", "inline;filename=Ramais.pdf");
	 * res.getOutputStream().write(this.relatorio_pdf);
	 * res.getCharacterEncoding();
	 * FacesContext.getCurrentInstance().responseComplete(); }
	 */

	/* GERA RELATÓRIO DE RAMAIS ATRAVÉS DA API 'ITEXT' */
	public void geraRamaisIText() {
		PdfUtil relatorio = new PdfUtil();
		this.setNome_pdf(relatorio.geraRamais(this.getManager()));
	}
}