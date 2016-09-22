package br.com.oma.intranet.managedbeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;

import br.com.oma.intranet.dao.PagamentoEletronicoContasDAO;
import br.com.oma.intranet.entidades.intra_condominios;
import br.com.oma.omaonline.entidades.cndpagar;

@ViewScoped
@ManagedBean
public class PagamentoEletronicoContasMB {

	private Integer conta;
	private int fonte;
	private Date dataInicio, dataFim;
	private List<cndpagar> listaLancamentos;

	@PostConstruct
	public void init() {
		this.dataInicio = new DateTime().plusDays(1).withMillisOfDay(0).toDate();
		this.dataFim = new DateTime().plusDays(1).withMillisOfDay(0).toDate();
		this.carregarRelatorio();
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public int getFonte() {
		return fonte;
	}

	public void setFonte(int fonte) {
		this.fonte = fonte;
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

	public List<cndpagar> getListaLancamentos() {
		return listaLancamentos;
	}

	public void setListaLancamentos(List<cndpagar> listaLancamentos) {
		this.listaLancamentos = listaLancamentos;
	}

	public void carregarRelatorio() {
		try {
			PagamentoEletronicoContasDAO dao = new PagamentoEletronicoContasDAO();
			Integer contaPesquisa = this.conta;
			if (contaPesquisa == null) {
				contaPesquisa = 0;
			}
			this.listaLancamentos = dao.pesquisaLancamentosEnviar(this.dataInicio, this.dataFim, contaPesquisa);
			if (contaPesquisa > 0) {
			}
			if (this.listaLancamentos != null) {
				for (cndpagar aux : this.listaLancamentos) {
					// aux.setPossuiImg(this.possuiImg(dao,
					// aux.getNrolancto()));
					intra_condominios c = dao.getNomeCondominio(aux.getCondominio());
					if (c != null) {
						aux.setCndCodNome(aux.getCondominio() + " - " + c.getNome());
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean possuiImg(PagamentoEletronicoContasDAO dao, int nrolancto) {
		// return dao.possuiImg(nrolancto, "P");
		return false;
	}

}
