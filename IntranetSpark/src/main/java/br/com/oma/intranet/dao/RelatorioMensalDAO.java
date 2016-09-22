package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.entidades.intra_relatorio_mensal;
import br.com.oma.intranet.entidades.intra_taxa_bancaria;
import br.com.oma.intranet.util.JPAUtil;

@Stateless
public class RelatorioMensalDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2675892920021847752L;
	private EntityManager manager;
	private Query query;

	public RelatorioMensalDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios() {
		this.query = this.manager.createQuery("from intra_condominios ");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios(int codigoGerente) {
		Query query = this.manager
				.createQuery("from intra_condominios where codigoGerente = :p1 and codigo <> 1 order by codigo");
		query.setParameter("p1", codigoGerente);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_grupo_gerente> getListaGerentes() {
		Query query = this.manager.createQuery("from intra_grupo_gerente order by nome");
		return query.getResultList();
	}

	public intra_grupo_gerente pesquisaGerentePorCodigo(int codgeren) {
		return this.manager.find(intra_grupo_gerente.class, codgeren);
	}

	@SuppressWarnings("unchecked")
	public List<intra_relatorio_mensal> pesquisaRelatorioMensal(List<intra_condominios> condominio, Date dataInicio,
			Date dataFim) throws SQLException {
		List<intra_relatorio_mensal> listaRelatorio = new ArrayList<intra_relatorio_mensal>();
		for (intra_condominios c : condominio) {
			this.query = this.manager.createNativeQuery(
					"select c.flag_baixa,c.data_baixa,c.codigo, c.nome as nomeCondominio, g.nome as nomeGerente, c.qtde_aptos, c.qtde_cjtos, c.qtde_lojas, "
							+ "t.valor, c.dt_inicio_adm, sum(r.valor_recibo) valor_recibo ,c.logradouro, c.endereco, c.nro_end, "
							+ "c.bairro, c.cep, c.estado, s.nome as nomeSindico, s.dia_ani, s.mes_ani, s.ano_ani, s.profissao, b.inicio_mandato, c.conta_vinculada "
							+ "from sigadm.dbo.cndcondo c full join sigadm.dbo.cndtaxa t on c.codigo = t.condominio full join sigadm.dbo.cndgeren g on c.gerente = g.codigo "
							+ "full join sigadm.dbo.cndmemb m on c.codigo = m.condominio full join sigadm.dbo.ilclient s on m.cliente = s.codigo "
							+ "full join sigadm.dbo.cndbloco b on b.condominio = c.codigo full join sigadm.dbo.cndrecib r on c.codigo = r.condominio "
							+ "where c.codigo = :p1 and c.codigo <> 3010 and (m.cargo = 1 or m.cargo is null or m.cargo = 12 ) "
							+ "and r.vencto between :p2 and :p3 and flag_situacao <> 9 and b.bloco = '0' "
							+ "group by c.flag_baixa,c.data_baixa,c.codigo, c.nome,g.nome, c.qtde_aptos, c.qtde_cjtos, c.qtde_lojas, "
							+ "t.valor, c.dt_inicio_adm, c.logradouro, c.endereco, c.nro_end, c.bairro, c.cep, c.estado, s.nome, "
							+ "s.dia_ani, s.mes_ani, s.ano_ani, s.profissao, b.inicio_mandato, c.conta_vinculada order by c.codigo");
			this.query.setParameter("p1", c.getCodigo());
			this.query.setParameter("p2", new java.sql.Date(dataInicio.getTime()));
			this.query.setParameter("p3", new java.sql.Date(dataFim.getTime()));
			List<Object[]> lista = this.query.getResultList();
			if (!lista.isEmpty()) {
				for (Object[] obj : lista) {
					intra_relatorio_mensal rm = new intra_relatorio_mensal();
					if (obj[0] != null) {
						rm.setBaixado(obj[0].toString());
					}
					if (obj[1] != null) {
						rm.setDataBaixa((Date) obj[1]);
					}
					if (obj[2] != null) {
						rm.setCodigo(Integer.valueOf(obj[2].toString()));
					}
					if (obj[3] != null) {
						rm.setNomeCondominio(obj[3].toString());
					}
					if (obj[4] != null) {
						rm.setNomeGerente(obj[4].toString());
					}
					if (obj[5] != null) {
						rm.setQtde_aptos(Integer.valueOf(obj[5].toString()));
					}
					if (obj[6] != null) {
						rm.setQtde_cjtos(Integer.valueOf(obj[6].toString()));
					}
					if (obj[7] != null) {
						rm.setQtde_lojas(Integer.valueOf(obj[7].toString()));
					}
					if (obj[8] != null) {
						rm.setTaxaAdm(Double.valueOf(obj[8].toString()));
					}
					if (obj[9] != null) {
						rm.setInicioAdm((Date) obj[9]);
					}
					if (obj[10] != null) {
						rm.setTotalCota(Double.valueOf(obj[10].toString()));
					}
					if (obj[11] != null) {
						rm.setLogradouro(obj[11].toString());
					}
					if (obj[12] != null) {
						rm.setEndereco(obj[12].toString());
					}
					if (obj[13] != null) {
						rm.setNro_end(obj[13].toString());
					}
					if (obj[14] != null) {
						rm.setBairro(obj[14].toString());
					}
					if (obj[15] != null) {
						rm.setCep(Double.valueOf(obj[15].toString()));
					}
					if (obj[16] != null) {
						rm.setEstado(obj[16].toString());
					}
					if (obj[17] != null) {
						rm.setNomeSindico(obj[17].toString());
					}
					if (obj[18] != null) {
						rm.setDiaAniSind(Integer.valueOf(obj[18].toString()));
					}
					if (obj[19] != null) {
						rm.setMesAniSind(Integer.valueOf(obj[19].toString()));
					}
					if (obj[20] != null) {
						rm.setAnoAniSind(Integer.valueOf(obj[20].toString()));
					}
					if (obj[21] != null) {
						rm.setProfissaoSind(obj[21].toString());
					}
					if (obj[22] != null) {
						rm.setInicioMandSind((Date) obj[22]);
					}
					if (obj[23] != null) {
						rm.setContaVinculada((char) obj[23]);
					}
					listaRelatorio.add(rm);
				}
			}
		}
		return listaRelatorio;
	}

	@SuppressWarnings("unchecked")
	public List<intra_taxa_bancaria> pesquisarTaxaBancaria(Date dataInicio, Date dataFim) throws SQLException {
		List<intra_taxa_bancaria> listaTaxas = new ArrayList<intra_taxa_bancaria>();
		this.query = this.manager.createNativeQuery(
				"select condominio, bloco, data, conta, sinal, valor, historico from sigadm.dbo.cndespes "
						+ "where data between :p1 and :p2 and conta = 26864 order by condominio");
		this.query.setParameter("p1", new java.sql.Date(dataInicio.getTime()));
		this.query.setParameter("p2", new java.sql.Date(dataFim.getTime()));
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_taxa_bancaria tb = new intra_taxa_bancaria();
				if (obj[0] != null) {
					tb.setCondominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					tb.setBloco(obj[1].toString());
				}
				if (obj[2] != null) {
					tb.setData((Date) obj[2]);
				}
				if (obj[3] != null) {
					tb.setConta(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					tb.setSinal(obj[4].toString());
				}
				if (obj[5] != null) {
					tb.setValor(Double.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					tb.setHistorico(obj[6].toString());
				}
				listaTaxas.add(tb);
			}
		}
		return listaTaxas;
	}

}
