package br.com.oma.intranet.managedbeans;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.AnaliseFiscalDAO;
import br.com.oma.intranet.dao.ConsultaPreLancamentosDAO;
import br.com.oma.intranet.dao.ParametrosFiscalDAO;
import br.com.oma.intranet.entidades.intra_fiscal_param;
import br.com.oma.intranet.view.Impostos;
import br.com.oma.omaonline.dao.FinanceiroDAO;
import br.com.oma.omaonline.entidades.cndpagar;
import br.com.oma.omaonline.entidades.cndpagar_followup;
import br.com.oma.omaonline.entidades.cpcredor;
import br.com.oma.omaonline.entidades.financeiro_imagem;
import br.com.oma.sigadm.entidades.sgimpos;
import br.com.oma.sigadm.entidades.sgltimp;
import br.com.oma.sigadm.entidades.sgreten;
import de.jollyday.Holiday;
import de.jollyday.HolidayManager;

@ViewScoped
@ManagedBean
public class AnaliseFiscalMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6037557759068430130L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private cndpagar lancamento;
	private cpcredor fornecedor;
	private Impostos impostos = new Impostos();
	private intra_fiscal_param fiscalParam;
	private int codigoImagem;
	private String usualcred;
	private List<financeiro_imagem> listaArquivos = new ArrayList<financeiro_imagem>();
	private List<cndpagar_followup> fltrFollowUp, lstFollowUp;

	private String obsLancto;

	private double retencaoValor;

	public String getUsualcred() {
		return usualcred;
	}

	public void setUsualcred(String usualcred) {
		this.usualcred = usualcred;
	}

	public List<financeiro_imagem> getListaArquivos() {
		return listaArquivos;
	}

	public void setListaArquivos(List<financeiro_imagem> listaArquivos) {
		this.listaArquivos = listaArquivos;
	}

	public List<cndpagar_followup> getFltrFollowUp() {
		return fltrFollowUp;
	}

	public void setFltrFollowUp(List<cndpagar_followup> fltrFollowUp) {
		this.fltrFollowUp = fltrFollowUp;
	}

	public List<cndpagar_followup> getLstFollowUp() {
		return lstFollowUp;
	}

	public void setLstFollowUp(List<cndpagar_followup> lstFollowUp) {
		this.lstFollowUp = lstFollowUp;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public cndpagar getLancamento() {
		return lancamento;
	}

	public void setLancamento(cndpagar lancamento) {
		this.lancamento = lancamento;
	}

	public cpcredor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(cpcredor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Impostos getImpostos() {
		return impostos;
	}

	public void setImpostos(Impostos impostos) {
		this.impostos = impostos;
	}

	public intra_fiscal_param getFiscalParam() {
		if (this.fiscalParam == null) {
			ParametrosFiscalDAO dao = new ParametrosFiscalDAO();
			this.fiscalParam = dao.pesquisarFiscalParam(1);
			if (this.fiscalParam != null) {
				carregarVencimentos();
			}
		}
		return fiscalParam;
	}

	public void setFiscalParam(intra_fiscal_param fiscalParam) {
		this.fiscalParam = fiscalParam;
	}

	public int getCodigoImagem() {
		return codigoImagem;
	}

	public void setCodigoImagem(int codigoImagem) {
		this.codigoImagem = codigoImagem;
	}

	public String getObsLancto() {
		return obsLancto;
	}

	public void setObsLancto(String obsLancto) {
		this.obsLancto = obsLancto;
	}

	@PostConstruct
	public void init() {
		if (this.lancamento == null) {
			try {
				FacesContext ctx = FacesContext.getCurrentInstance();
				Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
				String codigoLancto = params.get("codigoLancto");
				if (codigoLancto != null) {
					AnaliseFiscalDAO dao = new AnaliseFiscalDAO();
					this.lancamento = dao.getLancamento(Integer.parseInt(codigoLancto));
					this.impostos.calculaImpostos(this.lancamento.getValorLancto());
					this.pesquisarFornecedor();
					this.getFiscalParam();

					this.listaArquivos = new ArrayList<>();
					if (this.lancamento.getImagens() != null) {
						this.listaArquivos.addAll(this.lancamento.getImagens());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
			}
		}
	}

	public void aprovarEVoltar() {
		try {
			AnaliseFiscalDAO dao = new AnaliseFiscalDAO();

			if (this.impostos.isUsarPCC() & this.impostos.isUsarIRRF()) {
				this.lancamento.setBaseIrCsll(this.impostos.getBasePCC() + this.impostos.getBaseIRRF());
				this.retencaoValor = this.impostos.getValorPCC() + this.impostos.getValorIRRF();
			} else if (this.impostos.isUsarPCC()) {
				this.lancamento.setBaseIrCsll(this.impostos.getBasePCC());
				this.retencaoValor = this.impostos.getValorPCC();
			} else if (this.impostos.isUsarIRRF()) {
				this.lancamento.setBaseIrCsll(this.impostos.getBaseIRRF());
				this.retencaoValor = this.impostos.getValorIRRF();
			}

			if (this.impostos.isUsarINSS()) {
				this.lancamento.setBaseInss(this.impostos.getBaseINSS());
				this.retencaoValor = this.impostos.getValorINSS();
			}

			if (this.impostos.isUsarISS()) {
				this.lancamento.setBaseIssqn(this.impostos.getBaseISS());
				this.retencaoValor = this.impostos.getValorISS();
			}

			if (this.impostos.isUsarPCC() || this.impostos.isUsarINSS() || this.impostos.isUsarIRRF()
					|| this.impostos.isUsarISS()) {
				this.lancamento.setReterImposto("S");
				this.lancamento.setValorRetencao(this.retencaoValor);

				if (this.impostos.getValorLiquido() > 0) {
					this.lancamento.setValor(this.impostos.getValorLiquido());
				}
			} else {
				this.lancamento.setValor(this.lancamento.getValorLancto());
			}

			this.lancamento.setFeitoFiscalSIP(new Date());
			this.lancamento.setStatusSIP(3);

			if (this.calcularValorCodigoBarras()) {

				if (this.lancamento.getNrolancto() == 0) {
					this.lancamento.setNrolancto(dao.returnUltimoLancto());
				}
				dao.aprovarAnaliseFiscal(this.lancamento, this.sessaoMB);

				if (this.impostos.isUsarPCC() || this.impostos.isUsarINSS() || this.impostos.isUsarIRRF()
						|| this.impostos.isUsarISS()) {
					this.salvarRetencaoSiga(this.lancamento);
				}

				FacesContext fc = FacesContext.getCurrentInstance();
				NavigationHandler nh = fc.getApplication().getNavigationHandler();
				nh.handleNavigation(fc, null, "pre-lancamentos.xhtml?faces-redirect=true");
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Erro - Calculo codigo de barras!", "Erro - Calculo codigo de barras!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void suspenderLancamento() {
		if (this.obsLancto != null) {
			if (!this.obsLancto.trim().isEmpty()) {

				ConsultaPreLancamentosDAO dao = new ConsultaPreLancamentosDAO();
				dao.suspenderLancamentoFiscal(this.lancamento, this.sessaoMB, "Fiscal - " + this.obsLancto);
				this.obsLancto = "";

				FacesContext fc = FacesContext.getCurrentInstance();
				NavigationHandler nh = fc.getApplication().getNavigationHandler();
				nh.handleNavigation(fc, null, "pre-lancamentos.xhtml?faces-redirect=true");

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Suspenso", "Suspenso"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Obrigatório colocar uma observação!", "Obrigatório colocar uma observação!"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Obrigatório colocar uma observação!", "Obrigatório colocar uma observação!"));
		}

	}

	public void liberarLancamento() {
		ConsultaPreLancamentosDAO dao = new ConsultaPreLancamentosDAO();
		dao.liberarLancamentoFiscal(this.lancamento, this.sessaoMB, "Fiscal - " + this.obsLancto);
		this.obsLancto = "";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Liberado", "Liberado"));

		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, "pre-lancamentos.xhtml?faces-redirect=true");

	}

	public boolean calcularValorCodigoBarras() {
		try {
			DecimalFormat df = new DecimalFormat("#.00");
			if (this.lancamento.getCodigoBarra() != null && !this.lancamento.getCodigoBarra().trim().isEmpty()) {
				if (this.lancamento.getTipoPagto().equals("8")) {
					String val = this.lancamento.getCodigoBarra().substring(10, 17);
					val = val + ".";
					val = val + this.lancamento.getCodigoBarra().substring(17, 19);
					double resultado = Double.valueOf(val);

					if (resultado == 0.0) {
						return true;
					} else {

						String resultadoFinal = df.format(resultado);
						String valor = df.format(this.impostos.getValorLiquido());

						if (resultadoFinal.trim().equals(valor)) {
							return true;
						} else {
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_WARN,
											"Valor liquido diferente do valor boleto!",
											"Valor liquido diferente do valor boleto!"));
							RequestContext.getCurrentInstance().execute("frmAnaliseFiscal");
							return false;
						}
					}
				} else {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public void aprovarEIrParaProximo() {
		try {
			AnaliseFiscalDAO dao = new AnaliseFiscalDAO();
			// this.lancamento.setPreLancamento(false);

			if (this.impostos.isUsarPCC() & this.impostos.isUsarIRRF()) {
				this.lancamento.setBaseIrCsll(this.impostos.getBasePCC() + this.impostos.getBaseIRRF());
				this.retencaoValor = this.impostos.getValorPCC() + this.impostos.getValorIRRF();
			} else if (this.impostos.isUsarPCC()) {
				this.lancamento.setBaseIrCsll(this.impostos.getBasePCC());
				this.retencaoValor = this.impostos.getValorPCC();
			} else if (this.impostos.isUsarIRRF()) {
				this.lancamento.setBaseIrCsll(this.impostos.getBaseIRRF());
				this.retencaoValor = this.impostos.getValorIRRF();
			}

			if (this.impostos.isUsarINSS()) {
				this.lancamento.setBaseInss(this.impostos.getBaseINSS());
				this.retencaoValor = this.impostos.getValorINSS();
			}

			if (this.impostos.isUsarISS()) {
				this.lancamento.setBaseIssqn(this.impostos.getBaseISS());
				this.retencaoValor = this.impostos.getValorISS();
			}

			if (this.impostos.isUsarPCC() || this.impostos.isUsarINSS() || this.impostos.isUsarIRRF()
					|| this.impostos.isUsarISS()) {
				this.lancamento.setReterImposto("S");
				this.lancamento.setValorRetencao(this.retencaoValor);

				if (this.impostos.getValorLiquido() > 0) {
					this.lancamento.setValor(this.impostos.getValorLiquido());
				}
			} else {
				this.lancamento.setValor(this.lancamento.getValorLancto());
			}

			this.lancamento.setFeitoFiscalSIP(new Date());

			this.lancamento.setStatusSIP(3);

			if (this.lancamento.getNrolancto() == 0) {
				this.lancamento.setNrolancto(dao.returnUltimoLancto());
			}

			if (this.calcularValorCodigoBarras()) {
				dao.aprovarAnaliseFiscal(this.lancamento, this.sessaoMB);

				if (this.impostos.isUsarPCC() || this.impostos.isUsarINSS() || this.impostos.isUsarIRRF()
						|| this.impostos.isUsarISS()) {
					this.salvarRetencaoSiga(this.lancamento);
				}

				List<cndpagar> listaPreLancamento = dao.getListaPreLancamento();
				if (listaPreLancamento != null && listaPreLancamento.size() > 0) {
					cndpagar proximoLancamento = listaPreLancamento.get(0);
					String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
					FacesContext.getCurrentInstance().getExternalContext().redirect(
							caminho + "/sip/fiscal/analise-fiscal?codigoLancto=" + proximoLancamento.getCodigo());
				} else {
					FacesContext fc = FacesContext.getCurrentInstance();
					NavigationHandler nh = fc.getApplication().getNavigationHandler();
					nh.handleNavigation(fc, null, "pre-lancamentos.xhtml?faces-redirect=true");
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Erro - Calculo codigo de barras!", "Erro - Calculo codigo de barras!"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarRetencaoSiga(cndpagar pagar) {
		AnaliseFiscalDAO dao = new AnaliseFiscalDAO();
		cpcredor credorBean = new cpcredor();
		credorBean = dao.pesquisarFornecedorSiga(pagar.getCredor());
		double cnpjCondoBean = dao.pesquisarCondominioCNPJ(pagar.getCondominio());

		dao.excluirImpostosExistentes(pagar.getNrolancto());

		sgreten retenBean = new sgreten();
		retenBean.setNrolancto(pagar.getNrolancto());
		retenBean.setSistema(53);
		retenBean.setCodigo(pagar.getCondominio());
		retenBean.setCredor(credorBean.getUsualcred());
		retenBean.setCnpj(credorBean.getInscricao());
		retenBean.setVencimento(pagar.getVencimento());
		retenBean.setValor(pagar.getValorLancto());
		retenBean.setValor_base(pagar.getValorLancto());
		retenBean.setValor_retencao(pagar.getValorRetencao());
		retenBean.setValor_csll(0.00);
		retenBean.setValor_cofins(0.00);
		retenBean.setValor_pis(0.00);
		retenBean.setOrigem(1);
		retenBean.setCnpj_cliente(cnpjCondoBean);

		dao.salvarSGRETEN(retenBean);

		sgltimp timpBean = new sgltimp();
		timpBean.setNrolancto(pagar.getNrolancto());
		timpBean.setCodigo(pagar.getCondominio());
		timpBean.setBloco("0");
		timpBean.setData_inclusao(pagar.getDataInclusao());
		timpBean.setVencimento(pagar.getVencimento());
		timpBean.setValor(pagar.getValorLancto());
		timpBean.setHistorico(pagar.getHistorico());
		timpBean.setCta_bancaria(pagar.getContaBancaria());
		timpBean.setCredor(credorBean.getUsualcred());
		timpBean.setData_base(pagar.getEmissaoNf());
		timpBean.setDocumento(pagar.getNumeroNf().toString());
		timpBean.setTotal_retencao(pagar.getValorRetencao());
		timpBean.setSistema(53);
		timpBean.setRepassar("N");
		timpBean.setDiv_propriet("N");
		timpBean.setCpmf("N");

		if (this.impostos.isUsarPCC()) {
			timpBean.setFavorecido("MINISTERIO DA FAZENDA");
		} else if (this.impostos.isUsarINSS() & this.impostos.isUsarIRRF() & this.impostos.isUsarPCC()
				& this.impostos.isUsarISS()) {
			timpBean.setFavorecido("MINISTERIO DA FAZENDA");
		} else if ((this.impostos.isUsarINSS() & this.impostos.isUsarISS())
				|| (this.impostos.isUsarINSS() & this.impostos.isUsarIRRF())) {
			timpBean.setFavorecido("MINIST.TRAB.PREV.SOCIAL.");
		} else if (this.impostos.isUsarIRRF() & this.impostos.isUsarISS()) {
			timpBean.setFavorecido("MINISTERIO DA FAZENDA");
		} else if (this.impostos.isUsarINSS()) {
			timpBean.setFavorecido("MINIST.TRAB.PREV.SOCIAL.");
		} else if (this.impostos.isUsarISS()) {
			timpBean.setFavorecido("PREFEITURA DO MUNICIPIO DE SAO PAULO");
		} else if (this.impostos.isUsarIRRF()) {
			timpBean.setFavorecido("MINISTERIO DA FAZENDA");
		}

		timpBean.setData_alteracao(null);
		dao.salvarSGLTIMP(timpBean);

		sgimpos imposBean = new sgimpos();
		imposBean.setNrolancto(pagar.getNrolancto());
		imposBean.setControle(0);

		if (this.impostos.isUsarINSS()) {
			imposBean.setTipo_imposto(1);
			imposBean.setCod_receita(2631);
			imposBean.setVencto(this.impostos.getVencINSS());
			imposBean.setPercentual(this.impostos.getINSS());
			imposBean.setValor(pagar.getValorLancto());
			imposBean.setCod_ccm(credorBean.getCcm());
			imposBean.setObs_1(null);
			imposBean.setObs_2(null);
			imposBean.setDt_base(pagar.getVencimento());
			imposBean.setCod_municip(credorBean.getCod_munic());
			imposBean.setVencto_guia(null);
			imposBean.setSistema(53);
			imposBean.setConta_contabil(pagar.getConta());
			imposBean.setTipopagto("D");
			imposBean.setValor_a_pagar(this.impostos.getValorINSS());
			imposBean.setData_alteracao(null);
			imposBean.setNumero_nf(pagar.getNumeroNf().toString());

			dao.salvarSGIMPOS(imposBean);

		}
		if (this.impostos.isUsarISS()) {
			imposBean.setTipo_imposto(2);
			imposBean.setCod_receita(9679);
			imposBean.setVencto(this.impostos.getVencISS());
			imposBean.setPercentual(this.impostos.getISS());
			imposBean.setValor(pagar.getValorLancto());
			imposBean.setCod_ccm(credorBean.getCcm());
			imposBean.setObs_1(null);
			imposBean.setObs_2(null);
			imposBean.setDt_base(pagar.getVencimento());
			imposBean.setCod_municip(credorBean.getCod_munic());
			imposBean.setVencto_guia(null);
			imposBean.setSistema(53);
			imposBean.setConta_contabil(pagar.getConta());
			imposBean.setTipopagto("K");
			imposBean.setValor_a_pagar(this.impostos.getValorISS());

			imposBean.setData_alteracao(null);
			imposBean.setNumero_nf(pagar.getNumeroNf().toString());

			dao.salvarSGIMPOS(imposBean);
		}
		if (this.impostos.isUsarIRRF()) {
			imposBean.setTipo_imposto(3);
			imposBean.setCod_receita(1708);
			imposBean.setVencto(this.impostos.getVencIRRF());
			imposBean.setPercentual(this.impostos.getIRRF());
			imposBean.setValor(pagar.getValorLancto());
			imposBean.setCod_ccm(credorBean.getCcm());
			imposBean.setObs_1(null);
			imposBean.setObs_2(null);
			imposBean.setDt_base(pagar.getVencimento());
			imposBean.setCod_municip(credorBean.getCod_munic());
			imposBean.setVencto_guia(null);
			imposBean.setSistema(53);
			imposBean.setConta_contabil(pagar.getConta());
			imposBean.setTipopagto("F");
			imposBean.setValor_a_pagar(this.impostos.getValorIRRF());

			imposBean.setData_alteracao(null);
			imposBean.setNumero_nf(pagar.getNumeroNf().toString());

			dao.salvarSGIMPOS(imposBean);
		}
		if (this.impostos.isUsarPCC()) {
			imposBean.setTipo_imposto(4);
			imposBean.setCod_receita(5952);
			imposBean.setVencto(this.impostos.getVencPCC());
			imposBean.setPercentual(this.impostos.getPCC());
			imposBean.setValor(pagar.getValorLancto());
			imposBean.setCod_ccm(credorBean.getCcm());
			imposBean.setObs_1(null);
			imposBean.setObs_2(null);
			imposBean.setDt_base(pagar.getVencimento());
			imposBean.setCod_municip(credorBean.getCod_munic());
			imposBean.setVencto_guia(null);
			imposBean.setSistema(53);
			imposBean.setConta_contabil(pagar.getConta());
			imposBean.setTipopagto("F");
			imposBean.setValor_a_pagar(this.impostos.getValorPCC());

			imposBean.setData_alteracao(null);
			imposBean.setNumero_nf(pagar.getNumeroNf().toString());

			dao.salvarSGIMPOS(imposBean);

		}
	}

	public void excluirLancamento() {
		try {
			AnaliseFiscalDAO dao = new AnaliseFiscalDAO();
			dao.excluirLancamento(this.lancamento);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "pre-lancamentos.xhtml?faces-redirect=true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pesquisarFornecedor() {
		if (this.lancamento.getCredor() != null && !this.lancamento.getCredor().trim().isEmpty()
				&& !this.lancamento.getCredor().trim().equals(".")) {
			AnaliseFiscalDAO dao = new AnaliseFiscalDAO();
			this.fornecedor = dao.pesquisarFornecedorSiga(this.lancamento.getCredor());
			if (this.fornecedor != null) {
				if (this.fornecedor.isReter_impostos()) {
					double total = this.fornecedor.getPerc_pis() + this.fornecedor.getPerc_cofins()
							+ this.fornecedor.getPerc_csll();
					this.impostos.setUsarPCC(true);
					this.impostos.setPCC(total);
				}
				if (this.fornecedor.isReter_inss()) {
					this.impostos.setUsarINSS(true);
					this.impostos.setINSS(this.fornecedor.getPerc_inss());
				}
				if (this.fornecedor.isReter_irrf()) {
					this.impostos.setUsarIRRF(true);
					this.impostos.setIRRF(this.fornecedor.getPerc_irrf());
				}
				if (this.fornecedor.isReter_iss()) {
					this.impostos.setUsarISS(true);
					this.impostos.setISS(this.fornecedor.getPerc_iss());
				}
				this.impostos.calculaValorImpostos();
				this.impostos.calculaValorLiquido();
			}
		}
	}

	public void carregarVencimentos() {
		Date vencPCC = null, vencINSS = null, vencIRRF = null, vencISS = null;

		// PCC
		if (this.fiscalParam.isUsarParamPCC()) {
			if (this.fiscalParam.getMesPCC() == 1) {
				DateTime vencimento = new DateTime(verificaParamLancamento(this.fiscalParam.getParamPCC()));
				vencPCC = new DateTime().withYear(vencimento.getYear()).withMonthOfYear(vencimento.getMonthOfYear())
						.withDayOfMonth(this.fiscalParam.getDiaPCC()).toDate();
				vencPCC = this.retornaDataVenc(new DateTime(vencPCC), this.fiscalParam.getAntecedenciaPCC());
			} else {
				DateTime vencimento = new DateTime(verificaParamLancamento(this.fiscalParam.getParamPCC()));
				vencPCC = new DateTime().withYear(vencimento.getYear()).withMonthOfYear(vencimento.getMonthOfYear())
						.withDayOfMonth(this.fiscalParam.getDiaPCC()).plusMonths(1).toDate();
				vencPCC = this.retornaDataVenc(new DateTime(vencPCC), this.fiscalParam.getAntecedenciaPCC());
			}
			this.impostos.setVencPCC(vencPCC);
		}

		// INSS
		if (this.fiscalParam.isUsarParamINSS()) {
			if (this.fiscalParam.getMesINSS() == 1) {
				DateTime vencimento = new DateTime(verificaParamLancamento(this.fiscalParam.getParamINSS()));
				vencINSS = new DateTime().withYear(vencimento.getYear()).withMonthOfYear(vencimento.getMonthOfYear())
						.withDayOfMonth(this.fiscalParam.getDiaINSS()).toDate();
				vencINSS = this.retornaDataVenc(new DateTime(vencINSS), this.fiscalParam.getAntecedenciaINSS());
			} else {
				DateTime vencimento = new DateTime(verificaParamLancamento(this.fiscalParam.getParamINSS()));
				vencINSS = new DateTime().withYear(vencimento.getYear()).withMonthOfYear(vencimento.getMonthOfYear())
						.withDayOfMonth(this.fiscalParam.getDiaINSS()).plusMonths(1).toDate();
				vencINSS = this.retornaDataVenc(new DateTime(vencINSS), this.fiscalParam.getAntecedenciaINSS());
			}
			this.impostos.setVencINSS(vencINSS);
		}

		// IRRF
		if (this.fiscalParam.isUsarParamIRRF()) {
			if (this.fiscalParam.getMesIRRF() == 1) {
				DateTime vencimento = new DateTime(verificaParamLancamento(this.fiscalParam.getParamIRRF()));
				vencIRRF = new DateTime().withYear(vencimento.getYear()).withMonthOfYear(vencimento.getMonthOfYear())
						.withDayOfMonth(this.fiscalParam.getDiaIRRF()).toDate();
				vencIRRF = this.retornaDataVenc(new DateTime(vencIRRF), this.fiscalParam.getAntecedenciaIRRF());
			} else {
				DateTime vencimento = new DateTime(verificaParamLancamento(this.fiscalParam.getParamIRRF()));
				vencIRRF = new DateTime().withYear(vencimento.getYear()).withMonthOfYear(vencimento.getMonthOfYear())
						.withDayOfMonth(this.fiscalParam.getDiaIRRF()).plusMonths(1).toDate();
				vencIRRF = this.retornaDataVenc(new DateTime(vencIRRF), this.fiscalParam.getAntecedenciaIRRF());
			}
			this.impostos.setVencIRRF(vencIRRF);
		}

		// ISS
		if (this.fiscalParam.isUsarParamISS()) {
			if (this.fiscalParam.getMesISS() == 1) {
				DateTime vencimento = new DateTime(verificaParamLancamento(this.fiscalParam.getParamISS()));
				vencISS = new DateTime().withYear(vencimento.getYear()).withMonthOfYear(vencimento.getMonthOfYear())
						.withDayOfMonth(this.fiscalParam.getDiaISS()).toDate();
				vencISS = this.retornaDataVenc(new DateTime(vencISS), this.fiscalParam.getAntecedenciaISS());
			} else {
				DateTime vencimento = new DateTime(verificaParamLancamento(this.fiscalParam.getParamISS()));
				vencISS = new DateTime().withYear(vencimento.getYear()).withMonthOfYear(vencimento.getMonthOfYear())
						.withDayOfMonth(this.fiscalParam.getDiaISS()).plusMonths(1).toDate();
				vencISS = this.retornaDataVenc(new DateTime(vencISS), this.fiscalParam.getAntecedenciaISS());
			}
			this.impostos.setVencISS(vencISS);
		}
	}

	public Date verificaParamLancamento(int param) {
		if (this.lancamento != null) {
			if (param == 1) {
				return this.lancamento.getVencimento();
			} else {
				return this.lancamento.getEmissaoNf();
			}
		} else {
			return null;
		}
	}

	public Date retornaDataVenc(DateTime data, int antecedencia) {
		DateTime novaData = null;
		try {
			if (data != null) {
				novaData = data.minusDays(antecedencia);
				HolidayManager m = HolidayManager.getInstance(de.jollyday.HolidayCalendar.BRAZIL);
				Set<Holiday> holidays = m.getHolidays(novaData.getYear(), "BR", "s");
				for (Holiday feriado : holidays) {
					if (novaData.getDayOfYear() == this.converteHolidayParaDate(feriado).getDayOfYear()) {
						novaData = novaData.minusDays(1);
					}
				}
				if (new DateTime(novaData).getDayOfWeek() == 6) {
					novaData = novaData.minusDays(1);
				}
				if (new DateTime(novaData).getDayOfWeek() == 7) {
					novaData = novaData.minusDays(2);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return novaData.toDate();
	}

	public DateTime converteHolidayParaDate(Holiday feriado) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return new DateTime(sf.parse(new DateTime(feriado.getDate().toDateTimeAtCurrentTime()).getDayOfMonth() + "/"
				+ new DateTime(feriado.getDate().toDateTimeAtCurrentTime()).getMonthOfYear() + "/"
				+ new DateTime(feriado.getDate().toDateTimeAtCurrentTime()).getYear() + " "
				+ new DateTime(feriado.getDate().toDateTimeAtCurrentTime()).getHourOfDay() + ":"
				+ new DateTime(feriado.getDate().toDateTimeAtCurrentTime()).getMinuteOfHour()));
	}

	public String validaVencimento(Date d) {
		DateTime vencimento = new DateTime(d);
		DateTime dataHoje = new DateTime(new Date());
		if (vencimento.isBefore(dataHoje) || (vencimento.getDayOfYear() == dataHoje.getDayOfYear()
				&& vencimento.getYear() == dataHoje.getYear())) {
			return "invalido";
		} else {
			return "valido";
		}
	}

	public void cadastraFornecedor() {

		AnaliseFiscalDAO dao = new AnaliseFiscalDAO();
		this.fornecedor = dao.pesquisarFornecedorSiga(this.usualcred.toUpperCase().trim());

		if (this.fornecedor != null) {
			this.lancamento.setCredor(this.fornecedor.getUsualcred());
			this.lancamento.setFornecedorCnpj(String.valueOf(this.fornecedor.getInscricao()));
			dao.updateLancamento(this.lancamento);
			RequestContext.getCurrentInstance().execute("atualizar();");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, cadastre FORNECEDOR no SIGA", ""));
		}
	}

	public void alteraValorBruto() {
		AnaliseFiscalDAO dao = new AnaliseFiscalDAO();
		dao.updateLancamento(this.lancamento);
		RequestContext.getCurrentInstance().execute("atualizar();");
	}

	public String converte(long value) {
		BigInteger teste = BigInteger.valueOf(value);
		return teste.toString();
	}

	public void visualizarImagem(int index) throws FileNotFoundException {
		this.codigoImagem = (int) this.listaArquivos.get(index).getId();
	}

	public void abrirFollowUp() {
		FinanceiroDAO dao = new FinanceiroDAO();
		this.lstFollowUp = dao.listarFollowUp(this.lancamento.getCodigo());
		if (this.lstFollowUp.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Não há nenhum Follow Up para este lançamento!", "Não há nenhum Follow Up para este lançamento!"));
		} else {
			RequestContext.getCurrentInstance().execute("PF('dlgFollowUp').show();");
		}
	}

	public void reprovar() {
		if (this.obsLancto != null && !this.obsLancto.trim().isEmpty()) {
			try {
				this.lancamento.setReprovadoFiscal(true);
				AnaliseFiscalDAO dao = new AnaliseFiscalDAO();
				dao.reprovar(this.lancamento, this.sessaoMB, "Fiscal - " + this.obsLancto);
				FacesContext fc = FacesContext.getCurrentInstance();
				NavigationHandler nh = fc.getApplication().getNavigationHandler();
				nh.handleNavigation(fc, null, "pre-lancamentos.xhtml?faces-redirect=true");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Obrigatório colocar uma observação!", "Obrigatório colocar uma observação!"));
		}
	}

}
