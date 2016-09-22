package br.com.oma.intranet.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.oma.intranet.dao.PrevisaoDAO;
import br.com.oma.intranet.entidades.intra_previsao_orcamentaria;

public class PrevisaoAUX {

	private PrevisaoDAO previsaoDAO;
	private String nomeCapa;
	private double totalGeral;

	DecimalFormat df = new DecimalFormat("#,###,##0.00",
			new DecimalFormatSymbols(new Locale("pt", "BR")));

	private List<intra_previsao_orcamentaria> lista;

	public PrevisaoAUX() {
		lista = new ArrayList<>();
	}

	public PrevisaoAUX(String nomeCapa) {
		lista = new ArrayList<>();
		this.nomeCapa = nomeCapa;
	}

	public String getNomeCapa() {
		return nomeCapa;
	}

	public void setNomeCapa(String nomeCapa) {
		this.nomeCapa = nomeCapa;
	}

	public List<intra_previsao_orcamentaria> getLista() {
		return lista;
	}

	public void setLista(List<intra_previsao_orcamentaria> lista) {
		this.lista = lista;
	}

	public String getAllJaneiro() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesJaneiro();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllFevereiro() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesFevereiro();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllMarco() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesMarco();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllAbril() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesAbril();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllMaio() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesMaio();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllJunho() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesJunho();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllJulho() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesJulho();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllAgosto() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesAgosto();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllSetembro() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesSetembro();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllOutubro() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesOutubro();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllNovembro() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesNovembro();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllDezembro() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMesDezembro();
		}
		return String.valueOf(df.format(sum));
	}
	public String getAllMedia() {
		double sum = 0.0;
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMedia();
		}
		return String.valueOf(df.format(sum));
	}
	
	public String getAllPercentual(int condominio) {
		double sum = 0.0;
		this.previsaoDAO = new PrevisaoDAO();
		if(condominio > 0){
			this.totalGeral = this.previsaoDAO.totalGeralMedia(condominio);
		}
		for (intra_previsao_orcamentaria s : this.lista) {
			sum += s.getMedia();
		}
		if(this.totalGeral > 0){
			sum = ((sum/totalGeral) * 100);
		}
		return String.valueOf(df.format(sum));
	}
}
