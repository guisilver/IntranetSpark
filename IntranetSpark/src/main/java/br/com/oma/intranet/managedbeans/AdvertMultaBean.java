package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import br.com.oma.intranet.dao.AdvertenciaMultaDAO;
import br.com.oma.intranet.dao.LISTAR_BL_UNI_DAO;
import br.com.oma.intranet.entidades.intra_advert_mult;
import br.com.oma.intranet.entidades.intra_cndunidade;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.util.ExportWord;
import br.com.oma.intranet.util.Mensagens;
import br.com.oma.intranet.util.RelatorioJasperUtil;
import br.com.oma.intranet.util.StringUtil;

@ManagedBean(name = "amMB")
@ViewScoped
public class AdvertMultaBean extends Mensagens {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1638694332928243373L;

	// DEPENDENCIA
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	// OBJETOS
	private AdvertenciaMultaDAO amDAO;
	private LISTAR_BL_UNI_DAO cndUnidaDAO;
	private intra_advert_mult amBEAN = new intra_advert_mult();
	private intra_condominios icBEAN = new intra_condominios();
	// private intra_cndunidade cndUnidaBEAN = new intra_cndunidade();
	private intra_advert_mult imprimirBEAN = new intra_advert_mult();

	DecimalFormat df = new DecimalFormat("#,###,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// ATRIBUTOS
	private Date dataAtual = new Date();
	private List<intra_cndunidade> listaDeBloco, listaDeUnidade;
	private List<intra_advert_mult> listaAdvmMul, filtroAdvmMul;
	private List<String> adv = new ArrayList<String>();
	private String codigo;
	private byte[] arquivo;
	private String nomeArquivo;
	private String nomeCondo;
	private double valor1;
	private boolean todos;
	private StreamedContent stream, streamWord;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	private int condominio;
	private String bloco;
	private String unidade;

	@PostConstruct
	public void init() {
		this.limparListas();
	}

	// GET X SET
	/**
	 * @param sessaoMB
	 *            the sessaoMB to set
	 */
	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	/**
	 * @return the amBEAN
	 */
	public intra_advert_mult getAmBEAN() {
		return amBEAN;
	}

	/**
	 * @param amBEAN
	 *            the amBEAN to set
	 */
	public void setAmBEAN(intra_advert_mult amBEAN) {
		this.amBEAN = amBEAN;
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
		if (this.condominio > 0) {
			this.cndUnidaDAO = new LISTAR_BL_UNI_DAO();
			this.listaDeBloco = null;
			if (this.amBEAN != null) {
				this.amBEAN.setUnidade("");
				this.amBEAN.setNome("");
			}
			this.listaDeBloco = this.cndUnidaDAO.listaDeBloco(this.condominio);
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
	 * @return the lstaDeUnidade
	 */
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

	/**
	 * @param lstaDeUnidade
	 *            the lstaDeUnidade to set
	 */
	public void setListaDeUnidade(List<intra_cndunidade> listaDeUnidade) {
		this.listaDeUnidade = listaDeUnidade;
	}

	/*	*//**
			 * @return the cndUnidaBEAN
			 */
	/*
	 * public intra_cndunidade getCndUnidaBEAN() { return cndUnidaBEAN; }
	 * 
	 *//**
		 * @param cndUnidaBEAN
		 *            the cndUnidaBEAN to set
		 *//*
		 * public void setCndUnidaBEAN(intra_cndunidade cndUnidaBEAN) {
		 * this.cndUnidaBEAN = cndUnidaBEAN; }
		 */

	/**
	 * @return the imprimirBEAN
	 */
	public intra_advert_mult getImprimirBEAN() {
		return imprimirBEAN;
	}

	/**
	 * @param imprimirBEAN
	 *            the imprimirBEAN to set
	 */
	public void setImprimirBEAN(intra_advert_mult imprimirBEAN) {
		this.imprimirBEAN = imprimirBEAN;
	}

	/**
	 * @return the listaAdvmMul
	 */
	public List<intra_advert_mult> getListaAdvmMul() {
		if (this.sessaoMB.getGerenteSelecionado() != null && this.sessaoMB.getGerenteSelecionado().getCodigo() > 0) {
			this.amDAO = new AdvertenciaMultaDAO();
			this.listaAdvmMul = this.amDAO.listarAdvmMul(this.sessaoMB.getGerenteSelecionado().getCodigo());
		}
		return listaAdvmMul;
	}

	/**
	 * @param listaAdvmMul
	 *            the listaAdvmMul to set
	 */
	public void setListaAdvmMul(List<intra_advert_mult> listaAdvmMul) {
		this.listaAdvmMul = listaAdvmMul;
	}

	/**
	 * @return the filtroAdvmMul
	 */
	public List<intra_advert_mult> getFiltroAdvmMul() {
		return filtroAdvmMul;
	}

	/**
	 * @param filtroAdvmMul
	 *            the filtroAdvmMul to set
	 */
	public void setFiltroAdvmMul(List<intra_advert_mult> filtroAdvmMul) {
		this.filtroAdvmMul = filtroAdvmMul;
	}

	/**
	 * @return the nomeArquivo
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	/**
	 * @param nomeArquivo
	 *            the nomeArquivo to set
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	/**
	 * @return the adv
	 */
	public List<String> getAdv() {
		return adv;
	}

	/**
	 * @param adv
	 *            the adv to set
	 */
	public void setAdv(List<String> adv) {
		this.adv = adv;
	}

	/**
	 * @return the arquivo
	 */
	public byte[] getArquivo() {
		return arquivo;
	}

	/**
	 * @param arquivo
	 *            the arquivo to set
	 */
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
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
	 * @return the valor1
	 */
	public double getValor1() {
		return valor1;
	}

	/**
	 * @param valor1
	 *            the valor1 to set
	 */
	public void setValor1(double valor1) {
		this.valor1 = valor1;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public StreamedContent getStream() {
		try {
			this.gerarelatorioExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}

	public void setStream(StreamedContent stream) {
		this.stream = stream;
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

	public StreamedContent getStreamWord() {
		return streamWord;
	}

	public void setStreamWord(StreamedContent streamWord) {
		this.streamWord = streamWord;
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
	}

	public void nomeCliente() {
		for (intra_cndunidade uni : listaDeUnidade) {
			if (uni.getBloco().equals(this.bloco) & uni.getUnidade().equals(this.unidade)) {
				if (this.amBEAN == null) {
					this.amBEAN = new intra_advert_mult();
				}
				this.amBEAN.setNome(StringUtil.trataNomeComposto(uni.getNome()));
			}
		}
	}

	public void limparListas() {
		this.listaDeBloco = null;
		this.listaDeUnidade = null;
		// this.cndUnidaBEAN = new intra_cndunidade();
		this.icBEAN = new intra_condominios();
		this.amBEAN = new intra_advert_mult();
		this.valor1 = 0;
	}

	public void limpaTexto() {
		if (this.amBEAN.getValidaTexto().equals("S")) {
			this.amBEAN.setTexto("");
		}
	}

	@SuppressWarnings("static-access")
	public void salvarAdvertencia() {
		this.amDAO = new AdvertenciaMultaDAO();
		Date d;
		try {
			d = (Date) sdf.parse(this.amBEAN.getDataOcorrido());
			this.amBEAN.setOcorrido(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.amBEAN.setCondominio(this.condominio);
		this.amBEAN.setBloco(this.bloco);
		this.amBEAN.setUnidade(this.unidade);
		this.amBEAN.setTipo(1);

		this.amBEAN.setNomeCondominio(this.trataNomeComposto(this.icBEAN.getNome()));
		this.amBEAN.setNome(this.trataNomeComposto(this.amBEAN.getNome()));
		this.amBEAN.setNomeGeren(this.trataNomeComposto(this.icBEAN.getNomeGerente()));
		this.amBEAN.setEmailGeren(this.icBEAN.getEmailGerente());
		this.amBEAN.setDataAtual(this.dataAtual);
		this.amBEAN.setCodigoGeren(this.sessaoMB.getGerenteSelecionado().getCodigo());

		if (this.amBEAN != null) {
			if (!this.amBEAN.getBloco().isEmpty() & !this.amBEAN.getUnidade().isEmpty()
					& !this.amBEAN.getSexo().trim().isEmpty() & !this.amBEAN.getNome().trim().isEmpty()) {
				try {
					this.imprimirBEAN = new intra_advert_mult();
					this.amBEAN.setCodigo(0);
					this.imprimirBEAN = this.amDAO.salvar(amBEAN, this.sessaoMB);

					DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
							.findComponent("tbvAdvMul:frmHistorico:dtHistorico");
					table.setValue(null);
					RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");

					this.amBEAN = new intra_advert_mult();
					this.valor1 = 0;
					this.bloco = "";
					this.unidade = "";
					this.listaAdvmMul = null;
					this.filtroAdvmMul = null;
					this.msgSalvo();
				} catch (Exception e) {
					this.msgErro();
				}
			} else {
				this.msgObrigatorio();
			}
		}
	}

	@SuppressWarnings("static-access")
	public void salvarMulta() {
		this.amDAO = new AdvertenciaMultaDAO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d;
		try {
			d = (Date) sdf.parse(this.amBEAN.getDataOcorrido());
			this.amBEAN.setOcorrido(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (this.amBEAN != null) {
			this.amBEAN.setCondominio(this.condominio);
			this.amBEAN.setBloco(this.bloco);
			this.amBEAN.setUnidade(this.unidade);
			this.amBEAN.setTipo(2);

			this.amBEAN.setNomeCondominio(this.trataNomeComposto(this.icBEAN.getNome()));
			this.amBEAN.setNome(this.trataNomeComposto(this.amBEAN.getNome()));
			this.amBEAN.setNomeGeren(this.trataNomeComposto(this.icBEAN.getNomeGerente()));
			this.amBEAN.setEmailGeren(this.icBEAN.getEmailGerente());
			this.amBEAN.setDataAtual(this.dataAtual);
			this.amBEAN.setCodigoGeren(this.icBEAN.getCodigoGerente());

			if (!this.amBEAN.getBloco().trim().isEmpty() & !this.amBEAN.getUnidade().trim().isEmpty()
					& !this.amBEAN.getSexo().trim().isEmpty() & !this.amBEAN.getNome().trim().isEmpty()) {
				try {
					this.imprimirBEAN = new intra_advert_mult();
					this.icBEAN.setCodigo(0);
					this.imprimirBEAN = this.amDAO.salvar(amBEAN, this.sessaoMB);
					DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
							.findComponent("tbvAdvMul:frmHistorico:dtHistorico");
					table.setValue(null);
					RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
					this.amBEAN = new intra_advert_mult();
					this.valor1 = 0;
					this.listaAdvmMul = null;
					this.filtroAdvmMul = null;
					this.bloco = "";
					this.unidade = "";
					this.msgSalvo();
				} catch (Exception e) {
					this.msgErro();
				}
			}
		}
	}

	public void alterarAdvMul() throws ParseException {
		this.amDAO = new AdvertenciaMultaDAO();
		Date d;
		d = (Date) sdf.parse(this.amBEAN.getDataOcorrido());
		this.amBEAN.setOcorrido(d);
		this.amDAO.alterarAdvm(this.amBEAN, this.sessaoMB);
		DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("tbvAdvMul:frmHistorico:dtHistorico");
		table.setValue(null);
		RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
		this.listaAdvmMul = null;
		this.filtroAdvmMul = null;
		this.bloco = "";
		this.unidade = "";
		this.msgAlterado();
	}

	public void deletarAdvm() {
		if (this.amBEAN != null) {
			if (this.amBEAN.getCodigo() > 0) {
				this.amDAO = new AdvertenciaMultaDAO();
				this.amDAO.deletarAdvertencia(this.amBEAN, this.sessaoMB);
				DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
						.findComponent("tbvAdvMul:frmHistorico:dtHistorico");
				table.setValue(null);
				RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
				this.listaAdvmMul = null;
				this.filtroAdvmMul = null;
				this.bloco = "";
				this.unidade = "";
				this.msgExclusao();
			}
		}
	}

	public void handleFileUpload(FileUploadEvent event) throws FileNotFoundException {
		this.amDAO = new AdvertenciaMultaDAO();
		this.nomeArquivo = event.getFile().getFileName();
		this.arquivo = event.getFile().getContents();
		this.amBEAN.setProtocolo(null);
		this.amBEAN.setProtocolo(this.arquivo);

		this.amDAO.salvarProtocolo(this.amBEAN, this.sessaoMB);

		DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("tbvAdvMul:frmHistorico:dtHistorico");
		table.setValue(null);
		RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
		this.listaAdvmMul = null;
		this.filtroAdvmMul = null;

		this.msgAdicinado();
	}

	public void geraRel() {
		this.imprimirBEAN = this.amBEAN;
		if (this.amBEAN != null) {
			if (this.amBEAN.getCondominio() > 0) {

				if (this.amBEAN.getValor() == null) {
					try {
						if (this.amBEAN.getTipoRegCov().equals("O")) {
							this.nomeArquivo = "advertencia_outros";
						} else {
							this.nomeArquivo = "advertencia";
						}
						this.gerarelatorioAdvertencia();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						if (this.amBEAN.getTipoRegCov().equals("O")) {
							this.nomeArquivo = "multa_outros";
						} else {
							this.nomeArquivo = "multa";
						}
						this.gerarelatorioMulta();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				this.msgSelecione();
			}
		} else {
			this.msgSelecione();
		}
	}

	public StreamedContent geraRelWord() {
		ExportWord expo = new ExportWord();
		if (this.amBEAN == null) {
			this.amBEAN = this.imprimirBEAN;
		} else if (this.amBEAN.getCondominio() == 0) {
			this.amBEAN = this.imprimirBEAN;
		}
		this.imprimirBEAN = this.amBEAN;

		if (this.amBEAN != null) {
			if (this.amBEAN.getCondominio() > 0) {

				if (this.amBEAN.getValor() == null) {
					try {
						if (this.amBEAN.getTipoRegCov().equals("O")) {

							this.nomeArquivo = "advertencia_outros";
							Date data = this.amBEAN.getDataAtual();
							String uniBloc = "Unidade: " + this.amBEAN.getUnidade();
							if (this.amBEAN.getBloco() != null) {
								if (!this.amBEAN.getBloco().trim().equals("0")) {
									uniBloc += "    Torre: " + this.amBEAN.getBloco();
								}
							}
							String ocorridoMotivo = "Desta forma, solicitamos sua especial atenção para que o fato ocorrido em "
									+ this.amBEAN.getDataOcorrido() + ", quando " + this.amBEAN.getMotivo()
									+ ", não mais ocorra.";
							String texto = this.amBEAN.getTexto();
							this.streamWord = expo.advWordOutros(data, this.amBEAN.getNomeCondominio(),
									this.amBEAN.getSexo().equals("F") ? "Sra. " + this.amBEAN.getNome()
											: "Sr. " + this.amBEAN.getNome(),
									uniBloc, ocorridoMotivo, this.amBEAN.getNomeGeren(), this.amBEAN.getEmailGeren(),
									texto);

						} else {

							Date data = this.amBEAN.getDataAtual();
							String uniBloc = "Unidade: " + this.amBEAN.getUnidade();
							if (this.amBEAN.getBloco() != null) {
								if (!this.amBEAN.getBloco().trim().equals("0")) {
									uniBloc += "    Torre: " + this.amBEAN.getBloco();
								}
							}
							String tipoReg = this.amBEAN.getTipoRegCov().equals("R")
									? "Com base neste preceito, em atendimento a solicitação do Corpo Diretivo e objetivando manter a boa convivência entre todos, informamos que o Regulamento Interno do Condomínio, estabelece o quanto segue:"
									: "Com base neste preceito, em atendimento a solicitação do Corpo Diretivo e objetivando manter a boa convivência entre todos, informamos que a Convenção do Condomínio, estabelece o quanto segue:";
							String tipoClauArt = this.amBEAN.isItemClausula() == true
									? "- Cláusula " + this.amBEAN.getClausula() + ", Artigo " + this.amBEAN.getArtigo()
											+ ":“" + this.amBEAN.getFrase() + "”" + "."
									: "Artigo " + this.amBEAN.getArtigo() + ": “" + this.amBEAN.getFrase() + "”" + ".";
							String ocorridoMotivo = "Desta forma, solicitamos sua especial atenção para que o fato ocorrido em "
									+ this.amBEAN.getDataOcorrido() + ", quando " + this.amBEAN.getMotivo()
									+ ", não mais ocorra.";
							String texto = this.amBEAN.getTexto();
							this.streamWord = expo.advWord(data, this.amBEAN.getNomeCondominio(),
									this.amBEAN.getSexo().equals("F") ? "Sra. " + this.amBEAN.getNome()
											: "Sr. " + this.amBEAN.getNome(),
									uniBloc, tipoReg, tipoClauArt, ocorridoMotivo, this.amBEAN.getNomeGeren(),
									this.amBEAN.getEmailGeren(), texto);

						}
						// this.gerarelatorioAdvertencia();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						if (this.amBEAN.getTipoRegCov().equals("O")) {

							Date data = this.amBEAN.getDataAtual();
							String uniBloc = "Unidade: " + this.amBEAN.getUnidade();
							if (this.amBEAN.getBloco() != null) {
								if (!this.amBEAN.getBloco().trim().equals("0")) {
									uniBloc += "    Torre: " + this.amBEAN.getBloco();
								}
							}
							String ocorridoMotivo = "Desta forma, solicitamos sua especial atenção para que o fato ocorrido em "
									+ this.amBEAN.getDataOcorrido() + ", quando " + this.amBEAN.getMotivo()
									+ ", não mais ocorra.";
							String valor = "Mediante ao exposto, considerando tratar-se de reincidência, fica aplicada multa de R$ "
									+ this.amBEAN.getValor() + " (" + this.amBEAN.getValorExtenso()
									+ "), com vencimento em " + this.amBEAN.getVencimento() + ".";
							String texto = this.amBEAN.getTexto();
							this.streamWord = expo.multWordOutros(data, this.amBEAN.getNomeCondominio(),
									this.amBEAN.getSexo().equals("F") ? "Sra. " + this.amBEAN.getNome()
											: "Sr. " + this.amBEAN.getNome(),
									uniBloc, ocorridoMotivo, this.amBEAN.getNomeGeren(), this.amBEAN.getEmailGeren(),
									texto, valor);

						} else {

							Date data = this.amBEAN.getDataAtual();
							String uniBloc = "Unidade: " + this.amBEAN.getUnidade();
							if (this.amBEAN.getBloco() != null) {
								if (!this.amBEAN.getBloco().trim().equals("0")) {
									uniBloc += "    Torre: " + this.amBEAN.getBloco();
								}
							}
							String tipoReg = this.amBEAN.getTipoRegCov().equals("R")
									? "Com base neste preceito, em atendimento a solicitação do Corpo Diretivo e objetivando manter a boa convivência entre todos, informamos que o Regulamento Interno do Condomínio, estabelece o quanto segue:"
									: "Com base neste preceito, em atendimento a solicitação do Corpo Diretivo e objetivando manter a boa convivência entre todos, informamos que a Convenção do Condomínio, estabelece o quanto segue:";
							String tipoClauArt = this.amBEAN.isItemClausula() == true
									? "- Cláusula " + this.amBEAN.getClausula() + ", Artigo " + this.amBEAN.getArtigo()
											+ ":“" + this.amBEAN.getFrase() + "”" + "."
									: "Artigo " + this.amBEAN.getArtigo() + ": “" + this.amBEAN.getFrase() + "”" + ".";
							String ocorridoMotivo = "Desta forma, solicitamos sua especial atenção para que o fato ocorrido em "
									+ this.amBEAN.getDataOcorrido() + ", quando " + this.amBEAN.getMotivo()
									+ ", não mais ocorra.";
							String texto = this.amBEAN.getTexto();
							String valor = "Mediante ao exposto, considerando tratar-se de reincidência, fica aplicada multa de R$ "
									+ this.amBEAN.getValor() + " (" + this.amBEAN.getValorExtenso()
									+ "), com vencimento em " + this.amBEAN.getVencimento() + ".";
							this.streamWord = expo.multWord(data, this.amBEAN.getNomeCondominio(),
									this.amBEAN.getSexo().equals("F") ? "Sra. " + this.amBEAN.getNome()
											: "Sr. " + this.amBEAN.getNome(),
									uniBloc, tipoReg, tipoClauArt, ocorridoMotivo, this.amBEAN.getNomeGeren(),
									this.amBEAN.getEmailGeren(), texto, valor);

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				this.msgSelecione();
			}
		} else {
			this.msgSelecione();
		}
		return this.streamWord;
	}

	public void geraRel2() {
		if (this.imprimirBEAN != null) {
			if (this.imprimirBEAN.getCondominio() > 0) {

				if (this.imprimirBEAN.getValor() == null) {
					try {
						if (this.amBEAN.getTipoRegCov().equals("O")) {
							this.nomeArquivo = "advertencia_outros";
						} else {
							this.nomeArquivo = "advertencia";
						}
						this.gerarelatorioAdvertencia();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						if (this.imprimirBEAN.getTipoRegCov().equals("O")) {
							this.nomeArquivo = "multa_outros";
						} else {
							this.nomeArquivo = "multa";
						}
						this.gerarelatorioMulta();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				this.msgErro();
			}
		} else {
			this.msgErro();
		}
	}

	/**
	 * Metodo para gerar o relatório
	 * 
	 * @throws Exception
	 */
	public void gerarelatorioAdvertencia() throws Exception {
		String nome = "";
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		if (this.imprimirBEAN.getTipoRegCov().equals("O")) {
			nome = "advertencia_outros";
		} else {
			nome = "advertencia";
		}
		HashMap<Object, Object> parametros = new HashMap<>();
		parametros.put("codigo", this.imprimirBEAN.getCodigo());
		this.nomeArquivo = oncapture(rju.geraRel2(parametros, nome, nome, 1));
	}

	/**
	 * Metodo para gerar o relatório
	 * 
	 * @throws Exception
	 */
	public void gerarelatorioMulta() throws Exception {
		String nome = "";
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		if (this.imprimirBEAN.getTipoRegCov().equals("O")) {
			nome = "multa_outros";
		} else {
			nome = "multa";
		}
		HashMap<Object, Object> parametros = new HashMap<>();
		parametros.put("codigo", this.imprimirBEAN.getCodigo());
		this.nomeArquivo = oncapture(rju.geraRel2(parametros, nome, nome, 1));
	}

	public void gerarelatorioExcel() throws Exception {
		int colina;
		RelatorioJasperUtil rju = new RelatorioJasperUtil();
		if (this.todos) {
			colina = 2;
		} else {
			colina = 1;
		}

		HashMap<Object, Object> parametros = new HashMap<>();
		parametros.put("tipoPesquisa", colina);
		parametros.put("codigo", colina == 1 ? this.condominio : this.sessaoMB.getGerenteSelecionado().getCodigo());

		rju.geraRel2(parametros, "AdvMultRelatorio", "AdvMultRelatorio", 2);

		this.stream = rju.getStream();
	}

	/**
	 * Metodo de converção
	 * 
	 * @param relatorio
	 * 			@return, Converte os bytes para um arquivo pdf
	 */
	public String oncapture(byte[] relatorio) {
		String rel = getRandomImageName();
		this.adv.add(rel);
		String newFileName;
		if (relatorio != null) {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			newFileName = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator + rel
					+ ".pdf";
			FileOutputStream imageOutput;
			try {
				imageOutput = new FileOutputStream(new File(newFileName));
				imageOutput.write(relatorio);
				imageOutput.close();
			} catch (Exception e) {
				throw new FacesException("Erro na Converção!");
			}
		} else {
			newFileName = "relatoryweek";
			rel = newFileName;
		}
		return rel;
	}

	/**
	 * Metodo radom para gerar o relatório
	 */
	private String getRandomImageName() {
		int i = (int) (Math.random() * 10000000);
		return String.valueOf(i);
	}

	public void converteValor() {
		this.amBEAN.setValor(this.df.format(valor1));
	}

	public void downloadPDF(byte[] retorno, String nome) throws IOException {
		// Prepare.
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			// Open file.
			input = new BufferedInputStream(new ByteArrayInputStream(retorno), DEFAULT_BUFFER_SIZE);
			// Init servlet response.
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(retorno.length));
			response.setHeader("Content-Disposition", "inline;filename=\"" + nome + ".pdf\"");
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			// Finalize task.
			output.flush();
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}
		// Inform JSF that it doesn't need to handle response.
		// This is very important, otherwise you will get the following
		// exception in the logs:
		// java.lang.IllegalStateException: Cannot forward after response has
		// been committed.
		facesContext.responseComplete();
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail
				// it. It may be useful to
				// know that this will generally only be thrown when the client
				// aborted the download.
				e.printStackTrace();
			}
		}
	}

}
