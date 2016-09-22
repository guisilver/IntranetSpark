package br.com.oma.intranet.util;

import java.io.Serializable;

public class AuxAtbifon implements Serializable{

	private static final long serialVersionUID = 1L;

	public String listarTelEmail(int codigo) {

		switch (codigo) {
		case 1:
			return "Telefone comercial";
		case 2:
			return "Fax comercial";
		case 3:
			return "Telefone residencial";
		case 4:
			return "Fax residencial";
		case 5:
			return "Celular";
		case 6:
			return "E-mail";
		case 7:
			return "Pager";
		case 8:
			return "Outros";
		case 9:
			return "Tefefone";
		case 10:
			return "Zelador";
		case 11:
			return "Portaria";
		case 12:
			return "Nextel";
		case 13:
			return "Inquilino";
		case 14:
			return "Gerente Predial";
		case 15:
			return "Recado";
		case 16:
			return "Proprietario";
		case 17:
			return "Morador";
		case 18:
			return "Fax";
		case 19:
			return "Residencial/Coml";
		case 20:
			return "Portaria";
		case 21:
			return "Administração";
		case 22:
			return "Escritório";
		}
		return null;
	}
}
