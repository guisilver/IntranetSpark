package br.com.oma.intranet.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;

import br.com.oma.intranet.entidades.TaxaInadimplencia;
import br.com.oma.intranet.util.JPAUtil;

public class PrevisaoInadimplenciaDAO extends LogGeralDAO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8642463065685698299L;
	private EntityManager manager;
	private Query query;

	private double taxaValor;
	TaxaInadimplencia taxa = null;
	private List<TaxaInadimplencia> taxaTotal;
	private List<TaxaInadimplencia> taxaPagos;

	public PrevisaoInadimplenciaDAO() {
		this.manager = JPAUtil.getManager();
	}
	
	public double getTaxaValor() {
		return taxaValor;
	}

	public void setTaxaValor(double taxaValor) {
		this.taxaValor = taxaValor;
	}

	public List<TaxaInadimplencia> getTaxaTotal() {
		return taxaTotal;
	}

	public void setTaxaTotal(List<TaxaInadimplencia> taxaTotal) {
		this.taxaTotal = taxaTotal;
	}

	public List<TaxaInadimplencia> getTaxaPagos() {
		return taxaPagos;
	}

	public void setTaxaPagos(List<TaxaInadimplencia> taxaPagos) {
		this.taxaPagos = taxaPagos;
	}

	@SuppressWarnings("unchecked")
	public double pesquisaTaxaTotal(int condominio, Date dataInicio, Date dataFim) {
		
		this.taxaPagos = new ArrayList<TaxaInadimplencia>();
		this.taxaTotal = new ArrayList<TaxaInadimplencia>();
		
		int mesI = new DateTime(dataInicio).getMonthOfYear();
		int anoI = new DateTime(dataInicio).getYear();

		int mesF = new DateTime(dataFim).getMonthOfYear();
		int anoF = new DateTime(dataFim).getYear();

			this.query = this.manager.createNativeQuery("exec sp_pesquisa_periodo_total @condominio = :p1, @mesI = :p2, @anoI = :p3, @mesF = :p4, @anoF = :p5");
			
			this.query.setParameter("p1", condominio);
			this.query.setParameter("p2", mesI);
			this.query.setParameter("p3", anoI);
			this.query.setParameter("p4", mesF);
			this.query.setParameter("p5", anoF);
			
			List<Object[]> listaTotal = new ArrayList<Object[]>();
			listaTotal = this.query.getResultList();
			
			if(!listaTotal.isEmpty()){
				for (Object[] obj : listaTotal) {
					taxa = new TaxaInadimplencia();
					taxa.setCodigoCondominio(Integer.valueOf(obj[0].toString()));
					taxa.setRecibo(Double.valueOf(obj[1].toString()));
					taxa.setReciboValor(Double.valueOf(obj[2].toString()));
					taxa.setAnoVencimento(Integer.valueOf(obj[3].toString()));
					taxa.setMesVencimento(Integer.valueOf(obj[4].toString()));
					taxaTotal.add(taxa);
				}
			}

			this.query = this.manager.createNativeQuery("exec sp_pesquisa_periodo_pagos @condominio = :p1, @mesI = :p2, @anoI = :p3, @mesF = :p4, @anoF = :p5");
			
			this.query.setParameter("p1", condominio);
			this.query.setParameter("p2", mesI);
			this.query.setParameter("p3", anoI);
			this.query.setParameter("p4", mesF);
			this.query.setParameter("p5", anoF);
			
			List<Object[]> listaPagos = new ArrayList<Object[]>();
			listaPagos = this.query.getResultList();

			if(!listaPagos.isEmpty()){
				for (Object[] obj : listaPagos) {
					taxa = new TaxaInadimplencia();
					taxa.setCodigoCondominio(Integer.valueOf(obj[0].toString()));
					taxa.setRecibo(Double.valueOf(obj[1].toString()));
					taxa.setReciboValor(Double.valueOf(obj[2].toString()));
					taxa.setAnoVencimento(Integer.valueOf(obj[3].toString()));
					taxa.setMesVencimento(Integer.valueOf(obj[4].toString()));
					taxaPagos.add(taxa);
				}
			}
		return this.populaInadimplencia(taxaTotal, taxaPagos);
	}

	public double populaInadimplencia(List<TaxaInadimplencia> total, List<TaxaInadimplencia> pagos) {
		TaxaInadimplencia taxaFinal = null;
		List<TaxaInadimplencia> listaTaxas = new ArrayList<>();
		for (TaxaInadimplencia aux1 : total) {
			for (TaxaInadimplencia aux2 : pagos) {
				if (aux1.getMesVencimento() == aux2.getMesVencimento()
						&& aux1.getAnoVencimento() == aux2.getAnoVencimento()
						&& aux1.getCodigoCondominio() == aux2
								.getCodigoCondominio()) {
					taxaFinal = new TaxaInadimplencia();
					this.taxaValor += ((aux1.getReciboValor() - aux2.getReciboValor()) / aux1.getReciboValor()) * 100;
					taxaFinal.setPorcentagemValor(taxaValor);
					listaTaxas.add(taxaFinal);
				}
			}
		}
		return this.taxaValor;
	}


	
}
