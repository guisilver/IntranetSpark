package br.com.oma.omaonline.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.oma.intranet.filters.ConexaoStatic;

public class intra_noticias_mkt_imagens_service {

	public int saveUploadedFile(intra_noticias_mkt_imagens uploadedFile) {
		int id = 0;
		try {
			Connection con = ConexaoStatic.getConexaoService();
			String generatedColumns[] = { "ID" };
			String sql = "insert into intra_noticias_mkt_imagens(content,contentType,fileName) values (?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql, generatedColumns);
			stmt.setBytes(1, uploadedFile.getContent());
			stmt.setString(2, uploadedFile.getContentType());
			stmt.setString(3, uploadedFile.getFileName());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return id;
	}

	public intra_noticias_mkt_imagens getUploadedFileById(int imageId) {
		intra_noticias_mkt_imagens imagem = new intra_noticias_mkt_imagens();
		try {
			Connection con = ConexaoStatic.getConexaoService();
			String sql = "select id,content,contentType,fileName from intra_noticias_mkt_imagens where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, imageId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				imagem.setId(rs.getInt("id"));
				imagem.setContent(rs.getBytes("content"));
				imagem.setContentType(rs.getString("contentType"));
				imagem.setFileName(rs.getString("fileName"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return imagem;
	}

	public intra_noticias_mkt_imagens getCandidatoById(int imageId) {
		intra_noticias_mkt_imagens imagem = new intra_noticias_mkt_imagens();
		try {
			Connection con = ConexaoStatic.getConexaoService();
			String sql = "select codigo,foto from intra_candidato_foto where codigoCandidato = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, imageId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				imagem.setId(rs.getInt("codigo"));
				imagem.setContent(rs.getBytes("foto"));
				imagem.setContentType(rs.getString("codigo"));
				imagem.setFileName(rs.getString("codigo"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return imagem;
	}

	public byte[] getEmpty(Connection con)
			throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		byte[] b = null;
		String sql = "select codigo,foto from intra_foto_silhueta where codigo = 1";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			b = rs.getBytes("foto");
		}
		return b;
	}

}
