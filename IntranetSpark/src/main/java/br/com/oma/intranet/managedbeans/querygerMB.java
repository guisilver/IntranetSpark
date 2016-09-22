package br.com.oma.intranet.managedbeans;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.LISTAR_BL_UNI_DAO;
import br.com.oma.intranet.dao.QuerygerDAO;
import br.com.oma.intranet.entidades.intra_cndunidade;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.QueryRelatorioJasperUtil;

@ManagedBean(name = "qgerMB")
@ViewScoped

public class querygerMB extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4551776066369747282L;

	private StreamedContent stream, stream2, stream5, stream6;
	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private QuerygerDAO qDAO;
	private LISTAR_BL_UNI_DAO cndUnidaDAO;
	private intra_condominios icBEAN = new intra_condominios();
	private intra_cndunidade cndUnidaBEAN = new intra_cndunidade();

	// ATRIBUTOS
	private List<intra_grupo_gerente> listaDeGerentes;
	private List<intra_condominios> listaDeCondominio;
	private List<intra_cndunidade> listaDeBloco, listaDeUnidade;
	private String codigo;
	private String nomeCondo;
	private String cabecalho;
	private String nomeArquivo;

	// GET X SET
	/**
	 * @param sessaoMB
	 *            the sessaoMB to set
	 */
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public String getCabecalho() {
		return cabecalho;
	}

	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}

	/**
	 * @return the icBEAN
	 */
	public intra_condominios getIcBEAN() {
		return icBEAN;
	}

	/**
	 * @param icBEAN
	 *            the icBEAN to set
	 */
	public void setIcBEAN(intra_condominios icBEAN) {
		this.icBEAN = icBEAN;
	}

	/**
	 * @return the listaDeGerentes
	 */
	public List<intra_grupo_gerente> getListaDeGerentes() {
		if (this.listaDeGerentes == null) {
			this.listaDeGerentes = this.retornaGerentes();
		}
		return listaDeGerentes;
	}

	/**
	 * @param listaDeGerentes
	 *            the listaDeGerentes to set
	 */
	public void setListaDeGerentes(List<intra_grupo_gerente> listaDeGerentes) {
		this.listaDeGerentes = listaDeGerentes;
	}

	/**
	 * @return the listaDeCondominio
	 */
	public List<intra_condominios> getListaDeCondominio() {
		if (this.sessaoMB.getGerenteSelecionado() != null && this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
			this.qDAO = new QuerygerDAO();
			this.listaDeCondominio = this.qDAO.listarCondominios(this.sessaoMB.getGerenteSelecionado().getCodigo());
		}
		return listaDeCondominio;
	}

	/**
	 * @param listaDeCondominio
	 *            the listaDeCondominio to set
	 */
	public void setListaDeCondominio(List<intra_condominios> listaDeCondominio) {
		this.listaDeCondominio = listaDeCondominio;
	}

	/**
	 * @return the nomeCondo
	 */
	public String getNomeCondo() {
		return nomeCondo;
	}

	/**
	 * @param nomeCondo
	 *            the nomeCondo to set
	 */
	public void setNomeCondo(String nomeCondo) {
		this.nomeCondo = nomeCondo;
	}

	/**
	 * @return the listaDeBloco
	 */
	public List<intra_cndunidade> getListaDeBloco() {
		if (this.icBEAN.getCodigo() > 0) {
			this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
			this.listaDeBloco = null;
			this.listaDeBloco = this.cndUnidaDAO.listaDeBloco(this.icBEAN.getCodigo());
		}
		return listaDeBloco;
	}

	/**
	 * @param listaDeBloco
	 *            the listaDeBloco to set
	 */
	public void setListaDeBloco(List<intra_cndunidade> listaDeBloco) {
		this.listaDeBloco = listaDeBloco;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the lstaDeUnidade
	 */
	public List<intra_cndunidade> getListaDeUnidade() {
		if (this.sessaoMB.getGerenteSelecionado() != null && this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
			if (this.cndUnidaBEAN.getBloco() != null) {
				if (!this.cndUnidaBEAN.getBloco().trim().isEmpty()) {
					this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
					this.listaDeUnidade = null;
					this.listaDeUnidade = this.cndUnidaDAO.listaDeUnidade(this.icBEAN.getCodigo(),
							this.cndUnidaBEAN.getBloco());
				}
			}
		}
		return listaDeUnidade;
	}

	/**
	 * @param lstaDeUnidade
	 *            the lstaDeUnidade to set
	 */
	public void setListaDeUnidade(List<intra_cndunidade> listaDeUnidade) {
		this.listaDeUnidade = listaDeUnidade;
	}

	/**
	 * @return the cndUnidaBEAN
	 */
	public intra_cndunidade getCndUnidaBEAN() {
		return cndUnidaBEAN;
	}

	/**
	 * @param cndUnidaBEAN
	 *            the cndUnidaBEAN to set
	 */
	public void setCndUnidaBEAN(intra_cndunidade cndUnidaBEAN) {
		this.cndUnidaBEAN = cndUnidaBEAN;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public StreamedContent getStream() {
		try {
			this.gerarelatorioExcelCondomino();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
	}

	public StreamedContent getStream2() {
		try {
			this.gerarelatorioExcelSindico();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stream2;
	}

	public void setStream2(StreamedContent stream2) {
		this.stream2 = stream2;
	}

	public StreamedContent getStream5() {
		try {
			this.gerarelatorioExcelZelador();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return stream5;
	}

	public void setStream5(StreamedContent stream5) {
		this.stream5 = stream5;
	}

	public StreamedContent getStream6() {
		try {
			this.gerarelatorioExcelZelador2();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stream6;
	}

	public void setStream6(StreamedContent stream6) {
		this.stream6 = stream6;
	}

	// METODOS

	// ↓ MÉTODO PARA RETORNAR O CÓDIGO E O NOME DO GERENTE ↓

	public List<intra_grupo_gerente> retornaGerentes() {
		if (!this.sessaoMB.getUsuario().getGrupoGer().isEmpty()) {
			if (this.sessaoMB.getUsuario().getGrupoGer().get(0).getNome().equals(" Todos")) {
				this.listaDeCondominio = null;
				this.listaDeBloco = null;
				this.listaDeUnidade = null;
				this.nomeCondo = "";
				if (this.sessaoMB.getGerenteSelecionado() == null) {
					this.sessaoMB.setGerenteSelecionado(this.sessaoMB.getListaDeGerente().get(0));
				}
				return this.sessaoMB.getListaDeGerente();
			} else {
				this.listaDeCondominio = null;
				this.listaDeBloco = null;
				this.listaDeUnidade = null;
				this.nomeCondo = "";
				if (this.sessaoMB.getGerenteSelecionado() == null) {
					this.sessaoMB.setGerenteSelecionado(this.sessaoMB.getUsuario().getGrupoGer().get(0));
				}
				return this.sessaoMB.getUsuario().getGrupoGer();
			}
		} else {
			return null;
		}
	}

	// ↓ MÉTODO PARA RETORNAR O CÓDIGO E O NOME DO CONDOMÍNIO ↓

	public void retornaNomeCondominio() {
		for (intra_condominios c : sessaoMB.getListaDeCondominios()) {
			if (c.getCodigo() == this.icBEAN.getCodigo()) {
				this.nomeCondo = c.getNome();
				this.icBEAN.setNomeGerente(c.getNomeGerente());
				this.icBEAN.setEmailGerente(c.getEmailGerente());
				this.icBEAN.setCodigoGerente(c.getCodigoGerente());
				this.icBEAN.setNome(c.getNome());
			}
		}
	}

	// ↓ MÉTODO PARA RELATÓRIO DE CONDÔMINO ↓

	public void gerarelatorioExcelCondomino() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();
		parametros.put("gerente", this.sessaoMB.getGerenteSelecionado().getCodigo());
		rju.geraRelCond(parametros, "QueryCondomino_Ger", "QueryCondomino_Ger", 2);
		this.stream = rju.getStream();
	}

	// ↓ MÉTODO PARA RELATÓRIO DE SÍNDICO ↓

	public void gerarelatorioExcelSindico() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();
		parametros.put("gerente", this.sessaoMB.getGerenteSelecionado().getCodigo());
		rju.geraRelSind(parametros, "QuerySindico_Ger", "QuerySindico_Ger", 2);
		this.stream2 = rju.getStream2();
	}

	// ↓ MÉTODO PARA RELATÓRIO DE ZELADOR(SIGADM) ↓

	public void gerarelatorioExcelZelador() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();
		parametros.put("gerente", this.sessaoMB.getGerenteSelecionado().getCodigo());
		rju.geraRelZel(parametros, "QueryZelador_Ger", "QueryZelador_Ger", 2);
		this.stream5 = rju.getStream5();
	}

	// ↓ MÉTODO PARA RELATÓRIO DE ZELADOR(FOLHAWEB) ↓

	public void gerarelatorioExcelZelador2() throws Exception {

		HashMap<Object, Object> parametros = new HashMap<>();
		QueryRelatorioJasperUtil rju = new QueryRelatorioJasperUtil();
		parametros.put("gerente", this.sessaoMB.getGerenteSelecionado().getCodigo());
		rju.geraRelZel2(parametros, "QueryZelador2_Ger", "QueryZelador2_Ger", 2);
		this.stream6 = rju.getStream6();
	}
}