package br.com.oma.intranet.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.oma.intranet.entidades.ilclient;
import br.com.oma.intranet.entidades.iltelcli;
import br.com.oma.intranet.entidades.iltelcli_pk;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.omaonline.entidades.cndunida;

public class RecadastramentoDAO {

	private EntityManager manager;
	private Query query;

	public RecadastramentoDAO() {
		this.manager = JPAUtil.getManager();
	}

	public List<ilclient> getListaClientes() {
		TypedQuery<ilclient> query = this.manager.createQuery(
				"from ilclient order by dataRecadastramento", ilclient.class);
		return query.getResultList();
	}

	public cndunida pesquisaUnidadeCliente(Integer codigo) {
		try {
			cndunida unidade = null;
			Connection con = Conexao.getConexaoSiga();
			String query = "select condominio, bloco, unidade from cndunida where cod_cliente = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, codigo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				unidade = new cndunida();
				unidade.setCodigo_cnd(rs.getShort("condominio"));
				unidade.setBloco(rs.getString("bloco"));
				unidade.setUnidade(rs.getString("unidade"));
			}
			rs.close();
			stmt.close();
			con.close();
			if (unidade != null) {
				return unidade;
			} else {
				return new cndunida();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new cndunida();
	}

	public ilclient pesquisaCliente(Integer codigo) {
		try {
			ilclient cliente = null;
			Connection con = Conexao.getConexaoSiga();
			String query = "select codigo, nome, aos_cuidados, logradouro, endereco, complemento, bairro, cidade, estado, cep, tipo_pessoa, cnpj_cpf, data_nascimento, nro_dependentes, data_cadastro,"
					+ "profissao, nacionalidade, tipo_identidade, nro_identidade, orgao_emissor, data_emissao, estado_civil, observacao, qt_contacor, qt_inquilino, qt_fiador, er_logradouro,"
					+ "er_endereco, er_complemento, er_bairro, er_cidade, er_estado, er_cep, cj_nome, cj_cpf, cj_data_nascto, cj_profissao, cj_nacionalid, cj_rg, cj_org_emissor,cj_data_emissao,"
					+ "end_corresp, imp_etiq, qt_condomino, er_aos_cuidados, reside_exterior, estrangeiro, nro_end, er_nro_end, dt_alteracao, usuario, data_alteracao, religiao, sexo, dia_ani,"
					+ "mes_ani, ano_ani, recolhe_inss, valor_inss, inscr_pis, situacao_prof, dt_inscr_inss, dt_inscr_pis, cartorio_1, cartorio_2, qt_conselho, exterior, pais, cod_postal, er_exterior,"
					+ "er_pais, er_cod_postal, enviado, cod_gosati, cod_pais, municipio from ilclient "
					+ "where codigo = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, codigo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cliente = new ilclient();
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCnpj_cpf(rs.getLong("cnpj_cpf"));
				cliente.setData_nascimento(rs.getDate("data_nascimento"));
				cliente.setProfissao(rs.getString("profissao"));
				cliente.setNro_identidade(rs.getString("nro_identidade"));
				cliente.setDia_ani(rs.getShort("dia_ani"));
				cliente.setMes_ani(rs.getShort("mes_ani"));
				cliente.setAno_ani(rs.getShort("ano_ani"));
			}
			if (cliente != null) {
				return cliente;
			} else {
				return new ilclient();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ilclient();
	}

	public List<iltelcli> pesquisaContatoClienteAntigo(int codigoCliente) {
		List<iltelcli> listaContatos = new ArrayList<>();
		iltelcli_pk contatoPK = null;
		iltelcli contato = null;
		try {
			Connection con = Conexao.getConexaoSiga();
			String query = "select cliente, tipo, flag_cadastro, dfrecnum, tel_email, observacao, boleto_envio, envio_pc, envio_carta, envio_sms from iltelcli where cliente = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, codigoCliente);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				contatoPK = new iltelcli_pk(rs.getInt("cliente"),
						rs.getShort("tipo"), rs.getShort("flag_cadastro"),
						rs.getInt("dfrecnum"));
				contato = new iltelcli(contatoPK, rs.getString("tel_email"),
						rs.getString("observacao"),
						rs.getShort("boleto_envio"), rs.getBoolean("envio_pc"),
						rs.getBoolean("envio_carta"), rs.getShort("envio_sms"));
				listaContatos.add(contato);
			}
			return listaContatos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<iltelcli> pesquisaContatoCliente(int codigoCliente) {
		// PESQUISA UNIDADE
		Query query = this.manager
				.createQuery("from iltelcli where cliente = :p1");
		query.setParameter("p1", codigoCliente);
		return query.getResultList();
	}

	public void aprovar(ilclient clienteSelecionado, iltelcli telefoneCelular,
			iltelcli telefoneComercial, iltelcli telefoneResidencial,
			iltelcli email) throws Exception {
		try {
			Connection con = Conexao.getConexaoSiga();
			String query = "update ilclient set nome = ?, cnpj_cpf = ?, data_nascimento = ?, profissao = ?, nro_identidade = ?, dia_ani = ?, mes_ani = ?, ano_ani = ? where codigo = ? ";
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, clienteSelecionado.getNome());

			if (clienteSelecionado.getCnpj_cpf() != null) {
				stmt.setLong(2, clienteSelecionado.getCnpj_cpf());
			} else {
				stmt.setLong(2, 0);
			}

			if (clienteSelecionado.getData_nascimento() != null) {
				stmt.setDate(3, new java.sql.Date(clienteSelecionado
						.getData_nascimento().getTime()));
			} else {
				stmt.setDate(3, null);
			}

			stmt.setString(4, clienteSelecionado.getProfissao());

			stmt.setString(5, clienteSelecionado.getNro_identidade());

			if (clienteSelecionado.getDia_ani() != null) {
				stmt.setByte(6, Byte.parseByte(String
						.valueOf(clienteSelecionado.getDia_ani())));
			} else {
				stmt.setByte(6, (byte) 0);
			}

			if (clienteSelecionado.getMes_ani() != null) {
				stmt.setByte(7, Byte.parseByte(String
						.valueOf(clienteSelecionado.getMes_ani())));
			} else {
				stmt.setByte(7, (byte) 0);
			}

			if (clienteSelecionado.getAno_ani() != null) {
				stmt.setShort(8, clienteSelecionado.getAno_ani());
			} else {
				stmt.setShort(8, (short) 0);
			}

			if (clienteSelecionado.getCodigo() != null) {
				stmt.setInt(9, clienteSelecionado.getCodigo());
			} else {
				throw new Exception("Erro! O código do cliente está zerado!");
			}

			stmt.executeUpdate();

			con = Conexao.getConexaoSiga();
			query = "delete iltelcli where cliente = ?";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, clienteSelecionado.getCodigo());
			stmt.executeUpdate();

			clienteSelecionado = this.manager.merge(clienteSelecionado);
			this.manager.remove(clienteSelecionado);

			if (telefoneCelular != null && telefoneCelular.getId() != null
					&& telefoneCelular.getTel_email() != null
					&& !telefoneCelular.getTel_email().isEmpty()) {
				telefoneCelular = this.manager.merge(telefoneCelular);
				this.manager.remove(telefoneCelular);
				atualizarContato(telefoneCelular);
			}

			if (telefoneComercial != null && telefoneComercial.getId() != null
					&& telefoneComercial.getTel_email() != null
					&& !telefoneComercial.getTel_email().isEmpty()) {
				telefoneComercial = this.manager.merge(telefoneComercial);
				this.manager.remove(telefoneComercial);
				atualizarContato(telefoneComercial);
			}

			if (telefoneResidencial != null
					&& telefoneResidencial.getId() != null
					&& telefoneResidencial.getTel_email() != null
					&& !telefoneResidencial.getTel_email().isEmpty()) {
				telefoneResidencial = this.manager.merge(telefoneResidencial);
				this.manager.remove(telefoneResidencial);
				atualizarContato(telefoneResidencial);
			}

			if (email != null && email.getId() != null
					&& email.getTel_email() != null
					&& !email.getTel_email().isEmpty()) {
				email = this.manager.merge(email);
				this.manager.remove(email);
				atualizarContato(email);
			}

			// SOMA QTD DE RECADASTRAMENTOS APROVADOS NO CONTADOR
			this.manager
					.createNativeQuery(
							"update intra_recadastramento_contador set aprovados = aprovados + 1")
					.executeUpdate();

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizarContato(iltelcli tel) {
		try {
			this.query = this.manager
					.createNativeQuery("insert into sigadm.dbo.iltelcli(boleto_envio, envio_carta, envio_pc, envio_sms, observacao, tel_email, cliente, tipo, flag_cadastro) "
							+ "values (:p1,:p2,:p3,:p4,:p5,:p6,:p7,:p8,:p9)");

			if (tel.getBoleto_envio() != null) {
				this.query.setParameter("p1", tel.getBoleto_envio());
			} else {
				this.query.setParameter("p1", (short) 0);
			}

			if (tel.getEnvio_carta() != null) {
				this.query.setParameter("p2", tel.getEnvio_carta());
			} else {
				this.query.setParameter("p2", false);
			}

			if (tel.getEnvio_pc() != null) {
				this.query.setParameter("p3", tel.getEnvio_pc());
			} else {
				this.query.setParameter("p3", false);
			}

			if (tel.getEnvio_sms() != null) {
				this.query.setParameter("p4", tel.getEnvio_sms());
			} else {
				this.query.setParameter("p4", (short) 0);
			}
			this.query.setParameter("p5", tel.getObservacao());
			this.query.setParameter("p6", tel.getTel_email());

			if (tel.getId().getCliente() != null) {

				this.query.setParameter("p7", tel.getId().getCliente());
			} else {
				this.query.setParameter("p7", 0);
			}

			if (tel.getId().getTipo() != null) {
				this.query.setParameter("p8", tel.getId().getTipo());
			} else {
				this.query.setParameter("p8", 0);
			}

			if (tel.getId().getFlag_cadastro() != null) {
				this.query.setParameter("p9", tel.getId().getFlag_cadastro());
			} else {
				this.query.setParameter("p9", (short) 0);
			}

			this.query.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reprovar(ilclient clienteSelecionado, iltelcli telefoneCelular,
			iltelcli telefoneComercial, iltelcli telefoneResidencial,
			iltelcli email) {
		clienteSelecionado = this.manager.merge(clienteSelecionado);
		this.manager.remove(clienteSelecionado);

		// SOMA QTD DE RECADASTRAMENTOS REPROVADOS NO CONTADOR
		this.manager
				.createNativeQuery(
						"update intra_recadastramento_contador set reprovados = reprovados + 1")
				.executeUpdate();

		if (telefoneCelular != null && telefoneCelular.getId() != null) {
			telefoneCelular = this.manager.merge(telefoneCelular);
			this.manager.remove(telefoneCelular);
		}

		if (telefoneComercial != null && telefoneComercial.getId() != null) {
			telefoneComercial = this.manager.merge(telefoneComercial);
			this.manager.remove(telefoneComercial);
		}

		if (telefoneResidencial != null && telefoneResidencial.getId() != null) {
			telefoneResidencial = this.manager.merge(telefoneResidencial);
			this.manager.remove(telefoneResidencial);
		}

		if (email != null && email.getId() != null) {
			email = this.manager.merge(email);
			this.manager.remove(email);
		}
	}

	@SuppressWarnings("unchecked")
	public int getAprovados() {
		int aprovados = 0;
		Query query = this.manager
				.createNativeQuery("select aprovados from intra_recadastramento_contador");
		List<Integer> listaRetorno = query.getResultList();
		for (int aux : listaRetorno) {
			aprovados = aux;
		}
		return aprovados;
	}

	@SuppressWarnings("unchecked")
	public int getReprovados() {
		int reprovados = 0;
		Query query = this.manager
				.createNativeQuery("select reprovados from intra_recadastramento_contador");
		List<Integer> listaRetorno = query.getResultList();
		for (int aux : listaRetorno) {
			reprovados = aux;
		}
		return reprovados;
	}

}
