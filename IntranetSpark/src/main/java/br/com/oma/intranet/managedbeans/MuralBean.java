package br.com.oma.intranet.managedbeans;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.oma.intranet.dao.MuralDAO;
import br.com.oma.intranet.entidades.intramural;

@ViewScoped
@ManagedBean(name = "MuralBean")
public class MuralBean {

	/* DEPEND�NCIAS */
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	/* OBJETOS */
	private intramural mr = new intramural(), mrMkt = new intramural(),
			mrRH = new intramural();
	private MuralDAO repositorio;
	private intramural linha_selecionada;
	/* LISTAS */
	private List<intramural> lista_mural;
	private List<intramural> lista_mkt;
	private List<intramural> lista_rh;

	/* ATRIBUTOS */
	private String conteudo;
	private String noticia;
	private String nome_setor;
	private int setor;

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public intramural getMr() {
		return mr;
	}

	public void setMr(intramural mr) {
		this.mr = mr;
	}

	public intramural getMrMkt() {
		return mrMkt;
	}

	public void setMrMkt(intramural mrMkt) {
		this.mrMkt = mrMkt;
	}

	public intramural getMrRH() {
		return mrRH;
	}

	public void setMrRH(intramural mrRH) {
		this.mrRH = mrRH;
	}

	public List<intramural> getLista_mural() {
		return lista_mural;
	}

	public void setLista_mural(List<intramural> lista_mural) {
		this.lista_mural = lista_mural;
	}

	public List<intramural> getLista_mkt() {
		return lista_mkt;
	}

	public void setLista_mkt(List<intramural> lista_mkt) {
		this.lista_mkt = lista_mkt;
	}

	public List<intramural> getLista_rh() {
		return lista_rh;
	}

	public void setLista_rh(List<intramural> lista_rh) {
		this.lista_rh = lista_rh;
	}

	public intramural getLinha_selecionada() {
		return linha_selecionada;
	}

	public void setLinha_selecionada(intramural linha_selecionada) {
		this.linha_selecionada = linha_selecionada;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getNoticia() {
		return noticia;
	}

	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}

	public String getNome_setor() {
		return nome_setor;
	}

	public void setNome_setor(String nome_setor) {
		this.nome_setor = nome_setor;
	}

	public int getSetor() {
		return setor;
	}

	public void setSetor(int setor) {
		this.setor = setor;
	}

	/*----------------------------------------------CREATE-----------------------------------------------*/

