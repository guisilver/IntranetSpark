package br.com.oma.intranet.managedbeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.swing.text.MaskFormatter;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import br.com.oma.intranet.dao.EscalaDeFolgaDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_escala_folga;
import br.com.oma.intranet.entidades.intra_escala_param;
import br.com.oma.intranet.entidades.intra_func_ultimo_tipo_escala;
import br.com.oma.intranet.entidades.intra_funcionario;
import br.com.oma.intranet.util.EnderecoCnd;
import br.com.oma.intranet.util.Folga;
import br.com.oma.intranet.util.FolgaHeader;
import br.com.oma.intranet.util.FuncionarioFerias;
import br.com.oma.intranet.util.FuncionarioHorario;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;

@ViewScoped
@ManagedBean
public class EscalaDeFolgaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6487603981162976172L;
	private Folga folga;
	private intra_funcionario funcionario = new intra_funcionario();
	private intra_condominios condominio;
	private FolgaHeader folgaHeader;
	private Date dataInicio;
	private Date dataFim;
	private Date folgaInicio;
	private String nomeRelatorio;
	private List<Folga> listaFolgas;
	private List<intra_funcionario> listaFuncionarios;
	private List<intra_condominios> listaCondominio;
	private boolean preenchimentoAutomatico;
	private int diaInicio = 1;
	private int mesInicio = new DateTime().getMonthOfYear();
	private int anoInicio = new DateTime().getYear();
	private String tipoEscala = "Personalizado";
	private Set<Holiday> holidays;
	private String periodo;
	private intra_escala_param escala_param = new intra_escala_param();
	private intra_func_ultimo_tipo_escala ultimo_tipo_escala = new intra_func_ultimo_tipo_escala();
	private Date ultimaFolga;
	private String legenda;
	private Date dataInicioPintar;
	private Date dataFimPintar;
	private Calendar calendarUtil = Calendar.getInstance();
	private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

	@PostConstruct
	public void init() {
		try {
			Map<String, Object> requestCookieMap = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestCookieMap();
			Cookie cookie = (Cookie) requestCookieMap.get("diaInicioEscala");
			if (cookie != null) {
				if (new DateTime().isBefore(new DateTime().withDayOfMonth(Integer.parseInt(cookie.getValue())))) {
					this.diaInicio = Integer.parseInt(cookie.getValue());
					this.dataInicio = new DateTime().withDayOfMonth(this.diaInicio).withMonthOfYear(this.mesInicio)
							.minusMonths(1).withYear(this.anoInicio).toDate();
					this.mesInicio = new DateTime().withDayOfMonth(this.diaInicio).withMonthOfYear(this.mesInicio)
							.minusMonths(1).withYear(this.anoInicio).getMonthOfYear();
					this.anoInicio = new DateTime().withDayOfMonth(this.diaInicio).withMonthOfYear(this.mesInicio)
							.minusMonths(1).withYear(this.anoInicio).getYear();

				} else {
					this.dataInicio = new DateTime().withDayOfMonth(this.diaInicio).withMonthOfYear(this.mesInicio)
							.withYear(this.anoInicio).withMillisOfDay(0).toDate();
				}
			} else {
				this.dataInicio = new DateTime().withDayOfMonth(this.diaInicio).withMonthOfYear(this.mesInicio)
						.withYear(this.anoInicio).withMillisOfDay(0).toDate();
			}
			this.dataFim = new DateTime(this.dataInicio).plusMonths(1).minusDays(1).withMillisOfDay(0).toDate();
			populaFolgaHeader();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Folga getFolga() {
		return folga;
	}

	public void setFolga(Folga folga) {
		this.folga = folga;
	}

	public intra_funcionario getFuncionario() {
		return funcionario;
	}

	public void setCodigoFuncionario(intra_funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public FolgaHeader getFolgaHeader() throws Exception {
		return folgaHeader;
	}

	public void setFolgaHeader(FolgaHeader folgaHeader) {
		this.folgaHeader = folgaHeader;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getFolgaInicio() {
		return folgaInicio;
	}

	public void setFolgaInicio(Date folgaInicio) {
		this.folgaInicio = folgaInicio;
	}

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public List<Folga> getListaFolgas() {
		try {
			if (this.listaFolgas == null) {
				geraEscalaDeFolgas();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaFolgas;
	}

	public void setListaFolgas(List<Folga> listaFolgas) {
		this.listaFolgas = listaFolgas;
	}

	public List<intra_funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<intra_funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<intra_condominios> getListaCondominio()
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		try {
			if (this.listaCondominio == null) {
				this.listaCondominio = new ArrayList<>();
				EscalaDeFolgaDAO dao = new EscalaDeFolgaDAO();
				this.listaCondominio = dao.listarCondominios();
				this.condominio = this.listaCondominio.get(0);
				this.pesquisaEscalaParam();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCondominio;
	}

	public void setListaCondominio(List<intra_condominios> listaCondominio) {
		this.listaCondominio = listaCondominio;
	}

	public boolean isPreenchimentoAutomatico() {
		return preenchimentoAutomatico;
	}

	public void setPreenchimentoAutomatico(boolean preenchimentoAutomatico) {
		this.preenchimentoAutomatico = preenchimentoAutomatico;
	}

	public int getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(int diaInicio) {
		this.diaInicio = diaInicio;
	}

	public int getMesInicio() {
		return mesInicio;
	}

	public void setMesInicio(int mesInicio) {
		this.mesInicio = mesInicio;
	}

	public int getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(int anoInicio) {
		this.anoInicio = anoInicio;
	}

	public String getTipoEscala() {
		return tipoEscala;
	}

	public void setTipoEscala(String tipoEscala) {
		this.tipoEscala = tipoEscala;
	}

	public Set<Holiday> getHolidays() {
		try {
			HolidayManager m = HolidayManager.getInstance(HolidayCalendar.BRAZIL);
			this.holidays = m.getHolidays(anoInicio, "br", "s");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return holidays;
	}

	public void setHolidays(Set<Holiday> holidays) {
		this.holidays = holidays;
	}

	public String getPeriodo() {
		try {
			if (dataInicio != null && dataFim != null) {
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
				this.periodo = sf.format(this.dataInicio) + " à " + sf.format(this.dataFim);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public intra_escala_param getEscala_param() {
		return escala_param;
	}

	public void setEscala_param(intra_escala_param escala_param) {
		this.escala_param = escala_param;
	}

	public intra_func_ultimo_tipo_escala getUltimo_tipo_escala() {
		return ultimo_tipo_escala;
	}

	public void setUltimo_tipo_escala(intra_func_ultimo_tipo_escala ultimo_tipo_escala) {
		this.ultimo_tipo_escala = ultimo_tipo_escala;
	}

	public Date getUltimaFolga() {
		return ultimaFolga;
	}

	public void setUltimaFolga(Date ultimaFolga) {
		this.ultimaFolga = ultimaFolga;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public Date getDataInicioPintar() {
		return dataInicioPintar;
	}

	public void setDataInicioPintar(Date dataInicioPintar) {
		this.dataInicioPintar = dataInicioPintar;
	}

	public Date getDataFimPintar() {
		return dataFimPintar;
	}

	public void setDataFimPintar(Date dataFimPintar) {
		this.dataFimPintar = dataFimPintar;
	}

	public void setFuncionario(intra_funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void salvarNovaEscalaFuncionario() {
		try {
			EscalaDeFolgaDAO dao = new EscalaDeFolgaDAO();
			if (this.isPreenchimentoAutomatico()) {
				preenchimentoAutomatico();
				this.ultimo_tipo_escala.setCodigoCondominio(this.condominio.getCodigo());
				this.ultimo_tipo_escala.setCodigoFuncionario(this.funcionario.getCodigo());
				this.ultimo_tipo_escala.setTipo(this.funcionario.getTipoEscala());
				dao.salvarUltimoTipoEscala(this.ultimo_tipo_escala);
			} else {
				intra_escala_folga folga = new intra_escala_folga();
				folga.setFolga(this.folgaInicio);
				folga.setCodigoCondominio(this.condominio.getCodigo());
				folga.setCodigoFuncionario(this.funcionario.getCodigo());
				if (!folgaJaExiste(this.folgaInicio) || this.funcionario.getCodigo() == 0) {
					this.funcionario.getFolgas().add(folga);
				}
			}
			this.funcionario.setCondominio(this.condominio);
			dao.salvarFolgas(this.funcionario.getFolgas());
			this.funcionario = new intra_funcionario();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
			RequestContext.getCurrentInstance().execute("PF('dlgAltrFuncionario').hide();");
			this.limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void preenchimentoAutomatico() {
		try {
			intra_escala_folga folga = null;
			DateTime folgaDataInicio = new DateTime(this.folgaInicio).withMillisOfDay(0);
			switch (this.funcionario.getTipoEscala()) {
			case 42:
				while (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
					folga = new intra_escala_folga();
					folga.setFolga(folgaDataInicio.toDate());
					folga.setCodigoCondominio(this.condominio.getCodigo());
					folga.setCodigoFuncionario(this.funcionario.getCodigo());
					folga.setLegenda(this.legenda.toUpperCase());
					if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
						this.funcionario.getFolgas().add(folga);
					}
					folgaDataInicio = folgaDataInicio.plusDays(1);
					if (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
						folga = new intra_escala_folga();
						folga.setFolga(folgaDataInicio.toDate());
						folga.setCodigoCondominio(this.condominio.getCodigo());
						folga.setCodigoFuncionario(this.funcionario.getCodigo());
						folga.setLegenda(this.legenda.toUpperCase());
						if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
							this.funcionario.getFolgas().add(folga);
						}
					}
					folgaDataInicio = folgaDataInicio.plusDays(5);
				}
				break;
			case 51:
				while (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
					folga = new intra_escala_folga();
					folga.setFolga(folgaDataInicio.toDate());
					folga.setCodigoCondominio(this.condominio.getCodigo());
					folga.setCodigoFuncionario(this.funcionario.getCodigo());
					folga.setLegenda(this.legenda.toUpperCase());
					if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
						this.funcionario.getFolgas().add(folga);
					}

					folgaDataInicio = folgaDataInicio.plusDays(6);
				}
				break;
			case 52:
				while (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
					folga = new intra_escala_folga();
					folga.setFolga(folgaDataInicio.toDate());
					folga.setCodigoCondominio(this.condominio.getCodigo());
					folga.setCodigoFuncionario(this.funcionario.getCodigo());
					folga.setLegenda(this.legenda.toUpperCase());
					if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
						this.funcionario.getFolgas().add(folga);
					}

					folgaDataInicio = folgaDataInicio.plusDays(1);
					if (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
						folga = new intra_escala_folga();
						folga.setFolga(folgaDataInicio.toDate());
						folga.setCodigoCondominio(this.condominio.getCodigo());
						folga.setCodigoFuncionario(this.funcionario.getCodigo());
						folga.setLegenda(this.legenda.toUpperCase());
						if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
							this.funcionario.getFolgas().add(folga);
						}
					}
					folgaDataInicio = folgaDataInicio.plusDays(6);
				}

				break;
			case 61:
				while (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
					folga = new intra_escala_folga();
					folga.setFolga(folgaDataInicio.toDate());
					folga.setCodigoCondominio(this.condominio.getCodigo());
					folga.setCodigoFuncionario(this.funcionario.getCodigo());
					folga.setLegenda(this.legenda.toUpperCase());
					if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
						this.funcionario.getFolgas().add(folga);
					}

					folgaDataInicio = folgaDataInicio.plusDays(7);
				}
				break;
			case 62:
				while (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
					folga = new intra_escala_folga();
					folga.setFolga(folgaDataInicio.toDate());
					folga.setCodigoCondominio(this.condominio.getCodigo());
					folga.setCodigoFuncionario(this.funcionario.getCodigo());
					folga.setLegenda(this.legenda.toUpperCase());
					if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
						this.funcionario.getFolgas().add(folga);
					}

					folgaDataInicio = folgaDataInicio.plusDays(1);
					if (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
						folga = new intra_escala_folga();
						folga.setFolga(folgaDataInicio.toDate());
						folga.setCodigoCondominio(this.condominio.getCodigo());
						folga.setCodigoFuncionario(this.funcionario.getCodigo());
						folga.setLegenda(this.legenda.toUpperCase());
						if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
							this.funcionario.getFolgas().add(folga);
						}
					}
					folgaDataInicio = folgaDataInicio.plusDays(7);
				}
				break;
			case 1236:
				while (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
					folga = new intra_escala_folga();
					folga.setFolga(folgaDataInicio.toDate());
					folga.setCodigoCondominio(this.condominio.getCodigo());
					folga.setCodigoFuncionario(this.funcionario.getCodigo());
					folga.setLegenda(this.legenda.toUpperCase());
					if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
						this.funcionario.getFolgas().add(folga);
					}

					folgaDataInicio = folgaDataInicio.plusDays(2);
					if (folgaDataInicio.isBefore(new DateTime(this.dataFim))) {
						folga = new intra_escala_folga();
						folga.setFolga(folgaDataInicio.toDate());
						folga.setCodigoCondominio(this.condominio.getCodigo());
						folga.setCodigoFuncionario(this.funcionario.getCodigo());
						folga.setLegenda(this.legenda.toUpperCase());
						if (!folgaJaExiste(folgaDataInicio.toDate()) || this.funcionario.getCodigo() == 0) {
							this.funcionario.getFolgas().add(folga);
						}
					}
					folgaDataInicio = folgaDataInicio.plusDays(2);
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void geraEscalaDeFolgas() throws Exception {
		populaFolgaHeader();
		criaObjetosFolga();
	}

	public void populaFolgaHeader() throws Exception {
		try {
			List<DateTime> listaDatas = new ArrayList<>();
			this.folgaHeader = new FolgaHeader();
			this.dataInicio = new DateTime().withDayOfMonth(this.diaInicio).withMonthOfYear(this.mesInicio)
					.withYear(this.anoInicio).toDate();
			this.dataFim = new DateTime(this.dataInicio).plusMonths(1).minusDays(1).toDate();
			Locale locale = new Locale("pt", "BR");
			Calendar c1 = Calendar.getInstance(locale);
			Calendar c2 = Calendar.getInstance(locale);
			c1.setTime(this.dataInicio);
			c2.setTime(this.dataFim);
			int diferencaDias = Days.daysBetween(new DateTime(dataInicio), new DateTime(dataFim)).getDays();
			listaDatas = new ArrayList<>();
			listaDatas.add(new DateTime(c1.getTime()));
			for (int i = 1; i <= diferencaDias; i++) {
				c1.add(Calendar.DATE, 1);
				listaDatas.add(new DateTime(c1.getTime()));
			}
			this.folgaHeader.setH1(listaDatas.get(0));
			this.folgaHeader.setH1fds(this.sabDomFeriado(listaDatas.get(0)));

			this.folgaHeader.setH2(listaDatas.get(1));
			this.folgaHeader.setH2fds(this.sabDomFeriado(listaDatas.get(1)));

			this.folgaHeader.setH3(listaDatas.get(2));
			this.folgaHeader.setH3fds(this.sabDomFeriado(listaDatas.get(2)));

			this.folgaHeader.setH4(listaDatas.get(3));
			this.folgaHeader.setH4fds(this.sabDomFeriado(listaDatas.get(3)));

			this.folgaHeader.setH5(listaDatas.get(4));
			this.folgaHeader.setH5fds(this.sabDomFeriado(listaDatas.get(4)));

			this.folgaHeader.setH6(listaDatas.get(5));
			this.folgaHeader.setH6fds(this.sabDomFeriado(listaDatas.get(5)));

			this.folgaHeader.setH7(listaDatas.get(6));
			this.folgaHeader.setH7fds(this.sabDomFeriado(listaDatas.get(6)));

			this.folgaHeader.setH8(listaDatas.get(7));
			this.folgaHeader.setH8fds(this.sabDomFeriado(listaDatas.get(7)));

			this.folgaHeader.setH9(listaDatas.get(8));
			this.folgaHeader.setH9fds(this.sabDomFeriado(listaDatas.get(8)));

			this.folgaHeader.setH10(listaDatas.get(9));
			this.folgaHeader.setH10fds(this.sabDomFeriado(listaDatas.get(9)));

			this.folgaHeader.setH11(listaDatas.get(10));
			this.folgaHeader.setH11fds(this.sabDomFeriado(listaDatas.get(10)));

			this.folgaHeader.setH12(listaDatas.get(11));
			this.folgaHeader.setH12fds(this.sabDomFeriado(listaDatas.get(11)));

			this.folgaHeader.setH13(listaDatas.get(12));
			this.folgaHeader.setH13fds(this.sabDomFeriado(listaDatas.get(12)));

			this.folgaHeader.setH14(listaDatas.get(13));
			this.folgaHeader.setH14fds(this.sabDomFeriado(listaDatas.get(13)));

			this.folgaHeader.setH15(listaDatas.get(14));
			this.folgaHeader.setH15fds(this.sabDomFeriado(listaDatas.get(14)));

			this.folgaHeader.setH16(listaDatas.get(15));
			this.folgaHeader.setH16fds(this.sabDomFeriado(listaDatas.get(15)));

			this.folgaHeader.setH17(listaDatas.get(16));
			this.folgaHeader.setH17fds(this.sabDomFeriado(listaDatas.get(16)));

			this.folgaHeader.setH18(listaDatas.get(17));
			this.folgaHeader.setH18fds(this.sabDomFeriado(listaDatas.get(17)));

			this.folgaHeader.setH19(listaDatas.get(18));
			this.folgaHeader.setH19fds(this.sabDomFeriado(listaDatas.get(18)));

			this.folgaHeader.setH20(listaDatas.get(19));
			this.folgaHeader.setH20fds(this.sabDomFeriado(listaDatas.get(19)));

			this.folgaHeader.setH21(listaDatas.get(20));
			this.folgaHeader.setH21fds(this.sabDomFeriado(listaDatas.get(20)));

			this.folgaHeader.setH22(listaDatas.get(21));
			this.folgaHeader.setH22fds(this.sabDomFeriado(listaDatas.get(21)));

			this.folgaHeader.setH23(listaDatas.get(22));
			this.folgaHeader.setH23fds(this.sabDomFeriado(listaDatas.get(22)));

			this.folgaHeader.setH24(listaDatas.get(23));
			this.folgaHeader.setH24fds(this.sabDomFeriado(listaDatas.get(23)));

			this.folgaHeader.setH25(listaDatas.get(24));
			this.folgaHeader.setH25fds(this.sabDomFeriado(listaDatas.get(24)));

			this.folgaHeader.setH26(listaDatas.get(25));
			this.folgaHeader.setH26fds(this.sabDomFeriado(listaDatas.get(25)));

			this.folgaHeader.setH27(listaDatas.get(26));
			this.folgaHeader.setH27fds(this.sabDomFeriado(listaDatas.get(26)));

			this.folgaHeader.setH28(listaDatas.get(27));
			this.folgaHeader.setH28fds(this.sabDomFeriado(listaDatas.get(27)));

			if (listaDatas.size() >= 29) {
				this.folgaHeader.setH29(listaDatas.get(28));
				this.folgaHeader.setH29fds(this.sabDomFeriado(listaDatas.get(28)));
			}
			if (listaDatas.size() >= 30) {
				this.folgaHeader.setH30(listaDatas.get(29));
				this.folgaHeader.setH30fds(this.sabDomFeriado(listaDatas.get(29)));

			}
			if (listaDatas.size() >= 31) {
				this.folgaHeader.setH31(listaDatas.get(30));
				this.folgaHeader.setH31fds(this.sabDomFeriado(listaDatas.get(30)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pesquisaFolgas() {
		try {
			Folga f1 = new Folga();
			f1.setF1("F");
			f1.setF2("F");
			f1.setF30("F");
			Folga f2 = new Folga();
			f2.setF4("F");
			f2.setF20("F");
			f2.setF27("F");
			Folga f3 = new Folga();
			f3.setF8("F");
			f3.setF15("F");
			this.listaFolgas = new ArrayList<>();
			this.listaFolgas.add(f1);
			this.listaFolgas.add(f2);
			this.listaFolgas.add(f3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public void geraRelatorio() throws Exception {
		try {
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			HashMap parametros = new HashMap();

			parametros.put("h1", this.folgaHeader.getH1().getDayOfMonth());
			parametros.put("h1fds", this.folgaHeader.getH1fds());

			parametros.put("h2", this.folgaHeader.getH2().getDayOfMonth());
			parametros.put("h2fds", this.folgaHeader.getH2fds());

			parametros.put("h3", this.folgaHeader.getH3().getDayOfMonth());
			parametros.put("h3fds", this.folgaHeader.getH3fds());

			parametros.put("h4", this.folgaHeader.getH4().getDayOfMonth());
			parametros.put("h4fds", this.folgaHeader.getH4fds());

			parametros.put("h5", this.folgaHeader.getH5().getDayOfMonth());
			parametros.put("h5fds", this.folgaHeader.getH5fds());

			parametros.put("h6", this.folgaHeader.getH6().getDayOfMonth());
			parametros.put("h6fds", this.folgaHeader.getH6fds());

			parametros.put("h7", this.folgaHeader.getH7().getDayOfMonth());
			parametros.put("h7fds", this.folgaHeader.getH7fds());

			parametros.put("h8", this.folgaHeader.getH8().getDayOfMonth());
			parametros.put("h8fds", this.folgaHeader.getH8fds());

			parametros.put("h9", this.folgaHeader.getH9().getDayOfMonth());
			parametros.put("h9fds", this.folgaHeader.getH9fds());

			parametros.put("h10", this.folgaHeader.getH10().getDayOfMonth());
			parametros.put("h10fds", this.folgaHeader.getH10fds());

			parametros.put("h11", this.folgaHeader.getH11().getDayOfMonth());
			parametros.put("h11fds", this.folgaHeader.getH11fds());

			parametros.put("h12", this.folgaHeader.getH12().getDayOfMonth());
			parametros.put("h12fds", this.folgaHeader.getH12fds());

			parametros.put("h13", this.folgaHeader.getH13().getDayOfMonth());
			parametros.put("h13fds", this.folgaHeader.getH13fds());

			parametros.put("h14", this.folgaHeader.getH14().getDayOfMonth());
			parametros.put("h14fds", this.folgaHeader.getH14fds());

			parametros.put("h15", this.folgaHeader.getH15().getDayOfMonth());
			parametros.put("h15fds", this.folgaHeader.getH15fds());

			parametros.put("h16", this.folgaHeader.getH16().getDayOfMonth());
			parametros.put("h16fds", this.folgaHeader.getH16fds());

			parametros.put("h17", this.folgaHeader.getH17().getDayOfMonth());
			parametros.put("h17fds", this.folgaHeader.getH17fds());

			parametros.put("h18", this.folgaHeader.getH18().getDayOfMonth());
			parametros.put("h18fds", this.folgaHeader.getH18fds());

			parametros.put("h19", this.folgaHeader.getH19().getDayOfMonth());
			parametros.put("h19fds", this.folgaHeader.getH19fds());

			parametros.put("h20", this.folgaHeader.getH20().getDayOfMonth());
			parametros.put("h20fds", this.folgaHeader.getH20fds());

			parametros.put("h21", this.folgaHeader.getH21().getDayOfMonth());
			parametros.put("h21fds", this.folgaHeader.getH21fds());

			parametros.put("h22", this.folgaHeader.getH22().getDayOfMonth());
			parametros.put("h22fds", this.folgaHeader.getH22fds());

			parametros.put("h23", this.folgaHeader.getH23().getDayOfMonth());
			parametros.put("h23fds", this.folgaHeader.getH23fds());

			parametros.put("h24", this.folgaHeader.getH24().getDayOfMonth());
			parametros.put("h24fds", this.folgaHeader.getH24fds());

			parametros.put("h25", this.folgaHeader.getH25().getDayOfMonth());
			parametros.put("h25fds", this.folgaHeader.getH25fds());

			parametros.put("h26", this.folgaHeader.getH26().getDayOfMonth());
			parametros.put("h26fds", this.folgaHeader.getH26fds());

			parametros.put("h27", this.folgaHeader.getH27().getDayOfMonth());
			parametros.put("h27fds", this.folgaHeader.getH27fds());

			parametros.put("h28", this.folgaHeader.getH28().getDayOfMonth());
			parametros.put("h28fds", this.folgaHeader.getH28fds());

			parametros.put("cdCnd", this.condominio.getCodigo());
			parametros.put("nomeCnd", this.condominio.getNome());

			String endereco = this.condominio.getEndereco() + " " + this.condominio.getNro() + " - "
					+ this.condominio.getBairro();
			parametros.put("endCnd", endereco);
			if (this.folgaHeader.getH29() != null) {
				parametros.put("h29", this.folgaHeader.getH29().getDayOfMonth());
				parametros.put("h29fds", this.folgaHeader.getH29fds());
			}
			if (this.folgaHeader.getH30() != null) {
				parametros.put("h30", this.folgaHeader.getH30().getDayOfMonth());
				parametros.put("h30fds", this.folgaHeader.getH30fds());
			}
			if (this.folgaHeader.getH31() != null) {
				parametros.put("h31", this.folgaHeader.getH31().getDayOfMonth());
				parametros.put("h31fds", this.folgaHeader.getH31fds());
			}
			EscalaDeFolgaDAO dao = new EscalaDeFolgaDAO();
			EnderecoCnd endCnd = dao.pesquisaEnderecoCnd(this.condominio.getCodigo());
			if (endCnd != null) {
				String cep = String.valueOf(endCnd.getCndCep());
				cep = this.format("####-###", cep);
				String cnpj = String.valueOf(endCnd.getCndCnpj());
				while (cnpj.length() < 15) {
					cnpj = "0" + cnpj;
				}
				cnpj = this.format("###.###.###/####-##", cnpj);
				parametros.put("cndCep", cep);
				parametros.put("cndCidade", endCnd.getCndCidade());
				parametros.put("cndEstado", endCnd.getCndEstado());
				parametros.put("cndCnpj", cnpj);
			}
			if (this.escala_param != null) {
				parametros.put("obs1", this.escala_param.getObs1());
				parametros.put("obs2", this.escala_param.getObs2());
				parametros.put("obs3", this.escala_param.getObs3());
				parametros.put("obs4", this.escala_param.getObs4());
				parametros.put("obs5", this.escala_param.getObs5());
				parametros.put("obs6", this.escala_param.getObs6());
				parametros.put("manha", this.escala_param.getManha());
				parametros.put("tarde", this.escala_param.getTarde());
				parametros.put("noite", this.escala_param.getNoite());
			}
			String periodo = this.sf.format(this.dataInicio) + " à " + this.sf.format(this.dataFim);
			parametros.put("periodo", periodo);
			byte[] retorno = null;
			retorno = rju.geraEscalaDeFolga(parametros, "EscalaDeFolgas", "EscalaDeFolgas", 1, this.listaFolgas);
			this.nomeRelatorio = this.exportarRelatorio(1, retorno);
			RequestContext.getCurrentInstance().execute("PF('dialogRelatorio2').show();");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String exportarRelatorio(int tipo, byte[] arquivo) throws IOException {
		String nome_relatorio = "";
		try {
			nome_relatorio = this.getRandomName();
			String caminho_relatorio = "";
			if (arquivo != null) {
				ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
						.getContext();
				caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator
						+ nome_relatorio + ".pdf";
				FileOutputStream output;
				output = new FileOutputStream(new File(caminho_relatorio));
				output.write(arquivo);
				output.close();
			} else {
				caminho_relatorio = "vazio";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nome_relatorio;
	}

	public String getRandomName() {
		try {
			int i = (int) (Math.random() * 100000);
			return String.valueOf(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void criaObjetosFolga() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		try {
			this.listaFolgas = new ArrayList<>();
			EscalaDeFolgaDAO dao = new EscalaDeFolgaDAO();
			Date dataInicio = new DateTime(this.dataInicio).toLocalDate().toDateTimeAtStartOfDay().minusMinutes(1)
					.toDate();
			Date dataFim = new DateTime(this.dataFim).toLocalDate().toDateTimeAtStartOfDay().plusDays(1).toDate();
			List<intra_escala_folga> listaFolga = dao.getFolgas(this.condominio, dataInicio, dataFim);
			this.listaFuncionarios = dao.getFuncionarios(this.condominio, this.dataInicio, this.dataFim);
			Collections.sort(this.listaFuncionarios);
			for (intra_funcionario aux : this.listaFuncionarios) {
				this.folga = new Folga();
				for (intra_escala_folga aux2 : listaFolga) {
					if (aux.getCodigo() == aux2.getCodigoFuncionario()) {
						populaFolga(aux2, this.folga);
					}
				}
				this.folga.setCodigo(aux.getCodigo());
				this.folga.setNome(aux.getNome());
				this.folga.setFuncao(aux.getFuncao());
				FuncionarioFerias ferias = dao.pesquisaFeriasFuncionario(aux.getCodigo(), this.condominio.getCodigo(),
						this.dataInicio, this.dataFim);
				if (ferias != null && ferias.getInicio_gozo() != null && ferias.getFinal_gozo() != null) {
					this.folga.setInicio_gozo(ferias.getInicio_gozo());
					this.folga.setFinal_gozo(ferias.getFinal_gozo());
					this.populaFerias(this.folga);
				}
				FuncionarioHorario horario = dao.pesquisaFuncionarioHorarios(aux.getCodigo(),
						this.condominio.getCodigo());
				if (horario != null) {
					if (horario.getEntr_prim() != null) {
						this.folga.setEntr_prim(horario.getEntr_prim().replace(".", ":"));
					}
					if (horario.getSaida_prim() != null) {
						this.folga.setSaida_prim(horario.getSaida_prim().replace(".", ":"));
					}
					if (horario.getSaida_prim() != null) {
						this.folga.setEntr_seg(horario.getEntr_seg().replace(".", ":"));
					}
					if (horario.getSaida_seg() != null) {
						this.folga.setSaida_seg(horario.getSaida_seg().replace(".", ":"));
					}
				}
				if (aux.getSituacao() == 5) {
					this.folga.setAfastado(true);
				}
				this.listaFolgas.add(this.folga);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int capturaDiaData(Date data) {
		try {
			this.calendarUtil.setTime(data);
			this.calendarUtil.get(Calendar.DAY_OF_YEAR);
			return this.calendarUtil.get(Calendar.DAY_OF_YEAR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void populaFolga(intra_escala_folga aux, Folga folga) {
		try {
			legenda = aux.getLegenda();
			if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH1().getDayOfYear()) {
				folga.setF1(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH2().getDayOfYear()) {
				folga.setF2(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH3().getDayOfYear()) {
				folga.setF3(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH4().getDayOfYear()) {
				folga.setF4(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH5().getDayOfYear()) {
				folga.setF5(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH6().getDayOfYear()) {
				folga.setF6(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH7().getDayOfYear()) {
				folga.setF7(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH8().getDayOfYear()) {
				folga.setF8(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH9().getDayOfYear()) {
				folga.setF9(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH10().getDayOfYear()) {
				folga.setF10(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH11().getDayOfYear()) {
				folga.setF11(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH12().getDayOfYear()) {
				folga.setF12(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH13().getDayOfYear()) {
				folga.setF13(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH14().getDayOfYear()) {
				folga.setF14(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH15().getDayOfYear()) {
				folga.setF15(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH16().getDayOfYear()) {
				folga.setF16(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH17().getDayOfYear()) {
				folga.setF17(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH18().getDayOfYear()) {
				folga.setF18(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH19().getDayOfYear()) {
				folga.setF19(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH20().getDayOfYear()) {
				folga.setF20(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH21().getDayOfYear()) {
				folga.setF21(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH22().getDayOfYear()) {
				folga.setF22(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH23().getDayOfYear()) {
				folga.setF23(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH24().getDayOfYear()) {
				folga.setF24(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH25().getDayOfYear()) {
				folga.setF25(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH26().getDayOfYear()) {
				folga.setF26(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH27().getDayOfYear()) {
				folga.setF27(legenda);
			} else if (capturaDiaData(aux.getFolga()) == this.folgaHeader.getH28().getDayOfYear()) {
				folga.setF28(legenda);
			} else if (this.folgaHeader.getH29() != null
					&& capturaDiaData(aux.getFolga()) == this.folgaHeader.getH29().getDayOfYear()) {
				folga.setF29(legenda);
			} else if (this.folgaHeader.getH30() != null
					&& capturaDiaData(aux.getFolga()) == this.folgaHeader.getH30().getDayOfYear()) {
				folga.setF30(legenda);
			} else if (this.folgaHeader.getH31() != null
					&& capturaDiaData(aux.getFolga()) == this.folgaHeader.getH31().getDayOfYear()) {
				folga.setF31(legenda);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void populaFerias(Folga folga) {
		try {
			boolean diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH1());
			folga.setF1fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH2());
			folga.setF2fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH3());
			folga.setF3fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH4());
			folga.setF4fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH5());
			folga.setF5fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH6());
			folga.setF6fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH7());
			folga.setF7fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH8());
			folga.setF8fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH9());
			folga.setF9fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH10());
			folga.setF10fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH11());
			folga.setF11fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH12());
			folga.setF12fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH13());
			folga.setF13fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH14());
			folga.setF14fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH15());
			folga.setF15fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH16());
			folga.setF16fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH17());
			folga.setF17fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH18());
			folga.setF18fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH19());
			folga.setF19fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH20());
			folga.setF20fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH21());
			folga.setF21fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH22());
			folga.setF22fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH23());
			folga.setF23fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH24());
			folga.setF24fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH25());
			folga.setF25fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH26());
			folga.setF26fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH27());
			folga.setF27fer(diaFerias);
			diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
					this.folgaHeader.getH28());
			folga.setF28fer(diaFerias);
			if (this.folgaHeader.getH29() != null) {
				diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
						this.folgaHeader.getH29());
				folga.setF29fer(diaFerias);
			}
			if (this.folgaHeader.getH30() != null) {
				diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
						this.folgaHeader.getH30());
				folga.setF30fer(diaFerias);
			}
			if (this.folgaHeader.getH31() != null) {
				diaFerias = this.diaFerias(this.folga.getInicio_gozo(), this.folga.getFinal_gozo(),
						this.folgaHeader.getH31());
				folga.setF31fer(diaFerias);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selecionaTipoEscala() throws Exception {
		try {
			if (this.tipoEscala.equals("Mensal")) {
				this.dataInicio = new DateTime(this.dataInicio).withDayOfMonth(1).toDate();
				this.dataFim = new DateTime(this.dataInicio).plusMonths(1).minusDays(1).toDate();
				this.diaInicio = 1;
			}
			this.geraEscalaDeFolgas();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sabDomFeriado(DateTime data) throws ParseException {
		String retorno = "";
		try {
			if (data != null) {
				boolean diaFeriado = false;
				HolidayManager m = HolidayManager.getInstance(de.jollyday.HolidayCalendar.BRAZIL);
				if (m != null) {
					Set<Holiday> holidays = m.getHolidays(anoInicio, "BR", "s");
					for (Holiday feriado : holidays) {
						if (feriado.getPropertiesKey() != null
								&& !feriado.getPropertiesKey().equals("christian.CARNIVAL")
								&& !feriado.getPropertiesKey().equals("christian.ASH_WEDNESDAY")) {
							Date dataFeriado = this.converteHolidayParaDate(feriado);
							if (dataFeriado != null) {
								int dt1 = new DateTime(data).getDayOfYear();
								int dt3 = new DateTime(dataFeriado).getDayOfYear();
								if (dt1 == dt3) {
									diaFeriado = true;
								}
							}
						}
					}
				}
				if (data.getDayOfWeek() == 1) {
					retorno = "SEG";
				}
				if (data.getDayOfWeek() == 2) {
					retorno = "TER";
				}
				if (data.getDayOfWeek() == 3) {
					retorno = "QUA";
				}
				if (data.getDayOfWeek() == 4) {
					retorno = "QUI";
				}
				if (data.getDayOfWeek() == 5) {
					retorno = "SEX";
				}
				if (data.getDayOfWeek() == 6) {
					retorno = "SAB";
				}
				if (data.getDayOfWeek() == 7) {
					retorno = "DOM";
				}
				if (diaFeriado && data.getDayOfWeek() != 6 && data.getDayOfWeek() != 7) {
					retorno = "FER";
				}
				if (!this.validaFeriado()) {
					if (data.getDayOfYear() == 25) {
						retorno = "FER";
					}
				}
				if (data.getDayOfMonth() == 9 && data.getMonthOfYear() == 7 && data.getYear() == 2016) {
					retorno = "FER";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public boolean validaFeriado() {
		boolean val = false;
		try {
			if (this.condominio != null) {
				switch (this.condominio.getCodigo()) {
				case 402:
					val = true;
					break;
				case 788:
					val = true;
					break;
				case 1007:
					val = true;
					break;
				case 1011:
					val = true;
					break;
				case 1083:
					val = true;
					break;
				case 1124:
					val = true;
					break;
				case 1146:
					val = true;
					break;
				case 1159:
					val = true;
					break;
				case 1228:
					val = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	public Date converteHolidayParaDate(Holiday feriado) throws ParseException {
		Date retorno = null;
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			DateTime dt = new DateTime(feriado.getDate().toDateTimeAtCurrentTime());
			retorno = sf.parse(dt.getDayOfMonth() + "/" + dt.getMonthOfYear() + "/" + dt.getYear() + " "
					+ dt.getHourOfDay() + ":" + dt.getMinuteOfHour());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public void onCellEdit(CellEditEvent event) throws Exception {
		try {
			String newValue = String.valueOf(event.getNewValue());
			int index = event.getRowIndex();
			Folga folga = this.listaFolgas.get(index);
			EscalaDeFolgaDAO dao = new EscalaDeFolgaDAO();
			if (newValue == null || newValue.equals("")) {
				String header = event.getColumn().getHeaderText();
				int dia = Integer.parseInt(header.replaceAll("[\\D]", ""));
				int mesInicio = this.mesInicio;
				if (dia < this.diaInicio) {
					mesInicio++;
				}
				int anoInicio = this.anoInicio;
				if (new DateTime(this.dataFim).getYear() > new DateTime(this.dataInicio).getYear()
						&& dia < this.diaInicio) {
					anoInicio++;
					mesInicio = 1;
				}
				if (this.condominio != null) {
					Date data = new DateTime().withMonthOfYear(mesInicio).withDayOfMonth(dia).withYear(anoInicio)
							.toDate();
					dao.excluirFolga(data, folga.getCodigo(), this.condominio.getCodigo());
				}
			} else {
				String header = event.getColumn().getHeaderText();
				int dia = Integer.parseInt(header.replaceAll("[\\D]", ""));
				int mesInicio = this.mesInicio;
				if (dia < this.diaInicio) {
					mesInicio++;
				}
				int anoInicio = this.anoInicio;
				if (new DateTime(this.dataFim).getYear() > new DateTime(this.dataInicio).getYear()
						&& dia < this.diaInicio) {
					anoInicio++;
					mesInicio = 1;
				}
				Date data = new DateTime().withMonthOfYear(mesInicio).withDayOfMonth(dia).withYear(anoInicio).toDate();
				intra_escala_folga novaFolga = new intra_escala_folga();
				if (this.condominio != null) {
					novaFolga.setCodigoCondominio(this.condominio.getCodigo());
					novaFolga.setFolga(data);
					novaFolga.setCodigoFuncionario(folga.getCodigo());
					newValue = newValue.toUpperCase();
					novaFolga.setLegenda(newValue);
					List<intra_escala_folga> listaFolgas = new ArrayList<>();
					listaFolgas.add(novaFolga);
					dao.excluirFolga(data, folga.getCodigo(), this.condominio.getCodigo());
					dao.salvarFolgas(listaFolgas);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpar() {
		try {
			this.listaFolgas = null;
			this.folga = new Folga();
			this.ultimo_tipo_escala = new intra_func_ultimo_tipo_escala();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirDlgAlteraFuncionario(Folga folga)
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		try {
			for (intra_funcionario aux : this.listaFuncionarios) {
				if (aux.getCodigo() == folga.getCodigo()) {
					this.funcionario = aux;
				}
			}
			EscalaDeFolgaDAO dao = new EscalaDeFolgaDAO();
			List<Date> listaDatas = dao.pesquisaUltimaFolga(this.funcionario.getCodigo(), this.condominio.getCodigo());
			if (listaDatas != null && listaDatas.size() > 0) {
				this.ultimaFolga = listaDatas.get(listaDatas.size() - 1);
			} else {
				this.ultimaFolga = null;
			}
			List<intra_func_ultimo_tipo_escala> listaUltimaEscala = dao
					.pesquisaUltimoTipoEscala(this.funcionario.getCodigo(), this.condominio.getCodigo());
			if (listaUltimaEscala != null && listaUltimaEscala.size() > 0) {
				this.ultimo_tipo_escala = listaUltimaEscala.get(listaUltimaEscala.size() - 1);
			} else {
				this.ultimo_tipo_escala = new intra_func_ultimo_tipo_escala();
			}
			RequestContext.getCurrentInstance().execute("PF('dlgAltrFuncionario').show();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limparFolgasMes(Folga folga)
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		try {
			this.dataInicio = new DateTime(this.dataInicio).toLocalDate().toDateTimeAtStartOfDay().minusMinutes(1)
					.toDate();
			this.dataFim = new DateTime(this.dataFim).toLocalDate().toDateTimeAtStartOfDay().plusDays(1).toDate();
			EscalaDeFolgaDAO dao = new EscalaDeFolgaDAO();
			dao.limparFolgasMes(folga.getCodigo(), this.condominio.getCodigo(), this.dataInicio, this.dataFim);
			this.limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Integer> getListaAnos() {
		List<Integer> listaAnos = new ArrayList<>();
		try {
			listaAnos.add(new DateTime().minusYears(2).getYear());
			listaAnos.add(new DateTime().minusYears(1).getYear());
			listaAnos.add(new DateTime().getYear());
			listaAnos.add(new DateTime().plusYears(1).getYear());
			listaAnos.add(new DateTime().plusYears(2).getYear());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAnos;
	}

	public boolean folgaJaExiste(Date data) {
		try {
			List<intra_escala_folga> folgas = this.funcionario.getFolgas();
			for (intra_escala_folga aux : folgas) {
				if (new DateTime(aux.getFolga()).withMillisOfDay(0).toLocalDate().toDateTimeAtStartOfDay()
						.isEqual(new DateTime(data).withMillisOfDay(0).toLocalDate().toDateTimeAtStartOfDay())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void salvarEscalaParam() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		try {
			EscalaDeFolgaDAO dao = new EscalaDeFolgaDAO();
			this.escala_param.setCodigoCondominio(this.condominio.getCodigo());
			dao.salvarEscalaParam(this.escala_param);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
			RequestContext.getCurrentInstance().execute("PF('dlgEscalaParam').hide();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String format(String pattern, Object value) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(pattern);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean diaFerias(Date inicioGozo, Date fimGozo, DateTime dia) {
		try {
			if (new DateTime(inicioGozo).toLocalDate().toDateTimeAtStartOfDay().isBefore(dia)
					&& new DateTime(fimGozo).toLocalDate().toDateTimeAtStartOfDay().plusHours(1)
							.isAfter(dia.toLocalDate().toDateTimeAtStartOfDay())) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setCookieDiaInicio() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("diaInicioEscala",
					String.valueOf(this.diaInicio), null);
			this.geraEscalaDeFolgas();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selecionaCondominio() {
		try {
			this.limpar();
			this.geraEscalaDeFolgas();
			this.pesquisaEscalaParam();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pesquisaEscalaParam() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		try {
			EscalaDeFolgaDAO dao = new EscalaDeFolgaDAO();
			List<intra_escala_param> listaEscalaParam = dao.pesquisaEscalaParam(this.condominio);
			if (listaEscalaParam.size() > 0) {
				this.escala_param = listaEscalaParam.get(0);
			} else {
				this.escala_param = new intra_escala_param();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String converteTipoEscala(int tipo) {
		String retorno = "";
		try {
			switch (tipo) {
			case 42:
				retorno = "4/2";
				break;
			case 51:
				retorno = "5/1";
				break;
			case 52:
				retorno = "5/2";
				break;
			case 61:
				retorno = "6/1";
				break;
			case 62:
				retorno = "6/2";
				break;
			case 1236:
				retorno = "12/36";
				break;
			default:
				retorno = "";
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public void abrirDlgPintar(Folga folga) {
		try {
			for (intra_funcionario aux : this.listaFuncionarios) {
				if (aux.getCodigo() == folga.getCodigo()) {
					this.funcionario = aux;
				}
			}
			RequestContext.getCurrentInstance().execute("PF('dlgPintar').show();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pintarPeriodo() {

	}

	public String removeX(String s) {
		try {
			s = s.toUpperCase();
			s = s.replace("X", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
}