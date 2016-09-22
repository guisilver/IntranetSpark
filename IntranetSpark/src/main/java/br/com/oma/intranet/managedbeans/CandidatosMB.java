package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;

import br.com.oma.intranet.dao.CandidatosDAO;
import br.com.oma.intranet.dao.VagasDAO;
import br.com.oma.intranet.entidades.intra_cadastro_recepcao;
import br.com.oma.intranet.entidades.intra_cadastro_recepcao_foto;
import br.com.oma.intranet.entidades.intra_candidato;
import br.com.oma.intranet.entidades.intra_candidato_contatos;
import br.com.oma.intranet.entidades.intra_candidato_controle_encaminhamento;
import br.com.oma.intranet.entidades.intra_candidato_dependentes;
import br.com.oma.intranet.entidades.intra_candidato_foto;
import br.com.oma.intranet.entidades.intra_candidato_historico_profissional;
import br.com.oma.intranet.entidades.intra_candidato_prontuario;
import br.com.oma.intranet.entidades.intra_candidato_vaga;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import br.com.oma.omaonline.util.CameraUtil;

@ViewScoped
@ManagedBean
public class CandidatosMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8552484332192439720L;
	private intra_candidato candidato = new intra_candidato();
	private intra_candidato_dependentes dependente = new intra_candidato_dependentes();
	private intra_candidato_historico_profissional historicoProfissional = new intra_candidato_historico_profissional();
	private List<intra_candidato_dependentes> listaDependentes = new ArrayList<>();
	private List<intra_candidato_historico_profissional> listaHistorico = new ArrayList<>();
	private List<intra_candidato> listaCandidatos, listaPreCadastro, filtroPreCadastro;
	private List<intra_condominios> listaCondominios;
	private List<String> listaCidades, listaCargoPretendido;
	private String pretensaoSalarial;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	// PESQUISA
	private String cidadeSelecionada;
	private String palavraChave;
	private int idadeInicio, idadeFim;
	private double salarioInicio, salarioFim;
	private String candidatoCPF;
	private String categoriaCNH[] = new String[6];
	private String periodoTrabalho[] = new String[6];

	// DETALHES CANDIDATO
	private intra_candidato candidatoSelecionado;
	private intra_candidato_foto candidatoFoto;

	private intra_cadastro_recepcao cadastro = new intra_cadastro_recepcao();
	private intra_cadastro_recepcao_foto cadastroFoto = new intra_cadastro_recepcao_foto();
	private CameraUtil camera = new CameraUtil();
	private String nomeImagem, pastaImagem;
	private int codigoSelecionado;

	private String[] filtroEntrevista, filtroLevantamento, filtroContato, filtroContratado;
	private String filtroHistorico;

	// ARQUIVO DO LEVANTAMENTO CADASTRAL
	private intra_candidato_prontuario levantamentoCadastral;
	private intra_candidato_prontuario curriculo;
	private intra_candidato_contatos contato = new intra_candidato_contatos();
	private List<intra_candidato_prontuario> listaLevantamentoCadastral;

	// VAGAS
	private List<intra_candidato_vaga> listaVagas;
	private intra_candidato_vaga vaga = new intra_candidato_vaga();

	private intra_candidato_controle_encaminhamento controleEncaminhamento = new intra_candidato_controle_encaminhamento();

	@PostConstruct
	public void init() {
		this.filtroEntrevista = new String[] { "S", "N", "I" };
		this.filtroLevantamento = new String[] { "S", "N", "I" };
		this.filtroContato = new String[] { "S", "N" };
		this.filtroContratado = new String[] { "N" };
	}

	public String getCandidatoCPF() {
		return candidatoCPF;
	}

	public void setCandidatoCPF(String candidatoCPF) {
		this.candidatoCPF = candidatoCPF;
	}

	public String[] getCategoriaCNH() {
		return categoriaCNH;
	}

	public void setCategoriaCNH(String[] categoriaCNH) {
		this.categoriaCNH = categoriaCNH;
	}

	public String[] getPeriodoTrabalho() {
		return periodoTrabalho;
	}

	public void setPeriodoTrabalho(String[] periodoTrabalho) {
		this.periodoTrabalho = periodoTrabalho;
	}

	public intra_candidato getCandidato() {
		this.recebePreCadastro();
		if (this.candidato == null) {
			this.candidato = new intra_candidato();
		}
		this.candidato.setCidade("São Paulo");
		return candidato;
	}

	public void setCandidato(intra_candidato candidato) {
		this.candidato = candidato;
	}

	public intra_candidato_dependentes getDependente() {
		return dependente;
	}

	public void setDependente(intra_candidato_dependentes dependente) {
		this.dependente = dependente;
	}

	public intra_candidato_historico_profissional getHistoricoProfissional() {
		return historicoProfissional;
	}

	public void setHistoricoProfissional(intra_candidato_historico_profissional historicoProfissional) {
		this.historicoProfissional = historicoProfissional;
	}

	public List<intra_candidato_dependentes> getListaDependentes() {
		return listaDependentes;
	}

	public void setListaDependentes(List<intra_candidato_dependentes> listaDependentes) {
		this.listaDependentes = listaDependentes;
	}

	public List<intra_candidato_historico_profissional> getListaHistorico() {
		return listaHistorico;
	}

	public void setListaHistorico(List<intra_candidato_historico_profissional> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}

	public List<intra_candidato> getListaCandidatos() {
		return listaCandidatos;
	}

	public void setListaCandidatos(List<intra_candidato> listaCandidatos) {
		this.listaCandidatos = listaCandidatos;
	}

	public List<intra_candidato> getListaPreCadastro() {
		if (this.listaPreCadastro == null) {
			CandidatosDAO dao = new CandidatosDAO();
			this.listaPreCadastro = dao.getListaPreCadastro();
		}
		return listaPreCadastro;
	}

	public void setListaPreCadastro(List<intra_candidato> listaPreCadastro) {
		this.listaPreCadastro = listaPreCadastro;
	}

	public List<intra_candidato> getFiltroPreCadastro() {
		return filtroPreCadastro;
	}

	public void setFiltroPreCadastro(List<intra_candidato> filtroPreCadastro) {
		this.filtroPreCadastro = filtroPreCadastro;
	}

	public List<intra_condominios> getListaCondominios() {
		if (this.listaCondominios == null) {
			CandidatosDAO dao = new CandidatosDAO();
			this.listaCondominios = dao.getListaCondominios();
		}
		return listaCondominios;
	}

	public void setListaCondominios(List<intra_condominios> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public List<String> getListaCidades() {
		if (this.listaCidades == null) {
			CandidatosDAO dao = new CandidatosDAO();
			this.listaCidades = dao.getListaCidades();
		}
		return listaCidades;
	}

	public void setListaCidades(List<String> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public List<String> getListaCargoPretendido() {
		if (this.listaCargoPretendido == null) {
			this.listaCargoPretendido = new ArrayList<>();
			this.listaCargoPretendido.add("ADMINISTRATIVO CONDOMÍNIO");
			this.listaCargoPretendido.add("ARRUMADEIRA");
			this.listaCargoPretendido.add("AUXILIAR DE LIMPEZA");
			this.listaCargoPretendido.add("GERENTE PREDIAL");
			this.listaCargoPretendido.add("ZELADOR(A)");
			this.listaCargoPretendido.add("FAXINEIRO(A)");
			this.listaCargoPretendido.add("FOLGUISTA");
			this.listaCargoPretendido.add("JARDINEIRO(A)");
			this.listaCargoPretendido.add("MANOBRISTA");
			this.listaCargoPretendido.add("MANUTENÇÃO");
			this.listaCargoPretendido.add("PORTEIRO(A)");
			this.listaCargoPretendido.add("RECEPCIONISTA");
			this.listaCargoPretendido.add("SERVIÇOS GERAIS");
		}
		return listaCargoPretendido;
	}

	public void setListaCargoPretendido(List<String> listaCargoPretendido) {
		this.listaCargoPretendido = listaCargoPretendido;
	}

	public String getPretensaoSalarial() {
		return pretensaoSalarial;
	}

	public void setPretensaoSalarial(String pretensaoSalarial) {
		this.pretensaoSalarial = pretensaoSalarial;
	}

	public String getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(String cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public int getIdadeInicio() {
		return idadeInicio;
	}

	public void setIdadeInicio(int idadeInicio) {
		this.idadeInicio = idadeInicio;
	}

	public int getIdadeFim() {
		return idadeFim;
	}

	public void setIdadeFim(int idadeFim) {
		this.idadeFim = idadeFim;
	}

	public double getSalarioInicio() {
		return salarioInicio;
	}

	public void setSalarioInicio(double salarioInicio) {
		this.salarioInicio = salarioInicio;
	}

	public double getSalarioFim() {
		return salarioFim;
	}

	public void setSalarioFim(double salarioFim) {
		this.salarioFim = salarioFim;
	}

	public intra_candidato getCandidatoSelecionado() {
		if (this.candidatoSelecionado == null) {
			try {
				FacesContext ctx = FacesContext.getCurrentInstance();
				Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
				String codigoCandidato = params.get("codigoCandidato");
				if (codigoCandidato != null) {
					CandidatosDAO dao = new CandidatosDAO();
					this.candidatoSelecionado = dao.pesquisaCandidatoPorCodigo(Integer.parseInt(codigoCandidato));
					if (this.candidatoSelecionado != null) {
						this.candidatoSelecionado.getDependente();
						this.candidatoSelecionado.getHistoricoProfissional();
						List<intra_candidato_foto> l = dao.pesquisaCandidatoFoto(this.candidatoSelecionado);
						if (l != null && l.size() > 0) {
							this.candidatoFoto = l.get(0);
						} else {
							this.candidatoFoto = null;
						}

						if (this.candidatoSelecionado.getCategoriaCnh() != null) {
							String[] aux = this.candidatoSelecionado.getCategoriaCnh().split("");
							for (int i = 1; i < aux.length; i++) {
								this.categoriaCNH[i] = aux[i];
							}
						}

						if (this.candidatoSelecionado.getPeriodoTrabalho() != null) {
							String[] aux = this.candidatoSelecionado.getPeriodoTrabalho().split("");
							for (int i = 1; i < aux.length; i++) {
								this.periodoTrabalho[i] = aux[i];
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return candidatoSelecionado;
	}

	public void setCandidatoSelecionado(intra_candidato candidatoSelecionado) {
		this.candidatoSelecionado = candidatoSelecionado;
	}

	public intra_candidato_foto getCandidatoFoto() {
		return candidatoFoto;
	}

	public void setCandidatoFoto(intra_candidato_foto candidatoFoto) {
		this.candidatoFoto = candidatoFoto;
	}

	public intra_cadastro_recepcao getCadastro() {
		return cadastro;
	}

	public void setCadastro(intra_cadastro_recepcao cadastro) {
		this.cadastro = cadastro;
	}

	public intra_cadastro_recepcao_foto getCadastroFoto() {
		return cadastroFoto;
	}

	public void setCadastroFoto(intra_cadastro_recepcao_foto cadastroFoto) {
		this.cadastroFoto = cadastroFoto;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public String getPastaImagem() {
		return pastaImagem;
	}

	public void setPastaImagem(String pastaImagem) {
		this.pastaImagem = pastaImagem;
	}

	public int getCodigoSelecionado() {
		return codigoSelecionado;
	}

	public void setCodigoSelecionado(int codigoSelecionado) {
		this.codigoSelecionado = codigoSelecionado;
	}

	public String[] getFiltroEntrevista() {
		return filtroEntrevista;
	}

	public void setFiltroEntrevista(String[] filtroEntrevista) {
		this.filtroEntrevista = filtroEntrevista;
	}

	public String[] getFiltroLevantamento() {
		return filtroLevantamento;
	}

	public void setFiltroLevantamento(String[] filtroLevantamento) {
		this.filtroLevantamento = filtroLevantamento;
	}

	public String[] getFiltroContato() {
		return filtroContato;
	}

	public void setFiltroContato(String[] filtroContato) {
		this.filtroContato = filtroContato;
	}

	public String[] getFiltroContratado() {
		return filtroContratado;
	}

	public void setFiltroContratado(String[] filtroContratado) {
		this.filtroContratado = filtroContratado;
	}

	public String getFiltroHistorico() {
		return filtroHistorico;
	}

	public void setFiltroHistorico(String filtroHistorico) {
		this.filtroHistorico = filtroHistorico;
	}

	public intra_candidato_prontuario getLevantamentoCadastral() {
		return levantamentoCadastral;
	}

	public void setLevantamentoCadastral(intra_candidato_prontuario levantamentoCadastral) {
		this.levantamentoCadastral = levantamentoCadastral;
	}

	public intra_candidato_prontuario getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(intra_candidato_prontuario curriculo) {
		this.curriculo = curriculo;
	}

	public intra_candidato_contatos getContato() {
		return contato;
	}

	public void setContato(intra_candidato_contatos contato) {
		this.contato = contato;
	}

	public List<intra_candidato_prontuario> getListaLevantamentoCadastral() {
		this.listaLevantamentoCadastral = new ArrayList<>();
		if (this.candidatoSelecionado != null && this.candidatoSelecionado.getPronturario() != null) {
			for (intra_candidato_prontuario aux : this.candidatoSelecionado.getPronturario()) {
				if (aux.getTipoArquivo().equals("Levantamento Cadastral")) {
					this.levantamentoCadastral = aux;
					this.listaLevantamentoCadastral.add(aux);
				}
			}
		}
		return listaLevantamentoCadastral;
	}

	public void setListaLevantamentoCadastral(List<intra_candidato_prontuario> listaLevantamentoCadastral) {
		this.listaLevantamentoCadastral = listaLevantamentoCadastral;
	}

	public List<intra_candidato_vaga> getListaVagas() {
		if (this.listaVagas == null) {
			VagasDAO dao = new VagasDAO();
			this.listaVagas = dao.pesquisaVagasDisponiveis(this.candidatoSelecionado);
		}
		return listaVagas;
	}

	public void setListaVagas(List<intra_candidato_vaga> listaVagas) {
		this.listaVagas = listaVagas;
	}

	public intra_candidato_vaga getVaga() {
		return vaga;
	}

	public void setVaga(intra_candidato_vaga vaga) {
		this.vaga = vaga;
	}

	public intra_candidato_controle_encaminhamento getControleEncaminhamento() {
		return controleEncaminhamento;
	}

	public void setControleEncaminhamento(intra_candidato_controle_encaminhamento controleEncaminhamento) {
		this.controleEncaminhamento = controleEncaminhamento;
	}

	public void adicionarDependente() {
		this.dependente.setCandidato(this.candidato);
		this.listaDependentes.add(this.dependente);
		this.dependente = new intra_candidato_dependentes();
	}

	public void adicionarDependenteAlteracao() {
		this.dependente.setCandidato(this.candidatoSelecionado);
		if (this.candidatoSelecionado.getDependente() == null) {
			this.candidatoSelecionado.setDependente(new ArrayList<intra_candidato_dependentes>());
		}
		this.candidatoSelecionado.getDependente().add(this.dependente);
		this.dependente = new intra_candidato_dependentes();
	}

	public void excluirDependente(intra_candidato_dependentes dependente) {
		this.listaDependentes.remove(dependente);
	}

	public void excluirDependenteAlteracao(intra_candidato_dependentes dependente) {
		dependente.setCandidato(null);
		List<intra_candidato_dependentes> l = new ArrayList<>();
		l.addAll(this.candidatoSelecionado.getDependente());
		l.remove(dependente);
		this.candidatoSelecionado.setDependente(new ArrayList<intra_candidato_dependentes>());
		this.candidatoSelecionado.getDependente().addAll(l);
	}

	public void adicionarHistorico() {
		this.historicoProfissional.setCandidato(this.candidato);
		this.historicoProfissional.setImprimir(true);
		this.listaHistorico.add(this.historicoProfissional);
		this.historicoProfissional = new intra_candidato_historico_profissional();
	}

	public void adicionarHistoricoAlteracao() {
		this.historicoProfissional.setCandidato(this.candidatoSelecionado);
		this.historicoProfissional.setImprimir(true);
		if (this.candidatoSelecionado.getHistoricoProfissional() == null) {
			this.candidatoSelecionado.setHistoricoProfissional(new ArrayList<intra_candidato_historico_profissional>());
		}
		this.candidatoSelecionado.getHistoricoProfissional().add(this.historicoProfissional);
		this.historicoProfissional = new intra_candidato_historico_profissional();
	}

	public void excluirHistorico(intra_candidato_historico_profissional historicoProfissional) {
		this.listaHistorico.remove(historicoProfissional);
	}

	public void excluirHistoricoAlteracao(intra_candidato_historico_profissional historicoProfissional) {
		historicoProfissional.setCandidato(null);
		List<intra_candidato_historico_profissional> l = new ArrayList<>();
		l.addAll(this.candidatoSelecionado.getHistoricoProfissional());
		l.remove(historicoProfissional);
		this.candidatoSelecionado.setHistoricoProfissional(new ArrayList<intra_candidato_historico_profissional>());
		this.candidatoSelecionado.getHistoricoProfissional().addAll(l);
	}

	public void adicionarContatoAlteracao() {
		this.contato.setCandidato(this.candidatoSelecionado);
		if (this.candidatoSelecionado.getContatos() == null) {
			this.candidatoSelecionado.setContatos(new ArrayList<intra_candidato_contatos>());
		}
		this.candidatoSelecionado.getContatos().add(this.contato);
		this.contato = new intra_candidato_contatos();
	}

	public void excluirContatoAlteracao(intra_candidato_contatos contato) {
		contato.setCandidato(null);
		List<intra_candidato_contatos> l = new ArrayList<>();
		l.addAll(this.candidatoSelecionado.getContatos());
		l.remove(contato);
		this.candidatoSelecionado.setContatos(new ArrayList<intra_candidato_contatos>());
		this.candidatoSelecionado.getContatos().addAll(l);
	}

	public void constroiCandidato() {
		if (this.candidato.getPretensaoSalarial() == null) {
			this.candidato.setPretensaoSalarial(0.0);
		}
		String strCpf = (String) this.candidatoCPF;
		strCpf = strCpf.trim();
		strCpf = strCpf.replace("-", "");
		strCpf = strCpf.replace(".", "");
		this.candidato.setCpf(strCpf);

		StringBuffer bf = new StringBuffer();
		if (this.categoriaCNH.length > 0) {
			for (int i = 0; i < this.categoriaCNH.length; i++) {
				bf.append(this.categoriaCNH[i]);
			}
			this.candidato.setCategoriaCnh(bf.toString());
		}

		StringBuffer bf2 = new StringBuffer();
		if (this.periodoTrabalho.length > 0) {
			for (int i = 0; i < this.periodoTrabalho.length; i++) {
				bf2.append(this.periodoTrabalho[i]);
			}
			this.candidato.setPeriodoTrabalho(bf2.toString());
		} else {
			this.candidato.setPeriodoTrabalho(null);
		}

		if (this.candidato.isPreCadastro()) {
			this.candidato.setDependente(new ArrayList<intra_candidato_dependentes>());
		}
		this.candidato.setDependente(this.listaDependentes);
		this.candidato.setHistoricoProfissional(this.listaHistorico);
		this.candidato.setPreCadastro(false);
	}

	public void constroiCandidatoAlteracao() {
		if (this.candidatoSelecionado != null && this.candidatoSelecionado.getCpf() != null) {
			String strCpf = (String) this.candidatoSelecionado.getCpf();
			strCpf = strCpf.trim();
			strCpf = strCpf.replace("-", "");
			strCpf = strCpf.replace(".", "");
			this.candidatoSelecionado.setCpf(strCpf);
		}

		StringBuffer bf = new StringBuffer();
		if (this.categoriaCNH.length > 0) {
			for (int i = 0; i < this.categoriaCNH.length; i++) {
				bf.append(this.categoriaCNH[i]);
			}
			this.candidatoSelecionado.setCategoriaCnh(bf.toString());
		}

		StringBuffer bf2 = new StringBuffer();
		if (this.periodoTrabalho.length > 0) {
			for (int i = 0; i < this.periodoTrabalho.length; i++) {
				bf2.append(this.periodoTrabalho[i]);
			}
			this.candidatoSelecionado.setPeriodoTrabalho(bf2.toString());
		} else {
			this.candidatoSelecionado.setPeriodoTrabalho(null);
		}
		this.candidato.setPreCadastro(false);
	}

	public void salvarNovoCandidato() {
		try {
			this.constroiCandidato();
			CandidatosDAO dao = new CandidatosDAO();
			dao.salvarCandidato(this.candidato);
			this.limpar();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro! " + e.getMessage(), ""));
			e.printStackTrace();
		}
	}

	public void salvarAlteracoesCandidato() {
		try {
			this.constroiCandidatoAlteracao();
			CandidatosDAO dao = new CandidatosDAO();
			if (this.candidatoSelecionado.isPreCadastro()) {
				this.candidatoSelecionado.setPreCadastro(false);
			}
			this.candidatoSelecionado = dao.salvarAlteracoesCandidato(this.candidatoSelecionado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!" + e.getMessage(), ""));
		}
	}

	public void pesquisarCandidatos() {
		try {
			CandidatosDAO dao = new CandidatosDAO();
			if (this.filtroHistorico == null || this.filtroHistorico.trim().isEmpty()) {
				this.listaCandidatos = dao.pesquisarCandidatos(this.palavraChave, this.cidadeSelecionada,
						this.idadeInicio, this.idadeFim, this.salarioInicio, this.salarioFim, this.candidato);
			} else {
				this.listaCandidatos = dao.pesquisarCandidatosJoin(this.filtroHistorico, this.palavraChave,
						this.cidadeSelecionada, this.idadeInicio, this.idadeFim, this.salarioInicio, this.salarioFim,
						this.candidato);
			}

			if (this.filtroEntrevista != null) {
				this.pesquisaEntrevista();
			}
			if (this.filtroLevantamento != null) {
				this.pesquisaLevantamento();
			}
			if (this.filtroContato != null) {
				this.pesquisaContato();
			}

			if (this.filtroContratado != null) {
				this.pesquisaContratado();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limparFiltros() {
		this.palavraChave = null;
		this.cidadeSelecionada = null;
		this.idadeInicio = 0;
		this.idadeFim = 0;
		this.salarioInicio = 0;
		this.salarioFim = 0;
		this.candidato = new intra_candidato();
	}

	public void limpar() {
		this.candidato = new intra_candidato();
		this.dependente = new intra_candidato_dependentes();
		this.historicoProfissional = new intra_candidato_historico_profissional();
		this.listaDependentes = null;
		this.historicoProfissional = null;
		this.pretensaoSalarial = null;
	}

	public void abreCandidatoSelecionado(int codigoCandidato) {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/rh/candidatos/detalhes-candidato.xhtml?codigoCandidato=" + codigoCandidato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abreAlteraCandidatos(int codigoCandidato) {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/rh/candidatos/alterar-candidato.xhtml?codigoCandidato=" + codigoCandidato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (event.getFile().getContents() != null) {
				intra_candidato_foto candidatoFoto = new intra_candidato_foto();
				candidatoFoto.setFoto(event.getFile().getContents());
				CandidatosDAO dao = new CandidatosDAO();
				if (this.candidatoSelecionado != null) {
					candidatoFoto.setCodigoCandidato(this.candidatoSelecionado.getCodigo());
					List<intra_candidato_foto> l = dao.pesquisaCandidatoFoto(this.candidatoSelecionado);
					if (l != null && l.size() > 0) {
						candidatoFoto.setCodigo(l.get(0).getCodigo());
					}
				}
				dao.salvarFotoCandidato(candidatoFoto);
				this.candidatoFoto = candidatoFoto;
				RequestContext.getCurrentInstance().reset("frmUpload");
				RequestContext.getCurrentInstance().execute("PF('dlgUpload').hide();");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleFileUploadDocumento(FileUploadEvent event) {
		try {
			if (event.getFile().getContents() != null) {
				intra_candidato_prontuario prontuario = new intra_candidato_prontuario();
				prontuario.setArquivo(event.getFile().getContents());
				prontuario.setDataEnviado(new Date());
				prontuario.setNomeArquivo(event.getFile().getFileName());
				prontuario.setTipoArquivo("Levantamento Cadastral");
				prontuario.setCandidato(this.candidatoSelecionado);
				this.candidatoSelecionado.getPronturario().add(prontuario);
				RequestContext.getCurrentInstance().reset("frmUploadDocumento");
				RequestContext.getCurrentInstance().execute("PF('dlgUploadDocumento').hide();");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleFileUploadCurriculo(FileUploadEvent event) {
		try {
			if (event.getFile().getContents() != null) {
				intra_candidato_prontuario prontuario = new intra_candidato_prontuario();
				prontuario.setArquivo(event.getFile().getContents());
				prontuario.setDataEnviado(new Date());
				prontuario.setNomeArquivo(event.getFile().getFileName());
				prontuario.setTipoArquivo("Curriculo");
				prontuario.setCandidato(this.candidatoSelecionado);
				this.candidatoSelecionado.getPronturario().add(prontuario);
				RequestContext.getCurrentInstance().reset("frmUploadCurriculo");
				RequestContext.getCurrentInstance().execute("PF('dlgUploadCurriculo').hide();");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleFileUpload2(FileUploadEvent event) {
		try {
			if (event.getFile().getContents() != null) {
				intra_candidato_foto candidatoFoto = new intra_candidato_foto();
				candidatoFoto.setCodigo(this.candidato.getCodigo());
				candidatoFoto.setFoto(event.getFile().getContents());
				CandidatosDAO dao = new CandidatosDAO();
				dao.salvarFotoCandidato(candidatoFoto);
				this.candidatoFoto = candidatoFoto;
				RequestContext.getCurrentInstance().reset("frmUpload");
				RequestContext.getCurrentInstance().execute("PF('dlgUpload').hide();");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirFotoCandidato() {
		try {
			CandidatosDAO dao = new CandidatosDAO();
			dao.excluirFotoCandidato(this.candidatoSelecionado.getCodigo());
			this.candidatoFoto = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void impressaoCandidato() throws Exception {
		HashMap<Object, Object> parametros = new HashMap<>();
		String nome = "";
		RelatorioJasperUtil rju = new RelatorioJasperUtil();

		parametros.put("codigoCandidato", this.candidatoSelecionado.getCodigo());
		parametros.put("nome", this.candidatoSelecionado.getNome());
		nome = "ImpressaoCandidato";

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		this.downloadPDF(rju.geraRelSiga(parametros, nome, nome, 1), "ImpressaoCandidato.pdf");
	}

	public void downloadPDF(byte[] retorno, String nomeArquivo) throws IOException {
		// Prepare.
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			// Open file.
			input = new BufferedInputStream(new ByteArrayInputStream(retorno), DEFAULT_BUFFER_SIZE);
			// Init servlet response.
			response.reset();
			/* response.setHeader("Content-Type", "application/pdf"); */
			response.setHeader("Content-Length", String.valueOf(retorno.length));
			response.setHeader("Content-Disposition", "inline;filename=\"" + nomeArquivo + "\"");
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			// Finalize task.
			output.flush();
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}
		// Inform JSF that it doesn't need to handle response.
		// This is very important, otherwise you will get the following
		// exception in the logs:
		// java.lang.IllegalStateException: Cannot forward after response has
		// been committed.
		facesContext.responseComplete();
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail
				// it. It may be useful to
				// know that this will generally only be thrown when the client
				// aborted the download.
				e.printStackTrace();
			}
		}
	}

	public void oncapture(CaptureEvent captureEvent) throws IOException {
		this.nomeImagem = this.camera.oncapture(captureEvent);
		this.pastaImagem = "temp";
	}

	public void pesquisaCadastro() {
		try {
			CandidatosDAO dao = new CandidatosDAO();
			String rg = this.cadastro.getRg();
			List<intra_cadastro_recepcao> retorno = dao.pesquisaCadastro(rg);
			if (retorno != null && retorno.size() > 0) {
				this.cadastro = retorno.get(0);
			} else {
				this.cadastro = new intra_cadastro_recepcao();
				this.cadastro.setRg(rg);
			}
			if (this.cadastro != null && this.cadastro.getCodigo() > 0) {
				this.cadastroFoto = dao.pesquisaFotoCadastro(this.cadastro);
				this.exportarFoto(this.cadastroFoto.getFoto());
				if (this.cadastro.getTipoVisita().equals("Candidato")) {
					this.candidato = this.pesquisaCandidatoPorRg(this.cadastro);
					if (this.candidato != null) {
						List<intra_candidato_foto> l = dao.pesquisaCandidatoFoto(this.candidato);
						if (l != null && l.size() > 0) {
							this.candidatoFoto = l.get(0);
						} else {
							this.candidatoFoto = null;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarCadastro() {
		try {
			CandidatosDAO dao = new CandidatosDAO();

			if (this.camera.getFoto() != null) {
				this.cadastroFoto.setFoto(this.camera.getFoto());
			}

			this.cadastro.setDataEntrada(new Date());
			dao.salvarCadastro(this.cadastro, this.cadastroFoto);
			if (this.cadastro.getTipoVisita().equals("Candidato")) {
				if (this.candidato == null || this.candidato.getCodigo() == 0) {
					this.candidato = new intra_candidato();
				}
				this.candidato.setPreCadastro(true);
				this.candidato.setNome(this.cadastro.getNome());
				this.candidato.setRg(this.cadastro.getRg());
				this.candidato.setCpf(this.cadastro.getCpf());
				this.candidato.setDataNascimento(this.cadastro.getDataNascimento());
				this.candidato.setTelCelular(this.cadastro.getCelular());
				this.candidato.setTelResidencial(this.cadastro.getTelResidencial());
				this.candidato.setCargoPretendido(this.cadastro.getCargoPretendido());

				if (this.candidatoFoto == null || this.candidatoFoto.getCodigo() == 0) {
					this.candidatoFoto = new intra_candidato_foto();
				}

				if (this.cadastroFoto != null && this.cadastroFoto.getFoto() != null) {
					this.candidatoFoto.setFoto(this.cadastroFoto.getFoto());
				}

				if (this.camera.getFoto() != null) {
					this.candidatoFoto.setFoto(this.camera.getFoto());
				}

				dao.salvarCandidato(this.candidato);
				this.candidatoFoto.setCodigoCandidato(this.candidato.getCodigo());
				if (this.candidatoFoto != null) {
					dao.salvarFotoCandidato(this.candidatoFoto);
				}
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
			this.limparFormCadastro();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao salvar!", e.getMessage()));
			e.printStackTrace();
		}

	}

	public void exportarFoto(byte[] foto) {
		try {
			if (foto != null) {
				this.nomeImagem = this.camera.getRandomImageName();
				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
						.getContext();
				String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator
						+ "temp" + File.separator + this.nomeImagem + ".jpg";
				this.pastaImagem = "temp";
				FileImageOutputStream imageOutput;
				imageOutput = new FileImageOutputStream(new File(newFileName));
				imageOutput.write(foto, 0, foto.length);
				imageOutput.close();
			}
		} catch (IOException e) {
			throw new FacesException("Error in writing captured image.", e);
		}
	}

	public void limparFormCadastro() {
		this.camera = new CameraUtil();
		this.pastaImagem = "temp";
		this.nomeImagem = null;
		this.cadastro = new intra_cadastro_recepcao();
		this.cadastroFoto = new intra_cadastro_recepcao_foto();
		this.candidato = new intra_candidato();
		this.candidatoFoto = new intra_candidato_foto();
	}

	public intra_candidato pesquisaCandidatoPorRg(intra_cadastro_recepcao cadastro) {
		CandidatosDAO dao = new CandidatosDAO();
		List<intra_candidato> retorno = dao.pesquisaCandidatoPorRg(cadastro.getRg());
		if (retorno != null && retorno.size() > 0) {
			return retorno.get(0);
		}
		return null;
	}

	public void redirecionaPreCadastro(intra_candidato candidato) {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/rh/candidatos/novo-candidato.xhtml?codigoCandidato=" + candidato.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recebePreCadastro() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String codigoCandidato = params.get("codigoCandidato");
			if (codigoCandidato != null) {
				CandidatosDAO dao = new CandidatosDAO();
				this.candidato = dao.recebeCandidatoPreCadastro(Integer.parseInt(codigoCandidato));
				this.candidatoCPF = String.valueOf(this.candidato.getCpf());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void imprimirFicha() throws IOException, Exception {
		try {
			HashMap<Object, Object> parametros = new HashMap<>();
			String nome = "";
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminhoRelatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator;
			parametros.put("SUBREPORT_DIR", caminhoRelatorio);
			parametros.put("codigoCandidato", this.candidatoSelecionado.getCodigo());
			parametros.put("idadeFilhos", this.retornaIdadeFilhos());
			parametros.put("periodo", this.retornaPeriodoTrabalho());
			parametros.put("dCargos", this.candidatoSelecionado.getDemaisCargos());
			nome = "ImpressaoCandidato";
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.renderResponse();
			facesContext.responseComplete();
			this.downloadPDF(rju.geraRel2(parametros, nome, nome, 1), "ImpressaoCandidato.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verificaPossuiCurriculo() {
		if (this.candidatoSelecionado != null && this.candidatoSelecionado.getPronturario() != null) {
			for (intra_candidato_prontuario aux : this.candidatoSelecionado.getPronturario()) {
				if (aux.getTipoArquivo().equals("Curriculo")) {
					this.curriculo = aux;
					return true;
				}
			}
		}
		return false;
	}

	public void excluirLevantamentoDocumento(intra_candidato_prontuario levantamentoCadastral) {
		try {
			if (levantamentoCadastral != null && levantamentoCadastral.getArquivo() != null
					&& this.listaLevantamentoCadastral != null && this.listaLevantamentoCadastral.size() > 0) {
				this.listaLevantamentoCadastral.remove(levantamentoCadastral);
				this.candidatoSelecionado.getPronturario().remove(levantamentoCadastral);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void vizualizarLevantamentoDocumento() {
		try {
			if (this.levantamentoCadastral != null && this.levantamentoCadastral.getArquivo() != null) {
				this.downloadPDF(this.levantamentoCadastral.getArquivo(), this.levantamentoCadastral.getNomeArquivo());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void vizualizarCurriculo() {
		try {
			if (this.curriculo != null && this.curriculo.getArquivo() != null) {
				this.downloadPDF(this.curriculo.getArquivo(), this.curriculo.getNomeArquivo());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void excluirCurriculo() {
		Iterator<intra_candidato_prontuario> itr = this.candidatoSelecionado.getPronturario().iterator();
		while (itr.hasNext()) {
			if (itr.next().getTipoArquivo().equals("Curriculo")) {
				itr.remove();
			}
		}
	}

	public void adicionarControleEncaminhamento() {
		if (this.candidatoSelecionado != null && this.candidatoSelecionado.getControleEncaminhamento() != null) {
			this.controleEncaminhamento.setCandidato(this.candidatoSelecionado);
			this.candidatoSelecionado.getControleEncaminhamento().add(this.controleEncaminhamento);
			this.controleEncaminhamento = new intra_candidato_controle_encaminhamento();
		}
	}

	public void excluirControleEncaminhamento(intra_candidato_controle_encaminhamento controleEncaminhamento) {
		controleEncaminhamento.setCandidato(null);
		this.candidatoSelecionado.getControleEncaminhamento().remove(controleEncaminhamento);
	}

	public void limpaEntrevista() {
		this.candidatoSelecionado.setDataEntrevista(null);
		this.candidatoSelecionado.setBloqEntrevista(false);
	}

	public void limpaLevantamento() {
		this.candidatoSelecionado.setDataLevantamento(null);
		this.candidatoSelecionado.setBloqLevantamentoCadastral(false);
	}

	public void pesquisaEntrevista() {
		if (this.listaCandidatos != null) {
			if (this.filtroEntrevista != null && this.filtroEntrevista.length > 0) {
				List<intra_candidato> l = new ArrayList<>();
				for (intra_candidato aux : this.listaCandidatos) {
					if (aux.getVerificaAptoEntrevista().equals("SIM")
							&& Arrays.asList(this.filtroEntrevista).contains("S")) {
						l.add(aux);
					}
					if (aux.getVerificaAptoEntrevista().equals("NÃO")
							&& Arrays.asList(this.filtroEntrevista).contains("N")) {
						l.add(aux);
					}
					if (aux.getVerificaAptoEntrevista().equals("INAPTO")
							&& Arrays.asList(this.filtroEntrevista).contains("I")) {
						l.add(aux);
					}
				}
				this.listaCandidatos = l;
			}
		}
	}

	public void pesquisaLevantamento() {
		if (this.listaCandidatos != null) {
			if (this.filtroLevantamento != null && this.filtroLevantamento.length > 0) {
				List<intra_candidato> l = new ArrayList<>();
				for (intra_candidato aux : this.listaCandidatos) {
					if (aux.getVerificaAptoLevantamento().equals("SIM")
							&& Arrays.asList(this.filtroLevantamento).contains("S")) {
						l.add(aux);
					}
					if (aux.getVerificaAptoLevantamento().equals("NÃO")
							&& Arrays.asList(this.filtroLevantamento).contains("N")) {
						l.add(aux);
					}
					if (aux.getVerificaAptoLevantamento().equals("INAPTO")
							&& Arrays.asList(this.filtroLevantamento).contains("I")) {
						l.add(aux);
					}
				}
				this.listaCandidatos = l;
			}
		}
	}

	public void pesquisaContato() {
		if (this.listaCandidatos != null) {
			if (this.filtroContato != null && this.filtroContato.length > 0) {
				List<intra_candidato> l = new ArrayList<>();
				for (intra_candidato aux : this.listaCandidatos) {
					if (aux.isBloqContato() && Arrays.asList(this.filtroContato).contains("S")) {
						l.add(aux);
					}
					if (!aux.isBloqContato() && Arrays.asList(this.filtroContato).contains("N")) {
						l.add(aux);
					}
				}
				this.listaCandidatos = l;
			}
		}
	}

	public void pesquisaContratado() {
		if (this.listaCandidatos != null) {
			if (this.filtroContratado != null && this.filtroContratado.length > 0) {
				List<intra_candidato> l = new ArrayList<>();
				for (intra_candidato aux : this.listaCandidatos) {
					if (aux.isContratado() && Arrays.asList(this.filtroContratado).contains("S")) {
						l.add(aux);
					}
					if (!aux.isContratado() && Arrays.asList(this.filtroContratado).contains("N")) {
						l.add(aux);
					}
				}
				this.listaCandidatos = l;
			}
		}
	}

	public void excluirCandidato() {
		try {
			if (this.candidatoSelecionado != null) {
				CandidatosDAO dao = new CandidatosDAO();
				dao.excluirCandidato(this.candidatoSelecionado);
			}
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "excluido-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", e.getMessage()));
		}
	}

	public void excluirCadastroTbl(intra_candidato cadastro) {
		try {
			if (cadastro != null) {
				CandidatosDAO dao = new CandidatosDAO();
				dao.excluirCandidato(cadastro);
			}
			this.listaPreCadastro = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", e.getMessage()));
		}
	}

	public void excluirCandidatoTbl(intra_candidato candidato) {
		try {
			if (candidato != null) {
				CandidatosDAO dao = new CandidatosDAO();
				dao.excluirCandidato(candidato);
			}
			this.listaCandidatos = null;
			this.pesquisarCandidatos();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", e.getMessage()));
		}
	}

	public int calculaIdade() {
		if (this.candidatoSelecionado != null) {
			return Years
					.yearsBetween(new DateTime(this.candidatoSelecionado.getDataNascimento()), new DateTime(new Date()))
					.getYears();
		} else {
			return 0;
		}
	}

	public String calculaIdade(Date dataNascimento) {
		if (dataNascimento != null) {
			return String
					.valueOf(Years.yearsBetween(new DateTime(dataNascimento), new DateTime(new Date())).getYears());
		} else {
			return "Não Informado";
		}
	}

	public void adicionarVagaAoCandidato() {
		if (this.vaga != null) {
			this.candidatoSelecionado.getVagas().add(this.vaga);
			this.listaVagas.remove(this.vaga);
		}
		this.vaga = new intra_candidato_vaga();
	}

	public void excluirVagaAlteracao(intra_candidato_vaga vaga) {
		this.candidatoSelecionado.getVagas().remove(vaga);
		if (this.listaVagas == null) {
			this.listaVagas = new ArrayList<>();
		}
		this.listaVagas.add(vaga);
	}

	public String retornaIdadeFilhos() {
		if (this.candidatoSelecionado != null && this.candidatoSelecionado.getDependente() != null
				&& this.candidatoSelecionado.getDependente().size() > 0) {
			StringBuffer bf = new StringBuffer();
			List<intra_candidato_dependentes> filhos = new ArrayList<>();
			for (intra_candidato_dependentes aux : this.candidatoSelecionado.getDependente()) {
				if (aux.getParentesco().toLowerCase().trim().equals("filho")
						|| aux.getParentesco().toLowerCase().trim().equals("filha")) {
					filhos.add(aux);
				}
			}
			if (filhos != null && filhos.size() > 0) {
				bf.append(filhos.size() + "  -  ");
				if (filhos.size() == 1) {
					int idade = Years
							.yearsBetween(new DateTime(filhos.get(0).getDataNascimento()), new DateTime(new Date()))
							.getYears();
					bf.append("(" + idade + (idade == 1 ? " ano)" : " anos)"));
				} else if (filhos.size() > 1) {
					for (int i = 0; i <= filhos.size() - 1; i++) {
						if (filhos.get(i).getDataNascimento() != null && filhos.get(i).getParentesco() != null
								&& !filhos.get(i).getParentesco().trim().isEmpty()) {
							int idade = Years.yearsBetween(new DateTime(filhos.get(i).getDataNascimento()),
									new DateTime(new Date())).getYears();
							if (i == 0) {
								bf.append("(" + idade);
							} else if (i > 0 && i < filhos.size() - 1) {
								bf.append(", " + idade);
							} else if (i == filhos.size() - 1) {
								bf.append(" e " + (idade == 1 ? " ano)" : idade + " anos)"));
							}
						}
					}
				}
			}
			return bf.toString();
		} else {
			return "";
		}
	}

	public String retornaPeriodoTrabalho() {
		StringBuilder str = new StringBuilder();
		StringBuilder str3 = new StringBuilder();
		if (this.candidatoSelecionado.getPeriodoTrabalho() != null
				&& !this.candidatoSelecionado.getPeriodoTrabalho().trim().isEmpty()) {

			if (this.candidatoSelecionado.getPeriodoTrabalho().trim().contains("M")) {
				str.append("Matutino ");
			}

			if (this.candidatoSelecionado.getPeriodoTrabalho().trim().contains("V")) {
				str.append("Vespertino ");

			}

			if (this.candidatoSelecionado.getPeriodoTrabalho().trim().contains("N")) {
				str.append("Noturno ");
			}

			if (this.candidatoSelecionado.getPeriodoTrabalho().trim().contains("R")) {
				str.append("Rotativo ");
			}

			String[] str2 = str.toString().split(" ");

			for (int i = 0; i <= str2.length - 1; i++) {
				if (i < str2.length - 2) {
					str3.append(str2[i] + ", ");
				} else if (i < str2.length - 1) {
					str3.append(str2[i] + " e ");
				} else {
					str3.append(str2[i] + ".");
				}
			}
		}
		return str3.toString();
	}

	public void retornaZona(String cep) {
		if (cep != null && cep.length() > 5) {
			int cepFinal = Integer.valueOf(cep.substring(0, 5));
			String zona = "";

			if (cepFinal >= 1000 && cepFinal <= 1599) {
				zona = "Centro";
			}

			if (cepFinal >= 2000 && cepFinal <= 2999) {
				zona = "Norte";
			}

			if ((cepFinal >= 3000 && cepFinal <= 3999) || (cepFinal >= 8000 && cepFinal <= 8499)) {
				zona = "Leste";
			}

			if (cepFinal >= 5000 && cepFinal <= 5899) {
				zona = "Oeste";
			}

			if (cepFinal >= 4000 && cepFinal <= 4999) {
				zona = "Sul";
			}

			if (this.candidato != null) {
				this.candidato.setZonaBairro(zona);
			}

			if (this.candidatoSelecionado != null) {
				this.candidatoSelecionado.setZonaBairro(zona);
			}
		} else {
			if (this.candidato != null) {
				this.candidato.setZonaBairro("");
			}
			if (this.candidatoSelecionado != null) {
				this.candidatoSelecionado.setZonaBairro("");
			}
		}
	}

	public void goBack() {
		String referrer = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("referer");
		if (referrer != null && referrer.contains("rh/candidatos") && !referrer.contains("alterar")) {
			RequestContext.getCurrentInstance().execute("goBack();");
		} else {
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "/rh/candidatos.xhtml?faces-redirect=true");
		}
	}

	public void limpaComoConheceu() {
		if (this.candidatoSelecionado != null && this.candidatoSelecionado.getComoConheceuOma() != null
				&& this.candidatoSelecionado.getComoConheceuOma().equals("Outros")) {
			this.candidatoSelecionado.setComoConheceuOmaObs("");
		}

		if (this.candidato != null && this.candidato.getComoConheceuOma() != null
				&& this.candidato.getComoConheceuOma().equals("Outros")) {
			this.candidato.setComoConheceuOmaObs("");
		}
	}

}
