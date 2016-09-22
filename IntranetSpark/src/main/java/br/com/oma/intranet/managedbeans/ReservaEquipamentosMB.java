package br.com.oma.intranet.managedbeans;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.oma.intranet.dao.ReservaEquipamentosDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_reservas_ti;
import br.com.oma.intranet.util.StringUtil;

@ViewScoped
@ManagedBean
public class ReservaEquipamentosMB extends DefaultMB {

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private intra_reservas_ti reserva = new intra_reservas_ti();
	private ScheduleModel listaReservas;
	private intra_condominios condominioSelecionado;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private boolean emprestado;
	private boolean devolvido;
	private boolean todosEquipamentos;

	public void setSessao(SessaoMB sessao) {
		this.sessaoMB = sessao;
	}

	public intra_reservas_ti getReserva() {
		return reserva;
	}

	public void setReserva(intra_reservas_ti reserva) {
		this.reserva = reserva;
	}

	public ScheduleModel getListaReservas() {
		try {
			if (this.listaReservas == null) {
				this.pesquisaReservas();
			}
			return listaReservas;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void setListaReservas(ScheduleModel listaReservas) {
		this.listaReservas = listaReservas;
	}

	public intra_condominios getCondominioSelecionado() {
		return condominioSelecionado;
	}

	public void setCondominioSelecionado(intra_condominios condominioSelecionado) {
		this.condominioSelecionado = condominioSelecionado;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public boolean isEmprestado() {
		return emprestado;
	}

	public void setEmprestado(boolean emprestado) {
		this.emprestado = emprestado;
	}

	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}

	public boolean isTodosEquipamentos() {
		return this.todosEquipamentos;
	}

	public void setTodosEquipamentos(boolean todosEquipamentos) {
		this.todosEquipamentos = todosEquipamentos;
	}

	public void pesquisaReservas() {
		ReservaEquipamentosDAO dao = new ReservaEquipamentosDAO();
		List<intra_reservas_ti> lstRetorno = dao.listarReservas();
		this.listaReservas = new DefaultScheduleModel();
		DefaultScheduleEvent evento;
		for (intra_reservas_ti aux : lstRetorno) {
			if (aux.getData() != null) {
				String titulo = "";
				evento = new DefaultScheduleEvent(titulo, new DateTime(
						aux.getData()).withMillisOfDay(0).toDate(),
						new DateTime(aux.getData()).withMillisOfDay(86340000)
								.toDate(), aux);
				evento.setAllDay(true);
				String id = String.valueOf(aux.getCodigo());
				evento.setId(id);
				evento.setData(aux);
				if (aux.getNome_gerente() != null) {
					evento.setDescription(aux.getCod_condominio()
							+ " - "
							+ StringUtil.trataNomeCalendario(aux
									.getNome_gerente()));
					evento.setTitle(aux.getCod_condominio()
							+ " - "
							+ StringUtil.trataNomeCalendario(aux
									.getNome_gerente()));
					evento.setStyleClass(aux.getStatus());
				}
				this.listaReservas.addEvent(evento);
			}
		}
	}

	public void abreDlgNovaReserva() {
		this.reserva = new intra_reservas_ti();
	}

	public void addNovaReserva() {
		try {
			ReservaEquipamentosDAO dao = new ReservaEquipamentosDAO();
			this.reserva.setCod_gerente(this.sessaoMB.getGerenteSelecionado().getCodigo());
			this.reserva.setNome_gerente(this.sessaoMB.getGerenteSelecionado().getNome());
			this.reserva.setCod_condominio(this.condominioSelecionado
					.getCodigo());
			this.reserva.setNome_condominio(this.condominioSelecionado
					.getNome());
			this.reserva.setStatus("P");
			this.reserva.getHorario();
			this.reserva.setData_inserido(new Date());
			this.reserva.setHorario(this.reserva.getData());
			dao.addNovaReserva(this.reserva,
					this.sessaoMB.getUsuario().getNome(), this.sessaoMB.getIpUser());
			this.limpar();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null,
					"/reserva-de-equipamentos/salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void salvarStatus() {
		try {
			ReservaEquipamentosDAO dao = new ReservaEquipamentosDAO();
			if (this.reserva.getStatus().equals("P") && this.emprestado) {
				this.reserva.setStatus("E");
			}
			if (this.reserva.getStatus().equals("E") && this.devolvido) {
				this.reserva.setStatus("D");
			}
			dao.salvarStatus(this.reserva, this.sessaoMB.getUsuario().getNome(),
					this.sessaoMB.getIpUser());
			this.limpar();
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null,
					"salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void onEventSelect(SelectEvent selectEvent) {
		this.event = (ScheduleEvent) selectEvent.getObject();
		intra_reservas_ti rsv = (intra_reservas_ti) this.event.getData();
		try {
			this.emprestado = false;
			this.devolvido = false;
			this.reserva = rsv;
			String caminho = FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationContextPath();
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							caminho
									+ "/reserva-de-equipamentos/detalhes-reserva.xhtml?codigoReserva="
									+ this.reserva.getCodigo());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String verificaStatus(String status) {
		String retorno = "";
		switch (status) {
		case "P":
			retorno = "Pendente";
			break;
		case "E":
			retorno = "Emprestado";
			break;
		case "D":
			retorno = "Devolvido";
			break;
		}
		return retorno;
	}

	public void excluiReservaTi() {
		if (this.reserva != null && this.reserva.getCodigo() > 0) {
			try {
				ReservaEquipamentosDAO dao = new ReservaEquipamentosDAO();
				dao.excluiReservaTi(this.reserva, this.sessaoMB.getUsuario()
						.getNome(), this.sessaoMB.getIpUser());
				this.limpar();
				FacesContext fc = FacesContext.getCurrentInstance();
				NavigationHandler nh = fc.getApplication()
						.getNavigationHandler();
				nh.handleNavigation(fc, null,
						"excluido-com-sucesso.xhtml?faces-redirect=true");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void limpar() {
		this.reserva = new intra_reservas_ti();
		this.listaReservas = null;
	}

	public void selecionaTodosEquipamentos() {
		RequestContext.getCurrentInstance().reset("frmNovaReserva");
		this.reserva.setAdaptador(this.todosEquipamentos);
		this.reserva.setApresentador(this.todosEquipamentos);
		this.reserva.setEnergia(this.todosEquipamentos);
		this.reserva.setVga(this.todosEquipamentos);
		this.reserva.setMala(this.todosEquipamentos);
		this.reserva.setPendrive(this.todosEquipamentos);
		this.reserva.setProjetor(this.todosEquipamentos);
		this.reserva.setTela(this.todosEquipamentos);
		this.reserva.setNotebook(this.todosEquipamentos);
	}

	public void pesquisaReservaSelecionada() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, String> params = ctx.getExternalContext()
				.getRequestParameterMap();
		String codigoReserva = params.get("codigoReserva");
		if (codigoReserva != null) {
			ReservaEquipamentosDAO dao = new ReservaEquipamentosDAO();
			this.reserva = dao.pesquisaReservaPorCodigo(Integer
					.parseInt(codigoReserva));
		}
		verificaTodosSelecionados();
	}

	public void verificaTodosSelecionados() {
		if (this.reserva != null) {
			boolean todos = true;
			if (!this.reserva.isAdaptador()) {
				todos = false;
			}
			if (!this.reserva.isApresentador()) {
				todos = false;
			}
			if (!this.reserva.isEnergia()) {
				todos = false;
			}
			if (!this.reserva.isVga()) {
				todos = false;
			}
			if (!this.reserva.isMala()) {
				todos = false;
			}
			if (!this.reserva.isPendrive()) {
				todos = false;
			}
			if (!this.reserva.isProjetor()) {
				todos = false;
			}
			if (!this.reserva.isTela()) {
				todos = false;
			}
			if (!this.reserva.isNotebook()) {
				todos = false;
			}
			this.todosEquipamentos = todos;
		}
	}

}
