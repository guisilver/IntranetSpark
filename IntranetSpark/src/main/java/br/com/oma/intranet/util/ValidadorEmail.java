package br.com.oma.intranet.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.validator.ValidatorException;

public class ValidadorEmail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4325033621354622517L;

	public boolean validaEmail(String email) throws ValidatorException {
		boolean valida = true;
		if (!email.equals("") & email.toString().length() > 0) {
			String express = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern part = Pattern.compile(express, Pattern.CASE_INSENSITIVE);
			Matcher matcher = part.matcher(email.toString());
			if (!matcher.matches()) {
				valida = false;
			}
		}
		return valida;
	}
	
	public boolean validaInteiro(String inteiro){
		boolean valida = true;
		
		if(!inteiro.trim().equals("") & inteiro.toString().length() > 0){
			String express = "[0-9]*";
			Pattern part = Pattern.compile(express, Pattern.CASE_INSENSITIVE);
			Matcher matcher = part.matcher(inteiro.toString());
			if (!matcher.matches()) {
				valida = false;
			}
		}
		return valida;
	}
}
