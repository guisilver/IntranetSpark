package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
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

import br.com.oma.intranet.entidades.TaxaInadimplencia;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.util.JPAUtil;

public class TaxaInadimplenciaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7598305194979467899L;
	private Connection con;
	private EntityManager manager;

	public TaxaInadimplenciaDAO() throws FileNotFoundException,
			ClassNotFoundException, IOException, SQLException {
		this.con = Conexao.getConexaoSiga();
		this.manager = JPAUtil.getManager();
	}

	public List<TaxaInadimplencia> pesquisaTaxaTotal(
			List<intra_condominios> condominio, Date dataInicio, Date dataFim)
			throws SQLException {
		/*int i = 0;*/
		String sql = "";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<TaxaInadimplencia> listaTaxas = new ArrayList<>();
		List<TaxaInadimplencia> taxaTotal = null;
		List<TaxaInadimplencia> taxaPagos = null;
		TaxaInadimplencia taxa = null;
		int mes = new DateTime(dataInicio).getMonthOfYear();
		int ano = new DateTime(dataInicio).getYear();

		int mesFim = new DateTime(dataFim).getMonthOfYear();
		int anoFim = new DateTime(dataFim).getYear();
		for (intra_condominios cnd : condominio) {

			sql = "select data_inicial,data_final from cndgrctr where condominio = ? and mes = ? and ano = ?";
			stmt = this.con.prepareStatement(sql);
			stmt.setInt(1, cnd.getCodigo());
			stmt.setInt(2, mes);
			stmt.setInt(3, ano);

			rs = stmt.executeQuery();
			while (rs.next()) {
				dataInicio = rs.getDate("data_inicial");
			}

			sql = "select data_inicial,data_final from cndgrctr where condominio = ? and mes = ? and ano = ?";
			stmt = this.con.prepareStatement(sql);
			stmt.setInt(1, cnd.getCodigo());
			stmt.setInt(2, mesFim);
			stmt.setInt(3, anoFim);

			rs = stmt.executeQuery();
			while (rs.next()) {
				dataFim = rs.getDate("data_final");
			}

			sql = "select condominio, count(recibo) recibo, sum(valor_recibo) valor_recibo, YEAR(vencto) ano_vencto, MONTH(vencto) mes_vencto from cndrecib "
					+ "where condominio = ? and vencto between ? and ?  and flag_situacao <> 9 "
					+ "group by condominio, YEAR(vencto), MONTH(vencto) ORDER BY condominio, YEAR(vencto), MONTH(vencto) ";
			stmt = this.con.prepareStatement(sql);
			stmt.setInt(1, cnd.getCodigo());
			stmt.setDate(2, new java.sql.Date(dataInicio.getTime()));
			stmt.setDate(3, new java.sql.Date(dataFim.getTime()));

			rs = stmt.executeQuery();
			taxaTotal = new ArrayList<>();
			while (rs.next()) {
				taxa = new TaxaInadimplencia();
				taxa.setCodigoCondominio(rs.getInt("condominio"));
				taxa.setAnoVencimento(rs.getInt("ano_vencto"));
				taxa.setMesVencimento(rs.getInt("mes_vencto"));
				taxa.setRecibo(rs.getInt("recibo"));
				taxa.setReciboValor(rs.getDouble("valor_recibo"));
				taxaTotal.add(taxa);
/*				if(taxa.getReciboValor() > 200000){
					System.out.println(taxa.getCodigoCondominio() +" - "+ taxa.getReciboValor());
				}
				i++;*/
			}

			sql = "select condominio, count(recibo) recibo, sum(valor_recibo) valor_recibo, YEAR(vencto) ano_vencto,MONTH(vencto) mes_vencto from cndrecib "
					+ "where condominio = ? and vencto between ? and ? and flag_situacao <> 9  and CONVERT(CHAR(7),data_recbto,21) <= CONVERT(CHAR(7),vencto,21) "
					+ "and data_recbto is not null "
					+ "group by condominio,YEAR(vencto), MONTH(vencto) "
					+ "ORDER BY condominio,YEAR(vencto), MONTH(vencto)";
			stmt = this.con.prepareStatement(sql);
			stmt.setInt(1, cnd.getCodigo());
			stmt.setDate(2, new java.sql.Date(dataInicio.getTime()));
			stmt.setDate(3, new java.sql.Date(dataFim.getTime()));

			rs = stmt.executeQuery();
			taxaPagos = new ArrayList<>();
			while (rs.next()) {
				taxa = new TaxaInadimplencia();
				taxa.setCodigoCondominio(rs.getInt("condominio"));
				taxa.setAnoVencimento(rs.getInt("ano_vencto"));
				taxa.setMesVencimento(rs.getInt("mes_vencto"));
				taxa.setRecibo(rs.getInt("recibo"));
				taxa.setReciboValor(rs.getDouble("valor_recibo"));
				taxaPagos.add(taxa);
			}
			
			listaTaxas.addAll(populaInadimplencia(taxaTotal, taxaPagos));
		}
	/*	System.out.println(i);*/
		return listaTaxas;
	}

	public List<TaxaInadimplencia> populaInadimplencia(
			List<TaxaInadimplencia> total, List<TaxaInadimplencia> pagos) {
		TaxaInadimplencia taxaFinal = null;
		List<TaxaInadimplencia> listaTaxas = new ArrayList<>();
		for (TaxaInadimplencia aux1 : total) {
			for (TaxaInadimplencia aux2 : pagos) {
				if (aux1.getMesVencimento() == aux2.getMesVencimento()
						&& aux1.getAnoVencimento() == aux2.getAnoVencimento()
						&& aux1.getCodigoCondominio() == aux2
								.getCodigoCondominio()) {
					taxaFinal = new TaxaInadimplencia();
					taxaFinal = aux1;
					taxaFinal.setReciboPago(aux2.getRecibo());
					taxaFinal.setReciboValorPago(aux2.getReciboValor());
					taxaFinal.setInadimplenciaQtd(aux1.getRecibo()
							- aux2.getRecibo());
					taxaFinal.setInadimplenciaValor(aux1.getReciboValor()- aux2.getReciboValor());
					double taxaQtd = ((aux1.getRecibo() - aux2.getRecibo()) / aux1.getRecibo()) * 100;
					double taxaValor = ((aux1.getReciboValor() - aux2.getReciboValor()) / aux1.getReciboValor()) * 100;
					taxaFinal.setPorcentagemQtd(taxaQtd);
					taxaFinal.setPorcentagemValor(taxaValor);
					listaTaxas.add(taxaFinal);
				}
			}
		}
		return listaTaxas;
	}

	public void fechaConexao() throws SQLException {
		this.con.close();
	}

	@SuppressWarnings("unchecked")
	public List<intra_grupo_gerente> getListaGerentes() {
		Query query = this.manager
				.createQuery("from intra_grupo_gerente order by nome");
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
	public List<intra_condominios> getListaCondominios() {
		Query query = this.manager
				.createQuery("from intra_condominios where codigo between 111 and 2000 order by codigo");
		return query.getResultList();
	}

	public intra_grupo_gerente pesquisaGerentePorCodigo(int codgeren) {
		return this.manager.find(intra_grupo_gerente.class, codgeren);
	}

}
