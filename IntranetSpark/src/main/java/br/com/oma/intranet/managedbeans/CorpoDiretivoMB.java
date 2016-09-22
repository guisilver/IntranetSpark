package br.com.oma.intranet.managedbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.CorpoDiretivoDAO;
import br.com.oma.intranet.dao.LISTAR_BL_UNI_DAO;
import br.com.oma.intranet.entidades.intra_cndunidade;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_corpo_diretivo;
import br.com.oma.intranet.util.AuxAtbifon;
import br.com.oma.intranet.util.CorpoDiretivoAUX;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.StringUtil;
import br.com.oma.intranet.util.ValidaCPFCNPJ;
import br.com.oma.sigadm.entidades.intra_atbtifon;
import br.com.oma.sigadm.entidades.intra_cndbloco;
import br.com.oma.sigadm.entidades.intra_cndcargo;
import br.com.oma.sigadm.entidades.intra_cndmemb;
import br.com.oma.sigadm.entidades.intra_ilclient;
import br.com.oma.sigadm.entidades.intra_ilmunic;
import br.com.oma.sigadm.entidades.intra_ilograd;
import br.com.oma.sigadm.entidades.intra_iltelcli;

@ManagedBean(name = "cdMB")
@ViewScoped
public class CorpoDiretivoMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1638694332928243373L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private CorpoDiretivoDAO cdDAO;
	private LISTAR_BL_UNI_DAO cndUnidaDAO;
	private ValidaCPFCNPJ validaCPFCNPJ;
	private intra_condominios icBEAN = new intra_condominios();
	private intra_ilclient clienteBEAN = new intra_ilclient();
	private intra_iltelcli telcliBEAN = new intra_iltelcli();
	private intra_cndbloco cndblocoBEAN = new intra_cndbloco();
	private intra_cndmemb cndmembBEAN = new intra_cndmemb();
	private intra_cndunidade unidaBEAN = new intra_cndunidade();
	private intra_corpo_diretivo corpoDiretivoBEAN = new intra_corpo_diretivo();
	private intra_ilograd logradouroBEAN = new intra_ilograd();
	private intra_ilmunic cidadeBEAN = new intra_ilmunic();
	private intra_atbtifon atbtifonBEAN = new intra_atbtifon();

	// LISTAS
	private List<intra_cndunidade> listaDeBloco, listaDeUnidade;
	private List<intra_cndmemb> listaDeCndmemb;
	private List<intra_corpo_diretivo> listaDeCorpoDiretivo;
	private List<intra_ilclient> listaDeCliente, listaDeSindicoProfissional, filtroDeSindicoProfissional;
	private List<intra_iltelcli> listaDeTelcli;
	private List<intra_cndcargo> listaDeCargo;
	private List<intra_ilograd> listaDeLogradouro;
	private List<intra_ilmunic> listaDeCidade;
	private List<intra_atbtifon> listaDeContatosDescricao;

	// ATRIBUTOS
	private int condominio;
	private int funcao;
	private int pagina1 = 1;
	private int pagina2;
	private int pagina3;
	private int pagina4;
	private int tipoContato;
	private int validaNome = 1;
	private int updateCodigoCli;
	private int sindicoExistente = 1;

	private boolean valida;

	private String nomeCondo;
	private String bloco;
	private String unidade;
	private String cpf;
	private String cnpj;
	private String cep;
	private String telEmail;
	private String observacao;
	private String outroNome;
	private String updateNomeUni;
	private String nomeSindico;
	private String prazoAssembleia;
	private String quorumPrimeira;
	private String segundaChamada;
	private String quorumSegunda;
	private String obs1;

	private Date inicioMandato;
	private Date finalMandato;
	private Date dtAprovacao;
	private Date prxAprovacao;

	// GET X SET
	public List<intra_cndunidade> getListaDeBloco() {
		if (this.condominio > 0) {
			this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
			this.listaDeBloco = this.cndUnidaDAO.listaDeBloco(this.condominio);
		}
		return listaDeBloco;
	}

	public void setListaDeBloco(List<intra_cndunidade> listaDeBloco) {
		this.listaDeBloco = listaDeBloco;
	}

	public List<intra_cndunidade> getListaDeUnidade() {
		if (this.sessaoMB.getGerenteSelecionado() != null && this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
			if (this.bloco != null) {
				if (!this.bloco.trim().isEmpty()) {
					this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
					this.listaDeUnidade = null;
					this.listaDeUnidade = this.cndUnidaDAO.listaDeUnidade(this.condominio, this.bloco);
				}
			}
		}
		return listaDeUnidade;
	}

	public void setListaDeUnidade(List<intra_cndunidade> listaDeUnidade) {
		this.listaDeUnidade = listaDeUnidade;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public String getNomeCondo() {
		return nomeCondo;
	}

	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	public int getCondominio() {
		return condominio;
	}

	public void setCondominio(int condominio) {
		this.condominio = condominio;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public intra_ilclient getClienteBEAN() {
		return clienteBEAN;
	}

	public void setClienteBEAN(intra_ilclient clienteBEAN) {
		this.clienteBEAN = clienteBEAN;
	}

	public intra_iltelcli getTelcliBEAN() {
		return telcliBEAN;
	}

	public void setTelcliBEAN(intra_iltelcli telcliBEAN) {
		this.telcliBEAN = telcliBEAN;
	}

	public intra_cndbloco getCndblocoBEAN() {
		return cndblocoBEAN;
	}

	public void setCndblocoBEAN(intra_cndbloco cndblocoBEAN) {
		this.cndblocoBEAN = cndblocoBEAN;
	}

	public intra_cndmemb getCndmembBEAN() {
		return cndmembBEAN;
	}

	public void setCndmembBEAN(intra_cndmemb cndmembBEAN) {
		this.cndmembBEAN = cndmembBEAN;
	}

	public intra_cndunidade getUnidaBEAN() {
		return unidaBEAN;
	}

	public void setUnidaBEAN(intra_cndunidade unidaBEAN) {
		this.unidaBEAN = unidaBEAN;
	}

	public intra_corpo_diretivo getCorpoDiretivoBEAN() {
		return corpoDiretivoBEAN;
	}

	public void setCorpoDiretivoBEAN(intra_corpo_diretivo corpoDiretivoBEAN) {
		this.corpoDiretivoBEAN = corpoDiretivoBEAN;
	}

	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
	}

	public int getFuncao() {
		return funcao;
	}

	public void setFuncao(int funcao) {
		this.funcao = funcao;
	}

	public List<intra_cndmemb> getListaDeCndmemb() {
		return listaDeCndmemb;
	}

	public void setListaDeCndmemb(List<intra_cndmemb> listaDeCndmemb) {
		this.listaDeCndmemb = listaDeCndmemb;
	}

	public List<intra_ilclient> getListaDeCliente() {
		return listaDeCliente;
	}

	public void setListaDeCliente(List<intra_ilclient> listaDeCliente) {
		this.listaDeCliente = listaDeCliente;
	}

	public List<intra_iltelcli> getListaDeTelcli() {
		return listaDeTelcli;
	}

	public void setListaDeTelcli(List<intra_iltelcli> listaDeTelcli) {
		this.listaDeTelcli = listaDeTelcli;
	}

	public List<intra_corpo_diretivo> getListaDeCorpoDiretivo() {
		return listaDeCorpoDiretivo;
	}

	public void setListaDeCorpoDiretivo(List<intra_corpo_diretivo> listaDeCorpoDiretivo) {
		this.listaDeCorpoDiretivo = listaDeCorpoDiretivo;
	}

	public List<intra_cndcargo> getListaDeCargo() {
		this.cdDAO = new CorpoDiretivoDAO();
		this.listaDeCargo = this.cdDAO.listarCargo();
		return listaDeCargo;
	}

	public void setListaDeCargo(List<intra_cndcargo> listaDeCargo) {
		this.listaDeCargo = listaDeCargo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public intra_ilograd getLogradouroBEAN() {
		return logradouroBEAN;
	}

	public void setLogradouroBEAN(intra_ilograd logradouroBEAN) {
		this.logradouroBEAN = logradouroBEAN;
	}

	public List<intra_ilograd> getListaDeLogradouro() {
		if (this.listaDeLogradouro == null) {
			this.listaDeLogradouro = this.cdDAO.listarLogradouro();
		}
		return listaDeLogradouro;
	}

	public void setListaDeLogradouro(List<intra_ilograd> listaDeLogradouro) {
		this.listaDeLogradouro = listaDeLogradouro;
	}

	public intra_ilmunic getCidadeBEAN() {
		return cidadeBEAN;
	}

	public void setCidadeBEAN(intra_ilmunic cidadeBEAN) {
		this.cidadeBEAN = cidadeBEAN;
	}

	public List<intra_ilmunic> getListaDeCidade() {
		if (this.listaDeCidade == null) {
			this.listaDeCidade = this.cdDAO.listarMunicipio();
		}
		return listaDeCidade;
	}

	public void setListaDeCidade(List<intra_ilmunic> listaDeCidade) {
		this.listaDeCidade = listaDeCidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public intra_atbtifon getAtbtifonBEAN() {
		return atbtifonBEAN;
	}

	public void setAtbtifonBEAN(intra_atbtifon atbtifonBEAN) {
		this.atbtifonBEAN = atbtifonBEAN;
	}

	public List<intra_atbtifon> getListaDeContatosDescricao() {
		if (this.listaDeContatosDescricao == null) {
			this.cdDAO = new CorpoDiretivoDAO();
			this.listaDeContatosDescricao = this.cdDAO.listarDescricaoContatos();
		}
		return listaDeContatosDescricao;
	}

	public void setListaDeContatosDescricao(List<intra_atbtifon> listaDeContatosDescricao) {
		this.listaDeContatosDescricao = listaDeContatosDescricao;
	}

	public int getPagina1() {
		return pagina1;
	}

	public void setPagina1(int pagina1) {
		this.pagina1 = pagina1;
	}

	public int getPagina2() {
		return pagina2;
	}

	public void setPagina2(int pagina2) {
		this.pagina2 = pagina2;
	}

	public int getPagina3() {
		return pagina3;
	}

	public void setPagina3(int pagina3) {
		this.pagina3 = pagina3;
	}

	public int getPagina4() {
		return pagina4;
	}

	public void setPagina4(int pagina4) {
		this.pagina4 = pagina4;
	}

	public int getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(int tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getTelEmail() {
		return telEmail;
	}

	public void setTelEmail(String telEmail) {
		this.telEmail = telEmail;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getValidaNome() {
		return validaNome;
	}

	public void setValidaNome(int validaNome) {
		this.validaNome = validaNome;
	}

	public String getOutroNome() {
		return outroNome;
	}

	public void setOutroNome(String outroNome) {
		this.outroNome = outroNome;
	}

	public int getUpdateCodigoCli() {
		return updateCodigoCli;
	}

	public void setUpdateCodigoCli(int updateCodigoCli) {
		this.updateCodigoCli = updateCodigoCli;
	}

	public String getUpdateNomeUni() {
		return updateNomeUni;
	}

	public void setUpdateNomeUni(String updateNomeUni) {
		this.updateNomeUni = updateNomeUni;
	}

	public List<intra_ilclient> getListaDeSindicoProfissional() {
		return listaDeSindicoProfissional;
	}

	public void setListaDeSindicoProfissional(List<intra_ilclient> listaDeSindicoProfissional) {
		this.listaDeSindicoProfissional = listaDeSindicoProfissional;
	}

	public List<intra_ilclient> getFiltroDeSindicoProfissional() {
		return filtroDeSindicoProfissional;
	}

	public void setFiltroDeSindicoProfissional(List<intra_ilclient> filtroDeSindicoProfissional) {
		this.filtroDeSindicoProfissional = filtroDeSindicoProfissional;
	}

	public String getNomeSindico() {
		return nomeSindico;
	}

	public void setNomeSindico(String nomeSindico) {
		this.nomeSindico = nomeSindico;
	}

	public String getPrazoAssembleia() {
		return prazoAssembleia;
	}

	public void setPrazoAssembleia(String prazoAssembleia) {
		this.prazoAssembleia = prazoAssembleia;
	}

	public String getQuorumPrimeira() {
		return quorumPrimeira;
	}

	public void setQuorumPrimeira(String quorumPrimeira) {
		this.quorumPrimeira = quorumPrimeira;
	}

	public String getSegundaChamada() {
		return segundaChamada;
	}

	public void setSegundaChamada(String segundaChamada) {
		this.segundaChamada = segundaChamada;
	}

	public String getQuorumSegunda() {
		return quorumSegunda;
	}

	public void setQuorumSegunda(String quorumSegunda) {
		this.quorumSegunda = quorumSegunda;
	}

	public String getObs1() {
		return obs1;
	}

	public void setObs1(String obs1) {
		this.obs1 = obs1;
	}

	public Date getInicioMandato() {
		return inicioMandato;
	}

	public void setInicioMandato(Date inicioMandato) {
		this.inicioMandato = inicioMandato;
	}

	public Date getFinalMandato() {
		return finalMandato;
	}

	public void setFinalMandato(Date finalMandato) {
		this.finalMandato = finalMandato;
	}

	public Date getDtAprovacao() {
		return dtAprovacao;
	}

	public void setDtAprovacao(Date dtAprovacao) {
		this.dtAprovacao = dtAprovacao;
	}

	public Date getPrxAprovacao() {
		return prxAprovacao;
	}

	public void setPrxAprovacao(Date prxAprovacao) {
		this.prxAprovacao = prxAprovacao;
	}

	public int getSindicoExistente() {
		return sindicoExistente;
	}

	public void setSindicoExistente(int sindicoExistente) {
		this.sindicoExistente = sindicoExistente;
	}

	public boolean isValida() {
		return valida;
	}

	public void setValida(boolean valida) {
		this.valida = valida;
	}

	public void retornaNomeCondominio() {
		for (intra_condominios c : sessaoMB.getListaDeCondominios()) {
			if (c.getCodigo() == this.condominio) {
				this.nomeCondo = c.getNome();
				if (this.icBEAN == null) {
					this.icBEAN = new intra_condominios();
					this.icBEAN.setCodigo(this.condominio);
				}
				this.icBEAN.setNomeGerente(c.getNomeGerente());
				this.icBEAN.setEmailGerente(c.getEmailGerente());
				this.icBEAN.setCodigoGerente(c.getCodigoGerente());
				this.icBEAN.setNome(c.getNome());
			}
		}
		if (this.condominio > 0) {
			this.listaDeCliente = new ArrayList<intra_ilclient>();
			this.cdDAO = new CorpoDiretivoDAO();
			this.cndblocoBEAN = new intra_cndbloco();

			this.cndblocoBEAN = this.cdDAO.listarCndBloco(this.condominio);
			this.inicioMandato = this.cndblocoBEAN.getInicioMandato();
			this.finalMandato = this.cndblocoBEAN.getFinalMandato();
			this.prazoAssembleia = this.cndblocoBEAN.getPrazoAssembl();
			this.quorumPrimeira = this.cndblocoBEAN.getQuorumPrimeira();
			this.segundaChamada = this.cndblocoBEAN.getSegundaChamada();
			this.quorumSegunda = this.cndblocoBEAN.getQuorumSegunda();
			this.dtAprovacao = this.cndblocoBEAN.getDtAprovacao();
			this.prxAprovacao = this.cndblocoBEAN.getPrxAprovacao();
			this.obs1 = this.cndblocoBEAN.getObs1();

			this.listaDeCndmemb = this.cdDAO.listarCndMemb(this.condominio);
			this.listaDeCorpoDiretivo = this.cdDAO.listarCorpoDiretivo(this.condominio);

			if (!this.listaDeCndmemb.isEmpty()) {
				for (intra_cndmemb memb : listaDeCndmemb) {
					intra_ilclient cli = new intra_ilclient();
					cli = this.cdDAO.listarCliente(memb.getCliente());
					this.listaDeCliente.add(cli);
				}
			}
		}
	}

	public String listarDescricao(int codigo) {
		AuxAtbifon aux = new AuxAtbifon();
		return aux.listarTelEmail(codigo);
	}

	public void nomeCliente() {
		for (intra_cndunidade uni : listaDeUnidade) {
			if (uni.getBloco().equals(this.bloco) & uni.getUnidade().equals(this.unidade)) {
				if (this.clienteBEAN == null) {
					this.clienteBEAN = new intra_ilclient();
				}
				this.clienteBEAN.setNome(StringUtil.trataNomeComposto(uni.getNome()));
			}
		}
	}

	public void salvarDadosGerais() {
		if (this.condominio > 0) {

			this.cdDAO = new CorpoDiretivoDAO();
			this.cndblocoBEAN.setCondominio(this.condominio);
			this.cndblocoBEAN.setInicioMandato(this.inicioMandato);
			this.cndblocoBEAN.setFinalMandato(this.finalMandato);
			this.cndblocoBEAN.setPrazoAssembl(this.prazoAssembleia);
			this.cndblocoBEAN.setQuorumPrimeira(this.quorumPrimeira);
			this.cndblocoBEAN.setSegundaChamada(this.segundaChamada);
			this.cndblocoBEAN.setQuorumSegunda(this.quorumSegunda);
			this.cndblocoBEAN.setDtAprovacao(this.dtAprovacao);
			this.cndblocoBEAN.setPrxAprovacao(this.prxAprovacao);
			this.cndblocoBEAN.setObs1(this.obs1);

			this.cdDAO.atualizarDadosGerais(this.cndblocoBEAN);
			this.msgSalvo();
		}
	}

	public void alterar() {
		this.cdDAO = new CorpoDiretivoDAO();
		this.proximo2();
		this.bloco = this.corpoDiretivoBEAN.getBloco();
		/*
		 * this.unidaBEAN.setBloco(this.corpoDiretivoBEAN.getBloco().trim());
		 * this.unidaBEAN.setUnidade(this.corpoDiretivoBEAN.getUnidade());
		 */
		this.unidade = this.corpoDiretivoBEAN.getUnidade();
		this.clienteBEAN = this.cdDAO.listarCliente(this.corpoDiretivoBEAN.getCodigoCliente());
		this.logradouroBEAN.setCodigo(this.clienteBEAN.getLogradouro().trim());
		this.cidadeBEAN.setCodigo(this.clienteBEAN.getMunicipio());

		if (this.clienteBEAN.getCnpjCpf() > 0) {
			CorpoDiretivoAUX cda = new CorpoDiretivoAUX();
			if (this.clienteBEAN.getTipoPessoa().trim().equals("F")) {
				this.cpf = String.valueOf((long) this.clienteBEAN.getCnpjCpf());
				this.cpf = cda.retornaCpfCnpj(this.cpf, 1);
			} else {
				this.cnpj = String.valueOf(this.clienteBEAN.getCnpjCpf());
				this.cnpj = cda.retornaCpfCnpj(this.cnpj, 2);
			}
		} else {
			this.cpf = "";
			this.cnpj = "";
		}

		int cepInt = (int) this.clienteBEAN.getCep();
		this.cep = String.valueOf(cepInt);
		if (this.cep.length() == 7) {
			this.cep = "0" + this.cep;
		} else if (this.cep.length() == 6) {
			this.cep = "00" + this.cep;
		} else if (this.cep.length() == 5) {
			this.cep = "000" + this.cep;
		} else if (this.cep.length() == 4) {
			this.cep = "0000" + this.cep;
		} else if (this.cep.length() == 3) {
			this.cep = "00000" + this.cep;
		} else if (this.cep.length() == 2) {
			this.cep = "000000" + this.cep;
		} else if (this.cep.length() == 1) {
			this.cep = "0000000" + this.cep;
		}
		this.listaDeTelcli = this.cdDAO.listarIltelcli(this.corpoDiretivoBEAN.getCodigoCliente());

		this.funcao = this.corpoDiretivoBEAN.getCodigoCargo();

	}

	public void alterar2() {
		this.cdDAO = new CorpoDiretivoDAO();
		if (this.bloco != null && this.unidade != null) {
			if (!this.bloco.trim().isEmpty() && !this.unidade.trim().isEmpty()) {
				this.clienteBEAN = this.cdDAO.listarClienteNovo(this.condominio, this.bloco, this.unidade);
				if (this.clienteBEAN != null) {

					if (this.clienteBEAN.getLogradouro() != null
							&& !this.clienteBEAN.getLogradouro().trim().isEmpty()) {
						this.logradouroBEAN.setCodigo(this.clienteBEAN.getLogradouro().trim());
					}
					if (this.clienteBEAN.getMunicipio() > 0) {
						this.cidadeBEAN.setCodigo(this.clienteBEAN.getMunicipio());
					}
					if (this.clienteBEAN.getCnpjCpf() > 0) {
						CorpoDiretivoAUX cda = new CorpoDiretivoAUX();
						if (this.clienteBEAN.getTipoPessoa().trim().equals("F")) {
							this.cpf = String.valueOf((long) this.clienteBEAN.getCnpjCpf());
							this.cpf = cda.retornaCpfCnpj(this.cpf, 1);
						} else {
							this.cnpj = String.valueOf(this.clienteBEAN.getCnpjCpf());
							this.cnpj = cda.retornaCpfCnpj(this.cnpj, 2);
						}
					} else {
						this.cpf = "";
						this.cnpj = "";
					}
					if (this.clienteBEAN.getCep() >= 0) {
						int cepInt = (int) this.clienteBEAN.getCep();
						this.cep = String.valueOf(cepInt);
						if (this.cep.length() == 7) {
							this.cep = "0" + this.cep;
						} else if (this.cep.length() == 6) {
							this.cep = "00" + this.cep;
						} else if (this.cep.length() == 5) {
							this.cep = "000" + this.cep;
						} else if (this.cep.length() == 4) {
							this.cep = "0000" + this.cep;
						} else if (this.cep.length() == 3) {
							this.cep = "00000" + this.cep;
						} else if (this.cep.length() == 2) {
							this.cep = "000000" + this.cep;
						} else if (this.cep.length() == 1) {
							this.cep = "0000000" + this.cep;
						}
					}
					if (this.clienteBEAN.getCodigo() > 0) {
						this.listaDeTelcli = this.cdDAO.listarIltelcli(this.clienteBEAN.getCodigo());
					}
				}
			}
		}
	}

	public void alterarTelcli() {
		this.cdDAO = new CorpoDiretivoDAO();
		if (this.telcliBEAN.getTipo() > 0) {
			if (!this.telcliBEAN.getTelEmail().trim().isEmpty()) {
				this.telcliBEAN.setFlagCadastro(0);
				this.telcliBEAN.setBoleto_envio(1);
				this.cdDAO.alterarContato(this.telcliBEAN, this.sessaoMB, this.condominio);
				this.listaDeTelcli = this.cdDAO.listarIltelcli(telcliBEAN.getCliente());
				this.msgAlterado();
			} else {
				this.msgErro();
			}
		} else {
			this.msgErro();
		}
	}

	public void alterarTelcli2() {
		if (this.telcliBEAN.getTipo() > 0) {
			if (!this.telcliBEAN.getTelEmail().trim().isEmpty()) {
				for (int i = 0; i < this.listaDeTelcli.size(); i++) {
					if (this.listaDeTelcli.get(i).getDfrecnum() == this.telcliBEAN.getDfrecnum()) {
						this.listaDeTelcli.remove(i);
						this.listaDeTelcli.add(this.telcliBEAN);
					}
				}
				this.msgAlterado();
			} else {
				this.msgErro();
			}
		} else {
			this.msgErro();
		}
	}

	public void alterarIlclient() {
		this.cdDAO = new CorpoDiretivoDAO();
		if (this.clienteBEAN != null) {

			if (this.clienteBEAN.getCodigo() > 0) {
				if (this.clienteBEAN.getTipoPessoa().trim().equals("F")) {
					if (this.cpf != null && !this.cpf.trim().isEmpty()) {
						String cpf1 = this.cpf.replace(".", "");
						String cpf2 = cpf1.replace("-", "");
						this.clienteBEAN.setCnpjCpf(Double.valueOf(cpf2));
					} else {
						this.clienteBEAN.setCnpjCpf(0);
					}
				}
				if (this.clienteBEAN.getTipoPessoa().trim().equals("J")) {
					if (this.cnpj != null && !this.cnpj.trim().isEmpty()) {
						String cnpj1 = this.cnpj.replace(".", "");
						String cnpj2 = cnpj1.replace("/", "");
						String cnpj3 = cnpj2.replace("-", "");
						this.clienteBEAN.setCnpjCpf(Double.valueOf(cnpj3));
						this.clienteBEAN.setNroIdentidade("");
						this.clienteBEAN.setTipoIdentidade("");
						this.clienteBEAN.setProfissao("");
						this.clienteBEAN.setOrgaoEmissor("");
						this.clienteBEAN.setDataNascimento(null);
					} else {
						this.clienteBEAN.setCnpjCpf(0);
						this.clienteBEAN.setNroIdentidade("");
						this.clienteBEAN.setTipoIdentidade("");
						this.clienteBEAN.setProfissao("");
						this.clienteBEAN.setOrgaoEmissor("");
						this.clienteBEAN.setDataNascimento(null);
					}
				}
				String cep = this.cep.replace("-", "");
				this.clienteBEAN.setCep(Double.valueOf(cep));
				this.clienteBEAN.setMunicipio(this.cidadeBEAN.getCodigo());
				if (!this.listaDeCidade.isEmpty()) {
					for (intra_ilmunic cid : listaDeCidade) {
						if (this.cidadeBEAN.getCodigo() == cid.getCodigo()) {
							this.clienteBEAN.setCidade(cid.getCidade());
							this.clienteBEAN.setEstado(cid.getEstado());
						}
					}
				}
				if (this.clienteBEAN.getDataNascimento() != null) {
					DateTime dt = new DateTime(this.clienteBEAN.getDataNascimento());
					this.clienteBEAN.setDiaAni(dt.getDayOfMonth());
					this.clienteBEAN.setMesAni(dt.getMonthOfYear());
					this.clienteBEAN.setAnoAni((short) dt.getYear());
				} else {
					this.clienteBEAN.setDiaAni(0);
					this.clienteBEAN.setMesAni(0);
					this.clienteBEAN.setAnoAni((short) 0);
				}

				CorpoDiretivoAUX cda = new CorpoDiretivoAUX();

				this.clienteBEAN.setNome(cda.retornaNome(this.clienteBEAN.getNome().trim(), (this.funcao == 0 ? 1 : this.funcao)));

				this.cdDAO.alterarIlclient(this.clienteBEAN, this.sessaoMB, this.condominio);

				this.cdDAO.updateCndMemb(this.clienteBEAN, this.funcao, this.condominio);
				this.listaDeCorpoDiretivo = this.cdDAO.listarCorpoDiretivo(this.condominio);

				if (this.bloco != null && !this.bloco.trim().isEmpty()) {
					if (this.unidade != null && !this.unidade.trim().isEmpty()) {
						intra_ilclient cli = this.cdDAO.listarClienteNovo(this.condominio, this.bloco, this.unidade);
						if (!this.unidade.trim().isEmpty()) {
							cli.setNome(cda.retornaNome(cli.getNome().trim(), this.funcao));
							
							if(this.clienteBEAN.getCodigo() == cli.getCodigo()){
								String cliUnida = (cda.retornaNome(cli.getNome(), (this.funcao == 0 ? 1 : this.funcao)));
								this.cdDAO.updateIlclienteNomeCndUnida(cli.getCodigo(), bloco, unidade, cliUnida);
							}else{
								String cliUnida = (cda.retornaNome(cli.getNome(), (this.funcao == 0 ? 1 : this.funcao)));
								this.cdDAO.updateIlclienteNomeCndUnida(bloco, unidade, cliUnida, this.condominio);
								this.cdDAO.updateIlclienteNome(cli.getCodigo(), cliUnida);
							}
						}
					}
				}
				this.msgSalvo();
			} else {
				this.msgErro();
			}
		} else {
			this.msgErro();
		}
	}

	public void excluirContatoTelcli() {
		if (this.telcliBEAN != null) {
			if (this.telcliBEAN.getDfrecnum() > 0) {
				this.cdDAO = new CorpoDiretivoDAO();
				this.cdDAO.excluirContato(this.telcliBEAN, this.sessaoMB, this.condominio);
				this.listaDeTelcli = this.cdDAO.listarIltelcli(telcliBEAN.getCliente());
				this.msgExclusao();
			} else {
				this.msgErro();
			}
		} else {
			this.msgErro();
		}
	}

	public void excluirContatoTelcli2() {
		if (this.telcliBEAN != null) {
			if (this.telcliBEAN.getDfrecnum() > 0) {
				for (int i = 0; i < this.listaDeTelcli.size(); i++) {
					if (this.telcliBEAN.getDfrecnum() == this.listaDeTelcli.get(i).getDfrecnum()) {
						this.listaDeTelcli.remove(i);
					}
				}
				this.msgExclusao();
			} else {
				this.msgErro();
			}
		} else {
			this.msgErro();
		}
	}

	public void excluirMembroDoCorpoDiretivo() {
		if (this.corpoDiretivoBEAN != null) {
			if (this.corpoDiretivoBEAN.getCodigoCliente() > 0) {
				this.cdDAO = new CorpoDiretivoDAO();
				this.cdDAO.excluirDoCorpoDiretivo(this.corpoDiretivoBEAN, this.sessaoMB, this.condominio);

				if (this.corpoDiretivoBEAN.getUnidade() != null
						&& !this.corpoDiretivoBEAN.getUnidade().trim().isEmpty()) {
					intra_ilclient cli = this.cdDAO.listarClienteCndUnida(this.corpoDiretivoBEAN.getBloco(),
							this.corpoDiretivoBEAN.getUnidade(), this.condominio);
					String nome1 = cli.getNome().replace("*", "");
					String nome2 = nome1.replace("=", "");

					this.cdDAO.updateIlclienteNome(cli.getCodigo(), nome2);
					if (!this.corpoDiretivoBEAN.getBloco().trim().isEmpty()
							&& !this.corpoDiretivoBEAN.getUnidade().trim().isEmpty()) {
						this.cdDAO.updateIlclienteNomeCndUnida(cli.getCodigo(), this.corpoDiretivoBEAN.getBloco(),
								this.corpoDiretivoBEAN.getUnidade(), nome2);
					}
				}

				this.listaDeCorpoDiretivo = this.cdDAO.listarCorpoDiretivo(this.condominio);
				this.msgExclusao();
			} else {
				this.msgErro();
			}
		} else {
			this.msgErro();
		}
	}

	public void addContatoTelcli() {

		if (this.tipoContato > 0) {
			if (this.telEmail != null && !this.telEmail.trim().isEmpty()) {
				this.cdDAO = new CorpoDiretivoDAO();
				this.cdDAO.adicionarContato(this.clienteBEAN.getCodigo(), this.tipoContato, this.telEmail,
						this.observacao);
				this.listaDeTelcli = this.cdDAO.listarIltelcli(clienteBEAN.getCodigo());
				this.telEmail = "";
				this.observacao = "";
				this.listaDeContatosDescricao = null;
				this.msgAdicinado();
			} else {
				this.msgErro();
			}
		} else {
			this.msgErro();
		}
	}

	public void addContatoTelcli2() {

		if (this.tipoContato > 0) {
			if (this.telEmail != null && !this.telEmail.trim().isEmpty()) {
				if (this.listaDeTelcli == null) {
					this.listaDeTelcli = new ArrayList<>();
				}
				intra_iltelcli t = new intra_iltelcli();
				t.setTelEmail(this.telEmail);
				t.setTipo(this.tipoContato);
				t.setObservacao(this.observacao);
				t.setDfrecnum((int) (Math.random() * 100000));
				this.listaDeTelcli.add(t);
				this.telEmail = "";
				this.tipoContato = 1;
				this.observacao = "";
				this.msgAdicinado();
			} else {
				this.msgErro();
			}
		} else {
			this.msgErro();
		}
	}

	public void alteraNome() {
		if (this.validaNome == 1) {
			this.alterar2();
		} else {
			this.updateCodigoCli = this.clienteBEAN.getCodigo();
			this.updateNomeUni = this.clienteBEAN.getNome();
			this.clienteBEAN = new intra_ilclient();
			this.clienteBEAN.setTipoPessoa("F");
			this.cpf = "";
			this.cnpj = "";
			this.listaDeTelcli = null;
		}
	}

	public void addNovoMembroCorpoDiretivo() {
		String nomeFinal = "";
		CorpoDiretivoAUX cda = new CorpoDiretivoAUX();
		if (this.condominio > 0) {
			if (this.clienteBEAN != null) {

				if (this.clienteBEAN.getNome() == null) {
					this.clienteBEAN.setNome(this.outroNome);
				} else if (this.clienteBEAN.getNome().trim().isEmpty()) {
					this.clienteBEAN.setNome(this.outroNome);
				}
				if (this.clienteBEAN.getTipoPessoa().trim().equals("J")) {
					this.clienteBEAN.setEstadoCivil(1);
				}

				if (cda.validadorCampos(this.clienteBEAN, this.logradouroBEAN, this.cidadeBEAN, this.cep)) {

					if (this.validaNome == 2) {
						if (this.clienteBEAN.getTipoPessoa().trim().equals("F")) {
							if (this.cpf != null && !this.cpf.trim().isEmpty()) {
								String cpf1 = this.cpf.replace(".", "");
								String cpf2 = cpf1.replace("-", "");
								this.validaCPFCNPJ = new ValidaCPFCNPJ();
								if (this.validaCPFCNPJ.validaCPF(this.cpf)) {
									this.clienteBEAN.setCnpjCpf(Double.valueOf(cpf2));
								} else {
									this.clienteBEAN.setCnpjCpf(0);
								}
							} else {
								this.clienteBEAN.setCnpjCpf(0);
							}
						}
						if (this.clienteBEAN.getTipoPessoa().trim().equals("J")) {
							if (this.cnpj != null && !this.cnpj.trim().isEmpty()) {
								String cnpj1 = this.cnpj.replace(".", "");
								String cnpj2 = cnpj1.replace("/", "");
								String cnpj3 = cnpj2.replace("-", "");
								this.clienteBEAN.setCnpjCpf(Double.valueOf(cnpj3));
								this.clienteBEAN.setNroIdentidade("");
								this.clienteBEAN.setTipoIdentidade("");
								this.clienteBEAN.setProfissao("");
								this.clienteBEAN.setOrgaoEmissor("");
								this.clienteBEAN.setDataNascimento(null);
							} else {
								this.clienteBEAN.setCnpjCpf(0);
								this.clienteBEAN.setNroIdentidade("");
								this.clienteBEAN.setTipoIdentidade("");
								this.clienteBEAN.setProfissao("");
								this.clienteBEAN.setOrgaoEmissor("");
								this.clienteBEAN.setDataNascimento(null);
							}
						}
						if (this.cep.length() > 0) {
							String cep = this.cep.replace("-", "");
							this.clienteBEAN.setCep(Double.valueOf(cep));
						} else {
							this.clienteBEAN.setCep(0);
						}
						this.clienteBEAN.setMunicipio(this.cidadeBEAN.getCodigo());
						if (!this.listaDeCidade.isEmpty()) {
							for (intra_ilmunic cid : listaDeCidade) {
								if (this.cidadeBEAN.getCodigo() == cid.getCodigo()) {
									this.clienteBEAN.setCidade(cid.getCidade());
									this.clienteBEAN.setEstado(cid.getEstado());
								}
							}
						}

						if (this.clienteBEAN.getDataNascimento() != null) {
							DateTime dt = new DateTime(this.clienteBEAN.getDataNascimento());
							this.clienteBEAN.setDiaAni(dt.getDayOfMonth());
							this.clienteBEAN.setMesAni(dt.getMonthOfYear());
							this.clienteBEAN.setAnoAni((short) dt.getYear());
						} else {
							this.clienteBEAN.setDiaAni(0);
							this.clienteBEAN.setMesAni(0);
							this.clienteBEAN.setAnoAni((short) 0);
						}

						if (this.validaNome == 1) {
							this.cdDAO.alterarIlclient(this.clienteBEAN, this.sessaoMB, this.condominio);
							this.cdDAO.updateCndMemb(this.clienteBEAN, (this.funcao == 0 ? 1 : this.funcao),
									this.condominio);
						} else {
							if (this.validaNome == 2) {
								nomeFinal = cda.retornaNome(this.outroNome, (this.funcao == 0 ? 1 : this.funcao));
								if (this.funcao > 0) {
									if (!this.bloco.trim().isEmpty() && !this.unidade.trim().isEmpty()) {
										String nomeCndUnida = this.cdDAO.retornaNomeCndUnida(this.bloco, this.unidade,
												condominio);
										this.cdDAO.updateCndUnidaNomeCli(
												cda.retornaNome(nomeCndUnida, (this.funcao == 0 ? 1 : this.funcao)),
												this.bloco, this.unidade, this.condominio);
									}
								}
							} else {
								nomeFinal = cda.retornaNome(this.clienteBEAN.getNome(),
										(this.funcao == 0 ? 1 : this.funcao));
							}

							this.clienteBEAN.setNome(nomeFinal);

							for (intra_ilmunic cid : listaDeCidade) {
								if (this.cidadeBEAN.getCodigo() == cid.getCodigo()) {
									this.clienteBEAN.setCidade(cid.getCidade());
									this.clienteBEAN.setEstado(cid.getEstado());
									this.clienteBEAN.setMunicipio(cid.getCodigo());
								}
							}

							this.clienteBEAN.setUsuario(766844);

							int codNovoCli = this.cdDAO.novoCodigoCliente();

							this.clienteBEAN.setLogradouro(this.logradouroBEAN.getCodigo());
							this.clienteBEAN.setCodigo(codNovoCli);
							this.clienteBEAN.setEndCorresp("N");
							this.clienteBEAN.setImpEtiq("S");
							this.clienteBEAN.setResideExterior("N");
							this.clienteBEAN.setDataCadastro(new Date());

							this.cdDAO.addNovoCliente(this.clienteBEAN, this.sessaoMB, this.condominio);

							this.corpoDiretivoBEAN = new intra_corpo_diretivo();
							this.corpoDiretivoBEAN.setCodigoCliente(this.clienteBEAN.getCodigo());

							this.cndmembBEAN.setCliente(this.clienteBEAN.getCodigo());
							this.cndmembBEAN.setBloco("0");
							this.cndmembBEAN.setCargo((this.funcao == 0 ? 1 : this.funcao));
							this.cndmembBEAN.setCondominio(this.condominio);
							this.cndmembBEAN.setSituacao(0);
							this.cndmembBEAN.setTermo(1);
							this.cndmembBEAN.setCondResp(0);
							this.cndmembBEAN.setValorBase(0);
							this.cndmembBEAN.setValorOutros(0);
							if (this.funcao != 0) {
								this.cndmembBEAN.setCliBloco(this.bloco.trim());
								this.cndmembBEAN.setCliUnidade(this.unidade.trim());
							}

							this.cdDAO.addCorpoDiretivo(this.cndmembBEAN);

							if (this.listaDeTelcli != null && !this.listaDeTelcli.isEmpty()) {
								for (intra_iltelcli telcli : listaDeTelcli) {
									intra_iltelcli t = new intra_iltelcli();
									t.setCliente(codNovoCli);
									t.setObservacao(telcli.getObservacao());
									t.setTelEmail(telcli.getTelEmail());
									t.setTipo(telcli.getTipo());
									t.setFlagCadastro(0);
									t.setBoleto_envio(0);
									this.cdDAO.adicionarContato(t.getCliente(), t.getTipo(), t.getTelEmail(),
											t.getObservacao());
								}
							}

							this.listaDeCorpoDiretivo = this.cdDAO.listarCorpoDiretivo(this.condominio);

							if (this.validaNome == 2 && this.funcao > 0) {
								this.cdDAO.updateNomeDaUnidade(
										cda.retornaNome(this.updateNomeUni, (this.funcao == 0 ? 1 : this.funcao)),
										this.updateCodigoCli);
							}

							this.voltar2();
						}
						this.msgSalvo();
						this.telEmail = "";
						this.observacao = "";
						this.outroNome = "";
						this.listaDeContatosDescricao = null;

					} else {
						this.alterarIlclient();
						if (this.validaNome == 1 && this.funcao > 0) {
							if (!this.bloco.trim().isEmpty() && !this.unidade.trim().isEmpty()) {
								this.cdDAO.updateCndUnidaNomeCli(
										cda.retornaNome(this.clienteBEAN.getNome(),
												(this.funcao == 0 ? 1 : this.funcao)),
										this.clienteBEAN.getCodigo(), this.bloco, this.unidade);
							}
						}
						this.corpoDiretivoBEAN = new intra_corpo_diretivo();
						this.corpoDiretivoBEAN.setCodigoCliente(this.clienteBEAN.getCodigo());
						this.cndmembBEAN.setCliente(this.clienteBEAN.getCodigo());
						this.cndmembBEAN.setBloco("0");
						this.cndmembBEAN.setCargo((this.funcao == 0 ? 1 : this.funcao));
						this.cndmembBEAN.setCondominio(this.condominio);
						this.cndmembBEAN.setSituacao(0);
						this.cndmembBEAN.setTermo(1);
						this.cndmembBEAN.setCondResp(0);
						this.cndmembBEAN.setValorBase(0);
						this.cndmembBEAN.setValorOutros(0);
						if (this.funcao != 0) {
							this.cndmembBEAN.setCliBloco(this.bloco.trim());
							this.cndmembBEAN.setCliUnidade(this.unidade.trim());
						}

						this.cdDAO.addCorpoDiretivo(this.cndmembBEAN);

						this.listaDeCorpoDiretivo = this.cdDAO.listarCorpoDiretivo(this.condominio);
						this.voltar2();
					}
				} else {
					this.msgObrigatorio();
				}
			} else {
				this.msgErro();
			}
		} else {
			this.msgCondominio();
		}
	}

	public void pesquisarSindicoProfissional() {
		this.cdDAO = new CorpoDiretivoDAO();
		this.listaDeSindicoProfissional = this.cdDAO.listaDeIlclient(this.nomeSindico);
	}

	public void updatePesquisaSindico() {
		this.cdDAO = new CorpoDiretivoDAO();
		this.logradouroBEAN.setCodigo(this.clienteBEAN.getLogradouro().trim());

		if (this.clienteBEAN.getCnpjCpf() > 0) {
			CorpoDiretivoAUX cda = new CorpoDiretivoAUX();
			if (this.clienteBEAN.getTipoPessoa().trim().equals("F")) {
				this.cpf = String.valueOf((long) this.clienteBEAN.getCnpjCpf());
				this.cpf = cda.retornaCpfCnpj(this.cpf, 1);
			} else {
				this.cnpj = String.valueOf(this.clienteBEAN.getCnpjCpf());
				this.cnpj = cda.retornaCpfCnpj(this.cnpj, 2);
			}
		} else {
			this.cpf = "";
			this.cnpj = "";
		}

		int cepInt = (int) this.clienteBEAN.getCep();
		this.cep = String.valueOf(cepInt);
		if (this.cep.length() == 7) {
			this.cep = "0" + this.cep;
		} else if (this.cep.length() == 6) {
			this.cep = "00" + this.cep;
		} else if (this.cep.length() == 5) {
			this.cep = "000" + this.cep;
		} else if (this.cep.length() == 4) {
			this.cep = "0000" + this.cep;
		} else if (this.cep.length() == 3) {
			this.cep = "00000" + this.cep;
		} else if (this.cep.length() == 2) {
			this.cep = "000000" + this.cep;
		} else if (this.cep.length() == 1) {
			this.cep = "0000000" + this.cep;
		}

		this.listaDeTelcli = this.cdDAO.listarIltelcli(this.clienteBEAN.getCodigo());
	}

	public void validaCPF() {
		this.validaCPFCNPJ = new ValidaCPFCNPJ();
		if (this.cpf != null && !this.cpf.trim().isEmpty()) {
			if (validaCPFCNPJ.validaCPF(this.cpf)) {
				this.msgCPFValido();
				RequestContext.getCurrentInstance().update("frmCD:msg5");
			} else {
				RequestContext.getCurrentInstance()
						.execute("document.getElementById('frmCD:iptmCPF3').style.borderColor='#cd0a0a';");
				this.msgCPFInvalido();
				RequestContext.getCurrentInstance().update("frmCD:msg5");
			}
		} else if (this.cnpj != null && !this.cnpj.trim().isEmpty()) {
			if (validaCPFCNPJ.validaCNPJ(this.cnpj)) {
				this.msgCNPJValido();
				RequestContext.getCurrentInstance().update("frmCD:msg5");
			} else {
				this.msgCNPJInvalido();
				RequestContext.getCurrentInstance()
						.execute("document.getElementById('frmCD:iptmCNPJ3').style.borderColor='#cd0a0a';");
				RequestContext.getCurrentInstance().update("frmCD:msg5");
			}
			this.clienteBEAN.setEstadoCivil(1);
		}
	}

	public void validaCPFAlterar() {
		this.validaCPFCNPJ = new ValidaCPFCNPJ();
		if (this.cpf != null && !this.cpf.trim().isEmpty()) {
			if (validaCPFCNPJ.validaCPF(this.cpf)) {
				this.msgCPFValido();
				RequestContext.getCurrentInstance().update("frmCD:msg6");
			} else {
				RequestContext.getCurrentInstance()
						.execute("document.getElementById('frmCD:iptmCPF').style.borderColor='#cd0a0a';");
				this.msgCPFInvalido();
				RequestContext.getCurrentInstance().update("frmCD:msg6");
			}
		} else if (this.cnpj != null && !this.cnpj.trim().isEmpty()) {
			if (validaCPFCNPJ.validaCNPJ(this.cnpj)) {
				this.msgCNPJValido();
				RequestContext.getCurrentInstance().update("frmCD:msg6");
			} else {
				this.msgCNPJInvalido();
				RequestContext.getCurrentInstance()
						.execute("document.getElementById('frmCD:iptmCNPJ').style.borderColor='#cd0a0a';");
				RequestContext.getCurrentInstance().update("frmCD:msg6");
			}
			this.clienteBEAN.setEstadoCivil(1);
		}
	}

	public boolean validaNomeSindico() {
		if (this.validaNome == 1 && this.sindicoExistente == 1 && this.valida == false) {
			return false;
		} else {
			return true;
		}
	}

	public void nomeUnidade() {
		this.validaNome = 1;
		this.sindicoExistente = 2;
		this.valida = true;
	}

	public void sindicoExistenteNao() {
		this.valida = false;
	}

	public void limparCPFCNPJ() {
		this.cpf = "";
		this.cnpj = "";
	}

	public void alterarPagina() {
		this.pagina1 = 1;
		this.pagina2 = 0;
		this.pagina3 = 0;
		this.pagina4 = 0;

		this.inicioMandato = null;
		this.finalMandato = null;
		this.dtAprovacao = null;
		this.prxAprovacao = null;
		this.prazoAssembleia = "";
		this.quorumPrimeira = "";
		this.segundaChamada = "";
		this.quorumSegunda = "";
		this.obs1 = "";
	}

	public void proximo1() {
		this.pagina1 = 0;
		this.pagina2 = 2;
		this.pagina3 = 0;
		this.pagina4 = 0;
	}

	public void proximo2() {
		this.pagina1 = 0;
		this.pagina2 = 0;
		this.pagina3 = 3;
		this.pagina4 = 0;
	}

	public void proximo3() {
		this.funcao = 0;
		this.bloco = "";
		this.unidade = "";
		this.validaNome = 2;
		this.clienteBEAN = new intra_ilclient();
		this.listaDeTelcli = new ArrayList<>();

		this.pagina1 = 0;
		this.pagina2 = 0;
		this.pagina3 = 0;
		this.pagina4 = 4;
	}

	public void voltar1() {
		this.cndblocoBEAN = new intra_cndbloco();
		this.cndblocoBEAN = this.cdDAO.listarCndBloco(this.condominio);
		this.pagina1 = 1;
		this.pagina2 = 0;
		this.pagina3 = 0;
		this.pagina4 = 0;
	}

	public void voltar2() {
		this.pagina1 = 0;
		this.pagina2 = 2;
		this.pagina3 = 0;
		this.pagina4 = 0;
	}

	public void voltar3() {
		this.pagina1 = 0;
		this.pagina2 = 0;
		this.pagina3 = 3;
		this.pagina4 = 0;
	}

}
