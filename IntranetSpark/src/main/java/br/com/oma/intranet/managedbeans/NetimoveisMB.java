package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
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

import br.com.oma.intranet.dao.NetimoveisDAO;
import br.com.oma.intranet.entidades.intra_netimoveis_captacao2;
import br.com.oma.intranet.entidades.intra_netimoveis_proprietario;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ManagedBean(name = "ntMB")
@ViewScoped
public class NetimoveisMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6236100256857562189L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private NetimoveisDAO ntDAO;
	private intra_netimoveis_captacao2 ncBEAN = new intra_netimoveis_captacao2();
	private intra_netimoveis_captacao2 captacaoSelecionada;
	private intra_netimoveis_proprietario npBEAN = new intra_netimoveis_proprietario();
	private intra_netimoveis_proprietario proprietarioSelecionado;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	// ATRIBUTOS
	private List<intra_netimoveis_captacao2> listaCaptacao;

	// GET X SET

	public NetimoveisDAO getNtDAO() {
		return ntDAO;
	}

	public void setNtDAO(NetimoveisDAO ntDAO) {
		this.ntDAO = ntDAO;
	}

	public intra_netimoveis_captacao2 getNcBEAN() {
		return ncBEAN;
	}

	public void setNcBEAN(intra_netimoveis_captacao2 ncBEAN) {
		this.ncBEAN = ncBEAN;
	}

	public intra_netimoveis_proprietario getNpBEAN() {
		return npBEAN;
	}

	public void setNpBEAN(intra_netimoveis_proprietario npBEAN) {
		this.npBEAN = npBEAN;
	}

	public List<intra_netimoveis_captacao2> getListaCaptacao() {
		try {
			this.ntDAO = new NetimoveisDAO();
			this.listaCaptacao = this.ntDAO.listaCaptacao();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return listaCaptacao;
	}

	public void setListaCaptacao(List<intra_netimoveis_captacao2> listaCaptacao) {
		this.listaCaptacao = listaCaptacao;
	}

	public intra_netimoveis_captacao2 getCaptacaoSelecionada() {
		return captacaoSelecionada;
	}

	public void setCaptacaoSelecionada(intra_netimoveis_captacao2 captacaoSelecionada) {
		this.captacaoSelecionada = captacaoSelecionada;
	}

	public intra_netimoveis_proprietario getProprietarioSelecionado() {
		return proprietarioSelecionado;
	}

	public void setProprietarioSelecionado(intra_netimoveis_proprietario proprietarioSelecionado) {
		this.proprietarioSelecionado = proprietarioSelecionado;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}
	
	// MÉTODOS

	// ↓ MÉTODO PARA SALVAR LAUDO DE AVALIAÇÃO ↓
	
	public void salvar() {
		try {
			this.ntDAO = new NetimoveisDAO();

			this.ncBEAN.setCodigoCondominio(this.ncBEAN.getCodigoCondominio());
			this.ncBEAN.setTipoImovel(this.ncBEAN.getTipoImovel());
			this.ncBEAN.setPrecoImovel(this.ncBEAN.getPrecoImovel());
			this.ncBEAN.setData(this.ncBEAN.getData());
			this.ncBEAN.setExclusividade(this.ncBEAN.isExclusividade());
			this.ncBEAN.setAutorizacao(this.ncBEAN.isAutorizacao());
			this.ncBEAN.setVenda(this.ncBEAN.isVenda());
			this.ncBEAN.setAluguel(this.ncBEAN.isAluguel());
			this.ncBEAN.setBairro(this.ncBEAN.getBairro());
			this.ncBEAN.setNroSalas(this.ncBEAN.getNroSalas());
			this.ncBEAN.setNroQuartos(this.ncBEAN.getNroQuartos());
			this.ncBEAN.setBhSocial(this.ncBEAN.getBhSocial());
			this.ncBEAN.setVaranda(this.ncBEAN.getVaranda());
			this.ncBEAN.setVagaLivre(this.ncBEAN.getVagaLivre());
			this.ncBEAN.setVagaPresa(this.ncBEAN.getVagaPresa());
			this.ncBEAN.setEndereco(this.ncBEAN.getEndereco());
			this.ncBEAN.setCep(this.ncBEAN.getCep());
			this.ncBEAN.setNroEndereco(this.ncBEAN.getNroEndereco());
			this.ncBEAN.setUnidade(this.ncBEAN.getUnidade());
			this.ncBEAN.setIdade(this.ncBEAN.getIdade());
			this.ncBEAN.setEdificio(this.ncBEAN.getEdificio());
			this.ncBEAN.setCondominioValor(this.ncBEAN.getCondominioValor());
			this.ncBEAN.setIptuValor(this.ncBEAN.getIptuValor());
			this.ncBEAN.setIndiceCadastral(this.ncBEAN.getIndiceCadastral());
			this.ncBEAN.setPosicao(this.ncBEAN.getPosicao());
			this.ncBEAN.setFachadaFrontal(this.ncBEAN.getFachadaFrontal());
			this.ncBEAN.setFachadaLateral(this.ncBEAN.getFachadaLateral());
			this.ncBEAN.setSindicoTelefone(this.ncBEAN.getSindicoTelefone());
			this.ncBEAN.setConservadoraTelefone(this.ncBEAN.getConservadoraTelefone());
			this.ncBEAN.setNroUnidades(this.ncBEAN.getNroUnidades());
			this.ncBEAN.setNroPavimentos(this.ncBEAN.getNroPavimentos());
			this.ncBEAN.setUnidsPorAndar(this.ncBEAN.getUnidsPorAndar());
			this.ncBEAN.setAreaRealPriv(this.ncBEAN.getAreaRealPriv());
			this.ncBEAN.setAreaConstruida(this.ncBEAN.getAreaConstruida());
			this.ncBEAN.setAreaLote(this.ncBEAN.getAreaLote());
			this.ncBEAN.setFrenteLote(this.ncBEAN.getFrenteLote());
			this.ncBEAN.setZonaUso(this.ncBEAN.getZonaUso());
			this.ncBEAN.setAproveitamento(this.ncBEAN.getAproveitamento());
			this.ncBEAN.setReferencia(this.ncBEAN.getReferencia());
			this.ncBEAN.setMelhorAcesso(this.ncBEAN.getMelhorAcesso());
			this.ncBEAN.setImovelOcupado(this.ncBEAN.getImovelOcupado());
			this.ncBEAN.setImovelVago(this.ncBEAN.isImovelVago());
			this.ncBEAN.setNomeLocatario(this.ncBEAN.getNomeLocatario());
			this.ncBEAN.setTelefone(this.ncBEAN.getTelefone());
			this.ncBEAN.setChaves(this.ncBEAN.getChaves());
			this.ncBEAN.setHoraVisita(this.ncBEAN.getHoraVisita());
			this.ncBEAN.setBeneficios(this.ncBEAN.getBeneficios());
			this.ncBEAN.setArCondicionado(this.ncBEAN.isArCondicionado());
			this.ncBEAN.setPortaoEletronico(this.ncBEAN.isPortaoEletronico());
			this.ncBEAN.setPortariaPermanente(this.ncBEAN.isPortariaPermanente());
			this.ncBEAN.setQuadraEsportes(this.ncBEAN.isQuadraEsportes());
			this.ncBEAN.setElevadorSocial(this.ncBEAN.isElevadorSocial());
			this.ncBEAN.setAcademia(this.ncBEAN.isAcademia());
			this.ncBEAN.setSalaMassagem(this.ncBEAN.isSalaMassagem());
			this.ncBEAN.setSalaoFestas(this.ncBEAN.isSalaoFestas());
			this.ncBEAN.setSalaoJogos(this.ncBEAN.isSalaoJogos());
			this.ncBEAN.setSauna(this.ncBEAN.isSauna());
			this.ncBEAN.setSolDeManha(this.ncBEAN.isSolDeManha());
			this.ncBEAN.setPiscinas(this.ncBEAN.isPiscinas());
			this.ncBEAN.setPlayground(this.ncBEAN.isPlayground());
			this.ncBEAN.setChurrasqEspGourmet(this.ncBEAN.isChurrasqEspGourmet());
			this.ncBEAN.setCircuitoTV(this.ncBEAN.isCircuitoTV());
			this.ncBEAN.setElevadorServico(this.ncBEAN.isElevadorServico());
			this.ncBEAN.setEsquadriasAluminio(this.ncBEAN.isEsquadriasAluminio());
			this.ncBEAN.setGasCanalizado(this.ncBEAN.isGasCanalizado());
			this.ncBEAN.setHallSocialDecorado(this.ncBEAN.isHallSocialDecorado());
			this.ncBEAN.setInterfone(this.ncBEAN.isInterfone());
			this.ncBEAN.setJanelaComVenezianas(this.ncBEAN.isJanelaComVenezianas());
			this.ncBEAN.setJardins(this.ncBEAN.isJardins());
			this.ncBEAN.setMobilhado(this.ncBEAN.isMobilhado());
			this.ncBEAN.setAndarAlto(this.ncBEAN.isAndarAlto());
			this.ncBEAN.setDescrevaPredio(this.ncBEAN.getDescrevaPredio());
			this.ncBEAN.setConstrutora(this.ncBEAN.getConstrutora());
			this.ncBEAN.setDescrevaImovelOrdem(this.ncBEAN.getDescrevaImovelOrdem());
			this.ncBEAN.setDocumentacao(this.ncBEAN.getDocumentacao());
			this.ncBEAN.setValorEscritura(this.ncBEAN.getValorEscritura());
			this.ncBEAN.setCondicoesPagto(this.ncBEAN.getCondicoesPagto());
			this.ncBEAN.setEstudaPermuta(this.ncBEAN.getEstudaPermuta());
			this.ncBEAN.setMotivoVenda(this.ncBEAN.getMotivoVenda());
			this.ncBEAN.setCaptadorCelular(this.ncBEAN.getCaptadorCelular());
			this.ncBEAN.setAvaliacaoCorretora(this.ncBEAN.getAvaliacaoCorretora());
			this.ncBEAN.setCustoM3(this.ncBEAN.getCustoM3());
			this.ncBEAN.setUltimaConfirmacao(this.ncBEAN.getUltimaConfirmacao());
			this.ntDAO.salvarCaptacao(this.ncBEAN, this.sessaoMB);

			this.npBEAN.setCaptacao(this.ncBEAN.getId());
			this.npBEAN.setNome(this.npBEAN.getNome());
			this.npBEAN.setNacionalidade(this.npBEAN.getNacionalidade());
			this.npBEAN.setEstadoCivil(this.npBEAN.getEstadoCivil());
			this.npBEAN.setProfissao(this.npBEAN.getProfissao());
			this.npBEAN.setCpfCnpj(this.npBEAN.getCpfCnpj());
			this.npBEAN.setCi(this.npBEAN.getCi());
			this.npBEAN.setNascimento(this.npBEAN.getNascimento());
			this.npBEAN.setTelResidencial(this.npBEAN.getTelResidencial());
			this.npBEAN.setTelComercial(this.npBEAN.getTelComercial());
			this.npBEAN.setFax(this.npBEAN.getFax());
			this.npBEAN.setCelular(this.npBEAN.getCelular());
			this.npBEAN.setEmail(this.npBEAN.getEmail());
			this.npBEAN.setEnderecoResidencial(this.npBEAN.getEnderecoResidencial());
			this.npBEAN.setBairroResidencial(this.npBEAN.getBairroResidencial());
			this.npBEAN.setCidadeResidencia(this.npBEAN.getCidadeResidencia());
			this.npBEAN.setCepResidencial(this.npBEAN.getCepResidencial());
			this.npBEAN.setEnderecoComercial(this.npBEAN.getEnderecoComercial());
			this.npBEAN.setBairroComercial(this.npBEAN.getBairroComercial());
			this.npBEAN.setCidadeComercial(this.npBEAN.getCidadeComercial());
			this.npBEAN.setCepComercial(this.npBEAN.getCepComercial());
			this.npBEAN.setConjuge(this.npBEAN.getConjuge());
			this.npBEAN.setConjugeNacionalidade(this.npBEAN.getConjugeNacionalidade());
			this.npBEAN.setEstadoCivilConjuge(this.npBEAN.getEstadoCivilConjuge());
			this.npBEAN.setProfissaoConjuge(this.npBEAN.getProfissaoConjuge());
			this.npBEAN.setCpfConjuge(this.npBEAN.getCpfConjuge());
			this.npBEAN.setCiConjuge(this.npBEAN.getCiConjuge());
			this.npBEAN.setNascimentoConjuge(this.npBEAN.getNascimentoConjuge());
			this.npBEAN.setEndResConjuge(this.npBEAN.getEndResConjuge());
			this.npBEAN.setTelResConjuge(this.npBEAN.getTelResConjuge());
			this.npBEAN.setCelularConjuge(this.npBEAN.getCelularConjuge());
			this.npBEAN.setProcurador(this.npBEAN.getProcurador());
			this.npBEAN.setTelefoneProcurador(this.npBEAN.getTelefoneProcurador());
			this.npBEAN.setEmailProcurador(this.npBEAN.getEmailProcurador());
			this.npBEAN.setBanco(this.npBEAN.getBanco());
			this.npBEAN.setNomeAgencia(this.npBEAN.getNomeAgencia());
			this.npBEAN.setPraca(this.npBEAN.getPraca());
			this.npBEAN.setAgencia(this.npBEAN.getAgencia());
			this.npBEAN.setContaCorrente(this.npBEAN.getContaCorrente());
			this.npBEAN.setCorrentista(this.npBEAN.getCorrentista());
			this.npBEAN.setPrazoContrato(this.npBEAN.getPrazoContrato());
			this.npBEAN.setReajuste(this.npBEAN.getReajuste());
			this.npBEAN.setTaxaAdm(this.npBEAN.getTaxaAdm());
			this.npBEAN.setPorcPropriedade(this.npBEAN.getPorcPropriedade());
			this.npBEAN.setTaxaContrato(this.npBEAN.getTaxaContrato());
			this.npBEAN.setAssJuridica(this.npBEAN.getAssJuridica());
			this.npBEAN.setValorVenal(this.npBEAN.getValorVenal());
			this.npBEAN.setControleGaragem(this.npBEAN.getControleGaragem());
			this.npBEAN.setMarca(this.npBEAN.getMarca());
			this.npBEAN.setImovelAnuncio(this.npBEAN.isImovelAnuncio());
			this.npBEAN.setPagaPA(this.npBEAN.isPagaPA());
			this.npBEAN.setObservacao(this.npBEAN.getObservacao());
			this.ntDAO.salvarProprietario(this.npBEAN, this.sessaoMB);

			FacesContext fc = FacesContext.getCurrentInstance();
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "/netimoveis/captacao/tabela-de-captacao.xhtml?faces-redirect=true");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA IMPRIMIR LAUDO DE AVALIAÇÃO ↓
	
	public void gerarRelatorio() {
		try {
			HashMap<Object, Object> parametros = new HashMap<>();
			String nome = "";
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			parametros.put("Parameter1", this.captacaoSelecionada.getId());
			nome = "Proposta-Netimoveis";
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.renderResponse();
			facesContext.responseComplete();
			this.downloadPDF(rju.geraRelSiga(parametros, nome, nome, 1));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void downloadPDF(byte[] retorno) throws IOException {
		// Prepare.
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
	
	// ↓ MÉTODO PARA ABRIR ALTERAR LAUDO DE AVALIAÇÃO ↓
	
	public void abreAlteraCaptacao() {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(caminho + "/netimoveis/captacao/alteracao-de-captacao.xhtml?idCaptacao="
							+ this.captacaoSelecionada.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ↓ MÉTODO PARA PESQUISAR LAUDO DE AVALIAÇÃO ↓
	
	public void pesquisaCaptacaoSelecionada() {
		try {
			FacesContext ctx = FacesContext.getCurrentInstance();
			Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
			String idCaptacao = params.get("idCaptacao");
			if (idCaptacao != null) {
				this.ntDAO = new NetimoveisDAO();
				this.ncBEAN = this.ntDAO.pesquisaCondominioPorCodigo(Integer.parseInt(idCaptacao));
				this.npBEAN = this.ntDAO.pesquisaProprietarioPorCodigo(Integer.parseInt(idCaptacao));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), ""));
		}
	}

	// ↓ MÉTODO PARA EXCLUIR LAUDO DE AVALIAÇÃO ↓
	
	public void excluir() {
		if (this.captacaoSelecionada != null) {
			try {
				this.ntDAO = new NetimoveisDAO();

				intra_netimoveis_captacao2 captacao = this.ntDAO
						.pesquisaCondominioPorCodigo(this.captacaoSelecionada.getId());
				this.ntDAO.excluirCaptacao(captacao, this.sessaoMB);
				intra_netimoveis_proprietario proprietario = this.ntDAO
						.pesquisaProprietarioPorCodigo(this.captacaoSelecionada.getId());
				this.ntDAO.excluirProprietario(proprietario, this.sessaoMB);
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}

		}
	}

}