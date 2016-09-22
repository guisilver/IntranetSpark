package br.com.oma.intranet.filters;

public class ConexaoGeral {
	//BASE PARA ENVIO DE EMAIL
	static final String email = "email";
	
	//BASE SERVIDOR LOCAL
	static final String banco = "local";
	static final String persistence = "persistLocal";

	//BASE SERVIDOR PRODUCAO
/*	static final String banco = "producao";
	static final String persistence = "persistProducao";*/

	public String getBanco() {
		return banco;
	}

	public String getPersistence() {
		return persistence;
	}

	public String getEmail(){
		return email;
	}
	
}
