package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import br.com.oma.intranet.entidades.SeguroConteudo;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.util.JPAUtil;

@Stateless
public class SeguroConteudoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8239338268587801066L;
	private EntityManager manager;
	private Query query;
	private Connection con;

	public SeguroConteudoDAO() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		this.manager = JPAUtil.getManager();
		this.con = Conexao.getConexaoSiga();
	}

	public void fechaConexao() throws SQLException {
		this.con.close();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager
				.createQuery("from intra_condominios where codigoGerente = :p1 or codigo = 1 order by codigo");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> getListaCondominios() {
		this.query = this.manager.createQuery("from intra_condominios ");
		return this.query.getResultList();
	}

	public String retornaIdUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		return (String) session.getAttribute("usuario");
	}

	@SuppressWarnings("unchecked")
	public List<SeguroConteudo> listarSeguroCondomino(Date dataInicial, Date dataFinal,
			List<intra_condominios> condominio) {
		List<SeguroConteudo> listaSeguro = new ArrayList<SeguroConteudo>();
		for (intra_condominios cnd : condominio) {
			this.query = this.manager.createNativeQuery(
					"select r.recibo, r.vencto, s.servico,s.valor_recbto,u.condominio, u.bloco, u.unidade, u.classificacao, "
							+ "i.nome, c.logradouro, c.endereco, c.nro_end, c.complemento, "
							+ "c.bairro, c.cep, c.cidade, c.estado, c.data_baixa from sigadm.dbo.cndserec s "
							+ "left join sigadm.dbo.cndrecib r on s.recibo = r.recibo "
							+ "inner join sigadm.dbo.cndcondo c on r.condominio = c.codigo "
							+ "inner join sigadm.dbo.cndunida u on c.codigo = u.condominio "
							+ "inner join sigadm.dbo.ilclient i on u.cod_cliente = i.codigo "
							+ "where r.vencto between :p1 and :p2 and s.data_recbto <= GETDATE() "
							+ "and (s.servico = 8 or s.servico = 9 or s.servico = 10 or s.servico = 11) "
							+ "and r.data_recbto is not null and s.data_recbto is not null "
							+ "and r.condominio = :p3 and r.bloco = u.bloco "
							+ "and r.unidade = u.unidade order by s.servico, u.classificacao, r.condominio, r.bloco, r.unidade ");
			this.query.setParameter("p1", dataInicial);
			this.query.setParameter("p2", dataFinal);
			this.query.setParameter("p3", cnd.getCodigo());

			List<Object[]> lista = this.query.getResultList();
			if (!lista.isEmpty()) {
				for (Object[] obj : lista) {
					SeguroConteudo seguro = new SeguroConteudo();
					if (obj[0] != null) {
						seguro.setRECIBO(Integer.valueOf(obj[0].toString()));
					}
					if (obj[1] != null) {
						seguro.setVENCTO((Date) (obj[1]));
					}
					if (obj[2] != null) {
						seguro.setServico(Integer.valueOf(obj[2].toString()));
					}
					if (obj[3] != null) {
						seguro.setValor_recbto(Double.valueOf(obj[3].toString()));
					}
					if (obj[4] != null) {
						seguro.setCondominio(Integer.valueOf(obj[4].toString()));
					}
					if (obj[5] != null) {
						seguro.setBloco(obj[5].toString());
					}
					if (obj[6] != null) {
						seguro.setUnidade(obj[6].toString());
					}
					if (obj[7] != null) {
						seguro.setClassificacao(Integer.valueOf(obj[7].toString()));
					}
					if (obj[8] != null) {
						seguro.setNome(obj[8].toString());
					}
					if (obj[9] != null) {
						seguro.setLogradouro(obj[9].toString());
					}
					if (obj[10] != null) {
						seguro.setEndereco(obj[10].toString());
					}
					if (obj[11] != null) {
						seguro.setNro_end(obj[11].toString());
					}
					if (obj[12] != null) {
						seguro.setComplemento(obj[12].toString());
					}
					if (obj[13] != null) {
						seguro.setBairro(obj[13].toString());
					}
					if (obj[14] != null) {
						seguro.setCep(Double.valueOf(obj[14].toString()));
					}
					if (obj[15] != null) {
						seguro.setCidade(obj[15].toString());
					}
					if (obj[16] != null) {
						seguro.setEstado(obj[16].toString());
					}
					if (obj[17] != null) {
						seguro.setData_baixa(obj[17].toString());
					}

					listaSeguro.add(seguro);
				}
			}
		}
		return listaSeguro;
	}

	@SuppressWarnings("unchecked")
	public List<SeguroConteudo> listarSeguroCondomino2(Date dataInicial, Date dataFinal,
			List<intra_condominios> condominio, int servico) {
		List<SeguroConteudo> listaSeguro = new ArrayList<SeguroConteudo>();
		for (intra_condominios cnd : condominio) {
			this.query = this.manager.createNativeQuery(
					"select r.recibo, r.vencto, s.servico,s.valor_recbto,u.condominio, u.bloco, u.unidade, u.classificacao, "
							+ "i.nome, c.logradouro, c.endereco, c.nro_end, c.complemento, "
							+ "c.bairro, c.cep, c.cidade, c.estado, c.data_baixa from sigadm.dbo.cndserec s "
							+ "left join sigadm.dbo.cndrecib r on s.recibo = r.recibo "
							+ "inner join sigadm.dbo.cndcondo c on r.condominio = c.codigo "
							+ "inner join sigadm.dbo.cndunida u on c.codigo = u.condominio "
							+ "inner join sigadm.dbo.ilclient i on u.cod_cliente = i.codigo "
							+ "where r.vencto between :p1 and :p2 and s.data_recbto <= GETDATE() "
							+ "and (s.servico = 8 or s.servico = 9 or s.servico = 10 or s.servico = 11) "
							+ "and r.data_recbto is not null and s.data_recbto is not null "
							+ "and r.condominio = :p3 and r.bloco = u.bloco and s.servico = :p4 "
							+ "and r.unidade = u.unidade order by s.servico, u.classificacao, r.condominio, r.bloco, r.unidade ");
			this.query.setParameter("p1", dataInicial);
			this.query.setParameter("p2", dataFinal);
			this.query.setParameter("p3", cnd.getCodigo());
			this.query.setParameter("p4", servico);
			List<Object[]> lista = this.query.getResultList();
			if (!lista.isEmpty()) {
				for (Object[] obj : lista) {
					SeguroConteudo seguro = new SeguroConteudo();
					if (obj[0] != null) {
						seguro.setRECIBO(Integer.valueOf(obj[0].toString()));
					}
					if (obj[1] != null) {
						seguro.setVENCTO((Date) (obj[1]));
					}
					if (obj[2] != null) {
						seguro.setServico(Integer.valueOf(obj[2].toString()));
					}
					if (obj[3] != null) {
						seguro.setValor_recbto(Double.valueOf(obj[3].toString()));
					}
					if (obj[4] != null) {
						seguro.setCondominio(Integer.valueOf(obj[4].toString()));
					}
					if (obj[5] != null) {
						seguro.setBloco(obj[5].toString());
					}
					if (obj[6] != null) {
						seguro.setUnidade(obj[6].toString());
					}
					if (obj[7] != null) {
						seguro.setClassificacao(Integer.valueOf(obj[7].toString()));
					}
					if (obj[8] != null) {
						seguro.setNome(obj[8].toString());
					}
					if (obj[9] != null) {
						seguro.setLogradouro(obj[9].toString());
					}
					if (obj[10] != null) {
						seguro.setEndereco(obj[10].toString());
					}
					if (obj[11] != null) {
						seguro.setNro_end(obj[11].toString());
					}
					if (obj[12] != null) {
						seguro.setComplemento(obj[12].toString());
					}
					if (obj[13] != null) {
						seguro.setBairro(obj[13].toString());
					}
					if (obj[14] != null) {
						seguro.setCep(Double.valueOf(obj[14].toString()));
					}
					if (obj[15] != null) {
						seguro.setCidade(obj[15].toString());
					}
					if (obj[16] != null) {
						seguro.setEstado(obj[16].toString());
					}
					if (obj[17] != null) {
						seguro.setData_baixa(obj[17].toString());
					}

					listaSeguro.add(seguro);
				}
			}
		}
		return listaSeguro;
	}
	
	@SuppressWarnings("unchecked")
	public List<SeguroConteudo> listarSeguroCondomino3(Date dataInicial, Date dataFinal,
			List<intra_condominios> condominio, int servico, int tipo) {
		List<SeguroConteudo> listaSeguro = new ArrayList<SeguroConteudo>();
		for (intra_condominios cnd : condominio) {
			this.query = this.manager.createNativeQuery(
					"select r.recibo, r.vencto, s.servico,s.valor_recbto,u.condominio, u.bloco, u.unidade, u.classificacao, "
							+ "i.nome, c.logradouro, c.endereco, c.nro_end, c.complemento, "
							+ "c.bairro, c.cep, c.cidade, c.estado, c.data_baixa from sigadm.dbo.cndserec s "
							+ "left join sigadm.dbo.cndrecib r on s.recibo = r.recibo "
							+ "inner join sigadm.dbo.cndcondo c on r.condominio = c.codigo "
							+ "inner join sigadm.dbo.cndunida u on c.codigo = u.condominio "
							+ "inner join sigadm.dbo.ilclient i on u.cod_cliente = i.codigo "
							+ "where r.vencto between :p1 and :p2 and s.data_recbto <= GETDATE() "
							+ "and (s.servico = 8 or s.servico = 9 or s.servico = 10 or s.servico = 11) "
							+ "and r.data_recbto is not null and s.data_recbto is not null "
							+ "and r.condominio = :p3 and r.bloco = u.bloco and s.servico = :p4 and u.classificacao = :p5 "
							+ "and r.unidade = u.unidade order by s.servico, u.classificacao, r.condominio, r.bloco, r.unidade ");
			this.query.setParameter("p1", dataInicial);
			this.query.setParameter("p2", dataFinal);
			this.query.setParameter("p3", cnd.getCodigo());
			this.query.setParameter("p4", servico);
			this.query.setParameter("p5", tipo);
			List<Object[]> lista = this.query.getResultList();
			if (!lista.isEmpty()) {
				for (Object[] obj : lista) {
					SeguroConteudo seguro = new SeguroConteudo();
					if (obj[0] != null) {
						seguro.setRECIBO(Integer.valueOf(obj[0].toString()));
					}
					if (obj[1] != null) {
						seguro.setVENCTO((Date) (obj[1]));
					}
					if (obj[2] != null) {
						seguro.setServico(Integer.valueOf(obj[2].toString()));
					}
					if (obj[3] != null) {
						seguro.setValor_recbto(Double.valueOf(obj[3].toString()));
					}
					if (obj[4] != null) {
						seguro.setCondominio(Integer.valueOf(obj[4].toString()));
					}
					if (obj[5] != null) {
						seguro.setBloco(obj[5].toString());
					}
					if (obj[6] != null) {
						seguro.setUnidade(obj[6].toString());
					}
					if (obj[7] != null) {
						seguro.setClassificacao(Integer.valueOf(obj[7].toString()));
					}
					if (obj[8] != null) {
						seguro.setNome(obj[8].toString());
					}
					if (obj[9] != null) {
						seguro.setLogradouro(obj[9].toString());
					}
					if (obj[10] != null) {
						seguro.setEndereco(obj[10].toString());
					}
					if (obj[11] != null) {
						seguro.setNro_end(obj[11].toString());
					}
					if (obj[12] != null) {
						seguro.setComplemento(obj[12].toString());
					}
					if (obj[13] != null) {
						seguro.setBairro(obj[13].toString());
					}
					if (obj[14] != null) {
						seguro.setCep(Double.valueOf(obj[14].toString()));
					}
					if (obj[15] != null) {
						seguro.setCidade(obj[15].toString());
					}
					if (obj[16] != null) {
						seguro.setEstado(obj[16].toString());
					}
					if (obj[17] != null) {
						seguro.setData_baixa(obj[17].toString());
					}

					listaSeguro.add(seguro);
				}
			}
		}
		return listaSeguro;
	}
	@SuppressWarnings("unchecked")
	public List<SeguroConteudo> listaSeguro8888(Date dataInicial, Date dataFinal) {
		List<SeguroConteudo> listaSeguro = new ArrayList<SeguroConteudo>();
		this.query = this.manager.createNativeQuery(
				"select r.recibo, r.valor_recebido, u.condominio, u.bloco, u.unidade, u.classificacao, "
						+ "i.nome, e.logradouro, e.endereco, e.nro_end, e.complemento, e.bairro, e.cep, e.cidade, "
						+ "e.estado, c.data_baixa from sigadm.dbo.cndrecib r "
						+ "inner join sigadm.dbo.cndcondo c on r.condominio = c.codigo "
						+ "inner join sigadm.dbo.cndunida u on c.codigo = u.condominio "
						+ "inner join sigadm.dbo.ilclient i on u.cod_cliente = i.codigo "
						+ "inner join sigadm.dbo.cndcondo e on u.bloco = e.codigo "
						+ "where r.condominio = 8888 and r.vencto between :p1 and :p2 and "
						+ "flag_situacao <> 9 and u.bloco <> 'SAIU' and r.data_recbto is not null and "
						+ "r.condominio = u.condominio and r.bloco = u.bloco and r.unidade = u.unidade "
						+ "order by u.classificacao, r.valor_recebido, r.condominio, r.bloco, r.unidade");
		this.query.setParameter("p1", dataInicial);
		this.query.setParameter("p2", dataFinal);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				SeguroConteudo seguro = new SeguroConteudo();
				if (obj[0] != null) {
					seguro.setRECIBO(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					seguro.setValor_recbto(Double.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					seguro.setCondominio(Integer.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					seguro.setBloco(obj[3].toString());
				}
				if (obj[4] != null) {
					seguro.setUnidade(obj[4].toString());
				}
				if (obj[5] != null) {
					seguro.setClassificacao(Integer.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					seguro.setNome(obj[6].toString());
				}
				if (obj[7] != null) {
					seguro.setLogradouro(obj[7].toString());
				}
				if (obj[8] != null) {
					seguro.setEndereco(obj[8].toString());
				}
				if (obj[9] != null) {
					seguro.setNro_end(obj[9].toString());
				}
				if (obj[10] != null) {
					seguro.setComplemento(obj[10].toString());
				}
				if (obj[11] != null) {
					seguro.setBairro(obj[11].toString());
				}
				if (obj[12] != null) {
					seguro.setCep(Double.valueOf(obj[12].toString()));
				}
				if (obj[13] != null) {
					seguro.setCidade(obj[13].toString());
				}
				if (obj[14] != null) {
					seguro.setEstado(obj[14].toString());
				}
				if (obj[15] != null) {
					seguro.setData_baixa(obj[15].toString());
				}

				listaSeguro.add(seguro);
			}
		}

		return listaSeguro;
	}

	@SuppressWarnings("unchecked")
	public List<SeguroConteudo> listaSeguro88882(Date dataInicial, Date dataFinal,double valor) {
		List<SeguroConteudo> listaSeguro = new ArrayList<SeguroConteudo>();
		this.query = this.manager.createNativeQuery(
				"select r.recibo, r.valor_recebido, u.condominio, u.bloco, u.unidade, u.classificacao, "
						+ "i.nome, e.logradouro, e.endereco, e.nro_end, e.complemento, e.bairro, e.cep, e.cidade, "
						+ "e.estado, c.data_baixa from sigadm.dbo.cndrecib r "
						+ "inner join sigadm.dbo.cndcondo c on r.condominio = c.codigo "
						+ "inner join sigadm.dbo.cndunida u on c.codigo = u.condominio "
						+ "inner join sigadm.dbo.ilclient i on u.cod_cliente = i.codigo "
						+ "inner join sigadm.dbo.cndcondo e on u.bloco = e.codigo "
						+ "where r.condominio = 8888 and r.vencto between :p1 and :p2 and r.valor_recebido = :p3 and "
						+ "flag_situacao <> 9 and u.bloco <> 'SAIU' and r.data_recbto is not null and "
						+ "r.condominio = u.condominio and r.bloco = u.bloco and r.unidade = u.unidade "
						+ "order by u.classificacao, r.valor_recebido, r.condominio, r.bloco, r.unidade");
		this.query.setParameter("p1", dataInicial);
		this.query.setParameter("p2", dataFinal);
		this.query.setParameter("p3", valor);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				SeguroConteudo seguro = new SeguroConteudo();
				if (obj[0] != null) {
					seguro.setRECIBO(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					seguro.setValor_recbto(Double.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					seguro.setCondominio(Integer.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					seguro.setBloco(obj[3].toString());
				}
				if (obj[4] != null) {
					seguro.setUnidade(obj[4].toString());
				}
				if (obj[5] != null) {
					seguro.setClassificacao(Integer.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					seguro.setNome(obj[6].toString());
				}
				if (obj[7] != null) {
					seguro.setLogradouro(obj[7].toString());
				}
				if (obj[8] != null) {
					seguro.setEndereco(obj[8].toString());
				}
				if (obj[9] != null) {
					seguro.setNro_end(obj[9].toString());
				}
				if (obj[10] != null) {
					seguro.setComplemento(obj[10].toString());
				}
				if (obj[11] != null) {
					seguro.setBairro(obj[11].toString());
				}
				if (obj[12] != null) {
					seguro.setCep(Double.valueOf(obj[12].toString()));
				}
				if (obj[13] != null) {
					seguro.setCidade(obj[13].toString());
				}
				if (obj[14] != null) {
					seguro.setEstado(obj[14].toString());
				}
				if (obj[15] != null) {
					seguro.setData_baixa(obj[15].toString());
				}

				listaSeguro.add(seguro);
			}
		}

		return listaSeguro;
	}

	@SuppressWarnings("unchecked")
	public List<SeguroConteudo> listaSeguroEmbutido() {
		List<SeguroConteudo> listaSeguro = new ArrayList<SeguroConteudo>();
		this.query = this.manager.createNativeQuery(
				"select C.codigo, C.nome as nomeCondominio, I.nome as nomeUnidade,u.classificacao,c.logradouro,c.endereco,c.nro_end,u.bloco, "
						+ "u.unidade,c.bairro, c.cep,c.cidade,c.estado from sigadm.dbo.cndunida u "
						+ "inner join sigadm.dbo.cndcondo c on u.condominio = c.codigo "
						+ "inner join sigadm.dbo.ilclient i on u.cod_cliente = i.codigo " + "where u.condominio = 1125 "
						+ "or u.condominio = 1130 " + "or u.condominio = 1132 " + "or u.condominio = 1134 "
						+ "or u.condominio = 1145 " + "or u.condominio = 1208 "
						+ "order by u.classificacao, u.condominio, u.bloco, u.unidade ");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				SeguroConteudo seguro = new SeguroConteudo();
				if (obj[0] != null) {
					seguro.setCondominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					seguro.setNomeCondominio(obj[1].toString());
				}
				if (obj[2] != null) {
					seguro.setNome(obj[2].toString());
				}
				if (obj[3] != null) {
					seguro.setClassificacao(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					seguro.setLogradouro(obj[4].toString());
				}
				if (obj[5] != null) {
					seguro.setEndereco(obj[5].toString());
				}
				if (obj[6] != null) {
					seguro.setNro_end(obj[6].toString());
				}
				if (obj[7] != null) {
					seguro.setBloco(obj[7].toString());
				}
				if (obj[8] != null) {
					seguro.setUnidade(obj[8].toString());
				}
				if (obj[9] != null) {
					seguro.setBairro(obj[9].toString());
				}
				if (obj[10] != null) {
					seguro.setCep(Double.valueOf(obj[10].toString()));
				}
				if (obj[11] != null) {
					seguro.setCidade(obj[11].toString());
				}
				if (obj[12] != null) {
					seguro.setEstado(obj[12].toString());
				}
				listaSeguro.add(seguro);
			}
		}
		return listaSeguro;
	}
	
	@SuppressWarnings("unchecked")
	public List<SeguroConteudo> listaSeguroEmbutido2(int condominio) {
		List<SeguroConteudo> listaSeguro = new ArrayList<SeguroConteudo>();
		this.query = this.manager.createNativeQuery(
				"select C.codigo, C.nome as nomeCondominio, I.nome as nomeUnidade,u.classificacao,c.logradouro,c.endereco,c.nro_end,u.bloco, "
						+ "u.unidade,c.bairro, c.cep,c.cidade,c.estado from sigadm.dbo.cndunida u "
						+ "inner join sigadm.dbo.cndcondo c on u.condominio = c.codigo "
						+ "inner join sigadm.dbo.ilclient i on u.cod_cliente = i.codigo " + "where u.condominio = :p1 "
						+ "order by u.classificacao, u.condominio, u.bloco, u.unidade ");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				SeguroConteudo seguro = new SeguroConteudo();
				if (obj[0] != null) {
					seguro.setCondominio(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					seguro.setNomeCondominio(obj[1].toString());
				}
				if (obj[2] != null) {
					seguro.setNome(obj[2].toString());
				}
				if (obj[3] != null) {
					seguro.setClassificacao(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					seguro.setLogradouro(obj[4].toString());
				}
				if (obj[5] != null) {
					seguro.setEndereco(obj[5].toString());
				}
				if (obj[6] != null) {
					seguro.setNro_end(obj[6].toString());
				}
				if (obj[7] != null) {
					seguro.setBloco(obj[7].toString());
				}
				if (obj[8] != null) {
					seguro.setUnidade(obj[8].toString());
				}
				if (obj[9] != null) {
					seguro.setBairro(obj[9].toString());
				}
				if (obj[10] != null) {
					seguro.setCep(Double.valueOf(obj[10].toString()));
				}
				if (obj[11] != null) {
					seguro.setCidade(obj[11].toString());
				}
				if (obj[12] != null) {
					seguro.setEstado(obj[12].toString());
				}
				listaSeguro.add(seguro);
			}
		}
		return listaSeguro;
	}

	@SuppressWarnings("unchecked")
	public List<SeguroConteudo> listaSeguroLocacao(int mes, int ano) {
		List<SeguroConteudo> listaSeguro = new ArrayList<SeguroConteudo>();
		this.query = this.manager.createNativeQuery(
				"select t.numero, i.locador_princ , p.nome as locador, t.inqui_princ, e.nome as inquilino, i.codigo, i.logradouro, "
						+ "i.endereco, i.nro_end, i.complemento, i.bairro, i.cep, i.cidade, i.estado "
						+ "from sigadm.dbo.ilctacor c " + "inner join sigadm.dbo.ilrecibo r on c.recibo = r.recibo "
						+ "inner join sigadm.dbo.ilimovel i on c.imovel = i.codigo "
						+ "inner join sigadm.dbo.ilcotrto t on c.contrato = t.numero "
						+ "inner join sigadm.dbo.ilclient e on t.inqui_princ = e.codigo "
						+ "inner join sigadm.dbo.ilocador l on i.locador_princ = l.codigo "
						+ "inner join sigadm.dbo.ilclient p on l.cliente = p.codigo "
						+ "where c.conta = 5090 and r.mes_compt = :p1 and r.ano_compt = :p2 and c.flag_divisao not like 'P' order by i.locador_princ ");
		this.query.setParameter("p1", mes);
		this.query.setParameter("p2", ano);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				SeguroConteudo seg = new SeguroConteudo();
				if (obj[0] != null) {
					seg.setNumeroLoc(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					seg.setLocador_princLoc(Integer.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					seg.setLocador(obj[2].toString());
				}
				if (obj[3] != null) {
					seg.setInqui_princLoc(Integer.valueOf(obj[3].toString()));
				}
				if (obj[4] != null) {
					seg.setInquilino(obj[4].toString());
				}
				if (obj[5] != null) {
					seg.setCodigo(Integer.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					seg.setLogradouroLoc(obj[6].toString());
				}
				if (obj[7] != null) {
					seg.setEnderecoLoc(obj[7].toString());
				}
				if (obj[8] != null) {
					seg.setNro_endLoc(obj[8].toString());
				}
				if (obj[9] != null) {
					seg.setComplementoLoc(obj[9].toString());
				}
				if (obj[10] != null) {
					seg.setBairroLoc(obj[10].toString());
				}
				if (obj[11] != null) {
					seg.setCepLoc(Double.valueOf(obj[11].toString()));
				}
				if (obj[12] != null) {
					seg.setCidadeLoc(obj[12].toString());
				}
				if (obj[13] != null) {
					seg.setEstadoLoc(obj[13].toString());
				}
				listaSeguro.add(seg);
			}
		}
		return listaSeguro;
	}

}
