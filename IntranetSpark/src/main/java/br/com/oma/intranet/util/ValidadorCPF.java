package br.com.oma.intranet.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ValidadorCPF")
public class ValidadorCPF implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object val) throws ValidatorException {
		String value = String.valueOf(val);
		if (!value.equals("")) {
			String strCpf = (String) value;
			strCpf = strCpf.trim();
			strCpf = strCpf.replace("-", "");
			strCpf = strCpf.replace(".", "");

			if (strCpf.equals("00000000000") || strCpf.equals("11111111111") || strCpf.equals("22222222222")
					|| strCpf.equals("33333333333") || strCpf.equals("44444444444") || strCpf.equals("55555555555")
					|| strCpf.equals("66666666666") || strCpf.equals("77777777777") || strCpf.equals("88888888888")
					|| strCpf.equals("99999999999") || (strCpf.length() != 11)) {

				FacesMessage mensagem = new FacesMessage("O cpf não é válido!");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(mensagem);

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
					FacesMessage mensagem = new FacesMessage("O cpf não é válido!");
					mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(mensagem);
				}
			}
		}
	}
	
	public boolean ValidaCPF(String strCpf) {
		if (strCpf.contains("-")) {
			strCpf = strCpf.replace("-", "");
		}
		if (strCpf.contains(".")) {
			strCpf = strCpf.replace(".", "");
		}
		if (strCpf.equals("00000000000") || strCpf.equals("11111111111")
				|| strCpf.equals("22222222222") || strCpf.equals("33333333333")
				|| strCpf.equals("44444444444") || strCpf.equals("55555555555")
				|| strCpf.equals("66666666666") || strCpf.equals("77777777777")
				|| strCpf.equals("88888888888") || strCpf.equals("99999999999")
				|| (strCpf.length() != 11)) {
			return (false);
		} else {

			int d1, d2;
			int digito1, digito2, resto;
			int digitoCPF;
			String nDigResult;

			d1 = d2 = 0;
			digito1 = digito2 = resto = 0;
			for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
				digitoCPF = Integer.valueOf(
						strCpf.substring(nCount - 1, nCount)).intValue();
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

			String nDigVerific = strCpf.substring(strCpf.length() - 2,
					strCpf.length());
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
			return nDigVerific.equals(nDigResult);
		}
	}

}
