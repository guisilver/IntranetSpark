package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import br.com.oma.intranet.entidades.intra_cadastro_recepcao;
import br.com.oma.intranet.entidades.intra_cadastro_recepcao_foto;
import br.com.oma.intranet.entidades.intra_candidato;
import br.com.oma.intranet.entidades.intra_candidato_dependentes;
import br.com.oma.intranet.entidades.intra_candidato_foto;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.util.JPAUtil;

public class CandidatosDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6277063720138059059L;
	private EntityManager manager;

	public CandidatosDAO() {
		this.manager = JPAUtil.getManager();
	}

	public void salvarCandidato(intra_candidato candidato) {
		if (candidato.getCodigo() == 0) {
			candidato.setDataCadastro(new Date());
			this.manager.persist(candidato);
			intra_log_geral log = new intra_log_geral(candidato.getCodigo(), retornaIdUsuario(), "intra_candidato",
					true, false, false, candidato.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		} else {
			this.manager.merge(candidato);
			intra_log_geral log = new intra_log_geral(candidato.getCodigo(), retornaIdUsuario(), "intra_candidato",
					false, true, false, candidato.toString(), new Date(), 0, null, null);
			this.logGeral(log);
		}
	}

	public intra_candidato salvarAlteracoesCandidato(intra_candidato candidatoSelecionado) {
		candidatoSelecionado = this.manager.merge(candidatoSelecionado);
		this.manager.flush();
		intra_log_geral log = new intra_log_geral(candidatoSelecionado.getCodigo(), retornaIdUsuario(),
				"intra_candidato", false, true, false, candidatoSelecionado.toString(), new Date(), 0, null, null);
		this.logGeral(log);
		return candidatoSelecionado;
	}

	@SuppressWarnings("unchecked")
	public List<String> getListaCidades() {
		return this.manager.createNativeQuery("select nome from intra_cidade where codigo_estado = 26").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato> pesquisarCandidatos(String palavraChave, String cidadeSelecionada, int idadeInicio,
			int idadeFim, double salarioInicio, double salarioFim, intra_candidato candidato) {

		boolean pesquisar = false;

		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from intra_candidato as c where 1 = 1");

		if (palavraChave != null && !palavraChave.trim().isEmpty()) {
			buffer.append(" and (nome COLLATE Latin1_general_CI_AI like :pc or"
					+ " nacionalidade COLLATE Latin1_general_CI_AI like :pc or"
					+ " bairro COLLATE Latin1_general_CI_AI like :pc or"
					+ " cidade COLLATE Latin1_general_CI_AI like :pc or" + " rg like :pc or" + " cpf like :pc or"
					+ " estadoCivil COLLATE Latin1_general_CI_AI like :pc or"
					+ " idade like :pc or cargoPretendido COLLATE Latin1_general_CI_AI like :pc or"
					+ " demaisCargos COLLATE Latin1_general_CI_AI like :pc)");
			pesquisar = true;
		}

		if (candidato.getNome() != null && !candidato.getNome().trim().isEmpty()) {
			buffer.append(" and nome COLLATE Latin1_general_CI_AI like :nome");
			pesquisar = true;
		}

		if (cidadeSelecionada != null && !cidadeSelecionada.trim().isEmpty()) {
			buffer.append(" and cidade = :cidade");
			pesquisar = true;
		}

		if (candidato.getCargoPretendido() != null && !candidato.getCargoPretendido().trim().isEmpty()
				&& !candidato.getCargoPretendido().equals("Todos")) {
			buffer.append(" and cargoPretendido = :cargoPretendido");
			pesquisar = true;
		}

		if (candidato.getCargoPretendido().equals("Todos")) {
			pesquisar = true;
		}

		if (candidato.getBairro() != null && !candidato.getBairro().trim().isEmpty()) {
			buffer.append(" and bairro COLLATE Latin1_general_CI_AI like :bairro");
			pesquisar = true;
		}

		if (candidato.getZonaBairro() != null && !candidato.getZonaBairro().isEmpty()) {
			buffer.append(" and zonaBairro = :zonaBairro");
			pesquisar = true;
		}

		if (candidato.getEstadoCivil() != null && !candidato.getEstadoCivil().equals("")
				&& !candidato.getEstadoCivil().equals("I")) {
			buffer.append(" and estadoCivil = :estadoCivil");
			pesquisar = true;
		}

		if (candidato.getSexo() != null && !candidato.getSexo().equals("") && !candidato.getSexo().equals("I")) {
			buffer.append(" and sexo = :sexo");
			pesquisar = true;
		}

		if (candidato.isFumante()) {
			buffer.append(" and fumante = :fumante");
			pesquisar = true;
		}

		if (candidato.isEstudaAtualmente()) {
			buffer.append(" and estudaAtualmente = :estudaAtualmente");
			pesquisar = true;
		}

		if (candidato.getFormacao() != null && !candidato.getFormacao().trim().isEmpty()) {
			buffer.append(" and formacao = :formacao");
			pesquisar = true;
		}

		if (candidato.getConhecimentoInformatica() != null && !candidato.getConhecimentoInformatica().trim().isEmpty()
				&& !candidato.getConhecimentoInformatica().trim().equals("Nenhum")) {
			buffer.append(" and conhecimentoInformatica = :conhecimentoInformatica");
			pesquisar = true;
		}

		if (candidato.isPossuiDeficiencia()) {
			buffer.append(" and possuiDeficiencia = :possuiDeficiencia");
			pesquisar = true;
		}

		if (candidato.getTipoDeficiencia() != null && !candidato.getTipoDeficiencia().equals("")) {
			buffer.append(" and tipoDeficiencia = :tipoDeficiencia");
			pesquisar = true;
		}

		if (idadeInicio > 0 && idadeFim > 0) {
			buffer.append(" and dataNascimento between :idadeInicio and :idadeFim");
			pesquisar = true;
		}

		if (salarioFim > 0) {
			buffer.append(" and pretensaoSalarial <= :pretensaoSalarial");
			pesquisar = true;
		}

		if (candidato.getCategoriaCnh() != null && !candidato.getCategoriaCnh().equals("")) {
			buffer.append(" and categoriaCnh = :categoriaCnh");
			pesquisar = true;
		}

		if (candidato.getDataPreenchimentoFicha() != null) {
			buffer.append(" and CONVERT(Date,dataPreenchimentoFicha) = CONVERT(Date,:dataPreenchimentoFicha)");
			pesquisar = true;
		}

		if (candidato.getDataEntrevista() != null) {
			buffer.append(" and CONVERT(Date,dataEntrevista) = CONVERT(Date,:dataEntrevista)");
			pesquisar = true;
		}

		if (candidato.getDataEntrevista() != null) {
			buffer.append(" ORDER BY entrevistado, dataEntrevista desc, nome");
		}

		String hql = buffer.toString();

		Query query = this.manager.createNativeQuery(hql, intra_candidato.class);

		if (palavraChave != null && !palavraChave.trim().isEmpty()) {
			query.setParameter("pc", "%" + palavraChave + "%");
		}

		if (candidato.getNome() != null && !candidato.getNome().trim().isEmpty()) {
			query.setParameter("nome", "%" + candidato.getNome() + "%");
		}

		if (cidadeSelecionada != null && !cidadeSelecionada.trim().isEmpty()) {
			query.setParameter("cidade", cidadeSelecionada);
		}

		if (candidato.getCargoPretendido() != null && !candidato.getCargoPretendido().trim().isEmpty()
				&& !candidato.getCargoPretendido().equals("Todos")) {
			query.setParameter("cargoPretendido", candidato.getCargoPretendido());
		}

		if (candidato.getBairro() != null && !candidato.getBairro().trim().isEmpty()) {
			query.setParameter("bairro", "%" + candidato.getBairro() + "%");
		}

		if (candidato.getZonaBairro() != null && !candidato.getZonaBairro().isEmpty()) {
			query.setParameter("zonaBairro", candidato.getZonaBairro());
		}

		if (candidato.getEstadoCivil() != null && !candidato.getEstadoCivil().equals("")
				&& !candidato.getEstadoCivil().equals("I")) {
			query.setParameter("estadoCivil", candidato.getEstadoCivil());
		}

		if (candidato.getSexo() != null && !candidato.getSexo().equals("") && !candidato.getSexo().equals("I")) {
			query.setParameter("sexo", candidato.getSexo());
		}

		if (candidato.isFumante()) {
			query.setParameter("fumante", false);
		}

		if (candidato.isEstudaAtualmente()) {
			query.setParameter("estudaAtualmente", candidato.isEstudaAtualmente());
		}

		if (candidato.getFormacao() != null && !candidato.getFormacao().trim().isEmpty()) {
			query.setParameter("formacao", candidato.getFormacao());
		}

		if (candidato.getConhecimentoInformatica() != null && !candidato.getConhecimentoInformatica().trim().isEmpty()
				&& !candidato.getConhecimentoInformatica().trim().equals("Nenhum")) {
			query.setParameter("conhecimentoInformatica", candidato.getConhecimentoInformatica());
		}

		if (candidato.isPossuiDeficiencia()) {
			query.setParameter("possuiDeficiencia", candidato.isPossuiDeficiencia());
		}

		if (candidato.getTipoDeficiencia() != null && !candidato.getTipoDeficiencia().equals("")) {
			query.setParameter("tipoDeficiencia", candidato.getTipoDeficiencia());
		}

		if (idadeInicio > 0 && idadeFim > 0) {
			Date dInicio = new DateTime().minusYears(idadeFim).withDayOfYear(1).toDate();
			Date dFim = new DateTime().minusYears(idadeInicio).withDayOfYear(365).toDate();
			query.setParameter("idadeInicio", dInicio);
			query.setParameter("idadeFim", dFim);
		}

		if (salarioFim > 0) {
			query.setParameter("pretensaoSalarial", salarioFim);
		}

		if (candidato.getCategoriaCnh() != null && !candidato.getCategoriaCnh().equals("")) {
			query.setParameter("categoriaCnh", candidato.getCategoriaCnh());
		}

		if (candidato.getDataPreenchimentoFicha() != null) {
			query.setParameter("dataPreenchimentoFicha", candidato.getDataPreenchimentoFicha());
		}

		if (candidato.getDataEntrevista() != null) {
			query.setParameter("dataEntrevista", candidato.getDataEntrevista());
		}

		if (pesquisar) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato> pesquisarCandidatosJoin(String experiencia, String palavraChave,
			String cidadeSelecionada, int idadeInicio, int idadeFim, double salarioInicio, double salarioFim,
			intra_candidato candidato) {

		boolean pesquisar = false;

		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"use omacorp select distinct(c.codigo),c.bairro,c.bloqEntrevista,c.bloqLevantamentoCadastral,c.cargoPretendido,c.categoriaCnh,c.cep,"
						+ "c.cidade,c.cnh,c.complemento,c.conjugeTrabalha,c.cpf,c.cursos,c.dataEntrevista,c.dataExpedicaoCtps,c.dataExpedicaoRG,"
						+ "c.dataLevantamento,c.dataNascimento,c.dataValidadeCnh,c.dispensadoServicoMilitar,c.email,c.endereco,c.entrevistado,"
						+ "c.estadoCivil,c.estudaAtualmente,c.formacao,c.fumante,c.idade,c.levantamentoFeito,c.localNascimento,c.maeTrabalha,c.nacionalidade,c.nome,c.nomeBanco,"
						+ "c.nomeConjuge,c.nomeMae,c.nomePai,c.numeroAgencia,c.numeroConta,c.numeroCtps,c.numeroEndereco,c.numeroPis,c.paiTrabalha,c.parecerEntrevistador,c.possuiCertificadoMilitar,"
						+ "c.possuiConhecimentoInformatica,c.possuiContaBancaria,c.possuiDeficiencia,c.possuiFilhos,c.possuiPis,c.possuiTituloEleitor,c.preCadastro,c.pretendeResidirNoPredio,"
						+ "c.pretensaoSalarial,c.profissaoConjuge,c.profissaoMae,c.profissaoPai,c.qtdFilhos,c.rg,c.serieCtps,c.sexo,c.telCelular,c.telResidencial,c.tipoConta,c.tipoDeficiencia,c.zonaBairro,c.obsLevantamento,"
						+ "c.dataPreenchimentoFicha,c.telRecado,c.demaisCargos,c.periodoTrabalho,c.bloqContato,c.horarioChegada,c.contratado, c.conhecimentoInformatica, c.comoConheceuOma, c.comoConheceuOmaObs, c.dataCadastro from intra_candidato as c left join intra_candidato_historico_profissional h "
						+ "on c.codigo = h.candidato_codigo where 1 = 1");

		if (palavraChave != null && !palavraChave.trim().isEmpty()) {
			buffer.append(" and (c.nome COLLATE Latin1_general_CI_AI like :pc or"
					+ " c.nacionalidade COLLATE Latin1_general_CI_AI like :pc or"
					+ " c.bairro COLLATE Latin1_general_CI_AI like :pc or"
					+ " c.cidade COLLATE Latin1_general_CI_AI like :pc or" + " c.rg like :pc or" + " c.cpf like :pc or"
					+ " c.estadoCivil COLLATE Latin1_general_CI_AI like :pc or"
					+ " c.idade like :pc or c.cargoPretendido COLLATE Latin1_general_CI_AI like :pc or"
					+ " c.demaisCargos COLLATE Latin1_general_CI_AI like :pc)");
			pesquisar = true;
		}

		if (experiencia != null && !experiencia.trim().isEmpty()) {
			buffer.append(" and h.cargo COLLATE Latin1_general_CI_AI like :experiencia");
			pesquisar = true;
		}

		if (candidato.getNome() != null && !candidato.getNome().trim().isEmpty()) {
			buffer.append(" and c.nome COLLATE Latin1_general_CI_AI like :nome");
			pesquisar = true;
		}

		if (cidadeSelecionada != null && !cidadeSelecionada.trim().isEmpty()) {
			buffer.append(" and c.cidade = :cidade");
			pesquisar = true;
		}

		if (candidato.getCargoPretendido() != null && !candidato.getCargoPretendido().trim().isEmpty()
				&& !candidato.getCargoPretendido().equals("Todos")) {
			buffer.append(" and cargoPretendido = :cargoPretendido");
			pesquisar = true;
		}

		if (candidato.getCargoPretendido().equals("Todos")) {
			pesquisar = true;
		}

		if (candidato.getBairro() != null && !candidato.getBairro().trim().isEmpty()) {
			buffer.append(" and c.bairro COLLATE Latin1_general_CI_AI like :bairro");
			pesquisar = true;
		}

		if (candidato.getZonaBairro() != null && !candidato.getZonaBairro().isEmpty()) {
			buffer.append(" and c.zonaBairro = :zonaBairro");
			pesquisar = true;
		}

		if (candidato.getEstadoCivil() != null && !candidato.getEstadoCivil().equals("")
				&& !candidato.getEstadoCivil().equals("I")) {
			buffer.append(" and c.estadoCivil = :estadoCivil");
			pesquisar = true;
		}

		if (candidato.getSexo() != null && !candidato.getSexo().equals("") && !candidato.getSexo().equals("I")) {
			buffer.append(" and c.sexo = :sexo");
			pesquisar = true;
		}

		if (candidato.isFumante()) {
			buffer.append(" and c.fumante = :fumante");
			pesquisar = true;
		}

		if (candidato.isEstudaAtualmente()) {
			buffer.append(" and c.estudaAtualmente = :estudaAtualmente");
			pesquisar = true;
		}

		if (candidato.getFormacao() != null && !candidato.getFormacao().trim().isEmpty()) {
			buffer.append(" and c.formacao = :formacao");
			pesquisar = true;
		}

		if (candidato.getConhecimentoInformatica() != null && !candidato.getConhecimentoInformatica().trim().isEmpty()
				&& !candidato.getConhecimentoInformatica().trim().equals("Nenhum")) {
			buffer.append(" and conhecimentoInformatica = :conhecimentoInformatica");
			pesquisar = true;
		}

		if (candidato.isPossuiDeficiencia()) {
			buffer.append(" and c.possuiDeficiencia = :possuiDeficiencia");
			pesquisar = true;
		}

		if (candidato.getTipoDeficiencia() != null && !candidato.getTipoDeficiencia().equals("")) {
			buffer.append(" and c.tipoDeficiencia = :tipoDeficiencia");
			pesquisar = true;
		}

		if (idadeInicio > 0 && idadeFim > 0) {
			buffer.append(" and c.idade between :idadeInicio and :idadeFim");
			pesquisar = true;
		}

		if (salarioFim > 0) {
			buffer.append(" and c.pretensaoSalarial <= :pretensaoSalarial");
			pesquisar = true;
		}

		if (candidato.getCategoriaCnh() != null && !candidato.getCategoriaCnh().equals("")) {
			buffer.append(" and c.categoriaCnh = :categoriaCnh");
			pesquisar = true;
		}

		if (candidato.getDataPreenchimentoFicha() != null) {
			buffer.append(" and CONVERT(Date,c.dataPreenchimentoFicha) = CONVERT(Date,:dataPreenchimentoFicha)");
			pesquisar = true;
		}

		if (candidato.getDataEntrevista() != null) {
			buffer.append(" and CONVERT(Date,c.dataEntrevista) = CONVERT(Date,:dataEntrevista)");
			pesquisar = true;
		}

		String hql = buffer.toString();

		Query query = this.manager.createNativeQuery(hql, intra_candidato.class);

		if (palavraChave != null && !palavraChave.trim().isEmpty()) {
			query.setParameter("pc", "%" + palavraChave + "%");
		}

		if (experiencia != null && !experiencia.trim().isEmpty()) {
			query.setParameter("experiencia", "%" + experiencia + "%");
		}

		if (candidato.getNome() != null && !candidato.getNome().trim().isEmpty()) {
			query.setParameter("nome", "%" + candidato.getNome() + "%");
		}

		if (cidadeSelecionada != null && !cidadeSelecionada.trim().isEmpty()) {
			query.setParameter("cidade", cidadeSelecionada);
		}

		if (candidato.getCargoPretendido() != null && !candidato.getCargoPretendido().trim().isEmpty()
				&& !candidato.getCargoPretendido().equals("Todos")) {
			query.setParameter("cargoPretendido", candidato.getCargoPretendido());
		}

		if (candidato.getBairro() != null && !candidato.getBairro().trim().isEmpty()) {
			query.setParameter("bairro", "%" + candidato.getBairro() + "%");
		}

		if (candidato.getZonaBairro() != null && !candidato.getZonaBairro().isEmpty()) {
			query.setParameter("zonaBairro", candidato.getZonaBairro());
		}

		if (candidato.getEstadoCivil() != null && !candidato.getEstadoCivil().equals("")
				&& !candidato.getEstadoCivil().equals("I")) {
			query.setParameter("estadoCivil", candidato.getEstadoCivil());
		}

		if (candidato.getSexo() != null && !candidato.getSexo().equals("") && !candidato.getSexo().equals("I")) {
			query.setParameter("sexo", candidato.getSexo());
		}

		if (candidato.isFumante()) {
			query.setParameter("fumante", false);
		}

		if (candidato.isEstudaAtualmente()) {
			query.setParameter("estudaAtualmente", candidato.isEstudaAtualmente());
		}

		if (candidato.getFormacao() != null && !candidato.getFormacao().trim().isEmpty()) {
			query.setParameter("formacao", candidato.getFormacao());
		}

		if (candidato.getConhecimentoInformatica() != null && !candidato.getConhecimentoInformatica().trim().isEmpty()
				&& !candidato.getConhecimentoInformatica().trim().equals("Nenhum")) {
			query.setParameter("conhecimentoInformatica", candidato.getConhecimentoInformatica());
		}

		if (candidato.isPossuiDeficiencia())

		{
			query.setParameter("possuiDeficiencia", candidato.isPossuiDeficiencia());
		}

		if (candidato.getTipoDeficiencia() != null && !candidato.getTipoDeficiencia().equals("")) {
			query.setParameter("tipoDeficiencia", candidato.getTipoDeficiencia());
		}

		if (idadeInicio > 0 && idadeFim > 0) {
			query.setParameter("idadeInicio", idadeInicio);
			query.setParameter("idadeFim", idadeFim);
		}

		if (salarioFim > 0) {
			query.setParameter("pretensaoSalarial", salarioFim);
		}

		if (candidato.getCategoriaCnh() != null && !candidato.getCategoriaCnh().equals("")) {
			query.setParameter("categoriaCnh", candidato.getCategoriaCnh());
		}

		if (candidato.getDataPreenchimentoFicha() != null) {
			query.setParameter("dataPreenchimentoFicha", candidato.getDataPreenchimentoFicha());
		}

		if (candidato.getDataEntrevista() != null) {
			query.setParameter("dataEntrevista", candidato.getDataEntrevista());
		}

		if (pesquisar) {
			return query.getResultList();
		} else {
			return null;
		}
	}

	public intra_candidato pesquisaCandidatoPorCodigo(int codigoCandidato) {
		return this.manager.find(intra_candidato.class, codigoCandidato);
	}

	public List<intra_candidato_dependentes> pesquisaDependentes(String codigoCandidato) {
		// TODO Auto-generated method stub
		return null;
	}

	public void salvarFotoCandidato(intra_candidato_foto candidatoFoto) {
		if (candidatoFoto.getCodigo() == 0) {
			this.manager.persist(candidatoFoto);
		} else {
			this.manager.merge(candidatoFoto);
		}
	}

	@SuppressWarnings("unchecked")
	public byte[] pesquisaFoto(Integer codigoCandidato) {
		byte[] foto = null;
		if (this.manager.isOpen()) {
			Query query = this.manager.createQuery("from intra_candidato_foto where codigoCandidato = :p1");
			query.setParameter("p1", codigoCandidato);
			List<intra_candidato_foto> l = query.getResultList();
			for (intra_candidato_foto aux : l) {
				if (aux != null) {
					foto = aux.getFoto();
				}
			}
		}
		return foto;
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato_foto> pesquisaCandidatoFoto(intra_candidato candidatoSelecionado) {
		List<intra_candidato_foto> lstFotos = new ArrayList<>();
		intra_candidato_foto foto = null;
		Query query = this.manager.createQuery(
				"select codigo,codigoCandidato,foto from intra_candidato_foto where codigoCandidato = :p1");
		query.setParameter("p1", candidatoSelecionado.getCodigo());
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			foto = new intra_candidato_foto();
			if (aux[0] != null) {
				foto.setCodigo(Integer.parseInt(aux[0].toString()));
			}
			if (aux[1] != null) {
				foto.setCodigoCandidato(Integer.parseInt(aux[1].toString()));
			}
			if (aux[2] != null) {
				foto.setFoto(aux[2].toString().getBytes());
			}
			lstFotos.add(foto);
		}
		return lstFotos;
	}

	public void excluirFotoCandidato(int codigoCandidato) {
		Query query = this.manager.createQuery("delete intra_candidato_foto where codigoCandidato = :p1");
		query.setParameter("p1", codigoCandidato).executeUpdate();
	}

	public void salvarCadastro(intra_cadastro_recepcao cadastro, intra_cadastro_recepcao_foto cadastroFoto) {
		if (cadastro.getCodigo() == 0) {
			this.manager.persist(cadastro);
			cadastroFoto.setCodigo(cadastro.getCodigo());
			this.manager.persist(cadastroFoto);
			intra_log_geral log = new intra_log_geral(cadastro.getCodigo(), retornaIdUsuario(),
					"intra_cadastro_recepcao", true, false, false, cadastro.toString(), new Date(), 0, null, null);
			this.logGeral(log);
			intra_log_geral log2 = new intra_log_geral(cadastroFoto.getCodigo(), retornaIdUsuario(),
					"intra_cadastro_recepcao_foto", true, false, false, cadastroFoto.toString(), new Date(), 0, null,
					null);
			this.logGeral(log2);
		} else {
			this.manager.merge(cadastro);
			if (cadastroFoto.getCodigo() == 0) {
				cadastroFoto.setCodigo(cadastro.getCodigo());
			}
			this.manager.merge(cadastroFoto);
			intra_log_geral log = new intra_log_geral(cadastro.getCodigo(), retornaIdUsuario(),
					"intra_cadastro_recepcao", false, true, false, cadastro.toString(), new Date(), 0, null, null);
			this.logGeral(log);
			intra_log_geral log2 = new intra_log_geral(cadastroFoto.getCodigo(), retornaIdUsuario(),
					"intra_cadastro_recepcao_foto", false, true, false, cadastroFoto.toString(), new Date(), 0, null,
					null);
			this.logGeral(log2);
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_cadastro_recepcao> pesquisaCadastro(String rg) {
		Query query = this.manager.createQuery("from intra_cadastro_recepcao where rg = :p1");
		query.setParameter("p1", rg);
		return query.getResultList();
	}

	public intra_cadastro_recepcao_foto pesquisaFotoCadastro(intra_cadastro_recepcao cadastro) {
		TypedQuery<intra_cadastro_recepcao_foto> query = this.manager.createQuery(
				"from intra_cadastro_recepcao_foto where codigo = :p1", intra_cadastro_recepcao_foto.class);
		query.setParameter("p1", cadastro.getCodigo());
		for (intra_cadastro_recepcao_foto aux : query.getResultList()) {
			if (aux != null) {
				return aux;
			} else {
				return new intra_cadastro_recepcao_foto();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato> pesquisaCandidatoPorRg(String rg) {
		Query query = this.manager.createQuery("from intra_candidato where rg = :p1");
		query.setParameter("p1", rg);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_candidato> getListaPreCadastro() {
		Query query = this.manager.createQuery("from intra_candidato where preCadastro = true");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public intra_candidato recebeCandidatoPreCadastro(int codigo) {
		Query query = this.manager.createQuery("from intra_candidato where codigo = :p1");
		query.setParameter("p1", codigo);
		List<intra_candidato> retorno = query.getResultList();
		return retorno.get(0);
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

	public void excluirDependente(intra_candidato_dependentes dependente) {
		this.manager.merge(dependente);
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios() {
		Query query = this.manager
				.createQuery("from intra_condominios where codigo between 111 and 2000 order by codigo");
		return query.getResultList();
	}

	public void excluirCandidato(intra_candidato candidatoSelecionado) {
		candidatoSelecionado = this.manager.merge(candidatoSelecionado);
		intra_log_geral log = new intra_log_geral(candidatoSelecionado.getCodigo(), retornaIdUsuario(),
				"intra_candidato", false, false, true, candidatoSelecionado.toString(), new Date(), 0, null, null);
		this.logGeral(log);
		this.manager.remove(candidatoSelecionado);
	}

}
