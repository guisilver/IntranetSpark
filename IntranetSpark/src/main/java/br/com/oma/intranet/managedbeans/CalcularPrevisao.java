package br.com.oma.intranet.managedbeans;

import java.text.DecimalFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Months;

import br.com.oma.intranet.dao.ProjetarPrevisaoDAO;
import br.com.oma.intranet.entidades.intra_previsao_orcamentaria;

public class CalcularPrevisao {

	// OBJETOS
	private ProjetarPrevisaoDAO ppDAO;
	private intra_previsao_orcamentaria ipo = new intra_previsao_orcamentaria();
	intra_previsao_orcamentaria po = new intra_previsao_orcamentaria();
	
	// ATRIBUTOS
	DecimalFormat formato = new DecimalFormat("#.##");
	double mediaGeral;

	public intra_previsao_orcamentaria getIpo() {
		return ipo;
	}

	public void setIpo(intra_previsao_orcamentaria ipo) {
		this.ipo = ipo;
	}
	public intra_previsao_orcamentaria getPo() {
		return po;
	}
	
	public void setPo(intra_previsao_orcamentaria po) {
		this.po = po;
	}
	

	// METODOS
		//CAPAS FUNCIONARIOS E TERCEIRIZAÇÃO
		public intra_previsao_orcamentaria calcSavePrevisao(int condominio, int codigoGerente, String capa, Date mesProjecao, boolean conta, double valorConta, String nomeConta, int codigoConta, int mesReajuste, double indice, String codigoDespesa, SessaoMB sessao) {
			this.ppDAO = new ProjetarPrevisaoDAO();
			for (int i = 1; i < 13; i++) {
				if (conta & i == 1) {this.ipo.setMesJaneiro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 2) {this.ipo.setMesFevereiro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 3) {this.ipo.setMesMarco(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 4) {this.ipo.setMesAbril(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 5) {this.ipo.setMesMaio(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 6) {this.ipo.setMesJunho(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 7) {this.ipo.setMesJulho(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 8) {this.ipo.setMesAgosto(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 9) {this.ipo.setMesSetembro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 10) {this.ipo.setMesOutubro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 11) {this.ipo.setMesNovembro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 12) {this.ipo.setMesDezembro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
			} 
			
			if (mesReajuste > 0) {
				DateTime mesX = new DateTime();
				DateTime mesFinal = new DateTime(mesProjecao).plusMonths(11);

				if (mesReajuste < new DateTime(mesProjecao).getMonthOfYear()) {
					mesX = new DateTime().withMonthOfYear(mesReajuste).withYear(new DateTime(mesProjecao).getYear()).plusYears(1);
				} else {
					mesX = new DateTime().withMonthOfYear(mesReajuste).withYear(new DateTime(mesProjecao).getYear());
				}

				int mesesDiferenca = Months.monthsBetween(mesX, mesFinal).getMonths();

				for (int i = 0; i <= mesesDiferenca+1; i++) {
					if (mesX.getMonthOfYear() == 1) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesJaneiro(numero);
					}
					if (mesX.getMonthOfYear() == 2) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesFevereiro(numero);
					}
					if (mesX.getMonthOfYear() == 3) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesMarco(numero);
					}
					if (mesX.getMonthOfYear() == 4) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesAbril(numero);
					}
					if (mesX.getMonthOfYear() == 5) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesMaio(numero);
					}
					if (mesX.getMonthOfYear() == 6) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesJunho(numero);
					}
					if (mesX.getMonthOfYear() == 7) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesJulho(numero);
					}
					if (mesX.getMonthOfYear() == 8) {double resultado = (((valorConta * indice) / 100) + valorConta);
					double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesAgosto(numero);
					}
					if (mesX.getMonthOfYear() == 9) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesSetembro(numero);
					}
					if (mesX.getMonthOfYear() == 10) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesOutubro(numero);
					}
					if (mesX.getMonthOfYear() == 11) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesNovembro(numero);
					}
					if (mesX.getMonthOfYear() == 12) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesDezembro(numero);
					}
					mesX = mesX.plusMonths(1);
				}
			}
			
			this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
									+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
									+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
			this.ipo.setMedia(this.mediaGeral);
			this.ipo.setCapa(capa);
			
			this.ipo.setCondominio(condominio);
			this.ipo.setCodigoGerente(codigoGerente);
			this.ipo.setMesProjecao(mesProjecao);
			
			this.ppDAO.salvarPrevisao(this.ipo, sessao);
			this.ipo = new intra_previsao_orcamentaria();

			return this.ipo;
		}
		
		//CAPAS FUNCIONARIOS E TERCEIRIZAÇÃO
		public intra_previsao_orcamentaria calcSavePrevisaoFunc(int condominio, int codigoGerente, String capa, Date mesProjecao, boolean conta, double valorConta, String nomeConta, int codigoConta, int mesReajuste, double indice, String codigoDespesa, boolean adicionarItem, SessaoMB sessao) {
			this.ppDAO = new ProjetarPrevisaoDAO();
			for (int i = 1; i < 13; i++) {
				if (conta & i == 1) {this.ipo.setMesJaneiro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 2) {this.ipo.setMesFevereiro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 3) {this.ipo.setMesMarco(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 4) {this.ipo.setMesAbril(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 5) {this.ipo.setMesMaio(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 6) {this.ipo.setMesJunho(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 7) {this.ipo.setMesJulho(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 8) {this.ipo.setMesAgosto(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 9) {this.ipo.setMesSetembro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 10) {this.ipo.setMesOutubro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 11) {this.ipo.setMesNovembro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 12) {this.ipo.setMesDezembro(valorConta);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
			} 
			
			if (mesReajuste > 0) {
				DateTime mesX = new DateTime();
				DateTime mesFinal = new DateTime(mesProjecao).plusMonths(11);

				if (mesReajuste < new DateTime(mesProjecao).getMonthOfYear()) {
					mesX = new DateTime().withMonthOfYear(mesReajuste).withYear(new DateTime(mesProjecao).getYear()).plusYears(1);
				} else {
					mesX = new DateTime().withMonthOfYear(mesReajuste).withYear(new DateTime(mesProjecao).getYear());
				}

				int mesesDiferenca = Months.monthsBetween(mesX, mesFinal).getMonths();

				for (int i = 0; i <= mesesDiferenca+1; i++) {
					if (mesX.getMonthOfYear() == 1) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesJaneiro(numero);
					}
					if (mesX.getMonthOfYear() == 2) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesFevereiro(numero);
					}
					if (mesX.getMonthOfYear() == 3) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesMarco(numero);
					}
					if (mesX.getMonthOfYear() == 4) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesAbril(numero);
					}
					if (mesX.getMonthOfYear() == 5) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesMaio(numero);
					}
					if (mesX.getMonthOfYear() == 6) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesJunho(numero);
					}
					if (mesX.getMonthOfYear() == 7) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesJulho(numero);
					}
					if (mesX.getMonthOfYear() == 8) {double resultado = (((valorConta * indice) / 100) + valorConta);
					double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesAgosto(numero);
					}
					if (mesX.getMonthOfYear() == 9) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesSetembro(numero);
					}
					if (mesX.getMonthOfYear() == 10) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesOutubro(numero);
					}
					if (mesX.getMonthOfYear() == 11) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesNovembro(numero);
					}
					if (mesX.getMonthOfYear() == 12) {double resultado = (((valorConta * indice) / 100) + valorConta);
						double numero = Double.valueOf(formato.format(resultado).replace(",", "."));this.ipo.setMesDezembro(numero);
					}
					mesX = mesX.plusMonths(1);
				}
			}
			
			if(adicionarItem){
				this.po = this.ppDAO.listarPrevisao(condominio, codigoDespesa);
				
				this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
						+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
						+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
				this.ipo.setMedia(this.mediaGeral);
				this.ipo.setCapa(capa);
				
				po.setMesJaneiro(po.getMesJaneiro()+ipo.getMesJaneiro());
				po.setMesFevereiro(po.getMesFevereiro()+ipo.getMesFevereiro());
				po.setMesMarco(po.getMesMarco()+ipo.getMesMarco());
				po.setMesAbril(po.getMesAbril()+ipo.getMesAbril());
				po.setMesMaio(po.getMesMaio()+ipo.getMesMaio());
				po.setMesJunho(po.getMesJunho()+ipo.getMesJunho());
				po.setMesJulho(po.getMesJulho()+ipo.getMesJulho());
				po.setMesAgosto(po.getMesAgosto()+ipo.getMesAgosto());
				po.setMesSetembro(po.getMesSetembro()+ipo.getMesSetembro());
				po.setMesOutubro(po.getMesOutubro()+ipo.getMesOutubro());
				po.setMesNovembro(po.getMesNovembro()+ipo.getMesNovembro());
				po.setMesDezembro(po.getMesDezembro()+ipo.getMesDezembro());
				po.setMedia(po.getMedia()+this.mediaGeral);

				if(this.po.getCondominio() > 0){
					this.ppDAO.salvarPrevisaoMerge(this.po, sessao);
					this.po = new intra_previsao_orcamentaria();
				}else{
					this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
							+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
							+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
							this.ipo.setMedia(this.mediaGeral);
							this.ipo.setCapa(capa);

							this.ipo.setCondominio(condominio);
							this.ipo.setCodigoGerente(codigoGerente);
							this.ipo.setMesProjecao(mesProjecao);

							this.ppDAO.salvarPrevisao(this.ipo, sessao);
							this.ipo = new intra_previsao_orcamentaria();
							return this.ipo;
				}
				return this.po;
			}else{
			this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
									+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
									+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
			this.ipo.setMedia(this.mediaGeral);
			this.ipo.setCapa(capa);
			
			this.ipo.setCondominio(condominio);
			this.ipo.setCodigoGerente(codigoGerente);
			this.ipo.setMesProjecao(mesProjecao);
			
			this.ppDAO.salvarPrevisao(this.ipo, sessao);
			this.ipo = new intra_previsao_orcamentaria();
			return this.ipo;
			}
		}
		
		//CAPA FERIAS
		public intra_previsao_orcamentaria calcSavePrevisao1(int condominio, int codigoGerente, String capa, Date mesProjecao, boolean conta, 
				double jan, double fev, double mar,double abr,double mai,double jun,double jul,double ago,double set, double out, double nov, double dez, String nomeConta, int codigoConta, String codigoDespesa, SessaoMB sessao) {
			for (int i = 1; i < 13; i++) {
				if (conta & i == 1) {this.ipo.setMesJaneiro(jan);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 2) {this.ipo.setMesFevereiro(fev);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 3) {this.ipo.setMesMarco(mar);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 4) {this.ipo.setMesAbril(abr);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 5) {this.ipo.setMesMaio(mai);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 6) {this.ipo.setMesJunho(jun);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 7) {this.ipo.setMesJulho(jul);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 8) {this.ipo.setMesAgosto(ago);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 9) {this.ipo.setMesSetembro(set);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 10) {this.ipo.setMesOutubro(out);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 11) {this.ipo.setMesNovembro(nov);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 12) {this.ipo.setMesDezembro(dez);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
			} 
			
			this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
									+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
									+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
			this.ipo.setMedia(this.mediaGeral);
			this.ipo.setCapa(capa);
			
			this.ppDAO = new ProjetarPrevisaoDAO();
			this.ipo.setCondominio(condominio);
			this.ipo.setCodigoGerente(codigoGerente);
			this.ipo.setMesProjecao(mesProjecao);
			
			this.ppDAO.salvarPrevisao(this.ipo, sessao);
			this.ipo = new intra_previsao_orcamentaria();

			return this.ipo;
		}
		
		//CAPA 13 FERIAS
		public intra_previsao_orcamentaria calcSavePrevisao2(int condominio, int codigoGerente, String capa, Date mesProjecao, boolean conta, double nov, double dez, String nomeConta, int codigoConta, String codigoDespesa, SessaoMB sessao) {
			for (int i = 1; i < 13; i++) {
				if (conta & i == 11) {this.ipo.setMesNovembro(nov);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 12) {this.ipo.setMesDezembro(dez);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
			} 
			
			this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
									+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
									+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
			this.ipo.setMedia(this.mediaGeral);
			this.ipo.setCapa(capa);
			
			this.ppDAO = new ProjetarPrevisaoDAO();
			this.ipo.setCondominio(condominio);
			this.ipo.setCodigoGerente(codigoGerente);
			this.ipo.setMesProjecao(mesProjecao);
			
			this.ppDAO.salvarPrevisao(this.ipo, sessao);
			this.ipo = new intra_previsao_orcamentaria();

			return this.ipo;
		}
		

		
		//CAPA outros
		public intra_previsao_orcamentaria calcSavePrevisao3(int condominio, int codigoGerente, String capa, Date mesProjecao, boolean conta, double inss, String nomeConta, int codigoConta, String codigoDespesa, SessaoMB sessao) {
			for (int i = 1; i < 13; i++) {
				if (conta & i == 1) {this.ipo.setMesJaneiro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 2) {this.ipo.setMesFevereiro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 3) {this.ipo.setMesMarco(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 4) {this.ipo.setMesAbril(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 5) {this.ipo.setMesMaio(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 6) {this.ipo.setMesJunho(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 7) {this.ipo.setMesJulho(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 8) {this.ipo.setMesAgosto(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 9) {this.ipo.setMesSetembro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 10) {this.ipo.setMesOutubro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 11) {this.ipo.setMesNovembro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				if (conta & i == 12) {this.ipo.setMesDezembro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
			} 
			
			this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
									+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
									+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
			this.ipo.setMedia(this.mediaGeral);
			this.ipo.setCapa(capa);
			
			this.ppDAO = new ProjetarPrevisaoDAO();
			this.ipo.setCondominio(condominio);
			this.ipo.setCodigoGerente(codigoGerente);
			this.ipo.setMesProjecao(mesProjecao);
			
			this.ppDAO.salvarPrevisao(this.ipo, sessao);
			this.ipo = new intra_previsao_orcamentaria();

			return this.ipo;
		}
		
		//CAPA CESTA DE NATAL
			public intra_previsao_orcamentaria calcSavePrevisao4(int condominio, int codigoGerente, String capa, Date mesProjecao, boolean conta, double inss, String nomeConta, int codigoConta, String codigoDespesa, SessaoMB sessao) {
				
				this.ipo.setMesDezembro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);
				
				this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
										+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
										+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
				this.ipo.setMedia(this.mediaGeral);
				this.ipo.setCapa(capa);
				
				this.ppDAO = new ProjetarPrevisaoDAO();
				this.ipo.setCondominio(condominio);
				this.ipo.setCodigoGerente(codigoGerente);
				this.ipo.setMesProjecao(mesProjecao);
				
				this.ppDAO.salvarPrevisao(this.ipo, sessao);
				this.ipo = new intra_previsao_orcamentaria();

				return this.ipo;
			}
		
			//CAPA INSS 13 FERIAS
			public intra_previsao_orcamentaria calcSavePrevisao5(int condominio, int codigoGerente, String capa, Date mesProjecao, boolean conta, double inss, String nomeConta, int codigoConta, String codigoDespesa, SessaoMB sessao) {
				for (int i = 1; i < 13; i++) {
					/*if (conta & i == 1) {this.ipo.setMesJaneiro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 2) {this.ipo.setMesFevereiro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 3) {this.ipo.setMesMarco(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 4) {this.ipo.setMesAbril(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 5) {this.ipo.setMesMaio(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 6) {this.ipo.setMesJunho(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 7) {this.ipo.setMesJulho(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 8) {this.ipo.setMesAgosto(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 9) {this.ipo.setMesSetembro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 10) {this.ipo.setMesOutubro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
					if (conta & i == 11) {this.ipo.setMesNovembro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}*/
					if (conta & i == 12) {this.ipo.setMesDezembro(inss);this.ipo.setDespesas(nomeConta);this.ipo.setConta(codigoConta);this.ipo.setCodigoDespesa(codigoDespesa);}
				} 
				
				this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
										+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
										+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
				this.ipo.setMedia(this.mediaGeral);
				this.ipo.setCapa(capa);
				
				this.ppDAO = new ProjetarPrevisaoDAO();
				this.ipo.setCondominio(condominio);
				this.ipo.setCodigoGerente(codigoGerente);
				this.ipo.setMesProjecao(mesProjecao);
				
				this.ppDAO.salvarPrevisao(this.ipo, sessao);
				this.ipo = new intra_previsao_orcamentaria();

				return this.ipo;
			}
			
			//CAPA Inadimplencia
			public intra_previsao_orcamentaria calcSavePrevisao6(int condominio, int codigoGerente, String capa, Date mesProjecao, boolean conta, double inss, String nomeConta, int codigoConta, String codigoDespesa, SessaoMB sessao) {
					
				this.ipo.setDespesas(nomeConta);
				this.ipo.setConta(codigoConta);
				this.ipo.setCodigoDespesa(codigoDespesa);
				this.ipo.setInadimplencia(inss);
				
				/*this.mediaGeral = Double.valueOf(formato.format((this.ipo.getMesJaneiro() + this.ipo.getMesFevereiro()+ this.ipo.getMesMarco()
										+ this.ipo.getMesAbril()+ this.ipo.getMesMaio() + this.ipo.getMesJunho()+ this.ipo.getMesJulho()+ this.ipo.getMesAgosto() 
										+ this.ipo.getMesSetembro() + this.ipo.getMesOutubro()+ this.ipo.getMesNovembro() + this.ipo.getMesDezembro()) / 12).replace(",", "."));
				this.ipo.setMedia(this.mediaGeral);*/
				this.ipo.setCapa(capa);
				
				this.ppDAO = new ProjetarPrevisaoDAO();
				this.ipo.setCondominio(condominio);
				this.ipo.setCodigoGerente(codigoGerente);
				this.ipo.setMesProjecao(mesProjecao);
				
				this.ppDAO.salvarPrevisao(this.ipo, sessao);
				this.ipo = new intra_previsao_orcamentaria();

				return this.ipo;
			}
}