	/**
	 * SALVA UMA NOVA NOT�CIA EDITADA NA P�GINA MURAL
	 */
	public void salvar() {
		if (this.mr.getTitulo() != null && this.mr.getTitulo() != "") {
			if (!this.conteudo.contains("<style>")
					&& !this.conteudo.contains(".css")) {
				this.mr.setStatus(true);

				this.setor = 1;

				if (this.setor != 0) {
					if (this.conteudo != null && this.conteudo != "") {
						this.repositorio = new MuralDAO(
								this.getManager());
						this.mr.setData(new Date());
						switch (this.setor) {
						case 1:
							this.mr.setMkt(this.conteudo);
							break;
						case 2:
							this.mr.setRh(this.conteudo);
							break;
						default:
							break;
						}
						this.repositorio.salvarMural(this.mr);
						this.mr = new intramural();
						this.conteudo = "";
						this.limpar();
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Salvo com sucesso!", ""));
					} else {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"Insira um texto!", ""));
					}
				} else {
					RequestContext.getCurrentInstance().execute(
							"recebeValor();");
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Selecione um setor!", ""));
				}
			} else {
				RequestContext.getCurrentInstance().execute("recebeValor();");
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_WARN,
										"N�o � permitido usar css global para este atributo!",
										"Insira somente css dentro dos elementos"));
			}
		} else {
			RequestContext.getCurrentInstance().execute("recebeValor();");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Insira um t�tulo!", ""));
		}
	}

	/*----------------------------------------------READ-----------------------------------------------*/

	/**
	 * PESQUISA AS �LTIMAS NOT�CIAS PARA EXIBIR NA HOME
	 */
	public void pesquisaMural() {
		this.repositorio = new MuralDAO(this.getManager());
		this.lista_mkt = this.repositorio.pesquisaMkt();
		this.lista_rh = this.repositorio.pesquisaRh();

		if (this.lista_mkt != null && this.lista_mkt.size() > 0) {
			this.mrMkt = this.lista_mkt.get(this.lista_mkt.size() - 1);
		}
		if (this.lista_rh != null && this.lista_rh.size() > 0) {
			this.mrRH = this.lista_rh.get(this.lista_rh.size() - 1);
		}

		for (intramural aux : this.lista_mkt) {
			this.mr.setMkt(aux.getMkt());
		}

		for (intramural aux2 : this.lista_rh) {
			this.mr.setRh(aux2.getRh());
		}

		if (this.mr.getMkt() == null) {
			this.mr.setMkt("Sem not�cias");
		}
		if (this.mr.getRh() == null) {
			this.mr.setRh("Sem not�cias");
		}
	}

	/**
	 * PESQUISA AS NOT�CIAS DE ACORDO COM O PAR�METRO DO SETOR PARA EXIBIR NA
	 * P�GINA DE NOT�CIAS
	 */
	public void pesquisaNoticias() {
		Map<String, String> mapa = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		if (mapa.containsKey("setor")) {
			this.setor = Integer.parseInt(mapa.get("setor"));
		}
		this.repositorio = new MuralDAO(this.getManager());
		switch (this.setor) {
		case 1:
			this.lista_mural = this.repositorio.pesquisaNoticiasMkt2();
			this.nome_setor = "Not�cias Marketing";
			break;
		case 2:
			this.lista_mural = this.repositorio.pesquisaNoticiasRh2();
			this.nome_setor = "Not�cias RH";
			break;
		default:
			break;
		}
	}

	/**
	 * PESQUISA AS NOT�CIAS DE ACORDO COM O DEPARTAMENTO PARA EXIBIR NA P�GINA
	 * DE EDI��O DO MURAL
	 */
	public void pesquisaNoticias2() {
		this.setor = 1;
		this.repositorio = new MuralDAO(this.getManager());
		switch (this.setor) {
		case 1:
			this.lista_mural = this.repositorio.pesquisaNoticiasMkt2();
			this.nome_setor = "Not�cias Marketing";
			break;
		case 2:
			this.lista_mural = this.repositorio.pesquisaNoticiasRh2();
			this.nome_setor = "Not�cias RH";
			break;
		default:
			break;
		}
	}

	/**
	 * SELECIONA UMA NOT�CIA NO DATATABLE E EXIBE NO PAINEL DE NOT�CIAS
	 */
	public void selecionaNoticia() {
		switch (this.setor) {
		case 1:
			this.noticia = this.linha_selecionada.getMkt();
			break;
		case 2:
			this.noticia = this.linha_selecionada.getRh();
			break;
		default:
			break;
		}
	}

	/**
	 * SELECIONA UMA NOT�CIA NO DATATABLE E EXIBE NO PAINEL DE EDI��O DE
	 * NOT�CIAS
	 */
	public void selecionaNoticiaEdit() {
		this.setor = 1;

		switch (this.setor) {
		case 1:
			this.conteudo = this.linha_selecionada.getMkt();
			break;
		case 2:
			this.conteudo = this.linha_selecionada.getRh();
			break;
		default:
			break;
		}
	}

	/*----------------------------------------------UPDATE-----------------------------------------------*/

	/**
	 * MUDA O STATUS DA NOT�CIA
	 */
	public void mudaStatusNoticia() {
		if (this.linha_selecionada != null) {
			this.mr = this.linha_selecionada;
			this.mr.setStatus(!this.mr.isStatus());
			this.repositorio = new MuralDAO(this.getManager());
			this.repositorio.mudaStatusNoticia(this.mr);
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Selecione uma not�cia para alterar!", ""));
		}
	}

	/*----------------------------------------------DELETE-----------------------------------------------*/

	/**
	 * EXCLUI A NOT�CIA SELECIONADA NO DATATABLE
	 */
	public void excluiNoticia() {
		if (this.linha_selecionada != null) {
			this.repositorio = new MuralDAO(this.getManager());
			this.repositorio.excluiNoticia(this.linha_selecionada.getCodigo());
			this.conteudo = "";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Exclu�do com sucesso!", ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Selecione uma not�cia para deletar!", ""));
		}
	}

	/*----------------------------------------------M�TODOS-----------------------------------------------*/

	public void selecionaUltima() {
		if (this.linha_selecionada == null) {
			if (this.lista_mural != null) {
				if (this.lista_mural.size() > 0) {
					this.linha_selecionada = this.lista_mural.get(0);
					this.selecionaNoticia();
				}
			}
		}
	}

	/**
	 * LIMPA OS DADOS DA CLASSE ARMAZENADOS NA SESS�O
	 */
	public void limpar() {
		this.mr = new intramural();
		this.linha_selecionada = new intramural();
		this.lista_mural = new ArrayList<>();
		this.conteudo = "";
		this.noticia = "Selecione uma not�cia na tabela ao lado.";
		this.nome_setor = "";
		this.setor = 0;
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		while (event != null) {
			ServletContext servletContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String caminho_arquivo = servletContext.getRealPath("")
					+ File.separator + "resources" + File.separator + "uploads"
					+ File.separator + event.getFile().getFileName() + "";

			HttpServletRequest req = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String caminho_arquivo2 = "";
			String url = req.getRequestURL().toString();
			if (url.contains("local")) {
				caminho_arquivo2 = "http://localhost:8080" + File.separator
						+ "Intranet" + File.separator + "resources"
						+ File.separator + "uploads" + File.separator
						+ event.getFile().getFileName() + "";
			}

			if (url.contains("10.1.1.20") || url.contains("omaonline")) {
				caminho_arquivo2 = "http://10.1.1.20:8080" + File.separator
						+ "Intranet" + File.separator + "resources"
						+ File.separator + "uploads" + File.separator
						+ event.getFile().getFileName() + "";
			}

			File file = new File(caminho_arquivo);
			while (file.exists()) {
				file.delete();
			}

			FileImageOutputStream output;
			byte[] arquivo = event.getFile().getContents();
			try {
				output = new FileImageOutputStream(new File(caminho_arquivo));
				output.write(arquivo);
				output.flush();
				output.close();
			} catch (Exception e) {
				System.out.println(e);
			}

			this.conteudo = this.conteudo + "<p><img src='" + caminho_arquivo2
					+ "' /></p>";
			RequestContext.getCurrentInstance().execute("recebeValor();");
			/* this.conteudo = ""; */
			event = null;
		}
	}

	public String getRandomName() {
		int i = (int) (Math.random() * 100000);
		return String.valueOf(i);
	}

	/*----------------------------------------------ENTITY-MANAGER-----------------------------------------------*/

	/**
	 * RECUPERA A CONEX�O COM BANCO DE DADOS ARMAZENADA NA SESS�O
	 */
	public EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}
}
