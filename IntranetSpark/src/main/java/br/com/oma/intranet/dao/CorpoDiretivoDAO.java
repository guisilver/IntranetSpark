package br.com.oma.intranet.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_corpo_diretivo;
import br.com.oma.intranet.entidades.intra_log_geral;
import br.com.oma.intranet.filters.Conexao;
import br.com.oma.intranet.managedbeans.SessaoMB;
import br.com.oma.intranet.util.JPAUtil;
import br.com.oma.sigadm.entidades.intra_atbtifon;
import br.com.oma.sigadm.entidades.intra_cndbloco;
import br.com.oma.sigadm.entidades.intra_cndcargo;
import br.com.oma.sigadm.entidades.intra_cndmemb;
import br.com.oma.sigadm.entidades.intra_ilclient;
import br.com.oma.sigadm.entidades.intra_ilmunic;
import br.com.oma.sigadm.entidades.intra_ilograd;
import br.com.oma.sigadm.entidades.intra_iltelcli;

public class CorpoDiretivoDAO extends LogGeralDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8256987118472339577L;
	private EntityManager manager;
	private Query query;
	private intra_log_geral ilg = new intra_log_geral();
	private Date data;

	private PreparedStatement pmst;
	private Connection con;

	public CorpoDiretivoDAO() {
		this.manager = JPAUtil.getManager();
	}

	@SuppressWarnings("unchecked")
	public List<intra_condominios> listarCondominios(int codigo) {
		this.query = this.manager.createQuery("from intra_condominios where codigoGerente = :p1");
		this.query.setParameter("p1", codigo);
		return this.query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public intra_cndbloco listarCndBloco(int condominio) {
		intra_cndbloco cndbloco = new intra_cndbloco();
		this.query = this.manager.createNativeQuery("select inicio_mandato, final_mandato,"
				+ " prazo_assembl, quorum_primeira, segunda_chamada, quorum_segunda, dt_aprovacao, prx_aprovacao, obs_1  "
				+ "From sigadm.dbo.cndbloco where condominio = :p1");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				cndbloco.setCondominio(condominio);
				if (obj[0] != null) {
					cndbloco.setInicioMandato((Date) obj[0]);
				}
				if (obj[1] != null) {
					cndbloco.setFinalMandato((Date) obj[1]);
				}
				if (obj[2] != null) {
					cndbloco.setPrazoAssembl(obj[2].toString());
				}
				if (obj[3] != null) {
					cndbloco.setQuorumPrimeira(obj[3].toString());
				}
				if (obj[4] != null) {
					cndbloco.setSegundaChamada(obj[4].toString());
				}
				if (obj[5] != null) {
					cndbloco.setQuorumSegunda(obj[5].toString());
				}
				if (obj[6] != null) {
					cndbloco.setDtAprovacao((Date) obj[6]);
				}
				if (obj[7] != null) {
					cndbloco.setPrxAprovacao((Date) obj[7]);
				}
				if (obj[8] != null) {
					cndbloco.setObs1(obj[8].toString());
				}
			}
		}

		return cndbloco;
	}

	public void atualizarDadosGerais(intra_cndbloco cndbloco) {
		String sql = "update sigadm.dbo.cndbloco set inicio_mandato = ?, final_mandato = ?, prazo_assembl = ?, "
						+ "quorum_primeira = ?, segunda_chamada = ?, quorum_segunda = ?, dt_aprovacao = ?, prx_aprovacao = ?, obs_1 = ? where condominio = ? ";
		
		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			if(cndbloco.getInicioMandato() != null){
				this.pmst.setDate(1, new java.sql.Date(cndbloco.getInicioMandato().getTime()));
			}else{
				this.pmst.setDate(1, null);
			}
			if(cndbloco.getFinalMandato() != null){
				this.pmst.setDate(2, new java.sql.Date(cndbloco.getFinalMandato().getTime()));
			}else{
				this.pmst.setDate(2, null);
			}
			this.pmst.setString(3, cndbloco.getPrazoAssembl());
			this.pmst.setString(4, cndbloco.getQuorumPrimeira());
			this.pmst.setString(5, cndbloco.getSegundaChamada());
			this.pmst.setString(6, cndbloco.getQuorumSegunda());
			if(cndbloco.getDtAprovacao() != null){
				this.pmst.setDate(7, new java.sql.Date(cndbloco.getDtAprovacao().getTime()));
			}else{
				this.pmst.setDate(7, null);
			}
			if(cndbloco.getPrxAprovacao() != null){
				this.pmst.setDate(8, new java.sql.Date(cndbloco.getPrxAprovacao().getTime()));
			}else{
				this.pmst.setDate(8, null);
			}
			this.pmst.setString(9, cndbloco.getObs1());
			this.pmst.setInt(10, cndbloco.getCondominio());
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_cndmemb> listarCndMemb(int condominio) {
		List<intra_cndmemb> membros = new ArrayList<intra_cndmemb>();
		this.query = this.manager.createNativeQuery(
				"select bloco, cargo, cliente, cli_bloco, cli_unidade, termo, situacao, cond_resp, valor_base, valor_outros "
						+ "from sigadm.dbo.cndmemb where condominio = :p1");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cndmemb m = new intra_cndmemb();
				m.setCondominio(condominio);
				if (obj[0] != null) {
					m.setBloco(obj[0].toString());
				}
				if (obj[1] != null) {
					m.setCargo(Integer.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					m.setCliente(Integer.valueOf(obj[2].toString()));
				}
				if (obj[3] != null) {
					m.setCliBloco(obj[3].toString());
				}
				if (obj[4] != null) {
					m.setCliUnidade(obj[4].toString());
				}
				if (obj[5] != null) {
					m.setTermo(Integer.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					m.setSituacao(Integer.valueOf(obj[6].toString()));
				}
				if (obj[7] != null) {
					m.setCondResp(Integer.valueOf(obj[7].toString()));
				}
				if (obj[8] != null) {
					m.setValorBase(Double.valueOf(obj[8].toString()));
				}
				if (obj[9] != null) {
					m.setValorOutros(Double.valueOf(obj[9].toString()));
				}
				membros.add(m);
			}
		}
		return membros;
	}

	@SuppressWarnings("unchecked")
	public intra_ilclient listarCliente(int cliente) {
		this.query = this.manager
				.createNativeQuery("select codigo, nome, logradouro, endereco, complemento, bairro, cidade, "
						+ "estado, cep, tipo_pessoa, cnpj_cpf, data_nascimento, profissao, nacionalidade, tipo_identidade, "
						+ "nro_identidade, orgao_emissor, estado_civil, end_corresp, imp_etiq, reside_exterior, nro_end,dt_alteracao, "
						+ "usuario, sexo, dia_ani, mes_ani, ano_ani, pais,  municipio, cartorio_1, cartorio_2 from sigadm.dbo.ilclient where codigo = :p1");
		this.query.setParameter("p1", cliente);
		intra_ilclient cli = new intra_ilclient();
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				if (obj[0] != null) {
					cli.setCodigo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					cli.setNome(obj[1].toString());
				}
				if (obj[2] != null) {
					cli.setLogradouro(obj[2].toString());
				}
				if (obj[3] != null) {
					cli.setEndereco(obj[3].toString());
				}
				if (obj[4] != null) {
					cli.setComplemento(obj[4].toString());
				}
				if (obj[5] != null) {
					cli.setBairro(obj[5].toString());
				}
				if (obj[6] != null) {
					cli.setCidade(obj[6].toString());
				}
				if (obj[7] != null) {
					cli.setEstado(obj[7].toString());
				}
				if (obj[8] != null) {
					cli.setCep(Double.valueOf(obj[8].toString()));
				}
				if (obj[9] != null) {
					cli.setTipoPessoa(obj[9].toString());
				}
				if (obj[10] != null) {
					cli.setCnpjCpf(Double.valueOf(obj[10].toString()));
				}
				if (obj[11] != null) {
					cli.setDataNascimento((Date) obj[11]);
				}
				if (obj[12] != null) {
					cli.setProfissao(obj[12].toString());
				}
				if (obj[13] != null) {
					cli.setNacionalidade(obj[13].toString());
				}
				if (obj[14] != null) {
					cli.setTipoIdentidade(obj[14].toString());
				}
				if (obj[15] != null) {
					cli.setNroIdentidade(obj[15].toString());
				}
				if (obj[16] != null) {
					cli.setOrgaoEmissor(obj[16].toString());
				}
				if (obj[17] != null) {
					cli.setEstadoCivil(Integer.valueOf(obj[17].toString()));
				}
				if (obj[18] != null) {
					cli.setEndCorresp(obj[18].toString());
				}
				if (obj[19] != null) {
					cli.setImpEtiq(obj[19].toString());
				}
				if (obj[20] != null) {
					cli.setResideExterior(obj[20].toString());
				}
				if (obj[21] != null) {
					cli.setNroEnd(obj[21].toString());
				}
				if (obj[22] != null) {
					cli.setDtAlteracao((Date) obj[22]);
				}
				if (obj[23] != null) {
					cli.setUsuario(Integer.valueOf(obj[23].toString()));
				}
				if (obj[24] != null) {
					cli.setSexo(obj[24].toString());
				}
				if (obj[25] != null) {
					cli.setDiaAni(Integer.valueOf(obj[25].toString()));
				}
				if (obj[26] != null) {
					cli.setDiaAni(Integer.valueOf(obj[26].toString()));
				}
				if (obj[27] != null) {
					cli.setMesAni(Short.valueOf(obj[27].toString()));
				}
				if (obj[28] != null) {
					cli.setPais(obj[28].toString());
				}
				if (obj[29] != null) {
					cli.setMunicipio(Integer.valueOf(obj[29].toString()));
				}
				if (obj[30] != null) {
					cli.setCartorio1(obj[30].toString());
				}
				if (obj[31] != null) {
					cli.setCartorio2(obj[31].toString());
				}
			}
		}
		return cli;
	}

	@SuppressWarnings("unchecked")
	public List<intra_corpo_diretivo> listarCorpoDiretivo(int condominio) {
		List<intra_corpo_diretivo> cd = new ArrayList<intra_corpo_diretivo>();
		this.query = this.manager.createNativeQuery(
				"select c.codigo as codigoCargo, c.nome as nomeCargo, i.nome as nomeCliente, m.cli_bloco, cli_unidade, i.codigo as codigoCliente "
						+ "from sigadm.dbo.cndmemb m " + "inner join sigadm.dbo.cndcargo c on m.cargo = c.codigo "
						+ "inner join sigadm.dbo.ilclient i on m.cliente = i.codigo where condominio = :p1");
		this.query.setParameter("p1", condominio);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_corpo_diretivo icd = new intra_corpo_diretivo();
				if (obj[0] != null) {
					icd.setCodigoCargo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					icd.setNomeCargo(obj[1].toString());
				}
				if (obj[2] != null) {
					icd.setNomeCliente(obj[2].toString());
				}
				if (obj[3] != null) {
					icd.setBloco(obj[3].toString());
				}
				if (obj[4] != null) {
					icd.setUnidade(obj[4].toString());
				}
				if (obj[5] != null) {
					icd.setCodigoCliente(Integer.valueOf(obj[5].toString()));
				}
				cd.add(icd);
			}
		}
		return cd;
	}

	@SuppressWarnings("unchecked")
	public List<intra_iltelcli> listarIltelcli(int cliente) {
		List<intra_iltelcli> telcli = new ArrayList<intra_iltelcli>();
		this.query = this.manager.createNativeQuery(
				"select cliente, tipo, tel_email, observacao, flag_cadastro, boleto_envio, dfrecnum from sigadm.dbo.iltelcli where cliente = :p1");
		this.query.setParameter("p1", cliente);
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_iltelcli t = new intra_iltelcli();
				if (obj[0] != null) {
					t.setCliente(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					t.setTipo(Integer.valueOf(obj[1].toString()));
				}
				if (obj[2] != null) {
					t.setTelEmail(obj[2].toString());
				}
				if (obj[3] != null) {
					t.setObservacao(obj[3].toString());
				}
				if (obj[4] != null) {
					t.setFlagCadastro(Integer.valueOf(obj[4].toString()));
				}
				if (obj[5] != null) {
					t.setBoleto_envio(Integer.valueOf(obj[5].toString()));
				}
				if (obj[6] != null) {
					t.setDfrecnum(Integer.valueOf(obj[6].toString()));
				}
				telcli.add(t);
			}
		}
		return telcli;
	}

	@SuppressWarnings("unchecked")
	public List<intra_cndcargo> listarCargo() {
		List<intra_cndcargo> cargo = new ArrayList<intra_cndcargo>();
		this.query = this.manager.createNativeQuery("select codigo , nome from sigadm.dbo.cndcargo order by codigo");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_cndcargo c = new intra_cndcargo();
				if (obj[0] != null) {
					c.setCodigo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					c.setNome(obj[1].toString());
				}
				cargo.add(c);
			}
		}
		return cargo;
	}

	@SuppressWarnings("unchecked")
	public List<intra_ilograd> listarLogradouro() {
		List<intra_ilograd> log = new ArrayList<>();
		this.query = this.manager.createNativeQuery("select codigo, descricao from sigadm.dbo.ilograd");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_ilograd l = new intra_ilograd();
				if (obj[0] != null) {
					l.setCodigo(obj[0].toString());
				}
				if (obj[1] != null) {
					l.setDescricao(obj[1].toString());
				}
				log.add(l);
			}
		}
		return log;
	}

	@SuppressWarnings("unchecked")
	public List<intra_ilmunic> listarMunicipio() {
		List<intra_ilmunic> log = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select codigo, cidade, estado from sigadm.dbo.ilmunic where estado = 'SP'");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_ilmunic l = new intra_ilmunic();
				if (obj[0] != null) {
					l.setCodigo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					l.setCidade(obj[1].toString());
				}
				if (obj[2] != null) {
					l.setEstado(obj[2].toString());
				}
				log.add(l);
			}
		}
		return log;
	}

	@SuppressWarnings("unchecked")
	public List<intra_atbtifon> listarDescricaoContatos() {
		List<intra_atbtifon> fon = new ArrayList<>();
		this.query = this.manager.createNativeQuery("select codigo, descricao from sigadm.dbo.atbtifon");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_atbtifon f = new intra_atbtifon();
				if (obj[0] != null) {
					f.setCodigo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					f.setDescricao(obj[1].toString());
				}
				fon.add(f);
			}
		}
		return fon;
	}

	public void alterarContato(intra_iltelcli telcliBEAN, SessaoMB sessaoMB, int condominio) {
		
		String sql = "update sigadm.dbo.iltelcli set tipo = ?, tel_email = ?, observacao = ?, flag_cadastro = ?, boleto_envio = ? where dfrecnum = ?";
		
		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setInt(1, telcliBEAN.getTipo());
			this.pmst.setString(2, telcliBEAN.getTelEmail());
			this.pmst.setString(3, telcliBEAN.getObservacao());
			this.pmst.setInt(4, telcliBEAN.getFlagCadastro());
			this.pmst.setInt(5, telcliBEAN.getBoleto_envio());
			this.pmst.setInt(6, telcliBEAN.getDfrecnum());
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(condominio);
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.iltelcli");
		this.ilg.setInfoAnterior("Alterado Contato cliente codigo: " + telcliBEAN.getCliente());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void excluirContato(intra_iltelcli telcliBEAN, SessaoMB sessaoMB, int condominio) {
		this.query = this.manager.createNativeQuery("delete sigadm.dbo.iltelcli where dfrecnum = :p1");
		this.query.setParameter("p1", telcliBEAN.getDfrecnum());
		this.query.executeUpdate();
		this.ilg = new intra_log_geral();
		data = new Date();
		this.ilg.setCondominio(condominio);
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.iltelcli");
		this.ilg.setInfoAnterior("Excluido Contato cliente codigo: " + telcliBEAN.getCliente());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void excluirDoCorpoDiretivo(intra_corpo_diretivo corpoDiretivoBEAN, SessaoMB sessaoMB, int condominio) {
		this.query = this.manager
				.createNativeQuery("delete sigadm.dbo.cndmemb where condominio = :p1 and cliente = :p2");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", corpoDiretivoBEAN.getCodigoCliente());
		this.query.executeUpdate();
		data = new Date();
		this.ilg.setCondominio(condominio);
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(true);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.cndmemb");
		this.ilg.setInfoAnterior(
				"Excluido do corpo diretivo codigo: " + corpoDiretivoBEAN.getCodigoCliente() + " Bloco:"
						+ (corpoDiretivoBEAN.getBloco() == null ? "0"
								: corpoDiretivoBEAN.getBloco().trim().isEmpty() ? "0"
										: corpoDiretivoBEAN.getBloco().trim())
						+ " Unidade:" + corpoDiretivoBEAN.getUnidade());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void alterarIlclient(intra_ilclient clienteBEAN, SessaoMB sessaoMB, int condominio) {
		
		String sql = "update sigadm.dbo.ilclient set logradouro = ?,endereco = ?,complemento = ?,bairro = ?,"
						+ "cidade = ?,estado = ?,cep = ?,tipo_pessoa = ?,cnpj_cpf = ?,data_nascimento = ?,"
						+ "profissao = ?,nacionalidade = ?,tipo_identidade = ?,nro_identidade = ?,"
						+ "orgao_emissor = ?,estado_civil = ?,end_corresp = ?,imp_etiq = ?,reside_exterior = ?,"
						+ "nro_end = ?,dt_alteracao = ?,usuario = ?,religiao = ?,sexo = ?,dia_ani = ?,mes_ani = ?,"
						+ "ano_ani = ?,pais = ?,cod_pais = ?,municipio = ?,cartorio_1 = ?,cartorio_2 = ?, nome = ? where codigo = ?";
		
		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setString(1, clienteBEAN.getLogradouro());
			this.pmst.setString(2, clienteBEAN.getEndereco());
			this.pmst.setString(3, clienteBEAN.getComplemento());
			this.pmst.setString(4, clienteBEAN.getBairro());
			while(clienteBEAN.getCidade().length() > 20){
				clienteBEAN.setCidade(clienteBEAN.getCidade().substring (0, clienteBEAN.getCidade().length() - 1));
			}
			this.pmst.setString(5, clienteBEAN.getCidade().toUpperCase());
			
			this.pmst.setString(6, clienteBEAN.getEstado());
			this.pmst.setDouble(7, clienteBEAN.getCep());
			this.pmst.setString(8, clienteBEAN.getTipoPessoa());
			this.pmst.setDouble(9, clienteBEAN.getCnpjCpf());
			if(clienteBEAN.getDataNascimento() != null){
				this.pmst.setDate(10, new java.sql.Date(clienteBEAN.getDataNascimento().getTime()));
			}else{
				this.pmst.setDate(10,null);
			}
			this.pmst.setString(11, clienteBEAN.getProfissao());
			this.pmst.setString(12, clienteBEAN.getNacionalidade());
			this.pmst.setString(13, clienteBEAN.getTipoIdentidade());
			this.pmst.setString(14, clienteBEAN.getNroIdentidade());
			this.pmst.setString(15, clienteBEAN.getOrgaoEmissor());
			this.pmst.setInt(16, clienteBEAN.getEstadoCivil());
			this.pmst.setString(17, clienteBEAN.getEndCorresp());
			this.pmst.setString(18, clienteBEAN.getImpEtiq());
			this.pmst.setString(19, clienteBEAN.getResideExterior());
			this.pmst.setString(20, clienteBEAN.getNroEnd());
			this.pmst.setDate(21, new java.sql.Date(new Date().getTime()));
			this.pmst.setInt(22, clienteBEAN.getUsuario());
			this.pmst.setInt(23, clienteBEAN.getReligiao());
			this.pmst.setString(24, clienteBEAN.getSexo());
			this.pmst.setInt(25, clienteBEAN.getDiaAni());
			this.pmst.setInt(26, clienteBEAN.getMesAni());
			this.pmst.setInt(27, clienteBEAN.getAnoAni());
			this.pmst.setString(28, clienteBEAN.getPais());
			this.pmst.setInt(29, clienteBEAN.getCodPais());
			this.pmst.setInt(30, clienteBEAN.getMunicipio());
			this.pmst.setString(31, clienteBEAN.getCartorio1());
			this.pmst.setString(32, clienteBEAN.getCartorio2());
			this.pmst.setString(33, clienteBEAN.getNome().trim());
			this.pmst.setInt(34, clienteBEAN.getCodigo());
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
		data = new Date();
		this.ilg.setCondominio(condominio);
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(true);
		this.ilg.setInserir(false);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.ilclient");
		this.ilg.setInfoAnterior("Alteração do corpo diretivo cliente codigo: " + clienteBEAN.getCodigo());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void updateCndMemb(intra_ilclient clienteBEAN, int funcao, int condominio) {
		String sql = "update sigadm.dbo.cndmemb set cargo = ? where condominio = ? and cliente = ?";
		
		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setInt(1, funcao);
			this.pmst.setInt(2, condominio);
			this.pmst.setInt(3, clienteBEAN.getCodigo());
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void adicionarContato(int cliente, int tipoContato, String telEmail, String observacao) {
		String sql = "insert into sigadm.dbo.iltelcli (cliente, tipo, tel_email, observacao, flag_cadastro, boleto_envio)values(?,?,?,?,0,0)";
		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = con.prepareStatement(sql);
			this.pmst.setInt(1, cliente);
			this.pmst.setInt(2, tipoContato);
			this.pmst.setString(3, telEmail);
			this.pmst.setString(4, observacao);
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public intra_ilclient listarClienteCndUnida(String bloco, String unidade, int condominio) {
		this.query = this.manager.createNativeQuery(
				"select nome, cod_cliente from sigadm.dbo.cndunida where condominio = :p1 and bloco = :p2 and unidade = :p3");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", bloco.trim());
		this.query.setParameter("p3", unidade.trim());
		intra_ilclient cli = new intra_ilclient();
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				if (obj[0] != null) {
					cli.setNome(obj[0].toString());
				}
				if (obj[1] != null) {
					cli.setCodigo(Integer.valueOf(obj[1].toString()));
				}
			}
		}
		return cli;
	}

	public void updateIlclienteNome(int codigoCliente, String nome2) {
		
		String sql = "update sigadm.dbo.ilclient set nome = ? where codigo = ?";
		
		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setString(1, nome2);
			this.pmst.setInt(2, codigoCliente);
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateIlclienteNomeCndUnida(int codigoCliente, String bloco, String unidade, String nome2) {
		
		String sql = "update sigadm.dbo.cndunida set nome = ? where cod_cliente = ? and bloco = ? and unidade = ?";
		
		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setString(1, nome2);
			this.pmst.setInt(2, codigoCliente);
			this.pmst.setString(3, bloco.trim());
			this.pmst.setString(4, unidade.trim());
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateIlclienteNomeCndUnida(String bloco, String unidade, String nome2, int condominio) {
		
		String sql = "update sigadm.dbo.cndunida set nome = ? where condominio = ? and bloco = ? and unidade = ?";
		
		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setString(1, nome2);
			this.pmst.setInt(2, condominio);
			this.pmst.setString(3, bloco.trim());
			this.pmst.setString(4, unidade.trim());
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public intra_ilclient listarClienteNovo(int condominio, String bloco, String unidade) {
		this.query = this.manager.createNativeQuery(
				"select i.codigo, i.nome, i.logradouro, i.endereco, i.complemento, i.bairro, i.cidade, "
						+ "i.estado, i.cep, i.tipo_pessoa, i.cnpj_cpf, i.data_nascimento, i.profissao, i.nacionalidade, i.tipo_identidade, "
						+ "i.nro_identidade, i.orgao_emissor, i.estado_civil, i.end_corresp, i.imp_etiq, i.reside_exterior,i.nro_end, i.dt_alteracao, "
						+ "i.usuario, i.sexo, i.dia_ani, i.mes_ani, i.ano_ani, i.pais,  i.municipio, i.cartorio_1, i.cartorio_2 from sigadm.dbo.ilclient i "
						+ "inner join sigadm.dbo.cndunida u on i.codigo = u.cod_cliente where u.condominio = :p1 and u.bloco = :p2 and u.unidade = :p3");
		this.query.setParameter("p1", condominio);
		this.query.setParameter("p2", bloco.trim());
		this.query.setParameter("p3", unidade.trim());
		intra_ilclient cli = new intra_ilclient();
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				if (obj[0] != null) {
					cli.setCodigo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					cli.setNome(obj[1].toString());
				}
				if (obj[2] != null) {
					cli.setLogradouro(obj[2].toString());
				}
				if (obj[3] != null) {
					cli.setEndereco(obj[3].toString());
				}
				if (obj[4] != null) {
					cli.setComplemento(obj[4].toString());
				}
				if (obj[5] != null) {
					cli.setBairro(obj[5].toString());
				}
				if (obj[6] != null) {
					cli.setCidade(obj[6].toString());
				}
				if (obj[7] != null) {
					cli.setEstado(obj[7].toString());
				}
				if (obj[8] != null) {
					cli.setCep(Double.valueOf(obj[8].toString()));
				}
				if (obj[9] != null) {
					cli.setTipoPessoa(obj[9].toString());
				}
				if (obj[10] != null) {
					cli.setCnpjCpf(Double.valueOf(obj[10].toString()));
				}
				if (obj[11] != null) {
					cli.setDataNascimento((Date) obj[11]);
				}
				if (obj[12] != null) {
					cli.setProfissao(obj[12].toString());
				}
				if (obj[13] != null) {
					cli.setNacionalidade(obj[13].toString());
				}
				if (obj[14] != null) {
					cli.setTipoIdentidade(obj[14].toString());
				}
				if (obj[15] != null) {
					cli.setNroIdentidade(obj[15].toString());
				}
				if (obj[16] != null) {
					cli.setOrgaoEmissor(obj[16].toString());
				}
				if (obj[17] != null) {
					cli.setEstadoCivil(Integer.valueOf(obj[17].toString()));
				}
				if (obj[18] != null) {
					cli.setEndCorresp(obj[18].toString());
				}
				if (obj[19] != null) {
					cli.setImpEtiq(obj[19].toString());
				}
				if (obj[20] != null) {
					cli.setResideExterior(obj[20].toString());
				}
				if (obj[21] != null) {
					cli.setNroEnd(obj[21].toString());
				}
				if (obj[22] != null) {
					cli.setDtAlteracao((Date) obj[22]);
				}
				if (obj[23] != null) {
					cli.setUsuario(Integer.valueOf(obj[23].toString()));
				}
				if (obj[24] != null) {
					cli.setSexo(obj[24].toString());
				}
				if (obj[25] != null) {
					cli.setDiaAni(Integer.valueOf(obj[25].toString()));
				}
				if (obj[26] != null) {
					cli.setDiaAni(Integer.valueOf(obj[26].toString()));
				}
				if (obj[27] != null) {
					cli.setMesAni(Short.valueOf(obj[27].toString()));
				}
				if (obj[28] != null) {
					cli.setPais(obj[28].toString());
				}
				if (obj[29] != null) {
					cli.setMunicipio(Integer.valueOf(obj[29].toString()));
				}
				if (obj[30] != null) {
					cli.setCartorio1(obj[30].toString());
				}
				if (obj[31] != null) {
					cli.setCartorio2(obj[31].toString());
				}
			}
		}
		return cli;
	}

	public int novoCodigoCliente() {
		int contador = 1;
		String sql = "select codigo from sigadm.dbo.ilclient order by codigo";

		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);

			ResultSet rs = this.pmst.executeQuery();

			while (rs.next()) {
				if (rs.getInt("codigo") != contador) {
					System.out.println(contador);
					break;
				}
				contador++;
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}

		return contador;
	}

	public void addNovoCliente(intra_ilclient clienteBEAN, SessaoMB sessaoMB, int condominio) {

		String sql = "insert into sigadm.dbo.ilclient (logradouro,endereco,complemento,bairro,"
				+ "cidade,estado,cep,tipo_pessoa,cnpj_cpf,data_nascimento,"
				+ "profissao,nacionalidade,tipo_identidade,nro_identidade,"
				+ "orgao_emissor,estado_civil,end_corresp,imp_etiq,reside_exterior,"
				+ "nro_end,dt_alteracao,usuario,religiao,sexo,dia_ani,mes_ani,"
				+ "ano_ani,pais,cod_pais,municipio,cartorio_1,cartorio_2,codigo, nome, data_cadastro)"
				+ "values(?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?)";
		try {
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setString(1, clienteBEAN.getLogradouro());
			this.pmst.setString(2, clienteBEAN.getEndereco());
			this.pmst.setString(3, clienteBEAN.getComplemento());
			this.pmst.setString(4, clienteBEAN.getBairro());
			
			
			while(clienteBEAN.getCidade().length() > 20){
				clienteBEAN.setCidade(clienteBEAN.getCidade().substring (0, clienteBEAN.getCidade().length() - 1));
			}
			this.pmst.setString(5, clienteBEAN.getCidade().toUpperCase());
			
			
			this.pmst.setString(6, clienteBEAN.getEstado());
			this.pmst.setDouble(7, clienteBEAN.getCep());
			this.pmst.setString(8, clienteBEAN.getTipoPessoa());
			this.pmst.setDouble(9, clienteBEAN.getCnpjCpf());
			if(clienteBEAN.getDataNascimento() != null){
				this.pmst.setDate(10, new java.sql.Date(clienteBEAN.getDataNascimento().getTime()));
			}else{
				this.pmst.setDate(10,null);
			}
			this.pmst.setString(11, clienteBEAN.getProfissao());
			this.pmst.setString(12, clienteBEAN.getNacionalidade());
			this.pmst.setString(13, clienteBEAN.getTipoIdentidade());
			this.pmst.setString(14, clienteBEAN.getNroIdentidade());
			this.pmst.setString(15, clienteBEAN.getOrgaoEmissor());
			this.pmst.setInt(16, clienteBEAN.getEstadoCivil());
			this.pmst.setString(17, clienteBEAN.getEndCorresp());
			this.pmst.setString(18, clienteBEAN.getImpEtiq());
			this.pmst.setString(19, clienteBEAN.getResideExterior());
			this.pmst.setString(20, clienteBEAN.getNroEnd());
			this.pmst.setDate(21, new java.sql.Date(new Date().getTime()));
			this.pmst.setInt(22, clienteBEAN.getUsuario());
			this.pmst.setShort(23, clienteBEAN.getReligiao());
			this.pmst.setString(24, clienteBEAN.getSexo());
			this.pmst.setInt(25, clienteBEAN.getDiaAni());
			this.pmst.setInt(26, clienteBEAN.getMesAni());
			this.pmst.setShort(27, clienteBEAN.getAnoAni());
			this.pmst.setString(28, clienteBEAN.getPais());
			this.pmst.setInt(29, clienteBEAN.getCodPais());
			this.pmst.setInt(30, clienteBEAN.getMunicipio());
			this.pmst.setString(31, clienteBEAN.getCartorio1());
			this.pmst.setString(32, clienteBEAN.getCartorio2());
			this.pmst.setInt(33, clienteBEAN.getCodigo());
			this.pmst.setString(34, clienteBEAN.getNome().toUpperCase());
			if(clienteBEAN.getDataCadastro() != null){
				this.pmst.setDate(35, new java.sql.Date(clienteBEAN.getDataCadastro().getTime()));
			}else{
				this.pmst.setDate(35,null);
			}
			
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("insert na cndmemb Sistema Corpo Diretivo " + e.getMessage());
		}

		data = new Date();
		this.ilg.setCondominio(condominio);
		this.ilg.setIp(sessaoMB.getIpUser());
		this.ilg.setDataFeito(data);
		this.ilg.setDeletar(false);
		this.ilg.setAlterar(false);
		this.ilg.setInserir(true);
		this.ilg.setFeitoPor(sessaoMB.getUsuario().getEmail());
		this.ilg.setTabela("sigadm.dbo.ilclient");
		this.ilg.setInfoAnterior("Novo Cliente e membro do corpo diretivo cliente codigo: " + clienteBEAN.getCodigo());
		this.logGeral(this.ilg);
		this.ilg = new intra_log_geral();
	}

	public void addCorpoDiretivo(intra_cndmemb cndmembBEAN) {
		try {
			String insert = "insert into sigadm.dbo.cndmemb (bloco, cargo, cliente, cli_bloco, cli_unidade, termo, situacao, cond_resp, valor_base, valor_outros, condominio) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(insert);
			this.pmst.setString(1, cndmembBEAN.getBloco());
			this.pmst.setInt(2, cndmembBEAN.getCargo());
			this.pmst.setInt(3, cndmembBEAN.getCliente());
			this.pmst.setString(4, cndmembBEAN.getCliBloco());
			this.pmst.setString(5, cndmembBEAN.getCliUnidade());
			this.pmst.setInt(6, cndmembBEAN.getTermo());
			this.pmst.setInt(7, cndmembBEAN.getSituacao());
			this.pmst.setInt(8, cndmembBEAN.getCondResp());
			this.pmst.setDouble(9, cndmembBEAN.getValorBase());
			this.pmst.setDouble(10, cndmembBEAN.getValorOutros());
			this.pmst.setInt(11, cndmembBEAN.getCondominio());
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("insert na cndmemb Sistema Corpo Diretivo " + e.getMessage());
		}

	}

	public void updateNomeDaUnidade(String retornaNome, int codigoDaUnidade) {
		
		try {
			String sql = "update sigadm.dbo.ilclient set nome = ? where codigo = ?";
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setString(1, retornaNome);
			this.pmst.setInt(2, codigoDaUnidade);
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("update ilclient Sistema Corpo Diretivo " + e.getMessage());
		}
	}

	public void updateCndUnidaNomeCli(String retornaNome, int codigoDaUnidade, String bloco, String unidade) {
		
		try {
			String sql = "update sigadm.dbo.cndunida set nome = ? where cod_cliente = ? and bloco = ? and unidade = ?";
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setString(1, retornaNome);
			this.pmst.setInt(2, codigoDaUnidade);
			this.pmst.setString(3, bloco.trim());
			this.pmst.setString(4, unidade.trim());
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("update na cndunida Sistema Corpo Diretivo " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<intra_ilclient> listaDeIlclient(String nome) {
		List<intra_ilclient> client = new ArrayList<>();
		this.query = this.manager
				.createNativeQuery("select codigo, nome, logradouro, endereco, complemento, bairro, cidade, "
						+ "estado, cep, tipo_pessoa, cnpj_cpf, data_nascimento, profissao, nacionalidade, tipo_identidade, "
						+ "nro_identidade, orgao_emissor, estado_civil, end_corresp, imp_etiq, reside_exterior, nro_end,dt_alteracao, "
						+ "usuario, sexo, dia_ani, mes_ani, ano_ani, pais,  municipio, cartorio_1, cartorio_2 from sigadm.dbo.ilclient where nome like '%"
						+ nome + "%'");
		List<Object[]> lista = this.query.getResultList();
		if (!lista.isEmpty()) {
			for (Object[] obj : lista) {
				intra_ilclient cli = new intra_ilclient();
				if (obj[0] != null) {
					cli.setCodigo(Integer.valueOf(obj[0].toString()));
				}
				if (obj[1] != null) {
					cli.setNome(obj[1].toString());
				}
				if (obj[2] != null) {
					cli.setLogradouro(obj[2].toString());
				}
				if (obj[3] != null) {
					cli.setEndereco(obj[3].toString());
				}
				if (obj[4] != null) {
					cli.setComplemento(obj[4].toString());
				}
				if (obj[5] != null) {
					cli.setBairro(obj[5].toString());
				}
				if (obj[6] != null) {
					cli.setCidade(obj[6].toString());
				}
				if (obj[7] != null) {
					cli.setEstado(obj[7].toString());
				}
				if (obj[8] != null) {
					cli.setCep(Double.valueOf(obj[8].toString()));
				}
				if (obj[9] != null) {
					cli.setTipoPessoa(obj[9].toString());
				}
				if (obj[10] != null) {
					cli.setCnpjCpf(Double.valueOf(obj[10].toString()));
				}
				if (obj[11] != null) {
					cli.setDataNascimento((Date) obj[11]);
				}
				if (obj[12] != null) {
					cli.setProfissao(obj[12].toString());
				}
				if (obj[13] != null) {
					cli.setNacionalidade(obj[13].toString());
				}
				if (obj[14] != null) {
					cli.setTipoIdentidade(obj[14].toString());
				}
				if (obj[15] != null) {
					cli.setNroIdentidade(obj[15].toString());
				}
				if (obj[16] != null) {
					cli.setOrgaoEmissor(obj[16].toString());
				}
				if (obj[17] != null) {
					cli.setEstadoCivil(Integer.valueOf(obj[17].toString()));
				}
				if (obj[18] != null) {
					cli.setEndCorresp(obj[18].toString());
				}
				if (obj[19] != null) {
					cli.setImpEtiq(obj[19].toString());
				}
				if (obj[20] != null) {
					cli.setResideExterior(obj[20].toString());
				}
				if (obj[21] != null) {
					cli.setNroEnd(obj[21].toString());
				}
				if (obj[22] != null) {
					cli.setDtAlteracao((Date) obj[22]);
				}
				if (obj[23] != null) {
					cli.setUsuario(Integer.valueOf(obj[23].toString()));
				}
				if (obj[24] != null) {
					cli.setSexo(obj[24].toString());
				}
				if (obj[25] != null) {
					cli.setDiaAni(Integer.valueOf(obj[25].toString()));
				}
				if (obj[26] != null) {
					cli.setDiaAni(Integer.valueOf(obj[26].toString()));
				}
				if (obj[27] != null) {
					cli.setMesAni(Short.valueOf(obj[27].toString()));
				}
				if (obj[28] != null) {
					cli.setPais(obj[28].toString());
				}
				if (obj[29] != null) {
					cli.setMunicipio(Integer.valueOf(obj[29].toString()));
				}
				if (obj[30] != null) {
					cli.setCartorio1(obj[30].toString());
				}
				if (obj[31] != null) {
					cli.setCartorio2(obj[31].toString());
				}
				client.add(cli);
			}
		}
		return client;
	}

	public String retornaNomeCndUnida(String bloco, String unidade, int condominio) {
		this.query = this.manager.createNativeQuery(
				"select nome from sigadm.dbo.cndunida where bloco = :p1 and unidade = :p2 and condominio = :p3");
		query.setParameter("p1", bloco.trim());
		query.setParameter("p2", unidade.trim());
		query.setParameter("p3", condominio);
		return query.getResultList().get(0).toString();
	}

	public void updateCndUnidaNomeCli(String retornaNome, String bloco, String unidade, int condominio) {
		try {
			String sql = "update sigadm.dbo.cndunida set nome = ? where bloco = ? and unidade = ? and condominio = ?";
			this.con = Conexao.getConexaoSiga();
			this.pmst = this.con.prepareStatement(sql);
			this.pmst.setString(1, retornaNome);
			this.pmst.setString(2, bloco.trim());
			this.pmst.setString(3, unidade.trim());
			this.pmst.setInt(4, condominio);
			this.pmst.executeUpdate();
			this.pmst.close();
			this.con.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("update na cndunida Sistema Corpo Diretivo " + e.getMessage());
		}
	}

}
