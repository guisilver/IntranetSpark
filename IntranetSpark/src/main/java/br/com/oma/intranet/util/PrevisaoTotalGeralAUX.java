package br.com.oma.intranet.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;

import br.com.oma.intranet.entidades.intra_previsao_orcamentaria;

public class PrevisaoTotalGeralAUX {
	
	DecimalFormat df = new DecimalFormat("#,###,##0.00",
			new DecimalFormatSymbols(new Locale("pt", "BR")));

	public double retornaTotalGeral(List<intra_previsao_orcamentaria> lista, int i){
		double valor = 0;
		for (intra_previsao_orcamentaria p : lista) {
			if (p.getCodigoDespesa().equals("01")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("02")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("03")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("04")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("05")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("06")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("07")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("08")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("09")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("010")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("011")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("012")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("013")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("014")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("015")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("016")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("017")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("018")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("019")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("020")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("021")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("022")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("023")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("024")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("025")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("026")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("027")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("028")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("029")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("030")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("031")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("032")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("033")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("034")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("035")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("036")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("037")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("038")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("039")) {valor += this.retornaMes(p, i);}
			
			if (p.getCodigoDespesa().equals("11")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("12")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("13")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("14")) {valor += this.retornaMes(p, i);}
			
			if (p.getCodigoDespesa().equals("21")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("22")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("23")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("24")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("25")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("26")) {valor += this.retornaMes(p, i);}
			
			if (p.getCodigoDespesa().equals("31")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("32")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("33")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("34")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("35")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("36")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("37")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("38")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("39")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("310")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("311")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("312")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("313")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("314")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("315")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("316")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("317")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("318")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("319")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("320")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("321")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("322")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("323")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("324")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("325")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("326")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("327")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("328")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("329")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("330")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("331")) {valor += this.retornaMes(p, i);}
			
			if (p.getCodigoDespesa().equals("41")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("42")) {valor += this.retornaMes(p, i);}
			
			if (p.getCodigoDespesa().equals("51")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("52")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("53")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("54")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("55")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("56")) {valor += this.retornaMes(p, i);}
			
			if (p.getCodigoDespesa().equals("61")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("62")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("63")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("64")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("65")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("66")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("67")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("68")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("69")) {valor += this.retornaMes(p, i);}
			
			if (p.getCodigoDespesa().equals("71")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("72")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("73")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("74")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("75")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("76")) {valor += this.retornaMes(p, i);}
			
			if (p.getCodigoDespesa().equals("81")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("82")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("83")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("84")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("85")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("86")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("87")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("88")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("89")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("810")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("811")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("812")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("813")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("814")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("815")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("816")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("817")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("818")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("819")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("820")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("821")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("822")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("823")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("824")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("825")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("826")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("827")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("828")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("829")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("830")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("831")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("832")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("833")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("834")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("835")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("836")) {valor += this.retornaMes(p, i);}
			if (p.getCodigoDespesa().equals("837")) {valor += this.retornaMes(p, i);}
		}
		return valor;
	}
	
	public double retornaTotalGeralMedia(List<intra_previsao_orcamentaria> lista){
		double valor = 0;
		for (intra_previsao_orcamentaria p : lista) {
			if (p.getCodigoDespesa().equals("01")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("02")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("03")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("04")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("05")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("06")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("07")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("08")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("09")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("010")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("011")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("012")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("013")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("014")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("015")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("016")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("017")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("018")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("019")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("020")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("021")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("022")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("023")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("024")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("025")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("026")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("027")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("028")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("029")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("030")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("031")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("032")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("033")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("034")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("035")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("036")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("037")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("038")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("039")) {valor += p.getMedia();}
			
			if (p.getCodigoDespesa().equals("11")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("12")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("13")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("14")) {valor += p.getMedia();}
			
			if (p.getCodigoDespesa().equals("21")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("22")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("23")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("24")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("25")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("26")) {valor += p.getMedia();}
			
			if (p.getCodigoDespesa().equals("31")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("32")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("33")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("34")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("35")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("36")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("37")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("38")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("39")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("310")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("311")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("312")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("313")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("314")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("315")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("316")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("317")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("318")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("319")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("320")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("321")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("322")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("323")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("324")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("325")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("326")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("327")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("328")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("329")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("330")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("331")) {valor += p.getMedia();}
			
			if (p.getCodigoDespesa().equals("41")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("42")) {valor += p.getMedia();}
			
			if (p.getCodigoDespesa().equals("51")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("52")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("53")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("54")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("55")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("56")) {valor += p.getMedia();}
			
			if (p.getCodigoDespesa().equals("61")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("62")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("63")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("64")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("65")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("66")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("67")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("68")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("69")) {valor += p.getMedia();}
			
			if (p.getCodigoDespesa().equals("71")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("72")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("73")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("74")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("75")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("76")) {valor += p.getMedia();}
			
			if (p.getCodigoDespesa().equals("81")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("82")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("83")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("84")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("85")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("86")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("87")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("88")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("89")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("810")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("811")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("812")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("813")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("814")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("815")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("816")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("817")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("818")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("819")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("820")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("821")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("822")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("823")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("824")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("825")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("826")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("827")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("828")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("829")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("830")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("831")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("832")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("833")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("834")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("835")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("836")) {valor += p.getMedia();}
			if (p.getCodigoDespesa().equals("837")) {valor += p.getMedia();}
		}
		return valor;
	}
	
	public double retornaMes(intra_previsao_orcamentaria ipo, int mesProjecao){
		DateTime dt = new DateTime(ipo.getMesProjecao());
		double valor = 0;
		switch (dt.plusMonths(mesProjecao).getMonthOfYear()) {
		case 1:valor = ipo.getMesJaneiro();	break;
		case 2:valor = ipo.getMesFevereiro();break;
		case 3:valor = ipo.getMesMarco();break;
		case 4:valor = ipo.getMesAbril();break;
		case 5:valor = ipo.getMesMaio();break;
		case 6:valor = ipo.getMesJunho();break;
		case 7:valor = ipo.getMesJulho();break;
		case 8:valor = ipo.getMesAgosto();break;
		case 9:valor = ipo.getMesSetembro();break;
		case 10:valor = ipo.getMesOutubro();break;
		case 11:valor = ipo.getMesNovembro();break;
		case 12:valor = ipo.getMesDezembro();break;
		}
		return valor;
	}
}
