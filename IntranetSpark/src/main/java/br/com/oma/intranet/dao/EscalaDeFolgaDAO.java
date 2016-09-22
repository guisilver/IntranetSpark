package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_escala_folga;
import br.com.oma.intranet.entidades.intra_escala_param;
import br.com.oma.intranet.entidades.intra_func_ultimo_tipo_escala;
import br.com.oma.intranet.entidades.intra_funcionario;
import br.com.oma.intranet.util.EnderecoCnd;
import br.com.oma.intranet.util.FuncionarioFerias;
import br.com.oma.intranet.util.FuncionarioHorario;
import br.com.oma.intranet.util.JPAUtil;

public class EscalaDeFolgaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5887957385577533880L;
	private EntityManager manager;

	public EscalaDeFolgaDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
	}

	public List<intra_escala_folga> getFolgas(intra_condominios intra_condominio, Date dataInicio, Date dataFim) {
		TypedQuery<intra_escala_folga> query = this.manager.createQuery(
				"from intra_escala_folga where codigoCondominio = :codigoCondominio and folga between :p2 and :p3 order by codigoFuncionario",
				intra_escala_folga.class);
		query.setParameter("codigoCondominio", intra_condominio.getCodigo());
		query.setParameter("p2", dataInicio);
		query.setParameter("p3", dataFim);
		return query.getResultList();
	}

	public int excluirFolga(Date data, int codigoFuncionario, int codigoCondominio) {
		Query query = this.manager.createNativeQuery(
				"delete intra_escala_folga where DAY(folga) = DAY(:p1) and MONTH(folga) = MONTH(:p1) and YEAR(folga) = YEAR(:p1) and codigoFuncionario = :p2 and codigoCondominio = :p3");
		query.setParameter("p1", data);
		query.setParameter("p2", codigoFuncionario);
		query.setParameter("p3", codigoCondominio);
		return query.executeUpdate();
	}

	public void limparFolgasMes(int codigoFuncionario, int codigoCondominio, Date dataInicio, Date dataFim) {
		Query query = this.manager.createQuery(
				"delete intra_escala_folga where folga between :p1 and :p2 and codigoFuncionario = :p3 and codigoCondominio = :p4");
		query.setParameter("p1", dataInicio);
		query.setParameter("p2", dataFim);
		query.setParameter("p3", codigoFuncionario);
		query.setParameter("p4", codigoCondominio);
		query.executeUpdate();
	}

	public List<intra_funcionario> getFuncionarios(intra_condominios condominio, Date dataInicio, Date dataFim)
			throws SQLException {
		List<intra_funcionario> listaFuncionarios = new ArrayList<>();
		intra_funcionario funcionario = null;
		String pesquisa = "select f.empresa, f.funcionario, p.nome, t.descricao, f.situacao from sigadm.dbo.gsffunc f "
				+ "inner join sigadm.dbo.gsftabel t on f.funcao = t.codigo "
				+ "inner join sigadm.dbo.cndcondo c on f.empresa = c.codigo "
				+ "inner join sigadm.dbo.gsfpessoa p on f.cod_pessoa = p.codigo "
				+ "where t.tipo_reg = 4 and c.codigo between 111 and 1300 and c.data_baixa is null and f.data_desligto is null and (f.situacao = 1 or f.situacao = 5) and empresa = :p1 "
				+ "order by t.codigo,t.descricao,f.empresa, f.funcionario";
		Query query = this.manager.createNativeQuery(pesquisa);
		query.setParameter("p1", condominio.getCodigo());
		@SuppressWarnings("unchecked")
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			funcionario = new intra_funcionario();
			funcionario.setCondominio(condominio);
			if (aux[1] != null) {
				funcionario.setCodigo(Integer.parseInt(aux[1].toString()));
			}
			if (aux[2] != null) {
				funcionario.setNome(aux[2].toString());
			}
			if (aux[3] != null) {
				funcionario.setFuncao(aux[3].toString());
			}
			if (aux[4] != null) {
				funcionario.setSituacao(Integer.parseInt(aux[4].toString()));
			}
			funcionario.getFolgas().addAll(this.pesquisaFolgasFuncionario(funcionario.getCodigo(),
					condominio.getCodigo(), dataInicio, dataFim));
			listaFuncionarios.add(funcionario);
		}
		return listaFuncionarios;
	}

	public void salvarFolgas(List<intra_escala_folga> folgas) {
		for (intra_escala_folga folga : folgas) {
			if (folga.getCodigo() == 0) {
				this.manager.persist(folga);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_escala_folga> pesquisaFolgasFuncionario(int codigoFuncionario, int codigoCondominio,
			Date dataInicio, Date dataFim) {
		Query query = this.manager.createQuery(
				"from intra_escala_folga where codigoFuncionario = :p1 and codigoCondominio = :p2 and folga between :p3 and :p4");
		query.setParameter("p1", codigoFuncionario);
		query.setParameter("p2", codigoCondominio);
		query.setParameter("p3", dataInicio);
		query.setParameter("p4", dataFim);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public FuncionarioFerias pesquisaFeriasFuncionario(int codigoFuncionario, int codigoCondominio, Date dataInicio,
			Date dataFim) throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
		FuncionarioFerias ferias = null;
		String pesquisa = "select inicio_gozo, final_gozo from sigadm.dbo.gsfferia where funcionario = :p1 and empresa = :p2 and (inicio_gozo between :p3 and :p4 or final_gozo between :p5 and :p6)";
		Query query = this.manager.createNativeQuery(pesquisa);
		query.setParameter("p1", codigoFuncionario);
		query.setParameter("p2", codigoCondominio);
		query.setParameter("p3", dataInicio);
		query.setParameter("p4", dataFim);
		query.setParameter("p5", dataInicio);
		query.setParameter("p6", dataFim);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			ferias = new FuncionarioFerias();

			if (aux[0] != null) {
				ferias.setInicio_gozo((Date) aux[0]);
			}

			if (aux[1] != null) {
				ferias.setFinal_gozo((Date) aux[1]);
			}
		}
		return ferias;
	}

	@SuppressWarnings("unchecked")
	public EnderecoCnd pesquisaEnderecoCnd(int codigoCondominio)
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
		EnderecoCnd endCnd = null;
		String pesquisa = "select cep,estado,cidade,cgc from sigadm.dbo.cndcondo where codigo = :p1";
		Query query = this.manager.createNativeQuery(pesquisa);
		query.setParameter("p1", codigoCondominio);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			endCnd = new EnderecoCnd();
			if (aux[0] != null) {
				endCnd.setCndCep(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				endCnd.setCndEstado(aux[1].toString());
			}
			if (aux[2] != null) {
				endCnd.setCndCidade(aux[2].toString());
			}
			if (aux[3] != null) {
				endCnd.setCndCnpj(Long.parseLong(aux[3].toString()));
			}
		}
		return endCnd;
	}

	public void salvarEscalaParam(intra_escala_param escala_param) {
		if (escala_param.getCodigo() == 0) {
			this.manager.persist(escala_param);
		} else {
			this.manager.merge(escala_param);
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_escala_param> pesquisaEscalaParam(intra_condominios condominio) {
		Query query = this.manager.createQuery("from intra_escala_param where codigoCondominio = :p1");
		query.setParameter("p1", condominio.getCodigo());
		return query.getResultList();
	}

	public void salvarUltimoTipoEscala(intra_func_ultimo_tipo_escala ultimo_tipo_escala) {
		if (ultimo_tipo_escala.getCodigo() == 0) {
			this.manager.persist(ultimo_tipo_escala);
		} else {
			this.manager.merge(ultimo_tipo_escala);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Date> pesquisaUltimaFolga(int codigoFuncionario, int codigoCondominio) {
		Query query = this.manager.createQuery(
				"select MAX(folga) from intra_escala_folga where codigoFuncionario = :p1 and codigoCondominio = :p2");
		query.setParameter("p1", codigoFuncionario);
		query.setParameter("p2", codigoCondominio);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_func_ultimo_tipo_escala> pesquisaUltimoTipoEscala(int codigoFuncionario, int codigoCondominio) {
		Query query = this.manager.createQuery(
				"from intra_func_ultimo_tipo_escala where codigoFuncionario = :p1 and codigoCondominio = :p2");
		query.setParameter("p1", codigoFuncionario);
		query.setParameter("p2", codigoCondominio);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public FuncionarioHorario pesquisaFuncionarioHorarios(int codigoFuncionario, int codigoCondominio)
			throws SQLException, FileNotFoundException, ClassNotFoundException, IOException {
		FuncionarioHorario horario = null;
		String pesquisa = "select h.entr_prim,h.saida_prim,h.entr_seg,h.saida_seg from sigadm.dbo.gsffunc f inner join sigadm.dbo.gsfhorar h on f.codigo_horario = h.codigo where f.funcionario = :p1 and empresa = :p2";
		Query query = this.manager.createNativeQuery(pesquisa);
		query.setParameter("p1", codigoFuncionario);
		query.setParameter("p2", codigoCondominio);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			horario = new FuncionarioHorario();
			if (aux[0] != null) {
				horario.setEntr_prim(aux[0].toString());
			}
			if (aux[1] != null) {
				horario.setSaida_prim(aux[1].toString());
			}
			if (aux[2] != null) {
				horario.setEntr_seg(aux[2].toString());
			}
			if (aux[3] != null) {
				horario.setSaida_seg(aux[3].toString());
			}
		}
		return horario;
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios() {
		return this.manager.createQuery("from intra_condominios order by nome").getResultList();
	}
}
