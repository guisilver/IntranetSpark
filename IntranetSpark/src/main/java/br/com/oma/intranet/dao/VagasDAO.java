package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_candidato;
import br.com.oma.intranet.entidades.intra_candidato_vaga;
import br.com.oma.intranet.entidades.intra_candidato_vaga_historico;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.JPAUtil;

public class VagasDAO {

	private EntityManager manager;

	public VagasDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato_vaga> getVagas(String[] situacao, intra_candidato_vaga vaga, Date dataInicio,
			Date dataFim) {
		if (situacao != null && situacao.length > 0) {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("FROM intra_candidato_vaga where 1 = 1");

			stringBuffer.append(" and situacao in ");
			List<String> listaSit = Arrays.asList(situacao);
			if (listaSit.size() == 1) {
				stringBuffer.append("(" + Integer.parseInt(listaSit.get(0)) + ")");
			} else if (listaSit.size() > 1) {
				for (int i = 0; i <= listaSit.size() - 1; i++) {
					if (i == 0) {
						stringBuffer.append("(" + Integer.parseInt(listaSit.get(i)));
					} else if (i > 0 && i < listaSit.size() - 1) {
						stringBuffer.append("," + Integer.parseInt(listaSit.get(i)));
					} else if (i == listaSit.size() - 1) {
						stringBuffer.append("," + Integer.parseInt(listaSit.get(i)) + ")");
					}
				}
			}

			if (vaga != null && vaga.getResponsavelVaga() != null && !vaga.getResponsavelVaga().trim().isEmpty()
					&& !vaga.getResponsavelVaga().equals("Todos")) {
				stringBuffer.append(" and responsavelVaga = :responsavelVaga");
			}
			if (dataInicio != null && dataFim != null) {
				stringBuffer.append(" and dataAbertura between :dataInicio and :dataFim");
			}
			stringBuffer.append(" ORDER BY dataAbertura");
			Query query = this.manager.createQuery(stringBuffer.toString(), intra_candidato_vaga.class);
			if (vaga != null && vaga.getResponsavelVaga() != null && !vaga.getResponsavelVaga().trim().isEmpty()
					&& !vaga.getResponsavelVaga().equals("Todos")) {
				query.setParameter("responsavelVaga", vaga.getResponsavelVaga());
			}
			if (dataInicio != null && dataFim != null) {
				query.setParameter("dataInicio", dataInicio);
				query.setParameter("dataFim", dataFim);
			}
			return query.getResultList();
		}
		return null;
	}

	public void salvarNovaVaga(intra_candidato_vaga vaga) {
		this.manager.persist(vaga);
	}

	public void salvarAlteracoesVaga(intra_candidato_vaga vaga) {
		this.manager.merge(vaga);
		this.manager.detach(vaga);
	}

	public void excluirVaga(intra_candidato_vaga vaga) {
		Query query = this.manager
				.createNativeQuery("delete intra_candidato_intra_candidato_vaga where vagas_codigo = :p1");
		query.setParameter("p1", vaga.getCodigo());
		query.executeUpdate();

		vaga = this.manager.merge(vaga);
		this.manager.remove(vaga);
	}

