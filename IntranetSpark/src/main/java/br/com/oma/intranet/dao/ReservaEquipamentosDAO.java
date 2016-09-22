package br.com.oma.intranet.dao;

import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.entidades.intra_assembleia;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.entidades.intra_reservas_ti;
import br.com.oma.intranet.util.JPAUtil;

public class ReservaEquipamentosDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7612538982291530486L;
	private EntityManager manager;

	public ReservaEquipamentosDAO() {
		this.manager = JPAUtil.getManager();
	}

	public List<intra_reservas_ti> listarReservas() {
		TypedQuery<intra_reservas_ti> query = this.manager
				.createQuery(
						"from intra_reservas_ti where data > GETDATE() - 90 "
								+ "and (adaptador = 1 or apresentador = 1 or energia = 1 or mala = 1 or pendrive = 1 or projetor = 1 or tela = 1 or vga = 1 or notebook = 1)",
						intra_reservas_ti.class);
		return query.getResultList();
	}

	public intra_reservas_ti pesquisaReservas(int codigo) {
		return this.manager.find(intra_reservas_ti.class, codigo);
	}

	public void salvarStatus(intra_reservas_ti reserva, String usuario,
			String ip) {
		this.manager.merge(reserva);
		this.manager.flush();

		String resumoInfos = "Cod CND: " + reserva.getCod_condominio()
				+ "; Nome CND: " + reserva.getNome_condominio()
				+ "; Cod. Assembleia: " + reserva.getCod_assembleia()
				+ "; Cod. Gerente: " + reserva.getCod_gerente()
				+ "; Nome Gerente: " + reserva.getNome_gerente()
				+ "; Cod. Reserva: " + reserva.getCodigo() + "; Obs: "
				+ reserva.getObs() + "; Status: " + reserva.getStatus()
				+ "; Tipo Assembleia: " + reserva.getTipo()
				+ "; Data Assembleia: " + reserva.getData()
				+ "; Data Inserido: " + reserva.getData_inserido()
				+ "; Horário Assembleia" + reserva.getHorario();

		intra_log_geral log = new intra_log_geral(reserva.getCod_condominio(),
				this.retornaIdUsuario(), "intra_reservas_ti", false, true,
				false, resumoInfos, new Date(), 0, null, null);
		log.setIp(ip);
		this.logGeral(log);
	}

	@SuppressWarnings("unchecked")
	public void excluiReservaTi(intra_reservas_ti reserva, String usuario,
			String ip) {
		
		Query query = this.manager.createQuery("from intra_assembleia where reserva.codigo = :p1");
		query.setParameter("p1", reserva.getCodigo());
		List<intra_assembleia> l = query.getResultList();
		for(intra_assembleia aux : l){
			aux.setReserva(null);
			this.manager.merge(aux);
			this.manager.flush();
		}
		
		reserva = this.manager.merge(reserva);
		this.manager.remove(reserva);

		String resumoInfos = "Cod CND: " + reserva.getCod_condominio()
				+ "; Nome CND: " + reserva.getNome_condominio()
				+ "; Cod. Assembleia: " + reserva.getCod_assembleia()
				+ "; Cod. Gerente: " + reserva.getCod_gerente()
				+ "; Nome Gerente: " + reserva.getNome_gerente()
				+ "; Cod. Reserva: " + reserva.getCodigo() + "; Obs: "
				+ reserva.getObs() + "; Status: " + reserva.getStatus()
				+ "; Tipo Assembleia: " + reserva.getTipo()
				+ "; Data Assembleia: " + reserva.getData()
				+ "; Data Inserido: " + reserva.getData_inserido()
				+ "; Horário Assembleia" + reserva.getHorario();

		intra_log_geral log = new intra_log_geral(reserva.getCod_condominio(),
				this.retornaIdUsuario(), "intra_reservas_ti", false, false,
				true, resumoInfos, new Date(), 0, null, null);
		log.setIp(ip);
		this.logGeral(log);
	}

	public void addNovaReserva(intra_reservas_ti reserva, String usuario,
			String ip) {
		this.manager.persist(reserva);

		String resumoInfos = "Cod CND: " + reserva.getCod_condominio()
				+ "; Nome CND: " + reserva.getNome_condominio()
				+ "; Cod. Assembleia: " + reserva.getCod_assembleia()
				+ "; Cod. Gerente: " + reserva.getCod_gerente()
				+ "; Nome Gerente: " + reserva.getNome_gerente()
				+ "; Cod. Reserva: " + reserva.getCodigo() + "; Obs: "
				+ reserva.getObs() + "; Status: " + reserva.getStatus()
				+ "; Tipo Assembleia: " + reserva.getTipo()
				+ "; Data Assembleia: " + reserva.getData()
				+ "; Data Inserido: " + reserva.getData_inserido()
				+ "; Horário Assembleia" + reserva.getHorario();

		intra_log_geral log = new intra_log_geral(reserva.getCod_condominio(),
				this.retornaIdUsuario(), "intra_reservas_ti", true, false,
				false, resumoInfos, new Date(), 0, null, null);
		log.setIp(ip);
		this.logGeral(log);
	}

	@SuppressWarnings("unchecked")
	public List<intra_grupo_gerente> getGerentes() {
		return this.manager.createQuery(
				"from intra_grupo_gerente order by nome").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominiosGerente(int codigoGerente) {
		Query query = this.manager
				.createQuery("from intra_condominios where codigoGerente = :codigoGerente");
		query.setParameter("codigoGerente", codigoGerente);
		return query.getResultList();
	}

	public intra_reservas_ti pesquisaReservaPorCodigo(int codigoReserva) {
		return this.manager.find(intra_reservas_ti.class, codigoReserva);
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

}
