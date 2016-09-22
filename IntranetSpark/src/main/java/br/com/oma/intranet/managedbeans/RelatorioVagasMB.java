package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import br.com.oma.intranet.dao.RelatorioVagasDAO;
import br.com.oma.intranet.dao.VagasDAO;
import br.com.oma.intranet.entidades.ResponsavelVaga;
import br.com.oma.intranet.entidades.intra_candidato_vaga;

@ViewScoped
@ManagedBean
public class RelatorioVagasMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5474514158080048777L;
	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;
	private String responsavelVaga;
	private Date dataInicio, dataFim;
	private List<String> listaUsuarioRH;
	private List<intra_candidato_vaga> listaVagas, listaVagasView, fltrVagasView;
	private PieChartModel pizza;
	private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	private boolean pendentes, emAndamento, standBy, canceladas, concluidas;
	private int totalVagas;
	private List<ResponsavelVaga> rv;
	private String situacao;

	@PostConstruct
	public void init() {
		this.dataInicio = new DateTime().withDayOfMonth(1).toDate();
		this.dataFim = new DateTime()
				.withDayOfMonth(new DateTime().withDayOfMonth(1).plusMonths(1).minusDays(1).getDayOfMonth()).toDate();
		this.pendentes = true;
		this.emAndamento = true;
		this.standBy = true;
		this.canceladas = true;
		this.concluidas = true;
		this.responsavelVaga = "Todos";
		this.getListaUsuarioRH();
		this.pesquisaRelatorio();
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public String getResponsavelVaga() {
		return responsavelVaga;
	}

	public void setResponsavelVaga(String responsavelVaga) {
		this.responsavelVaga = responsavelVaga;
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

	public List<String> getListaUsuarioRH() {
		if (this.listaUsuarioRH == null) {
			VagasDAO dao = new VagasDAO();
			this.listaUsuarioRH = dao.getListaUsuarioRH();
			this.listaUsuarioRH.remove("Erika Cristina");
			this.listaUsuarioRH.remove("Pabx");
			this.listaUsuarioRH.remove("Recepção");
			this.listaUsuarioRH.remove("Gisely Gomes");
		}
		return listaUsuarioRH;
	}

	public void setListaUsuarioRH(List<String> listaUsuarioRH) {
		this.listaUsuarioRH = listaUsuarioRH;
	}

	public List<intra_candidato_vaga> getListaVagas() {
		return listaVagas;
	}

	public void setListaVagas(List<intra_candidato_vaga> listaVagas) {
		this.listaVagas = listaVagas;
	}

	public List<intra_candidato_vaga> getListaVagasView() {
		return listaVagasView;
	}

	public void setListaVagasView(List<intra_candidato_vaga> listaVagasView) {
		this.listaVagasView = listaVagasView;
	}

	public List<intra_candidato_vaga> getFltrVagasView() {
		return fltrVagasView;
	}

	public void setFltrVagasView(List<intra_candidato_vaga> fltrVagasView) {
		this.fltrVagasView = fltrVagasView;
	}

	public PieChartModel getPizza() {
		return pizza;
	}

	public void setPizza(PieChartModel pizza) {
		this.pizza = pizza;
	}

	public boolean isPendentes() {
		return pendentes;
	}

	public void setPendentes(boolean pendentes) {
		this.pendentes = pendentes;
	}

	public boolean isEmAndamento() {
		return emAndamento;
	}

	public void setEmAndamento(boolean emAndamento) {
		this.emAndamento = emAndamento;
	}

	public boolean isStandBy() {
		return standBy;
	}

	public void setStandBy(boolean standBy) {
		this.standBy = standBy;
	}

	public boolean isCanceladas() {
		return canceladas;
	}

	public void setCanceladas(boolean canceladas) {
		this.canceladas = canceladas;
	}

	public boolean isConcluidas() {
		return concluidas;
	}

	public void setConcluidas(boolean concluidas) {
		this.concluidas = concluidas;
	}

	public int getTotalVagas() {
		return totalVagas;
	}

	public void setTotalVagas(int totalVagas) {
		this.totalVagas = totalVagas;
	}

	public List<ResponsavelVaga> getRv() {
		return rv;
	}

	public void setRv(List<ResponsavelVaga> rv) {
		this.rv = rv;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public void pesquisaRelatorio() {
		try {
			RelatorioVagasDAO dao = new RelatorioVagasDAO();
			List<intra_candidato_vaga> l = new ArrayList<>();
			if (this.responsavelVaga != null && this.responsavelVaga.equals("Todos")) {
				l = dao.pesquisaRelatorio(this.dataInicio, this.dataFim);
			} else {
				l = dao.pesquisaRelatorioResponsavel(this.dataInicio, this.dataFim, this.responsavelVaga);
			}
			if (l != null && l.size() > 0) {
				this.listaVagas = l;
				this.pizza = new PieChartModel();
				this.pizza.setTitle(sf.format(this.dataInicio) + " à " + sf.format(this.dataFim));
				this.pizza.setLegendPosition("w");
				List<intra_candidato_vaga> pendentes = new ArrayList<>(), emAndamento = new ArrayList<>(),
						standBy = new ArrayList<>(), canceladas = new ArrayList<>(), concluidas = new ArrayList<>();
				for (intra_candidato_vaga aux : l) {
					if (aux.getSituacao() == 0) {
						pendentes.add(aux);
					}
					if (aux.getSituacao() == 1) {
						emAndamento.add(aux);
					}
					if (aux.getSituacao() == 2) {
						standBy.add(aux);
					}
					if (aux.getSituacao() == 3) {
						canceladas.add(aux);
					}
					if (aux.getSituacao() == 4) {
						concluidas.add(aux);
					}
				}
				List<intra_candidato_vaga> lTotal = new ArrayList<>();
				StringBuffer bf = new StringBuffer();
				if (this.pendentes) {
					this.pizza.set("Pendentes", pendentes.size());
					lTotal.addAll(pendentes);
					bf.append("CCCCCC");
				}
				if (this.emAndamento) {
					if (bf.toString() != null && !bf.toString().trim().isEmpty()) {
						bf.append(",");
					}
					this.pizza.set("Em Andamento", emAndamento.size());
					lTotal.addAll(emAndamento);
					bf.append("CCFFCC");
				}
				if (this.standBy) {
					if (bf.toString() != null && !bf.toString().trim().isEmpty()) {
						bf.append(",");
					}
					this.pizza.set("Stand By", standBy.size());
					lTotal.addAll(standBy);
					bf.append("FFC000");
				}
				if (this.canceladas) {
					if (bf.toString() != null && !bf.toString().trim().isEmpty()) {
						bf.append(",");
					}
					this.pizza.set("Canceladas", canceladas.size());
					lTotal.addAll(canceladas);
					bf.append("FFFF99");
				}
				if (this.concluidas) {
					if (bf.toString() != null && !bf.toString().trim().isEmpty()) {
						bf.append(",");
					}
					this.pizza.set("Concluídas", concluidas.size());
					lTotal.addAll(concluidas);
					bf.append("99CCFF");
				}
				this.pizza.setSeriesColors(bf.toString());
				this.populaRV(lTotal);
			} else {
				this.pizza = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectVagas(ItemSelectEvent event) {
		try {
			this.listaVagasView = new ArrayList<>();
			String status = "";
			Map<String, Number> series = this.pizza.getData();
			int i = 0;
			for (Entry<String, Number> entry : series.entrySet()) {
				if (i == event.getItemIndex()) {
					status = entry.getKey();
				}
				i++;
			}
			for (intra_candidato_vaga aux : this.listaVagas) {
				if (this.situacaoEmTexto(aux.getSituacao()).equals(status)) {
					this.listaVagasView.add(aux);
				}
			}
			this.situacao = status;
			RequestContext.getCurrentInstance().execute("PF('dlgVagas').show();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String situacaoEmTexto(int situacao) {
		switch (situacao) {
		case 0:
			return "Pendentes";
		case 1:
			return "Em Andamento";
		case 2:
			return "Stand By";
		case 3:
			return "Canceladas";
		case 4:
			return "Concluídas";
		default:
			return "";
		}
	}

	public void populaRV(List<intra_candidato_vaga> lTotal) {
		this.totalVagas = lTotal.size();
		this.rv = new ArrayList<>();
		ResponsavelVaga v = null;
		for (String responsavel : this.listaUsuarioRH) {
			v = new ResponsavelVaga();
			for (intra_candidato_vaga aux : lTotal) {
				if (aux.getResponsavelVaga().equals(responsavel)) {
					v.setQtd(v.getQtd() + 1);
				}
			}
			v.setQtd(((v.getQtd() * 100) / this.totalVagas));
			v.setNome(responsavel);
			if (v.getQtd() > 0) {
				this.rv.add(v);
			}
		}

	}

}
