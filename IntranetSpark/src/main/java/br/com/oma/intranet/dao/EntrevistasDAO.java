package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.Entrevista;
import br.com.oma.intranet.util.JPAUtil;

public class EntrevistasDAO {

	private EntityManager manager;

	public EntrevistasDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<Entrevista> getEntrevistas() {
		List<Entrevista> listaEntrevistas = new ArrayList<>();
		Entrevista entrevista = null;
		Query query = this.manager.createNativeQuery(
				"SELECT codigo,nome,dataEntrevista,entrevistado,horarioChegada FROM intra_candidato ORDER BY nome");
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			entrevista = new Entrevista();
			if (aux[0] != null) {
				entrevista.setCodigoCandidato(Integer.valueOf(aux[0].toString()));
			}
			if (aux[1] != null) {
				entrevista.setNomeEntrevistado(aux[1].toString());
			}
			if (aux[2] != null) {
				entrevista.setDataEntrevista((Date) aux[2]);
			}
			if (aux[3] != null) {
				entrevista.setEntrevistado(Boolean.parseBoolean(aux[3].toString()));
			}
			if (aux[4] != null) {
				entrevista.setHorarioChegada((Date) aux[4]);
			}
			listaEntrevistas.add(entrevista);
		}
		return listaEntrevistas;
	}

	@SuppressWarnings("unchecked")
	public List<Entrevista> getEntrevistasMes(Date dataInicio, Date dataFim) {
		List<Entrevista> listaEntrevistas = new ArrayList<>();
		Entrevista entrevista = null;
		Query query = this.manager.createNativeQuery(
				"SELECT codigo,nome,dataEntrevista,entrevistado,horarioChegada FROM intra_candidato WHERE dataEntrevista between :dataInicio and :dataFim ORDER BY nome");
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			entrevista = new Entrevista();
			if (aux[0] != null) {
				entrevista.setCodigoCandidato(Integer.valueOf(aux[0].toString()));
			}
			if (aux[1] != null) {
				entrevista.setNomeEntrevistado(aux[1].toString());
			}
			if (aux[2] != null) {
				entrevista.setDataEntrevista((Date) aux[2]);
			}
			if (aux[3] != null) {
				entrevista.setEntrevistado(Boolean.parseBoolean(aux[3].toString()));
			}
			if (aux[4] != null) {
				entrevista.setHorarioChegada((Date) aux[4]);
			}
			listaEntrevistas.add(entrevista);
		}
		return listaEntrevistas;
	}

}
