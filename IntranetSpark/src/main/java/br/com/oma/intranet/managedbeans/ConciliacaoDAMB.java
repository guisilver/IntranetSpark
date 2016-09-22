package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.ConcessionariaDAO;
import br.com.oma.intranet.dao.ConciliacaoDADAO;
import br.com.oma.intranet.entidades.intra_conciliacao;
import br.com.oma.intranet.util.RelatorioJasperUtil;

@ViewScoped
@ManagedBean
public class ConciliacaoDAMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7275955389944339021L;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	private int idImg;
	private Date dataInicio;
	private Date dataFim;
	private List<intra_conciliacao> listaConc;
	private double valorTotal;
	private byte[] relatorio;
	private DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

	@PostConstruct
	public void init() {
		this.dataInicio = new DateTime().minusDays(1).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime().minusDays(1).withHourOfDay(1).withMinuteOfHour(59).withSecondOfMinute(59)
				.toDate();
		this.pesquisarConciliados();
	}

	public int getIdImg() {
		return idImg;
	}

	public void setIdImg(int idImg) {
		this.idImg = idImg;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<intra_conciliacao> getListaConc() {
		return listaConc;
	}

	public void setListaConc(List<intra_conciliacao> listaConc) {
		this.listaConc = listaConc;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean possuiImg(String tipo_lcto, int nrolancto, ConciliacaoDADAO dao) {
		return dao.possuiImg(nrolancto, tipo_lcto);
	}

	public void pesquisarConciliados() {
		try {
			this.valorTotal = 0;
			ConciliacaoDADAO dao = new ConciliacaoDADAO();
			this.dataInicio = new DateTime(this.dataInicio).withMillisOfDay(0).toDate();
			this.dataFim = new DateTime(this.dataFim).withHourOfDay(23).withMinuteOfHour(59).toDate();
			this.listaConc = dao.getListaConciliacao(this.dataInicio, this.dataFim);
			for (intra_conciliacao aux : this.listaConc) {
				aux.setPossuiImg(this.possuiImg("P", aux.getNrolancto(), dao));
				aux.setValor(aux.getValor());
				this.valorTotal += aux.getValor();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void geraRelConciliacao() {
		try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String caminho_relatorio = servletContext.getRealPath("") + File.separator + "relatorios" + File.separator;
			RelatorioJasperUtil rju = new RelatorioJasperUtil();
			HashMap parametros = new HashMap();
			parametros.put("SUBREPORT_DIR", caminho_relatorio);
			parametros.put("dataInicio", this.dataInicio);
			parametros.put("dataFim", this.dataFim);
			parametros.put("valorTotal", df.format(this.valorTotal));
			this.relatorio = rju.geraRelConciliacao(parametros, "Conciliacao", "Conciliacao", 1, this.listaConc);
			RequestContext.getCurrentInstance().execute("clicaBtnRel();");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Ocorreu um erro ao gerar o relatório!", "Contate o administrador."));
			e.printStackTrace();
		}
	}

	public void baixarRel() {
		try {
			this.downloadPDF(this.relatorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void excluir(int codigo) {
		try {
			ConciliacaoDADAO dao = new ConciliacaoDADAO();
			int i = dao.excluir(codigo);
			if (i > 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
						"Ocorreu um erro ao excluir!", "Favor, contate o administrador."));
			}
			this.pesquisarConciliados();
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
}
