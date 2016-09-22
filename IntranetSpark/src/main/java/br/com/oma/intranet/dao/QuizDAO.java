package br.com.oma.intranet.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_quiz_controle_condominios;
import br.com.oma.intranet.entidades.intra_quiz_controle_fase_geral;
import br.com.oma.intranet.entidades.intra_quiz_controle_respostas;
import br.com.oma.intranet.entidades.intra_quiz_emails;
import br.com.oma.intranet.entidades.intra_quiz_param;
import br.com.oma.intranet.entidades.intra_quiz_perguntas;
import br.com.oma.intranet.entidades.intra_quiz_respostas;
import br.com.oma.intranet.util.JPAUtil;

public class QuizDAO {

	private EntityManager manager;

	public QuizDAO() {
		this.manager = JPAUtil.getManager();
	}

	public intra_quiz_perguntas getPerguntas() {
		return this.manager.find(intra_quiz_perguntas.class, 1);
	}

	public void salvarPerguntas(intra_quiz_perguntas perguntas) {
		this.manager.merge(perguntas);
	}

	@SuppressWarnings("unchecked")
	public List<intra_quiz_respostas> pesquisaRespostasPergunta(int faseGeral) {
		Query query = this.manager.createQuery("from intra_quiz_respostas where faseGeral = :faseGeral");
		query.setParameter("faseGeral", faseGeral);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_quiz_respostas> pesquisaRespostasPerguntaGerente(int faseGeral, int codigoGerente)
			throws ParseException {
		List<intra_quiz_respostas> listaRespostas = new ArrayList<>();
		intra_quiz_respostas resposta = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
		Query query = this.manager.createNativeQuery(
				"use omacorp select r.codigo,r.condominio,r.dataRespondido,r.emailSindico,r.faseGeral,r.nomeSindico"
						+ ",r.pergunta1,r.pergunta2,r.pergunta3,r.pergunta4,r.pergunta5,r.pergunta6,r.pergunta7,r.pergunta8"
						+ ",r.pergunta9,r.pergunta10,r.perguntaObs,r.resposta1,r.resposta2,r.resposta3,r.resposta4,r.resposta5"
						+ ",r.resposta6,r.resposta7,r.resposta8,r.resposta9,r.resposta10,r.respostaObs "
						+ "from intra_quiz_respostas r inner join intra_condominios c on r.condominio = c.codigo where c.codigoGerente = :codigoGerente and r.faseGeral = :faseGeral");
		query.setParameter("faseGeral", faseGeral);
		query.setParameter("codigoGerente", codigoGerente);
		List<Object[]> l = query.getResultList();

		for (Object[] aux : l) {
			resposta = new intra_quiz_respostas();
			if (aux[0] != null) {
				resposta.setCodigo((int) aux[0]);
			}

			if (aux[1] != null) {
				resposta.setCondominio((int) aux[1]);
			}

			if (aux[2] != null) {
				resposta.setDataRespondido(sf.parse(aux[2].toString()));
			}

			if (aux[3] != null) {
				resposta.setEmailSindico(aux[3].toString());
			}

			if (aux[4] != null) {
				resposta.setFaseGeral((int) aux[4]);
			}
			if (aux[5] != null) {
				resposta.setNomeSindico(aux[5].toString());
			}
			if (aux[6] != null) {
				resposta.setPergunta1(aux[6].toString());
			}
			if (aux[7] != null) {
				resposta.setPergunta2(aux[7].toString());
			}
			if (aux[8] != null) {
				resposta.setPergunta3(aux[8].toString());
			}
			if (aux[9] != null) {
				resposta.setPergunta4(aux[9].toString());
			}
			if (aux[10] != null) {
				resposta.setPergunta5(aux[10].toString());
			}
			if (aux[11] != null) {
				resposta.setPergunta6(aux[11].toString());
			}
			if (aux[12] != null) {
				resposta.setPergunta7(aux[12].toString());
			}
			if (aux[13] != null) {
				resposta.setPergunta8(aux[13].toString());
			}
			if (aux[14] != null) {
				resposta.setPergunta9(aux[14].toString());
			}
			if (aux[15] != null) {
				resposta.setPergunta10(aux[15].toString());
			}
			if (aux[16] != null) {
				resposta.setPerguntaObs(aux[16].toString());
			}
			if (aux[17] != null) {
				resposta.setResposta1(aux[17].toString());
			}
			if (aux[18] != null) {
				resposta.setResposta2(aux[18].toString());
			}
			if (aux[19] != null) {
				resposta.setResposta3(aux[19].toString());
			}
			if (aux[20] != null) {
				resposta.setResposta4(aux[20].toString());
			}
			if (aux[21] != null) {
				resposta.setResposta5(aux[21].toString());
			}
			if (aux[22] != null) {
				resposta.setResposta6(aux[22].toString());
			}
			if (aux[23] != null) {
				resposta.setResposta7(aux[23].toString());
			}
			if (aux[24] != null) {
				resposta.setResposta8(aux[24].toString());
			}
			if (aux[25] != null) {
				resposta.setResposta9(aux[25].toString());
			}
			if (aux[26] != null) {
				resposta.setResposta10(aux[26].toString());
			}
			if (aux[27] != null) {
				resposta.setRespostaObs(aux[27].toString());
			}
			listaRespostas.add(resposta);
		}

		return listaRespostas;
	}

	@SuppressWarnings("unchecked")
	public List<intra_quiz_controle_fase_geral> getListaFases() {
		return this.manager.createQuery("from intra_quiz_controle_fase_geral").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios() {
		return this.manager.createQuery("from intra_condominios order by codigo").getResultList();
	}

	@SuppressWarnings("unchecked")
	public String verificaStatusQuiz(int codigo) {
		Query query = this.manager
				.createQuery("from intra_quiz_controle_fase_geral where codigo = :codigo order by codigo");
		query.setParameter("codigo", codigo);
		List<intra_quiz_controle_fase_geral> l = query.getResultList();
		intra_quiz_controle_fase_geral aux = l.get(l.size() - 1);
		String retorno = "";
		retorno = aux.getFase() + "/" + aux.getQtdFases();
		if (aux.getFase() == aux.getQtdFases()) {
			retorno = retorno + " - Finalizado";
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public intra_quiz_controle_respostas pesquisaSindico(int codigoCondominio) {
		intra_quiz_controle_respostas sindico = null;
		Query query = this.manager.createNativeQuery("select i.nome, t.tel_email from sigadm.dbo.cndmemb m "
				+ "inner join sigadm.dbo.cndcargo g on m.cargo = g.codigo "
				+ "inner join sigadm.dbo.cndcondo c on m.condominio = c.codigo "
				+ "inner join sigadm.dbo.ilclient i on m.cliente = i.codigo "
				+ "inner join sigadm.dbo.iltelcli t on i.codigo = t.cliente "
				+ "where c.codigo = :codigoCondominio and c.data_baixa is null and cargo = 1 and t.tipo = 6 "
				+ "order by m.condominio, m.cargo, m.cli_bloco, m.cli_unidade");
		query.setParameter("codigoCondominio", codigoCondominio);
		List<Object[]> l = query.getResultList();
		for (Object[] aux : l) {
			sindico = new intra_quiz_controle_respostas();
			if (aux[0] != null) {
				sindico.setNomeSindico(aux[0].toString());
			}

			if (aux[1] != null) {
				sindico.setEmailSindico(aux[1].toString());
			}
		}
		return sindico;
	}

	public void salvaControleCondominios(intra_quiz_controle_condominios cnd) {
		this.manager.merge(cnd);
	}

	public void salvaControleRespostas(intra_quiz_controle_respostas sindico) {
		this.manager.merge(sindico);
	}

	public void salvarParametros(intra_quiz_param parametros, List<intra_quiz_emails> emails) {
		this.manager.merge(parametros);
		for (intra_quiz_emails email : emails) {
			this.manager.merge(email);
		}
	}

	@SuppressWarnings("unchecked")
	public intra_quiz_param pesquisaParametros() {
		Query query = this.manager.createQuery("FROM intra_quiz_param");
		List<intra_quiz_param> l = query.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return new intra_quiz_param();
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_quiz_emails> getEmails() {
		return this.manager.createQuery("from intra_quiz_emails order by email").getResultList();
	}

	public void removerEmail(intra_quiz_emails email) {
		email = this.manager.merge(email);
		this.manager.remove(email);
	}

	@SuppressWarnings("unchecked")
	public List<intra_quiz_respostas> pesquisaSugestoes(int faseGeral) {
		Query query = this.manager.createQuery("from intra_quiz_respostas where faseGeral = :faseGeral");
		query.setParameter("faseGeral", faseGeral);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_quiz_respostas> pesquisaSugestoesPorGerente(int faseGeral, int codigoGerente) {
		Query query = this.manager.createQuery("from intra_quiz_respostas where faseGeral = :faseGeral");
		query.setParameter("faseGeral", faseGeral);
		return query.getResultList();
	}

}
