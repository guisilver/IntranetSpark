package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.MovimentoCaixaDAO;
import br.com.oma.intranet.dao.PC_FAVDAO;
import br.com.oma.intranet.entidades.intra_bd_es;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_favorecido;
import br.com.oma.intranet.entidades.intra_plano_contas;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ViewScoped
@ManagedBean
public class MovimentoCaixaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5213445911175679773L;
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private intra_bd_es movimentoCaixa = new intra_bd_es();
	private intra_bd_es movimentoCaixaSelecionado;
	private intra_condominios condominio;
	private intra_favorecido favorecido = new intra_favorecido();
	private intra_plano_contas planoContas = new intra_plano_contas();

	private List<intra_bd_es> listaMovimentoCaixa, filtroMovimentoCaixa;
	private List<intra_favorecido> listaDeFavorecido, filtroFavorecido;
	private List<intra_plano_contas> listaPlanoContas, filtroPlanoContas;

	private double valorBD;
	private DecimalFormat df = new DecimalFormat("#,###,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
	private byte[] retorno = null;
	private static final int DEFAULT_BUFFER_SIZE = 10240;

	private String tipoMovimentoPesquisa = "Todos";

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public intra_bd_es getMovimentoCaixa() {
		return movimentoCaixa;
	}

	public void setMovimentoCaixa(intra_bd_es movimentoCaixa) {
		this.movimentoCaixa = movimentoCaixa;
	}

	public intra_bd_es getMovimentoCaixaSelecionado() {
		return movimentoCaixaSelecionado;
	}

	public void setMovimentoCaixaSelecionado(intra_bd_es movimentoCaixaSelecionado) {
		this.movimentoCaixaSelecionado = movimentoCaixaSelecionado;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public intra_favorecido getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(intra_favorecido favorecido) {
		this.favorecido = favorecido;
	}

	public intra_plano_contas getPlanoContas() {
		return planoContas;
	}

	public void setPlanoContas(intra_plano_contas planoContas) {
		this.planoContas = planoContas;
	}

	public List<intra_bd_es> getListaMovimentoCaixa() {
		if (this.listaMovimentoCaixa == null) {
			MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
			if (this.sessaoMB.getGerenteSelecionado() == null && this.sessaoMB.getUsuario().getGrupoGer() != null
					&& this.sessaoMB.getUsuario().getGrupoGer().size() > 0
					&& this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")
					|| this.sessaoMB.getGerenteSelecionado() == null && this.sessaoMB.getIgp().isSysAdmin()) {
				if (this.tipoMovimentoPesquisa.equals("Todos")) {
					this.listaMovimentoCaixa = dao.getListaMovimentoCaixa();
				}
				if (this.tipoMovimentoPesquisa.equals("Entrada")) {
					this.listaMovimentoCaixa = dao.getListaMovimentoCaixaTipo(1);
				}
				if (this.tipoMovimentoPesquisa.equals("Saida")) {
					this.listaMovimentoCaixa = dao.getListaMovimentoCaixaTipo(0);
				}
			} else if (this.sessaoMB.getGerenteSelecionado() != null
					&& this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
				if (this.tipoMovimentoPesquisa.equals("Todos")) {
					this.listaMovimentoCaixa = dao.getListaMovimentoCaixaGerente(this.sessaoMB.getGerenteSelecionado());
				}
				if (this.tipoMovimentoPesquisa.equals("Entrada")) {
					this.listaMovimentoCaixa = dao
							.getListaMovimentoCaixaGerenteTipo(this.sessaoMB.getGerenteSelecionado(), 1);
				}
				if (this.tipoMovimentoPesquisa.equals("Saida")) {
					this.listaMovimentoCaixa = dao
							.getListaMovimentoCaixaGerenteTipo(this.sessaoMB.getGerenteSelecionado(), 0);
				}
			}
		}
		return listaMovimentoCaixa;
	}

	public void setListaMovimentoCaixa(List<intra_bd_es> listaMovimentoCaixa) {
		this.listaMovimentoCaixa = listaMovimentoCaixa;
	}

	public List<intra_bd_es> getFiltroMovimentoCaixa() {
		return filtroMovimentoCaixa;
	}

	public void setFiltroMovimentoCaixa(List<intra_bd_es> filtroMovimentoCaixa) {
		this.filtroMovimentoCaixa = filtroMovimentoCaixa;
	}

	public List<intra_favorecido> getListaDeFavorecido() {
		try {
			if (this.listaDeFavorecido == null) {
				PC_FAVDAO pcFavDAO = new PC_FAVDAO();
				this.listaDeFavorecido = pcFavDAO.listarFavorecido();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDeFavorecido;
	}

	public void setListaDeFavorecido(List<intra_favorecido> listaDeFavorecido) {
		this.listaDeFavorecido = listaDeFavorecido;
	}

	public List<intra_favorecido> getFiltroFavorecido() {
		return filtroFavorecido;
	}

	public void setFiltroFavorecido(List<intra_favorecido> filtroFavorecido) {
		this.filtroFavorecido = filtroFavorecido;
	}

	public List<intra_plano_contas> getListaPlanoContas() {
		try {
			if (this.listaPlanoContas == null) {
				PC_FAVDAO pcFavDAO = new PC_FAVDAO();
				this.listaPlanoContas = pcFavDAO.listarPlanoContas();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPlanoContas;
	}

	public void setListaPlanoContas(List<intra_plano_contas> listaPlanoContas) {
		this.listaPlanoContas = listaPlanoContas;
	}

	public List<intra_plano_contas> getFiltroPlanoContas() {
		return filtroPlanoContas;
	}

	public void setFiltroPlanoContas(List<intra_plano_contas> filtroPlanoContas) {
		this.filtroPlanoContas = filtroPlanoContas;
	}

	public double getValorBD() {
		return valorBD;
	}

	public void setValorBD(double valorBD) {
		this.valorBD = valorBD;
	}

	public byte[] getRetorno() {
		return retorno;
	}

	public void setRetorno(byte[] retorno) {
		this.retorno = retorno;
	}

	public String getTipoMovimentoPesquisa() {
		return tipoMovimentoPesquisa;
	}

	public void setTipoMovimentoPesquisa(String tipoMovimentoPesquisa) {
		this.tipoMovimentoPesquisa = tipoMovimentoPesquisa;
	}

	public void duplicarMovimento() {
		this.pesquisaMovimentoSelecionado();
		MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
		dao.detachMovimento(this.movimentoCaixa);
		this.movimentoCaixa.setId(0);
	}

	public void pesquisaMovimentoSelecionado() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String codigoMovimento = params.get("codigoMovimento");
			if (codigoMovimento != null) {
				MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
				this.movimentoCaixa = dao.pesquisaMovimentoPorCodigo(Integer.parseInt(codigoMovimento));
				String valor = this.movimentoCaixa.getValor().replace(".", "");
				valor = valor.replace(",", ".");
				this.valorBD = Double.valueOf(valor);
				this.sessaoMB.setGerenteSelecionado(dao.pesquisaGerentePorCodigo(this.movimentoCaixa.getCodGerente()));
				this.sessaoMB.listarCondominios();
				if (this.sessaoMB.getListaCondominios() != null) {
					for (intra_condominios aux : this.sessaoMB.getListaCondominios()) {
						if (this.movimentoCaixa.getCondominio() == aux.getCodigo()) {
							this.condominio = aux;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarNovoMovimento() {
		try {
			this.constroiMovimentoCaixa();
			MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
			dao.salvarNovoMovimento(this.movimentoCaixa);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
			intra_bd_es es = (intra_bd_es) this.movimentoCaixa.clone();
			this.geraRelatorio(es);
			this.limpar();
			RequestContext.getCurrentInstance().execute("clicaBotaoDownload();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarNovoMovimentoEVoltar() {
		try {
			this.constroiMovimentoCaixa();
			MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
			dao.salvarNovoMovimento(this.movimentoCaixa);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "/entrada-saida.xhtml?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarAlteracoesMovimento() {
		try {
			this.constroiMovimentoCaixa();
			MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
			dao.salvarAlteracoesMovimento(this.movimentoCaixa);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
			intra_bd_es es = (intra_bd_es) this.movimentoCaixa.clone();
			this.geraRelatorio(es);
			RequestContext.getCurrentInstance().execute("clicaBotaoDownload();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarAlteracoesMovimentoEVoltar() {
		try {
			this.constroiMovimentoCaixa();
			MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
			dao.salvarAlteracoesMovimento(this.movimentoCaixa);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "/entrada-saida.xhtml?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirMovimento() {
		try {
			MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
			dao.excluirMovimento(this.movimentoCaixa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void constroiMovimentoCaixa() throws Exception {
		if (this.movimentoCaixa.getTipo() == 1) {
			this.movimentoCaixa.setNf("");
		}

		if (this.sessaoMB.getGerenteSelecionado() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Insira um gerente!", ""));
			throw new Exception("Insira um gerente!");
		} else {
			this.movimentoCaixa.setNomeGerente(this.sessaoMB.getGerenteSelecionado().getNome());
			this.movimentoCaixa.setCodGerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
		}

		if (this.condominio == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Insira um condomínio!", ""));
			throw new Exception("Insira um condomínio!");
		} else {
			this.movimentoCaixa.setCondominio(this.condominio.getCodigo());
			this.movimentoCaixa.setNomeCondo(this.condominio.getNome());
		}

		this.movimentoCaixa.setData(new Date());
		this.movimentoCaixa.setValor(String.valueOf(this.df.format(this.valorBD)));
		this.carregaFavorecido();
		this.carregaPlanoContas();
	}

	public void carregaFavorecido() {
		if (this.favorecido != null && this.favorecido.getCodigo() > 0) {
			try {
				this.movimentoCaixa
						.setFavorecido(this.favorecido.getCodigo() + " - " + this.favorecido.getFavorecido());
				this.movimentoCaixa.setBanco(this.favorecido.getBanco());
				this.movimentoCaixa.setAgencia(this.favorecido.getAgencia());
				this.movimentoCaixa.setConta(this.favorecido.getConta());
				this.movimentoCaixa.setDigConta(this.favorecido.getDigConta());
				this.movimentoCaixa.setCpfCnpj(this.favorecido.getCnpjCpf());
				if (this.favorecido.getTipoConta().equals("N")) {
					this.movimentoCaixa.setTipoConta("Corrente");
				} else {
					this.movimentoCaixa.setTipoConta("Poupança");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void carregaPlanoContas() {
		if (this.planoContas != null && this.planoContas.getCodigo() > 0) {
			try {
				this.movimentoCaixa.setCodLancamento(this.planoContas.getCodReduzido());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void geraRelatorio(intra_bd_es es) throws Exception {
		try {
			String nome = "Saida";
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			if (es.getTipo() == 1) {
				nome = "Entrada";
			} else {
				if (this.movimentoCaixa.getNf() != null) {
					if (this.movimentoCaixa.getNf().trim().equals("")) {
						nome = "SaidaNF";
					} else {
						nome = "Saida";
					}
				} else {
					nome = "SaidaNF";
				}
			}
			HashMap<Object, Object> parametros = new HashMap<>();
			List<intra_bd_es> listaSaida = new ArrayList<>();
			listaSaida.add(this.movimentoCaixa);
			this.retorno = rju.geraSaida(parametros, nome, nome, 1, listaSaida);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void geraRelatorioTbl(intra_bd_es es) throws Exception {
		try {
			String nome = "Saida";
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			if (es.getTipo() == 1) {
				nome = "Entrada";
			} else {
				if (es.getNf() != null) {
					if (es.getNf().trim().equals("")) {
						nome = "SaidaNF";
					} else {
						nome = "Saida";
					}
				} else {
					nome = "SaidaNF";
				}
			}
			HashMap<Object, Object> parametros = new HashMap<>();
			List<intra_bd_es> listaSaida = new ArrayList<>();
			listaSaida.add(es);
			this.retorno = rju.geraSaida(parametros, nome, nome, 1, listaSaida);
			RequestContext.getCurrentInstance().execute("clicaBotaoDownload();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadPDF(byte[] retorno) throws IOException {
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
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(retorno.length));
			response.setHeader("Content-Disposition", "inline;filename=\"Entranda_Saida.pdf\"");
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
		// java.lang.IllegalStateException: Cannot forward after response
		// has
		// been committed.
		facesContext.responseComplete();
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or
				// mail
				// it. It may be useful to
				// know that this will generally only be thrown when the
				// client
				// aborted the download.
				e.printStackTrace();
			}
		}
	}

	public void limpar() {
		this.movimentoCaixa = new intra_bd_es();
		this.condominio = null;
		this.valorBD = 0;
	}

	public void abreAlteraMovimentoCaixa(intra_bd_es movimento) {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					caminho + "/entrada-saida/alterar-entrada-saida.xhtml?codigoMovimento=" + movimento.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abreDuplicaMovimentoCaixa(intra_bd_es movimento) {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/entrada-saida/nova-entrada-saida.xhtml?codigoMovimento=" + movimento.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirMovimentoTbl(intra_bd_es movimentoSelecionado) {
		try {
			MovimentoCaixaDAO dao = new MovimentoCaixaDAO();
			dao.excluirMovimentoTbl(movimentoSelecionado);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
			this.listaMovimentoCaixa = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizaLista() {
		DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("frmMovimentos:singleDT");
		d.setValue(null);
		RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
		this.listaMovimentoCaixa = null;
		this.filtroMovimentoCaixa = null;
	}
}
