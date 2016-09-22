package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.oma.intranet.dao.CadastroRecepcaoDAO;
import br.com.oma.intranet.entidades.intra_cadastro_recepcao;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ViewScoped
@ManagedBean
public class CadastroRecepcaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7185915135742986978L;
	private intra_cadastro_recepcao cadastro;
	private List<intra_cadastro_recepcao> listaCadastro, filtroCadastro;
	private Date inicio, fim;
	private int codigoSelecionado;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	
	public intra_cadastro_recepcao getCadastro() {
		return cadastro;
	}

	public void setCadastro(intra_cadastro_recepcao cadastro) {
		this.cadastro = cadastro;
	}

	public List<intra_cadastro_recepcao> getListaCadastro() {
		return listaCadastro;
	}

	public void setListaCadastro(List<intra_cadastro_recepcao> listaCadastro) {
		this.listaCadastro = listaCadastro;
	}

	public List<intra_cadastro_recepcao> getFiltroCadastro() {
		return filtroCadastro;
	}

	public void setFiltroCadastro(List<intra_cadastro_recepcao> filtroCadastro) {
		this.filtroCadastro = filtroCadastro;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public int getCodigoSelecionado() {
		return codigoSelecionado;
	}

	public void setCodigoSelecionado(int codigoSelecionado) {
		this.codigoSelecionado = codigoSelecionado;
	}

	public void pesquisaVisitantes() {
		CadastroRecepcaoDAO dao = new CadastroRecepcaoDAO();
		this.listaCadastro = dao.pesquisaVisitantes(this.inicio, this.fim);
	}

	public void etiquetaRecepcao() throws IOException, Exception{
		HashMap<Object, Object> parametros = new HashMap<>();
		String nome = "";
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		parametros.put("Codigo", this.codigoSelecionado);
		nome = "Etiqueta-Visitante";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.renderResponse();
		facesContext.responseComplete();
		this.downloadPDF(rju.geraRelSiga(parametros, nome, nome, 1));
	}
	
	public void downloadPDF(byte[] retorno) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			input = new BufferedInputStream(new ByteArrayInputStream(retorno), DEFAULT_BUFFER_SIZE);
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(retorno.length));
			response.setHeader("Content-Disposition", "inline;filename=\"EtiquetaVisitante.pdf\"");
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();
		} finally {
			close(output);
			close(input);
		}
		facesContext.responseComplete();
	}
	
	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
