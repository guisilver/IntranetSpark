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

import org.joda.time.DateTime;

import br.com.oma.intranet.filters.Conexao;
import br.com.oma.pc.modelo.entidades.Capa;
import br.com.oma.pc.modelo.entidades.Condominio;
import br.com.oma.pc.modelo.entidades.Conta;
import br.com.oma.pc.modelo.entidades.Documento;
import br.com.oma.pc.modelo.entidades.Lancamento;

public class PlanoContasDAO {

	private Connection con;

	public PlanoContasDAO() throws FileNotFoundException,
			ClassNotFoundException, IOException, SQLException {
		this.con = Conexao.getConexaoSiga();
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

	public List<Capa> pesquisaCapas(Condominio condominio, Date dataInicio,
			Date dataFim) throws SQLException {
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
			capa.getContas().addAll(
					pesquisaContas(condominio, capa.getCodigo(), dataInicio,
							dataFim));
			listaCapas.add(capa);
		}
		stmt.close();
		rs.close();
		return listaCapas;
	}

	public List<Conta> pesquisaContas(Condominio condominio, int capa,
			Date dataInicio, Date dataFim) throws SQLException {
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
			conta.getLancamentos().addAll(
					pesqLanctos(condominio, conta, dataInicio, dataFim));
			lista.add(conta);
		}
		return lista;
	}

	public String pesquisaNomeConta(Condominio condominio, int conta)
			throws SQLException {
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

	public List<Lancamento> pesqLanctos(Condominio condominio, Conta conta,
			Date dataInicio, Date dataFim) throws SQLException {
		List<Lancamento> listalancamentos = new ArrayList<>();
		if (condominio.isAnalit_sitet()) {
			listalancamentos.addAll(pesqLanctoSinteticos(condominio, conta,
					dataInicio, dataFim));
			listalancamentos.addAll(pesqLanctoAnaliticos(condominio,
					conta.getCodigo(), dataInicio, dataFim));
		} else {
			listalancamentos.addAll(pesqLanctoTodos(condominio,
					conta.getCodigo(), dataInicio, dataFim));
		}
		return listalancamentos;
	}

	public List<Lancamento> pesqLanctoSinteticos(Condominio condominio,
			Conta conta, Date dataInicio, Date dataFim) throws SQLException {
		Lancamento lancamento = null;
		List<Lancamento> listalancamentos = new ArrayList<>();
		String sql = "select p.conta, SUM(d.valor) as valor,p.nome,MONTH(d.data) as mes,YEAR(d.data) as ano,p.sub_conta,p.cod_reduzido from cndespes d "
				+ "inner join cndplano p on d.conta = p.cod_reduzido "
				+ "where d.condominio = ? and d.data between ? and ? and d.sinal = 'D' and p.analit_sintet = 'S' and p.conta = ? "
				+ "group by p.conta,p.nome,MONTH(d.data),YEAR(d.data),p.sub_conta,p.cod_reduzido order by p.sub_conta,p.conta,p.nome";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setInt(1, condominio.getCodigo());
		stmt.setDate(2, new java.sql.Date(dataInicio.getTime()));
		stmt.setDate(3, new java.sql.Date(dataFim.getTime()));
		stmt.setInt(4, conta.getCodigo());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			lancamento = new Lancamento();
			lancamento.setConta(rs.getInt("cod_reduzido"));
			lancamento.setValor(rs.getDouble("valor"));
			lancamento.setHistorico(rs.getString("nome"));
			lancamento.setMes(rs.getInt("mes"));
			lancamento.setAno(rs.getInt("ano"));
			lancamento.setData(new DateTime().withDayOfMonth(1)
					.withMonthOfYear(lancamento.getMes())
					.withYear(lancamento.getAno()).toDate());
			lancamento.setPossuiDocs(this.verificaDoctoExiste(lancamento
					.getNumero()));
			lancamento.setSintetico(true);
			listalancamentos.add(lancamento);
		}
		return listalancamentos;
	}

	public List<Lancamento> pesqLanctoAnaliticos(Condominio condominio,
			int conta, Date dataInicio, Date dataFim) throws SQLException {
		Lancamento lancamento = null;
		List<Lancamento> listalancamentos = new ArrayList<>();
		String sql = "select d.condominio, MONTH(d.data) as mes, YEAR(d.data) as ano, d.conta, d.valor as valor,d.data,d.historico,d.numero from cndespes d "
				+ "inner join cndplano p on d.conta = p.cod_reduzido "
				+ "where d.condominio = ? and d.data between ? and ? and d.sinal = 'D' and p.analit_sintet = 'A' and p.conta = ? "
				+ "order by p.conta ";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setInt(1, condominio.getCodigo());
		stmt.setDate(2, new java.sql.Date(dataInicio.getTime()));
		stmt.setDate(3, new java.sql.Date(dataFim.getTime()));
		stmt.setInt(4, conta);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			lancamento = new Lancamento();
			lancamento.setCondominio(rs.getShort("condominio"));
			lancamento.setMes(rs.getInt("mes"));
			lancamento.setAno(rs.getInt("ano"));
			lancamento.setConta(rs.getInt("conta"));
			lancamento.setValor(rs.getDouble("valor"));
			lancamento.setData(rs.getDate("data"));
			lancamento.setHistorico(rs.getString("historico"));
			lancamento.setNumero(rs.getInt("numero"));
			lancamento.setPossuiDocs(this.verificaDoctoExiste(lancamento
					.getNumero()));
			listalancamentos.add(lancamento);
		}
		return listalancamentos;
	}

	public List<Lancamento> pesqLanctoTodos(Condominio condominio, int conta,
			Date dataInicio, Date dataFim) throws SQLException {
		Lancamento lancamento = null;
		List<Lancamento> listalancamentos = new ArrayList<>();
		String sql = "select d.condominio, MONTH(d.data) as mes, YEAR(d.data) as ano, d.conta, d.valor as valor,d.data,d.historico,d.numero from cndespes d "
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
			lancamento = new Lancamento();
			lancamento.setCondominio(rs.getShort("condominio"));
			lancamento.setMes(rs.getInt("mes"));
			lancamento.setAno(rs.getInt("ano"));
			lancamento.setConta(rs.getInt("conta"));
			lancamento.setValor(rs.getDouble("valor"));
			lancamento.setData(rs.getDate("data"));
			lancamento.setHistorico(rs.getString("historico"));
			lancamento.setNumero(rs.getInt("numero"));
			lancamento.setPossuiDocs(this.verificaDoctoExiste(lancamento
					.getNumero()));
			listalancamentos.add(lancamento);
		}
		return listalancamentos;
	}

	public boolean verificaDoctoExiste(int numeroLancto) throws SQLException {
		String sql = "select id_lcto from sigadm.dbo.glbCatalogo_Lcto where id_lcto = ? and tipo_lcto = 'L'";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setInt(1, numeroLancto);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public List<Documento> pesquisaDoctosLancto(int numeroLancto, short cdCnd)
			throws SQLException {
		List<Documento> listaDoctos = new ArrayList<>();
		Documento doc = null;
		String sql = "select i.id,c.nome_arquivo from sigadm.dbo.cndespes d inner join sigadm.dbo.glbCatalogo_Lcto l on d.numero = l.id_lcto "
				+ "inner join sigadm.dbo.glbCatalogo_Docto c on l.id_catalogo = c.id_catalogo "
				+ "inner join SIGADM_DOCUMENTOS.dbo.glbDocumento i on c.id = i.id where d.condominio = ? and d.numero = ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setInt(1, cdCnd);
		stmt.setInt(2, numeroLancto);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			doc = new Documento();
			doc.setId(rs.getInt("id"));
			doc.setNome(rs.getString("nome_arquivo"));
			listaDoctos.add(doc);
		}
		stmt.close();
		rs.close();
		return listaDoctos;
	}

	public byte[] pesquisaDocto(String id) throws NumberFormatException,
			SQLException {
		String sql = "select obj from SIGADM_DOCUMENTOS.dbo.glbDocumento where id = ?";
		PreparedStatement stmt = this.con.prepareStatement(sql);
		stmt.setInt(1, Integer.parseInt(id));
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			byte[] arq = rs.getBytes("obj");
			stmt.close();
			rs.close();
			return arq;
		} else {
			stmt.close();
			rs.close();
			return null;
		}
	}

}