	@SuppressWarnings("unchecked")
	public intra_candidato_vaga pesquisaVagaPorCodigo(int codigoVaga) {
		Query query = this.manager.createQuery("from intra_candidato_vaga v where v.codigo = :p1");
		query.setParameter("p1", codigoVaga);
		List<intra_candidato_vaga> v = query.getResultList();
		if (v != null && v.size() > 0) {
			return v.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaTodosCondominios() {
		Query query = this.manager.createQuery("from intra_condominios order by codigo");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato_vaga> pesquisaVagasDisponiveis(intra_candidato candidato) {
		Query query = this.manager.createQuery(
				"FROM intra_candidato_vaga WHERE situacao = 1 AND :candidato NOT MEMBER OF candidatos ORDER BY cargo");
		query.setParameter("candidato", candidato);
		return query.getResultList();
	}

	public List<intra_candidato_vaga> getTodasVagas() {
		return this.manager.createQuery("FROM intra_candidato_vaga ORDER BY cargo", intra_candidato_vaga.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<String> getListaUsuarioRH() {
		return (List<String>) this.manager
				.createNativeQuery(
						"select nome from intra_usuario u inner join intra_usuario_intra_grupo_depto d on u.email = d.intra_usuario_email where d.grupoDepto_nome = 'RH' ORDER BY nome")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato> pesquisarCandidatos(int codigo) {
		intra_candidato candidato = null;
		List<intra_candidato> listaCandidatos = new ArrayList<>();
		Query query = this.manager.createNativeQuery(
				"use omacorp select c.codigo,c.nome from intra_candidato_vaga v inner join intra_candidato_intra_candidato_vaga x on v.codigo = x.vagas_codigo "
						+ "inner join intra_candidato c on x.candidatos_codigo = c.codigo where v.codigo = :codigo");
		query.setParameter("codigo", codigo);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			candidato = new intra_candidato();
			if (aux[0] != null) {
				candidato.setCodigo(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				candidato.setNome(aux[1].toString());
			}
			listaCandidatos.add(candidato);
		}
		return listaCandidatos;
	}

	@SuppressWarnings("unchecked")
	public boolean possuiCandidato(int codigo) {
		Query query = this.manager.createNativeQuery(
				"use omacorp select c.codigo,c.nome from intra_candidato_vaga v inner join intra_candidato_intra_candidato_vaga x on v.codigo = x.vagas_codigo "
						+ "inner join intra_candidato c on x.candidatos_codigo = c.codigo where v.codigo = :codigo");
		query.setParameter("codigo", codigo);
		List<Object[]> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void salvarHistorico(intra_candidato_vaga_historico historico) {
		this.manager.persist(historico);
		this.manager.flush();
	}

	public int getTotalVagas(Date dataInicio, Date dataFim, String situacao[]) {
		if (situacao != null && situacao.length > 0) {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("FROM intra_candidato_vaga where 1 = 1 and dataAbertura between :p1 and :p2");
			stringBuffer.append(" and situacao in ");
			List<String> listaSit = Arrays.asList(situacao);
			if (listaSit.size() == 1) {
				stringBuffer.append("(" + Integer.parseInt(listaSit.get(0)) + ")");
			} else if (listaSit.size() > 1) {
				for (int i = 0; i <= listaSit.size() - 1; i++) {
					if (i == 0) {
						stringBuffer.append("(" + Integer.parseInt(listaSit.get(i)));
					} else if (i > 0 && i < listaSit.size() - 1) {
						stringBuffer.append("," + Integer.parseInt(listaSit.get(i)));
					} else if (i == listaSit.size() - 1) {
						stringBuffer.append("," + Integer.parseInt(listaSit.get(i)) + ")");
					}
				}
			}
			stringBuffer.append(" order by dataAbertura");
			Query query = this.manager.createQuery(stringBuffer.toString());
			query.setParameter("p1", dataInicio);
			query.setParameter("p2", dataFim);
			return query.getResultList().size();
		}
		return 0;
	}

	public int pesquisaVagasPorResponsavel(Date dataInicio, Date dataFim, String responsavel, String[] situacao) {
		if (situacao != null && situacao.length > 0) {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(
					"FROM intra_candidato_vaga where 1 = 1 and dataAbertura between :p1 and :p2 and responsavelVaga = :p3");
			stringBuffer.append(" and situacao in ");
			List<String> listaSit = Arrays.asList(situacao);
			if (listaSit.size() == 1) {
				stringBuffer.append("(" + Integer.parseInt(listaSit.get(0)) + ")");
			} else if (listaSit.size() > 1) {
				for (int i = 0; i <= listaSit.size() - 1; i++) {
					if (i == 0) {
						stringBuffer.append("(" + Integer.parseInt(listaSit.get(i)));
					} else if (i > 0 && i < listaSit.size() - 1) {
						stringBuffer.append("," + Integer.parseInt(listaSit.get(i)));
					} else if (i == listaSit.size() - 1) {
						stringBuffer.append("," + Integer.parseInt(listaSit.get(i)) + ")");
					}
				}
			}
			stringBuffer.append(" order by dataAbertura");
			Query query = this.manager.createQuery(stringBuffer.toString());
			query.setParameter("p1", dataInicio);
			query.setParameter("p2", dataFim);
			query.setParameter("p3", responsavel);
			return query.getResultList().size();
		}
		return 0;
	}

}
