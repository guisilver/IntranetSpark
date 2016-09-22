package br.com.oma.pc.model.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.pc.modelo.entidades.Capa;
import br.com.oma.pc.modelo.entidades.Condominio;
import br.com.oma.pc.modelo.entidades.Conta;
import br.com.oma.pc.modelo.entidades.Lancamento;

public class GraficosDAO {

	private Connection con;
	private EntityManager manager;

	public GraficosDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.con = Conexao.getConexao();
		this.manager = JPAUtil.getManager();
	}

	public Condominio pesquisaCnd(Condominio condominio) throws SQLException {
		Condominio retorno = new Condominio();
		retorno.setCodigo(condominio.getCodigo());
		String sql = "select codigo_plano,nome from cndcondo where codigo = ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setShort(1, condominio.getCodigo());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			retorno.setCodigoPlano(rs.getInt("codigo_plano"));
			retorno.setNome(rs.getString("nome"));
		}
		return retorno;
	}

	public List<Capa> pesquisaCapas(Condominio condominio, Date dataInicio, Date dataFim) throws SQLException {
		int mes = new DateTime(dataInicio).getMonthOfYear();
		int ano = new DateTime(dataInicio).getYear();
		String sql = "select data_inicial,data_final from cndgrctr where condominio = ? and mes = ? and ano = ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setShort(1, condominio.getCodigo());
		stmt.setInt(2, mes);
		stmt.setInt(3, ano);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			dataInicio = rs.getDate("data_inicial");
		}
		mes = new DateTime(dataFim).getMonthOfYear();
		ano = new DateTime(dataFim).getYear();
		sql = "select data_inicial,data_final from cndgrctr where condominio = ? and mes = ? and ano = ?";
		stmt = this.con.prepareStatement(sql);
		stmt.setShort(1, condominio.getCodigo());
		stmt.setInt(2, mes);
		stmt.setInt(3, ano);
		rs = stmt.executeQuery();
		while (rs.next()) {
			dataFim = rs.getDate("data_final");
		}
		sql = "select distinct(c.conta_grau1),c.nome from cndespes d "
				+ "inner join cndplano p on d.conta = p.cod_reduzido "
				+ "inner join cndplano c on p.conta_grau1 = c.conta "
				+ "where d.condominio = ? and d.data between ? and ? and d.sinal = 'D' and p.tipo = 'D' and p.codigo_plano = ? and c.codigo_plano = ?";
		stmt = this.con.prepareStatement(sql);
		stmt.setShort(1, condominio.getCodigo());
		stmt.setDate(2, new java.sql.Date(dataInicio.getTime()));
		stmt.setDate(3, new java.sql.Date(dataFim.getTime()));
		stmt.setInt(4, condominio.getCodigoPlano());
		stmt.setInt(5, condominio.getCodigoPlano());
		rs = stmt.executeQuery();
		Capa capa = null;
		List<Capa> listaCapas = new ArrayList<Capa>();
		while (rs.next()) {
			capa = new Capa();
			capa.setCodigo(rs.getInt("conta_grau1"));
			capa.setNome(rs.getString("nome"));
			capa.getContas().addAll(pesquisaContas(condominio, capa.getCodigo(), dataInicio, dataFim));
			listaCapas.add(capa);
		}
		stmt.close();
		rs.close();
		return listaCapas;
	}

	public List<Conta> pesquisaContas(Condominio condominio, int capa, Date dataInicio, Date dataFim)
			throws SQLException {
		capa = capa / 1000;
		List<Conta> lista = new ArrayList<>();
		String sql = "select distinct p.conta from cndespes d inner join cndplano p on d.conta = p.cod_reduzido "
				+ "	where condominio = ? and d.sinal = 'D' and p.tipo = 'D' and p.conta/1000 = ? and d.data between ? and ? order by p.conta";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setInt(1, condominio.getCodigo());
		stmt.setInt(2, capa);
		stmt.setDate(3, new java.sql.Date(dataInicio.getTime()));
		stmt.setDate(4, new java.sql.Date(dataFim.getTime()));
		ResultSet rs = stmt.executeQuery();
		Conta conta = null;
		while (rs.next()) {
			conta = new Conta();
			conta.setCodigo(rs.getInt("conta"));
			conta.setNome(pesquisaNomeConta(condominio, conta.getCodigo()));
			conta.getLancamentos().addAll(pesquisaPagamentos(condominio, conta.getCodigo(), dataInicio, dataFim));
			lista.add(conta);
		}
		return lista;
	}

	public String pesquisaNomeConta(Condominio condominio, int conta) throws SQLException {
		String retorno = "";
		String sql = "select nome from cndplano where sub_conta = 0 and codigo_plano = ? and conta = ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setInt(1, condominio.getCodigoPlano());
		stmt.setInt(2, conta);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			retorno = rs.getString("nome");
		}
		return retorno;
	}

	public List<Lancamento> pesquisaPagamentos(Condominio condominio, int conta, Date dataInicio, Date dataFim)
			throws SQLException {
		Lancamento pagamento = null;
		List<Lancamento> listaPagamentos = new ArrayList<>();
		String sql = "select d.condominio, MONTH(d.data) as mes, YEAR(d.data) as ano, d.conta, d.valor as valor,d.data,d.historico from cndespes d "
				+ "inner join cndplano p on d.conta = p.cod_reduzido "
				+ "where d.condominio = ? and d.data between ? and ? and d.sinal = 'D' and p.conta = ? "
				+ "order by p.conta ";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setInt(1, condominio.getCodigo());
		stmt.setDate(2, new java.sql.Date(dataInicio.getTime()));
		stmt.setDate(3, new java.sql.Date(dataFim.getTime()));
		stmt.setInt(4, conta);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			pagamento = new Lancamento();
			pagamento.setCondominio(rs.getShort("condominio"));
			pagamento.setMes(rs.getInt("mes"));
			pagamento.setAno(rs.getInt("ano"));
			pagamento.setConta(rs.getInt("conta"));
			pagamento.setValor(rs.getDouble("valor"));
			pagamento.setData(rs.getDate("data"));
			pagamento.setHistorico(rs.getString("historico"));
			listaPagamentos.add(pagamento);
		}
		return listaPagamentos;
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios(int codigoGerente) {
		Query query = this.manager
				.createQuery("from intra_condominios where codigoGerente = :codigoGerente order by codigo,nome");
		query.setParameter("codigoGerente", codigoGerente);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaTodosCondominios() {
		Query query = this.manager.createQuery("from intra_condominios");
		return query.getResultList();
	}

}
