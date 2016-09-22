package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.omnifaces.util.Ajax;
import org.primefaces.context.RequestContext;

import br.com.oma.intranet.dao.VagasDAO;
import br.com.oma.intranet.entidades.ResponsavelVaga;
import br.com.oma.intranet.entidades.intra_candidato;
import br.com.oma.intranet.entidades.intra_candidato_vaga;
import br.com.oma.intranet.entidades.intra_candidato_vaga_historico;
import br.com.oma.intranet.entidades.intra_condominios;

@ViewScoped
@ManagedBean
public class VagasMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4841269318917403990L;

	@ManagedProperty(value = "#{SessaoMB}")
	private SessaoMB sessaoMB;

	private intra_candidato_vaga vaga = new intra_candidato_vaga();
	private intra_candidato_vaga vagaSelecionada;
	private intra_candidato_vaga_historico historico = new intra_candidato_vaga_historico();
	private List<intra_candidato_vaga> listaVagas;
	private List<intra_candidato> listaCandidatos;
	private List<String> listaCargos;
	private List<intra_condominios> listaCondominios;
	private List<String> listaUsuarioRH;
	private List<ResponsavelVaga> rv;
	private String[] situacaoFiltro;
	private int totalVagas;
	private Date dataInicio, dataFim;
	private int codigoCandidato;
	private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

	@PostConstruct
	public void init() {
		if (this.vaga != null) {
			this.vaga.setResponsavelVaga("Todos");
		}
		this.dataInicio = new DateTime().withDayOfYear(1).toDate();
		this.dataFim = new DateTime().toDate();

		this.situacaoFiltro = new String[2];
		this.situacaoFiltro[0] = "0";
		this.situacaoFiltro[1] = "1";
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public intra_candidato_vaga getVaga() {
		return vaga;
	}

	public void setVaga(intra_candidato_vaga vaga) {
		this.vaga = vaga;
	}

	public intra_candidato_vaga getVagaSelecionada() {
		return vagaSelecionada;
	}

	public void setVagaSelecionada(intra_candidato_vaga vagaSelecionada) {
		this.vagaSelecionada = vagaSelecionada;
	}

	public intra_candidato_vaga_historico getHistorico() {
		return historico;
	}

	public void setHistorico(intra_candidato_vaga_historico historico) {
		this.historico = historico;
	}

	public List<intra_candidato_vaga> getListaVagas() {
		if (this.listaVagas == null) {
			try {
				VagasDAO dao = new VagasDAO();
				this.listaVagas = dao.getVagas(this.situacaoFiltro, this.vaga, this.dataInicio, this.dataFim);
				if (this.listaVagas != null) {
					for (intra_candidato_vaga aux : this.listaVagas) {
						boolean possui = dao.possuiCandidato(aux.getCodigo());
						aux.setPossuiCdt(possui);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaVagas;
	}

	public void setListaVagas(List<intra_candidato_vaga> listaVagas) {
		this.listaVagas = listaVagas;
	}

	public List<intra_candidato> getListaCandidatos() {
		return listaCandidatos;
	}

	public void setListaCandidatos(List<intra_candidato> listaCandidatos) {
		this.listaCandidatos = listaCandidatos;
	}

	public List<String> getListaCargos() {
		if (this.listaCargos == null) {
			this.listaCargos = new ArrayList<>();
			this.listaCargos.add("ADMINISTRATIVO CONDOMÍNIO");
			this.listaCargos.add("ARRUMADEIRA");
			this.listaCargos.add("AUXILIAR DE LIMPEZA");
			this.listaCargos.add("GERENTE PREDIAL");
			this.listaCargos.add("ZELADOR(A)");
			this.listaCargos.add("FAXINEIRO(A)");
			this.listaCargos.add("FOLGUISTA");
			this.listaCargos.add("JARDINEIRO(A)");
			this.listaCargos.add("MANOBRISTA");
			this.listaCargos.add("MANUTENÇÃO");
			this.listaCargos.add("PORTEIRO(A)");
			this.listaCargos.add("RECEPCIONISTA");
			this.listaCargos.add("SERVIÇOS GERAIS");
		}
		return listaCargos;
	}

	public void setListaCargos(List<String> listaCargos) {
		this.listaCargos = listaCargos;
	}

	public List<intra_condominios> getListaCondominios() {
		if (this.listaCondominios == null) {
			try {
				VagasDAO dao = new VagasDAO();
				this.listaCondominios = dao.getListaTodosCondominios();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaCondominios;
	}

	public void setListaCondominios(List<intra_condominios> listaCondominios) {
		this.listaCondominios = listaCondominios;
	}

	public List<String> getListaUsuarioRH() {
		if (this.listaUsuarioRH == null) {
			VagasDAO dao = new VagasDAO();
			this.listaUsuarioRH = dao.getListaUsuarioRH();
			this.listaUsuarioRH.remove("Erika Cristina");
			this.listaUsuarioRH.remove("Pabx");
			this.listaUsuarioRH.remove("Recepção");
			this.listaUsuarioRH.remove("Gisely Gomes");
			if (this.listaUsuarioRH.contains(this.sessaoMB.getUsuario().getNome())) {
				this.vaga.setResponsavelVaga(this.sessaoMB.getUsuario().getNome());
			}
			this.getRv();
		}
		return listaUsuarioRH;
	}

	public void setListaUsuarioRH(List<String> listaUsuarioRH) {
		this.listaUsuarioRH = listaUsuarioRH;
	}

	public List<ResponsavelVaga> getRv() {
		if (this.listaUsuarioRH == null) {
			this.getListaUsuarioRH();
		}
		this.rv = new ArrayList<>();
		VagasDAO dao = new VagasDAO();
		ResponsavelVaga v = null;
		for (String aux : this.listaUsuarioRH) {
			v = new ResponsavelVaga();
			int i = dao.pesquisaVagasPorResponsavel(this.dataInicio, this.dataFim, aux, this.situacaoFiltro);
			v.setNome(aux);
			v.setQtd(i);
			if (i > 0) {
				this.rv.add(v);
			}
		}
		return rv;
	}

	public void setRv(List<ResponsavelVaga> rv) {
		this.rv = rv;
	}

	public String[] getSituacaoFiltro() {
		return situacaoFiltro;
	}

	public void setSituacaoFiltro(String[] situacaoFiltro) {
		if (situacaoFiltro != null) {
			List<String> listaSit = Arrays.asList(situacaoFiltro);
			List<String> l = new ArrayList<>();
			l.addAll(listaSit);
			if (l.size() == 5 && !l.contains("5")) {
				this.situacaoFiltro = new String[6];
				this.situacaoFiltro[0] = "0";
				this.situacaoFiltro[1] = "1";
				this.situacaoFiltro[2] = "2";
				this.situacaoFiltro[3] = "3";
				this.situacaoFiltro[4] = "4";
				this.situacaoFiltro[5] = "5";
				situacaoFiltro = this.situacaoFiltro;
			}
			if (l.size() <= 5 && l.contains("5")) {
				l.remove(l.indexOf("5"));
				this.situacaoFiltro = l.toArray(new String[0]);
				situacaoFiltro = this.situacaoFiltro;
			}
		}

		this.situacaoFiltro = situacaoFiltro;
	}

	public void selectTodos() {
		this.situacaoFiltro = new String[6];
		this.situacaoFiltro[0] = "0";
		this.situacaoFiltro[1] = "1";
		this.situacaoFiltro[2] = "2";
		this.situacaoFiltro[3] = "3";
		this.situacaoFiltro[4] = "4";
		this.situacaoFiltro[5] = "5";
	}

	public void deselectTodos() {
		this.situacaoFiltro = null;
	}

	public int getTotalVagas() {
		VagasDAO dao = new VagasDAO();
		this.totalVagas = dao.getTotalVagas(this.dataInicio, this.dataFim, this.situacaoFiltro);
		return totalVagas;
	}

	public void setTotalVagas(int totalVagas) {
		this.totalVagas = totalVagas;
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

	public int getCodigoCandidato() {
		return codigoCandidato;
	}

	public void setCodigoCandidato(int codigoCandidato) {
		this.codigoCandidato = codigoCandidato;
	}

	public void salvarNovaVaga() {
		try {
			VagasDAO dao = new VagasDAO();
			dao.salvarNovaVaga(this.vaga);
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(caminho + "/rh/vagas.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao salvar! " + e.getMessage(), ""));
		}
	}

	public void salvarAlteracoesVaga() {
		try {
			VagasDAO dao = new VagasDAO();
			dao.salvarAlteracoesVaga(this.vagaSelecionada);
			this.listaVagas = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Salvo com sucesso!", ""));
			RequestContext.getCurrentInstance().execute("PF('dlgAltrVaga').hide();");
			Ajax.update("msgs");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao salvar! " + e.getMessage(), ""));
		}
	}

	public void excluirVaga() {
		try {
			VagasDAO dao = new VagasDAO();
			dao.excluirVaga(this.vagaSelecionada);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao excluir! " + e.getMessage(), ""));
		}
	}

	public void excluirVagaTbl(intra_candidato_vaga vagaSelecionada) {
		try {
			VagasDAO dao = new VagasDAO();
			dao.excluirVaga(vagaSelecionada);
			this.limparAposExclusao();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao excluir! " + e.getMessage(), ""));
		}
	}

	public void abrirAlterarVaga(int codigoVaga) {
		try {
			VagasDAO dao = new VagasDAO();
			this.listaCondominios = dao.getListaTodosCondominios();
			this.vagaSelecionada = dao.pesquisaVagaPorCodigo(Integer.valueOf(codigoVaga));
			this.listaCandidatos = dao.pesquisarCandidatos(this.vagaSelecionada.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limparAposExclusao() {
		this.listaVagas = null;
		this.vagaSelecionada = null;
	}

	public String situacaoEmTexto(int situacao) {
		switch (situacao) {
		case 0:
			return "Pendente";
		case 1:
			return "Em Andamento";
		case 2:
			return "Stand By";
		case 3:
			return "Cancelada";
		case 4:
			return "Concluída";
		default:
			return "";
		}
	}

	public int verificaEntrevistados(intra_candidato_vaga vaga) {
		int qtd = 0;
		for (intra_candidato aux : vaga.getCandidatos()) {
			if (aux.isEntrevistado()) {
				qtd++;
			}
		}
		return qtd;
	}

	public void pesquisaVagas() {
		try {
			VagasDAO dao = new VagasDAO();
			this.listaVagas = dao.getVagas(this.situacaoFiltro, this.vaga, this.dataInicio, this.dataFim);
			if (this.listaVagas != null) {
				for (intra_candidato_vaga aux : this.listaVagas) {
					boolean possui = dao.possuiCandidato(aux.getCodigo());
					aux.setPossuiCdt(possui);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verificaSituacao() {
		if (this.vagaSelecionada.getSituacao() == 4) {
			this.vagaSelecionada.setDataEncerramento(new Date());
		} else {
			this.vagaSelecionada.setDataEncerramento(null);
		}
	}

	public void pesquisarCandidatos(intra_candidato_vaga vaga) {
		if (vaga != null) {
			VagasDAO dao = new VagasDAO();
			this.listaCandidatos = dao.pesquisarCandidatos(vaga.getCodigo());
		}
	}

	public void salvarHistorico() {
		try {
			this.historico.setData(new DateTime(this.historico.getData())
					.withMillisOfDay(new DateTime(new Date()).getMillisOfDay()).toDate());
			List<intra_candidato_vaga_historico> l = new ArrayList<>();
			l.addAll(this.vagaSelecionada.getHistorico());
			this.vagaSelecionada.setHistorico(new ArrayList<intra_candidato_vaga_historico>());
			this.vagaSelecionada.getHistorico().addAll(l);
			this.vagaSelecionada.getHistorico().add(this.historico);
			VagasDAO dao = new VagasDAO();
			dao.salvarAlteracoesVaga(this.vagaSelecionada);
			Collections.sort(this.vagaSelecionada.getHistorico(), Collections.reverseOrder());
			this.listaVagas = null;
			this.historico = new intra_candidato_vaga_historico();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirHistorico(intra_candidato_vaga_historico h) {
		try {
			this.vagaSelecionada.getHistorico().remove(h);
			VagasDAO dao = new VagasDAO();
			dao.salvarAlteracoesVaga(this.vagaSelecionada);
			this.listaVagas = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvarAlteracoesHistorico() {
		try {
			VagasDAO dao = new VagasDAO();
			Collections.sort(this.vagaSelecionada.getHistorico(), Collections.reverseOrder());
			dao.salvarAlteracoesVaga(this.vagaSelecionada);
			this.listaVagas = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String retornaUltimoHist(intra_candidato_vaga vaga) {
		if (vaga.getHistorico() != null && vaga.getHistorico().size() > 0) {
			intra_candidato_vaga_historico h = vaga.getHistorico().get(0);
			return this.sf.format(h.getData())
					+ (h.getTexto() != null && !h.getTexto().trim().isEmpty() ? " - " + h.getTexto() : "");
		} else {
			return "";
		}
	}

	public void pesquisaHistCelula(String codigo) {
		try {
			this.vagaSelecionada = new VagasDAO().pesquisaVagaPorCodigo(Integer.parseInt(codigo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void abrirCandidatoSelecionado() {
		try {
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					caminho + "/rh/candidatos/alterar-candidato.xhtml?codigoCandidato=" + this.codigoCandidato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean getRenderizaEnc() {
		if (this.situacaoFiltro != null) {
			List<String> l = Arrays.asList(this.situacaoFiltro);
			if (l.contains("2") || l.contains("3") || l.contains("4")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String recuperaNomeCnd(int codigo) {
		String nomeCondominio = "";
		if (codigo > 0) {
			for (intra_condominios aux : this.listaCondominios) {
				if (aux.getCodigo() == codigo) {
					nomeCondominio = aux.getNome();
				}
			}
		}
		return nomeCondominio;
	}
	
	public String recuperaNomeGerente(int codigo){
		String nomeGerente = "";
		if (codigo > 0) {
			for (intra_condominios aux : this.listaCondominios) {
				if (aux.getCodigo() == codigo) {
					nomeGerente = aux.getNomeGerente();
				}
			}
		}
		return nomeGerente;
	}
}
