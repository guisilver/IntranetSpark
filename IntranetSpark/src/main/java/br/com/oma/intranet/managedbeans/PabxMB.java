package br.com.oma.intranet.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.oma.intranet.entidades.intra_agd_contatos;

@ViewScoped
@ManagedBean
public class PabxMB {

	private intra_agd_contatos contato = new intra_agd_contatos(), contatoSelecionado;
	private List<intra_agd_contatos> listaContatos = new ArrayList<>(), fltrContatos;

	public intra_agd_contatos getContato() {
		return contato;
	}

	public void setContato(intra_agd_contatos contato) {
		this.contato = contato;
	}

	public intra_agd_contatos getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(intra_agd_contatos contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}

	public List<intra_agd_contatos> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(List<intra_agd_contatos> listaContatos) {
		this.listaContatos = listaContatos;
	}

	public List<intra_agd_contatos> getFltrContatos() {
		return fltrContatos;
	}

	public void setFltrContatos(List<intra_agd_contatos> fltrContatos) {
		this.fltrContatos = fltrContatos;
	}

	public void salvarNovoContato() {

	}

	public void salvarAlteracoesContato() {

	}

	public void excluirContato() {

	}

}
