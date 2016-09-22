package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.LancamentoDAO;
import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.CodigoBarras;
import br.com.oma.intranet.util.IntranetException;
import br.com.oma.intranet.util.RNException;
import br.com.oma.intranet.view.LancamentoView;
import br.com.oma.omaonline.dao.FinanceiroDAO;
import br.com.oma.omaonline.entidades.atbancos;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cpfavor;
import br.com.oma.omaonline.entidades.financeiro_imagem;
import br.com.oma.omaonline.entidades.periodo_lancamento;
import br.com.oma.sigadm.entidades.glbCatalogo_Lcto;

@ViewScoped
@ManagedBean
public class LancamentoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4546909603894519498L;
	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private LancamentoView lv = new LancamentoView();
	private List<cndpagar> listaDeDuplicidade, listaDePendencias;
	private cndpagar pagar = new cndpagar();
	private int duplicidadeTotal;
	private String obslancto;
	private String historico;

	@PostConstruct
	public void init() {
		this.lv.setTipoPagamento("2");
		this.lv.setLeitorOptico(true);
		this.listarCondominios();
		try {
			// TELA DE ALTERAÇÃO
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String codigoLancamento = params.get("codigoLancamento");
			if (codigoLancamento != null && !codigoLancamento.trim().isEmpty()
					&& NumberUtils.isNumber(codigoLancamento)) {
				LancamentoDAO dao = new LancamentoDAO();
				cndpagar lancamento = dao.pesquisaLancamentoPorCodigo(Integer.parseInt(codigoLancamento));
				if (lancamento == null) {
					throw new IntranetException("O lançamento não foi encontrado em nosso banco de dados!");
				} else {
					this.lv.setLancamento(lancamento);
					this.constroiViewAlteracao(dao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getObslancto() {
		return obslancto;
	}

	public void setObslancto(String obslancto) {
		this.obslancto = obslancto;
	}

	public cndpagar getPagar() {
		return pagar;
	}

	public void setPagar(cndpagar pagar) {
		this.pagar = pagar;
	}

	public List<cndpagar> getListaDePendencias() {
		return listaDePendencias;
	}

	public void setListaDePendencias(List<cndpagar> listaDePendencias) {
		this.listaDePendencias = listaDePendencias;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public LancamentoView getLv() {
		return lv;
	}

	public void setLv(LancamentoView lv) {
		this.lv = lv;
	}

	public List<cndpagar> getListaDeDuplicidade() {
		return listaDeDuplicidade;
	}

	public void setListaDeDuplicidade(List<cndpagar> listaDeDuplicidade) {
		this.listaDeDuplicidade = listaDeDuplicidade;
	}

	public int getDuplicidadeTotal() {
		return duplicidadeTotal;
	}

	public void setDuplicidadeTotal(int duplicidadeTotal) {
		this.duplicidadeTotal = duplicidadeTotal;
	}

	public void pesquisarImagemPorEtiqueta() {
		if (this.lv.getEtiqueta() != null && !this.lv.getEtiqueta().trim().isEmpty()) {
			long etiqueta = Long.parseLong(this.lv.getEtiqueta());
			LancamentoDAO dao = new LancamentoDAO();
			this.lv.setImagem(dao.pesquisarImagemPorEtiqueta(etiqueta));
			if (this.lv.getImagem() != null) {
				for (intra_condominios aux : this.lv.getListaCondominios()) {
					if (aux.getCodigo() == this.lv.getImagem().getCdCnd()) {
						this.lv.setCondominio(aux);
					}
				}
				RequestContext.getCurrentInstance().execute("PF('dlgImagem').show();");
				this.listaDePendencias = null;
				this.listarPendencias();
			}
		}
	}

	public void excluirImagem() {
		this.lv.setImagem(null);
		this.lv.setCondominio(null);
	}

	public void excluirFornecedor() {
		this.lv.setFornecedor(null);
	}

	public void salvarNovoLancamento() {
		try {
			LancamentoDAO dao = new LancamentoDAO();
			DateFormat dfData = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dfHora = new SimpleDateFormat("HH");
			if (this.lv.getTipoLancamento() == 1 || this.lv.getTipoLancamento() == 3) {
				if (!this.lv.getValor().trim().isEmpty()) {
					this.validaImagem();
					this.validaFornecedor();
					this.constroiCndpagar(dao);
					this.constroiImagem();
					String venc = dfData.format(this.lv.getVencimento());
					String data = dfData.format(new Date());
					int horaPeriodo = Integer.valueOf(dfHora.format(new Date()));
					periodo_lancamento periodo = dao.pesquisarPeriodo();

					Date dt1;
					Date dt2;

					dt1 = dfData.parse(venc);
					dt2 = dfData.parse(data);

					if (venc.equals(data) & horaPeriodo > periodo.getSegundoPeriodo()) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"Vencimento fora do período de Lançamento!", ""));
					} else if (dt1.before(dt2)) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"Vencimento fora do período de Lançamento!", ""));
					} else {
						this.lv.getLancamento().setCodigoGerente(this.lv.getCondominio().getCodigoGerente());
						dao.salvarNovoLancamento(this.lv.getLancamento(), this.sessaoMB);
						this.limpar();
						this.pagar = new cndpagar();
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Salvo com sucesso!", "Salvo com sucesso!"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Campo valor não informado!", "Campo valor não informado!"));
				}
			} else {
				this.validaImagem();
				this.validaFornecedor();
				this.constroiCndpagar(dao);
				this.constroiImagem();
				
				if(this.lv.getLancamento().getTipoLancamento() == 5){
					this.lv.getLancamento().setFeitoFiscalSIP(new Date());
					this.lv.getLancamento().setStatusSIP(3);
				}
				
				this.lv.getLancamento().setCodigoGerente(this.lv.getCondominio().getCodigoGerente());
				dao.salvarNovoLancamento(this.lv.getLancamento(), this.sessaoMB);
				this.limpar();
				this.pagar = new cndpagar();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Salvo com sucesso!", "Salvo com sucesso!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void salvarAlteracoesLancamento() {
		try {
			LancamentoDAO dao = new LancamentoDAO();
			DateFormat dfData = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat dfHora = new SimpleDateFormat("HH");
			this.lv.getLancamento().setReprovadoFiscal(false);
			if (this.lv.getTipoLancamento() == 1 || this.lv.getTipoLancamento() == 3) {
				if (!this.lv.getValor().trim().isEmpty()) {
					this.validaImagem();
					this.validaFornecedor();
					this.constroiCndpagar(dao);
					this.lv.getLancamento().setImagens(new ArrayList<financeiro_imagem>());
					this.constroiImagem();
					String venc = dfData.format(this.lv.getVencimento());
					String data = dfData.format(new Date());
					int horaPeriodo = Integer.valueOf(dfHora.format(new Date()));
					periodo_lancamento periodo = dao.pesquisarPeriodo();

					Date dt1;
					Date dt2;

					dt1 = dfData.parse(venc);
					dt2 = dfData.parse(data);

					if (venc.equals(data) & horaPeriodo > periodo.getSegundoPeriodo()) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"Vencimento fora do período de Lançamento!", ""));
					} else if (dt1.before(dt2)) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
								"Vencimento fora do período de Lançamento!", ""));
					} else {
						this.lv.getLancamento().setCodigoGerente(this.lv.getCondominio().getCodigoGerente());
						dao.salvarAlteracoesLancamento(this.lv.getLancamento(), this.sessaoMB);
						FacesContext fc = FacesContext.getCurrentInstance();
						NavigationHandler nh = fc.getApplication().getNavigationHandler();
						nh.handleNavigation(fc, null, "/sip/pre-lancamento/pre-lancamentos.xhtml?faces-redirect=true");
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Campo valor não informado!", "Campo valor não informado!"));
				}
			} else {
				this.validaImagem();
				this.validaFornecedor();
				this.constroiCndpagar(dao);
				this.constroiImagem();
				this.lv.getLancamento().setCodigoGerente(this.lv.getCondominio().getCodigoGerente());
				
				dao.salvarAlteracoesLancamento(this.lv.getLancamento(), this.sessaoMB);
				FacesContext fc = FacesContext.getCurrentInstance();
				NavigationHandler nh = fc.getApplication().getNavigationHandler();
				nh.handleNavigation(fc, null, "/sip/pre-lancamento/pre-lancamentos.xhtml?faces-redirect=true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	public void controleDeDuplicidade() {
		LancamentoDAO dao = new LancamentoDAO();
		if (this.pagar.getCodigo() > 0) {
			this.pagar.getImagens().add(this.lv.getImagem());
			this.pagar.setStatusSIP(2);
			this.pagar.setVistoGerente(false);
			this.pagar.setFeitoPreLanctoSIP(new Date());
			this.pagar.setTipoLancamento(1);
			this.pagar.setAdicionadoPor(this.sessaoMB.getUsuario().getEmail());
			this.pagar.setClassificacao(this.lv.getClassificacao());
			this.pagar.setStatus("P");
			this.pagar.setCodigoBarra(this.lv.getCodigoBarras());
			this.carregaLanctoBoleto();
			dao.unificarLancto(pagar);
			this.limpar();
			this.pagar = new cndpagar();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Salvo com sucesso!", "Salvo com sucesso!"));
		} else {
			this.listaDeDuplicidade = dao.pesquisarDuplicidadeTotal(this.lv);
			if (!this.listaDeDuplicidade.isEmpty()) {
				this.duplicidadeTotal = 1;
				RequestContext.getCurrentInstance().execute("PF('duplicidade').show()");
			} else {
				this.listaDeDuplicidade = dao.pesquisarDuplicidade(this.lv);

				if (!this.lv.getCnpj().trim().isEmpty()) {
					if (this.lv.getVencimento() != null) {
						if (!this.listaDeDuplicidade.isEmpty()) {
							this.duplicidadeTotal = 2;
							this.lv.setSemelhante(true);
						} 
							this.salvarNovoLancamento();
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Campo VENCIMENTO obrigatório!", "Campo VENCIMENTO obrigatório!"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Campo CPF/CNPJ obrigatório!", "Campo CPF/CNPJ obrigatório!"));
				}
			}
		}
	}

	public void salvarDebitoAutomatico() {
		try {
			if (this.lv.getDebitoAutomatico().getMes() == 0) {
				UIComponent component = FacesContext.getCurrentInstance().getViewRoot()
						.findComponent("frmNovoLancamento:txtDAMes");
				((UIInput) component).setValid(false);
				throw new IntranetException("O mês deve ser informado!");
			}
			if (this.lv.getDebitoAutomatico().getAno() == 0) {
				UIComponent component = FacesContext.getCurrentInstance().getViewRoot()
						.findComponent("frmNovoLancamento:txtDAAno");
				((UIInput) component).setValid(false);
				throw new IntranetException("O ano deve ser informado!");
			}
			int i = 0;
			LancamentoDAO dao = new LancamentoDAO();
			this.validaImagem();
			glbCatalogo_Lcto gcl = this.constroiDebitoAutomatico();

			i += dao.salvarDebitoAutomatico(gcl);
			double consumo = 0.0;
			if (this.lv.getDebitoAutomatico().getConsumo() != null
					&& !this.lv.getDebitoAutomatico().getConsumo().trim().isEmpty()) {
				consumo = Double.parseDouble(this.lv.getDebitoAutomatico().getConsumo());
			}
			dao.updateConsumo1(consumo, this.lv.getDebitoAutomatico().getMes(), this.lv.getDebitoAutomatico().getAno(),
					gcl.getId_lcto());
			dao.updateHistorico1(this.lv.getDebitoAutomatico().getNrolancto(),
					this.lv.getDebitoAutomatico().getHistorico());
			dao.updateConsumo2(consumo, this.lv.getDebitoAutomatico().getMes(), this.lv.getDebitoAutomatico().getAno(),
					gcl.getId_lcto());
			dao.updateHistorico2(this.lv.getDebitoAutomatico().getNrolancto(),
					this.lv.getDebitoAutomatico().getHistorico());

			gcl.setTipo_lcto("F");
			gcl.setId_lcto(this.lv.getDebitoAutomatico().getIdFatura());
			i += dao.salvarDebitoAutomatico(gcl);
			if (this.lv.getDebitoAutomatico().getEstimado().equals("L")) {
				gcl.setTipo_lcto("L");
				gcl.setId_lcto(this.lv.getDebitoAutomatico().getNroPagto());
				i += dao.salvarDebitoAutomatico(gcl);
			}
			this.limpar();
			if (i > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Salvo com sucesso!", "Salvo com sucesso!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Esta imagem já estava associada à este lançamento!",
								"Esta imagem já estava associada à este lançamento!"));
			}
		} catch (IntranetException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Ocorreu um erro inesperado! Favor, entre em contato com o administrador!", ""));
			e.printStackTrace();
		}
	}

	public void salvarAlteracoesAutomatico() {
		try {
			if (this.lv.getDebitoAutomatico().getMes() == 0) {
				UIComponent component = FacesContext.getCurrentInstance().getViewRoot()
						.findComponent("frmNovoLancamento:txtDAMes");
				((UIInput) component).setValid(false);
				throw new IntranetException("O mês deve ser informado!");
			}
			if (this.lv.getDebitoAutomatico().getAno() == 0) {
				UIComponent component = FacesContext.getCurrentInstance().getViewRoot()
						.findComponent("frmNovoLancamento:txtDAAno");
				((UIInput) component).setValid(false);
				throw new IntranetException("O ano deve ser informado!");
			}
			int i = 0;
			LancamentoDAO dao = new LancamentoDAO();
			this.validaImagem();
			glbCatalogo_Lcto gcl = this.constroiDebitoAutomatico();

			i += dao.salvarDebitoAutomatico(gcl);
			double consumo = 0.0;
			if (this.lv.getDebitoAutomatico().getConsumo() != null
					&& !this.lv.getDebitoAutomatico().getConsumo().trim().isEmpty()) {
				consumo = Double.parseDouble(this.lv.getDebitoAutomatico().getConsumo());
			}
			dao.updateConsumo1(consumo, this.lv.getDebitoAutomatico().getMes(), this.lv.getDebitoAutomatico().getAno(),
					gcl.getId_lcto());
			dao.updateHistorico1(this.lv.getDebitoAutomatico().getNrolancto(),
					this.lv.getDebitoAutomatico().getHistorico());
			dao.updateConsumo2(consumo, this.lv.getDebitoAutomatico().getMes(), this.lv.getDebitoAutomatico().getAno(),
					gcl.getId_lcto());
			dao.updateHistorico2(this.lv.getDebitoAutomatico().getNrolancto(),
					this.lv.getDebitoAutomatico().getHistorico());

			gcl.setTipo_lcto("F");
			gcl.setId_lcto(this.lv.getDebitoAutomatico().getIdFatura());
			i += dao.salvarDebitoAutomatico(gcl);
			if (this.lv.getDebitoAutomatico().getEstimado().equals("L")) {
				gcl.setTipo_lcto("L");
				gcl.setId_lcto(this.lv.getDebitoAutomatico().getNroPagto());
				i += dao.salvarDebitoAutomatico(gcl);
			}
			this.limpar();
			if (i > 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Salvo com sucesso!", "Salvo com sucesso!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Esta imagem já estava associada à este lançamento!",
								"Esta imagem já estava associada à este lançamento!"));
			}
		} catch (IntranetException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Ocorreu um erro inesperado! Favor, entre em contato com o administrador!", ""));
			e.printStackTrace();
		}
	}

	public glbCatalogo_Lcto constroiDebitoAutomatico() throws Exception {
		glbCatalogo_Lcto gcl = new glbCatalogo_Lcto();
		if (this.lv.getImagem() != null) {
			gcl.setId_catalogo(this.lv.getImagem().getId());
			gcl.setId_lcto(this.lv.getDebitoAutomatico().getNrolancto());
			gcl.setTipo_lcto("P");
			if (this.lv.getDebitoAutomatico().getConsumo() == null) {
				this.lv.getDebitoAutomatico().setConsumo("0.0");
			}
		} else {
			throw new IntranetException("Não é possível salvar um Débito Automático sem imagem!");
		}
		return gcl;
	}

	public void pesquisarFornecedor() {
		try {
			LancamentoDAO dao = new LancamentoDAO();
			this.pesquisarFornecedor(dao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public List<nfe> pesquisarXML(LancamentoDAO dao) throws SAXException,
	// IOException, ParserConfigurationException {
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// DocumentBuilder builder = factory.newDocumentBuilder();
	// List<nfe> listaXML = dao.pesquisaXML(this.lv.getCnpj());
	// if (listaXML != null) {
	// this.lv.setListaXML(new ArrayList<nfe>());
	// for (nfe aux : listaXML) {
	// Document document = builder.parse(new
	// ByteArrayInputStream(aux.getXml()));
	// NodeList list = document.getElementsByTagName("emit");
	// Element element = (Element) list.item(0);
	// if (element != null) {
	// this.lv.getListaXML().add(aux);
	// }
	// }
	// if (this.lv.getListaXML() != null && this.lv.getListaXML().size() > 0) {
	// RequestContext.getCurrentInstance().execute("PF('dlgFornecedor').show();");
	// }
	// }
	// return listaXML;
	// }

	public void pesquisarFornecedor(LancamentoDAO dao) {
		if (this.lv.getCnpj() != null && !this.lv.getCnpj().trim().isEmpty()) {
			this.lv.setFornecedor(dao.pesquisarFornecedorSiga(Long.parseLong(this.lv.getCnpj())));
		}
	}

	// public void selecionarXML() {
	// try {
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// DocumentBuilder builder = factory.newDocumentBuilder();
	// Document document;
	// document = builder.parse(new
	// ByteArrayInputStream(this.lv.getNfe().getXml()));
	// NodeList list = document.getElementsByTagName("emit");
	// Element element = (Element) list.item(0);
	// if (element != null) {
	// PFNFE pfnfe = new PFNFE(this.lv.getNfe().getXml());
	// if (pfnfe != null) {
	// this.lv.setVencimento(pfnfe.getDataVencimento());
	// this.lv.setValor(String.valueOf(pfnfe.getValorNF()));
	// this.lv.getFornecedor().setInscricao(Long.parseLong(pfnfe.getCnpjFornecedor()));
	// this.lv.getFornecedor().setNome(pfnfe.getRazaoSocial());
	// this.lv.setEmpresa(pfnfe.getNomeFantasia());
	// this.lv.setNumeroNotaFiscal(pfnfe.getNumeroNF());
	// this.lv.setDataEmissaoNF(pfnfe.getDataEmissao());
	// }
	// RequestContext.getCurrentInstance().execute("PF('dlgFornecedor').hide();");
	// }
	// } catch (SAXException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ParserConfigurationException e) {
	// e.printStackTrace();
	// }
	// }

	public void pesquisarConta() {
		if (this.lv.getNomeConta() == null || this.lv.getNomeConta().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Insira o nome da conta para pesquisar!", "Insira o nome da conta para pesquisar!"));
		} else if (this.lv.getCondominio() == null || this.lv.getCondominio().getCodigo() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"O condomínio deve ser informado!", "O condomínio deve ser informado!"));
		} else {
			FinanceiroDAO dao = new FinanceiroDAO();
			this.lv.setListaConta(
					dao.listarPlanoContaNome(this.lv.getNomeConta(), this.lv.getCondominio().getCodigo()));
			if (this.lv.getListaConta().size() == 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Nenhum resultado encontrado!", "Nenhum resultado encontrado!"));
			} else {
				RequestContext.getCurrentInstance().execute("PF('dlgResultadoConta').show();");
			}
		}
	}

	public void listarCondominios() {
		LancamentoDAO dao = new LancamentoDAO();
		this.lv.setListaCondominios(dao.listarCondominios());
	}

	public void selecionarCC() {
		RequestContext.getCurrentInstance().execute("PF('dlgPesqConta').hide();");
		RequestContext.getCurrentInstance().execute("PF('dlgResultadoConta').hide();");
	}

	public void pesquisaContaCod() {
		FinanceiroDAO dao = new FinanceiroDAO();
		if (this.lv.getContaContabil() != null && this.lv.getCondominio() != null) {
			this.lv.setListaConta(dao.listarPlanoConta(this.lv.getContaContabil().getCod_reduzido(),
					Short.valueOf(String.valueOf(this.lv.getCondominio().getCodigo()))));
			if (this.lv.getListaConta().size() > 0) {
				this.lv.setContaContabil(this.lv.getListaConta().get(0));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Conta não encontrada!", "Conta não encontrada!"));
			}
		}
	}

	public void pesquisarBanco() {
		if (this.lv.getCodBanco() != null) {
			FinanceiroDAO dao = new FinanceiroDAO();
			int valor = Integer.valueOf(this.lv.getCodBanco());
			this.lv.setBanco(dao.pesquisarBanco(valor));
			if (this.lv.getBanco() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Banco não encontrado!", "Banco não encontrado!"));
				this.lv.setNomeDoBanco("");
			} else {
				this.lv.setNomeDoBanco(this.lv.getBanco().getNomeDoBanco());
			}
		} else {
			this.lv.setNomeDoBanco("");
		}
	}

	public boolean listarLinhaDigitavel() {
		boolean sucesso = false;
		this.lv.setDebitoAutomatico(null);
		if (this.lv.getCodigoBarras() != null && !this.lv.getCodigoBarras().trim().isEmpty()) {
			if (this.lv.getCodigoBarras().substring(0, 1).equals("8")) {
				if (this.lv.getCodigoBarras() != null && this.lv.getCodigoBarras().length() > 0) {
					if (this.lv.getCodigoBarras().substring(1, 2).equals("2")
							|| this.lv.getCodigoBarras().substring(1, 2).equals("3")
							|| this.lv.getCodigoBarras().substring(1, 2).equals("4")) {
						this.lv.setTipoPagamento("E");
					} else {
						this.lv.setTipoPagamento("J");
					}
				}
			} else {
				this.lv.setTipoPagamento("8");
			}
			try {
				this.lv.setCodigoBarrasUtil(new CodigoBarras());
				String linha = this.lv.getCodigoBarras();
				this.lv.setLdCampo1("");
				this.lv.setLdCampo2("");
				this.lv.setLdCampo3("");
				this.lv.setLdDac("");
				this.lv.setLdValor("");
				this.lv.setConcSegbarra1("");
				this.lv.setConcSegbarra2("");
				this.lv.setConcSegbarra3("");
				this.lv.setConcSegbarra4("");
				try {
					if (linha.length() == 44) {
						if (this.lv.getTipoPagamento().equals("8")) {
							String l = linha.substring(0, 3);
							if (l.equals("033")) {
								this.lv.setLinhaDigitavel(this.listarSantander(linha));
								this.lv.setLinhaDigitavel(this.validaCodBarras("8", linha));
								this.lv.setLdCampo1(this.lv.getLinhaDigitavel().substring(0, 11));
								this.lv.setLdCampo2(this.lv.getLinhaDigitavel().substring(11, 23));
								this.lv.setLdCampo3(this.lv.getLinhaDigitavel().substring(23, 35));
								this.lv.setLdDac(this.lv.getLinhaDigitavel().substring(35, 36));
								this.lv.setLdValor(this.lv.getLinhaDigitavel().substring(36, 50));
							} else {
								this.lv.setLinhaDigitavel(this.listarBancos(linha));
								this.lv.setLinhaDigitavel(this.validaCodBarras("1", linha));
								this.lv.setLdCampo1(this.lv.getLinhaDigitavel().substring(0, 11));
								this.lv.setLdCampo2(this.lv.getLinhaDigitavel().substring(11, 23));
								this.lv.setLdCampo3(this.lv.getLinhaDigitavel().substring(23, 35));
								this.lv.setLdDac(this.lv.getLinhaDigitavel().substring(35, 36));
								this.lv.setLdValor(this.lv.getLinhaDigitavel().substring(36, 50));
							}
							sucesso = false;
						} else if (this.lv.getTipoPagamento().equals("E") || this.lv.getTipoPagamento().equals("J")) {
							this.lv.setLinhaDigitavel(this.listarConcessionarias(linha));
							this.lv.setLinhaDigitavel(this.validaCodBarras("E", linha));
							this.lv.setConcSegbarra1(this.lv.getLinhaDigitavel().substring(0, 12));
							this.lv.setConcSegbarra2(this.lv.getLinhaDigitavel().substring(12, 24));
							this.lv.setConcSegbarra3(this.lv.getLinhaDigitavel().substring(24, 36));
							this.lv.setConcSegbarra4(this.lv.getLinhaDigitavel().substring(36, 48));
						}

						int venc = Integer.valueOf(this.lv.getCodigoBarras().substring(5, 9));
						if (venc > 0) {
							DateTime dataVenc = new DateTime(1997, 10, 07, 00, 00);
							DateTime dataVencFinal = dataVenc.plusDays(venc);
							this.lv.setVencimento(dataVencFinal.toDate());
						}

						sucesso = true;
					} else {
						sucesso = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Codigo de barras incorreto!", "Codigo de barras incorreto!"));
					}
				} catch (Exception e) {
					sucesso = false;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getMessage()));
				}
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), e.getMessage()));
			}
		} else {
			this.lv.setTipoPagamento("2");
		}

		if (this.lv.getCodigoBarras().length() > 2) {
			if (this.lv.getCodigoBarras().substring(0, 2).equals("82")
					|| this.lv.getCodigoBarras().substring(0, 2).equals("83")
					|| this.lv.getCodigoBarras().substring(0, 2).equals("84")) {
				this.pesquisarProjetadosDA();
			}
		}

		return sucesso;
	}

	public String validaCodBarras(String tipo, String cb) throws RNException {
		switch (tipo) {
		case "8":
			if (!cb.subSequence(0, 1).equals("8")) {
				this.lv.setCodigoBarrasUtil(new CodigoBarras());
				return this.lv.getCodigoBarrasUtil().calculaLinhaBBBrCaIt(cb);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"O tipo de pagamento é diferente do codigo de barras!",
								"O tipo de pagamento é diferente do codigo de barras!"));
				break;
			}
		case "1":
			if (!cb.subSequence(0, 1).equals("8")) {
				this.lv.setCodigoBarrasUtil(new CodigoBarras());
				return this.lv.getCodigoBarrasUtil().calculaLinhaBBBrCaIt(cb);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"O tipo de pagamento é diferente do codigo de barras!",
								"O tipo de pagamento é diferente do codigo de barras!"));
				break;
			}
		case "E":
			if (cb.subSequence(0, 1).equals("8")) {
				this.lv.setCodigoBarrasUtil(new CodigoBarras());
				return this.lv.getCodigoBarrasUtil().calculaConcessionaria(cb);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"O tipo de pagamento é diferente do codigo de barras!",
								"O tipo de pagamento é diferente do codigo de barras!"));
				break;
			}
		}
		return null;
	}

	public void pesquisarFavorecido() {
		FinanceiroDAO dao = new FinanceiroDAO();
		this.lv.setListaFavorecido(dao.listarFavorecido(this.lv.getNomeFavorecido()));
		if (this.lv.getListaFavorecido() != null && this.lv.getListaFavorecido().size() > 0) {
			RequestContext.getCurrentInstance().execute("PF('dlgResultadoFavorecido').show();");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Nenhum resultado encontrado!", "Nenhum resultado encontrado!"));
		}
	}

	public void selecionarFavorecido() {
		if (this.lv.getFavorecido() != null) {
			this.lv.setNomeFavorecido(this.lv.getFavorecido().getFavorecido());
			this.lv.setCodBanco(String.valueOf(this.lv.getFavorecido().getBanco()));
			this.lv.setCodAgencia(String.valueOf(this.lv.getFavorecido().getAgencia()));
			this.lv.setNomeDoBanco(this.lv.getFavorecido().getNomeBanco());
			this.lv.setCc(this.lv.getFavorecido().getContaCorrente());
			this.lv.setDac(this.lv.getFavorecido().getDacConta());
			this.lv.setContaPoupanca(this.lv.getFavorecido().getContaPoupanca());
			this.lv.setTipoPessoa(this.lv.getFavorecido().getTipoPessoa());
			this.lv.setCpf_cnpj(String.valueOf(BigInteger.valueOf(this.lv.getFavorecido().getCnpjCpf())));
		}
		RequestContext.getCurrentInstance().execute("PF('dlgResultadoFavorecido').hide();");
		RequestContext.getCurrentInstance().execute("PF('dlgPesqFavorecido').hide();");
	}

	public String getTipoDocumento() {
		switch (this.lv.getTipoPagamento()) {
		case "2":
			this.lv.setTipoDocumento("NF");
			return "Nota Fiscal";
		case "8":
			this.lv.setTipoDocumento("NF");
			return "Nota Fiscal";
		case "5":
			this.lv.setTipoDocumento("REC.");
			return "Recibo";
		case "7":
			this.lv.setTipoDocumento("REC.");
			return "Recibo";
		case "E":
			this.lv.setTipoDocumento("Fatura");
			return "Fatura";
		case "J":
			this.lv.setTipoDocumento("Fatura");
			return "Fatura";
		default:
			return "";
		}
	}

	public void pesquisarBancosPorNome() {
		if (this.lv.getNomeDoBanco() != null && !this.lv.getNomeDoBanco().trim().isEmpty()) {
			LancamentoDAO dao = new LancamentoDAO();
			this.lv.setListaBancos(dao.pesquisarBancosPorNome(this.lv.getNomeDoBanco()));
			if (this.lv.getListaBancos() == null || this.lv.getListaBancos().size() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Banco não encontrado!", "Banco não encontrado!"));
			} else {
				RequestContext.getCurrentInstance().execute("PF('dlgResultadoBanco').show();");
			}
		}
	}

	public void selecionarBanco() {
		if (this.lv.getBanco() != null) {
			this.lv.setNomeDoBanco(this.lv.getBanco().getNomeDoBanco());
			this.lv.setCodBanco(String.valueOf(this.lv.getBanco().getCodBanco()));
			RequestContext.getCurrentInstance().execute("PF('dlgPesqBanco').hide();");
			RequestContext.getCurrentInstance().execute("PF('dlgResultadoBanco').hide();");
		}
	}

	public boolean listarCodigoBarras() {
		String codigoBarras = null;
		boolean sucesso = true;
		if (this.lv.getTipoPagamento().equals("E") || this.lv.getTipoPagamento().equals("J")) {
			if (this.validaLDConcessionaria()) {
				String parte1 = this.lv.getConcSegbarra1().substring(0, this.lv.getConcSegbarra1().length() - 1);
				String parte2 = this.lv.getConcSegbarra2().substring(0, this.lv.getConcSegbarra2().length() - 1);
				String parte3 = this.lv.getConcSegbarra3().substring(0, this.lv.getConcSegbarra3().length() - 1);
				String parte4 = this.lv.getConcSegbarra4().substring(0, this.lv.getConcSegbarra4().length() - 1);
				codigoBarras = parte1 + parte2 + parte3 + parte4;
				this.lv.setCodigoBarras(codigoBarras);
			} else {
				sucesso = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Insira uma linha digitável válida!", "Insira uma linha digitável válida!"));
				this.lv.setCodigoBarras(codigoBarras);
			}
		} else if (this.lv.getTipoPagamento().equals("8")) {
			if (this.validaLDBoleto()) {
				String linhaDigitavel = this.lv.getLdCampo1() + this.lv.getLdCampo2() + this.lv.getLdCampo3()
						+ this.lv.getLdDac() + this.lv.getLdValor();
				linhaDigitavel = linhaDigitavel.replace(".", "");
				codigoBarras = linhaDigitavel.substring(0, 4) + linhaDigitavel.substring(32, 47)
						+ linhaDigitavel.substring(4, 9) + linhaDigitavel.substring(10, 20)
						+ linhaDigitavel.substring(21, 31);
				this.lv.setCodigoBarras(codigoBarras);
			} else {
				sucesso = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Insira uma linha digitável válida!", "Insira uma linha digitável válida!"));
				this.lv.setCodigoBarras(null);
			}

			int venc = Integer.valueOf(this.lv.getCodigoBarras().substring(5, 9));
			if (venc > 0) {
				DateTime dataVenc = new DateTime(1997, 10, 07, 00, 00);
				DateTime dataVencFinal = dataVenc.plusDays(venc);
				this.lv.setVencimento(dataVencFinal.toDate());
			}

		}

		if (this.lv.getCodigoBarras() != null && this.lv.getCodigoBarras().length() > 2) {
			if (codigoBarras.substring(0, 2).equals("82") || codigoBarras.substring(0, 2).equals("83")
					|| codigoBarras.substring(0, 2).equals("84")) {
				this.pesquisarProjetadosDA();
			}
		}

		return sucesso;
	}

	public boolean validaLDConcessionaria() {
		boolean valido = true;
		if (this.lv.getConcSegbarra1() == null || this.lv.getConcSegbarra1().length() != 12
				|| this.lv.getConcSegbarra1().contains("_")) {
			valido = false;
		}
		if (this.lv.getConcSegbarra2() == null || this.lv.getConcSegbarra2().length() != 12
				|| this.lv.getConcSegbarra2().contains("_")) {
			valido = false;
		}
		if (this.lv.getConcSegbarra3() == null || this.lv.getConcSegbarra3().length() != 12
				|| this.lv.getConcSegbarra3().contains("_")) {
			valido = false;
		}
		if (this.lv.getConcSegbarra4() == null || this.lv.getConcSegbarra4().length() != 12
				|| this.lv.getConcSegbarra4().contains("_")) {
			valido = false;
		}
		return valido;
	}

	public boolean validaLDBoleto() {
		boolean valido = true;
		if (this.lv.getLdCampo1() == null || this.lv.getLdCampo1().length() != 10
				|| this.lv.getLdCampo1().contains("_")) {
			valido = false;
		}
		if (this.lv.getLdCampo2() == null || this.lv.getLdCampo2().length() != 11
				|| this.lv.getLdCampo2().contains("_")) {
			valido = false;
		}
		if (this.lv.getLdCampo3() == null || this.lv.getLdCampo3().length() != 11
				|| this.lv.getLdCampo3().contains("_")) {
			valido = false;
		}
		if (this.lv.getLdDac() == null || this.lv.getLdDac().length() != 1 || this.lv.getLdDac().contains("_")) {
			valido = false;
		}
		if (this.lv.getLdValor() == null || this.lv.getLdValor().length() != 14 || this.lv.getLdValor().contains("_")) {
			valido = false;
		}
		return valido;
	}

	public String listarSantander(String codigo) throws RNException {
		if (!codigo.subSequence(0, 1).equals("8")) {
			this.lv.setCodigoBarrasUtil(new CodigoBarras());
		} else {
			throw new RNException("O tipo de pagamento é diferente do codigo de barras!");
		}
		return this.lv.getCodigoBarrasUtil().calculaSantender(codigo);
	}

	public String listarConcessionarias(String codigo) throws RNException {
		if (codigo.subSequence(0, 1).equals("8")) {
			this.lv.setCodigoBarrasUtil(new CodigoBarras());
		} else {
			throw new RNException("O tipo de pagamento é diferente do codigo de barras!");
		}
		return this.lv.getCodigoBarrasUtil().calculaConcessionaria(codigo);
	}

	public String listarBancos(String codigo) throws RNException {
		if (!codigo.subSequence(0, 1).equals("8")) {
			this.lv.setCodigoBarrasUtil(new CodigoBarras());
		} else {
			throw new RNException("O tipo de pagamento é diferente do codigo de barras!");
		}
		return this.lv.getCodigoBarrasUtil().calculaLinhaBBBrCaIt(codigo);
	}

	public String getHistoricoCompleto() {
		StringBuilder strBuilder = new StringBuilder();
		if (this.lv.getEmpresa() != null && !this.lv.getEmpresa().isEmpty()) {
			strBuilder.append(this.lv.getEmpresa());
			this.lv.getLancamento().setEmpresa(this.lv.getEmpresa());
		}
		if (this.lv.getNumeroNotaFiscal() != null && !this.lv.getNumeroNotaFiscal().isEmpty()) {
			if (this.lv.getEmpresa() != null && !this.lv.getEmpresa().isEmpty()) {
				strBuilder.append(" - ");
			}
			if (!this.lv.getNumeroNotaFiscal().trim().isEmpty()) {
				strBuilder.append(this.lv.getTipoDocumento() + " " + this.lv.getNumeroNotaFiscal());
			}
		}
		if (this.lv.getComplemento() != null && !this.lv.getComplemento().isEmpty()) {
			if ((this.lv.getNumeroNotaFiscal() != null && !this.lv.getNumeroNotaFiscal().isEmpty())
					|| (this.lv.getEmpresa() != null && !this.lv.getEmpresa().isEmpty())) {
				strBuilder.append(" - ");
			}
			strBuilder.append(this.lv.getComplemento());
			this.lv.getLancamento().setHist(this.lv.getComplemento());
		}
		this.lv.getLancamento().setHistorico(strBuilder.toString());
		return strBuilder.toString();
	}

	public void constroiViewAlteracao(LancamentoDAO dao) {
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
		this.lv.setTipoLancamento(this.lv.getLancamento().getTipoLancamento());
		this.lv.setCondominio(dao.pesquisaCondPorCodigo(this.lv.getLancamento().getCondominio()));
		this.lv.setClassificacao(this.lv.getLancamento().getClassificacao());
		this.lv.setVencimento(this.lv.getLancamento().getVencimento());
		this.lv.setValor(df.format(this.lv.getLancamento().getValor()));
		this.lv.setContaContabil(dao.pesquisaContaContabil(this.lv.getLancamento().getConta()));
		if (this.lv.getLancamento().getTipoDocumento() != null) {
			this.lv.setTipoDocumento(this.lv.getLancamento().getTipoDocumento());
		} else {
			this.lv.setTipoDocumento("N.F");
		}
		this.lv.setFornecedor(dao.pesquisarFornecedorUsualcred(this.lv.getLancamento().getCredor()));
		this.lv.setCnpj(this.lv.getLancamento().getFornecedorCnpj());
		this.lv.setNumeroNotaFiscal(String.valueOf(this.lv.getLancamento().getNumeroNf()));
		this.lv.setDataEmissaoNF(this.lv.getLancamento().getEmissaoNf());
		this.lv.setEmpresa(this.lv.getLancamento().getEmpresa());
		this.lv.setComplemento(this.lv.getLancamento().getHist());
		this.lv.setCodigoBarras(this.lv.getLancamento().getCodigoBarra());
		this.lv.setTipoPagamento(this.lv.getLancamento().getTipoPagto());
		if (this.lv.getLancamento().getCodigoBarra() != null
				&& !this.lv.getLancamento().getCodigoBarra().trim().isEmpty()) {
			this.lv.setCodigoBarras(this.lv.getLancamento().getCodigoBarra());
			this.listarLinhaDigitavel();
		}
		if (this.lv.getLancamento().getCodigoBarra() == null
				|| this.lv.getLancamento().getCodigoBarra().trim().isEmpty()) {
			this.listarCodigoBarras();
		}
		if (this.lv.getLancamento().getTipoPagto() != null) {
			if (this.lv.getLancamento().getTipoPagto().equals("5")) {
				cpfavor fav = dao.pesquisarFavorecido(this.lv.getLancamento().getCodigoFav());
				if (fav != null) {
					this.lv.setFavorecido(fav);
				}
				this.lv.setNomeFavorecido(this.lv.getLancamento().getFavorecido());
				this.lv.setCodAgencia(String.valueOf(this.lv.getLancamento().getAgencDestino()));
				this.lv.setCodBanco(String.valueOf(this.lv.getLancamento().getBancoDestino()));
				this.lv.setCc(this.lv.getLancamento().getContaDestino());
				this.lv.setDac(this.lv.getLancamento().getDigAgeDest());
				this.lv.setTipoPessoa(this.lv.getLancamento().getTipoPessoa());
				this.lv.setCpf_cnpj(String.valueOf(this.lv.getLancamento().getCnpj()));
				this.lv.setContaPoupanca(this.lv.getLancamento().getTipoContaBancaria());
			} else if (this.lv.getTipoPagamento().equals("7")) {
				cpfavor fav = dao.pesquisarFavorecido(this.lv.getLancamento().getCodigoFav());
				if (fav != null) {
					this.lv.setFavorecido(fav);
				}
				this.lv.setCodAgencia(String.valueOf(this.lv.getLancamento().getAgencDestino()));
				this.lv.setCodBanco(String.valueOf(this.lv.getLancamento().getBancoDestino()));
				this.lv.getLancamento().setTipoContaBancaria(this.lv.getContaPoupanca());
				this.lv.setCc(this.lv.getLancamento().getContaDestino());
				this.lv.setDac(this.lv.getLancamento().getDigAgeDest());
				this.lv.setTipoPessoa(this.lv.getLancamento().getTipoPessoa());
				this.lv.setCpf_cnpj(String.valueOf(this.lv.getLancamento().getCnpj()));
				this.lv.setTipoPagamento(this.lv.getLancamento().getTipoPagto());
			} else if (this.lv.getTipoPagamento().equals("8")) {
				this.lv.setLdCampo1(String.valueOf(this.lv.getLancamento().getLdCampo1()));
				this.lv.setLdCampo2(String.valueOf(this.lv.getLancamento().getLdCampo2()));
				this.lv.setLdCampo3(String.valueOf(this.lv.getLancamento().getLdCampo3()));
				this.lv.setLdDac(String.valueOf(this.lv.getLancamento().getLdDac()));
				this.lv.setLdValor(String.valueOf(this.lv.getLancamento().getLdValor()));
				this.lv.setTipoPagamento(this.lv.getLancamento().getTipoPagto());
			} else if (this.lv.getTipoPagamento().equals("E") || this.lv.getTipoPagamento().equals("J")) {
				this.lv.setConcSegbarra1(String.valueOf(this.lv.getLancamento().getConcSegbarra1()));
				this.lv.setConcSegbarra2(String.valueOf(this.lv.getLancamento().getConcSegbarra2()));
				this.lv.setConcSegbarra3(String.valueOf(this.lv.getLancamento().getConcSegbarra3()));
				this.lv.setConcSegbarra4(String.valueOf(this.lv.getLancamento().getConcSegbarra4()));
				this.lv.setTipoPagamento(this.lv.getLancamento().getTipoPagto());
			}
		}
		if (this.lv.getLancamento().getImagens() != null && this.lv.getLancamento().getImagens().size() > 0) {
			List<financeiro_imagem> l = new ArrayList<>();
			l.addAll(this.lv.getLancamento().getImagens());
			this.lv.setEtiqueta(String.valueOf(new Double(l.get(0).getId()).longValue()));
			this.lv.setImagem(l.get(0));
		}
	}

	public void constroiCndpagar(LancamentoDAO dao) {
		this.lv.getLancamento().setStatusSIP(2);
		this.lv.getLancamento().setVistoGerente(false);
		this.lv.getLancamento().setFeitoPreLanctoSIP(new Date());
		this.lv.getLancamento().setTipoLancamento(this.lv.getTipoLancamento());
		this.lv.getLancamento().setFeitoPreLanctoSIP(new Date());
		this.lv.getLancamento().setCondominio(Short.parseShort(String.valueOf(this.lv.getCondominio().getCodigo())));
		this.lv.getLancamento().setAdicionadoPor(this.sessaoMB.getUsuario().getEmail());
		this.lv.getLancamento().setDataInclusao(new Date());
		this.lv.getLancamento().setClassificacao(this.lv.getClassificacao());
		this.lv.getLancamento().setVencimento(this.lv.getVencimento());
		this.lv.getLancamento().setRateado("N");
		this.lv.getLancamento().setStatus("P");
		
		if(this.lv.isSemelhante()){
			this.lv.getLancamento().setSemelhante(true);
		}
		
		if (this.lv.getValor() != null && !this.lv.getValor().trim().isEmpty()) {
			this.lv.setValor(this.lv.getValor().replace("R$ ", ""));
			DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
			try {
				// this.lv.getLancamento().setValor(df.parse(this.lv.getValor()).doubleValue());
				this.lv.getLancamento().setValorLancto(df.parse(this.lv.getValor()).doubleValue());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			this.lv.getLancamento().setValor(0.0);
		}
		if (this.lv.getContaContabil() != null) {
			this.lv.getLancamento().setConta(this.lv.getContaContabil().getConta());
			this.lv.getLancamento().setCtaAnlFinanc(Integer.valueOf(this.lv.getContaContabil().getCodigo_grafico()));
		}
		if (this.lv.getTipoDocumento() != null) {
			this.lv.getLancamento().setTipoDocumento(this.lv.getTipoDocumento());
		}
		if (this.lv.getFornecedor() != null) {
			this.lv.getLancamento().setCredor(String.valueOf(this.lv.getFornecedor().getUsualcred()));
		}
		if (this.lv.getCnpj() != null && !this.lv.getCnpj().trim().isEmpty()) {
			this.lv.getLancamento().setFornecedorCnpj(this.lv.getCnpj());
		}
		if (this.lv.getNumeroNotaFiscal() != null && !this.lv.getNumeroNotaFiscal().trim().isEmpty()) {
			this.lv.getLancamento().setNumeroNf(BigInteger.valueOf(Long.valueOf(this.lv.getNumeroNotaFiscal())));
		}
		this.lv.getLancamento().setNf(this.lv.getNumeroNotaFiscal());
		this.lv.getLancamento().setEmissaoNf(this.lv.getDataEmissaoNF());
		StringBuilder strBuilder = new StringBuilder();
		if (this.lv.getEmpresa() != null && !this.lv.getEmpresa().isEmpty()) {
			strBuilder.append(this.lv.getEmpresa());
			this.lv.getLancamento().setEmpresa(this.lv.getEmpresa());
		}
		if (this.lv.getNumeroNotaFiscal() != null && !this.lv.getNumeroNotaFiscal().isEmpty()) {
			if (this.lv.getEmpresa() != null && !this.lv.getEmpresa().isEmpty()) {
				strBuilder.append(" - ");
			}
			if (!this.lv.getNumeroNotaFiscal().trim().isEmpty()) {
				strBuilder.append(this.lv.getTipoDocumento() + " " + this.lv.getNumeroNotaFiscal());
			}
		}
		if (this.lv.getComplemento() != null && !this.lv.getComplemento().isEmpty()) {
			if ((this.lv.getNumeroNotaFiscal() != null && !this.lv.getNumeroNotaFiscal().isEmpty())
					|| (this.lv.getEmpresa() != null && !this.lv.getEmpresa().isEmpty())) {
				strBuilder.append(" - ");
			}
			strBuilder.append(this.lv.getComplemento());
			this.lv.getLancamento().setHist(this.lv.getComplemento());
		}
		this.lv.getLancamento().setHistorico(strBuilder.toString());
		this.lv.getLancamento().setObs("N");
		this.lv.getLancamento().setCodigoBarra(this.lv.getCodigoBarras());
		if (this.lv.getTipoPagamento() != null) {
			if (this.lv.getTipoPagamento().equals("5")) {
				this.lv.getLancamento().setFavorecido(this.lv.getNomeFavorecido());
				this.lv.getLancamento().setAgencDestino(Integer.valueOf(this.lv.getCodAgencia()));
				this.lv.getLancamento().setBancoDestino(Short.valueOf(this.lv.getCodBanco()));
				this.lv.getLancamento().setContaDestino(this.lv.getCc());
				this.lv.getLancamento().setDigAgeDest(this.lv.getDac());
				this.lv.getLancamento().setTipoPessoa(this.lv.getTipoPessoa());
				this.lv.getLancamento().setCnpj(Double.valueOf(this.lv.getCpf_cnpj()));
				this.lv.getLancamento().setTipoPagto(this.lv.getTipoPagamento());
				this.lv.getLancamento().setTipoContaBancaria(this.lv.getContaPoupanca());
				this.lv.getLancamento().setModalPagto(this.lv.getCondominio().getCodigo() == 4241 ? "20" : "");
				this.lv.getLancamento().setParcelado("N");
				short codConta = dao.listContCondo(Short.valueOf(String.valueOf(this.lv.getCondominio().getCodigo())));
				this.lv.getLancamento().setContaBancaria(codConta);
				this.lv.getLancamento().setEstimado("P");
				if (this.lv.getFavorecido() != null && this.lv.getFavorecido().getCodigo() != 0) {
					this.lv.getLancamento().setCodigoFav(this.lv.getFavorecido().getCodigo());
				} else {
					System.out.println("erro favorecido");
				}
			} else if (this.lv.getTipoPagamento().equals("7")) {
				this.lv.getLancamento().setFavorecido(this.lv.getNomeFavorecido());
				this.lv.getLancamento().setAgencDestino(Integer.valueOf(this.lv.getCodAgencia()));
				this.lv.getLancamento().setBancoDestino(Short.valueOf(this.lv.getCodBanco()));
				if (this.lv.getContaPoupanca() != null) {
					if (this.lv.getContaPoupanca().equals("S")) {
						this.lv.getLancamento()
								.setModalPagto((this.lv.getCondominio().getCodigo() == 4241 ? "20" : "11"));
						this.lv.getLancamento().setTipoConta("02");
						this.lv.getLancamento().setTipoContaBancaria(this.lv.getContaPoupanca());
					} else {
						this.lv.getLancamento()
								.setModalPagto((this.lv.getCondominio().getCodigo() == 4241 ? "20" : "01"));
						this.lv.getLancamento().setTipoConta("01");
						this.lv.getLancamento().setTipoContaBancaria(this.lv.getContaPoupanca());
					}
				}
				this.lv.getLancamento().setParcelado("N");
				short codConta = dao.listContCondo(Short.valueOf(String.valueOf(this.lv.getCondominio().getCodigo())));
				this.lv.getLancamento().setContaBancaria(codConta);
				this.lv.getLancamento().setContaDestino(this.lv.getCc());
				this.lv.getLancamento().setDigAgeDest(this.lv.getDac());
				this.lv.getLancamento().setTipoPessoa(this.lv.getTipoPessoa());
				this.lv.getLancamento().setCnpj(Double.valueOf(this.lv.getCpf_cnpj()));
				this.lv.getLancamento().setTipoPagto(this.lv.getTipoPagamento());
				this.lv.getLancamento().setEstimado("R");
				if (this.lv.getFavorecido() != null && this.lv.getFavorecido().getCodigo() != 0) {
					this.lv.getLancamento().setCodigoFav(this.lv.getFavorecido().getCodigo());
				} else {
					System.out.println("erro favorecido");
				}
			} else if (this.lv.getTipoPagamento().equals("8")) {
				short cod = Short.valueOf(String.valueOf(this.lv.getCodigoBarras().subSequence(0, 3)));
				short codConta = dao.listContCondo(Short.valueOf(String.valueOf(this.lv.getCondominio().getCodigo())));
				atbancos c = new atbancos();
				c = dao.listBancoOMA2(cod);
				this.lv.getLancamento().setContaBancaria(codConta);
				this.lv.getLancamento().setBancoDestino(c.getCodBanco());
				this.lv.getLancamento().setFavorecido(c.getNomeDoBanco());

				if (this.lv.getLdCampo1() != null && !this.lv.getLdCampo1().trim().isEmpty()
						&& this.lv.getLdCampo1().trim().length() == 10) {
					this.lv.setLdCampo1(
							this.lv.getLdCampo1().substring(0, 5) + "." + this.lv.getLdCampo1().substring(5, 10));
					this.lv.getLancamento().setLdCampo1(Double.valueOf(this.lv.getLdCampo1()));
				}

				if (this.lv.getLdCampo2() != null && !this.lv.getLdCampo2().trim().isEmpty()
						&& this.lv.getLdCampo2().trim().length() == 11) {
					this.lv.setLdCampo2(
							this.lv.getLdCampo2().substring(0, 5) + "." + this.lv.getLdCampo2().substring(5, 11));
					this.lv.getLancamento().setLdCampo2(Double.valueOf(this.lv.getLdCampo2()));
				}

				if (this.lv.getLdCampo3() != null && !this.lv.getLdCampo3().trim().isEmpty()
						&& this.lv.getLdCampo3().trim().length() == 11) {
					this.lv.setLdCampo3(
							this.lv.getLdCampo3().substring(0, 5) + "." + this.lv.getLdCampo3().substring(5, 11));
					this.lv.getLancamento().setLdCampo3(Double.valueOf(this.lv.getLdCampo3()));
				}

				if (this.lv.getLdDac() != null && !this.lv.getLdCampo1().trim().isEmpty()) {
					this.lv.getLancamento().setLdDac(Integer.valueOf(this.lv.getLdDac()));
				}

				if (this.lv.getLdValor() != null && !this.lv.getLdCampo1().trim().isEmpty()) {
					this.lv.getLancamento().setLdValor(Double.valueOf(this.lv.getLdValor()));
				}

				this.lv.getLancamento().setEstimado("R");
				this.lv.getLancamento().setTipoPagto(this.lv.getTipoPagamento());
				this.lv.getLancamento().setTipoPessoa("J");
				this.lv.getLancamento().setCodigoFav(cod);
				this.lv.getLancamento().setParcelado("N");
			} else if (this.lv.getTipoPagamento().equals("E") || this.lv.getTipoPagamento().equals("J")) {
				short codConta = dao.listContCondo(Short.valueOf(String.valueOf(this.lv.getCondominio().getCodigo())));
				atbancos c = new atbancos();
				c = dao.listBancoOMA(codConta);
				this.lv.getLancamento().setContaBancaria(codConta);
				this.lv.getLancamento().setBancoDestino((short) 0);
				this.lv.getLancamento().setCodigoFav(Integer.valueOf(c.getCodBanco()));
				this.lv.getLancamento().setFavorecido(c.getNomeDoBanco());
				this.lv.getLancamento().setConcSegbarra1(Double.valueOf(this.lv.getConcSegbarra1()));
				this.lv.getLancamento().setConcSegbarra2(Double.valueOf(this.lv.getConcSegbarra2()));
				this.lv.getLancamento().setConcSegbarra3(Double.valueOf(this.lv.getConcSegbarra3()));
				this.lv.getLancamento().setConcSegbarra4(Double.valueOf(this.lv.getConcSegbarra4()));
				this.lv.getLancamento().setTipoPagto(this.lv.getTipoPagto());
				this.lv.getLancamento().setTipoPessoa("J");
				this.lv.getLancamento().setEstimado("R");
				this.lv.getLancamento().setParcelado("N");
			}
		}
	}

	public void constroiImagem() {
		if (this.lv.getImagem() != null) {
			this.lv.getLancamento().getImagens().add(this.lv.getImagem());
		}
	}

	public void limpar() {
		this.lv = new LancamentoView();
		DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmDA:tblDA");
		d.setValue(null);
		RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
		this.init();
	}

	public void updateCamposLD() {
		if (this.lv.getLdInicial() != null && this.lv.getLdInicial().trim().length() > 0) {
			if (this.lv.getLdInicial().substring(0, 1).equals("8")) {
				this.lv.setConcSegbarra1(this.lv.getLdInicial());
				this.lv.setLdCampo1(null);
			} else {
				this.lv.setTipoPagamento("8");
				this.lv.setLdCampo1(this.lv.getLdInicial());
				this.lv.setConcSegbarra1(null);
			}
		}
	}

	public void updateCamposLDBol() {
		this.lv.setLdInicial(this.lv.getLdCampo1());
		if (this.lv.getLdCampo1() != null && !this.lv.getLdCampo1().trim().isEmpty()
				&& this.lv.getLdCampo1().trim().length() > 0) {
			if (this.lv.getLdCampo1().substring(0, 1).equals("8")) {
				if (this.lv.getLdCampo1().length() >= 2) {
					if (this.lv.getLdCampo1().substring(1, 2).equals("2")
							|| this.lv.getLdCampo1().substring(1, 2).equals("3")
							|| this.lv.getLdCampo1().substring(1, 2).equals("4")) {
						this.lv.setTipoPagamento("E");
					} else {
						this.lv.setTipoPagamento("J");
					}
				}
			}
		}
		if (this.lv.getLdCampo1() == null || this.lv.getLdCampo1().trim().isEmpty()) {
			this.lv.setLdCampo2(null);
			this.lv.setLdCampo3(null);
			this.lv.setLdDac(null);
			this.lv.setLdValor(null);
			this.lv.setTipoPagamento(null);
		}
	}

	public void updateCamposLDConc() {
		this.lv.setLdInicial(this.lv.getConcSegbarra1());
		if (this.lv.getConcSegbarra1() != null && !this.lv.getConcSegbarra1().trim().isEmpty()
				&& this.lv.getConcSegbarra1().trim().length() > 0) {
			if (this.lv.getConcSegbarra1().substring(0, 1).equals("8")) {
				if (this.lv.getConcSegbarra1().length() >= 2) {
					if (this.lv.getConcSegbarra1().substring(1, 2).equals("2")
							|| this.lv.getConcSegbarra1().substring(1, 2).equals("3")
							|| this.lv.getConcSegbarra1().substring(1, 2).equals("4")) {
						this.lv.setTipoPagamento("E");
					} else {
						this.lv.setTipoPagamento("J");
					}
				}
			}
		}
		if (this.lv.getConcSegbarra1() == null || this.lv.getConcSegbarra1().trim().isEmpty()) {
			this.lv.setConcSegbarra2(null);
			this.lv.setConcSegbarra3(null);
			this.lv.setConcSegbarra4(null);
			this.lv.setTipoPagamento(null);
		}
	}

	public void limparCBLD() {
		this.lv.setCodigoBarras(null);
		this.lv.setLdInicial(null);
		this.lv.setLdCampo1(null);
		this.lv.setLdCampo2(null);
		this.lv.setLdCampo3(null);
		this.lv.setLdDac(null);
		this.lv.setLdValor(null);
		this.lv.setConcSegbarra1(null);
		this.lv.setConcSegbarra2(null);
		this.lv.setConcSegbarra3(null);
		this.lv.setConcSegbarra4(null);
		this.lv.setTipoPagamento("2");
	}

	public void validaImagem() throws Exception {
		if (this.lv.getImagem() == null) {
			throw new Exception("Não é possível fazer um lançamento sem imagem!");
		}
	}

	public void validaFornecedor() throws Exception {
		if (this.lv.getCnpj() == null || this.lv.getCnpj().trim().isEmpty()) {
			throw new Exception("O fornecedor deve ser informado!");
		}
	}

	public void pesquisarProjetadosDA() {
		try {
			if (this.lv.getImagem() != null && this.lv.getImagem().getCdCnd() > 0) {
				this.lv.setListaDA(new ArrayList<DebitoAutomatico>());
				this.lv.setFltrDA(null);
				DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmDA:tblDA");
				d.setValue(null);
				RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");

				LancamentoDAO dao = new LancamentoDAO();

				List<DebitoAutomatico> ProjetadosEReais = dao.pesquisaDebitoAutomatico(this.lv.getImagem().getCdCnd());
				if (ProjetadosEReais != null && ProjetadosEReais.size() > 0) {
					ProjetadosEReais = this.tratarPR(ProjetadosEReais);
				}

				List<DebitoAutomatico> AguardandoBaixa = dao.pesquisaDebitoAutomaticoAB(this.lv.getImagem().getCdCnd());
				if (AguardandoBaixa != null && AguardandoBaixa.size() > 0) {
					AguardandoBaixa = this.tratarPR(AguardandoBaixa);
				}

				List<DebitoAutomatico> Pagos = dao.pesquisaDebitoAutomaticoPagos(this.lv.getImagem().getCdCnd());

				this.lv.getListaDA().addAll(ProjetadosEReais);
				this.lv.getListaDA().addAll(AguardandoBaixa);
				this.lv.getListaDA().addAll(Pagos);

				if (this.lv.getListaDA() != null && !this.lv.getListaDA().isEmpty()) {

					List<DebitoAutomatico> listaCleaner = new ArrayList<>();
					for (DebitoAutomatico da : this.lv.getListaDA()) {
						int id_lcto = 0;
						String tipo = "";
						if (da.getEstimado().equals("L")) {
							id_lcto = da.getNroPagto();
							tipo = "L";
						} else {
							id_lcto = da.getNrolancto();
							tipo = "P";
						}
						if (this.verificaExisteImagem(dao, id_lcto, tipo)) {
							da.setPossuiImg(true);
						}
						listaCleaner.add(da);
					}

					Collections.sort(listaCleaner);
					this.lv.setListaDA(listaCleaner);
					RequestContext.getCurrentInstance().execute("PF('dlgDebitoAutomatico').show();");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<DebitoAutomatico> tratarPR(List<DebitoAutomatico> lAux) {
		List<DebitoAutomatico> lr = new ArrayList<>();
		List<DebitoAutomatico> lp = new ArrayList<>();
		List<DebitoAutomatico> l = new ArrayList<>();
		for (DebitoAutomatico x : lAux) {
			if (x.getEstimado().trim().equals("R")) {
				lr.add(x);
			} else {
				lp.add(x);
			}
		}
		for (DebitoAutomatico z : lr) {
			Iterator<DebitoAutomatico> itr = lp.iterator();
			while (itr.hasNext()) {
				DebitoAutomatico n = itr.next();
				if (z.getId_contrato() == n.getId_contrato() && n.getEstimado().trim().equals("P")) {
					itr.remove();
				}
			}
		}
		l.addAll(lr);
		l.addAll(lp);
		return l;
	}

	public void selecionarDA() {
		this.lv.getDebitoAutomatico().setMes(0);
		this.lv.getDebitoAutomatico().setAno(0);
		RequestContext.getCurrentInstance().execute("PF('dlgDebitoAutomatico').hide();");
	}

	public String verificaSituacaoDA(String estimado) {
		switch (estimado) {
		case "P":
			return "Projetado";
		case "R":
			return "À pagar";
		case "L":
			return "Pago";
		default:
			return "";
		}
	}

	public boolean verificaExisteImagem(LancamentoDAO dao, int id_lcto, String tipo_lcto) {
		boolean existe = dao.verificaExisteImagem(id_lcto, tipo_lcto);
		return existe;
	}

	public void limparDA() {
		this.lv.setDebitoAutomatico(null);
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getHistorico() {
		StringBuffer str = new StringBuffer();
		if (this.lv != null && this.lv.getDebitoAutomatico() != null && this.lv.getDebitoAutomatico().getUm() != null) {
			String a = this.lv.getDebitoAutomatico().getUm().getObs()
					+ this.getMesAno(this.lv.getDebitoAutomatico().getMes(), this.lv.getDebitoAutomatico().getAno());
			str.append(a);
			if (this.lv.getDebitoAutomatico().getComplemento() != null
					&& !this.lv.getDebitoAutomatico().getComplemento().trim().isEmpty()) {
				str.append(" - " + this.lv.getDebitoAutomatico().getComplemento());
			}
			if (this.lv.getDebitoAutomatico().getConsumo() != null
					&& !this.lv.getDebitoAutomatico().getConsumo().trim().isEmpty()) {
				String b = this.lv.getDebitoAutomatico().getConsumo() + " "
						+ this.lv.getDebitoAutomatico().getUm().getUnidade_medida();
				str.append(" - ");
				str.append(b);
			}
			this.lv.getDebitoAutomatico().setHistorico(str.toString().toUpperCase());
		}
		this.historico = str.toString().toUpperCase();
		return this.historico;
	}

	public String getMesAno(int mes, int ano) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("MMMM/yyyy", new Locale("pt", "BR"));
			Date d = new DateTime().withDayOfMonth(1).withMonthOfYear(mes).withYear(ano).toDate();
			String data = sf.format(d);
			return data;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Mês ou ano inválido!", "Mês ou ano inválido!"));
			return "";
		}
	}

	public void limparParcelado() {
		this.lv.setPci("");
		this.lv.setPcf("");
	}

	public void listarPendencias() {
		LancamentoDAO dao = new LancamentoDAO();
		if (this.lv.getCondominio() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Selecione um condomínio!", ""));
		} else {
			if (this.listaDePendencias == null) {
				this.listaDePendencias = dao.listarPendencias(this.lv.getCondominio());
				if (!this.listaDePendencias.isEmpty()) {
					RequestContext.getCurrentInstance().execute("PF('pendencias').show();");
					RequestContext.getCurrentInstance().execute("frmPendencias");
				}
			}
		}
	}

	public void unificarLancamento() {
		this.lv.setCodigoBarras(this.pagar.getCodigoBarra());
		this.listarLinhaDigitavel();
		this.lv.setVencimento(this.pagar.getVencimento() != null ? this.pagar.getVencimento() : null);
		this.lv.setCnpj(!this.pagar.getFornecedorCnpj().trim().isEmpty() ? this.pagar.getFornecedorCnpj() : "");
		this.lv.setNumeroNotaFiscal(String.valueOf((this.pagar.getNumeroNf() == null ? "" : this.pagar.getNumeroNf())));
		this.lv.setDataEmissaoNF(this.pagar.getEmissaoNf() != null ? this.pagar.getEmissaoNf() : null);
		if (this.pagar.getValorLancto() > 0) {
			DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
			String val = df.format(this.pagar.getValorLancto());
			this.lv.setValor("R$ " + val);
		}
	}

	public void carregaLanctoBoleto() {
		LancamentoDAO dao = new LancamentoDAO();
		try {
			short cod = Short.valueOf(String.valueOf(this.lv.getCodigoBarras().subSequence(0, 3)));
			short codConta = dao.listContCondo(Short.valueOf(String.valueOf(this.lv.getCondominio().getCodigo())));
			atbancos c = new atbancos();
			c = dao.listBancoOMA2(cod);
			this.pagar.setContaBancaria(codConta);
			this.pagar.setBancoDestino(c.getCodBanco());
			this.pagar.setFavorecido(c.getNomeDoBanco());

			if (this.lv.getLdCampo1() != null && !this.lv.getLdCampo1().trim().isEmpty()
					&& this.lv.getLdCampo1().trim().length() == 10) {
				this.lv.setLdCampo1(
						this.lv.getLdCampo1().substring(0, 5) + "." + this.lv.getLdCampo1().substring(5, 10));
				this.pagar.setLdCampo1(Double.valueOf(this.lv.getLdCampo1()));
			}

			if (this.lv.getLdCampo2() != null && !this.lv.getLdCampo2().trim().isEmpty()
					&& this.lv.getLdCampo2().trim().length() == 11) {
				this.lv.setLdCampo2(
						this.lv.getLdCampo2().substring(0, 5) + "." + this.lv.getLdCampo2().substring(5, 11));
				this.pagar.setLdCampo2(Double.valueOf(this.lv.getLdCampo2()));
			}

			if (this.lv.getLdCampo3() != null && !this.lv.getLdCampo3().trim().isEmpty()
					&& this.lv.getLdCampo3().trim().length() == 11) {
				this.lv.setLdCampo3(
						this.lv.getLdCampo3().substring(0, 5) + "." + this.lv.getLdCampo3().substring(5, 11));
				this.pagar.setLdCampo3(Double.valueOf(this.lv.getLdCampo3()));
			}

			if (this.lv.getLdDac() != null && !this.lv.getLdCampo1().trim().isEmpty()) {
				this.pagar.setLdDac(Integer.valueOf(this.lv.getLdDac()));
			}

			if (this.lv.getLdValor() != null && !this.lv.getLdCampo1().trim().isEmpty()) {
				this.pagar.setLdValor(Double.valueOf(this.lv.getLdValor()));
			}

			this.pagar.setEstimado("R");
			this.pagar.setTipoPagto(this.lv.getTipoPagamento());
			this.pagar.setTipoPessoa("J");
			this.pagar.setCodigoFav(cod);
			this.pagar.setParcelado("N");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro na leitura do codigo de barras!", "Erro na leitura do codigo de barras!"));
		}
	}

	public String alerta() {
		return "fa fa-bell-o animated swing infinite red";
	}
}