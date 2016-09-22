package br.com.oma.intranet.interfaces;

import javax.persistence.EntityManager;

public interface Conexao {

	EntityManager getConexao();
}
