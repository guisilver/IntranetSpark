package br.com.oma.omaonline.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jgroups.tests.bla;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.black_list;
import br.com.oma.sigadm.entidades.sgimpos;
import br.com.oma.sigadm.entidades.sgltimp;
import br.com.oma.sigadm.entidades.sgreten;

public class FinanceiroImpostosDAO {

	private PreparedStatement pmst;
	private Connection con;
	
	private EntityManager manager;
	
	public FinanceiroImpostosDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	public void salvarSGRETEN(sgreten retenBean) {

		String sql = "INSERT INTO sip_teste.dbo.[sgreten]"
				+ "([nrolancto],[cnpj] ,[cnpj_cliente] ,[codigo],[credor],[origem] ,[sistema],[valor],"
				+ "[valor_base],[valor_cofins],[valor_csll],[valor_pis],[valor_retencao] ,[vencimento]) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexaoSIP();
			
			pmst = con.prepareStatement(sql);

			pmst = con.prepareStatement(sql);
			pmst.setInt(1, retenBean.getNrolancto());
			pmst.setDouble(2, retenBean.getCnpj());
			pmst.setDouble(3, retenBean.getCnpj_cliente());
			pmst.setInt(4, retenBean.getCodigo());
			pmst.setString(5, retenBean.getCredor());
			pmst.setInt(6, retenBean.getOrigem());
			pmst.setInt(7, retenBean.getSistema());
			pmst.setDouble(8, retenBean.getValor());
			pmst.setDouble(9, retenBean.getValor_base());
			pmst.setDouble(10, retenBean.getValor_cofins());
			pmst.setDouble(11, retenBean.getValor_csll());
			pmst.setDouble(12, retenBean.getValor_pis());
			pmst.setDouble(13, retenBean.getValor_retencao());
			pmst.setDate(14, new java.sql.Date(retenBean.getVencimento().getTime()));
			
			pmst.executeUpdate();
			pmst.close();
			con.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvarSGLTIMP(sgltimp timpBean) {
		try {
			String sql = "INSERT INTO sip_teste.dbo.sgltimp ([nrolancto],[data_inclusao],[codigo],[bloco],[vencimento],[valor],[historico],[cta_bancaria],[credor],"
					+ "[data_base],[documento],[total_retencao],[sistema],[contrato],[imovel],[repassar],[div_propriet],[cpmf],[favorecido],[data_alteracao],[nrolancto_aux])"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,null)";

			//con = Conexao.getConexaoSiga();
			con = Conexao.getConexaoSIP();
			//con = Conexao.getConexaoOmaonline();
			
			pmst = con.prepareStatement(sql);
			pmst.setInt(1, timpBean.getNrolancto());
			pmst.setDate(2, new java.sql.Date(timpBean.getData_inclusao().getTime()));
			pmst.setInt(3, timpBean.getCodigo());
			pmst.setString(4, timpBean.getBloco());
			pmst.setDate(5, new java.sql.Date(timpBean.getVencimento().getTime()));
			pmst.setDouble(6, timpBean.getValor());
			pmst.setString(7, timpBean.getHistorico());
			pmst.setInt(8, timpBean.getCta_bancaria());
			pmst.setString(9, timpBean.getCredor());
			pmst.setDate(10, new java.sql.Date(timpBean.getData_base().getTime()));
			pmst.setString(11, timpBean.getDocumento());
			pmst.setDouble(12, timpBean.getTotal_retencao());
			pmst.setInt(13, timpBean.getSistema());
			pmst.setInt(14, timpBean.getContrato());
			pmst.setInt(15, timpBean.getImovel());
			pmst.setString(16, timpBean.getRepassar());
			pmst.setString(17, timpBean.getDiv_propriet());
			pmst.setString(18, timpBean.getCpmf());
			pmst.setString(19, timpBean.getFavorecido());
			pmst.setDate(20, null);

			pmst.executeUpdate();
			pmst.close();
			con.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//	this.manager.persist(timpBean);
	}

	public void salvarSGIMPOS(sgimpos imposBean) {
		try {
			String sql = "INSERT INTO sip_teste.dbo.sgimpos ([nrolancto],[tipo_imposto],[cod_receita],[vencto],[percentual],[valor],[cod_ccm],"
					+ "[obs_1],[obs_2],[lancto_gerado],[dt_base],[cod_municip],[controle],[vencto_guia],[sistema],[conta_contabil],[tipopagto],"
					+ "[valor_a_pagar],[data_alteracao],[numero_nf],[item_nf]) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			//con = Conexao.getConexaoSiga();
			con = Conexao.getConexaoSIP();
			//con = Conexao.getConexaoOmaonline();
			
			pmst = con.prepareStatement(sql);
			pmst.setInt(1, imposBean.getNrolancto());
			pmst.setInt(2, imposBean.getTipo_imposto());
			pmst.setDouble(3, imposBean.getCod_receita());
			pmst.setDate(4,new java.sql.Date(imposBean.getVencto().getTime()));
			pmst.setDouble(5, imposBean.getPercentual());
			pmst.setDouble(6, imposBean.getValor());
			pmst.setString(7, imposBean.getCod_ccm());
			pmst.setString(8, imposBean.getObs_1());
			pmst.setString(9, imposBean.getObs_2());
			pmst.setInt(10, imposBean.getLancto_gerado());
			pmst.setDate(11, new java.sql.Date(imposBean.getDt_base().getTime()));
			pmst.setInt(12, imposBean.getCod_municip());
			pmst.setInt(13, imposBean.getControle());
			pmst.setDate(14, null);
			pmst.setInt(15, imposBean.getSistema());
			pmst.setInt(16, imposBean.getConta_contabil());
			pmst.setString(17, imposBean.getTipopagto());
			pmst.setDouble(18, imposBean.getValor_a_pagar());
			pmst.setDate(19, null);
			pmst.setString(20, imposBean.getNumero_nf());
			pmst.setInt(21, imposBean.getItem_nf());

			pmst.executeUpdate();
			pmst.close();
			con.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluirImpostosExistentes(int nrolancto) {
		try {
			String sgreten = "delete sgreten where nrolancto = ?";
			//con = Conexao.getConexaoSiga();
			//con = Conexao.getConexaoSIP();
			this.con = Conexao.getConexaoOmaonline();
			pmst = con.prepareStatement(sgreten);
			pmst.setInt(1, nrolancto);
			
			pmst.executeUpdate();
			pmst.close();
			con.close();
			
			String sgimpos = "delete sgimpos where nrolancto = ?";
			//con = Conexao.getConexaoSiga();
			//con = Conexao.getConexaoSIP();
			this.con = Conexao.getConexaoOmaonline();
			pmst = con.prepareStatement(sgimpos);
			pmst.setInt(1, nrolancto);
			
			pmst.executeUpdate();
			pmst.close();
			con.close();
			
			String sgltimp = "delete sgltimp where nrolancto = ?";
			//con = Conexao.getConexaoSiga();
			//con = Conexao.getConexaoSIP();
			this.con = Conexao.getConexaoOmaonline();
			pmst = con.prepareStatement(sgltimp);
			pmst.setInt(1, nrolancto);
			
			pmst.executeUpdate();
			pmst.close();
			con.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	public boolean verificaBlackList(black_list blackListMB) {
		
		Query query = this.manager.createQuery("from black_list where "
				+ "((condominio = :p1 and cnpj = 0 and conta_contabil = 0 and codigo_gerente = :p4) or "
				+ "(condominio = :p1 and cnpj > :p2 and conta_contabil > :p3 and codigo_gerente = :p4) or "
				+ "(condominio = :p1 and cnpj > :p2 and codigo_gerente = :p4) or "
				+ "(condominio = :p1 and conta_contabil > :p3 and codigo_gerente = :p4) or "
				+ "(cnpj > :p2 and conta_contabil > :p3 and codigo_gerente = :p4) or "
				+ "(cnpj > :p2 and codigo_gerente = :p4) or "
				+ "(conta_contabil > 0 and codigo_gerente = :p4)) and "
				+ "status_item = 1");
		query.setParameter("p1", blackListMB.getCondominio());
		query.setParameter("p2", blackListMB.getCnpj());
		query.setParameter("p3", blackListMB.getContaContabil());
		query.setParameter("p4", blackListMB.getCodigoGerente());
		List<black_list> lista = query.getResultList();
		if(!lista.isEmpty()){
			return true;
		}else{
			return false;
		}
			
	}
		
}
