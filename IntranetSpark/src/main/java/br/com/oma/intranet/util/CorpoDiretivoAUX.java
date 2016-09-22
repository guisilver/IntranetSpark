package br.com.oma.intranet.util;

import java.io.Serializable;

import org.primefaces.context.RequestContext;

import br.com.oma.sigadm.entidades.intra_ilclient;
import br.com.oma.sigadm.entidades.intra_ilmunic;
import br.com.oma.sigadm.entidades.intra_ilograd;

public class CorpoDiretivoAUX implements Serializable{

	private static final long serialVersionUID = 1L;

	public String retornaNome(String nome, int funcao){
		String nomeFinal="";
		String nome1 = nome.replace("=", "");
		String nome2 = nome1.replace("*", "");
		if(nome.length() < 30){
			if(funcao < 2){
				nomeFinal = nome2 + "*****";
			}else{
				nomeFinal = nome2 + "===";
			}
			return nomeFinal;
		}else if(nome.length() < 31){
			if(funcao < 2){
				nomeFinal = nome2 + "*****";
			}else{
				nomeFinal = nome2 + "===";
			}
			return nomeFinal;
		}else if(nome.length() < 32){
			if(funcao < 2){
				nomeFinal = nome2 + "****";
			}else{
				nomeFinal = nome2 + "===";
			}
			return nomeFinal;
		}else if(nome.length() < 33){
			if(funcao < 2){
				nomeFinal = nome2 + "***";
			}else{
				nomeFinal = nome2 + "===";
			}
			return nomeFinal;
		}else if(nome.length() < 34){
			if(funcao < 2){
				nomeFinal = nome2 + "**";
			}else{
				nomeFinal = nome2 + "==";
			}
			return nomeFinal;
		}else if(nome.length() < 35){
			if(funcao < 2){
				nomeFinal = nome2 + "*";
			}else{
				nomeFinal = nome2 + "=";
			}
			return nomeFinal;
		}else if(nome.length() == 35){
			return nome;
		}else{
			return nome;
		}
	}
	
	public String retornaCpfCnpj(String valor, int valida){
		if(valida == 1){
			if(valor.length() < 11){
				return valor = "0" + valor;
			}else if(valor.length() < 10){
				return valor = "00" + valor;
			}else if(valor.length() < 9){
				return valor = "000" + valor;
			}else if(valor.length() < 8){
				return valor = "0000" + valor;
			}else if(valor.length() < 7){
				return valor = "00000" + valor;
			}else if(valor.length() < 6){
				return valor = "000000" + valor;
			}else if(valor.length() < 5){
				return valor = "0000000" + valor;
			}else if(valor.length() < 4){
				return valor = "00000000" + valor;
			}else if(valor.length() < 3){
				return valor = "000000000" + valor;
			}else if(valor.length() < 2){
				return valor = "0000000000" + valor;
			}else if(valor.length() == 1){
				return valor = "0000000000" + valor;
			}
			else{
				return valor;
			}
		}else{
			if(valor.length() < 14){
				return valor = "0" + valor;
			}else if(valor.length() < 13){
				return valor = "00" + valor;
			}else if(valor.length() < 12){
				return valor = "000" + valor;
			}else if(valor.length() < 11){
				return valor = "0000" + valor;
			}else if(valor.length() < 10){
				return valor = "00000" + valor;
			}else if(valor.length() < 9){
				return valor = "000000" + valor;
			}else if(valor.length() < 8){
				return valor = "0000000" + valor;
			}else if(valor.length() < 7){
				return valor = "00000000" + valor;
			}else if(valor.length() < 6){
				return valor = "000000000" + valor;
			}else if(valor.length() < 5){
				return valor = "0000000000" + valor;
			}else if(valor.length() < 4){
				return valor = "00000000000" + valor;
			}else if(valor.length() < 3){
				return valor = "000000000000" + valor;
			}else if(valor.length() < 2){
				return valor = "0000000000000" + valor;
			}else if(valor.length() == 1){
				return valor = "0000000000000" + valor;
			}else{
				return valor;
			}
		}
	}
	
	public boolean validadorCampos(intra_ilclient clienteBEAN, intra_ilograd logradouroBEAN, intra_ilmunic cidadeBEAN, String cep){
		boolean valida = true;
		if(clienteBEAN.getNome() == null || clienteBEAN.getNome().trim().isEmpty()){
			RequestContext.getCurrentInstance().execute("document.getElementById('frmCD:nomeCliOutro').style.borderColor='#cd0a0a';");
			RequestContext.getCurrentInstance().execute("document.getElementById('frmCD:nomeCli3').style.borderColor='#cd0a0a';");
			valida = false;
		}else if(clienteBEAN.getSexo() == null || clienteBEAN.getSexo().trim().isEmpty()){
			RequestContext.getCurrentInstance().execute("document.getElementById('frmCD:somSexo3').style.borderColor='#cd0a0a';");
			valida = false;
		}else if(clienteBEAN.getTipoPessoa() == null || clienteBEAN.getTipoPessoa().trim().isEmpty()){
			RequestContext.getCurrentInstance().execute("document.getElementById('frmCD:somTipo3').style.borderColor='#cd0a0a';");
			valida = false;
		}else if(clienteBEAN.getEstadoCivil() < 1){
			RequestContext.getCurrentInstance().execute("document.getElementById('frmCD:somEstadoCivel3').style.borderColor='#cd0a0a';");
			valida = false;
		}else if(logradouroBEAN.getCodigo() == null || logradouroBEAN.getCodigo().trim().isEmpty()){
			RequestContext.getCurrentInstance().execute("document.getElementById('frmCD:somLogradouro3').style.borderColor='#cd0a0a';");
			valida = false;
		}else if(clienteBEAN.getEndereco() == null || clienteBEAN.getEndereco().trim().isEmpty()){
			RequestContext.getCurrentInstance().execute("document.getElementById('frmCD:ipEndereco3').style.borderColor='#cd0a0a';");
			valida = false;
		}else if(cidadeBEAN.getCodigo() < 1){
			RequestContext.getCurrentInstance().execute("document.getElementById('frmCD:somCidade3').style.borderColor='#cd0a0a';");
			valida = false;
		}else if(cep == null || cep.trim().isEmpty()){
			RequestContext.getCurrentInstance().execute("document.getElementById('frmCD:ipmCep3').style.borderColor='#cd0a0a';");
			valida = false;
		}
		return valida;
	}
}
