package br.com.oma.intranet.util;

import java.io.Serializable;

public class CodigoBarras implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * metodo utilizado para calcular a concessionaria 
	 * @param barra
	 * @return
	 */
	public String calculaConcessionaria(String barra) {
	    String linha = barra.replaceAll("[^0-9]", "");
	    if (linha.length() != 44) {
	        return null; // 'A linha do Codigo de Barras est� incompleta!'
	    }
	    String campo1 = linha.substring(0,11);
	    String campo2 = linha.substring(11,22);
	    String campo3 = linha.substring(22,33);
	    String campo4 = linha.substring(33,44); // Digito verificador
	    return   campo1 + modulo10(campo1)+campo2 + modulo10(campo2) +campo3 + modulo10(campo3)+campo4 + modulo10(campo4);
	}

	/**
	 * metodo utilizado para calcular bancos
	 * banco do brasil, bradesco, caixa e itau
	 * @param barra
	 * @return
	 */
	public String calculaLinhaBBBrCaIt(String barra) {
	    String linha = barra.replaceAll("[^0-9]", "");
	    if (linha.length() != 44) {
	        return null; // 'A linha do C�digo de Barras est� incompleta!'
	    }
	    String campo1 = linha.substring(0,4)+linha.substring(19,20)+'.'+linha.substring(20,24);
	    String campo2 = linha.substring(24,29)+'.'+linha.substring(29,34);
	    String campo3 = linha.substring(34,39)+'.'+linha.substring(39,44);
	    String campo4 = linha.substring(4,5); // Digito verificador
	    String campo5 = linha.substring(5,19); // Vencimento + Valor
	    
	    return   campo1 + modulo10(campo1)+campo2 + modulo10(campo2)+campo3 + modulo10(campo3)+campo4 +campo5;
	}
	/**
	 * metodo utilizado para calcular banco
	 * santander
	 * @param barra
	 * @return
	 */
	public String calculaSantender(String barra) {
	    String linha = barra.replaceAll("[^0-9]", "");

	    if (linha.length() != 44) {
	        return null; // 'A linha do C�digo de Barras est� incompleta!'
	    }
	    String campo1 = linha.substring(0,4)+linha.substring(19,20)+'.'+linha.substring(20,24);
	    String campo2 = linha.substring(24,29)+'.'+linha.substring(29,34);
	    String campo3 = linha.substring(34,39)+'.'+linha.substring(39,44);
	    String campo4 = linha.substring(4,5); // Digito verificador
	    String campo5 = linha.substring(5,19); // Vencimento + Valor
	    
	    return   campo1 + modulo10(campo1) +campo2 + modulo10(campo2) +campo3 + modulo10(campo3) +campo4 +campo5 ;
	}

	/**
	 * metodo utilizado para calcular o digito de cada sequencia de caracteres
	 * @param numero
	 * @return
	 */
	public int modulo10(String numero) {
	    numero = numero.replaceAll("[^0-9]","");
	    int soma  = 0;
	    int peso  = 2;
	    int contador = numero.length()-1;
	    while (contador >= 0) {
	        int multiplicacao = Integer.valueOf( numero.substring(contador,contador+1) ) * peso;
	        if (multiplicacao >= 10) {multiplicacao = 1 + (multiplicacao-10);}
	        soma = soma + multiplicacao;
	        if (peso == 2) {
	            peso = 1;
	        } else {
	            peso = 2;
	        }
	        contador = contador - 1;
	    }
	    int digito = 10 - (soma % 10);
	    if (digito == 10) digito = 0;

	    return digito;
	}
}
