package br.com.oma.intranet.managedbeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.ConcessionariaDAO;
import br.com.oma.intranet.entidades.Concessionaria;
import br.com.oma.intranet.entidades.DebitoAutomatico;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.intranet.entidades.intra_controle_concessionarias;
import br.com.oma.intranet.entidades.intra_grupo_gerente;
import br.com.oma.intranet.util.IntranetException;

@ViewScoped
@ManagedBean
public class ConcessionariaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private intra_controle_concessionarias concessionaria = new intra_controle_concessionarias();
	private intra_condominios condominio;
	private intra_grupo_gerente gerente;
	private List<Concessionaria> listaConcessionaria, fltrConcessionaria;
	private List<intra_condominios> listaCondominios;
	private List<Short> listaCndShort;
	private int ano, idImg;
	private List<Integer> anos;
	private String pesquisarPor;

	@PostConstruct
	public void init() {
		this.ano = Calendar.getInstance().get(Calendar.YEAR);
		this.anos = new ArrayList<>();
		this.anos.add(Calendar.getInstance().get(Calendar.YEAR) - 4);
		this.anos.add(Calendar.getInstance().get(Calendar.YEAR) - 3);
		this.anos.add(Calendar.getInstance().get(Calendar.YEAR) - 2);
		this.anos.add(Calendar.getInstance().get(Calendar.YEAR) - 1);
		this.anos.add(Calendar.getInstance().get(Calendar.YEAR));
		this.anos.add(Calendar.getInstance().get(Calendar.YEAR) + 1);
		this.pesquisarPor = "Condominio";
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public intra_controle_concessionarias getConcessionaria() {
		return concessionaria;
	}

	public void setConcessionaria(intra_controle_concessionarias concessionaria) {
		this.concessionaria = concessionaria;
	}

	public intra_condominios getCondominio() {
		return condominio;
	}

	public void setCondominio(intra_condominios condominio) {
		this.condominio = condominio;
	}

	public intra_grupo_gerente getGerente() {
		return gerente;
	}

	public void setGerente(intra_grupo_gerente gerente) {
		this.gerente = gerente;
	}

	public List<Concessionaria> getListaConcessionaria() {
		return listaConcessionaria;
	}

	public void setListaConcessionaria(List<Concessionaria> listaConcessionaria) {
		this.listaConcessionaria = listaConcessionaria;
	}

	public List<Concessionaria> getFltrConcessionaria() {
		return fltrConcessionaria;
	}

	public void setFltrConcessionaria(List<Concessionaria> fltrConcessionaria) {
		this.fltrConcessionaria = fltrConcessionaria;
	}

	public List<intra_condominios> getListaCondominios() {
		if (this.listaCondominios == null) {
			if (this.pesquisarPor != null && this.pesquisarPor.equals("Condominio")) {
				this.listarTodosCondominios();
			} else {
				this.listarCondominiosGerente();
			}
		}
		return listaCondominios;
	}

	public void setListaCondominios(List<intra_condominios> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public List<Short> getListaCndShort() {
		if (this.listaCondominios != null) {
			this.listaCndShort = new ArrayList<>();
			for (intra_condominios aux : this.listaCondominios) {
				this.listaCndShort.add((short) aux.getCodigo());
			}
		}
		return listaCndShort;
	}

	public void setListaCndShort(List<Short> listaCndShort) {
		this.listaCndShort = listaCndShort;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getIdImg() {
		return idImg;
	}

	public void setIdImg(int idImg) {
		this.idImg = idImg;
	}

	public List<Integer> getAnos() {
		return anos;
	}

	public void setAnos(List<Integer> anos) {
		this.anos = anos;
	}

	public String getPesquisarPor() {
		return pesquisarPor;
	}

	public void setPesquisarPor(String pesquisarPor) {
		this.pesquisarPor = pesquisarPor;
	}

	public void salvarNovaConcessionaria() {
		ConcessionariaDAO dao = new ConcessionariaDAO();
		dao.salvarNovaConcessionaria(this.concessionaria);
	}

	public void pesquisarConcessionariasCnd() {
		try {

			if (this.condominio == null) {
				throw new IntranetException("Selecione um condomínio para pesquisar!");
			}

			// long segundos = System.currentTimeMillis();
			this.getListaCndShort();
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo de pesquisa de condomínios:" +
			// (segundos / 1000.0));

			this.listaConcessionaria = new ArrayList<>();

			// segundos = System.currentTimeMillis();
			ConcessionariaDAO dao = new ConcessionariaDAO();
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para criar conexão:" + (segundos /
			// 1000.0));

			// segundos = System.currentTimeMillis();
			List<intra_controle_concessionarias> lista = new ArrayList<>();
			List<intra_controle_concessionarias> conc = new ArrayList<>();
			conc.addAll(dao.pesquisarConcessionariasCnd(this.ano, (short) this.condominio.getCodigo()));
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para pesquisar concessionárias
			// pendentes:" + (segundos / 1000.0));

			// segundos = System.currentTimeMillis();
			lista.addAll(dao.pesquisarConcessionariasPagasCnd(this.ano, (short) this.condominio.getCodigo()));
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para pesquisar concessionárias pagas:"
			// + (segundos / 1000.0));

			// segundos = System.currentTimeMillis();
			for (intra_controle_concessionarias aux : conc) {
				if (!lista.contains(aux)) {
					lista.add(aux);
				}
			}
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para limpar lista de concessionárias
			// repetidas:" + (segundos / 1000.0));

			// segundos = System.currentTimeMillis();
			this.constroiConcessionaria(lista, dao);
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para construir objetos das
			// concessionárias:" + (segundos / 1000.0));

		} catch (IntranetException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pesquisarConcessionarias() {
		try {
			// long segundos = System.currentTimeMillis();

			if (this.gerente == null) {
				throw new IntranetException("Selecione um gerente para pesquisar!");
			}

			this.getListaCndShort();
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo de pesquisa de condomínios:" +
			// (segundos / 1000.0));

			this.listaConcessionaria = new ArrayList<>();

			// segundos = System.currentTimeMillis();
			ConcessionariaDAO dao = new ConcessionariaDAO();
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para criar conexão:" + (segundos /
			// 1000.0));

			// segundos = System.currentTimeMillis();
			List<intra_controle_concessionarias> lista = new ArrayList<>();
			List<intra_controle_concessionarias> conc = new ArrayList<>();
			conc.addAll(dao.pesquisarConcessionarias(this.ano, this.listaCndShort));
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para pesquisar concessionárias
			// pendentes:" + (segundos / 1000.0));

			// segundos = System.currentTimeMillis();
			lista.addAll(dao.pesquisarConcessionariasPagas(this.ano, this.listaCndShort));
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para pesquisar concessionárias pagas:"
			// + (segundos / 1000.0));

			// segundos = System.currentTimeMillis();
			for (intra_controle_concessionarias aux : conc) {
				if (!lista.contains(aux)) {
					lista.add(aux);
				}
			}
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para limpar lista de concessionárias
			// repetidas:" + (segundos / 1000.0));

			// segundos = System.currentTimeMillis();
			this.constroiConcessionaria(lista, dao);
			// segundos = System.currentTimeMillis() - segundos;
			// System.out.println("Tempo para construir objetos das
			// concessionárias:" + (segundos / 1000.0));
		} catch (IntranetException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void constroiConcessionaria(List<intra_controle_concessionarias> concessionarias, ConcessionariaDAO dao) {
		Concessionaria c = null;
		for (intra_controle_concessionarias aux : concessionarias) {
			c = new Concessionaria();
			c.setControle(new intra_controle_concessionarias());
			c.getControle().setFornecedor(aux.getFornecedor());
			c.getControle().setCodigoDebito(aux.getCodigoDebito());
			c.getControle().setCodigoCondominio(aux.getCodigoCondominio());
			c.getControle().setObs(aux.getObs());
			c.getControle().setDebitoAutomatico(aux.isDebitoAutomatico());
			this.constroiAnoConcessionaria(c, dao);
			this.listaConcessionaria.add(c);
		}
	}

	public void constroiAnoConcessionaria(Concessionaria c, ConcessionariaDAO dao) {
		List<DebitoAutomatico> l = dao.pesquisarLanctoConcPagos(Long.parseLong(c.getControle().getCodigoDebito()),
				c.getControle().getCodigoCondominio(), this.ano);
		l.addAll(dao.pesquisarLanctoConc(Long.parseLong(c.getControle().getCodigoDebito()),
				c.getControle().getCodigoCondominio(), this.ano));
		for (DebitoAutomatico aux : l) {
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 1) {
				c.setValorJan(aux.getValor());
				c.setImgJan(aux.getNrolancto());
				c.setDiaJan(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgJan(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitJan(aux.isPago() ? "L" : "P");
				c.setConsumoJan(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 2) {
				c.setValorFev(aux.getValor());
				c.setImgFev(aux.getNrolancto());
				c.setDiaFev(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgFev(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitFev(aux.isPago() ? "L" : "P");
				c.setConsumoFev(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 3) {
				c.setValorMar(aux.getValor());
				c.setImgMar(aux.getNrolancto());
				c.setDiaMar(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgMar(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitMar(aux.isPago() ? "L" : "P");
				c.setConsumoMar(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 4) {
				c.setValorAbr(aux.getValor());
				c.setImgAbr(aux.getNrolancto());
				c.setDiaAbr(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgAbr(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitAbr(aux.isPago() ? "L" : "P");
				c.setConsumoAbr(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 5) {
				c.setValorMai(aux.getValor());
				c.setImgMai(aux.getNrolancto());
				c.setDiaMai(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgMai(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitMai(aux.isPago() ? "L" : "P");
				c.setConsumoMai(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 6) {
				c.setValorJun(aux.getValor());
				c.setImgJun(aux.getNrolancto());
				c.setDiaJun(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgJun(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitJun(aux.isPago() ? "L" : "P");
				c.setConsumoJun(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 7) {
				c.setValorJul(aux.getValor());
				c.setImgJul(aux.getNrolancto());
				c.setDiaJul(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgJul(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitJul(aux.isPago() ? "L" : "P");
				c.setConsumoJul(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 8) {
				c.setValorAgo(aux.getValor());
				c.setImgAgo(aux.getNrolancto());
				c.setDiaAgo(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgAgo(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitAgo(aux.isPago() ? "L" : "P");
				c.setConsumoAgo(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 9) {
				c.setValorSet(aux.getValor());
				c.setImgSet(aux.getNrolancto());
				c.setDiaSet(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgSet(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitSet(aux.isPago() ? "L" : "P");
				c.setConsumoSet(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 10) {
				c.setValorOut(aux.getValor());
				c.setImgOut(aux.getNrolancto());
				c.setDiaOut(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgOut(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitOut(aux.isPago() ? "L" : "P");
				c.setConsumoOut(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 11) {
				c.setValorNov(aux.getValor());
				c.setImgNov(aux.getNrolancto());
				c.setDiaNov(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgNov(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitNov(aux.isPago() ? "L" : "P");
				c.setConsumoNov(aux.getConsumoDouble());
			}
			if (new DateTime(aux.getVencimento()).getMonthOfYear() == 12) {
				c.setValorDez(aux.getValor());
				c.setImgDez(aux.getNrolancto());
				c.setDiaDez(new DateTime(aux.getVencimento()).getDayOfMonth());
				c.setPossuiImgDez(possuiImg(aux.getEstimado(), Integer.valueOf(aux.getNrolancto()), dao));
				c.setSitDez(aux.isPago() ? "L" : "P");
				c.setConsumoDez(aux.getConsumoDouble());
			}
		}
		c.populaPendencia();
	}

	public void limpar() {
		this.concessionaria = new intra_controle_concessionarias();
		this.listaConcessionaria = null;
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

	public boolean possuiImg(String tipo_lcto, int nrolancto, ConcessionariaDAO dao) {
		return dao.possuiImg(nrolancto, tipo_lcto);
	}

	public void limparTbl() {
		this.listaConcessionaria = null;
		this.fltrConcessionaria = null;
		DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmTblConc:tblConc");
		d.setValue(null);
		RequestContext.getCurrentInstance().execute("$('.ui-column-filter').val('');");
	}

	public void limparListas() {
		this.listaConcessionaria = null;
		this.fltrConcessionaria = null;
		this.listaCndShort = null;
	}

	public void listarCondominiosGerente() {
		try {
			if (this.gerente != null) {
				ConcessionariaDAO dao = new ConcessionariaDAO();
				this.limparTbl();
				this.listaCondominios = dao.getListaCondominios(this.gerente.getCodigo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listarTodosCondominios() {
		try {
			ConcessionariaDAO dao = new ConcessionariaDAO();
			this.listaCondominios = dao.getListaTodosCondominios();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
