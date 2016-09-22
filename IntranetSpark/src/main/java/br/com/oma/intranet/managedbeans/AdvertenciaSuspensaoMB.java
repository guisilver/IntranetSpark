package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.oma.intranet.dao.AdvertenciaSuspensaoDAO;
import br.com.oma.intranet.entidades.intra_advert_susp;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ManagedBean(name = "asMB")
@ViewScoped
public class AdvertenciaSuspensaoMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7245178573504042369L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private AdvertenciaSuspensaoDAO asDAO;
	private intra_advert_susp asBEAN = new intra_advert_susp();
	private intra_advert_susp asBEANSelecionado;
	private intra_condominios icBEAN = new intra_condominios();

	// ATRIBUTOS
	private List<intra_advert_susp> listaTabela, listaFuncionario;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	private String pesquisarPor = "Todos";

	// GET X SET
	public AdvertenciaSuspensaoDAO getAsDAO() {
		return asDAO;
	}

	public void setAsDAO(AdvertenciaSuspensaoDAO asDAO) {
		this.asDAO = asDAO;
	}

	public intra_advert_susp getAsBEAN() {
		return asBEAN;
	}

	public void setAsBEAN(intra_advert_susp asBEAN) {
		this.asBEAN = asBEAN;
	}

	public intra_advert_susp getAsBEANSelecionado() {
		return asBEANSelecionado;
	}

	public void setAsBEANSelecionado(intra_advert_susp asBEANSelecionado) {
		this.asBEANSelecionado = asBEANSelecionado;
	}

	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
	}

	public List<intra_advert_susp> getListaTabela() {
		return listaTabela;
	}

	public void setListaTabela(List<intra_advert_susp> listaTabela) {
		this.listaTabela = listaTabela;
	}

	public List<intra_advert_susp> getListaFuncionario() {
		try {
			this.asDAO = new AdvertenciaSuspensaoDAO();
			this.listaFuncionario = this.asDAO.listaFuncionarios(this.asBEAN.getEmpresa());
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaFuncionario;
	}

	public void setListaFuncionario(List<intra_advert_susp> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public String getPesquisarPor() {
		return pesquisarPor;
	}

	public void setPesquisarPor(String pesquisarPor) {
		this.pesquisarPor = pesquisarPor;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// MÉTODOS

	// ↓ MÉTODO PARA SALVAR ↓

	public void salvar() {
		try {
			this.asDAO = new AdvertenciaSuspensaoDAO();
			this.asBEAN.setEmpresa(this.asBEAN.getEmpresa());
			this.asBEAN.setFuncao(this.asBEAN.getFuncao());
			this.asBEAN.setFuncionario(this.asBEAN.getFuncionario());
			this.asBEAN.setMotSusp(this.asBEAN.getMotSusp());
			this.nomeFuncionario();
			this.asBEAN.setTipo(this.asBEAN.isTipo());
			if (this.asBEAN.isTipo() == true) {
				this.asBEAN.setRetSusp(this.asBEAN.getRetSusp());
				this.asBEAN.setDiaSusp(this.asBEAN.getDiaSusp());
			}
			if (this.asBEAN.getEmpresa() != 1) {
				this.asBEAN.setSetor("Condomínio");
			} else {
				this.asBEAN.setSetor(this.asBEAN.getSetor());
			}
			this.asBEAN.setDataFeito(new Date());
			this.asDAO.salvar(this.asBEAN, this.sessaoMB);
			this.asBEAN = new intra_advert_susp();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	// ↓ MÉTODO PARA RETORNAR FUNÇÃO DO FUNCIONÁRIO ↓

	public void nomeFuncao() {
		try {
			this.asDAO = new AdvertenciaSuspensaoDAO();
			String i = this.asDAO.nomeFuncao(this.asBEAN.getEmpresa(), this.asBEAN.getFuncionario());
			this.asBEAN.setFuncao(i);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	// ↓ MÉTODO PARA RETORNAR NOME DO FUNCIONÁRIO ↓

	public void nomeFuncionario() {
		try {
			this.asDAO = new AdvertenciaSuspensaoDAO();
			String i = this.asDAO.nomeFuncionario(this.asBEAN.getEmpresa(), this.asBEAN.getFuncionario());
			this.asBEAN.setNome(i);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA CARREGAR INFOS PARA ALTERAÇÃO ↓

	public void carregarDialogFuncionario(intra_advert_susp i) {
		this.asBEAN.setDiaSusp(i.getDiaSusp());
		this.asBEAN.setEmpresa(i.getEmpresa());
		this.asBEAN.setFuncao(i.getFuncao());
		this.asBEAN.setFuncionario(i.getFuncionario());
		this.asBEAN.setId(i.getId());
		this.asBEAN.setMotSusp(i.getMotSusp());
		this.asBEAN.setNome(i.getNome());
		this.asBEAN.setRetSusp(i.getRetSusp());
		this.asBEAN.setTipo(i.isTipo());
		this.asBEAN.setSetor(i.getSetor());
	}

	// ↓ MÉTODO PARA IMPRESSÃO DO RELATÓRIO ↓

	public void imprimir() throws IOException, Exception {
		HashMap<Object, Object> parametros = new HashMap<>();
		String nome = "";
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		parametros.put("Parameter1", this.asBEANSelecionado.getId());
		if (this.asBEAN.isTipo() == true) {
			nome = "DP-Suspensao";
		} else {
			nome = "DP-Advertencia";
		}
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
			response.setHeader("Content-Disposition", "inline;filename=\"Advert-Susp.pdf\"");
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

	// ↓ MÉTODO PARA PESQUISAR ADVERTÊNCIA E SUSPENSÃO ↓

	public void pesquisarAdvertenciaSuspensao() {
		try {
			this.asDAO = new AdvertenciaSuspensaoDAO();
			if (this.pesquisarPor.equals("Condomínio")) {
				throw new Exception("Selecione um condomínio para pesquisar!");
			}
			switch (this.pesquisarPor) {
			case "Todos":
				this.listaTabela = this.asDAO.listaTabela();
				break;
			case "Empresa":
				this.listaTabela = this.asDAO.listaTabela(this.asBEAN.getEmpresa());
				break;
			case "Funcionário":
				this.listaTabela = this.asDAO.listaTabela2(this.asBEAN.getEmpresa(), this.asBEAN.getFuncionario());
				break;
			default:
				break;
			}
			this.asDAO.fechaConexao();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA EXCLUIR ADVERTÊNCIA OU SUSPENSÃO ↓

	public void excluir() {
		if (this.asBEANSelecionado != null) {
			try {
				this.asDAO = new AdvertenciaSuspensaoDAO();
				intra_advert_susp i = this.asDAO.pesquisaAdvertSusp(this.asBEANSelecionado.getId());
				this.asDAO.excluir(i, this.sessaoMB);
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
