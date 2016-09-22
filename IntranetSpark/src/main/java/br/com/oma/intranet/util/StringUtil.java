package br.com.oma.intranet.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil extends EnvioEmail {

	private static final long serialVersionUID = 1218125031520793273L;

	public static String trataNomeComposto(String nome) {
		nome = nome.toLowerCase();
		StringBuffer res = new StringBuffer();
		String[] strArr = nome.split(" ");
		for (String str : strArr) {
			if (!str.isEmpty()) {
				if (str.equals("ii") || str.equals("iii") || str.equals("iv") || str.equals("vi") || str.equals("vii")
						|| str.equals("viii") || str.equals("ix") || str.equals("xi") || str.equals("xii")
						|| str.equals("xiii") || str.equals("xiv") || str.equals("xv") || str.equals("x")) {
					char[] stringArray = str.trim().toCharArray();
					str = new String(stringArray);
					str = str.toUpperCase();
					res.append(str).append(" ");
				} else {
					char[] stringArray = str.trim().toCharArray();
					stringArray[0] = Character.toUpperCase(stringArray[0]);
					str = new String(stringArray);
					res.append(str).append(" ");
				}
			}
		}
		nome = res.toString().trim();
		return nome;
	}

	public List<String> trataAtributosAD(String atributos) {
		List<String> lstAtributos = new ArrayList<>();
		String[] strArr = atributos.split("CN=");
		for (String str : strArr) {
			if (!str.isEmpty()) {
				atributos = str.substring(str.indexOf("CN=") + 1, str.indexOf(","));
				lstAtributos.add(atributos);
			}
		}
		return lstAtributos;
	}

	public static String trataNomeCalendario(String nome) {
		StringBuffer res = new StringBuffer();
		if (nome != null && !nome.trim().isEmpty()) {
			String[] strArr = nome.split(" ");
			if (strArr.length > 1) {
				res.append(strArr[0] + " " + strArr[1]);
			} else if (strArr.length == 1) {
				res.append(strArr[0]);
			}
			return res.toString();
		} else {
			return "";
		}
	}
}