package br.com.oma.intranet.dao;

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

import br.com.oma.intranet.entidades.intra_papercut;
import br.com.oma.intranet.entidades.intra_papercut_isento;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.util.JPAUtil;

public class PapercutDao extends LogGeralDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PreparedStatement pst;
	private Connection con;
	private ResultSet rs;

	private EntityManager manager;

	public PapercutDao() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_papercut> listarPapercut(Date periodoInicial, Date periodoFinal) {
		List<intra_papercut> listaPaper = new ArrayList<>();
		double contador = 1.0;
		Query query = this.manager
				.createNativeQuery("select c.account_name, c.sub_name, SUM( l.total_pages) as total_page "
						+ "from papercut.dbo.tbl_printer_usage_log l "
						+ "inner join papercut.dbo.tbl_account c on l.assoc_with_account_id = c.account_id "
						+ "inner join papercut.dbo.tbl_user u on l.used_by_user_id = u.user_id "
						+ "inner join papercut.dbo.tbl_printer i on l.printer_id = i.printer_id "
						+ "where l.usage_date between :p1 and :p2 and l.printed = 'Y' and l.cancelled = 'N' and l.job_comment is NULL "
						+ "group by c.account_name, c.sub_name order by c.account_name,c.sub_name ");
		query.setParameter("p1", periodoInicial);
		query.setParameter("p2", periodoFinal);
		List<Object[]> objetos = query.getResultList();
		
		if (!objetos.isEmpty()) {
			for (Object[] obj : objetos) {
				intra_papercut pc = new intra_papercut();
				pc.setId(contador);

				if(obj[0] !=null){
					pc.setNome(obj[0].toString());
				}
				if(obj[1] !=null){
					pc.setSubNome(obj[1].toString());
				}
				if(obj[2] !=null){
					pc.setTotalPage(Double.valueOf(obj[2].toString()));
				}
				listaPaper.add(pc);
				contador++;
			}
		}

		Query queryPaperIsento = this.manager.createQuery("from intra_papercut_isento");
		List<intra_papercut_isento> listaIsencao = queryPaperIsento.getResultList();
		for (int i = 0; i < listaPaper.size(); i++) {
		for (intra_papercut_isento ipi : listaIsencao) {
				if (listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("0")
						|| listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("1")
						|| listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("2")
						|| listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("3")
						|| listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("4")
						|| listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("5")
						|| listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("6")
						|| listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("7")
						|| listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("8")
						|| listaPaper.get(i).getNome().subSequence(0, 1).toString().trim().contains("9")
								& !listaPaper.get(i).getSubNome().trim().contains("Recibos")) {
					if (!listaPaper.get(i).getSubNome().trim().contains("Recibos")) {
						int condominio = Integer.valueOf(listaPaper.get(i).getNome().substring(0, 4));
						if (condominio == ipi.getCondominio()) {
							 if (ipi.isAta() & listaPaper.get(i).getSubNome().trim().equals("ATA")) {
								listaPaper.remove(i);
							}
							 if (ipi.isBalancete() & listaPaper.get(i).getSubNome().trim().equals("Balancete")) {
								listaPaper.remove(i);
							}
							 if (ipi.isCirculares()
									& listaPaper.get(i).getSubNome().trim().equals("Circulares")) {
								listaPaper.remove(i);
							}
							 if (ipi.isConvencaoRegulamento()
									& listaPaper.get(i).getSubNome().trim().equals("Convencao-Reg. Interno")) {
								listaPaper.remove(i);
							}
							 if (ipi.isConvocacao()
									& listaPaper.get(i).getSubNome().trim().equals("Convocacao")) {
								listaPaper.remove(i);
							}
							 if (ipi.isGeral() & listaPaper.get(i).getSubNome().trim().equals("GERAL")) {
								listaPaper.remove(i);
							}
							 if (ipi.isImpressaoColorida()
									& listaPaper.get(i).getSubNome().trim().equals("Impressoes Coloridas")) {
								listaPaper.remove(i);
							}
						}
					}
				}
			}
		}

		return listaPaper;
	}

	@SuppressWarnings("unchecked")
	public List<intra_papercut> listarPapercutAnalitico(Date periodoInicial, Date periodoFinal, String nome, String subNome) {
		List<intra_papercut> listaPaper = new ArrayList<>();
		Query query = this.manager
				.createNativeQuery("select l.printer_usage_log_id, c.account_name, c.sub_name, l.document_name, l.total_pages, l.usage_date, u.user_name "
						+ "from papercut.dbo.tbl_printer_usage_log l "
						+ "inner join papercut.dbo.tbl_account c on l.assoc_with_account_id = c.account_id "
						+ "inner join papercut.dbo.tbl_user u on l.used_by_user_id = u.user_id "
						+ "inner join papercut.dbo.tbl_printer i on l.printer_id = i.printer_id "
						+ "where l.usage_date between :p1 and :p2 and c.account_name = :p3 and c.sub_name = :p4 and l.printed = 'Y' and l.cancelled = 'N' "
						+ "order by c.account_name,c.sub_name ");
		query.setParameter("p1", periodoInicial);
		query.setParameter("p2", periodoFinal);
		query.setParameter("p3", nome);
		query.setParameter("p4", subNome);
		List<Object[]> objetos = query.getResultList();
		if (!objetos.isEmpty()) {
			for (Object[] obj : objetos) {
				intra_papercut pc = new intra_papercut();
				if(obj[0] !=null){
					pc.setId(Double.valueOf(obj[0].toString()));
				}
				if(obj[1] !=null){
					pc.setNome(obj[1].toString());
				}else{
					pc.setNome("");
				}
				if(obj[2] !=null){
					pc.setSubNome(obj[2].toString());
				}else{
					pc.setSubNome("");
				}
				if(obj[3] !=null){
					pc.setDocumentoNome(obj[3].toString());
				}else{
					pc.setDocumentoNome("");
				}
				if(obj[4] !=null){
					pc.setTotalPage(Double.valueOf(obj[4].toString()));
				}
				if(obj[5] != null){
					pc.setData((Date) obj[5]);
				}
				if(obj[6] != null){
					pc.setUsuario(obj[6].toString());
				}
				listaPaper.add(pc);
			}
		}
		return listaPaper;
	}
	
	@SuppressWarnings("unchecked")
	public int retornaCodigoDoServico(int condominio, double tipoDoEmissor) {
		int valor = 0;
		Query query = this.manager.createNativeQuery(
				"select codigo from sigadm.dbo.cndsvcon where condominio = :p1 and codigo_serv = :p2");
		query.setParameter("p1", condominio);
		query.setParameter("p2", tipoDoEmissor);
		List<Object> lista = query.getResultList();
		for (Object is : lista) {
			valor = Integer.valueOf(String.valueOf(is));
		}
		return valor;
	}
	
	public int retornaCodigoMsvcon(int qtdaLinhas) {
		int retorno = 0;
		try {
			String consulta = "select ult_svmov from [sigadm].[dbo].[cndmonit]";
			this.con = Conexao.getConexaoSiga();
			this.pst = con.prepareStatement(consulta);
			this.rs = this.pst.executeQuery();
			while(this.rs.next()){
				if(this.rs.getInt("ult_svmov") > 0){
					retorno = this.rs.getInt("ult_svmov");
					retorno += 1;
					String update = "update sigadm.dbo.cndmonit set ult_svmov = " +(retorno+qtdaLinhas);
					this.pst = this.con.prepareStatement(update);
					this.pst.executeUpdate();
				}
			}
			this.pst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("insert na cndmonit sistema Papercut "+e.getMessage());
		}
		
	/*	man.getTransaction().begin();
		Query query = man.createNativeQuery("select ult_svmov from sigadm.dbo.cndmonit");
		List<Object[]> lista = query.getResultList();
		if(!lista.isEmpty()){
			for (Object obj : lista) {
				retorno = Integer.valueOf(String.valueOf(obj));
				man.createNativeQuery("update sigadm.dbo.cndmonit set ult_svmov = "+(retorno+qtdaLinhas)).executeUpdate();
				man.getTransaction().commit();
				man.close();
				factory.close();
			}
		}*/
		
		return retorno;
	}
	
	public void salvarCobrancaServico(int codigoMsvcon,int codigoDoServico, double qtdaImpressao, String historico, Date dataProcessamento) {
		try {
			String insert = "insert into [sigadm].[dbo].[cndsvmov] (codigo, cod_svcon, qtde, historico, data) values (?,?,?,?,?)";
			this.con = Conexao.getConexaoSiga();
			this.pst = this.con.prepareStatement(insert);
			this.pst.setInt(1, codigoMsvcon);
			this.pst.setInt(2, codigoDoServico);
			this.pst.setDouble(3, qtdaImpressao);
			this.pst.setString(4, historico);
			this.pst.setDate(5, new java.sql.Date(dataProcessamento.getTime()));
			this.pst.executeUpdate();
			this.pst.close();
			this.con.close();
					
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("insert na cndsvmov sistema Papercut "+e.getMessage());
		
		}
	}

	public void updatePaperCut(intra_papercut cut2) {
		try {
			String update ="update [papercut].[dbo].[tbl_printer_usage_log] set job_comment = 'sim' where printer_usage_log_id = "+cut2.getId();
			this.con = Conexao.getConexaoPapercut();
			this.pst = this.con.prepareStatement(update);
			this.pst.executeUpdate();
			this.pst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("insert na cndsvmov sistema Papercut "+e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_papercut> listarPaperRel(Date periodoInicial, Date periodoFinal) {
		List<intra_papercut> listaPaper = new ArrayList<>();
		Query query = this.manager
				.createNativeQuery("select l.printer_usage_log_id, c.account_name, c.sub_name, l.document_name, l.total_pages, l.usage_date, u.user_name "
						+ "from papercut.dbo.tbl_printer_usage_log l "
						+ "inner join papercut.dbo.tbl_account c on l.assoc_with_account_id = c.account_id "
						+ "inner join papercut.dbo.tbl_user u on l.used_by_user_id = u.user_id "
						+ "inner join papercut.dbo.tbl_printer i on l.printer_id = i.printer_id "
						+ "where l.usage_date between :p1 and :p2 and l.printed = 'Y' and l.cancelled = 'N' and l.job_comment is not null "
						+ "order by c.account_name,c.sub_name ");
		query.setParameter("p1", periodoInicial);
		query.setParameter("p2", periodoFinal);
		List<Object[]> objetos = query.getResultList();
		if (!objetos.isEmpty()) {
			for (Object[] obj : objetos) {
				intra_papercut pc = new intra_papercut();
				if(obj[0] !=null){
					pc.setId(Double.valueOf(obj[0].toString()));
				}
				if(obj[1] !=null){
					pc.setNome(obj[1].toString());
				}else{
					pc.setNome("");
				}
				if(obj[2] !=null){
					pc.setSubNome(obj[2].toString());
				}else{
					pc.setSubNome("");
				}
				if(obj[3] !=null){
					pc.setDocumentoNome(obj[3].toString());
				}else{
					pc.setDocumentoNome("");
				}
				if(obj[4] !=null){
					pc.setTotalPage(Double.valueOf(obj[4].toString()));
				}
				if(obj[5] != null){
					pc.setData((Date) obj[5]);
				}
				if(obj[6] != null){
					pc.setUsuario(obj[6].toString());
				}
				listaPaper.add(pc);
			}
		}
		return listaPaper;
	}

	@SuppressWarnings("unchecked")
	public List<intra_papercut> listarPaperRel(Date periodoInicial) {
		List<intra_papercut> listaPaper = new ArrayList<>();
		Query query = this.manager
				.createNativeQuery("select l.printer_usage_log_id, c.account_name, c.sub_name, l.document_name, l.total_pages, l.usage_date, u.user_name "
						+ "from papercut.dbo.tbl_printer_usage_log l "
						+ "inner join papercut.dbo.tbl_account c on l.assoc_with_account_id = c.account_id "
						+ "inner join papercut.dbo.tbl_user u on l.used_by_user_id = u.user_id "
						+ "inner join papercut.dbo.tbl_printer i on l.printer_id = i.printer_id "
						+ "where l.usage_date >= :p1 and l.printed = 'Y' and l.cancelled = 'N' and l.job_comment is not null "
						+ "order by c.account_name,c.sub_name ");
		query.setParameter("p1", periodoInicial);
		List<Object[]> objetos = query.getResultList();
		if (!objetos.isEmpty()) {
			for (Object[] obj : objetos) {
				intra_papercut pc = new intra_papercut();
				if(obj[0] !=null){
					pc.setId(Double.valueOf(obj[0].toString()));
				}
				if(obj[1] !=null){
					pc.setNome(obj[1].toString());
				}else{
					pc.setNome("");
				}
				if(obj[2] !=null){
					pc.setSubNome(obj[2].toString());
				}else{
					pc.setSubNome("");
				}
				if(obj[3] !=null){
					pc.setDocumentoNome(obj[3].toString());
				}else{
					pc.setDocumentoNome("");
				}
				if(obj[4] !=null){
					pc.setTotalPage(Double.valueOf(obj[4].toString()));
				}
				if(obj[5] != null){
					pc.setData((Date) obj[5]);
				}
				if(obj[6] != null){
					pc.setUsuario(obj[6].toString());
				}
				listaPaper.add(pc);
			}
		}
		return listaPaper;
	}

	@SuppressWarnings("unchecked")
	public List<intra_papercut> listarPapercut(Date periodoInicial) {
		List<intra_papercut> listaPaper = new ArrayList<>();
		double contador = 1.0;
		Query query = this.manager
				.createNativeQuery("select c.account_name, c.sub_name, SUM( l.total_pages) as total_page "
						+ "from papercut.dbo.tbl_printer_usage_log l "
						+ "inner join papercut.dbo.tbl_account c on l.assoc_with_account_id = c.account_id "
						+ "inner join papercut.dbo.tbl_user u on l.used_by_user_id = u.user_id "
						+ "inner join papercut.dbo.tbl_printer i on l.printer_id = i.printer_id "
						+ "where l.usage_date >= :p1 and l.printed = 'Y' and l.cancelled = 'N' and l.job_comment is NULL "
						+ "group by c.account_name, c.sub_name order by c.account_name,c.sub_name ");
		query.setParameter("p1", periodoInicial);
		List<Object[]> objetos = query.getResultList();
		if (!objetos.isEmpty()) {
			for (Object[] obj : objetos) {
				intra_papercut pc = new intra_papercut();
				pc.setId(contador);

				if(obj[0] !=null){
					pc.setNome(obj[0].toString());
				}
				if(obj[1] !=null){
					pc.setSubNome(obj[1].toString());
				}
				if(obj[2] !=null){
					pc.setTotalPage(Double.valueOf(obj[2].toString()));
				}
				listaPaper.add(pc);
				contador++;
			}
		}
		return listaPaper;
	}

	@SuppressWarnings("unchecked")
	public List<intra_papercut> listarPapercutAnalitico(Date periodoInicial, String nome, String subNome) {
		List<intra_papercut> listaPaper = new ArrayList<>();
		Query query = this.manager
				.createNativeQuery("select l.printer_usage_log_id, c.account_name, c.sub_name, l.document_name, l.total_pages, l.usage_date, u.user_name "
						+ "from papercut.dbo.tbl_printer_usage_log l "
						+ "inner join papercut.dbo.tbl_account c on l.assoc_with_account_id = c.account_id "
						+ "inner join papercut.dbo.tbl_user u on l.used_by_user_id = u.user_id "
						+ "inner join papercut.dbo.tbl_printer i on l.printer_id = i.printer_id "
						+ "where l.usage_date >= :p1 and c.account_name = :p3 and c.sub_name = :p4 and l.printed = 'Y' and l.cancelled = 'N' "
						+ "order by c.account_name,c.sub_name ");
		query.setParameter("p1", periodoInicial);
		query.setParameter("p3", nome);
		query.setParameter("p4", subNome);
		List<Object[]> objetos = query.getResultList();
		if (!objetos.isEmpty()) {
			for (Object[] obj : objetos) {
				intra_papercut pc = new intra_papercut();
				if(obj[0] !=null){
					pc.setId(Double.valueOf(obj[0].toString()));
				}
				if(obj[1] !=null){
					pc.setNome(obj[1].toString());
				}else{
					pc.setNome("");
				}
				if(obj[2] !=null){
					pc.setSubNome(obj[2].toString());
				}else{
					pc.setSubNome("");
				}
				if(obj[3] !=null){
					pc.setDocumentoNome(obj[3].toString());
				}else{
					pc.setDocumentoNome("");
				}
				if(obj[4] !=null){
					pc.setTotalPage(Double.valueOf(obj[4].toString()));
				}
				if(obj[5] != null){
					pc.setData((Date) obj[5]);
				}
				if(obj[6] != null){
					pc.setUsuario(obj[6].toString());
				}
				listaPaper.add(pc);
			}
		}
		return listaPaper;
	}
}
