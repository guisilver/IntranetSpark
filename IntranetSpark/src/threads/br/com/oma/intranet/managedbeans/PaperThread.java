package br.com.oma.intranet.managedbeans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.oma.intranet.dao.LogGeralDAO;
import br.com.oma.intranet.entidades.intra_papercut;
import br.com.oma.intranet.filters.Conexao;

public class PaperThread extends LogGeralDAO implements Runnable {

	private static final long serialVersionUID = 1L;

	private PreparedStatement pst;
	private Connection con;
	private ResultSet rs;

	private Date periodoInicial = null;
	private Date periodoFinal = null;
	private List<intra_papercut> selectPaperCut, listaPapercutAnalitico;

	public PaperThread(List<intra_papercut> selectPaperCut, Date periodoInicial, Date periodoFinal) {
		this.selectPaperCut = selectPaperCut;
		this.periodoInicial = periodoInicial;
		this.periodoFinal = periodoFinal;
	}

	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public List<intra_papercut> getSelectPaperCut() {
		return selectPaperCut;
	}

	public void setSelectPaperCut(List<intra_papercut> selectPaperCut) {
		this.selectPaperCut = selectPaperCut;
	}

	public List<intra_papercut> getListaPapercutAnalitico() {
		return listaPapercutAnalitico;
	}

	public void setListaPapercutAnalitico(List<intra_papercut> listaPapercutAnalitico) {
		this.listaPapercutAnalitico = listaPapercutAnalitico;
	}

	@Override
	public void run() {
		System.out.println("iniciou");
		for (intra_papercut cut : selectPaperCut) {
			if (this.periodoInicial != null & this.periodoFinal != null) {
				this.listaPapercutAnalitico = this.listarPapercutAnalitico(this.periodoInicial, this.periodoFinal,
						cut.getNome(), cut.getSubNome());
			} else {
				this.listaPapercutAnalitico = this.listarPapercutAnalitico(this.periodoInicial, cut.getNome(),
						cut.getSubNome());
			}
			for (intra_papercut cut2 : listaPapercutAnalitico) {
				this.updatePaperCut(cut2);
			}
		}
		System.out.println("finalizou");
	}

	public void updatePaperCut(intra_papercut cut2) {
		try {
			String update = "update [papercut].[dbo].[tbl_printer_usage_log] set job_comment = 'sim' where printer_usage_log_id = "
					+ cut2.getId();
			this.con = Conexao.getConexaoPapercut();
			this.pst = this.con.prepareStatement(update);
			this.pst.executeUpdate();
			this.pst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("insert na cndsvmov sistema Papercut " + e.getMessage());
		}
	}

	public List<intra_papercut> listarPapercutAnalitico(Date periodoInicial, Date periodoFinal, String nome,
			String subNome) {
		List<intra_papercut> listaPaper = new ArrayList<>();
		try {
			String sql = "select l.printer_usage_log_id, c.account_name, c.sub_name, l.document_name, l.total_pages, l.usage_date, u.user_name "
					+ "from papercut.dbo.tbl_printer_usage_log l "
					+ "inner join papercut.dbo.tbl_account c on l.assoc_with_account_id = c.account_id "
					+ "inner join papercut.dbo.tbl_user u on l.used_by_user_id = u.user_id "
					+ "inner join papercut.dbo.tbl_printer i on l.printer_id = i.printer_id "
					+ "where l.usage_date between ? and ? and c.account_name = ? and c.sub_name = ? and l.printed = 'Y' and l.cancelled = 'N' "
					+ "order by c.account_name,c.sub_name ";
			this.con = Conexao.getConexaoPapercut();
			this.pst = con.prepareStatement(sql);
			this.pst.setDate(1, new java.sql.Date(periodoInicial.getTime()));
			this.pst.setDate(2, new java.sql.Date(periodoFinal.getTime()));
			this.pst.setString(3, nome);
			this.pst.setString(4, subNome);
			this.rs = this.pst.executeQuery();
			while (this.rs.next()) {
				intra_papercut pc = new intra_papercut();
				if (this.rs.getDouble("printer_usage_log_id") > 0) {
					pc.setId(this.rs.getDouble("printer_usage_log_id"));
				}
				if (this.rs.getString("account_name") != null) {
					pc.setNome(this.rs.getString("account_name"));
				} else {
					pc.setNome("");
				}
				if (this.rs.getString("sub_name") != null) {
					pc.setSubNome(this.rs.getString("sub_name"));
				} else {
					pc.setSubNome("");
				}
				if (this.rs.getString("document_name") != null) {
					pc.setDocumentoNome(this.rs.getString("document_name"));
				} else {
					pc.setDocumentoNome("");
				}
				if (this.rs.getDouble("total_pages") > 0) {
					pc.setTotalPage(this.rs.getDouble("total_pages"));
				}
				if (this.rs.getDate("usage_date") != null) {
					pc.setData(this.rs.getDate("usage_date"));
				}
				if (this.rs.getString("document_name") != null) {
					pc.setUsuario(this.rs.getString("document_name"));
				}
				listaPaper.add(pc);
			}
			this.pst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("Erro ao listar tabela papercut salvar :" + e.getMessage());
		}
		return listaPaper;
	}

	public List<intra_papercut> listarPapercutAnalitico(Date periodoInicial, String nome, String subNome) {
		List<intra_papercut> listaPaper = new ArrayList<>();
		try {
			String sql = ("select l.printer_usage_log_id, c.account_name, c.sub_name, l.document_name, l.total_pages, l.usage_date, u.user_name "
					+ "from papercut.dbo.tbl_printer_usage_log l "
					+ "inner join papercut.dbo.tbl_account c on l.assoc_with_account_id = c.account_id "
					+ "inner join papercut.dbo.tbl_user u on l.used_by_user_id = u.user_id "
					+ "inner join papercut.dbo.tbl_printer i on l.printer_id = i.printer_id "
					+ "where l.usage_date >= ? and c.account_name = ? and c.sub_name = ? and l.printed = 'Y' and l.cancelled = 'N' "
					+ "order by c.account_name,c.sub_name ");
			this.con = Conexao.getConexaoPapercut();
			this.pst = con.prepareStatement(sql);
			this.pst.setDate(1, new java.sql.Date(periodoInicial.getTime()));
			this.pst.setString(2, nome);
			this.pst.setString(3, subNome);
			this.rs = this.pst.executeQuery();
			while (this.rs.next()) {
				intra_papercut pc = new intra_papercut();
				if (this.rs.getDouble("printer_usage_log_id") > 0) {
					pc.setId(this.rs.getDouble("printer_usage_log_id"));
				}
				if (this.rs.getString("account_name") != null) {
					pc.setNome(this.rs.getString("account_name"));
				} else {
					pc.setNome("");
				}
				if (this.rs.getString("sub_name") != null) {
					pc.setSubNome(this.rs.getString("sub_name"));
				} else {
					pc.setSubNome("");
				}
				if (this.rs.getString("document_name") != null) {
					pc.setDocumentoNome(this.rs.getString("document_name"));
				} else {
					pc.setDocumentoNome("");
				}
				if (this.rs.getDouble("total_pages") > 0) {
					pc.setTotalPage(this.rs.getDouble("total_pages"));
				}
				if (this.rs.getDate("usage_date") != null) {
					pc.setData(this.rs.getDate("usage_date"));
				}
				if (this.rs.getString("document_name") != null) {
					pc.setUsuario(this.rs.getString("document_name"));
				}
				listaPaper.add(pc);
			}
			this.pst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("Erro ao listar tabela papercut salvar :" + e.getMessage());
		}
		return listaPaper;
	}
}
