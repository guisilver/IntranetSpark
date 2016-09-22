package br.com.oma.intranet.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.oma.intranet.entidades.intra_funcionario_oma;
import br.com.oma.intranet.util.JPAUtil;

public class FuncionariosDAO {

	private EntityManager manager;

	public FuncionariosDAO() {
		this.manager = JPAUtil.getManager();
	}

	public List<intra_funcionario_oma> getListaFuncionarios() {
		return this.manager.createQuery("FROM intra_funcionario_oma", intra_funcionario_oma.class).getResultList();
	}

	public void salvarNovoFuncionario(intra_funcionario_oma funcionario) {
		this.manager.persist(funcionario);
		this.manager.flush();
	}

	public void salvarAlteracoesFuncionario(intra_funcionario_oma funcionarioSelecionado) {
		this.manager.merge(funcionarioSelecionado);
		this.manager.flush();
	}

	public void excluirFuncionario(intra_funcionario_oma funcionarioSelecionado) {
		funcionarioSelecionado = this.manager.merge(funcionarioSelecionado);
		this.manager.remove(funcionarioSelecionado);
	}

}
