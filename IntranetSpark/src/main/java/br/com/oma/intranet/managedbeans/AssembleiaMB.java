package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.AssembleiaDAO;
import br.com.oma.intranet.entidades.intra_assembleia;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_reservas_ti;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.util.ExportWord;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import br.com.oma.intranet.util.StringUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@ViewScoped
@ManagedBean
public class AssembleiaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5423667581379105195L;

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private intra_assembleia assembleia = new intra_assembleia();
	private intra_assembleia assembleiaSelecionada;
	private List<intra_assembleia> listaAssembleias;
	private boolean todosEquipamentos;
	private boolean possuiSegHorario;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	private StreamedContent stream;

	private Date dataInicio;

	private String tipoRelatorio = "A";
	private String tipoAssembleiaRelatorio;
	private String ordenacaoRelatorio;
	private Date relDtInicio;
	private Date relDtFim;
	private boolean relDtParam;
	private boolean publicados;

	private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

	private byte[] retorno;
	private List<String> listaOrdenacao;

	@PostConstruct
	public void init() {
		this.listaOrdenacao = new ArrayList<>();
		this.listaOrdenacao.add("Data");
		this.listaOrdenacao.add("Gerente");
		this.listaOrdenacao.add("Código do Cnd");
		this.listaOrdenacao.add("Nome do Cnd");
		this.dataInicio = new DateTime().withDayOfYear(1).toDate();
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public intra_assembleia getAssembleia() {
		return assembleia;
	}

	public void setAssembleia(intra_assembleia assembleia) {
		this.assembleia = assembleia;
	}

	public intra_assembleia getAssembleiaSelecionada() {
		return assembleiaSelecionada;
	}

	public void setAssembleiaSelecionada(intra_assembleia assembleiaSelecionada) {
		this.assembleiaSelecionada = assembleiaSelecionada;
	}

	public List<intra_assembleia> getListaAssembleias() {
		if (this.listaAssembleias == null) {
			AssembleiaDAO dao = new AssembleiaDAO();
			if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
				if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
					this.listaAssembleias = dao.getListaAssembleias(this.dataInicio);
				} else {
					if (this.sessaoMB.getGerenteSelecionado() != null) {
						this.listaAssembleias = dao.getListaAssembleiasPorGerente(this.dataInicio,
								this.sessaoMB.getGerenteSelecionado().getCodigo());
					}
				}
			}
		}
		return listaAssembleias;
	}

	public void setListaAssembleias(List<intra_assembleia> listaAssembleias) {
		this.listaAssembleias = listaAssembleias;
	}

	public boolean isTodosEquipamentos() {
		return todosEquipamentos;
	}

	public void setTodosEquipamentos(boolean todosEquipamentos) {
		this.todosEquipamentos = todosEquipamentos;
	}

	public boolean isPossuiSegHorario() {
		return possuiSegHorario;
	}

	public void setPossuiSegHorario(boolean possuiSegHorario) {
		this.possuiSegHorario = possuiSegHorario;
	}

	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public String getTipoAssembleiaRelatorio() {
		return tipoAssembleiaRelatorio;
	}

	public void setTipoAssembleiaRelatorio(String tipoAssembleiaRelatorio) {
		this.tipoAssembleiaRelatorio = tipoAssembleiaRelatorio;
	}

	public String getOrdenacaoRelatorio() {
		return ordenacaoRelatorio;
	}

	public void setOrdenacaoRelatorio(String ordenacaoRelatorio) {
		this.ordenacaoRelatorio = ordenacaoRelatorio;
	}

	public Date getRelDtInicio() {
		return relDtInicio;
	}

	public void setRelDtInicio(Date relDtInicio) {
		this.relDtInicio = relDtInicio;
	}

	public Date getRelDtFim() {
		return relDtFim;
	}

	public void setRelDtFim(Date relDtFim) {
		this.relDtFim = relDtFim;
	}

	public boolean isRelDtParam() {
		return relDtParam;
	}

	public void setRelDtParam(boolean relDtParam) {
		this.relDtParam = relDtParam;
	}

	public byte[] getRetorno() {
		return retorno;
	}

	public void setRetorno(byte[] retorno) {
		this.retorno = retorno;
	}

	public boolean isPublicados() {
		return publicados;
	}

	public void setPublicados(boolean publicados) {
		this.publicados = publicados;
	}

	public List<String> getListaOrdenacao() {
		return listaOrdenacao;
	}

	public void setListaOrdenacao(List<String> listaOrdenacao) {
		this.listaOrdenacao = listaOrdenacao;
	}

	private void populaReserva() {
		try {
			if (this.assembleia.getReserva() == null) {
				this.assembleia.setReserva(new intra_reservas_ti());
			}
			this.assembleia.getReserva().setData(this.assembleia.getDataAssembleia());
			this.assembleia.getReserva().setData_inserido(new Date());
			this.assembleia.getReserva().setTipo(this.assembleia.getTipo());
			this.assembleia.getReserva().setStatus("P");
			this.assembleia.getReserva().setNome_condominio(this.getAssembleia().getCondominio().getNome());
			this.assembleia.getReserva().setCod_condominio(this.getAssembleia().getCondominio().getCodigo());
			this.assembleia.getReserva().setNome_gerente(this.sessaoMB.getGerenteSelecionado().getNome());
			this.assembleia.getReserva().setCod_gerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
			this.assembleia.getReserva().setHorario(this.assembleia.getHorario1());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void populaAssembleia() {
		try {
			this.assembleia.setDataInserido(new Date());
			if (this.assembleia.getHorario1() != null) {
				this.assembleia.setHorario1(new DateTime(this.assembleia.getDataAssembleia())
						.withHourOfDay(new DateTime(this.assembleia.getHorario1()).getHourOfDay())
						.withMinuteOfHour(new DateTime(this.assembleia.getHorario1()).getMinuteOfHour()).toDate());
			}
			if (this.assembleia.getHorario2() != null) {
				this.assembleia.setHorario2(new DateTime(this.assembleia.getDataAssembleia())
						.withHourOfDay(new DateTime(this.assembleia.getHorario2()).getHourOfDay())
						.withMinuteOfHour(new DateTime(this.assembleia.getHorario2()).getMinuteOfHour()).toDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionarAssembleia() {
		try {
			this.populaAssembleia();
			this.populaReserva();
			AssembleiaDAO dao = new AssembleiaDAO();
			this.assembleia.setGerente(this.sessaoMB.getGerenteSelecionado());
			dao.salvarAssembleia(this.assembleia);
			this.limpar();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarAssembleia() {
		try {
			AssembleiaDAO dao = new AssembleiaDAO();
			if (this.assembleia.getCodigo() == 0) {
				this.populaAssembleia();
				this.populaReserva();
				this.assembleia.setGerente(this.sessaoMB.getGerenteSelecionado());
				this.assembleia.setDataInserido(new Date());
			}

			if (this.assembleia.getDevolvido() != null) {
				this.assembleia.setProtocoloRecebido(true);
			}

			dao.salvarAssembleia(this.assembleia);
			this.limpar();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirAssembleiaTbl(intra_assembleia assembleia) {
		try {
			AssembleiaDAO dao = new AssembleiaDAO();
			dao.excluirAssembleia(assembleia);
			this.limpar();
			this.limparFiltroTbl();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirAssembleia() {
		try {
			AssembleiaDAO dao = new AssembleiaDAO();
			dao.excluirAssembleia(this.assembleia);
			this.limpar();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "excluido-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selecionarLocal() {
		try {
			if (this.assembleia.getCondominio() != null && this.assembleia.getLocalAssembleia() != null) {
				switch (this.assembleia.getLocalAssembleia()) {
				case "Predio":
					this.assembleia.setEnderecoLocal(this.geraEnderecoCondominio(this.assembleia.getCondominio()));
					break;
				case "OMA":
					this.assembleia.setEnderecoLocal("Rua Cincinato Braga, 204, Bela Vista");
					break;
				default:
					this.assembleia.setEnderecoLocal("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String geraEnderecoCondominio(intra_condominios condominio) {
		String endereco = condominio.getLogradouro() + " " + condominio.getEndereco() + " " + condominio.getNro() + ", "
				+ condominio.getBairro();
		endereco = StringUtil.trataNomeComposto(endereco);
		return endereco;
	}

	public void selecionaTodosEquipamentos() {
		try {
			if (this.assembleia.getReserva() == null) {
				this.assembleia.setReserva(new intra_reservas_ti());
			}
			this.assembleia.getReserva().setAdaptador(this.todosEquipamentos);
			this.assembleia.getReserva().setApresentador(this.todosEquipamentos);
			this.assembleia.getReserva().setEnergia(this.todosEquipamentos);
			this.assembleia.getReserva().setVga(this.todosEquipamentos);
			this.assembleia.getReserva().setMala(this.todosEquipamentos);
			this.assembleia.getReserva().setPendrive(this.todosEquipamentos);
			this.assembleia.getReserva().setProjetor(this.todosEquipamentos);
			this.assembleia.getReserva().setTela(this.todosEquipamentos);
			this.assembleia.getReserva().setNotebook(this.todosEquipamentos);
			this.assembleia.getReserva().setCadeiras(this.todosEquipamentos);
			this.assembleia.getReserva().setMesas(this.todosEquipamentos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verificaTodosSelecionados() {
		try {
			if (this.assembleia != null) {
				if (this.assembleia.getReserva() != null) {
					boolean todos = true;
					if (!this.assembleia.getReserva().isAdaptador()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isApresentador()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isEnergia()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isVga()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isMala()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isPendrive()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isProjetor()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isTela()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isNotebook()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isCadeiras()) {
						todos = false;
					}
					if (!this.assembleia.getReserva().isMesas()) {
						todos = false;
					}
					this.todosEquipamentos = todos;
				} else {
					this.assembleia.setReserva(new intra_reservas_ti());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String verificaStatusConvocacao(intra_assembleia assembleia) {
		String status = "Pendente";
		if (assembleia.getEnviado() != null) {
			status = "Enviado";
		}
		if (assembleia.getDevolvido() != null) {
			status = "Devolvido";
		}
		if (assembleia.getPublicacaoConvocacao() != null) {
			status = "Publicado";
		}
		return status;
	}

	public String verificaStatusAta(intra_assembleia assembleia) {
		String status = "Pendente";
		if (assembleia.getDigitacao() != null) {
			status = "1 - Digitado - " + this.sf.format(assembleia.getDigitacao());
		}
		if (assembleia.getAdvogado() != null) {
			status = "2 - Advogado - " + this.sf.format(assembleia.getAdvogado());
		}
		if (assembleia.getEnviadoPresidente() != null && !assembleia.getEnviadoPresidente().trim().equals("")) {
			status = "3 - Enviado p/ Presidente";
		}
		if (assembleia.getAssinada() != null) {
			status = "4 - Assinado - " + this.sf.format(assembleia.getAssinada());
		}
		if (assembleia.getCartorio() != null) {
			status = "5 - Cartório - " + this.sf.format(assembleia.getCartorio());
		}
		if (assembleia.getDistribuicao() != null) {
			status = "6 - Distribuído - " + this.sf.format(assembleia.getDistribuicao());
		}
		if (assembleia.getEnviadoTi() != null) {
			status = "7 - Enviado p/ TI - " + this.sf.format(assembleia.getEnviadoTi());
		}
		if (assembleia.getPublicacaoAta() != null) {
			status = "8 - Publicado - " + this.sf.format(assembleia.getPublicacaoAta());
		}
		return status;
	}

	public void pesquisaAssembleiaSelecionada() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String codigoAssembleia = params.get("codigoAssembleia");
			if (codigoAssembleia != null) {
				AssembleiaDAO dao = new AssembleiaDAO();
				this.assembleia = dao.pesquisaAssembleiaPorCodigo(Integer.parseInt(codigoAssembleia));
				verificaTodosSelecionados();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abreAlteraAssembleia() throws IOException {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/assembleias/alterar-assembleia.xhtml?codigoAssembleia="
							+ this.assembleiaSelecionada.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void geraEditalPdf() {
		String ordem = this.assembleia.getOrdem();
		ordem = ordem.replaceAll("font-size:13px", "");
		ordem = ordem.replaceAll("font-size:10px", "");
		this.assembleia.setOrdem(ordem);
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator;
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			HashMap parametros = new HashMap();
			parametros.put("SUBREPORT_DIR", caminho_relatorio);
			parametros.put("codigo", this.assembleia.getCodigo());
			parametros.put("nome_cnd", StringUtil.trataNomeComposto(this.assembleia.getCondominio().getNome()));
			parametros.put("end_cnd", this.geraEnderecoCondominio(this.assembleia.getCondominio()));

			byte[] retorno = null;
			switch (this.assembleia.getNumPautas()) {
			case 1:
				if (!this.assembleia.getObs().trim().isEmpty()) {
					retorno = rju.geraRel3(parametros, "EditalPdf1Obs", "EditalPdf1Obs", 1);
				} else {
					retorno = rju.geraRel3(parametros, "EditalPdf1SemObs", "EditalPdf1SemObs", 1);
				}
				break;
			case 2:
				if (!this.assembleia.getObs().trim().isEmpty()) {
					retorno = rju.geraRel3(parametros, "EditalPdf2Obs", "EditalPdf2Obs", 1);
				} else {
					retorno = rju.geraRel3(parametros, "EditalPdf2SemObs", "EditalPdf2SemObs", 1);
				}
				break;
			case 3:
				if (!this.assembleia.getObs().trim().isEmpty()) {
					retorno = rju.geraRel3(parametros, "EditalPdf3Obs", "EditalPdf3Obs", 1);
				} else {
					retorno = rju.geraRel3(parametros, "EditalPdf3SemObs", "EditalPdf3SemObs", 1);
				}
				break;
			case 4:
				if (!this.assembleia.getObs().trim().isEmpty()) {
					retorno = rju.geraRel3(parametros, "EditalPdf4Obs", "EditalPdf4Obs", 1);
				} else {
					retorno = rju.geraRel3(parametros, "EditalPdf4SemObs", "EditalPdf4SemObs", 1);
				}
				break;
			case 5:
				if (!this.assembleia.getObs().trim().isEmpty()) {
					retorno = rju.geraRel3(parametros, "EditalPdf5Obs", "EditalPdf5Obs", 1);
				} else {
					retorno = rju.geraRel3(parametros, "EditalPdf5SemObs", "EditalPdf5SemObs", 1);
				}
				break;
			case 6:
				if (!this.assembleia.getObs().trim().isEmpty()) {
					retorno = rju.geraRel3(parametros, "EditalPdf6Obs", "EditalPdf6Obs", 1);
				} else {
					retorno = rju.geraRel3(parametros, "EditalPdf6SemObs", "EditalPdf6SemObs", 1);
				}
				break;
			default:
				break;
			}
			RequestContext.getCurrentInstance().execute("PF('dlgTipoEdital').hide();");

			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.renderResponse();
			facesContext.responseComplete();
			this.downloadPDF(retorno);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Ocorreu um erro ao gerar o relatório!", "Contate o administrador."));
			e.printStackTrace();
		}
	}

	public void geraWord() throws IOException, SQLException {
		ExportWord expo = new ExportWord();
		if (this.assembleia != null) {
			try {
				int pauta = this.assembleia.getNumPautas();

				String nomeCondo = this.assembleia.getCondominio().getNome();
				String endereco = this.assembleia.getEnderecoLocal();
				String tipo = this.assembleia.getTipo();
				Date data = this.assembleia.getDataAssembleia();
				Date hr1 = this.assembleia.getHorario1();
				Date hr2 = this.assembleia.getHorario2();
				String obs1 = this.assembleia.getObsHorario1();
				String obs2 = this.assembleia.getObsHorario2();
				String local = this.assembleia.getLocalAssembleia();
				String ordem = this.assembleia.getOrdem();
				String sind = this.assembleia.getSexo().equals("F") ? "Sindica" : "Sindico";
				String outroLocal = this.assembleia.getLocalAssembleia();
				String obs = this.assembleia.getObs();

				if (pauta == 1) {
					if (!obs.trim().isEmpty()) {
						this.stream = expo.emissorItemUnicoObs(nomeCondo, endereco, tipo, data, hr1, hr2, obs1, obs2,
								local, ordem, sind, outroLocal, obs);
					} else {
						this.stream = expo.emissorItemUnicoSemObs(nomeCondo, endereco, tipo, data, hr1, hr2, obs1, obs2,
								local, ordem, sind, outroLocal);
					}
				} else {
					if (!obs.trim().isEmpty()) {
						this.stream = expo.emissor5Obs(nomeCondo, endereco, tipo, data, hr1, hr2, obs1, obs2, local,
								ordem, sind, outroLocal, obs);
					} else {
						this.stream = expo.emissor5SemObs(nomeCondo, endereco, tipo, data, hr1, hr2, obs1, obs2, local,
								ordem, sind, outroLocal);
					}
				}
				RequestContext.getCurrentInstance().execute("clicaBotao();");
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Ocorreu um erro ao gerar o relatório!", "Contate o administrador."));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Selecione um edital!", ""));
		}
	}

	// Actions
	// ------------------------------------------------------------------------------------
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
			response.setHeader("Content-Disposition", "inline;filename=\"EditalWord.pdf\"");
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

	// Helpers (can be refactored to public utility class)
	// ----------------------------------------

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gerarRelatorioConvocacoesTeste() throws Exception {
		Date dataHoje = new DateTime().withMillisOfDay(0).minusMinutes(1).toDate();
		HashMap parametros = new HashMap();
		parametros.put("dataHoje", dataHoje);
		parametros.put("relListar", this.tipoAssembleiaRelatorio);
		parametros.put("relOrdernarPor", this.ordenacaoRelatorio);

		int codigoGerente = 0;
		if (this.sessaoMB.getGerenteSelecionado() != null) {
			codigoGerente = this.sessaoMB.getGerenteSelecionado().getCodigo();
		}

		try {
			AssembleiaDAO dao = new AssembleiaDAO();
			List<intra_assembleia> listaAssembleias = null;
			if (this.tipoRelatorio.equals("A")) {
				listaAssembleias = dao.pesquisaAtas(this.relDtInicio, this.relDtFim, this.tipoAssembleiaRelatorio,
						this.listaOrdenacao, codigoGerente, this.publicados);
				this.retorno = geraRelAtas(parametros, "Atas", "Atas", 1, listaAssembleias);
			} else {
				listaAssembleias = dao.pesquisaConvocacoes(this.relDtInicio, this.relDtFim,
						this.tipoAssembleiaRelatorio, this.listaOrdenacao, codigoGerente, this.publicados);
				this.retorno = populaRelatorioConvocacoes(parametros, "Convocacoes", "Convocacoes", 1,
						listaAssembleias);
			}
			RequestContext.getCurrentInstance().execute("clicaBotaoDownload();");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] populaRelatorioConvocacoes(Map parametros, String nome_entrada, String nome_saida, int tipo,
			List<intra_assembleia> lstConvocacoes) throws Exception {
		@SuppressWarnings("unused")
		StreamedContent retorno = null;
		byte[] b;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Connection conex = Conexao.getConexaoSiga();
			String caminho_rel = context.getExternalContext().getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros,
					new JRBeanCollectionDataSource(lstConvocacoes));
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case RelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter exporterPdf = new JRPdfExporter();
				extensao_exportada = "pdf";
				exporterPdf.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterPdf.exportReport();
				break;
			case RelatorioJasperUtil.RELATORIO_DOCX:
				JRDocxExporter exporterWord = new JRDocxExporter();
				extensao_exportada = "docx";
				exporterWord.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterWord.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterWord.exportReport();
				break;
			}
			retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
					"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
			conex.close();
		} catch (JRException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível gerar o relatorio." + e, ""));
			throw new Exception("Não foi possível gerar o relatorio.", e);
		} catch (FileNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível gerar o relatorio." + e, ""));
			throw new Exception("Arquivo do relatório não encontrado.", e);
		}
		return b;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] geraRelAtas(Map parametros, String nome_entrada, String nome_saida, int tipo,
			List<intra_assembleia> lstAtas) throws Exception {
		@SuppressWarnings("unused")
		StreamedContent retorno = null;
		byte[] b;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Connection conex = Conexao.getConexao();
			String caminho_rel = context.getExternalContext().getRealPath("relatorios");
			String caminho_jasper = caminho_rel + File.separator + nome_entrada + ".jasper";
			JasperReport relatorio_jasper = (JasperReport) JRLoader.loadObjectFromFile(caminho_jasper);
			JasperPrint impressora_jasper = JasperFillManager.fillReport(relatorio_jasper, parametros,
					new JRBeanCollectionDataSource(lstAtas));
			String extensao_exportada = "";
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			switch (tipo) {
			case RelatorioJasperUtil.RELATORIO_PDF:
				JRPdfExporter exporterPdf = new JRPdfExporter();
				extensao_exportada = "pdf";
				exporterPdf.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterPdf.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterPdf.exportReport();
				break;
			case RelatorioJasperUtil.RELATORIO_DOCX:
				JRDocxExporter exporterWord = new JRDocxExporter();
				extensao_exportada = "docx";
				exporterWord.setExporterInput(new SimpleExporterInput(impressora_jasper));
				exporterWord.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
				exporterWord.exportReport();
				break;
			}
			retorno = new DefaultStreamedContent(new ByteArrayInputStream(output.toByteArray()),
					"application/" + extensao_exportada, nome_saida + "." + extensao_exportada);
			b = JasperExportManager.exportReportToPdf(impressora_jasper);
			conex.close();
		} catch (JRException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível gerar o relatorio." + e, ""));
			throw new Exception("Não foi possível gerar o relatorio.", e);
		} catch (FileNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível gerar o relatorio." + e, ""));
			throw new Exception("Arquivo do relatório não encontrado.", e);
		}
		return b;
	}

	public void limpar() {
		this.assembleia = new intra_assembleia();
		this.listaAssembleias = null;
		this.todosEquipamentos = false;
		this.possuiSegHorario = false;
	}

	public void listarAssembleiasGerente() {
		AssembleiaDAO dao = new AssembleiaDAO();
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				this.listaAssembleias = dao.getListaAssembleias(this.dataInicio);
			} else {
				this.listaAssembleias = dao.getListaAssembleiasPorGerente(this.dataInicio,
						this.sessaoMB.getGerenteSelecionado().getCodigo());
			}
		}
	}

	public void limparFiltroTbl() {
		DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("frmAssembleia:singleDT");
		d.setValue(null);
		RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
	}

	public void insereAprovacaoSindico() {
		if (this.assembleia.isAprovacaoSindico()) {
			this.assembleia.setDataAprovacaoSindico(new Date());
		} else {
			this.assembleia.setDataAprovacaoSindico(null);
		}
	}

}