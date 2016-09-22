package br.com.oma.intranet.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oma.intranet.dao.FuncionariosDAO;
import br.com.oma.intranet.entidades.intra_funcionario_oma;

@ViewScoped
@ManagedBean
public class FuncionariosMB {

	private intra_funcionario_oma funcionario = new intra_funcionario_oma();
	private intra_funcionario_oma funcionarioSelecionado;
	private List<intra_funcionario_oma> listaFuncionarios;

	public intra_funcionario_oma getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(intra_funcionario_oma funcionario) {
		this.funcionario = funcionario;
	}

	public intra_funcionario_oma getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(intra_funcionario_oma funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public List<intra_funcionario_oma> getListaFuncionarios() {
		if (this.listaFuncionarios == null) {
			FuncionariosDAO dao = new FuncionariosDAO();
			this.listaFuncionarios = dao.getListaFuncionarios();
		}
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<intra_funcionario_oma> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public intra_funcionario_oma constroiFuncionario() {
		return null;
	}

	public void salvarNovoFuncionario() {
		try {
			FuncionariosDAO dao = new FuncionariosDAO();
			dao.salvarNovoFuncionario(this.funcionario);
			this.limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarAlteracoesFuncionario() {
		try {
			FuncionariosDAO dao = new FuncionariosDAO();
			dao.salvarAlteracoesFuncionario(this.funcionarioSelecionado);
			this.limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirFuncionario() {
		try {
			FuncionariosDAO dao = new FuncionariosDAO();
			dao.excluirFuncionario(this.funcionarioSelecionado);
			this.limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpar() {
		this.funcionario = new intra_funcionario_oma();
	}

}
