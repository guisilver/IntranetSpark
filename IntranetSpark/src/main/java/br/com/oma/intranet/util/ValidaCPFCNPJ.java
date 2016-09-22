package br.com.oma.intranet.util;

import java.io.Serializable;

public class ValidaCPFCNPJ extends Mensagens implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	public boolean validaCPF(String value) {
		boolean valida = true;
		if (!value.equals("")) {
			String strCpf = (String) value;
			strCpf = strCpf.trim();
			strCpf = strCpf.replace("-", "");
			strCpf = strCpf.replace(".", "");

			if (strCpf.equals("00000000000") || strCpf.equals("11111111111") || strCpf.equals("22222222222")
					|| strCpf.equals("33333333333") || strCpf.equals("44444444444") || strCpf.equals("55555555555")
					|| strCpf.equals("66666666666") || strCpf.equals("77777777777") || strCpf.equals("88888888888")
					|| strCpf.equals("99999999999") || (strCpf.length() != 11)) {
				valida = false;
			} else {

				int d1, d2;
				int digito1, digito2, resto;
				int digitoCPF;
				String nDigResult;

				d1 = d2 = 0;
				digito1 = digito2 = resto = 0;
				for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
					digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();
					d1 = d1 + (11 - nCount) * digitoCPF;
					d2 = d2 + (12 - nCount) * digitoCPF;
				}
				resto = (d1 % 11);
				if (resto < 2) {
					digito1 = 0;
				} else {
					digito1 = 11 - resto;
					d2 += 2 * digito1;
				}
				resto = (d2 % 11);
				if (resto < 2) {
					digito2 = 0;
				} else {
					digito2 = 11 - resto;
				}

				String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());
				nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
				if (!nDigVerific.equals(nDigResult)) {

					valida = false;
				} else {
					valida = true;
				}
			}
		}
		return valida;
	}

	private int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public boolean validaCNPJ(String cnpj) {
		String cnpj1 = cnpj.replace(".", "");
		String cnpj2 = cnpj1.replace("/", "");
		String cnpj3 = cnpj2.replace("-", "");
		if ((cnpj3 == null) || (cnpj3.length() != 14)) {
			return false;
		} else {
			Integer digito1 = calcularDigito(cnpj3.substring(0, 12), pesoCNPJ);
			Integer digito2 = calcularDigito(cnpj3.substring(0, 12) + digito1, pesoCNPJ);
			if (cnpj3.equals(cnpj3.substring(0, 12) + digito1.toString() + digito2.toString()) == false) {
				return false;
			} else {
				return true;
			}
		}
	}

}
