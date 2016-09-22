package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.oma.intranet.dao.ComercialDAO;
import br.com.oma.intranet.entidades.intra_cptadministracao2;
import br.com.oma.intranet.entidades.intra_cptcondo2;
import br.com.oma.intranet.entidades.intra_cptcondo_foto2;
import br.com.oma.intranet.entidades.intra_cptconstrutora2;
import br.com.oma.intranet.entidades.intra_cptproposta2;
import br.com.oma.intranet.entidades.intra_cptsindico2;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ManagedBean(name = "cmcoMB")
@ViewScoped
public class ComercialCOMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3706627177723057421L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private ComercialDAO cmDAO;
	private intra_cptcondo2 icBEAN = new intra_cptcondo2();
	private intra_cptcondo2 condominioSelecionado;
	private intra_cptadministracao2 admBEAN = new intra_cptadministracao2();
	private intra_cptadministracao2 administracaoSelecionada;
	private intra_cptproposta2 propBEAN = new intra_cptproposta2();
	private intra_cptproposta2 propostaSelecionada;
	private intra_cptsindico2 sindBEAN = new intra_cptsindico2();
	private intra_cptsindico2 sindicoSelecionado;
	private intra_cptcondo_foto2 fotoCondominio = new intra_cptcondo_foto2();
	private intra_cptcondo_foto2 fotoCondominioSelecionada;

	// ATRIBUTOS
	private List<intra_cptcondo2> listaCondominios, listaCondominios2, listaCondominiosSelecionados;
	private List<intra_cptconstrutora2> listaConstrutora2;
	private List<intra_cptadministracao2> listaAdministracao;
	private List<intra_cptproposta2> listaProposta;
	private List<intra_cptsindico2> listaSindico;
	private List<intra_cptcondo_foto2> listaImagem;
	private List<intra_cptcondo_foto2> listaArquivos = new ArrayList<intra_cptcondo_foto2>();
	private List<String> listaCidades2, listaCidades3, listaCidades4, listaCidades5, listaLogradouro, listaEstado;
	private String logradouro, descricao, estado1, estado2, estado3, estado4;
	private String filtro = "Todos";
	private String tipo, regiao, cep1, cep2;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	// GET X SET

	public ComercialDAO getCmDAO() {
		return cmDAO;
	}

	public void setCmDAO(ComercialDAO cmDAO) {
		this.cmDAO = cmDAO;
	}

	public intra_cptcondo2 getIcBEAN() {
		return icBEAN;
	}

	public void setIcBEAN(intra_cptcondo2 icBEAN) {
		this.icBEAN = icBEAN;
	}

	public intra_cptcondo2 getCondominioSelecionado() {
		return condominioSelecionado;
	}

	public void setCondominioSelecionado(intra_cptcondo2 condominioSelecionado) {
		this.condominioSelecionado = condominioSelecionado;
	}

	public intra_cptadministracao2 getAdmBEAN() {
		return admBEAN;
	}

	public void setAdmBEAN(intra_cptadministracao2 admBEAN) {
		this.admBEAN = admBEAN;
	}

	public intra_cptadministracao2 getAdministracaoSelecionada() {
		return administracaoSelecionada;
	}

	public void setAdministracaoSelecionada(intra_cptadministracao2 administracaoSelecionada) {
		this.administracaoSelecionada = administracaoSelecionada;
	}

	public intra_cptproposta2 getPropBEAN() {
		return propBEAN;
	}

	public void setPropBEAN(intra_cptproposta2 propBEAN) {
		this.propBEAN = propBEAN;
	}

	public intra_cptproposta2 getPropostaSelecionada() {
		return propostaSelecionada;
	}

	public void setPropostaSelecionada(intra_cptproposta2 propostaSelecionada) {
		this.propostaSelecionada = propostaSelecionada;
	}

	public intra_cptsindico2 getSindBEAN() {
		return sindBEAN;
	}

	public void setSindBEAN(intra_cptsindico2 sindBEAN) {
		this.sindBEAN = sindBEAN;
	}

	public intra_cptsindico2 getSindicoSelecionado() {
		return sindicoSelecionado;
	}

	public void setSindicoSelecionado(intra_cptsindico2 sindicoSelecionado) {
		this.sindicoSelecionado = sindicoSelecionado;
	}

	public intra_cptcondo_foto2 getFotoCondominio() {
		return fotoCondominio;
	}

	public void setFotoCondominio(intra_cptcondo_foto2 fotoCondominio) {
		this.fotoCondominio = fotoCondominio;
	}

	public intra_cptcondo_foto2 getFotoCondominioSelecionada() {
		return fotoCondominioSelecionada;
	}

	public void setFotoCondominioSelecionada(intra_cptcondo_foto2 fotoCondominioSelecionada) {
		this.fotoCondominioSelecionada = fotoCondominioSelecionada;
	}

	public List<intra_cptcondo2> getListaCondominios() {
		try {
			this.cmDAO = new ComercialDAO();
			this.listaCondominios = this.cmDAO.listaCondominiosTop1000();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaCondominios;
	}

	public void setListaCondominios(List<intra_cptcondo2> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public List<intra_cptcondo2> getListaCondominios2() {
		try {
			this.cmDAO = new ComercialDAO();
			this.listaCondominios2 = this.cmDAO.listaCondominios2();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaCondominios2;
	}

	public void setListaCondominios2(List<intra_cptcondo2> listaCondominios2) {
		this.listaCondominios2 = listaCondominios2;
	}

	public List<intra_cptcondo2> getListaCondominiosSelecionados() {
		return listaCondominiosSelecionados;
	}

	public void setListaCondominiosSelecionados(List<intra_cptcondo2> listaCondominiosSelecionados) {
		this.listaCondominiosSelecionados = listaCondominiosSelecionados;
	}

	public List<intra_cptconstrutora2> getListaConstrutora2() {
		try {
			this.cmDAO = new ComercialDAO();
			this.listaConstrutora2 = this.cmDAO.listaConstrutora();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaConstrutora2;
	}

	public void setListaConstrutora2(List<intra_cptconstrutora2> listaConstrutora2) {
		this.listaConstrutora2 = listaConstrutora2;
	}

	public List<intra_cptadministracao2> getListaAdministracao() {
		return listaAdministracao;
	}

	public void setListaAdministracao(List<intra_cptadministracao2> listaAdministracao) {
		this.listaAdministracao = listaAdministracao;
	}

	public List<intra_cptproposta2> getListaProposta() {
		return listaProposta;
	}

	public void setListaProposta(List<intra_cptproposta2> listaProposta) {
		this.listaProposta = listaProposta;
	}

	public List<intra_cptsindico2> getListaSindico() {
		return listaSindico;
	}

	public void setListaSindico(List<intra_cptsindico2> listaSindico) {
		this.listaSindico = listaSindico;
	}

	public List<intra_cptcondo_foto2> getListaImagem() {
		if (this.listaImagem == null) {
			try {
				this.cmDAO = new ComercialDAO();
				this.listaImagem = this.cmDAO.listarImagem();
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}
		return listaImagem;
	}

	public void setListaImagem(List<intra_cptcondo_foto2> listaImagem) {
		this.listaImagem = listaImagem;
	}

	public List<intra_cptcondo_foto2> getListaArquivos() {
		return listaArquivos;
	}

	public void setListaArquivos(List<intra_cptcondo_foto2> listaArquivos) {
		this.listaArquivos = listaArquivos;
	}

	public List<String> getListaCidades2() {
		try {
			if (this.estado1 != null) {
				this.cmDAO = new ComercialDAO();
				this.listaCidades2 = this.cmDAO.listaCidades(this.alterarEstado1());
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaCidades2;
	}

	public void setListaCidades2(List<String> listaCidades2) {
		this.listaCidades2 = listaCidades2;
	}

	public List<String> getListaCidades3() {
		try {
			if (this.estado2 != null) {
				this.cmDAO = new ComercialDAO();
				this.listaCidades3 = this.cmDAO.listaCidades(this.alterarEstado2());
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaCidades3;
	}

	public void setListaCidades3(List<String> listaCidades3) {
		this.listaCidades3 = listaCidades3;
	}

	public List<String> getListaCidades4() {
		try {
			if (this.estado3 != null) {
				this.cmDAO = new ComercialDAO();
				this.listaCidades4 = this.cmDAO.listaCidades(this.alterarEstado3());
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaCidades4;
	}

	public void setListaCidades4(List<String> listaCidades4) {
		this.listaCidades4 = listaCidades4;
	}

	public List<String> getListaCidades5() {
		try {
			if (this.estado4 != null) {
				this.cmDAO = new ComercialDAO();
				this.listaCidades5 = this.cmDAO.listaCidades(this.alterarEstado4());
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaCidades5;
	}

	public void setListaCidades5(List<String> listaCidades5) {
		this.listaCidades5 = listaCidades5;
	}

	public List<String> getListaLogradouro() {
		if (this.listaLogradouro == null) {
			this.listaLogradouro = new ArrayList<>();
			this.listaLogradouro.add("ACESSO");
			this.listaLogradouro.add("ALAMEDA");
			this.listaLogradouro.add("AVENIDA");
			this.listaLogradouro.add("BAIRRO");
			this.listaLogradouro.add("BECO");
			this.listaLogradouro.add("CALÇADA");
			this.listaLogradouro.add("CAMINHO");
			this.listaLogradouro.add("CHÁCARA");
			this.listaLogradouro.add("DOUTOR");
			this.listaLogradouro.add("ESTRADA");
			this.listaLogradouro.add("GENERAL");
			this.listaLogradouro.add("LADEIRA");
			this.listaLogradouro.add("LARGO");
			this.listaLogradouro.add("PASSAGEM");
			this.listaLogradouro.add("PRAÇA");
			this.listaLogradouro.add("PADRE");
			this.listaLogradouro.add("PARQUE");
			this.listaLogradouro.add("PROFESSOR");
			this.listaLogradouro.add("RODOVIA");
			this.listaLogradouro.add("RUA");
			this.listaLogradouro.add("TRAVESSA");
			this.listaLogradouro.add("TREVO");
			this.listaLogradouro.add("VILA");
			this.listaLogradouro.add("VIADUTO");
			this.listaLogradouro.add("VIA");
			this.listaLogradouro.add("VIELA");
		}
		return listaLogradouro;
	}

	public void setlistaLogradouro(List<String> listaLogradouro) {
		this.listaLogradouro = listaLogradouro;
	}

	public List<String> getListaEstado() {
		if (this.listaEstado == null) {
			this.listaEstado = new ArrayList<>();
			this.listaEstado.add("ACRE");
			this.listaEstado.add("ALAGOAS");
			this.listaEstado.add("AMAPÁ");
			this.listaEstado.add("AMAZONAS");
			this.listaEstado.add("BAHIA");
			this.listaEstado.add("CEARÁ");
			this.listaEstado.add("DISTRITO FEDERAL");
			this.listaEstado.add("ESPÍRITO SANTO");
			this.listaEstado.add("GOIÁS");
			this.listaEstado.add("MARANHÃO");
			this.listaEstado.add("MATO GROSSO");
			this.listaEstado.add("MATO GROSSO DO SUL");
			this.listaEstado.add("MINAS GERAIS");
			this.listaEstado.add("PARÁ");
			this.listaEstado.add("PARAÍBA");
			this.listaEstado.add("PARANÁ");
			this.listaEstado.add("PERNAMBUCO");
			this.listaEstado.add("PIAUÍ");
			this.listaEstado.add("RIO DE JANEIRO");
			this.listaEstado.add("RIO GRANDE DO NORTE");
			this.listaEstado.add("RIO GRANDE DO SUL");
			this.listaEstado.add("RONDÔNIA");
			this.listaEstado.add("RORAIMA");
			this.listaEstado.add("SANTA CATARINA");
			this.listaEstado.add("SÃO PAULO");
			this.listaEstado.add("SERGIPE");
			this.listaEstado.add("TOCANTINS");
		}
		return listaEstado;
	}

	public void setListaEstado(List<String> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEstado1() {
		return estado1;
	}

	public void setEstado1(String estado1) {
		this.estado1 = estado1;
	}

	public String getEstado2() {
		return estado2;
	}

	public void setEstado2(String estado2) {
		this.estado2 = estado2;
	}

	public String getEstado3() {
		return estado3;
	}

	public void setEstado3(String estado3) {
		this.estado3 = estado3;
	}

	public String getEstado4() {
		return estado4;
	}

	public void setEstado4(String estado4) {
		this.estado4 = estado4;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getCep1() {
		return cep1;
	}

	public void setCep1(String cep1) {
		this.cep1 = cep1;
	}

	public String getCep2() {
		return cep2;
	}

	public void setCep2(String cep2) {
		this.cep2 = cep2;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	// MÉTODOS

	// ↓ MÉTODO PARA ALTERAR CONDOMÍNIO ↓

	public void abreAlteraCondominio() {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/comercial/condominio/alteracao-de-condominios.xhtml?idCondominio="
							+ this.condominioSelecionado.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA CONSULTAR CONDOMÍNIO ↓

	public void abreConsultaCondominio() {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/comercial/condominio/consulta-de-condominios.xhtml?idCondominio="
							+ this.condominioSelecionado.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA PESQUISAR CONDOMINIO SELECIONADO ↓

	public void pesquisaCondominioSelecionado() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String idCondominio = params.get("idCondominio");
			if (idCondominio != null) {
				this.cmDAO = new ComercialDAO();
				this.icBEAN = this.cmDAO.pesquisaCondominioPorCodigo(Integer.parseInt(idCondominio));
				this.sindBEAN = this.cmDAO.pesquisaSindicoPorCodigo(Integer.parseInt(idCondominio));
				this.admBEAN = this.cmDAO.pesquisaAdministracaoPorCodigo(Integer.parseInt(idCondominio));
				this.propBEAN = this.cmDAO.pesquisaPropostaPorCodigo(Integer.parseInt(idCondominio));
				List<intra_cptcondo_foto2> l = this.cmDAO.pesquisaFotoCondoPorCodigo(this.icBEAN);
				if (l != null && l.size() > 0) {
					this.fotoCondominio = l.get(0);
				} else {
					this.fotoCondominio = null;
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA SALVAR CONDOMINIO ↓

	public void salvarCondominio() {
		try {
			this.cmDAO = new ComercialDAO();

			this.icBEAN.setNome("ED." + this.icBEAN.getNome().toUpperCase());
			this.icBEAN.setDataCadastro(new Date());
			this.icBEAN.setEndereco(this.icBEAN.getEndereco().toUpperCase());
			this.icBEAN.setCidade(this.icBEAN.getCidade().toUpperCase());
			this.icBEAN.setEstado(this.alterarEstado1());
			this.icBEAN.setCep(this.icBEAN.getCep());
			this.icBEAN.setCnpj(this.icBEAN.getCnpj());
			this.icBEAN.setBairro(this.icBEAN.getBairro().toUpperCase());
			if (this.icBEAN.getEstado() == 26) {
				this.icBEAN.setRegiao(this.zona().toUpperCase());
			} else {
				this.icBEAN.setRegiao("");
			}
			this.icBEAN.setTelPortaria(this.icBEAN.getTelPortaria());
			this.icBEAN.setConstrutora(this.icBEAN.getConstrutora());
			this.icBEAN.setNomeEngenheiro(this.icBEAN.getNomeEngenheiro().toUpperCase());
			this.icBEAN.setDataEntrega(this.icBEAN.getDataEntrega());
			this.icBEAN.setClassificacao(this.icBEAN.getClassificacao().toUpperCase());
			this.icBEAN.setBloco(this.icBEAN.getBloco());
			this.icBEAN.setAndar(this.icBEAN.getAndar());
			this.icBEAN.setApartamento(this.icBEAN.getApartamento());
			this.icBEAN.setAptosPorAndar(this.icBEAN.getAptosPorAndar());
			this.icBEAN.setFuncCondominio(this.icBEAN.getFuncCondominio());
			this.icBEAN.setFuncTerceirizado(this.icBEAN.getFuncTerceirizado());
			this.icBEAN.setElevador(this.icBEAN.getElevador());
			this.icBEAN.setPiscina(this.icBEAN.isPiscina());
			this.icBEAN.setPlayground(this.icBEAN.isPlayground());
			this.icBEAN.setSauna(this.icBEAN.isSauna());
			this.icBEAN.setJogos(this.icBEAN.isJogos());
			this.icBEAN.setGourmet(this.icBEAN.isGourmet());
			this.icBEAN.setQuadra(this.icBEAN.isQuadra());
			this.icBEAN.setChurrasqueira(this.icBEAN.isChurrasqueira());
			this.icBEAN.setSalaoFestas(this.icBEAN.isSalaoFestas());
			this.icBEAN.setGinastica(this.icBEAN.isGinastica());
			this.icBEAN.setJardim(this.icBEAN.isJardim());
			this.icBEAN.setTipo(this.icBEAN.getTipo());
			this.icBEAN.setCaracteristica(this.icBEAN.getCaracteristica().toUpperCase());
			this.icBEAN.setMarcaElevador(this.icBEAN.getMarcaElevador().toUpperCase());
			this.icBEAN.setOutrasCaracteristicas(this.icBEAN.getOutrasCaracteristicas().toUpperCase());
			this.icBEAN.setObsCondominio(this.icBEAN.getObsCondominio());
			this.icBEAN.setCaptadoPor(this.sessaoMB.getUsuario().getNome());
			this.cmDAO.salvarCondominio(this.icBEAN, this.sessaoMB);

			this.icBEAN.setCondo(this.icBEAN.getId());
			this.cmDAO.updateCondominio(this.icBEAN);

			this.admBEAN.setCondo2(this.icBEAN.getId());
			this.admBEAN.setNome(this.admBEAN.getNome().toUpperCase());
			this.admBEAN.setEndereco(this.admBEAN.getEndereco().toUpperCase());
			this.admBEAN.setBairro(this.admBEAN.getBairro().toUpperCase());
			this.admBEAN.setCidade(this.admBEAN.getCidade().toUpperCase());
			this.admBEAN.setEstado(this.alterarEstado2());
			this.admBEAN.setCep(this.admBEAN.getCep());
			this.admBEAN.setTelefone(this.admBEAN.getTelefone());
			this.admBEAN.setE_mail(this.admBEAN.getE_mail());
			this.admBEAN.setValorCondominio(this.admBEAN.getValorCondominio());
			this.admBEAN.setVencimento(this.admBEAN.getVencimento());
			this.admBEAN.setValorArrecadado(this.admBEAN.getValorArrecadado());
			this.admBEAN.setInicioContrato(this.admBEAN.getInicioContrato());
			this.admBEAN.setGrauSatisfacao(this.admBEAN.getGrauSatisfacao().toUpperCase());
			this.admBEAN.setTaxa(this.admBEAN.getTaxa());
			this.admBEAN.setAtendimento(this.admBEAN.isAtendimento());
			this.admBEAN.setSelecao(this.admBEAN.isSelecao());
			this.admBEAN.setTaxaB(this.admBEAN.isTaxaB());
			this.admBEAN.setTelefoneB(this.admBEAN.isTelefoneB());
			this.admBEAN.setFlexibilidade(this.admBEAN.isFlexibilidade());
			this.admBEAN.setSistOperacional(this.admBEAN.isSistOperacional());
			this.admBEAN.setManutencao(this.admBEAN.isManutencao());
			this.admBEAN.setMalote(this.admBEAN.isMalote());
			this.admBEAN.setGerencia(this.admBEAN.isGerencia());
			this.admBEAN.setPessoal(this.admBEAN.isPessoal());
			this.admBEAN.setVistoria(this.admBEAN.isVistoria());
			this.admBEAN.setCusto(this.admBEAN.isCusto());
			this.admBEAN.setProvidenciasLentas(this.admBEAN.isProvidenciasLentas());
			this.admBEAN.setDeptoJuridico(this.admBEAN.isDeptoJuridico());
			this.admBEAN.setObservacao(this.admBEAN.getObservacao().toUpperCase());
			this.cmDAO.salvarAdministracao(this.admBEAN, this.sessaoMB);

			this.propBEAN.setCondo3(this.icBEAN.getId());
			this.propBEAN.setIndicadoPor(this.propBEAN.getIndicadoPor().toUpperCase());
			this.propBEAN.setSolicitadoPor(this.propBEAN.getSolicitadoPor().toUpperCase());
			this.propBEAN.setEndereco(this.propBEAN.getEndereco().toUpperCase());
			this.propBEAN.setBairro(this.propBEAN.getBairro().toUpperCase());
			this.propBEAN.setCidade(this.propBEAN.getCidade().toUpperCase());
			this.propBEAN.setEstado(this.alterarEstado3());
			this.propBEAN.setCep(this.propBEAN.getCep());
			this.propBEAN.setApartamento(this.propBEAN.getApartamento().toUpperCase());
			this.propBEAN.setTelResidencial(this.propBEAN.getTelResidencial());
			this.propBEAN.setTelComercial(this.propBEAN.getTelComercial());
			this.propBEAN.setFax(this.propBEAN.getFax());
			this.propBEAN.setCelular(this.propBEAN.getCelular());
			this.propBEAN.setE_mail(this.propBEAN.getE_mail());
			this.propBEAN.setEntreguePor(this.propBEAN.getEntreguePor().toUpperCase());
			this.propBEAN.setDataProposta(this.propBEAN.getDataProposta());
			this.propBEAN.setInicioContrato(this.propBEAN.getInicioContrato());
			this.propBEAN.setTaxa(this.propBEAN.getTaxa());
			this.propBEAN.setObservacao(this.propBEAN.getObservacao().toUpperCase());
			this.cmDAO.salvarProposta(this.propBEAN, this.sessaoMB);

			this.sindBEAN.setCondo(this.icBEAN.getId());
			this.sindBEAN.setNome(this.sindBEAN.getNome().toUpperCase());
			this.sindBEAN.setEndereco(this.sindBEAN.getEndereco().toUpperCase());
			this.sindBEAN.setBairro(this.sindBEAN.getBairro().toUpperCase());
			this.sindBEAN.setCidade(this.sindBEAN.getCidade().toUpperCase());
			this.sindBEAN.setEstado(this.alterarEstado4());
			this.sindBEAN.setCep(this.sindBEAN.getCep());
			this.sindBEAN.setApartamento(this.sindBEAN.getApartamento().toUpperCase());
			this.sindBEAN.setTelResidencial(this.sindBEAN.getTelResidencial());
			this.sindBEAN.setTelComercial(this.sindBEAN.getTelComercial());
			this.sindBEAN.setFax(this.sindBEAN.getFax());
			this.sindBEAN.setCelular(this.sindBEAN.getCelular());
			this.sindBEAN.setE_mail(this.sindBEAN.getE_mail());
			this.sindBEAN.setObsSindico(this.sindBEAN.getObsSindico());
			this.cmDAO.salvarSindico(this.sindBEAN, this.sessaoMB);
			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "salvo-com-sucesso.xhtml?faces-redirect=true");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA SELECIONAR ZONA POR CEP ↓

	public String zona() {
		int cepInicial = Integer.valueOf(this.icBEAN.getCep().substring(0, 5));
		String zona = "";
		if (cepInicial >= 1000 && cepInicial <= 1999) {
			zona = "Centro";
		}
		if (cepInicial >= 2000 && cepInicial <= 2999) {
			zona = "Norte";
		}
		if (cepInicial >= 4000 && cepInicial <= 4999) {
			zona = "Sul";
		}
		if (cepInicial >= 3000 && cepInicial <= 3999) {
			zona = "Leste";
		}
		if (cepInicial >= 5000 && cepInicial <= 5999) {
			zona = "Oeste";
		}
		if (cepInicial >= 6000 && cepInicial <= 6999) {
			zona = "Osasco e Região";
		}
		if (cepInicial >= 7000 && cepInicial <= 7999) {
			zona = "Guarulhos e Região";
		}
		if (cepInicial >= 8000 && cepInicial <= 8999) {
			zona = "Mogi das Cruzes e Região";
		}
		if (cepInicial >= 9000 && cepInicial <= 9999) {
			zona = "Santo André e Região";
		}
		return zona;
	}

	// ↓ MÉTODO PARA SALVAR FOTO DO CONDOMÍNIO ↓

	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (event.getFile().getContents() != null) {
				intra_cptcondo_foto2 c = new intra_cptcondo_foto2();
				c.setFoto(event.getFile().getContents());
				c.setCodigoCondominio(this.icBEAN.getId());
				c.setDescricao(this.descricao);
				this.listaArquivos.add(c);
				this.cmDAO = new ComercialDAO();
				this.cmDAO.salvarFoto(c);
				RequestContext.getCurrentInstance().reset("frmTblImagensCondominio");
				RequestContext.getCurrentInstance().update("frmTblImagensCondominio:dtImagem");
				RequestContext.getCurrentInstance().execute("PF('dlgUpload').hide();");
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	// ↓ MÉTODO PARA ABRIR FOTO DO CONDOMÍNIO ↓

	public void abrirImagem() {
		try {
			this.cmDAO = new ComercialDAO();
			byte[] arquivo = null;
			if (this.fotoCondominioSelecionada.getCodigo() != 0) {
				arquivo = this.cmDAO.pesquisaFoto(this.fotoCondominioSelecionada.getCodigo());
				this.downloadJPEG(arquivo);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA INSERIR LOGRADOURO ↓

	@SuppressWarnings("static-access")
	public void logradouro() {
		this.setLogradouro(this.trataNomeComposto(this.getLogradouro()));
		this.icBEAN.setEndereco(this.trataNomeComposto(this.getLogradouro()));
		this.admBEAN.setEndereco(this.trataNomeComposto(this.getLogradouro()));
		this.propBEAN.setEndereco(this.trataNomeComposto(this.getLogradouro()));
		this.sindBEAN.setEndereco(this.trataNomeComposto(this.getLogradouro()));
	}

	// ↓ MÉTODO PARA INSERIR NOME DA CONSTRUTORA/ ENGENHEIRO RESPONSÁVEL ↓

	public void nomeEngenheiro() {
		try {
			this.cmDAO = new ComercialDAO();
			intra_cptconstrutora2 i = this.cmDAO.nomeConstrutora(this.icBEAN.getConstrutora());
			this.icBEAN.setNomeConstrutora(retornaNome(i));
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public String retornaNome(intra_cptconstrutora2 cp) {
		String nome = null;
		try {
			this.cmDAO = new ComercialDAO();
			this.icBEAN.setNomeConstrutora(cp.getNome());
			this.icBEAN.setNomeEngenheiro(cp.getResponsavel());
			nome = this.icBEAN.getNomeConstrutora();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return nome;
	}

	// ↓ MÉTODOS PARA ALTERAR ESTADO DE STRING PARA INT ↓

	public int alterarEstado1() {
		int estado = 0;
		if (this.getEstado1() != null) {
			switch (this.getEstado1()) {
			case "ACRE":
				estado = 1;
				break;
			case "ALAGOAS":
				estado = 2;
				break;
			case "AMAPÁ":
				estado = 3;
				break;
			case "AMAZONAS":
				estado = 4;
				break;
			case "BAHIA":
				estado = 5;
				break;
			case "CEARÁ":
				estado = 6;
				break;
			case "DISTRITO FEDERAL":
				estado = 7;
				break;
			case "ESPÍRITO SANTO":
				estado = 8;
				break;
			case "GOIÁS":
				estado = 9;
				break;
			case "MARANHÃO":
				estado = 10;
				break;
			case "MINAS GERAIS":
				estado = 11;
				break;
			case "MATO GROSSO DO SUL":
				estado = 12;
				break;
			case "MATO GROSSO":
				estado = 13;
				break;
			case "PARÁ":
				estado = 14;
				break;
			case "PARAÍBA":
				estado = 15;
				break;
			case "PERNAMBUCO":
				estado = 16;
				break;
			case "PIAUÍ":
				estado = 17;
				break;
			case "PARANÁ":
				estado = 18;
				break;
			case "RIO DE JANEIRO":
				estado = 19;
				break;
			case "RIO GRANDE DO NORTE":
				estado = 20;
				break;
			case "RONDÔNIA":
				estado = 21;
				break;
			case "RORAIMA":
				estado = 22;
				break;
			case "RIO GRANDE DO SUL":
				estado = 23;
				break;
			case "SANTA CATARINA":
				estado = 24;
				break;
			case "SERGIPE":
				estado = 25;
				break;
			case "SÃO PAULO":
				estado = 26;
				break;
			case "TOCANTINS":
				estado = 27;
				break;
			}
		}
		return estado;
	}

	public int alterarEstado2() {
		int estado = 0;
		if (this.getEstado2() != null) {
			switch (this.getEstado2()) {
			case "ACRE":
				estado = 1;
				break;
			case "ALAGOAS":
				estado = 2;
				break;
			case "AMAPÁ":
				estado = 3;
				break;
			case "AMAZONAS":
				estado = 4;
				break;
			case "BAHIA":
				estado = 5;
				break;
			case "CEARÁ":
				estado = 6;
				break;
			case "DISTRITO FEDERAL":
				estado = 7;
				break;
			case "ESPÍRITO SANTO":
				estado = 8;
				break;
			case "GOIÁS":
				estado = 9;
				break;
			case "MARANHÃO":
				estado = 10;
				break;
			case "MINAS GERAIS":
				estado = 11;
				break;
			case "MATO GROSSO DO SUL":
				estado = 12;
				break;
			case "MATO GROSSO":
				estado = 13;
				break;
			case "PARÁ":
				estado = 14;
				break;
			case "PARAÍBA":
				estado = 15;
				break;
			case "PERNAMBUCO":
				estado = 16;
				break;
			case "PIAUÍ":
				estado = 17;
				break;
			case "PARANÁ":
				estado = 18;
				break;
			case "RIO DE JANEIRO":
				estado = 19;
				break;
			case "RIO GRANDE DO NORTE":
				estado = 20;
				break;
			case "RONDÔNIA":
				estado = 21;
				break;
			case "RORAIMA":
				estado = 22;
				break;
			case "RIO GRANDE DO SUL":
				estado = 23;
				break;
			case "SANTA CATARINA":
				estado = 24;
				break;
			case "SERGIPE":
				estado = 25;
				break;
			case "SÃO PAULO":
				estado = 26;
				break;
			case "TOCANTINS":
				estado = 27;
				break;
			}
		}
		return estado;
	}

	public int alterarEstado3() {
		int estado = 0;
		if (this.getEstado3() != null) {
			switch (this.getEstado3()) {
			case "ACRE":
				estado = 1;
				break;
			case "ALAGOAS":
				estado = 2;
				break;
			case "AMAPÁ":
				estado = 3;
				break;
			case "AMAZONAS":
				estado = 4;
				break;
			case "BAHIA":
				estado = 5;
				break;
			case "CEARÁ":
				estado = 6;
				break;
			case "DISTRITO FEDERAL":
				estado = 7;
				break;
			case "ESPÍRITO SANTO":
				estado = 8;
				break;
			case "GOIÁS":
				estado = 9;
				break;
			case "MARANHÃO":
				estado = 10;
				break;
			case "MINAS GERAIS":
				estado = 11;
				break;
			case "MATO GROSSO DO SUL":
				estado = 12;
				break;
			case "MATO GROSSO":
				estado = 13;
				break;
			case "PARÁ":
				estado = 14;
				break;
			case "PARAÍBA":
				estado = 15;
				break;
			case "PERNAMBUCO":
				estado = 16;
				break;
			case "PIAUÍ":
				estado = 17;
				break;
			case "PARANÁ":
				estado = 18;
				break;
			case "RIO DE JANEIRO":
				estado = 19;
				break;
			case "RIO GRANDE DO NORTE":
				estado = 20;
				break;
			case "RONDÔNIA":
				estado = 21;
				break;
			case "RORAIMA":
				estado = 22;
				break;
			case "RIO GRANDE DO SUL":
				estado = 23;
				break;
			case "SANTA CATARINA":
				estado = 24;
				break;
			case "SERGIPE":
				estado = 25;
				break;
			case "SÃO PAULO":
				estado = 26;
				break;
			case "TOCANTINS":
				estado = 27;
				break;
			}
		}
		return estado;
	}

	public int alterarEstado4() {
		int estado = 0;
		if (this.getEstado4() != null) {
			switch (this.getEstado4()) {
			case "ACRE":
				estado = 1;
				break;
			case "ALAGOAS":
				estado = 2;
				break;
			case "AMAPÁ":
				estado = 3;
				break;
			case "AMAZONAS":
				estado = 4;
				break;
			case "BAHIA":
				estado = 5;
				break;
			case "CEARÁ":
				estado = 6;
				break;
			case "DISTRITO FEDERAL":
				estado = 7;
				break;
			case "ESPÍRITO SANTO":
				estado = 8;
				break;
			case "GOIÁS":
				estado = 9;
				break;
			case "MARANHÃO":
				estado = 10;
				break;
			case "MINAS GERAIS":
				estado = 11;
				break;
			case "MATO GROSSO DO SUL":
				estado = 12;
				break;
			case "MATO GROSSO":
				estado = 13;
				break;
			case "PARÁ":
				estado = 14;
				break;
			case "PARAÍBA":
				estado = 15;
				break;
			case "PERNAMBUCO":
				estado = 16;
				break;
			case "PIAUÍ":
				estado = 17;
				break;
			case "PARANÁ":
				estado = 18;
				break;
			case "RIO DE JANEIRO":
				estado = 19;
				break;
			case "RIO GRANDE DO NORTE":
				estado = 20;
				break;
			case "RONDÔNIA":
				estado = 21;
				break;
			case "RORAIMA":
				estado = 22;
				break;
			case "RIO GRANDE DO SUL":
				estado = 23;
				break;
			case "SANTA CATARINA":
				estado = 24;
				break;
			case "SERGIPE":
				estado = 25;
				break;
			case "SÃO PAULO":
				estado = 26;
				break;
			case "TOCANTINS":
				estado = 27;
				break;
			}
		}
		return estado;
	}

	// ↓ MÉTODO PARA FILTRAR TABELA DE CONDOMÍNIOS ↓

	public void pesquisarCondominio() {
		try {
			this.cmDAO = new ComercialDAO();
			switch (this.filtro) {
			case "Tipo":
				this.listaCondominios = this.cmDAO.listaCondominioPorTipo(this.tipo);
				break;
			case "Região":
				this.listaCondominios = this.cmDAO.listaCondominioPorRegiao(this.regiao);
				break;
			case "Cep1":
				this.listaCondominios = this.cmDAO.listaCondominioPorCep(this.cep1);
				break;
			case "Todos":
				this.listaCondominios = this.cmDAO.listaCondominios();
				break;
			default:
				break;
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	// ↓ MÉTODO PARA IMPRIMIR PROPOSTA ↓

	public void gerarProposta() throws Exception {
		HashMap<Object, Object> parametros = new HashMap<>();
		String nome = "";
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		parametros.put("Parameter1", this.condominioSelecionado.getCondo());
		nome = "Comercial-Proposta";
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
			response.setHeader("Content-Disposition", "inline;filename=\"Proposta.pdf\"");
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

	// ↓ MÉTODO COMPLEMENTAR DO DOWNLOAD DA FOTO ↓

	public void downloadJPEG(byte[] retorno) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			input = new BufferedInputStream(new ByteArrayInputStream(retorno), DEFAULT_BUFFER_SIZE);
			response.reset();
			response.setHeader("Content-Type", "application/jpeg");
			response.setHeader("Content-Length", String.valueOf(retorno.length));
			response.setHeader("Content-Disposition", "inline;filename=\"Imagem.jpeg\"");
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
}