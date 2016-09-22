package br.com.oma.intranet.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

import br.com.oma.intranet.dao.FinanceiroImgDAO;
import br.com.oma.intranet.entidades.Conta;
import br.com.oma.intranet.entidades.FinanceiroImg;
import br.com.oma.intranet.entidades.Lancamento;
import br.com.oma.intranet.entidades.TipoPagamento;
import br.com.oma.omaonline.entidades.cndpagar;

@ViewScoped
@ManagedBean
public class FinanceiroImgMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8669479436927931810L;
	private TreeNode root;
	private TreeNode selectedNode;
	private String idImg;
	private Integer nro_remessa;
	private StreamedContent content;
	private Date dataInicio, dataFim;
	private List<FinanceiroImg> listaEnviar, listaEnviados;

	private List<Conta> contas, fltrContas;
	private List<TipoPagamento> fltrTipoPagamento;
	private List<Lancamento> fltrLancamentos;

	private Integer conta;

	private int tipoPesquisa = 1;

	private int qtdTotal;
	private double valorTotal;

	private cndpagar lancamentoSelecionado;

	private int fonte;

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Conta> getFltrContas() {
		return fltrContas;
	}

	public void setFltrContas(List<Conta> fltrContas) {
		this.fltrContas = fltrContas;
	}

	public List<TipoPagamento> getFltrTipoPagamento() {
		return fltrTipoPagamento;
	}

	public void setFltrTipoPagamento(List<TipoPagamento> fltrTipoPagamento) {
		this.fltrTipoPagamento = fltrTipoPagamento;
	}

	public List<Lancamento> getFltrLancamentos() {
		return fltrLancamentos;
	}

	public void setFltrLancamentos(List<Lancamento> fltrLancamentos) {
		this.fltrLancamentos = fltrLancamentos;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public int getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(int tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public int getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(int qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public cndpagar getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(cndpagar lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	public int getFonte() {
		return fonte;
	}

	public void setFonte(int fonte) {
		this.fonte = fonte;
	}

	@PostConstruct
	public void init() {
		this.dataInicio = new DateTime().plusDays(1).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime().plusDays(1).withMillisOfDay(0).toDate();
		this.constroiContas();
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public String getIdImg() {
		return idImg;
	}

	public void setIdImg(String idImg) {
		this.idImg = idImg;
	}

	public Integer getNro_remessa() {
		return nro_remessa;
	}

	public void setNro_remessa(Integer nro_remessa) {
		this.nro_remessa = nro_remessa;
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
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

	public List<FinanceiroImg> getListaEnviar() {
		return listaEnviar;
	}

	public void setListaEnviar(List<FinanceiroImg> listaEnviar) {
		this.listaEnviar = listaEnviar;
	}

	public List<FinanceiroImg> getListaEnviados() {
		return listaEnviados;
	}

	public void setListaEnviados(List<FinanceiroImg> listaEnviados) {
		this.listaEnviados = listaEnviados;
	}

	public void carregarRelatorio() {
		try {
			this.limpar();
			TreeNode root = new DefaultTreeNode("Root", null);
			FinanceiroImgDAO dao = new FinanceiroImgDAO();
			switch (this.tipoPesquisa) {
			case 1:
				Integer contaPesquisa = this.conta;
				if (contaPesquisa == null) {
					contaPesquisa = 0;
				}
				if (contaPesquisa > 0) {
					this.listaEnviar = dao.pesquisaLancamentosEnviar(this.dataInicio, this.dataFim, contaPesquisa);
				}
				break;
			case 2:
				Integer nroRemessa = this.nro_remessa;
				if (nroRemessa == null) {
					nroRemessa = 0;
				}
				if (nroRemessa > 0) {
					this.listaEnviar = dao.pesquisaLancamentosEnviados(nroRemessa);
				}
				break;
			default:
				break;
			}

			if (this.listaEnviar != null) {
				for (FinanceiroImg aux : this.listaEnviar) {
					aux.setPossuiImg(this.possuiImg(dao, aux.getCndpagar().getNrolancto()));
				}
				Map<Short, Map<String, List<FinanceiroImg>>> l = this.populaContasBancarias(this.listaEnviar);
				Map<Short, Map<String, List<FinanceiroImg>>> treeMap = new TreeMap<Short, Map<String, List<FinanceiroImg>>>(
						l);
				for (Map.Entry<Short, Map<String, List<FinanceiroImg>>> entry : treeMap.entrySet()) {
					TreeNode conta = new DefaultTreeNode(entry.getKey(), root);
					for (Map.Entry<String, List<FinanceiroImg>> entry2 : entry.getValue().entrySet()) {
						TreeNode tipoPagto = new DefaultTreeNode(entry2.getKey(), conta);
						for (FinanceiroImg aux : entry2.getValue()) {
							new DefaultTreeNode(aux.getCndpagar().getNrolancto(), tipoPagto);
						}
					}
				}
			}
			this.root = root;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<Short, Map<String, List<FinanceiroImg>>> populaContasBancarias(List<FinanceiroImg> l) {
		Map<Short, Map<String, List<FinanceiroImg>>> map = new HashMap<>();
		for (FinanceiroImg aux : l) {
			if (!map.containsKey(aux.getCndpagar().getContaBancaria())) {
				map.put(aux.getCndpagar().getContaBancaria(),
						populaTipoPagamentos(l, aux.getCndpagar().getContaBancaria()));
			}
		}
		return map;
	}

	private Map<String, List<FinanceiroImg>> populaTipoPagamentos(List<FinanceiroImg> l, short contaBancaria) {
		Map<String, List<FinanceiroImg>> tipoPagto = new HashMap<>();
		for (FinanceiroImg aux : l) {
			if (aux.getCndpagar().getContaBancaria() == contaBancaria) {
				if (!tipoPagto.containsKey(aux.getCndpagar().getTipoPagto())) {
					tipoPagto.put(aux.getCndpagar().getTipoPagto(),
							populaLancamentos(l, contaBancaria, aux.getCndpagar().getTipoPagto()));
				}
			}
		}
		return tipoPagto;
	}

	public List<FinanceiroImg> populaLancamentos(List<FinanceiroImg> l, short contaBancaria, String tipoPagto) {
		List<FinanceiroImg> l2 = new ArrayList<>();
		for (FinanceiroImg aux : l) {
			if (aux.getCndpagar().getContaBancaria() == contaBancaria
					&& aux.getCndpagar().getTipoPagto().equals(tipoPagto)) {
				l2.add(aux);
			}
		}
		return l2;
	}

	public void onNodeSelect(NodeSelectEvent event) {
		this.idImg = event.getTreeNode().toString();
	}

	public boolean possuiImg(FinanceiroImgDAO dao, int nrolancto) {
		return dao.possuiImg(nrolancto, "P");
	}

	public void constroiContas() {
		Conta conta = null;
		this.limpar();
		FinanceiroImgDAO dao = new FinanceiroImgDAO();

		switch (this.tipoPesquisa) {
		case 1:
			Integer contaPesquisa = this.conta;
			if (contaPesquisa == null) {
				contaPesquisa = 0;
			}
			this.listaEnviar = dao.pesquisaLancamentosEnviar(this.dataInicio, this.dataFim, contaPesquisa);
			break;
		case 2:
			Integer nroRemessa = this.nro_remessa;
			if (nroRemessa == null) {
				nroRemessa = 0;
			}
			if (nroRemessa > 0) {
				this.listaEnviar = dao.pesquisaLancamentosEnviados(nroRemessa);
			}
			break;
		default:
			break;
		}

		if (this.listaEnviar != null) {
			for (FinanceiroImg aux : this.listaEnviar) {
				if (!this.verificaPossuiConta(this.contas, aux)) {
					conta = new Conta();
					conta.setId_contrato(aux.getId_contrato());
					conta.setCC(aux.getCC());
					conta.setCodAgencia(aux.getCodAgencia());
					conta.setNomeAgencia(aux.getNomeAgencia());
					conta.setNomeBanco(aux.getNomeBanco());
					conta.setContaBancaria(aux.getCndpagar().getContaBancaria());
					conta.setCodBanco(aux.getCodBanco());
					conta.setCondominio(aux.getCondominio());
					this.constroiTipoPagtos(conta, dao);
					this.contas.add(conta);
					this.qtdTotal = conta.getContador() + this.qtdTotal;
					this.valorTotal = conta.getValorTotal() + this.valorTotal;
				}
			}
		}
	}

	public void limpar() {
		this.contas = new ArrayList<>();
		this.listaEnviar = null;
		this.qtdTotal = 0;
		this.valorTotal = 0;
		this.conta = 0;
	}

	public boolean verificaPossuiConta(List<Conta> lista, FinanceiroImg conta) {
		boolean possui = false;
		for (Conta c : lista) {
			if (c.getContaBancaria() == conta.getCndpagar().getContaBancaria()) {
				possui = true;
			}
		}
		return possui;
	}

	public void constroiTipoPagtos(Conta conta, FinanceiroImgDAO dao) {
		TipoPagamento t = null;
		double valorTotal = 0.0;
		conta.setTipoPagamento(new ArrayList<TipoPagamento>());
		for (FinanceiroImg aux : this.listaEnviar) {
			if (aux.getCndpagar().getContaBancaria() == conta.getContaBancaria()) {
				if (!this.verificaPossuiTipoPagto(conta.getTipoPagamento(), aux)) {
					t = new TipoPagamento();
					t.setTipoPagamento(aux.getCndpagar().getTipoPagto());
					t.setLancamentos(new ArrayList<Lancamento>());
					t.setDescricao(this.descricaoTipoPagto(t.getTipoPagamento()));
					this.constroiLancamentos(conta, t, dao);
					valorTotal = valorTotal + t.getValorTotalTipo();
					conta.setContador(t.getContador() + conta.getContador());
					conta.getTipoPagamento().add(t);
				}
			}
		}
		conta.setValorTotal(valorTotal);
	}

	public boolean verificaPossuiTipoPagto(List<TipoPagamento> lista, FinanceiroImg tipoPagto) {
		boolean possui = false;
		for (TipoPagamento t : lista) {
			if (t.getTipoPagamento().equals(tipoPagto.getCndpagar().getTipoPagto())) {
				possui = true;
			}
		}
		return possui;
	}

	public void constroiLancamentos(Conta conta, TipoPagamento t, FinanceiroImgDAO dao) {
		Lancamento l = null;
		double valorTotalTipo = 0.0;
		for (FinanceiroImg aux : this.listaEnviar) {
			if (aux.getCndpagar().getContaBancaria() == conta.getContaBancaria()
					&& t.getTipoPagamento().equals(aux.getCndpagar().getTipoPagto())) {
				l = new Lancamento();
				l.setCndpagar(aux.getCndpagar());
				l.setPossuiImg(this.possuiImg(dao, l.getCndpagar().getNrolancto()));
				valorTotalTipo = valorTotalTipo + l.getCndpagar().getValor();
				t.setContador(t.getContador() + 1);
				if (aux.getCndpagar().getContaBancaria() == 1 && aux.getContaVinculada() != null
						&& aux.getContaVinculada().trim().equals("S")) {
					l.setContaVinculada(true);
					t.setContaVinculada(true);
					conta.setContaVinculada(true);
				}
				t.getLancamentos().add(l);
			}
		}
		t.setValorTotalTipo(valorTotalTipo);
	}

	public String descricaoTipoPagto(String tipo) {
		switch (tipo) {
		case "5":
			return "CRÉDITO EM CONTA CORRENTE";
		case "7":
			return "DOC";
		case "8":
			return "FICHA DE COMPENSAÇÃO";
		case "D":
			return "INSS / GPS";
		case "E":
			return "CONCESSIONÁRIAS";
		case "F":
			return "IR/PCC/PIS (DARFS)";
		case "H":
			return "DARFS COM CÓDIGO DE BARRAS";
		case "I":
			return "FGTS";
		case "J":
			return "IPTU OU DAMSP (TAXA, MULTAS, ETC / O QUE FOR PREFEITURA)";
		case "K":
			return "ISS COM CÓDIGO DE BARRAS (TAXA, MULTAS, ETC / O QUE FOR PREFEITURA)";
		default:
			return "";
		}
	}
}
