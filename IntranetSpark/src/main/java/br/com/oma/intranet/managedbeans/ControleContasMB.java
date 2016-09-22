package br.com.oma.intranet.managedbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.oma.intranet.dao.ControleContasDAO;
import br.com.oma.intranet.dao.ControleContasValidaDAO;
import br.com.oma.intranet.dao.PC_FAVDAO;
import br.com.oma.intranet.entidades.intra_controle_contas;
import br.com.oma.intranet.entidades.intra_plano_contas;
import br.com.oma.intranet.util.Conta;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ManagedBean(name = "ccMB")
@ViewScoped
public class ControleContasMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3225519458743117710L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private ControleContasDAO ccrDAO;
	private PC_FAVDAO pcFavDAO;
	private ControleContasValidaDAO ccvDAO;
	private intra_controle_contas iccBEAN = new intra_controle_contas();
	private intra_controle_contas icc2BEAN = new intra_controle_contas();
	private intra_plano_contas planoBEAN = new intra_plano_contas();

	// ATRIBUTOS
	private List<intra_plano_contas> listaPlanoContas, filtroPlanoContas;
	private List<intra_controle_contas> listarContas, filtrarContas, listaDeExclusao;
	private List<Object[]> listaDeCondominios;
	private List<SelectItem> listaDeAno;
	private List<SelectItem> status;
	private String valor1;

	private int anoVigente;
	private String valorMes;
	private Date mesConta;
	private int orange1;
	private int red1;
	private int black1;
	private int qtda;
	private boolean validaAno;

	private Date dataRelInicio;
	private Date dataRelFim;
	private boolean avencer, vencido, recebido;
	private String nomeRelatorio;

	private int mesStatus;

	// GET X SET
	/**
	 * @param sessaoMB
	 *            the sessaoMB to set
	 */
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	/**
	 * @return the planoBEAN
	 */
	public intra_plano_contas getPlanoBEAN() {
		return planoBEAN;
	}

	/**
	 * @param planoBEAN
	 *            the planoBEAN to set
	 */
	public void setPlanoBEAN(intra_plano_contas planoBEAN) {
		this.planoBEAN = planoBEAN;
	}

	/**
	 * @return the listaPlanoContas
	 */
	public List<intra_plano_contas> getListaPlanoContas() {
		if (this.listaPlanoContas == null) {
			this.pcFavDAO = new PC_FAVDAO();
			this.listaPlanoContas = this.pcFavDAO.listarPlanoContas();
		}
		return listaPlanoContas;
	}

	/**
	 * @param listaPlanoContas
	 *            the listaPlanoContas to set
	 */
	public void setListaPlanoContas(List<intra_plano_contas> listaPlanoContas) {
		this.listaPlanoContas = listaPlanoContas;
	}

	/**
	 * @return the filtroPlanoContas
	 */
	public List<intra_plano_contas> getFiltroPlanoContas() {
		return filtroPlanoContas;
	}

	/**
	 * @param filtroPlanoContas
	 *            the filtroPlanoContas to set
	 */
	public void setFiltroPlanoContas(List<intra_plano_contas> filtroPlanoContas) {
		this.filtroPlanoContas = filtroPlanoContas;
	}

	public Date getDataRelInicio() {
		return dataRelInicio;
	}

	public void setDataRelInicio(Date dataRelInicio) {
		this.dataRelInicio = dataRelInicio;
	}

	public Date getDataRelFim() {
		return dataRelFim;
	}

	public void setDataRelFim(Date dataRelFim) {
		this.dataRelFim = dataRelFim;
	}

	public boolean isAvencer() {
		return avencer;
	}

	public void setAvencer(boolean avencer) {
		this.avencer = avencer;
	}

	public boolean isVencido() {
		return vencido;
	}

	public void setVencido(boolean vencido) {
		this.vencido = vencido;
	}

	public boolean isRecebido() {
		return recebido;
	}

	public void setRecebido(boolean recebido) {
		this.recebido = recebido;
	}

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public intra_controle_contas getIccBEAN() {
		return iccBEAN;
	}

	public void setIccBEAN(intra_controle_contas iccBEAN) {
		this.iccBEAN = iccBEAN;
	}

	public List<SelectItem> getListaDeAno() {
		DateTime dt = new DateTime();
		this.listaDeAno = new ArrayList<>();
		int val = 0;
		val = Integer.valueOf(dt.getYear());
		this.anoVigente = val;
		for (int i = 1; i <= 3; i++) {
			listaDeAno.add(new SelectItem(val - 1));
			val++;
		}
		return this.listaDeAno;
	}

	public void setListaDeAno(List<SelectItem> listaDeAno) {
		this.listaDeAno = listaDeAno;
	}

	public int getAnoVigente() {
		return anoVigente;
	}

	public void setAnoVigente(int anoVigente) {
		this.anoVigente = anoVigente;
	}

	public List<intra_controle_contas> getListarContas() {
		if (this.listarContas == null) {
			this.ccrDAO = new ControleContasDAO();
			this.ccvDAO = new ControleContasValidaDAO();
			if (this.sessaoMB.getUsuario().getPerfil().trim().equals("5")) {
				this.listarContas = ccrDAO.listarContas(this.anoVigente);
			} else {
				this.listarContas = ccrDAO.listarContas(this.sessaoMB.getGerenteSelecionado().getCodigo(),
						this.anoVigente);
			}
			this.listaDeCondominios = this.ccrDAO.listarContaVinculada();
		}
		return listarContas;
	}

	public void setListarContas(List<intra_controle_contas> listarContas) {
		this.listarContas = listarContas;
	}

	public List<SelectItem> getStatus() {
		this.status = new ArrayList<>();
		status.add(new SelectItem(""));
		for (int i = 1; i < 5; i++) {
			status.add(new SelectItem(i));
		}
		return status;
	}

	public void setStatus(List<SelectItem> status) {
		this.status = status;
	}

	public int getOrange1() {
		return orange1;
	}

	public void setOrange1(int orange1) {
		this.orange1 = orange1;
	}

	public int getRed1() {
		return red1;
	}

	public void setRed1(int red1) {
		this.red1 = red1;
	}

	public int getBlack1() {
		return black1;
	}

	public void setBlack1(int black1) {
		this.black1 = black1;
	}

	public String getValor1() {
		return valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	public Date getMesConta() {
		return mesConta;
	}

	public void setMesConta(Date mesConta) {
		this.mesConta = mesConta;
	}

	public String getValorMes() {
		return valorMes;
	}

	public void setValorMes(String valorMes) {
		this.valorMes = valorMes;
	}

	public int getQtda() {
		return qtda;
	}

	public void setQtda(int qtda) {
		this.qtda = qtda;
	}

	public boolean isValidaAno() {
		return validaAno;
	}

	public void setValidaAno(boolean validaAno) {
		this.validaAno = validaAno;
	}

	public intra_controle_contas getIcc2BEAN() {
		return icc2BEAN;
	}

	public void setIcc2BEAN(intra_controle_contas icc2bean) {
		icc2BEAN = icc2bean;
	}

	public int getMesStatus() {
		LocalDate hoje = new LocalDate();
		this.mesStatus = Integer.valueOf(hoje.toString("MM", new Locale("pt", "BR")).toString());
		return mesStatus;
	}

	public void setMesStatus(int mesStatus) {
		this.mesStatus = mesStatus;
	}

	public List<intra_controle_contas> getListaDeExclusao() {
		return listaDeExclusao;
	}

	public void setListaDeExclusao(List<intra_controle_contas> listaDeExclusao) {
		this.listaDeExclusao = listaDeExclusao;
	}

	public List<Object[]> getListaDeCondominios() {
		return listaDeCondominios;
	}

	public void setListaDeCondominios(List<Object[]> listaDeCondominios) {
		this.listaDeCondominios = listaDeCondominios;
	}

	public void novaLinha() {
		DateTime dt = new DateTime();
		int mes_atual = dt.getMonthOfYear();
		this.ccrDAO = new ControleContasDAO();
		this.iccBEAN = new intra_controle_contas();
		iccBEAN.setCod_geren(this.sessaoMB.getGerenteSelecionado().getCodigo());
		iccBEAN.setVencimento(1);
		iccBEAN.setAno(this.anoVigente);
		iccBEAN.setMes_atual(mes_atual);
		ccrDAO.create(iccBEAN);
		this.iccBEAN = new intra_controle_contas();
		this.listarContas = null;
	}

	public int status(int tipo) {
		this.ccrDAO = new ControleContasDAO();
		int val = ccrDAO.listarStatus(this.sessaoMB.getGerenteSelecionado().getCodigo(), this.mesStatus,
				this.anoVigente, this.sessaoMB.getUsuario().getPerfil(), tipo);
		return val;
	}

	public String validaLegenda(int legenda_id) {
		String legenda = null;
		switch (legenda_id) {
		case 0:
			legenda = "white";
			break;
		case 1:
			legenda = "verde";
			break;
		case 2:
			legenda = "laranja";
			break;
		case 3:
			legenda = "vermelho";
			break;
		case 4:
			legenda = "preto";
			break;
		default:
			legenda = "white";
		}
		return legenda;
	}

	public void carregaPlanoContas() {
		this.iccBEAN.setTipo(Integer.valueOf(this.planoBEAN.getCodReduzido()));
	}

	@SuppressWarnings("unused")
	public void salvarNovaConta() {
		SimpleDateFormat data = new SimpleDateFormat("dd");
		boolean val1;
		int venc;
		this.ccrDAO = new ControleContasDAO();
		DateTime dt = new DateTime();
		this.iccBEAN = this.icc2BEAN;

		if (this.iccBEAN == null) {
			String condo = String.valueOf(this.iccBEAN.getCondominio());
			if (condo.length() < 3) {
				this.msgCondominio();
			} else {
				if (this.anoVigente > 0) {
					int mes_atual = dt.getMonthOfYear();
					this.iccBEAN = new intra_controle_contas();
					if (this.sessaoMB.getUsuario().getPerfil().trim().equals("5")
							|| this.sessaoMB.verificaDepto(" Todos")) {
						iccBEAN.setCod_geren(this.ccrDAO.listarCodigoGerente(this.iccBEAN.getCondominio()));
					} else {
						iccBEAN.setCod_geren(this.sessaoMB.getGerenteSelecionado().getCodigo());
					}

					iccBEAN.setVencimento(1);
					iccBEAN.setAno(this.anoVigente);
					iccBEAN.setMes_atual(mes_atual);
					ccrDAO.create(iccBEAN);
					this.iccBEAN = new intra_controle_contas();
					this.listarContas = null;
				} else {
					this.msgSelecioneAno();
				}
			}
		} else {
			String condo = String.valueOf(this.iccBEAN.getCondominio());
			if (condo.length() < 3) {
				this.msgCondominio();
			} else {
				if (this.anoVigente > 0) {

					int mes_atual = dt.getMonthOfYear();
					iccBEAN.setMes_atual(mes_atual);
					iccBEAN.setAno(this.anoVigente);

					iccBEAN.setCod_geren(this.sessaoMB.getGerenteSelecionado().getCodigo());
					this.iccBEAN.setNomeGerente(this.sessaoMB.getGerenteSelecionado().getNome());

					iccBEAN.setStatus_jan(iccBEAN.getMes1().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_fev(iccBEAN.getMes2().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_mar(iccBEAN.getMes3().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_abr(iccBEAN.getMes4().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_mai(iccBEAN.getMes5().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_jun(iccBEAN.getMes6().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_jul(iccBEAN.getMes7().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_ago(iccBEAN.getMes8().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_set(iccBEAN.getMes9().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_out(iccBEAN.getMes10().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_nov(iccBEAN.getMes11().trim().isEmpty() ? 0 : 1);
					iccBEAN.setStatus_dez(iccBEAN.getMes12().trim().isEmpty() ? 0 : 1);

					val1 = ccrDAO.salvar(iccBEAN, this.sessaoMB);

					if (val1 = true) {
						this.msgAdicinado();
						DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
								.findComponent("frmTabelaContas:dtControlContas");
						d.setValue(null);
						this.listarContas = null;
						this.filtrarContas = null;
						RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
						this.iccBEAN = new intra_controle_contas();
						this.icc2BEAN = new intra_controle_contas();
					} else {
						this.msgErro();
					}
				} else {
					this.msgSelecioneAno();
				}
			}
		}
	}

	public void alterarConta() {
		if (this.iccBEAN != null) {
			String condo = String.valueOf(this.iccBEAN.getCondominio());
			if (condo.length() < 3) {
				this.msgCondominio();
			} else {
				this.ccrDAO = new ControleContasDAO();
				if (this.sessaoMB.getUsuario().getPerfil().trim().equals("5")
						|| this.sessaoMB.verificaDepto(" Todos")) {
					iccBEAN.setCod_geren(this.ccrDAO.listarCodigoGerente(this.iccBEAN.getCondominio()));
				}
				boolean val = ccrDAO.alteraConta(this.iccBEAN, this.sessaoMB);
				if (val == true) {
					this.ccvDAO = new ControleContasValidaDAO();
					ccvDAO.list2(this.iccBEAN, this.sessaoMB);
					this.msgAlterado();
					this.iccBEAN = new intra_controle_contas();
					DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
							.findComponent("frmTabelaContas:dtControlContas");
					d.setValue(null);
					this.listarContas = null;
					this.filtrarContas = null;
					RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
				} else {
					this.msgErro();
				}
			}
		}
	}

	public void limparConta() {
		this.iccBEAN = new intra_controle_contas();
	}

	public void validaMes() {

	}

	public void validaConta(int tipo) {
		this.iccBEAN.setTipo(tipo);
		this.iccBEAN.setTipo(tipo);
	}

	public void teste(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Car Selected", ((intra_controle_contas) event.getObject()).getDespesa());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void excluiConta() {
		if (this.iccBEAN != null) {
			this.ccrDAO = new ControleContasDAO();
			boolean sucesso = this.ccrDAO.excluiConta(this.iccBEAN, this.sessaoMB);
			if (sucesso) {
				this.msgExclusao();
				DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
						.findComponent("frmTabelaContas:dtControlContas");
				d.setValue(null);
				this.listarContas = null;
				this.filtrarContas = null;
				RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
			} else {
				this.msgErro();
			}
		} else {
			this.msgExcluirConta();
		}
	}

	public void excluir() {
		this.ccrDAO = new ControleContasDAO();
		if (this.listaDeExclusao != null) {
			if (!this.listaDeExclusao.isEmpty()) {
				for (intra_controle_contas ccExcluir : listaDeExclusao) {
					this.ccrDAO.excluirConta(ccExcluir, this.sessaoMB);
				}
				DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
						.findComponent("frmTabelaContas:dtControlContas");
				d.setValue(null);

				DataTable d3 = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
						.findComponent("frmControleExclusao:dtControleExclusao");
				d3.setValue(null);

				this.listaDeExclusao = null;
				this.listarContas = null;
				this.filtrarContas = null;
				RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
				this.msgExclusao();
			} else {
				this.msgSelecione();
			}
		} else {
			this.msgSelecione();
		}
	}

	public List<intra_controle_contas> getFiltrarContas() {
		return filtrarContas;
	}

	public void setFiltrarContas(List<intra_controle_contas> filtrarContas) {
		this.filtrarContas = filtrarContas;
	}

	public void mudaAno() {
		this.listarContas = null;
	}

	@SuppressWarnings("rawtypes")
	public void geraRelatorioContasAVencer() throws Exception {
		this.ccrDAO = new ControleContasDAO();
		List<intra_controle_contas> lista = this.ccrDAO
				.geraRelatorioContasAVencer(this.sessaoMB.getGerenteSelecionado().getCodigo(), this.anoVigente);
		List<Conta> listaContas = new ArrayList<>();
		Conta conta = null;
		for (intra_controle_contas aux : lista) {

			// JANEIRO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(1).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes1 = new DateTime(new DateTime().withMonthOfYear(1)
						.withYear(this.anoVigente).withDayOfMonth(new DateTime().withYear(this.anoVigente)
								.withMonthOfYear(1).withDayOfMonth(1).plusMonths(1).minusDays(1).getDayOfMonth())
						.toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes1)
						&& new DateTime(this.dataRelFim).isAfter(dataMes1)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes1.toDate());
					conta.setValor(aux.getMes1());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_jan()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_jan())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes1 = new DateTime(new DateTime().withMonthOfYear(1).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes1)
						&& new DateTime(this.dataRelFim).isAfter(dataMes1)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes1.toDate());
					conta.setValor(aux.getMes1());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_jan()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_jan())) {
						listaContas.add(conta);
					}
				}
			}

			// FEVEREIRO
			if (aux.getVencimento() > 28) {
				DateTime dataMes2 = new DateTime(new DateTime().withMonthOfYear(2)
						.withYear(this.anoVigente).withDayOfMonth(new DateTime().withYear(this.anoVigente)
								.withMonthOfYear(2).withDayOfMonth(1).plusMonths(1).minusDays(1).getDayOfMonth())
						.toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes2)
						&& new DateTime(this.dataRelFim).isAfter(dataMes2)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes2.toDate());
					conta.setValor(aux.getMes3());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_fev()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_fev())) {
						listaContas.add(conta);
					}
				}

			} else {
				DateTime dataMes2 = new DateTime(new DateTime().withMonthOfYear(2).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes2)
						&& new DateTime(this.dataRelFim).isAfter(dataMes2)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes2.toDate());
					conta.setValor(aux.getMes3());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_fev()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_fev())) {
						listaContas.add(conta);
					}
				}
			}

			// MARÇO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(3).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes3 = new DateTime(new DateTime().withMonthOfYear(3).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes3)
						&& new DateTime(this.dataRelFim).isAfter(dataMes3)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes3.toDate());
					conta.setValor(aux.getMes3());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_mar()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_mar())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes3 = new DateTime(new DateTime().withMonthOfYear(3).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes3)
						&& new DateTime(this.dataRelFim).isAfter(dataMes3)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes3.toDate());
					conta.setValor(aux.getMes3());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_mar()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_mar())) {
						listaContas.add(conta);
					}
				}
			}

			// ABRIL
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(4).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes4 = new DateTime(new DateTime().withMonthOfYear(4).withYear(this.anoVigente)
						.withDayOfMonth(new DateTime().withYear(this.anoVigente).withMonthOfYear(4).withDayOfMonth(1)
								.plusMonths(1).minusDays(1).getDayOfMonth()));
				if (new DateTime(this.dataRelInicio).isBefore(dataMes4)
						&& new DateTime(this.dataRelFim).isAfter(dataMes4)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes4.toDate());
					conta.setValor(aux.getMes4());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_abr()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_abr())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes4 = new DateTime(new DateTime().withMonthOfYear(4).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes4)
						&& new DateTime(this.dataRelFim).isAfter(dataMes4)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes4.toDate());
					conta.setValor(aux.getMes4());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_abr()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_abr())) {
						listaContas.add(conta);
					}
				}
			}

			// MAIO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(5).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes5 = new DateTime(new DateTime().withMonthOfYear(5).withYear(this.anoVigente)
						.withDayOfMonth(new DateTime().withYear(this.anoVigente).withMonthOfYear(5).withDayOfMonth(1)
								.plusMonths(1).minusDays(1).getDayOfMonth()));
				if (new DateTime(this.dataRelInicio).isBefore(dataMes5)
						&& new DateTime(this.dataRelFim).isAfter(dataMes5)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes5.toDate());
					conta.setValor(aux.getMes5());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_mai()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_mai())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes5 = new DateTime(new DateTime().withMonthOfYear(5).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes5)
						&& new DateTime(this.dataRelFim).isAfter(dataMes5)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes5.toDate());
					conta.setValor(aux.getMes5());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_mai()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_mai())) {
						listaContas.add(conta);
					}
				}
			}

			// JUNHO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(6).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes6 = new DateTime(new DateTime().withMonthOfYear(6).withYear(this.anoVigente)
						.withDayOfMonth(new DateTime().withYear(this.anoVigente).withMonthOfYear(6).withDayOfMonth(1)
								.plusMonths(1).minusDays(1).getDayOfMonth()));
				if (new DateTime(this.dataRelInicio).isBefore(dataMes6)
						&& new DateTime(this.dataRelFim).isAfter(dataMes6)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes6.toDate());
					conta.setValor(aux.getMes6());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_jun()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_jun())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes6 = new DateTime(new DateTime().withMonthOfYear(6).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes6)
						&& new DateTime(this.dataRelFim).isAfter(dataMes6)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes6.toDate());
					conta.setValor(aux.getMes6());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_jun()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_jun())) {
						listaContas.add(conta);
					}
				}
			}

			// JULHO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(7).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes7 = new DateTime(new DateTime().withMonthOfYear(7).withYear(this.anoVigente)
						.withDayOfMonth(new DateTime().withYear(this.anoVigente).withMonthOfYear(7).withDayOfMonth(1)
								.plusMonths(1).minusDays(1).getDayOfMonth()));
				if (new DateTime(this.dataRelInicio).isBefore(dataMes7)
						&& new DateTime(this.dataRelFim).isAfter(dataMes7)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes7.toDate());
					conta.setValor(aux.getMes7());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_jul()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_jul())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes7 = new DateTime(new DateTime().withMonthOfYear(7).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes7)
						&& new DateTime(this.dataRelFim).isAfter(dataMes7)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes7.toDate());
					conta.setValor(aux.getMes7());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_jul()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_jul())) {
						listaContas.add(conta);
					}
				}
			}

			// AGOSTO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(8).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes8 = new DateTime(new DateTime().withMonthOfYear(8).withYear(this.anoVigente)
						.withDayOfMonth(new DateTime().withYear(this.anoVigente).withMonthOfYear(8).withDayOfMonth(1)
								.plusMonths(1).minusDays(1).getDayOfMonth()));
				if (new DateTime(this.dataRelInicio).isBefore(dataMes8)
						&& new DateTime(this.dataRelFim).isAfter(dataMes8)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes8.toDate());
					conta.setValor(aux.getMes8());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_ago()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_ago())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes8 = new DateTime(new DateTime().withMonthOfYear(8).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes8)
						&& new DateTime(this.dataRelFim).isAfter(dataMes8)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes8.toDate());
					conta.setValor(aux.getMes8());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_ago()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_ago())) {
						listaContas.add(conta);
					}
				}
			}

			// SETEMBRO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(9).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes9 = new DateTime(new DateTime().withMonthOfYear(9).withYear(this.anoVigente)
						.withDayOfMonth(new DateTime().withYear(this.anoVigente).withMonthOfYear(9).withDayOfMonth(1)
								.plusMonths(1).minusDays(1).getDayOfMonth()));
				if (new DateTime(this.dataRelInicio).isBefore(dataMes9)
						&& new DateTime(this.dataRelFim).isAfter(dataMes9)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes9.toDate());
					conta.setValor(aux.getMes9());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_set()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_set())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes9 = new DateTime(new DateTime().withMonthOfYear(9).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes9)
						&& new DateTime(this.dataRelFim).isAfter(dataMes9)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes9.toDate());
					conta.setValor(aux.getMes9());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_set()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_set())) {
						listaContas.add(conta);
					}
				}
			}

			// OUTUBRO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(10).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes10 = new DateTime(new DateTime().withMonthOfYear(10).withYear(this.anoVigente)
						.withDayOfMonth(new DateTime().withYear(this.anoVigente).withMonthOfYear(10).withDayOfMonth(1)
								.plusMonths(1).minusDays(1).getDayOfMonth()));
				if (new DateTime(this.dataRelInicio).isBefore(dataMes10)
						&& new DateTime(this.dataRelFim).isAfter(dataMes10)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes10.toDate());
					conta.setValor(aux.getMes10());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_out()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_out())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes10 = new DateTime(new DateTime().withMonthOfYear(10).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes10)
						&& new DateTime(this.dataRelFim).isAfter(dataMes10)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes10.toDate());
					conta.setValor(aux.getMes10());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_out()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_out())) {
						listaContas.add(conta);
					}
				}
			}

			// NOVEMBRO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(11).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes11 = new DateTime(new DateTime().withMonthOfYear(11).withYear(this.anoVigente)
						.withDayOfMonth(new DateTime().withYear(this.anoVigente).withMonthOfYear(11).withDayOfMonth(1)
								.plusMonths(1).minusDays(1).getDayOfMonth()));
				if (new DateTime(this.dataRelInicio).isBefore(dataMes11)
						&& new DateTime(this.dataRelFim).isAfter(dataMes11)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes11.toDate());
					conta.setValor(aux.getMes11());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_nov()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_nov())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes11 = new DateTime(new DateTime().withMonthOfYear(11).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes11)
						&& new DateTime(this.dataRelFim).isAfter(dataMes11)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes11.toDate());
					conta.setValor(aux.getMes11());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_nov()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_nov())) {
						listaContas.add(conta);
					}
				}
			}

			// DEZEMBRO
			if (aux.getVencimento() > new DateTime().withYear(this.anoVigente).withMonthOfYear(12).withDayOfMonth(1)
					.plusMonths(1).minusDays(1).getDayOfMonth()) {
				DateTime dataMes12 = new DateTime(new DateTime().withMonthOfYear(12).withYear(this.anoVigente)
						.withDayOfMonth(new DateTime().withYear(this.anoVigente).withMonthOfYear(12).withDayOfMonth(1)
								.plusMonths(1).minusDays(1).getDayOfMonth()));
				if (new DateTime(this.dataRelInicio).isBefore(dataMes12)
						&& new DateTime(this.dataRelFim).isAfter(dataMes12)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes12.toDate());
					conta.setValor(aux.getMes12());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_dez()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_dez())) {
						listaContas.add(conta);
					}
				}
			} else {
				DateTime dataMes12 = new DateTime(new DateTime().withMonthOfYear(12).withYear(this.anoVigente)
						.withDayOfMonth(aux.getVencimento()).toDate());
				if (new DateTime(this.dataRelInicio).isBefore(dataMes12)
						&& new DateTime(this.dataRelFim).isAfter(dataMes12)) {
					conta = new Conta();
					conta.setConta(aux.getTipo());
					conta.setVencimento(dataMes12.toDate());
					conta.setValor(aux.getMes12());
					conta.setCodigoCondominio(aux.getCondominio());
					conta.setSituacao(this.verificaSituacao(aux.getStatus_dez()));
					conta.setNomeConta(aux.getDespesa());
					if (insereRelatorioSituacao(aux.getStatus_dez())) {
						listaContas.add(conta);
					}
				}
			}
		}

		Collections.sort(listaContas);

		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		HashMap parametros = new HashMap();
		byte[] retorno = null;
		try {
			retorno = rju.geraRelatorioContas(parametros, "RelatorioContas", "RelatorioContas", 1, listaContas);
			this.nomeRelatorio = this.exportarRelatorio(1, retorno);
			RequestContext.getCurrentInstance().execute("PF('dlgRelatorioContas').show();");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String exportarRelatorio(int tipo, byte[] arquivo) throws IOException {
		String nome_relatorio = this.getRandomName();
		String caminho_relatorio = "";
		if (arquivo != null) {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator
					+ nome_relatorio + ".pdf";
			try {
				FileOutputStream output;
				output = new FileOutputStream(new File(caminho_relatorio));
				output.write(arquivo);
				output.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			caminho_relatorio = "vazio";
		}
		return nome_relatorio;
	}

	public String getRandomName() {
		int i = (int) (Math.random() * 100000);
		return String.valueOf(i);
	}

	public String verificaSituacao(int situacao) {
		String retorno = "";
		switch (situacao) {
		case 1:
			retorno = "Recebido";
			break;
		case 4:
			retorno = "Vencido";
			break;
		default:
			retorno = "A vencer";
			break;
		}
		return retorno;
	}

	public boolean insereRelatorioSituacao(int situacao) {
		if (situacao == 1 && this.recebido) {
			return true;
		} else if (situacao == 4 && this.vencido) {
			return true;
		} else if (situacao == 2 && this.avencer) {
			return true;
		} else if (situacao == 3 && this.avencer) {
			return true;
		} else {
			return false;
		}
	}

	public boolean contaVincualda(String condominio) {
		int c = Integer.valueOf(condominio);
		if (this.listaDeCondominios != null) {
			for (Object[] condo : listaDeCondominios) {
				if (Integer.valueOf(condo[0].toString()) == c & condo[1].toString().trim().equals("S")) {
					return true;
				}
			}
		}
		return false;
	}

}
