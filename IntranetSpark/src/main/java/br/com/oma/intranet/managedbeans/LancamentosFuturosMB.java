package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.joda.time.DateTime;
import org.primefaces.event.FileUploadEvent;

import au.com.bytecode.opencsv.CSVReader;
import br.com.oma.intranet.dao.ConcessionariaDAO;
import br.com.oma.intranet.dao.LancamentosFuturosDAO;
import br.com.oma.intranet.entidades.DACSV;
import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.entidades.intra_conciliacao_lf;
import br.com.oma.pc.modelo.entidades.Condominio;

@ViewScoped
@ManagedBean
public class LancamentosFuturosMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6086097211267971077L;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	private int idImg;
	private List<DACSV> listaDACSV, listaSelecionada;
	private int qtdEncontrados;
	private int qtdCndSaiu;
	private int fonte;
	private double valorTotal;

	public int getIdImg() {
		return idImg;
	}

	public void setIdImg(int idImg) {
		this.idImg = idImg;
	}

	public List<DACSV> getListaDACSV() {
		return listaDACSV;
	}

	public void setListaDACSV(List<DACSV> listaDACSV) {
		this.listaDACSV = listaDACSV;
	}

	public List<DACSV> getListaSelecionada() {
		return listaSelecionada;
	}

	public void setListaSelecionada(List<DACSV> listaSelecionada) {
		this.listaSelecionada = listaSelecionada;
	}

	public int getQtdEncontrados() {
		return qtdEncontrados;
	}

	public void setQtdEncontrados(int qtdEncontrados) {
		this.qtdEncontrados = qtdEncontrados;
	}

	public int getQtdCndSaiu() {
		return qtdCndSaiu;
	}

	public void setQtdCndSaiu(int qtdCndSaiu) {
		this.qtdCndSaiu = qtdCndSaiu;
	}

	public int getFonte() {
		return fonte;
	}

	public void setFonte(int fonte) {
		this.fonte = fonte;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void upload(FileUploadEvent event)
			throws IOException, InvalidFormatException, SQLException, java.text.ParseException, ClassNotFoundException {
		byte[] arquivo = event.getFile().getContents();
		try {
			this.listaDACSV = this.lerCsv(arquivo);
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));
			e.printStackTrace();
		}
	}

	public List<DACSV> lerCsv(byte[] arquivo) throws IOException, ParseException {
		this.qtdEncontrados = 0;
		this.qtdCndSaiu = 0;
		LancamentosFuturosDAO dao = new LancamentosFuturosDAO();
		DateTime hoje = new DateTime(new Date());
		NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
		nf.setMaximumFractionDigits(2);
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		List<DACSV> dacsv = new ArrayList<>();
		DACSV csv = null;
		CSVReader csvReader = new CSVReader(new InputStreamReader(new ByteArrayInputStream(arquivo)), '#');
		String[] row = null;
		List<Integer> lCndSaida = new ArrayList<>();
		lCndSaida.addAll(dao.getListaCndSaida());

		// POPULA A LISTA COM AS INFORMAÇÕES DO ARQUIVO CSV
		while ((row = csvReader.readNext()) != null) {
			String[] colunas = row[0].trim().replace(",", "###").split(";");
			if (colunas != null && colunas.length >= 1) {
				String dataPagto = colunas[0];
				String id = null;
				String valor = null;
				if (colunas.length > 1) {
					id = this.retornaID(colunas[1]);
					if (id.trim().length() >= 5) {
						if (colunas.length > 4) {

							valor = colunas[4].replace(".", "");
							valor = valor.replace("###", ".");

							if (!NumberUtils.isNumber(valor)) {
								valor = "0.0";
							}

							valor = String.valueOf(Double.valueOf(Double.parseDouble(valor) * (-1)));
							if (NumberUtils.isNumber(StringUtils.stripStart(id, "0"))) {
								csv = new DACSV();
								if (dataPagto != null && !dataPagto.trim().isEmpty()) {
									csv.setDataPagto(sf.parse(dataPagto));
								}
								csv.setCodigoDebito(StringUtils.stripStart(id, "0"));
								csv.setValor(Double.parseDouble(valor));

								if (new DateTime(csv.getDataPagto()).isAfter(hoje)) {
									dacsv.add(csv);
								}
							}
						}
					}
				}
			}
		}

		dacsv = this.agrupaDuplicados(dacsv);

		// VERIFICA OS BAIXADOS
		List<DACSV> dacsv2 = new ArrayList<>();
		if (dacsv != null && dacsv.size() > 0) {
			List<DebitoAutomatico> listaDA = dao.pesquisaBaixado();
			if (listaDA == null) {
				listaDA = new ArrayList<>();
			}
			List<String> listaBaixados = new ArrayList<>();
			for (DebitoAutomatico aux : listaDA) {
				listaBaixados.add(aux.getCodDebAutom());
			}
			for (DACSV csv2 : dacsv) {
				for (DebitoAutomatico aux : listaDA) {
					if (aux.getCodDebAutom().contains(csv2.getCodigoDebito()) && csv2.getValor() == aux.getValor()) {
						csv2.setVencimento(aux.getVencimento());
						csv2.setNrolancto(aux.getNrolancto());
						csv2.setValorBanco(aux.getValor());
						csv2.setBaixado(true);
						this.valorTotal += csv2.getValor();
						this.qtdEncontrados++;
					}
				}

				// VERIFICA OS DÉBITOS AUTOMÁTICOS CONCILIADOS QUE JÁ FORAM
				// SALVOS
				List<intra_conciliacao_lf> lConc = dao.pesquisarConciliados();
				List<String> lNrolancto = new ArrayList<>();
				for (intra_conciliacao_lf aux : lConc) {
					lNrolancto.add(
							String.valueOf(aux.getNro_identificacao() + aux.getDataPagto() + aux.getValor()).trim());
				}
				SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
				String tester = "";
				if (csv2.getDataPagto() != null) {
					tester = String.valueOf(csv2.getCodigoDebito() + sf2.format(csv2.getDataPagto()) + csv2.getValor())
							.trim();
				}
				if (!lNrolancto.contains(tester)) {
					csv2.setCnd(this.pesquisaCodNomeCnd(Long.parseLong(csv2.getCodigoDebito()), dao));
					if (csv2.getCnd() != null) {
						if (lCndSaida.contains(Integer.valueOf(csv2.getCnd().getCodigo()))) {
							csv2.setSaidaCnd(true);
							this.qtdCndSaiu++;
						}
					}
					csv2.setPossuiImg(this.possuiImg("P", csv2.getNrolancto(), dao));
					csv2.setCredor(dao.pesquisaFornecedor(Long.parseLong(csv2.getCodigoDebito())));
					dacsv2.add(csv2);
				}
			}
		}
		csvReader.close();
		return dacsv2;
	}

	public String retornaID(String id) {
		String verificador = id.toString().trim();
		if (verificador.contains("-")) {
			String[] cleaner = verificador.split("-");
			if (cleaner != null) {
				verificador = cleaner[cleaner.length - 1];
			}
		}
		return verificador;
	}

	public Condominio pesquisaCodNomeCnd(long id, LancamentosFuturosDAO dao) {
		return dao.pesquisaCodNomeCnd(id);
	}

	public void baixarConciliados() {
		try {
			if (this.listaDACSV != null) {
				List<DACSV> listaUtil = new ArrayList<>();
				listaUtil.addAll(this.listaDACSV);
				List<intra_conciliacao_lf> l = new ArrayList<>();
				intra_conciliacao_lf c = null;
				LancamentosFuturosDAO dao = new LancamentosFuturosDAO();
				for (DACSV aux : listaUtil) {
					if (aux.isBaixado()) {
						c = new intra_conciliacao_lf();
						c.setNrolancto(aux.getNrolancto());
						c.setDataPagto(aux.getDataPagto());
						c.setVencimento(aux.getVencimento());
						if (aux.getCnd() != null) {
							c.setCondominio(aux.getCnd().getCodigo() + " - " + aux.getCnd().getNome());
						}
						c.setNro_identificacao(aux.getCodigoDebito());
						c.setCredor(aux.getCredor());
						c.setValor(aux.getValor());
						c.setObs(aux.getObs());
						c.setBaixado(true);
						c.setDataBaixado(new Date());
						l.add(c);
						this.listaDACSV.remove(aux);
					}
				}
				if (l != null && l.size() > 0) {
					dao.baixarConciliados(l);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Baixado com sucesso!", ""));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Não há nenhum conciliado para baixar neste arquivo!", ""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Ocorreu um erro inesperado!", "Por favor, entre em contato com o administrador!"));
		}
	}

	public boolean possuiImg(String tipo_lcto, int nrolancto, LancamentosFuturosDAO dao) {
		return dao.possuiImg(nrolancto, tipo_lcto);
	}

	public void baixarImg() {
		try {
			ConcessionariaDAO dao = new ConcessionariaDAO();
			byte[] pdf = dao.pesquisarPDF1(this.idImg);
			if (pdf == null) {
				pdf = dao.pesquisarPDF2(this.idImg);
			}
			if (pdf != null) {
				this.downloadPDF(pdf);
			}
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
			// Open file.
			input = new BufferedInputStream(new ByteArrayInputStream(retorno), DEFAULT_BUFFER_SIZE);
			// Init servlet response.
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(retorno.length));
			response.setHeader("Content-Disposition", "inline;filename=\"EditalWord.pdf\"");
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

	public List<DACSV> agrupaDuplicados(List<DACSV> dacsv2) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		List<DACSV> l = new ArrayList<>();
		List<DACSV> lFinal = new ArrayList<>();
		l.addAll(dacsv2);
		DACSV novo = null;
		for (DACSV aux : dacsv2) {
			int repetido = 0;
			novo = new DACSV();
			novo.setBaixado(aux.isBaixado());
			novo.setCodigoCnd(aux.getCodigoCnd());
			novo.setCodigoDebito(aux.getCodigoDebito());
			novo.setCondominio(aux.getCondominio());
			novo.setCredor(aux.getCredor());
			novo.setDataPagto(aux.getDataPagto());
			novo.setNrolancto(aux.getNrolancto());
			novo.setObs(aux.getObs());
			novo.setPossuiImg(aux.isPossuiImg());
			novo.setValor(aux.getValor());
			novo.setValorBanco(aux.getValorBanco());
			novo.setVencimento(aux.getVencimento());
			for (DACSV aux2 : l) {
				if (String.valueOf(aux.getCodigoDebito() + sf.format(aux.getDataPagto())).trim()
						.equals(String.valueOf(aux2.getCodigoDebito() + sf.format(aux2.getDataPagto())).trim())
						&& aux.getValor() != aux2.getValor()) {
					novo.setValor(novo.getValor() + aux2.getValor());
					if (novo.getObs() != null && novo.getObs().contains("Agrupados")) {
						novo.setObs(novo.getObs() + "; " + aux2.getValor());
					} else {
						novo.setObs("Agrupados valores de: " + retornaStringValor(aux.getValor(), aux2.getValor()));
					}
				}
				if (String.valueOf(aux.getCodigoDebito() + sf.format(aux.getDataPagto())).trim()
						.equals(String.valueOf(aux2.getCodigoDebito() + sf.format(aux2.getDataPagto())).trim())
						&& aux.getValor() == aux2.getValor()) {
					if (repetido >= 1) {
						if (novo.getObs() != null && novo.getObs().contains("Agrupados")) {
							novo.setObs(novo.getObs() + "; " + aux2.getValor());
						} else {
							novo.setObs("Agrupados valores de: " + retornaStringValor(aux.getValor(), aux2.getValor()));
						}
						novo.setValor(novo.getValor() + aux2.getValor());
					}
					repetido++;
				}
			}
			if (!lFinal.contains(novo)) {
				lFinal.add(novo);
			}
		}
		return lFinal;
	}

	public String retornaStringValor(double valor1, double valor2) {
		if (valor1 > valor2) {
			return valor1 + "; " + valor2;
		} else {
			return valor2 + "; " + valor1;
		}
	}

	public void somaSubtraiValorTotal(boolean b, double valor) {
		if (b) {
			this.valorTotal += valor;
			this.qtdEncontrados++;
		} else {
			this.valorTotal -= valor;
			this.qtdEncontrados--;
		}
	}

}
