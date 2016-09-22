package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.oma.intranet.dao.CandidatosDAO;
import br.com.oma.intranet.dao.EntrevistasDAO;
import br.com.oma.intranet.entidades.Entrevista;
import br.com.oma.intranet.entidades.intra_candidato;
import br.com.oma.intranet.util.StringUtil;

@ViewScoped
@ManagedBean
public class EntrevistasMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -270619431980653271L;
	private ScheduleModel listaEntrevistas;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private intra_candidato candidato;
	private boolean chegou;
	private Date dataInicio, dataFim;
	private int concluidas, pendentes;

	@PostConstruct
	public void init() {
		this.dataInicio = new DateTime().withMillisOfDay(0).withDayOfMonth(1).toDate();
		this.dataFim = new DateTime(this.dataInicio).plusMonths(1).minusDays(1).withHourOfDay(23).withMinuteOfHour(59)
				.toDate();
		this.pesquisarEntrevistas();
	}

	public intra_candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(intra_candidato candidato) {
		this.candidato = candidato;
	}

	public boolean isChegou() {
		return chegou;
	}

	public void setChegou(boolean chegou) {
		this.chegou = chegou;
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

	public int getConcluidas() {
		return concluidas;
	}

	public void setConcluidas(int concluidas) {
		this.concluidas = concluidas;
	}

	public int getPendentes() {
		return pendentes;
	}

	public void setPendentes(int pendentes) {
		this.pendentes = pendentes;
	}

	public ScheduleModel getListaEntrevistas() {
		return listaEntrevistas;
	}

	public void setListaEntrevistas(ScheduleModel listaEntrevistas) {
		this.listaEntrevistas = listaEntrevistas;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void onEventSelect(SelectEvent selectEvent) {
		this.event = (ScheduleEvent) selectEvent.getObject();
		Entrevista entrevista = (Entrevista) this.event.getData();
		CandidatosDAO dao = new CandidatosDAO();
		this.candidato = dao.pesquisaCandidatoPorCodigo(entrevista.getCodigoCandidato());
		if (this.candidato != null && this.candidato.getHorarioChegada() != null) {
			this.chegou = true;
		} else {
			this.chegou = false;
		}
	}

	public void pesquisarEntrevistas() {
		EntrevistasDAO dao = new EntrevistasDAO();
		List<Entrevista> lstRetorno = dao.getEntrevistas();
		this.listaEntrevistas = new DefaultScheduleModel();
		DefaultScheduleEvent evento;
		for (Entrevista aux : lstRetorno) {
			if (aux.getDataEntrevista() != null) {
				String titulo = aux.getNomeEntrevistado();
				evento = new DefaultScheduleEvent(titulo,
						new DateTime(aux.getDataEntrevista()).withMillisOfDay(0).toDate(),
						new DateTime(aux.getDataEntrevista()).withMillisOfDay(86340000).toDate(), aux);
				String id = String.valueOf(aux.getCodigoCandidato());
				evento.setId(id);
				evento.setData(aux);
				if (aux.getHorarioChegada() != null) {
					evento.setStartDate(new DateTime(aux.getDataEntrevista())
							.withHourOfDay(new DateTime(aux.getHorarioChegada()).getHourOfDay())
							.withMinuteOfHour(new DateTime(aux.getHorarioChegada()).getMinuteOfHour())
							.withSecondOfMinute(new DateTime(aux.getHorarioChegada()).getSecondOfMinute()).toDate());
					evento.setEndDate(new DateTime(aux.getDataEntrevista())
							.withHourOfDay(new DateTime(aux.getHorarioChegada()).getHourOfDay())
							.withMinuteOfHour(new DateTime(aux.getHorarioChegada()).getMinuteOfHour())
							.withSecondOfMinute(new DateTime(aux.getHorarioChegada()).getSecondOfMinute()).toDate());
				} else {
					evento.setStartDate(aux.getDataEntrevista());
					evento.setEndDate(aux.getDataEntrevista());
				}
				if (!aux.isEntrevistado() && aux.getHorarioChegada() == null) {
					evento.setStyleClass("nao-entrevistado");
					evento.setStartDate(
							new DateTime(aux.getDataEntrevista()).withHourOfDay(23).withMinuteOfHour(59).toDate());
					evento.setEndDate(
							new DateTime(aux.getDataEntrevista()).withHourOfDay(23).withMinuteOfHour(59).toDate());
				} else if (!aux.isEntrevistado() && aux.getHorarioChegada() != null) {
					evento.setStyleClass("chegou");
				} else {
					evento.setStyleClass("entrevistado");
				}
				if (aux.getNomeEntrevistado() != null) {
					evento.setDescription(StringUtil.trataNomeCalendario(aux.getNomeEntrevistado()));
					evento.setTitle(StringUtil.trataNomeCalendario(aux.getNomeEntrevistado()));
				}
				this.listaEntrevistas.addEvent(evento);
			}
		}
		this.contador();
	}

	public void salvarChegada() {
		try {
			if (this.chegou) {
				this.candidato.setHorarioChegada(new Date());
				this.candidato.setDataEntrevista(new DateTime(this.candidato.getDataEntrevista())
						.withHourOfDay(new DateTime(this.candidato.getHorarioChegada()).getHourOfDay())
						.withMinuteOfHour(new DateTime(this.candidato.getHorarioChegada()).getMinuteOfHour())
						.withSecondOfMinute(new DateTime(this.candidato.getHorarioChegada()).getSecondOfMinute())
						.toDate());
			} else {
				this.candidato.setHorarioChegada(null);
				this.candidato.setDataEntrevista(new DateTime(this.candidato.getDataEntrevista()).withHourOfDay(0)
						.withMinuteOfHour(0)
						.withSecondOfMinute(new DateTime(this.candidato.getHorarioChegada()).getSecondOfMinute())
						.toDate());
			}
			CandidatosDAO dao = new CandidatosDAO();
			dao.salvarAlteracoesCandidato(this.candidato);
			RequestContext.getCurrentInstance().execute("PF('dlgCandidato').hide();");
			this.candidato = null;
			this.pesquisarEntrevistas();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirPerfilCompleto() {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					caminho + "/rh/candidatos/alterar-candidato?codigoCandidato=" + this.candidato.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int calculaIdade() {
		if (this.candidato != null) {
			return Years.yearsBetween(new DateTime(this.candidato.getDataNascimento()), new DateTime(new Date()))
					.getYears();
		} else {
			return 0;
		}
	}

	public void verificaMesCalendario(final String str) {
		String[] mesAno = str.split(" ");
		if (mesAno != null && mesAno.length == 2) {
			String mes = mesAno[0].trim();
			String ano = mesAno[1].trim();
			int mesInt = getMesInt(mes);
			int anoInt = Integer.parseInt(ano);
			this.dataInicio = new DateTime().withMillisOfDay(0).withDayOfMonth(1).withMonthOfYear(mesInt)
					.withYear(anoInt).toDate();
			this.dataFim = new DateTime(this.dataInicio).plusMonths(1).minusDays(1).withHourOfDay(23)
					.withMinuteOfHour(59).toDate();
			this.concluidas = 0;
			this.pendentes = 0;
			EntrevistasDAO dao = new EntrevistasDAO();
			List<Entrevista> l = dao.getEntrevistasMes(this.dataInicio, this.dataFim);
			for (Entrevista aux : l) {
				if (aux.isEntrevistado()) {
					this.concluidas++;
				} else {
					this.pendentes++;
				}
			}
		}
	}

	public void contador() {
		this.concluidas = 0;
		this.pendentes = 0;
		EntrevistasDAO dao = new EntrevistasDAO();
		List<Entrevista> l = dao.getEntrevistasMes(this.dataInicio, this.dataFim);
		for (Entrevista aux : l) {
			if (aux.isEntrevistado()) {
				this.concluidas++;
			} else {
				this.pendentes++;
			}
		}
	}

	public int getMesInt(String mes) {
		switch (mes) {
		case "Janeiro":
			return 1;
		case "Fevereiro":
			return 2;
		case "Março":
			return 3;
		case "Abril":
			return 4;
		case "Maio":
			return 5;
		case "Junho":
			return 6;
		case "Julho":
			return 7;
		case "Agosto":
			return 8;
		case "Setembro":
			return 9;
		case "Outubro":
			return 10;
		case "Novembro":
			return 11;
		case "Dezembro":
			return 12;
		default:
			return 0;
		}
	}
}
