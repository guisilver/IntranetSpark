package br.com.oma.intranet.managedbeans;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.PrepaxDAO;
import br.com.oma.intranet.entidades.intra_prepax;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import br.com.oma.sigadm.entidades.actavlan;
import br.com.oma.sigadm.entidades.cndavlan;

@ManagedBean(name = "ppMB")
@ViewScoped
public class PrepaxMB extends Mensagens {

	private static final long serialVersionUID = 1027377278724795482L;

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private PrepaxDAO ppDAO;
	private intra_prepax ppBEAN = new intra_prepax();
	private cndavlan cdBEAN = new cndavlan();
	private actavlan acBEAN = new actavlan();
	private List<intra_prepax> listaPrepax, listaPrepaxSelecionada, listaImprimir;
	private List<cndavlan> listaDebito;
	private List<actavlan> listaCredito;
	private int mes, ano, codigo;
	private Date dataPagto;
	private StreamedContent stream, stream2;

	// GET X SET
	public PrepaxMB() throws ParseException {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());
		this.mes = (cal.get(Calendar.MONDAY) + 1);
		this.ano = cal.get(Calendar.YEAR);
	}

	public List<intra_prepax> getListaImprimir() {
		return listaImprimir;
	}

	public void setListaImprimir(List<intra_prepax> listaImprimir) {
		this.listaImprimir = listaImprimir;
	}

	public PrepaxDAO getPpDAO() {
		return ppDAO;
	}

	public void setPpDAO(PrepaxDAO ppDAO) {
		this.ppDAO = ppDAO;
	}

	public cndavlan getcdBEAN() {
		return cdBEAN;
	}

	public void setcdBEAN(cndavlan cdBEAN) {
		this.cdBEAN = cdBEAN;
	}

	public actavlan getAcBEAN() {
		return acBEAN;
	}

	public void setAcBEAN(actavlan acBEAN) {
		this.acBEAN = acBEAN;
	}

	public intra_prepax getPpBEAN() {
		return ppBEAN;
	}

	public void setPpBEAN(intra_prepax ppBEAN) {
		this.ppBEAN = ppBEAN;
	}

	public List<intra_prepax> getListaPrepax() {
		return listaPrepax;
	}

	public void setListaPrepax(List<intra_prepax> listaPrepax) {
		this.listaPrepax = listaPrepax;
	}

	public List<intra_prepax> getListaPrepaxSelecionada() {
		return listaPrepaxSelecionada;
	}

	public void setListaPrepaxSelecionada(List<intra_prepax> listaPrepaxSelecionada) {
		this.listaPrepaxSelecionada = listaPrepaxSelecionada;
	}

	public List<cndavlan> getListaDebito() {
		return listaDebito;
	}

	public void setListaDebito(List<cndavlan> listaDebito) {
		this.listaDebito = listaDebito;
	}

	public List<actavlan> getListaCredito() {
		return listaCredito;
	}

	public void setListaCredito(List<actavlan> listaCredito) {
		this.listaCredito = listaCredito;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Date getDataPagto() {
		return dataPagto;
	}

	public void setDataPagto(Date dataPagto) {
		this.dataPagto = dataPagto;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public StreamedContent getStream() {
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	public StreamedContent getStream2() {
		return stream2;
	}

	public void setStream2(StreamedContent stream2) {
		this.stream2 = stream2;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// MÉTODOS

	/* MÉTODO PARA PESQUISAR PREPAX */
	public void pesquisarPorData() throws Exception {
		try {
			this.ppDAO = new PrepaxDAO();
			if (this.codigo == 900) {
				this.listaPrepax = this.ppDAO.listaRelatorio900(this.mes, this.mes, this.ano, this.dataPagto,
						this.codigo);
			} else {
				int mes2 = (this.mes - 1);
				this.listaPrepax = this.ppDAO.listaRelatorio123(mes2, this.mes, this.ano, this.dataPagto, this.codigo);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	/* MÉTODO PARA PESQUISAR LANÇAMENTO */
	public void pesquisarDCPorData() {
		try {
			this.ppDAO = new PrepaxDAO();
			this.listaDebito = this.ppDAO.listaDebito(this.dataPagto, this.codigo);
			this.listaCredito = this.ppDAO.listaCredito(this.dataPagto);

		} catch (ClassNotFoundException | IOException | SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	/* MÉTODO PARA LIMPAR LISTAS */
	public void limpar() {
		this.listaPrepax = null;
		this.listaPrepaxSelecionada = null;
	}

	/* MÉTODO PARA SALVAR (SIGADM) */
	public void salvar() {
		try {
			double valorCreditoOMA = 0;
			/*
			 * Salario - 26722 - 663 /Adiantamento - 26723 - 664 /13º Salario -
			 * 26727 - 3901 /Ferias - 26726 - 4132 /Rescisao - 26728 - 3902
			 * /Estagiario - 26724 - 665
			 */
			this.listaImprimir = this.listaPrepaxSelecionada;
			for (intra_prepax ip : this.listaPrepaxSelecionada) {
				this.ppDAO = new PrepaxDAO();
				cndavlan cdBEAN = new cndavlan();
				cdBEAN.setNrolancto(this.ppDAO.returnUltimoLancto());
				cdBEAN.setCondominio(ip.getEmpresa());
				cdBEAN.setBloco("0");

				if (this.codigo == 900) {
					cdBEAN.setConta(26722);
					cdBEAN.setCta_anl_financ(663);
					String historico = (ip.getNome().toUpperCase() + " - FOLHA PAGTO. " + this.meses() + "/"
							+ ip.getAno() + " (PREPAX)");
					cdBEAN.setHistorico(historico);
				} else {
					cdBEAN.setConta(26723);
					cdBEAN.setCta_anl_financ(664);
					String historico = (ip.getNome().toUpperCase() + " - ADIANT. SAL. " + this.meses() + "/"
							+ ip.getAno() + " (PREPAX)");
					cdBEAN.setHistorico(historico);
				}
				cdBEAN.setCod_histo(0);
				cdBEAN.setData(this.dataPagto);
				cdBEAN.setValor(ip.getValor());
				cdBEAN.setContab_debito(2776);
				cdBEAN.setContab_credito(100);
				cdBEAN.setDeb_cre('D');
				cdBEAN.setCpmf('N');
				cdBEAN.setBaixado_caixa(0);
				cdBEAN.setIncide_tx_adm(0);
				cdBEAN.setUsuario(0);
				valorCreditoOMA += cdBEAN.getValor();
				cdBEAN.setLogin_user(this.sessaoMB.getUsuario().getNome());
				this.ppDAO.adicionarCndavlan(cdBEAN, this.sessaoMB);
				this.limpar();
			}
			this.acBEAN.setNumero(this.ppDAO.returnUltimoLanctoOMA());
			this.acBEAN.setEmpresa(1);
			this.acBEAN.setData(this.dataPagto);
			this.acBEAN.setCta_debito(100);
			this.acBEAN.setCta_credito(2749);
			this.acBEAN.setCcusto_deb(0);
			this.acBEAN.setCcusto_cre(0);
			this.acBEAN.setValor(valorCreditoOMA);
			if (this.codigo == 900) {
				this.acBEAN.setHistorico(
						"FOLHA DE PAGTO. (PREPAX) " + this.meses() + "/" + this.ano + " - COND. DIVERSOS");
			} else {
				this.acBEAN.setHistorico(
						"ADIANT. SALARIAL (PREPAX) " + this.meses() + "/" + this.ano + " - COND. DIVERSOS");
			}
			this.acBEAN.setData_inclusao(new Date());
			this.acBEAN.setCod_usuario(763091);
			this.acBEAN.setUsuario(0);
			this.ppDAO.adicionarCndavlanOMA(this.acBEAN, this.sessaoMB);

			this.imprimirCSV();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	/* MÉTODO PARA IMPRIMIR EM CSV */
	public void imprimirCSV() {
		HashMap<Object, Object> parametros = new HashMap<>();
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		try {
			rju.geraRelatorioCSV(parametros, "prepax", "prepax", this.listaImprimir);
			this.stream = rju.getStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* MÉTODO PARA ALTERAR O MÊS */
	public String meses() {
		String retorno = null;
		switch (this.mes) {
		case 1:
			retorno = "JAN";
			break;
		case 2:
			retorno = "FEV";
			break;
		case 3:
			retorno = "MAR";
			break;
		case 4:
			retorno = "ABR";
			break;
		case 5:
			retorno = "MAI";
			break;
		case 6:
			retorno = "JUN";
			break;
		case 7:
			retorno = "JUL";
			break;
		case 8:
			retorno = "AGO";
			break;
		case 9:
			retorno = "SET";
			break;
		case 10:
			retorno = "OUT";
			break;
		case 11:
			retorno = "NOV";
			break;
		case 12:
			retorno = "DEZ";
			break;
		default:
			break;
		}
		return retorno;
	}

}